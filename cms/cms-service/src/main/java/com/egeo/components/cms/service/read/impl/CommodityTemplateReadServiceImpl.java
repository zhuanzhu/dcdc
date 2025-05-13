package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CommodityTemplateReadService;
import com.egeo.components.cms.manage.read.CommodityTemplateReadManage;
import com.egeo.components.cms.converter.CommodityTemplateConverter;
import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.components.cms.po.CommodityTemplatePO;

import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("commodityTemplateReadService")
public class CommodityTemplateReadServiceImpl  implements CommodityTemplateReadService {
	@Autowired
	private CommodityTemplateReadManage commodityTemplateReadManage;

	@Override
	public CommodityTemplateDTO findCommodityTemplateById(CommodityTemplateDTO dto) {
		CommodityTemplatePO po = CommodityTemplateConverter.toPO(dto);
		CommodityTemplatePO list = commodityTemplateReadManage.findCommodityTemplateById(po);		
		return CommodityTemplateConverter.toDTO(list);
	}

	@Override
	public PageResult<CommodityTemplateDTO> findCommodityTemplateOfPage(CommodityTemplateDTO dto, Pagination page) {
		CommodityTemplatePO po = CommodityTemplateConverter.toPO(dto);
        PageResult<CommodityTemplatePO> pageResult = commodityTemplateReadManage.findCommodityTemplateOfPage(po, page);
        
        List<CommodityTemplateDTO> list = CommodityTemplateConverter.toDTO(pageResult.getList());
        PageResult<CommodityTemplateDTO> result = new PageResult<CommodityTemplateDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CommodityTemplateDTO> findCommodityTemplateAll(CommodityTemplateDTO dto) {
		CommodityTemplatePO po = CommodityTemplateConverter.toPO(dto);
		List<CommodityTemplatePO> list = commodityTemplateReadManage.findCommodityTemplateAll(po);		
		return CommodityTemplateConverter.toDTO(list);
	}
	/**
	 * 根据类型查询默认模版信息
	 * @param type
	 * @return
	 */
	@Override
	public CommodityTemplateDTO findDefaultByType(Integer type,Long platformId) {
		CommodityTemplatePO commodityTemplatePO = commodityTemplateReadManage.findDefaultByType(type,platformId);
		if(EmptyUtil.isEmpty(commodityTemplatePO)){
			//模版类型：1、实物商品模版 2、电子卡券模板 3、话费充值模版
			throw new BusinessException("商品类型"+templateTypeToString(type)+"默认模版不能为空");
		}
		return CommodityTemplateConverter.toDTO(commodityTemplatePO);
		
	}
	/**
	 * 类型转字符
	 * @return
	 */
	private String templateTypeToString(Integer type){
		switch (type) {
		case 1:
			return "实物商品模版";
		case 2:
			return "电子卡券模板";
		case 3:
			return "话费充值模版";
		default:
			return null;
		}
	}
}
	