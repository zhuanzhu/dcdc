package com.egeo.components.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientSingleton {

    private static final  int connectTimeout = 5000; // 连接超时时间（毫秒）
    private static final int socketTimeout = 10000; // 套接字超时时间（毫秒）

    // 使用volatile关键字确保多线程环境下的可见性
    // 使用private和static确保只有一个实例且为全局可访问
    private static volatile CloseableHttpClient httpClientInstance;

    // 私有构造函数防止外部实例化
    private HttpClientSingleton() {}

    // 提供全局访问点来获取实例
    public static CloseableHttpClient getInstance(int connectTimeout, int socketTimeout) {
        if (httpClientInstance == null) {
            synchronized (HttpClientSingleton.class) {
                if (httpClientInstance == null) {
                    RequestConfig requestConfig = RequestConfig.custom()
                            .setConnectTimeout(connectTimeout)
                            .setSocketTimeout(socketTimeout)
                            .build();
                    httpClientInstance = HttpClients.custom()
                            .setDefaultRequestConfig(requestConfig)
                            .build();
                }
            }
        }
        return httpClientInstance;
    }

    // 提供一个方法来发送POST请求（可选，根据需求添加）
    public static String doPostByJson(String url, String charset, String content) throws IOException {
        // 这里可以根据需要调整超时时间
        CloseableHttpClient httpClient = getInstance(connectTimeout, socketTimeout);
        String result = null;

        HttpPost post = new HttpPost(url);
        StringEntity se = new StringEntity(content, charset);
        se.setContentType("application/json; charset=" + charset);
        post.setEntity(se);

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getEntity() != null) {
                result = EntityUtils.toString(response.getEntity(), charset);
            }
        }

        // 注意：这里不关闭httpClient，因为它是单例的，会在整个应用程序生命周期中重复使用

        return result;
    }

    // 提供一个方法来关闭HttpClient（通常在应用程序结束时调用）
    public static void shutdown() throws IOException {
        if (httpClientInstance != null) {
            httpClientInstance.close();
            httpClientInstance = null; // 可选，用于明确表示实例已被关闭
        }
    }

    // 主方法（仅用于测试）
    public static void main(String[] args) {
        try {
            String url = "http://example.com/api";
            String charset = "UTF-8";
            String content = "{\"key\":\"value\"}";

            String response = doPostByJson(url, charset, content);
            System.out.println("Response: " + response);

        } catch (IOException e) {
            e.printStackTrace(); // 在实际应用中，应使用日志记录框架记录错误
        } finally {
            // 在应用程序结束时关闭HttpClient
            try {
                shutdown();
            } catch (IOException e) {
                e.printStackTrace(); // 在实际应用中，应使用日志记录框架记录错误
            }
        }
    }
}
