package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.PictureConverter;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.manage.write.PictureWriteManage;
import com.egeo.components.product.po.PicturePO;
import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.service.write.PictureWriteService;
import com.egeo.components.product.service.write.ProductPictureWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("pictureWriteService")
public class PictureWriteServiceImpl  implements PictureWriteService {
	private static final XLogger logger = XLogger.getLogger(PictureWriteServiceImpl.class);
	@Autowired
	private PictureWriteManage pictureWriteManage;
	
	@Autowired
    private PictureReadService pictureReadService;


	@Autowired
        private ProductPictureWriteService productPictureWriteService;

    @Resource
    private JedisUtil jedisUtil;
        @Override
        public Long savePictureWithTx(PictureDTO dto) {
            dto.setSortValue(1);
            return pictureWriteManage.savePictureWithTx(PictureConverter.toPO(dto));
        }

        @Override
        public String deleteWithTx(PictureDTO dto) {
            
            return pictureWriteManage.deleteWithTx(PictureConverter.toPO(dto));
        }

        @Override
        public String deleteByIdsWithTx(String ids) {
            
            return pictureWriteManage.deleteByIds(ids);
        }

        @Override
        public String deletePictureWithTx(String picture) {
            PictureDTO dto = new PictureDTO();
            dto.setUrl(picture);
            List<PictureDTO> pictureList = pictureReadService.findAll(dto);
            if(pictureList.size() > 0){
                //因为七牛生成的图片url唯一，所以图片id唯一
                //根据图片id删除产品与图片关系和图片
                ProductPictureDTO productPictureDTO = new ProductPictureDTO();
                productPictureDTO.setPictureId(pictureList.get(0).getId());
                //根据图片id删除图片
                pictureWriteManage.deleteWithTx(PictureConverter.toPO(pictureList.get(0)));
            }
            return "删除成功";
        }
        /**
         * 根据suid和图片id集合删除所有没有查询出来的su图片关系
         * @param merchantProductId
         * @param pictureIds
         * @return
         */
		@Override
		public int delByStandardUnitIdPictureIdWithTx(Long merchantProductId, List<Long> pictureIds) {
			// TODO Auto-generated method stub
			return pictureWriteManage.delByStandardUnitIdPictureId(merchantProductId, pictureIds);
		}


    //批量保存picture
    @Override
    public void savePicture(List<Long> pictureIdList, List<String> pictureUrlList) {
        Integer SIZE=10000;
        ExecutorService executor = Executors.newCachedThreadPool();

        List<PicturePO> res = new ArrayList<>();
        for (int i=0;i< pictureIdList.size();i++) {
            PicturePO picture = new PicturePO();
            picture.setId(pictureIdList.get(i));
            picture.setName("京东图片");
            picture.setUrl(pictureUrlList.get(i));
            picture.setSortValue(0);
            picture.setType(Integer.valueOf(1));
            picture.setPlatformId(7L);
            res.add(picture);
        }

        if(EmptyUtil.isEmpty(res)){
            return;
        }
        logger.info("开始异步处理Picture保存");
        CountDownLatch countDownLatch = new CountDownLatch(1);


        int page=((res.size()-1)/SIZE)+1;
        try {
            jedisUtil.set("Picture",24*60*60,page);
        }catch (Exception e){
            logger.info("[Picture]设置redis失败");
        }
        for(int i=0;i<page;i++) {
            List<PicturePO> list = new ArrayList<>();
            if (i == (page - 1)) {
                for (int j = i * SIZE; j < res.size(); j++) {
                    list.add(res.get(j));
                }
            } else {
                for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
                    list.add(res.get(j));
                }
            }
            SaveListFactory saveListFactory = new SaveListFactory();
            saveListFactory.setLatch(countDownLatch);
            saveListFactory.setSaveType("Picture");
            saveListFactory.setPicturePOList(list);
            saveListFactory.setPictureWriteManage(pictureWriteManage);
            saveListFactory.setJedisUtil(jedisUtil);

            executor.execute(saveListFactory);

        }
        countDownLatch.countDown();
        executor.shutdown();
    }

    @Override
    public void updatePictureList(List<Long> pictureIdList, List<String> pictureUrlList) {
        Integer SIZE=10000;
        ExecutorService executor = Executors.newCachedThreadPool();

        List<PicturePO> res = new ArrayList<>();
        for (int i=0;i<pictureIdList.size();i++) {
            PicturePO picturePO = new PicturePO();
            picturePO.setId(pictureIdList.get(i));
            picturePO.setUrl(pictureUrlList.get(i));
            res.add(picturePO);
        }
        if(EmptyUtil.isEmpty(res)){
            return;
        }
        logger.info("开始异步处理product更新");
        CountDownLatch countDownLatch = new CountDownLatch(1);

        int page=((res.size()-1)/SIZE)+1;
        for(int i=0;i<page;i++) {
            List<PicturePO> list = new ArrayList<>();
            if (i == (page - 1)) {
                for (int j = i * SIZE; j < res.size(); j++) {
                    list.add(res.get(j));
                }
            } else {
                for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
                    list.add(res.get(j));
                }
            }
            UpdateListFactory factory = new UpdateListFactory();
            factory.setLatch(countDownLatch);
            factory.setSaveType("PictureList");
            factory.setPicturePOList(list);
            factory.setPictureWriteManage(pictureWriteManage);
            factory.setJedisUtil(jedisUtil);

            executor.execute(factory);
        }
        countDownLatch.countDown();
        executor.shutdown();

    }
}
	