package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.common.NormalConstant;
import com.egeo.components.promotion.client.CouponCompanyClient;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.dto.DepMemberDTO;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.components.user.service.read.ChannelReadService;
import com.egeo.components.user.service.read.CompanyPageReadService;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.service.read.StoreAdminReadService;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.components.user.service.write.StoreAdminWriteService;
import com.egeo.components.user.service.write.UserExtendWriteService;
import com.egeo.components.user.service.write.UserWelfareWriteService;
import com.egeo.components.user.service.write.UserWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class UserExtendFacade {
	@Resource
	private UserExtendReadService userExtendReadService;

	@Resource
	private UserExtendWriteService userExtendWriteService;

	@Resource
	private UserWriteService userWriteService;

	@Resource
	private UserWelfareWriteService userWelfareWriteService;

	@Resource
	private CompanyPageReadService companyPageReadService;

	@Resource
	private ChannelReadService channelReadService;

	@Resource
	private CouponCompanyClient couponCompanyReadService;

	@Resource
	private CompanyReadService companyReadService;

	@Resource
	private StoreAdminReadService storeAdminReadService;

	@Resource
	private StoreAdminWriteService storeAdminWriteService;

	public UserExtendDTO findById(Long id) {

		return userExtendReadService.findById(id);
	}
	/**
     * 完善用户扩展表信息
     * @param dto
     * @return
     */
	public int saveUserExtendWithTx(UserExtendDTO dto) {

		return userExtendWriteService.saveWithTx(dto);
	}
	/**
	 * 更新用户主表信息
	 */
	public int updateUserWithTx(Long userId, Long companyId) {

		return userWriteService.updateUserWithTx(userId,companyId);
	}
	/**
	 * 添加扩展表信息
	 */
	public Long saveUserWelfareWithTx(UserWelfareDTO userWelfareDTO) {

		return userWelfareWriteService.insertUserWelfareWithTx(userWelfareDTO);
	}
	/**
     * 根据用户id修改用户扩展表信息(真实姓名、性别、生日)
     * @param dto
     * @return
     */
	public int updateUserExtend(UserExtendDTO dto) {

		return userExtendWriteService.updateUserExtendWithTx(dto);
	}
	/**
     * 根据用户id查询修改用户头像、真实姓名、公司等信息
     * @param dto
     * @return
     */
	public UserExtendDTO userExtendByUserId(Long userId) {

		return userExtendReadService.userExtendByUserId(userId);
	}
	/**
     * 根据用户id查询个人信息接口
     * @param dto
     * @return
     */
	public UserExtendDTO userByUserId(Long userId) {

		return userExtendReadService.userByUserId(userId);
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
	public PageResult<UserExtendDTO> userAllOfPage(UserExtendDTO dto, Pagination page) {

		return userExtendReadService.userAllOfPage(dto,page);
	}

	public PageResult<UserExtendDTO> userExtendAllOfPage(UserExtendDTO dto, Long couponId, Long couponGroupId,Pagination page) {
		List<Long> companyIds = null;
		if(EmptyUtil.isNotEmpty(couponId) || EmptyUtil.isNotEmpty(couponGroupId)){
			List<Long> officialList = new ArrayList<>();
			List<Long> competitiveList = new ArrayList<>();
			List<Long> testList = new ArrayList<>();
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyType(NormalConstant.COMPANY_OFFICIAL);
			List<CompanyDTO> officialCompanyList = companyReadService.findCompanyAll(companyDTO);
			companyDTO.setCompanyType(NormalConstant.COMPANY_COMPETITIVE_PRODUCTS);
			List<CompanyDTO> competitiveCompanyList = companyReadService.findCompanyAll(companyDTO);
			companyDTO.setCompanyType(NormalConstant.COMPANY_TEST);
			List<CompanyDTO> testCompanyList = companyReadService.findCompanyAll(companyDTO);
			for (CompanyDTO companyDTO_ : officialCompanyList) {
				officialList.add(companyDTO_.getId());
			}
			for (CompanyDTO companyDTO_ : competitiveCompanyList) {
				competitiveList.add(companyDTO_.getId());
			}
			for (CompanyDTO companyDTO_ : testCompanyList) {
				testList.add(companyDTO_.getId());
			}

			companyIds = com.egeo.utils.StringUtils.stringsToLongs(couponCompanyReadService.findCompanyByCouponIdOrCouponGroupId(couponId, couponGroupId, com.egeo.utils.StringUtils.longsToStrings(officialList), com.egeo.utils.StringUtils.longsToStrings(competitiveList), com.egeo.utils.StringUtils.longsToStrings(testList)));
			if (EmptyUtil.isEmpty(companyIds)) {
				companyIds.add(-999L);
			}
		}
		return userExtendReadService.userExtendAllOfPage(dto, companyIds, page);
	}

	public PageResult<UserExtendDTO> userExtendAllOfPage(UserExtendDTO dto, Long couponId, Long couponGroupId,Pagination page,List<Long> companyIds) {

		return userExtendReadService.userExtendAllOfPage(dto, companyIds, page);
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
	public List<UserExtendDTO> userAll(UserExtendDTO dto) {

		return userExtendReadService.userAll(dto);
	}

	/**
	 * 查询除当前用户之外的部门用户分页列表
	 * @param page
	 * @param userId
	 * @return
	 */
	public PageResult<DepMemberDTO> queryDepMemberPageExceptThisId(Pagination page,Long depId, Long userId) {
		return userExtendReadService.queryDepMemberPageExceptThisId(page,depId,userId);
	}

	/**
	 * 模糊搜索用户
	 * 排除userid
	 * 约束公司
	 * 约束姓名
	 * @param keyWord
	 * @param userId
	 * @param companyId
	 * @return
	 */
	public List<DepMemberDTO> searchUsers(String keyWord, Long userId, Long companyId) {
		return userExtendReadService.searchUsers(keyWord,userId,companyId);
	}
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	public int updateUserBackground(Long userId, String headPicUrl) {
		return userExtendWriteService.updateUserBackground(userId, headPicUrl);
	}
	public List<UserExtendDTO> findAlluser(UserExtendDTO userExtendDTO) {
		return userExtendReadService.findAlluser(userExtendDTO);
	}
	/**
	 * 根据公司id查询公司页面配置信息
	 * @param companyId
	 * @return
	 */
	public List<CompanyPageDTO> findCompanyPageByCompanyId(Long companyId) {
		CompanyPageDTO companyPageDTO = new CompanyPageDTO();
		companyPageDTO.setCompanyId(companyId);
		return companyPageReadService.findCompanyPageAll(companyPageDTO);
	}
	/**
	 * 根据渠道id查询渠道信息
	 * @param channelId
	 * @return
	 */
	public ChannelDTO findChannelByChannelId(Long channelId) {
		ChannelDTO channelDTO = new ChannelDTO();
		channelDTO.setId(channelId);
		return channelReadService.findChannelById(channelDTO);
	}

	public int updateWithTx(UserExtendDTO dto) {
		return userExtendWriteService.updateWithTx(dto);
	}

	/**
	 * 取消管理员身份
	 * @param dto
	 * @return
	 */
	public int cancelAdministratorWithTx(UserExtendDTO dto) {
		//判断是否为门店管理员
		StoreAdminDTO storeAdminDTO = new StoreAdminDTO();
		storeAdminDTO.setUserId(dto.getId());
		StoreAdminDTO storeAdminDTO2 = storeAdminReadService.findStoreAdminById(storeAdminDTO);
		if (EmptyUtil.isNotEmpty(storeAdminDTO2)){
			//是门店管理员,删除门店管理员身份
			storeAdminWriteService.deleteStoreAdminWithTx(storeAdminDTO2);
		}
		return  userExtendWriteService.updateWithTx(dto);
	}

	public int updateInvalidTime(UserExtendDTO dto) {
		return userExtendWriteService.updateInvalidTime(dto);
	}
	/**
	 * 查询所有管理员用户
	 * @return
	 */
	public List<Map<String, Object>> userAdminAll() {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<UserExtendDTO> userExtendList = userExtendReadService.userAdminAll();
		for (UserExtendDTO userExtendDTO : userExtendList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", userExtendDTO.getId());
			map.put("name", userExtendDTO.getName());
			maps.add(map);
		}
		return maps;
	}
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	public List<Map<String, Object>> userByMobile(String mobile,Long platformId) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<UserExtendDTO> userExtendList = userExtendReadService.userByMobile(mobile,platformId);
		for (UserExtendDTO userExtendDTO : userExtendList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", userExtendDTO.getId());
			map.put("name", userExtendDTO.getName());
			map.put("mail", userExtendDTO.getMail());
			map.put("headPicUrl", userExtendDTO.getHeadPicUrl());
			map.put("companyId", userExtendDTO.getCompanyId());
			map.put("companyName", userExtendDTO.getCompanyName());
			map.put("status", userExtendDTO.getStatus());
			maps.add(map);
		}
		return maps;
	}
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	public List<UserExtendDTO> userExtendByMobile(String mobile,Long platformId) {
		return userExtendReadService.userByMobile(mobile,platformId);
	}
	public List<UserExtendDTO> queryUserByCondition(UserExtendDTO dto) {
		return userExtendReadService.queryUserByCondition(dto);
	}
	public int updateUserExtendInfoWithTx(UserExtendDTO dto) {
		return userExtendWriteService.updateUserExtendInfoWithTx(dto);
	}
	/**
	 * 根据手机号绑定微信OpenId
	 * @param mobile
	 * @param weiXinOpenId
	 * @return
	 */
	public int bindingWeiXinOpenIdByMobile(String mobile, String weiXinOpenId) {
		// TODO Auto-generated method stub
		return userExtendWriteService.bindingWeiXinOpenIdByMobileWithTx(mobile, weiXinOpenId);
	}
	/**
	 * 根据用户id绑定微信OpenId
	 * @param userId 用户id
	 * @param weiXinOpenId 微信OpenId
	 * @return
	 */
	public int bindingWeiXinOpenIdByUserId(Long userId, String weiXinOpenId) {
		return userExtendWriteService.bindingWeiXinOpenIdByUserIdWithTx(userId, weiXinOpenId);

	}
	/**
	 * 切换用户OpenId
	 * @param id
	 * @param userId
	 * @return
	 */
	public int switchUserOpenId(Long id, Long userId) {
		// TODO Auto-generated method stub
		return userExtendWriteService.switchUserOpenIdWithTx(id, userId);
	}
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param userId
	 * @return
	 */
	public int weixinSignoutWithTx(String mobile) {
		// TODO Auto-generated method stub
		return userExtendWriteService.weixinSignoutWithTx(mobile);
	}
	public int bindingChannelIdByUserId(Long userId, String baiDuChannelId,Integer deviceType) {
		return userExtendWriteService.bindingChannelIdByUserIdWithTx(userId, baiDuChannelId,deviceType);
	}
	/**
	 * 根据userId退出
	 * @param userId
	 * @return
	 */
	public int signoutByUserId(Long userId) {
		return userExtendWriteService.signoutByUserIdWithTx(userId);
	}

	public List<StoreAdminDTO> getStoreAdminAll(StoreAdminDTO storeAdminDTO) {
		return storeAdminReadService.findStoreAdminAll(storeAdminDTO);
	}

	public List<UserExtendDTO> getUserAdminAll(UserExtendDTO userExtendDTO) {
		return userExtendReadService.getUserAdminAll(userExtendDTO);
	}
	public UserExtendDTO findAdminUserByManage(String mobile, Long platformId) {
		return userExtendReadService.findAdminUserByManage(mobile, platformId);
	}

	public int updateReImportUserStatus(Long id) {
		return userExtendWriteService.updateReImportUserStatus(id);
	}

	public void updateUserExtendWithTx(UserExtendDTO userExtendDTO) {
		userExtendWriteService.updateUserExtendWithTx(userExtendDTO);
	}

	public int fillRealNameAndIdCard(Long userId,String realName,String idCardNo){
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		userExtendDTO.setId(userId);
		userExtendDTO.setIdCardNo(idCardNo);
		userExtendDTO.setName(realName);
		return userExtendWriteService.updateUserExtendWithTx(userExtendDTO);
	}
}
