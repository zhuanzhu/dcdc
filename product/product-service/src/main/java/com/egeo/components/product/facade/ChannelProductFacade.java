package com.egeo.components.product.facade;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dto.CakeProductDTO;
import com.egeo.components.product.dto.ChannelEnterpriseConfigDTO;
import com.egeo.components.product.dto.ChannelPriceDTO;
import com.egeo.components.product.dto.channel.*;
import com.egeo.components.product.enums.ChannelPriceConstants;
import com.egeo.components.product.helper.ChannelPriceHelper;
import com.egeo.components.product.service.read.ChannelProductReadService;
import com.egeo.components.product.service.write.ChannelProductWriteService;
import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class ChannelProductFacade {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ChannelProductReadService channelProductReadService;

    @Resource
    private ChannelProductWriteService channelProductWriteService;

    @Resource
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Autowired
    private ChannelPriceHelper channelPriceHelper;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ChannelProductBatchFacade channelProductBatchFacade;

    @Autowired
    private ChannelProductPictureFacade channelProductPictureFacade;

    @Autowired
    private ChannelProductDescriptionFacade channelProductDescriptionFacade;

    public ChannelProductDTO findChannelProductById(ChannelProductDTO dto){

        return channelProductReadService.findChannelProductById(dto);
    }

    public PageResult<ChannelProductDTO> findChannelProductOfPage(ChannelProductDTO dto, Pagination page){

        return channelProductReadService.findChannelProductOfPage(dto, page);

    }

    public List<ChannelProductDTO> findChannelProductAll(ChannelProductDTO dto){

        return channelProductReadService.findChannelProductAll(dto);

    }
    public Long insertChannelProductWithTx(ChannelProductDTO dto){

        return channelProductWriteService.insertChannelProductWithTx(dto);
    }

    public int updateChannelProductWithTx(ChannelProductDTO dto){

        return channelProductWriteService.updateChannelProductWithTx(dto);
    }

    public int deleteChannelProductWithTx(ChannelProductDTO dto){

        return channelProductWriteService.deleteChannelProductWithTx(dto);

    }

    private ChannelProductDTO getChannelProductSingle(String productId,String channelCode){
        if(EmptyUtil.isEmpty(productId) || EmptyUtil.isEmpty(channelCode)){
            return null;
        }
        ChannelProductDTO dto = new ChannelProductDTO();
        dto.setProductId(productId);
        dto.setChannelCode(channelCode);
        List<ChannelProductDTO> list = findChannelProductAll(dto);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    /***
     * @Description 获取渠道商品详情
     **/
    public ChannelProductDetailVO getChannelProductDetail(ChannelProductDetailRequestVO vo){
        ChannelProductDTO channelProductDTO = getChannelProductSingle(vo.getProductId(),vo.getChannelCode());
        if(channelProductDTO == null){
            return null;
        }
        Long enterpriseId = EmptyUtil.isNotEmpty(vo.getEnterpriseId())?vo.getEnterpriseId():getEnterpriseId();
        ChannelProductDetailVO resultVO = new ChannelProductDetailVO(channelProductDTO);
        ChannelProductSkuDTO skuQueryDTO = new ChannelProductSkuDTO();
        skuQueryDTO.setProductId(vo.getProductId());
        skuQueryDTO.setChannelCode(vo.getChannelCode());
        List<ChannelProductSkuDTO> skuDTOList =  channelProductSkuFacade.findChannelProductSkuAll(skuQueryDTO);
        resultVO.setSkuList(skuDTOList);
        List<ChannelEnterpriseConfigDTO>  channelEnterpriseConfigs = channelPriceHelper.getChannelEnterpriseConfigAll(vo.getChannelCode(),enterpriseId,false);
        List<String> skuIds = null;
        if(CollectionUtils.isEmpty(skuDTOList)){
            skuIds = new ArrayList<>();
            for (ChannelProductSkuDTO channelProductSkuDTO : skuDTOList) {
                skuIds.add(String.valueOf(channelProductSkuDTO.getId()));
            }
        }
        //描述
        ChannelProductDescriptionDTO descriptionDTO = channelProductDescriptionFacade.getOneByProductId(vo.getProductId(),vo.getChannelCode());
        resultVO.setDescriptionDTO(descriptionDTO);
        //图片
        ChannelProductPictureDTO pictureQuery = new ChannelProductPictureDTO();
        pictureQuery.setProductId(vo.getProductId());
        pictureQuery.setChannelCode(vo.getChannelCode());
        List<ChannelProductPictureDTO> pictureDTOList = channelProductPictureFacade.findChannelProductPictureAll(pictureQuery);
        String picMain = null;
        List<String> picList=null;
        if(!CollectionUtils.isEmpty(pictureDTOList)){
            picList = new ArrayList<>();
            for (ChannelProductPictureDTO channelProductPictureDTO : pictureDTOList) {
                if(channelProductPictureDTO.getType()==null || channelProductPictureDTO.getType().intValue()==1){
                    picMain = channelProductPictureDTO.getPictureUrl();
                }
                if(channelProductPictureDTO.getType().intValue()==2){
                    picList.add(channelProductPictureDTO.getPictureUrl());
                }
            }
            resultVO.setPictureList(picList);
        }
        if(EmptyUtil.isEmpty(picMain) && EmptyUtil.isNotEmpty(picList)){
            picMain = picList.get(0);
        }
        resultVO.setProductImg(picMain);
        //批次
        if(ProductChannelCodeEnum.WORLD_BUY.getCode().equals(vo.getChannelCode())){
            ChannelProductBatchDTO queryBatchDTO = new ChannelProductBatchDTO();
            queryBatchDTO.setProductId(vo.getProductId());
            List<ChannelProductBatchDTO>  batchDTOList =  channelProductBatchFacade.findChannelProductBatchAll(queryBatchDTO);
            resultVO.setBatchDTOList(batchDTOList);
            if(CollectionUtils.isEmpty(batchDTOList)){
                setSkuPriceData(vo, channelProductDTO, enterpriseId, skuDTOList, channelEnterpriseConfigs, skuIds);
                return resultVO;
            }
            //List<String> specList = FHCollectionUtils.listToStrList(batchDTOList,ChannelProductBatchDTO::getSpecList);
            List<String> specList = new ArrayList<>();
            for (ChannelProductBatchDTO batchDTO : batchDTOList) {
                specList.add(batchDTO.getId().toString());
            }
            Map<String, ChannelPriceDTO> channelPriceDTOMap = toChannelPriceDTOMap(channelProductDTO.getProductId(), specList, vo.getChannelCode(), enterpriseId);
            List<ChannelProductBatchDTO> resultBatchList = new ArrayList();
            for (ChannelProductBatchDTO batchDTO:batchDTOList){
                Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(batchDTO.getPriceMarket()+"",batchDTO.getPriceSettleMent()+"", channelEnterpriseConfigs,channelPriceDTOMap.get(batchDTO.getProductId()+batchDTO.getId()));
                batchDTO.setPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
                if(!channelPriceHelper.checkIsVaild(priceMap)){
                    logger.info("产品ID：{}规格信息:{}不合法{}", channelProductDTO.getProductId(), JSON.toJSONString(batchDTO),JSON.toJSONString(priceMap));
                    continue;
                }
                resultBatchList.add(batchDTO);
            }
            resultVO.setBatchDTOList(resultBatchList);
            return resultVO;
        }
        setSkuPriceData(vo, channelProductDTO, enterpriseId, skuDTOList, channelEnterpriseConfigs, skuIds);
        return resultVO;
    }

    private void setSkuPriceData(ChannelProductDetailRequestVO vo, ChannelProductDTO channelProductDTO, Long enterpriseId, List<ChannelProductSkuDTO> skuDTOList, List<ChannelEnterpriseConfigDTO> channelEnterpriseConfigs, List<String> skuIds) {
        Map<String, ChannelPriceDTO> channelPriceDTOMap = toChannelPriceDTOMap(channelProductDTO.getProductId(), skuIds, vo.getChannelCode(), enterpriseId);
        ChannelProductSkuDTO currProductSkuDTO = null;
        for (ChannelProductSkuDTO channelProductSkuDTO : skuDTOList) {
            Map<String,String> priceMap = channelPriceHelper.calcPlatformPrice(channelProductSkuDTO.getSkuMarketPrice()+"",channelProductSkuDTO.getSkuCostingPrice()+"", channelEnterpriseConfigs,channelPriceDTOMap.get(channelProductSkuDTO.getProductId()+channelProductSkuDTO.getId()));
            channelProductSkuDTO.setSkuPrice(new BigDecimal(priceMap.get(ChannelPriceConstants.SALE_PRICE_KEY)));
            if(!channelPriceHelper.checkIsVaild(priceMap)){
                logger.info("产品ID：{}规格信息:{}不合法{}", channelProductDTO.getProductId(), JSON.toJSONString(channelProductSkuDTO),JSON.toJSONString(priceMap));
                continue;
            }
            if(StringUtils.isEmpty(vo.getSkuId()) && vo.getSkuId().equals(String.valueOf(channelProductSkuDTO.getId()))){
                currProductSkuDTO = channelProductSkuDTO;
            }
        }
        if(currProductSkuDTO == null){
            Optional<ChannelProductSkuDTO> minSalePriceCondition = FHCollectionUtils.findMinT(skuDTOList,ChannelProductSkuDTO::getSkuPrice);
            if(minSalePriceCondition.isPresent()){
                currProductSkuDTO = minSalePriceCondition.get();
            }
        }
        if(currProductSkuDTO !=null){
            channelProductDTO.setPrice(currProductSkuDTO.getSkuPrice());
            channelProductDTO.setMarketPrice(currProductSkuDTO.getSkuMarketPrice());
            channelProductDTO.setId(currProductSkuDTO.getId());
        }
    }

    private Map<String, ChannelPriceDTO> toChannelPriceDTOMap(String pid,List<String> skuIds, String channelCode,Long enterpriseId){
        List<String> pids = Arrays.asList(pid);
        return channelPriceHelper.getChannelPriceMap(channelCode,pids,skuIds,enterpriseId);
    }

    private Long getEnterpriseId(){
        Long enenterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        if(enenterpriseId !=null){
            return enenterpriseId;
        }
        if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
            if(RuntimeContext.cacheUser().getCompanyId()!=null) {
                CompanyDTO company = companyClient.findCompanyById(RuntimeContext.cacheUser().getCompanyId());
                if(company.getEnterpriseId()!=null) {
                    logger.info("用户："+RuntimeContext.cacheUser().getLoginName()+"  登录公司id:"+RuntimeContext.cacheUser().getCompanyId().longValue()+" 代理id为空并填充成功");
                    RuntimeContext.cacheUser().setEnterpriseId(company.getEnterpriseId());
                    return company.getEnterpriseId();
                }
            }
        }
        return null;
    }

    public ChannelProductBatchDTO getCurrBatch(ChannelProductDetailRequestVO vo){
        ChannelProductBatchDTO queryBatchDTO = new ChannelProductBatchDTO();
        queryBatchDTO.setProductId(vo.getProductId());
        List<ChannelProductBatchDTO>  batchDTOList =  channelProductBatchFacade.findChannelProductBatchAll(queryBatchDTO);
        return getCurrBatch(vo,batchDTOList);
    }

    public ChannelProductBatchDTO getCurrBatch(ChannelProductDetailRequestVO vo,List<ChannelProductBatchDTO> list){
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        for (ChannelProductBatchDTO batchDTO : list) {
            if(String.valueOf(batchDTO.getId()).equals(vo.getSkuId())){
                return batchDTO;
            }
        }
        return null;
    }

    /***
     * @Description 获取渠道商品详情
     **/
    public ChannelProductDetailVO getChannelProductDetailUnPrice(ChannelProductDetailRequestVO vo){
        ChannelProductDTO channelProductDTO = getChannelProductSingle(vo.getProductId(),vo.getChannelCode());
        if(channelProductDTO == null){
            return null;
        }
        ChannelProductDetailVO resultVO = new ChannelProductDetailVO(channelProductDTO);
        ChannelProductSkuDTO skuQueryDTO = new ChannelProductSkuDTO();
        skuQueryDTO.setProductId(vo.getProductId());
        skuQueryDTO.setChannelCode(vo.getChannelCode());
        List<ChannelProductSkuDTO> skuDTOList =  channelProductSkuFacade.findChannelProductSkuAll(skuQueryDTO);
        resultVO.setSkuList(skuDTOList);
        //图片
        ChannelProductPictureDTO pictureQuery = new ChannelProductPictureDTO();
        pictureQuery.setProductId(vo.getProductId());
        pictureQuery.setChannelCode(vo.getChannelCode());
        List<ChannelProductPictureDTO> pictureDTOList = channelProductPictureFacade.findChannelProductPictureAll(pictureQuery);
        String picMain = null;
        List<String> picList=null;
        if(!CollectionUtils.isEmpty(pictureDTOList)) {
            picList = new ArrayList<>();
            for (ChannelProductPictureDTO channelProductPictureDTO : pictureDTOList) {
                if (channelProductPictureDTO.getType() == null || channelProductPictureDTO.getType().intValue() == 1) {
                    picMain = channelProductPictureDTO.getPictureUrl();
                }
                if (channelProductPictureDTO.getType().intValue() == 2) {
                    picList.add(channelProductPictureDTO.getPictureUrl());
                }
            }
            if(!CollectionUtils.isEmpty(picList)){
                resultVO.setPictureList(picList);
            }
        }

        if(EmptyUtil.isEmpty(picMain) && EmptyUtil.isNotEmpty(picList)){
            picMain = picList.get(0);
        }

        ChannelProductBatchDTO queryBatchDTO = new ChannelProductBatchDTO();
        queryBatchDTO.setProductId(vo.getProductId());
        List<ChannelProductBatchDTO>  batchDTOList =  channelProductBatchFacade.findChannelProductBatchAll(queryBatchDTO);
        resultVO.setBatchDTOList(batchDTOList);
        resultVO.setProductImg(picMain);
        return resultVO;
    }
}
