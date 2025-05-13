package com.egeo.components.promotion.business.impl;


import com.egeo.components.promotion.business.BuyCardItemRefundManage;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.facade.BuyCardItemRefundFacade;
import com.egeo.components.promotion.service.read.UserCardRecordReadService;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("buyCardItemRefund")
public class BuyCardItemRefundManageImpl implements BuyCardItemRefundManage{

	@Resource
	private UserCardRecordReadService userCardRecordReadService;


	@Resource(name="buyCardItemRefundFacade")
	private BuyCardItemRefundFacade buyCardItemRefundFacade;

	@Override
	public BuyCardItemRefundDTO findBuyCardItemRefundById(BuyCardItemRefundDTO dto) {
		return buyCardItemRefundFacade.findBuyCardItemRefundById(dto);
	}

	@Override
	public PageResult<BuyCardItemRefundDTO> findBuyCardItemRefundOfPage(BuyCardItemRefundDTO dto, Pagination page) {
		PageResult<BuyCardItemRefundDTO> rt = buyCardItemRefundFacade.findBuyCardItemRefundOfPage(dto, page);
		setUserCardRecord(rt.getList());
		return rt;
	}

	private void setUserCardRecord(List<BuyCardItemRefundDTO> list){
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		List<Long> cardIds = FHCollectionUtils.listToStrList(list,BuyCardItemRefundDTO::getCardId);
		UserCardRecordDTO dto = new UserCardRecordDTO();
		dto.setIds(cardIds);
		List<UserCardRecordDTO> userCardRecordDTOS = userCardRecordReadService.findUserCardRecordAll(dto);
		if(CollectionUtils.isEmpty(userCardRecordDTOS)){
			return;
		}
		Map<Long,UserCardRecordDTO> userCardRecordDTOMap = FHCollectionUtils.listToMap(userCardRecordDTOS,UserCardRecordDTO::getId, e->e);
		for (BuyCardItemRefundDTO buyCardItemRefundDTO : list) {
			UserCardRecordDTO userCardRecordDTO = userCardRecordDTOMap.get(buyCardItemRefundDTO.getCardId());
			if(userCardRecordDTO == null){
				continue;
			}
			buyCardItemRefundDTO.setCardName(userCardRecordDTO.getCardName());
			buyCardItemRefundDTO.setCardType(userCardRecordDTO.getCardType());
			buyCardItemRefundDTO.setSettleMethod(userCardRecordDTO.getSettleMethod());
			buyCardItemRefundDTO.setCardNo(userCardRecordDTO.getCardNo());
		}
	}

	@Override
	public List<BuyCardItemRefundDTO> findBuyCardItemRefundAll(BuyCardItemRefundDTO dto) {
		List<BuyCardItemRefundDTO> list = buyCardItemRefundFacade.findBuyCardItemRefundAll(dto);
		setUserCardRecord(list);
		return list;
	}

	@Override
	public Long insertBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto) {
		return buyCardItemRefundFacade.insertBuyCardItemRefundWithTx(dto);
	}

	@Override
	public int updateBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto) {
		return buyCardItemRefundFacade.updateBuyCardItemRefundWithTx(dto);
	}

	@Override
	public int deleteBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto) {
		return buyCardItemRefundFacade.deleteBuyCardItemRefundWithTx(dto);
	}
}
