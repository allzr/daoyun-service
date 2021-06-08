package com.fzu.edu.daoyun.util;

import com.fzu.edu.daoyun.entity.Url;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientTest {

    @Test
    void doGetToken() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String token="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";
        HttpGet httpGet = new HttpGet("https://api.github.com/user");
        httpGet.setHeader("Authorization","token "+token);
        // 发送了一个http请求
        CloseableHttpResponse response = httpclient.execute(httpGet);
        System.out.println(response.getStatusLine().getStatusCode());
        // 如果响应200成功,解析响应结果
        if(response.getStatusLine().getStatusCode()==200){
            // 获取响应的内容
            HttpEntity responseEntity = response.getEntity();
            String res=EntityUtils.toString(responseEntity);
            Map<String, String> responseMap = HttpClient.getMapByJson(res);
        }
    }
}