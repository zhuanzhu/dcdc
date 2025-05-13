package com.egeo.components.product.strategy.impl.receive;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.bean.ReceiveProductBean;
import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.channel.*;
import com.egeo.components.product.dto.world.WorldBuyBaseNoticeDTO;
import com.egeo.components.product.dto.world.WorldGoodsChangeNoticeDTO;
import com.egeo.components.product.dto.world.WorldNoticeResponseDTO;
import com.egeo.components.product.dto.world.goodchild.WorldGoodsBatchListDTO;
import com.egeo.components.product.dto.world.goodchild.WorldSkuDetailDTO;
import com.egeo.components.product.dto.world.goodchild.WorldSpecListDTO;
import com.egeo.components.product.dto.world.goodchild.WorldStoreDTO;
import com.egeo.components.product.facade.*;
import com.egeo.components.product.strategy.ReceiveProductStrategy;
import com.egeo.components.utils.JsonUtils;
import com.egeo.utils.EmptyUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description 全球购推送产品商品服务
 * @Author lsl
 * @Version V1.0
 **/
@Service("worldBuyReceiveProductStrategyImpl")
public class WorldBuyReceiveProductStrategyImpl implements ReceiveProductStrategy {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductFacade channelProductFacade;

    @Autowired
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Autowired
    private ChannelProductPictureFacade channelProductPictureFacade;

    @Autowired
    private ChannelProductTextFacade channelProductTextFacade;

    @Autowired
    private ChannelProductDescriptionFacade channelProductDescriptionFacade;

    @Autowired
    private ChannelProductBatchFacade channelProductBatchFacade;

    private final static boolean IS_UPDATE_TAX_RATE=false;
    private final static boolean IS_DOUBLE_MARKET_PRICE =true;
    private final static String DOUBLE_MARKET_PRICE_OF_NUM ="2";

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public WorldNoticeResponseDTO receiveProduct(ReceiveProductBean bean) {
        String channelCode = bean.getChannelCode();
        try {
            logger.info("全球购商品变更或者新增消息通知接口通知报文{}", JsonUtils.objectToJson(bean));
            if(bean.getParamObject()==null){
                return WorldNoticeResponseDTO.fail("接收失败,data不能为空");
            }
            WorldBuyBaseNoticeDTO dto = (WorldBuyBaseNoticeDTO)bean.getParamObject();
            List<WorldGoodsChangeNoticeDTO>  worldGoodsChangeNoticeDTOS = JSONObject.parseArray(JsonUtils.objectToJson(dto.getData()),WorldGoodsChangeNoticeDTO.class);
            for (WorldGoodsChangeNoticeDTO goodsChangeNoticeDTO : worldGoodsChangeNoticeDTOS) {
                saveText(bean,dto,goodsChangeNoticeDTO);
                saveDetail(bean, goodsChangeNoticeDTO);
            }
            return WorldNoticeResponseDTO.success();
        }catch (Exception e){
            logger.error("", e);
            logger.error("商品变更或者新增消息通知接口->渠道{}通知发生异常{}",channelCode,e);
            return WorldNoticeResponseDTO.fail("接收失败");
        }
    }

    /**
     * @Description 保存报文
     **/
    private void saveText(ReceiveProductBean bean,WorldBuyBaseNoticeDTO dto,WorldGoodsChangeNoticeDTO  worldGoodsChangeNoticeDTO){
        ChannelProductTextDTO channelProductTextDTO = new ChannelProductTextDTO();
        channelProductTextDTO.setProductId(worldGoodsChangeNoticeDTO.getGoodsId());
        channelProductTextDTO.setChannelCode(bean.getChannelCode());
        channelProductTextDTO.setInfo(JsonUtils.objectToJson(dto));
        channelProductTextFacade.insertChannelProductTextWithTx(channelProductTextDTO);
    }

    private void saveDetail(ReceiveProductBean bean, WorldGoodsChangeNoticeDTO worldGoodsChangeNoticeDTO) {
        //新增产品
        ChannelProductDTO productDTO = saveProduct(worldGoodsChangeNoticeDTO, bean);
        //保存产品描述
        saveProductDescription(productDTO.getId(),productDTO.getChannelCode(), worldGoodsChangeNoticeDTO);
        //新增sku
        saveSku(productDTO.getId(),productDTO.getChannelCode(), worldGoodsChangeNoticeDTO);
        //新增图片
        saveImages(productDTO.getId(),productDTO.getChannelCode(), worldGoodsChangeNoticeDTO);
        //保存批次
        saveBatch(productDTO.getId(),productDTO.getChannelCode(), worldGoodsChangeNoticeDTO);

    }

