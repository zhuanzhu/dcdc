package com.egeo.components.product.strategy.impl;

import com.egeo.components.product.bean.ChannelExcuteResult;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.CategoryAndChannelCondition;
import com.egeo.components.product.condition.CategoryAndJdCondition;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dao.read.CategoryReadDAO;
import com.egeo.components.product.dto.CakeProductDTO;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/7/16 11:17
 * @Version V1.0
 **/
@Service("remoteProductStrategyImpl")
public class RemoteProductStrategyImpl  implements SearchProductStrategy {

    @Autowired
    private CategoryReadDAO categoryReadDAO;

    @Autowired
    private ChannelServiceConfigClient channelServiceConfigClient;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.REMOTE.getCode();
    }

    @Override
    public boolean hasSearchNext(ChannelProductSearchBean param) {
        return true;
    }

    @Override
    public PageResult<StandardUnitCondition> searchChannelProduct(ChannelProductSearchBean param) {

        List<CategoryAndChannelCondition> categorys = categoryReadDAO.findChannelCategoryByCategoryTreeNodes(param.getCategoryTreeNodeIdList(),param.getChannelCode());
        addCategorys(categorys,param);

        EnterpriseChannelServiceDTO enterpriseChannelServiceDTO = param.getChannelServiceDTO();
        RemoteExecuteDTO dto = new RemoteExecuteDTO();
        dto.setChannelCode(param.getChannelCode());
        dto.setChannelServiceName(enterpriseChannelServiceDTO.getChannelServiceName());
        dto.setChannelServiceType(enterpriseChannelServiceDTO.getChannelServiceType());
        dto.setEnterpriseId(param.getEnterpriseId().intValue());
        dto.setJsonString(JsonUtils.objectToJson(param));

        JsonResult jsonResult = channelServiceConfigClient.remoteExecute(dto);
        if(jsonResult ==null || !jsonResult.getSuccess().equals("200")){
            return getEmptyPageResult(param);
        }
        List<StandardUnitCondition> list = JsonUtils.jsonToList(JsonUtils.objectToJson(jsonResult.getData()),StandardUnitCondition.class);
        PageResult<StandardUnitCondition> pageResult = getRealPageResult(param, list);
        return pageResult;
    }

    private PageResult<StandardUnitCondition> getRealPageResult(ChannelProductSearchBean param, List<StandardUnitCondition> list) {
        //所有渠道都没有查询到数据，直接返回空列表
        PageResult<StandardUnitCondition> pageResult = new PageResult<>();
        Pagination page = param.getPage();
        pageResult.setList(list);
        pageResult.setTotalSize(list.size());
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    private PageResult<StandardUnitCondition> getEmptyPageResult(ChannelProductSearchBean param) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<>();
        Pagination page = param.getPage();
        pageResult.setList(new ArrayList<>());
        pageResult.setTotalSize(0);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    private void addCategorys(List<CategoryAndChannelCondition> categorys, ChannelProductSearchBean param){
        if(CollectionUtils.isEmpty(categorys)){
            return ;
        }
        for(CategoryAndChannelCondition one : categorys) {
            if(one.getChannelCategory()!=null && one.getChannelCategory()>0) {
                setTypeAndBrandIds(one,param);
            }
        }
    }

    private void setTypeAndBrandIds(CategoryAndChannelCondition one,ChannelProductSearchBean param){
        if(one.getChannelCategoryType()==1){
            param.getProductTypes().add(one.getChannelCategoryId());
        }else{
            param.getBrandIds().add(one.getChannelCategoryId());
        }
    }
}
