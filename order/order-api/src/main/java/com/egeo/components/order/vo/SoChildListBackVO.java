package com.egeo.components.order.vo;

import java.util.Date;

/**
 * Created by 0.0 on 2018/9/16.
 */
public class SoChildListBackVO {

    //子訂單id
    private Long id;
    //子訂單編號
    private String soChildCode;
    //子訂單編號
    private String soCode;


    //買家賬戶
    private String mail;
    //买家姓名
    private String userName;
    //买家电话
    private String mobile;
    //下单时间
    private Date createTime;
    //应发货日期
    private Date sendTime;
    //商品原价总金额
    private Double amountTotal;
    //优惠卷优惠金额
    private Double couponeDiscount;
    //门店优惠金额
    private Double storeDiscount;
    private Double discount;
	private Long supplierId;	

    public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    // 结算金额
    private Double amount;
    //子订单物流状态
    private Integer soChildDeliveryStatus;
    //母订单确认状态
    private Integer soConfirmStatus;
    private Integer soPayStatus;
    //订单类型
    private Integer saleWay;



    public String getSoCode() {
		return soCode;
	}

	public void setSoCode(String soCode) {
		this.soCode = soCode;
	}

	public Double getCouponeDiscount() {
        return couponeDiscount;
    }

    public void setCouponeDiscount(Double couponeDiscount) {
        this.couponeDiscount = couponeDiscount;
    }

    public Double getStoreDiscount() {
        return storeDiscount;
    }

    public void setStoreDiscount(Double storeDiscount) {
        this.storeDiscount = storeDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoPayStatus() {
		return soPayStatus;
	}

	public void setSoPayStatus(Integer soPayStatus) {
		this.soPayStatus = soPayStatus;
	}

	public String getSoChildCode() {
        return soChildCode;
    }

    public void setSoChildCode(String soChildCode) {
        this.soChildCode = soChildCode;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Double getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Double amountTotal) {
        this.amountTotal = amountTotal;
    }



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getSoChildDeliveryStatus() {
        return soChildDeliveryStatus;
    }

    public void setSoChildDeliveryStatus(Integer soChildDeliveryStatus) {
        this.soChildDeliveryStatus = soChildDeliveryStatus;
    }

    public Integer getSoConfirmStatus() {
        return soConfirmStatus;
    }

    public void setSoConfirmStatus(Integer soConfirmStatus) {
        this.soConfirmStatus = soConfirmStatus;
    }

    public Integer getSaleWay() {
        return saleWay;
    }

    public void setSaleWay(Integer saleWay) {
        this.saleWay = saleWay;
    }
}
