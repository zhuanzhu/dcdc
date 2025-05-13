package com.egeo.components.utils.qc;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.egeo.utils.DateUtils;
import com.egeo.utils.SendMail;
//import com.hisun.crypt.mac.CryptUtilImpl;
import com.egeo.utils.thirdparty.RechargePhoneUtil;


@Component
@ConditionalOnResource(resources= {"classpath:config/QCConfig.properties"})
@PropertySource(value = {"classpath:config/QCConfig.properties"},ignoreResourceNotFound = false, encoding = "UTF-8", name = "QCConfig.properties")
public class QCUtil {
    public static Logger logger=Logger.getLogger(QCUtil.class);


	//渠道号
    @Value("${qc.CHNNO}")
	private  String CHNNO;
	//OPENID
    @Value("${qc.OPENID}")
	private  String OPENID;
	//md5密钥
    @Value("${qc.SIGNKEY}")
	private  String SIGNKEY;
	//des密钥
    @Value("${qc.DESKEY}")
	private  String DESKEY;
	//公司ip
    @Value("${qc.ipaddr}")
	private  String ipaddr;
	//手机号码
    @Value("${qc.phoneno}")
	private  String phoneno ;

    @Value("${qc.URL}")
	private  String URL ;
	//private static final String URL ="http://test.yidianlife.com:7001/jf_trans/api/service.htm";

    @Autowired
    private SendMail sendMail;


	/**
	 * 卡券购买接口
	 * @param actid 活动编号
	 * @param amount 数量
	 */
	public String buyCard(String actid,int amount,String ordno) throws Exception {
		return "";/*
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", ipaddr); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------

		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		//卡券购买接口
		header.put("apiid", "620006"); //接口编号
		body.put("mercid",OPENID); //商户编号(OpenId)
		body.put("ordno", ordno);//商户订单号
		//TODO 根据查询接口返回的actid
		body.put("actid",actid); //营销案编号
		JSONArray infoArray = new JSONArray();
		JSONObject info = new JSONObject();
		logger.info("手机号码"+phoneno);
		info.put("phoneno",phoneno); //手机号
		info.put("amount",amount+""); //发放数量
		infoArray.add(info);
		JSONObject info1 = new JSONObject();
		info1.put("phoneno","*******"); //手机号
		info1.put("amount","1"); //发放数量
		infoArray.add(info1);
		body.put("infos", infoArray);

		request.put("body", body);
		//------------------------业务参数 End  ---------------------


		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);

		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");

		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);

		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		HttpClientUtil util = new HttpClientUtil();
		responseData = util.post(URL,requestData,mimeType,"GBK");
		return responseData;


	*/}

	/**
	 * 查询订单状态的接口
	 * @param ordno 子订单单号
	 * @return
	 * @throws Exception
	 */
	public String queryOrder(String ordno) throws Exception {
		return "";/*
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", ipaddr); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------

		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		//订单查询接口
		header.put("apiid", "620015"); //接口编号
		//TODO 根据购买卡券返回的ordno
		body.put("ordno", ordno);

		request.put("body", body);
		//------------------------业务参数 End  ---------------------


		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);

		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");

		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);

		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		HttpClientUtil util = new HttpClientUtil();
		responseData = util.post(URL, requestData, mimeType, "GBK");
		return responseData;*/
	}


	public  String getRequestjournal() {
		return Long.toString(Math.abs(new Random().nextLong()));
	}

