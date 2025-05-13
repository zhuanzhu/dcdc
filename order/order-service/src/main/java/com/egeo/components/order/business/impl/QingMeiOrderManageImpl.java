package com.egeo.components.order.business.impl;

import com.alibaba.fastjson.JSON;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.business.QingMeiOrderManage;
import com.egeo.components.order.business.SoManage;
import com.egeo.components.order.business.SoRefundNewManage;
import com.egeo.components.order.dao.write.SoPackageWriteDAO;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.facade.UserFacade;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.components.order.service.SoService;
import com.egeo.components.order.service.read.*;
import com.egeo.components.order.service.write.QmOrderWriteService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoItemWriteService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.utils.QingMeiUtil;
import com.egeo.components.utils.StringUtil;
import com.egeo.components.utils.ValidateUtil;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.exception.BusinessException;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.web.JsonResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 清美订单相关接口
 */
@Service
public class QingMeiOrderManageImpl implements QingMeiOrderManage {
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private QingMeiUtil qingMeiUtil;

    @Resource
    private SoManage soManage;
    @Resource
    private SoService soService;
    @Resource
    private UserFacade userFacade;
    @Resource
    private QmOrderReadService qmOrderReadService;
    @Resource
    private QmOrderWriteService qmOrderWriteService;
    @Resource
    private SoChildReadService soChildReadService;
    @Resource
    private SoChildWriteService soChildWriteService;
    @Resource
    private SoWriteService soWriteService;
    @Resource
    private SoReadService soReadService;
    @Resource
    private SoItemReadService soItemReadService;
    @Resource
    private SoItemWriteService soItemWriteService;
    @Resource
    private SoRefundReadService soRefundReadService;
    @Resource
    private PushOrderManage pushOrderManage;
    @Resource
    private SoRefundNewManage soRefundNewManage;
    @Resource
    private SoPackageWriteDAO soPackageWriteDAO;
    @Resource
    private SoPackageReadService soPackageReadService;
    /**
     * 统一下单
     * @param orderDTO
     * @param req
     * @return
     */
    @Override
    @Transactional
    public String unifiedOrderWithTx(QingMeiOrderDTO orderDTO, HttpServletRequest req) {
        QmOrderDTO qmOrderDTO=qmOrderReadService.findByOutTradeNo(orderDTO.getOutTradeNo());
        if (Objects.nonNull(qmOrderDTO)){
            return qmOrderDTO.getOrderCode();
        }
        if (EmptyUtil.isEmpty(orderDTO.getOpenId())){
            throw new BusinessException("openId不可为空");
        }
        UserDTO userDTO=userFacade.findUserById(Long.valueOf(orderDTO.getOpenId()));
        if (Objects.isNull(userDTO)){
            throw new BusinessException("openId:"+orderDTO.getOpenId()+"对应的用户不存在");
        }
        checkOrderDetail(orderDTO);
        CompanyDTO companyDTO=userFacade.queryCompanyById(userDTO.getCompanyId());
        String ip = HostUtils.getClientIP(req);

        Long platformId = companyDTO.getPlatformId();

        Long storeId =null;
        if(platformId==7){
            logger.info("大厨管家订单");
            storeId=1L;
        }
        if(platformId==2){
            logger.info("富宏云采订单");
            storeId = 2L;
        }
        // 根据ip获取mac地址
        String mac;
        try {
            mac = HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        CreateOrderDTO createOrderDTO=new CreateOrderDTO();
        createOrderDTO.setUserId(userDTO.getId());
        createOrderDTO.setCompanyId(companyDTO.getId());
        createOrderDTO.setStoreId(storeId);
        createOrderDTO.setMac(mac);
        createOrderDTO.setIp(ip);
        createOrderDTO.setPlatformId(platformId);
        createOrderDTO.setType(6);
        createOrderDTO.setThirdOrderJsonStr(JSON.toJSONString(orderDTO));
        createOrderDTO.setOrderChannel(2);
        createOrderDTO.setDeviceId("0001");
        createOrderDTO.setPhoneModel("1.0.0");
        createOrderDTO.setVersionCode("1.0.0");
        createOrderDTO.setOs("1.0.0");
        //创建订单操作
        JsonResult<Map<String, Object>> result = soManage.createOrder(req,createOrderDTO);
        String orderCode;
        if (Objects.equals(result.getCode(),0)) {
            orderCode=(String) result.getData().get("orderCode");
            saveQmOrder(orderDTO,orderCode);
            //记录创建订单日志
            recordBusinessLog(orderCode,req);
        }else {
            throw new BusinessException(result.getError());
        }
        return orderCode;
    }

    /**
     * 订单状态更新
     * @param statusUpdateDTO
     * @param req
     */
    @Override
    @Transactional
    public void orderStateWithTx(QmOrderStatusUpdateDTO statusUpdateDTO,HttpServletRequest req) {
        boolean validSign=qingMeiUtil.checkSign(statusUpdateDTO.toMap());
        if (!validSign){
            throw new BusinessException("sign签名不正确");
        }
        QmOrderDTO qmOrderDTO=qmOrderReadService.findByOutTradeNo(statusUpdateDTO.getOutTradeNo());
        if (Objects.isNull(qmOrderDTO)){
            throw new BusinessException("外部交易单号outTradeNo:"+statusUpdateDTO.getOutTradeNo()+"对应的订单不存在");
        }
        Long childId=soChildReadService.findSoChildIdByThirdpartyId(Long.valueOf(statusUpdateDTO.getOrderNo()));
        if (Objects.isNull(childId)){
            throw new BusinessException("子订单号orderNo:"+statusUpdateDTO.getOrderNo()+"不存在");
        }
        SoChildDTO childDTO=soChildReadService.findSoChildById(childId);
        SoDTO soDTO=soReadService.querySoById(childDTO.getSoId());
        boolean statusChange=false;
        if (Objects.equals(statusUpdateDTO.getOrderState(),QmOrderDTO.OrderState.CANCEL)){
            if (Objects.equals(childDTO.getCancelStatus(),1)||
                    Objects.equals(soDTO.getOrderStatus(), OrderConstant.ORDER_STATUS_CANCELED.getStatus())){
                return;
            }
            SoChildDTO updateDTO = new SoChildDTO();
            updateDTO.setCancelStatus(Integer.valueOf(1));
            updateDTO.setId(childDTO.getId());
            updateDTO.setThirdpartySoChildStatus(Integer.valueOf(statusUpdateDTO.getOrderState()));
            soChildWriteService.updateSoChildWithTx(updateDTO);
            SoChildDTO dto = new SoChildDTO();
            dto.setSoId(soDTO.getId());
            List<SoChildDTO> childDTOS= soChildReadService.findSoChildAll(dto);
            boolean allCancel=true;
            for (SoChildDTO child:childDTOS){
                if (!Objects.equals(child.getCancelStatus(), 1)){
                    allCancel=false;
                    break;
                }
            }
            if (allCancel){
                if (!Objects.equals(soDTO.getOrderStatus(), OrderConstant.ORDER_STATUS_CANCELED.getStatus())){
                    SoDTO newSo = new SoDTO();
                    newSo.setId(soDTO.getId());
                    newSo.setOrderStatus(OrderConstant.ORDER_STATUS_CANCELED.getStatus());
                    newSo.setOrderConfirmStatus(OrderConstant.ORDER_CONFIRM_STATUS_ALREADY_CANCEL.getStatus());
                    soWriteService.updateOrderWithTX(newSo);
                }
            }
            statusChange=true;
        } else if (Objects.equals(statusUpdateDTO.getOrderState(),QmOrderDTO.OrderState.SHIP)) {
            if (!Objects.equals(soDTO.getOrderPayStatus(), OrderConstant.ORDER_STATUS_PAYED.getStatus())){
                throw new BusinessException("子订单号orderNo:"+statusUpdateDTO.getOrderNo()+"状态未付款,不可更新发货状态");
            }
            SoChildDTO dto = new SoChildDTO();
            dto.setDeliveryStatus(Integer.valueOf(2));
            dto.setId(childDTO.getId());
            dto.setThirdpartySoChildStatus(Integer.valueOf(statusUpdateDTO.getOrderState()));
            soChildWriteService.updateSoChildWithTx(dto);
            if (Objects.equals(soDTO.getOrderStatus(),OrderConstant.ORDER_STATUS_UNPAY.getStatus())||
                    Objects.equals(soDTO.getOrderStatus(),OrderConstant.ORDER_STATUS_PAYED.getStatus())){
                SoDTO newSo = new SoDTO();
                newSo.setId(soDTO.getId());
                newSo.setOrderStatus(Integer.valueOf(2));
                soWriteService.updateOrderWithTX(newSo);
            }
            saveDeliveryTrace(soDTO,childDTO);
            statusChange=true;
        } else if (Objects.equals(statusUpdateDTO.getOrderState(),QmOrderDTO.OrderState.FINISH)) {
            if (Objects.equals(soDTO.getOrderPayStatus(), OrderConstant.ORDER_STATUS_UNPAY.getStatus())){
                throw new BusinessException("子订单号orderNo:"+statusUpdateDTO.getOrderNo()+"状态未付款,不可更新完成状态");
            }
            if (Objects.equals(soDTO.getOrderStatus(),OrderConstant.ORDER_STATUS_PAYED.getStatus())
                    && Objects.equals(childDTO.getDeliveryStatus(),2)){
                SoChildDTO dto = new SoChildDTO();
                dto.setDeliveryStatus(Integer.valueOf(3));
                dto.setId(childDTO.getId());
                dto.setThirdpartySoChildStatus(Integer.valueOf(statusUpdateDTO.getOrderState()));
                soChildWriteService.updateSoChildWithTx(dto);
                signDeliveryTrace(childId);
            }
            if (Objects.equals(soDTO.getOrderStatus(),2)||
                    Objects.equals(soDTO.getOrderStatus(),OrderConstant.ORDER_STATUS_PAYED.getStatus())){
                SoDTO newSo = new SoDTO();
                newSo.setId(soDTO.getId());
                newSo.setOrderStatus(Integer.valueOf(4));
                newSo.setOrderConfirmStatus(3);
                soWriteService.updateOrderWithTX(newSo);
            }
            statusChange=true;
        }else {
            throw new BusinessException("子订单号orderNo:"+statusUpdateDTO.getOrderNo()+"状态["+statusUpdateDTO.getOrderState()+"]不正确");
        }
        //推送dlf订单状态变更
        if (statusChange && Objects.nonNull(soDTO)){
            pushOrderManage.pushOrderInfo(soDTO.getId(),null,null);
        }
    }


    /**
     * 订单退款
     * @param refundDTO
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String orderRefundWithTx(QmOrderRefundDTO refundDTO, HttpServletRequest req) {
        QmOrderDTO qmOrderDTO=qmOrderReadService.findByOutTradeNo(refundDTO.getOutTradeNo());
        if (Objects.isNull(qmOrderDTO)){
            throw new BusinessException("外部交易单号outTradeNo:"+refundDTO.getOutTradeNo()+"对应的订单不存在");
        }
        SoRefundDTO findSoRefundDTO=new SoRefundDTO();
        findSoRefundDTO.setThirdRefundCode(refundDTO.getRefundNo());
        List<SoRefundDTO> soRefunds=soRefundReadService.findSoRefundAll(findSoRefundDTO);
        if (EmptyUtil.isNotEmpty(soRefunds)){
            return soRefunds.get(0).getSoRefundCode();
        }
        Long childId=soChildReadService.findSoChildIdByThirdpartyId(Long.valueOf(refundDTO.getOrderNo()));
        if (Objects.isNull(childId)){
            throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"不存在");
        }
        SoChildDTO soChildDTO=soChildReadService.findSoChildById(childId);
        SoDTO soDTO=soReadService.querySoById(soChildDTO.getSoId());
        if (Objects.isNull(soDTO)|| !Objects.equals(refundDTO.getTradeNo(),soDTO.getOrderCode())){
            throw new BusinessException("外部交易单号outTradeNo:"+refundDTO.getOutTradeNo()+"对应的收银台订单号tradeNo:"+refundDTO.getTradeNo()+"不存在");
        }
        if (!Objects.equals(soDTO.getOrderPayStatus(), OrderConstant.ORDER_STATUS_PAYED.getStatus())){
            throw new BusinessException("外部交易单号outTradeNo:"+refundDTO.getOutTradeNo()+"对应的订单未支付");
        }
        //剩余可退现金
        BigDecimal remainRefundAmount=soChildDTO.getThirdpartySoChildPayAmount().subtract(soChildDTO.getRefundAmount());
        if (refundDTO.getRefundAmount().compareTo(remainRefundAmount)>0){
            throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"本次退款金额大于可退款金额");
        }
        //是否退运费
        boolean isRefundFee=Objects.nonNull(refundDTO.getRefundFee()) && refundDTO.getRefundFee().compareTo(BigDecimal.ZERO)>0;
        if (isRefundFee){
            BigDecimal remainRefundDeliveryFee=soChildDTO.getDeliveryFee().subtract(soChildDTO.getRefundDeliveryFee());
            if (refundDTO.getRefundFee().compareTo(remainRefundDeliveryFee)>0){
                throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"本次退运费金额大于可退款运费金额");
            }
        }
        BigDecimal totalRefundDeliveryFee=BigDecimal.ZERO;
        BigDecimal totalGoodsRefundAmount=BigDecimal.ZERO;
        SoItemDTO findSoItem=new SoItemDTO();
        findSoItem.setSoChildId(childId);
        List<SoItemDTO> soItemDTOS=soItemReadService.findAll(findSoItem);
        Map<String,SoItemDTO> soItemMap=new HashMap<>();
        soItemDTOS.forEach(item->soItemMap.put(item.getPuId()+"_"+item.getSubBizId(),item));
        //是否需要重新计算商品运费(当不是整单退时，部分单个商品推完后需重新计算商品平摊运费)
        boolean isRecalculateItemFee=false;
        //退款商品明细信息
        List<SoItemDTO> refundItems=new ArrayList<>();
        for (QmRefundItemDTO refundItemDTO:refundDTO.getGoodsList()){
            String key=refundItemDTO.getSkuCode()+"_"+refundItemDTO.getSubBizId();
            SoItemDTO soItemDTO=soItemMap.get(key);
            if (Objects.isNull(soItemDTO)){
                throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"商品明细中不存在该skuCode:"+refundItemDTO.getSkuCode());
            }
            Integer remainRefundCount=soItemDTO.getPuCount()-soItemDTO.getRefundCount();
            if (Objects.equals(1,refundItemDTO.getQuantity())){
                if (refundItemDTO.getGoodsRefundPrice().compareTo(refundItemDTO.getPrice())<0){
                    refundItemDTO.setQuantity(0);
                }
            }
            if (refundItemDTO.getQuantity()>remainRefundCount){
                throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"本次商品skuCode:"+refundItemDTO.getSkuCode()+"退款数量大于可退款数量");
            }
            BigDecimal remainItemRefundAmount=(soItemDTO.getPrice().multiply(new BigDecimal(soItemDTO.getPuCount()))
                    .subtract(soItemDTO.getFinalPromotionAver())).subtract(soItemDTO.getRefundAmount());
            if (refundItemDTO.getGoodsRefundPrice().compareTo(remainItemRefundAmount)>0){
                throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"本次商品skuCode:"+refundItemDTO.getSkuCode()+"退款金额大于可退款金额");
            }
            soItemDTO.setRefundCount(soItemDTO.getRefundCount()+refundItemDTO.getQuantity());
            soItemDTO.setRefundAmount(soItemDTO.getRefundAmount().add(refundItemDTO.getGoodsRefundPrice()));
            if (isRefundFee){
                totalRefundDeliveryFee=totalRefundDeliveryFee.add(soItemDTO.getDeliveryFeeAver());
                soItemDTO.setRefundDeliveryFee(soItemDTO.getDeliveryFeeAver());
            }else {
                if (Objects.equals(0,refundItemDTO.getQuantity())){
                    BigDecimal productAmount=soItemDTO.getPrice().multiply(new BigDecimal(soItemDTO.getPuCount()))
                            .subtract(StringUtil.nullToZero(soItemDTO.getFinalPromotionAver()));
                    if (StringUtil.nullToZero(soItemDTO.getRefundAmount()).compareTo(productAmount) ==0){
                        Integer quantity=soItemDTO.getPuCount()-soItemDTO.getRefundCount();
                        refundItemDTO.setQuantity(quantity<0?soItemDTO.getPuCount():quantity);
                        soItemDTO.setRefundCount(soItemDTO.getPuCount());
                    }
                }
                if (Objects.equals(soItemDTO.getPuCount(),soItemDTO.getRefundCount())){
                    isRecalculateItemFee=true;
                }
            }
            totalGoodsRefundAmount=totalGoodsRefundAmount.add(refundItemDTO.getGoodsRefundPrice());
//            soItemWriteService.updateSoItemWithTx(soItemDTO);
            SoItemDTO refundSoItemDTO=soItemDTO.copyThis();
            refundSoItemDTO.setId(soItemDTO.getId());
            refundSoItemDTO.setSoChildId(soItemDTO.getSoChildId());
//            refundSoItemDTO.setPuCount(refundItemDTO.getQuantity());
//            refundSoItemDTO.setPrice(refundItemDTO.getPrice());
//            refundSoItemDTO.setRefundDeliveryFee(soItemDTO.getRefundDeliveryFee());
//            refundSoItemDTO.setGoodsRefundPrice(refundItemDTO.getGoodsRefundPrice());
            refundSoItemDTO.setRefundCount(refundItemDTO.getQuantity());
            refundSoItemDTO.setRefundAmount(refundItemDTO.getGoodsRefundPrice().add(soItemDTO.getRefundDeliveryFee()));
            refundSoItemDTO.setSource(soItemDTO.getSource());
            refundItems.add(refundSoItemDTO);
        }
        BigDecimal refundFee=Objects.isNull(refundDTO.getRefundFee())?BigDecimal.ZERO:refundDTO.getRefundFee();
        if (refundDTO.getRefundAmount().compareTo(totalGoodsRefundAmount.add(refundFee))!=0){
            throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"退款总金额与明细金额不匹配");
        }
        if (isRefundFee){
            if (refundDTO.getRefundFee().compareTo(totalRefundDeliveryFee)!=0){
                throw new BusinessException("子订单号orderNo:"+refundDTO.getOrderNo()+"本次退运费金额与付款运费金额不一致");
            }
        }
        //处理母订单已退金额
        //可退现金
        BigDecimal remainRefundCash=soDTO.getOrderPaidByCash().subtract(soDTO.getRefundCash());
        //可退积分
        BigDecimal remainRefundFubi=soDTO.getOrderPaidByFubi().subtract(soDTO.getRefundFubi());
        //可退积点
        BigDecimal remainRefundJiDian=soDTO.getOrderPaidByJidian().subtract(soDTO.getRefundJidian());
        //可退卡劵金额
        BigDecimal remainRefundCardAmount = soDTO.getOrderCardPaid().subtract(soDTO.getRefundCard());
        //可退总数
        BigDecimal remainTotalAmount=remainRefundCash.add(remainRefundFubi).add(remainRefundJiDian).add(remainRefundCardAmount);

        if (refundDTO.getRefundAmount().compareTo(remainTotalAmount)>0){
            throw new BusinessException("外部交易单号outTradeNo:"+refundDTO.getOutTradeNo()+"退款金额大于可退款总金额");
        }
        logger.info("清美实际发起退款前refundItems:{}",JSON.toJSONString(refundItems));
        CancelAndRefundOrderExtendsWithTxDTO dto = new CancelAndRefundOrderExtendsWithTxDTO();
        dto.setReason("清美发起退款");
        dto.setOrder(soDTO);
        dto.setItems(refundItems);
        dto.setReq(new HttpServletRequestDTO(req));
        dto.setUserId(0l);
        dto.setAutoRefundCash(true);
        dto.setThirdRefundCode(refundDTO.getRefundNo());
        Map<String, Object> refundResultMap=soRefundNewManage.productRefundOrderWithTx(dto);
        return refundResultMap.get("refundNo").toString();
    }

    @Override
    public void verifyUnifiedOrder(QingMeiOrderDTO orderDTO) {
        Set<String>  errors=new HashSet<>();
        if (Objects.nonNull(orderDTO)){
            Set<String> errorOrders=ValidateUtil.valid(orderDTO);
            if (EmptyUtil.isNotEmpty(errorOrders)){
                errors.addAll(errorOrders);
            }
            if (EmptyUtil.isNotEmpty(orderDTO.getTradeList())){
                orderDTO.getTradeList().forEach(trade->{
                    Set<String> errorTrades=ValidateUtil.valid(trade);
                    if (EmptyUtil.isNotEmpty(errorTrades)){
                        errors.addAll(errorTrades);
                    }
                    if (EmptyUtil.isNotEmpty(trade.getGoodsList())){
                        trade.getGoodsList().forEach(good->{
                            Set<String> errorGoods=ValidateUtil.valid(good);
                            if (EmptyUtil.isNotEmpty(errorGoods)){
                                errors.addAll(errorGoods);
                            }
                        });
                    }
                });
            }
        }
        if (EmptyUtil.isNotEmpty(errors)){
            String errorMsg= StringUtils.join(errors,";");
            throw new BusinessException(errorMsg);
        }
    }

    @Override
    public void verifyOrderState(QmOrderStatusUpdateDTO statusUpdateDTO) {
        Set<String>  errors=new HashSet<>();
        if (Objects.nonNull(statusUpdateDTO)) {
            Set<String> errorUpdates = ValidateUtil.valid(statusUpdateDTO);
            if (EmptyUtil.isNotEmpty(errorUpdates)) {
                errors.addAll(errorUpdates);
            }
        }
        if (EmptyUtil.isNotEmpty(errors)){
            String errorMsg= StringUtils.join(errors,";");
            throw new BusinessException(errorMsg);
        }
    }


    @Override
    public void verifyOrderRefund(QmOrderRefundDTO refundDTO) {
        Set<String>  errors=new HashSet<>();
        if (Objects.nonNull(refundDTO)) {
            Set<String> errorRefunds = ValidateUtil.valid(refundDTO);
            if (EmptyUtil.isNotEmpty(errorRefunds)) {
                errors.addAll(errorRefunds);
            }
            if (EmptyUtil.isNotEmpty(refundDTO.getGoodsList())){
                refundDTO.getGoodsList().forEach(good->{
                    Set<String> errorGoods=ValidateUtil.valid(good);
                    if (EmptyUtil.isNotEmpty(errorGoods)){
                        errors.addAll(errorGoods);
                    }
                });
            }
        }
        if (EmptyUtil.isNotEmpty(errors)){
            String errorMsg= StringUtils.join(errors,";");
            throw new BusinessException(errorMsg);
        }
    }

    @Override
    public void checkSign(Map<String, String> params) {
        boolean validSign=qingMeiUtil.checkSign(params);
        if (!validSign){
            throw new BusinessException("sign签名不正确");
        }
    }

    private void checkOrderDetail(QingMeiOrderDTO orderDTO){
        List<QingMeiChildOrderDTO> childOrderDTOS=orderDTO.getTradeList();
        BigDecimal totalAmount=BigDecimal.ZERO;
        for (QingMeiChildOrderDTO tradeDTO:childOrderDTOS){
            totalAmount=totalAmount.add(tradeDTO.getOrderAmount());
            BigDecimal orderAmount=tradeDTO.getGoodsAmount().add(tradeDTO.getShippingFee()).subtract(tradeDTO.getDiscountAmount());
            if (orderAmount.compareTo(tradeDTO.getOrderAmount())!=0){
                throw new BusinessException("子订单金额匹配");
            }
            BigDecimal totalGoodsPayPrice=BigDecimal.ZERO;
            for (QingMeiChildItemDTO itemDTO:tradeDTO.getGoodsList()){
                totalGoodsPayPrice=totalGoodsPayPrice.add(itemDTO.getGoodsPayPrice());
            }
            if (totalGoodsPayPrice.add(tradeDTO.getShippingFee()).compareTo(tradeDTO.getOrderAmount())!=0){
                throw new BusinessException("子订单商品明细金额不匹配");
            }
        }
        if (totalAmount.compareTo(orderDTO.getTotalAmount())!=0){
            throw new BusinessException("订单总金额与子订单金额不匹配");
        }
    }
    /**
     * 保存清美订单信息
     * @param qmOrder
     * @param orderCode
     */
    private int saveQmOrder(QingMeiOrderDTO qmOrder,String orderCode){
        SoDTO soDTO=soManage.querySoByOrderCode(orderCode);
        QmOrderDTO qmOrderDTO =new QmOrderDTO();
        qmOrderDTO.setSoId(soDTO.getId());
        qmOrderDTO.setOutTradeNo(qmOrder.getOutTradeNo());
        qmOrderDTO.setOrderCode(orderCode);
        qmOrderDTO.setRemark(qmOrder.getAttach());
        qmOrderDTO.setReturnUrl(qmOrder.getReturnUrl());
        qmOrderDTO.setNotifyUrl(qmOrder.getNotifyUrl());
        qmOrderDTO.setOrderTime(DateUtils.parseDate(qmOrder.getOrderTime()));
        qmOrderDTO.setExpireTime(DateUtils.parseDate(qmOrder.getExpireTime()));
        int rst=qmOrderWriteService.saveOrder(qmOrderDTO);
        if (rst!=1){
            throw new BusinessException("保存订单数据失败");
        }
        return rst;
    }


    /**
     *记录创建订单日志
     * @param orderCode
     * @param req
     */
    private void recordBusinessLog(String orderCode,HttpServletRequest req){
        SoDTO newSoDTO = soManage.querySoByOrderCode(orderCode);
        EgeoLog log = new EgeoLog();
        log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
        log.setOperObject("QingMeiOrderAction_unifiedOrder");
        log.setMsgId(LogConstant.ORDER_NEW.getStatus());
        log.setType(LogTypeConstant.SO.getStatus());
        log.setOperatorObjId(newSoDTO.getId());
        log.setOperatorObjCode(newSoDTO.getOrderCode());
        log.setNewObj(newSoDTO);
        EgeoBusinessLogCommon.fillLogValue(log, req);
        try {
            ActiveMQUtils.recordBusinessLog(log);
        }catch (Exception e) {
            logger.error("发送日志消息失败："+ JSON.toJSONString(log));
        }
    }

    /**
     * 保存物流信息
     * @param soDTO
     * @param childDTO
     */
    private void saveDeliveryTrace(SoDTO soDTO,SoChildDTO childDTO){
        try {
            List<SoPackageDTO> packageDTOS=soPackageReadService.queryPackageBySoChildId(childDTO.getId());
            if (EmptyUtil.isNotEmpty(packageDTOS)){
                return;
            }
            SoPackagePO pack=new SoPackagePO();
            pack.setDeliveryMode(2);
            pack.setDeliveryStatus(2);
            pack.setDeliveryDate(new Date());
            pack.setOrderCode(soDTO.getOrderCode());
            pack.setPackageType(1);
            pack.setPlatformId(soDTO.getPlatformId());
            pack.setSoChildId(childDTO.getId());
            pack.setSoId(soDTO.getId());
            pack.setUserId(soDTO.getUserId());
            pack.setMerchantId(ThirdConst.Merchant.QM);
            soPackageWriteDAO.insert(pack);
        }catch (Exception e){
            logger.error("saveDeliveryTrace,childId-{},error-{}",childDTO.getId(),e.getMessage());
        }
    }

    private void signDeliveryTrace(Long childId){
        try {
            List<SoPackageDTO> packageDTOS=soPackageReadService.queryPackageBySoChildId(childId);
            if (EmptyUtil.isEmpty(packageDTOS)){
                return;
            }
            SoPackagePO packCond=new SoPackagePO();
            packCond.setId(packageDTOS.get(0).getId());
            packCond.setDeliveryStatus(3);
            packCond.setSignDate(new Date());
            soPackageWriteDAO.update(packCond);
        }catch (Exception e){
            logger.error("signDeliveryTrace,childId-{},error-{}",childId,e.getMessage());
        }
    }
}
