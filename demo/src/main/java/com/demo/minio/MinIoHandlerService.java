//package com.demo.minio;
//
//import io.minio.messages.Bucket;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Optional;
//
///**
// * MinIo handler interface
// */
//public interface MinIoHandlerService {
//
//    /**
//     * 判断桶是否存在
//     *
//     * @param bucketName bucket名称
//     * @return true存在，false不存在
//     */
//    Boolean bucketExists(String bucketName) throws Exception;
//
//    /**
//     * 上传文件
//     *
//     * @param objectName  文件名
//     * @param inputStream 文件流
//     * @param contentType 文件类型
//     * @return 文件url
//     */
//    String putObject(String objectName, InputStream inputStream, String contentType) throws Exception;
//
//    /**
//     * 上传bytes文件
//     *
//     * @param objectName  文件名
//     * @param bytes       字节
//     * @param contentType 文件类型
//     * @return 文件url
//     */
//    String putObject(String objectName, byte[] bytes, String contentType) throws Exception;
//
//    /**
//     * 上传File文件
//     *
//     * @param objectName  文件名
//     * @param file        文件
//     * @param contentType 文件类型
//     * @return 文件url
//     */
//    String putObject(String objectName, File file, String contentType) throws Exception;
//
//    /**
//     * 上传MultipartFile文件到全局默认文件桶下的folder文件夹下
//     *
//     * @param objectName 文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
//     * @param file       文件
//     * @return 文件对应的URL
//     */
//    String putObject(String objectName, MultipartFile file) throws Exception;
//
//    /**
//     * 上传MultipartFile文件到指定的文件桶下
//     *
//     * @param bucketName 桶名称
//     * @param objectName 文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
//     * @param file       文件
//     * @return 文件对应的URL
//     */
//    String putObject(String bucketName, String objectName, MultipartFile file) throws Exception;
//
//    /**
//     * 上传流到指定的文件桶下
//     *
//     * @param bucketName  桶名称
//     * @param objectName  文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
//     * @param inputStream 文件流
//     * @param contentType 文件类型, 例如 image/jpeg: jpg图片格式, 详细可看: https://www.runoob.com/http/http-content-type.html
//     * @return 文件对应的URL
//     */
//    String putObject(String bucketName, String objectName, InputStream inputStream, String contentType) throws Exception;
//
//    /**
//     * 上传流到指定的文件桶下
//     *
//     * @param bucketName  桶名称
//     * @param objectName  文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
//     * @param bytes       字节
//     * @param contentType 文件类型, 例如 image/jpeg: jpg图片格式, 详细可看: https://www.runoob.com/http/http-content-type.html
//     * @return 文件对应的URL
//     */
//    String putObject(String bucketName, String objectName, byte[] bytes, String contentType) throws Exception;
//
//    /**
//     * 上传File文件
//     *
//     * @param bucketName  文件桶
//     * @param objectName  文件名
//     * @param file        文件
//     * @param contentType 文件类型, 例如 image/jpeg: jpg图片格式, 详细可看: https://www.runoob.com/http/http-content-type.html
//     * @return 文件对应的URL
//     */
//    String putObject(String bucketName, String objectName, File file, String contentType) throws Exception;
//
//    /**
//     * 判断文件是否存在
//     *
//     * @param objectName 文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
//     * @return true存在, 反之
//     */
//    Boolean checkFileIsExist(String objectName) throws Exception;
//
//    /**
//     * 判断文件夹是否存在
//     *
//     * @param folderName 文件夹名称
//     * @return true存在, 反之
//     */
//    Boolean checkFolderIsExist(String folderName) throws Exception;
//
//    /**
//     * 判断文件是否存在
//     *
//     * @param bucketName 桶名称
//     * @param objectName 文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
//     * @return true存在, 反之
//     */
//    Boolean checkFileIsExist(String bucketName, String objectName) throws Exception;
//
//    /**
//     * 判断文件夹是否存在
//     *
//     * @param bucketName 桶名称
//     * @param folderName 文件夹名称
//     * @return true存在, 反之
//     */
//    Boolean checkFolderIsExist(String bucketName, String folderName) throws Exception;
//
//    /**
//     * 根据文件全路径获取文件流
//     *
//     * @param objectName 文件名称
//     * @return 文件流
//     */
//    InputStream getObject(String objectName) throws Exception;
//
//    /**
//     * 根据文件桶和文件全路径获取文件流
//     *
//     * @param bucketName 桶名称
//     * @param objectName 文件名
//     * @return 文件流
//     */
//    InputStream getObject(String bucketName, String objectName) throws Exception;
//
//    /**
//     * 根据url获取文件流
//     *
//     * @param url 文件对于URL
//     * @return 文件流
//     */
//    InputStream getObjectByUrl(String url) throws Exception;
//
//    /**
//     * 根据bucketName获取信息
//     *
//     * @param bucketName bucket名称
//     * @return 单个桶信息
//     */
//    Optional<Bucket> getBucket(String bucketName) throws Exception;
//
//    /**
//     * 根据文件夹名获取其下的文件夹或文件路径列表
//     *
//     * @param folderName 文件夹名
//     * @return 文件夹或文件路径列表
//     */
//    List<String> getFoldersByDir(String folderName) throws Exception;
//
//    /**
//     * 删除文件
//     *
//     * @param objectName 文件名称
//     */
//    void removeObject(String objectName) throws Exception;
//
//    /**
//     * 删除文件
//     *
//     * @param bucketName bucket名称
//     * @param objectName 文件名称
//     */
//    void removeObject(String bucketName, String objectName) throws Exception;
//
//    /**
//     * 批量删除文件
//     *
//     * @param objectNames 文件名称列表
//     * @throws Exception 自定义异常
//     */
//    void removeObjects(List<String> objectNames) throws Exception;
//
//    /**
//     * 批量删除文件夹下文件
//     *
//     * @param folderName 文件夹路径
//     * @throws Exception 自定义异常
//     */
//    void removeObjectsByDir(String folderName) throws Exception;
//
//    /**
//     * 配置桶策略
//     * @param bucketName 桶名称
//     * @param prefix 文件夹路径
//     * @param days 过期时间
//     * @param ruleName 规则名称
//     * @throws Exception
//     */
//    void configureBucketPolicy(String bucketName, String prefix, Integer days, String ruleName) throws Exception;
//
//    /**
//     * 列出某个目录下的所有对象名
//     * @return
//     * @throws Exception
//     */
//    List<String> listObjectNames(String prefix) throws Exception;
//
//    /**
//     * 列出某个目录下的所有对象名 递归
//     * @return
//     * @throws Exception
//     */
//    List<String> listRecursiveObjectNames(String prefix) throws Exception;
//}
