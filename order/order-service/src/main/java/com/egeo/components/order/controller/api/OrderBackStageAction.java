package com.egeo.components.order.controller.api;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.vo.OrderSearchVO;
import com.egeo.components.order.vo.RePushRefundOrderVO;
import com.egeo.components.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.order.business.SoItemManage;
import com.egeo.components.order.business.SoManage;
import com.egeo.components.order.controller.client.SoChildController;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.vo.SoDetailItemVo;
import com.egeo.components.order.vo.SoVO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.orm.Pagination;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/api/order/backStage")
public class OrderBackStageAction extends BaseSpringController {

	@Resource(name = "so")
	private SoManage soManage;
	@Autowired
	private SoChildWriteService soChildWriteService;
	@Resource
	private SoFacade soFacade;

	@Resource(name = "soItem")
	private SoItemManage soItemManage;
	@Resource
	private PushOrderManage pushOrderManage;

	Gson gson = new Gson();

//	/**
//	 * 后台查询订单列表
//	 *
//	 * @param pageNo
//	 * @param pageSize
//	 * @param orderCode
//	 * @param merchantName
//	 * @param startTime
//	 * @param endTime
//	 * @param orderStatus
//	 * @return
//	 */
//	@Deprecated
//	@RequestMapping(value = "/backOrderList", method = RequestMethod.POST)
//	@ResponseBody
//	public JsonResult<PageResult<SoListBackVo>> backOrderList(Integer pageNo, Integer pageSize, String orderCode,
//			String merchantName, Long startTime, Long endTime, Integer orderStatus, HttpServletRequest req) {
//		logger.info("后台查询订单列表");
//		String str = req.getHeader("platformId");
//		if (EmptyUtil.isBlank(str))
//			return JsonResult.fail("platformId为空");
//		Long platformId = Long.valueOf(str);
//		PageResult<SoListBackVo> result = soManage.backOrderList(pageNo, pageSize, orderCode, merchantName, startTime,
//				endTime, orderStatus, platformId);
//		return JsonResult.success(result);
//	}

	/**
	 * 后台查询母订单列表
	 *
	 * @param paymentType
	 * @param startTime
	 * @param endTime
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderList", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String,Object>> orderList(OrderSearchVO searchVO, HttpServletRequest req) {
		logger.info("后台查询订单列表");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		if(EmptyUtil.isEmpty(searchVO.getStoreId())||searchVO.getStoreId()==0){
			if(platformId==7){
				searchVO.setStoreId(1L);
			}
		}
		if(EmptyUtil.isEmpty(searchVO.getOrderConfirmStatus())||searchVO.getOrderConfirmStatus()==-99){
			searchVO.setOrderConfirmStatus(null);
		}
		if(EmptyUtil.isEmpty(searchVO.getOrderPayStatus())||searchVO.getOrderPayStatus()==-99){
			searchVO.setOrderPayStatus(null);
		}
		//门店运营端需要显示测试数据
		if(EmptyUtil.isNotEmpty(searchVO.getStoreId())&&searchVO.getStoreId()!=1L){
			searchVO.setShowTest(true);
		}
		searchVO.setPlatformId(platformId);
		searchVO.setRefundFlag(false);
		return soManage.orderList(searchVO);
	}

	/**
	 * 后台查看订单详情
	 *
	 * @param orderId
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/backOrderDetail", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> backOrderDetail(Long orderId,String orderCode, HttpServletRequest req) {
		logger.info("根据id查询订单详情");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		if((orderId==0||orderId==null)&&orderCode!=null){
			List<SoDTO> list=soManage.findSoByCode(orderCode);
			if(list.size()==0||list==null){
				return JsonResult.fail("该订单不存在");
			}else if (list.size()>1){
				return JsonResult.fail(orderCode+"该订单重复出现");
			}else {
				orderId = list.get(0).getId();
			}

		}

		return soManage.backOrderDetail(orderId, platformId);
	}

	/**
	 * 后台查看母订单详情
	 *
	 * @return
	 */
	@RequestMapping(value = "/findOrderDetail", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> findOrderDetail(Long soId, HttpServletRequest req) {
		logger.info("后台查看母订单详情");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);

		return soManage.findOrderDetail(soId, platformId);
	}

	/**
	 * 后台修改订单状态
	 *
	 * @return
	 */
	@RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> updateOrderStatus(String orderCode, Integer status) {
		logger.info("根据订单id修改订单信息");
		return soManage.updateOrderStatus(orderCode, status);
	}

