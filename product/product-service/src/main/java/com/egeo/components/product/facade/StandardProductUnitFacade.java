package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.business.StandardProductUnitManage;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.service.read.MerchantReadService;
import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.service.read.ProductAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.read.StandardProductUnitReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.write.StandardProductUnitWriteService;
import com.egeo.components.user.client.ClientClient;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class StandardProductUnitFacade {
	
	@Resource
	private StandardProductUnitReadService standardProductUnitReadService;
	
	@Resource
	private StandardProductUnitWriteService standardProductUnitWriteService;
	
	@Autowired
	private CompanyClient companyReadService;
	
	@Autowired
	private ClientClient clientReadService;
	
	@Resource
	private PictureReadService pictureReadService;
	
	@Resource
	private StandardProductUnitAttNameReadService standardProductUnitAttNameReadService;
	
	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;

	@Resource
	private MerchantReadService merchantReadService;

	@Resource
	private ProductAttNameReadService productAttNameReadService;

;
	@Resource(name="standardProductUnit")
	private StandardProductUnitManage standardProductUnitManage;
	
	public StandardProductUnitDTO findStandardProductUnitById(StandardProductUnitDTO dto){
		
		return standardProductUnitReadService.findStandardProductUnitById(dto);
	}

	public PageResult<StandardProductUnitDTO> findStandardProductUnitOfPage(StandardProductUnitDTO dto,Pagination page){
		
		return standardProductUnitReadService.findStandardProductUnitOfPage(dto, page);
		
	}

	public PageResult<Map<String, Object>> findStandardProductUnitOfPageQuick(StandardProductUnitDTO dto,Pagination page){
		PageResult<StandardProductUnitDTO> spus = standardProductUnitReadService.findStandardProductUnitOfPage(dto, page);
		PageResult<Map<String, Object>> rslt = new PageResult<Map<String, Object>>();
		rslt.setPageSize(spus.getTotalSize());
		rslt.setPageNo(spus.getPageNo());
		rslt.setTotalSize(spus.getTotalSize());
		rslt.setPlatformName(spus.getPlatformName());
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		rslt.setList(lists);
		if(spus!=null && spus.getList()!=null && spus.getList().size()>0) {
			for(StandardProductUnitDTO spuDto : spus.getList()) {
				Map<String, Object> map = standardProductUnitManage.findStandardProductUnitById(spuDto,true);	
				map.put("standardProductUnit", spuDto);				
				lists.add(map);
			}
		}
		return rslt;
		
	}
	
	

	public Map<String, Object> findStandardProductUnitByIdQuick(long spuId){
		StandardProductUnitDTO dto = new StandardProductUnitDTO();
		dto.setId(spuId);
		StandardProductUnitDTO spu = standardProductUnitReadService.findStandardProductUnitById(dto);
		if(spu!=null) {
			Map<String, Object> map = standardProductUnitManage.findStandardProductUnitById(spu,true);	
			map.put("standardProductUnit", spu);	
			return map;	
		}
		return null;
	}
	public List<StandardProductUnitDTO> findStandardProductUnitAll(StandardProductUnitDTO dto){
		
		return standardProductUnitReadService.findStandardProductUnitAll(dto);
		
	}

	public Long insertStandardProductUnitWithTx(StandardProductUnitDTO dto){
		
		return standardProductUnitWriteService.insertStandardProductUnitWithTx(dto);
	}

	public int updateStandardProductUnitWithTx(StandardProductUnitDTO dto){
		
		return standardProductUnitWriteService.updateStandardProductUnitWithTx(dto);
	}

	public int deleteStandardProductUnitWithTx(StandardProductUnitDTO dto){
		
		return standardProductUnitWriteService.deleteStandardProductUnitWithTx(dto);
		
	}
	/**
	 * 根据条件查询客户端
	 * @param dto
	 * @return
	 */
	public List<ClientDTO> findClientAll(ClientDTO dto){
		return clientReadService.findClientAll(dto);
	}
	/**
	 * 根据条件查询福利企业
	 * @param dto
	 * @return
	 */
	public List<CompanyDTO> findCompanyAll(CompanyDTO dto){
		return companyReadService.findCompanyAll(dto);
	}
	/**
	 * 根据spuid设置是否启用
	 * @param standardProductUnitDTO
	 * @return
	 */
	public int updateAvailableWithTx(StandardProductUnitDTO standardProductUnitDTO) {
		// TODO Auto-generated method stub
		return standardProductUnitWriteService.updateStandardProductUnitWithTx(standardProductUnitDTO);
	}
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	public Map<String, Object> queryStandardProductUnitById(Long standardProductUnitId) {
		Map<String, Object> map = new HashMap<>();
		StandardProductUnitDTO standardProductUnitDTO = standardProductUnitReadService.queryStandardProductUnitById(standardProductUnitId);
		map.put("id", standardProductUnitDTO.getId());
		map.put("categoryTreeNodeId", standardProductUnitDTO.getCategoryTreeNodeId());
		map.put("commodityTemplateId", standardProductUnitDTO.getCommodityTemplateId());
		map.put("content", standardProductUnitDTO.getContent());
		map.put("isAvailable", standardProductUnitDTO.getIsAvailable());
		map.put("name", standardProductUnitDTO.getName());
		map.put("productCategory", standardProductUnitDTO.getProductCategory());
		map.put("productSerialNumber", standardProductUnitDTO.getProductSerialNumber());
		map.put("status", standardProductUnitDTO.getStatus());
		
		//根据产品id查询产品图片信息
        List<PictureDTO> pictures = pictureReadService.findByStandardProductUnitId(standardProductUnitId);
        List<String> pictureList = new ArrayList<>();
        for (PictureDTO pictureDTO : pictures) {
        	//类型：1、列表图片 2、轮播图片
			if(pictureDTO.getType() == 1){
				map.put("url", pictureDTO.getUrl());
			}else{
				pictureList.add(pictureDTO.getUrl());
			}
		}
        map.put("pictureList", pictureList);
        
        //根据spuId查询spu属性信息
        List<StandardProductUnitAttNameDTO> standardProductUnitAttNameList = standardProductUnitAttNameReadService.findStandardProductUnitAttNameAll(standardProductUnitId);
        
        List<Map<String, Object>> parameterCategoryAttNameValuse = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> categoryAttNameValuseList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> apecificationList = new ArrayList<Map<String, Object>>();
        //赋值
        assignment(standardProductUnitAttNameList,parameterCategoryAttNameValuse,categoryAttNameValuseList,apecificationList);
        
        map.put("parameterCategoryAttNameValuse", parameterCategoryAttNameValuse);
        map.put("categoryAttNameValuseList", categoryAttNameValuseList);
        map.put("apecificationList", apecificationList);
		return map;
	}

	private void assignment(List<StandardProductUnitAttNameDTO> standardProductUnitAttNameList,
			List<Map<String, Object>> parameterCategoryAttNameValuse, List<Map<String, Object>> categoryAttNameValuseList,
			List<Map<String, Object>> apecificationList) {
		
		for (StandardProductUnitAttNameDTO standardProductUnitAttNameDTO : standardProductUnitAttNameList) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", standardProductUnitAttNameDTO.getAttName());
			map.put("mode", standardProductUnitAttNameDTO.getMode());
			
			if(standardProductUnitAttNameDTO.getType() == 1 || standardProductUnitAttNameDTO.getType() == 3){
				
				if(standardProductUnitAttNameDTO.getMode() == 1){
					//根据spu属性id查询spu属性值信息
					List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
					map.put("value", standardProductUnitAttValueList.get(0).getAttValue());
				}
				if(standardProductUnitAttNameDTO.getMode() == 3){
					//根据spu属性id查询spu属性值信息
					List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
					List<String> values = new ArrayList<>();
					for (StandardProductUnitAttValueDTO standardProductUnitAttValueDTO : standardProductUnitAttValueList) {
						values.add(standardProductUnitAttValueDTO.getAttValue());
					}
					map.put("value", values);
				}
				if(standardProductUnitAttNameDTO.getMode() == 2 || standardProductUnitAttNameDTO.getMode() == 5 || standardProductUnitAttNameDTO.getMode() == 6){
					//根据spu属性id查询spu属性值信息
					List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
					map.put("value", standardProductUnitAttValueList.get(0).getAttValueCustom());
                }else if(standardProductUnitAttNameDTO.getMode() == 4){
                	//根据spu属性id查询spu属性值信息
					List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
					if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList)){
						map.put("value", standardProductUnitAttValueList.get(0).getAttValueCustom());
					}else{
						map.put("value", null);
					}
                }
				if(standardProductUnitAttNameDTO.getMode() == 7){
					//根据spu属性id查询spu属性值信息
					List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
					List<String> values = new ArrayList<>();
					for (StandardProductUnitAttValueDTO standardProductUnitAttValueDTO : standardProductUnitAttValueList) {
						values.add(standardProductUnitAttValueDTO.getAttValueCustom());
					}
					map.put("value", values);
				}
				if(standardProductUnitAttNameDTO.getType() == 1){
					categoryAttNameValuseList.add(map);
				}
				if(standardProductUnitAttNameDTO.getType() == 3){
					parameterCategoryAttNameValuse.add(map);
				}
			}
			if(standardProductUnitAttNameDTO.getType() == 2){
				map.put("showPicture", standardProductUnitAttNameDTO.getShowPicture());
				//根据spu属性id查询spu属性值信息
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
				List<Map<String, Object>> maps = new ArrayList<>();
				for (StandardProductUnitAttValueDTO standardProductUnitAttValueDTO : standardProductUnitAttValueList) {
					Map<String, Object> map2 = new HashMap<>();
					map2.put("attValue", standardProductUnitAttValueDTO.getAttValue());
					map2.put("pictureUrl", standardProductUnitAttValueDTO.getPictureUrl());
					maps.add(map2);
				}
				map.put("value", maps);
				apecificationList.add(map);
			}
		}
		
	}

	public Long findCommodityTemplateIdByStandardUnitId(Long standardUnitId) {
		return standardProductUnitReadService.findCommodityTemplateIdByStandardUnitId(standardUnitId);
	}

	public int findThirdpartyAttBySpuId(Long id) {
		return standardProductUnitAttValueReadService.findThirdpartyAttBySpuId(id);

	}

	/**
	 * 查询id为1和大于等于1000的所有运营方
	 * @return
	 */
    public List<Map<Long,String>> findMerchantList() {
    	List<MerchantDTO> mapList=merchantReadService.findMerchantList();
		List<Map<Long, String>> result = new ArrayList<>();
		if(EmptyUtil.isEmpty(mapList)){
			return result;
		}
		for(MerchantDTO dto:mapList){
			Map<Long,String> map=new HashMap<>();
			map.put(dto.getId(), dto.getName());
			result.add(map);
		}
		return result;
	}

	public List<Map<Long,String>> findMerchantListByType(Integer type) {
		List<MerchantDTO> mapList=merchantReadService.findMerchantListByType(type);
		List<Map<Long, String>> result = new ArrayList<>();
		if(EmptyUtil.isEmpty(mapList)){
			return result;
		}
		for(MerchantDTO dto:mapList){
			Map<Long,String> map=new HashMap<>();
			map.put(dto.getId(), dto.getName());
			result.add(map);
		}
		return result;
	}

    public ProductAttNameDTO queryIsElectronicBySpuId(StandardProductUnitDTO dto) {
		return productAttNameReadService.queryIsElectronicBySpuId(dto);
    }

	public ProductAttNameDTO queryIsUnitBySpuId(StandardProductUnitDTO dto) {
		return productAttNameReadService.queryIsUnitBySpuId(dto);
	}
}
	