package com.egeo.components.config.vo;

public class PolymallRequest {
	
	/**
	 * 查询商品
	 */
	public static final String METHOD_DOWNLOAD_PRODUCT = "Differ.JH.Business.DownloadProduct";
	/**
	 * 下载订单
	 */
	public static final String METHOD_GET_ORDER = "Differ.JH.Business.GetOrder";
	/**
	 * 退款检测
	 */
	public static final String METHOD_CHECK_REFUND_STATUS = "Differ.JH.Business.CheckRefundStatus";
	/**
	 * 订单发货
	 */
	public static final String METHOD_SEND = "Differ.JH.Business.Send";
	/**
	 * 商品库存同步
	 */
	public static final String METHOD_SYNC_STOCK = "Differ.JH.Business.SyncStock";
	
	private String method;
	
	private String appkey;
	
	private String token;
	
	private String bizcontent;
	
	private String sign;
	
	public PolymallRequest() {
		
	}

	public PolymallRequest(String method, String appkey, String token, String bizcontent, String sign) {
		this.method = method;
		this.appkey = appkey;
		this.token = token;
		this.bizcontent = bizcontent;
		this.sign = sign;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBizcontent() {
		return bizcontent;
	}

	public void setBizcontent(String bizcontent) {
		this.bizcontent = bizcontent;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
