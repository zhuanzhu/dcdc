package com.egeo.components.product.controller.client;

import com.egeo.components.product.client.WorldBuyProductClient;
import com.egeo.components.product.facade.ChannelProductFacade;
import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/product/worldBuyProduct")
public class WorldBuyProductController implements WorldBuyProductClient {

    @Resource
    private ChannelProductFacade channelProductFacade;

    @Override
    @RequestMapping(value = "/findWorldProductBySkuId", method = { RequestMethod.POST })
    @ResponseBody
    public ChannelProductDetailVO findWorldProductBySkuId(@RequestBody ChannelProductDetailRequestVO vo) {
        return channelProductFacade.getChannelProductDetail(vo);
    }
}
