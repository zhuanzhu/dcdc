package com.egeo.components.promotion.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 购物卡使用记录信息表
 * @Author lsl
 * @Version V1.0
 **/
public class CardUseRecordPO {

    /**
     * 主键
     **/
    private Long id;

    /**
     * '卡id'
     **/
    private Long cardId;

    /**
     * '卡编号'
     **/
    private String cardNo;

    /**
     * '持有用户id'
     **/
    private Long userId;

    /**
     * '使用金额'
     **/
    private BigDecimal useAmount;

    /**
     * '订单id'
     **/
    private Long soId;

    /**
     * '公司id'
     **/
    private Long companyId;

    /**
     * 创建时间（发放时间）
     **/
    private Date createTime;

    /**
     * 更新时间
     **/
    private Date updateTime;

    private String orderCode;

    private Date startTime;
    private  Date endTime;

    private String cardName;

    private String cardNoLike;

    private String cardNameLike;

    private String orderCodeLike;

    /**
     * '卡类型：1畅购卡 2礼品卡'
     **/
    private Integer cardType;

    /**
     * '结算方式:1预付费 2后付费'
     **/
    private Integer settleMethod;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(BigDecimal useAmount) {
        this.useAmount = useAmount;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNoLike() {
        return cardNoLike;
    }

    public void setCardNoLike(String cardNoLike) {
        this.cardNoLike = cardNoLike;
    }

    public String getCardNameLike() {
        return cardNameLike;
    }

    public void setCardNameLike(String cardNameLike) {
        this.cardNameLike = cardNameLike;
    }

    public String getOrderCodeLike() {
        return orderCodeLike;
    }

    public void setOrderCodeLike(String orderCodeLike) {
        this.orderCodeLike = orderCodeLike;
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
}
