package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductPictureWriteManage;
import com.egeo.components.product.dao.write.ProductPictureWriteDAO;
import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class ProductPictureWriteManageImpl implements ProductPictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductPictureWriteDAO productPictureWriteDAO;

	@Override
	public Long insertProductPictureWithTx(ProductPicturePO po) {
		
		int i ;
		try {
				i = productPictureWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateProductPictureWithTx(ProductPicturePO po) {
		int i;
		i = productPictureWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteProductPictureWithTx(ProductPicturePO po) {
		int i;
		i = productPictureWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据产品id删除产品与图片的关系
	 */
	@Override
	public int deleteByProductIdWithTx(Long id) {
		ProductPicturePO productPicturePO = new ProductPicturePO();
		productPicturePO.setProductId(id);
		return productPictureWriteDAO.deleteByPara(productPicturePO);
	}

	@Override
	public void saveProductPicture(List<ProductPicturePO> productPicturePOList) {
		try {
			productPictureWriteDAO.saveProductPicture(productPicturePOList);
		}catch (Exception e){
			logger.error("saveProductPicture失败,e:"+e.getMessage());
		}
	}
}
	