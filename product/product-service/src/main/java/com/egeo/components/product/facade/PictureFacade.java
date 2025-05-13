package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.service.write.PictureWriteService;
import com.egeo.components.product.dto.PictureDTO;


@Component
public class PictureFacade {
	
	@Resource
	private PictureReadService pictureReadService;
	
	@Resource
        private PictureWriteService pictureWriteService;

        public Long savePicturect(PictureDTO dto) {
            
            return pictureWriteService.savePictureWithTx(dto);
        }

        public PictureDTO findById(PictureDTO dto) {
            
            return pictureReadService.findById(dto);
        }

        public List<PictureDTO> findAll(PictureDTO dto) {
            
            return pictureReadService.findAll(dto);
        }

        public String delete(PictureDTO dto) {
            
            return pictureWriteService.deleteWithTx(dto);
        }

        public String deleteByIds(String ids) {
            
            return pictureWriteService.deleteByIdsWithTx(ids);
        }

        public String deletePicture(String picture) {
            
            return pictureWriteService.deletePictureWithTx(picture);
        }
        /**
         * su草稿id查询su草稿图片信息
         * @param id
         * @return
         */
		public List<PictureDTO> findMPictureByMerchantProdId(Long merchantProdId) {
			// TODO Auto-generated method stub
			return pictureReadService.findMPictureByMerchantProdId(merchantProdId);
		}
		/**
		 * 根据spuid查询spu图片
		 * @param id
		 * @return
		 */
		public List<PictureDTO> findByStandardProductUnitId(Long standardProductUnitId) {
			// TODO Auto-generated method stub
			return pictureReadService.findByStandardProductUnitId(standardProductUnitId);
		}
		/**
		 * 根据spuid查询su轮播图信息
		 * @param standardProductUnitId
		 * @param req
		 * @return
		 */
		public List<String> findByStandardUnitId(Long standardUnitId) {
			// TODO Auto-generated method stub
			return pictureReadService.findByStandardUnitId(standardUnitId);
		}
		/**
		 * 根据suid查询su封面图信息
		 * @param standardProductUnitId
		 * @param req
		 * @return
		 */
		public String findPictureByStandardUnitId(Long standardUnitId) {
			// TODO Auto-generated method stub
			return pictureReadService.findPictureByStandardUnitId(standardUnitId);
		}
		/**
		 * 根据su商品id和类型查询su图片信息
		 * @param standardUnitId
		 * @param i
		 * @return
		 */
		public List<String> findByStandardUnitIdAndType(Long standardUnitId, int type) {
			// TODO Auto-generated method stub
			return pictureReadService.findByStandardUnitIdAndType(standardUnitId, type);
		}
	


}
	