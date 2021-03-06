package com.iptv.core.hls.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * HTTP辅助
 */
public final class HttpHelper {
    private static OkHttpClient sClient = null;

    /**
     * GET
     */
    public static synchronized InputStream get(
            String url, Map<String, String> properties) throws IOException {
        Response response = call(createGetRequest(url, properties));
        if (!response.isSuccessful()) {
            response.close();

            throw new IOException("access " + url + " fail, " + response.message());
        }

        return response.body().byteStream();
    }

    /**
     * 新的会话
     */
    private static Response call(Request request) throws IOException {
        /**
         * 复用
         */
        if (sClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            /**
             * 全局设置
             */
            builder.followRedirects(true);
            builder.connectTimeout(3, TimeUnit.SECONDS);
            builder.readTimeout(5, TimeUnit.SECONDS);
            builder.writeTimeout(5, TimeUnit.SECONDS);

            sClient = builder.build();
        }

        return sClient.newCall(request).execute();
    }

    /**
     * 创建GET请求
     */
    private static Request createGetRequest(String url, Map<String, String> property) {
        Request.Builder builder = new Request.Builder();

        builder.url(url);
        builder.get();

        if (property != null && !property.isEmpty()) {
            for (Map.Entry<String, String> entry : property.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        return builder.build();
    }

    /**
     * 构造函数（私有属性，不允许创建实例）
     */
    private HttpHelper() {
        /**
         * nothing
         */
    }
}
