package com.egeo.components.order.strategy.service.impl.channel;

import com.egeo.common.RechargePhoneErrorCode;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.facade.*;
import com.egeo.components.order.facade.utils.UserMembershipCheckUtils;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;
import com.egeo.components.product.dto.*;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 自营商品确认
 * @Author lsl
 * @Date 2024/12/1 14:12
 * @Version V1.0
 **/
@Service("orderConfirmSelfImpl")
public class OrderConfirmSelfImpl extends OrderConfirmChannelBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private JedisUtil jedisUtil;
    @Resource(name = "merchantFacade")
    private MerchantFacade merchantFacade;

    @Resource(name = "productFacade")
    private ProductFacade productFacade;

    @Resource(name = "storeFacade")
    private StoreFacade storeFacade;

    @Resource(name = "soFacade")
    private SoFacade soFacade;

    @Resource(name = "commodityProductUnitFacade")
    private CommodityProductUnitFacade commodityProductUnitFacade;

    @Autowired
    private RechargePhoneUtil rechargePhoneUtil;

    @Resource(name = "productUnitFacade")
    private ProductUnitFacade productUnitFacade;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.SELF.getCode();
    }

    @Override
    public OrderResult buyPrductByRightNow(OrderConfirmReqVO reqVO) {
        OrderResult result = new OrderResult();
        result.setIsPayByFuBiOnly(0);
        OrderConfirmGoodsDTO gvo = new OrderConfirmGoodsDTO();
        Boolean flag = false;
        Long  puId = reqVO.getPuId();
        Integer num = reqVO.getNum();
        Long storeId = reqVO.getStoreId();
        CommodityProductUnitDTO pu = reqVO.getPu();
        if(pu == null){
             pu = productFacade.queryPuById(puId);
        }
        Long commodityTemplateId = reqVO.getCommodityTemplateId();
        Integer companyType = reqVO.getCompanyType();
        String phone = reqVO.getPhone();
        Long clientId = reqVO.getClientId();
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        //订单商品总数
        int goodsAccount = num;
        //订单总金额
        BigDecimal orderAmount = BigDecimal.ZERO;

        // 判断是否是话费充值
        if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
            if (EmptyUtil.isEmpty(phone)) {
                result.setSuccess(false);
                result.setError("手机号码不能为空");
                return result;
            }
            if (!StringUtils.validMobile(phone)) {
                result.setSuccess(false);
                result.setError("请输入正确的手机号码");
                return result;
            }
            // 默认商品数量为1
            num = Integer.valueOf(1);
        }

        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
            if (pu == null) {
                result.setSuccess(false);
                result.setError("商品不存在");
                return result;
            }
            result.setIsPayByFuBiOnly(0);
            StandardUnitDTO standardUnitDTO=productFacade.findStandardUnit(pu.getStandardUnitId());
            if(standardUnitDTO.getBuyType()==3){
                result.setIsPayByFuBiOnly(1);
            }
            MerchantDTO merchant = merchantFacade.findMerchantById(standardUnitDTO.getMerchantId());
            result.setMerchant(merchant);
            StoreDTO store = storeFacade.findStoreById(storeId);
            result.setStore(store);

            if(storeId==1L||storeId==2L){
                //总店
                if(!standardUnitDTO.getStoreId().equals(storeId)){
                    result.setSuccess(false);
                    result.setError("您提交的订单中有商品已被总店管理员移除");
                    return result;
                }

            }else{
                //门店
                List<StandardUnitStoreDTO> standardUnitStoreDTO= (List<StandardUnitStoreDTO>) productFacade.findStandUnitStore(pu.getStandardUnitId(),storeId);
                if(EmptyUtil.isEmpty(standardUnitStoreDTO)){
                    result.setSuccess(false);
                    result.setError("您提交的订单中有商品已被门店管理员移除");
                    return result;
                }
            }

            if (pu.getIsVendible() == 0) {
                // pu不可销售
                result.setSuccess(false);
                result.setError("无该规格商品");
                return result;
            }
            // 商品上架校验
            if (pu.getStatus().intValue() != 3&&clientId!=2) {
                result.setSuccess(false);
                result.setError("商品已下架");
                return result;
            }

            // 判断是否是话费充值,是,调用第三方接口校验手机号与面值是否被支持
            if (commodityTemplateId != null && (commodityTemplateId.equals(4L)||commodityTemplateId.equals(9L))) {
                int errorCode = -1;
                try {
                    if(companyType==0){
                        errorCode = rechargePhoneUtil.telCheck(phone, pu.getSalePrice().intValue());
                    }else if(companyType==1){
                        errorCode = rechargePhoneUtil.telCheck(phone, pu.getDemoSalePrice().intValue());

                    }else if(companyType==2){
                        errorCode = rechargePhoneUtil.telCheck(phone, pu.getCompetingSalePrice().intValue());
                    }

                } catch (Exception e) {
                    logger.error("手机号码校验错误", e);
                }
                if (errorCode != RechargePhoneErrorCode.REQUEST_SUCCESS.getCode()) {
                    result.setSuccess(false);
                    result.setError("请输入正确的手机号码");
                    return result;
                }
            }

            String puImg = pu.getPuPicUrl();
            if (EmptyUtil.isBlank(puImg)) {
                // pu图片查询逻辑()
                puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());

            }
            // 商品库存校验
            Long stock = productFacade.queryStockByPUId(puId);
            if (stock == null || stock.intValue() < num) {
                logger.info("商品库存不足，puId:" + puId + "，库存：" + stock);
                result.setSuccess(false);
                result.setError("库存不足");
                return result;
            }
            // 判断unit库存属性
            // 判断是否有unit库存
            boolean unitFlag = productFacade.queryIsUnit(puId);
            if (unitFlag) {
                Long unitStock = productFacade.queryUnitStockBySkuId(pu.getSkuId());
                if (unitStock == null || unitStock.intValue() < num) {
                    logger.info("sku商品库存不足,skuId" + pu.getSkuId() + "库存为：" + unitStock);
                    result.setSuccess(false);
                    result.setError("卡券库存不足");
                    return result;
                }
            }
            boolean isCard=productFacade.queryIsCard(puId);
            //校验门店库存
            //根据storeid和puid查询storeproductunitid
            if(storeId!=1L&&storeId!=2L){
                StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(storeId,puId);
                StorePuWarehouseStockDTO storeStock =soFacade.findStoreStock(storeProductUnitDTO.getId(),storeProductUnitDTO.getStoreId());
                Long storeStockNum = storeStock.getRealStockNum() - storeStock.getRealFrozenStockNum();
                if(storeStockNum<num){
                    logger.info("sku商品门店库存不足,skuId" + pu.getSkuId() + "库存为：" + storeStockNum);
                    result.setSuccess(false);
                    result.setError("库存不足");
                    return result;
                }
            }



            double price =0;
            if(companyType==0){
                price = pu.getSalePrice().doubleValue();
            }else if(companyType==1){
                price = pu.getDemoSalePrice().doubleValue();

            }else if(companyType==2){
                price = pu.getCompetingSalePrice().doubleValue();
            }

            SkuDTO skuDTO=productFacade.findSkuBySkuId(pu.getSkuId());

        String externalSkuId = (skuDTO !=null && EmptyUtil.isNotEmpty(skuDTO.getExternalSkuId()))?skuDTO.getExternalSkuId(): pu.getExternalSkuId();
        gvo.setCartItemId(reqVO.getCartItemId());
        gvo.setNum(num);
            gvo.setPrice(price);
            gvo.setPuId(puId);
            gvo.setPuImg(puImg);
            gvo.setPuName(pu.getName());
            gvo.setStandardUnitId(pu.getStandardUnitId());
            gvo.setUnit(unitFlag);
            gvo.setCard(isCard);
            gvo.setBuyType(standardUnitDTO.getBuyType());
            gvo.setExternalSkuId(externalSkuId);
            gvo.setLimitBuyNum(pu.getLimitBuyNum());

            if(standardUnitDTO.getBuyType()==3){
                result.setPayByFuBiAmount(BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(num)));
            }else{
                result.setPayByFuBiAmount(BigDecimal.ZERO);
            }
            if(pu.getLimitBuyNum()>=2){
                if(num<pu.getLimitBuyNum()){
                    throw new BusinessException("至少购买"+pu.getLimitBuyNum()+"件哦");
                }
            }
            if(standardUnitDTO.getSaleWay()==4||standardUnitDTO.getSaleWay()==6){
                gvo.setPreSaleDay(standardUnitDTO.getPresellPeriod());
                gvo.setSaleWay(standardUnitDTO.getSaleWay());
            }
            gvo.setIsOwnMerchant(merchant.getId().equals(1L) ? 1 : 0);

            //校验当前商品是否为京东商品,是的话校验是否下架失效(微信端展示商品,app端报错)

            if(!flag){
                orderAmount=orderAmount.add(BigDecimal.valueOf(num).multiply(BigDecimal.valueOf(price)));
                //立即购买,单个商品,设置仅现金支付金额
                BigDecimal orderAmountOnlyCash = new BigDecimal(0);
                if(standardUnitDTO.getBuyType().equals(2)){
                    //仅现金支付,此时商品的金额就是仅现金支付的金额
                    orderAmountOnlyCash = orderAmount;
                }
                //如果商品是积分+现金则设置为0
                result.setOrderAmountOnlyCash(orderAmountOnlyCash);
                goodsList.add(gvo);
            }

            // 限购规则记录
            LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
            lrro.setBuySum(Long.valueOf(num));
            lrro.setBuyMoneySum(BigDecimal.valueOf(price));
            lrro.setProductUnitId(puId);
            lrro.setStandardUnitId(pu.getStandardUnitId());
            limitRuleRecordList.add(lrro);
            logger.info("立即购买添加限购记录="+limitRuleRecordList.size());
        setCommonResult(result, payByFuBiAmount, goodsAccount, orderAmount, goodsList, jdDownGoods, limitRuleRecordList,merchant);
        return result;
    }

    @Override
    public OrderResult buyProductByCart(OrderConfirmReqVO reqVO) {
        OrderResult result = new OrderResult();
        result.setIsPayByFuBiOnly(0);
        OrderConfirmGoodsDTO gvo = new OrderConfirmGoodsDTO();
        Boolean flag = false;
        Long  puId = reqVO.getPuId();
        Integer num_ = reqVO.getNum();
        Long storeId = reqVO.getStoreId();
        Integer companyType = reqVO.getCompanyType();
        Long puId_ = reqVO.getPuId();
        Long userId = reqVO.getMemberId();
        Integer buyType =0;
        Long clientId = reqVO.getClientId();
        Long platformId = reqVO.getPlatformId();
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;
        int goodsAccount = 0;//订单商品总数
        BigDecimal orderAmount = BigDecimal.ZERO;//订单总金额
        BigDecimal orderAmountOnlyCash = BigDecimal.valueOf(0);//仅现金支付金额
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        MerchantDTO merchant = merchantFacade.findMerchantById(reqVO.getMerchantId());

        CommodityProductUnitDTO pu = productFacade.queryPuById(puId_);
        SkuDTO skuDTO=productFacade.findSkuBySkuId(pu.getSkuId());
        //根据公司类型区分价格
        double price_ =0;
        if (pu == null) {
            result.setError("商品不存在");
            result.setSuccess(false);
            return result;
        }
        StandardUnitDTO suByPuId = getSUByPuId(pu.getId());
        buyType = suByPuId.getBuyType();
        if(suByPuId.getBuyType()==3){
            result.setIsPayByFuBiOnly(1);
        }
        Map<Integer, String> integerStringMap = UserMembershipCheckUtils.checkUserMembershipAuthority(userId, suByPuId.getId(), suByPuId.getSaleWay(), platformId);
        Set<Integer> integers = integerStringMap.keySet();
        for (Integer i : integers) {
            if (i == 0) {
                result.setError(integerStringMap.get(i));
                result.setSuccess(false);
                return result;
            }
        }

        // 商品上架校验
        if(suByPuId.getMerchantId()!=6&&clientId!=2){
            //京东的上下架单独校验(且微信端也是单独校验)
            if (pu.getStatus().intValue() != 3) {
                result.setError("抱歉，该商品已下架");
                result.setSuccess(false);
                return result;
            }
        }

        String puImg = pu.getPuPicUrl();
        if (EmptyUtil.isBlank(puImg)) {
            // pu图片查询逻辑
            puImg = productFacade.queryPuNullImgUrl(pu.getSkuId());

        }
        // 商品库存校验
        Long stock = productFacade.queryStockByPUId(puId_);
        if (stock.intValue() < num_) {
            result.setError("库存不足");
            result.setSuccess(false);
            return result;
        }

        if(storeId!=1L&&storeId!=2L){
            StoreProductUnitDTO storeProductUnitDTO=storeFacade.findStorePuId(storeId,puId_);
            StorePuWarehouseStockDTO storeStock =soFacade.findStoreStock(storeProductUnitDTO.getId(),storeProductUnitDTO.getStoreId());
            Long storeStockNum = storeStock.getRealStockNum() - storeStock.getRealFrozenStockNum();
            if(storeStockNum<num_){
                logger.info("sku商品门店库存不足,skuId" + pu.getSkuId() + "库存为：" + storeStockNum);
                result.setSuccess(false);
                result.setError("库存不足");
                return result;
            }
        }
        //校验门店
        if(storeId==1L||storeId==2L){
            if(!suByPuId.getStoreId().equals(storeId)){
                result.setError("您提交的商品有被管理员移除");
                result.setSuccess(false);
                return result;

            }
        }else{
            List<StandardUnitStoreDTO> standUnitStore = productFacade.findStandUnitStore(suByPuId.getId(), storeId);
            if(EmptyUtil.isEmpty(standUnitStore)){
                result.setError("您提交的商品有被管理员移除");
                result.setSuccess(false);
                return result;
            }
        }



        if(companyType==0){
            price_ = pu.getSalePrice().doubleValue();
        }else if(companyType==1){
            price_ = pu.getDemoSalePrice().doubleValue();

        }else if(companyType==2){
            price_ = pu.getCompetingSalePrice().doubleValue();
        }
        //double price_ = pu.getSalePrice().doubleValue();

        gvo.setNum(num_);
        gvo.setCartItemId(reqVO.getCartItemId());
        gvo.setPrice(price_);
        gvo.setPuId(puId_);
        gvo.setPuImg(puImg);
        gvo.setPuName(pu.getName());
        gvo.setBuyType(suByPuId.getBuyType());
        gvo.setExternalSkuId(skuDTO.getExternalSkuId());
        gvo.setStandardUnitId(pu.getStandardUnitId());
        gvo.setLimitBuyNum(pu.getLimitBuyNum());
        if(suByPuId.getBuyType()==3){
            payByFuBiAmount = payByFuBiAmount.add(BigDecimal.valueOf(price_).multiply(BigDecimal.valueOf(num_)));
        }else{
            result.setPayByFuBiAmount(BigDecimal.ZERO);
        }
        if(pu.getLimitBuyNum()>=2){
            if(num_<pu.getLimitBuyNum()){
                throw new BusinessException("至少购买"+pu.getLimitBuyNum()+"件哦");
            }

        }

        if (suByPuId.getSaleWay() == 4 || suByPuId.getSaleWay() == 6) {
            gvo.setPreSaleDay(suByPuId.getPresellPeriod());
            gvo.setSaleWay(suByPuId.getSaleWay());

        }

        gvo.setIsOwnMerchant(merchant.getId().equals(1L) ? 1 : 0);
        // 限购规则记录
        LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
        lrro.setBuySum(Long.valueOf(num_));
        lrro.setBuyMoneySum(BigDecimal.valueOf(price_));
        lrro.setProductUnitId(puId);
        lrro.setStandardUnitId(pu.getStandardUnitId());
        limitRuleRecordList.add(lrro);

        if(!flag){
            goodsAccount += num_;
            orderAmount=orderAmount.add(BigDecimal.valueOf(num_).multiply(BigDecimal.valueOf(price_)));
            if(buyType.equals(2)){
                orderAmountOnlyCash = orderAmountOnlyCash.add(BigDecimal.valueOf(num_).multiply(BigDecimal.valueOf(price_)));
            }
            result.setOrderAmountOnlyCash(orderAmountOnlyCash);
            goodsList.add(gvo);
        }

        result.setMerchant(merchant);
        result.setGoodsList(goodsList);
        result.setLimitRuleRecordList(limitRuleRecordList);
        result.setOrderAmount(orderAmount);
        result.setGoodsAccount(goodsAccount);
        result.setPayByFuBiAmount(payByFuBiAmount);
        result.setJdDownGoods(jdDownGoods);
        result.setSuccess(true);
        return result;
    }


    //抽取的方法:根据puid查询su
    private StandardUnitDTO getSUByPuId(Long puId) {
        //根据puid查询pu信息
        CommodityProductUnitDTO puCond = new CommodityProductUnitDTO();
        puCond.setId(puId);
        CommodityProductUnitDTO pu = commodityProductUnitFacade.findCommodityProductUnitById(puCond);
        if (pu == null) {
            throw new BusinessException("商品不存在");
        }
        if (pu.getIsVendible() == 0) {
            //pu不可销售
            throw new BusinessException("无该规格商品");
        }
        //根据suid查询su信息
        StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
        standardUnitDTO.setId(pu.getStandardUnitId());

        StandardUnitDTO standardUnitDTO2 = productUnitFacade.findStandardUnitById(pu.getStandardUnitId());
        if (EmptyUtil.isNotEmpty(standardUnitDTO2)) {
            if (standardUnitDTO2.getStatus() != 3) {
                throw new BusinessException("商品已下架");
            }
        }
        return standardUnitDTO2;
    }
}
