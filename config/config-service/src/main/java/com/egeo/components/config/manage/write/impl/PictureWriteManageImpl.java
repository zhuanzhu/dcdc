package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.PictureWriteDAO;
import com.egeo.components.config.manage.write.PictureWriteManage;
import com.egeo.components.config.po.PicturePO;
import com.egeo.exception.BusinessException;

@Service
public class PictureWriteManageImpl implements PictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PictureWriteDAO pictureWriteDAO;

	@Override
	public Long insertPictureWithTx(PicturePO po) {
		
		int i ;
		try {
				i = pictureWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePictureWithTx(PicturePO po) {
		int i;
		i = pictureWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePictureWithTx(PicturePO po) {
		int i;
		i = pictureWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	