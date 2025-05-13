package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.ProductCondition;
import com.egeo.components.product.po.ProductPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface ProductReadDAO extends BaseReadDAO<ProductPO>{

	int productByActivityIdCount(@Param("po")ProductPO po);

	List<ProductPO> productByActivityId(@Param("po")ProductPO po, @Param("page")Pagination page);

	List<ProductPO> queryProductsByIds(@Param("ids")List<Long> productIds);
	/**
	 * 根据产品id查询产品信息及产品详情
	 * @param id
	 * @return
	 */
	ProductCondition productAndProductDescriptionById(@Param("productId")Long productId);
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	List<ProductPO> findProductByIds(@Param("ids")List<Long> ids);
	/**
	 * 根据类目节点id查询spu草稿数量
	 * @param id
	 * @return
	 */
	int findByCategoryTreeNodeId(@Param("categoryTreeNodeId")Long categoryTreeNodeId);
	/**
	 * 获取product表中所有的id
	 * @param po
	 * 参数作为后期扩展，本次未使用（2018.11.7）
	 */
	public  List<Long> getProductIdsByCondition(@Param("po")ProductPO po);

    List<String> findPrecautiousLines(@Param("ids") List<Long> precautiousLineIds,@Param("productId") Long id);

    Long findLastId();

    int countOfPageByNameList(@Param("po")ProductPO po,@Param("nameList") List<String> nameList);

	List<ProductPO> findOfPageByNameList(@Param("po")ProductPO po, @Param("page")Pagination page,@Param("nameList")List<String> nameList);

    List<Long> findAllCategoryTreeNodeId();
}
	