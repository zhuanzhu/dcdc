package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.components.product.condition.StandardProductUnitCondition;
import com.egeo.components.product.converter.StandardProductUnitConverter;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitReadManage;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.components.product.service.read.StandardProductUnitReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Service("standardProductUnitReadService")
public class StandardProductUnitReadServiceImpl  implements StandardProductUnitReadService {
	private static final XLogger logger = XLogger.getLogger(StandardProductUnitReadServiceImpl.class);
	@Autowired
	private StandardProductUnitReadManage standardProductUnitReadManage;
	
	@Autowired
	private StandardProductUnitAttValueReadManage spuAtValueReadManage;

	@Override
	public StandardProductUnitDTO findStandardProductUnitById(StandardProductUnitDTO dto) {
		StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
		StandardProductUnitPO list = standardProductUnitReadManage.findStandardProductUnitById(po);		
		if(EmptyUtil.isEmpty(list)){
			return null;
		}else{
			return StandardProductUnitConverter.toDTO(list);
		}
		
	}

	@Override
	public PageResult<StandardProductUnitDTO> findStandardProductUnitOfPage(StandardProductUnitDTO dto, Pagination page) {
		StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
        PageResult<StandardProductUnitPO> pageResult = standardProductUnitReadManage.findStandardProductUnitOfPage(po, page);
        
        List<StandardProductUnitDTO> list = StandardProductUnitConverter.toDTO(pageResult.getList());
        PageResult<StandardProductUnitDTO> result = new PageResult<StandardProductUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardProductUnitDTO> findStandardProductUnitAll(StandardProductUnitDTO dto) {
		StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
		List<StandardProductUnitPO> list = standardProductUnitReadManage.findStandardProductUnitAll(po);		
		return StandardProductUnitConverter.toDTO(list);
	}
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	@Override
	public List<StandardProductUnitDTO> findProductByIds(List<Long> ids) {
		List<StandardProductUnitPO> list = standardProductUnitReadManage.findProductByIds(ids);
		return StandardProductUnitConverter.toDTO(list);
	}

	@Override
	public StandardProductUnitDTO querySpuBySuId(Long suId) {
		return StandardProductUnitConverter.toDTO(standardProductUnitReadManage.querySpuBySuId(suId));
	}
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public StandardProductUnitDTO queryStandardProductUnitById(Long standardProductUnitId) {
		StandardProductUnitCondition standardProductUnitCondition =  standardProductUnitReadManage.queryStandardProductUnitById(standardProductUnitId);
		StandardProductUnitDTO standardProductUnitDTO = StandardProductUnitConverter.toDTO(standardProductUnitCondition);
		standardProductUnitDTO.setContent(standardProductUnitCondition.getContent());
		return standardProductUnitDTO;
	}
	/**
	 * 判断spu是否是unit商品
	 * @param puId
	 * @return
	 */
	@Override
	public boolean queryIsUnit(Long standardProductUnitId) {
		//有unit库存的基本属性值id
		Long basicAttValueId_unitStock=spuAtValueReadManage.queryAttValueIdBySpuIdAndAttNameId(standardProductUnitId,BusinessConstant.IS_UNIT_INVENTORY_ID);
		return basicAttValueId_unitStock!=null && basicAttValueId_unitStock.longValue()==3l;
	}

	@Override
	public Long findCommodityTemplateIdByStandardUnitId(Long standardUnitId) {
		return standardProductUnitReadManage.findCommodityTemplateIdByStandardUnitId(standardUnitId);
	}

	@Override
	public Long findLastId() {
		return standardProductUnitReadManage.findLastId();
	}


}
	