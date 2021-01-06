package com.cloudinteractive.core.config;

import com.cloudinteractive.core.filter.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfiguration {
  @Bean
  public RequestLoggingFilter requestLoggingFilter() {
    RequestLoggingFilter loggingFilter = new RequestLoggingFilter();
    loggingFilter.setIncludeClientInfo(false);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setIncludeHeaders(false);
    loggingFilter.setMaxPayloadLength(9999);//會去cache一個空間 要注意大小
    return loggingFilter;
  }

  @Bean
  public FilterRegistrationBean<RequestLoggingFilter> loggingFilterRegistration() {
    FilterRegistrationBean<RequestLoggingFilter> registration = new FilterRegistrationBean<>(requestLoggingFilter());
    registration.addUrlPatterns("/api/*"); //API pattern
    registration.addUrlPatterns("/test/*"); //test pattern
    return registration;
  }
}
