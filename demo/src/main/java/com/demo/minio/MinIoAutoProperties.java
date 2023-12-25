//package com.demo.minio;
//
//import org.springframework.beans.factory.annotation.Value;
//
///**
// * MinIo 配置属性
// */
//public class MinIoAutoProperties {
//
//    /**
//     * 服务地址
//     */
//    @Value("${minio.url}")
//    private String url;
//
//    /**
//     * 认证账户
//     */
//    @Value("${minio.accessKey}")
//    private String accessKey;
//
//    /**
//     * 认证密码
//     */
//    @Value("${minio.secretKey}")
//    private String secretKey;
//
//    /**
//     * 桶名称, 优先级最低
//     */
//    @Value("${minio.bucket}")
//    private String bucket;
//
//    /**
//     * 公共桶名称, 优先级最低
//     */
//    @Value("${minio.publicBucket}")
//    private String publicBucket;
//
//    /**
//     * 桶不在的时候是否新建桶
//     */
//    @Value("${minio.createBucket}")
//    private boolean createBucket = true;
//
//    /**
//     * 启动的时候检查桶是否存在
//     */
//    @Value("${minio.checkBucket}")
//    private boolean checkBucket = true;
//
//    /**
//     * 默认的过期时间
//     */
//    @Value("${minio.default-expire-time}")
//    private Integer defaultExpireTime;
//
//    /**
//     * 设置HTTP连接、写入和读取超时。值为0意味着没有超时
//     * HTTP连接超时，以毫秒为单位。
//     */
//    @Value("${minio.connectTimeout}")
//    private long connectTimeout;
//
//    /**
//     * 设置HTTP连接、写入和读取超时。值为0意味着没有超时
//     * HTTP写超时，以毫秒为单位。
//     */
//    @Value("${minio.writeTimeout}")
//    private long writeTimeout;
//
//    /**
//     * 设置HTTP连接、写入和读取超时。值为0意味着没有超时
//     * HTTP读取超时，以毫秒为单位。
//     */
//    @Value("${minio.readTimeout}")
//    private long readTimeout;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getAccessKey() {
//        return accessKey;
//    }
//
//    public void setAccessKey(String accessKey) {
//        this.accessKey = accessKey;
//    }
//
//    public String getSecretKey() {
//        return secretKey;
//    }
//
//    public void setSecretKey(String secretKey) {
//        this.secretKey = secretKey;
//    }
//
//    public String getBucket() {
//        return bucket;
//    }
//
//    public void setBucket(String bucket) {
//        this.bucket = bucket;
//    }
//
//    public String getPublicBucket() {
//        return publicBucket;
//    }
//
//    public void setPublicBucket(String publicBucket) {
//        this.publicBucket = publicBucket;
//    }
//
//    public boolean isCreateBucket() {
//        return createBucket;
//    }
//
//    public void setCreateBucket(boolean createBucket) {
//        this.createBucket = createBucket;
//    }
//
//    public boolean isCheckBucket() {
//        return checkBucket;
//    }
//
//    public void setCheckBucket(boolean checkBucket) {
//        this.checkBucket = checkBucket;
//    }
//
//    public Integer getDefaultExpireTime() {
//        return defaultExpireTime;
//    }
//
//    public void setDefaultExpireTime(Integer defaultExpireTime) {
//        this.defaultExpireTime = defaultExpireTime;
//    }
//
//    public long getConnectTimeout() {
//        return connectTimeout;
//    }
//
//    public void setConnectTimeout(long connectTimeout) {
//        this.connectTimeout = connectTimeout;
//    }
//
//    public long getWriteTimeout() {
//        return writeTimeout;
//    }
//
//    public void setWriteTimeout(long writeTimeout) {
//        this.writeTimeout = writeTimeout;
//    }
//
//    public long getReadTimeout() {
//        return readTimeout;
//    }
//
//    public void setReadTimeout(long readTimeout) {
//        this.readTimeout = readTimeout;
//    }
//}
