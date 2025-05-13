package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.manage.read.PictureReadManage;
import com.egeo.components.product.condition.PictureCondition;
import com.egeo.components.product.converter.PictureConverter;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.po.PicturePO;


@Service("pictureReadService")
public class PictureReadServiceImpl  implements PictureReadService {
	@Autowired
	private PictureReadManage pictureReadManage;

        @Override
        public PictureDTO findById(PictureDTO dto) {
            PicturePO picturePO = pictureReadManage.findById(PictureConverter.toPO(dto));
            return PictureConverter.toDTO(picturePO);
        }

        @Override
        public List<PictureDTO> findAll(PictureDTO dto) {
            List<PicturePO> list = pictureReadManage.findAll(PictureConverter.toPO(dto));
            return PictureConverter.toDTO(list);
        }
        /**
         * 根据产品id查询产品图片信息
         * @param pictureId
         * @return
         */
		@Override
		public List<PictureDTO> pictureByProductId(Long pictureId) {
			List<PicturePO> list = pictureReadManage.pictureByProductId(pictureId);
			return PictureConverter.toDTO(list);
		}
		/**
         * su草稿id查询su草稿图片信息
         * @param id
         * @return
         */
		@Override
		public List<PictureDTO> findMPictureByMerchantProdId(Long merchantProdId) {
			List<PictureDTO> list = new ArrayList<>();
			List<PictureCondition> pictureConditionList = pictureReadManage.findMPictureByMerchantProdId(merchantProdId);
			for (PictureCondition pictureCondition : pictureConditionList) {
				PictureDTO pictureDTO = PictureConverter.toDTO(pictureCondition);
				pictureDTO.setMerchantProdPictureId(pictureCondition.getMerchantProdPictureId());
				pictureDTO.setMerchantPictureId(pictureCondition.getMerchantPictureId());
				list.add(pictureDTO);
			}
			return list;
		}
		/**
		 * 根据spuid查询spu图片
		 * @param id
		 * @return
		 */
		@Override
		public List<PictureDTO> findByStandardProductUnitId(Long standardProductUnitId) {
			List<PictureDTO> list = new ArrayList<>();
			List<PictureCondition> pictureConditionList = pictureReadManage.findByStandardProductUnitId(standardProductUnitId);
			for (PictureCondition pictureCondition : pictureConditionList) {
				PictureDTO pictureDTO = PictureConverter.toDTO(pictureCondition);
				pictureDTO.setMerchantProdPictureId(pictureCondition.getMerchantProdPictureId());
				list.add(pictureDTO);
			}
			return list;
		}
		/**
		 * 根据suid查询su轮播图信息
		 * @param standardProductUnitId
		 * @param req
		 * @return
		 */
		@Override
		public List<String> findByStandardUnitId(Long standardUnitId) {
			
			return pictureReadManage.findByStandardUnitId(standardUnitId);
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
			return pictureReadManage.findPictureByStandardUnitId(standardUnitId);
		}
		/**
		 * 根据suid查询所有su图片信息
		 * @param merchantProductId
		 * @return
		 */
		@Override
		public List<PictureDTO> picturesByStandardUnitId(Long standardUnitId) {
			List<PictureDTO> list = new ArrayList<>();
			List<PictureCondition> pictureConditionList = pictureReadManage.picturesByStandardUnitId(standardUnitId);
			for (PictureCondition pictureCondition : pictureConditionList) {
				PictureDTO pictureDTO = PictureConverter.toDTO(pictureCondition);
				pictureDTO.setMerchantProdPictureId(pictureCondition.getMerchantProdPictureId());
				pictureDTO.setMerchantPictureId(pictureCondition.getMerchantPictureId());
				list.add(pictureDTO);
			}
			return list;
		}

		@Override
		public List<String> findByStandardUnitIdAndType(Long standardUnitId, int type) {
			return pictureReadManage.findByStandardUnitIdAndType(standardUnitId, type);
		}

	@Override
	public Long findLastId() {
		return pictureReadManage.findLastId();
	}
}
	