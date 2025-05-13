package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.components.product.condition.CommodityProductUnitCondition;
import com.egeo.components.product.converter.CommodityProductUnitConverter;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.QueryProductUnitDTO;
import com.egeo.components.product.manage.read.CommodityProductUnitReadManage;
import com.egeo.components.product.manage.read.JdProductInnerIdReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueReadManage;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.po.QueryProductUnitPO;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Service("commodityProductUnitReadService")
public class CommodityProductUnitReadServiceImpl  implements CommodityProductUnitReadService {
	private static final XLogger logger = XLogger.getLogger(CommodityProductUnitReadServiceImpl.class);
	@Autowired
	private CommodityProductUnitReadManage commodityProductUnitReadManage;
	
	@Autowired
	private StandardProductUnitAttValueReadManage spuAtValueReadManage;

	@Autowired
	private JdProductInnerIdReadManage jdProductInnerIdReadManage;
	@Override
	public CommodityProductUnitDTO findCommodityProductUnitById(CommodityProductUnitDTO dto) {
		CommodityProductUnitPO po = CommodityProductUnitConverter.toPO(dto);
		CommodityProductUnitPO list = commodityProductUnitReadManage.findCommodityProductUnitById(po);		
		return CommodityProductUnitConverter.toDTO(list);
	}

