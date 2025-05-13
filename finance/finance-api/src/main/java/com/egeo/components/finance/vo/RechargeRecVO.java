package com.egeo.components.finance.vo;

/**
 * 后台充值记录列表VO
 * @author GRACIA
 *
 */
public class RechargeRecVO {

	private Long id;
	
	private String companyName;
	
	private String raBatch;
	
	private String finBatch;
	
	private Integer accountType;
	
	private String reason;
	
	private double sum;
	
	private String create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRaBatch() {
		return raBatch;
	}

	public void setRaBatch(String raBatch) {
		this.raBatch = raBatch;
	}

	public String getFinBatch() {
		return finBatch;
	}

	public void setFinBatch(String finBatch) {
		this.finBatch = finBatch;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
}
