package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.CompanyReadDAO;
import com.egeo.components.user.dao.read.DepartmentReadDAO;
import com.egeo.components.user.manage.read.CompanyReadManage;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CompanyReadManageImpl implements CompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyReadDAO companyReadDAO;
	
	@Autowired 
	private DepartmentReadDAO departmentReadDAO;
	
	public CompanyPO findCompanyById(CompanyPO po) {
		CompanyPO companypo = new CompanyPO();
		companypo.setId(po.getId());
		return companyReadDAO.findById(companypo);
	}

	public PageResult<CompanyPO> findCompanyOfPage(CompanyPO po, Pagination page,List<Long> companyIdList) {
		
		PageResult<CompanyPO> pageResult = new PageResult<CompanyPO>();
		List<CompanyPO> list = null;

		if (page != null) {
			
			int cnt = companyReadDAO.countCompanyOfPage(po,companyIdList);
			
			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				list = companyReadDAO.findCompanyOfPage(po, page,companyIdList);
			} else {
				list = new ArrayList<CompanyPO>();
			}
			pageResult.setList(list);
			pageResult.setTotalSize(cnt);
			pageResult.setPageNo(page.getPageNo());
			pageResult.setPageSize(page.getPageSize());
			return pageResult;
		} else {
			
			// 公司列表不分页的特殊处理
			list = companyReadDAO.findCompanyOfPage(po, page,companyIdList);
			pageResult.setList(list);
			return pageResult;
		}

	}

	public List<CompanyPO> findCompanyAll(CompanyPO po) {
		return companyReadDAO.findAll(po,null);
	}
	
	@Override
	public List<DepartmentPO> queryDepartmentListByCompanyId(Long companyId) {
		return departmentReadDAO.queryDepartmentByCompanyId(companyId);
	}

	@Override
	public List<CompanyPO> queryCompanysByPlatformId(Long platformId) {
		return companyReadDAO.queryCompanysByPlatformId(platformId);
	}

	@Override
	public CompanyPO queryCompanyByUserId(long userId) {
		return companyReadDAO.queryCompanyByUserId(userId);
	}
	/**
	 * 根据福利公司id集合查询福利公司信息
	 * @param companyIds
	 * @return
	 */
	@Override
	public List<CompanyPO> findCompanyByCompanyIds(List<Long> companyIds) {
		return companyReadDAO.findCompanyByCompanyIds(companyIds);
	}
	/**
	 * 根据公司id集合查询公司名称信息
	 * @param companyIds
	 * @return
	 */
	@Override
	public List<String> findByCompanys(List<Long> companyIds) {
		// TODO Auto-generated method stub
		return companyReadDAO.findByCompanys(companyIds);
	}

	@Override
	public List<CompanyPO> queryCompaniesByIds(List<Long> ids) {
		return companyReadDAO.queryCompaniesByIds(ids);
	}

	@Override
	public CompanyPO queryCompanyByName(String name) {
		return companyReadDAO.queryCompanyByName(name);
	}

	@Override
	public List<CompanyPO> findCompanyAllByFuzzyName(CompanyPO po) {
		// TODO Auto-generated method stub
		return companyReadDAO.findAllByFuzzyName(po);
	}
	/**
	 * 查询所有福利公司
	 * @return
	 */
	@Override
	public List<CompanyPO> findCompanyAll() {
		// TODO Auto-generated method stub
		return companyReadDAO.findCompanyAll();
	}

}
	