package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.dao.read.CompanyReadDAO;
import com.egeo.components.user.dao.read.DepartmentReadDAO;
import com.egeo.components.user.dao.read.UserExtendReadDAO;
import com.egeo.components.user.manage.read.UserExtendReadManage;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.DepMemberPO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service
public class UserExtendReadManageImpl implements UserExtendReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserExtendReadDAO userExtendReadDAO;
	
	@Autowired
	private CompanyReadDAO companyReadDAO;
	
	@Autowired
	private DepartmentReadDAO departmentReadDAO;
	@Override
	public UserExtendPO findById(Long id) {
		
		UserExtendPO po = new UserExtendPO();
		po.setId(id);
		return userExtendReadDAO.findById(po);
	}
    @Override
    public PageResult<UserExtendPO> findPage(Pagination page, UserExtendPO po) {
        int cnt = userExtendReadDAO.countOfPage(po);
        List<UserExtendPO> list = new ArrayList<UserExtendPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
                page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
                list = userExtendReadDAO.findOfPage(po, page);
        }
        PageResult<UserExtendPO> pageResult = new PageResult<UserExtendPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
	@Override
	public List<UserExtendPO> queryUserExtendsByIds(List<Long> userIds) {
		
		return userExtendReadDAO.queryUserExtendsByIds(userIds);
	}
	@Override
	public List<UserExtendPO> querySoonBirthdayUsers(Long companyId) {
		
		return userExtendReadDAO.querySoonBirthdayUsers(companyId);
	}
	@Override
	public List<UserExtendPO> querySoonEntrydayUsers(Long companyId) {
		return userExtendReadDAO.querySoonEntrydayUsers(companyId);
	}
	/**
     * 根据用户id查询修改用户头像、真实姓名、公司等信息
     * @param dto
     * @return
     */
	@Override
	public UserExtendCondition userExtendByUserId(Long userId) {
		
		return userExtendReadDAO.userExtendByUserId(userId);
	}
	/**
     * 根据用户id查询个人信息接口
     * @param dto
     * @return
     */
	@Override
	public UserExtendCondition userByUserId(Long userId) {
		
		return userExtendReadDAO.userByUserId(userId);
	}
	/**
	 * 根据公司id按条件查询所有用户信息
	 * @param companyId
	 * @param name
	 * @param departmentId
	 * @param sex
	 * @param birthdayStartTime
	 * @param birthdayFinishTime
	 * @param entryStartTime
	 * @param entryFinishTime
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<UserExtendCondition> userAllOfPage(UserExtendPO po, Pagination page) {
		int cnt = userExtendReadDAO.countuserAllOfPage(po);
        List<UserExtendCondition> list = new ArrayList<UserExtendCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
                page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
                list = userExtendReadDAO.userAllOfPage(po, page);
        }
        PageResult<UserExtendCondition> pageResult = new PageResult<UserExtendCondition>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
	
	@Override
	public PageResult<UserExtendCondition> userExtendAllOfPage(UserExtendPO po, List<Long> companyIds, Pagination page) {
		int cnt = userExtendReadDAO.countuserExtendAllOfPage(po, companyIds,null);
        List<UserExtendCondition> list = new ArrayList<UserExtendCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
                page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
                list = userExtendReadDAO.userExtendAllOfPage(po, companyIds, page);
        }
        for (UserExtendCondition userExtendCondition : list) {
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
        PageResult<UserExtendCondition> pageResult = new PageResult<UserExtendCondition>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
	/**
	 * 根据公司id按条件查询所有用户信息
	 * @param companyId
	 * @param name
	 * @param departmentId
	 * @param sex
	 * @param birthdayStartTime
	 * @param birthdayFinishTime
	 * @param entryStartTime
	 * @param entryFinishTime
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public List<UserExtendCondition> userAll(UserExtendPO po) {
		
		return userExtendReadDAO.userAll(po);
	}
	@Override
	public PageResult<DepMemberPO> queryDepMemberPageExceptThisId(Pagination page,Long depId, Long userId) {
		List<DepMemberPO> poList=userExtendReadDAO.queryDepMemberPageExceptThisId(page,depId,userId);
		Integer totalCount=userExtendReadDAO.queryDepMemberTotalCountExceptThisId(depId,userId);
		PageResult<DepMemberPO> res=new PageResult<>();
		res.setTotalSize(totalCount);
		res.copy(page);
		res.setList(poList);
		return res;
	}
	
	@Override
	public List<DepMemberPO> searchUsers(String keyWord, Long userId, Long companyId) {
		
		return userExtendReadDAO.searchUsers(keyWord,userId,companyId);
	}
	@Override
	public List<UserExtendPO> queryNearestBirthdayUsers(Long companyId) {
		//查询日期大于等于今天的生日日期
		Date targetDate=userExtendReadDAO.queryNearestBirthDateThisYear(companyId);
		//如果无结果
		if(targetDate==null){
			//查询日期最小的生日日期
			targetDate=userExtendReadDAO.queryEarliestBirthDate(companyId);
		}
		//无任何生日信息,返回空list
		if(targetDate==null){
			return new ArrayList<>();
		}
		//根据获取的日期,查询所有当天生日的人
		List<UserExtendPO> res=userExtendReadDAO.queryUserExtendsByBirthDate(targetDate,companyId);
		return res;
	}
	
	@Override
	public UserExtendPO queryUserExtendsByEmail(String email) {
		return userExtendReadDAO.queryUserExtendsByEmail(email);
	}
	@Override
	public List<UserExtendCondition> findAlluser(UserExtendPO po) {
		return userExtendReadDAO.findAlluser(po);
	}
	@Override
	public PageResult<UserExtendPO> queryFullUserExtendPage(Pagination page, UserExtendPO condition) {
		List<UserExtendPO> poList=userExtendReadDAO.queryFullUserExtendPage(page,condition);
		Integer totalCount=userExtendReadDAO.queryFullUserExtendPageTotalSize(condition);
		PageResult<UserExtendPO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}
	/**
	 * 查询所有管理员用户
	 * @return
	 */
	@Override
	public List<UserExtendPO> userAdminAll() {
		// TODO Auto-generated method stub
		return userExtendReadDAO.userAdminAll();
	}
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	@Override
	public List<UserExtendCondition> userByMobile(String mobile,Long platformId) {
		// TODO Auto-generated method stub
		return userExtendReadDAO.userByMobile(mobile,platformId);
	}
	@Override
	public List<UserExtendPO> queryUserByCondition(UserExtendPO po) {
		return userExtendReadDAO.queryUserByCondition(po);
	}
	@Override
	public PageResult<UserExtendCondition> userExtendAllByCompanyOfPage(List<Long> companyList, Pagination page) {
		int cnt = userExtendReadDAO.countUserExtendAllByCompanyOfPage(companyList);
        List<UserExtendCondition> list = new ArrayList<UserExtendCondition>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
                page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
                list = userExtendReadDAO.userExtendAllByCompanyOfPage(companyList, page);
        }
        PageResult<UserExtendCondition> pageResult = new PageResult<UserExtendCondition>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}
	@Override
	public List<Long> findUserIdByIsAdministrator(int isAdministrator) {
		return userExtendReadDAO.findUserIdByIsAdministrator(isAdministrator);
	}
	@Override
	public String findBaiDuChannelId(Long userId) {
		return userExtendReadDAO.findBaiDuChannelId(userId);
	}

	@Override
	public List<Long> findUserList(String name, String mail, String mobile, Integer sex, Date birthday, List<Long> companyId, Long platformId) {

		if(companyId==null||companyId.size()==0){
			return userExtendReadDAO.findUserList(name,mail,mobile,sex,birthday,platformId);

		}

		return userExtendReadDAO.findUserLists(name,mail,mobile,sex,birthday,companyId,platformId);
	}
	@Override
	public UserExtendPO findAdminUserByManage(String mobile, Long platformId) {
		return userExtendReadDAO.findAdminUserByManage(mobile, platformId);
	}	
	@Override
	public List<UserExtendCondition> getUserAdminAll(UserExtendPO po,Long storeId) {
        List<UserExtendCondition> list = new ArrayList<UserExtendCondition>();
        if(storeId == -1L) {
        	list = userExtendReadDAO.getUserAdminAll(po);
        }else {
        	list = userExtendReadDAO.getUserStoreAdminAll(po, storeId);
        }             
        for (UserExtendCondition userExtendCondition : list) {
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

        return list;
	}
	@Override
	public Integer findUserSumByStoreId(Long storeId, Long platformId) {
		return userExtendReadDAO.findUserSumByStoreId(storeId, platformId);
	}
	@Override
	public Integer findCurrentMonthUserSumByStoreId(Long storeId, Long platformId) {
		return userExtendReadDAO.findCurrentMonthUserSumByStoreId(storeId,new Date(), platformId);
	}
	@Override
	public Integer findCurrentDayUserSumByStoreId(Long storeId, Long platformId) {
		return userExtendReadDAO.findCurrentDayUserSumByStoreId(storeId,new Date(), platformId);
	}

	@Override
	public List<UserExtendPO> queryFullUserExtend(List<Long> userIdList) {
		List<UserExtendPO> poList=userExtendReadDAO.queryFullUserExtend(userIdList);
		return poList;
	}
	@Override
	public List<UserExtendCondition> findByUserIds(List<Long> ids) {
		return userExtendReadDAO.findByUserIds(ids);
	}
	@Override
	public UserExtendCondition findCompanyAdmin(Long companyId) {
		// TODO Auto-generated method stub
		List<UserExtendCondition> users = userExtendReadDAO.userAdminCompany(companyId);
		if(users!=null && users.size()>0) {
			return users.get(0);
		}
		return null;
	}


}
	