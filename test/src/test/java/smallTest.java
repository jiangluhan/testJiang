import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 用于测试一些比较小的且简单的测试验证，多是公司业务代码需要进行一些小验证的时候可以在这个类下面验证
 */
public class smallTest {

    public static void main(String[] args) {


//        // 测试判断map是否为空
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "test");
//        if(map.entrySet().isEmpty()) {
//            System.out.println("map为空");
//        }else {
//            System.out.println("map不为空");
//        }
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime dateTime = LocalDateTime.parse("2022-11-03 11:41", df);
//        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        System.out.println(df2.format(dateTime));
//        String str = "2022-11-03 11:41";
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        try {
//            Date date = formatter.parse(str);
//            System.out.println(date);
//            System.out.print("格式正确！");
//        } catch (Exception e) {
//            System.out.println("格式错误！");
//        }
//        List<String> failureFileNames = new ArrayList<>();
//        failureFileNames.add("1.jpg");
//        failureFileNames.add("2.png");
//        failureFileNames.add("3.tiff");
//
//        StringBuilder failureFileName = new StringBuilder();
//        for(int i = 0; i < failureFileNames.size(); i++) {
//            if(i != failureFileNames.size() - 1) {
//                failureFileName.append(failureFileNames.get(i)).append("、");
//            } else {
//                failureFileName.append(failureFileNames.get(i));
//            }
//        }
//        System.out.println(failureFileName.toString());\





//        String mysql = "jdbc:mysql://192.168.1.53:3306/center"; // mysql
//        String oracle = "jdbc:oracle:thin:@10.10.10.10:1521:dataBase"; //oracle
//        String sqlserver = "jdbc:sqlserver://10.10.10.10:1433;DatabaseName=dataBase"; //sqlserver
//        String db2 = "jdbc:db2://10.10.10.10:5000/dataBase"; //db2
//        String postgresql = "jdbc:postgresql://10.10.10.10:5432/postgres"; //postgresql
//        String sybase = "jdbc:sybase:Tds:192.168.2.103:5000/SXABC"; //sybase
//        String dm = "jdbc:dm://localhost:5236/hive"; //dm
//
//        getHostFrom(mysql);
//        getHostFrom(oracle);
//        getHostFrom(sqlserver);
//        getHostFrom(db2);
//        getHostFrom(postgresql);
//        getHostFrom(sybase);
//        getHostFrom(dm);

//        String mysqlIpPort = getHostAndIpFromUrl(mysql);
//        String oracleIpPort = getHostAndIpFromUrl(oracle);
//        String sqlserverIpPort = getHostAndIpFromUrl(sqlserver);
//        String db2IpPort = getHostAndIpFromUrl(db2);
//        String postgresqlIpPort = getHostAndIpFromUrl(postgresql);
//        String sybaseIpPort = getHostAndIpFromUrl(sybase);
//        String dmIpPort = getHostAndIpFromUrl(dm);
//
//        System.out.println("-------------------------");
//        String s = StringUtils.substringBetween(dm, ":");
//        System.out.println(s);
//        String str = "jdbc:sybase:Tds:192.168.2.103:5000/SXABC"; //sybase
//        int index=str.indexOf(":");
//        System.out.println(index);
//        //根据第一个点的位置 获得第二个点的位置
//        index=str.indexOf(":", index+1);
//        //根据第二个点的位置 获得第三个点的位置
//        index=str.indexOf(":", index+2);
//        System.out.println(index);
//        //根据第二个点的位置，截取 字符串。得到结果 result
//        String result=str.substring(index+1, str.lastIndexOf("/"));
//        System.out.println(result);

//        String subString = "192.168.2.103:5000";
//        String host = subString.substring(0, subString.lastIndexOf(":"));
//        String ip = subString.substring(subString.lastIndexOf(":") + 1);
//        System.out.println(host + "," + ip);

//        String sourceJDBC = "jdbc:sybase:Tds:192.168.2.103:5000/SXABC";
//
//       String dataBaseName = sourceJDBC.substring(sourceJDBC.lastIndexOf("/") + 1);
//        System.out.println(dataBaseName);

//            guavaRetry();
//        System.out.println(String.valueOf(605).startsWith("60"));
//
//        List<String> databaseAddress = new ArrayList<>();
//        databaseAddress.add("192.168.1.53,192.168.1.241");
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < databaseAddress.size(); i++) {
//            sb.append(databaseAddress.get(i));
//            if(i != databaseAddress.size() -1) {
//                sb.append(",");
//            }
//        }
//        System.out.println(sb);


//        pngToJpg("D:\\Users\\admin\\Desktop\\3.jpg", "D:\\Users\\admin\\Desktop\\3-1.jpeg");
//        png2jpeg("D:\\Users\\admin\\Desktop\\2.png", "D:\\Users\\admin\\Desktop\\2-1-1.jpeg");

        // 获取系统架构
//        Properties props = System.getProperties();
//        String arch = (String) props.get("os.arch");
//        System.out.println(arch);

//        String path = "HDDataClient_online_4.0.0.tag.gz";
//        String oo=path.substring(0,path.lastIndexOf(".",path.lastIndexOf(".")-1));
//        System.out.println(oo);

//        String s ="192.168.6.245&192.168.120.1&192.168.86.1&192.168.8.77&192.168.1.1";
//        String[] split = s.split("&");
//        for(String s1 : split) {
//            System.out.println(s1);
//        }
//        for(int i = 0 ;i <10;i++) {
//            System.out.println(i);;
//            for (int j = 0; j < 10 ; j++) {
//                System.out.println(j);
//                if(j < 5) {
//                    return;
//                }
//            }
//        }
//        String name = "ha哈 哈哈";
//        boolean b = name.matches(".*[~!@#$%^&*()_+]+.*");
//        System.out.println(b);

//        String test = "admin,test";
//        String test1 = "admin,test";
//        String[] split = test1.split(",");
////        System.out.println();
//        System.out.println(test.equals(test1));
//        String test = "select role_name /*keep orderby*/\n" +
//                "    from sys_user\n" +
//                "    join user_rlp_role\n" +
//                "    on sys_user.id= user_rlp_role.user_id\n" +
//                "    join sys_role\n" +
//                "    on user_rlp_role.role_id = sys_role.id\n" +
//                "    where 1=1\n" +
//                "   ORDER BY sys_user.create_time desc";
//        System.out.println(test.indexOf("/*keep orderby*/"));

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//
//        List<Integer> userRlpDatasourceDB = new ArrayList<>();
//        userRlpDatasourceDB.add(2);
//        userRlpDatasourceDB.add(1);
//        userRlpDatasourceDB.add(3);
//        userRlpDatasourceDB.add(3);
//
//        // 判断两个集合是否相等 不在乎顺序 只判断元素
//        boolean collection = CollectionUtils.isEqualCollection(list, userRlpDatasourceDB);
//        System.out.println(collection);
//        System.out.println(reduceList);

//        String attr = "{'attr1':'aaaaa','attr2':'Intel(R) Core(TM) i3-3217U CPU @ 1.80GHz', 'chnlName':'渠道名称', 'funName':'功能名称'}";
//        String attr1 = "{'attr1':'aaaaa', 'chnlName':'渠道名称', 'funName':'功能名称'}";
//        System.out.println(StrUtil.equals(attr1, attr));



        // 加密操作
        // json串
        String txt = "{\"curType\": \"CNY\",\"goodsName\": \"XXX\",\"goodsType\": \"02\",\"merCode\": \"001\",\"extendData\": \"贷款人 | 张三;金额 | 1.00\"}";
//        // 拿着sm4 key加密json sm4 key:GJwsXX_BzW=gJWJW
//        String sm4 = SM4Utils.encryptData_ECB(txt);
//        System.out.println("用sm4加密后的json字符串为："+ sm4);
//        // sm2对sm4的密钥进行加密 sm2公钥：042B302B5BB818001D377F07340A788AB4E8BB16C1D420E09F92519EC66D06E1A7AB58DD9F8159811F1CB9954DDB61B7FAA87D7A06A50E47F2A72C1DC8946D4494
//        String publicKey = "042B302B5BB818001D377F07340A788AB4E8BB16C1D420E09F92519EC66D06E1A7AB58DD9F8159811F1CB9954DDB61B7FAA87D7A06A50E47F2A72C1DC8946D4494";
//        String encrypt = SM2Utils.encrypt(publicKey, "GJwsXX_BzW=gJWJE");
//        System.out.println("用sm2加密sm4 key后的字符串为："+ encrypt);

        // GJwsXX_BzW=gJWJW
        //用sm4加密后的json字符串为：fvaLAyhd60aTCv3rWVq1gLXePPQG4QtllooKsNt/0X3BsMu0nFEhXQ8pQet/m7zjuCQUHyiS/6VKAR2IZ2EMW75Gt+r8oiYCWnGh/QsGuAOPvYNg/FKQUtj3eqds73M4VroFwmXCmcmD7v7Ut9pvNm8lPjORDSm2sL1jpwveOB8=
        //用sm2加密sm4 key后的字符串为：04fce1485c0b449c6c7e59965b8f44bb3f4beaa8893a28587b988a513378a8456736ebff8ad96b915c9d17d754434985258d2b2d290d60cd1f63ae69cfb19d016f9f299dbeb99f4e9df74c9261bcf3a44ad7fe18f07bc2a639ff462e675cafe424c76ec5b592399ce6abcc5232e8742424


        // GJwsXX_BzW=gJWJE
        // 用sm4加密后的json字符串为：fvaLAyhd60aTCv3rWVq1gLXePPQG4QtllooKsNt/0X3BsMu0nFEhXQ8pQet/m7zjuCQUHyiS/6VKAR2IZ2EMW75Gt+r8oiYCWnGh/QsGuAOPvYNg/FKQUtj3eqds73M4VroFwmXCmcmD7v7Ut9pvNm8lPjORDSm2sL1jpwveOB8=
        // 用sm2加密sm4 key后的字符串为：04f73fb87fe461799bfed172f9371072204f792d09d8f1cac1fbabb8d335e7c8cbe3b32ad26b2860f3fd490c17bd61452fdd010664eeeed310ab5aba762ea683f0b10b584e41a6b26fd9777aa909306bbc4f1a0406e18d87c397fa6168e5160dec69354ceb6c9278c7a07754c69e24dd3a



        System.out.println("-------------------------------------");


        // 对上述进行解密，看看解密出来的内容对不对
        // 先用sm2私钥对上述结果进行解密
//        String privateKey = "1AC7B9CB4B071CA0B48A1DBD996175455070FB9ECCB90F83CAE6F59410B6BEE0";
//        String decrypt = SM2Utils.decrypt(privateKey, "04f73fb87fe461799bfed172f9371072204f792d09d8f1cac1fbabb8d335e7c8cbe3b32ad26b2860f3fd490c17bd61452fdd010664eeeed310ab5aba762ea683f0b10b584e41a6b26fd9777aa909306bbc4f1a0406e18d87c397fa6168e5160dec69354ceb6c9278c7a07754c69e24dd3a");
//        System.out.println("用sm2解密【加密后的sm4 key】为："+ decrypt);
//        // 用解密出来的sm4 key进行解密json
//        String result = SM4Utils.decryptData_ECB("fvaLAyhd60aTCv3rWVq1gLXePPQG4QtllooKsNt/0X3BsMu0nFEhXQ8pQet/m7zjuCQUHyiS/6VKAR2IZ2EMW75Gt+r8oiYCWnGh/QsGuAOPvYNg/FKQUtj3eqds73M4VroFwmXCmcmD7v7Ut9pvNm8lPjORDSm2sL1jpwveOB8=");
//        System.out.println("最终解密后的结果是：" + result);

//        SM4 sm4 = SmUtil.sm4(txt.getBytes());


        String path = "D:\\gw_resource_pool_data.txt";
        File file = new File(path);
        System.out.println(file.getPath());
    }


