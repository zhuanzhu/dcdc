package com.egeo.components.product.business.impl;


import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.product.dao.read.CommodityProductUnitReadDAO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.facade.SkuFacade;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.vo.CardCombinationCheckReqVO;
import com.egeo.components.product.vo.CardCombinationCheckRespVO;
import com.egeo.components.product.vo.UserCardCombinationReqVO;
import com.egeo.components.product.vo.UserCardCombinationRespVO;
import com.egeo.components.utils.StringUtil;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitCombinationSuManage;
import com.egeo.components.product.facade.StandardUnitCombinationSuFacade;
import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitCombinationSu")
public class StandardUnitCombinationSuManageImpl implements StandardUnitCombinationSuManage{


	@Resource(name="standardUnitCombinationSuFacade")
	private StandardUnitCombinationSuFacade standardUnitCombinationSuFacade;
	@Resource
	private SkuFacade skuFacade;
	@Resource
	private CommodityProductUnitReadDAO commodityProductUnitReadDAO;

	@Override
	public StandardUnitCombinationSuDTO findStandardUnitCombinationSuById(StandardUnitCombinationSuDTO dto) {
		return standardUnitCombinationSuFacade.findStandardUnitCombinationSuById(dto);
	}

	@Override
	public PageResult<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuDTO dto, Pagination page) {
		return standardUnitCombinationSuFacade.findStandardUnitCombinationSuOfPage(dto, page);
	}

	@Override
	public List<StandardUnitCombinationSuDTO> findStandardUnitCombinationSuAll(StandardUnitCombinationSuDTO dto) {
		return standardUnitCombinationSuFacade.findStandardUnitCombinationSuAll(dto);
	}

	@Override
	public Long insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto) {
		return standardUnitCombinationSuFacade.insertStandardUnitCombinationSuWithTx(dto);
	}

	@Override
	public int updateStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto) {
		return standardUnitCombinationSuFacade.updateStandardUnitCombinationSuWithTx(dto);
	}

	@Override
	public int deleteStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto) {
		return standardUnitCombinationSuFacade.deleteStandardUnitCombinationSuWithTx(dto);
	}

	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @param standardUnits
	 * @return
	 */
	@Override
	public boolean saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId, List<Long> standardUnits,Integer source,List<Map<String,String>> productAndSkuMap) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuFacade.saveStandardUnitCombinationSuAllWithTx(standardUnitCombinationId, standardUnits,source,productAndSkuMap);
	}
	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,String keyWord,Pagination page,boolean isUserClient) {
		List<Map<String, Object>> list = new ArrayList<>();
		PageResult<StandardUnitCombinationSuDTO> pageResult = standardUnitCombinationSuFacade.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId,page,isUserClient,keyWord);
		if (EmptyUtil.isNotEmpty(pageResult)) {
			List<StandardUnitCombinationSuDTO> StandardUnitCombinationSuDTOList = pageResult.getList();
			Map<Long, SkuDTO> skuSerialMap=new HashMap<>();
			if (EmptyUtil.isNotEmpty(StandardUnitCombinationSuDTOList)){
				List<Long> skuIds=new ArrayList<>();
				StandardUnitCombinationSuDTOList.forEach(item->{
					if (Objects.equals(1,item.getSource()) && EmptyUtil.isNotEmpty(item.getStandardUnitId())
							&& EmptyUtil.isNotEmpty(item.getStandardUnitSalePrice())){
						CommodityProductUnitPO commodityProductUnitPO=new CommodityProductUnitPO();
						commodityProductUnitPO.setStandardUnitId(item.getStandardUnitId());
						commodityProductUnitPO.setSalePrice(item.getStandardUnitSalePrice());
						List<CommodityProductUnitPO> commodityProductUnitPOS=commodityProductUnitReadDAO.findAll(commodityProductUnitPO,null);
						if (EmptyUtil.isNotEmpty(commodityProductUnitPOS)){
							Long skuId=commodityProductUnitPOS.get(0).getSkuId();
							item.setSkuId(skuId);
							skuIds.add(skuId);
						}
					}
				});
				if (EmptyUtil.isNotEmpty(skuIds)){
					List<SkuDTO> skuDTOS=skuFacade.findSkuBySkuIds(skuIds);
					if (EmptyUtil.isNotEmpty(skuDTOS)){
						skuDTOS.forEach(item->skuSerialMap.put(item.getId(),item));
					}
				}
			}
			for (StandardUnitCombinationSuDTO standardUnitCombinationSuDTO : StandardUnitCombinationSuDTOList) {
				Map<String, Object> map= new HashMap<>();
				map.put("StandardUnitName", standardUnitCombinationSuDTO.getStandardUnitName());
				map.put("standardUnitId", standardUnitCombinationSuDTO.getStandardUnitId());
				map.put("sortValue", standardUnitCombinationSuDTO.getSortValue());
				map.put("imgUrl", standardUnitCombinationSuDTO.getImgUrl());
				map.put("standardUnitCombinationSuId", standardUnitCombinationSuDTO.getId());
				map.put("standardUnitCombinationName",standardUnitCombinationSuDTO.getStandardUnitCombinationName());
				BigDecimal salePrice=standardUnitCombinationSuDTO.getStandardUnitSalePrice();
				map.put("suSalePrice", salePrice);
				map.put("sellState",EmptyUtil.isEmpty(standardUnitCombinationSuDTO.getSellState())?1:standardUnitCombinationSuDTO.getSellState());
				map.put("profit",StringUtil.isEmpty(standardUnitCombinationSuDTO.getProfit())?"-":standardUnitCombinationSuDTO.getProfit());
				map.put("grossProfit","-");
				map.put("supplierPrice", "-");
				map.put("source",standardUnitCombinationSuDTO.getSource());
				salePrice=StringUtil.nullToZero(salePrice);
				if (Objects.equals(1,standardUnitCombinationSuDTO.getSource())){
                    map.put("supplierId",standardUnitCombinationSuDTO.getSupplierId());
					SkuDTO skuDTO=skuSerialMap.get(standardUnitCombinationSuDTO.getSkuId());
					if (EmptyUtil.isNotEmpty(skuDTO)){
						BigDecimal supplierPrice=StringUtil.nullToZero(skuDTO.getSkuCostingPrice());
						map.put("supplierPrice", supplierPrice);
						if (supplierPrice.compareTo(BigDecimal.ZERO)>0 && salePrice.compareTo(BigDecimal.ZERO)>0){
                            BigDecimal grossProfit=salePrice.subtract(supplierPrice);
							BigDecimal profit=grossProfit.divide(salePrice,4,BigDecimal.ROUND_HALF_UP)
									.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
							map.put("profit",profit);
							map.put("grossProfit",grossProfit);
						}
					}
				} else if (Objects.equals(3,standardUnitCombinationSuDTO.getSource())) {
					BigDecimal supplierPrice=standardUnitCombinationSuDTO.getStandardUnitSupplierPrice();
					if (EmptyUtil.isNotEmpty(supplierPrice) && supplierPrice.compareTo(BigDecimal.ZERO)>=0){
						BigDecimal grossProfit=salePrice.subtract(supplierPrice);
						map.put("supplierPrice", supplierPrice);
						map.put("grossProfit",grossProfit);
					}
				}else if(Objects.equals(ThirdConst.Source.CAKE,standardUnitCombinationSuDTO.getSource()) || Objects.equals(ThirdConst.Source.WORLD,standardUnitCombinationSuDTO.getSource())){
					BigDecimal supplierPrice = standardUnitCombinationSuDTO.getStandardUnitSupplierPrice();
					map.put("supplierPrice", standardUnitCombinationSuDTO.getStandardUnitSupplierPrice());
					if (supplierPrice.compareTo(BigDecimal.ZERO)>0 && salePrice.compareTo(BigDecimal.ZERO)>0){
						BigDecimal grossProfit=salePrice.subtract(supplierPrice);
						BigDecimal profit=grossProfit.divide(salePrice,4,BigDecimal.ROUND_HALF_UP)
								.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
						map.put("profit",profit);
						map.put("grossProfit",grossProfit);
					}
				}
				list.add(map);
			}
			PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
			result.setList(list);
			result.setPageNo(pageResult.getPageNo());
			result.setPageSize(pageResult.getPageSize());
			result.setTotalSize(pageResult.getTotalSize());
			return result;
		}
		return null;
	}
	/**
	 * 根据su组合与su关系id修改排序
	 * @param standardUnitCombinationId
	 * @param sortValue
	 * @return
	 */
	@Override
	public boolean sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationSuId, Integer sortValue) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuFacade.sortValueByStandardUnitCombinationIdWithTx(standardUnitCombinationSuId, sortValue);
	}

	@Override
	public CardCombinationCheckRespVO checkCardCombination(CardCombinationCheckReqVO vo){
		return standardUnitCombinationSuFacade.checkCardCombination(vo);
	}

	@Override
	public UserCardCombinationRespVO getCardCombination(UserCardCombinationReqVO vo){

		return standardUnitCombinationSuFacade.getCardCombination(vo);
	}
}
