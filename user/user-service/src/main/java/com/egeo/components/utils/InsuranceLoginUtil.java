package com.egeo.components.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.http.HttpClientUtil;


/**
 * 保交汇保险登陆工具类
 * @author graci
 *
 */
public class InsuranceLoginUtil {

	public enum EnvPrefix{
		DEV("dev","用户姓名不一致"),
		SIT("sit","用户身份证不一致"),
		PRE("pre","用户身份证不一致"),
		PRD("prd","用户身份证不一致");
	    private String env;
	    private String prefix;
	    EnvPrefix(String env, String prefix) {
	        this.env = env;
	        this.prefix = prefix;
	    }
	    
	    public String getEnv() {
			return env;
		}

		public String getPrefix() {
			return prefix;
		}

		public static EnvPrefix getByEnv(String env) {
	    	EnvPrefix[] all_type = EnvPrefix.values();
			for (EnvPrefix one : all_type) {
				if (one.getEnv().equalsIgnoreCase(env)) {
					return one;
				}
			}
			return EnvPrefix.DEV;
		}

		public static String getPrefix(String env) {
	    	EnvPrefix[] all_type = EnvPrefix.values();
			for (EnvPrefix one : all_type) {
				if (one.getEnv().equalsIgnoreCase(env)) {
					return one.getPrefix();
				}
			}
			return EnvPrefix.DEV.getPrefix();
		}
	}
	
	/**
	 * 迩格token
	 */
	private static final String TOKEN="9e20d6684c3e4fa8aa421339ca1e1140";
	
	/**
	 * 成功
	 */
	public static final int SUCCESS=0;
	
	/**
	 * 某个参数验证失败，具体参数看返回内容
	 */
	public static final int PARAM_WRONG=10000;
	
	/**
	 * 账号密码错误
	 */
	public static final int LOGIN_INFO_WRONG=10001;
	
	/**
	 * 新密码长度位数不足
	 */
	public static final int NEW_PASSWORD_INVALID=10002;
	
	/**
	 * 操作失败，请开发者稍后再试
	 */
	public static final int FAIL=10003;
	
	/**
	 * 请求域名
	 */
	private static final String HOST_NAME="http://api.wxshop.wddcn.com/";
	
	/**
	 * 邀请人手机号
	 */
	private static final String INVITOR_MOBILE="18817578517";
	
	
	private static final Random randomGenerator=new Random();
	
	public static final String INDEX_REF="http%3A%2F%2Fweixin.wddcn.com%2FViews%2FWeChat%2FWeiShop%2FssCarousel.ashx%3FrefType%3Dindex%26mid%3D660";
	
	public static final String ORDER_REF="http%3A%2F%2Fweixin.wddcn.com%2FViews%2FWeChat%2FWeiShop%2FssCarousel.ashx%3FrefType%3Dmyorder%26mid%3D660";
	
	
	/**
	 * 获取用户注册接口url
	 * @return
	 */
	private static String url_register() {
		return HOST_NAME+TOKEN+"/dzd/api?method=add.vip.user&ResultType=JSON";
	}
	
	/**
	 * 获取用户登陆接口url
	 * @return
	 */
	private static String url_login() {
		return HOST_NAME+TOKEN+"/dzd/api?method=check.vip.user&ResultType=JSON";
	}

	/**
	 * 根据id生成用户登录名
	 * 第三方保险商城用户名：
	 * 格式为FGJ+8位数字（用户的福管加账户id编号，不足的补0至8位）+4位随机数
	 * 例如FGJ000000014738
	 * @param userId
	 * @return
	 */
	public static String genUserNameById(Long userId) {
		String formatUserId=String.format("%08d", userId);
		int randomSuffix=randomGenerator.nextInt(9999);
		String suffix4=String.format("%04d", randomSuffix);
		String env = SpringContextTool.getPrefilesActive();
		return EnvPrefix.getPrefix(env)+"FGJ"+formatUserId+suffix4;
	}
	
	/**
	 * 访问保交汇注册接口
	 * 返回值示例
	 * {
	 * "code":0,
	 * "msg":"success",
	 * "data":{
	 * 		"cardno":"MCBX000029003909",
	 * 		"memberid":"29003909",
	 * 		"membercode":"5D5E19740148437B2E4F2A3D9FCA4E16"
	 * 		}
	 * }
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String register(String userName,String password) {
		Map<String,String> params=new HashMap<>();
		params.put("username", userName);
		params.put("pwd", password);
		params.put("invite", INVITOR_MOBILE);
		return HttpClientUtil.doPost(url_register(), params, "utf-8",null);
	}
	
	/**
	 * 访问保交汇登陆接口
	 * 返回值示例:
	 * {
	 * "code":0,
	 * "msg":"success",
	 * "data":{
	 * 		"cardno":"MCBX000029003909",
	 * 		"dccuid":"0",
	 * 		"dccucode":"4E71EB4A5EFCEFD5",
	 * 		"memberid":"29003909",
	 * 		"openid":"85b28657-8adc-4cbf-81f2-642295e1936b",
	 * 		"userno":"KFGJTESTUSER",
	 * 		"mid":660,
	 * 		"vs_id":"1892226",
	 * 		"membercode":"5D5E19740148437B2E4F2A3D9FCA4E16",
	 * 		"nickname":"KFGJTESTUSER",
	 * 		"shopname":"保交汇",
	 * 		"headerurl":"",
	 * 		"autologinurl":"http://qtdzd.wddcn.com/vshop/autologin.html?code=5D5E19740148437B2E4F2A3D9FCA4E16\u0026p=KFGJTESTUSER\u0026ref=",
	 * 		"logouturl":"http://qtdzd.wddcn.com/vshop/autologin.html?action=logout\u0026mid=660\u0026ref="
	 * 		}
	 * }
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String login(String userName,String password) {
		Map<String,String> params=new HashMap<>();
		params.put("username", userName);
		params.put("pwd", password);
		return HttpClientUtil.doPost(url_login(), params, "utf-8",null);
	}
	
	/**
	 * 生成六位随机数密码
	 * @return
	 */
	public static String genPassword() {
		return String.format("%06d",randomGenerator.nextInt(999999));
	}
	
	public static void main(String[] args) {
		//System.out.println(genUserNameById(198322l));
//		String s=HttpClientUtil.doPost(url_register(), new HashMap<String,String>(), "utf-8");
//		System.out.println(s);
		//System.out.println(ENV_PREFFIX);
//		System.out.println(genPassword());
		
//		String regRes=register("KFGJTESTUSER", "123456");
//		System.out.println(regRes);
		
//		String loginRes=login("KFGJTESTUSER", "123456");
//		System.out.println(loginRes);
		
		System.out.println("http://qtdzd.wddcn.com/vshop/autologin.html?code=5D5E19740148437B2E4F2A3D9FCA4E16\u0026p=KFGJTESTUSER\u0026ref=");
	}
}