    /**
     * 随机生成秘钥
     */
//    public static void getKey(){
//        try {
//            KeyGenerator kg = KeyGenerator.getInstance("AES");
//            kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
//            SecretKey sk = kg.generateKey();
//            byte[] b = sk.getEncoded();
//            String s = byteToHexString(b);
//            System.out.println(s);
//            System.out.println("十六进制密钥长度为"+s.length());
//            System.out.println("二进制密钥的长度为"+s.length()*4);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            System.out.println("没有此算法。");
//        }

    public static void img2jpeg(String imagePath, String jpegPath) {
        BufferedImage bufferedImage;
        try {
            // read image file
            bufferedImage = ImageIO.read(new File(imagePath));
            // create a blank, RGB, same width and height, and a white
            // background
            BufferedImage newBufferedImage = new BufferedImage(
                    bufferedImage.getWidth(), bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            // TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0,
                    Color.WHITE, null);
            // write to jpeg file
            ImageIO.write(newBufferedImage, "jpeg", new File(jpegPath));
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void png2jpeg(String pngPath, String jpegPath) {
        //读取图片
        FileOutputStream fos =null;
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(pngPath));
            //转成jpeg、
            BufferedImage bufferedImage1 = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            bufferedImage1.createGraphics().drawImage(bufferedImage,0,0, Color.white,null);
            fos = new FileOutputStream(jpegPath);
            ImageIO.write(bufferedImage,"jpg",fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                fos.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static void guavaRetry() {
        Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
                .retryIfExceptionOfType(GwException.class)
                .retryIfResult(e -> e.intValue() == 0)
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            retryer.call(() -> test(0));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static int test(int i) {
        int j = 0;
        for(j = 0; j < 2; j++) {
            j = j + i;
        }
        return  j;
    }

    public static void getHostFrom(String url) {
        Pattern p = Pattern.compile("jdbc:(?<db>\\w+):.*((//)|@|(Tds:))(?<host>.+):(?<port>\\d+)(/|(;DatabaseName=)|:)(?<dbName>\\w+)\\??.*");
        Matcher m = p.matcher(url);
        if(m.find()) {
            System.out.println(m.group("db"));
            System.out.println(m.group("host"));
            System.out.println(m.group("port"));
            System.out.println(m.group("dbName"));
        }
    }

    final public static String regexForHostAndPort = "[.\\w]+:\\d+";
    final public static Pattern hostAndPortPattern = Pattern.compile(regexForHostAndPort);

    public static String getHostAndIpFromUrl(String url) {
        Matcher matcher = hostAndPortPattern.matcher(url);
        matcher.find();
        int start = matcher.start();
        int end = matcher.end();
        if(start >= 0 && end >= 0) {
            String hostAndPort = url.substring(start, end);
            System.out.println(hostAndPort);
            return hostAndPort;
        }
        throw new IllegalArgumentException("couldn't find pattern '" + regexForHostAndPort + "' in '" + url + "'");
    }
}
