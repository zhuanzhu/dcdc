package com.egeo.components.user.facade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.egeo.components.config.client.*;
import com.egeo.components.user.business.util.DLFUserUtils;
import com.egeo.components.user.common.Constants;
import com.egeo.components.user.dao.write.EnterpriseMapper;
import com.egeo.components.user.dto.*;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.user.service.write.*;
import com.egeo.components.user.vo.ChannelUserUniqueRegLoginVO;
import com.egeo.components.user.vo.SwitchUserEnterpriseVO;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.egeo.common.BusinessConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountFlowClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.promotion.client.CouponBatchClient;
import com.egeo.components.promotion.client.CouponClient;
import com.egeo.components.promotion.client.CouponGroupStoreClient;
import com.egeo.components.promotion.client.CouponStoreClient;
import com.egeo.components.promotion.client.CouponUnitClient;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.user.common.DateUtils;
import com.egeo.components.user.common.LockConfig;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.service.read.CompanyCoreReadService;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.service.read.InsuranceLoginReadService;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.components.user.service.read.UserQuitReadService;
import com.egeo.components.user.service.read.UserQuitTempReadService;
import com.egeo.components.user.service.read.UserReadService;
import com.egeo.components.user.service.read.UserTempReadService;
import com.egeo.components.user.service.read.UserWelfareReadService;
import com.egeo.components.user.vo.ProductUser;
import com.egeo.components.user.vo.UserVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Support;
import com.egeo.util.security.MD5Util;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.UUIDBuild;
import com.egeo.utils.WorkWeChatUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.lock.LogicLock;
import com.egeo.utils.str.UUID;

@Component
public class UserFacade {

	@Resource
	private UserReadService userReadService;

	@Resource
	private UserWriteService userWriteService;

	@Resource
	private UserExtendReadService userExtendReadService;

	@Resource
	private UserExtendWriteService userExtendWriteService;

	@Resource
	private CompanyReadService companyReadService;

	@Autowired
	private PaymentCodeSaltClient paymentCodeSaltWriteService;

	@Autowired
	private HeadImportRecordsClient headImportRecordsReadService;

	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;

	@Autowired
	private ImportRecordsClient importRecordsWriteService;

	@Autowired
	private ImportRecordsClient importRecordsReadService;

	@Resource
	private UserTempReadService userTempReadService;

	@Resource
	private UserWelfareReadService userWelfareReadService;

	@Resource
	private UserWelfareWriteService userWelfareWriteService;

	@Autowired
	private SaltClient saltWriteService;

	@Autowired
	private UserAccountClient userAccountWriteService;

	@Resource
	private UserQuitTempReadService userQuitTempReadService;

	@Resource
	private UserQuitWriteService userQuitWriteService;

	@Resource
	private UserQuitReadService userQuitReadService;

	@Resource
	private InsuranceLoginWriteService insuranceLoginWriteService;

	@Resource
	private InsuranceLoginReadService insuranceLoginReadService;

	@Autowired
	private PaymentCodeSaltClient paymentCodeSaltReadService;

    @Autowired
    private LockConfig lockConfig;
	@Autowired
	private UserAccountClient userAccountReadService;

	@Resource
	private UserTempWriteService userTempWriteService;

	@Resource
	private SendInfoWriteService sendInfoWriteService;

	@Autowired
    private CouponStoreClient couponStoreReadService;

	@Autowired
    private CouponGroupStoreClient couponGroupStoreReadService;

	@Autowired
    private CouponBatchClient couponBatchReadService;

	@Autowired
    private CouponUnitClient couponUnitReadService;

	@Autowired
    private CouponClient couponReadService;

	@Autowired
    private CouponUnitClient couponUnitWriteService;

	@Autowired
	private UserQuitTempWriteService userQuitTempWriteService;

	@Autowired
	private AccountFlowClient accountFlowReadService;
	@Resource
	private CompanyCoreReadService companyCoreReadService;

	@Autowired
	private WorkWeChatUtil workWeChatUtil;

	@Autowired
	private UserPlatformWriteService userPlatformWriteService;

	@Autowired
	private UserRoleWriteService userRoleWriteService;

	@Autowired
	private CompanyConfigClient companyConfigClient;

	@Autowired
	private EnterpriseMapper uniauthDepartmentService;

	@Autowired
	private CompanyWriteService companyWriteService;

	public UserDTO findUniqueUser(UserDTO dto) {

		return userReadService.findUniqueUser(dto);
	}

	public int updateEncryptInfoByUserWithTx(UserDTO updUser) {

		return userWriteService.updateEncryptInfoByUserWithTx(updUser);
	}

	public UserDTO findUserByID(Long userId) {

		return userReadService.findUserByID(userId);
	}

	public int updateUserInfo(UserDTO user) {

		return userWriteService.updateUserInfoWithTx(user);
	}

	public UserAccountDTO queryUserAccountByUserIdAndType(Long userId,Integer accountType){
		return userAccountReadService.queryUserAccountByUserIdAndType(userId,accountType);
	}

	public PageResult<UserDTO> findPage(Pagination page, UserDTO dto, UserExtendDTO userExtendDTO,
			UserCookieDTO userCookieDTO,Integer... types) {

		return userReadService.findPage(page, dto, userExtendDTO, userCookieDTO,types);

	}

	public PageResult<UserDTO> findPage(Pagination page, UserDTO dto,Integer... types) {

		return userReadService.findPage(page, dto, types);

	}
	public String saveOrUpdate(UserDTO dto, UserExtendDTO userExtendDTO, UserCookieDTO userCookieDTO) {
		return userWriteService.saveOrUpdateWithTx(dto, userExtendDTO, userCookieDTO);

	}

	public void deleteWithTx(UserDTO dto) {
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		userExtendDTO.setId(dto.getId());
		userExtendWriteService.deleteWithTx(userExtendDTO);
		userWriteService.deleteWithTx(dto);

	}

	public UserDTO userById(UserDTO dto) {

		return userReadService.userById(dto);
	}

	public PageResult<ProductUser> findProductUser(Pagination page, UserDTO dto, UserExtendDTO dto2) {
		PageResult<ProductUser> pageResult = new PageResult<ProductUser>();
		List<ProductUser> lists = new ArrayList<ProductUser>();
		// 查看用户信息
		PageResult<UserDTO> userList = userReadService.findProductUser(page, dto);
		List<UserDTO> list = userList.getList();
		for (UserDTO userDTO : list) {
			ProductUser productUser = new ProductUser();
			// 根据用户id查询扩展信息
			UserExtendDTO userExtendDTO = userExtendReadService.findById(userDTO.getId());
			productUser.setId(userDTO.getId());
			productUser.setName(userExtendDTO.getName());
			productUser.setLoginName(userDTO.getLoginName());
			productUser.setSex(userExtendDTO.getSex());
			productUser.setBirthday(userExtendDTO.getBirthday());
			productUser.setMobile(userDTO.getMobile());
			productUser.setMail(userDTO.getMail());
			productUser.setStatus(userExtendDTO.getStatus());
			productUser.setCreateTime(userDTO.getCreateTime());
			lists.add(productUser);
		}
		pageResult.setList(lists);
		pageResult.setPageNo(userList.getPageNo());
		pageResult.setPageSize(userList.getPageSize());
		pageResult.setPlatformName(userList.getPlatformName());
		pageResult.setTotalSize(userList.getTotalSize());
		return pageResult;
	}

	public ProductUser productUserById(ProductUser vo) {
		// 根据用户id查询用户信息
		UserDTO userDTO = userReadService.findUserByID(vo.getId());
		// 根据用户id查询用户扩展信息
		UserExtendDTO userExtendDTO = userExtendReadService.findById(vo.getId());

		ProductUser productUser = new ProductUser();
		productUser.setId(userDTO.getId());
		productUser.setName(userExtendDTO.getName());
		productUser.setLoginName(userDTO.getLoginName());
		productUser.setSex(userExtendDTO.getSex());
		productUser.setBirthday(userExtendDTO.getBirthday());
		productUser.setMobile(userDTO.getMobile());
		productUser.setMail(userDTO.getMail());
		productUser.setStatus(userExtendDTO.getStatus());
		productUser.setCreateTime(userDTO.getCreateTime());
		return productUser;
	}

