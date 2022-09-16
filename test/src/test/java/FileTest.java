import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.FileChannel;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileTest {
    public static void main(String[] args) throws IOException {

        // 测试文件复制，并且压缩成zip
//        String src = "D:\\Users\\admin\\Desktop\\test.txt";
//        File srcFile = new File(src);
//        String descName = "D:\\Users\\admin\\Desktop\\test1.txt";
//        File desFile = new File(descName);
//        copyFileUsingChannel(srcFile, desFile);

        // 下载列表
//        Vector<String> downloadList = new Vector<String>();
//        // 添加下载地址
//        String filePath = "D:\\Users\\admin\\Desktop\\test.txt";
//        String url = "file://" + filePath;
//        downloadList.add(url);
//        downloadList.add("D:\\Users\\admin\\Desktop\\test1.txt");
//        download(downloadList);
        String filePath = "D:\\Users\\admin\\Desktop\\夹带测试(图片).zip";
//        String filePath = "D:\\Users\\admin\\Desktop\\夹带测试(图片+文档+压缩包).zip";
        File file = new File(filePath);
        boolean b = checkFileSize(file.length(), 1, "G");
        System.out.println(b);
    }


    public static boolean checkFileSize(Long len, int size, String unit) {

        // long len = file.length();

        double fileSize = 0;

        if ("B".equals(unit.toUpperCase())) {

            fileSize = (double) len;

        } else if ("K".equals(unit.toUpperCase())) {

            fileSize = (double) len / 1024;

        } else if ("M".equals(unit.toUpperCase())) {

            fileSize = (double) len / 1048576;

        } else if ("G".equals(unit.toUpperCase())) {

            fileSize = (double) len / 1073741824;

        }
        System.out.println(fileSize);
        if (fileSize > size) {

            return true;

        }

        return false;

    }

    private static void copyFileUsingChannel(File source, File dest) throws IOException {

        FileChannel sourceChannel = null;

        FileChannel destChannel = null;

        try {

            sourceChannel = new FileInputStream(source).getChannel();

            destChannel = new FileOutputStream(dest).getChannel();

            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        }finally{

            sourceChannel.close();

            destChannel.close();

        }

    }

    /**
     * 下载
     */
    static void download(Vector<String> downloadList){
        // 线程池
        ExecutorService pool = null;
        HttpURLConnection connection = null;
        //循环下载
        try {
            for (int i = 0; i < downloadList.size(); i++) {
                pool = Executors.newCachedThreadPool();
                final String url = downloadList.get(i);
                String filename = getFilename(downloadList.get(i));
                System.out.println("正在下载第" + (i+1) + "个文件，地址：" + url);
                Future<HttpURLConnection> future = pool.submit(new Callable<HttpURLConnection>(){
                    @Override
                    public HttpURLConnection call() throws Exception {
                        HttpURLConnection connection = null;
                        connection = (HttpURLConnection) new URL(url).openConnection();
                        connection.setConnectTimeout(10000);//连接超时时间
                        connection.setReadTimeout(10000);// 读取超时时间
                        connection.setDoInput(true);
                        connection.setDoOutput(true);
                        connection.setRequestMethod("GET");
                        //connection.setRequestProperty("Range", "bytes=0");
                        connection.connect();
                        return connection;
                    }
                });
                connection = future.get();
                System.out.println("下载完成.响应码:"+ connection.getResponseCode());
                // 写入文件
                writeFile(new BufferedInputStream(connection.getInputStream()), URLDecoder.decode(filename,"UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection)
                connection.disconnect();
            if (null != pool)
                pool.shutdown();
        }
    }

    /**
     * 通过截取URL地址获得文件名
     * 注意：还有一种下载地址是没有文件后缀的，这个需要通过响应头中的
     * Content-Disposition字段 获得filename，一般格式为："attachment; filename=\xxx.exe\"
     * @param url
     * @return
     */
    static String getFilename(String url){
        return ("".equals(url) || null == url) ? "" : url.substring(url.lastIndexOf("/") + 1,url.length());
    }

    /**
     * 写入文件
     */
    static void writeFile(BufferedInputStream bufferedInputStream,String filename){
        //创建本地文件
        File destfileFile = new File("D:\\Downloads\\"+ filename);
        if (!destfileFile.getParentFile().exists()) {
            destfileFile.getParentFile().mkdir();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(destfileFile);
            byte[] b = new byte[1024];
            int len = 0;
            // 写入文件
            System.out.println("开始写入本地文件.");
            while ((len = bufferedInputStream.read(b, 0, b.length)) != -1) {
                System.out.println("正在写入字节：" + len);
                fileOutputStream.write(b, 0, len);
            }
            System.out.println("写入本地文件完成.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileOutputStream) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                if (null != bufferedInputStream)
                    bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
