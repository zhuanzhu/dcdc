package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.IconCompanyReadService;
import com.egeo.components.cms.service.read.IconGroupReadService;
import com.egeo.components.cms.service.read.IconReadService;
import com.egeo.components.cms.service.write.IconGroupWriteService;
import com.egeo.components.cms.service.write.IconWriteService;
import com.egeo.components.cms.dto.IconCompanyDTO;
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class IconFacade {
	
	@Resource
	private IconReadService iconReadService;
	
	@Resource
	private IconWriteService iconWriteService;
	
	@Resource
	private IconGroupReadService iconGroupReadService;
	
	@Resource
	private IconGroupWriteService iconGroupWriteService;
	
	@Resource
	private IconCompanyReadService iconCompanyReadService;
	
	
	public IconDTO findIconById(IconDTO dto){
		
		return iconReadService.findIconById(dto);
	}

	public PageResult<IconDTO> findIconOfPage(IconDTO dto,Pagination page){
		
		return iconReadService.findIconOfPage(dto, page);
		
	}

	public List<IconDTO> findIconAll(IconDTO dto){
		
		return iconReadService.findIconAll(dto);
		
	}

	public Long insertIconWithTx(IconDTO dto){
		
		return iconWriteService.insertIconWithTx(dto);
	}

	public int updateIconWithTx(IconDTO dto){
		
		return iconWriteService.updateIconWithTx(dto);
	}

	public int deleteIconWithTx(IconDTO dto){
		
		return iconWriteService.deleteIconWithTx(dto);
		
	}

	/**
	 * 根据实例id查询icon组
	 * @param instId
	 * @return
	 */
	public IconGroupDTO queryIconGroupByInstId(Long instId) {
		
		return iconGroupReadService.queryIconGroupByInstId(instId);
	}

	/**
	 * 根据组id查询icon列表
	 * @param id
	 * @return
	 */
	public List<IconDTO> queryIconsByGroupId(Long groupId) {
		return iconReadService.queryIconsByGroupId(groupId);
	}

	/**
	 * 根据iconid查询icon与公司关系
	 * @param id
	 * @return
	 */
	public List<IconCompanyDTO> queryIconCompanyByIconId(Long iconId) {
		IconCompanyDTO cond=new IconCompanyDTO();
		cond.setIconId(iconId);
		return iconCompanyReadService.findIconCompanyAll(cond);
	}

	/**
	 * 根据iconId和公司id查询icon与公司关系列表
	 * @param id
	 * @param companyId
	 * @return
	 */
	public List<IconCompanyDTO> queryIconCompanysByIconIdAndCompanyId(Long id, Long companyId) {
		return iconCompanyReadService.queryIconCompanysByIconIdAndCompanyId(id,companyId);
	}

}
	