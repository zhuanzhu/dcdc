package com.egeo.components.config.controller.api;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.components.config.business.PolymallManage;
import com.egeo.components.config.vo.PalymallResponse;
import com.egeo.components.config.vo.PolymallRequest;
import com.egeo.utils.encrypt.MD5;
import com.egeo.web.BaseSpringController;

/**
 * 网店管家对接开放api
 * AppKey: 4cced8538d98447db4e876252edf5adc
 * AppSecret: c8a563bcfb7c41318d908bfce54a91df
 * Token: 11488ca7d4bf48dd9f47e98117c3eefb:5
 * 		  abefec99ea0b467f847f421e6497ec2a:3
 * @author tan
 *
 */
@Controller
@RequestMapping("/api/config/polymall")
public class PolymallAction extends BaseSpringController {
	
	private static final String APP_SECRET = "c8a563bcfb7c41318d908bfce54a91df";

	@Resource(name="polymall")
	private PolymallManage polymallManage;
	
	@RequestMapping(value = "/openapi")
	@ResponseBody
	public Map<String, Object> openapi(HttpServletRequest request) {
		Map<String, Object> result = null;
		try {
			PolymallRequest polymallRequest = parsePolymallRequest(request);
			logger.info("polymall接口调用，请求参数：{}", JSON.toJSONString(polymallRequest));
			if (checkRequest(polymallRequest)) {
				if (PolymallRequest.METHOD_DOWNLOAD_PRODUCT.equals(polymallRequest.getMethod())) {
					result = polymallManage.queryProduct(polymallRequest.getBizcontent(), polymallRequest.getToken());
				}else if (PolymallRequest.METHOD_GET_ORDER.equals(polymallRequest.getMethod())) {
					result = polymallManage.queryOrder(polymallRequest.getBizcontent(), polymallRequest.getToken());
				} else if (PolymallRequest.METHOD_CHECK_REFUND_STATUS.equals(polymallRequest.getMethod())) {
					result = polymallManage.checkOrderStatus(polymallRequest.getBizcontent());
				} else if (PolymallRequest.METHOD_SEND.equals(polymallRequest.getMethod())) {
					result = polymallManage.deliverOrder(polymallRequest.getBizcontent());
				} else if (PolymallRequest.METHOD_SYNC_STOCK.equals(polymallRequest.getMethod())) {
					result = polymallManage.syncStock(polymallRequest.getBizcontent());
				} else {
					result = PalymallResponse.buildResponse(40000, "平台不支持此方法", PalymallResponse.RSP_PLAT_NOT_SUPPORT, "平台不支持此方法");
				}
			} else {
				result = PalymallResponse.buildResponse(40000, "签名验证失败", PalymallResponse.RSP_VERIFYSIGN_FAILURE, "签名验证失败");
			}
		} catch (Exception e) {
			logger.error("polymall接口调用错误", e);
			result = PalymallResponse.buildResponse(40000, "系统错误", PalymallResponse.RSP_SYSTEM_ERROR, e.getMessage());
		}
		return result;
	}
	
	private PolymallRequest parsePolymallRequest(HttpServletRequest request) {
		String method = request.getParameter("method");
		String appkey = request.getParameter("appkey");
		String token = request.getParameter("token");
		String bizcontent = request.getParameter("bizcontent");
		String sign = request.getParameter("sign");
		return new PolymallRequest(method, appkey, token, bizcontent, sign);
	}
	
	private boolean checkRequest(PolymallRequest polymallRequest) {
		StringBuilder reqStr = new StringBuilder(APP_SECRET);
		reqStr.append("appkey");
		reqStr.append(polymallRequest.getAppkey());
		reqStr.append("bizcontent");
		reqStr.append(polymallRequest.getBizcontent());
		reqStr.append("method");
		reqStr.append(polymallRequest.getMethod());
		reqStr.append("token");
		reqStr.append(polymallRequest.getToken());
		reqStr.append(APP_SECRET);
		String sign = MD5.encode(reqStr.toString().toLowerCase());
		if (sign.equalsIgnoreCase(polymallRequest.getSign())) {
			return true;
		}
		return false;
	}
	
}
