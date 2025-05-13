package com.egeo.components.product.facade;

import java.util.Date;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitCombinationCategoryReadService;
import com.egeo.components.product.service.read.StandardUnitCombinationReadService;
import com.egeo.components.product.service.read.StandardUnitCombinationSuReadService;
import com.egeo.components.product.service.read.StandardUnitCombinationTagReadService;
import com.egeo.components.product.service.write.StandardUnitCombinationWriteService;
import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitCombinationFacade {
	
	@Resource
	private StandardUnitCombinationReadService standardUnitCombinationReadService;
	
	@Resource
	private StandardUnitCombinationWriteService standardUnitCombinationWriteService;
	
	@Resource
	private StandardUnitCombinationSuReadService standardUnitCombinationSuReadService;
	
	@Resource
	private StandardUnitCombinationCategoryReadService standardUnitCombinationCategoryReadService;
	
	@Resource
	private StandardUnitCombinationTagReadService standardUnitCombinationTagReadService;
	
	
	public StandardUnitCombinationDTO findStandardUnitCombinationById(StandardUnitCombinationDTO dto){
		
		return standardUnitCombinationReadService.findStandardUnitCombinationById(dto);
	}

	public PageResult<StandardUnitCombinationDTO> findStandardUnitCombinationOfPage(StandardUnitCombinationDTO dto,Pagination page,List<Long> standardUnitCombinationIdList){
		
		return standardUnitCombinationReadService.findStandardUnitCombinationOfPage(dto, page,standardUnitCombinationIdList);
		
	}

	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAll(StandardUnitCombinationDTO dto){

		return standardUnitCombinationReadService.findStandardUnitCombinationAll(dto);
		
	}

	public Long insertStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto){
		
		return standardUnitCombinationWriteService.insertStandardUnitCombinationWithTx(dto);
	}

	public int updateStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto){
		
		return standardUnitCombinationWriteService.updateStandardUnitCombinationWithTx(dto);
	}

	public int deleteStandardUnitCombinationWithTx(StandardUnitCombinationDTO dto){
		
		return standardUnitCombinationWriteService.deleteStandardUnitCombinationWithTx(dto);
		
	}
	/**
	 * 根据su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSizeBySUType(Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuReadService.findStandardUnitSize(standardUnitCombinationId);
	}
	/**
	 * 根据类目类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSizeByCategoryType(Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationCategoryReadService.findStandardUnitSizeByCategoryType(standardUnitCombinationId);
	}
	/**
	 * 根据su组合id查询关联前台类目节点信息
	 * @param id
	 * @return
	 */
	public List<StandardUnitCombinationCategoryDTO> categoryTreeNodeByStandardUnitCombinationId(Long standardUnitCombinationId) {
		StandardUnitCombinationCategoryDTO standardUnitCombinationCategoryDTO = new StandardUnitCombinationCategoryDTO();
		standardUnitCombinationCategoryDTO.setStandardUnitCombinationId(standardUnitCombinationId);
		return standardUnitCombinationCategoryReadService.findStandardUnitCombinationCategoryAll(standardUnitCombinationCategoryDTO);
	}

	/**
	 * 查询商品组合后台类目树
	 * @param standardUnitCombinationId
	 * @param type
	 * @return
	 */
	public List<StandardUnitCombinationCategoryDTO> queryCategoryTreeNodeByStandardUnitCombinationId(Long standardUnitCombinationId, Integer type) {
		StandardUnitCombinationCategoryDTO standardUnitCombinationCategoryDTO = new StandardUnitCombinationCategoryDTO();
		standardUnitCombinationCategoryDTO.setStandardUnitCombinationId(standardUnitCombinationId);
		standardUnitCombinationCategoryDTO.setType(type);
		return standardUnitCombinationCategoryReadService.findStandardUnitCombinationCategoryAll(standardUnitCombinationCategoryDTO);
	}

	/**
	 * 根据su组合id查询关联标签信息
	 * @param id
	 * @return
	 */
	public List<StandardUnitCombinationTagDTO> tagByStandardUnitCombinationId(Long standardUnitCombinationId) {
		StandardUnitCombinationTagDTO standardUnitCombinationTagDTO = new StandardUnitCombinationTagDTO();
		standardUnitCombinationTagDTO.setStandardUnitCombinationId(standardUnitCombinationId);
		return standardUnitCombinationTagReadService.findStandardUnitCombinationTagAll(standardUnitCombinationTagDTO);
	}
	/**
	 * 根据标签类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSizeByTag(Long standardUnitCombinationId,Long platformId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationReadService.findStandardUnitSizeByTag(standardUnitCombinationId,platformId);
	}

	/**
	 * 模糊查询所有商品组合信息
	 * @param dto
	 * @return
	 */
	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationDTO dto) {
		
		return standardUnitCombinationReadService.findStandardUnitCombinationAllByBlurry(dto);
	}

	public List<StandardUnitCombinationDTO> findStandardUnitCombinationAllLimit(String suCombinationName) {
		return standardUnitCombinationReadService.findStandardUnitCombinationAllLimit(suCombinationName);
	}

	public List<StandardUnitCombinationSuDTO> syncJdSellState(Integer source, Date endCheckTime, int size){
		return standardUnitCombinationSuReadService.syncJdSellState(source,endCheckTime,size);
	}

}
	