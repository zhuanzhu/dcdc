package com.egeo.components.finance.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.write.AdjustReasonWriteDAO;
import com.egeo.components.finance.dao.write.ReasonCompanyWriteDAO;
import com.egeo.components.finance.manage.write.AdjustReasonWriteManage;
import com.egeo.components.finance.po.AdjustReasonPO;
import com.egeo.components.finance.po.ReasonCompanyPO;

@Service
public class AdjustReasonWriteManageImpl implements AdjustReasonWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdjustReasonWriteDAO adjustReasonWriteDAO;
	
	@Autowired
	private ReasonCompanyWriteDAO reasonCompanyWriteDAO;
	
	@Override
	public Long insertAdjustReasonWithTx(AdjustReasonPO po) {
		
		int i ;
		try {
				i = adjustReasonWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAdjustReasonWithTx(AdjustReasonPO po) {
		int i;
		i = adjustReasonWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAdjustReasonWithTx(AdjustReasonPO po) {
		int i;
		i = adjustReasonWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Long createAdjustReasonWithTx(AdjustReasonPO po,List<Long> cIds) {
		adjustReasonWriteDAO.insert(po);
		//插入关系
		Long reasonId=po.getId();
		for(Long cId:cIds) {
			ReasonCompanyPO rc=new ReasonCompanyPO();
			rc.setReasonId(reasonId);
			rc.setCompanyId(cId);
			reasonCompanyWriteDAO.insert(rc);
		}
		return reasonId;
	}

	@Override
	public int editAdjustReasonWithTx(AdjustReasonPO po,List<Long> cIds) {
		//删除原有关系
		ReasonCompanyPO rcCond=new ReasonCompanyPO();
		Long reasonId=po.getId();
		rcCond.setReasonId(reasonId);
		reasonCompanyWriteDAO.deleteByPara(rcCond);
		//建立新关系
		for(Long cId:cIds) {
			ReasonCompanyPO rc=new ReasonCompanyPO();
			rc.setReasonId(reasonId);
			rc.setCompanyId(cId);
			reasonCompanyWriteDAO.insert(rc);
		}
		//更新原因
		return adjustReasonWriteDAO.update(po);
	}	
}
	