package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.promotion.client.CouponClient;
import com.egeo.components.promotion.client.CouponGroupClient;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.user.constant.UserLoginConstant;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserLoginDTO;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.components.user.service.read.UserLoginReadService;
import com.egeo.components.user.service.write.UserLoginWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UserLoginFacade {
	
	private final static Logger logger = LoggerFactory.getLogger(UserLoginFacade.class);
	
	@Resource
	private UserLoginWriteService userLoginWriteService;
	
	@Resource
	private UserLoginReadService userLoginReadService;
	
	@Autowired
	private StoreClient storeReadService;
	
	@Autowired
	private CouponClient couponReadService;
	
	@Autowired
	private CouponGroupClient couponGroupReadService;
	
	@Autowired
	private CouponUnitClient couponUnitReadService;
	
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	
	@Autowired
	private StandardUnitClient standardUnitReadService;
	
	@Resource
	private UserExtendReadService userExtendReadService;

	public void insertLoginLogWithTx(UserLoginDTO userLogin) {
		
		userLoginWriteService.insertLoginLogWithTx(userLogin);
	}


	public PageResult<Map<String, Object>> findOfPage(UserLoginDTO dto, Pagination page) {
		
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		PageResult<UserLoginDTO> result = userLoginReadService.findOfPage(dto,page);
		if(result.getList() != null && result.getList().size() > 0) {
			List<Map<String, Object>> list = new ArrayList<>();
			for (UserLoginDTO userLoginDTO : result.getList()) {
				Map<String, Object> map = new HashMap<>();
				map.put("loginTime", userLoginDTO.getLoginTime());
				map.put("ip", userLoginDTO.getIp());
				map.put("clientType", userLoginDTO.getClientType());
				map.put("store", translateStoreId(userLoginDTO.getStoreId()));
				map.put("ios", translateIOS(userLoginDTO.getBrowser()));
				map.put("loginType", UserLoginConstant.translate(userLoginDTO.getLoginType()));
				map.put("keyMessage", translateKeyMessage(userLoginDTO.getKeyMessage(),userLoginDTO.getLoginType()));
				list.add(map);
			}
			pageResult.setList(list);
		}
		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		return pageResult;
	}


	public List<Map<String, Object>> findByUserIds(List<Long> ids,Long startTime,Long endTime) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<UserLoginDTO> result = userLoginReadService.findByUserIds(ids,startTime,endTime);
		List<UserExtendDTO> userList = userExtendReadService.findByUserIds(ids);
		Map<Long,Map<String,String>> userMap = new HashMap<>();
		
		for (UserExtendDTO userExtendDTO : userList) {
			Map<String, String> temp = new HashMap<>();
			temp.put("name", userExtendDTO.getName());
			temp.put("mail", userExtendDTO.getMail());
			temp.put("memberCode", userExtendDTO.getMemberCode());
			temp.put("companyName", userExtendDTO.getCompanyName());
			userMap.put(userExtendDTO.getId(),temp);
		}
		logger.info("userMap:{}",userMap);
		
		if(result != null && result.size() > 0) {
			for (UserLoginDTO userLoginDTO : result) {
				Map<String, Object> map = new HashMap<>();
				Map<String, String> temp0 = userMap.get(userLoginDTO.getUserId());
				if(temp0 != null) {
					map.putAll(temp0);
				}
				map.put("loginTime", userLoginDTO.getLoginTime());
				map.put("ip", userLoginDTO.getIp());
				map.put("clientType", translateClientType(userLoginDTO.getClientType()));
				map.put("store", translateStoreId(userLoginDTO.getStoreId()));
				map.put("ios", translateIOS(userLoginDTO.getBrowser()));
				map.put("loginType", UserLoginConstant.translate(userLoginDTO.getLoginType()));
				map.put("keyMessage", translateKeyMessage(userLoginDTO.getKeyMessage(),userLoginDTO.getLoginType()));
				list.add(map);
			}
		}
		logger.info("list:{}",list.size());
		return list;
	}

	
	public String translateKeyMessage(Long id,String loginType) {
		
		if(id == null || StringUtils.isBlank(loginType)) {
			return "";
		}
		
		String keyMessage = "";
		switch (loginType) {
		
		case "coupon":
			CouponDTO dto = new CouponDTO();
			dto.setId(id);
			dto = couponReadService.findCouponById(dto);
			if(dto != null) {
				keyMessage = dto.getTitle();
			}
			break;
		case "coupon_group":
			CouponGroupDTO couponGroupDTO = new CouponGroupDTO();
			couponGroupDTO.setId(id);
			couponGroupDTO = couponGroupReadService.findCouponGroupById(couponGroupDTO);
			if(couponGroupDTO != null) {
				keyMessage = couponGroupDTO.getGroupName();
			}
			break;
		case "coupon_unit":
			CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
			couponUnitDTO.setId(id);
			couponUnitDTO = couponUnitReadService.findCouponUnitById(couponUnitDTO);
			if(couponUnitDTO != null) {
				keyMessage = couponUnitDTO.getCouponBatchName() + couponUnitDTO.getCouponUnitCode();
			}
			break;
		case "puo":
			CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
			commodityProductUnitDTO.setId(id);
			commodityProductUnitDTO = commodityProductUnitReadService.findCommodityProductUnitById(commodityProductUnitDTO);
			if(commodityProductUnitDTO != null) {
				keyMessage = commodityProductUnitDTO.getName();
			}
			break;
		case "puc":
			CommodityProductUnitDTO commodityProductUnitDTO1 = new CommodityProductUnitDTO();
			commodityProductUnitDTO1.setId(id);
			commodityProductUnitDTO1 = commodityProductUnitReadService.findCommodityProductUnitById(commodityProductUnitDTO1);
			if(commodityProductUnitDTO1 != null) {
				keyMessage = commodityProductUnitDTO1.getName();
			}
			break;
		case "su":
			StandardUnitDTO standardUnitDTO = standardUnitReadService.findStandardUnitById(id);
			if(standardUnitDTO != null) {
				keyMessage = standardUnitDTO.getName();
			}
			break;
		case "branch":
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId(id);
			storeDTO = storeReadService.findStoreById(storeDTO);
			if(storeDTO != null) {
				keyMessage = storeDTO.getName();
			}
			break;
		case "main_store":
			StoreDTO storeDTO1 = new StoreDTO();
			storeDTO1.setId(id);
			storeDTO1 = storeReadService.findStoreById(storeDTO1);
			if(storeDTO1 != null) {
				keyMessage = storeDTO1.getName();
			}
			break;

		default:
			break;
		}
		
		return keyMessage;
	}
	
	public String translateStoreId(Long storeId) {
		if(storeId != null) {
			if(storeId == 1) {
				return "大厨管家总店";
			}else if(storeId == 2) {
				return "富宏云采总店";
			}else {
				StoreDTO dto = new StoreDTO();
				dto.setId(storeId);
				dto = storeReadService.findStoreById(dto);
				if(dto != null) {
					return dto.getName();
				}
			}
		}
		return "";
	}
	
	public String translateIOS(String browser) {
		if(browser != null) {
			if(browser.contains("iPhone")) {
				return "IOS";
			}else if(browser.contains("Windows")) {
				return "Windows";
			}else if(browser.contains("MAC")) {
				return "MAC OS";
			}else if(browser.contains("Android")) {
				return "Android";
			}
		}else {
			return "Android";
		}
		return "";
	}
	
	public String translateClientType(Integer clientType) {
		if(clientType != null) {
			if(clientType == 1) {
				return "APP";
			}else if(clientType == 2) {
				return "微信端";
			}else if(clientType == 3) {
				return "Web端";
			}
		}
		return "";
	}

}
	