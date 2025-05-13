package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.orm.BaseWriteDAO;

public interface SuSerachKeywordWriteDAO extends BaseWriteDAO<SuSerachKeywordPO> {
	/**
	 * 批量保存su关键词信息
	 * @param suSerachKeywords
	 * @return
	 */
	int insertAll(@Param("poList")List<SuSerachKeywordPO> suSerachKeywords);
}
	