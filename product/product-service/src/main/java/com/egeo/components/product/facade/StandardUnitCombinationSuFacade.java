package com.egeo.components.product.facade;

import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.product.condition.CommodityProductUnitCondition;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.vo.*;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Component;

import com.egeo.components.product.condition.StandardUnitCombinationSuCondition;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.manage.read.StandardUnitCombinationSuReadManage;
import com.egeo.components.product.manage.read.StandardUnitReadManage;
import com.egeo.components.product.service.read.StandardUnitCombinationCategoryReadService;
import com.egeo.components.product.service.read.StandardUnitCombinationReadService;
import com.egeo.components.product.service.read.StandardUnitCombinationSuReadService;
import com.egeo.components.product.service.write.StandardUnitCombinationSuWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.util.CollectionUtils;


@Component
public class StandardUnitCombinationSuFacade {

	@Resource
	private StandardUnitCombinationSuReadService standardUnitCombinationSuReadService;

	@Resource
	private StandardUnitCombinationSuWriteService standardUnitCombinationSuWriteService;

	@Resource
	private StandardUnitCombinationReadService standardUnitCombinationReadService;

	@Resource
	private StandardUnitCombinationCategoryReadService standardUnitCombinationCategoryReadService;
	@Resource
	StandardUnitReadManage standardUnitReadManage;
	@Resource
	StandardUnitCombinationSuReadManage standardUnitCombinationSuReadManage;

	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;

	public StandardUnitCombinationSuDTO findStandardUnitCombinationSuById(StandardUnitCombinationSuDTO dto){

		return standardUnitCombinationSuReadService.findStandardUnitCombinationSuById(dto);
	}

