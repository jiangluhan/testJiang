package com.google.authenticator.auth;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.util.HexUtil;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

/**
 * GW最终用的这个类
 */
public class GoogleAuthenticatorUtils {

    /**
     * 工夫前后偏移量
     * 用于避免客户端工夫不准确导致生成的TOTP与服务器端的TOTP始终不统一
     * 如果为0,以后工夫为 10:10:15
     * 则表明在 10:10:00-10:10:30 之间生成的TOTP 能校验通过
     * 如果为1,则表明在
     * 10:09:30-10:10:00
     * 10:10:00-10:10:30
     * 10:10:30-10:11:00 之间生成的TOTP 能校验通过
     * 以此类推
     */
    private static final int TIME_OFFSET = 0;

    /**
     * 创立密钥
     */
    public static String createSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return Base32.encode(bytes).toLowerCase();
    }

    /**
     * 依据密钥获取验证码
     * 返回字符串是因为数值有可能以0结尾
     * @param secretKey 密钥
     * @param time 第几个30秒 System.currentTimeMillis() / 1000 / 30
     */
    public static String generateTOTP(String secretKey, long time) {
        byte[] bytes =  Base32.decode(secretKey.toUpperCase());
        String hexKey = HexUtil.encodeHexStr(bytes);
        String hexTime = Long.toHexString(time);
        return TOTP.generateTOTP(hexKey, hexTime, "6");
    }

    /**
     * 生成 Google Authenticator Key Uri
     * Google Authenticator 规定的 Key Uri 格局: otpauth://totp/{issuer}:{account}?secret={secret}&issuer={issuer}
     * https://github.com/google/google-authenticator/wiki/Key-Uri-Format
     * 参数须要进行 url 编码 +号须要替换成%20
     * @param secret 密钥 应用 createSecretKey 办法生成
     * @param account 用户账户 如: example@domain.com
     * @param issuer 服务名称 如: Google,GitHub
     * @throws UnsupportedEncodingException
     */
//    @SneakyThrows
//    public static String createKeyUri(String secret, String account, String issuer) throws UnsupportedEncodingException {
//        String qrCodeStr = "otpauth://totp/${issuer}:${account}?secret=${secret}&issuer=${issuer}";
//        ImmutableMap.Builder<String, String> mapBuilder = ImmutableMap.builder();
//        mapBuilder.put("account", URLEncoder.encode(account, "UTF-8").replace("+", "%20"));
//        mapBuilder.put("secret", URLEncoder.encode(secret, "UTF-8").replace("+", "%20"));
//        mapBuilder.put("issuer", URLEncoder.encode(issuer, "UTF-8").replace("+", "%20"));
//        return StringSubstitutor.replace(qrCodeStr, mapBuilder.build());
//    }

    /**
     * 校验办法
     *
     * @param secretKey 密钥
     * @param totpCode TOTP 一次性明码
     * @return 验证后果
     */
    public static boolean verification(String secretKey, String totpCode) {
        long time = System.currentTimeMillis() / 1000 / 30;
        // 优先计算以后工夫,而后再计算偏移量,因为大部分状况下客户端与服务的工夫统一
        if (totpCode.equals(generateTOTP(secretKey, time))) {
            return true;
        }
        for (int i = -TIME_OFFSET; i <= TIME_OFFSET; i++) {
            // i == 0 的状况曾经算过
            if (i != 0) {
                if (totpCode.equals(generateTOTP(secretKey, time + i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getQRBarcode(String user, String secret) {
        String format = "otpauth://totp/%s?secret=%s";
        return String.format(format, user, secret);
    }

    public static void main(String[] args) {
        String secretKey = createSecretKey();
        System.out.println(secretKey); // 3tnzpyfzdtmp6supzfiezgzmjd2prk3i
//        String qrBarcode = getQRBarcode("test", secretKey);
//        System.out.println(qrBarcode); // otpauth://totp/test?secret=3tnzpyfzdtmp6supzfiezgzmjd2prk3i
//        String code = "697761";
////        String code = "800249";
//        System.out.println(verification("3tnzpyfzdtmp6supzfiezgzmjd2prk3i", code));
    }

}