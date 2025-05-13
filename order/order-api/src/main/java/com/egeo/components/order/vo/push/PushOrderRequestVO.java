package com.egeo.components.order.vo.push;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class PushOrderRequestVO implements Serializable {

    /**
     * 订单编号
     **/
    private String orderCode;

    /**
     * 下单人姓名
     **/
    private String userName;

    /**
     * 下单手机号
     **/
    private String userMobile;

    /**
     * 订单状态
     **/
    private Integer orderStatus;

    /**
     * 订单创建时间yyyy-mm-dd hh:mi:ss
     **/
    private String createTime;

    /**
     * 运费金额
     **/
    private String orderDeliveryFee;

    /**
     * 支付时间yyyy-mm-dd hh:mi:ss
     **/
    private String payTime;

    /**
     * 订单金额
     **/
    private String orderAmount;

    /**
     * 订单优惠金额
     **/
    private String orderPromotionDiscount;

    /**
     * 订单实付金额
     **/
    private String orderAmountPay;

    /**
     * 收货人
     **/
    private String receiverUser;

    /**
     * 收货手机号
     **/
    private String receiverPhone;

    /**
     * 收货地址
     **/
    private String receiverAddress;

    /**
     * 发货时间
     **/
    private String deliveryTime;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 订单支付明细
     **/
    private List<PushOrderPaidDetailVO> orderPaidDetail;

    /**
     * 子订单明细
     **/
    private List<PushChildOrderVO> childOrder;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderDeliveryFee() {
        return orderDeliveryFee;
    }

    public void setOrderDeliveryFee(String orderDeliveryFee) {
        this.orderDeliveryFee = orderDeliveryFee;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderPromotionDiscount() {
        return orderPromotionDiscount;
    }

    public void setOrderPromotionDiscount(String orderPromotionDiscount) {
        this.orderPromotionDiscount = orderPromotionDiscount;
    }

    public String getOrderAmountPay() {
        return orderAmountPay;
    }

    public void setOrderAmountPay(String orderAmountPay) {
        this.orderAmountPay = orderAmountPay;
    }

    public String getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(String receiverUser) {
        this.receiverUser = receiverUser;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<PushOrderPaidDetailVO> getOrderPaidDetail() {
        return orderPaidDetail;
    }

    public void setOrderPaidDetail(List<PushOrderPaidDetailVO> orderPaidDetail) {
        this.orderPaidDetail = orderPaidDetail;
    }

    public List<PushChildOrderVO> getChildOrder() {
        return childOrder;
    }

    public void setChildOrder(List<PushChildOrderVO> childOrder) {
        this.childOrder = childOrder;
    }

    public PushOrderRequestVO() {
    }

    public PushOrderRequestVO(SoDTO soDTO){
        this.orderCode = soDTO.getOrderCode();
        this.orderStatus = soDTO.getOrderStatus();
        this.orderAmount = soDTO.getOrderAmount()+"";
        this.createTime = Objects.nonNull(soDTO.getCreateTime())?DateUtils.format(DateUtils.DATE_TIME_FORMAT,soDTO.getCreateTime()):null;
        this.payTime = Objects.nonNull(soDTO.getOrderPaymentConfirmDate())?DateUtils.format(DateUtils.DATE_TIME_FORMAT,soDTO.getOrderPaymentConfirmDate()):null;
        this.orderDeliveryFee = soDTO.getOrderDeliveryFee()+"";
        this.orderAmountPay = soDTO.getOrderAmountPay()+"";
        this.remark= soDTO.getRemark();
        this.orderPromotionDiscount = EmptyUtil.isNotEmpty(soDTO.getOrderPromotionDiscount())?String.valueOf(soDTO.getOrderPromotionDiscount()):null;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
