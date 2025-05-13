package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.ProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProductReadService {

    PageResult<ProductDTO> findPage(Pagination page, ProductDTO dto, List<String> nameList);

    ProductDTO findById(ProductDTO dto);

    List<ProductDTO> findAll(ProductDTO dto);

	PageResult<ProductDTO> productByActivityId(Pagination page, ProductDTO productDTO);
	/**
	 * 根据产品id查询产品信息及产品详情
	 * @param id
	 * @return
	 */
	ProductDTO productAndProductDescriptionById(Long productId);
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	List<ProductDTO> findProductByIds(List<Long> ids);
	/**
	 * 根据类目节点id查询spu草稿信息、有为true、没有为false
	 * @param id
	 * @return
	 */
	boolean findByCategoryTreeNodeId(Long categoryTreeNodeId);
	/**
	 * 获取product表中所有的id
	 * @param dto
	 * 参数作为后期扩展，本次未使用（2018.11.7）
	 */
	public  List<Long> getProductIdsByCondition(ProductDTO dto);

    Long findLastId();

    List<Long> findAllCategoryTreeNodeId();
}
	