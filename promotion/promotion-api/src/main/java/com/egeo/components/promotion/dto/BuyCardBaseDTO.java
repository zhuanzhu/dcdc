package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 购物卡基本信息
 * @Author lsl
 * @Version V1.0
 **/
public class BuyCardBaseDTO implements Serializable {


    /**
     *
     */
    private Long id;

    /**
     * 卡名称
     */
    private String cardName;

    /**
     * 卡种(卡号前缀)：如CY
     */
    private String cardPrefix;

    /**
     * 卡类型：1畅购卡 2礼品卡
     */
    private Integer cardType;

    /**
     * 结算方式:1预付费 2后付费
     */
    private Integer settleMethod;

    /**
     * 卡面值金额
     */
    private BigDecimal cardAmount;

    /**
     * 是否启用：0启用 1不启用
     */
    private Integer cardState;

    /**
     * 有效期数值
     */
    private Integer expirationDate;

    /**
     * 有效期单位 1日 2周 3月 4年
     */
    private Integer expirationDateUnit;


    /**
     * 删除状态：0正常 1已删除
     */
    private Integer delFlag;

    /**
     * 最后一次操作人
     */
    private Long operator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 规则描述
     */
    private String ruleDescribe;

    /**
     * 描述
     */
    private String describe;

    /**
     * 关联的组合id
     */
    private Long combinationId;

    /**
     * 卡排序
     */
    private Integer sortValue;


    private List<Long> ids;

    private String cardNameLike;

    public String getCardNameLike() {
        return cardNameLike;
    }

    public void setCardNameLike(String cardNameLike) {
        this.cardNameLike = cardNameLike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix;
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

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Integer getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Integer expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getExpirationDateUnit() {
        return expirationDateUnit;
    }

    public void setExpirationDateUnit(Integer expirationDateUnit) {
        this.expirationDateUnit = expirationDateUnit;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
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

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRuleDescribe() {
        return ruleDescribe;
    }

    public void setRuleDescribe(String ruleDescribe) {
        this.ruleDescribe = ruleDescribe;
    }

    public Long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(Long combinationId) {
        this.combinationId = combinationId;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }
}