	/**
	 * 盐加密失败邮件发送
	 * @param orderCode 订单编号
	 */
	public  void sendSysEmail(String orderCode,String result){
		final String content = "<html><head></head><body><h3>券仓购买卡券失败</h3><br/>原因:"+"保存卡号卡密失败"
				+"<br/>订单编号:"+orderCode
				+"<br/>结果:"+result
				+"<br/><br/><br/>大厨管家团队<br/>"+ DateUtils.getDefaultDate(new Date())+"</body></html>";
		//发送邮箱
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<String> emailList = com.alibaba.fastjson.JSONArray.parseArray(RechargePhoneUtil.systemEmail, String.class);
				for (String email : emailList) {
					try {
						//sendMail.sendMail(email, "大厨管家系统异常", content);
					} catch (Exception e) {
						logger.error(e.getMessage() + email + "发送邮箱失败");
					}
				}
			}
		}).start();
	}

	/**
	 * 卡券购买失败系统邮件
	 * @param orderCode 订单编号
	 * @param amount
	 */
	public  void sendSysExEmail(QCResult qcResult, String orderCode, int amount){
		// 当前账户可用余额不足
		final String content = "<html><head></head><body><h3>券仓购买卡券失败</h3><br/>原因:"+qcResult.getHeader().getRespmsg()
				+"<br/>订单编号:"+orderCode
				+"<br/>购买数量:"+amount
				+"个<br/><br/><br/>大厨管家团队<br/>"+ DateUtils.getDefaultDate(new Date())+"</body></html>";
		//发送邮箱
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<String> emailList = com.alibaba.fastjson.JSONArray.parseArray(RechargePhoneUtil.systemEmail, String.class);
				for (String email : emailList) {
					try {
						//sendMail.sendMail(email, "大厨管家系统异常", content);
					} catch (Exception e) {
						logger.error(e.getMessage() + email + "发送邮箱失败");
					}
				}
			}
		}).start();
	}
	/*//卡券商品查询接口
	@Test
	public void queryList(){
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", "127.0.0.1"); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------
		
		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		//卡券商品查询接口
		header.put("apiid", "610020"); //接口编号
		body.put("showtyp", "3"); //显示类型  1：积分热兑 2：免费热兑  3:所有
		body.put("prdtype", "0"); //商品类型  0：全部1：购物  2：美食  3：娱乐  4：生活服务
		body.put("pagesize", "20"); //页面大小
		body.put("curpage", "1"); //当前页码
		
		request.put("body", body);
		//------------------------业务参数 End  ---------------------
		
		
		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);
		
		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");
		
		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);
		
		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		try{
			HttpClientUtil util = new HttpClientUtil();
			responseData = util.post(URL,requestData,mimeType,"GBK");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(responseData);
	}
	
	//卡券购买接口
	@Test
	public void buyCard(){
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", "127.0.0.1"); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------
		
		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		//卡券购买接口
		header.put("apiid", "620006"); //接口编号
		body.put("mercid",OPENID); //商户编号(OpenId)
		//TODO 根据查询接口返回的actid
		body.put("actid",""); //营销案编号
		JSONArray infoArray = new JSONArray();
		JSONObject info = new JSONObject();
		info.put("phoneno","15280038813"); //手机号
		info.put("amount","1"); //发放数量
		infoArray.add(info);
		JSONObject info1 = new JSONObject();
		info1.put("phoneno","15280038814"); //手机号
		info1.put("amount","1"); //发放数量
		infoArray.add(info1);
		body.put("infos", infoArray);
		
		request.put("body", body);
		//------------------------业务参数 End  ---------------------
		
		
		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);
		
		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");
		
		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);
		
		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		try{
			HttpClientUtil util = new HttpClientUtil();
			responseData = util.post(URL,requestData,mimeType,"GBK");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(responseData);
	}
	
	//券码查询接口
	@Test
	public void queryCard(){
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", "127.0.0.1"); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------
		
		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		//券码查询接口
		header.put("apiid", "620007"); //接口编号
		//TODO 根据卡券购买接口返回的barcode
		body.put("barcode", "");
		body.put("reserved", "");
		
		request.put("body", body);
		//------------------------业务参数 End  ---------------------
		
		
		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);
		
		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");
		
		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);
		
		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		try{
			HttpClientUtil util = new HttpClientUtil();
			responseData = util.post(URL,requestData,mimeType,"GBK");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(responseData);
	}
	
	//订单查询接口
	@Test
	public void queryOrder(){
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", "127.0.0.1"); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------
		
		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		//订单查询接口
		header.put("apiid", "620015"); //接口编号
		//TODO 根据购买卡券返回的ordno
		body.put("ordno", "");
		
		request.put("body", body);
		//------------------------业务参数 End  ---------------------
		
		
		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);
		
		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");
		
		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);
		
		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		try{
			HttpClientUtil util = new HttpClientUtil();
			responseData = util.post(URL,requestData,mimeType,"GBK");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(responseData);
	}
	
	//账户明细查询接口
	@Test
	public void queryAccountDetail(){
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", "127.0.0.1"); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------
		
		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		
		//账户明细查询接口
		header.put("apiid", "620016"); //接口编号
		body.put("merid", OPENID);
		body.put("pagesize", "20"); //页面大小
		body.put("curpage", "1"); //当前页码
		
		request.put("body", body);
		//------------------------业务参数 End  ---------------------
		
		
		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);
		
		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");
		
		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);
		
		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		try{
			HttpClientUtil util = new HttpClientUtil();
			responseData = util.post(URL,requestData,mimeType,"GBK");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(responseData);
	}
	
	//商品库存查询接口
	@Test
	public void queryStock(){
		SimpleDateFormat sdf = new SimpleDateFormat();
		JSONObject request = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("ipaddr", "127.0.0.1"); //渠道IP
		header.put("chnno", CHNNO); //渠道编号
		header.put("version", "1.0");
		sdf.applyPattern("yyyyMMddHHmmss");
		header.put("reqopetm", sdf.format(new Date())); //请求操作时间
		sdf.applyPattern("yyyyMMdd");
		header.put("busdt", sdf.format(new Date())); //业务时间
		sdf.applyPattern("yyMMdd");
		header.put("reqjnl", sdf.format(new Date())+getRequestjournal()); //请求流水 25位全局唯一，不可重复
		//------------------------以上通用---------------------
		
		//------------------------业务参数 Begin---------------------
		JSONObject body = new JSONObject();
		
		//商品库存查询接口
		header.put("apiid", "620023"); //接口编号
		//TODO 指定具体商品营销编号
		body.put("actid",""); //营销案编号
		
		request.put("body", body);
		//------------------------业务参数 End  ---------------------
		
		
		//------------------------加密操作---------------------
		JSONObject security = new JSONObject();
		request.put("header", header);
		
		String desKey = DESKEY + header.getString("reqopetm");
		CryptUtilImpl cryptUtil = new CryptUtilImpl();
		String desvalue = cryptUtil.cryptDes(desKey, desKey);
		String signKey = SIGNKEY + header.getString("reqopetm");
		
		String signatureValue = cryptUtil.cryptMd5(JSON.toJSONString(request), signKey);
		security.put("desvalue", desvalue);
		security.put("signvalue", signatureValue);
		request.put("security", security);
		
		//--------------------以上为构建完整的请求参数----------------------------
		String mimeType="text/html";
		String requestData = JSON.toJSONString(request);
		System.out.println(requestData);
		//发送请求
		String responseData = "";
		try{
			HttpClientUtil util = new HttpClientUtil();
			responseData = util.post(URL,requestData,mimeType,"GBK");
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(responseData);
	}*/
	
}
