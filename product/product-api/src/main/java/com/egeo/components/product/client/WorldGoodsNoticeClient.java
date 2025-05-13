package com.egeo.components.product.client;

import com.egeo.components.product.dto.world.WorldBuyBaseNoticeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@FeignClient(
        name = "service-product-fgj",
        contextId = "WorldGoodsNoticeClient"
)
public interface WorldGoodsNoticeClient {

    @RequestMapping(
            value = {"/client/product/goodsNotice/world/goodsChange"},
            method = {RequestMethod.POST}
    )
    public Object goodsChange(WorldBuyBaseNoticeDTO dto);

    @RequestMapping(
            value = {"/client/product/goodsNotice/world/goodsSkuChange"},
            method = {RequestMethod.POST}
    )
    public Object goodsSkuChange(WorldBuyBaseNoticeDTO dto);

    @RequestMapping(
            value = {"/client/product/goodsNotice/world/goodsBatchDown"},
            method = {RequestMethod.POST}
    )
    public Object goodsBatchDown();
}
