package com.egeo.components.finance.po;

import java.math.BigDecimal;

/**
 * 账户更新po
 * 用于批量更新账户余额和加密值赋值
 * @author GRACIA
 *
 */
public class AccountUpdatePO {

	private Long id;
	private BigDecimal sum;
	private String cipher;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	public String getCipher() {
		return cipher;
	}
	public void setCipher(String cipher) {
		this.cipher = cipher;
	}
	
	
}