	/**
	 * 查询公司部门列表
	 *
	 * @param companyId
	 * @return
	 */
	public List<DepartmentDTO> queryDepartmentListByCompanyId(Long companyId) {
		return companyReadService.queryDepartmentListByCompanyId(companyId);
	}

	/**
	 * 根据公司id查询用户列表
	 *
	 * @param companyId
	 * @param page
	 * @return
	 */
	public PageResult<UserExtendDTO> queryUserListByCompanyId(Long companyId, Pagination page) {
		return userReadService.queryUserListByCompanyId(companyId, page);
	}

	/**
	 * 根据部门id查询用户列表
	 *
	 * @param departmentId
	 * @param page
	 * @return
	 */
	public PageResult<UserExtendDTO> queryUserListByDepartmentId(Long departmentId, Pagination page) {

		return userReadService.queryUserListByDepartmentId(departmentId, page);
	}

	/**
	 * 根据邮箱查询用户
	 *
	 * @param mail
	 * @return
	 */
	public UserDTO userByMail(String mail) {

		return userReadService.userByMail(mail);
	}

	/**
	 * 用户注册
	 *
	 * @param dto
	 * @return
	 */

	public Long userRegisterWithTx(UserDTO dto, String shortCode) {
		Long userId = userWriteService.userRegisterWithTx(dto, shortCode);
		// 发送用户注册消息
		sendInfoWriteService.insertUserStateChangeInfoAndSend(
				InfoConstant.USER_STATE_CHANGE_INFO_ID.getStatus(), BusinessConstant.USER_REGISTER, userId);
		return userId;
	}

