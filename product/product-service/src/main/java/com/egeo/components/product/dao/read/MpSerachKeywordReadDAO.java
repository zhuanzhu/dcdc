package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MpSerachKeywordPO;
import com.egeo.orm.BaseReadDAO;

public interface MpSerachKeywordReadDAO extends BaseReadDAO<MpSerachKeywordPO>{
	/**
	 * 根据suId查询su草稿关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	List<String> keyWordByMerchantProductId(@Param("merchantProductId")Long merchantProductId, @Param("platformId")Long platformId);
}
	