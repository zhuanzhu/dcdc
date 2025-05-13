package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface QrCodeManage {

	public QrCodeDTO findQrCodeById(QrCodeDTO dto);	

	public PageResult<QrCodeDTO> findQrCodeOfPage(QrCodeDTO dto,Pagination page);

	public List<QrCodeDTO> findQrCodeAll(QrCodeDTO dto);

	Long insertQrCodeWithTx(QrCodeDTO dto);

	int updateQrCodeWithTx(QrCodeDTO dto);

	int deleteQrCodeWithTx(QrCodeDTO dto);
	/**
	 * 根据渠道id和活动id查询rdid
	 * @param channelId
	 * @param campaignId
	 * @return
	 */
	public String findRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,Long platformId);

	public Long updateRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,Long platformId);
	/**
	 * 验证参数的有效性
	 * @param dto
	 * @return
	 */
	public Boolean qrCodeVerifyValid(QrCodeDTO dto);
}
	