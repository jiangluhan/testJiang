import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Demo {

    public static void main(String[] args) throws Exception {

        String userName = "360";
        String secret = "FKqrybHLb5IxRHRI1QnRSAaryUiUQj4N";
//        String body = "{\n" +
//                "    \"objectId\": \"test-1\",\n" +
//                "    \"watermarkTypes\": [1]\n" +
//                "}";
//        String body = "{\n" +
//                "    \"objectIds\": [\n" +
//                "        \"1\",\n" +
//                "        \"2\"\n" +
//                "    ]\n" +
//                "}";
//        String body = "";
//        String body = "{'computerName':'test','sysiplist':'192.168.248.146','groupName':'未分组','systemName':'Windows 7 Enterprise','assetUserName':'licj','systemIntallTime':'2023-01-11 15:36:11','platform':'Windows','assetDepartmentName':'TEST-1111111111111','email':'test@360.cn'}";
//        String body = "{\"objectId\" : \"test-2\",\"assetNum\" : \"test1\",\"employeeId\" : \"蒋露寒\",\"employeeName\" :  \"test\",\"imei\" : \"861111155566332\",\"ip\" : \"192.168.31.126\",\"mac\" : \"be:b2:b5:8e:67:21\",\"attr\" : \"{\\\"computerName\\\":\\\"test\\\",\\\"sysiplist\\\":\\\"192.168.248.146\\\",\\\"groupName\\\":\\\"未分组\\\",\\\"systemName\\\":\\\"Windows 7 Enterprise\\\",\\\"assetUserName\\\":\\\"王宇2--------\\\",\\\"systemIntallTime\\\":\\\"2023-01-11 15:36:11\\\",\\\"platform\\\":\\\"Windows\\\",\\\"assetDepartmentName\\\":\\\"研发\\\",\\\"email\\\":\\\"test@360.cn\\\"}\"}";
//        String body = "{\"objectId\" : \"test-1\",\"attr\" : \"{\\\"computerName\\\":\\\"test\\\",\\\"sysiplist\\\":\\\"192.168.248.146\\\",\\\"groupName\\\":\\\"未分组\\\",\\\"systemName\\\":\\\"Windows 7 Enterprise\\\",\\\"assetUserName\\\":\\\"licj\\\",\\\"systemIntallTime\\\":\\\"2023-01-11 15:36:11\\\",\\\"platform\\\":\\\"Windows\\\",\\\"assetDepartmentName\\\":\\\"test-1111111111111111111\\\",\\\"email\\\":\\\"test@360.cn\\\"}\"}";
        String body = "{\"objectId\" : \"test-1\",\"assetNum\" : \"test1\",\"employeeId\" : \"test1\",\"employeeName\" :  \"test\",\"imei\" : \"861111155566332\",\"ip\" : \"192.168.31.126\",\"mac\" : \"be:b2:b5:8e:67:21\",\"attr\" : \"{\\\"computerName\\\":\\\"test\\\",\\\"sysiplist\\\":\\\"192.168.248.146\\\",\\\"groupName\\\":\\\"未分组\\\",\\\"systemName\\\":\\\"Windows 7 Enterprise\\\",\\\"assetUserName\\\":\\\"licj\\\",\\\"systemIntallTime\\\":\\\"2023-01-11 15:36:11\\\",\\\"platform\\\":\\\"Windows\\\",\\\"assetDepartmentName\\\":\\\"研发\\\",\\\"email\\\":\\\"test@360.cn\\\"}\"}";
        String origin = "http://192.168.1.210:36043";
        String url = "/api/traceability/v1/addTraceabilityObject";
//        String url = "/api/traceability/v1/updateTraceability";
//        String url = "/api/traceability/v1/bindTraceabilityObject";
//        String url = "/api/forensicService/v1/exportReport";
//        String url = "/api/appService/license/v1/checkLicense";
//        String url = "/api/traceability/v1/unbindClient";
        String method = "post";
        String bodyDigest = getBodyDigest(body);
        System.out.println("Digest: " + bodyDigest);

        String timeNow = getDateGMT();
        //String timeNow = "Tue, 27 Dec 2022 02:16:36 GMT";
        System.out.println("X-Date: " + timeNow);
        String auth = getAuth(method, url, bodyDigest, timeNow, secret, userName);
        System.out.println("Authorization: " + auth);
        Response resPost = sendPostRequest(auth, bodyDigest, timeNow, body, origin+url);
        String retPost = resPost.body().string();
        System.out.println("post res:" + retPost);
    }

    /**
     * 生成body的sha256加密串
     * @param body
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String getBodyDigest(String body) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] digestHash = digest.digest(body.getBytes(StandardCharsets.UTF_8));
        String bodyHash = Base64.getEncoder().encodeToString(digestHash);
        String bodyDigest = String.format("SHA-256=%s", bodyHash);
        return bodyDigest;
    }

    /**
     * 生成当前GMT时间，注意格式不能改变，必须形如：Wed, 14 Aug 2019 09:09:28 GMT
     * @return
     */
    private static String getDateGMT() {
        DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z",
                Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String timeNow = df.format(new Date());
        return timeNow;
    }

    /**
     * 生成auth认证
     * @return
     */
    private static String getAuth(String method, String url, String bodyDigest, String timeNow, String secret, String userName) throws NoSuchAlgorithmException, InvalidKeyException {
        // 拼装待签名的数据
        String signData = String.format("x-date: %s\n@request-target: %s %s\ndigest: %s", timeNow, method.toLowerCase(), url, bodyDigest);
        // 生成hmac签名
        Mac hmac = Mac.getInstance("HmacSHA384");
        hmac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA384"));
        byte[] hmacHash = hmac.doFinal(signData.getBytes(StandardCharsets.UTF_8));
        String hmacSign = Base64.getEncoder().encodeToString(hmacHash);

        String auth = String.format("hmac username=\"%s\", algorithm=\"hmac-sha384\", headers=\"x-date @request-target digest\", signature=\"%s\"", userName, hmacSign);
        return auth;
    }

    /**
     * 发送post请求 json传输
     * @return
     * @throws IOException
     *
     */
    private static Response sendPostRequest(String auth, String bodyDigest, String timeNow, String body, String originUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.addHeader("Authorization", auth);
        builder.addHeader("Digest", bodyDigest);
        builder.addHeader("X-Date", timeNow);
        // POST
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, body);
        Request requestPost = builder.url(originUrl).post(requestBody).build();
        Response resPost = client.newCall(requestPost).execute();
        return resPost;
    }
}