	/**
	 * 根据邮箱修改密码

	 * @param mobile
	 * @return
	 */
	public int updateUserPasswordByMail(String mobile, Long userId, String mail, String password, Long platformId) {
		int i = userWriteService.updateUserPassword(mail, password);
		// 发送用户修改密码消息
//		sendInfoWriteService.insertUpdateUserPassWordInfoAndSend(
//				InfoConstant.UPDATE_USER_PASSWORD_INFO_ID.getStatus(), mail, userId,mobile);

		/***********发送消息*********/
		Map<String, String> params = new HashMap<>();
		params.put("账号邮箱", mail);
		Long infoTemplateId;
		if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
			infoTemplateId = InfoConstant.MYY_LOGIN_PASSWORD_CHANGE.getStatus();
		} else {
			infoTemplateId = InfoConstant.FGJ_LOGIN_PASSWORD_CHANGE.getStatus();
		}
		sendInfoWriteService.insertAndSendMessage(infoTemplateId, params, userId, mobile);
		/***********发送消息*********/
		return i;
	}

	/**
	 * 添加用户邮箱信息返回用户id
	 *
	 * @param mail
	 * @return
	 */
	public Long saveUserMail(String mail, Long companyId) {

		return userWriteService.saveUserMail(mail, companyId);
	}

	/**
	 * 给账户绑定手机号
	 *
	 * @param userId
	 */
	public int bindingMobileByUserId(String mobile, Long userId,String weiXinOpenId) {
		return userWriteService.saveUserMobile(mobile, userId,weiXinOpenId);
	}

	/**
	 * 根据用户手机号查询用户信息
	 *
	 * @param mobile
	 * @return
	 */
	public List<UserDTO> findByMobileAndRegister(String mobile,Long platformId) {
		return userReadService.findByMobileAndRegister(mobile,platformId);
	}

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 *
	 * @param mobile
	 * @return
	 */
	public UserDTO findLatestLoginByMobile(String mobile,Long platformId) {
		return userReadService.findLatestLoginByMobile(mobile,platformId);
	}

	/**
	 * 根据用户邮箱设置支付密码
	 *
	 * @param mail
	 * @param password
	 * @return
	 */
	public int setPaymentPassword(Long userId,String mail, String password, Long platformId) {
		int i = 0;
		PaymentCodeSaltDTO paymentCodeSaltDTO = new PaymentCodeSaltDTO();
		String uuid = UUIDBuild.getUUID();
		paymentCodeSaltDTO.setPaymentCodeUuid(uuid);
		String salt = SaltUtils.getRandomSalt();
		paymentCodeSaltDTO.setSaltValue(salt);

		Long paymentCodeSaltId = paymentCodeSaltWriteService.insertPaymentCodeSaltWithTx(paymentCodeSaltDTO);
		// 加密盐保存成功进行设置支付密码操作
		if (EmptyUtil.isNotEmpty(paymentCodeSaltId)) {
			i = userWriteService.setPaymentPassword(mail, MD5Support.MD5(password, salt), uuid);
		}
		// 发送用户修改密码消息
//		sendInfoWriteService.insertUpdateUserPassWordInfoAndSend(
//				InfoConstant.UPDATE_USER_PAY_PASSWORD_INFO_ID.getStatus(), mail, userId, null);
		/***********发送消息*********/
		Map<String, String> params = new HashMap<>();
		params.put("账号邮箱", mail);
		Long infoTemplateId;
		if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
			infoTemplateId = InfoConstant.MYY_PAY_PASSWORD_CHANGE.getStatus();
		} else {
			infoTemplateId = InfoConstant.FGJ_PAY_PASSWORD_CHANGE.getStatus();
		}
		sendInfoWriteService.insertAndSendMessage(infoTemplateId, params, userId, null);
		/***********发送消息*********/

		return i;
	}

	/**
	 * 查找导入记录
	 *
	 * @param headImportRecordsDTO
	 * @return
	 */
	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO headImportRecordsDTO) {
		return headImportRecordsReadService.findHeadImportRecordsAll(headImportRecordsDTO);
	}

	/**
	 * 表头信息储存到临时导入记录表
	 *
	 * @param importRecordsDTO1
	 * @return
	 */
	public Long insertImportRecords(ImportRecordsDTO importRecordsDTO1) {
		return importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO1);
	}

	public Long assureImportUser(Long companyId, Long platformId, Long importId, String userOrderInfo) {
		// 获取分布式锁
		LogicLock lock = lockConfig.getLockManager().getLogicLock("assureImportUser");
		if (lock.tryLock()) {
			long count = 0;
			try {
				// 根据导入批次id查询导入用户数据同步到正式表中
				userWriteService.syncUserAll(importId);
				// 根据导入批次id查询用户信息
				List<UserDTO> userDTOList = userReadService.findByImportId(importId);

				ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
				importRecordsDTO.setId(importId);
				ImportRecordsDTO findImportRecord = importRecordsReadService.findImportRecordsById(importRecordsDTO);

				if (EmptyUtil.isNotEmpty(findImportRecord)) {
					HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

					headImportRecordsDTO.setId(findImportRecord.getId());
					headImportRecordsDTO.setTemplateType(findImportRecord.getTemplateType());
					headImportRecordsDTO.setCompanyName(findImportRecord.getCompanyName());
					headImportRecordsDTO.setFileSequenceNumber(findImportRecord.getFileSequenceNumber());
					headImportRecordsDTO.setGeneratedTime(findImportRecord.getGeneratedTime());
					headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
				}

				/*String userIdStr = userOrderInfo.substring(1);

				String[] userIdArr = userIdStr.split(",");
				count = 0;

				List<UserTempDTO> userTempList = new ArrayList<>();

				for (int i = 0; i < userIdArr.length; i++) {
					// 根据id查询临时user表的数据
					UserTempDTO userTempDTO = new UserTempDTO();
					userTempDTO.setId(Long.parseLong(userIdArr[i]));
					UserTempDTO dto = userTempReadService.findUserTempById(userTempDTO);
					userTempList.add(dto);
					count++;
				}
				// 1.跟新和用户相关的表(此方法勿用)
				List<UserDTO> userDTOList = userWriteService.userIdassureImportUserAboutUserWithTx(userTempList,userOrderInfo);

				// 2.将临时记录表的数据同步到正式表
				ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
				importRecordsDTO.setId(importId);
				ImportRecordsDTO findImportRecord = importRecordsReadService.findImportRecordsById(importRecordsDTO);

				if (EmptyUtil.isNotEmpty(findImportRecord)) {
					HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

					headImportRecordsDTO.setId(findImportRecord.getId());
					headImportRecordsDTO.setTemplateType(findImportRecord.getTemplateType());
					headImportRecordsDTO.setCompanyName(findImportRecord.getCompanyName());
					headImportRecordsDTO.setFileSequenceNumber(findImportRecord.getFileSequenceNumber());
					headImportRecordsDTO.setGeneratedTime(findImportRecord.getGeneratedTime());

					headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
				}*/
				UserTempDTO tempDTO = new UserTempDTO();
				// ***************************新员工开户
				for (UserDTO userDTO : userDTOList) {
					userDTO.setPlatformId(platformId);
					tempDTO.setPlatformId(platformId);
					tempDTO.setCompanyId(userDTO.getCompanyId());
					tempDTO.setType(Integer.valueOf(2));
					this.openAccount(userDTO,false);
					count++;
				}
				/*if(EmptyUtil.isNotEmpty(userDTOList)){
					//清除缓存
					userTempWriteService.deleteUserTempByParamsWithTx(tempDTO);
				}*/
				return count;
			} catch (Exception e1) {
				throw new BusinessException("确认导入员工数据异常：" + e1.getMessage());
			}finally {
				// 不管是否发生异常都释放zk锁
				try {
					lock.unlock();
				} catch (Exception e) {
					throw new BusinessException("释放掉一个分布式锁失败，请联系管理员");
				}
			}
	 }else {
		 throw new BusinessException("其他管理员正在导入，请稍后再试");
	 }


	}

	public Long assureReImportUser(Long companyId, Long platformId, Long importId, String userOrderInfo) {
		// 获取分布式锁
		LogicLock lock = lockConfig.getLockManager().getLogicLock("assureImportUser");
		if (lock.tryLock()) {

			long count = 0;
			try {
				// 根据导入批次id查询导入用户数据同步到正式表中
				userWriteService.syncUserAll(importId);
				// 根据导入批次id查询用户信息
				List<UserDTO> userDTOList = userReadService.findByImportId(importId);
				ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
				importRecordsDTO.setId(importId);
				ImportRecordsDTO findImportRecord = importRecordsReadService.findImportRecordsById(importRecordsDTO);

				if (EmptyUtil.isNotEmpty(findImportRecord)) {
					HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

					headImportRecordsDTO.setId(findImportRecord.getId());
					headImportRecordsDTO.setTemplateType(findImportRecord.getTemplateType());
					headImportRecordsDTO.setCompanyName(findImportRecord.getCompanyName());
					headImportRecordsDTO.setFileSequenceNumber(findImportRecord.getFileSequenceNumber());
					headImportRecordsDTO.setGeneratedTime(findImportRecord.getGeneratedTime());

					headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
				}

				UserTempDTO tempDTO = new UserTempDTO();

				//将员工编号、邮箱都重复的员工的在职状态变更为“在职”、清空这部分员工的离职时间、资产回收日期 刪除离职记录
				for (UserDTO dto : userDTOList) {
					UserExtendDTO userExtendDTO = new UserExtendDTO();
					userExtendDTO.setMail(dto.getMail());
					userExtendDTO.setPlatformId(platformId);
					List<UserExtendDTO> userExtendAll = userExtendReadService.userAll(userExtendDTO);

					if (EmptyUtil.isNotEmpty(userExtendAll)) {
						for (UserExtendDTO dto2 : userExtendAll) {
							Long id = dto2.getId();
							userExtendWriteService.updateReImportUserStatus(id);
							UserQuitDTO userQuitDTO = new UserQuitDTO();
							userQuitDTO.setUserId(dto.getId());
							userQuitDTO.setCompanyId(companyId);
							userQuitDTO.setPlatformId (platformId);
							List<UserQuitDTO> userQuitAll = userQuitReadService.findUserQuitAll(userQuitDTO);
							if (EmptyUtil.isNotEmpty(userQuitAll)) {
								userQuitWriteService.deleteUserQuitWithTx(userQuitDTO);
								UserQuitTempDTO userQuitTempDTO = new UserQuitTempDTO();
								userQuitDTO.setCompanyId(companyId);
								userQuitDTO.setMemberCode(dto.getMail());
								userQuitTempDTO.setPlatformId(platformId);
								userQuitTempWriteService.deleteUserQuitTempByParamsWithTx(userQuitTempDTO);
							}
							if (importId.equals(dto2.getImportRecordsId())) {
								userWriteService.deleteByImportIdWithTx(id,importId);
								userExtendWriteService.deleteByIdWithTx(id);
							}
						}
					}
					// 未重复的员工信息作为新增员工开户
					dto.setPlatformId(platformId);
					tempDTO.setPlatformId(platformId);
					tempDTO.setCompanyId(dto.getCompanyId());
					tempDTO.setType(Integer.valueOf(10));
					this.openAccount(dto,false);
					count++;
				}
				return count;
			} catch (Exception e1) {
				throw new BusinessException("员工名单重发导入数据异常：" + e1.getMessage());
			}finally {
				// 不管是否发生异常都释放zk锁
				try {
					lock.unlock();
				} catch (Exception e) {
					throw new BusinessException("释放掉一个分布式锁失败，请联系管理员");
				}
			}
		}else {
			 throw new BusinessException("其他管理员正在导入，请稍后再试");
		}

	}

	public Long assureImportQuitUser(Long companyId, Long platformId, Long importOrder, String userQuitOrderInfo) {

//		String userIdStr = userQuitOrderInfo.substring(1);

		String[] userIdArr = userQuitOrderInfo.split(",");

		long count = 0;
		// 1.将离职员工临时表的数据同步到正式表
		for (int i = 0; i < userIdArr.length; i++) {
			if (EmptyUtil.isEmpty(userIdArr[i])) {
				continue;
			}

			UserQuitTempDTO userQuitTempDTO = new UserQuitTempDTO();
			userQuitTempDTO.setId(Long.valueOf(userIdArr[i]));

			UserQuitTempDTO userQuitTempDTO2 = userQuitTempReadService.findUserQuitTempById(userQuitTempDTO);

			UserQuitDTO userQuitDTO = new UserQuitDTO();
			userQuitDTO.setId(userQuitTempDTO2.getId());
			userQuitDTO.setUserId(userQuitTempDTO2.getUserId());
			userQuitDTO.setName(userQuitTempDTO2.getName());
			userQuitDTO.setMemberCode(userQuitTempDTO2.getMemberCode());
			userQuitDTO.setCompanyId(userQuitTempDTO2.getCompanyId());
			userQuitDTO.setPlatformId(userQuitTempDTO2.getPlatformId());
			userQuitDTO.setQuitTime(userQuitTempDTO2.getQuitTime());
			userQuitDTO.setInvalidTime(userQuitTempDTO2.getInvalidTime());
			userQuitDTO.setMail(userQuitTempDTO2.getMail());

			// 定时任务中同一个账户只保留一条最新的数据
			UserQuitDTO userQuitDTO_ = new UserQuitDTO();
			userQuitDTO_.setUserId(userQuitTempDTO2.getUserId());
			List<UserQuitDTO> userQuitDTOList = userQuitReadService.findUserQuitAll(userQuitDTO_);

			if (EmptyUtil.isNotEmpty(userQuitDTOList)) {
				// 员工的离职信息已经导入过的,更新离职信息
				userQuitDTO.setId(userQuitDTOList.get(0).getId());
				userQuitWriteService.updateUserQuitWithTx(userQuitDTO);
			} else {
				// 未导入过的,新增一条离职信息
				userQuitWriteService.insertUserQuitWithTx(userQuitDTO);
			}

			// 确认导入离职员工信息时,设置员工的离职日期和资产回收日期
			UserDTO userDTO = new UserDTO();
			userDTO.setQuitTime(userQuitTempDTO2.getQuitTime());
			userDTO.setId(userQuitTempDTO2.getUserId());
			userDTO.setIsAvailable(0);
			userWriteService.updateUserInfoWithTx(userDTO);
			UserExtendDTO userExtendDTO = new UserExtendDTO();
			userExtendDTO.setId(userQuitTempDTO2.getUserId());
			userExtendDTO.setInvalidTime(userQuitTempDTO2.getInvalidTime());
			userExtendWriteService.updateWithTx(userExtendDTO);

			// 发送用户离职状态变更消息
//			sendInfoWriteService.insertUserDimissionInfoAndSend(
//					InfoConstant.USER_DIMISSION_CHANGE_INFO_ID.getStatus(),
//					DateUtils.getDefaultDate(userQuitTempDTO2.getQuitTime()),
//					DateUtils.getDefaultDate(userQuitTempDTO2.getInvalidTime()),
//					userQuitTempDTO2.getUserId());
			/***********发送消息*********/
			Map<String, String> params = new HashMap<>();
			params.put("账号邮箱", userQuitTempDTO2.getMail());
			params.put("资产回收日期", DateUtils.getDefaultDate(userQuitTempDTO2.getInvalidTime()));
			params.put("离职日期", DateUtils.getDefaultDate(userQuitTempDTO2.getQuitTime()));

			UserAccountDTO accountDTO = new UserAccountDTO();
			accountDTO.setUserId(userQuitTempDTO2.getUserId());
			accountDTO.setPlatformId(platformId);
			//福币账户
			accountDTO.setType(0);
			List<UserAccountDTO> accountList1 = userAccountReadService.findUserAccountAll(accountDTO);
			if (EmptyUtil.isNotEmpty(accountList1)) {
				params.put("积分余额", accountList1.get(0).getBalance() + "");
			}

			//点赞积分账户
			accountDTO.setType(1);
			List<UserAccountDTO> accountList2 = userAccountReadService.findUserAccountAll(accountDTO);
			if (EmptyUtil.isNotEmpty(accountList2)) {
				params.put("点赞福豆余额", accountList2.get(0).getBalance() + "");
			}

			Long infoTemplateId;
			if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
				infoTemplateId = InfoConstant.MYY_ACCOUNT_DISABLE.getStatus();
			} else {
				infoTemplateId = InfoConstant.FGJ_ACCOUNT_DISABLE.getStatus();
			}
			sendInfoWriteService.insertAndSendMessage(infoTemplateId, params, userQuitTempDTO2.getUserId(), null);
			/***********发送消息*********/

			//清除缓存
			userQuitTempWriteService.deleteUserQuitTempWithTx(userQuitTempDTO);
			count++;
		}

		// 2.将临时记录表的数据同步到正式表
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setId(importOrder);
		ImportRecordsDTO findImportRecord = importRecordsReadService.findImportRecordsById(importRecordsDTO);

		if (EmptyUtil.isNotEmpty(findImportRecord)) {
			HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

			headImportRecordsDTO.setId(findImportRecord.getId());
			headImportRecordsDTO.setTemplateType(findImportRecord.getTemplateType());
			headImportRecordsDTO.setCompanyName(findImportRecord.getCompanyName());
			headImportRecordsDTO.setFileSequenceNumber(findImportRecord.getFileSequenceNumber());
			headImportRecordsDTO.setGeneratedTime(findImportRecord.getGeneratedTime());

			headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
		}

		return count;
	}

	public Long insertUserWithTx(UserDTO userDTO) {
		return userWriteService.insertUserWithTx(userDTO);
	}

	public void userAccount(UserDTO userDTO) {
		this.openAccount(userDTO,false);
	}

	public List<UserDTO> findUser(UserDTO userDTO) {
		return userReadService.findUser(userDTO);
	}

	public Map<String, Object> insertuserFacadesAndUserViewList(ImportRecordsDTO importRecordsDTO,
			List<UserTempDTO> userTempDTOList) {
		Map<String, Object> data = new HashMap<>();

		// 将导入信息，插入到记录表
		Long importRecordId = importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO);
		data.put("importUserInfo", importRecordId);

		// 根据创建用户id清楚缓存数据(同一批用户数据创建用户id一定相同)
		userTempWriteService.delByCreateUserId(userTempDTOList.get(0).getCreateUserid());

		for (UserTempDTO userTempDTO : userTempDTOList) {
			userTempDTO.setImportRecordsId(importRecordId);
		}

		// 批量保存预导入用户数据
		userTempWriteService.insertUserViewListWithTx(userTempDTOList);

		// 根据用户id查询预导入数据id
		List<Long> list = userTempReadService.findIdsByCreateUserid(userTempDTOList.get(0).getCreateUserid(),userTempDTOList.get(0).getPlatformId());
		// 拼接显示ids
		StringBuilder userTempListIdStr = new StringBuilder();
		for (Long userTempId : list) {
			userTempListIdStr.append(userTempId);
			userTempListIdStr.append(",");
		}
		data.put("userInfo", userTempListIdStr.substring(0, userTempListIdStr.length()-1));
		return data;
	}

	public Long assureImportDeptUser(Long companyId, Long platformId, Long importOrder, String userOrderInfo,
			int type) {

//		String userIdStr = userOrderInfo.substring(1);

		String[] userIdArr = userOrderInfo.split(",");

		List<UserTempDTO> userTempList = new ArrayList<>();

		for (int i = 0; i < userIdArr.length; i++) {
			if (EmptyUtil.isNotEmpty(userIdArr[i])) {
				// 根据id查询临时user表的数据
				UserTempDTO userTempDTO = new UserTempDTO();
				userTempDTO.setId(Long.parseLong(userIdArr[i]));
				UserTempDTO dto = userTempReadService.findUserTempById(userTempDTO);

				userTempList.add(dto);
			}
		}
		// 1.跟新和用户相关的表(跟新userWefalre)

		Long count = userWriteService.updateAboutUser(userTempList, type);

		if (userTempList.size() == count.intValue()) {
			// 2.将临时记录表的数据同步到正式表
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setId(importOrder);
			ImportRecordsDTO findImportRecord = importRecordsReadService.findImportRecordsById(importRecordsDTO);

			if (EmptyUtil.isNotEmpty(findImportRecord)) {
				HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

				headImportRecordsDTO.setId(findImportRecord.getId());
				headImportRecordsDTO.setTemplateType(findImportRecord.getTemplateType());
				headImportRecordsDTO.setCompanyName(findImportRecord.getCompanyName());
				headImportRecordsDTO.setFileSequenceNumber(findImportRecord.getFileSequenceNumber());
				headImportRecordsDTO.setGeneratedTime(findImportRecord.getGeneratedTime());

				headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
			}
			return (long) userTempList.size();
		}

		//清除缓存
		if(EmptyUtil.isNotEmpty(userTempList)){
			UserTempDTO tempDTO = new UserTempDTO();
			tempDTO.setType(userTempList.get(0).getType());
			tempDTO.setPlatformId(userTempList.get(0).getPlatformId());
			tempDTO.setCompanyId(userTempList.get(0).getCompanyId());
			userTempWriteService.deleteUserTempByParamsWithTx(tempDTO);
		}
		return 0L;
	}

	public static void main(String[] args) {
		//开通账户信息main
		String uuid = UUID.uuid();
		// 2.生成密文
		String randomSalt = SaltUtils.getRandomSalt();
		// 新开账户默认赋值一定要用这种语法new BigDecimal("0.00"),否则会出现加密校验不通过
		BigDecimal defaulValue = new BigDecimal("500.00");

		String ciphertext = MD5Util.MD5Salt(defaulValue.toString(), randomSalt);

		// 3.盐表插入数据
		SaltDTO saltDTO = new SaltDTO();
		saltDTO.setUuid(uuid);
		saltDTO.setSaltValue(randomSalt);


		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setUuid(uuid);
		userAccountDTO.setCiphertext(ciphertext);
		userAccountDTO.setBalance(defaulValue);
		userAccountDTO.setName("e-1c-68-13918536672");
		userAccountDTO.setPlatformId(7L);
		userAccountDTO.setType(4);
		userAccountDTO.setUserId(24612L);
		userAccountDTO.setDisabled(0);
		System.out.println(ciphertext);
		System.out.println(JSON.toJSONString(saltDTO));
		System.out.println(JSON.toJSONString(userAccountDTO));
	}

	private void openAccount(UserDTO userDTO,Boolean refundAccount) {
		openAccount(userDTO,refundAccount,false);
	}

	private void openAccount(UserDTO userDTO,Boolean refundAccount,Boolean jdDianAccount) {
		for (int i = 0; i < ((refundAccount!=null&&refundAccount)?5:4); i++) {
			createUserAccount(userDTO,i);
		}
		if (Objects.nonNull(jdDianAccount) && jdDianAccount){
			createUserAccount(userDTO,5);
		}
	}

	public void createUserAccount(UserDTO userDTO,int type){
		// 1.生成uuid
		String uuid = UUID.uuid();
		// 2.生成密文
		String randomSalt = SaltUtils.getRandomSalt();
		// 新开账户默认赋值一定要用这种语法new BigDecimal("0.00"),否则会出现加密校验不通过
		BigDecimal defaulValue = new BigDecimal("0.00");
		//新开户账号初始化默认积分
		if (type==0){
			if (Objects.nonNull(userDTO.getDefaultFuBiValue())
					&&userDTO.getDefaultFuBiValue().compareTo(BigDecimal.ZERO)>0){
				defaulValue=userDTO.getDefaultFuBiValue();
			}
		}
		String ciphertext = MD5Util.MD5Salt(defaulValue.toString(), randomSalt);

		// 3.盐表插入数据
		SaltDTO saltDTO = new SaltDTO();
		saltDTO.setUuid(uuid);
		saltDTO.setSaltValue(randomSalt);
		saltWriteService.insertSaltWithTx(saltDTO);

		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setUuid(uuid);
		userAccountDTO.setCiphertext(ciphertext);
		userAccountDTO.setBalance(defaulValue);
		userAccountDTO.setId(userDTO.getId());
		userAccountDTO.setName(userDTO.getMail());
		userAccountDTO.setPlatformId(userDTO.getPlatformId());
		userAccountDTO.setType(type);
		userAccountDTO.setUserId(userDTO.getId());
		userAccountDTO.setDisabled(0);
		userAccountWriteService.insertUserAccountWithTx(userAccountDTO);
	}

	private void openAccount(UserDTO userDTO,List<CompanyConfigDTO> configs) {
		Boolean refundAccount = false;
		Boolean jiDianAccount = false;
		if(configs!=null && configs.size()>0) {
			for(CompanyConfigDTO config : configs) {
				if(config.getKey().equalsIgnoreCase("account.refund.type")) {
					if(config.getValue().equalsIgnoreCase("4")) {
						refundAccount = true;
					}
				}
				if(config.getKey().equalsIgnoreCase("account.jidian.type")) {
					if(config.getValue().equalsIgnoreCase("5")) {
						jiDianAccount = true;
					}
				}
			}
		}
		openAccount(userDTO, refundAccount,jiDianAccount);
	}
	// /**
	// * 用户验证支付密码
	// *
	// * @param dto
	// * @return
	// */
	// public boolean verificationPaymentPassword(Long userId, String
	// paymentPassword) {
	// boolean isSucceed = false;
	// // 根据用户id查询用户信息
	// UserDTO userDTO = userReadService.findUserByID(userId);
	// if (EmptyUtil.isEmpty(userDTO.getPaymentCodeUuid())) {
	// throw new BusinessException("请设置支付密码");
	// }
	// // 根据uuId查询用户支付密钥
	// String salt =
	// paymentCodeSaltReadService.findSaltByUUID(userDTO.getPaymentCodeUuid());
	// String paymentCode = MD5Support.MD5(paymentPassword, salt);
	// if (!paymentCode.equals(userDTO.getPaymentCode())) {
	// throw new
	// BusinessException(BusinessExceptionConstant.PAYMENTPASSWORD_NO_MATCH,
	// "支付密码错误");
	// }
	// isSucceed = true;
	// return isSucceed;
	// }

	public List<UserTempDTO> findUserTempAll(UserTempDTO dto) {
		return userTempReadService.findUserTempAll(dto);
	}

	/**
	 * 根据邮箱查询用户信息
	 */
	public UserDTO findByMail(String mail) {
		// TODO Auto-generated method stub
		return userReadService.findByMail(mail);
	}

	/**
	 * 根据用户id修改用户密码
	 *
	 * @param userId
	 * @param password
	 * @return
	 */
	public int updateUserPassword(Long userId, String password, String originalPassword) {
		int i = userWriteService.updateUserPasswordWithTx(userId, password, originalPassword);
		UserDTO userDTO = userReadService.findUserByID(userId);
		// 发送用户修改密码消息
		sendInfoWriteService.insertUpdateUserPassWordInfoAndSend(
				InfoConstant.UPDATE_USER_PASSWORD_INFO_ID.getStatus(), userDTO.getMail(), userId, null);
		return i;
	}

	/**
	 * 根据用户id修改用户密码
	 *
	 * @param userId
	 * @param password
	 * @return
	 */
	public int resetManageUserPasswordWithTx(Long userId, String password) {
		int i = userWriteService.resetManageUserPasswordWithTx(userId, password);
		UserDTO userDTO = userReadService.findUserByID(userId);
		// 发送用户修改密码消息
		sendInfoWriteService.insertUpdateUserPassWordInfoAndSend(
				InfoConstant.UPDATE_USER_PASSWORD_INFO_ID.getStatus(), userDTO.getMail(), userId, null);
		return i;
	}

	/**
	 * 查询用户的第三方保险登陆信息
	 *
	 * @param userId
	 * @return
	 */
	public InsuranceLoginDTO queryInsuranceLoginByUserId(Long userId) {
		return insuranceLoginReadService.queryInsuranceLoginByUserId(userId);
	}

	/**
	 * 新增用户的第三方保险登陆信息
	 *
	 * @param il
	 * @return
	 */
	public Long insertInsuranceInfo(InsuranceLoginDTO il) {
		return insuranceLoginWriteService.insertInsuranceLoginWithTx(il);
	}

	/**
	 * 保险登陆更新用户信息
	 *

	 */
	public int insuranceLogin(InsuranceLoginDTO il) {
		return insuranceLoginWriteService.insuranceLogin(il);

	}

	public int updateQuitTime(UserDTO dto) {
		return userWriteService.updateQuitTime(dto);
	}
	/**
	 * 根据用户Id集合解绑用户
	 * @param req
	 * @return
	 */
	public Integer unbindByUserIds(List<Long> userIdList,Long platformId) {
		if(EmptyUtil.isEmpty(userIdList))
			throw new BusinessException("请选择用户");
		UserDTO userDTO = userReadService.findUserByID(userIdList.get(0));
		String mobile = userDTO.getMobile();
		if(EmptyUtil.isEmpty(mobile))
			throw new BusinessException("解绑用户手机号码为空，用户id="+userIdList.get(0));
		List<UserDTO> list = userReadService.findListByManage(mobile, platformId);
		userWriteService.unbindByUserIds(userIdList,platformId);
		for (UserDTO user : list) {
			if (userIdList.contains(user.getId())) {
				// 发送用户解绑手机号消息
//				sendInfoWriteService.insertUnbindUserInfoAndSend(
//						InfoConstant.UNBIND_USER_INFO_ID.getStatus(),
//						mobile,DateUtils.getDefaultDateNow(),user.getMail(), user.getId());
				/***********发送消息*********/
				Map<String, String> params = new HashMap<>();
				params.put("旧手机号", mobile);
				params.put("解绑时间", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
				params.put("解绑账号", user.getMail());
				Long infoTemplateId;
				if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
					infoTemplateId = InfoConstant.MYY_UNBIND_MOBILE.getStatus();
				} else {
					infoTemplateId = InfoConstant.FGJ_UNBIND_MOBILE.getStatus();
				}
				sendInfoWriteService.insertAndSendMessage(infoTemplateId, params, user.getId(), mobile);
				/***********发送消息*********/
			}
		}
		return userIdList.size();
	}
	/**
	 * 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
	 * @param userId
	 * @param mobile
	 * @return
	 */
	public UserDTO findByUserIdMobile(Long userId, String mobile,Long platformId) {
		// TODO Auto-generated method stub
		return userReadService.findByUserIdMobile(userId, mobile,platformId);
	}
	/**
	 * 根据用户之前手机号统一换绑手机
	 * @param mobile
	 * @param beforeMobile
	 * @param platformId
	 * @return
	 */
	public int bindingMobileByBeforeMobile(String mobile, String beforeMobile, Long platformId) {
		// TODO Auto-generated method stub
		return userWriteService.bindingMobileByBeforeMobileWithTx(mobile, beforeMobile,platformId);
	}
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	public UserDTO findByWeiXinOpenId(String openId, Long platformId) {
		// TODO Auto-generated method stub
		return userReadService.findByWeiXinOpenId(openId, platformId);
	}

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	public UserDTO findLatestLoginByWeiXinOpenId(String openId, Long platformId) {
		return userReadService.findLatestLoginByWeiXinOpenId(openId, platformId);
	}

	/**
	 * 根据手机号平台id查询用户信息
	 */
	public List<UserDTO> findListByManage(String mobile, Long platformId) {
		// TODO Auto-generated method stub
		return userReadService.findListByManage(mobile, platformId);
	}
	/**
	 * 关联用户消息保存并发送
	 * @param infoTemplateId
	 * @param mail 邮箱地址
	 * @param companyName 公司名称
	 * @param userId
	 * @return
	 */
	public void insertRelevanceUserInfoAndSend(String mobile,Long platformId, Long userId){
		UserDTO userDTO = userReadService.findUserByID(userId);
		if (EmptyUtil.isNotEmpty(userDTO)) {
			//绑定，换绑手机只发一条验证码
			CompanyDTO companyDTO = companyReadService.findCompanyById(userDTO.getCompanyId());
//			sendInfoWriteService.insertRelevanceUserInfoAndSend(
//					InfoConstant.RELEVANCE_USER_INFO_ID.getStatus(),
//					mobile,DateUtils.getDefaultDateNow(),userDTO.getMail(),
//					companyDTO.getCompanyName(), userDTO.getId());
			/***********发送消息*********/
			Map<String, String> params = new HashMap<>();
			params.put("新手机号", mobile);
			params.put("账号邮箱", userDTO.getMail());
			params.put("绑定时间", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
			params.put("公司名称", companyDTO.getCompanyName());
			Long infoTemplateId;
			if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
				infoTemplateId = InfoConstant.MYY_BIND_MOBILE.getStatus();
			} else {
				infoTemplateId = InfoConstant.FGJ_BIND_MOBILE.getStatus();
			}
			sendInfoWriteService.insertAndSendMessage(infoTemplateId, params, userDTO.getId(), mobile);
			/***********发送消息*********/
		}



		/*for (UserDTO userDTO : list) {
			// 发送用户绑定手机号消息
	    	CompanyDTO companyDTO = companyReadService.findCompanyById(userDTO.getCompanyId());
	    	sendInfoWriteService.insertRelevanceUserInfoAndSend(
	    			InfoConstant.RELEVANCE_USER_INFO_ID.getStatus(),
	    			mobile,DateUtils.getDefaultDateNow(),userDTO.getMail(),
	    			companyDTO.getCompanyName(), userDTO.getId());
		}*/
	}

	public UserDTO findAdminUserByManage(String mobile, Long platformId) {
		return userReadService.findAdminUserByManage(mobile, platformId);
	}

	public List<CouponStoreDTO> findCouponStore(Long storeId) {
        CouponStoreDTO dto = new CouponStoreDTO();
        dto.setStoreId(storeId);
        return couponStoreReadService.findCouponStoreAll(dto);
    }

    public List<CouponGroupStoreDTO> findCouponGroupStoreAll(CouponGroupStoreDTO dto) {
        return couponGroupStoreReadService.findCouponGroupStoreAll(dto);
    }

    /**
	 * 根据优惠券ID列表查询注册领取的优惠券批次列表
	 * @param couponIdList
	 * @param platformId
	 * @return
	 */
    public List<CouponBatchDTO> findRegisterCouponBatchByCouponId(List<Long> couponIdList, Long platformId) {
    	return couponBatchReadService.findRegisterCouponBatchByCouponId(com.egeo.utils.StringUtils.longsToStrings(couponIdList), platformId);
    }

    /**
	 * 根据优惠券ID列表查询注册领取的优惠券组批次列表
	 * @param couponIdList
	 * @param platformId
	 * @return
	 */
    public List<CouponBatchDTO> findRegisterCouponBatchByCouponGroup(List<Long> couponIdList, Long platformId) {
    	return couponBatchReadService.findRegisterCouponBatchByCouponGroup(com.egeo.utils.StringUtils.longsToStrings(couponIdList), platformId);
    }

    public List<CouponUnitDTO> findCouponUnitAll(CouponUnitDTO dto){
		return couponUnitReadService.findCouponUnitAll(dto);
	}

    public CouponDTO findCouponById(CouponDTO dto) {
		return couponReadService.findCouponById(dto);
	}

    public Long insertCouponUnitWithTx(CouponUnitDTO dto){
		Long couponUnitId = couponUnitWriteService.insertCouponUnitWithTx(dto);

		String startTime = DateUtils.getDefaultDate(new Date());
		String stopTime = "无限";
		CompanyDTO companyDTO = companyReadService.queryCompanyByUserId(dto.getUserId());
		if(EmptyUtil.isNotEmpty(dto.getEffectStartTime()))
			startTime = DateUtils.getDefaultDate(dto.getEffectStartTime());
		if(EmptyUtil.isNotEmpty(dto.getEffectEndTime()))
			stopTime = DateUtils.getDefaultDate(dto.getEffectEndTime());
		sendInfoWriteService.insertSendCouponInfoAndSend(InfoConstant.SEND_COUPON_INFO_ID.getStatus(), companyDTO.getCompanyName(), dto.getTitle(), 1, startTime, stopTime, dto.getUserId());
		return couponUnitId;
	}
    /**
     * 根据平台公司及手机判断是否存在有效用户
     * @param mobile
     * @param companyId
     * @param platformId
     * @return
     */
	public Boolean findIsExistUser(String mobile, Long companyId, Long platformId) {
		return userReadService.findIsExistUser(mobile, companyId, platformId);
	}

	public Long insertAllUserInfosWithTx(UserDTO userDTO, UserExtendDTO userExtendDTO, UserWelfareDTO userWelfareDTO) {
		return userWriteService.insertAllUserInfosWithTx(userDTO, userExtendDTO, userWelfareDTO);
	}

	public Long updateAllUserInfosWithTx(UserDTO userDTO, UserExtendDTO userExtendDTO, UserWelfareDTO userWelfareDTO) {
		return userWriteService.updateAllUserInfosWithTx(userDTO, userExtendDTO, userWelfareDTO);
	}

    public List<UserQuitTempDTO> findUserQuitTempAll(UserQuitTempDTO userQuitTempDTO) {
		return userQuitTempReadService.findUserQuitTempAll(userQuitTempDTO);
	}

    public void deleteUserTempByParamsWithTx(UserTempDTO tempDTO) {
		userTempWriteService.deleteUserTempByParamsWithTx(tempDTO);
	}

	public List<UserDTO> findUserByMobile(String mobile, Long platformId) {
		return userReadService.findUserByMobile(mobile, platformId);

	}

	public int activateUserByIds(List<Long> userIds) {
		return userExtendWriteService.activateUserByIds(userIds);
	}

	public UserVO registDefaultCompanyUserByMobile(String mobile,String passwd,String wxOpenId, Long platformId,Long realCompanyId) {
		Long enterpriseId = Constants.USER_DEFAULT_ENTERPRISE_ID;
		Long companyId = Constants.USER_DEFAULT_COMPANY_ID;
		BigDecimal defaultFuBiValue=null;
		if (Objects.nonNull(realCompanyId)){
			companyId=realCompanyId;
			defaultFuBiValue=account0DefaultFuBiValue(realCompanyId);
		}
		String salt = SaltUtils.getRandomSalt();
		String md5 = MD5Support.MD5(passwd, salt);
		UserDTO userDto = new UserDTO();
		userDto.setEnterpriseId(enterpriseId);
		userDto.setCompanyId(companyId);
		userDto.setPlatformId(platformId);
		userDto.setType(4);
		userDto.setPassword(md5);
		userDto.setSalt(salt);
		userDto.setName(mobile);
		userDto.setMobile(mobile);
		userDto.setLoginName("e"+"-"+enterpriseId.longValue()+"c"+"-"+companyId.intValue()+"-"+mobile);
		userDto.setMail("e"+"-"+enterpriseId.longValue()+"c"+"-"+companyId.intValue()+"-"+mobile+"@dachuguanjia.cn");
		if (Objects.nonNull(defaultFuBiValue)){
			userDto.setDefaultFuBiValue(defaultFuBiValue);
		}
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		userExtendDTO.setStatus(1);
		userExtendDTO.setAccountStatus(0);
		userExtendDTO.setIsAdministrator(0);
		userExtendDTO.setWeixin(wxOpenId);
		userExtendDTO.setName(mobile);
		userExtendDTO.setIsDeleted(0);
		userExtendDTO.setRemark("短信注册用户");
		userExtendDTO.setMemberCode("companyUser-"+companyId.longValue()+"-"+mobile);
		List<CompanyConfigDTO> companyConfigs= new ArrayList<>();
		if (Objects.nonNull(companyId)){
			if (Objects.equals(DLFUserUtils.companyId,companyId)){
				userExtendDTO.setChannelSource(UserChannelSourceEnum.DLF.getChannelSource());
			}
			companyConfigs=companyConfigClient.queryCompanyconfigs(companyId);
		}
        userExtendDTO.setRegtime(new Date());
		Long createdUserId = createUser(userDto, userExtendDTO,companyConfigs);
		UserRoleDTO userRolePo = new UserRoleDTO();
		userRolePo.setRoleId(BusinessConstant.PLATFORM7_USER_ROLEID);
		userRolePo.setUserId(createdUserId);
		userRolePo.setCreateTime(new Date());
		userRoleWriteService.saveWithTx(userRolePo);
//		UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
//		userPlatformDTO.setIsAvailable(1);
//		userPlatformDTO.setUserId(createdUserId);
//		userPlatformDTO.setPlatformId(7l);
//		userPlatformDTO.setCreateTime(new Date());
//		userPlatformWriteService.saveWithTx(userPlatformDTO);
		UserDTO returnUser = findByWeiXinOpenId(wxOpenId, 7l);
		return UserConverter.toVO(returnUser);
	}
	public Map<String, Object> findUserNews(Long userId) {
		Map<String, Object> result = new HashMap<>();
		int userNewsCount = 0;
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
		couponUnitDTO.setIsRead(0);
		couponUnitDTO.setUserId(userId);
		List<CouponUnitDTO> couponUnitList = couponUnitReadService.findCouponUnitAll(couponUnitDTO);
		result.put("newCouponUnitCount", couponUnitList == null ? 0 : couponUnitList.size());
		userNewsCount += couponUnitList == null ? 0 : couponUnitList.size();
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setUserId(userId);
		List<UserAccountDTO> userAccountList = userAccountReadService.findUserAccountAll(userAccountDTO);
		result.put("newFBFlowCount", 0);
		result.put("newPraiseFlowCount", 0);
		result.put("newCashFlowCount", 0);
		if (EmptyUtil.isNotEmpty(userAccountList)) {
			for (UserAccountDTO acc : userAccountList) {
				//0:福币账户 1:点赞积分账户 3:现金支出账户'
				AccountFlowDTO accountFlowDTO = new AccountFlowDTO();
				accountFlowDTO.setIsRead(0);
				accountFlowDTO.setInflowAccountid(acc.getId());
				List<AccountFlowDTO> inAccFlowList = accountFlowReadService.findAccountFlowAll(accountFlowDTO);

				accountFlowDTO.setInflowAccountid(null);
				accountFlowDTO.setOutflowAccountid(acc.getId());
				List<AccountFlowDTO> outAccFlowList = accountFlowReadService.findAccountFlowAll(accountFlowDTO);
				int total = 0;
				if (inAccFlowList != null) {
					total += inAccFlowList.size();
				}
				if (outAccFlowList != null) {
					total += outAccFlowList.size();
				}
				if (acc.getType() == 0) {
					result.put("newFBFlowCount", total);
				} else if (acc.getType() == 1) {
					result.put("newPraiseFlowCount", total);
				} else if (acc.getType() == 3) {
					result.put("newCashFlowCount", total);
				}
				userNewsCount += total;
			}
		}
		result.put("userNewsCount", userNewsCount);
		return result;
	}

	public Long createUser(UserDTO userDTO, UserExtendDTO extendDTO) {
		Long userId=userWriteService.createUserWithTx(userDTO,extendDTO);
		userDTO.setId(userId);
		if(userDTO.getType()==null || userDTO.getType().intValue()==4) {
			openAccount(userDTO,false);
		}
		return userId;
	}

	public Long createUser(UserDTO userDTO, UserExtendDTO extendDTO,List<CompanyConfigDTO> configs) {
		Long userId=userWriteService.createUserWithTx(userDTO,extendDTO);
		userDTO.setId(userId);
		if(userDTO.getType()==null || userDTO.getType().intValue()==4) {
			openAccount(userDTO,configs);
		}
		return userId;
	}
	public String getWorkWeChatAccessToken(JedisUtil cache, String companyId) {
		CompanyDTO dto = new CompanyDTO();
		dto.setOpenWorkWechat(Integer.valueOf(1));
		dto.setId(Long.valueOf(companyId));
		CompanyDTO companyDTO=companyReadService.findCompanyById(Long.valueOf(companyId));
		String access_token = "";
		if(EmptyUtil.isNotEmpty(companyDTO)){
				String res = workWeChatUtil.getWeChatInnerAppAccessToken(companyDTO.getCorpid(), companyDTO.getSecret());
				JSONObject jsonObject = JSONObject.parseObject(res);
				Integer errcode = jsonObject.getInteger("errcode");
				Long id = companyDTO.getId();
				if(EmptyUtil.isNotEmpty(id)&&errcode.equals(Integer.valueOf(0))){
					access_token = jsonObject.getString("access_token");
					Long expTime = jsonObject.getLong("expires_in");
					cache.setString(WorkWeChatUtil.PRE_JEDIS_ACCESS_TOKEN_KEY+id,expTime.intValue(),access_token);
				}
		}
		return access_token;
	}

    public String getWorkWeChatSuiteAccessToken(JedisUtil cache) {
		String suiteTicket = (String) cache.get("WorkWeChat_SuiteTicket");
		String jsonStr = workWeChatUtil.getSuiteAccessToken(suiteTicket);
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		/*Integer errcode = jsonObject.getInteger("errcode");
		if(errcode==0){*/
			String suiteAccessToken = jsonObject.getString("suite_access_token");
		if(EmptyUtil.isNotEmpty(suiteAccessToken)){
			Long expTime = jsonObject.getLong("expires_in");
			cache.setString(WorkWeChatUtil.PRE_JEDIS_SUITE_ACCESS_TOKEN_KEY,expTime.intValue(),suiteAccessToken);
			return suiteAccessToken;
		}
		return null;
	}

	public List<UserDTO> findUserByWxUserId(Long companyId, String wxUserId) {
		UserDTO userDTO = new UserDTO();
		userDTO.setCompanyId(companyId);
		userDTO.setWechatUserId(wxUserId);
		List<UserDTO> user = userReadService.findUser(userDTO);
		return user;
	}

	public List<UserDTO> findByAccountAndMobile(String account, String mobile) {
		List<UserDTO> user = userReadService.findByAccountAndMobile(account,mobile);
		return user;
	}
	public void saveUserByMail(String id, String mail) {
		UserDTO userDTO = userReadService.findUserByID(Long.valueOf(id));
		if(EmptyUtil.isEmpty(userDTO)){
			throw new BusinessException("userId无效");
		}
		if(EmptyUtil.isNotEmpty(userDTO.getMail())){
			throw new BusinessException("当前用户非企业微信第一次登陆用户");
		}
		if(EmptyUtil.isEmpty(userDTO.getWechatUserId())){
			throw new BusinessException("当前用户非企业微信用户");
		}
		UserDTO byMail = userReadService.findByMail(mail);
		if(EmptyUtil.isNotEmpty(byMail)){
			throw new BusinessException("当前邮箱已使用，请更换邮箱");
		}
		userDTO.setMail(mail);
		userWriteService.updateUserInfoWithTx(userDTO);
		userAccountWriteService.updateAccountNameByUserId(id,mail);
	}
	public PageResult<UserDTO> findManagePage(Pagination page, UserDTO dto){
		return userReadService.findManagePage(page,dto);
	}

	public int updateManageUser(UserDTO user) {
		userWriteService.updateUserInfoWithTx(user);
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		userExtendDTO.setId(user.getId());
		if(user.getName()!=null && user.getName().length()>0) {
			userExtendDTO.setName(user.getName());
			userExtendWriteService.updateWithTx(userExtendDTO);
		}
		return 1;
	}

	/**
	 * 查询公司配置默认积分初始值
	 * @param companyId
	 * @return
	 */
	private BigDecimal account0DefaultFuBiValue(Long companyId){
		String value=companyConfigClient.queryCompanyconfigValue(companyId,"account.0.defaultValue");
		if (EmptyUtil.isNotEmpty(value)){
			return new BigDecimal(value);
		}
		return null;
	}

	/**
	 * 根据渠道用户唯一标识查询用户信息
	 *
	 * @param channelUserUnique
	 * @return
	 */
	public List<UserDTO> findByChannelUserUniqueAndRegister(String channelUserUnique,Long companyId,Long platformId) {
		return userReadService.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
	}


	public UserVO registDefaultCompanyUserByChannelUserUnique(String channelUserUnique, String name, String passwd, String wxOpenId, Long platformId, Long realCompanyId, ChannelUserUniqueRegLoginVO vo) {
		Long enterpriseId = Constants.USER_DEFAULT_ENTERPRISE_ID;
		Long companyId = Constants.USER_DEFAULT_COMPANY_ID;
		BigDecimal defaultFuBiValue=null;
		if (Objects.nonNull(realCompanyId)){
			companyId=realCompanyId;
			defaultFuBiValue=account0DefaultFuBiValue(realCompanyId);

		}
		CompanyDTO companyDTO = companyReadService.findCompanyById(companyId);
		if(companyDTO !=null && companyDTO.getEnterpriseId() !=null){
			enterpriseId = companyDTO.getEnterpriseId();
		}
		String salt = SaltUtils.getRandomSalt();
		String md5 = MD5Support.MD5(passwd, salt);
		UserDTO userDto = new UserDTO();
		userDto.setEnterpriseId(enterpriseId);
		userDto.setCompanyId(companyId);
		userDto.setPlatformId(platformId);
		userDto.setType(4);
		userDto.setPassword(md5);
		userDto.setSalt(salt);
		userDto.setName(name);
		//userDto.setMobile(name);
		userDto.setChannelUserUnique(channelUserUnique);
		userDto.setLoginName("e"+"-"+enterpriseId.longValue()+"c"+"-"+companyId.intValue()+"-"+channelUserUnique);
		userDto.setMail("e"+"-"+enterpriseId.longValue()+"c"+"-"+companyId.intValue()+"-"+channelUserUnique+"@dachuguanjia.cn");
		if (Objects.nonNull(defaultFuBiValue)){
			userDto.setDefaultFuBiValue(defaultFuBiValue);
		}
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		userExtendDTO.setNickname("e"+"-"+enterpriseId.longValue()+"c"+"-"+companyId.intValue()+"-"+channelUserUnique);
		userExtendDTO.setHeadPicUrl(vo.getHeadPicUrl());
		userExtendDTO.setStatus(1);
		userExtendDTO.setAccountStatus(0);
		userExtendDTO.setIsAdministrator(0);
		userExtendDTO.setWeixin(wxOpenId);
		userExtendDTO.setName(name);
		userExtendDTO.setIsDeleted(0);
		userExtendDTO.setRemark("短信注册用户");
		userExtendDTO.setMemberCode("companyUser-"+companyId.longValue()+"-"+channelUserUnique);
		List<CompanyConfigDTO> companyConfigs= new ArrayList<>();
		if (Objects.nonNull(companyId)){
			userExtendDTO.setChannelSource(UserChannelSourceEnum.getChannelSourceByCompanyId(companyId));
			companyConfigs=companyConfigClient.queryCompanyconfigs(companyId);
		}
		userExtendDTO.setRegtime(new Date());
		Long createdUserId = createUser(userDto, userExtendDTO,companyConfigs);
		UserRoleDTO userRolePo = new UserRoleDTO();
		userRolePo.setRoleId(BusinessConstant.PLATFORM7_USER_ROLEID);
		userRolePo.setUserId(createdUserId);
		userRolePo.setCreateTime(new Date());
		userRoleWriteService.saveWithTx(userRolePo);
		UserDTO returnUser = null;
		if(EmptyUtil.isNotEmpty(wxOpenId)){
			returnUser = findByWeiXinOpenId(wxOpenId,7L);
		}else {
			List<UserDTO> userDTOList = findByChannelUserUniqueAndRegister(channelUserUnique,companyId, 7L);
			if(EmptyUtil.isNotEmpty(userDTOList)){
				returnUser = userDTOList.get(0);
			}
		}
		return UserConverter.toVO(returnUser);
	}

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 *
	 * @param channelUserUnique
	 * @return
	 */
	public UserDTO findLatestLoginByChannelUserUnique(String channelUserUnique,Long companyId,Long platformId) {
		return userReadService.findLatestLoginByChannelUserUnique(channelUserUnique,companyId,platformId);
	}

	public JsonResult<Integer> switchUserEnterprise(SwitchUserEnterpriseVO vo){
		Long companyId = vo.getCompanyId();
		CompanyDTO companyDTO = companyReadService.findCompanyById(vo.getCompanyId());
		if(companyDTO ==null){
			return JsonResult.fail("未找到对应的公司");
		}
		Enterprise enterprise = uniauthDepartmentService.findById(vo.getEnterpriseId().intValue());
		if(enterprise == null){
			return JsonResult.fail("未找到对应的代理商");
		}
		//不切换公司
		if(Objects.isNull(vo.getTargetCompanyId())){
			if(Objects.isNull(companyDTO.getEnterpriseId()) || !Objects.equals(companyDTO.getEnterpriseId(),enterprise.getId())){
				CompanyDTO editCompanyDTO = new CompanyDTO();
				editCompanyDTO.setId(vo.getCompanyId());
				editCompanyDTO.setEnterpriseId(vo.getEnterpriseId());
				companyWriteService.updateCompanyParamWithTX(editCompanyDTO);
			}
		}else{
			//否则切换到新公司
			CompanyDTO targetCompanyDTO = companyReadService.findCompanyById(vo.getCompanyId());
			if(targetCompanyDTO ==null){
				return JsonResult.fail("未找到对应的切换的新公司");
			}
			companyId = targetCompanyDTO.getId();
			if(Objects.isNull(targetCompanyDTO.getEnterpriseId()) || !Objects.equals(targetCompanyDTO.getEnterpriseId(),enterprise.getId())){
				CompanyDTO editCompanyDTO = new CompanyDTO();
				editCompanyDTO.setId(vo.getCompanyId());
				editCompanyDTO.setEnterpriseId(vo.getEnterpriseId());
				companyWriteService.updateCompanyParamWithTX(editCompanyDTO);
			}
		}



		UserDTO userDTO = new UserDTO();
		userDTO.setEnterpriseId(vo.getEnterpriseId());
		userDTO.setCompanyId(companyId);
		userDTO.setId(vo.getUserId());
		int rt = userWriteService.switchUserEnterpriseByCompanyId(userDTO,companyId);
		return JsonResult.success(rt);
	}
}
