package com.egeo.components.promotion.facade;

import com.egeo.components.promotion.dto.BuyCardBaseDTO;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.enums.CardUseStateEnum;
import com.egeo.components.promotion.service.read.UserCardRecordReadService;
import com.egeo.components.promotion.service.write.UserCardRecordWriteService;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Component
public class UserCardRecordFacade {

	@Autowired
	private UserCardRecordReadService UserCardRecordReadService;

	@Autowired
	private UserCardRecordWriteService UserCardRecordWriteService;

	@Resource
	private BuyCardBaseFacade buyCardBaseFacade;


	public UserCardRecordDTO findUserCardRecordById(UserCardRecordDTO dto){

		return UserCardRecordReadService.findUserCardRecordById(dto);
	}

	public PageResult<UserCardRecordDTO> findUserCardRecordOfPage(UserCardRecordDTO dto,Pagination page){

		return UserCardRecordReadService.findUserCardRecordOfPage(dto, page);

	}

	public List<UserCardRecordDTO> findUserCardRecordAll(UserCardRecordDTO dto){

		return UserCardRecordReadService.findUserCardRecordAll(dto);

	}

	public Long insertUserCardRecordWithTx(UserCardRecordDTO dto){

		return UserCardRecordWriteService.insertUserCardRecordWithTx(dto);
	}

	public int updateUserCardRecordWithTx(UserCardRecordDTO dto){

		return UserCardRecordWriteService.updateUserCardRecordWithTx(dto);
	}

	public int deleteUserCardRecordWithTx(UserCardRecordDTO dto){

		return UserCardRecordWriteService.deleteUserCardRecordWithTx(dto);

	}

	public Long insertUserCardRecordBatchWithTx(List<UserCardRecordDTO> dtos){
		return UserCardRecordWriteService.insertUserCardRecordBatchWithTx(dtos);
	}

	public Integer countUserCardRecord(UserCardRecordDTO dto){
		 return UserCardRecordReadService.countUserCardRecord(dto);
	}

	public List<UserCardRecordDTO> sumUserCardTypeAmount(UserCardRecordDTO dto){

		return UserCardRecordReadService.sumUserCardTypeAmount(dto);
	}


	public PageResult<UserCardRecordDTO> findClientUserCardRecordOfPage(UserCardRecordDTO dto,Pagination page){
		PageResult<UserCardRecordDTO> rt = findUserCardRecordOfPage(dto,page);
		setCardBaseInfo(rt);
		return rt;

	}

	private void setCardBaseInfo(PageResult<UserCardRecordDTO> rt){
		if(rt ==null || CollectionUtils.isEmpty(rt.getList())){
			return;
		}
		setCardBaseInfo(rt.getList());
	}

	private void setCardBaseInfo(List<UserCardRecordDTO> list){
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		List<Long> sourceIds = FHCollectionUtils.listToStrList(list,UserCardRecordDTO::getSourceCardId,e->e.getSourceCardId() !=null);
		if(CollectionUtils.isEmpty(sourceIds)){
			return;
		}

		Map<Long, BuyCardBaseDTO> baseDTOMap =  buyCardBaseFacade.findBuyCardBaseMaps(sourceIds);
		for (UserCardRecordDTO userCardRecordDTO : list) {
			if(userCardRecordDTO.getSourceCardId() ==null || !baseDTOMap.containsKey(userCardRecordDTO.getSourceCardId())){
				continue;
			}
			BuyCardBaseDTO buyCardBaseDTO = baseDTOMap.get(userCardRecordDTO.getSourceCardId());
			userCardRecordDTO.setRuleDescribe(buyCardBaseDTO.getRuleDescribe());
			userCardRecordDTO.setDescribe(buyCardBaseDTO.getDescribe());
			userCardRecordDTO.setCombinationId(buyCardBaseDTO.getCombinationId());
		}
	}

	public List<UserCardRecordDTO> findClientUserCardRecordAll(UserCardRecordDTO dto){
		List<UserCardRecordDTO> list = findUserCardRecordAll(dto);
		setCardBaseInfo(list);
		return list;
	}

	public Boolean updateUserCardRecordWithTx(Long id,BigDecimal amount){
		UserCardRecordDTO dto = new UserCardRecordDTO();
		dto.setId(id);
		UserCardRecordDTO rtDTO = UserCardRecordReadService.findUserCardRecordById(dto);
		Integer useState = CardUseStateEnum.USE_ING.getUseState();
		BigDecimal supAmount = rtDTO.getCardCash().subtract(amount);
		if(supAmount.compareTo(BigDecimal.ZERO) <=0){
			useState = CardUseStateEnum.USED.getUseState();
		}
		return UserCardRecordWriteService.useCardWithTx(id,amount,useState);
	}

	public Boolean useCardWithTx(Long id,BigDecimal amount,Integer useState){

		return UserCardRecordWriteService.useCardWithTx(id,amount,useState);
	}

	public Boolean refundUserCardRecordWithTx(Long id,BigDecimal amount,Integer useState){

		return UserCardRecordWriteService.refundUserCardRecordWithTx(id,amount,useState);
	}

	public Boolean refundUserCardUseIngWithTx(Long id,BigDecimal amount){
		UserCardRecordDTO dto = new UserCardRecordDTO();
		dto.setId(id);
		UserCardRecordDTO one =	findUserCardRecordById(dto);
		Integer useState = CardUseStateEnum.USE_ING.getUseState();
		if(one.getUseState().equals(CardUseStateEnum.USE_ING.getUseState())){
			useState = one.getUseState();
		}
		return UserCardRecordWriteService.refundUserCardRecordWithTx(id,amount,useState);
	}
}
