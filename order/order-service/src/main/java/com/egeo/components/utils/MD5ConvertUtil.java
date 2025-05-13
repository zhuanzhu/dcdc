package com.egeo.components.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/24 15:11
 * @Version V1.0
 **/
public class MD5ConvertUtil {

    /**
     * @Description 进行MD5加密
     **/
    public static String getMD5(String input) {
        return getAlgorithmString(input, "MD5");
    }

    private static String getAlgorithmString(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
