package com.egeo.components.order.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.ProductUnitClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitDTO;

/**
 * Created by 0.0 on 2018/9/14.
 */
@Component
public class ProductUnitFacade {


	@Autowired
    private ProductUnitClient productUnitReadService;
	@Autowired
    private SkuClient skuReadService;
	@Autowired
    private StandardProductUnitClient standardProductUnitReadService;
	@Autowired
    private StandardUnitClient standardUnitReadService;

    public ProductUnitDTO findProductUnitById(Long puId) {
        ProductUnitDTO dto = new ProductUnitDTO();
        dto.setId(puId);
        return productUnitReadService.findProductUnitById(dto);
    }

    public SkuDTO finSkuById(Long skuId){
        SkuDTO skuDTO = new SkuDTO();
        skuDTO.setId(skuId);
       return skuReadService.findSkuById(skuDTO);


    }
    public StandardProductUnitDTO findStandardProductUnitById(Long spuId){
        StandardProductUnitDTO dto=new StandardProductUnitDTO();
        dto.setId(spuId);

        return standardProductUnitReadService.findStandardProductUnitById(dto);
    }
    public StandardUnitDTO findStandardUnitById(Long suId){
        return standardUnitReadService.findStandardUnitById(suId);

    }
}