    private void saveBatch(Long id, String channelCode, WorldGoodsChangeNoticeDTO dto) {
        List<WorldSkuDetailDTO> sukList =  dto.getSkuList();
        if(CollectionUtils.isEmpty(sukList)){
            throw new BusinessException("没有发现sku");
        }
        List<ChannelProductBatchDTO> list = new ArrayList<>();
        for (WorldSkuDetailDTO worldSkuDetailDTO : sukList) {
            List<WorldStoreDTO>  worldStoreDTOS =  worldSkuDetailDTO.getStoreList();
            for (WorldStoreDTO worldStoreDTO : worldStoreDTOS) {
                List<WorldGoodsBatchListDTO> worldGoodsBatchListDTOS = worldStoreDTO.getGoods_batch_list();
                for (WorldGoodsBatchListDTO worldGoodsBatchListDTO : worldGoodsBatchListDTOS) {
                    List<WorldSpecListDTO>  specList = worldGoodsBatchListDTO.getSpec_list();
                    int i =1;
                    for (WorldSpecListDTO worldSpecListDTO : specList) {
                        ChannelProductBatchDTO rtDTO = new ChannelProductBatchDTO();
                        rtDTO.setLinkSkuId(worldStoreDTO.getLinkSkuID());
                        rtDTO.setBatchId(worldGoodsBatchListDTO.getBatch_id());
                        rtDTO.setBatchNo(worldGoodsBatchListDTO.getBatch_no());
                        rtDTO.setNum(Integer.valueOf(worldGoodsBatchListDTO.getNum()));
                        rtDTO.setChannelCode(channelCode);
                        rtDTO.setProductId(dto.getGoodsId());
                        rtDTO.setExpiredDate(worldSpecListDTO.getExpired_date());
                        rtDTO.setStatus(Integer.valueOf(worldGoodsBatchListDTO.getStatus()));
                        rtDTO.setSpecList(String.valueOf(i++));
                        rtDTO.setSpecName(worldSpecListDTO.getSpecName());
                        rtDTO.setSpecNum(worldSpecListDTO.getSpec_num());
                        rtDTO.setMakeDate(worldSpecListDTO.getMake_date());
                        rtDTO.setPrice(new BigDecimal(worldSpecListDTO.getPrice()).setScale(2));
                        rtDTO.setPriceVip(com.egeo.utils.StringUtils.isNotEmpty(worldSpecListDTO.getPriceVip())?new BigDecimal(worldSpecListDTO.getPriceVip()).setScale(2):null);
                        rtDTO.setPriceSettleMent(new BigDecimal(worldSpecListDTO.getPriceSettlement()).setScale(2));
                        rtDTO.setPriceMarket(worldSpecListDTO.getPriceMarket() );
                        rtDTO.setPriceControl(com.egeo.utils.StringUtils.isNotEmpty(worldSpecListDTO.getPriceControl())?new BigDecimal(worldSpecListDTO.getPriceControl()).setScale(2):null);
                        rtDTO.setIsMinStockSpecificationItem(0);
                        if(com.egeo.utils.StringUtils.isNotEmpty(worldSpecListDTO.getIsMinStockSpecificationItem()) && worldSpecListDTO.getIsMinStockSpecificationItem().equals("true")){
                            rtDTO.setIsMinStockSpecificationItem(1);
                        }
                        if(IS_DOUBLE_MARKET_PRICE){
                            if(EmptyUtil.isNotEmpty(rtDTO.getPriceMarket())){
                                BigDecimal amt = new BigDecimal(rtDTO.getPriceMarket()).multiply(new BigDecimal(DOUBLE_MARKET_PRICE_OF_NUM)).setScale(2, RoundingMode.HALF_UP);
                                rtDTO.setPriceMarket(String.valueOf(amt));
                            }
                        }
                        list.add(rtDTO);
                    }
                }
            }
        }
        if(!CollectionUtils.isEmpty(list)){
            for (ChannelProductBatchDTO channelProductBatchDTO : list) {
                ChannelProductBatchDTO queryDTO = new ChannelProductBatchDTO();
                queryDTO.setSpecNum(channelProductBatchDTO.getSpecNum());
                queryDTO.setBatchNo(channelProductBatchDTO.getBatchNo());
                queryDTO.setProductId(channelProductBatchDTO.getProductId());
                queryDTO.setLinkSkuId(channelProductBatchDTO.getLinkSkuId());
                List<ChannelProductBatchDTO> batchDTOList = channelProductBatchFacade.findChannelProductBatchAll(queryDTO);
                if(CollectionUtils.isEmpty(batchDTOList)){
                    channelProductBatchFacade.insertChannelProductBatchWithTx(channelProductBatchDTO);
                }else{
                    ChannelProductBatchDTO oldBatchDTO = batchDTOList.get(0);
                    channelProductBatchDTO.setId(oldBatchDTO.getId());
                    channelProductBatchFacade.updateChannelProductBatchWithTx(channelProductBatchDTO);
                }

            }
        }
    }

