package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.dao.write.BannerInstWriteDAO;
import com.egeo.components.cms.manage.write.BannerInstWriteManage;
import com.egeo.components.cms.po.BannerInstPO;
import com.egeo.exception.BusinessException;

@Service
public class BannerInstWriteManageImpl implements BannerInstWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BannerInstWriteDAO bannerInstWriteDAO;

	@Override
	public Long insertBannerInstWithTx(BannerInstPO po) {
		
		int i ;
		try {
				i = bannerInstWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateBannerInstWithTx(BannerInstPO po) {
		int i;
		i = bannerInstWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteBannerInstWithTx(BannerInstPO po) {
		int i;
		i = bannerInstWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	