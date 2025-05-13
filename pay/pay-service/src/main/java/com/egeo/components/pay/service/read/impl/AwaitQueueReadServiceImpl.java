package com.egeo.components.pay.service.read.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.egeo.components.pay.converter.AwaitQueueConverter;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.manage.read.AwaitQueueReadManage;
import com.egeo.components.pay.po.AwaitQueuePO;
import com.egeo.components.pay.service.read.AwaitQueueReadService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.pay.PayUtil;
import com.egeo.utils.str.UUID;

@Service("awaitQueueReadService")
public class AwaitQueueReadServiceImpl  implements AwaitQueueReadService {
	/**
	 * 阿里私钥
	 */
	private static final String aliPayPrivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDFG14ijdiElzrx"
			+ "mt6K42RYl21kgM9t6Kx/X1vMTKqluWOc0ZX+88IoyTMH6Iq8oEupYVbytz7zLH"
			+ "CDJbV0utGW/z9FRKIHXmpZt/N2CJ5j5lNvKFzeV+m/FJwYrnaeAEWrnvLs1JJB"
			+ "4AwQfFR30oehbpESecXAon66FvmHBA3r56szl21ljhESioZcYkGX3IEgrgia6d"
			+ "bzssDzPcHwawparbKISaN+qTBnGL67W61KSgxjFrjB5JXtS9QD3chGNo4iFfuy"
			+ "yI35CtZ7UpGrA9eWfxtsSy/zWcBSjoUqM7kKYaKbrcn/o7iQt18UgPsR2MEDvv"
			+ "jPYJ4CSomjA9hpNeHPAgMBAAECggEAffmV2oPJ5EQ7mfsfTKk0kyKE+f0Rle9l"
			+ "gCrUIujFEBJCxhF1ynLEnwioot9O/Gt+sAzUY2AWzZiTeF57cNWN6vHL4FuTKx"
			+ "5+3kShR8kOYk7s+FhVso2BiWyEr4mhqi4GWT/HhvH9YDjvvQGZgtN9eAGQmrxV"
			+ "VV8Fm63fYRmIREK3c/bukc+rO7oOqc+A031UM/jAPsIYb4tOhUxSYPHJOru2x+"
			+ "e3KImFyR+gM5XMTGcL8e6f4A3yUKflM5kndLXvV1jlBPAUd7572p/WBQ92KorO"
			+ "T9+e0w8Zyq1HAg2KElSBTz87+Vr8v1lf2mDlhNi3P73x7mpW21PKKN/DR+b5EQ"
			+ "KBgQDnvJCDUDw4TtwTeXcC5WgkbB9p64YW3HosHJ1J6m6HdQJQKpfJ7w5Z21E5"
			+ "XsAE02D7Uh0GAwlBtZK9elstflouTVKe1Uxg5zTE8Wcx1pManWcdbQIyJ/lVYq"
			+ "4w2Y/Z8HuvrbXoaQVlLLd/QcJ54bfkH8DY3b6sOAMVjK48mN+6TQKBgQDZvpga"
			+ "i6fMDjOAOSTA2Tb9MaXt1hZwz27dwtHBsAdUBClmn0WHkCm36NqYDnl0/U0gm9"
			+ "towGkF+GpYGIAUgTMZ0YKcn/HIkV87i2/jFPwE6+LgC4u9kTRoSSz/f8iNzWxs"
			+ "bygh+p1C/AMbMezYW0CCYkSYnRR1y6HKFrz+0LOiiwKBgQCQnvmBOd2bNnvaG3"
			+ "/OauTndiLUs4vnUIleZi0zJ/76wmdDKJG6EFrgcSLrrtofKNvpfIFocxSf0XVW"
			+ "krm8VpJpOxBI18zm3OPt1MoWLFd50Km9j35DVvcf9onHKr+/v3kxVjOka4ZWce"
			+ "XCoYOBFQDyeW6c6SZJzFsAY9+JKc7WMQKBgQDDy9nUTO5G1CJFU50T+H608FcH"
			+ "ZbJqIGzN5mhdZV9Bkjh0OhX4j46j/DPL9YALzdXz05JZQGNv5fAiZW2OgpsjxT"
			+ "xQgJaWxvm+fmTO6kiwWDh7RIaUoKRifiKDYTFDKYwseUu6OjP9TsWwR5V0+6En"
			+ "XRvXq7ABOGm0YfvymlJdfwKBgQC7Od3poxJ9u1MTpyWwPas9+0Izvd1Buheo+B"
			+ "eiyXYXJaTAEhN/gv9qi5Q8Mn44OEMLqTlzzCJenuZUVqZy+w8/VsGyC8p8XTII"
			+ "gzmA46kZiaJYHmxHDMAynNYVhovyuv38wzBW1Gpll/8Jmwd7DUWVYXDXoPuDqW" + "6XIkEe5MPd2A==";
	
