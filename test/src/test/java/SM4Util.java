package com.cetcnav.smartreception.util;

//模式

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cn.hutool.crypto.Mode.ECB;
import static cn.hutool.crypto.Padding.NoPadding;

public class SM4Util {

	private static Logger logger = LoggerFactory.getLogger(SM4Util.class);
	//国四加解密的秘钥需要16个字符长度
	private static String key = "yyyyMMddyyyyMMdd";

	/**
	 * hutool MS4加密
	 * 
	 * @param content
	 *            加密内容
	 */
	public static String encode(String content) {
		try {
			// SymmetricCrypto sm4 = SmUtil.sm4(ZeroPadding,key.getBytes());
			SymmetricCrypto sm4 = new SM4(ECB, NoPadding, key.getBytes());
			// 在NoPadding模式下需要手动补齐分组不为8字节的
			byte[] data = padding(content);
			String encryptHex = sm4.encryptHex(data);
			return encryptHex;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("MS4加密失败");
		}
		return null;
	}

	/**
	 * hutool 解密SM4
	 * 
	 * @param encodeContent
	 * @return
	 */
	public static String decode(String encodeContent) {
		try {
			SymmetricCrypto sm4 = new SM4(ECB, NoPadding, key.getBytes());
			String decryptStr = sm4.decryptStr(encodeContent, CharsetUtil.CHARSET_UTF_8);
			return decryptStr;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SM4解密失败");
		}
		return null;
	}

	// 在NoPadding模式下需要手动对齐16字节的倍数
	public static byte[] padding(String arg_text) {
		byte[] encrypt = arg_text.getBytes();

		if (encrypt.length % 16 != 0) { // not a multiple of 8
			// create a new array with a size which is a multiple of 8
			byte[] padded = new byte[encrypt.length + 16 - (encrypt.length % 16)];

			// copy the old array into it
			System.arraycopy(encrypt, 0, padded, 0, encrypt.length);
			encrypt = padded;
		}
		return encrypt;
	}

	public static void main(String[] args) {

	}
}
