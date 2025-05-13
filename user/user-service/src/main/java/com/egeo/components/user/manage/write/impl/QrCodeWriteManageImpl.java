package com.egeo.components.user.manage.write.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.QrCodeWriteManage;
import com.egeo.components.user.dao.write.QrCodeWriteDAO;
import com.egeo.components.user.po.QrCodePO;
import com.egeo.exception.BusinessException;

@Service
public class QrCodeWriteManageImpl implements QrCodeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QrCodeWriteDAO qrCodeWriteDAO;

	@Override
	public Long insertQrCodeWithTx(QrCodePO po) {
		
		int i ;
		try {
				i = qrCodeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateQrCodeWithTx(QrCodePO po) {
		int i;
		i = qrCodeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteQrCodeWithTx(QrCodePO po) {
		int i;
		i = qrCodeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Long updateRdidByChannelIdCampaignId(Long channelId, Long campaignId) {
		Random random = new Random();
		Long rdid = (long) random.nextInt(999999);
		QrCodePO po  = new QrCodePO();
		po.setCampaignId(campaignId);
		po.setChannelId(channelId);
		po.setRdid(rdid+"");
		int i;
		i = qrCodeWriteDAO.updateByccId(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");		
		return rdid;
	}

	@Override
	public Integer insertQrCodeListWithTx(List<QrCodePO> poList) {

		int i ;
		try {
			i = qrCodeWriteDAO.insertList(poList);
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return i;
	}

	@Override
	public Integer updateQrCodeByCouponUnitIds(List<Long> couponUnitIds, Long channelActivityId, Long channelId) {
		int i=qrCodeWriteDAO.updateQrCodeByCouponUnitIds(couponUnitIds,channelActivityId,channelId);
		return i;
	}
}
	