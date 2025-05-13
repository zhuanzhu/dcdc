package com.egeo.components.promotion.service.write.impl;

import com.alibaba.fastjson.JSON;
import com.egeo.components.promotion.converter.BuyCardBaseConverter;
import com.egeo.components.promotion.dto.BuyCardBaseDTO;
import com.egeo.components.promotion.manage.write.BuyCardBaseWriteManage;
import com.egeo.components.promotion.po.BuyCardBasePO;
import com.egeo.components.promotion.service.write.BuyCardBaseWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("buyCardBaseWriteService")
public class BuyCardBaseWriteServiceImpl implements BuyCardBaseWriteService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BuyCardBaseWriteManage buyCardBaseWriteManage;

	@Override
	public Long insertBuyCardBaseWithTx(BuyCardBaseDTO dto) {
		checkParamIsNull(dto);
		BuyCardBasePO po = BuyCardBaseConverter.toPO(dto);
		Long rt = buyCardBaseWriteManage.insertBuyCardBaseWithTx(po);
		return rt;
	}

	@Override
	public int updateBuyCardBaseWithTx(BuyCardBaseDTO dto) {
		BuyCardBasePO po = BuyCardBaseConverter.toPO(dto);
		int rt = buyCardBaseWriteManage.updateBuyCardBaseWithTx(po);
		return rt;
	}

	@Override
	public int deleteBuyCardBaseWithTx(BuyCardBaseDTO dto) {
		BuyCardBasePO po = BuyCardBaseConverter.toPO(dto);
		int rt = buyCardBaseWriteManage.deleteBuyCardBaseWithTx(po);
		return rt;
	}

	private void checkParamIsNull(BuyCardBaseDTO dto){
		if(dto ==null || EmptyUtil.isEmpty(dto.getCardName()) || EmptyUtil.isEmpty(dto.getCardType())
				|| EmptyUtil.isEmpty(dto.getSettleMethod()) || EmptyUtil.isEmpty(dto.getCardAmount())
				|| dto.getCardAmount().compareTo(BigDecimal.ZERO) !=1
				|| dto.getExpirationDateUnit()==null || dto.getExpirationDate()==null
				|| dto.getExpirationDate()<1 || dto.getExpirationDateUnit()<0
				|| dto.getExpirationDateUnit()>4){
			logger.error("操作卡基本信息参数缺省:{}", JSON.toJSONString(dto));
			throw new BusinessException("参数错误");
		}
	}
}
