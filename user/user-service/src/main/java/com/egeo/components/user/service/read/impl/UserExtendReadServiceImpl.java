package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.converter.DepMemberConverter;
import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.dto.DepMemberDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.manage.read.UserExtendReadManage;
import com.egeo.components.user.po.DepMemberPO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("userExtendReadService")
public class UserExtendReadServiceImpl implements UserExtendReadService {
	@Autowired
	private UserExtendReadManage userExtendReadManage;

	@Override
	public UserExtendDTO findById(Long id) {
		
		return UserExtendConverter.toDTO(userExtendReadManage.findById(id));
	}

	@Override
	public PageResult<UserExtendDTO> findProductUser(Pagination page, UserExtendDTO dto2) {
		UserExtendPO po = UserExtendConverter.toPO(dto2);
        PageResult<UserExtendPO> pageResult = userExtendReadManage.findPage(page, po);
        
        List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        for (UserExtendPO tmp : pageResult.getList()) {
            UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(tmp);
                list.add(userExtendDTO);
        }
        PageResult<UserExtendDTO> result = new PageResult<UserExtendDTO>();
        result.setList(list);
        result.copy(pageResult);
        return result;
	}

	@Override
	public List<UserExtendDTO> queryUserExtendsByIds(List<Long> userIds) {
		
		return UserExtendConverter.toDTO(userExtendReadManage.queryUserExtendsByIds(userIds));
	}

	@Override
	public List<UserExtendDTO> querySoonBirthdayUsers(Long companyId) {
		return UserExtendConverter.toDTO(userExtendReadManage.querySoonBirthdayUsers(companyId));
	}

