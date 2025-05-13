package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardProductUnitManage;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.facade.PictureFacade;
import com.egeo.components.product.facade.SellPlatformFacade;
import com.egeo.components.product.facade.SkuFacade;
import com.egeo.components.product.facade.StandardProductUnitAttValueFacade;
import com.egeo.components.product.facade.StandardProductUnitFacade;
import com.egeo.components.product.facade.StandardProductUnitPictureFacade;
import com.egeo.components.product.facade.StandardUnitFacade;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("standardProductUnit")
public class StandardProductUnitManageImpl implements StandardProductUnitManage{

	private static final Logger logger = LoggerFactory.getLogger(StandardProductUnitManageImpl.class);
	
	@Resource(name="standardProductUnitFacade")
	private StandardProductUnitFacade standardProductUnitFacade;
	
	@Resource(name="skuFacade")
	private SkuFacade skuFacade;
	
	@Resource(name="standardProductUnitPictureFacade")
	private StandardProductUnitPictureFacade standardProductUnitPictureFacade;
	
	@Resource(name="pictureFacade")
	private PictureFacade pictureFacade;
	
	@Resource(name="sellPlatformFacade")
	private SellPlatformFacade sellPlatformFacade;
	
	@Resource(name="standardProductUnitAttValueFacade")
	private StandardProductUnitAttValueFacade standardProductUnitAttValueFacade;

	@Resource
	private StandardUnitFacade standardUnitFacade;

	@Override
	public Map<String, Object> findStandardProductUnitById(StandardProductUnitDTO dto,Boolean quick) {
		Map<String, Object> map = new HashMap<>();
		boolean notQuick = (quick==null || !quick);
		StandardProductUnitDTO standardProductUnitDTO = dto;
		//根据spuid查询第三方对接参数
		if(notQuick) {
			standardProductUnitDTO = standardProductUnitFacade.findStandardProductUnitById(dto);
			int thirdpartyAtt=standardProductUnitFacade.findThirdpartyAttBySpuId(standardProductUnitDTO.getId());
			if(EmptyUtil.isEmpty(thirdpartyAtt)){
				throw new BusinessException("未查询到第三方对接参数");
			}
			standardProductUnitDTO.setThirdpartyAtt(thirdpartyAtt);
			List<Map<Long, String>> merchantList = new ArrayList<>();
			//第三方对接参数为7(内部)时,可编辑不小于1000的运营方,默认为自营可编辑
			if(thirdpartyAtt==7){
				//查询id不小于1000和id等于1的运营方
				List<Map<Long,String>> mapList= standardProductUnitFacade.findMerchantList();
				merchantList.addAll(mapList);
			}else if(thirdpartyAtt==12){
				//网店管家(TYpe=1)
				List<Map<Long,String>> mapList= standardProductUnitFacade.findMerchantListByType(Integer.valueOf(1));
				merchantList.addAll(mapList);
			}
			standardProductUnitDTO.setMerchantMapList(merchantList);
		}
		
		
		//根据spuid查询spu及sku信息
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setStandardProductUnitId(standardProductUnitDTO.getId());
		List<SkuDTO> skuList = skuFacade.findSkuAll(skuDTO);
		for (SkuDTO sku : skuList) {
			sku.setPuName(sku.getSkuName());
		}

		//根据spuid查询spu图片
		List<PictureDTO> pictureList = pictureFacade.findByStandardProductUnitId(standardProductUnitDTO.getId());
		List<String> pictureDTOs = new ArrayList<>();
		for (PictureDTO pictureDTO : pictureList) {
			if(pictureDTO.getType() == 1){
				standardProductUnitDTO.setPicture(pictureDTO.getUrl());
			}else if(pictureDTO.getType() == 2){
				pictureDTOs.add(pictureDTO.getUrl());
			}
		}
		if(notQuick) {			
			List<ClientDTO> clientList = standardProductUnitFacade.findClientAll(new ClientDTO());
			map.put("clientList", clientList);
			CompanyDTO companyDTO2 = new CompanyDTO();
			if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null &&RuntimeContext.cacheUser().getType().intValue()>1&&RuntimeContext.cacheUser().getType().intValue()!=5) {
				companyDTO2.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
			}
			companyDTO2.setPlatformId(dto.getPlatformId());
			List<CompanyDTO> companys = standardProductUnitFacade.findCompanyAll(companyDTO2);
			List<CompanyDTO> companyList = new ArrayList<>(); // 正式公司集合
			List<CompanyDTO> demoCompanyList = new ArrayList<>(); // 演示公司集合
			List<CompanyDTO> competingCompanyList = new ArrayList<>(); // 竞品公司集合
			for (CompanyDTO companyDTO : companys) {
				if(EmptyUtil.isNotEmpty(companyDTO.getIsTest())){
					// 公司类型 0:正式公司 1:测试公司 2:竞品公司
					switch (companyDTO.getCompanyType()) {
					case 0:
						companyList.add(companyDTO);
						break;
					case 1:
						demoCompanyList.add(companyDTO);
						break;
					case 2:
						competingCompanyList.add(companyDTO);
						break;

					default:
						break;
					}
				}
			}
			map.put("companyList", companyList);
			map.put("demoCompanyList", demoCompanyList);
			map.put("competingCompanyList", competingCompanyList);
		}
		standardProductUnitDTO.setPictureDTOs(pictureDTOs);