	/**
	 * 后台修改订单信息
	 *
	 * @param orderId
	 * @param orderAmount
	 * @param deliveryAmount
	 * @param payMoney
	 * @return
	 */
	@RequestMapping(value = "/updateOrderInfo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> updateOrderInfo(Long orderId, BigDecimal orderAmount,
			BigDecimal deliveryAmount, BigDecimal payMoney) {
		logger.info("根据订单id修改订单信息");
		return soManage.updateOrderInfo(orderId, orderAmount, deliveryAmount, payMoney);
	}
	@RequestMapping(value = "/updateChildSoRemark", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> updateChildSoRemark(Long orderChildId, String  remark) {
		logger.info("根据子订单id修改订单备注");
		SoChildDTO dto = new SoChildDTO();
		dto.setId(orderChildId);
		dto.setRemark(remark);
		soChildWriteService.updateSoChildWithTx(dto);
		return JsonResult.success(null);
	}

	/**
	 * 后台修改收货信息
	 *
	 * @param orderId
	 * @param receiverName
	 * @param receiverPhone
	 * @param receiverAddress
	 * @return
	 */
	@RequestMapping(value = "/updateDeliveryInfo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> updateDeliveryInfo(Long orderId, String receiverName, String receiverPhone,
			String receiverAddress) {
		logger.info("后台修改收货信息");
		return soManage.updateDeliveryInfo(orderId, receiverName, receiverPhone, receiverAddress);
	}

	/**
	 * 获取订单状态列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/orderStatusList", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<List<Map<String, String>>> orderStatusList() {


		logger.info("获取订单状态列表");
		return soManage.orderStatusList();
	}

	/**
	 * 后台根据订单id修改订单收货人信息
	 *
	 * @param soVO
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateOrderByOrderId")
	@ResponseBody
	public JsonResult<String> updateOrderByOrderId(SoVO soVO, HttpServletRequest req) {
		logger.info("根据订单id修改订单收货人信息");
//		CacheUser userCache = (CacheUser) req.getAttribute("ut");
		String platformId_ = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(platformId_)) {
			return JsonResult.fail("platformId为空");
		} else {
			soVO.setPlatformId(Long.parseLong(platformId_));
		}
		return soManage.updateOrderByOrderId(soVO);
	}

	/**
	 * 后台根据订单id查询订单项信息
	 *
	 * @param soId
	 * @return
	 */
	@RequestMapping(value = "/soDetailItemVoBySoId")
	@ResponseBody
	public JsonResult<List<SoDetailItemVo>> soDetailItemVoBySoId(Long soId, Long packId, HttpServletRequest req) {
		logger.info("根据订单id查询订单项信息");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return soManage.soDetailItemVoBySoId(soId, packId, platformId);
	}

	/**
	 * 后台删除订单
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/deleteBySoId")
	@ResponseBody
	public JsonResult<String> deleteBySoId(String orderCode, HttpServletRequest req) {
		logger.info("根据订单id删除订单");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		soManage.deleteByOrderCode(orderCode, platformId);
		return JsonResult.success(null);
	}

	/**
	 * 根据母订单查询所有子订单
	 *
	 * @param soId
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findAllSOChildBySoId", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> findAllSOChildBySoId(Long soId, Pagination page, HttpServletRequest req) {
		logger.info("后台查询所有子单列表");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		if (EmptyUtil.isEmpty(soId)) {
			return JsonResult.fail("母单id不能为空");
		}
		SoChildDTO soChildDTO = new SoChildDTO();
		soChildDTO.setSoId(soId);
		soChildDTO.setPlatformId(platformId);
		return soManage.findAllSOChild(soChildDTO, page);
	}

	/**
	 * 获取订单收货信息
	 *
	 * @param orderId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/receiverInfos", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> receiverInfos(Long orderId, HttpServletRequest req) {
		return soManage.receiverInfos(orderId);
	}

	/**
	 * 导出子订单
	 *
	 * @param orderId
	 *            订单id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/exportSoChild", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> exportSoChild(Long orderId, HttpServletRequest req) {
		logger.info("导出子订单！");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return soManage.exportSoChild(orderId, platformId);
	}

	/**
	 * 后台查询子单详情
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findSochildById", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> findSochildById(Long soChildId, HttpServletRequest req) {
		logger.info("后台查询子单详情");
		return soManage.findSochildById(soChildId);
	}

	/**
	 * 查询拆单时使用的pu列表
	 *
	 * @param soChildId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/puInfoForOpenOrder", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> puInfoForOpenOrder(Long soChildId, HttpServletRequest req) {
		logger.info("查询商品和收获地址的信息");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		return soManage.puInfoForOpenOrder(soChildId, platformId);
	}

	/**
	 * 查询该子订单的快照,原始快照,用户创建,后台创建列表
	 *
	 * @param soChildId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/userReceiverInfos", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> userReceiverInfos(Long soChildId, Long pageNum,HttpServletRequest req) {
		logger.info("查询该子订单的快照,原始快照,用户创建,后台创建列表");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);

		return soManage.getSoChildAllReceiverInfoPage(soChildId, platformId,pageNum);
	}

	/**
	 * 查询该子订单的所属用户的收货地址列表
	 *
	 * @param soChildId
	 * @param req
	 * @return
	@RequestMapping(value = "/userReceiverInfos", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> userReceiverInfos(Long soChildId, HttpServletRequest req) {
		logger.info("查询该子订单的所属用户的收货地址列表");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);

		return soManage.userReceiverInfos(soChildId, platformId);
	}*/



	/**
	 * 根据地质id查询地址信息
	 * @param updateAddressId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/receiverAddressById",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> receiverAddressById(Long updateAddressId,HttpServletRequest req){
		logger.info("查询该id的地址详情");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);

		return soManage.receiverAddressById(updateAddressId, platformId);
	}

	@RequestMapping(value = "/updateAddressCreateByBackStage",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String,Object>> updateAddressCreateByBackStage(Long soChildId, Long updateAddressId,
																		 String receiverName, String receiverMobile, Long provinceId, Long cityId, Long countyId, String address,
																		 HttpServletRequest req){
		logger.info("更新后台创建收货地址信息");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		CacheUser userCache = this.getCacheUser();
		SoChildDTO oldSoChildDTO =	soManage.querySoChildById(soChildId);
		Long memberId = userCache.getId();// 用户id
		JsonResult<Map<String, Object>> result = soManage.updateAddressCreateByBackStage(soChildId, updateAddressId, receiverName, receiverMobile, provinceId,
				cityId, countyId, address, platformId, memberId);

		if (result.getCode() == 0) {
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("OrderBackStageAction_updateReceiverInfo");
			log.setOperatorObjCode(oldSoChildDTO.getChildCode());
				// 修改收货地址
				SoChildDTO newSoChildDTO = soManage.querySoChildById(soChildId);
				log.setMsgId(LogConstant.RECEIVER_ADDRESS_MODIFY.getStatus());
				log.setType(LogTypeConstant.SO_CHILD.getStatus());
				log.setOperatorObjId(newSoChildDTO.getId());
				log.setNewObj(newSoChildDTO);
				log.setOldObj(oldSoChildDTO);


			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}

		return result;



	}


	/**
	 * 后台新增地址,后台修改保存收货地址
	 *
	 * @param soChildId
	 * @param receiverName
	 * @param receiverMobile
	 * @param provinceId
	 * @param
	 * @param cityId
	 * @param
	 * @param countyId
	 * @param
	 * @param address
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateReceiverInfo")
	@ResponseBody
	public JsonResult<Map<String, Object>> updateReceiverInfo(Long soChildId, Long receiverAddressId,
			String receiverName, String receiverMobile, Long provinceId, Long cityId, Long countyId, String address,
			HttpServletRequest req) {
		logger.info("更新子订单收货地址信息");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		CacheUser userCache = this.getCacheUser();
		Long memberId = userCache.getId();// 用户id
		// 修改/新增收货地址操作日志(修改子订单的收货地址)
		SoChildDTO oldSoChildDTO =	soManage.querySoChildById(soChildId);


		JsonResult<Map<String, Object>> result = soManage.updateReceiverInfo(soChildId, receiverAddressId, receiverName, receiverMobile, provinceId,
				cityId, countyId, address, platformId, memberId);

		if (result.getCode() == 0) {
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("OrderBackStageAction_updateReceiverInfo");
			log.setOperatorObjCode(oldSoChildDTO.getChildCode());
			if (EmptyUtil.isNotEmpty(receiverAddressId)){
				// 修改收货地址
				SoChildDTO newSoChildDTO = soManage.querySoChildById(soChildId);
				log.setMsgId(LogConstant.RECEIVER_ADDRESS_MODIFY.getStatus());
				log.setType(LogTypeConstant.SO_CHILD.getStatus());
				log.setOperatorObjId(newSoChildDTO.getId());
				log.setNewObj(newSoChildDTO);
				log.setOldObj(oldSoChildDTO);
			} else {
				// 新增收货地址
				log.setMsgId(LogConstant.RECEIVER_ADDRESS_NEW.getStatus());
				log.setType(LogTypeConstant.RECEIVER_ADDRESS.getStatus());
				ReceiverAddressDTO newReceiverAddressDTO = soManage.queryReceiverAddressById(oldSoChildDTO.getReceiverAddressId());
				log.setOperatorObjId(newReceiverAddressDTO.getId());
				log.setNewObj(newReceiverAddressDTO);
			}

			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}

		 return result;
	}

	/**
	 * 拆单
	 *
	 * @param soChildId
	 * @param puIdArr
	 *            形如[{itemId:123,num:1}]的json数组
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/openOrder", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> openOrder(Long soChildId, String puIdArr, HttpServletRequest req) {
		logger.info("拆单");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		CacheUser userCache = this.getCacheUser();
		Long memberId = userCache.getId();// 用户id

		SoChildDTO oldSoChildDTO = soManage.querySoChildById(soChildId);

		JsonResult<Map<String, Object>> result = soManage.openOrder(soChildId, puIdArr, memberId);

		if (result.getCode() == 0) {
			SoChildDTO newSoChildDTO = soManage.querySoChildById(soChildId);
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("OrderBackStageAction_openOrder");
			log.setOperatorObjId(newSoChildDTO.getId());
			log.setOperatorObjCode(newSoChildDTO.getChildCode());
			log.setMsgId(LogConstant.CHILDORDER_SPLIT.getStatus());
			log.setType(LogTypeConstant.SO_CHILD.getStatus());
			log.setOldObj(oldSoChildDTO);
			log.setNewObj(newSoChildDTO);

			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}

		return result;
	}

	/**
	 * 母订单分拣
	 *
	 * @param soIdArrStr
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/soSort", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<String> soSort(String soIdArrStr, HttpServletRequest req) {
		logger.info("母订单分拣");
		if (StringUtils.isEmpty(soIdArrStr)) {
			return JsonResult.fail("请选择母单");
		}
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);

		return soManage.soSort(soIdArrStr, platformId);
	}

	/**
	 * 查询财务信息
	 *
	 * @param soId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findFinanceBySoId", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> findFinanceBySoId(Long soId, HttpServletRequest req) {
		logger.info("查询财务信息");
		if (soId == null) {
			return JsonResult.fail("母单Id不能为空");
		}
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		// Long platformId = Long.valueOf(str);
		SoDTO soDTO = new SoDTO();
		soDTO.setId(soId);
		return soManage.findFinanceBySoId(soDTO);
	}

	/**
	 * 查询订单退款信息
	 *
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/soRefundInfo", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> soRefundInfo(Long orderId) {
		return soManage.soRefundInfo(orderId);
	}

	/**
	 * 订单退款
	 *
	 * @param orderId
	 * @param refundCash
	 * @param refundFubi
	 * @param reason
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/soRefund", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> soRefund(
			Long orderId, Double refundCash,
			Double refundFubi,Double refundJiDian, String reason,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(str);
		CacheUser userCache = getCacheUser();
		Long memberId = userCache.getId();// 用户id

		// 订单退款记录日志(母订单的变更记录)
		SoDTO oldSoDTO = soManage.findOrderById(orderId);
		if (EmptyUtil.isEmpty(oldSoDTO)){
			return JsonResult.fail("订单不存在");
		}
		List<SoChildDTO> childes=soFacade.querySoChildListBySoId(orderId);
		if (EmptyUtil.isEmpty(childes)){
			return JsonResult.fail("对应的子订单信息不存在");
		}
		for(SoChildDTO soChildDTO:childes){
			if (Objects.equals(ThirdConst.Source.QM,soChildDTO.getSource())){
				return JsonResult.fail("清美订单不可手动退款");
			}
		}
		JsonResult<Map<String, Object>> result = soManage.soRefund(orderId,refundCash,refundFubi,reason,platformId,memberId,req,refundJiDian);

		if (result.getCode() == 0) {
			SoDTO newSoDTO = soManage.findOrderById(orderId);
			EgeoLog log = new EgeoLog();
			log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
			log.setOperObject("OrderBackStageAction_soRefund");
			log.setMsgId(LogConstant.ORDER_REFUND.getStatus());
			log.setType(LogTypeConstant.SO.getStatus());
			log.setOperatorObjId(newSoDTO.getId());
			log.setOperatorObjCode(newSoDTO.getOrderCode());
			log.setOldObj(oldSoDTO);
			log.setNewObj(newSoDTO);
			EgeoBusinessLogCommon.fillLogValue(log, req);

			try {
				ActiveMQUtils.recordBusinessLog(log);
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("发送日志消息失败："+ JSON.toJSONString(log));
			}
		}

		return result;
	}

	/**
	 * 退款订单分页列表
	 * @param orderCode
	 * @param
	 * @param startTime
	 * @param endTime
	 * @param
	 * @param
	 * @param paymentType
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/refundOrderPage", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Map<String, Object>> refundOrderPage(OrderSearchVO vo,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(str);
		if(EmptyUtil.isEmpty(vo.getStoreId())){
			if(platformId==7){
				vo.setStoreId(1L);
			}
		}
		vo.setPlatformId(platformId);
		vo.setRefundFlag(true);
		return soManage.orderList(vo);
	}

	/**
	 * 查询订单退款详情
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/refundDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> refundDetail(Long orderId){
		return soManage.refundDetail(orderId);
	}

	/**
	 * 售后服务信息列表
	 *
	 * @param orderId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/customerServices")
	@ResponseBody
	public JsonResult<Map<String, Object>> customerServices(Long orderId, HttpServletRequest req) {
		return soManage.customerServices(orderId);
	}

	/**
	 * 保存运营备注
	 *
	 * @param soChildId
	 * @param operatorRemark
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveOperatorRemark")
	@ResponseBody
	public JsonResult<Map<String, Object>> saveOperatorRemark(Long soChildId, String operatorRemark,
			HttpServletRequest req) {
		return soManage.saveOperatorRemark(soChildId, operatorRemark);
	}

	/**
	 * 母订单操作流水列表
	 *
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/soOpFlow")
	@ResponseBody
	public JsonResult<Map<String, Object>> soOpFlow(Long orderId) {
		return soManage.soOpFlow(orderId);
	}

	/**
	 * 子订单操作流水列表
	 * @param soChildId
	 * @return
	 */
	@RequestMapping(value = "/soChildOpFlow")
	@ResponseBody
	public JsonResult<Map<String, Object>> soChildOpFlow(Long soChildId){
		return soManage.soChildOpFlow(soChildId);
	}


	/**
	 * 签收
	 * @param req
	 * @param orderCode
	 * @return
	 */
	@RequestMapping(value = "/receviedConfirm")
	@ResponseBody
	public JsonResult receviedConfirm(HttpServletRequest req,String orderCode){
		logger.info("签收");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(str);
		CacheUser userCache = this.getCacheUser();
		Long operatorId = userCache.getId();
		List<Long> longList = new ArrayList<>();
		List<SoDTO> soByCode = soManage.findSoByCode(orderCode);
		if(EmptyUtil.isEmpty(soByCode)){
			return JsonResult.fail("orderCode不存在");
		}
		longList.add(soByCode.get(0).getId());

		// 分拣导入记录日志: 1.母订单变更记录  2.子订单变更记录
		Map<String,Object> oldObjects =soManage.getOrderInfoByOrderIds(longList);
		List<SoDTO> oldSoDTOList = (List<SoDTO>) oldObjects.get("soDTOList");
		List<SoChildDTO> oldSoChildDTOList = (List<SoChildDTO>) oldObjects.get("soChildDTOList");
		//校验状态
		soManage.receviedConfirm(soByCode.get(0).getId(),operatorId,platformId);

		writeExportHistory(req,"["+soByCode.get(0).getId()+"]",oldSoDTOList,oldSoChildDTOList);

		//发货导入记录(记录子母订单状态,子母流水订单)
		soManage.sendHostoryConfirm(soByCode.get(0).getId(),operatorId,platformId);
		soManage.writeDeliveryDate(soByCode.get(0).getId(),platformId,new Date());//记录发货时间

		return JsonResult.success();
	}





	/**
	 * 分拣导出
	 * @param orderIds 形如 1,2,3 的数组
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderSortExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> orderSortExport(String orderIds,HttpServletRequest req){
		logger.info("分拣导出");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(str);
		CacheUser userCache = this.getCacheUser();
		Long operatorId = userCache.getId();// 用户id

		// 分拣导入记录日志: 1.母订单变更记录  2.子订单变更记录
		// 1)查询变更前数据
		Map<String,Object> oldObjects = soManage.getOrderInfoByOrderIds(com.alibaba.fastjson.JSONArray.parseArray(orderIds, Long.class));
		List<SoDTO> oldSoDTOList = (List<SoDTO>) oldObjects.get("soDTOList");
		List<SoChildDTO> oldSoChildDTOList = (List<SoChildDTO>) oldObjects.get("soChildDTOList");

		JsonResult<Map<String, Object>> result = soManage.orderSortExport(orderIds,operatorId,platformId);

		if (result.getCode() == 0) {
			writeExportHistory(req,orderIds,oldSoDTOList,oldSoChildDTOList);
			/*// 如果成功导出则记录日志
			// 2)查询变更后数据
			Map<String,Object> newObjects = soManage.getOrderInfoByOrderIds(com.alibaba.fastjson.JSONArray.parseArray(orderIds, Long.class));
			List<SoDTO>  newSoDTOList = (List<SoDTO>) newObjects.get("soDTOList");
			List<SoChildDTO>  newSoChildDTOList = (List<SoChildDTO>) newObjects.get("soChildDTOList");

			// 3)插入操作日志
			for (SoDTO newSoDTO : newSoDTOList) {
				for (SoDTO oldSoDTO : oldSoDTOList) {
					if (newSoDTO.getId().equals(oldSoDTO.getId())) {
						EgeoLog log = new EgeoLog();
						log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
						log.setOperObject("OrderBackStageAction_orderSortExport");
						log.setMsgId(LogConstant.SORTING_OUT_SO.getStatus());
						log.setType(LogTypeConstant.SO.getStatus());
						log.setOperatorObjId(newSoDTO.getId());
						log.setOperatorObjCode(newSoDTO.getOrderCode());
						log.setOldObj(oldSoDTO);
						log.setNewObj(newSoDTO);
						EgeoBusinessLogCommon.fillLogValue(log, req);
						MqService.recordBusinessLog(log);
					}
				}
			}

			for (SoChildDTO newSoChildDTO : newSoChildDTOList) {
				for (SoChildDTO oldSoChildDTO : oldSoChildDTOList) {
					if (newSoChildDTO.getId().equals(oldSoChildDTO.getId())) {
						EgeoLog log = new EgeoLog();
						log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
						log.setOperObject("OrderBackStageAction_orderSortExport");
						log.setMsgId(LogConstant.SORTING_OUT_SOCHILD.getStatus());
						log.setType(LogTypeConstant.SO_CHILD.getStatus());
						log.setOperatorObjId(newSoChildDTO.getId());
						log.setOperatorObjCode(newSoChildDTO.getChildCode());
						log.setOldObj(oldSoChildDTO);
						log.setNewObj(newSoChildDTO);
						EgeoBusinessLogCommon.fillLogValue(log, req);
						MqService.recordBusinessLog(log);
					}
				}
			}*/
		}

