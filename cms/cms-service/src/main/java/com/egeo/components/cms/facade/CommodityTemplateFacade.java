package com.egeo.components.cms.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.components.cms.service.read.CommodityTemplateReadService;
import com.egeo.components.cms.service.write.CommodityTemplateWriteService;
import com.egeo.components.user.client.VersionsClient;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CommodityTemplateFacade {
	Logger logger = Logger.getLogger(CommodityTemplateFacade.class);
	@Resource
	private CommodityTemplateReadService commodityTemplateReadService;
	
	@Resource
	private CommodityTemplateWriteService commodityTemplateWriteService;
	
	@Autowired
	private VersionsClient versionsReadService;
	
	
	public CommodityTemplateDTO findCommodityTemplateById(CommodityTemplateDTO dto){
		
		return commodityTemplateReadService.findCommodityTemplateById(dto);
	}

	public PageResult<CommodityTemplateDTO> findCommodityTemplateOfPage(CommodityTemplateDTO dto,Pagination page){
		
		return commodityTemplateReadService.findCommodityTemplateOfPage(dto, page);
		
	}

	public List<CommodityTemplateDTO> findCommodityTemplateAll(CommodityTemplateDTO dto){
		
		return commodityTemplateReadService.findCommodityTemplateAll(dto);
		
	}

	public Long insertCommodityTemplateWithTx(CommodityTemplateDTO dto){
		
		return commodityTemplateWriteService.insertCommodityTemplateWithTx(dto);
	}

	public int updateCommodityTemplateWithTx(CommodityTemplateDTO dto){
		
		return commodityTemplateWriteService.updateCommodityTemplateWithTx(dto);
	}

	public int deleteCommodityTemplateWithTx(CommodityTemplateDTO dto){
		
		return commodityTemplateWriteService.deleteCommodityTemplateWithTx(dto);
		
	}
	/**
	 * 分页查询所有商品类型模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> queryCommodityTemplateOfPage(CommodityTemplateDTO dto, Pagination page) {
		List<Map<String, Object>> maps = new ArrayList<>();
		PageResult<CommodityTemplateDTO> pageResult = commodityTemplateReadService.findCommodityTemplateOfPage(dto, page);
		List<CommodityTemplateDTO> list = pageResult.getList();
		for (CommodityTemplateDTO commodityTemplateDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("commodityTemplateId", commodityTemplateDTO.getId());
			map.put("templateName", commodityTemplateDTO.getTemplateName());
			map.put("templateType", commodityTemplateDTO.getTemplateType());
			//根据版本编号查询版本信息
			logger.info("versionCode="+commodityTemplateDTO.getVersionCodeA());
			logger.info("commodityTemplateId="+commodityTemplateDTO.getId());
			VersionsDTO versionsA = versionsReadService.queryVerisonByVersionCode(commodityTemplateDTO.getVersionCodeA(), 0,null,dto.getPlatformId());
			VersionsDTO versionsI = versionsReadService.queryVerisonByVersionCode(commodityTemplateDTO.getVersionCodeI(), 1,null,dto.getPlatformId());
			if(versionsA == null){
				throw new BusinessException("安卓版本编号异常，版本编号为"+commodityTemplateDTO.getVersionCodeA());
			}
			map.put("versionsA", versionsA.getVersionsMark());
			if(versionsI == null){
				throw new BusinessException("IOS版本编号异常，版本编号为"+commodityTemplateDTO.getVersionCodeI());
			}
			map.put("versionsI", versionsI.getVersionsMark());
			map.put("commodityTemplateId", commodityTemplateDTO.getId());
			map.put("show", commodityTemplateDTO.getShowTemplate());
			map.put("imgUrl", commodityTemplateDTO.getImgUrl());
			maps.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(maps);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	public boolean showCommodityTemplateWithTx(Long commodityTemplateId) {
		// TODO Auto-generated method stub
		return commodityTemplateWriteService.showCommodityTemplateWithTx(commodityTemplateId);
	}
	/**
	 * 根据商品类型查询商品模版
	 * @param commodityTemplateId
	 * @return
	 */
	public Map<String, Object> findCommodityTemplateByType(Integer type,Long platformId) {
		Map<String, Object> commodityTemplateList = new HashMap<>();
		List<Map<String, Object>> maps = new ArrayList<>();
		CommodityTemplateDTO commodityTemplateDTO = new CommodityTemplateDTO();
		commodityTemplateDTO.setTemplateType(type);
		commodityTemplateDTO.setPlatformId(platformId);
		List<CommodityTemplateDTO> list = commodityTemplateReadService.findCommodityTemplateAll(commodityTemplateDTO);
		for (CommodityTemplateDTO commodityTemplateDTO2 : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("commodityTemplateId", commodityTemplateDTO2.getId());
			map.put("templateName", commodityTemplateDTO2.getTemplateName());
			maps.add(map);
		}
		commodityTemplateList.put("commodityTemplateList", maps);
		//根据类型查询默认模版信息
		CommodityTemplateDTO commodityTemplateDTO2 = commodityTemplateReadService.findDefaultByType(type,platformId);
		commodityTemplateList.put("defaultTemplateId", commodityTemplateDTO2.getId());
		return commodityTemplateList;
	}

}
	