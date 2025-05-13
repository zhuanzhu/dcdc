package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProdPictureWriteManage;
import com.egeo.components.product.dao.write.MerchantProdPictureWriteDAO;
import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProdPictureWriteManageImpl implements MerchantProdPictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdPictureWriteDAO merchantProdPictureWriteDAO;

	@Override
	public Long insertMerchantProdPictureWithTx(MerchantProdPicturePO po) {
		
		int i ;
		try {
				i = merchantProdPictureWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProdPictureWithTx(MerchantProdPicturePO po) {
		int i;
		i = merchantProdPictureWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProdPictureWithTx(MerchantProdPicturePO po) {
		int i;
		i = merchantProdPictureWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据su草稿图片关系id集合批量删除su草稿图片关系
	 * @param merchantProdPictureIds
	 * @return
	 */
	@Override
	public int deleteBymerchantProdPictureIds(List<Long> merchantProdPictureIds) {
		// TODO Auto-generated method stub
		return merchantProdPictureWriteDAO.deleteBymerchantProdPictureIds(merchantProdPictureIds);
	}
	/**
	 * 根据su商品id删除su商品图片信息
	 */
	@Override
	public int delByMerchantProductId(Long merchantProductId) {
		MerchantProdPicturePO merchantProdPicturePO = new MerchantProdPicturePO();
		merchantProdPicturePO.setMerchantProdId(merchantProductId);
		return merchantProdPictureWriteDAO.deleteByPara(merchantProdPicturePO);
	}

	@Override
	public void saveMerchantProdPicture(List<MerchantProdPicturePO> merchantProdPicturePOList) {
		try{
		merchantProdPictureWriteDAO. saveMerchantProdPicture(merchantProdPicturePOList);
		}catch (Exception e){
			logger.error("saveMerchantProdPicture失败,e:"+e.getMessage());
		}
	}
}
	