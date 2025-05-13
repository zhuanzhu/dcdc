package com.egeo.components.product.facade;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.service.read.JdProductInnerIdReadService;
import com.egeo.components.product.service.read.JdProductReadService;
import com.egeo.components.product.service.write.CommodityProductUnitWriteService;
import com.egeo.components.product.service.write.JdProductInnerIdWriteService;
import com.egeo.components.product.service.write.JdProductWriteService;
import com.egeo.components.product.service.write.MerchantProductWriteService;
import com.egeo.components.product.service.write.ProductUnitWriteService;
import com.egeo.components.product.service.write.ProductWriteService;
import com.egeo.components.product.service.write.StandardProductUnitWriteService;
import com.egeo.components.product.service.write.StandardUnitWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;


@Component
public class JdProductInnerIdFacade {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private JdProductInnerIdReadService jdProductInnerIdReadService;
	
	@Resource
	private JdProductInnerIdWriteService jdProductInnerIdWriteService;

	@Resource
	private JdProductWriteService jdProductWriteService;

	@Resource
	private CommodityProductUnitWriteService commodityProductUnitWriteService;
	@Resource
	private MerchantProductWriteService merchantProductWriteService;
	@Resource
	private ProductWriteService productWriteService;
	@Resource
	private ProductUnitWriteService productUnitWriteService;
	@Resource
	private StandardProductUnitWriteService standardProductUnitWriteService;
	@Resource
	private StandardUnitWriteService standardUnitWriteService;

	@Resource
	private JdProductReadService jdProductReadService;
	@Autowired
	private JedisUtil jedisUtil;
	@Autowired
	private JdUtils jdUtils;
	
	public JdProductInnerIdDTO findJdProductInnerIdById(JdProductInnerIdDTO dto){
		
		return jdProductInnerIdReadService.findJdProductInnerIdById(dto);
	}

	public PageResult<JdProductInnerIdDTO> findJdProductInnerIdOfPage(JdProductInnerIdDTO dto,Pagination page){
		
		return jdProductInnerIdReadService.findJdProductInnerIdOfPage(dto, page);
		
	}

	public List<JdProductInnerIdDTO> findJdProductInnerIdAll(JdProductInnerIdDTO dto){
		
		return jdProductInnerIdReadService.findJdProductInnerIdAll(dto);
		
	}

	public Long insertJdProductInnerIdWithTx(JdProductInnerIdDTO dto){
		
		return jdProductInnerIdWriteService.insertJdProductInnerIdWithTx(dto);
	}

	public int updateJdProductInnerIdWithTx(JdProductInnerIdDTO dto){
		
		return jdProductInnerIdWriteService.updateJdProductInnerIdWithTx(dto);
	}

	public int deleteJdProductInnerIdWithTx(JdProductInnerIdDTO dto){
		
		return jdProductInnerIdWriteService.deleteJdProductInnerIdWithTx(dto);
		
	}


	public List<JdProductInnerIdDTO> findJdProductInnerIdAllByJdSkuIdList(List<Long> skuIdList) {
		return jdProductInnerIdReadService.findJdProductInnerIdAllByJdSkuIdList(skuIdList);
	}

    public void updateJdProductStatus(Long paramSkuId) {
		logger.info("进入脚本");
		JdProductInnerIdDTO dto = new JdProductInnerIdDTO();
		if(paramSkuId!=null){
			dto.setJdSkuId(paramSkuId);
			logger.info("paramSkuId="+paramSkuId);
		}
		List<JdProductInnerIdDTO> jdProductInnerIdAll = jdProductInnerIdReadService.findJdProductInnerIdAll(dto);
		for(JdProductInnerIdDTO innerIdDTO:jdProductInnerIdAll){
			Long jdSkuId = innerIdDTO.getJdSkuId();
			logger.info("jdSkuId="+jdSkuId);
			try {
				//查询状态
				Integer skuState = jdUtils.getSkuState(jedisUtil, jdSkuId);
				//修改状态
				JdProductDTO jdProductDTO = new JdProductDTO();
				logger.info("状态:"+skuState);
				jdProductDTO.setId(jdSkuId);
				try {
					List<JdProductDTO> jdProductAll = jdProductReadService.findJdProductAll(jdProductDTO);
					if(EmptyUtil.isNotEmpty(jdProductAll)){
						Integer state = jdProductAll.get(0).getState();
						if(state.equals(skuState)){
							continue;
						}

					}
					jdProductDTO.setState(skuState);
					jdProductWriteService.updateJdProductWithTx(jdProductDTO);
				} catch (Exception e) {

				}
				updateSuStatus(jdSkuId, skuState, innerIdDTO);
			}catch (Exception e){
				logger.error("商品不可见更新出错,pid="+jdSkuId);
			}

		}
		logger.info("结束脚本");

	}


	private void updateSuStatus(Long skuId, Integer skuStatus, JdProductInnerIdDTO innerIdDTO){

		Integer status=Integer.valueOf(4);
		if(Integer.valueOf(1).equals(skuStatus)){
			//上架
			status=Integer.valueOf(3);
		}
		logger.info("初始化更新京东商品状态:skuId="+skuId+",status="+status);
		Long innerSuId = innerIdDTO.getInnerSuId();
		Long innerSkuId = innerIdDTO.getInnerSkuId();
		Long innerPuId = innerIdDTO.getInnerPuId();
		Long innerProductUnitId = innerIdDTO.getInnerProductUnitId();
		Long innerProductId = innerIdDTO.getInnerProductId();
		//1.cpu
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setId(innerPuId);
		commodityProductUnitDTO.setStatus(status);
		try {
			commodityProductUnitWriteService.updateCommodityProductUnitWithTx(commodityProductUnitDTO);
		}catch (Exception e){

		}


		//2.merchantProduct
		MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
		merchantProductDTO.setId(innerSuId);
		merchantProductDTO.setStatus(status);
		try{
		merchantProductWriteService.updateStatusWithTx(merchantProductDTO);
		}catch (Exception e){

		}
		//3.product
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(innerProductId);
		productDTO.setStatus(status);
		try{
		productWriteService.updateWithTx(productDTO);
		}catch (Exception e){

		}


		//4.product_unit
		ProductUnitDTO productUnitDTO = new ProductUnitDTO();
		productUnitDTO.setId(innerProductUnitId);
		productUnitDTO.setStatus(status);
		try{
		productUnitWriteService.updateProductUnitWithTx(productUnitDTO);
		}catch (Exception e){

		}


		//6.standard_product_unit
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		standardProductUnitDTO.setId(innerProductId);
		standardProductUnitDTO.setStatus(status);
		try{
		int i = standardProductUnitWriteService.updateStandardProductUnitWithTx(standardProductUnitDTO);
		}catch (Exception e){

		}

		//7.standardunit
		StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
		standardUnitDTO.setId(innerSuId);
		standardUnitDTO.setStatus(status);
		if(status==4){
			logger.info("隐藏商品,suId="+innerSuId);
			standardUnitDTO.setIsVendible(1);
			standardUnitDTO.setIsVisible(1);
		}
		try{
		standardUnitWriteService.updateStandardUnitWithTx(standardUnitDTO);
		}catch (Exception e){

		}


	}

}
	