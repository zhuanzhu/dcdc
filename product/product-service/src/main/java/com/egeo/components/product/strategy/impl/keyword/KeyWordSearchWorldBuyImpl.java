package com.egeo.components.product.strategy.impl.keyword;

import com.egeo.components.product.bean.KeyWordSearchBean;
import com.egeo.components.product.bean.KeyWordSearchCachePageBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.facade.ChannelProductSkuFacade;
import com.egeo.components.product.strategy.KeyWordSearchStrategy;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.cache.JedisUtil;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/26 15:55
 * @Version V1.0
 **/
@Service("keyWordSearchWorldBuyImpl")
public class KeyWordSearchWorldBuyImpl extends KeyWordSearchCommonBase implements KeyWordSearchStrategy {
    @Resource
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public PageResult<StandardUnitDTO> getProductByKeyWord(KeyWordSearchBean bean) {
        Pagination page = bean.getPage();
        String name = bean.getName();
        PageResult<StandardUnitDTO> result = new PageResult<>();
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        ChannelProductAndSkuListDTO worldQueryDTO = new ChannelProductAndSkuListDTO();
        worldQueryDTO.setSkuProductName(name);
        worldQueryDTO.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
        int currPage = getCurrPage(getProductCode(),jedisUtil,bean);
        Pagination worldPage = new Pagination();
        worldPage.setPageNo(currPage);
        worldPage.setPageSize(page.getPageSize());
        worldPage.setOrderBy("sku.sku_market_price asc");
        if(page.getOrderBy()!=null && page.getOrderBy().length()>0) {
            if(page.getOrderBy().equalsIgnoreCase("sales_volume")) {
                //销量升序
            }else if(page.getOrderBy().equalsIgnoreCase("sales_volume desc")) {
                //销量降序
            }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price")) {
                worldPage.setOrderBy("sku.sku_market_price asc");
            }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
                worldPage.setOrderBy("sku.sku_market_price desc");
            }

        }
        PageResult<StandardUnitCondition> worldPageRt =  channelProductSkuFacade.getChannelProductAndSkuListOfPage(worldQueryDTO,null,worldPage);
        if(Objects.nonNull(worldPageRt) && !CollectionUtils.isEmpty(worldPageRt.getList())){
            for (StandardUnitCondition tmp : worldPageRt.getList()) {
                StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(tmp);
                standardUnitDTO2.setPictureUrl(tmp.getPictureUrl());
                standardUnitDTO2.setCommodityTemplateId(tmp.getCommodityTemplateId());
                standardUnitDTO2.setSalesVolume(tmp.getSalesVolume());
                standardUnitDTO2.setCode(tmp.getChannelProductId());
                standardUnitDTOList.add(standardUnitDTO2);
            }
            result.setList(standardUnitDTOList);
            result.setTotalSize(worldPageRt.getTotalSize());
            //缓存本次查询的分页数
            KeyWordSearchCachePageBean keyWordSearchCachePageBean = new KeyWordSearchCachePageBean();
            keyWordSearchCachePageBean.setCurrPageNo(currPage);
            keyWordSearchCachePageBean.setChannelCode(getProductCode());
            keyWordSearchCachePageBean.setTotalCount(worldPageRt.getTotalSize());
            keyWordSearchCachePageBean.setPageSize(page.getPageSize());
            saveRedisPage(keyWordSearchCachePageBean,jedisUtil,bean);
        }
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        return result;
    }
}
