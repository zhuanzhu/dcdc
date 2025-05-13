package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.write.StandardProductUnitAttNameWriteService;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitAttNameFacade {
	
	@Resource
	private StandardProductUnitAttNameReadService standardProductUnitAttNameReadService;
	
	@Resource
	private StandardProductUnitAttNameWriteService standardProductUnitAttNameWriteService;
	
	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;
	
	public StandardProductUnitAttNameDTO findStandardProductUnitAttNameById(StandardProductUnitAttNameDTO dto){
		
		return standardProductUnitAttNameReadService.findStandardProductUnitAttNameById(dto);
	}

	public PageResult<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNameDTO dto,Pagination page){
		
		return standardProductUnitAttNameReadService.findStandardProductUnitAttNameOfPage(dto, page);
		
	}

	public List<StandardProductUnitAttNameDTO> findStandardProductUnitAttNameAll(StandardProductUnitAttNameDTO dto){
		
		return standardProductUnitAttNameReadService.findStandardProductUnitAttNameAll(dto);
		
	}

	public Long insertStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto){
		
		return standardProductUnitAttNameWriteService.insertStandardProductUnitAttNameWithTx(dto);
	}

	public int updateStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto){
		
		return standardProductUnitAttNameWriteService.updateStandardProductUnitAttNameWithTx(dto);
	}

	public int deleteStandardProductUnitAttNameWithTx(StandardProductUnitAttNameDTO dto){
		
		return standardProductUnitAttNameWriteService.deleteStandardProductUnitAttNameWithTx(dto);
		
	}
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findByStandardUnitId(Long standardUnitId) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameDTOs = standardProductUnitAttNameReadService.findByStandardUnitId(standardUnitId);
		for (StandardProductUnitAttNameDTO standardProductUnitAttNameDTO : standardProductUnitAttNameDTOs) {
			Map<String, Object> map = new HashMap<>();
			map.put("attName", standardProductUnitAttNameDTO.getAttName());
			map.put("showPicture", standardProductUnitAttNameDTO.getShowPicture());
			List<Map<String, Object>> attList = new ArrayList<>();
			
			//根据spu属性id查询spu属性值信息
			List<StandardProductUnitAttValueDTO> list = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO.getId());
			for (StandardProductUnitAttValueDTO standardProductUnitAttValueDTO : list) {
				Map<String, Object> strings = new HashMap<>();
				strings.put("attValue",standardProductUnitAttValueDTO.getAttValue());
				strings.put("attValueId", standardProductUnitAttValueDTO.getAttValueId());
				if(standardProductUnitAttNameDTO.getShowPicture() == 1){
					strings.put("pictureUrl", standardProductUnitAttValueDTO.getPictureUrl());
				}else{
					strings.put("pictureUrl", null);
				}
				attList.add(strings);
			}
			map.put("attValue", attList);
			maps.add(map);
		}
		return maps;
	}

}
	