	@Override
	public List<UserExtendDTO> querySoonEntrydayUsers(Long companyId) {
		return UserExtendConverter.toDTO(userExtendReadManage.querySoonEntrydayUsers(companyId));
	}
	/**
     * 根据用户id查询修改用户头像、真实姓名、公司等信息
     * @param dto
     * @return
     */
	@Override
	public UserExtendDTO userExtendByUserId(Long userId) {
		UserExtendCondition userExtendCondition = userExtendReadManage.userExtendByUserId(userId);
		UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(userExtendCondition);
		userExtendDTO.setCompanyId(userExtendCondition.getCompanyId());
		userExtendDTO.setCompanyName(userExtendCondition.getCompanyName());
		return userExtendDTO;
	}
	/**
     * 根据用户id查询个人信息接口
     * @param dto
     * @return
     */
	@Override
	public UserExtendDTO userByUserId(Long userId) {
		UserExtendCondition userExtendCondition = userExtendReadManage.userByUserId(userId);
		UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(userExtendCondition);
		if(userExtendCondition != null && userExtendDTO != null){
			userExtendDTO.setCompanyId(userExtendCondition.getCompanyId());
			userExtendDTO.setCompanyName(userExtendCondition.getCompanyName());
			userExtendDTO.setDepartmentId(userExtendCondition.getDepartmentId());
			userExtendDTO.setDepartmentName(userExtendCondition.getDepartmentName());
			userExtendDTO.setEntryTime(userExtendCondition.getEntryTime());
			userExtendDTO.setPaymentCode(userExtendCondition.getPaymentCode());
			userExtendDTO.setMail(userExtendCondition.getMail());
			userExtendDTO.setBackgrondImg(userExtendCondition.getBackgrondImg());
			userExtendDTO.setCompanyLogo(userExtendCondition.getCompanyLogo());
			userExtendDTO.setIsAvailable(userExtendCondition.getIsAvailable());
		}
		return userExtendDTO;
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
	public PageResult<UserExtendDTO> userAllOfPage(UserExtendDTO dto, Pagination page) {
		UserExtendPO po = UserExtendConverter.toPO(dto);
        PageResult<UserExtendCondition> pageResult = userExtendReadManage.userAllOfPage(po,page);
        
        List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        for (UserExtendCondition tmp : pageResult.getList()) {
            UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(tmp);
            userExtendDTO.setCompanyId(tmp.getCompanyId());
    		userExtendDTO.setCompanyName(tmp.getCompanyName());
    		userExtendDTO.setDepartmentId(tmp.getDepartmentId());
    		userExtendDTO.setDepartmentName(tmp.getDepartmentName());
    		userExtendDTO.setEntryTime(tmp.getEntryTime());
    		userExtendDTO.setMail(tmp.getMail());
            list.add(userExtendDTO);
        }
        PageResult<UserExtendDTO> result = new PageResult<UserExtendDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
	
	@Override
	public PageResult<UserExtendDTO> userExtendAllOfPage(UserExtendDTO dto, List<Long> companyIds, Pagination page) {
		UserExtendPO po = UserExtendConverter.toPO(dto);
		if (StringUtils.isEmpty(companyIds)) {
			companyIds = null;
		}
        PageResult<UserExtendCondition> pageResult = userExtendReadManage.userExtendAllOfPage(po,companyIds,page);
        List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        
        for (UserExtendCondition tmp : pageResult.getList()) {
            UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(tmp);
            
    		userExtendDTO.setCompanyName(tmp.getCompanyName());
    		userExtendDTO.setDepartmentName(tmp.getDepartmentName());
    		userExtendDTO.setMail(tmp.getMail());
    		userExtendDTO.setIsAvailable(tmp.getIsAvailable());
    		userExtendDTO.setChannelId(tmp.getChannelId());
            list.add(userExtendDTO);
        }
        PageResult<UserExtendDTO> result = new PageResult<UserExtendDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
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
	public List<UserExtendDTO> userAll(UserExtendDTO dto) {
		List<UserExtendCondition> userExtendList = userExtendReadManage.userAll(UserExtendConverter.toPO(dto));
		List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        for (UserExtendCondition tmp : userExtendList) {
            UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(tmp);
            userExtendDTO.setCompanyId(tmp.getCompanyId());
    		userExtendDTO.setCompanyName(tmp.getCompanyName());
    		userExtendDTO.setDepartmentId(tmp.getDepartmentId());
    		userExtendDTO.setDepartmentName(tmp.getDepartmentName());
    		userExtendDTO.setEntryTime(tmp.getEntryTime());
    		userExtendDTO.setMail(tmp.getMail());
			userExtendDTO.setImportRecordsId(tmp.getImportRecordsId());
            list.add(userExtendDTO);
        }
		return list;
	}

	@Override
	public PageResult<DepMemberDTO> queryDepMemberPageExceptThisId(Pagination page,Long depId, Long userId) {
		PageResult<DepMemberPO> poPage=userExtendReadManage.queryDepMemberPageExceptThisId(page,depId,userId);
		PageResult<DepMemberDTO> res=new PageResult<>();
		res.copy(poPage);
		res.setList(DepMemberConverter.toDTO(poPage.getList()));
		return res;
	}

	@Override
	public List<DepMemberDTO> searchUsers(String keyWord, Long userId, Long companyId) {
		return DepMemberConverter.toDTO(userExtendReadManage.searchUsers(keyWord,userId,companyId));
	}

	@Override
	public List<UserExtendDTO> queryNearestBirthdayUsers(Long companyId) {
		return UserExtendConverter.toDTO(userExtendReadManage.queryNearestBirthdayUsers(companyId));
	}

	@Override
	public UserExtendDTO queryUserExtendsByEmail(String email) {
		
		return UserExtendConverter.toDTO(userExtendReadManage.queryUserExtendsByEmail(email));
	}

	@Override
	public List<UserExtendDTO> findAlluser(UserExtendDTO userExtendDTO) {
		List<UserExtendCondition> userExtendList = userExtendReadManage.findAlluser(UserExtendConverter.toPO(userExtendDTO));
		List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        for (UserExtendCondition tmp : userExtendList) {
            UserExtendDTO dto = UserExtendConverter.toDTO(tmp);
            dto.setCompanyId(tmp.getCompanyId());
            dto.setCompanyName(tmp.getCompanyName());
            dto.setDepartmentId(tmp.getDepartmentId());
            dto.setDepartmentName(tmp.getDepartmentName());
            dto.setEntryTime(tmp.getEntryTime());
            dto.setMail(tmp.getMail());
            list.add(dto);
        }
		return list;
	}

	@Override
	public PageResult<UserExtendDTO> queryFullUserExtendPage(Pagination page, UserExtendDTO condition) {
		UserExtendPO poCondition=UserExtendConverter.toPO(condition);
		PageResult<UserExtendPO> poPage=userExtendReadManage.queryFullUserExtendPage(page, poCondition);
		PageResult<UserExtendDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(UserExtendConverter.toDTO(poPage.getList()));
		return dtoPage;
	}
	/**
	 * 查询所有管理员用户
	 * @return
	 */
	@Override
	public List<UserExtendDTO> userAdminAll() {
		List<UserExtendPO> userExtendList = userExtendReadManage.userAdminAll();
		return UserExtendConverter.toDTO(userExtendList);
	}
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	@Override
	public List<UserExtendDTO> userByMobile(String mobile,Long platformId) {
		List<UserExtendCondition> userExtendList = userExtendReadManage.userByMobile(mobile,platformId);
		List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        for (UserExtendCondition tmp : userExtendList) {
            UserExtendDTO dto = UserExtendConverter.toDTO(tmp);
            dto.setCompanyId(tmp.getCompanyId());
            dto.setCompanyName(tmp.getCompanyName());
            list.add(dto);
        }
		return list;
	}

	@Override
	public List<UserExtendDTO> queryUserByCondition(UserExtendDTO dto) {
		return UserExtendConverter.toDTO(userExtendReadManage.queryUserByCondition(UserExtendConverter.toPO(dto)));
	}

	@Override
	public PageResult<UserExtendDTO> userExtendAllByCompanyOfPage(List<Long> companyList, Pagination page) {
        PageResult<UserExtendCondition> pageResult = userExtendReadManage.userExtendAllByCompanyOfPage(companyList,page);
        List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        
        for (UserExtendCondition tmp : pageResult.getList()) {
            UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(tmp);
            
    		userExtendDTO.setCompanyName(tmp.getCompanyName());
    		userExtendDTO.setDepartmentName(tmp.getDepartmentName());
    		userExtendDTO.setMail(tmp.getMail());
    		userExtendDTO.setIsAvailable(tmp.getIsAvailable());
    		userExtendDTO.setChannelId(tmp.getChannelId());
            list.add(userExtendDTO);
        }
        PageResult<UserExtendDTO> result = new PageResult<UserExtendDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
	
	@Override
	public List<Long> findUserIdByIsAdministrator(int isAdministrator) {
		return userExtendReadManage.findUserIdByIsAdministrator(isAdministrator);
	}

	@Override
	public List<Long> findUserList(String name, String mail, String mobile, Integer sex, Date birthday, List<Long> companyId, Long platformId) {

		return userExtendReadManage.findUserList(name,mail,mobile,sex,birthday,companyId,platformId);
	}


	@Override
	public List<UserExtendDTO> getUserAdminAll(UserExtendDTO userExtendDTO) {
		UserExtendPO po = UserExtendConverter.toPO(userExtendDTO);
		List<UserExtendCondition> userExtendList =
				userExtendReadManage.getUserAdminAll(po,userExtendDTO.getStoreId()) ;
		

		List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        for (UserExtendCondition tmp : userExtendList) {
            UserExtendDTO dto = UserExtendConverter.toDTO(tmp);
            dto.setCompanyId(tmp.getCompanyId());
            dto.setCompanyName(tmp.getCompanyName());
            dto.setDepartmentId(tmp.getDepartmentId());
            dto.setDepartmentName(tmp.getDepartmentName());
            dto.setEntryTime(tmp.getEntryTime());
            dto.setMail(tmp.getMail());
            list.add(dto);
        }
		return list;
	}

	@Override
	public UserExtendDTO findAdminUserByManage(String mobile, Long platformId) {
		UserExtendPO userExtendPO = userExtendReadManage.findAdminUserByManage(mobile, platformId);
		return UserExtendConverter.toDTO(userExtendPO);
	}

	@Override
	public Integer findUserSumByStoreId(Long storeId, Long platformId) {
		return userExtendReadManage.findUserSumByStoreId(storeId, platformId);
	}

	@Override
	public Integer findCurrentMonthUserSumByStoreId(Long storeId, Long platformId) {
		return userExtendReadManage.findCurrentMonthUserSumByStoreId(storeId, platformId);
	}

	@Override
	public Integer findCurrentDayUserSumByStoreId(Long storeId, Long platformId) {
		return userExtendReadManage.findCurrentDayUserSumByStoreId(storeId, platformId);
	}

	@Override
	public List<UserExtendDTO> queryFullUserExtend(List<Long> userIdList) {
		List<UserExtendPO> poPage=userExtendReadManage.queryFullUserExtend(userIdList);
		UserExtendConverter.toDTO(poPage);
		return UserExtendConverter.toDTO(poPage);
	}

	@Override
	public List<UserExtendDTO> findByUserIds(List<Long> ids) {
		List<UserExtendCondition> userExtendList =
				userExtendReadManage.findByUserIds(ids) ;
		List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        for (UserExtendCondition tmp : userExtendList) {
            UserExtendDTO dto = UserExtendConverter.toDTO(tmp);
            dto.setCompanyId(tmp.getCompanyId());
            dto.setCompanyName(tmp.getCompanyName());
            dto.setMail(tmp.getMail());
            list.add(dto);
        }
        return list;
	}


}
	