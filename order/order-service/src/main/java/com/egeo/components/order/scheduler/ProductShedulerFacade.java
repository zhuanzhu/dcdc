package com.egeo.components.order.scheduler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.client.CategoryTreeClient;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.JdProductClient;
import com.egeo.components.product.client.JdProductInnerIdClient;
import com.egeo.components.product.client.MembershipAuthorityClient;
import com.egeo.components.product.client.MembershipClient;
import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.product.client.MerchantProductClient;
import com.egeo.components.product.client.PictureClient;
import com.egeo.components.product.client.ProductPictureClient;
import com.egeo.components.product.client.ProductUnitClient;
import com.egeo.components.product.client.SkuAttNameClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.client.StandardProductUnitAttNameClient;
import com.egeo.components.product.client.StandardProductUnitAttValueClient;
import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitClientClient;
import com.egeo.components.product.client.StandardUnitCompanyClient;
import com.egeo.components.product.client.StandardUnitStoreClient;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.SkuPriceDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.user.client.SendInfoClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.str.StringUtils;

@Component
public class ProductShedulerFacade {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MembershipClient membershipReadService;

	@Autowired
	private SendInfoClient sendInfoWriteService;
	
	@Autowired
	private UserClient userReadService;
	
	@Autowired
	private StandardUnitClient standardUnitClient;
	
	@Autowired
	private MembershipUserClient membershipUserReadService;
	@Autowired
	private MembershipAuthorityClient membershipAuthorityReadService;
/*

	@Autowired
	private JedisUtil jedisUtil;


	@Autowired
	private JdProductClient jdProductReadService;

	@Autowired
	private JdProductClient jdProductWriteService;

	@Autowired
	private CategoryTreeClient categoryTreeReadService;
	@Autowired
	private ProductUnitClient productUnitReadService;


	@Autowired
	private CommodityProductUnitClient commodityProductUnitWriteService;


	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	@Autowired
	private MerchantProductClient merchantProductReadService;

	@Autowired
	private SkuClient skuReadService;

	@Autowired
	private SkuClient skuWriteService;

	@Autowired
	private SkuAttNameClient skuAttNameReadService;


	@Autowired
	private PictureClient pictureReadService;

	@Autowired
	private StandardProductUnitClient standardProductUnitReadService;


	@Autowired
	private StandardProductUnitClient standardProductUnitWriteService;


	@Autowired
	private StandardProductUnitAttNameClient standardProductUnitAttNameReadService;

	@Autowired
	private StandardProductUnitAttNameClient standardProductUnitAttNameWriteService;

	@Autowired
	private StandardProductUnitAttValueClient standardProductUnitAttValueWriteService;

	@Autowired
	private StandardProductUnitAttValueClient standardProductUnitAttValueReadService;

	@Autowired
	private ProductPictureClient productPictureReadService;


	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;

	@Autowired
	private SkuAttNameClient skuAttNameWriteService;

	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;*/

	@Autowired
	private StandardUnitClient standardUnitReadService;
/*	@Autowired
	private StandardUnitClient standardUnitWriteService;
	@Autowired
	private PictureClient pictureWriteService;
	@Autowired
	private ProductPictureClient productPictureWriteService;
	@Autowired
	private MerchantProductClient merchantProductWriteService;
	@Autowired
	private StandardUnitStoreClient standardUnitStoreWriteService;
	@Autowired
	private StandardUnitCompanyClient standardUnitCompanyWriteService;
	@Autowired
	private StandardUnitClientClient standardUnitClientWriteService;
	@Autowired
	private ProductUnitClient productUnitWriteService;

	@Autowired
	private JdProductInnerIdClient jdProductInnerIdReadService;*/



	/**
	 * 更新su搜索表数据
	 */
	public void syncSaveSuSerachRule() {
		standardUnitClient.syncSaveSuSerachRule();
	}
	
	public void sendMembershipNotify(Integer remainDays) {
		List<MembershipDTO> memList = membershipReadService.findNotifyMembership(remainDays);
		for (MembershipDTO m : memList) {
			/***********发送消息*********/
			UserDTO u = userReadService.findUserByID(m.getUserId());
			Map<String, String> params = new HashMap<>();
			params.put("会籍名称", m.getMembershipName());
			params.put("会籍剩余有效期", remainDays + "");
			params.put("会籍权限", "");
			params.put("用户账号名称", u.getLoginName());
			Long infoTemplateId = null;
			if (remainDays == 30) {
				if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(m.getPlatformId())) {
					infoTemplateId = InfoConstant.MYY_VIP_LEFT_30DAYS.getStatus();
				} else {
					infoTemplateId = InfoConstant.FGJ_VIP_LEFT_30DAYS.getStatus();
				}
			} else if (remainDays == 1) {
				if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(m.getPlatformId())) {
					infoTemplateId = InfoConstant.MYY_VIP_LEFT_1DAYS.getStatus();
				} else {
					infoTemplateId = InfoConstant.FGJ_VIP_LEFT_1DAYS.getStatus();
				}
			}
			sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, m.getUserId(), u.getMobile()));
			/***********发送消息*********/
		}
	}
	
	public void sendMembershipAuthorityChangeNotify() {
		List<MembershipAuthorityDTO> maList = membershipAuthorityReadService.findModifyYesterday();
		if (EmptyUtil.isNotEmpty(maList)) {
			Map<Long, String> memberMap = new HashMap<>();
			Map<Long, StringBuilder> authMap = new HashMap<>();
			Map<Long, Long> platformIdMap = new HashMap<>();
			for (MembershipAuthorityDTO ma : maList) {
				if (!memberMap.containsKey(ma.getMembershipId())) {
					memberMap.put(ma.getMembershipId(), ma.getMembershipName());
					authMap.put(ma.getMembershipId(), new StringBuilder(ma.getAuthorityName()));
					platformIdMap.put(ma.getMembershipId(), ma.getPlatformId());
				} else {
					authMap.get(ma.getMembershipId()).append("," + ma.getAuthorityName());
				}
			}
			MembershipUserDTO dto = new MembershipUserDTO();
			dto.setStatusCode(1);
			for (Entry<Long, String> member : memberMap.entrySet()) {
				dto.setMembershipId(member.getKey());
				List<MembershipUserDTO> muList = membershipUserReadService.findMembershipUserAll(dto);
				Long platformId = platformIdMap.get(member.getKey());
				if (EmptyUtil.isNotEmpty(muList)) {
					Map<String, String> params = new HashMap<>();
					params.put("会籍名称", member.getValue());
					params.put("权限名称", authMap.get(member.getKey()).toString());
					Long infoTemplateId;
					if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
						infoTemplateId = InfoConstant.MYY_VIP_AUTH_CHANGE.getStatus();
					} else {
						infoTemplateId = InfoConstant.FGJ_VIP_AUTH_CHANGE.getStatus();
					}
					for (MembershipUserDTO mu : muList) {
						/***********发送消息*********/
						sendInfoWriteService.insertAndSendMessage(new InsertAndSendMessageDTO(infoTemplateId, params, mu.getUserId(), null));
						/***********发送消息*********/
					}
				}
			}
		}
	}


}
