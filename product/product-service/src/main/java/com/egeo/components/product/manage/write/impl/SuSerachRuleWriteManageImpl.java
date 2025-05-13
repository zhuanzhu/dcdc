package com.egeo.components.product.manage.write.impl;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SuSerachRuleWriteManage;
import com.egeo.components.product.dao.write.SuSerachRuleWriteDAO;
import com.egeo.components.product.po.SuSerachRulePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class SuSerachRuleWriteManageImpl implements SuSerachRuleWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuSerachRuleWriteDAO suSerachRuleWriteDAO;

	@Override
	public Long insertSuSerachRuleWithTx(SuSerachRulePO po) {
		
		int i ;
		try {
				i = suSerachRuleWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSuSerachRuleWithTx(SuSerachRulePO po) {
		int i;
		i = suSerachRuleWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSuSerachRuleWithTx(SuSerachRulePO po) {
		int i;
		i = suSerachRuleWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateSuSerachRuleBySuIdWithTx(SuSerachRulePO suSerachRulePO) {
		return suSerachRuleWriteDAO.updateSuSerachRuleBySuIdWithTx(suSerachRulePO);
	}

	@Override
	public void saveSuSerachRulet(List<SuSerachRulePO> suSerachRuleList) {
		suSerachRuleWriteDAO.saveSuSerachRulet( suSerachRuleList);
	}

	@Override
	public void addSuSerachRuleList(List<SuSerachRulePO> addList) {
		try {
			suSerachRuleWriteDAO.addSuSerachRuleList(addList);
		}catch (Exception e){
			logger.error("添加失败"+e.getMessage());

		}
	}

	@Override
	public void updateSuSerachRuleList(List<SuSerachRulePO> updateList) {
		try {
			suSerachRuleWriteDAO.updateSuSerachRuleList(updateList);
		}catch (Exception e){
			logger.error("更新失败:"+ JSONObject.toJSONString(updateList)+e.getMessage());
		}
	}
}
	