package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.BannerWriteDAO;
import com.egeo.components.promotion.manage.write.BannerWriteManage;
import com.egeo.components.promotion.po.BannerPO;
import com.egeo.exception.BusinessException;

@Service
public class BannerWriteManageImpl implements BannerWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BannerWriteDAO bannerWriteDAO;

	@Override
	public Long insertBannerWithTx(BannerPO po) {
		
		int i ;
		try {
				i = bannerWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateBannerWithTx(BannerPO po) {
		int i;
		i = bannerWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteBannerWithTx(BannerPO po) {
		int i;
		i = bannerWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	