	@Override
	public PageResult<CommodityProductUnitDTO> findCommodityProductUnitOfPage( CommodityProductUnitDTO dto, Pagination page) {
		CommodityProductUnitPO po = CommodityProductUnitConverter.toPO(dto);
        PageResult<CommodityProductUnitCondition> pageResult = commodityProductUnitReadManage.findCommodityProductUnitOfPage(po, page);
        List<CommodityProductUnitCondition> commodityProductUnitConditionList = pageResult.getList();
        List<CommodityProductUnitDTO> list = new ArrayList<>(commodityProductUnitConditionList.size());
        for (CommodityProductUnitCondition commodityProductUnitCondition : commodityProductUnitConditionList) {
        	CommodityProductUnitDTO commodityProductUnitDTO = CommodityProductUnitConverter.toDTO(commodityProductUnitCondition);
    		commodityProductUnitDTO.setStandardUnitId(commodityProductUnitCondition.getStandardUnitId());
    		commodityProductUnitDTO.setMerchantId(commodityProductUnitCondition.getMerchantId());
    		commodityProductUnitDTO.setStoreId(commodityProductUnitCondition.getStoreId());
    		commodityProductUnitDTO.setStoreName(commodityProductUnitCondition.getStoreName());
    		if(commodityProductUnitCondition.getEnterpriseId()!=null) {
    			commodityProductUnitDTO.setEnterpriseId(commodityProductUnitCondition.getEnterpriseId());
    		}
    		commodityProductUnitDTO.setStandardUnitName(commodityProductUnitCondition.getStandardUnitName());
    		commodityProductUnitDTO.setIsVisible(commodityProductUnitCondition.getIsVisible());
    		list.add(commodityProductUnitDTO);
		}
        
        PageResult<CommodityProductUnitDTO> result = new PageResult<CommodityProductUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(CommodityProductUnitDTO dto) {
		CommodityProductUnitPO po = CommodityProductUnitConverter.toPO(dto);
		List<CommodityProductUnitPO> list = commodityProductUnitReadManage.findCommodityProductUnitAll(po);		
		return CommodityProductUnitConverter.toDTO(list);
	}
	/**
	 * 根据skuid查询pu总条数
	 * @param id
	 * @return
	 */
	@Override
	public int countRecord(Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadManage.countRecord(skuId);
	}
	/**
	 * 根据puid集合查询pu信息
	 * @param puIds
	 * @return
	 */
	@Override
	public List<CommodityProductUnitDTO> findByPUIds(List<Long> puIds) {
		List<CommodityProductUnitCondition> list = commodityProductUnitReadManage.findByPUIds(puIds);
		List<CommodityProductUnitDTO> commodityProductUnitDTOs = new ArrayList<>();
		for (CommodityProductUnitCondition commodityProductUnitCondition : list) {
			CommodityProductUnitDTO commodityProductUnitDTO = CommodityProductUnitConverter.toDTO(commodityProductUnitCondition);
			commodityProductUnitDTO.setCommodityTemplateId(commodityProductUnitCondition.getCommodityTemplateId());
			commodityProductUnitDTOs.add(commodityProductUnitDTO);
		}
		return commodityProductUnitDTOs;
	}
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	@Override
	public List<Long> attValueByProductUnitId(Long productUnitId) {
		List<Long> puids = commodityProductUnitReadManage.attValueIdByProductUnitId(productUnitId);
		return puids;
	}

	@Override
	public boolean queryIsUnit(Long puId) {
		//有unit库存的基本属性值id
		Long basicAttValueId_unitStock=spuAtValueReadManage.queryAttValueIdByPuIdAndAttNameId(puId,BusinessConstant.IS_UNIT_INVENTORY_ID);
		return basicAttValueId_unitStock!=null && basicAttValueId_unitStock.longValue()==3l;
	}

	/**
	 * 根据puid查询pu扩展信息
	 * @param puId
	 * @return
	 */
	@Override
	public CommodityProductUnitDTO findSUSPUByPUId(Long puId) {
		CommodityProductUnitCondition commodityProductUnitCondition = commodityProductUnitReadManage.findSUSPUByPUId(puId);
		if(EmptyUtil.isEmpty(commodityProductUnitCondition)){
			return null;

		}
		CommodityProductUnitDTO commodityProductUnitDTO = CommodityProductUnitConverter.toDTO(commodityProductUnitCondition);
		commodityProductUnitDTO.setStandardProductUnitId(commodityProductUnitCondition.getStandardProductUnitId());
		commodityProductUnitDTO.setStandardUnitId(commodityProductUnitCondition.getStandardUnitId());
		commodityProductUnitDTO.setMerchantId(commodityProductUnitCondition.getMerchantId());
        commodityProductUnitDTO.setProductCategory(commodityProductUnitCondition.getProductCategory());
		commodityProductUnitDTO.setMerchantProductSerialNumber(commodityProductUnitCondition.getMerchantProductSerialNumber());
		return commodityProductUnitDTO;
	}

	@Override
	public List<CommodityProductUnitDTO> queryPuByName(String puName) {
		return CommodityProductUnitConverter.toDTO(commodityProductUnitReadManage.queryPuByName(puName));
	}

	@Override
	public String queryPuNullImgUrl(Long skuId) {
		return commodityProductUnitReadManage.queryPuNullImgUrl(skuId);
	}
	/**
	 * 根据skuId查询puid集合
	 * @param skuId
	 * @return
	 */
	@Override
	public List<Long> puIdsBySkuId(Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitReadManage.puIdsBySkuId(skuId);
	}

	@Override
	public Long queryCommodityTemplateIdByPuId(Long puId) {
		return commodityProductUnitReadManage.queryCommodityTemplateIdByPuId(puId);
	}

	@Override
	public PageResult<CommodityProductUnitDTO> findPUByStoreNameSUNameOfPage(QueryProductUnitDTO dto, Pagination page) {
		try {
			PageResult<CommodityProductUnitPO> pageResult = commodityProductUnitReadManage.findPUByStoreNameSUNameOfPage(dto.clone(QueryProductUnitPO.class), page);
			List<CommodityProductUnitDTO> list = CommodityProductUnitConverter.toDTO(pageResult.getList());
			PageResult<CommodityProductUnitDTO> result = new PageResult<CommodityProductUnitDTO>();
			result.setList(list);
			result.setPageNo(pageResult.getPageNo());
			result.setPageSize(pageResult.getPageSize());
			result.setTotalSize(pageResult.getTotalSize());
			return result;
		} catch (Exception e) {
			logger.error("error:" + e);
			PageResult<CommodityProductUnitDTO> result = new PageResult<CommodityProductUnitDTO>();
			List<CommodityProductUnitDTO> list = new ArrayList<>();
			result.setList(list);
			result.setPageNo(page.getPageNo());
			result.setPageSize(page.getPageSize());
			result.setTotalSize(0);
			return result;
		}
	}

	@Override
	public String findPictureByStorePUId(Long storeProductUnitId) {
		return commodityProductUnitReadManage.findPictureByStorePUId(storeProductUnitId);
	}

	@Override
	public List<CommodityProductUnitDTO> findPuListBySuId(Long suId) {
		List<CommodityProductUnitPO> puList= commodityProductUnitReadManage.findPuListBySuId(suId);
		if(EmptyUtil.isEmpty(puList)){
			return null;
		}
		return CommodityProductUnitConverter.toDTO(puList);
	}
	
	@Override
	public PageResult<CommodityProductUnitDTO> findMerchantPUOfPage(CommodityProductUnitDTO dto, 
			Pagination page) {
		PageResult<CommodityProductUnitCondition> pageResult = commodityProductUnitReadManage.findMerchantPUOfPage(CommodityProductUnitConverter.toCondition(dto), page);
		List<CommodityProductUnitDTO> list = CommodityProductUnitConverter.conditionToDTO(pageResult.getList());

		PageResult<CommodityProductUnitDTO> result = new PageResult<>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public Long findSkuIdByPuId(Long puId) {
		return commodityProductUnitReadManage.findSkuIdByPuId(puId);
	}

	@Override
	public boolean queryIsCard(Long puId) {
		//是电子卡券的基本属性值id
		Long basicAttValueId_unitStock=spuAtValueReadManage.queryAttValueIdByPuIdAndAttNameId(puId,BusinessConstant.IS_E_CARD_ID 	);
		return basicAttValueId_unitStock!=null && basicAttValueId_unitStock.longValue()==BusinessConstant.ARE_E_CARD_VALUE_ID ;
	}

	@Override
	public List<CommodityProductUnitDTO> findPuInfoBySuIdList(Integer companyType, List<Long> suIds) {
		List<CommodityProductUnitCondition> list = commodityProductUnitReadManage.findPuInfoBySuIdList(companyType, suIds);
		return CommodityProductUnitConverter.conditionToDTO(list);
	}

	/**
	 * 查询同sku的Pu
	 */
	@Override
	public List<CommodityProductUnitDTO> findByPUIdSkuId(List<Long> puIdList, Long skuId) {
		if(puIdList != null && puIdList.size() > 0) {
			return CommodityProductUnitConverter.toDTO(commodityProductUnitReadManage.findByPUIdSkuId(puIdList,skuId));
		}
		return null;
	}

	@Override
	public List<CommodityProductUnitDTO> findCommodityProductUnitLimit(CommodityProductUnitDTO dto) {
		CommodityProductUnitPO po = CommodityProductUnitConverter.toPO(dto);
		List<CommodityProductUnitPO> list = commodityProductUnitReadManage.findCommodityProductUnitLimit(po);		
		return CommodityProductUnitConverter.toDTO(list);
	}

	@Override
	public Long findLastId() {
		return commodityProductUnitReadManage.findLastId();
	}

	@Override
	public Long findPuIdByExtendSkuId(Long skuId) {
		return commodityProductUnitReadManage.findPuIdByExtendSkuId(skuId);
	}
	@Override
	public Long findPuIdByJdSkuId(Long skuId) {
		return jdProductInnerIdReadManage.findPuIdByJdSkuId(skuId);
	}

}
	