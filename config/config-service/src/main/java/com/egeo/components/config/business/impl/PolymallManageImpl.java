package com.egeo.components.config.business.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.config.business.PolymallManage;
import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.components.config.facade.PolymallFacade;
import com.egeo.components.config.facade.PolymallUserFacade;
import com.egeo.components.config.vo.PalymallResponse;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;

@Service("polymall")
public class PolymallManageImpl implements PolymallManage {

	private static final String POLYMALL_UT = "POLYMALL_UT_";
	
	@Resource(name="polymallFacade")
	private PolymallFacade polymallFacade;
	
	@Resource
	private  JedisUtil cache;
	
	@Resource(name="polymallUserFacade")
	private PolymallUserFacade polymallUserFacade;

	@Override
	public Map<String, Object> queryProduct(String paramStr, String token) {
		CommodityProductUnitDTO dto = new CommodityProductUnitDTO();
		Long merchantId = convertTokenToMerchantId(token);
		if (merchantId == null) {
			throw new BusinessException("未找到token对应用户");
		}
		dto.setMerchantId(merchantId);
		Pagination page = new Pagination();
		parseQueryProductParam(paramStr, dto, page);
		return PalymallResponse.buildSuccessResponse(polymallFacade.queryProduct(dto, page));
	}
	
	@Override
	public Map<String, Object> queryOrder(String paramStr, String token) throws Exception {
		SoChildDTO dto = new SoChildDTO();
		Long merchantId = convertTokenToMerchantId(token);
		if (merchantId == null) {
			throw new BusinessException("未找到token对应用户");
		}
		dto.setPerformingParty(merchantId);
		Pagination page = new Pagination();
		parseOrderQueryParam(paramStr, dto, page);
		return PalymallResponse.buildSuccessResponse(polymallFacade.queryOrder(dto, page));
	}

	@Override
	public Map<String, Object> checkOrderStatus(String paramStr) {
		SoChildDTO dto = new SoChildDTO();
		parseOrderStatusParam(paramStr, dto);
		return PalymallResponse.buildSuccessResponse(polymallFacade.checkOrderStatus(dto));
	}

	@Override
	public Map<String, Object> deliverOrder(String paramStr) {
		Map<String, String> param = new HashMap<>();
		Map<String, Integer> deliverMap = new HashMap<>();
		SoChildDTO dto = new SoChildDTO();
		parseDeliverOrderParam(paramStr, param, deliverMap, dto);
		return PalymallResponse.buildSuccessResponse(polymallFacade.deliverOrder(param, deliverMap, dto));
	}

	@Override
	public Map<String, Object> syncStock(String paramStr) {
		Map<String, String> param = new HashMap<>();
		parseSyncStockParam(paramStr, param);
		return PalymallResponse.buildSuccessResponse(polymallFacade.syncStock(param));
	}
	
	private void parseSyncStockParam(String paramStr, Map<String, String> param) {
		if (EmptyUtil.isNotEmpty(paramStr)) {
			Map<String, Object> reqParam = JSON.parseObject(paramStr);
			for (Entry<String, Object> entry : reqParam.entrySet()) {
				if (EmptyUtil.isNotEmpty(entry.getValue())) {
					param.put(entry.getKey(), entry.getValue().toString());
				}
			}
			String platProductID = param.get("PlatProductID");
			String quantity = param.get("Quantity");
			if (EmptyUtil.isEmpty(platProductID)) {
				throw new BusinessException("PlatProductID参数缺失");
			}
			if (EmptyUtil.isEmpty(quantity)) {
				throw new BusinessException("Quantity参数缺失");
			}
		} else {
			throw new BusinessException("请求参数缺失");
		}
	}
	
	/**
	 * 解析商品查询参数
	 * @param paramStr
	 * @param dto
	 * @param page
	 */
	private void parseQueryProductParam(String paramStr, CommodityProductUnitDTO dto, Pagination page) {
		if (EmptyUtil.isNotEmpty(paramStr)) {
			Map<String, Object> param = JSON.parseObject(paramStr);
			Object productid = param.get("ProductId");
			Object productname = param.get("ProductName");
			Object status = param.get("Status");
			Object pageindex = param.get("PageIndex");
			Object pagesize = param.get("PageSize");
			if (EmptyUtil.isNotEmpty(productid)) {
				dto.setId(Long.parseLong(productid.toString()));
			}
			if (EmptyUtil.isNotEmpty(productname)) {
				dto.setName(productname.toString());
			}
			if (EmptyUtil.isNotEmpty(status)) {
				dto.setStatus(convertPolymallStatusToPUStatus(status.toString()));
			}
			if (EmptyUtil.isNotEmpty(pageindex)) {
				page.setPageNo(Integer.parseInt(pageindex.toString()));
			}
			if (EmptyUtil.isNotEmpty(pagesize)) {
				page.setPageSize(Integer.parseInt(pagesize.toString()));
			}
		}
	}
	
