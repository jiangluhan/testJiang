//package com.demo.minio;
//
//import io.minio.*;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//
///**
// * Minio 自动配置
// */
//@Configuration
//@ConditionalOnClass(MinioClient.class)
//@EnableConfigurationProperties(MinIoAutoProperties.class)
//@Slf4j
//public class MinIoAutoConfiguration {
//    @Resource
//    private MinIoAutoProperties minIoAutoProperties;
//
//    @Bean("GwMinioClient")
//    public MinioClient minioClient() throws MyException {
////        log.info("开始初始化MinIoClient, url为{}, accessKey为:{}", minIoAutoProperties.getUrl(), minIoAutoProperties.getAccessKey());
//        MinioClient minioClient = MinioClient
//                .builder()
//                .endpoint(minIoAutoProperties.getUrl())
//                .credentials(minIoAutoProperties.getAccessKey(), minIoAutoProperties.getSecretKey())
//                .build();
//        /**
//         * 设置超时等时间
//         */
//        minioClient.setTimeout(
//                minIoAutoProperties.getConnectTimeout(),
//                minIoAutoProperties.getWriteTimeout(),
//                minIoAutoProperties.getReadTimeout()
//        );
//        // 校验文件桶是否存在
//        if (minIoAutoProperties.isCheckBucket()) {
//            //log.info("checkBucket为{}, 开始检测桶是否存在", minIoAutoProperties.isCheckBucket());
//            String bucketName = minIoAutoProperties.getBucket();
//            String publicBucketName = minIoAutoProperties.getPublicBucket() == null ? "my-bucketname": minIoAutoProperties.getPublicBucket();
//            if (!checkBucket(bucketName, minioClient)) {
//                //log.info("文件桶[{}]不存在, 开始检查是否可以新建桶", bucketName);
//                if (minIoAutoProperties.isCreateBucket()) {
//                    //log.info("createBucket为{},开始新建文件桶", minIoAutoProperties.isCreateBucket());
//                    createBucket(bucketName, minioClient);
//                }
//            }
//            if (!checkBucket(publicBucketName, minioClient)) {
//                //log.info("公共文件桶[{}]不存在, 开始检查是否可以新建桶", publicBucketName);
//                if (minIoAutoProperties.isCreateBucket()) {
//                    //log.info("createBucket为{},开始新建公共文件桶", minIoAutoProperties.isCreateBucket());
//                    createBucket(publicBucketName, minioClient);
//                    // 设置桶策略为public
//                    createBucketPolicy(new StringBuilder(), publicBucketName, minioClient);
//                }
//            }
//            //log.info("文件桶[{}]已存在, 公共文件桶[{}]已存在, minio客户端连接成功!", bucketName, publicBucketName);
//
//        }
//        return minioClient;
//    }
//
//    /**
//     * 检查桶是否存在
//     *
//     * @param bucketName  桶名称
//     * @param minioClient 连接client
//     * @return
//     */
//    private boolean checkBucket(String bucketName, MinioClient minioClient) throws MyException {
//        boolean isExists = false;
//        try {
//            isExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
//        } catch (Exception e) {
//            //log.error("minio 检查bucket：{}异常", bucketName, e);
//            throw new MyException(ResultCode.MINIO_CHECK_BUCKET_EXIST_ERROR.getCode(), ResultCode.MINIO_CHECK_BUCKET_EXIST_ERROR.getMsg());
//        }
//        return isExists;
//    }
//
//    /**
//     * 创建桶
//     *
//     * @param bucketName  桶名称
//     * @param minioClient 连接client
//     */
//    private void createBucket(String bucketName, MinioClient minioClient) throws MyException {
//        try {
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
//            //log.info("文件桶[{}]新建成功, minIo客户端已连接", bucketName);
//        } catch (Exception e) {
//            //log.error("minio 创建bucket：{}异常", bucketName, e);
//            throw new MyException(ResultCode.MINIO_CREATE_BUCKET_CODE_ERROR.getCode(), ResultCode.MINIO_CREATE_BUCKET_CODE_ERROR.getMsg());
//        }
//    }
//
//    /**
//     * 设置桶策略
//     * @param builder 策略json
//     * @param publicBucketName 公共桶名称
//     */
//    @SneakyThrows(Exception.class)
//    private static void createBucketPolicy(StringBuilder builder, String publicBucketName, MinioClient minioClient) {
//        if(builder.length()==0) {
//            builder = publicBucketPolicy(publicBucketName);
//        }
//        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(publicBucketName).config(builder.toString()).build());
//    }
//
//    /**
//     * 获取公共桶策略
//     * @param publicBucketName
//     * @return
//     */
//    private static StringBuilder publicBucketPolicy(String publicBucketName){
//        StringBuilder builder=new StringBuilder();
//        builder.append("{\n" +
//                "\"Version\": \"2012-10-17\",\n" +
//                "\"Statement\": [{\n" +
//                "   \"Effect\": \"Allow\",\n" +
//                "   \"Principal\": {\n" +
//                "       \"AWS\": [\"*\"]\n" +
//                "       },\n" +
//                "   \"Action\": [\"s3:ListBucketMultipartUploads\", \"s3:GetBucketLocation\", \"s3:ListBucket\"],\n" +
//                "   \"Resource\": [\"arn:aws:s3:::"+ publicBucketName +"\"]\n" +
//                "}, {\n" +
//                "   \"Effect\": \"Allow\",\n" +
//                "   \"Principal\": {\n" +
//                "       \"AWS\": [\"*\"]\n"+
//                "   },\n"+
//                "   \"Action\": [\"s3:ListMultipartUploadParts\", \"s3:PutObject\", \"s3:AbortMultipartUpload\", \"s3:DeleteObject\", \"s3:GetObject\"],\n"+
//                "   \"Resource\": [\"arn:aws:s3:::"+ publicBucketName +"/*\"]\n"+
//                "   }]\n"+
//                "}"
//        );
//        return builder;
//    }
//
//    /**
//     * 查看桶策略
//     * @param bucketName
//     * @return
//     */
//    @SneakyThrows(Exception.class)
//    public static String queryBucketPolicy(String bucketName, MinioClient minioClient) {
//        String bucketPolicy = minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
//        return bucketPolicy;
//    }
//}
