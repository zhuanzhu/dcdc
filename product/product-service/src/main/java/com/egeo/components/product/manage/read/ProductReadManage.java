package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.ProductCondition;
import com.egeo.components.product.po.ProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProductReadManage {

    PageResult<ProductPO> findPage(Pagination page, ProductPO po, List<String> nameList);

    ProductPO findById(ProductPO po);

    List<ProductPO> findAll(ProductPO po);

	PageResult<ProductPO> productByActivityId(Pagination page, ProductPO po);

	/**
	 * 批量查询产品
	 * @param productIds
	 * @return
	 */
	List<ProductPO> queryProductsByIds(List<Long> productIds);
	/**
	 * 根据产品id查询产品信息及产品详情
	 * @param id
	 * @return
	 */
	ProductCondition productAndProductDescriptionById(Long productId);
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	List<ProductPO> findProductByIds(List<Long> ids);
	/**
	 * 根据类目节点id查询spu草稿信息、有为true、没有为false
	 * @param id
	 * @return
	 */
	boolean findByCategoryTreeNodeId(Long categoryTreeNodeId);

	/**
	 * 获取product表中所有的id
	 * @param po
	 * 参数作为后期扩展，本次未使用（2018.11.7）
	 */
	public  List<Long> getProductIdsByCondition(ProductPO po);


	List<String> findPrecautiousLines(List<Long> precautiousLineIds, Long id);

    Long findLastId();

    List<Long> findAllCategoryTreeNodeId();
}
	