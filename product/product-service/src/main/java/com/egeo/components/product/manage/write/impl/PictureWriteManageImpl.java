package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.PictureWriteManage;
import com.egeo.components.product.dao.write.PictureWriteDAO;
import com.egeo.components.product.po.PicturePO;
import com.egeo.exception.BusinessException;

@Service
public class PictureWriteManageImpl implements PictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PictureWriteDAO pictureWriteDAO;
	
        @Override
        public Long savePictureWithTx(PicturePO po) {
            pictureWriteDAO.insert(po);
            if(po.getId() != null){
            	return po.getId();
            }else{
            	throw new BusinessException("新增产品图片失败");
            }
            
        }

        @Override
        public String deleteWithTx(PicturePO po) {
            
            return pictureWriteDAO.delete(po)+"";
        }

        @Override
        public String deleteByIds(String ids) {
            
            return pictureWriteDAO.deleteByIds(ids)+"";
        }
        /**
         * 根据suid和图片id集合删除所有没有查询出来的su图片关系
         * @param merchantProductId
         * @param pictureIds
         * @return
         */
		@Override
		public int delByStandardUnitIdPictureId(Long merchantProductId, List<Long> pictureIds) {
			// TODO Auto-generated method stub
			return pictureWriteDAO.delByStandardUnitIdPictureId(merchantProductId, pictureIds);
		}

    @Override
    public void savePicture(List<PicturePO> picturePOList) {
		    try{
        pictureWriteDAO.savePicture(picturePOList);
            }catch (Exception e){
                logger.error("savePicture失败,e:"+e.getMessage());
            }
    }

    @Override
    public void updatePictureList(List<PicturePO> picturePOList) {
        try{
            pictureWriteDAO.updatePictureList(picturePOList);
        }catch (Exception e){
            logger.error("savePicture失败,e:"+e.getMessage());
        }
    }
}
	