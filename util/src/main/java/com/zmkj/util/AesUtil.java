package com.zmkj.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author maxiaodong
 */
public class AesUtil {
    /**
     * 加密
     * @param content 内容
     * @param key key
     * @return String
     * @throws Exception 异常
     */
    public static String encry(String content, String key) throws Exception {
        String iV = key;
        if (key.length() > 16) {
            // IV为商户MD5密钥后16位
            iV = key.substring(key.length() - 16);
            // RES的KEY 为商户MD5密钥的前16位
            key = key.substring(0, 16);
        }

        return encryptData(content, key, iV);
    }

    /**
     * 加密
     * @param content 内容
     * @param key key
     * @return String
     * @throws Exception 异常
     */
    public static String desEncry(String content, String key) throws Exception {
        String iV = key;
        if (key.length() > 16) {
            // IV为商户MD5密钥后16位
            iV = key.substring(key.length() - 16);
            // RES的KEY 为商户MD5密钥的前16位
            key = key.substring(0, 16);
        }
        return decryptData(content, key, iV);
    }

    /**
     * aes 加密
     *
     * @param data data
     * @return String
     */
    public static String encryptData(String data, String key, String iv) throws Exception {
        //System.out.println("加密前数据"+ data);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        int plaintextLength = dataBytes.length;
        // if (plaintextLength % blockSize != 0) {
        // plaintextLength = plaintextLength + (blockSize - (plaintextLength
        // % blockSize));
        // }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        byte[] encrypted = cipher.doFinal(plaintext);
        return new String(Base64.encodeBase64(encrypted), StandardCharsets.UTF_8);

    }

    /**
     * aes 解密
     *
     * @param data
     *            密文
     * @return String
     */
    public static String decryptData(String data, String key, String iv) throws Exception {
        byte[] encrypted1 = Base64.decodeBase64(data.getBytes(StandardCharsets.UTF_8));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, StandardCharsets.UTF_8);
    }

//    public static void main(String[] args) throws Exception {
//        String str="{\"orderNo\":\"order1552809255\",\"subject\":\"重庆火锅\",\"amount\":\"100\",\"notifyUrl\":\"http %3A%2F%2Fdemo-php%2Fnotice%2Fpay.php\",\"payType\":\"0\",\"source\":\"ZFBZF\"}";
//        String a = encryptData(str, "98883272bf0b42c9","c7f262e1cf2a4cb3");
//
//        String a1 = decryptData(a, "98883272bf0b42c9","c7f262e1cf2a4cb3");
//        System.out.println(a);
//        System.out.println(a1);
//        System.out.println("sss");
//    }
}
