package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.VideoWriteManage;
import com.egeo.components.product.dao.write.VideoWriteDAO;
import com.egeo.components.product.po.VideoPO;
import com.egeo.exception.BusinessException;

@Service
public class VideoWriteManageImpl implements VideoWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VideoWriteDAO videoWriteDAO;

	@Override
	public Long insertVideoWithTx(VideoPO po) {
		
		int i ;
		try {
				i = videoWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateVideoWithTx(VideoPO po) {
		int i;
		i = videoWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteVideoWithTx(VideoPO po) {
		int i;
		i = videoWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	