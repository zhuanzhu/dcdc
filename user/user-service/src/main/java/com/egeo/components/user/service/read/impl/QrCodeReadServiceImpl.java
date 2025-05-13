package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.QrCodeReadService;
import com.egeo.components.user.converter.QrCodeConverter;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.manage.read.ChannelActivityReadManage;
import com.egeo.components.user.manage.read.QrCodeReadManage;
import com.egeo.components.user.manage.write.ChannelActivityWriteManage;
import com.egeo.components.user.manage.write.QrCodeWriteManage;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.components.user.po.QrCodePO;

import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("qrCodeReadService")
public class QrCodeReadServiceImpl implements QrCodeReadService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QrCodeReadManage qrCodeReadManage;

	@Autowired
	private QrCodeWriteManage qrCodeWriteManage;

	@Autowired
	private ChannelActivityReadManage channelActivityReadManage;

	@Autowired
	private ChannelActivityWriteManage channelActivityWriteManage;

	@Override
	public QrCodeDTO findQrCodeById(QrCodeDTO dto) {
		QrCodePO po = QrCodeConverter.toPO(dto);
		QrCodePO list = qrCodeReadManage.findQrCodeById(po);
		return QrCodeConverter.toDTO(list);
	}

	@Override
	public PageResult<QrCodeDTO> findQrCodeOfPage(QrCodeDTO dto, Pagination page) {
		QrCodePO po = QrCodeConverter.toPO(dto);
		PageResult<QrCodePO> pageResult = qrCodeReadManage.findQrCodeOfPage(po, page);

		List<QrCodeDTO> list = QrCodeConverter.toDTO(pageResult.getList());
		PageResult<QrCodeDTO> result = new PageResult<QrCodeDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<QrCodeDTO> findQrCodeAll(QrCodeDTO dto) {
		QrCodePO po = QrCodeConverter.toPO(dto);
		List<QrCodePO> list = qrCodeReadManage.findQrCodeAll(po);
		return QrCodeConverter.toDTO(list);
	}

	@Override
	public String findRdidByChannelIdCampaignId(Integer type, Integer typeId, Long storeId, String storeName,
			Long channelId, String campaignCode,Long platformId) {
		// 根据活动短码查询活动id
		Long campaignId = null;
		ChannelActivityPO channelActivityPO = channelActivityReadManage.findByShortCode(campaignCode);
		if (StringUtils.isNotEmpty(channelActivityPO)) {
			campaignId = channelActivityPO.getId();
		} else {
			StringBuffer channelActivityName = new StringBuffer();
			switch (type) {
			case 1:
				channelActivityName.append("门店");
				break;
			case 2:
				channelActivityName.append("商品");
				break;
			case 3:
				channelActivityName.append("优惠卷");
				break;
			case 4:
				channelActivityName.append("商品购物车");
				break;
			case 5:
				channelActivityName.append("商品订单");
				break;
			default:
				break;
			}
			channelActivityName.append("Id：");
			channelActivityName.append(typeId);
			channelActivityName.append("门店：");
			channelActivityName.append(storeName);
			ChannelActivityPO channelActivityPO2 = new ChannelActivityPO();
			channelActivityPO2.setName(channelActivityName.toString());
			channelActivityPO2.setPlatformId(platformId);
			channelActivityPO2.setChannelId(channelId);
			channelActivityPO2.setShortCode(campaignCode);
			campaignId = channelActivityWriteManage.insertWithTx(channelActivityPO2);

		}
		String rdId = null;
		QrCodePO codePO = new QrCodePO();
		codePO.setChannelId(channelId);
		codePO.setCampaignId(campaignId);
		List<QrCodePO> qrCodeList = qrCodeReadManage.findQrCodeAll(codePO);
		if (StringUtils.isEmpty(qrCodeList)) {
			QrCodePO qrCodePO = new QrCodePO();
			qrCodePO.setChannelId(channelId);
			qrCodePO.setCampaignId(campaignId);
			// 加上两位随机数
			Random random = new Random();
			int rdid = random.nextInt(999999);
			qrCodePO.setRdid(Long.valueOf(rdid)+"");
			qrCodeWriteManage.insertQrCodeWithTx(qrCodePO);
			rdId = Long.valueOf(rdid)+"";
		} else {
			if (qrCodeList.size() > 1) {
				throw new BusinessException("渠道id：" + channelId + "渠道活动id" + campaignId + "rdId异常");
			}
			rdId = qrCodeList.get(0).getRdid();
		}
		return rdId;
	}

	@Override
	public List<QrCodeDTO> findQrCodeListByCouponUnitIds(List<Long> couponUnitIds) {
		return QrCodeConverter.conditionToDTO(qrCodeReadManage.findQrCodeListByCouponUnitIds(couponUnitIds));
	}

	@Override
	public QrCodeDTO findQrCodeByCouponUnitId(Long id) {
		return QrCodeConverter.conditionToDTO(qrCodeReadManage.findQrCodeByCouponUnitId(id));
	}
}
