package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantPictureWriteManage;
import com.egeo.components.product.dao.write.MerchantPictureWriteDAO;
import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class MerchantPictureWriteManageImpl implements MerchantPictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantPictureWriteDAO merchantPictureWriteDAO;

	@Override
	public Long insertMerchantPictureWithTx(MerchantPicturePO po) {
		
		int i ;
		try {
				i = merchantPictureWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantPictureWithTx(MerchantPicturePO po) {
		int i;
		i = merchantPictureWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantPictureWithTx(MerchantPicturePO po) {
		int i;
		i = merchantPictureWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveMerchantPicture(List<MerchantPicturePO> merchantPicturePOList) {
		try{
		merchantPictureWriteDAO.saveMerchantPicture( merchantPicturePOList);
		}catch (Exception e){
			logger.error("saveMerchantPicture失败,e:"+e.getMessage());
		}
	}
}
	