		return result;
	}

	/**
	 * 分拣导出
	 * @param orderIds 形如 1,2,3 的数组
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderChildSortExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> orderSortExportChild(String orderChildIds,HttpServletRequest req){
		logger.info("子订单分拣导出");
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId=Long.parseLong(str);
		//非供应商不能操作
		if(RuntimeContext.cacheUser()== null || RuntimeContext.cacheUser().getType().intValue()!=5) {
			return JsonResult.fail("无权操作");
		}


		Long operatorId = RuntimeContext.cacheUser().getId();// 用户id
		//supplierId
		// 分拣导入记录日志: 1.母订单变更记录  2.子订单变更记录
		// 1)查询变更前数据
		Map<String,Object> oldObjects = soManage.getOrderInfoByOrderChildIds(com.alibaba.fastjson.JSONArray.parseArray(orderChildIds, Long.class));
		List<SoDTO> oldSoDTOList = (List<SoDTO>) oldObjects.get("soDTOList");
		List<SoChildDTO> oldSoChildDTOList = (List<SoChildDTO>) oldObjects.get("soChildDTOList");

		JsonResult<Map<String, Object>> result = soManage.orderChildSortExport(orderChildIds,operatorId,platformId,RuntimeContext.cacheUser().getEnterpriseId());

		if (result.getCode() == 0) {
			writeChildExportHistory(req,orderChildIds,oldSoDTOList,oldSoChildDTOList);
			/*// 如果成功导出则记录日志
			// 2)查询变更后数据
			Map<String,Object> newObjects = soManage.getOrderInfoByOrderIds(com.alibaba.fastjson.JSONArray.parseArray(orderIds, Long.class));
			List<SoDTO>  newSoDTOList = (List<SoDTO>) newObjects.get("soDTOList");
			List<SoChildDTO>  newSoChildDTOList = (List<SoChildDTO>) newObjects.get("soChildDTOList");

			// 3)插入操作日志
			for (SoDTO newSoDTO : newSoDTOList) {
				for (SoDTO oldSoDTO : oldSoDTOList) {
					if (newSoDTO.getId().equals(oldSoDTO.getId())) {
						EgeoLog log = new EgeoLog();
						log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
						log.setOperObject("OrderBackStageAction_orderSortExport");
						log.setMsgId(LogConstant.SORTING_OUT_SO.getStatus());
						log.setType(LogTypeConstant.SO.getStatus());
						log.setOperatorObjId(newSoDTO.getId());
						log.setOperatorObjCode(newSoDTO.getOrderCode());
						log.setOldObj(oldSoDTO);
						log.setNewObj(newSoDTO);
						EgeoBusinessLogCommon.fillLogValue(log, req);
						MqService.recordBusinessLog(log);
					}
				}
			}

			for (SoChildDTO newSoChildDTO : newSoChildDTOList) {
				for (SoChildDTO oldSoChildDTO : oldSoChildDTOList) {
					if (newSoChildDTO.getId().equals(oldSoChildDTO.getId())) {
						EgeoLog log = new EgeoLog();
						log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
						log.setOperObject("OrderBackStageAction_orderSortExport");
						log.setMsgId(LogConstant.SORTING_OUT_SOCHILD.getStatus());
						log.setType(LogTypeConstant.SO_CHILD.getStatus());
						log.setOperatorObjId(newSoChildDTO.getId());
						log.setOperatorObjCode(newSoChildDTO.getChildCode());
						log.setOldObj(oldSoChildDTO);
						log.setNewObj(newSoChildDTO);
						EgeoBusinessLogCommon.fillLogValue(log, req);
						MqService.recordBusinessLog(log);
					}
				}
			}*/
		}

		return result;
	}


	private void writeExportHistory(HttpServletRequest req, String orderIds, List<SoDTO> oldSoDTOList,List<SoChildDTO> oldSoChildDTOList){
		// 如果成功导出则记录日志
		// 2)查询变更后数据
		Map<String,Object> newObjects = soManage.getOrderInfoByOrderIds(com.alibaba.fastjson.JSONArray.parseArray(orderIds, Long.class));
		List<SoDTO>  newSoDTOList = (List<SoDTO>) newObjects.get("soDTOList");
		List<SoChildDTO>  newSoChildDTOList = (List<SoChildDTO>) newObjects.get("soChildDTOList");

		// 3)插入操作日志
		for (SoDTO newSoDTO : newSoDTOList) {
			for (SoDTO oldSoDTO : oldSoDTOList) {
				if (newSoDTO.getId().equals(oldSoDTO.getId())) {
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("OrderBackStageAction_orderSortExport");
					log.setMsgId(LogConstant.SORTING_OUT_SO.getStatus());
					log.setType(LogTypeConstant.SO.getStatus());
					log.setOperatorObjId(newSoDTO.getId());
					log.setOperatorObjCode(newSoDTO.getOrderCode());
					log.setOldObj(oldSoDTO);
					log.setNewObj(newSoDTO);
					EgeoBusinessLogCommon.fillLogValue(log, req);

					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}
			}
		}

		for (SoChildDTO newSoChildDTO : newSoChildDTOList) {
			for (SoChildDTO oldSoChildDTO : oldSoChildDTOList) {
				if (newSoChildDTO.getId().equals(oldSoChildDTO.getId())) {
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("OrderBackStageAction_orderSortExport");
					log.setMsgId(LogConstant.SORTING_OUT_SOCHILD.getStatus());
					log.setType(LogTypeConstant.SO_CHILD.getStatus());
					log.setOperatorObjId(newSoChildDTO.getId());
					log.setOperatorObjCode(newSoChildDTO.getChildCode());
					log.setOldObj(oldSoChildDTO);
					log.setNewObj(newSoChildDTO);
					EgeoBusinessLogCommon.fillLogValue(log, req);

					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}
			}
		}

	}


	private void writeChildExportHistory(HttpServletRequest req, String orderIds, List<SoDTO> oldSoDTOList,List<SoChildDTO> oldSoChildDTOList){
		// 如果成功导出则记录日志
		// 2)查询变更后数据
		Map<String,Object> newObjects = soManage.getOrderInfoByOrderChildIds(com.alibaba.fastjson.JSONArray.parseArray(orderIds, Long.class));
		List<SoDTO>  newSoDTOList = (List<SoDTO>) newObjects.get("soDTOList");
		List<SoChildDTO>  newSoChildDTOList = (List<SoChildDTO>) newObjects.get("soChildDTOList");

		// 3)插入操作日志
		for (SoDTO newSoDTO : newSoDTOList) {
			for (SoDTO oldSoDTO : oldSoDTOList) {
				if (newSoDTO.getId().equals(oldSoDTO.getId())) {
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("OrderBackStageAction_orderSortExport");
					log.setMsgId(LogConstant.SORTING_OUT_SO.getStatus());
					log.setType(LogTypeConstant.SO.getStatus());
					log.setOperatorObjId(newSoDTO.getId());
					log.setOperatorObjCode(newSoDTO.getOrderCode());
					log.setOldObj(oldSoDTO);
					log.setNewObj(newSoDTO);
					EgeoBusinessLogCommon.fillLogValue(log, req);

					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}
			}
		}

		for (SoChildDTO newSoChildDTO : newSoChildDTOList) {
			for (SoChildDTO oldSoChildDTO : oldSoChildDTOList) {
				if (newSoChildDTO.getId().equals(oldSoChildDTO.getId())) {
					EgeoLog log = new EgeoLog();
					log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
					log.setOperObject("OrderBackStageAction_orderSortExport");
					log.setMsgId(LogConstant.SORTING_OUT_SOCHILD.getStatus());
					log.setType(LogTypeConstant.SO_CHILD.getStatus());
					log.setOperatorObjId(newSoChildDTO.getId());
					log.setOperatorObjCode(newSoChildDTO.getChildCode());
					log.setOldObj(oldSoChildDTO);
					log.setNewObj(newSoChildDTO);
					EgeoBusinessLogCommon.fillLogValue(log, req);

					try {
						ActiveMQUtils.recordBusinessLog(log);
					}catch (Exception e) {
						// TODO: handle exception
						logger.error("发送日志消息失败："+ JSON.toJSONString(log));
					}
				}
			}
		}

	}






	/**
	 * 订单信息导出
	 * @param orderIds
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> orderExport(String orderIds,HttpServletRequest req){
		logger.info("订单信息导出");
		List<Long> Ids = gson.fromJson(orderIds, new TypeToken<List<Long>>() {
		}.getType());
		if(EmptyUtil.isEmpty(Ids)){
			return JsonResult.fail("请选择订单");
		}

		return soManage.orderExport(orderIds);
	}

	/**
	 * 第三方订单导出

	 * @return
	 */
	@RequestMapping(value = "/thirdpartyOrderExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> thirdpartyOrderExport(String ids,Long merchantId){
		logger.info("第三方订单导出");

		if(EmptyUtil.isEmpty(merchantId)){
			return JsonResult.fail("请选择运营方");
		}
		List<Long> idList = JSONArray.parseArray(ids, Long.class);
		if(EmptyUtil.isEmpty(idList)){
			return JsonResult.fail("请选择订单");
		}
		return soManage.thirdpartyOrderExport(idList,merchantId);
	}

	/**
	 * 子订单导出
	 * @param orderId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/soChildExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> soChildExport(Long orderId,HttpServletRequest req){
		logger.info("子订单导出");
		return soManage.soChildExport(orderId);
	}

	/**
	 * 子订单导出
	 * @param childIds
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/soChildBatchExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> soChildBatchExport(String childIds,HttpServletRequest req){
		logger.info("子订单批量导出{}",childIds);
		List<Long> Ids = gson.fromJson(childIds, new TypeToken<List<Long>>() {
		}.getType());
		if(EmptyUtil.isEmpty(Ids)){
			return JsonResult.fail("请选择子订单");
		}

		return soManage.soChildBatchExport(Ids);
	}

	/**
	 * 导出订单操作流水
	 * @param orderId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/operateFlowExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> operateFlowExport(Long orderId,HttpServletRequest req){
		logger.info("导出订单操作流水");
		return soManage.operateFlowExport(orderId);
	}


	/**
	 * 查询子订单列表
	 * @param storeId
	 * @param soChildCode
	 * @param mail
	 * @param puName
	 * @param orderCreateTimeStart
	 * @param orderCreateTimeEnd
	 * @param soType
	 * @param soChildDeliveryStatus
	 * @param soConfirmStatus
	 * @param sendStart
	 * @param sendEnd
	 * @param showTest
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping("/soChildAllList")
	@ResponseBody
	public JsonResult<Map<String,Object>> soChildAllList(Long storeId,String soChildCode,
														 String mail,String puName,
														 Long orderCreateTimeStart,Long orderCreateTimeEnd,
														 Integer soType,Integer soChildDeliveryStatus,
														 Integer soConfirmStatus,Long sendStart,
														 Long sendEnd,Boolean showTest,Integer merchantId,
														 Pagination page,HttpServletRequest req){

		logger.info("后台查询订单列表");
		//校验门店id和平台id
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		/*if(EmptyUtil.isEmpty(storeId)){
			return JsonResult.fail("门店id不能为空");
		}*/

		//将时间数据转换成date格式
		Date soCreateTimeStart = null;
		if(EmptyUtil.isNotEmpty(orderCreateTimeStart)){
			soCreateTimeStart=new Date(orderCreateTimeStart);
		}
		Date soCreateTimeEnd=null;
		if(EmptyUtil.isNotEmpty(orderCreateTimeEnd)){
			soCreateTimeEnd = new Date(orderCreateTimeEnd);
		}
		Date sendTimeStart=null;
		if(EmptyUtil.isNotEmpty(sendStart)){
			sendTimeStart = new Date(sendStart);
		}
		Date sendTimeEnd=null;
		if(EmptyUtil.isNotEmpty(sendEnd)){
			sendTimeEnd = new Date(sendEnd+(24*60*60-1)*1000);
		}
		return soManage.soChildAllList(merchantId,soChildCode,mail,puName,soCreateTimeStart,soCreateTimeEnd,
				soType,soChildDeliveryStatus,soConfirmStatus,sendTimeStart,sendTimeEnd,showTest,page,platformId,null,null,null);

	}

	@RequestMapping("/supplierSoChildAllList")
	@ResponseBody
	public JsonResult<Map<String,Object>> supplierSoChildAllList(Long storeId,String soChildCode,
														 String mail,String puName,
														 Long orderCreateTimeStart,Long orderCreateTimeEnd,
														 Integer soType,Integer soChildDeliveryStatus,
														 Integer soConfirmStatus,Long sendStart,
														 Long sendEnd,Boolean showTest,Integer merchantId,Long supplierId,
														 Integer orderPayStatus,Integer auditStatus,
														 Pagination page,HttpServletRequest req){

		logger.info("后台查询订单列表");
		//校验门店id和平台id
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		/*if(EmptyUtil.isEmpty(storeId)){
			return JsonResult.fail("门店id不能为空");
		}*/

		//将时间数据转换成date格式
		Date soCreateTimeStart = null;
		if(EmptyUtil.isNotEmpty(orderCreateTimeStart)){
			soCreateTimeStart=new Date(orderCreateTimeStart);
		}
		Date soCreateTimeEnd=null;
		if(EmptyUtil.isNotEmpty(orderCreateTimeEnd)){
			soCreateTimeEnd = new Date(orderCreateTimeEnd);
		}
		Date sendTimeStart=null;
		if(EmptyUtil.isNotEmpty(sendStart)){
			sendTimeStart = new Date(sendStart);
		}
		Date sendTimeEnd=null;
		if(EmptyUtil.isNotEmpty(sendEnd)){
			sendTimeEnd = new Date(sendEnd+(24*60*60-1)*1000);
		}
		//供应商
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==5) {
			return soManage.soChildAllList(merchantId,soChildCode,mail,puName,soCreateTimeStart,soCreateTimeEnd,
					soType,soChildDeliveryStatus,soConfirmStatus,sendTimeStart,sendTimeEnd,showTest,page,
					platformId,RuntimeContext.cacheUser().getEnterpriseId(),orderPayStatus,0);

		}else if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==1) {
			//平台用户
			return soManage.soChildAllList(merchantId,soChildCode,mail,puName,soCreateTimeStart,soCreateTimeEnd,
					soType,soChildDeliveryStatus,soConfirmStatus,sendTimeStart,sendTimeEnd,showTest,page,
					platformId,(supplierId==null)?null:supplierId,orderPayStatus,auditStatus
			);

		}else {
			return JsonResult.fail("数据异常");
		}

	}

	@RequestMapping(value = "/pushOrderByOrderCode")
	@ResponseBody
	public JsonResult pushOrderByOrderCode(String orderCode, HttpServletRequest req) {
		logger.info("手动推送订单信息orderCode:{}",orderCode);
		List<SoDTO> soDTOs = soManage.findSoByCode(orderCode);
		if(CollectionUtils.isEmpty(soDTOs)){
			return JsonResult.fail("未找到订单");
		}
		SoDTO soDTO = soDTOs.get(0);
		JSONObject jsonObject = pushOrderManage.pushOrderInfo(soDTO.getId(),null,null);
		return JsonResult.success(jsonObject);
	}

	@RequestMapping(value = "/rePushRefundOrderByItemAndRefund")
	@ResponseBody
	public JsonResult rePushRefundOrderByItemAndRefund(@RequestBody RePushRefundOrderVO vo, HttpServletRequest req) {
		logger.info("手动推送订单退款信息请求参数:{}",JSON.toJSONString(vo));
		JSONObject jsonObject = pushOrderManage.rePushRefundOrderByItemAndRefund(vo);
		return JsonResult.success(jsonObject);
	}

	/**
	 * 订单信息按搜索条件导出
	 * @param searchVO
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/orderSearchExport")
	@ResponseBody
	public JsonResult<Map<String, Object>> orderSearchExport(OrderSearchVO searchVO, HttpServletRequest req) {
		logger.info("后台按搜索条件导出订单列表:{}",JSON.toJSONString(searchVO));
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);
		if(EmptyUtil.isEmpty(searchVO.getStoreId())||searchVO.getStoreId()==0){
			if(platformId==7){
				searchVO.setStoreId(1L);
			}
		}
		//限制时间范围，避免数量过大
		if(EmptyUtil.isEmpty(searchVO.getOrderCode()) && EmptyUtil.isEmpty(searchVO.getKeyWords()) && EmptyUtil.isEmpty(searchVO.getEmail()) && EmptyUtil.isEmpty(searchVO.getGoodReceiverMobile())){
			if(EmptyUtil.isEmpty(searchVO.getStartTime())  || EmptyUtil.isEmpty(searchVO.getEndTime())){
				return JsonResult.fail("请选择时间范围，且时间范围应在3个月以内");
			}
			Date startDateTime =  new Date(searchVO.getStartTime());
			Date endDateTime = new Date(searchVO.getEndTime());
			if(DateUtil.isMoreThanThreeMonths(startDateTime,endDateTime,3)){
				return JsonResult.fail("请选择的时间范围应在3个月以内");
			}
		}

		if(EmptyUtil.isEmpty(searchVO.getOrderConfirmStatus())||searchVO.getOrderConfirmStatus()==-99){
			searchVO.setOrderConfirmStatus(null);
		}
		if(EmptyUtil.isEmpty(searchVO.getOrderPayStatus())||searchVO.getOrderPayStatus()==-99){
			searchVO.setOrderPayStatus(null);
		}
		//门店运营端需要显示测试数据
		if(EmptyUtil.isNotEmpty(searchVO.getStoreId())&&searchVO.getStoreId()!=1L){
			searchVO.setShowTest(true);
		}
		searchVO.setPlatformId(platformId);
		searchVO.setRefundFlag(false);
		return soManage.orderSearchExport(searchVO);
	}

	@RequestMapping("/supplierSoChildAllListSearchExport")
	@ResponseBody
	public JsonResult<Map<String,Object>> supplierSoChildAllListSearchExport(Long storeId,String soChildCode,
																 String mail,String puName,
																 Long orderCreateTimeStart,Long orderCreateTimeEnd,
																 Integer soType,Integer soChildDeliveryStatus,
																 Integer soConfirmStatus,Long sendStart,
																 Long sendEnd,Boolean showTest,Integer merchantId,Long supplierId,
																 Integer orderPayStatus,Integer auditStatus,
																 Pagination page,HttpServletRequest req){

		logger.info("后台查询订单列表导出");
		//校验门店id和平台id
		String str = req.getHeader("platformId");
		if (EmptyUtil.isBlank(str))
			return JsonResult.fail("platformId为空");
		Long platformId = Long.valueOf(str);

		//将时间数据转换成date格式
		Date soCreateTimeStart = null;
		if(EmptyUtil.isNotEmpty(orderCreateTimeStart)){
			soCreateTimeStart=new Date(orderCreateTimeStart);
		}
		Date soCreateTimeEnd=null;
		if(EmptyUtil.isNotEmpty(orderCreateTimeEnd)){
			soCreateTimeEnd = new Date(orderCreateTimeEnd);
		}
		if(EmptyUtil.isEmpty(soChildCode) && EmptyUtil.isEmpty(mail)){
			if(soCreateTimeStart ==null || soCreateTimeEnd==null){
				return JsonResult.fail("请选择下单时间范围");
			}
			if(DateUtil.isMoreThanThreeMonths(soCreateTimeStart,soCreateTimeEnd,3)){
				return JsonResult.fail("请选择的下单时间范围应在3个月以内");
			}
		}
		Date sendTimeStart=null;
		if(EmptyUtil.isNotEmpty(sendStart)){
			sendTimeStart = new Date(sendStart);
		}
		Date sendTimeEnd=null;
		if(EmptyUtil.isNotEmpty(sendEnd)){
			sendTimeEnd = new Date(sendEnd+(24*60*60-1)*1000);
		}
		//供应商
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==5) {
			return soManage.soChildAllListToExport(merchantId,soChildCode,mail,puName,soCreateTimeStart,soCreateTimeEnd,
					soType,soChildDeliveryStatus,soConfirmStatus,sendTimeStart,sendTimeEnd,showTest,null,
					platformId,RuntimeContext.cacheUser().getEnterpriseId(),orderPayStatus,0);

		}else if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()==1) {
			//平台用户
			return soManage.soChildAllListToExport(merchantId,soChildCode,mail,puName,soCreateTimeStart,soCreateTimeEnd,
					soType,soChildDeliveryStatus,soConfirmStatus,sendTimeStart,sendTimeEnd,showTest,null,
					platformId,(supplierId==null)?null:supplierId,orderPayStatus,auditStatus
			);

		}else {
			return JsonResult.fail("数据异常");
		}

	}
}
