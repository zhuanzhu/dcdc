package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MpSerachKeywordPO;
import com.egeo.orm.BaseWriteDAO;

public interface MpSerachKeywordWriteDAO extends BaseWriteDAO<MpSerachKeywordPO> {
	/**
	 * 批量保存su商品关键词
	 * @param mpSerachKeywords
	 * @return
	 */
	int insertAll(@Param("poList")List<MpSerachKeywordPO> mpSerachKeywords);
}
	