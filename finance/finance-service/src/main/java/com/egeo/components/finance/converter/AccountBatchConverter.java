package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.po.AccountBatchPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-06 15:24:25
 */
public class AccountBatchConverter {

	public static AccountBatchDTO toDTO(AccountBatchPO src) {
		if (src == null)
			return null;
		AccountBatchDTO tar = new AccountBatchDTO();
		tar.setId(src.getId());
		tar.setInflowAccountid(src.getInflowAccountid());
		tar.setOutflowAccountid(src.getOutflowAccountid());
		tar.setRaBatch(src.getRaBatch());
		tar.setFinBatch(src.getFinBatch());
		tar.setSum(src.getSum());
		tar.setReasonId(src.getReasonId());
		tar.setRemark(src.getRemark());
		tar.setFlowAmount(src.getFlowAmount());
		tar.setStatus(src.getStatus());
		tar.setOperatorId(src.getOperatorId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setType(src.getType());
		tar.setCompanyId(src.getCompanyId());
		tar.setCurrencyType(src.getCurrencyType());
		return tar;
	}

	public static AccountBatchPO toPO(AccountBatchDTO src) {
		if (src == null)
			return null;
		AccountBatchPO tar = new AccountBatchPO();
		tar.setId(src.getId());
		tar.setInflowAccountid(src.getInflowAccountid());
		tar.setOutflowAccountid(src.getOutflowAccountid());
		tar.setRaBatch(src.getRaBatch());
		tar.setFinBatch(src.getFinBatch());
		tar.setSum(src.getSum());
		tar.setReasonId(src.getReasonId());
		tar.setRemark(src.getRemark());
		tar.setFlowAmount(src.getFlowAmount());
		tar.setStatus(src.getStatus());
		tar.setOperatorId(src.getOperatorId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setType(src.getType());
		tar.setCompanyId(src.getCompanyId());
		tar.setCurrencyType(src.getCurrencyType());
		return tar;
	}

	public static List<AccountBatchDTO> toDTO(List<AccountBatchPO> srcs) {
		if (srcs == null)
			return null;
		List<AccountBatchDTO> list = new ArrayList<AccountBatchDTO>();
		for (AccountBatchPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AccountBatchPO> toPO(List<AccountBatchDTO> srcs) {
		if (srcs == null)
			return null;
		List<AccountBatchPO> list = new ArrayList<AccountBatchPO>();
		for (AccountBatchDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
