package com.egeo.components.product.facade;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.ChannelProductAndSkuCondition;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dto.ChannelEnterpriseConfigDTO;
import com.egeo.components.product.dto.ChannelPriceDTO;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.service.read.ChannelProductSkuReadService;
import com.egeo.components.product.service.write.ChannelProductSkuWriteService;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.str.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class ChannelProductSkuFacade {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ChannelProductSkuReadService channelProductSkuReadService;

    @Resource
    private ChannelProductSkuWriteService channelProductSkuWriteService;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private ChannelProductBatchFacade channelProductBatchFacade;

    @Autowired
    private ChannelProductPictureFacade channelProductPictureFacade;


    public ChannelProductSkuDTO findChannelProductSkuById(ChannelProductSkuDTO dto){

        return channelProductSkuReadService.findChannelProductSkuById(dto);
    }

    public PageResult<ChannelProductSkuDTO> findChannelProductSkuOfPage(ChannelProductSkuDTO dto, Pagination page){

        return channelProductSkuReadService.findChannelProductSkuOfPage(dto, page);

    }

    public List<ChannelProductSkuDTO> findChannelProductSkuAll(ChannelProductSkuDTO dto){

        return channelProductSkuReadService.findChannelProductSkuAll(dto);

    }
    public Long insertChannelProductSkuWithTx(ChannelProductSkuDTO dto){

        return channelProductSkuWriteService.insertChannelProductSkuWithTx(dto);
    }

    public int updateChannelProductSkuWithTx(ChannelProductSkuDTO dto){

        return channelProductSkuWriteService.updateChannelProductSkuWithTx(dto);
    }

    public int deleteChannelProductSkuWithTx(ChannelProductSkuDTO dto){

        return channelProductSkuWriteService.deleteChannelProductSkuWithTx(dto);

    }

    public List<ChannelProductSkuDTO> findChannelProductSkuBySkuIds(List<String> skuList,String channelCode){

        return channelProductSkuReadService.findChannelProductSkuBySkuIds(skuList,channelCode);

    }

    public int updateChannelProductSkuWithTx(List<String> skuIdList, String channelCode){

        return channelProductSkuWriteService.updateChannelProductSkuStateWithTx(skuIdList,channelCode);
    }

    public PageResult<StandardUnitCondition> getChannelProductAndSkuListOfPage(ChannelProductAndSkuListDTO dto,Long enterpriseId, Pagination page){
        if(enterpriseId == null){
            enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        }
        PageResult<StandardUnitCondition> pageResult = new PageResult<>();
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotalSize(0);
        PageResult<ChannelProductAndSkuCondition> rtPage =  channelProductSkuReadService.getChannelProductAndSkuListOfPage(dto,page);
        List<ChannelProductAndSkuCondition> rtList = rtPage.getList();
        if(CollectionUtils.isEmpty(rtList)){
            return pageResult;
        }
        String channelCode = dto.getChannelCode();
        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(channelCode,enterpriseId,false);
        List<String> productIds = FHCollectionUtils.listToStrList(rtList,ChannelProductAndSkuCondition::getProductId);

        Map<String,String> urlMap = channelProductPictureFacade.findChannelPicByProductIdsToMap(productIds,channelCode);
        List<StandardUnitCondition> list = new ArrayList<>();
        for (ChannelProductAndSkuCondition channelProductAndSkuCondition : rtList) {
            if(!CollectionUtils.isEmpty(urlMap) && urlMap.containsKey(channelProductAndSkuCondition.getProductId())){
                channelProductAndSkuCondition.setImagePath(urlMap.get(channelProductAndSkuCondition.getProductId()));
            }

            ChannelProductBatchDTO queryBatchDTO = new ChannelProductBatchDTO();
            queryBatchDTO.setProductId(channelProductAndSkuCondition.getProductId());
            ChannelProductBatchDTO  batchDTO =  channelProductBatchFacade.getPriceMinOne(queryBatchDTO);
            if(batchDTO == null){
                logger.info("产品ID：{}未找到规格信息:{}自动抛弃",channelProductAndSkuCondition.getProductId(), JSON.toJSONString(channelProductAndSkuCondition));
            }
            List<String> specIds = new ArrayList<>();
            specIds.add(batchDTO.getId().toString());
            Map<String, ChannelPriceDTO> channelPriceDTOMap = toChannelPriceDTOMap(rtList,specIds,channelCode,enterpriseId);
            StandardUnitCondition tmp = new StandardUnitCondition();
            tmp.fromChannelProductAndSku(channelProductAndSkuCondition, Objects.equals(channelCode, ProductChannelCodeEnum.WORLD_BUY.getCode())? ThirdConst.Source.WORLD :null);
            Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(batchDTO.getPriceMarket()+"",batchDTO.getPriceSettleMent()+"",channelEnterpriseConfigs,channelPriceDTOMap.get(channelProductAndSkuCondition.getProductId()+batchDTO.getId()));
            tmp.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
            tmp.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
            if(channelPriceHelper.checkIsVaild(priceMap)){
                tmp.setId(batchDTO.getId());
                list.add(tmp);
            }else{
                logger.info("产品ID：{}规格信息:{}不合法{}",channelProductAndSkuCondition.getProductId(), JSON.toJSONString(channelProductAndSkuCondition),JSON.toJSONString(priceMap));
            }
        }
        pageResult.setList(list);
        pageResult.setTotalSize(rtPage.getTotalSize());
        return pageResult;
    }

    private Map<String, ChannelPriceDTO> toChannelPriceDTOMap(List<ChannelProductAndSkuCondition> rtList,List<String> specIds, String channelCode,Long enterpriseId){
        List<String> pid = new ArrayList<>();
        for (ChannelProductAndSkuCondition po : rtList) {
            if(StringUtils.isNotEmpty(po.getProductId())){
                pid.add(po.getProductId());
            }
        }
        return channelPriceHelper.getChannelPriceMap(channelCode,pid,specIds,enterpriseId);
    }

    public List<ChannelProductSkuDTO> findChannelProductSkuBySkuCodes(List<String> skuList,String channelCode){

        return channelProductSkuReadService.findChannelProductSkuBySkuCodes(skuList,channelCode);

    }
}
