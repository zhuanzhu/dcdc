package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.ImagetextGroupCompanyWriteManage;
import com.egeo.components.cms.dao.write.ImagetextGroupCompanyWriteDAO;
import com.egeo.components.cms.po.ImagetextGroupCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class ImagetextGroupCompanyWriteManageImpl implements ImagetextGroupCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ImagetextGroupCompanyWriteDAO imagetextGroupCompanyWriteDAO;

	@Override
	public Long insertImagetextGroupCompanyWithTx(ImagetextGroupCompanyPO po) {
		
		int i ;
		try {
				i = imagetextGroupCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateImagetextGroupCompanyWithTx(ImagetextGroupCompanyPO po) {
		int i;
		i = imagetextGroupCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteImagetextGroupCompanyWithTx(ImagetextGroupCompanyPO po) {
		int i;
		i = imagetextGroupCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	