	@Autowired
	private AwaitQueueReadManage awaitQueueReadManage;

	@Autowired
	private PayUtil payUtil;
	@Override
	public AwaitQueueDTO findAwaitQueueById(AwaitQueueDTO dto) {
		AwaitQueuePO po = AwaitQueueConverter.toPO(dto);
		AwaitQueuePO list = awaitQueueReadManage.findAwaitQueueById(po);		
		return AwaitQueueConverter.toDTO(list);
	}

	@Override
	public PageResult<AwaitQueueDTO> findAwaitQueueOfPage(AwaitQueueDTO dto, Pagination page) {
		AwaitQueuePO po = AwaitQueueConverter.toPO(dto);
        PageResult<AwaitQueuePO> pageResult = awaitQueueReadManage.findAwaitQueueOfPage(po, page);
        
        List<AwaitQueueDTO> list = AwaitQueueConverter.toDTO(pageResult.getList());
        PageResult<AwaitQueueDTO> result = new PageResult<AwaitQueueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AwaitQueueDTO> findAwaitQueueAll(AwaitQueueDTO dto) {
		AwaitQueuePO po = AwaitQueueConverter.toPO(dto);
		List<AwaitQueuePO> list = awaitQueueReadManage.findAwaitQueueAll(po);		
		return AwaitQueueConverter.toDTO(list);
	}
	/**
	 * 根据订单编号查询是否处于支付等待列表
	 * @param orderCode
	 * @return
	 */
	@Override
	public AwaitQueueDTO findByOrderCode(String orderCode) {
		AwaitQueuePO awaitQueuePO = awaitQueueReadManage.findByOrderCode(orderCode);
		return AwaitQueueConverter.toDTO(awaitQueuePO);
	}
	/**
	 * 根据订单号查询支付状态
	 * @param orderCode
	 * @return
	 */
	public Integer getOrderPayIsSuccess(AwaitQueueDTO awaitQueueDTO){
		if(awaitQueueDTO.getCashPayType() == 1){
			return aliGetOrderPayIsSuccess(awaitQueueDTO.getOrderCode());
		}
		if(awaitQueueDTO.getCashPayType() == 2){
			return weixinGetOrderPayIsSuccess(String.valueOf(awaitQueueDTO.getOrderCode() + awaitQueueDTO.getRandomNumber()));
		}
		return 3;
	}
	/**
	 * 微信根据订单号查询支付状态
	 * @param orderCode
	 * @return
	 */
	private Integer weixinGetOrderPayIsSuccess(String orderCode){
		// 组织签名
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("appid", payUtil.getProperty("wx.app.id.native"));
		paraMap.put("mch_id", payUtil.getProperty("wx.mch.id.native"));
		paraMap.put("nonce_str", UUID.uuid());
		paraMap.put("sign_type", payUtil.getProperty("wx.sign.type"));
		paraMap.put("out_trade_no", orderCode);
		// 打统一下单接口,做字典排序,生成签名,获取h5调取的url
		Map<String, String> responseParams = null;
		try {
			responseParams = payUtil.wxFindBuildXmlRequest(paraMap);
			String returnCode = responseParams.get("return_code");
			if (!returnCode.equals("SUCCESS")){
				throw new BusinessException("签名失败,原因是" + responseParams.get("return_msg"));
			}else{
				String tradeState = responseParams.get("trade_state");
				//SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付） USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
				if(tradeState.equals("SUCCESS") || tradeState.equals("REFUND") || tradeState.equals("CLOSED")){
					return 1;
				}else if(tradeState.equals("REVOKED") || tradeState.equals("PAYERROR")){
					return 2;
				}else if(tradeState.equals("NOTPAY") || tradeState.equals("USERPAYING")){
					return 3;
				}
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("签名错误");
		}
		return 3;
	}
	/**
	 * 支付宝根据订单号查询支付状态
	 * @param orderCode
	 * @return
	 */
	private Integer aliGetOrderPayIsSuccess(String orderCode){
		
		/**
		 * 参考 https://docs.open.alipay.com/203/105285/
		 */
		AlipayClient alipayClient = new DefaultAlipayClient(payUtil.getProperty("ali.openapi.url"),
				payUtil.getProperty("ali.app.id"), aliPayPrivateKey, payUtil.getProperty("ali.request.content.type"),
				payUtil.getProperty("ali.request.charset"), payUtil.getProperty("ali.public.key"),
				payUtil.getProperty("ali.sign.type")); // 获得初始化的AlipayClient
		try {
			AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
			request.setBizContent("{" +
			"\"out_trade_no\":\""+orderCode+"\"," +
			"\"trade_no\":\"\"" +
			"}");
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				//交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
				String status = response.getTradeStatus();
				if(status.equals("TRADE_SUCCESS") || status.equals("TRADE_FINISHED")){
					return 1;
				}else if(status.equals("TRADE_CLOSED")){
					return 2;
				}else if(status.equals("WAIT_BUYER_PAY")){
					return 3;
				}
			System.out.println("调用成功");
			} else {
			System.out.println("调用失败");
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return 3;
	}
	/**
	 * 根据订单编号撤销订单支付请求
	 * @param orderCode
	 * @return
	 */
	public boolean revocationOrderPay(AwaitQueueDTO awaitQueueDTO, Long platformId){
		if(awaitQueueDTO.getCashPayType() == 1){
			payUtil.alipayCloseSub(awaitQueueDTO.getOrderCode());
			return true;
			//return aliRevocationOrderPay(awaitQueueDTO.getOrderCode());
		}
		if(awaitQueueDTO.getCashPayType() == 2){
			payUtil.wxCancelSub(awaitQueueDTO.getOrderCode(), platformId);
			return true;
			//return weixinRevocationOrderPay(String.valueOf(awaitQueueDTO.getOrderCode() + awaitQueueDTO.getRandomNumber()));
		}
		return false;
	}
//	/**
//	 * 微信根据订单关闭支付请求
//	 * @param orderCode
//	 * @return
//	 */
//	@Deprecated
//	private boolean weixinRevocationOrderPay(String orderCode){
//		// 组织签名
//		Properties prop = PayUtil.payConfig;
//		Map<String, String> paraMap = new HashMap<>();
//		paraMap.put("appid", prop.getProperty("wx.app.id.native"));
//		paraMap.put("mch_id", prop.getProperty("wx.mch.id.native"));
//		paraMap.put("nonce_str", UUID.uuid());
//		paraMap.put("out_trade_no", orderCode);
//		// 打统一下单接口,做字典排序,生成签名,获取h5调取的url
//		Map<String, String> responseParams = null;
//		try {
//			responseParams = PayUtil.weixinRevocationOrderPay(paraMap);
//			String returnCode = responseParams.get("return_code");
//			if (!returnCode.equals("SUCCESS")){
//				throw new BusinessException("签名失败,原因是" + responseParams.get("return_msg"));
//			}else{
//				String tradeState = responseParams.get("trade_state");
//				//SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付） USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
//				if(tradeState.equals("SUCCESS")){
//					return true;
//				}else{
//					return false;
//				}
//			}
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new BusinessException("签名错误");
//		}
//	}
	
//	/**
//	 * 阿里根据订单编号撤销订单支付请求
//	 * @param orderCode
//	 * @return
//	 */
//	@Deprecated
//	private boolean aliRevocationOrderPay(String orderCode) {
//		Properties prop = PayUtil.payConfig;
//		/**
//		 * 参考 https://docs.open.alipay.com/203/105285/
//		 */
//		AlipayClient alipayClient = new DefaultAlipayClient(prop.getProperty("ali.openapi.url"),
//				prop.getProperty("ali.app.id"), aliPayPrivateKey, prop.getProperty("ali.request.content.type"),
//				prop.getProperty("ali.request.charset"), prop.getProperty("ali.public.key"),
//				prop.getProperty("ali.sign.type")); // 获得初始化的AlipayClient
//		try {
//			AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
//			request.setBizContent("{" +
//			"\"out_trade_no\":\""+orderCode+"\"," +
//			"\"trade_no\":\"\"" +
//			"  }");
//			AlipayTradeCancelResponse response = alipayClient.execute(request);
//			if(response.isSuccess()){
//			System.out.println("调用成功");
//			return true;
//			} else {
//			System.out.println("调用失败");
//			return false;
//			}
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	@Override
	public AwaitQueueDTO queryAwaitQueueByOrderId(Long orderId) {
		return AwaitQueueConverter.toDTO(awaitQueueReadManage.queryAwaitQueueByOrderId(orderId));
	}
}
	