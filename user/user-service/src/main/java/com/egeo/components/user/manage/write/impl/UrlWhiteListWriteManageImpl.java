package com.egeo.components.user.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UrlWhiteListWriteManage;
import com.egeo.components.user.dao.write.UrlWhiteListWriteDAO;
import com.egeo.components.user.po.UrlWhiteListPO;
import com.egeo.exception.BusinessException;

@Service
public class UrlWhiteListWriteManageImpl implements UrlWhiteListWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlWhiteListWriteDAO urlWhiteListWriteDAO;

	@Override
	public Long insertUrlWhiteListWithTx(UrlWhiteListPO po, List<Long> platformIdList) {
		
		int i  = 0;
		try {	
			for (Long platformId : platformIdList) {
				po.setPlatformId(platformId);
				i += urlWhiteListWriteDAO.insert(po);
			}
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateUrlWhiteListWithTx(UrlWhiteListPO po, List<Long> platformIdList) {
		int i = 0;
		UrlWhiteListPO delPara = new UrlWhiteListPO();
		delPara.setUrlId(po.getUrlId());
		i += urlWhiteListWriteDAO.deleteByPara(delPara);
		
		for (Long platformId : platformIdList) {
			po.setPlatformId(platformId);
			i += urlWhiteListWriteDAO.insert(po);
		}
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUrlWhiteListWithTx(UrlWhiteListPO po) {
		int i;
		i = urlWhiteListWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	