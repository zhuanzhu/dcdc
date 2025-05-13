package com.egeo.components.product.strategy;

import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.orm.PageResult;

import java.util.List;

/**
 * @Description 供应商渠道商品查询服务策略
 * @Author lsl
 * @Date 2024/4/30 1:09
 * @Version V1.0
 **/
public interface SearchProductStrategy {

    /**获取产品code 参照枚举ProductChannelCodeEnum**/
    public String getProductCode();

    /**是否可以查询下一个渠道**/
    public boolean hasSearchNext(ChannelProductSearchBean param);

    /**查询商品列表**/
    public PageResult<StandardUnitCondition> searchChannelProduct(ChannelProductSearchBean param);


}
