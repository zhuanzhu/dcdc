package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitPictureWriteManage;
import com.egeo.components.product.dao.write.StandardUnitPictureWriteDAO;
import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardUnitPictureWriteManageImpl implements StandardUnitPictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitPictureWriteDAO standardUnitPictureWriteDAO;

	@Override
	public Long insertStandardUnitPictureWithTx(StandardUnitPicturePO po) {
		
		int i ;
		try {
				i = standardUnitPictureWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitPictureWithTx(StandardUnitPicturePO po) {
		int i;
		i = standardUnitPictureWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitPictureWithTx(StandardUnitPicturePO po) {
		int i;
		i = standardUnitPictureWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据suid删除su图片关系表
	 * @param merchantProdId
	 * @return
	 */
	@Override
	public int deleteByStandardUnitIdWithTx(Long merchantProdId) {
		StandardUnitPicturePO po = new StandardUnitPicturePO();
		po.setStandardUnitId(merchantProdId);
		return standardUnitPictureWriteDAO.deleteByPara(po);
	}
	/**
	 * 根据suid全部删除
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public int delByStandardUnitId(Long merchantProductId) {
		StandardUnitPicturePO standardUnitPicturePO = new StandardUnitPicturePO();
		standardUnitPicturePO.setStandardUnitId(merchantProductId);
		return standardUnitPictureWriteDAO.deleteByPara(standardUnitPicturePO);
	}

	@Override
	public void saveStandardUnitPicture(List<StandardUnitPicturePO> standardUnitPicturePOList) {
		try{
		standardUnitPictureWriteDAO.saveStandardUnitPicture(standardUnitPicturePOList);
	}catch (Exception e){
		logger.error("StandardUnitPicture保存失败"+e.getMessage());

	}
	}
}
	