package com.egeo.components.product.controller.client;

import com.egeo.components.product.client.ChannelProductClient;
import com.egeo.components.product.facade.ChannelProductFacade;
import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/product/channelProduct")
public class ChannelProductController implements ChannelProductClient {

    @Autowired
    private ChannelProductFacade channelProductFacade;

    @Override
    @RequestMapping(value = "/findCakeProductById", method = { RequestMethod.POST })
    @ResponseBody
    public ChannelProductDetailVO findProductById(@RequestBody ChannelProductDetailRequestVO vo) {
        return channelProductFacade.getChannelProductDetail(vo);
    }
}
