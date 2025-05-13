package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.ProductAttValueConverter;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.manage.read.AttributeValueReadManage;
import com.egeo.components.product.manage.read.ProductAttNameReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueReadManage;
import com.egeo.components.product.manage.write.ProductAttNameWriteManage;
import com.egeo.components.product.manage.write.ProductAttValueWriteManage;
import com.egeo.components.product.manage.write.ProductWriteManage;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.components.product.po.ProductAttValuePO;
import com.egeo.components.product.po.ProductDescriptionPO;
import com.egeo.components.product.po.ProductPO;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.components.product.service.write.ProductAttValueWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("productAttValueWriteService")
public class ProductAttValueWriteServiceImpl  implements ProductAttValueWriteService {
	private static final XLogger logger = XLogger.getLogger(ProductAttValueWriteServiceImpl.class);
	@Autowired
	private ProductAttValueWriteManage productAttValueWriteManage;
	
	@Autowired
	private ProductWriteManage productWriteManage;
	
	@Autowired
	private AttributeValueReadManage attributeValueReadManage;
	
	@Autowired
	private ProductAttNameReadManage productAttNameReadManage;
	
	@Autowired
	private ProductAttNameWriteManage productAttNameWriteManage;
	
	@Autowired
	private StandardProductUnitAttValueReadManage standardProductUnitAttValueReadManage;
	@Resource
	private JedisUtil jedisUtil;

	@Override
        public Long saveProductAttValueWithTx(ProductAttValueDTO dto) {
            dto.setSortValue(1);
            return productAttValueWriteManage.saveProductAttValueWithTx(ProductAttValueConverter.toPO(dto));
        }

        @Override
        public String deleteByProductAttNameIdWithTx(ProductAttValueDTO dto) {
            
            return productAttValueWriteManage.deleteByProductAttNameIdWithTx(ProductAttValueConverter.toPO(dto));
        }

        @Override
        public String deleteByMuchProductAttNameIdWithTx(String productAttNameIds) {
            
            return productAttValueWriteManage.deleteByMuchProductAttNameIdWithTx(productAttNameIds);
        }
        /**
		 * 根据产品属性值id删除产品属性值的关系
		 * @param productAttValueVO
		 * @return
		 */
		@Override
		public int deleteByIdWithTx(ProductAttValueDTO dto,Long productId) {
			//根据spu草稿id和属性值id查询是否存在spu规格属性
			StandardProductUnitAttValuePO standardProductUnitAttValuePO = standardProductUnitAttValueReadManage.standardProductUnitAttValueByProductIdAttValueId(productId,dto.getAttValueId());
			if(EmptyUtil.isNotEmpty(standardProductUnitAttValuePO)){
				throw new BusinessException("该规格属性已通过审核，不能删除");
			}
			return productAttValueWriteManage.deleteById(ProductAttValueConverter.toPO(dto));
		}

