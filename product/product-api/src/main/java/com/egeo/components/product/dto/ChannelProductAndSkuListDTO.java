package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductAndSkuListDTO implements Serializable {

    private List<String> categoryTreeNodeIdList;

    private List<String> brandIds;

    private String channelCode;

    private List<String> cat2;

    private List<String> cat3;

    private String skuProductName;

    public List<String> getCategoryTreeNodeIdList() {
        return categoryTreeNodeIdList;
    }

    public void setCategoryTreeNodeIdList(List<String> categoryTreeNodeIdList) {
        this.categoryTreeNodeIdList = categoryTreeNodeIdList;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }


    public List<String> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<String> brandIds) {
        this.brandIds = brandIds;
    }

    public List<String> getCat2() {
        return cat2;
    }

    public void setCat2(List<String> cat2) {
        this.cat2 = cat2;
    }

    public List<String> getCat3() {
        return cat3;
    }

    public void setCat3(List<String> cat3) {
        this.cat3 = cat3;
    }

    public String getSkuProductName() {
        return skuProductName;
    }

    public void setSkuProductName(String skuProductName) {
        this.skuProductName = skuProductName;
    }
}
