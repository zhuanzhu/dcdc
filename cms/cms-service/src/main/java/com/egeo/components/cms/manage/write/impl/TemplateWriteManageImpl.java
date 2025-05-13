package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.TemplateWriteManage;
import com.egeo.components.cms.dao.write.ElementWriteDAO;
import com.egeo.components.cms.dao.write.TemplateWriteDAO;
import com.egeo.components.cms.po.ElementPO;
import com.egeo.components.cms.po.TemplatePO;
import com.egeo.exception.BusinessException;

@Service
public class TemplateWriteManageImpl implements TemplateWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TemplateWriteDAO templateWriteDAO;
	
	@Autowired
	private ElementWriteDAO elementWriteDAO;

	@Override
	public Long insertTemplateWithTx(TemplatePO po) {
		
		int i ;
		try {
				i = templateWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateTemplateWithTx(TemplatePO po) {
		int i;
		i = templateWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteTemplateWithTx(TemplatePO po) {
		int i;
		i = templateWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public boolean useTemplateWithTx(Long platformId, Long templateId, Integer clientType, Integer type, Integer companyType) {
		//将与指定template客户端类型相同的所有模板停用(添加platformId判断)
		templateWriteDAO.disableTmplsByClientTypeAndType(platformId,clientType,type,companyType);
		templateWriteDAO.useTemplate(templateId);
		return true;
	}

	@Override
	public Long createTemplateWithTx(TemplatePO po, List<Long> eleIds) {
		//新建模板
		templateWriteDAO.insert(po);
		Long tmplId=po.getId();
		//更改组件所属模板
		for(Long eleId:eleIds) {
			ElementPO elePO=new ElementPO();
			elePO.setId(eleId);
			elePO.setTemplateId(tmplId);
			elementWriteDAO.update(elePO);
		}
		return tmplId;
	}

	@Override
	public boolean editTemplateWithTx(TemplatePO po, List<Long> eleIds) {
		templateWriteDAO.update(po);
		for(int i=0;i<eleIds.size();i++) {
			Long ele=eleIds.get(i);
			ElementPO elePO=new ElementPO();
			elePO.setId(ele);
			elePO.setTemplateId(po.getId());
			elePO.setSort(i);
			elementWriteDAO.update(elePO);
		}
		return true;
	}	
}
	