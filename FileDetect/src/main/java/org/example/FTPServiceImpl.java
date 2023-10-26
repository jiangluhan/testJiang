package org.example;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;

@Service
public class FTPServiceImpl implements FTPService {

//    @Autowired
//    private FTPConfig ftpConfig;

    private String SPLIT = ":";

    private ThreadLocal<FTPClient> currentFTPClient;

    private ThreadLocal<ListenerChangeRunnable> currentListener;

    public FTPServiceImpl() {
        this.currentFTPClient = new ThreadLocal<>();
        this.currentListener = new ThreadLocal<>();
    }

    @Override
    public boolean login() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("192.168.1.124", 21);
            ftpClient.login("hddataftp", "gwsj@123");
            ftpClient.setControlEncoding("gb2312");

            ftpClient.changeWorkingDirectory(new String("/".getBytes("GBK"), "iso-8859-1"));
            this.currentFTPClient.set(ftpClient);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public boolean loginOut() {
        try {
            currentFTPClient.get().logout();
            currentFTPClient.get().disconnect();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public FTPFile[] listFile() {
        FTPClient ftpClient = this.currentFTPClient.get();
        try {
            return ftpClient.listFiles();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addListenerFileChange(FileChangeEvent fileChangeEvent) {
        FTPClient ftpClient = this.currentFTPClient.get();
        ListenerFileChangeThreadRunnable listenerFileChangeThread = new ListenerFileChangeThreadRunnable(ftpClient, fileChangeEvent);
        this.currentListener.set(listenerFileChangeThread);
        new Thread(listenerFileChangeThread).start();
    }
}

