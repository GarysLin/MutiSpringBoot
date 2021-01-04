package com.cloudinteractive.webapi.config;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.regex.Pattern;

@Slf4j
@Configuration
public class SwaggerConfig {
    @Value("${swagger.apiPackage}")
    private String apiPackage;

    @Value("${swagger.modelPackage}")
    private String modelPackage;

    @Bean
    public Docket newsApi(TypeResolver resolver) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
//				.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(apiPackage))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);

        List<Class<?>> resList = getClassesInPackage(modelPackage);
        for(Class<?> cls : resList) {
            docket.additionalModels(resolver.resolve(cls));
        }

        return docket;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("apiKey",
                authorizationScopes));
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().build();
    }

    @Bean
    ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Cathay Healthcare Management BFF API with Swagger")
                .description("Cathay Healthcare Management BFF API")
                .version("0.0.1")
                .build();
    }

    public static class ApplicationProperties {

        private String applicationName;

        public String getApplicationName() {
            return applicationName;
        }

        public void setApplicationName(String applicationName) {
            this.applicationName = applicationName;
        }

    }

    public static List<Class<?>> getClassesInPackage(String packageName) {
        Pattern pattern = Pattern.compile(".*\\$.*");
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replaceAll("\\.", File.separator);
        String[] classPathEntries = System.getProperty("java.class.path").split(
                System.getProperty("path.separator")
        );

        String name;
        for (String classpathEntry : classPathEntries) {
            if (classpathEntry.endsWith(".jar")) {
                File jar = new File(classpathEntry);
                try {
                    JarInputStream is = new JarInputStream(new FileInputStream(jar));
                    JarEntry entry;
                    while((entry = is.getNextJarEntry()) != null) {
                        name = entry.getName();
                        log.debug("name: " + name);
                        if (pattern.matcher(name).matches()) continue;
                        if (name.endsWith(".class")) {
                            if (name.contains(path) && name.endsWith(".class")) {
                                String classPath = name.substring(0, entry.getName().length() - 6);
                                log.debug("classPath: " + classPath);
                                classPath = classPath.replaceAll("BOOT-INF/classes/", "");
                                classPath = classPath.replaceAll("[\\|/]", ".");
                                classes.add(Class.forName(classPath));
                            }
                        }
                    }
                } catch (Exception ex) {
                    // Silence is gold
                }
            } else {
                try {
                    List<Class<?>> subClasses = getClassesInFile(classpathEntry, packageName);
                    classes.addAll(subClasses);
                } catch (Exception ex) {
                    // Silence is gold
                }
            }
        }
        log.debug("additionalModels: " + classes);

        return classes;
    }

    private static List<Class<?>> getClassesInFile(String classpathEntry, String packageName) {
        Pattern pattern = Pattern.compile(".*\\$.*");
        String path = packageName.replaceAll("\\.", File.separator);
        List<Class<?>> classes = new ArrayList<>();

        File base = new File(classpathEntry + File.separatorChar + path);
        for (File file : base.listFiles()) {
            String name = file.getName();
            if (pattern.matcher(name).matches()) continue;
            if (name.endsWith(".class")) {
                name = name.substring(0, name.length() - 6);
                try {
                    classes.add(Class.forName(packageName + "." + name));
                } catch (ClassNotFoundException e) {
                    log.error("class not found: " + packageName + "." + name);
                }
            } else {
                String subpackageName = packageName + "." + name;
                List<Class<?>> subClasses = getClassesInFile(classpathEntry, subpackageName);
                classes.addAll(subClasses);
            }
        }

        return classes;
    }
}
