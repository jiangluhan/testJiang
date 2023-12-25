package com.demo.httpclient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;

public class httpClientFile {

    /**
     * httpclient传输大文件
     * @param file
     * @param url
     * @return
     * @throws IOException
     */
    public static String getTestHttpClient(MultipartFile file, String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
//        RequestConfig requestConfig = RequestConfig.custom()
////                .setConnectionRequestTimeout(10000)
////                .setConnectTimeout(5000)
//                .build();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        // 解决中文文件名乱码问题
        entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entityBuilder.setCharset(Consts.UTF_8);
        ContentType contentType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), Consts.UTF_8);
        entityBuilder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, file.getOriginalFilename());
        httpPost.setEntity(entityBuilder.build());
//        httpPost.setConfig(requestConfig);
        HttpResponse execute = httpclient.execute(httpPost);
//        System.out.println(execute.getEntity());
        String response = EntityUtils.toString(execute.getEntity());
        return response;
    }

    public static String uploadFile(String url, MultipartFile multipartFile) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        String responseStr;
        try {

            System.out.println("this file ContentType:" + multipartFile.getContentType());
            //builder.addBinaryBody("file", multipartFile.getInputStream(), ContentType.MULTIPART_FORM_DATA, multipartFile.getOriginalFilename());
            builder.addBinaryBody("file", multipartFile.getInputStream(), ContentType.create(multipartFile.getContentType()), multipartFile.getOriginalFilename());

            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            // builder.setMode(HttpMultipartMode.RFC6532);
            builder.setCharset(Charset.forName("UTF-8"));


            HttpEntity multipart = builder.build();
            post.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            responseStr= EntityUtils.toString(responseEntity, "UTF-8");
            System.out.println("requestUrl is " + url + ", response is " + responseStr);

        } catch (Exception e) {
            return null;
        }
        return responseStr;
    }
}
