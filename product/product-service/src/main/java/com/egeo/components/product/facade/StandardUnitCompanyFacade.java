package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitCompanyReadService;
import com.egeo.components.product.service.write.StandardUnitCompanyWriteService;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitCompanyFacade {
	
	@Resource
	private StandardUnitCompanyReadService standardUnitCompanyReadService;
	
	@Resource
	private StandardUnitCompanyWriteService standardUnitCompanyWriteService;
	
	
	public StandardUnitCompanyDTO findStandardUnitCompanyById(StandardUnitCompanyDTO dto){
		
		return standardUnitCompanyReadService.findStandardUnitCompanyById(dto);
	}

	public PageResult<StandardUnitCompanyDTO> findStandardUnitCompanyOfPage(StandardUnitCompanyDTO dto,Pagination page){
		
		return standardUnitCompanyReadService.findStandardUnitCompanyOfPage(dto, page);
		
	}

	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(StandardUnitCompanyDTO dto){
		
		return standardUnitCompanyReadService.findStandardUnitCompanyAll(dto);
		
	}

	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(List<Long> standardUnitIds){
		List<StandardUnitCompanyDTO> list=new ArrayList<>();
		if (EmptyUtil.isNotEmpty(standardUnitIds)){
			for (int i=0;i<=standardUnitIds.size()/100;i++){
				int toIndex=(i+1)*100>standardUnitIds.size()?standardUnitIds.size():(i+1)*100;
				StandardUnitCompanyDTO dto=new StandardUnitCompanyDTO();
				dto.setStandardUnitIds(standardUnitIds.subList(i*100,toIndex));
				List<StandardUnitCompanyDTO> dtos=standardUnitCompanyReadService.findStandardUnitCompanyAll(dto);
				if (EmptyUtil.isNotEmpty(dtos)){
					list.addAll(dtos);
				}
			}
		}
		return list;

	}

	public Long insertStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto){
		
		return standardUnitCompanyWriteService.insertStandardUnitCompanyWithTx(dto);
	}

	public int updateStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto){
		
		return standardUnitCompanyWriteService.updateStandardUnitCompanyWithTx(dto);
	}

	public int deleteStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto){
		
		return standardUnitCompanyWriteService.deleteStandardUnitCompanyWithTx(dto);
		
	}
	/**
	 * 根据suid删除su福利企业关系信息
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitCompanyWriteService.deleteByStandardUnitIdWithTx(standardUnitId);
	}

}
	