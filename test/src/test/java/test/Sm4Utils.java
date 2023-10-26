package test;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public final class Sm4Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sm4Utils.class);

    private static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";

    private static volatile boolean initResult;

    private static Object lock = new Object();

    private static void init() {
        if (!initResult) {
            synchronized (lock) {
                if (!initResult) {
                    try {
                        Security.addProvider(new BouncyCastleProvider());
                        initResult = true;
                    } catch (Exception e) {
                        LOGGER.error("init failed:{}", e.getMessage(), e);
                    }
                }
            }
        }
    }

    /**
     * 加密
     * @param data 数据
     * @param key  秘钥
     * @return 密文
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        init();
        SecretKey secretKey = new SecretKeySpec(key, "SM4");
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME_ECB_PADDING, BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data);
        return encryptedBytes;
    }

    /**
     * 解密
     * @param data 数据
     * @param key  秘钥
     * @return 明文
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        init();
        SecretKey secretKey = new SecretKeySpec(key, "SM4");
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME_ECB_PADDING, BouncyCastleProvider.PROVIDER_NAME);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(data);
        return decryptedBytes;
    }
}