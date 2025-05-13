package com.egeo.components.product.business.impl;
	

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SkuManage;
import com.egeo.components.product.converter.SkuConverter;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.facade.SkuFacade;
import com.egeo.components.product.vo.SkuVO;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("sku")
public class SkuManageImpl implements SkuManage{

	
	@Resource(name="skuFacade")
	private SkuFacade skuFacade;
	
	@Override
	public SkuDTO findSkuById(SkuDTO dto) {
		return skuFacade.findSkuById(dto);
	}

	@Override
	public PageResult<SkuDTO> findSkuOfPage(SkuDTO dto, Pagination page) {
		return skuFacade.findSkuOfPage(dto, page);
	}

	@Override
	public List<SkuDTO> findSkuAll(SkuDTO dto) {
		List<SkuDTO> list = skuFacade.findSkuAll(dto);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (SkuDTO skuDTO : list) {
			//是否有效: 0、否 1、是
			if(skuDTO.getIsAvailable() == 0){
				skuDTO.setIsAvailableString("停用");
			}else if(skuDTO.getIsAvailable() == 1){
				skuDTO.setIsAvailableString("启用");
			}
			if(skuDTO.getIsValid() == 0){
				skuDTO.setIsValidString("有效");
			}else if(skuDTO.getIsValid() == 1){
				skuDTO.setIsValidString("无效");
			}
			if(EmptyUtil.isNotEmpty(skuDTO.getUpdateTime())){
				String updateString = formatter.format(skuDTO.getUpdateTime());
				skuDTO.setUpdateString(updateString);
			}
			if(EmptyUtil.isNotEmpty(skuDTO.getSynchronizationTime())){
				String synchronizationString = formatter.format(skuDTO.getSynchronizationTime());
				skuDTO.setSynchronizationString(synchronizationString);
			}
			
		}
		return list;
	}

	@Override
	public Long insertSkuWithTx(SkuDTO dto) {
		return skuFacade.insertSkuWithTx(dto);
	}

	@Override
	public int updateSkuWithTx(SkuDTO dto) {
		return skuFacade.updateSkuWithTx(dto);
	}

	@Override
	public int deleteSkuWithTx(SkuDTO dto) {
		return skuFacade.deleteSkuWithTx(dto);
	}
	/**
	 * 分页查询所有电子卡券sku
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> findSkuECardOfPage(SkuDTO dto, Pagination page) {
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		PageResult<SkuDTO> result = skuFacade.findSkuECardOfPage(dto,page);
		List<Map<String, Object>> skulist = new ArrayList<>();
		for (SkuDTO skuDTO : result.getList()) {
			Map<String, Object> map = new HashMap<>();
			//根据skuId查询sku库存信息
			MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = skuFacade.findMerchantProductWarehouseStockAll(skuDTO.getId());
			if(EmptyUtil.isEmpty(merchantProductVirtualStockDTO)){
				throw new BusinessException("sku库存信息异常，skuId："+skuDTO.getId());
			}
			//根据sku和已分配状态查询条数
			ECardDTO cardDTO = new ECardDTO();
			cardDTO.setSkuId(skuDTO.getId());
			cardDTO.setIsAllocation(1);
			List<ECardDTO> eCardList = skuFacade.findECardAll(cardDTO);
			map.put("eCardSize", eCardList.size());
			map.put("realFrozenStockNum", merchantProductVirtualStockDTO.getRealFrozenStockNum());
			//根据sku和已分配状态查询条数
			ECardDTO card = new ECardDTO();
			card.setSkuId(skuDTO.getId());
			card.setIsAllocation(0);
			List<ECardDTO> eCards = skuFacade.findECardAll(card);
			map.put("residue", eCards.size());
			//可分配
			int num = 0;
			for (int i = 0 ; i < eCards.size() ; i++) {
				if(eCards.get(i).getIsValid() == 1) {
					num++;
				}
			}
			map.put("available", num);
			map.put("PUrealStockNums", merchantProductVirtualStockDTO.getRealStockNums());
			map.put("realStockNum", eCardList.size() + eCards.size());
			//公共信息
			map.put("skuId", skuDTO.getId());
			map.put("skuSerialNumber", skuDTO.getSkuSerialNumber());
			map.put("skuName", skuDTO.getSkuName());
			skulist.add(map);
		}
		pageResult.setList(skulist);
		pageResult.setPageNo(result.getPageNo());
		pageResult.setPageSize(result.getPageSize());
		pageResult.setTotalSize(result.getTotalSize());
		return pageResult;
	}
	/**
	 * 批量启用停用
	 * @param ids
	 * @param type
	 * @return
	 */
	@Override
	public int isAvailableWithTx(List<Long> ids, int type) {
		// TODO Auto-generated method stub
		return skuFacade.isAvailableWithTx(ids, type);
	}

	@Override
	public JsonResult<List<SkuVO>> getMembershipSku(Long platformId) {
		//获得所有的会籍spuid
		List<SkuVO> skuVOList = new ArrayList<>();
		List<Long> result=skuFacade.getMembershipSku(platformId);
		//查询已配置的会籍sku
		List<Long> usedSkuId=skuFacade.getUsedMembershipSkuId(platformId);
		for(Long l:result){
			SkuDTO dto = new SkuDTO();
			dto.setStandardProductUnitId(l);
			dto.setPlatformId(platformId);
			List<SkuDTO> skuAll = skuFacade.findSkuAll(dto);
			if(skuAll!=null){
				List<SkuVO> skuVOS = SkuConverter.toVO(skuAll);
				for(SkuVO vo:skuVOS){
					skuVOList.add(vo);
				}
			}
		}
		//判断所有的sku是否已经被关联,如果已关联,将isvalid设置为0,不可用
		if(EmptyUtil.isNotEmpty(usedSkuId)){
			for(SkuVO vo:skuVOList){
				vo.setIsValid(1);
				for(Long l:usedSkuId){
					if(vo.getId().equals(l)){
						vo.setIsValid(0);
						break;
					}
				}
			}
		}

		return JsonResult.success(skuVOList);
	}

	@Override
	public void updateExternalSkuIdBackStage(Long skuId, String externalSkuId, String barCode) {
		skuFacade.updateExternalSkuId(skuId,externalSkuId,barCode);
	}


}
	