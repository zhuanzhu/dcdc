package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.QrCodeManage;
import com.egeo.components.user.facade.QrCodeFacade;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("qrCode")
public class QrCodeManageImpl implements QrCodeManage{

	
	@Resource(name="qrCodeFacade")
	private QrCodeFacade qrCodeFacade;

	@Override
	public QrCodeDTO findQrCodeById(QrCodeDTO dto) {
		return qrCodeFacade.findQrCodeById(dto);
	}

	@Override
	public PageResult<QrCodeDTO> findQrCodeOfPage(QrCodeDTO dto, Pagination page) {
		return qrCodeFacade.findQrCodeOfPage(dto, page);
	}

	@Override
	public List<QrCodeDTO> findQrCodeAll(QrCodeDTO dto) {
		return qrCodeFacade.findQrCodeAll(dto);
	}

	@Override
	public Long insertQrCodeWithTx(QrCodeDTO dto) {
		return qrCodeFacade.insertQrCodeWithTx(dto);
	}

	@Override
	public int updateQrCodeWithTx(QrCodeDTO dto) {
		return qrCodeFacade.updateQrCodeWithTx(dto);
	}

	@Override
	public int deleteQrCodeWithTx(QrCodeDTO dto) {
		return qrCodeFacade.deleteQrCodeWithTx(dto);
	}

	@Override
	public String findRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,Long platformId) {
		return qrCodeFacade.findRdidByChannelIdCampaignId(type,typeId,storeId,channelId, campaignCode,platformId);
	}

	@Override
	public Long updateRdidByChannelIdCampaignId(
			Integer type,Integer typeId,Long storeId, Long channelId, String campaignCode,Long platformId) {
		return qrCodeFacade.updateRdidByChannelIdCampaignId(
				type,typeId,storeId,channelId, campaignCode,platformId);
	}

	@Override
	public Boolean qrCodeVerifyValid(QrCodeDTO dto) {
		
		return qrCodeFacade.qrCodeVerifyValid(dto);
	}


}
	