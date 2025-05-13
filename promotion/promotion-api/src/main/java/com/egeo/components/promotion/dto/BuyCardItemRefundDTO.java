package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class BuyCardItemRefundDTO implements Serializable {


    private Long id;

    /**
     * 退款订单项id
     */
    private Long itemId;

    /**
     * 退款子订单id
     */
    private Long childId;

    /**
     * 退款订单id
     */
    private Long soId;

    /**
     * 卡劵id
     */
    private Long cardId;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退款用户
     */
    private Long userId;

    /**
     * 退款流水号
     */
    private String refundNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 订单编号
     */
    private String orderCode;

    private String cardName;

    /**
     * 卡类型：1畅购卡 2礼品卡
     */
    private Integer cardType;

    /**
     * 结算方式:1预付费 2后付费
     */
    private Integer settleMethod;

    private String cardNo;

    /**卡使用id**/
    private Long cardUseId;

    private Long refundId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getSettleMethod() {
        return settleMethod;
    }

    public void setSettleMethod(Integer settleMethod) {
        this.settleMethod = settleMethod;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Long getCardUseId() {
        return cardUseId;
    }

    public void setCardUseId(Long cardUseId) {
        this.cardUseId = cardUseId;
    }

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }
}
