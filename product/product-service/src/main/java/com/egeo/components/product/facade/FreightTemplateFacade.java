package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.service.read.FreightRegulationReadService;
import com.egeo.components.product.service.read.FreightTemplateReadService;
import com.egeo.components.product.service.read.MerchantReadService;
import com.egeo.components.product.service.read.StoreReadService;
import com.egeo.components.product.service.write.FreightTemplateWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class FreightTemplateFacade {
	
	@Resource
	private FreightTemplateReadService freightTemplateReadService;
	
	@Resource
	private FreightTemplateWriteService freightTemplateWriteService;
	
	@Resource
	private FreightRegulationReadService freightRegulationReadService;
	
	@Resource
	private StoreReadService storeReadService;

	@Resource
	private MerchantReadService merchantReadService;
	
	
	public Map<String, Object> findFreightTemplateById(Long freightTemplateId){
		Map<String, Object> map = new HashMap<>();
		FreightTemplateDTO freightTemplate = new FreightTemplateDTO();
		freightTemplate.setId(freightTemplateId);
		FreightTemplateDTO freightTemplateDTO = freightTemplateReadService.findFreightTemplateById(freightTemplate);
		map.put("id", freightTemplateDTO.getId());
		map.put("name", freightTemplateDTO.getName());
		map.put("isExemption", freightTemplateDTO.getIsExemption());
		map.put("shipmentsExplain", freightTemplateDTO.getShipmentsExplain());
		map.put("merchantId", freightTemplateDTO.getMerchantId());
		map.put("storeId", freightTemplateDTO.getStoreId());
		FreightRegulationDTO freightRegulationDTO = new FreightRegulationDTO();
		freightRegulationDTO.setFreightTemplateId(freightTemplateDTO.getId());
		List<FreightRegulationDTO> freightRegulationList = freightRegulationReadService.findFreightRegulationAll(freightRegulationDTO);
		map.put("freightRegulations", freightRegulationList);
		return map;
	}

	public PageResult<Map<String, Object>> findFreightTemplateOfPage(FreightTemplateDTO dto,Pagination page){
		List<Map<String, Object>> list = new ArrayList<>();
		PageResult<FreightTemplateDTO> pageResult = freightTemplateReadService.findFreightTemplateOfPage(dto, page);
		List<FreightTemplateDTO> freightTemplateList = pageResult.getList();
		for (FreightTemplateDTO freightTemplateDTO : freightTemplateList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", freightTemplateDTO.getId());
			map.put("name", freightTemplateDTO.getName());
			map.put("shipmentsExplain", freightTemplateDTO.getShipmentsExplain());
			map.put("isExemption", freightTemplateDTO.getIsExemption());
			// 根据门店id查询门店信息
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId(freightTemplateDTO.getStoreId());
			StoreDTO storeDTO2 = storeReadService.findStoreById(storeDTO);
			map.put("storeName", storeDTO2.getName());
			StringBuilder freightRegulation = new StringBuilder();
			
			if(freightTemplateDTO.getIsExemption() == 0){
				//如果不包邮查询运费规则信息
				FreightRegulationDTO freightRegulationDTO = new FreightRegulationDTO();
				freightRegulationDTO.setFreightTemplateId(freightTemplateDTO.getId());
				List<FreightRegulationDTO> freightRegulationList = freightRegulationReadService.findFreightRegulationAll(freightRegulationDTO);
				for (int i = 0; i < freightRegulationList.size(); i++) {
					if(i < freightRegulationList.size() -1){
						freightRegulation.append("订单不满" + freightRegulationList.get(i).getOrderMoney() + "元，运费" + freightRegulationList.get(i).getFreightMoney() + "元；");
					}else{
						freightRegulation.append("订单不满" + freightRegulationList.get(i).getOrderMoney() + "元，运费" + freightRegulationList.get(i).getFreightMoney() + "元；满" + freightRegulationList.get(i).getOrderMoney() + "元免运费");
					}
				}
			}else{
				freightRegulation.append("自营类商品免邮，其他运营方商品邮费标准请以详见结算页面显示为准");
			}
			map.put("freightRegulation", freightRegulation);
			map.put("isValid", freightTemplateDTO.getIsValid());
			map.put("merchantId", freightTemplateDTO.getMerchantId());
			MerchantDTO merchantDTO = new MerchantDTO();
			merchantDTO.setId(freightTemplateDTO.getMerchantId());
			MerchantDTO merchantById = merchantReadService.findMerchantById(merchantDTO);
			if(EmptyUtil.isEmpty(merchantById)){
				throw new BusinessException("运营方不存在,请联系管理员");
			}
			map.put("merchantName",merchantById.getName());
			list.add(map);
			
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
		
	}

	public List<FreightTemplateDTO> findFreightTemplateAll(FreightTemplateDTO dto){
		
		return freightTemplateReadService.findFreightTemplateAll(dto);
		
	}
	/**
	 * 新增运费模版
	 * @param dto
	 * @param freightRegulationList
	 * @return
	 */
	public Long insertFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList){
		
		return freightTemplateWriteService.insertFreightTemplateWithTx(dto,freightRegulationList);
	}
	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	public int updateFreightTemplateWithTx(FreightTemplateDTO dto,List<FreightRegulationDTO> freightRegulationList){
		
		return freightTemplateWriteService.updateFreightTemplateWithTx(dto,freightRegulationList);
	}

	public int deleteFreightTemplateWithTx(FreightTemplateDTO dto){
		
		return freightTemplateWriteService.deleteFreightTemplateWithTx(dto);
		
	}
	/**
	 * 根据运费模版id启用运费模版
	 * @param freightTemplateId
	 * @return
	 */
	public int startFreightTemplateWithTx(Long freightTemplateId) {
		// TODO Auto-generated method stub
		return freightTemplateWriteService.startFreightTemplateWithTx(freightTemplateId);
	}
	/**
	 * 根据商家Id查询运费模版信息
	 * @param freightTemplateId
	 * @return
	 */
	public Map<String, Object> freightTemplateByMerchantId(Long merchantId,Long storeId,Long platformId) {
		FreightTemplateDTO freightTemplateDTO = new FreightTemplateDTO();
		freightTemplateDTO.setMerchantId(merchantId);
		freightTemplateDTO.setIsValid(1);
		freightTemplateDTO.setStoreId(storeId);
		List<FreightTemplateDTO> list = freightTemplateReadService.findFreightTemplateAll(freightTemplateDTO);
		Map<String, Object> map = new HashMap<>();
		if(list.size() == 1){
			freightTemplateDTO = list.get(0);
			// 计算运费
			map = reckonvFreight(freightTemplateDTO);
			
		}else{
			// 默认启用运费模版查询默认总店的
			FreightTemplateDTO freightTemplate = new FreightTemplateDTO();
			freightTemplate.setMerchantId(merchantId);
			freightTemplate.setIsValid(1);
			if(platformId.equals(PlatformKeyConstant.FGJ_PLATFORM_ID)){
				freightTemplate.setStoreId(PlatformKeyConstant.FGJ_ROOT_STORE_ID);
			}
			if(platformId.equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
				freightTemplate.setStoreId(PlatformKeyConstant.MYY_ROOT_STORE_ID);
			}
			
			List<FreightTemplateDTO> freightTemplateList = freightTemplateReadService.findFreightTemplateAll(freightTemplate);
			if(freightTemplateList.size() == 1){
				// 计算运费
				freightTemplateDTO = freightTemplateList.get(0);
				map = reckonvFreight(freightTemplateDTO);
			}else{
				throw new BusinessException("请联系商家，启用一个运费模版");
			}
		}
		return map;
	}
	/**
	 * 计算运费
	 * @param freightTemplateDTO
	 * @return
	 */
	private Map<String, Object> reckonvFreight(FreightTemplateDTO freightTemplateDTO) {
		Map<String, Object> map = new HashMap<>();
		map.put("shipmentsExplain", freightTemplateDTO.getShipmentsExplain());
		StringBuilder freightRegulation = new StringBuilder();
		
		if(freightTemplateDTO.getIsExemption() == 0){
			//如果不包邮查询运费规则信息
			FreightRegulationDTO freightRegulationDTO = new FreightRegulationDTO();
			freightRegulationDTO.setFreightTemplateId(freightTemplateDTO.getId());
			List<FreightRegulationDTO> freightRegulationList = freightRegulationReadService.findFreightRegulationAll(freightRegulationDTO);
			for (int i = 0; i < freightRegulationList.size(); i++) {
				if(i < freightRegulationList.size() -1){
					freightRegulation.append("订单不满" + freightRegulationList.get(i).getOrderMoney() + "元，运费" + freightRegulationList.get(i).getFreightMoney() + "元；");
				}else{
					freightRegulation.append("订单不满" + freightRegulationList.get(i).getOrderMoney() + "元，运费" + freightRegulationList.get(i).getFreightMoney() + "元；满" + freightRegulationList.get(i).getOrderMoney() + "元免运费");
				}
			}
		}else{
			freightRegulation.append("自营类商品免邮，其他运营方商品邮费标准请以详见结算页面显示为准");
		}
		map.put("freightRegulation", freightRegulation);
		return map;
	}
	
	

}
	