		if(notQuick) {
			List<SellPlatformDTO> sellPlatformList = sellPlatformFacade.findSellPlatformAll(new SellPlatformDTO());
			map.put("sellPlatformList", sellPlatformList);
			map.put("standardProductUnit", standardProductUnitDTO);
		}else {
			map.put("createdSUCount", standardUnitFacade.countRecord(dto.getId()));	
		}
		// 根据spuid查询spu关键词
		List<String> keyWordList = standardProductUnitAttValueFacade.keyWordByStandardProductUnitId(dto.getId(),dto.getPlatformId());
		
		
		map.put("skuList", skuList);
		map.put("keyWordList", keyWordList);
		return map;
	}

	@Override
	public PageResult<StandardProductUnitDTO> findStandardProductUnitOfPage(StandardProductUnitDTO dto, Pagination page) {
		return standardProductUnitFacade.findStandardProductUnitOfPage(dto, page);
	}

	@Override
	public List<StandardProductUnitDTO> findStandardProductUnitAll(StandardProductUnitDTO dto) {
		return standardProductUnitFacade.findStandardProductUnitAll(dto);
	}

	@Override
	public Long insertStandardProductUnitWithTx(StandardProductUnitDTO dto) {
		return standardProductUnitFacade.insertStandardProductUnitWithTx(dto);
	}

	@Override
	public int updateStandardProductUnitWithTx(StandardProductUnitDTO dto) {
		return standardProductUnitFacade.updateStandardProductUnitWithTx(dto);
	}

	@Override
	public int deleteStandardProductUnitWithTx(StandardProductUnitDTO dto) {
		return standardProductUnitFacade.deleteStandardProductUnitWithTx(dto);
	}
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public Map<String, Object> queryStandardProductUnitById(Long standardProductUnitId) {
		
		return standardProductUnitFacade.queryStandardProductUnitById(standardProductUnitId);
	}

	@Override
	public Map<String, Object> findCommodityTemplateIdByStandardUnitId(Long standardUnitId) {
		// 根据suId查询spu模版id
		Long commodityTemplateId = standardProductUnitFacade.findCommodityTemplateIdByStandardUnitId(standardUnitId);
		Map<String, Object> data = new HashMap<>();
		data.put("commodityTemplateId", commodityTemplateId);
		data.put("standardUnitId", standardUnitId);
		return data;
	}

	@Override
	public List<StandardProductUnitDTO> conditionStandardProductUnitAll(StandardProductUnitDTO dto) {

		List<StandardProductUnitDTO> unitAll = standardProductUnitFacade.findStandardProductUnitAll(dto);
		logger.info("(start)unitAll size:",unitAll.size());
		Iterator<StandardProductUnitDTO> iter = unitAll.iterator();
		
		while (iter.hasNext()) {
			
			StandardProductUnitDTO standardProductUnitDTO = iter.next();
			ProductAttNameDTO productAttNameDTO = standardProductUnitFacade.queryIsElectronicBySpuId(standardProductUnitDTO);
			if (EmptyUtil.isNotEmpty(productAttNameDTO)) {
				//是电子卡券
				//spuId查询存在unit库存
				ProductAttNameDTO productAttName = standardProductUnitFacade.queryIsUnitBySpuId(standardProductUnitDTO);
				if (EmptyUtil.isNotEmpty(productAttName)) {
					//存在unit库存
					logger.info("同时满足是电子卡券 且 存在unit 库存 ： ",standardProductUnitDTO.getBrandId());
					iter.remove();
				}
			}
		}
		logger.info("(end)unitAll size:",unitAll.size());
		return unitAll;

	}

}
	