package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.po.CashFlowAccountPO;

public class CashFlowAccountConverter {
	
	public static CashFlowAccountPO toPO(CashFlowAccountDTO src) {
		if(src==null)
			return null;
		CashFlowAccountPO tar=new CashFlowAccountPO();
		tar.setAccountId(src.getAccountId());
//		tar.setBalance(src.get);
		tar.setSalt(src.getSalt());
		tar.setSubRemark(src.getSubRemark());
		tar.setSum(src.getSum());
		tar.setUserAccountType(src.getUserAccountType());
		return tar;
	}
	
	public static CashFlowAccountDTO toDTO(CashFlowAccountPO src) {
		if(src==null)
			return null;
		CashFlowAccountDTO tar=new CashFlowAccountDTO();
		tar.setAccountId(src.getAccountId());
		tar.setSalt(src.getSalt());
		tar.setSubRemark(src.getSubRemark());
		tar.setSum(src.getSum());
		tar.setUserAccountType(src.getUserAccountType());
		return tar;
	}
	
	public static List<CashFlowAccountPO> toPO(List<CashFlowAccountDTO> src){
		List<CashFlowAccountPO> tar=new ArrayList<>();
		for(CashFlowAccountDTO dto:src) {
			tar.add(toPO(dto));
		}
		return tar;
	}
	
	public static List<CashFlowAccountDTO> toDTO(List<CashFlowAccountPO> src){
		List<CashFlowAccountDTO> tar=new ArrayList<>();
		for(CashFlowAccountPO po:src) {
			tar.add(toDTO(po));
		}
		return tar;
	}


}