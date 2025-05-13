package com.egeo.components.cms.business.impl;
	

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.CommodityTemplateManage;
import com.egeo.components.cms.facade.CommodityTemplateFacade;
import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("commodityTemplate")
public class CommodityTemplateManageImpl implements CommodityTemplateManage{

	
	@Resource(name="commodityTemplateFacade")
	private CommodityTemplateFacade commodityTemplateFacade;
	/**
	 * 根据商品模版id查询商品模版信息
	 * @param commodityTemplateId
	 * @return
	 */
	@Override
	public Map<String, Object> findCommodityTemplateById(Long commodityTemplateId) {
		CommodityTemplateDTO commodityTemplateDTO = new CommodityTemplateDTO();
		commodityTemplateDTO.setId(commodityTemplateId);
		CommodityTemplateDTO commodityTemplateDTO2 = commodityTemplateFacade.findCommodityTemplateById(commodityTemplateDTO);
		Map<String, Object> map = new HashMap<>();
		map.put("commodityTemplateId", commodityTemplateDTO2.getId());
		map.put("templateName", commodityTemplateDTO2.getTemplateName());
		map.put("templateType", commodityTemplateDTO2.getTemplateType());
		map.put("versionCodeA", commodityTemplateDTO2.getVersionCodeA());
		map.put("versionCodeI", commodityTemplateDTO2.getVersionCodeI());
		map.put("showTemplate", commodityTemplateDTO2.getShowTemplate());
		return map;
	}

	@Override
	public PageResult<CommodityTemplateDTO> findCommodityTemplateOfPage(CommodityTemplateDTO dto, Pagination page) {
		return commodityTemplateFacade.findCommodityTemplateOfPage(dto, page);
	}

	@Override
	public List<CommodityTemplateDTO> findCommodityTemplateAll(CommodityTemplateDTO dto) {
		return commodityTemplateFacade.findCommodityTemplateAll(dto);
	}

	@Override
	public Long insertCommodityTemplateWithTx(CommodityTemplateDTO dto) {
		return commodityTemplateFacade.insertCommodityTemplateWithTx(dto);
	}

	@Override
	public int updateCommodityTemplateWithTx(CommodityTemplateDTO dto) {
		return commodityTemplateFacade.updateCommodityTemplateWithTx(dto);
	}

	@Override
	public int deleteCommodityTemplateWithTx(CommodityTemplateDTO dto) {
		return commodityTemplateFacade.deleteCommodityTemplateWithTx(dto);
	}
	/**
	 * 分页查询所有商品类型模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> queryCommodityTemplateOfPage(CommodityTemplateDTO dto, Pagination page) {
		// TODO Auto-generated method stub
		return commodityTemplateFacade.queryCommodityTemplateOfPage(dto, page);
	}
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	@Override
	public boolean showCommodityTemplateWithTx(Long commodityTemplateId) {
		// TODO Auto-generated method stub
		return commodityTemplateFacade.showCommodityTemplateWithTx(commodityTemplateId);
	}
	/**
	 * 根据商品类型查询商品模版
	 * @param commodityTemplateId
	 * @return
	 */
	@Override
	public Map<String, Object> findCommodityTemplateByType(Integer type,Long platformId) {
		// TODO Auto-generated method stub
		return commodityTemplateFacade.findCommodityTemplateByType(type,platformId);
	}


}
	