    private void saveProductDescription(Long id, String channelCode, WorldGoodsChangeNoticeDTO worldGoodsChangeNoticeDTO) {
        ChannelProductDescriptionDTO channelProductDescriptionDTO = new ChannelProductDescriptionDTO();
        channelProductDescriptionDTO.setChannelCode(channelCode);
        channelProductDescriptionDTO.setProductId(worldGoodsChangeNoticeDTO.getGoodsId());
        channelProductDescriptionDTO.setContent(worldGoodsChangeNoticeDTO.getDescription());
        ChannelProductDescriptionDTO old = channelProductDescriptionFacade.getOneByProductId(worldGoodsChangeNoticeDTO.getGoodsId(),channelCode);
        if(old ==null){
            channelProductDescriptionFacade.insertChannelProductDescriptionWithTx(channelProductDescriptionDTO);
        }else{
            channelProductDescriptionDTO.setId(old.getId());
            channelProductDescriptionFacade.updateChannelProductDescriptionWithTx(channelProductDescriptionDTO);
        }

    }

    private void saveImages(Long selfDBProductId,String channelCode,WorldGoodsChangeNoticeDTO  dto){
        List<String> images = dto.getImages();
        if(CollectionUtils.isEmpty(images)){
            return;
        }
        ChannelProductPictureDTO queryDTO = new ChannelProductPictureDTO();
        queryDTO.setType(2);
        queryDTO.setChannelCode(channelCode);
        queryDTO.setProductId(dto.getGoodsId());
        channelProductPictureFacade.deleteByParaWithTx(queryDTO);
        for (int i = 0; i < images.size(); i++) {
            String image = images.get(i);
            ChannelProductPictureDTO channelProductPictureDTO = new ChannelProductPictureDTO();
            channelProductPictureDTO.setType(2);
            channelProductPictureDTO.setChannelCode(channelCode);
            channelProductPictureDTO.setPictureUrl(image);
            channelProductPictureDTO.setProductId(dto.getGoodsId());
            channelProductPictureDTO.setSortValue(i);
            channelProductPictureFacade.insertChannelProductPictureWithTx(channelProductPictureDTO);
        }
   }

