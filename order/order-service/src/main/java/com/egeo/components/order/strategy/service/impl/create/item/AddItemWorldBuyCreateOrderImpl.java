package com.egeo.components.order.strategy.service.impl.create.item;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.strategy.vo.CreateOrderReqVO;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.components.order.vo.world.WroldSplitOrderRuleVO;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.dto.world.goodchild.WorldStoreDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 17:50
 * @Version V1.0
 **/
@Service("addItemWorldBuyCreateOrderImpl")
public class AddItemWorldBuyCreateOrderImpl extends AddItemsCommonBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public JsonResult<CreateOrderRespVO> getProduct(CreateOrderReqVO createOrderReqVO) {
        ChannelProductDetailVO channelProductDetailVO  = getChannelProductDetailVO(createOrderReqVO);
        if(channelProductDetailVO == null){
            return JsonResult.fail("全球购商品不存在");
        }
        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        createOrderRespVO.setProductObject(channelProductDetailVO);
        createOrderRespVO.setSourceProduct(ThirdConst.Source.WORLD);
        createOrderRespVO.setSource(ThirdConst.Source.WORLD);
        return JsonResult.success(createOrderRespVO);
    }

    private ChannelProductDetailVO getChannelProductDetailVO(CreateOrderReqVO createOrderReqVO){
        String channelProductId = createOrderReqVO.getChannelProductId();
        Long puId = createOrderReqVO.getPuId();
        Long enterpriseId = createOrderReqVO.getEnterpriseId();
        ChannelProductDetailVO channelProductDetailVO  = productFacade.findWorldProduct(channelProductId,puId+"",enterpriseId);
        return channelProductDetailVO;
    }

    @Override
    public JsonResult<CreateOrderRespVO> addItemsAndLimitRules(CreateOrderReqVO createOrderReqVO) {
        ChannelProductDetailVO channelProductDetailVO=null;
        if(createOrderReqVO.getType().intValue() ==0){
            channelProductDetailVO = (ChannelProductDetailVO)createOrderReqVO.getProductObject();
        }else{
            channelProductDetailVO = getChannelProductDetailVO(createOrderReqVO);
        }
        if(channelProductDetailVO == null){
            return JsonResult.fail("全球购商品不存在");
        }
        Long puId = createOrderReqVO.getPuId();
        ReceiverAddressDTO addr = createOrderReqVO.getAddr();
        Integer num = createOrderReqVO.getNum();
        Long userId = createOrderReqVO.getUserId();
        Long platformId = createOrderReqVO.getPlatformId();
        String userName = createOrderReqVO.getUserName();
        Long companyId = createOrderReqVO.getCompanyId();
        Long storeId = createOrderReqVO.getStoreId();
        UserDTO user = createOrderReqVO.getUser();
        String channelProductId = createOrderReqVO.getChannelProductId();
        JsonResult checkResult = checkWorldProductInfo(channelProductId,puId+"",channelProductDetailVO,addr);
        if(Objects.nonNull(checkResult)){
            return checkResult;
        }
        Double orderAmount = 0D;
        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        /**对应渠道对应的商品列表**/
        List<SoItemDTO> soItems = new ArrayList<>();
        // 组织限购规则记录集合
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        orderAmount = getWorldOrderAmountAndAddItem(storeId, puId, num, userId, platformId, userName, companyId, channelProductDetailVO, user, soItems, limitRuleRecordList, orderAmount);
        createOrderRespVO.setSoItems(soItems);
        createOrderRespVO.setLimitRuleRecordList(limitRuleRecordList);
        createOrderRespVO.setOrderAmount(orderAmount);
        createOrderRespVO.setSource(ThirdConst.Source.CAKE);
        createOrderRespVO.setOrderPayByCash(0D);
        createOrderRespVO.setLimitFuBiPayAmount(BigDecimal.ZERO);
        return JsonResult.success(createOrderRespVO);
    }


    private JsonResult checkWorldProductInfo(String puId, String skuId, ChannelProductDetailVO channelProductDetailVO, ReceiverAddressDTO addr){
        if(Objects.isNull(channelProductDetailVO)){
            return JsonResult.fail("无此商品");
        }
        ChannelProductDTO detailProductsDTO = channelProductDetailVO.getChannelProductDTO();
        if(Objects.isNull(detailProductsDTO) || detailProductsDTO.getStatus() ==null || detailProductsDTO.getStatus() !=1){
            return JsonResult.fail("此商品已下架");
        }

        List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
        ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(skuId,batchDTOList);
        if(Objects.isNull(batchDTO)){
            return JsonResult.fail("无此规格商品");
        }
        if( batchDTO.getNum()==null || batchDTO.getNum() <=0){
            return JsonResult.fail("商品已售罄");
        }

        return null;
    }

    private double getWorldOrderAmountAndAddItem(Long storeId, Long puId, Integer num, Long userId, Long platformId, String userName, Long companyId, ChannelProductDetailVO channelProductDetailVO, UserDTO user, List<SoItemDTO> soItems, List<LimitRuleRecordDTO> limitRuleRecordList, double orderAmount) {
        ChannelProductDTO channelProductDTO = channelProductDetailVO.getChannelProductDTO();
        List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
        ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(puId+"",batchDTOList);
        List<ChannelProductSkuDTO> channelProductSkuDTOS = channelProductDetailVO.getSkuList();
        ChannelProductSkuDTO channelProductSkuDTO = productFacade.getCurrSkuInfo(batchDTO.getLinkSkuId(),channelProductSkuDTOS);
        BigDecimal taxRate = BigDecimal.ZERO;
        if(channelProductSkuDTO !=null && channelProductSkuDTO.getTaxRate() !=null){
            taxRate = new BigDecimal(channelProductSkuDTO.getTaxRate()).multiply(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP);
        }
        WorldStoreDTO worldStoreDTO = null;
        if(EmptyUtil.isNotEmpty(channelProductSkuDTO.getStoreListText())){
            List<WorldStoreDTO> worldStoreDTOList = JSON.parseArray(channelProductSkuDTO.getStoreListText(), WorldStoreDTO.class);
            if(!CollectionUtils.isEmpty(worldStoreDTOList)){
                worldStoreDTO =worldStoreDTOList.get(0);
            }
        }

        WroldSplitOrderRuleVO splitOrderRuleVO = new WroldSplitOrderRuleVO();
        splitOrderRuleVO.setGoodsType(channelProductDTO.getGoodsType());
        if(Objects.nonNull(worldStoreDTO)){
            splitOrderRuleVO.setStore_code(worldStoreDTO.getStore_code());
            splitOrderRuleVO.setStore_id(worldStoreDTO.getStore_id());
            splitOrderRuleVO.setStore_isCombineOrders(Boolean.valueOf(worldStoreDTO.getStore_isCombineOrders()));
        }
        //校验结束
        SoItemDTO item = new SoItemDTO();
        item.setCartType(1);
        item.setPlatformId(platformId);
        item.setPrice(batchDTO.getPrice().setScale(2, RoundingMode.HALF_UP));
        item.setPuCount(num);
        item.setPuId(puId);
        // 一期暂时认为购物车商品不存在unit属性
        item.setUnitExist(0);
        item.setUserId(userId);
        item.setPuPicUrl(channelProductDetailVO.getProductImg());
        item.setPuName(channelProductDTO.getTitle()+batchDTO.getSpecName());
        item.setMerchantId(8L);
        item.setSource(5);
        item.setExternalSkuId(batchDTO.getLinkSkuId());
        item.setSnapshot(JSON.toJSONString(channelProductDetailVO));
        item.setExternalProductId(channelProductDTO.getProductId());
        item.setTaxRate(taxRate);
        item.setSplitOrderRule(JSON.toJSONString(splitOrderRuleVO));
        soItems.add(item);
        orderAmount += num * item.getPrice().doubleValue();


        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num));
        lrro.setBuyMoneySum(item.getPrice().setScale(2, RoundingMode.HALF_UP));
        lrro.setProductUnitId(puId);
        lrro.setProductUnitSerialNumber("world"+ puId);
        lrro.setStandardUnitId(puId);
        lrro.setCreateUserid(userId);
        lrro.setCreateUsername(userName);
        lrro.setCreateUserMobile(user.getMobile());
        lrro.setCompanyId(companyId);
        lrro.setStoreId(storeId);
        limitRuleRecordList.add(lrro);
        return orderAmount;
    }

}