	public PageResult<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuDTO dto,Pagination page){

		return standardUnitCombinationSuReadService.findStandardUnitCombinationSuOfPage(dto, page);

	}

	public List<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuAll(StandardUnitCombinationSuDTO dto){

		return standardUnitCombinationSuReadService.findStandardUnitCombinationSuAll(dto);

	}

	public Long insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto){

		return standardUnitCombinationSuWriteService.insertStandardUnitCombinationSuWithTx(dto);
	}

	public int updateStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto){

		return standardUnitCombinationSuWriteService.updateStandardUnitCombinationSuWithTx(dto);
	}

	public int deleteStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto){

		return standardUnitCombinationSuWriteService.deleteStandardUnitCombinationSuWithTx(dto);

	}
	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @return
	 */
	public boolean saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId, List<Long> standardUnits,Integer source,List<Map<String,String>> productAndSkuMap) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuWriteService.saveStandardUnitCombinationSuAllWithTx(standardUnitCombinationId, standardUnits,source,productAndSkuMap);
	}
	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	public PageResult<StandardUnitCombinationSuDTO> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,Pagination page,boolean isUserClient,String keyWord) {
		StandardUnitCombinationDTO standardUnitCombinationDTO = new StandardUnitCombinationDTO();
		standardUnitCombinationDTO.setId(standardUnitCombinationId);
		StandardUnitCombinationDTO standardUnitCombinationDTO2 = standardUnitCombinationReadService.findStandardUnitCombinationById(standardUnitCombinationDTO);
		if(standardUnitCombinationDTO2.getType() == 1){
			return standardUnitCombinationSuReadService.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,page);
		}else if(standardUnitCombinationDTO2.getType() == 2){
			//su商品类型排序只有一个所以不用选择
			if(standardUnitCombinationDTO2.getSortType() == 1){
				page.setOrderBy("sum desc");
			}
			if(standardUnitCombinationDTO2.getSortType() == 2){
				page.setOrderBy("su.update_time desc");
			}
			if(standardUnitCombinationDTO2.getSortType() == 3){
				page.setOrderBy("su.front_serial_number , su.id desc");
			}
			PageResult<StandardUnitDTO> pageResult = standardUnitCombinationCategoryReadService.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,page);
			List<StandardUnitCombinationSuDTO> list = new ArrayList<>();
			List<StandardUnitDTO> standardUnitList = pageResult.getList();
			for (StandardUnitDTO standardUnitDTO : standardUnitList) {
				StandardUnitCombinationSuDTO standardUnitCombinationSuDTO = new StandardUnitCombinationSuDTO();
				standardUnitCombinationSuDTO.setStandardUnitCombinationId(standardUnitCombinationId);
				standardUnitCombinationSuDTO.setStandardUnitId(standardUnitDTO.getId());
				standardUnitCombinationSuDTO.setStandardUnitName(standardUnitDTO.getName());
				standardUnitCombinationSuDTO.setStandardUnitSalePrice(standardUnitDTO.getSalePrice());
				list.add(standardUnitCombinationSuDTO);
			}
			PageResult<StandardUnitCombinationSuDTO> result = new PageResult<StandardUnitCombinationSuDTO>();
			result.setList(list);
			result.setPageNo(pageResult.getPageNo());
			result.setPageSize(pageResult.getPageSize());
			result.setTotalSize(pageResult.getTotalSize());
			return result;
		}else if(standardUnitCombinationDTO2.getType() >5){
			PageResult<StandardUnitCombinationSuDTO> result = new PageResult<StandardUnitCombinationSuDTO>();
			List<StandardUnitCombinationSuDTO> list = new ArrayList<>();
			PageResult<StandardUnitCondition> datas = standardUnitReadManage.standardUnitByStandardUnitCombinationId(null, null, standardUnitCombinationId, null, null, null, null,
					null, null, null, page, null,null,1,isUserClient,keyWord);
			PageResult<StandardUnitCombinationSuCondition> suPpageResult = standardUnitCombinationSuReadManage.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId, page);
			List<StandardUnitCombinationSuCondition> standardUnitCombinationSuConditionList = suPpageResult.getList();
	       /* for (StandardUnitCombinationSuCondition standardUnitCombinationSuCondition : standardUnitCombinationSuConditionList) {
	        	StandardUnitCombinationSuDTO standardUnitCombinationSuDTO = StandardUnitCombinationSuConverter.toDTO(standardUnitCombinationSuCondition);
	        	standardUnitCombinationSuDTO.setStandardUnitName(standardUnitCombinationSuCondition.getStandardUnitName());
	        	standardUnitCombinationSuDTO.setStandardUnitSalePrice(standardUnitCombinationSuCondition.getSalePrice());
	        	list.add(standardUnitCombinationSuDTO);
			}*/

			Map<Long,StandardUnitCombinationSuCondition> standardUnitCombinationSuConditionMap=new HashMap<>();
			if (EmptyUtil.isNotEmpty(standardUnitCombinationSuConditionList)){
				standardUnitCombinationSuConditionList.forEach(item->{
					if (EmptyUtil.isNotEmpty(item.getStandardUnitId())){
						standardUnitCombinationSuConditionMap.put(item.getStandardUnitId(),item);
					}
				});
			}

			result.setPageNo(datas.getPageNo());
			result.setPageSize(datas.getPageSize());
			result.setTotalSize(datas.getTotalSize());
			if(datas!=null && datas.getList()!=null && datas.getList().size()>0) {
				for(StandardUnitCondition oneData:datas.getList()) {
					StandardUnitCombinationSuDTO tmp = new StandardUnitCombinationSuDTO();
					tmp.setStandardUnitCombinationId(standardUnitCombinationId);
					tmp.setStandardUnitCombinationName(standardUnitCombinationDTO2.getCombinationName());
					if (EmptyUtil.isNotEmpty(oneData) && standardUnitCombinationSuConditionMap.containsKey(oneData.getId())){
						StandardUnitCombinationSuCondition oneSU = standardUnitCombinationSuConditionMap.get(oneData.getId());
						if (EmptyUtil.isNotEmpty(oneSU)){
							tmp.setId(oneSU.getId());
							tmp.setSortValue(oneSU.getSortValue());
							tmp.setSource(oneSU.getSource());
							if(Objects.equals(1,oneSU.getSource())){
								tmp.setSellState(Objects.equals(3,oneData.getStatus())?1:0);
							}else {
								tmp.setSellState(oneSU.getSellState());
							}
						}
					}
					tmp.setStandardUnitId(oneData.getId());
					tmp.setMerchantProductSerialNumber(oneData.getMerchantProductSerialNumber());
					tmp.setImgUrl(oneData.getPictureUrl());
					tmp.setStandardUnitName(oneData.getName());
					tmp.setStandardUnitSalePrice(oneData.getSalePrice());
					tmp.setStandardUnitSupplierPrice(oneData.getSupplierPrice());
					tmp.setSupplierId(oneData.getSupplierId());
					tmp.setProfit(EmptyUtil.isEmpty(oneData.getCustomProfit())?null:String.valueOf(oneData.getCustomProfit()));
					list.add(tmp);
				}
			}

			result.setList(list);
			return result;
		}

		return null;

	}
	/**
	 * 根据su组合与su关系id修改排序
	 * @param standardUnitCombinationSuId
	 * @param sortValue
	 * @return
	 */
	public boolean sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationSuId, Integer sortValue) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuWriteService.sortValueByStandardUnitCombinationIdWithTx(standardUnitCombinationSuId, sortValue);
	}





	public CardCombinationCheckRespVO checkCardCombination(CardCombinationCheckReqVO vo){
		CardCombinationCheckRespVO respVO = new CardCombinationCheckRespVO();
		if(CollectionUtils.isEmpty(vo.getCombinationIds()) || CollectionUtils.isEmpty(vo.getCardCombinations())){
			return respVO;
		}
		List<Long> combinationIds = vo.getCombinationIds();
		List<CardCombinationVO> cardCombinations = vo.getCardCombinations();
		StandardUnitCombinationSuPO queryPO =null;
		List<CardCombinationVO> rtList = new ArrayList<>();
		for (CardCombinationVO cardCombination : cardCombinations) {
			Long suId = cardCombination.getSuId();
			if(EmptyUtil.isEmpty(cardCombination.getSource()) || cardCombination.getSource().intValue() ==1){
				CommodityProductUnitDTO commodityProductUnitDTO = commodityProductUnitReadService.findSUSPUByPUId(cardCombination.getSuId());
				if(commodityProductUnitDTO == null){
					rtList.add(cardCombination);
					continue;
				}
				suId = commodityProductUnitDTO.getStandardUnitId();
			}

			queryPO = new StandardUnitCombinationSuCondition();
			queryPO.setStandardUnitCombinationIds(combinationIds);
			queryPO.setStandardUnitId(suId);
			queryPO.setThirdSkuId(cardCombination.getSkuId());
			queryPO.setSource(cardCombination.getSource());
			List<StandardUnitCombinationSuPO> list = standardUnitCombinationSuReadManage.findStandardUnitCombinationSuAll(queryPO);
			if(CollectionUtils.isEmpty(list)){
				rtList.add(cardCombination);
			}
		}
		respVO.setCardCombinations(rtList);
		return respVO;
	}

	public UserCardCombinationRespVO getCardCombination(UserCardCombinationReqVO vo){
		if(CollectionUtils.isEmpty(vo.getCombinationIds()) || CollectionUtils.isEmpty(vo.getCardCombinations())){
			return null;
		}
		List<Long> combinationIds = vo.getCombinationIds();
		List<CardCombinationVO> cardCombinations = vo.getCardCombinations();
		StandardUnitCombinationSuPO queryPO =null;
		List<Long> respIds = new ArrayList<>();
		for (Long combinationId : combinationIds) {
			for (CardCombinationVO cardCombination : cardCombinations) {
				Long suId = cardCombination.getSuId();
				if(EmptyUtil.isEmpty(cardCombination.getSource()) || cardCombination.getSource().intValue() ==1){
					CommodityProductUnitDTO commodityProductUnitDTO = commodityProductUnitReadService.findSUSPUByPUId(cardCombination.getSuId());
					if(commodityProductUnitDTO == null){
						continue;
					}
					suId = commodityProductUnitDTO.getStandardUnitId();
				}

				queryPO = new StandardUnitCombinationSuCondition();
				queryPO.setStandardUnitCombinationId(combinationId);
				queryPO.setStandardUnitId(suId);
				queryPO.setThirdSkuId(cardCombination.getSkuId());
				queryPO.setSource(cardCombination.getSource());
				List<StandardUnitCombinationSuPO> list = standardUnitCombinationSuReadManage.findStandardUnitCombinationSuAll(queryPO);
				if(!CollectionUtils.isEmpty(list) && !respIds.contains(combinationId)){
					respIds.add(combinationId);
				}
			}
		}
		UserCardCombinationRespVO respVO = new UserCardCombinationRespVO();
		respVO.setCombinationIds(respIds);
		return respVO;
	}
}
