package com.egeo.components.utils.weixin;

import com.alibaba.fastjson.JSONObject;
import com.belerweb.social.bean.Result;
import com.egeo.components.user.bean.CodeToSession;
import com.egeo.utils.http.HttpClientUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belerweb.social.weixin.api.Weixin;
import com.egeo.utils.SpringContextTool;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

public class WeiXinUtil {
    public static Logger logger = LoggerFactory.getLogger(WeiXinUtil.class);

    public static Weixin getWeixin(Long platformId) {
        String toWeixinLoginPage = null;
        Weixin weixin = null;
        if (platformId == 2) {
            toWeixinLoginPage = "muu";
            weixin = new Weixin(SpringContextTool.getProperty("myy.wx.app.id"), SpringContextTool.getProperty("myy.wx.app.secret"),
                    SpringContextTool.getProperty("wx.domain.name") + "/" + toWeixinLoginPage + "/", null);
        }
        if (platformId == 7) {
            toWeixinLoginPage = "#";
            weixin = new Weixin(SpringContextTool.getProperty("wx.app.id"), SpringContextTool.getProperty("wx.app.secret"),
                    SpringContextTool.getProperty("wx.domain.name"), null);
        }
        return weixin;
    }


    public static Weixin getMpWeixin() {
        Weixin weixin = new Weixin(SpringContextTool.getProperty("mp.wx.app.id"),
                SpringContextTool.getProperty("mp.wx.app.secret"), null, null);
        return weixin;
    }


    public static CodeToSession code2Session(String jsCode, String appId, String secret) {
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=").append(appId);
        url.append("&secret=").append(secret);
        url.append("&js_code=").append(jsCode);
        url.append("&grant_type=authorization_code");
        String jsonStr = HttpClientUtil.doGet(url.toString());
        logger.info("code2Session,url:{},result:{}", url.toString(), jsonStr);
        return JSONObject.parseObject(jsonStr, CodeToSession.class);
    }


    public static Result<AccessToken2> accessToken(String appId, String secret, String code) {
        StringBuffer url = new StringBuffer("https://api.weixin.qq.com/sns/oauth2/access_token?");
        url.append("appid=").append(appId);
        url.append("&secret=").append(secret);
        url.append("&code=").append(code);
        url.append("&grant_type=authorization_code");
        String result = HttpClientUtil.doGet(url.toString());
        logger.info("accessToken,url:{},result:{}", url.toString(), result);
        return Result.parse(result, AccessToken2.class);
    }

    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {

        logger.info("encryptedData:{},sessionKey:{},iv:{}", encryptedData, sessionKey, iv);
        //被加密的数据
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);
        //加密秘钥
        byte[] keyByte = Base64.getDecoder().decode(sessionKey);
        //偏移量
        byte[] ivByte = Base64.getDecoder().decode(iv);
        try {
            //如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            //初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            //初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, StandardCharsets.UTF_8);
                logger.info("getUserInfo：{}", result);
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            logger.error("解密，获取微信信息错误", e);
        }
        return null;
    }
}
