package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.PaymentCodeSaltConverter;
import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.components.config.manage.read.PaymentCodeSaltReadManage;
import com.egeo.components.config.po.PaymentCodeSaltPO;
import com.egeo.components.config.service.read.PaymentCodeSaltReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("paymentCodeSaltReadService")
public class PaymentCodeSaltReadServiceImpl implements PaymentCodeSaltReadService {
	@Autowired
	private PaymentCodeSaltReadManage paymentCodeSaltReadManage;

	@Override
	public PaymentCodeSaltDTO findPaymentCodeSaltById(PaymentCodeSaltDTO dto) {
		PaymentCodeSaltPO po = PaymentCodeSaltConverter.toPO(dto);
		PaymentCodeSaltPO list = paymentCodeSaltReadManage.findPaymentCodeSaltById(po);		
		return PaymentCodeSaltConverter.toDTO(list);
	}

	@Override
	public PageResult<PaymentCodeSaltDTO> findPaymentCodeSaltOfPage(PaymentCodeSaltDTO dto, Pagination page) {
		PaymentCodeSaltPO po = PaymentCodeSaltConverter.toPO(dto);
        PageResult<PaymentCodeSaltPO> pageResult = paymentCodeSaltReadManage.findPaymentCodeSaltOfPage(po, page);
        
        List<PaymentCodeSaltDTO> list = PaymentCodeSaltConverter.toDTO(pageResult.getList());
        PageResult<PaymentCodeSaltDTO> result = new PageResult<PaymentCodeSaltDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PaymentCodeSaltDTO> findPaymentCodeSaltAll(PaymentCodeSaltDTO dto) {
		PaymentCodeSaltPO po = PaymentCodeSaltConverter.toPO(dto);
		List<PaymentCodeSaltPO> list = paymentCodeSaltReadManage.findPaymentCodeSaltAll(po);		
		return PaymentCodeSaltConverter.toDTO(list);
	}
	/**
	 * 根据uuId查询用户支付密钥
	 * @param paymentCodeUuid
	 * @return
	 */
	@Override
	public String findSaltByUUID(String paymentCodeUuid) {
		// TODO Auto-generated method stub
		return paymentCodeSaltReadManage.findSaltByUUID(paymentCodeUuid);
	}
}
	