package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.TagPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface TagReadDAO extends BaseReadDAO<TagPO>{
	/**
	 * 根据su商品id分页查询标签数据条数
	 * @param po
	 * @param types
	 * @return
	 */
	int countOfPageBystandardUnitCId(@Param("po")TagPO po, @Param("ids")List<Long> types);

	/**
	 * 根据su商品id分页查询标签数据
	 * @param po
	 * @param page
	 * @param types
	 * @return
	 */
	List<TagPO> findTagOfPageBystandardUnitCId(@Param("po")TagPO po, @Param("page")Pagination page,
											   @Param("ids")List<Long> types);
}
	
