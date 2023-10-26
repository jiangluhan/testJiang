package org.example;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.util.*;
import java.util.concurrent.TimeUnit;

//@Slf4j
public class ListenerFileChangeThreadRunnable implements ListenerChangeRunnable {

    private final FTPClient ftpClient;

    private volatile boolean stop;

    private final Map<String, Long> fileMemory;

    private final FileChangeEvent fileChangeEvent;

    public ListenerFileChangeThreadRunnable(FTPClient ftpClient, FileChangeEvent fileChangeEvent) {
        this.ftpClient = ftpClient;
        this.fileChangeEvent = fileChangeEvent;
        this.fileMemory = new HashMap<>();
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                FTPFile[] ftpFiles = ftpClient.listFiles();

                //判断文件被删除
                if (fileMemory.size() > 0) {
                    Set<String> fileNames = new HashSet<>();
                    for (FTPFile ftpFile : ftpFiles) {
                        if (ftpFile.isDirectory()) {
                            System.out.println("文件夹不做删除判断");
                            continue;
                        }
                        fileNames.add(ftpFile.getName());
                    }
                    Set<Map.Entry<String, Long>> entries = fileMemory.entrySet();
                    for (Map.Entry<String, Long> map : entries) {
                        if (!fileNames.contains(map.getKey())) {
//                            log.info("文件{}被删除了", map.getKey());
                            FileChangeData fileChangeData = new FileChangeData();
                            fileChangeData.setEventType(FileChangeType.FILE_DELETED);
                            fileChangeData.setFileName(map.getKey());
                            fileChangeData.setFileSize(map.getValue());
                            fileMemory.remove(map.getKey());
                            fileChangeEvent.change(fileChangeData);
                        }
                    }
                }
                //判断文件是否有更改或新增
                for (FTPFile ftpFile: ftpFiles) {
                    //判断是否为文件夹
                    if (ftpFile.isDirectory()) {
//                        log.info("{}为文件不进行监听操作", ftpFile.getName());
                        continue;
                    }
                    FileChangeData fileChangeData = new FileChangeData();
                    fileChangeData.setFileName(ftpFile.getName());
                    fileChangeData.setFileSize(ftpFile.getSize());
                    fileChangeData.setFtpFile(ftpFile);
                    //文件是否存在于缓存文件列表中
                    if (fileMemory.containsKey(ftpFile.getName())) {
//                        log.info("文件{}在内存中已经存在，进行大小判断", ftpFile.getName());
                        if (!Objects.equals(fileMemory.get(ftpFile.getName()), ftpFile.getSize())) {
//                            log.info("文件{}在内存中已经存在且大小不一致，进行更新缓存操作", ftpFile.getName());
                            fileMemory.put(ftpFile.getName(), ftpFile.getSize());
                            fileChangeData.setEventType(FileChangeType.FILE_UPDATE);
                            fileChangeEvent.change(fileChangeData);
                        }
                        continue;
                    }
//                    log.info("文件{}在内存中不存在进行缓存操作", ftpFile.getName());
                    fileMemory.put(ftpFile.getName(), ftpFile.getSize());
                    fileChangeData.setEventType(FileChangeType.FILE_ADD);
                    fileChangeEvent.change(fileChangeData);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean stopListener() {
        this.stop = Boolean.TRUE;
        this.fileMemory.clear();
        return this.stop;
    }
}
