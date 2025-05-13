package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.converter.CompanyConverter;
import com.egeo.components.user.converter.DepartmentConverter;
import com.egeo.components.user.dao.read.CompanyReadDAO;
import com.egeo.components.user.dao.read.DepartmentReadDAO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.common.NormalConstant;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("companyReadService")
public class CompanyReadServiceImpl implements CompanyReadService {
	@Autowired
	private CompanyReadDAO companyReadDAO;
	@Autowired 
	private DepartmentReadDAO departmentReadDAO;

	@Override
	public CompanyDTO findCompanyById(Long id) {
		CompanyPO po = new CompanyPO();
		po.setId(id);
		return CompanyConverter.toDTO(companyReadDAO.findById(po));
	}

	@Override
	public PageResult<CompanyDTO> findCompanyOfPage(CompanyDTO dto, Pagination page,List<Long> companyIdList) {
		if (dto == null)
			dto = new CompanyDTO();
		
		String companyType_0 = "";
		String companyType_1 = "";
		String companyType_2 = "";
		List<Long> companyIdList_ = new ArrayList<Long>();
		if(StringUtils.isNotEmpty(companyIdList)){
			for (Long companyId : companyIdList) {
				if (companyId.equals(NormalConstant.COMPANY_OFFICIAL_ID)) {
					// 所有正式公司
					companyType_0 = NormalConstant.COMPANY_OFFICIAL.toString();
				} else if (companyId.equals(NormalConstant.COMPANY_TEST_ID)) {
					// 所有演示公司
					companyType_1= NormalConstant.COMPANY_TEST.toString();
				} else if (companyId.equals(NormalConstant.COMPANY_COMPETITIVE_PRODUCTS_ID)) {
					// 所有竞品
					companyType_2 = NormalConstant.COMPANY_COMPETITIVE_PRODUCTS.toString();
				} else {
					companyIdList_.add(companyId);
				}
			}
		}
		String companyTypeStr = companyType_1 + companyType_2 + companyType_0;
		if (StringUtils.isNotEmpty(companyTypeStr)) {
			Integer companyType = Integer.parseInt(companyTypeStr);
			dto.setCompanyType(companyType);
			companyIdList_ = null;
		}
		
		companyIdList_ = removeDuplicate(companyIdList_);
		
		CompanyPO po = CompanyConverter.toPO(dto);
		
				
		PageResult<CompanyPO> pageResult = new PageResult<CompanyPO>();
		List<CompanyPO> listT = null;

		if (page != null) {
			
			int cnt = companyReadDAO.countCompanyOfPage(po,companyIdList_);
			
			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				listT = companyReadDAO.findCompanyOfPage(po, page,companyIdList);
			} else {
				listT = new ArrayList<CompanyPO>();
			}
			pageResult.setList(listT);
			pageResult.setTotalSize(cnt);
			pageResult.setPageNo(page.getPageNo());
			pageResult.setPageSize(page.getPageSize());
		} else {
			
			// 公司列表不分页的特殊处理
			listT = companyReadDAO.findCompanyOfPage(po, page,companyIdList);
			pageResult.setList(listT);
		}

		List<CompanyDTO> list = CompanyConverter.toDTO(pageResult.getList());
		PageResult<CompanyDTO> result = new PageResult<CompanyDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}
	
	/**
	 * List去重
	 * 
	 * @param list
	 * @return
	 */
	public List removeDuplicate(List list) {
		if (list == null)
			return null;
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	@Override
	public List<CompanyDTO> findCompanyAll(CompanyDTO dto) {
		CompanyPO po = CompanyConverter.toPO(dto);
		List<CompanyPO> list = companyReadDAO.findAll(po,null);
		return CompanyConverter.toDTO(list);
	}

	/**
	 * 根据companyId查询部门列表
	 * 
	 * @param companyId
	 * @return
	 */
	@Override
	public List<DepartmentDTO> queryDepartmentListByCompanyId(Long companyId) {
		List<DepartmentPO> pos = departmentReadDAO.queryDepartmentByCompanyId(companyId);
		return DepartmentConverter.toDTO(pos);
	}

	@Override
	public List<CompanyDTO> queryCompanysByPlatformId(Long platformId) {

		return CompanyConverter.toDTO(companyReadDAO.queryCompanysByPlatformId(platformId));
	}

	@Override
	public CompanyDTO queryCompanyByUserId(long userId) {
		return CompanyConverter.toDTO(companyReadDAO.queryCompanyByUserId(userId));
	}

	/**
	 * 根据福利公司id集合查询福利公司信息
	 * 
	 * @param companyIds
	 * @return
	 */
	@Override
	public List<CompanyDTO> findCompanyByCompanyIds(List<Long> companyIds) {
		List<CompanyPO> list = companyReadDAO.findCompanyByCompanyIds(companyIds);
		return CompanyConverter.toDTO(list);
	}

	/**
	 * 根据公司id集合查询公司名称信息
	 * 
	 * @param companyIds
	 * @return
	 */
	@Override
	public List<String> findByCompanys(List<Long> companyIds) {
		// TODO Auto-generated method stub
		return companyReadDAO.findByCompanys(companyIds);
	}

	@Override
	public List<CompanyDTO> queryCompaniesByIds(List<Long> ids) {

		return CompanyConverter.toDTO(companyReadDAO.queryCompaniesByIds(ids));
	}

	@Override
	public CompanyDTO queryCompanyByName(String name) {
		return CompanyConverter.toDTO(companyReadDAO.queryCompanyByName(name));
	}

	@Override
	public List<CompanyDTO> findCompanyAllByFuzzyName(CompanyDTO dto) {
		CompanyPO po = CompanyConverter.toPO(dto);
		List<CompanyPO> list = companyReadDAO.findAllByFuzzyName(po);
		return CompanyConverter.toDTO(list);
	}
	/**
	 * 查询所有福利公司
	 * @return
	 */
	@Override
	public List<CompanyDTO> findCompanyAll() {
		List<CompanyPO> list = companyReadDAO.findCompanyAll();
		return CompanyConverter.toDTO(list);
	}

	@Override
	public List<CompanyDTO> findCompanyByWorkWechat(CompanyDTO dto) {
		return null;
	}
}
