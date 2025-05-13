package com.egeo.components.product.client;

import com.egeo.components.product.dto.CakeProductDetailDTO;
import com.egeo.components.product.dto.CakeProductDetailSearchDTO;
import com.egeo.components.product.dto.CakeSPUIdSearchProductDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@FeignClient(
        name = "service-product-fgj",
        contextId = "CakeProductClient"
)
public interface CakeProductClient {

    @RequestMapping(
            value = {"/client/product/cakeProduct/findCakeProductById"},
            method = {RequestMethod.POST}
    )
    public CakeProductDetailDTO findCakeProductById(CakeProductDetailSearchDTO search);

    @RequestMapping(
            value = {"/client/product/cakeProduct/searchProductStatus"},
            method = {RequestMethod.POST}
    )
    String searchProductStatus(String productId);

    @RequestMapping(
            value = {"/client/product/cakeProduct/findCakeProductBySkuId"},
            method = {RequestMethod.POST}
    )
    public CakeProductDetailDTO findCakeProductBySkuId(CakeSPUIdSearchProductDetailDTO dto);
}
