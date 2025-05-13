package com.egeo.components.product.strategy.impl.platform;

import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.ChannelEnterpriseConfigDTO;
import com.egeo.components.product.dto.ChannelPriceDTO;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.facade.ChannelProductBatchFacade;
import com.egeo.components.product.facade.ChannelProductPictureFacade;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/8/29 15:24
 * @Version V1.0
 **/
@Service("worldBuyPlatformProductStrategyImpl")
public class WorldBuyPlatformProductStrategyImpl extends ChannelPlatformProductAbstractImpl {

    @Resource
    private ChannelProductBatchFacade channelProductBatchFacade;

    @Resource
    private ChannelProductPictureFacade channelProductPictureFacade;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> platform(ChannelSupplierProductRequestVO vo, Pagination page) {
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> result = new JsonResult<>();
        PageResult<ChannelSupplierProductResponseVO> pageResult = super.setDefaultPageResult(vo,page);
        pageResult = channelProductBatchFacade.findChannelProductOfPage(vo,page);
        List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(getProductCode(),false);
        setImage(pageResult.getList(),vo.getChannelCode(),channelEnterpriseConfigs);
        result.setData(pageResult);
        return result;
    }

    @Override
    public JsonResult<PageResult<ChannelSupplierProductResponseVO>> enterprise(ChannelSupplierProductRequestVO vo, Pagination page) {
        JsonResult<PageResult<ChannelSupplierProductResponseVO>> result = new JsonResult<>();
        PageResult<ChannelSupplierProductResponseVO> pageResult = super.setDefaultPageResult(vo,page);
        pageResult = channelProductBatchFacade.findChannelProductOfPage(vo,page);
        List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(vo.getChannelCode(), RuntimeContext.cacheUser().getEnterpriseId(),false);
        setImage(pageResult.getList(),vo.getChannelCode(),channelEnterpriseConfigs);
        result.setData(pageResult);
        return result;
    }

    private void setImage(List<ChannelSupplierProductResponseVO> list,String channelCode,List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs){
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        Long enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        List<String> productIds = FHCollectionUtils.listToStrList(list,ChannelSupplierProductResponseVO::getProductId);
        Map<String,String> imageMap = channelProductPictureFacade.findChannelPicByProductIdsToMap(productIds,channelCode);
        List<String> skuIds = FHCollectionUtils.listToStrList(list,ChannelSupplierProductResponseVO::getSkuId);
        Map<String, ChannelPriceDTO> priceDTOMap =channelPriceHelper.getChannelPriceMap(getProductCode(), productIds,skuIds,enterpriseId);
        for (ChannelSupplierProductResponseVO vo : list) {
            if(!CollectionUtils.isEmpty(imageMap) && imageMap.containsKey(vo.getProductId())){
                vo.setImagePath(imageMap.get(vo.getProductId()));
            }
            Map<String, String>  priceMap = channelPriceHelper.calcPlatformPrice(vo.getMarketPrice()+"",vo.getSalePrice()+"",channelEnterpriseConfigs,priceDTOMap !=null ?priceDTOMap.get(vo.getProductId()+vo.getSkuId()):null);
            //if(channelPriceHelper.checkIsVaild(priceMap)){
            if(EmptyUtil.isNotEmpty(priceMap)){
                vo.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
                vo.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
                vo.setProfit(new BigDecimal(priceMap.get(ChannelPriceConstants.PROFIT_KEY)));
                if(priceMap.containsKey(ChannelPriceConstants.PRICE_AUDIT) && null !=priceMap.get(ChannelPriceConstants.PRICE_AUDIT)){
                    vo.setPriceAudit(Integer.valueOf(priceMap.get(ChannelPriceConstants.PRICE_AUDIT)));
                }
                if(priceMap.containsKey(ChannelPriceConstants.PRICE_TYPE) && null !=priceMap.get(ChannelPriceConstants.PRICE_TYPE)){
                    vo.setPriceType(Integer.valueOf(priceMap.get(ChannelPriceConstants.PRICE_TYPE)));
                }
                if(priceMap.containsKey(ChannelPriceConstants.PRICE_VALUE) && null !=priceMap.get(ChannelPriceConstants.PRICE_VALUE)){
                    vo.setPriceValue(priceMap.get(ChannelPriceConstants.PRICE_VALUE));
                }
            }

        }
    }
}
