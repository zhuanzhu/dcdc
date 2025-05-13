package com.egeo.components.pay.business;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.pay.vo.StandardOrderQueryResultVO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.web.JsonResult;

public interface PayCoreManage {
	/**
	 * 微信支付签名
	 * @param orderId
	 * @param userId
	 * @param platformId
	 * @param userIp
	 * @return
	 */
	JsonResult<Map<String, Object>> h5WXPaySign(StandardOrderQueryResultVO order, String userIp,Long platformId);

	/**
	 * 处理微信回调,确认订单支付完成
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	boolean handleWxPayCallBack(String xmlStr, HttpServletRequest req);

	/**
	 * 生成支付宝页面代码
	 * @param orderId
	 * @param userId
	 * @param platformId
	 * @return
	 */
	String genAliPayHtml(String orderId);

	/**
	 * 处理支付宝回调
	 * @param params
	 * @return
	 */
	boolean handleAliPayCallBack(Map<String, String> params, HttpServletRequest req);

	/**
	 * 标准订单查询,将订单转变为VO返回
	 * 调用时认为orderCode已经做了前置非空处理
	 * @param orderCode
	 * @return
	 */
	JsonResult<StandardOrderQueryResultVO> standardOrderQuery(String orderCode);

	/**
	 * 支付宝原生签名接口
	 * @param order
	 * @return
	 */
	JsonResult<Map<String, Object>> nativeAlipaySign(StandardOrderQueryResultVO order,Integer signPlatform,HttpServletRequest req,HttpServletResponse rse);

	/**
	 * 支付时标准订单查询接口
	 * @param useFubi
	 * @param clientId
     *@param orderCode  @return
	 */
	JsonResult<Map<String, Object>> standardOrderQueryAtPayInvoke(String clientId, String orderCode,Integer payType, HttpServletRequest req);

	/**
	 * 取消支付接口
	 * @param orderCode
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> standardPayCancle(String orderCode, Long userId);

	/**
	 * 支付方式选择页面的标准订单查询接口
	 * @param orderCode
	 * @return
	 */
	JsonResult<Map<String, Object>> standardOrderQueryAtPayMethod(String orderCode);

	/**
	 * 微信签名接口
	 * @param order 订单信息
	 * @param clientId
     *@param userIp
     * @param platformId 平台id
     * @param signPlatform 签名平台 1:原生 2:h5 3:公众号 4:扫码支付 5:电脑网站 默认1
     * @param weiXinOpenId 微信OpenId
     * @param req
     * @param rse       @return
	 */
	JsonResult<Map<String, Object>> nativeWXpaySign(
            String clientId, StandardOrderQueryResultVO data,
            String userIp,
            Long platformId,
            Integer signPlatform,
            String weiXinOpenId,
            HttpServletRequest req,
            HttpServletResponse rse);

	/**
	 * 通过订单编号查询订单信息
	 * @param orderCode
	 * @return
	 */
	SoDTO queryOrderByOrderCode(String orderCode);

	/**
	 * 仅取消队列和支付网关
	 * @param orderCode
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> cashPayCancel(String orderCode, Long userId);

	/**
	 * 通过用户id查询用户信息
	 * @param userId
	 * @return
	 */
	UserDTO queryUserById(Long userId);
	/**
	 * 支付宝扫码签名接口
	 * @param order
	 * @return
	 */
	JsonResult<Map<String, Object>> scanCodeAlipaySign(StandardOrderQueryResultVO order);

}
