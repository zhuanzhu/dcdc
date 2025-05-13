package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardUnitTagWriteDAO extends BaseWriteDAO<StandardUnitTagPO> {
	/**
	 * 根据商品id和标签id集合删除商品标签关系
	 * @param merchantProductId
	 * @param tagList
	 * @return
	 */
	int delByTags(@Param("standardUnitId")Long standardUnitId, @Param("ids")List<Long> tagList);
	/**
	 * 批量保存su标签关系信息
	 * @param standardUnitTags
	 * @return
	 */
	int insertAll(@Param("poList")List<StandardUnitTagPO> standardUnitTags);
}
	