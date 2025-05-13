package com.egeo.components.order.strategy.service.impl.channel;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.MerchantFacade;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;
import com.egeo.components.product.dto.*;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.str.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 蛋糕叔叔商品确认
 * @Author lsl
 * @Date 2024/12/1 14:03
 * @Version V1.0
 **/
@Service("orderConfirmCakeImpl")
public class OrderConfirmCakeImpl extends OrderConfirmChannelBase{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JedisUtil jedisUtil;
    @Resource(name = "merchantFacade")
    private MerchantFacade merchantFacade;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.CAKE.getCode();
    }

    @Override
    public OrderResult buyPrductByRightNow(OrderConfirmReqVO reqVO) {
        OrderResult result = new OrderResult();
        result.setIsPayByFuBiOnly(0);
        OrderConfirmGoodsDTO gvo = new OrderConfirmGoodsDTO();
        Boolean flag = false;
        Long  puId = reqVO.getPuId();
        Integer num = reqVO.getNum();
        String channelProductId = reqVO.getChannelProductId();

        int goodsAccount = num;//订单商品总数
        BigDecimal orderAmount = BigDecimal.ZERO;//订单总金额
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        int buyType = 1;
        //校验蛋糕叔叔
        //1.校验蛋糕叔叔上下架
        String externalSkuId = puId+"";
        CakeProductDetailDTO cakeProductDetailDTO = productFacade.getCakeProduct(channelProductId,puId+"",null,null, reqVO.getEnterpriseId());
        if(cakeProductDetailDTO==null){
            result.setSuccess(false);
            result.setError("商品不存在");
            return result;
        }
        CakeProductDetailProductsDTO productsDTO = cakeProductDetailDTO.getProduct();
        CakeProductBrandDTO brand = cakeProductDetailDTO.getBrand();
        if(productsDTO==null){
            result.setSuccess(false);
            result.setError("商品明细不存在");
            return result;
        }
        if(!productsDTO.getStatus().equals("1")){
            flag = true;
            gvo.setErrMessage("抱歉，该商品无货");
            jdDownGoods.add(gvo);
            result.setError("抱歉，该商品已下架");
            result.setSuccess(false);
            return result;

        }
        List<CakeProductDetailSpecsDTO> specsDTOS = cakeProductDetailDTO.getSpecs();
        if(CollectionUtils.isEmpty(specsDTOS)){
            result.setSuccess(false);
            result.setError("商品无规格");
            return result;
        }
        CakeProductDetailSpecsDTO specsDTO = null;
        for (CakeProductDetailSpecsDTO dto : specsDTOS) {
            if(dto.getId().equals(puId+"")){
                specsDTO = dto;
                break;
            }
        }
        if(specsDTO==null){
            result.setSuccess(false);
            result.setError("商品无对应的规格");
            return result;
        }
        if(EmptyUtil.isEmpty(specsDTO.getStock()) || EmptyUtil.isBlank(specsDTO.getStock())){
            throw new BusinessException("当前商品库存存在问题");
        }
        if(specsDTO.getStock().equals("-9999999") || Integer.valueOf(specsDTO.getStock()) >0){
            logger.info("有货");
        }else{
            flag = true;

            gvo.setErrMessage("抱歉，该商品无货");
            jdDownGoods.add(gvo);
            result.setError("抱歉，该商品无货");
            result.setSuccess(false);
            result.setJdDownGoods(jdDownGoods);
            return result;

        }
        double price = new BigDecimal(specsDTO.getPrice()).doubleValue();
        gvo.setCartItemId(reqVO.getCartItemId());
        gvo.setNum(num);
        gvo.setPrice(price);
        gvo.setPuId(puId);
        gvo.setSkuDesc(specsDTO.getDescription());
        gvo.setPuImg(StringUtils.isNotEmpty(productsDTO.getImage_path())?productsDTO.getImage_path():specsDTO.getSpec_img());
        gvo.setPuName(productsDTO.getTitle()+specsDTO.getName());
        gvo.setBuyType(1);
        gvo.setExternalSkuId(externalSkuId);
        gvo.setStandardUnitId(puId);
        gvo.setLimitBuyNum(1L);
        gvo.setExternalProductId(channelProductId);
        gvo.setIsOwnMerchant(0);
        gvo.setDistribution_rule_id(productsDTO.getDistribution_rule_id());
        gvo.setSource(ThirdConst.Source.CAKE);
        if(EmptyUtil.isEmpty(productsDTO.getDistribution_rule_id())){
            gvo.setDistribution_rule_id(brand.getDistribution_rule_id());
        }
        if(!flag){
            orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
            //立即购买,单个商品,设置仅现金支付金额
            BigDecimal orderAmountOnlyCash = new BigDecimal(0);
            //如果商品是积分+现金则设置为0
            result.setOrderAmountOnlyCash(orderAmountOnlyCash);
            goodsList.add(gvo);
        }
        MerchantDTO merchant = merchantFacade.findMerchantById(ThirdConst.Merchant.CAKE);
        setCommonResult(result, payByFuBiAmount, goodsAccount, orderAmount, goodsList, jdDownGoods, limitRuleRecordList,merchant);
        return result;
    }

    @Override
    public OrderResult buyProductByCart(OrderConfirmReqVO reqVO) {
        return buyPrductByRightNow(reqVO);
    }
}
