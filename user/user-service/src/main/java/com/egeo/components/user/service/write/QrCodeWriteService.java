package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.QrCodeDTO;

import java.util.List;


public interface QrCodeWriteService {

	public Long insertQrCodeWithTx(QrCodeDTO dto);

	public int updateQrCodeWithTx(QrCodeDTO dto);

	public int deleteQrCodeWithTx(QrCodeDTO dto);

	public Long updateRdidByChannelIdCampaignId(Long channelId, String campaignCode);

    Integer insertQrCodeListWithTx(List<QrCodeDTO> qrCodeDTOList);

    void updateQrCodeByCouponUnitIds(List<Long> couponUnitIds, Long channelActivityId, Long channelId);
}
	