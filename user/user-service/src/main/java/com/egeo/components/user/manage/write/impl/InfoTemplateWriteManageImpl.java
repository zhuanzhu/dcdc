package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.InfoTemplateWriteManage;
import com.egeo.components.user.dao.read.InfoTemplateReadDAO;
import com.egeo.components.user.dao.write.InfoTemplateSendWayWriteDAO;
import com.egeo.components.user.dao.write.InfoTemplateWriteDAO;
import com.egeo.components.user.po.InfoTemplatePO;
import com.egeo.components.user.po.InfoTemplateSendWayPO;
import com.egeo.exception.BusinessException;

@Service
public class InfoTemplateWriteManageImpl implements InfoTemplateWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoTemplateWriteDAO infoTemplateWriteDAO;
	
	@Autowired
	private InfoTemplateReadDAO infoTemplateReadDAO;
	
	@Autowired
	private InfoTemplateSendWayWriteDAO infoTemplateSendWayWriteDAO;

	@Override
	public Long insertInfoTemplateWithTx(InfoTemplatePO po) {
		
		int i ;
		try {
				i = infoTemplateWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateInfoTemplateWithTx(InfoTemplatePO po,List<Long> sendWayIds) {
		int i;
		i = infoTemplateWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		// 删除消息与发送方式的关系
		InfoTemplateSendWayPO templateSendWayPO = new InfoTemplateSendWayPO();
		templateSendWayPO.setInfoTemplateId(po.getId());
		templateSendWayPO.setPlatformId(po.getPlatformId());
		infoTemplateSendWayWriteDAO.deleteByPara(templateSendWayPO);
		
		for (Long sendWayId : sendWayIds) {
			InfoTemplateSendWayPO templateSendWayPO2 = new InfoTemplateSendWayPO();
			templateSendWayPO2.setInfoTemplateId(po.getId());
			templateSendWayPO2.setSendWayId(sendWayId);
			templateSendWayPO2.setPlatformId(po.getPlatformId());
			infoTemplateSendWayWriteDAO.insert(templateSendWayPO2);
		}
		return i;
	}

	@Override
	public int deleteInfoTemplateWithTx(InfoTemplatePO po) {
		int i;
		i = infoTemplateWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	
	@Override
	public int isStartByIdWithTx(Long infoTemplateId) {
		InfoTemplatePO infoTemplatePO = new InfoTemplatePO();
		infoTemplatePO.setId(infoTemplateId);
		InfoTemplatePO infoTemplatePO2 = infoTemplateReadDAO.findById(infoTemplatePO);
		int i;
		InfoTemplatePO po = new InfoTemplatePO();
		po.setId(infoTemplateId);
		if(infoTemplatePO2.getIsStart() == 0){
			po.setIsStart(1);
		}else{
			po.setIsStart(0);
		}
		i = infoTemplateWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}	
}
	