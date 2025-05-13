package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.LinkableParamWriteManage;
import com.egeo.components.cms.dao.write.LinkableParamWriteDAO;
import com.egeo.components.cms.po.LinkableParamPO;
import com.egeo.exception.BusinessException;

@Service
public class LinkableParamWriteManageImpl implements LinkableParamWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LinkableParamWriteDAO linkableParamWriteDAO;

	@Override
	public Long insertLinkableParamWithTx(LinkableParamPO po) {
		
		int i ;
		try {
				i = linkableParamWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLinkableParamWithTx(LinkableParamPO po) {
		int i;
		i = linkableParamWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLinkableParamWithTx(LinkableParamPO po) {
		int i;
		i = linkableParamWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int deleteLinkableParamByParamWithTx(LinkableParamPO po) {
		int i;
		i = linkableParamWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	