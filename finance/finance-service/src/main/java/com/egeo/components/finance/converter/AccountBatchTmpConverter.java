package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.AccountBatchTmpDTO;
import com.egeo.components.finance.po.AccountBatchTmpPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-23 14:37:37
 */
public class AccountBatchTmpConverter {
	
	public static AccountBatchTmpDTO toDTO(AccountBatchTmpPO src) {
		if (src == null)
		return null;	
		AccountBatchTmpDTO tar = new AccountBatchTmpDTO();
		tar.setId(src.getId());
		tar.setOutflowAccountid(src.getOutflowAccountid());
		tar.setInflowAccountid(src.getInflowAccountid());
		tar.setFinBatch(src.getFinBatch());
		tar.setSum(src.getSum());
		tar.setReasonId(src.getReasonId());
		tar.setRemark(src.getRemark());
		tar.setStatus(src.getStatus());
		tar.setOperatorId(src.getOperatorId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setCompanyId(src.getCompanyId());
		tar.setExamReason(src.getExamReason());
		return tar;
	}

	public static AccountBatchTmpPO toPO(AccountBatchTmpDTO src) {
		if (src == null)
		return null;	
		AccountBatchTmpPO tar = new AccountBatchTmpPO();
		tar.setId(src.getId());
		tar.setOutflowAccountid(src.getOutflowAccountid());
		tar.setInflowAccountid(src.getInflowAccountid());
		tar.setFinBatch(src.getFinBatch());
		tar.setSum(src.getSum());
		tar.setReasonId(src.getReasonId());
		tar.setRemark(src.getRemark());
		tar.setStatus(src.getStatus());
		tar.setOperatorId(src.getOperatorId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setCompanyId(src.getCompanyId());
		tar.setExamReason(src.getExamReason());
		return tar;
	}

	public static List<AccountBatchTmpDTO> toDTO(List<AccountBatchTmpPO> srcs) {
		if (srcs == null)
			return null;
		List<AccountBatchTmpDTO> list = new ArrayList<AccountBatchTmpDTO>();
		for (AccountBatchTmpPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AccountBatchTmpPO> toPO(List<AccountBatchTmpDTO> srcs) {
		if (srcs == null)
			return null;
		List<AccountBatchTmpPO> list = new ArrayList<AccountBatchTmpPO>();
		for (AccountBatchTmpDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	