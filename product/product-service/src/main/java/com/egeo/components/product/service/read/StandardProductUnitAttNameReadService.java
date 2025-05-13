package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitAttNameReadService {

	public StandardProductUnitAttNameDTO findStandardProductUnitAttNameById(StandardProductUnitAttNameDTO dto);

	public PageResult<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNameDTO dto,Pagination page);

	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(StandardProductUnitAttNameDTO dto);
	/**
	 * 根据suid查询su参数属性
	 * @param standardProductUnitId
	 * @return
	 */
	public List<StandardProductUnitAttNameDTO> findByStandardProductUnitId(Long standardProductUnitId);
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	public List<StandardProductUnitAttNameDTO> findByStandardUnitId(Long standardUnitId);

	public List<StandardProductUnitAttNameDTO> findByStandardProductUnitIdAttNameId(Long standardProductUnitId,
			Long attNameId);
	/**
	 * 根据spuId查询spu属性信息
	 * @param standardProductUnitId
	 * @return
	 */
	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(Long standardProductUnitId);

    Long findLastId();
}
	