	/**
	 * 解析订单下载参数
	 * @param paramStr
	 * @param dto
	 * @param page
	 */
	private void parseOrderQueryParam(String paramStr, SoChildDTO dto, Pagination page) throws Exception {
		if (EmptyUtil.isNotEmpty(paramStr)) {
			Map<String, Object> param = JSON.parseObject(paramStr);
			Object orderStatus = param.get("OrderStatus");
			Object platOrderNo = param.get("PlatOrderNo");
			Object startTime = param.get("StartTime");
			Object endTime = param.get("EndTime");
			Object timeType = param.get("TimeType");
			Object pageindex = param.get("PageIndex");
			Object pagesize = param.get("PageSize");
			if (EmptyUtil.isNotEmpty(orderStatus)) {
				dto.setOrderStatus(convertPolymallStatusToOrderStatus(orderStatus.toString()));
			}
			if (EmptyUtil.isNotEmpty(platOrderNo)) {
				dto.setChildCode(platOrderNo.toString());
			}
			if (EmptyUtil.isNotEmpty(timeType)) {
				if ("JH_01".equals(timeType)) {
					if (EmptyUtil.isNotEmpty(startTime)) {
						dto.setUpdateTimeStart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime.toString()));
					}
					if (EmptyUtil.isNotEmpty(endTime)) {
						dto.setUpdateTimeEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime.toString()));
					}
				}
				if ("JH_02".equals(timeType)) {
					if (EmptyUtil.isNotEmpty(startTime)) {
						dto.setCreateTimeStart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime.toString()));
					}
					if (EmptyUtil.isNotEmpty(endTime)) {
						dto.setCreateTimeEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime.toString()));
					}
				}
			}
			if (EmptyUtil.isNotEmpty(pageindex)) {
				page.setPageNo(Integer.parseInt(pageindex.toString()));
			}
			if (EmptyUtil.isNotEmpty(pagesize)) {
				page.setPageSize(Integer.parseInt(pagesize.toString()));
			}
		}
	}
	
	/**
	 * 解析退款检测
	 * @param paramStr
	 * @param dto
	 * @param page
	 */
	private void parseOrderStatusParam(String paramStr, SoChildDTO dto) {
		if (EmptyUtil.isNotEmpty(paramStr)) {
			Map<String, Object> param = JSON.parseObject(paramStr);
			Object orderId = param.get("OrderID");
			if (EmptyUtil.isNotEmpty(orderId)) {
				dto.setChildCode(orderId.toString());
			}
		} else {
			throw new BusinessException("OrderID参数缺失");
		}
	}
	
	/**
	 * 解析订单发货
	 * @param paramStr
	 * @param dto
	 * @param page
	 */
	private void parseDeliverOrderParam(String paramStr, Map<String, String> param, Map<String, Integer> deliverMap, SoChildDTO dto) {
		if (EmptyUtil.isNotEmpty(paramStr)) {
			Map<String, Object> reqParam = JSON.parseObject(paramStr);
			for (Entry<String, Object> entry : reqParam.entrySet()) {
				if (EmptyUtil.isNotEmpty(entry.getValue())) {
					param.put(entry.getKey(), entry.getValue().toString());
				}
			}
			
			String platOrderNo = param.get("PlatOrderNo");
			String isSplit = param.get("IsSplit");
			String subPlatOrderNo = param.get("SubPlatOrderNo");
			if (EmptyUtil.isEmpty(platOrderNo)) {
				throw new BusinessException("PlatOrderNo参数缺失");
			}
			dto.setChildCode(platOrderNo);
			if (EmptyUtil.isEmpty(isSplit)) {
				throw new BusinessException("IsSplit参数缺失");
			}
			if ("1".equals(isSplit)) {
				if (EmptyUtil.isEmpty(subPlatOrderNo)) {
					throw new BusinessException("拆单发货时SubPlatOrderNo参数缺失");
				} else {
					String[] subOrderArr = subPlatOrderNo.split("\\|");
					for (String subOrder : subOrderArr) {
						if (subOrder.contains(":")) {
							String[] deliverArr = subOrder.split(":");
							deliverMap.put(deliverArr[0], Integer.parseInt(deliverArr[1]));
						}
					}
				}
			}
		} else {
			throw new BusinessException("请求参数缺失");
		}
	}
	
	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 * 商品状态(已上架商品=JH_01，已下架商品=JH_02，已售罄商品=JH_03，未通过审核=JH_04，审核中商品=JH_05，即将上架商品=JH_06，其他商品=JH_98，所有商品=JH_99)
	 * @return
	 */
	private Integer convertPolymallStatusToPUStatus(String status) {
		if ("JH_06".equals(status)) {
			return 1;
		}
		if ("JH_05".equals(status)) {
			return 2;
		}
		if ("JH_01".equals(status)) {
			return 3;
		}
		if ("JH_02".equals(status)) {
			return 4;
		}
		if ("JH_04".equals(status)) {
			return 5;
		}
		return null;
	}
	
	/**
	 * 内部订单状态(10已取消;0-待付款;1-已付款;2-已发货;3-已收货;4-已完成;5-预退款;6已退款;7-部分发货;8-换货中)
	 * 外部订单状态(等待买家付款=JH_01，等待卖家发货=JH_02，等待买家确认收货=JH_03，交易成功=JH_04，交易关闭=JH_05)
	 * @param status
	 * @return
	 */
	private Integer convertPolymallStatusToOrderStatus(String status) {
		if ("JH_01".equals(status)) {
			return 0;
		}
		if ("JH_02".equals(status)) {
			return 1;
		}
		if ("JH_03".equals(status)) {
			return 2;
		}
		if ("JH_04".equals(status)) {
			return 4;
		}
		if ("JH_05".equals(status)) {
			return 10;
		}
		return null;
	}
	
	private Long convertTokenToMerchantId(String token) {
		Long merchantId = null;
		Object obj = cache.get(POLYMALL_UT + token);
		if (obj != null) {
			merchantId = Long.parseLong(obj.toString());
		} else {
			List<PolymallUserDTO> polymallUserList = polymallUserFacade.findPolymallUserAll(new PolymallUserDTO());
			if (polymallUserList != null) {
				for (PolymallUserDTO polymallUser : polymallUserList) {
					cache.set(POLYMALL_UT + polymallUser.getToken(), polymallUser.getMerchantId());
					if (token.equals(polymallUser.getToken())) {
						merchantId = polymallUser.getMerchantId();
					}
				}
			}
		}
		return merchantId;
	}
	
}
