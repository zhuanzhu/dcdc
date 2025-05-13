package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.PictureReadManage;
import com.egeo.components.product.condition.PictureCondition;
import com.egeo.components.product.dao.read.PictureReadDAO;
import com.egeo.components.product.po.PicturePO;

@Service
public class PictureReadManageImpl implements PictureReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PictureReadDAO pictureReadDAO;
	
        @Override
        public PicturePO findById(PicturePO po) {
            
            return pictureReadDAO.findById(po);
        }

        @Override
        public List<PicturePO> findAll(PicturePO po) {
            
            return pictureReadDAO.findAll(po,null);
        }
        /**
         * 根据产品id查询产品图片信息
         * @param pictureId
         * @return
         */
		@Override
		public List<PicturePO> pictureByProductId(Long pictureId) {
			
			return pictureReadDAO.pictureByProductId(pictureId);
		}
		/**
         * su草稿id查询su草稿图片信息
         * @param id
         * @return
         */
		@Override
		public List<PictureCondition> findMPictureByMerchantProdId(Long merchantProdId) {
			// TODO Auto-generated method stub
			return pictureReadDAO.findMPictureByMerchantProdId(merchantProdId);
		}
		/**
		 * 根据spuid查询spu图片
		 * @param id
		 * @return
		 */
		@Override
		public List<PictureCondition> findByStandardProductUnitId(Long standardProductUnitId) {
			// TODO Auto-generated method stub
			return pictureReadDAO.findByStandardProductUnitId(standardProductUnitId);
		}
		/**
		 * 根据spuid查询sku默认图片
		 * @param id
		 * @return
		 */
		@Override
		public String skuPicUrlByStandardProductUnitId(Long id) {
			// TODO Auto-generated method stub
			return pictureReadDAO.skuPicUrlByStandardProductUnitId(id);
		}
		/**
		 * 根据suid查询su轮播图信息
		 * @param standardProductUnitId
		 * @param req
		 * @return
		 */
		@Override
		public List<String> findByStandardUnitId(Long standardUnitId) {
			// TODO Auto-generated method stub
			return pictureReadDAO.findByStandardUnitId(standardUnitId);
		}
		/**
		 * 根据suid查询su封面图信息
		 * @param standardProductUnitId
		 * @param req
		 * @return
		 */
		@Override
		public String findPictureByStandardUnitId(Long standardUnitId) {
			// TODO Auto-generated method stub
			return pictureReadDAO.findPictureByStandardUnitId(standardUnitId);
		}
		/**
		 * 根据suid查询所有su图片信息
		 * @param merchantProductId
		 * @return
		 */
		@Override
		public List<PictureCondition> picturesByStandardUnitId(Long standardUnitId) {
			// TODO Auto-generated method stub
			return pictureReadDAO.picturesByStandardUnitId(standardUnitId);
		}

		@Override
		public List<String> findByStandardUnitIdAndType(Long standardUnitId, int type) {
			// TODO Auto-generated method stub
			return pictureReadDAO.findByStandardUnitIdAndType(standardUnitId, type);
		}

	@Override
	public Long findLastId() {
		return pictureReadDAO.findLastId();
	}

}
	