package com.cloudinteractive.base.service;

import com.alibaba.fastjson.JSONObject;
import com.cloudinteractive.base.model.HttpClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Slf4j
@Service
public class HttpClientService {
    /** 預設字符集 */
    public static final String DEFAULT_CHARSET = "UTF-8";

    @Autowired
    private CloseableHttpClient closeableHttpClient;

    @Autowired
    private RequestConfig config;

    public HttpClientResponse<JSONObject> doGet(String url) throws URISyntaxException, IOException {
        return this.doGet(url, null, null);
    }

    public <T> HttpClientResponse<T> doGetModel(String url, Class<T> clazz) throws IOException, URISyntaxException {
        HttpClientResponse<JSONObject> res = this.doGet(url);

        return new HttpClientResponse<>(res.getStatusCode(), res.getResponse().toJavaObject(clazz));
    }

    public HttpClientResponse<JSONObject> doGet(String url, JSONObject requestParameter, JSONObject httpHeader) throws URISyntaxException, IOException {
        JSONObject logData = new JSONObject();
        logData.put("url", url);
        logData.put("requestParameter", requestParameter);
        logData.put("httpHeader", httpHeader);
        log.debug(logData.toString());

        URIBuilder uriBuilder = new URIBuilder(url);

        if (requestParameter != null) {
            for (Map.Entry<String, Object> entry : requestParameter.entrySet()) {
                if (entry.getValue() == null) continue;
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        HttpGet httpGet = new HttpGet(uriBuilder.build().toString());
        httpGet.setConfig(config);
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
//        httpGet.addHeader(HttpHeaders.CONNECTION, "close");

        if (httpHeader != null) {
            for (Map.Entry<String, Object> entry : httpHeader.entrySet()) {
                if (entry.getValue() == null) continue;
                httpGet.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        CloseableHttpResponse response = this.closeableHttpClient.execute(httpGet);

        return convertHttpResponse(response);
    }

    public HttpClientResponse<JSONObject> doPost(String url, JSONObject requestParameter, JSONObject httpHeader, String contentType) throws IOException {
        JSONObject logData = new JSONObject();
        logData.put("url", url);
        logData.put("requestParameter", requestParameter);
        logData.put("httpHeader", httpHeader);
        logData.put("contentType", contentType);
        log.debug(logData.toString());

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, contentType);
//        httpPost.addHeader(HttpHeaders.CONNECTION, "close");

        if (httpHeader != null) {
            for (Map.Entry<String, Object> entry : httpHeader.entrySet()) {
                if (entry.getValue() == null) continue;
                httpPost.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        if (requestParameter != null) {
            String requestBody = JSONObject.toJSONString(requestParameter);
            StringEntity postEntity = new StringEntity(requestBody, "UTF-8");
            httpPost.setEntity(postEntity);
        }

        CloseableHttpResponse response = this.closeableHttpClient.execute(httpPost);

        return convertHttpResponse(response);
    }

    public HttpClientResponse<JSONObject> doPost(String url, JSONObject requestParameter, JSONObject httpHeader) throws IOException {
        return this.doPost(url, requestParameter, httpHeader, ContentType.APPLICATION_JSON.getMimeType());
    }

    public HttpClientResponse<JSONObject> doPost(String url) throws IOException {
        return this.doPost(url, null, null);
    }

    public HttpClientResponse<byte[]> doPostPDF(String url, JSONObject requestParameter, JSONObject httpHeader) throws IOException {
        JSONObject logData = new JSONObject();
        logData.put("url", url);
        logData.put("requestParameter", requestParameter);
        logData.put("httpHeader", httpHeader);
        log.debug(logData.toString());

        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
//        httpPost.addHeader(HttpHeaders.CONNECTION, "close");

        if (httpHeader != null) {
            for (Map.Entry<String, Object> entry : httpHeader.entrySet()) {
                if (entry.getValue() == null) continue;
                httpPost.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }

        if (requestParameter != null) {
            String requestBody = JSONObject.toJSONString(requestParameter);
            StringEntity postEntity = new StringEntity(requestBody, "UTF-8");
            httpPost.setEntity(postEntity);
        }

        CloseableHttpResponse response = this.closeableHttpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        response.getEntity().writeTo(baos);
        byte[] bytes = baos.toByteArray();
        Header contentType = response.getEntity().getContentType();
        String mimeType = contentType.getValue().split(";")[0].trim();

        HttpClientResponse<byte[]> res = new HttpClientResponse<>();
        res.setStatusCode(statusCode);
        res.setResponse(bytes);

        logData.clear();
        logData.put("statusCode", statusCode);
        logData.put("mimeType", mimeType);
        log.debug(logData.toString());

        if (MediaType.APPLICATION_JSON_VALUE.equals(mimeType)) {
            String resultData = new String(bytes, DEFAULT_CHARSET);
            logData.put("resultData", resultData);
            res.setStatusCode(404);
        }

        log.debug(logData.toString());

        return res;
    }

    public HttpClientResponse<JSONObject> doDelete(String url, JSONObject requestParameter, JSONObject httpHeader) throws IOException {
        JSONObject logData = new JSONObject();
        logData.put("url", url);
        logData.put("requestParameter", requestParameter);
        logData.put("httpHeader", httpHeader);
        log.debug(logData.toString());

        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
//        httpDelete.addHeader(HttpHeaders.CONNECTION, "close");

        if (httpHeader != null) {
            for (Map.Entry<String, Object> entry : httpHeader.entrySet()) {
                if (entry.getValue() == null) continue;
                httpDelete.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        if (requestParameter != null) {
            StringEntity input = new StringEntity(requestParameter.toJSONString(), ContentType.APPLICATION_JSON);
            httpDelete.setEntity(input);
        }

        CloseableHttpResponse response = this.closeableHttpClient.execute(httpDelete);

        return convertHttpResponse(response);
    }

    public HttpClientResponse<JSONObject> doPut(String url, JSONObject requestParameter, JSONObject httpHeader) throws IOException {
        JSONObject logData = new JSONObject();
        logData.put("url", url);
        logData.put("requestParameter", requestParameter);
        logData.put("httpHeader", httpHeader);
        log.debug(logData.toString());

        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(config);
        httpPut.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
//        httpPut.addHeader(HttpHeaders.CONNECTION, "close");

        if (httpHeader != null) {
            httpHeader.forEach((key, value) -> httpPut.addHeader(key, value.toString()));
        }

        if (requestParameter != null) {
            String requestBody = JSONObject.toJSONString(requestParameter);
            StringEntity postEntity = new StringEntity(requestBody, "UTF-8");
            httpPut.setEntity(postEntity);
        }

        CloseableHttpResponse response = this.closeableHttpClient.execute(httpPut);

        return convertHttpResponse(response);
    }





    /**
     * 將CloseableHttpResponse進行整理的通用方法
     * @param response CloseableHttpResponse
     * @return HttpClientResponse<JSONObject>
     * @throws IOException IOException
     */
    private HttpClientResponse<JSONObject> convertHttpResponse(CloseableHttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        String resultString = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        JSONObject resultData = new JSONObject();
        try {
            resultData = JSONObject.parseObject(resultString);
        } catch (Exception e) {
            resultData.put("resultString", resultString);
        }

        JSONObject logData = new JSONObject();
        logData.put("statusCode", statusCode);
        logData.put("resultData", resultData);
        log.debug(logData.toString());

        return new HttpClientResponse<>(statusCode, resultData);
    }

}
