package com.egeo.components.product.strategy.impl;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.CategoryAndChannelCondition;
import com.egeo.components.product.condition.CategoryAndJdCondition;
import com.egeo.components.product.condition.ChannelProductAndSkuCondition;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dao.read.CategoryReadDAO;
import com.egeo.components.product.dao.read.ChannelProductSkuReadDAO;
import com.egeo.components.product.dao.read.StandardUnitReadDAO;
import com.egeo.components.product.dto.CakeProductDTO;
import com.egeo.components.product.dto.ChannelEnterpriseConfigDTO;
import com.egeo.components.product.dto.ChannelPriceDTO;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.facade.*;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.StringUtil;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("worldBuyProductStrategyImpl")
public class WorldBuyProductStrategyImpl implements SearchProductStrategy {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryReadDAO categoryReadDAO;

    @Autowired
    private StandardUnitReadDAO standardUnitReadDAO;
    @Autowired
    private ChannelProductFacade channelProductFacade;

    @Autowired
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Autowired
    private ChannelProductPictureFacade channelProductPictureFacade;

    @Autowired
    private PictureFacade pictureFacade;

    @Autowired
    private ChannelProductTextFacade channelProductTextFacade;

    @Autowired
    private ChannelProductDescriptionFacade channelProductDescriptionFacade;

    @Autowired
    private ChannelProductSkuReadDAO channelProductSkuReadDAO;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private ChannelProductBatchFacade channelProductBatchFacade;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public boolean hasSearchNext(ChannelProductSearchBean param) {
        if(CollectionUtils.isEmpty(param.getCategoryTreeNodeIdList())){
            return true;
        }
        Pagination page = param.getPage();
        Integer couponType = param.getCouponType();
        StandardUnitPO po = param.getPo();
        //查看是否有该渠道对应的后台类目
        List<CategoryAndChannelCondition> categorys = categoryReadDAO.findChannelCategoryByCategoryTreeNodes(param.getCategoryTreeNodeIdList(),getProductCode());
        addCategorys(categorys,param);
        ChannelProductAndSkuListDTO dto = getChannelProductAndSkuListDTO(param);
        int cnt = channelProductSkuReadDAO.getChannelProductAndSkuListCountOfPage(dto);
        param.setCnt(cnt);
        //是否还有下一页数据
        if (cnt>0 && cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            return true;
        }
        return false;
    }

    private void addCategorys(List<CategoryAndChannelCondition> categorys,ChannelProductSearchBean param){
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
        if(param.getCategoryTreeNodeIdList() ==null){
            param.setCategoryTreeNodeIdList(new ArrayList<>());
        }
        if(one.getChannelCategoryLevel()==1){
            param.getCategoryTreeNodeIdList().add(Long.valueOf(one.getChannelCategoryId()));
        }
       if(one.getChannelCategoryLevel() ==2){
           param.getCat2().add(one.getChannelCategoryId());
       }
       if(one.getChannelCategoryLevel()==3){
           param.getCat3().add(one.getChannelCategoryId());
       }
    }

    private void setDefaultPageResult(PageResult<StandardUnitCondition> pageResult, Pagination page) {
        pageResult.setList(new ArrayList<>());
        pageResult.setTotalSize(0);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
    }

    @Override
    public PageResult<StandardUnitCondition> searchChannelProduct(ChannelProductSearchBean param) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        Pagination page = param.getPage();
        //查询渠道商品表
        setDefaultPageResult(pageResult, page);
        setOrderBy(page);
        int cnt = param.getCnt();

       /* int platformPage = (cnt ==0?0:((cnt / page.getPageSize())+1));
        int jdPage = page.getPageNo()-platformPage;*/

        Integer couponType = param.getCouponType();
        StandardUnitPO po = param.getPo();
        Integer companyType =param.getCompanyType();
        //查询统计渠道商品总记录数
        ChannelProductAndSkuListDTO dto = getChannelProductAndSkuListDTO(param);
        if(CollectionUtils.isEmpty(dto.getCategoryTreeNodeIdList()) && CollectionUtils.isEmpty(dto.getCat2()) && CollectionUtils.isEmpty(dto.getCat3())){
            return pageResult;
        }
        List<ChannelProductAndSkuCondition>  rtList = channelProductSkuReadDAO.getChannelProductAndSkuListOfPage(dto,page);
        if(CollectionUtils.isEmpty(rtList)){
            return pageResult;
        }
        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(getProductCode(),param.getEnterpriseId(),false);
        List<String> productIds = FHCollectionUtils.listToStrList(rtList,ChannelProductAndSkuCondition::getProductId);

        Map<String,String> urlMap = channelProductPictureFacade.findChannelPicByProductIdsToMap(productIds,param.getChannelCode());


