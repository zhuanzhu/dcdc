package com.egeo.components.product.condition;

import com.egeo.components.product.po.ChannelProductPicturePO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductPicCondition extends ChannelProductPicturePO {

    private String name;

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
