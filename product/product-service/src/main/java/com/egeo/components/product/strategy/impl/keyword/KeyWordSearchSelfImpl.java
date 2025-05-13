package com.egeo.components.product.strategy.impl.keyword;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.bean.KeyWordSearchBean;
import com.egeo.components.product.bean.KeyWordSearchCachePageBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.facade.ChannelProductSkuFacade;
import com.egeo.components.product.manage.read.StandardUnitReadManage;
import com.egeo.components.product.strategy.KeyWordSearchStrategy;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/26 15:50
 * @Version V1.0
 **/
@Service("keyWordSearchSelfImpl")
public class KeyWordSearchSelfImpl extends KeyWordSearchCommonBase implements KeyWordSearchStrategy {

    @Resource
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Autowired
    private StandardUnitReadManage standardUnitReadManage;

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.SELF.getCode();
    }

    @Override
    public PageResult<StandardUnitDTO> getProductByKeyWord(KeyWordSearchBean bean) {

        Pagination page = bean.getPage();
        List<StandardUnitCondition> list = null;
        PageResult<StandardUnitCondition> pageResult = standardUnitReadManage.findByKeywordOfPage(bean.getSaleWay(),bean.getStoreId(),bean.getName(),bean.getUserBalance(),bean.getClientId(),bean.getCompanyId(),bean.getPlatformId(),bean.getCompanyType(), page, bean.getBuyType());
        int cnt = pageResult.getTotalSize();
        bean.addTotalCount(cnt);
        PageResult<StandardUnitDTO> result = new PageResult<>();
        boolean hasNextPage = cnt>0 && cnt >= (page.getPageNo() - 1) * page.getPageSize();
        if (!hasNextPage) {
            return newNullPageResult(page);
        }
        page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
        list = pageResult.getList();
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        for (StandardUnitCondition standardUnitCondition : list) {
            StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTO2.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
            standardUnitDTO2.setSalesVolume(standardUnitCondition.getSalesVolume());
            standardUnitDTOList.add(standardUnitDTO2);
        }
        result.setList(standardUnitDTOList);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());

        //缓存本次查询的分页数
        KeyWordSearchCachePageBean keyWordSearchCachePageBean = new KeyWordSearchCachePageBean();
        keyWordSearchCachePageBean.setCurrPageNo(page.getPageNo());
        keyWordSearchCachePageBean.setChannelCode(getProductCode());
        keyWordSearchCachePageBean.setTotalCount(pageResult.getTotalSize());
        keyWordSearchCachePageBean.setPageSize(page.getPageSize());
        saveRedisPage(keyWordSearchCachePageBean,jedisUtil,bean);
        return result;

    }
}
