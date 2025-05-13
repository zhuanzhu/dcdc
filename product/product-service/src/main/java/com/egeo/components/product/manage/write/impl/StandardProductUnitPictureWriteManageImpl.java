package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardProductUnitPictureWriteManage;
import com.egeo.components.product.dao.write.StandardProductUnitPictureWriteDAO;
import com.egeo.components.product.po.StandardProductUnitPicturePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardProductUnitPictureWriteManageImpl implements StandardProductUnitPictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitPictureWriteDAO standardProductUnitPictureWriteDAO;

	@Override
	public Long insertStandardProductUnitPictureWithTx(StandardProductUnitPicturePO po) {
		
		int i ;
		try {
				i = standardProductUnitPictureWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardProductUnitPictureWithTx(StandardProductUnitPicturePO po) {
		int i;
		i = standardProductUnitPictureWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardProductUnitPictureWithTx(StandardProductUnitPicturePO po) {
		int i;
		i = standardProductUnitPictureWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据spuid删除spu图片关系
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByStandardProductUnitId(Long id) {
		StandardProductUnitPicturePO standardProductUnitPicturePO = new StandardProductUnitPicturePO();
		standardProductUnitPicturePO.setStandardProductUnitId(id);
		return standardProductUnitPictureWriteDAO.deleteByPara(standardProductUnitPicturePO);
	}

	@Override
	public void saveStandardProductUnitPicture(List<StandardProductUnitPicturePO> standardProductUnitPicturePOList) {
		try{
		standardProductUnitPictureWriteDAO.saveStandardProductUnitPicture(standardProductUnitPicturePOList);
		}catch (Exception e){
			logger.error("saveStandardProductUnitPicture失败,e:"+e.getMessage());
		}
	}
}
	