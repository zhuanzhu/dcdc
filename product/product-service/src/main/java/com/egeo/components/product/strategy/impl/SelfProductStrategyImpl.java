package com.egeo.components.product.strategy.impl;

import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dao.read.StandardUnitReadDAO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @Description 自营产品策略实现类
 * @Author lsl
 * @Version V1.0
 **/
@Service("selfProductStrategyImpl")
public class SelfProductStrategyImpl implements SearchProductStrategy {

    @Autowired
    private StandardUnitReadDAO standardUnitReadDAO;

    @Override
    public String getProductCode(){
        return ProductChannelCodeEnum.SELF.getCode();
    }

    @Override
    public boolean hasSearchNext(ChannelProductSearchBean param){
        Pagination page = param.getPage();
        Integer couponType = param.getCouponType();
        StandardUnitPO po = param.getPo();
        List<Long> categoryTreeNodeIdList= param.getCategoryTreeNodeIdList();
        Integer companyType =param.getCompanyType();
        int cnt = standardUnitReadDAO.countstandardUnitByFunctionModuleId(couponType,po, categoryTreeNodeIdList, companyType);
        param.setCnt(cnt);
        //是否还有下一页数据
        if (cnt>0 && cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            return true;
        }
        return false;
    }

    @Override
    public PageResult<StandardUnitCondition> searchChannelProduct(ChannelProductSearchBean param){
        StandardUnitPO po =  param.getPo();
        if(EmptyUtil.isNotEmpty(param.getKeyWord()) && EmptyUtil.isNotBlank(param.getKeyWord())){
            if(Objects.nonNull(po)){
                po = new StandardUnitPO();
            }
            po.setName(param.getKeyWord());
        }
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        Pagination page = param.getPage();
        page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
        List<StandardUnitCondition> list = standardUnitReadDAO.standardUnitByFunctionModuleId(param.getCouponType(),po, param.getCategoryTreeNodeIdList(), param.getCompanyType(), page);
        pageResult.setList(list);
        pageResult.setTotalSize(param.getCnt());
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }


}
