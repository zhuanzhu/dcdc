package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.QrCodeConverter;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.manage.read.ChannelActivityReadManage;
import com.egeo.components.user.manage.write.QrCodeWriteManage;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.components.user.po.QrCodePO;
import com.egeo.components.user.service.write.QrCodeWriteService;

@Service("qrCodeWriteService")
public class QrCodeWriteServiceImpl implements QrCodeWriteService {
	@Autowired
	private QrCodeWriteManage qrCodeWriteManage;
	
	@Autowired
	private ChannelActivityReadManage channelActivityReadManage;

	@Override
	public Long insertQrCodeWithTx(QrCodeDTO dto) {
		QrCodePO po = QrCodeConverter.toPO(dto);
		Long rt = qrCodeWriteManage.insertQrCodeWithTx(po);		
		return rt;
	}

	@Override
	public int updateQrCodeWithTx(QrCodeDTO dto) {
		QrCodePO po = QrCodeConverter.toPO(dto);
		int rt = qrCodeWriteManage.updateQrCodeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteQrCodeWithTx(QrCodeDTO dto) {
		QrCodePO po = QrCodeConverter.toPO(dto);
		int rt = qrCodeWriteManage.deleteQrCodeWithTx(po);		
		return rt;
	}

	@Override
	public Long updateRdidByChannelIdCampaignId(Long channelId, String campaignCode) {
		ChannelActivityPO channelActivityPO = channelActivityReadManage.findByShortCode(campaignCode);
		return qrCodeWriteManage.updateRdidByChannelIdCampaignId(channelId, channelActivityPO.getId());
	}

	@Override
	public Integer insertQrCodeListWithTx(List<QrCodeDTO> qrCodeDTOList) {
		List<QrCodePO> poList = QrCodeConverter.toPO(qrCodeDTOList);
		Integer rt = qrCodeWriteManage.insertQrCodeListWithTx(poList);
		return rt;
	}

	@Override
	public void updateQrCodeByCouponUnitIds(List<Long> couponUnitIds, Long channelActivityId, Long channelId) {
		Integer ret=qrCodeWriteManage.updateQrCodeByCouponUnitIds(couponUnitIds,channelActivityId,channelId);
	}
}
	