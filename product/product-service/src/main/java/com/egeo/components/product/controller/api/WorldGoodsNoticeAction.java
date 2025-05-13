package com.egeo.components.product.controller.api;

import com.egeo.components.product.bean.ReceiveProductBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.world.WorldBuyBaseNoticeDTO;
import com.egeo.components.product.strategy.ProductFactory;
import com.egeo.components.product.strategy.ProductUpOrDownStrategy;
import com.egeo.components.product.strategy.ReceiveProductStrategy;
import com.egeo.web.BaseSpringController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Description 全球购商品通知
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/product/goodsNotice")
public class WorldGoodsNoticeAction extends BaseSpringController {
    Logger log=Logger.getLogger(WorldGoodsNoticeAction.class);
    @Autowired
    private ProductFactory productFactory;

    @RequestMapping(value = "/world/goodsChange", method = { RequestMethod.POST })
    @ResponseBody
    public Object goodsChange(@RequestBody WorldBuyBaseNoticeDTO dto) {
        ReceiveProductStrategy receiveProductStrategy = productFactory.getReceiveProductStrategy(ProductChannelCodeEnum.WORLD_BUY.getCode());
        ReceiveProductBean bean = new ReceiveProductBean();
        bean.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
        bean.setParamObject(dto);
        return receiveProductStrategy.receiveProduct(bean);
    }

    @RequestMapping(value = "/world/goodsSkuChange", method = { RequestMethod.POST })
    @ResponseBody
    public Object goodsSkuChange(WorldBuyBaseNoticeDTO dto) {
        ProductUpOrDownStrategy productUpOrDownStrategy = productFactory.getProductUpOrDownStrategy(ProductChannelCodeEnum.WORLD_BUY.getCode());
        ReceiveProductBean bean = new ReceiveProductBean();
        bean.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
        bean.setParamObject(dto);
        return productUpOrDownStrategy.receiveProductState(bean);
    }

    @RequestMapping(value = "/world/goodsBatchDown", method = { RequestMethod.POST })
    @ResponseBody
    public Object goodsBatchDown() {
        ProductUpOrDownStrategy productUpOrDownStrategy = productFactory.getProductUpOrDownStrategy(ProductChannelCodeEnum.WORLD_BUY.getCode());
        ReceiveProductBean bean = new ReceiveProductBean();
        bean.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
        return productUpOrDownStrategy.receiveProductState(bean);
    }
}
