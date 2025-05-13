package com.egeo.components.promotion.facade;

import com.egeo.components.promotion.dto.BuyCardBaseDTO;
import com.egeo.components.promotion.enums.CardDelFlagEnum;
import com.egeo.components.promotion.service.read.BuyCardBaseReadService;
import com.egeo.components.promotion.service.write.BuyCardBaseWriteService;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


@Component
public class BuyCardBaseFacade {

	@Autowired
	private BuyCardBaseReadService buyCardBaseReadService;

	@Autowired
	private BuyCardBaseWriteService buyCardBaseWriteService;


	public BuyCardBaseDTO findBuyCardBaseById(BuyCardBaseDTO dto){

		return buyCardBaseReadService.findBuyCardBaseById(dto);
	}

	public PageResult<BuyCardBaseDTO> findBuyCardBaseOfPage(BuyCardBaseDTO dto,Pagination page){

		return buyCardBaseReadService.findBuyCardBaseOfPage(dto, page);

	}

	public List<BuyCardBaseDTO> findBuyCardBaseAll(BuyCardBaseDTO dto){

		return buyCardBaseReadService.findBuyCardBaseAll(dto);

	}

	public Long insertBuyCardBaseWithTx(BuyCardBaseDTO dto){
		if(dto.getSortValue()==null){
			int max = buyCardBaseReadService.findMaxSortValue();
			dto.setSortValue(max+1);
		}
		return buyCardBaseWriteService.insertBuyCardBaseWithTx(dto);
	}

	public int updateBuyCardBaseWithTx(BuyCardBaseDTO dto){

		return buyCardBaseWriteService.updateBuyCardBaseWithTx(dto);
	}

	public int deleteBuyCardBaseWithTx(BuyCardBaseDTO dto){

		return buyCardBaseWriteService.deleteBuyCardBaseWithTx(dto);

	}

	public List<BuyCardBaseDTO> findBuyCardBaseAll(List<Long> ids){
		BuyCardBaseDTO dto = new BuyCardBaseDTO();
		dto.setIds(ids);
		return buyCardBaseReadService.findBuyCardBaseAll(dto);

	}

	public Map<Long,BuyCardBaseDTO> findBuyCardBaseMaps(List<Long> ids){
		BuyCardBaseDTO dto = new BuyCardBaseDTO();
		dto.setIds(ids);
		dto.setDelFlag(CardDelFlagEnum.NORMAL.getDelFlag());
		List<BuyCardBaseDTO> list =  buyCardBaseReadService.findBuyCardBaseAll(dto);
		return FHCollectionUtils.listToMap(list,BuyCardBaseDTO::getId,e->e);
	}


	public boolean checkCardNameIsExist(String cardName,Long id){
		//新增时名称不能为空
		if(EmptyUtil.isEmpty(cardName) && id ==null){
			throw new BusinessException("卡名称不能为空");
		}
		//更新时并未更新卡名称
		if(id !=null && EmptyUtil.isEmpty(cardName)){
			return false;
		}
		BuyCardBaseDTO dto = new BuyCardBaseDTO();
		dto.setCardName(cardName);
		List<BuyCardBaseDTO> list = buyCardBaseReadService.findBuyCardBaseAll(dto);
		if(CollectionUtils.isEmpty(list)){
			return false;
		}
		//新增时若根据名称查询到数据，证明重复了
		if(id ==null && !CollectionUtils.isEmpty(list)){
			return true;
		}
		for (BuyCardBaseDTO cardBaseDTO : list) {
			if(!id.equals(cardBaseDTO.getId())){
				return true;
			}
		}
		return false;
	}
}
