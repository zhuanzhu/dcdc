package com.egeo.components.product.client;

import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.vo.AttValueCustomReqVO;
import com.egeo.components.product.vo.ProductAttValueVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/17 17:18
 * @Version V1.0
 **/
@FeignClient(name = "service-product-fgj",contextId="ProductAttValueClient")
public interface ProductAttValueClient {

    /**获取商品对应的产品编码属性**/
    @RequestMapping(value = { "/client/product/productAttValue/findAttValueCustomBySpuId" }, method = { RequestMethod.POST })
    public ProductAttValueDTO findAttValueCustomBySpuId(AttValueCustomReqVO vo);

}
