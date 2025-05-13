package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.orm.BaseReadDAO;

public interface SuSerachKeywordReadDAO extends BaseReadDAO<SuSerachKeywordPO>{

	List<String> keywordByStandardUnitId(@Param("suId")Long suId, @Param("platformId")Long platformId);
}
	