		@Override
		public Long saveProductAttValueByProductIdAndAttnameIdWithTx(Long productId, List<ProductAttValueDTO> dto,
				Long platformId) {
			if(EmptyUtil.isEmpty(productId)){
				ProductPO productPO = new ProductPO();
				productPO.setStatus(1);
				productId = productWriteManage.insertProductWithTx(productPO, new ProductDescriptionPO());
			}
			Long attnameId = null;
			if(EmptyUtil.isNotEmpty(dto)){
				//根据属性值id查询属性信息
				AttributeValuePO attributeValuePO = new AttributeValuePO();
				attributeValuePO.setId(dto.get(0).getAttValueId());
				AttributeValuePO attributeValue = attributeValueReadManage.findById(attributeValuePO);
				attnameId = attributeValue.getAttributeNameId();
			}
			ProductAttNamePO productAttNamePO = new ProductAttNamePO();
			productAttNamePO.setProductId(productId);
			productAttNamePO.setAttNameId(attnameId);
			productAttNamePO.setType(2);
			//根据sup草稿id和属性id查询spu属性信息
			List<ProductAttNamePO> list = productAttNameReadManage.findAll(productAttNamePO);
			Long productAttNameId = null;
			if(EmptyUtil.isEmpty(list)){
				productAttNamePO.setPlatformId(platformId);
				productAttNameId = productAttNameWriteManage.saveProductAttNameWithTx(productAttNamePO);
			}else{
				productAttNameId = list.get(0).getId();
			}
			
			
			
			if(EmptyUtil.isNotEmpty(productAttNameId)){
				for (ProductAttValueDTO productAttValueDTO : dto) {
					productAttValueDTO.setProductAttNameId(productAttNameId);
					productAttValueDTO.setPlatformId(platformId);
					productAttValueWriteManage.saveProductAttValueWithTx(ProductAttValueConverter.toPO(productAttValueDTO));
				}
			}
			return productId;
		}
		/**
		 * 根据spu规格属性值草稿id保存spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		@Override
		public boolean saveImgByProductAttValueId(Long productAttValueId, String pictureUrl) {
			// TODO Auto-generated method stub
			return productAttValueWriteManage.saveImgByProductAttValueId(productAttValueId, pictureUrl);
		}
		/**
		 * 根据spu规格属性草稿id删除spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		@Override
		public boolean delImgByProductAttNameId(Long productAttNameId) {
			// TODO Auto-generated method stub
			return productAttValueWriteManage.delImgByProductAttNameId(productAttNameId);
		}
		/**
		 * 根据属性属性值和spuid删除规格信息
		 * @param productId
		 * @param attributeNameId
		 * @param attributeValueId
		 * @return
		 */
		@Override
		public boolean delByAttIdAndSpuIdWithTx(Long productId, Long attributeNameId, Long attributeValueId,Long productAttValueId) {
			// TODO Auto-generated method stub
			return productAttValueWriteManage.delByAttIdAndSpuIdWithTx(productId, attributeNameId, attributeValueId,productAttValueId);
		}


	//为每个商品构建4个基本属性和一个规格属性的值
	private List<ProductAttValuePO> buildProductAttValue(Long productAttNameId, Long attValueId){
		List<ProductAttValuePO> res = new ArrayList<>();
		ProductAttValuePO productAttValuePO = new ProductAttValuePO();
		productAttValuePO.setProductAttNameId(productAttNameId);
		productAttValuePO.setAttValueId(attValueId);
		productAttValuePO.setPlatformId(7L);
		ProductAttValuePO productAttValuePO1 = new ProductAttValuePO();
		productAttValuePO1.setProductAttNameId(productAttNameId+1);
		productAttValuePO1.setAttValueId(2L);
		productAttValuePO1.setPlatformId(7L);
		ProductAttValuePO productAttValuePO2 = new ProductAttValuePO();
		productAttValuePO2.setProductAttNameId(productAttNameId+2);
		productAttValuePO2.setAttValueId(4L);
		productAttValuePO2.setPlatformId(7L);
		ProductAttValuePO productAttValuePO3 = new ProductAttValuePO();
		productAttValuePO3.setProductAttNameId(productAttNameId+3);
		productAttValuePO3.setAttValueId(6L);
		productAttValuePO3.setPlatformId(7L);
		ProductAttValuePO productAttValuePO4 = new ProductAttValuePO();
		productAttValuePO4.setProductAttNameId(productAttNameId+4);
		productAttValuePO4.setAttValueId(13L);
		productAttValuePO4.setPlatformId(7L);
		res.add(productAttValuePO);
		res.add(productAttValuePO1);
		res.add(productAttValuePO2);
		res.add(productAttValuePO3);
		res.add(productAttValuePO4);
		return res;
	}
	//保证参数顺序无误
	@Override
	public void saveProductAttValue(List<Long> productAttNameIdList, List<Long> attValueIdList){
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();

		List<ProductAttValuePO> list = new ArrayList<>();
		for(int i=0;i<productAttNameIdList.size();i++){
			List<ProductAttValuePO> productAttValuePOS = buildProductAttValue(productAttNameIdList.get(i), attValueIdList.get(i));
			list.addAll(productAttValuePOS);
		}

		if(EmptyUtil.isEmpty(list)){
			return;
		}
		logger.info("开始异步处理ProductAttValue保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((list.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("ProductAttValue",24*60*60,page);
		}catch (Exception e){
			logger.info("[ProductAttValue]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<ProductAttValuePO> res = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < list.size(); j++) {
					res.add(list.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					res.add(list.get(j));
				}
			}
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setLatch(countDownLatch);
			saveListFactory.setSaveType("ProductAttValue");
			saveListFactory.setProductAttValuePOList(res);
			saveListFactory.setProductAttValueWriteManage(productAttValueWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}

}
	