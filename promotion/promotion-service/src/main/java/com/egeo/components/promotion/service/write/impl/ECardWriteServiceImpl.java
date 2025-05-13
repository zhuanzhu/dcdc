package com.egeo.components.promotion.service.write.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.ServerApp;
import com.egeo.components.promotion.converter.CardBatchConverter;
import com.egeo.components.promotion.converter.ECardConverter;
import com.egeo.components.promotion.converter.ErCardRecordConverter;
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.manage.write.ECardWriteManage;
import com.egeo.components.promotion.manage.write.ErCardRecordWriteManage;
import com.egeo.components.promotion.po.ECardPO;
import com.egeo.components.promotion.service.write.ECardWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Service("eCardWriteService")
public class ECardWriteServiceImpl implements ECardWriteService {
	private static final XLogger logger = XLogger.getLogger(ECardWriteServiceImpl.class);
	@Autowired
	private ECardWriteManage eCardWriteManage;
	@Autowired
	private ErCardRecordWriteManage erCardRecordWriteManage;

	@Override
	//endtime为null时直接保存null
	public Long insertECardWithTx(ECardDTO dto) {
		logger.info("保存卡券");
		logger.info(dto.getCardNumber());
		logger.info(dto.getSkuSerialNumber());
		ECardPO po = ECardConverter.toPO(dto);
		Long rt = eCardWriteManage.insertECardWithTx(po);		
		return rt;
	}

	@Override
	public int updateECardWithTx(ECardDTO dto) {
		ECardPO po = ECardConverter.toPO(dto);
		int rt = eCardWriteManage.updateECardWithTx(po);		
		return rt;
	}

	@Override
	public int deleteECardWithTx(ECardDTO dto) {
		ECardPO po = ECardConverter.toPO(dto);
		int rt = eCardWriteManage.deleteECardWithTx(po);		
		return rt;
	}
	/**
	 * 批量导入
	 * @param list
	 * @param source
	 * @param remark
	 * @return
	 */
	@Override
	public int importECardWithTx(
			CardBatchDTO cardBatchDTO,
			List<ErCardRecordDTO> erCardRecords) {
		// 批量导入卡密数据
		return erCardRecordWriteManage.insertErCardRecordsWithTx(CardBatchConverter.toPO(cardBatchDTO),ErCardRecordConverter.toPO(erCardRecords));
	}
	
	@Override
	public int updateCardTypeBySpuId(Long spuId, Integer cardType) {
		if(EmptyUtil.isEmpty(spuId))
			throw new BusinessException("spuId不能为空");
		if(EmptyUtil.isEmpty(cardType))
			throw new BusinessException("卡密类型不能为空");
		return eCardWriteManage.updateCardTypeBySpuId(spuId, cardType);
	}

	public int updateECardList(Map<String, Object> keys) {
		if (EmptyUtil.isEmpty(keys)) {
			return 0;
		}
		return eCardWriteManage.updateECardByKeyWithTx(keys);
	}

}
	