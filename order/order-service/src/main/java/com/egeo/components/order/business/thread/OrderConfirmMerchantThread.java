package com.egeo.components.order.business.thread;

import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.OrderConfirmGoodsDTO;
import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.facade.MerchantFacade;
import com.egeo.components.order.facade.StoreFacade;
import com.egeo.components.order.strategy.vo.OrderConfirmMerchantThreadReqVo;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;
import com.egeo.components.order.vo.jd.ParseAddressJson;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SpringContextTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 18:15
 * @Version V1.0
 **/
public class OrderConfirmMerchantThread implements Callable<OrderResult> {
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    OrderConfirmMerchantThreadReqVo reqVo;
    public OrderConfirmMerchantThread(OrderConfirmMerchantThreadReqVo reqVo){
        this.reqVo = reqVo;
    }


    @Override
    public OrderResult call() {
        Long time1 = System.currentTimeMillis();
        OrderResult rt = buyProductByCartNew(reqVo.getParseAddressJson(),reqVo.getAddress(),reqVo.getClientId(),reqVo.getCompanyType(),reqVo.getCartItemList(),reqVo.getPuId(),reqVo.getUserId(),reqVo.getPlatformId(),reqVo.getStoreId(),reqVo.getMerchantId());
        Long time2 = System.currentTimeMillis();
        logger.info("商户确认订单商品耗时情况id:{}耗时:{}",reqVo.getMerchantId(),(time2-time1));
        return rt;
    }

    //购物车下单生成限购规则,订单商品列表
    private OrderResult buyProductByCartNew(ParseAddressJson parseAddressJson, String address, Long clientId, Integer companyType, List<CartItemDTO> cartItemList, Long puId, Long userId, Long platformId, Long storeId, Long merchantId) {

        OrderResult result = new OrderResult();
        List<OrderConfirmGoodsDTO> goodsList = new ArrayList<>();
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        int goodsAccount = 0;//订单商品总数
        BigDecimal orderAmount = BigDecimal.ZERO;//订单总金额
        BigDecimal orderAmountOnlyCash = BigDecimal.valueOf(0);//仅现金支付金额
        MerchantFacade merchantFacade = SpringContextTool.getBean(MerchantFacade.class);
        MerchantDTO merchant = merchantFacade.findMerchantById(merchantId);
        List<OrderConfirmGoodsDTO> jdDownGoods = new ArrayList<>();
        result.setIsPayByFuBiOnly(0);
        result.setMerchant(merchant);
        BigDecimal payByFuBiAmount = BigDecimal.ZERO;

        // 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
        ThreadPoolExecutor executor = CommonChildThreadPoolExecutor.getInstance();
        // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
        CompletionService<OrderResult> completionService = new ExecutorCompletionService<>(executor);
        List<Future<OrderResult>> results = new ArrayList<>();
        Future<OrderResult> future = null;

        // 购物车下单
        for (CartItemDTO ci : cartItemList) {
            // 商品存在校验
            Long puId_ = ci.getProductUnitId();
            int num_ = ci.getNum().intValue();
            OrderConfirmReqVO reqVO = new OrderConfirmReqVO();
            reqVO.setCartItemId(ci.getId());
            reqVO.setEnterpriseId(reqVo.getEnterpriseId());
            reqVO.setParseAddressJson(parseAddressJson);
            reqVO.setAddress(address);
            reqVO.setClientId(clientId);
            reqVO.setCompanyType(companyType);
            reqVO.setPuId(puId_);
            reqVO.setNum(num_);
            reqVO.setStoreId(storeId);
            reqVO.setChannelProductId(ci.getChannelProductId());
            reqVO.setSource(ci.getSource());
            reqVO.setPlatformId(platformId);
            reqVO.setMemberId(userId);
            reqVO.setMerchantId(merchantId);
            future = completionService.submit(new OrderConfirmProductThread(reqVO));
            results.add(future);
        }

        for (Future<OrderResult> orderResultFuture : results) {
            try {
                OrderResult pOrderResult =  orderResultFuture.get();
                if(Objects.isNull(pOrderResult)){
                    continue;
                }
                int pNum = EmptyUtil.isNotEmpty(pOrderResult.getGoodsAccount())?pOrderResult.getGoodsAccount():0;
                BigDecimal pPayByFuBiAmount = EmptyUtil.isNotEmpty(pOrderResult.getPayByFuBiAmount())?pOrderResult.getPayByFuBiAmount():BigDecimal.ZERO;
                BigDecimal pOrderAmount = EmptyUtil.isNotEmpty(pOrderResult.getOrderAmount())?pOrderResult.getOrderAmount():BigDecimal.ZERO;
                BigDecimal pOrderAmountOnlyCash = EmptyUtil.isNotEmpty(pOrderResult.getOrderAmountOnlyCash())?pOrderResult.getOrderAmountOnlyCash():BigDecimal.ZERO;
                goodsAccount =  goodsAccount+pNum;
                payByFuBiAmount = payByFuBiAmount.add(pPayByFuBiAmount);
                orderAmount = orderAmount.add(pOrderAmount);
                orderAmountOnlyCash = orderAmountOnlyCash.add(pOrderAmountOnlyCash);
                if(EmptyUtil.isNotEmpty(pOrderResult.getGoodsList())){
                    goodsList.addAll(pOrderResult.getGoodsList());
                }
                if(EmptyUtil.isNotEmpty(pOrderResult.getLimitRuleRecordList())){
                    limitRuleRecordList.addAll(pOrderResult.getLimitRuleRecordList());
                }
                if(EmptyUtil.isNotEmpty(pOrderResult.getJdDownGoods())){
                    jdDownGoods.addAll(pOrderResult.getJdDownGoods());
                }
            }catch (Exception e){
                logger.error("执行OrderConfirmProductThread任务失败:{}",e);
            }
        }
        StoreFacade storeFacade = SpringContextTool.getBean(StoreFacade.class);
        StoreDTO store = storeFacade.findStoreById(storeId);
        result.setStore(store);
        result.setOrderAmountOnlyCash(orderAmountOnlyCash);
        result.setGoodsAccount(goodsAccount);
        result.setPayByFuBiAmount(payByFuBiAmount);
        result.setOrderAmount(orderAmount);
        result.setLimitRuleRecordList(limitRuleRecordList);
        result.setGoodsList(goodsList);
        result.setJdDownGoods(jdDownGoods);
        result.setMerchant(merchant);
        result.setSuccess(true);
        return result;

    }
}
