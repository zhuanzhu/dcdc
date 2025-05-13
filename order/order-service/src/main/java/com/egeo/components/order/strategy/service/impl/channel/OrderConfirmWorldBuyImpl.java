package com.egeo.components.order.strategy.service.impl.channel;

import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.MerchantFacade;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.cache.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 全球购商品确认
 * @Author lsl
 * @Date 2024/12/1 14:08
 * @Version V1.0
 **/
@Service("orderConfirmWorldBuyImpl")
public class OrderConfirmWorldBuyImpl extends OrderConfirmChannelBase{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JedisUtil jedisUtil;
    @Resource(name = "merchantFacade")
    private MerchantFacade merchantFacade;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
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
        //校验全球购
        //1.校验全球购上下架
        String externalSkuId = puId+"";
        ChannelProductDetailVO channelProductDetailVO = productFacade.findWorldProduct(channelProductId,externalSkuId, reqVO.getEnterpriseId());
        if(channelProductDetailVO==null){
            result.setSuccess(false);
            result.setError("商品不存在");
            return result;
        }
        ChannelProductDTO channelProductDTO = channelProductDetailVO.getChannelProductDTO();
        if(channelProductDTO==null){
            result.setSuccess(false);
            result.setError("商品不存在");
            return result;
        }

        List<ChannelProductBatchDTO> batchDTOList = channelProductDetailVO.getBatchDTOList();
        ChannelProductBatchDTO batchDTO = productFacade.getCurrBatch(externalSkuId,batchDTOList);
        if(batchDTO==null){
            result.setSuccess(false);
            result.setError("商品无对应的规格");
            return result;
        }
        if(batchDTO.getNum() ==null){
            throw new BusinessException("当前商品库存存在问题");
        }
        if(batchDTO.getNum() >0){
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
        ChannelProductDescriptionDTO channelProductDescriptionDTO = channelProductDetailVO.getDescriptionDTO();
        String description = channelProductDTO.getName()+batchDTO.getSpecName();
        if(channelProductDescriptionDTO !=null){
            description = channelProductDescriptionDTO.getContent();
        }
        double price = batchDTO.getPrice().doubleValue();
        gvo.setCartItemId(reqVO.getCartItemId());
        gvo.setNum(num);
        gvo.setPrice(price);
        gvo.setPuId(puId);
        gvo.setSkuDesc(description);
        gvo.setPuImg(channelProductDetailVO.getProductImg());
        gvo.setPuName(channelProductDTO.getTitle()+batchDTO.getSpecName());
        gvo.setBuyType(1);
        gvo.setExternalSkuId(externalSkuId);
        gvo.setStandardUnitId(puId);
        gvo.setLimitBuyNum(1L);
        gvo.setIsOwnMerchant(0);
        gvo.setSource(ThirdConst.Source.WORLD);
        gvo.setExternalProductId(channelProductDTO.getProductId());
        if(!flag){
            orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
            //立即购买,单个商品,设置仅现金支付金额
            BigDecimal orderAmountOnlyCash = new BigDecimal(0);
            //如果商品是积分+现金则设置为0
            result.setOrderAmountOnlyCash(orderAmountOnlyCash);
            goodsList.add(gvo);
        }
        MerchantDTO merchant = merchantFacade.findMerchantById(ThirdConst.Merchant.WORLD);
        setCommonResult(result, payByFuBiAmount, goodsAccount, orderAmount, goodsList, jdDownGoods, limitRuleRecordList,merchant);
        return result;
    }

    @Override
    public OrderResult buyProductByCart(OrderConfirmReqVO reqVO) {
        return buyPrductByRightNow(reqVO);
    }
}
