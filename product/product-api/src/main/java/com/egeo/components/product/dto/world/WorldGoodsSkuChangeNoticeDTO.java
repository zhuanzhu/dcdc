package com.egeo.components.product.dto.world;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 商品下架消息通知接口对象
 * @Author lsl
 * @Version V1.0
 **/
public class WorldGoodsSkuChangeNoticeDTO implements Serializable {

    private List<String> skuList;

    public List<String> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<String> skuList) {
        this.skuList = skuList;
    }
}
