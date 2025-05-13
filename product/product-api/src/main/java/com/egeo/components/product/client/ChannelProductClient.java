package com.egeo.components.product.client;

import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
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
        contextId = "ChannelProductClient"
)
public interface ChannelProductClient {

    @RequestMapping(
            value = {"/client/product/channelProduct/findProductById"},
            method = {RequestMethod.POST}
    )
    public ChannelProductDetailVO findProductById(ChannelProductDetailRequestVO vo);
}
