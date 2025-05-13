package com.egeo.components.utils;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/9/6 12:12
 * @Version V1.0
 **/
public class RsaSignUtil {

    static final  Pattern pattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");

    public final static String ALGORITHM_256="SHA256withRSA";

    public final static String ALGORITHM_RSA="RSA";

    public final static String CHARSET_NAME="utf-8";


    public static String signData(String contentText,String privateKeyString) throws Exception {
        // 读取私钥
        PrivateKey privateKey = loadPrivateKeyByStr(privateKeyString);

        // 初始化Signature对象
        Signature signer = Signature.getInstance(ALGORITHM_256);
        signer.initSign(privateKey);

        // 对JSON字符串进行签名
        signer.update(contentText.getBytes(CHARSET_NAME));
        byte[] signatureBytes = signer.sign();
        // 将签名转换为Base64字符串
        return Base64.encode(signatureBytes);
    }

    public static boolean verify(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature.getInstance(ALGORITHM_256);

            signature.initVerify(pubKey);
            signature.update(content.getBytes());

            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 从字符串中加载私�?
     *
     * @param privateKeyStr
     *            私钥数据字符�?
     * @throws Exception
     *             加载私钥时产生的异常
     */
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    public static String decodeUnicodeEscapes(String input) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            // 将之前的非匹配部分添加到结果中
            matcher.appendReplacement(sb, "");

            // 获取匹配到的16进制字符串，并转换为Unicode字符
            String hex = matcher.group(1);
            int unicode = Integer.parseInt(hex, 16);
            char unicodeChar = (char) unicode;

            // 将Unicode字符添加到结果中
            sb.append(unicodeChar);
        }
        // 添加最后一个非匹配部分（如果有的话）
        matcher.appendTail(sb);

        return sb.toString();
    }
}
