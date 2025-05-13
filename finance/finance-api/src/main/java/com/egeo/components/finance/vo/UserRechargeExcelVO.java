package com.egeo.components.finance.vo;

import java.util.HashSet;
import java.util.Set;

public class UserRechargeExcelVO {

	private String memberCode;
	private String email;
	private double sum;
	private String reason;
	private String remark;
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((memberCode == null) ? 0 : memberCode.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRechargeExcelVO other = (UserRechargeExcelVO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (memberCode == null) {
			if (other.memberCode != null)
				return false;
		} else if (!memberCode.equals(other.memberCode))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (Double.doubleToLongBits(sum) != Double.doubleToLongBits(other.sum))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Set<UserRechargeExcelVO> set=new HashSet<>();
		UserRechargeExcelVO vo0=new UserRechargeExcelVO();
		vo0.setEmail("1");
		vo0.setMemberCode("1");
		vo0.setReason("1");
		vo0.setRemark("1");
		vo0.setSum(1d);
		
		UserRechargeExcelVO vo1=new UserRechargeExcelVO();
		vo1.setEmail("1");
		vo1.setMemberCode("1");
		vo1.setReason("1");
		vo1.setRemark("1");
		vo1.setSum(1d);
		boolean b0=set.add(vo0);
		System.out.println(b0);
		boolean b1=set.add(vo1);
		System.out.println(b1);
		System.out.println(set.size());
	}
	
	
}
