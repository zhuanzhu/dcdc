package com.egeo.components.product.dao.read;

import java.util.List;

import com.egeo.components.product.condition.CategoryAndChannelCondition;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.CategoryAndJdCondition;
import com.egeo.components.product.condition.CategoryCondition;
import com.egeo.components.product.po.CategoryPO;
import com.egeo.orm.BaseReadDAO;

public interface CategoryReadDAO extends BaseReadDAO<CategoryPO>{

    List<CategoryCondition> find(@Param("po")CategoryPO po);

	CategoryCondition findCategoryByPId(@Param("parentId")Long parentId);



	List<CategoryAndJdCondition> findCategoryByCategoryTreeNodes(@Param("ids")List<Long> categoryTreeNodeIdList);

	CategoryCondition findCategoryCntByPId(@Param("parentId")Long parentId);
	/**
	 * 根据上级节点信息查询类目信息
	 * @param parentId
	 * @return
	 */
	CategoryPO categoryByPIdNode(@Param("parentId")Long parentId);
	/**
	 * 根据suId查询su所属类目
	 * @param suId
	 * @return
	 */
	List<String> findCategoryNameBySuId(@Param("suId")Long suId);

	List<CategoryAndChannelCondition> findChannelCategoryByCategoryTreeNodes(@Param("ids")List<Long> categoryTreeNodeIdList, @Param("channelCode") String channelCode);
}
