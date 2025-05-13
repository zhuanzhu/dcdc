package com.egeo.components.user.service.read;

import java.util.List;

import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface QrCodeReadService {

	public QrCodeDTO findQrCodeById(QrCodeDTO dto);

	public PageResult<QrCodeDTO> findQrCodeOfPage(QrCodeDTO dto, Pagination page);

	public List<QrCodeDTO> findQrCodeAll(QrCodeDTO dto);

	/**
	 * 根据渠道id渠道活动id查询rdid
	 * 
	 * @param channelId
	 * @param id
	 * @return
	 */
	public String findRdidByChannelIdCampaignId(Integer type, Integer typeId, Long storeId, String storeName,
			Long channelId, String campaignCode, Long platformId);

    List<QrCodeDTO> findQrCodeListByCouponUnitIds(List<Long> couponUnitIds);

    QrCodeDTO findQrCodeByCouponUnitId(Long id);
}
