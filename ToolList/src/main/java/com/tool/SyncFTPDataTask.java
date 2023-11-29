//package com.tool;
//
//import com.gw.disclose.properties.FTPConnectProperties;
//import com.gw.minio.service.GwMinIoHandlerService;
//import lombok.extern.slf4j.Slf4j;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPFile;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import javax.annotation.Resource;
//import java.io.*;
//
//import static com.gw.disclose.constant.DiscloseConstant.*;
//
///**
// * 定时从ftp路径下读取增量文件 上传到minio  逻辑可参考 【主要是通过minio上有没有这个文件来判断是否是已经读取过的图片，侧面来判断增量】
// */
//@Slf4j
//@EnableScheduling
//public class SyncFTPDataTask {
////
////    @Resource
////    private GwMinIoHandlerService minIoHandlerService;
////
////    @Resource
////    private FTPConnectProperties ftpConnectProperties;
//
//    /**
//     * 每三分钟同步一次ftp数据
//     */
//    @Scheduled(fixedRate = 60000 * 3)
//    @Async
//    public void syncFtpData() {
//        FTPClient ftpClient = new FTPClient();
//        try {
//            ftpClient.connect(ftpConnectProperties.getServer(), ftpConnectProperties.getPort());
//            ftpClient.login(ftpConnectProperties.getUser(), ftpConnectProperties.getPassword());
//            ftpClient.setControlEncoding("UTF-8");
//            ftpClient.enterLocalPassiveMode();
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            traverseFolder(ftpClient, ftpConnectProperties.getRemoteFolderPath());
//        } catch (IOException e) {
//            log.error("扫描ftp异常" + e);
//        } finally {
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException e) {
//                log.error("断开ftp连接异常！");
//            }
//        }
//    }
//
//    private void traverseFolder(FTPClient ftpClient, String remoteFolderPath) throws IOException {
//        ftpClient.changeWorkingDirectory(new String(remoteFolderPath.getBytes("UTF-8"), FTP.DEFAULT_CONTROL_ENCODING));
//        FTPFile[] files = ftpClient.listFiles();
//
//        for (FTPFile file : files) {
//            String remoteFilePath = remoteFolderPath + "/" + file.getName();
//            if (file.isDirectory()) {
//                traverseFolder(ftpClient, remoteFilePath);
//            } else {
//                if(isImageFile(file.getName())) {
//                    ByteArrayOutputStream outputStream = null;
//                    InputStream inputStream = null;
//                    try {
//                        String srcMinioObjectName = UPLOAD_IMG_SRC_PATH_NAME + remoteFilePath;
//                        String resMinioObjectName = UPLOAD_IMG_RES_PATH_NAME + remoteFilePath;
//                        boolean objectExists = minIoHandlerService.checkFileIsExist(srcMinioObjectName) || minIoHandlerService.checkFileIsExist(resMinioObjectName);
//                        if (!objectExists) {
//                            outputStream = new ByteArrayOutputStream();
//                            ftpClient.retrieveFile(new String(remoteFilePath.getBytes("UTF-8"), FTP.DEFAULT_CONTROL_ENCODING), outputStream);
//                            byte[] responseBodys = outputStream.toByteArray();
//                            // 原图片上传到minio
//                            inputStream = new ByteArrayInputStream(responseBodys);
//                            uploadToMinio(inputStream, srcMinioObjectName);
//
//                            String thuMinioObjectName = UPLOAD_IMG_THU_PATH_NAME + remoteFilePath;
//                            objectExists = minIoHandlerService.checkFileIsExist(thuMinioObjectName);
//                            if (!objectExists) {
//                                // 缩略图图片路径
//                                inputStream = new ByteArrayInputStream(responseBodys);
//                                File thuLocalFile = new File(UPLOAD_IMG_THU_PATH_NAME+file.getName());
//                                scaleInputStream200(inputStream, thuLocalFile);
//                                // 缩略图上传到minio
//                                uploadToMinio(thuLocalFile, thuMinioObjectName);
//                                // 删除本地缩略图文件
//                                thuLocalFile.delete();
//                            } else {
//                                log.info("File already exists in MinIO. Skipping upload: {}", thuMinioObjectName);
//                            }
//                        } else {
//                            log.info("File already exists in MinIO. Skipping upload: {}", srcMinioObjectName);
//                        }
//                        //删除ftp原文件
//                        ftpClient.deleteFile(new String(remoteFilePath.getBytes("UTF-8"), FTP.DEFAULT_CONTROL_ENCODING));
//                    } finally {
//                        closeStreams(outputStream, inputStream);
//                    }
//                }
//            }
//        }
//    }
//
//    private void closeStreams(Closeable ... closeables) {
//        for(Closeable closeable : closeables){
//            if(closeable != null) {
//                try {
//                    closeable.close();
//                } catch (IOException e) {
//                    log.error("关闭流异常，原因：{}", e.getMessage());
//                }
//            }
//        }
//    }
//
//    private void uploadToMinio(InputStream inputStream, String minioObjectName) {
//        if (inputStream != null) {
//            minIoHandlerService.putObject(minioObjectName, inputStream, "application/octet-stream");
//            log.info("File uploaded to MinIO successfully: {}", minioObjectName);
//        }
//    }
//
//    private void uploadToMinio(File file, String minioObjectName) {
//        if (file != null) {
//            minIoHandlerService.putObject(minioObjectName, file, "application/octet-stream");
//            log.info("File uploaded to MinIO successfully: {}", minioObjectName);
//        }
//    }
//
//    private boolean isImageFile(String fileName) {
//        String lowerCaseFileName = fileName.toLowerCase();
//        return lowerCaseFileName.endsWith(".jpg") || lowerCaseFileName.endsWith(".jpeg") || lowerCaseFileName.endsWith(".png") || lowerCaseFileName.endsWith(".bmp");
//    }
//
//    /**
//     * 生成缩略图
//     *
//     * @param inputStream
//     * @param thuLocalPath
//     */
//    private void scaleInputStream200(InputStream inputStream, File thuLocalPath) {
//        try {
//            //获取文件宽高
//            Thumbnails.of(inputStream)
//                    .scalingMode(ScalingMode.BICUBIC)
//                    .size(200, 200)
//                    .toFile(thuLocalPath);
//        } catch (IOException e) {
//            log.error("缩略图生成失败，原因：{}", e.getMessage());
//        }
//    }
//
//}
