package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.QrCodePO;

import java.util.List;


public interface QrCodeWriteManage {

	Long insertQrCodeWithTx(QrCodePO po);

	int updateQrCodeWithTx(QrCodePO po);

	int deleteQrCodeWithTx(QrCodePO po);

	Long updateRdidByChannelIdCampaignId(Long channelId, Long campaignId);

    Integer insertQrCodeListWithTx(List<QrCodePO> poList);

    Integer updateQrCodeByCouponUnitIds(List<Long> couponUnitIds, Long channelActivityId, Long channelId);
}
	