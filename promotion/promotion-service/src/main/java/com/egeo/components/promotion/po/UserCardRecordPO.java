package com.egeo.components.promotion.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 购物卡发放记录信息表(卡和用户关联关系表)
 * @Author lsl
 * @Version V1.0
 **/
public class UserCardRecordPO {

    /**
     * 主键
     **/
    private Long id;

    /**
     *'卡编号'
     **/
    private String cardNo;

    /**
     * '持有用户id'
     **/
    private Long userId;

    /**
     * '卡名称'
     **/
    private String cardName;

    /**
     * '卡类型：1畅购卡 2礼品卡'
     **/
    private Integer cardType;

    /**
     * '结算方式:1预付费 2后付费'
     **/
    private Integer settleMethod;

    /**
     * '卡面值金额'
     **/
    private BigDecimal cardAmount;

    /**
     * '卡余额'
     **/
    private BigDecimal cardCash;


    /**
     * '有效期起始日期'
     **/
    private Date expireDateStart;

    /**
     * '有效期结束日期'
     **/
    private Date expireDateEnd;

    /**
     * 有效状态：0有效 1无效 默认有效
     **/
    private Integer cardState;

    /**
     * 使用状态：0未使用 1使用中 2已使用 3已过期 默认未使用
     **/
    private Integer useState;

    /**
     * '公司id'
     **/
    private Long companyId;

    /**
     * '来源卡基础信息id'
     **/
    private Long sourceCardId;

    /**
     * '最后一次操作人'
     **/
    private Long operator;

    /**
     * 创建时间（发放时间）
     **/
    private Date createTime;

    /**
     * 更新时间
     **/
    private Date updateTime;

    private List<Long> userIds;

    private List<Integer> useStates;

    private String cardNoLike;

    private String cardNameLike;

    private List<Long> ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getCardAmount() {
        return cardAmount;
    }

    public void setCardAmount(BigDecimal cardAmount) {
        this.cardAmount = cardAmount;
    }

    public BigDecimal getCardCash() {
        return cardCash;
    }

    public void setCardCash(BigDecimal cardCash) {
        this.cardCash = cardCash;
    }

    public Date getExpireDateStart() {
        return expireDateStart;
    }

    public void setExpireDateStart(Date expireDateStart) {
        this.expireDateStart = expireDateStart;
    }

    public Date getExpireDateEnd() {
        return expireDateEnd;
    }

    public void setExpireDateEnd(Date expireDateEnd) {
        this.expireDateEnd = expireDateEnd;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getSourceCardId() {
        return sourceCardId;
    }

    public void setSourceCardId(Long sourceCardId) {
        this.sourceCardId = sourceCardId;
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

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getUseStates() {
        return useStates;
    }

    public void setUseStates(List<Integer> useStates) {
        this.useStates = useStates;
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

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
