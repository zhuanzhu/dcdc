package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.SurSerachKeywordPO;
import com.egeo.orm.BaseWriteDAO;

public interface SurSerachKeywordWriteDAO extends BaseWriteDAO<SurSerachKeywordPO> {
	/**
	 * 批量保存su关键词记录信息
	 * @param surSerachKeywords
	 * @return
	 */
	int insertAll(@Param("poList")List<SurSerachKeywordPO> surSerachKeywords);
}
	