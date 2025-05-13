package com.egeo.components.finance.vo;

/**
 * 导入头文件vo
 * 
 * @author GRACIA
 *
 */
public class HeadInfoVO {

	private String companyName;
	private String templateType;
	private String fileSequenceNumber;
	private String accountType;
	private Double sum;

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getFileSequenceNumber() {
		return fileSequenceNumber;
	}

	public void setFileSequenceNumber(String fileSequenceNumber) {
		this.fileSequenceNumber = fileSequenceNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
