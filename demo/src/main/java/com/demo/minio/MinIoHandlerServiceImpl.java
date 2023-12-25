//package com.demo.minio;
//
//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.core.util.StrUtil;
//import io.minio.*;
//import io.minio.errors.*;
//import io.minio.messages.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.*;
//import java.net.URL;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * MinIo handler interface impl
// */
//@Service
//@Slf4j
//public class MinIoHandlerServiceImpl implements MinIoHandlerService {
//
//    @Autowired
//    private MinioClient minioClient;
//
//    @Autowired
//    private MinIoAutoProperties gwMinIoAutoProperties;
//
//    @Override
//    public Boolean bucketExists(String bucketName) throws MyException {
//        try {
//            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
//        } catch (Exception e) {
////            log.error("minIo 检查是否存在异常:{}", e.getMessage());
//            System.out.println("minIo 检查是否存在异常:{}" + e.getMessage());
//            throw new MyException(ResultCode.MINIO_CHECK_BUCKET_EXIST_ERROR.getCode(), ResultCode.MINIO_CHECK_BUCKET_EXIST_ERROR.getMsg());
//        }
//    }
//
//
//    @Override
//    public String putObject(String objectName, InputStream inputStream, String contentType) throws MyException {
//        this.putInputStream(gwMinIoAutoProperties.getBucket(), objectName, inputStream, "application/octet-stream");
//        return gwMinIoAutoProperties.getBucket() + File.separator + objectName;
//    }
//
//    @Override
//    public String putObject(String objectName, byte[] bytes, String contentType) throws MyException {
//        // 开始上传
//        this.putBytes(gwMinIoAutoProperties.getBucket(), objectName, bytes, "application/octet-stream");
//        return gwMinIoAutoProperties.getBucket() + File.separator + objectName;
//    }
//
//    @Override
//    public String putObject(String objectName, MultipartFile file) throws MyException {
//        // 开始上传
//        this.putMultipartFile(gwMinIoAutoProperties.getBucket(), objectName, file);
//        return gwMinIoAutoProperties.getBucket() + File.separator + objectName;
//    }
//
//    @Override
//    public String putObject(String bucketName, String objectName, MultipartFile file) throws MyException {
//        this.putMultipartFile(bucketName, objectName, file);
//        return bucketName + File.separator + objectName;
//    }
//
//    @Override
//    public String putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws MyException {
//        this.putInputStream(bucketName, objectName, inputStream, "application/octet-stream");
//        return bucketName + File.separator + objectName;
//    }
//
//    @Override
//    public String putObject(String bucketName, String objectName, byte[] bytes, String contentType) throws MyException {
//        // 开始上传
//        this.putBytes(bucketName, objectName, bytes, "application/octet-stream");
//        return bucketName + File.separator + objectName;
//    }
//
//    @Override
//    public String putObject(String objectName, File file, String contentType) throws MyException {
//        // 开始上传
//        this.putFile(gwMinIoAutoProperties.getBucket(), objectName, file, "application/octet-stream");
//        return gwMinIoAutoProperties.getBucket() + File.separator + objectName;
//    }
//
//    @Override
//    public String putObject(String bucketName, String objectName, File file, String contentType) throws MyException {
//        // 开始上传
//        this.putFile(bucketName, objectName, file, "application/octet-stream");
//        return bucketName + File.separator + objectName;
//    }
//
//    @Override
//    public Boolean checkFileIsExist(String objectName) throws MyException {
//        return this.checkFileIsExist(gwMinIoAutoProperties.getBucket(), objectName);
//    }
//
//    @Override
//    public Boolean checkFolderIsExist(String folderName) throws MyException {
//        return this.checkFolderIsExist(gwMinIoAutoProperties.getBucket(), folderName);
//    }
//
//    @Override
//    public Boolean checkFileIsExist(String bucketName, String objectName) throws MyException {
//        boolean exist = true;
//        try {
//            minioClient.statObject(
//                    StatObjectArgs.builder().bucket(bucketName).object(objectName).build()
//            );
//        } catch (Exception e) {
//            exist = false;
//        }
//        return exist;
//    }
//
//    @Override
//    public Boolean checkFolderIsExist(String bucketName, String folderName) throws MyException {
//        boolean exist = false;
//        try {
//            Iterable<Result<Item>> results = minioClient.listObjects(
//                    ListObjectsArgs
//                            .builder()
//                            .bucket(gwMinIoAutoProperties.getBucket())
//                            .prefix(folderName).recursive(false).build());
//            for (Result<Item> result : results) {
//                Item item = result.get();
//                if (item.isDir() && folderName.equals(item.objectName())) {
//                    exist = true;
//                }
//            }
//        } catch (Exception e) {
//            exist = false;
//        }
//        return exist;
//    }
//
//    @Override
//    public InputStream getObject(String objectName) throws MyException {
//        return this.getObject(gwMinIoAutoProperties.getBucket(), objectName);
//    }
//
//    @Override
//    public InputStream getObject(String bucketName, String objectName) throws MyException {
//        try {
//            return minioClient
//                    .getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
//        } catch (Exception e) {
////            System.out.println("minIo 获取文件流异常 objectName:{},bucketName:{},异常:{}," + objectName, bucketName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    @Override
//    public InputStream getObjectByUrl(String url) throws MyException {
//        try {
//            return new URL(url).openStream();
//        } catch (IOException e) {
////            log.error("minIo 获取文件流异常 url:{},异常:{},", url, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    @Override
//    public Optional<Bucket> getBucket(String bucketName) throws MyException {
//        try {
//            return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
//        } catch (Exception e) {
////            log.error("minIo 获取桶信息异常 bucketName:{},异常:{},", bucketName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    @Override
//    public List<String> getFoldersByDir(String folderName) throws MyException {
//        List<String> objectNames = new ArrayList<>();
//        try {
//            Iterable<Result<Item>> results = minioClient.listObjects(
//                    ListObjectsArgs
//                            .builder()
//                            .bucket(gwMinIoAutoProperties.getBucket())
//                            .prefix(folderName).recursive(false).useUrlEncodingType(false).build());
//            for (Result<Item> result : results) {
//                Item item = result.get();
//                if (item.isDir()) {
//                    String objectName = item.objectName();
//                    objectNames.add(objectName);
//                }
//            }
//        } catch (Exception e) {
////            log.error("minIo 删除文件信息异常 bucketName:{},folderName：{},异常:{},", gwMinIoAutoProperties.getBucket(), folderName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
////        log.info("获取到的文件夹列表：{}", JsonUtil.toJson(objectNames));
//        return objectNames;
//    }
//
//    @Override
//    public void removeObject(String objectName) throws MyException {
//        this.removeObject(gwMinIoAutoProperties.getBucket(), objectName);
//    }
//
//    @Override
//    public void removeObject(String bucketName, String objectName) throws MyException {
//        try {
//            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
//        } catch (Exception e) {
////            log.error("minIo 删除文件信息异常 bucketName:{},objectName：{},异常:{},", bucketName, objectName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    @Override
//    public void removeObjectsByDir(String folderName) throws MyException {
//        try {
//            Iterable<Result<Item>> results = minioClient.listObjects(
//                    ListObjectsArgs
//                            .builder()
//                            .bucket(gwMinIoAutoProperties.getBucket())
//                            .prefix(folderName).recursive(false).useUrlEncodingType(false).build());
//            for (Result<Item> result : results) {
//                Item item = result.get();
//                String objectName = item.objectName();
//                removeObject(objectName);
//            }
//        } catch (Exception e) {
////            log.error("minIo 删除文件信息异常 bucketName:{},folderName：{},异常:{},", gwMinIoAutoProperties.getBucket(), folderName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    @Override
//    public void configureBucketPolicy(String bucketName, String prefix, Integer days, String ruleName) throws MyException {
//        try {
//            //获取生命周期
//            LifecycleConfiguration bucketLifecycle = minioClient.getBucketLifecycle(
//                    GetBucketLifecycleArgs.builder().bucket(bucketName).build()
//            );
//            List<LifecycleRule> rules = new LinkedList<>();
//            if (ObjectUtil.isNotEmpty(bucketLifecycle)) {
//                bucketLifecycle.rules().forEach(lifecycleRule -> {
//                    if (!StrUtil.equals(lifecycleRule.id(), ruleName)) {
//                        rules.add(lifecycleRule);
//                    }
//                });
//            }
//            //配置生命周期
//            rules.add(
//                    new LifecycleRule(
//                            Status.ENABLED
//                            , null
//                            , new Expiration((ZonedDateTime) null, days, null)
//                            , new RuleFilter(prefix)
//                            , ruleName
//                            , null
//                            , null
//                            , null
//                    )
//            );
//            LifecycleConfiguration lifecycleConfiguration = new LifecycleConfiguration(rules);
//            //添加生命周期
//            minioClient.setBucketLifecycle(
//                    SetBucketLifecycleArgs.builder().bucket(bucketName).config(lifecycleConfiguration).build()
//            );
//        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException e) {
////            log.error(bucketName + "桶： " + prefix + "路径： 添加生命周期失败");
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    @Override
//    public List<String> listObjectNames(String prefix) throws MyException {
//        List<String> objectNames = new ArrayList<>();
//        try {
//            Iterable<Result<Item>> results = minioClient.listObjects(
//                    ListObjectsArgs
//                            .builder()
//                            .bucket(gwMinIoAutoProperties.getBucket())
//                            .prefix(prefix).recursive(false).useUrlEncodingType(false).build());
//            for (Result<Item> itemResult : results) {
//                Item item = itemResult.get();
//                String objectName = item.objectName();
//                objectNames.add(objectName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
////            log.error("minIo 列出对象名异常 bucketName:{},prefix：{},异常:{},", gwMinIoAutoProperties.getBucket(), prefix, e.getMessage());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//
//        return objectNames;
//    }
//
//    @Override
//    public List<String> listRecursiveObjectNames(String prefix) throws MyException {
//        List<String> objectNames = new ArrayList<>();
//        try {
//            Iterable<Result<Item>> results = minioClient.listObjects(
//                    ListObjectsArgs
//                            .builder()
//                            .bucket(gwMinIoAutoProperties.getBucket())
//                            .prefix(prefix).recursive(true).useUrlEncodingType(false)
//                            .maxKeys(1000).build());
//            for (Result<Item> itemResult : results) {
//                Item item = itemResult.get();
//                String objectName = item.objectName();
//                objectNames.add(objectName);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
////            log.error("minIo 列出对象名异常 bucketName:{},prefix：{},异常:{},", gwMinIoAutoProperties.getBucket(), prefix, e.getMessage());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//        return objectNames;
//    }
//
//    @Override
//    public void removeObjects(List<String> objectNames) throws MyException {
//        try {
////            log.info("删除的文件列表：{}", objectNames.toString());
//            List<DeleteObject> deleteObjects = new ArrayList<>(objectNames.size());
//            objectNames.forEach(objectName -> deleteObjects.add(new DeleteObject(objectName)));
//            Iterable<Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(gwMinIoAutoProperties.getBucket()).objects(deleteObjects).build());
//            for (Result<DeleteError> result : results) {
////                log.error(result.get().objectName(), result.get().message());
//            }
//        } catch (Exception e) {
////            log.error("minIo 删除文件信息异常 bucketName:{},objectName：{},异常:{},", gwMinIoAutoProperties.getBucket(), objectNames.toString(), e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    /**
//     * 上传MultipartFile通用方法
//     *
//     * @param bucketName 桶名称
//     * @param objectName 文件名
//     * @param file       文件
//     */
//    private void putMultipartFile(String bucketName, String objectName, MultipartFile file) throws MyException {
//        InputStream inputStream = null;
//        try {
//            inputStream = file.getInputStream();
//            minioClient.putObject(
//                    PutObjectArgs.builder()
//                            .bucket(bucketName)
//                            .object(objectName)
//                            .contentType(file.getContentType())
//                            .stream(inputStream, inputStream.available(), -1)
//                            .build()
//            );
//        } catch (Exception e) {
////            log.error("minIo 上传文件信息异常 bucketName:{},objectName：{},异常:{},", bucketName, objectName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
////                    log.error("文件io错误", e);
//                }
//            }
//        }
//    }
//
//    /**
//     * 上传InputStream通用方法
//     *
//     * @param bucketName  桶名称
//     * @param objectName  文件名
//     * @param inputStream 文件流
//     */
//    private void putInputStream(String bucketName, String objectName, InputStream inputStream, String contentType) throws MyException {
//        try {
//            minioClient.putObject(
//                    PutObjectArgs.builder()
//                            .bucket(bucketName)
//                            .object(objectName)
//                            .contentType(contentType)
//                            .stream(inputStream, inputStream.available(), -1)
//                            .build()
//            );
//        } catch (Exception e) {
////            log.error("minIo 上传文件信息流异常 bucketName:{},objectName：{},异常:{},", bucketName, objectName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        }
//    }
//
//    /**
//     * 上传 bytes 通用方法
//     *
//     * @param bucketName 桶名称
//     * @param objectName 文件名
//     * @param bytes      字节编码
//     */
//    private void putBytes(String bucketName, String objectName, byte[] bytes, String contentType) throws MyException {
//        // 字节转文件流
//        InputStream inputStream = new ByteArrayInputStream(bytes);
//        try {
//            minioClient.putObject(
//                    PutObjectArgs.builder()
//                            .bucket(bucketName)
//                            .object(objectName)
//                            .contentType(contentType)
//                            .stream(inputStream, inputStream.available(), -1)
//                            .build()
//            );
//        } catch (Exception e) {
////            log.error("minIo 上传文件字节流信息异常 bucketName:{},objectName：{},异常:{},", bucketName, objectName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        } finally {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
////                log.error("文件io错误", e);
//            }
//        }
//    }
//
//    /**
//     * 上传 file 通用方法
//     *
//     * @param bucketName  桶名称
//     * @param objectName  文件名
//     * @param file        文件
//     * @param contentType 内容类型
//     */
//    private void putFile(String bucketName, String objectName, File file, String contentType) throws MyException {
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream(file);
//            minioClient.putObject(
//                    PutObjectArgs.builder()
//                            .bucket(bucketName)
//                            .object(objectName)
//                            .contentType(contentType)
//                            .stream(fileInputStream, fileInputStream.available(), -1)
//                            .build()
//            );
//        } catch (Exception e) {
////            log.error("minIo 上传文件信息异常 bucketName:{},objectName：{},异常:{},", bucketName, objectName, e.fillInStackTrace());
//            throw new MyException(ResultCode.ERROR.getCode(), e.getMessage());
//        } finally {
//            if (fileInputStream != null) {
//                try {
//                    fileInputStream.close();
//                } catch (IOException e) {
////                    log.error("文件io错误", e);
//                }
//            }
//        }
//    }
//}