package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitReadService {

	public StandardProductUnitDTO findStandardProductUnitById(StandardProductUnitDTO dto);

	public PageResult<StandardProductUnitDTO> findStandardProductUnitOfPage(StandardProductUnitDTO dto,Pagination page);

	public List<StandardProductUnitDTO> findStandardProductUnitAll(StandardProductUnitDTO dto);
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	public List<StandardProductUnitDTO> findProductByIds(List<Long> ids);

	/**
	 * 根据suid查询spu
	 * @param suId
	 * @return
	 */
	public StandardProductUnitDTO querySpuBySuId(Long suId);
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	public StandardProductUnitDTO queryStandardProductUnitById(Long standardProductUnitId);
	/**
	 * 判断spu是否是unit商品
	 * @param puId
	 * @return
	 */
	public boolean queryIsUnit(Long standardProductUnitId);
	/**
	 * 根据suId查询spu模版id
	 * @param standardUnitId
	 * @return
	 */
	public Long findCommodityTemplateIdByStandardUnitId(Long standardUnitId);

    Long findLastId();
}
	