        List<StandardUnitCondition> list = new ArrayList<>();
        for (ChannelProductAndSkuCondition channelProductAndSkuCondition : rtList) {
            if(!CollectionUtils.isEmpty(urlMap) && urlMap.containsKey(channelProductAndSkuCondition.getProductId())){
                channelProductAndSkuCondition.setImagePath(urlMap.get(channelProductAndSkuCondition.getProductId()));
            }

            ChannelProductBatchDTO queryBatchDTO = new ChannelProductBatchDTO();
            queryBatchDTO.setProductId(channelProductAndSkuCondition.getProductId());
            ChannelProductBatchDTO  batchDTO =  channelProductBatchFacade.getPriceMinOne(queryBatchDTO);
            if(batchDTO !=null){
                List<String> specIds = new ArrayList<>();
                specIds.add(batchDTO.getId().toString());
                Map<String,ChannelPriceDTO> channelPriceDTOMap = toChannelPriceDTOMap(rtList,specIds,param);
                StandardUnitCondition tmp = new StandardUnitCondition();
                tmp.fromChannelProductAndSku(channelProductAndSkuCondition,5);
                Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(batchDTO.getPriceMarket()+"",batchDTO.getPriceSettleMent()+"",channelEnterpriseConfigs,channelPriceDTOMap.get(channelProductAndSkuCondition.getProductId()+batchDTO.getId()));
                tmp.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
                tmp.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
                if(channelPriceHelper.checkIsVaild(priceMap)){
                    //tmp.setId(Long.valueOf(batchDTO.getSpecList()));
                    tmp.setId(batchDTO.getId());
                    list.add(tmp);
                }else{
                    logger.info("产品ID：{}规格信息:{}不合法{}",channelProductAndSkuCondition.getProductId(), JSON.toJSONString(channelProductAndSkuCondition),JSON.toJSONString(priceMap));
                }
            }else{
                Map<String,ChannelPriceDTO> channelPriceDTOMap = toChannelPriceDTOMap(rtList,param);
                StandardUnitCondition tmp = new StandardUnitCondition();
                tmp.fromChannelProductAndSku(channelProductAndSkuCondition,5);
                Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(channelProductAndSkuCondition.getSkuMarketPrice()+"",channelProductAndSkuCondition.getSkuCostingPrice()+"",channelEnterpriseConfigs,channelPriceDTOMap.get(channelProductAndSkuCondition.getProductId()+channelProductAndSkuCondition.getId()));
                tmp.setSalePrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
                tmp.setMarketPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.MARKET_PRICE_KEY)));
                if(channelPriceHelper.checkIsVaild(priceMap)){
                    tmp.setId(Long.valueOf(channelProductAndSkuCondition.getExternalSkuId()));
                    list.add(tmp);
                }else{
                    logger.info("产品ID：{}规格信息:{}不合法{}",channelProductAndSkuCondition.getProductId(), JSON.toJSONString(channelProductAndSkuCondition),JSON.toJSONString(priceMap));
                }

            }
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        return pageResult;
    }

    private void setOrderBy(Pagination page) {
        page.setOrderBy("sku.sku_market_price asc");
        if(page.getOrderBy()!=null && page.getOrderBy().length()>0) {
            if(page.getOrderBy().equalsIgnoreCase("sales_volume")) {
                page.setOrderBy("sku.sku_market_price asc");
            }else if(page.getOrderBy().equalsIgnoreCase("sales_volume desc")) {
                page.setOrderBy("sku.sku_market_price asc");
            }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price")) {
                page.setOrderBy("sku.sku_market_price asc");
            }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
                page.setOrderBy("sku.sku_market_price desc");
            }
        }
    }

    private Map<String, ChannelPriceDTO> toChannelPriceDTOMap(List<ChannelProductAndSkuCondition> rtList, ChannelProductSearchBean param){
        List<String> pid = new ArrayList<>();
        List<String> specIds =new ArrayList<>();
        for (ChannelProductAndSkuCondition po : rtList) {
            if(StringUtils.isNotEmpty(po.getProductId())){
                pid.add(po.getProductId());
            }
           if(null !=po.getId()){
               specIds.add(String.valueOf(po.getId()));
           }
        }
        return channelPriceHelper.getChannelPriceMap(getProductCode(),pid,specIds,param.getEnterpriseId());
    }

    private ChannelProductAndSkuListDTO getChannelProductAndSkuListDTO(ChannelProductSearchBean param) {
        ChannelProductAndSkuListDTO dto = new ChannelProductAndSkuListDTO();
        dto.setChannelCode(param.getChannelCode());
        categoryTreeNodeIdList(param,dto);
        dto.setCat2(param.getCat2());
        dto.setCat3(param.getCat3());
        if(EmptyUtil.isNotEmpty(param.getKeyWord()) && EmptyUtil.isNotBlank(param.getKeyWord())){
            dto.setSkuProductName(param.getKeyWord());
        }
        return dto;
    }

    private void categoryTreeNodeIdList(ChannelProductSearchBean param,ChannelProductAndSkuListDTO dto){
        List<String> stringList = new ArrayList<>();
        for(Long id:param.getCategoryTreeNodeIdList()){
            stringList.add(id.toString());
        }
        dto.setCategoryTreeNodeIdList(stringList);
    }


    private Map<String, ChannelPriceDTO> toChannelPriceDTOMap(List<ChannelProductAndSkuCondition> rtList,List<String> specIds, ChannelProductSearchBean param){
        List<String> pid = new ArrayList<>();
        for (ChannelProductAndSkuCondition po : rtList) {
            if(StringUtils.isNotEmpty(po.getProductId())){
                pid.add(po.getProductId());
            }
        }
        return channelPriceHelper.getChannelPriceMap(getProductCode(),pid,specIds,param.getEnterpriseId());
    }

}
