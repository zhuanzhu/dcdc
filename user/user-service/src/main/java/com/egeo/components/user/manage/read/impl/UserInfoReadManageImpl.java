package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserInfoReadManage;
import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.dao.read.CompanyReadDAO;
import com.egeo.components.user.dao.read.DepartmentReadDAO;
import com.egeo.components.user.dao.read.UserExtendReadDAO;
import com.egeo.components.user.dao.read.UserInfoReadDAO;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.components.user.po.UserInfoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;


@Service
public class UserInfoReadManageImpl implements UserInfoReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInfoReadDAO userInfoReadDAO;
	
	@Autowired
	private UserExtendReadDAO userExtendReadDAO;
	
	@Autowired
	private CompanyReadDAO companyReadDAO;
	
	@Autowired
	private DepartmentReadDAO departmentReadDAO;
	
	public UserInfoPO findUserInfoById(UserInfoPO po) {
		UserInfoPO userInfopo = new UserInfoPO();
		userInfopo.setId(po.getId());
		return userInfoReadDAO.findById(userInfopo);
	}

	public PageResult<UserInfoPO> findUserInfoOfPage(UserInfoPO po, Pagination page) {
		
		PageResult<UserInfoPO> pageResult = new PageResult<UserInfoPO>();
		List<UserInfoPO> list = null;

		int cnt = userInfoReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userInfoReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserInfoPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserInfoPO> findUserInfoAll(UserInfoPO po) {

		return userInfoReadDAO.findAll(po,null);
	}

	@Override
	public int findUserSumByInfoId(Long infoId, Long platformId) {
		// TODO Auto-generated method stub
		return userInfoReadDAO.findUserSumByInfoId(infoId, platformId);
	}

	@Override
	public PageResult<UserExtendCondition> findUserByInfoIdOfPage(UserInfoPO po, Pagination page) {
		List<UserInfoPO> list = null;

		int cnt = userInfoReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userInfoReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserInfoPO>();
		}
		// 拼接用户id集合
		List<Long> userIds = new ArrayList<>();
		for (UserInfoPO userInfoPO : list) {
			userIds.add(userInfoPO.getUserId());
		}
		// 根据用户id集合查询用户及关联信息
		List<UserExtendCondition> UserExtendConditionList = new ArrayList<>();
		if(StringUtils.isNotEmpty(userIds))
			UserExtendConditionList = userExtendReadDAO.findUserExtendCByUserIds(userIds);
        
        for (UserExtendCondition userExtendCondition : UserExtendConditionList) {
        	if(StringUtils.isNotEmpty(userExtendCondition.getCompanyId())){
        		// 根据公司id查询公司名称信息
            	CompanyPO companyPO = new CompanyPO();
            	companyPO.setId(userExtendCondition.getCompanyId());
            	CompanyPO companyPO2 = companyReadDAO.findById(companyPO);
            	if(StringUtils.isNotEmpty(companyPO2))
            		userExtendCondition.setCompanyName(companyPO2.getCompanyName());
        	}
				// 根据用户id查询部门信息
	        	DepartmentPO departmentPO2 = departmentReadDAO.queryDepartmentByUserId(userExtendCondition.getId());
	        	if(StringUtils.isNotEmpty(departmentPO2))
	        		userExtendCondition.setDepartmentName(departmentPO2.getDepartmentName());
		}
        PageResult<UserExtendCondition> rt = new PageResult<UserExtendCondition>();
        rt.setList(UserExtendConditionList);
        rt.setTotalSize(cnt);
        rt.setPageNo(page.getPageNo());
        rt.setPageSize(page.getPageSize());
        return rt;
	}

	@Override
	public int findUserInfoSumByUserId(UserInfoPO po) {
		return userInfoReadDAO.findUserInfoSumByUserId(po);
	}
	
}
	