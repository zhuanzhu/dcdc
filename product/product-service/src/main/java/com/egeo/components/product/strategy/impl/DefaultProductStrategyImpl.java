package com.egeo.components.product.strategy.impl;

import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Description 默认情况下
 * @Author lsl
 * @Date 2024/4/30 2:31
 * @Version V1.0
 **/
@Service("defaultProductStrategyImpl")
public class DefaultProductStrategyImpl implements SearchProductStrategy {
    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.DEFAULT.getCode();
    }

    @Override
    public boolean hasSearchNext(ChannelProductSearchBean param) {
        return true;
    }

    @Override
    public PageResult<StandardUnitCondition> searchChannelProduct(ChannelProductSearchBean param) {
        //所有渠道都没有查询到数据，直接返回空列表
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        Pagination page = param.getPage();
        pageResult.setList(new ArrayList<>());
        pageResult.setTotalSize(0);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
}
