package com.egeo.components.finance.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.bean.AccountFlowDetailVO;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.components.utils.DateUtil;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-06 15:24:25
 */
public class AccountFlowConverter {
	
	public static  AccountFlowDetailVO toDetailVO(AccountFlowDTO src) {
		if (src == null)
		return null;	
		AccountFlowDetailVO tar = new AccountFlowDetailVO();
		

		tar.setId(src.getId());
		tar.setOutflowAccountid(src.getOutflowAccountid());
		tar.setOutflowAccounttype(src.getOutflowAccounttype());
		tar.setInflowAccountid(src.getInflowAccountid());
		tar.setInflowAccounttype(src.getInflowAccounttype());
		tar.setSum(src.getSum().setScale(2,BigDecimal.ROUND_DOWN).toEngineeringString());
		tar.setReasonId(src.getReasonId());
		tar.setRemark(src.getRemark());
		tar.setBatchId(src.getBatchId());
		tar.setCreateTime(DateUtil.formatDate(DateUtil.YearMonthDay, src.getCreateTime()));
		tar.setUpdateTime(DateUtil.formatDate(DateUtil.YearMonthDay, src.getUpdateTime()));
		tar.setReason(src.getReason());
		tar.setOrderId(src.getOrderId());
		tar.setOrderCode(src.getOrderCode());
		tar.setPlatformId(src.getPlatformId());
		tar.setLedgerEnterprise(src.getLedgerEnterprise()==null?"0":src.getLedgerEnterprise().setScale(2,BigDecimal.ROUND_DOWN).toEngineeringString());
		tar.setLedgerPlateform(src.getLedgerPlateform()==null?"0":src.getLedgerPlateform().setScale(2,BigDecimal.ROUND_DOWN).toEngineeringString());
		if(src.getType()==0) {
			tar.setType("企业账户充值");
		}else if(src.getType()==1) {
			tar.setType("企业账户调整收入");
		}else if(src.getType()==2) {
			tar.setType("企业账户失效");
		}else if(src.getType()==3) {
			tar.setType("订单现金付款");
		}else if(src.getType()==4) {
			tar.setType("订单福币付款");
		}else if(src.getType()==5) {
			tar.setType("订单现金退款");
		}else if(src.getType()==6) {
			tar.setType("订单福币退款");
		}else if(src.getType()==7) {
			tar.setType("员工福币充值");
		}else if(src.getType()==8) {
			tar.setType("员工点赞积分充值");
		}else if(src.getType()==9) {
			tar.setType("员工账户失效");
		}else if(src.getType()==10) {
			tar.setType("员工点赞");
		}else if(src.getType()==11) {
			tar.setType("企业账户调整支出");
		}else if(src.getType()==12) {
			tar.setType("其他");
		}else if(src.getType()==13) {
			tar.setType("企业账户恢复");
		}else if(src.getType()==14) {
			tar.setType("员工账户恢复");
		}
		tar.setCurrencyType(src.getCurrencyType());
		tar.setIsRead(src.getIsRead());
		return tar;
		
	}
	
	
	public static AccountFlowDTO toDTO(AccountFlowPO src) {
		if (src == null)
		return null;	
		AccountFlowDTO tar = new AccountFlowDTO();
		tar.setId(src.getId());
		tar.setOutflowAccountid(src.getOutflowAccountid());
		tar.setOutflowAccounttype(src.getOutflowAccounttype());
		tar.setInflowAccountid(src.getInflowAccountid());
		tar.setInflowAccounttype(src.getInflowAccounttype());
		tar.setSum(src.getSum());
		tar.setReasonId(src.getReasonId());
		tar.setRemark(src.getRemark());
		tar.setBatchId(src.getBatchId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setReason(src.getReason());
		tar.setOrderId(src.getOrderId());
		tar.setOrderCode(src.getOrderCode());
		tar.setPlatformId(src.getPlatformId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setLedgerEnterprise(src.getLedgerEnterprise());
		tar.setLedgerPlateform(src.getLedgerPlateform());
		tar.setType(src.getType());
		tar.setCurrencyType(src.getCurrencyType());
		tar.setIsRead(src.getIsRead());
		return tar;
	}

	public static AccountFlowPO toPO(AccountFlowDTO src) {
		if (src == null)
		return null;	
		AccountFlowPO tar = new AccountFlowPO();
		tar.setId(src.getId());
		tar.setOutflowAccountid(src.getOutflowAccountid());
		tar.setOutflowAccounttype(src.getOutflowAccounttype());
		tar.setInflowAccountid(src.getInflowAccountid());
		tar.setInflowAccounttype(src.getInflowAccounttype());
		tar.setSum(src.getSum());
		tar.setReasonId(src.getReasonId());
		tar.setRemark(src.getRemark());
		tar.setBatchId(src.getBatchId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setReason(src.getReason());
		tar.setOrderId(src.getOrderId());
		tar.setOrderCode(src.getOrderCode());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setCurrencyType(src.getCurrencyType());
		tar.setIsRead(src.getIsRead());
		return tar;
	}

	public static List<AccountFlowDTO> toDTO(List<AccountFlowPO> srcs) {
		if (srcs == null)
			return null;
		List<AccountFlowDTO> list = new ArrayList<AccountFlowDTO>();
		for (AccountFlowPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AccountFlowPO> toPO(List<AccountFlowDTO> srcs) {
		if (srcs == null)
			return null;
		List<AccountFlowPO> list = new ArrayList<AccountFlowPO>();
		for (AccountFlowDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	