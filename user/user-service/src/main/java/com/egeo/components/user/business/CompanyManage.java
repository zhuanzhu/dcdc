package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.entity.CacheUser;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface CompanyManage {

	public Map<String, Object> findCompanyById(Long is);	

	public PageResult<CompanyDTO> findCompanyOfPage(CompanyDTO dto,Pagination page,List<Long> companyIdList);

	public List<CompanyDTO> findCompanyAll(CompanyDTO dto);

	Long insertCompanyWithTx(CompanyDTO dto,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList);
	Long insertCompanyAndAdminWithTx(CompanyDTO dto,UserDTO userDto,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList);

	int updateCompanyWithTx(CompanyDTO dto, CacheUser userCache,List<CompanyPageDTO> companyPageList,String linkJson,String cmsPageTabList);

	int deleteCompanyWithTx(CompanyDTO dto);

	/**
	 * 简要公司信息列表
	 * @param platformId
	 * @return
	 */
	public JsonResult<Map<String,Object>> simpleCompanyList(Long platformId);

	/**
	 * 公司失效
	 * @param companyDTO
	 * @param validType 
	 * @return
	 */
	public Integer companyInvalid(CompanyDTO companyDTO, Integer validType);

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public List<CompanyDTO> findCompanyIdOrName(CompanyDTO dto);

	/**
	 * 公司有效
	 * @param companyDTO
	 */
	public Integer companyValid(Integer recoverUser ,CompanyDTO companyDTO);

	/**
	 * 根据名称精确查询公司
	 * @param companyName
	 * @return
	 */
	public CompanyDTO queryCompanyByName(String companyName);
}
	