    private void saveSku(Long selfDBProductId,String channelCode,WorldGoodsChangeNoticeDTO  dto){
        List<WorldSkuDetailDTO> sukList =  dto.getSkuList();
        if(CollectionUtils.isEmpty(sukList)){
            throw new BusinessException("没有发现sku");
        }
        ChannelProductSkuDTO channelProductSkuDTO =null;
        for (WorldSkuDetailDTO worldSkuDetailDTO : sukList) {
            channelProductSkuDTO = new ChannelProductSkuDTO();
            channelProductSkuDTO.setChannelCode(channelCode);
            channelProductSkuDTO.setProductId(dto.getGoodsId());
            channelProductSkuDTO.setSkuSerialNumber(worldSkuDetailDTO.getSkuId());
            channelProductSkuDTO.setSkuProductName(dto.getGoodsName());
            channelProductSkuDTO.setSkuMarketPrice(new BigDecimal(worldSkuDetailDTO.getPrice_market()));
            channelProductSkuDTO.setSkuCostingPrice(new BigDecimal(worldSkuDetailDTO.getPrice()));
            channelProductSkuDTO.setExternalSkuId(worldSkuDetailDTO.getSkuId());
            channelProductSkuDTO.setBarCode(worldSkuDetailDTO.getBarcode());
            channelProductSkuDTO.setSkuCode(worldSkuDetailDTO.getSkucode());
            channelProductSkuDTO.setCode(String.valueOf(selfDBProductId));
            channelProductSkuDTO.setStandardProductUnitId(Long.valueOf(worldSkuDetailDTO.getSpu_id()));
            channelProductSkuDTO.setIsValid(1);
            channelProductSkuDTO.setIsAvailable(1);
            channelProductSkuDTO.setTaxRate(worldSkuDetailDTO.getTax_rate());
            if(StringUtils.isNotEmpty(worldSkuDetailDTO.getHas_rate())){
                channelProductSkuDTO.setHasRate(Integer.valueOf(worldSkuDetailDTO.getHas_rate()));
            }
            channelProductSkuDTO.setStoreListText(JSON.toJSONString(worldSkuDetailDTO.getStoreList()));
            ChannelProductSkuDTO queryDTO = new ChannelProductSkuDTO();
            queryDTO.setChannelCode(channelCode);
            queryDTO.setSkuSerialNumber(channelProductSkuDTO.getSkuSerialNumber());
            queryDTO.setProductId(channelProductSkuDTO.getProductId());
            if(IS_DOUBLE_MARKET_PRICE){
                if(EmptyUtil.isNotEmpty(worldSkuDetailDTO.getPrice_market())){
                    BigDecimal amt = new BigDecimal(worldSkuDetailDTO.getPrice_market()).multiply(new BigDecimal(DOUBLE_MARKET_PRICE_OF_NUM)).setScale(2, RoundingMode.HALF_UP);
                    channelProductSkuDTO.setSkuMarketPrice(amt);
                }
            }
            List<ChannelProductSkuDTO> oldSkuList = channelProductSkuFacade.findChannelProductSkuAll(queryDTO);
            if(CollectionUtils.isEmpty(oldSkuList)){
                channelProductSkuFacade.insertChannelProductSkuWithTx(channelProductSkuDTO);
            }else{
                ChannelProductSkuDTO old = oldSkuList.get(0);
                channelProductSkuDTO.setId(old.getId());
                //更新时不修改税率
                if(!IS_UPDATE_TAX_RATE){
                    channelProductSkuDTO.setTaxRate(null);
                }
                channelProductSkuFacade.updateChannelProductSkuWithTx(channelProductSkuDTO);
            }
        }

    }

    private ChannelProductDTO saveProduct(WorldGoodsChangeNoticeDTO  dto,ReceiveProductBean bean){
        ChannelProductDTO productDTO = new ChannelProductDTO();
        productDTO.setProductId(dto.getGoodsId());
        productDTO.setChineseName(dto.getGoodsName());
        productDTO.setCategoryId(dto.getCategoryId());
        productDTO.setCategoryPid(dto.getCategoryPid());
        productDTO.setCategoryFPid(dto.getCategoryFPid());
        productDTO.setBrandId(dto.getBrandID());
        productDTO.setTitle(dto.getGoodsName());
        productDTO.setName(dto.getGoodsName());
        if(StringUtils.isNotEmpty(dto.getGoodsType())){
            productDTO.setGoodsType(Integer.valueOf(dto.getGoodsType()));
        }
        productDTO.setBrandName(dto.getBrandName());
        productDTO.setGoodsLevel(dto.getGoods_level());
        productDTO.setPlaceOfOrigin(dto.getCountryCode());
        productDTO.setCountryName(dto.getCountryName());
        productDTO.setCalculationUnit(dto.getUnitCode());
        productDTO.setUnitName(dto.getUnitName());
        productDTO.setStatus(Integer.valueOf(dto.getStatus()));
        productDTO.setIsAvailable(1);
        productDTO.setChannelCode(bean.getChannelCode());
        ChannelProductDTO oldProduct =  getChannelProductDTO(bean.getChannelCode(),dto.getGoodsId());
       if(oldProduct == null){
           channelProductFacade.insertChannelProductWithTx(productDTO);
       }else{
           productDTO.setId(oldProduct.getId());
           channelProductFacade.updateChannelProductWithTx(productDTO);
       }

        return productDTO;
    }

    private ChannelProductDTO getChannelProductDTO(String channelCode,String productId){
        ChannelProductDTO queryDTO = new ChannelProductDTO();
        queryDTO.setChannelCode(channelCode);
        queryDTO.setProductId(productId);
        List<ChannelProductDTO> list = channelProductFacade.findChannelProductAll(queryDTO);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
