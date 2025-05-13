package com.egeo.components.user.business.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.user.business.UserCookieManage;
import com.egeo.components.user.constant.UserLoginConstant;
import com.egeo.components.user.dao.write.WxOpenidMapper;
import com.egeo.components.user.dto.*;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.user.vo.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.egeo.common.BusinessConstant;
import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.CacheKeyConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.user.business.LoginManage;
import com.egeo.components.user.business.UserLoginManage;
import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.business.util.SsoClientUtil;
import com.egeo.components.user.common.DateUtils;
import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.controller.views.request.UniAuthUserInfoAddParam;
import com.egeo.components.user.converter.DepartmentConverter;
import com.egeo.components.user.converter.UserConverter;
import com.egeo.components.user.converter.UserCookieConverter;
import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.converter.UserTempConverter;
import com.egeo.components.user.dao.read.UserReadDAO;
import com.egeo.components.user.dao.write.UserRoleWriteDAO;
import com.egeo.components.user.dao.write.UserWriteDAO;
import com.egeo.components.user.facade.CompanyFacade;
import com.egeo.components.user.facade.DepartmentFacade;
import com.egeo.components.user.facade.UserExtendFacade;
import com.egeo.components.user.facade.UserFacade;
import com.egeo.components.user.facade.UserPlatformFacade;
import com.egeo.components.user.facade.UserQuitTempFacade;
import com.egeo.components.user.facade.UserRoleFacade;
import com.egeo.components.user.facade.UserWelfareFacade;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.utils.InsuranceLoginUtil;
import com.egeo.components.utils.UtilHelper;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Support;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.CodeUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.utils.MailCheckUtils;
import com.egeo.utils.SMSSender;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.Upload;
import com.egeo.utils.WorkWeChatUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.encrypt.MD5;
import com.egeo.utils.excel2.ExcelHeadChecker;
import com.egeo.utils.excel2.ExcelTmplConstant;
import com.egeo.utils.excel2.PropblemReportRowVO;
import com.egeo.utils.str.StringUtils;
import com.egeo.vo.BaseResponse;
import com.egeo.web.JsonResult;
import org.springframework.transaction.annotation.Transactional;

@Service("user")
public class UserManageImpl implements UserManage {
	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource(name = "userFacade")
	private UserFacade userFacade;
	@Autowired
	private UserRoleWriteDAO userRoleWriteDAO;
    @Autowired
    private UserReadDAO userReadDAO;
    @Autowired
    private UserWriteDAO userWriteDAO;

	@Resource(name = "userPlatformFacade")
	private UserPlatformFacade userPlatformFacade;

	@Resource(name = "userRoleFacade")
	private UserRoleFacade userRoleFacade;

	@Resource(name = "companyFacade")
	private CompanyFacade companyFacade;

	@Resource
	private JedisUtil cache;

	@Resource
	private SMSSender sMSSender;

	@Resource(name = "departmentFacade")
	private DepartmentFacade departmentFacade;

	@Resource(name = "userExtendFacade")
	private UserExtendFacade userExtendFacade;

	@Resource(name = "userQuitTempFacade")
	private UserQuitTempFacade userQuitTempFacade;

	@Resource(name = "userWelfareFacade")
	private UserWelfareFacade userWelfareFacade;

	@Resource(name="login")
	private LoginManage loginManage;

	@Resource(name="userLogin")
	private UserLoginManage userLoginManage;

	@Resource(name="ssoClientUtil")
	private  SsoClientUtil ssoClientUtil;
	@Autowired
	private Upload uploadService;
	@Autowired
	private WorkWeChatUtil workWeChatUtil;
	@Resource(name="userCookie")
	private UserCookieManage userCookieManage;
    @Autowired
    private WxOpenidMapper wxOpenIdManage;

	@Autowired
	private CompanyConfigClient companyConfig;

	@Override
	public UserVO findUniqueUser(UserVO user) {

		if (EmptyUtil.isNotEmpty(user.getLoginName()) || EmptyUtil.isNotEmpty(user.getMail())
				|| StringUtils.isNotEmpty(user.getMobile())) {
			UserDTO dto = new UserDTO();
			dto.setLoginName(user.getLoginName());
			dto.setMail(user.getMail());
			dto.setMobile(user.getMobile());
			return UserConverter.toVO(userFacade.findUniqueUser(dto));
		} else {
			throw new BusinessException("账号，邮箱，电话号码必须有一个");
		}

	}

	@Override
	public int updateEncryptInfoByUserWithTx(UserDTO updUser) {

		return userFacade.updateEncryptInfoByUserWithTx(updUser);
	}

	@Override
	public UserDTO findUserByID(Long userId) {

		return userFacade.findUserByID(userId);
	}

	@Override
	public int updateUserInfo(UserDTO upduser) {

		return userFacade.updateUserInfo(upduser);
	}

	@Override
	public int updateEncryptByNewEncryptWithTx(PassWordVO passWord, HttpServletRequest req) {

		CacheUser cacheUser = RuntimeContext.cacheUser();

		UserDTO dto = userFacade.findUserByID(cacheUser.getId());
		String pd = null;

		if (StringUtils.isEmpty(dto.getSalt())) {
			pd = MD5Support.MD5(passWord.getNewPassword());
			;
		} else {
			pd = MD5Support.MD5(passWord.getNewPassword(), dto.getSalt());
		}

		dto.setPassword(pd);
		return userFacade.updateEncryptInfoByUserWithTx(dto);
	}

	public PageResult<UserDTO> findPage(Pagination page, UserVO userVO, UserExtendVO userExtendVO,
			UserCookieVO userCookieVO,Integer... types) {
		UserDTO dto = UserConverter.toDTO(userVO);
		//查询可有管理员权限
		userExtendVO.setIsAdministrator(1);
		UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(userExtendVO);
		UserCookieDTO userCookieDTO = UserCookieConverter.toDTO(userCookieVO);
		return userFacade.findPage(page, dto, userExtendDTO, userCookieDTO,types);

	}
	public PageResult<UserDTO> findPage(Pagination page, UserVO userVO,Integer... types) {
		UserDTO dto = UserConverter.toDTO(userVO);
		//查询可有管理员权限
		return userFacade.findPage(page, dto,types);

	}

    public PageResult<UserDTO> findPage(Pagination page,String sysCode, Long rid, UserVO userVO,Integer... types){
    	UserDTO dto = UserConverter.toDTO(userVO);
		//查询可有管理员权限
		return userFacade.findPage(page, dto,types);
    }
	public String saveOrUpdate(UserVO userVO, UserExtendVO userExtendVO, UserCookieVO userCookieVO) {
		if (userVO.getLoginName().contains("@")) {
			throw new BusinessException("账号不能包含@符号");
		}
		if (StringUtils.validMobile(userVO.getLoginName())) {
			throw new BusinessException("账号不能为手机号码");
		}
		UserDTO dto = UserConverter.toDTO(userVO);
		UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(userExtendVO);
		UserCookieDTO userCookieDTO = UserCookieConverter.toDTO(userCookieVO);
		return userFacade.saveOrUpdate(dto, userExtendDTO, userCookieDTO);
	}

	/* (non-Javadoc)
	 * @see com.egeo.components.user.business.UserManage#insertOrUpdateManageUser(com.egeo.components.user.vo.UserTempConditionVO, java.lang.String)
	 *  这里用departmentId代替代理商Id
	 *
	 */
	@Override
	public Long insertOrUpdateManageUser(String loginName,String mobile,String email,String newPwd,
			Long enterpriseId, Long companyId,String roleIds, Long id,Integer type) {

		UserDTO userDTO = new UserDTO();
		if (id == null) {
			//判断账号是否存在
			UserPO existQ = new UserPO();
			if(loginName==null) {
				throw new BusinessException("账号为空");
			}
			if(type==null || type.intValue()<0 ||type.intValue()>3) {
				throw new BusinessException("用户类型异常");
			}
			existQ.setLoginName(loginName);
			if(email!=null) {
				existQ.setMail(email);
			}
			if(mobile!=null) {
				existQ.setMobile(mobile);
			}

			List<UserCondition> exists = userReadDAO.findExistManageUser(existQ);
			if(exists!=null && exists.size()>0) {
				throw new BusinessException("登录名、手机号码、邮箱 存在重复，请更换重试");
			}
			// user
			//userDTO.setName(vo.getName());
			if(email!=null) {
				userDTO.setMail(StringUtils.lowerCase(email));
			}
			userDTO.setLoginName(loginName);
			if(type.intValue()==2) {
				userDTO.setEnterpriseId(enterpriseId);
			}else if(type.intValue()==3) {
				userDTO.setEnterpriseId(enterpriseId);
				userDTO.setCompanyId(companyId);
			}
			userDTO.setMobile(mobile);
			userDTO.setSalt(SaltUtils.getRandomSalt());
			userDTO.setPassword(MD5Support.MD5(newPwd, userDTO.getSalt()));
			userDTO.setPlatformId(7l);

			/*userWelfareDTO.setUserId(userId);

			userWelfareFacade.insertUserWelfareWithTx(userWelfareDTO);*/

			//插入user库用户信息相关的表
			UserPO po = UserConverter.toPO(userDTO);
			int userId = userWriteDAO.insert(po);




			return po.getId();

		} else {
			// 说明是更新
			// user
			userDTO.setId(id);
			userDTO.setMail(StringUtils.lowerCase(email));
			userDTO.setMobile(mobile);
			/*userWelfareFacade.updateUserWelfareWithTx(userWelfareDTO2);*/

			//return userFacade.updateAllUserInfosWithTx(userDTO, userExtendDTO2, userWelfareDTO2);
			UserPO po = UserConverter.toPO(userDTO);
			userWriteDAO.update(po);
			return id;
		}
	}

	@Override
	public Long insertOrUpdateUser(UserTempConditionVO vo, String dePartMentIdArr) {

		UserDTO userDTO = new UserDTO();
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		UserWelfareDTO userWelfareDTO = new UserWelfareDTO();
		if (vo.getId() == null) {
			// 说明是新增
			// 如果是慢有优平台则判断慢有优公司或不是慢有优公司是否存在该手机用户
			if(vo.getPlatformId().equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
				// 根据平台公司及手机判断是否存在有效用户
				Boolean isExistUser = userFacade.findIsExistUser(vo.getMobile(),vo.getCompanyId(),vo.getPlatformId());
				if(isExistUser){
					throw new BusinessException("该手机已存在有效用户");
				}
			}
			// user
			userDTO.setName(vo.getName());
			//根据用户邮箱查询用户信息
			// 邮箱查重:导入和新增的邮箱必须在数据库中不存在，或者存在但是邮箱对应的账户均为失效状态
			UserExtendDTO userExtendDTO3 = new UserExtendDTO();
			userExtendDTO3.setMail(StringUtils.lowerCase(vo.getMail()));
			userExtendDTO3.setAccountStatus(0);
			userExtendDTO3.setPlatformId(vo.getPlatformId());
			List<UserExtendDTO> userExtendDTOList = userExtendFacade.findAlluser(userExtendDTO3);
			if(EmptyUtil.isNotEmpty(userExtendDTOList)){
				throw new BusinessException("邮箱已被使用");
			}

			userDTO.setMail(StringUtils.lowerCase(vo.getMail()));
			userDTO.setLoginName(StringUtils.lowerCase(vo.getMail()));
			userDTO.setCompanyId(vo.getCompanyId());
			if(vo.getCompanyId()!=null) {
				CompanyDTO companyDto = companyFacade.findCompanyById(vo.getCompanyId());
				if(companyDto.getEnterpriseId()!=null) {
					userDTO.setEnterpriseId(companyDto.getEnterpriseId());
				}
			}
			userDTO.setMobile(vo.getMobile());
			userDTO.setSalt(SaltUtils.getRandomSalt());
			userDTO.setPassword(MD5Support.MD5(vo.getPassword(), userDTO.getSalt()));
			userDTO.setPlatformId(vo.getPlatformId());
			/*Long userId = userFacade.insertUserWithTx(userDTO);
			// userExtend
			userExtendDTO.setId(userId);*/
			userExtendDTO.setSex(vo.getSex());
			userExtendDTO.setName(vo.getName());
			userExtendDTO.setNamePy(StringUtils.Hanyu2Pinyin(vo.getName()));
			userExtendDTO.setRegtime(new Date(System.currentTimeMillis()));
			userExtendDTO.setBirthday(vo.getBirthday());
			userExtendDTO.setMobile(vo.getMobile());
			userExtendDTO.setRegisterStoreId(EmptyUtil.isEmpty(vo.getRegisterShopCode()) ? null : Long.valueOf(vo.getRegisterShopCode()));
			//根据用户编号查询用户信息
			// 编号查重: 同一公司下的员工编号必须保持唯一性，不同公司之间的员工编号可重复
			UserExtendDTO userExtendDTO2 = new UserExtendDTO();
			userExtendDTO2.setMemberCode(vo.getMemberCode());
			userExtendDTO2.setCompanyId(vo.getCompanyId());
			userExtendDTO2.setAccountStatus(0);
			List<UserExtendDTO> userExtendList = userExtendFacade.findAlluser(userExtendDTO2);
			if(EmptyUtil.isNotEmpty(userExtendList)){
				// 平台id为慢有优  查询重新生成一下，不行报错
				if(vo.getPlatformId().equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
					// 根据平台id查询其下员工数量
					Integer userCount = userPlatformFacade.findUserCountByPlatformId(vo.getPlatformId());
					vo.setMemberCode(PlatformKeyConstant.MYY_PLATFORM_USER_CODE_PREFIX + "-" + userCount + 1);


					UserExtendDTO userExtend = new UserExtendDTO();
					userExtend.setMemberCode(vo.getMemberCode());
					userExtend.setCompanyId(vo.getCompanyId());
					userExtend.setAccountStatus(0);
					List<UserExtendDTO> userExtends = userExtendFacade.findAlluser(userExtend);
					if(EmptyUtil.isNotEmpty(userExtends)){
						throw new BusinessException("员工编号已存在");
					}
				}
				if(vo.getPlatformId().equals(PlatformKeyConstant.FGJ_PLATFORM_ID)){
					throw new BusinessException("员工编号已存在");
				}

			}

			userExtendDTO.setMemberCode(vo.getMemberCode());
			userExtendDTO.setPlatformId(vo.getPlatformId());
			/*if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(vo.getPlatformId())) {
				//慢有优平台用户企业账号直接设置为启用
				userExtendDTO.setStatus(1);
				UserRoleDTO userRoleDTO = new UserRoleDTO();
				userRoleDTO.setRoleId(BusinessConstant.PLATFORM2_USER_ROLEID);
				userRoleDTO.setUserId(userId);
				userRoleFacade.save(userRoleDTO);
			}*/

			userExtendDTO.setIsAdministrator(vo.getCurrUserId().equals(1L) ? vo.getIsAdministrator() : null);
//			userExtendDTO.setIsAdministrator(vo.getIsAdministrator());
			/*userExtendFacade.saveUserExtendWithTx(userExtendDTO);*/
			// userWelfare
			if (StringUtils.isNotEmpty(dePartMentIdArr) && !dePartMentIdArr.equals("[]")) {
				String dePartMentIds = dePartMentIdArr.substring(1, dePartMentIdArr.length() - 1);
				String[] dePartMentIdArray = dePartMentIds.split(",");
				String id = dePartMentIdArray[dePartMentIdArray.length - 1];

				userWelfareDTO.setDepartmentId(Long.valueOf(id));
			} else {
				userWelfareDTO.setDepartmentId(null);
			}
			/*userWelfareDTO.setUserId(userId);

			userWelfareFacade.insertUserWelfareWithTx(userWelfareDTO);*/

			//插入user库用户信息相关的表
			Long userId = userFacade.insertAllUserInfosWithTx(userDTO, userExtendDTO, userWelfareDTO);
			// 给用户开户
			userDTO.setId(userId);
			userFacade.userAccount(userDTO);

			return userId;

		} else {
			// 说明是更新
			// user
			userDTO.setId(vo.getId());
			userDTO.setName(vo.getName());
			userDTO.setLoginName(StringUtils.lowerCase(vo.getMail()));
			//根据用户邮箱查询用户信息
			// 邮箱查重:导入和新增的邮箱必须在数据库中不存在，或者存在但是邮箱对应的账户均为失效状态
			UserExtendDTO userExtendDTO3 = new UserExtendDTO();
			userExtendDTO3.setMail(StringUtils.lowerCase(vo.getMail()));
			userExtendDTO3.setAccountStatus(0);
			userExtendDTO3.setPlatformId(vo.getPlatformId());
			List<UserExtendDTO> userExtendDTOList = userExtendFacade.findAlluser(userExtendDTO3);
			/*if(userExtendDTOList.size()>0){
				if(!vo.getId().equals(userExtendDTOList.get(0).getId())){
					throw new BusinessException("用户邮箱重复");
				}
			}*/
			for (UserExtendDTO dto : userExtendDTOList) {
				if(!dto.getId().equals(vo.getId())){
					throw new BusinessException("邮箱已被使用");
				}
			}

			userDTO.setMail(StringUtils.lowerCase(vo.getMail()));
			userDTO.setMobile(vo.getMobile());
			userDTO.setCompanyId(vo.getCompanyId());
			/*int updateUserInfo = userFacade.updateUserInfo(userDTO);*/

			// userExtend
			userExtendDTO.setId(vo.getId());
			userExtendDTO.setSex(vo.getSex());//1
			userExtendDTO.setName(vo.getName());
			//根据用户编号查询用户信息
			// 编号查重: 同一公司下的员工编号必须保持唯一性，不同公司之间的员工编号可重复
			UserExtendDTO userExtendDTO2 = new UserExtendDTO();
			userExtendDTO2.setMemberCode(vo.getMemberCode());
			userExtendDTO2.setCompanyId(vo.getCompanyId());
			List<UserExtendDTO> userExtendList = userExtendFacade.findAlluser(userExtendDTO2);
			/*if(EmptyUtil.isNotEmpty(userExtendList)){
				if(userExtendList.get(0).getId().longValue() != vo.getId().longValue()){
					throw new BusinessException("用户编号重复");
				}
			}*/
			for (UserExtendDTO dto : userExtendList) {
				if(!dto.getId().equals(vo.getId())){
					throw new BusinessException("用户编号重复");
				}
			}

			userExtendDTO.setMemberCode(vo.getMemberCode());
			userExtendDTO.setNamePy(StringUtils.Hanyu2Pinyin(vo.getName()));
			userExtendDTO.setBirthday(vo.getBirthday());
			userExtendDTO.setIsAdministrator(vo.getCurrUserId().equals(1L) ? vo.getIsAdministrator() : null);	// ..
//			userExtendDTO.setIsAdministrator(vo.getIsAdministrator());
			/*userExtendFacade.updateUserExtend(userExtendDTO);*/

			// userWelfare
			userWelfareDTO.setUserId(vo.getId());
			List<UserWelfareDTO> findUserWelfareAll = userWelfareFacade.findUserWelfareAll(userWelfareDTO);
			UserWelfareDTO userWelfareDTO2 = findUserWelfareAll.get(0);
			// userWelfare
			if (StringUtils.isNotEmpty(dePartMentIdArr)) {
				String dePartMentIds = dePartMentIdArr.substring(1, dePartMentIdArr.length() - 1);
				String[] dePartMentIdArray = dePartMentIds.split(",");
				String id = dePartMentIdArray[dePartMentIdArray.length - 1];

				userWelfareDTO2.setDepartmentId(Long.valueOf(id));
			} else {
				userWelfareDTO2.setDepartmentId(null);
			}
			/*userWelfareFacade.updateUserWelfareWithTx(userWelfareDTO2);*/

			//return userFacade.updateAllUserInfosWithTx(userDTO, userExtendDTO2, userWelfareDTO2);
			return userFacade.updateAllUserInfosWithTx(userDTO, userExtendDTO, userWelfareDTO2);
		}

	}

	@Override
	public void deleteWithTx(UserDTO dto) {
		userFacade.deleteWithTx(dto);
	}

	@Override
	public UserDTO userById(UserVO vo) {
		return userFacade.userById(UserConverter.toDTO(vo));
	}

	@Override
	public PageResult<ProductUser> findProductUser(Pagination page, UserVO userVO, UserExtendVO userExtendVO) {

		return userFacade.findProductUser(page, UserConverter.toDTO(userVO), UserExtendConverter.toDTO(userExtendVO));
	}

	@Override
	public ProductUser productUserById(ProductUser vo) {

		return userFacade.productUserById(vo);
	}

	@Override
	public JsonResult<Map<String, Object>> companyDepartmentList(Long companyId) {
		List<DepartmentDTO> dtos = userFacade.queryDepartmentListByCompanyId(companyId);
		List<DepartmentVO> vos = DepartmentConverter.toVO(dtos);
		Map<String, Object> data = new HashMap<>();
		data.put("departmentList", vos);
		return JsonResult.success(data);
	}

	@Override
	public JsonResult<Map<String, Object>> departMentMemberList(Long departmentId, Long companyId, Integer pageNo,
			Integer pageSize) {
		if (pageNo == null)
			pageNo = 1;
		if (pageSize == null)
			pageNo = 20;
		Pagination page = new Pagination(pageNo, pageSize);
		PageResult<UserExtendDTO> dtos = null;
		if (departmentId == null) {
			// 查询整个公司
			dtos = userFacade.queryUserListByCompanyId(companyId, page);
		} else {
			// 按部门查询
			dtos = userFacade.queryUserListByDepartmentId(departmentId, page);
		}
		PageResult<DepartmentUserVO> result = new PageResult<>();
		List<DepartmentUserVO> vos = UserExtendConverter.toDepartmentUserVO(dtos.getList());
		result.setList(vos);
		result.setPageNo(dtos.getPageNo());
		result.setPageSize(dtos.getPageSize());
		result.setTotalSize(dtos.getTotalSize());
		Map<String, Object> data = new HashMap<>();
		return JsonResult.success(data);
	}

	/**
	 * 根据邮箱查询用户
	 *
	 * @param mail
	 * @return
	 */
	@Override
	public UserVO userByMail(String mail) {
		UserDTO userDTO = userFacade.userByMail(mail);
		if (EmptyUtil.isNotEmpty(userDTO)) {
			return UserConverter.toVO(userDTO);
		} else {
			return null;
		}

	}

	/**
	 * 用户注册
	 *
	 * @return
	 */
	@Override
	public Long userRegister(UserVO vo,String shortCode) {
		if (EmptyUtil.isEmpty(vo.getPlatformId())) {
			throw new BusinessException(BusinessExceptionConstant.USER_LOGIN_NOCOMPANYID_OR_NOPLATFORMID,
					"用户登录没有带company_id或者platform_id");
		}
		Long id = userFacade.userRegisterWithTx(UserConverter.toDTO(vo),shortCode);
		if (id != null) {
			vo.setId(id);
			autoReceiveRegisterCoupon(vo);
		}
		return id;
	}

	/**
	 * 根据邮箱修改密码
	 *
	 * @return
	 */
	@Override
	public int updateUserPasswordByMail(String mobile, Long userId, String mail, String password, Long platformId) {

		return userFacade.updateUserPasswordByMail(mobile,userId,mail, password, platformId);
	}

	// 绑定手机的时候发送验证码
	@Override
	public Long sendIdentifyingCode(Long companyId, int type, String mobile, int len, String redisKye, Long platformId) {
		List<UserDTO> userByMobile = userFacade.findUserByMobile(mobile, platformId);

		String platformNmae = null;
        if(platformId.equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
        	platformNmae = PlatformKeyConstant.MYY_PLATFORM_NAME;
        }else if(platformId.equals(PlatformKeyConstant.FGJ_PLATFORM_ID)){
			platformNmae = PlatformKeyConstant.FGJ_PLATFORM_NAME;
			if(type!=1&&type!=6){
				if(PlatformKeyConstant.BAI_ER_COMPANY_ID.equals(companyId)){
					platformNmae = PlatformKeyConstant.BAI_ER_MESSAGE_HEAD_NAME;
				}
			}else{
				if(EmptyUtil.isNotEmpty(userByMobile)){
					UserDTO user = userByMobile.get(0);
					if(user.getCompanyId().equals(PlatformKeyConstant.BAI_ER_COMPANY_ID)){
						platformNmae = PlatformKeyConstant.BAI_ER_MESSAGE_HEAD_NAME;
					}
				}
			}

        }
		String randomCode = StringUtils.getRandomByCount(len);
		String expireMsg = " 30分钟内有效！";
		System.out.println(randomCode+" 30分钟内有效！");
		String returnCode = sMSSender.sendRegisterCode(mobile, Integer.valueOf(randomCode), expireMsg,platformNmae);
		//String returnCode ="1";
		if (StringUtils.isNotBlank(returnCode)) {
			// 将验证码保存到redis数据库
			cache.set(redisKye, 1800, randomCode);
			if(userByMobile==null || userByMobile.size()==0){
				CacheUser cacheUser = RuntimeContext.cacheUser();
				if(cacheUser!=null) {
					Long userId = cacheUser.getId();
					userFacade.bindingMobileByUserId(mobile, userId, null);
				}
			}
			return 1L;
		}
		return null;

	}

	@Override
	public String bindingMobile(String mobile, String identityCode, Long userId, int type,String beforeMobile,String weiXinOpenId,Long platformId) {
		String code = (String) cache.get(CacheKeyConstant.MOBILE_BINDING_IDENTITY_KEY + mobile);

		// 判断验证码
		if (EmptyUtil.isEmpty(code)) {
			// 如果验证码为空
			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG, "验证码错误");
		}

		// 验证码不为空，比较验证码是否正确
		if (StringUtils.equals(code, identityCode)) {

			/*// 根据用户手机号查询用户信息
			UserDTO userDTO = userFacade.findByManage(mobile);
			// 1 、绑定 2、判断是否解绑

			if (EmptyUtil.isNotEmpty(userDTO)) {
				if (type == 1) {
					throw new BusinessException(BusinessExceptionConstant.IS_BINDING_VERIFICATION,
							"该手机已绑定其他账号。如果继续，原账号将自动解绑，确认绑定吗?");
				} else if (type == 2) {
					// 验证码正确，解绑
					userFacade.bindingMobileByUserId(null, userDTO.getId());
					// 验证码正确，绑定手机
					userFacade.bindingMobileByUserId(mobile, userId);
				}
			} else {
				// 验证码正确，更新用户账户信息
				userFacade.bindingMobileByUserId(mobile, userId);
			}*/

			// 2018/6/30逻辑处理
			// 如果之前手机号为空直接根据当前用户id绑定手机，否则根据之前手机号统一换绑手机
			if(beforeMobile == null){
				// 验证码正确，更新用户账户信息
				userFacade.bindingMobileByUserId(mobile, userId,weiXinOpenId);
			}else{
				// 验证码正确，根据用户之前手机号统一换绑手机
				userFacade.bindingMobileByBeforeMobile(mobile, beforeMobile,platformId);
			}
    		// 使用一次之后直接删除
        	cache.del(CacheKeyConstant.MOBILE_LOGIN_KEY + mobile);
        	userFacade.insertRelevanceUserInfoAndSend(mobile,platformId, userId);
			return "绑定成功";
		} else {
			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG, "验证码错误");
		}

	}

	@Override
	public List<UserVO> findByMobileAndRegister(String mobile,Long platformId) {
		List<UserDTO> list = userFacade.findByMobileAndRegister(mobile,platformId);
		return UserConverter.toVO(list);
	}

	@Override
	public UserVO findLatestLoginByMobile(String mobile,Long platformId) {
		UserDTO userDTO = userFacade.findLatestLoginByMobile(mobile,platformId);
		return UserConverter.toVO(userDTO);
	}

	/**
	 * 用户设置支付密码
	 *
	 * @return
	 */
	@Override
	public int setPaymentPassword(String mail, String verificationCode, String password, String confirmPassword, Long platformId) {
		if (EmptyUtil.isEmpty(mail)) {
			throw new BusinessException(BusinessExceptionConstant.EMAIL_EMPTY, "邮箱为空");
		}
		if(EmptyUtil.isEmpty(verificationCode))
			throw new BusinessException("请输入验证码");
		// 根据邮箱查询用户信息
		UserDTO userDTO = userFacade.userByMail(mail);
		// 根据邮箱获取放入缓存中的验证码
		String redisVerificationCode = (String) cache.get(CacheKeyConstant.UPDATE_PAYMENT_PASSWORD_MAIL_KEY + mail);
		if (EmptyUtil.isEmpty(redisVerificationCode)) {
			throw new BusinessException("验证码过期、请重新发送");
		}
		String md5 = MD5Support.MD5(verificationCode, mail);
		if (!redisVerificationCode.equals(md5)) {
			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG, "验证码错误");
		}
		if (!confirmPassword.equals(password)) {
			throw new BusinessException(BusinessExceptionConstant.USER_NEW_PASSWORD_NO_MATCH, "密码不一致");
		}
		// 使用一次之后直接删除
    	cache.del(CacheKeyConstant.UPDATE_PAYMENT_PASSWORD_MAIL_KEY + mail);
		// 根据用户邮箱设置支付密码
		return userFacade.setPaymentPassword(userDTO.getId(),mail, password, platformId);
	}

	@Override
	public JsonResult<Map<String, Object>> importUserWithTx(Long companyId, Integer tempType, Long platformId,
			List<Map<String, Object>> valueList,HttpServletRequest req) {
		//校验是否存在缓存,存在则提示清除缓存
		UserTempDTO tempDTO = new UserTempDTO();
		tempDTO.setType(Integer.valueOf(2));
		tempDTO.setPlatformId(platformId);
		tempDTO.setCompanyId(companyId);
		List<UserTempDTO> userTempAll = userFacade.findUserTempAll(tempDTO);
		if(EmptyUtil.isNotEmpty(userTempAll)){
			return JsonResult.fail("请清除缓存");
		}


		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容

		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.newStaff.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);
		// 公司校验
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(companyId);
		companyDTO.setPlatformId(platformId);
		List<CompanyDTO> companyList = companyFacade.findCompanyAll(companyDTO);
		if (EmptyUtil.isEmpty(companyList) || companyList.size() != 1)
			return JsonResult.fail("公司不存在");

		CompanyDTO c = companyList.get(0);

		if (!c.getCompanyName().equals(valueList.get(0).get("CELL2").toString()))
			return JsonResult.fail("公司信息错误");

		// 模板类型
		String tmplName = ExcelTmplConstant.newStaff.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		CacheUser userCache = RuntimeContext.cacheUser();
		Long userId = userCache.getId();// 用户id
		String userName = userCache.getName();// 用户名称
		String ip=HostUtils.getClientIP(req);
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}

		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间解析错误");
		}

		// 序列号校验
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

		//headImportRecordsDTO.setGeneratedTime(date);
		headImportRecordsDTO.setTemplateType(tmplName);
		headImportRecordsDTO.setCompanyName(c.getCompanyName());
		headImportRecordsDTO.setFileSequenceNumber(sn);

		List<HeadImportRecordsDTO> importRecordList = userFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (!EmptyUtil.isEmpty(importRecordList))
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");

		// ************************************

		// 列表文件校验

		// 查询公司所有的部门编号
		// 设置一个集合用来装部门的编号
		List<Long> departmentIdList = new ArrayList<>();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setCompanyId(companyId);
		List<DepartmentDTO> findDepartmentAll = departmentFacade.findDepartmentAll(departmentDTO);

		for (DepartmentDTO departmentDTO2 : findDepartmentAll) {
			departmentIdList.add(departmentDTO2.getId());
		}

		if (departmentIdList.size() == 0) {
			return JsonResult.fail("公司的组织架构已经失效,请稍后再试");
		}

		// 封装错误的集合
		List<PropblemReportRowVO<UserTempVO>> problemRep = new ArrayList<>();

		// 预览的集合
		List<UserTempVO> userViewList = new ArrayList<>();

		// 对员工编号创建一个集合
		Set<String> memberCodeSet = new HashSet<String>();

		// 对员工邮箱创建一个集合
		Set<String> mailSet = new HashSet<String>();

		for (int i = 2; i < valueList.size(); i++) {

			// 内部不重复,空值校验

			UserTempVO vo = rowBean(valueList.get(i), problemRep, i, memberCodeSet, mailSet, departmentIdList,companyId,platformId);
			vo.setCreateUserid(userId);
			vo.setCreateUsername(userName);
			vo.setCreateUserip(ip);
			vo.setEnterpriseId(c.getEnterpriseId());
			vo.setCreateUsermac(mac);
			vo.setType(Integer.valueOf(2));//设置类型为1.新职员导入
			userViewList.add(vo);
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {

			// 说明没有任何错误
			// 1.经表头信息储存到临时导入记录表,并且封装导入记录表的信息
			// 写导入记录表的数据
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setCompanyName(valueList.get(0).get("CELL2").toString());
			importRecordsDTO.setTemplateType(valueList.get(0).get("CELL4").toString());
			importRecordsDTO.setGeneratedTime(date);
			importRecordsDTO.setFileSequenceNumber(sn);

			// 2.将读到的数据存储在临时表里面。封装要预览的数据
			for (UserTempVO userTempVO : userViewList) {
				userTempVO.setCompanyId(companyId);
				userTempVO.setPlatformId(platformId);
			}
			Set<UserTempVO> userViews = userViewList.stream().collect(Collectors.toSet());
			List<UserTempDTO> userTempDTOList = UserTempConverter.toDTO(userViews);

			Map<String, Object> data = userFacade.insertuserFacadesAndUserViewList(importRecordsDTO, userTempDTOList);

			// 将表头信心返回
			data.put("headInfo", importRecordsDTO);
			data.put("overView", userViewList);

			return JsonResult.success("success", data);
		} else {

			// 说明有错误
			/*
			 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
			 */
			// 出现错误
			// 生成问题报告.xlsx上传至dfs
			// 封装返回值得集合
			//Map<String, Object> data = new HashMap<>();
			String repUrl = genAndUploadRep(problemRep);

			return JsonResult.fail(repUrl, 169);
		}
	}

	@Override
	public Long assureImportUser(Long companyId, Long platformId, Long importId, String userOrderInfo) {

		return userFacade.assureImportUser(companyId, platformId, importId, userOrderInfo);
	}

	@Override
	public JsonResult<Map<String, Object>> reImportUserWithTx(Long companyId, Integer tempType, Long platformId,
															List<Map<String, Object>> valueList,HttpServletRequest req) {
		//校验是否存在缓存,存在则提示清除缓存
		UserTempDTO tempDTO = new UserTempDTO();
		tempDTO.setType(Integer.valueOf(10));
		tempDTO.setPlatformId(platformId);
		tempDTO.setCompanyId(companyId);
		List<UserTempDTO> userTempAll = userFacade.findUserTempAll(tempDTO);
		if(EmptyUtil.isNotEmpty(userTempAll)){
			return JsonResult.fail("请清除缓存");
		}


		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容

		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.reStaff.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);
		// 公司校验
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(companyId);
		companyDTO.setPlatformId(platformId);
		List<CompanyDTO> companyList = companyFacade.findCompanyAll(companyDTO);
		if (EmptyUtil.isEmpty(companyList) || companyList.size() != 1)
			return JsonResult.fail("公司不存在");

		CompanyDTO c = companyList.get(0);

		if (!c.getCompanyName().equals(valueList.get(0).get("CELL2").toString()))
			return JsonResult.fail("公司信息错误");

		// 模板类型
		String tmplName = ExcelTmplConstant.reStaff.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		CacheUser userCache = RuntimeContext.cacheUser();
		Long userId = userCache.getId();// 用户id
		String userName = userCache.getName();// 用户名称
		String ip=HostUtils.getClientIP(req);
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}

		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间解析错误");
		}

		// 序列号校验
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

		//headImportRecordsDTO.setGeneratedTime(date);
		headImportRecordsDTO.setTemplateType(tmplName);
		headImportRecordsDTO.setCompanyName(c.getCompanyName());
		headImportRecordsDTO.setFileSequenceNumber(sn);

		List<HeadImportRecordsDTO> importRecordList = userFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (!EmptyUtil.isEmpty(importRecordList))
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");

		// ************************************

		// 列表文件校验

		// 查询公司所有的部门编号
		// 设置一个集合用来装部门的编号
		List<Long> departmentIdList = new ArrayList<>();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setCompanyId(companyId);
		List<DepartmentDTO> findDepartmentAll = departmentFacade.findDepartmentAll(departmentDTO);

		for (DepartmentDTO departmentDTO2 : findDepartmentAll) {
			departmentIdList.add(departmentDTO2.getId());
		}

		if (departmentIdList.size() == 0) {
			return JsonResult.fail("公司的组织架构已经失效,请稍后再试");
		}

		// 封装错误的集合
		List<PropblemReportRowVO<UserTempVO>> problemRep = new ArrayList<>();

		// 预览的集合
		List<UserTempVO> userViewList = new ArrayList<>();

		// 对员工编号创建一个集合
		Set<String> memberCodeSet = new HashSet<String>();

		// 对员工邮箱创建一个集合
		Set<String> mailSet = new HashSet<String>();

		for (int i = 2; i < valueList.size(); i++) {

			// 内部不重复,空值校验

			UserTempVO vo = rowBeanRe(valueList.get(i), problemRep, i, memberCodeSet, mailSet, departmentIdList,companyId,platformId);
			vo.setCreateUserid(userId);
			vo.setCreateUsername(userName);
			vo.setCreateUserip(ip);
			vo.setCreateUsermac(mac);
			vo.setType(Integer.valueOf(10));//设置类型为10.员工名单重导入
			userViewList.add(vo);
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {

			// 说明没有任何错误
			// 1.经表头信息储存到临时导入记录表,并且封装导入记录表的信息
			// 写导入记录表的数据
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setCompanyName(valueList.get(0).get("CELL2").toString());
			importRecordsDTO.setTemplateType(valueList.get(0).get("CELL4").toString());
			importRecordsDTO.setGeneratedTime(date);
			importRecordsDTO.setFileSequenceNumber(sn);

			// 2.将读到的数据存储在临时表里面。封装要预览的数据
			for (UserTempVO userTempVO : userViewList) {
				userTempVO.setCompanyId(companyId);
				userTempVO.setPlatformId(platformId);
			}

			Set<UserTempVO> userViews = userViewList.stream().collect(Collectors.toSet());

			List<UserTempDTO> userTempDTOList = UserTempConverter.toDTO(userViews);

			/*List<UserTempDTO> list = new ArrayList<UserTempDTO>();

			//将员工编号、邮箱都重复的员工的在职状态变更为“在职”、清空这部分员工的离职时间、资产回收日期
			/*for (UserTempDTO dto : userTempDTOList) {

				UserExtendDTO userExtendDTO = new UserExtendDTO();
				userExtendDTO.setMail(dto.getMail());
				userExtendDTO.setMemberCode(dto.getMemberCode());
				userExtendDTO.setAccountStatus(0);
				userExtendDTO.setPlatformId(platformId);
				List<UserExtendDTO> userExtendAll = userExtendFacade.userAll(userExtendDTO);

				//未重复的员工信息作为新增员工进行导入
				if (EmptyUtil.isEmpty(userExtendAll) && userExtendAll.size() == 0) {
					list.add(dto);
				}else {
					for (UserExtendDTO dto2 :userExtendAll) {
						Long id = dto.getId();
						userExtendFacade.updateReImportUserStatus(id);
					}
				}
			}*/


			Map<String, Object> data = userFacade.insertuserFacadesAndUserViewList(importRecordsDTO, userTempDTOList);

			// 将表头信心返回
			data.put("headInfo", importRecordsDTO);
			data.put("overView", userViewList);

			return JsonResult.success("success", data);
		} else {

			// 说明有错误
			/*
			 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
			 */
			// 出现错误
			// 生成问题报告.xlsx上传至dfs
			// 封装返回值得集合
			//Map<String, Object> data = new HashMap<>();
			String repUrl = genAndUploadRep(problemRep);

			return JsonResult.fail(repUrl, 169);
		}
	}

	@Override
	public Long assureReImportUser(Long companyId, Long platformId, Long importId, String userOrderInfo) {

		return userFacade.assureReImportUser(companyId, platformId, importId, userOrderInfo);
	}

	@Override
	public JsonResult<Map<String, Object>> importQuitUserWithTx(Long companyId, Integer tempType, Long platformId,
			List<Map<String, Object>> valueList, HttpServletRequest req) {

		//查询草稿表中是否存在缓存存在则提示清除缓存
		UserQuitTempDTO tempDTO = new UserQuitTempDTO();
		tempDTO.setCompanyId(companyId);
		tempDTO.setPlatformId(platformId);
		List<UserQuitTempDTO> userQuitTempAll = userQuitTempFacade.findUserQuitTempAll(tempDTO);
		if(EmptyUtil.isNotEmpty(userQuitTempAll)){
			return JsonResult.fail("请清除缓存");
		}


		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容

		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.leavingStaff.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);
		// 公司校验
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(companyId);
		companyDTO.setPlatformId(platformId);
		List<CompanyDTO> companyList = companyFacade.findCompanyAll(companyDTO);
		if (EmptyUtil.isEmpty(companyList) || companyList.size() != 1)
			return JsonResult.fail("公司不存在");

		CompanyDTO c = companyList.get(0);
		if (!c.getCompanyName().equals(valueList.get(0).get("CELL2").toString()))
			return JsonResult.fail("公司信息错误");

		// 模板类型
		String tmplName = ExcelTmplConstant.leavingStaff.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间解析错误");
		}

		// 序列号校验
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

		//headImportRecordsDTO.setGeneratedTime(date);
		headImportRecordsDTO.setTemplateType(tmplName);
		headImportRecordsDTO.setCompanyName(c.getCompanyName());
		headImportRecordsDTO.setFileSequenceNumber(sn);

		List<HeadImportRecordsDTO> importRecordList = userFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (!EmptyUtil.isEmpty(importRecordList))
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");

		// ************************************

		// 列表文件校验

		// 封装错误的集合
		List<PropblemReportRowVO<StaffQuitVO>> problemRep = new ArrayList<>();

		// 预览的集合
		List<StaffQuitVO> staffQuitVOList = new ArrayList<>();

		// 对员工编号创建一个集合
		Set<String> memberCodeSet = new HashSet<String>();
		Set<String> mailSet = new HashSet<String>();

		for (int i = 2; i < valueList.size(); i++) {

			// 内部不重复,空值校验
			StaffQuitVO vo = rowBeanQuit(valueList.get(i), problemRep, i, memberCodeSet, mailSet, companyId, platformId);

			staffQuitVOList.add(vo);
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {
			// 说明没有任何错误
			// 1.经表头信息储存到临时导入记录表,并且封装导入记录表的信息
			// 写导入记录表的数据
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setCompanyName(valueList.get(0).get("CELL2").toString());
			importRecordsDTO.setTemplateType(valueList.get(0).get("CELL4").toString());
			importRecordsDTO.setGeneratedTime(date);
			importRecordsDTO.setFileSequenceNumber(sn);

			// 2. 将数据封装到临时表里面
			List<UserQuitTempDTO> userQuitTempDTOList = new ArrayList<UserQuitTempDTO>();

			for (StaffQuitVO staffQuitVO : staffQuitVOList) {

				UserQuitTempDTO userQuitTempDTO = new UserQuitTempDTO();

				userQuitTempDTO.setUserId(staffQuitVO.getUserId());
				userQuitTempDTO.setCompanyId(companyId);
				userQuitTempDTO.setPlatformId(platformId);

				userQuitTempDTO.setName(staffQuitVO.getName());
				userQuitTempDTO.setMemberCode(staffQuitVO.getMemberCode());
				userQuitTempDTO.setMail(staffQuitVO.getMail());
				try {
					userQuitTempDTO.setQuitTime(sdf.parse(staffQuitVO.getQuitTime()));
					userQuitTempDTO.setInvalidTime(sdf.parse(staffQuitVO.getInvalidTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				userQuitTempDTOList.add(userQuitTempDTO);
			}

			Map<String, Object> data = userQuitTempFacade.insertImportRecordsAndUserQuitTempList(importRecordsDTO,
					userQuitTempDTOList);

			// 将表头信心返回
			data.put("headInfo", importRecordsDTO);

			data.put("overView", staffQuitVOList);

			return JsonResult.success("success", data);
		} else {

			// 说明有错误
			/*
			 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
			 */
			// 出现错误
			// 生成问题报告.xlsx上传至dfs
			// 封装返回值得集合
			//Map<String, Object> data = new HashMap<>();
			String repUrl = genAndUploadRepQuit(problemRep);
			return JsonResult.fail(repUrl, 169);
		}
	}

	@Override
	public Long assureImportQuitUser(Long companyId, Long platformId, Long importOrder, String userQuitOrderInfo) {

		return userFacade.assureImportQuitUser(companyId, platformId, importOrder, userQuitOrderInfo);

	}

	@Override
	public JsonResult<Map<String, Object>> importUserDeptWithTx(Long companyId, Integer tempType, Long platformId,
			List<Map<String, Object>> valueList, HttpServletRequest req) {

		//校验是否存在缓存,存在则提示清除缓存
		UserTempDTO tempDTO = new UserTempDTO();
		tempDTO.setType(Integer.valueOf(3));
		tempDTO.setCompanyId(companyId);
		tempDTO.setPlatformId(platformId);
		List<UserTempDTO> userTempAll = userFacade.findUserTempAll(tempDTO);
		if(EmptyUtil.isNotEmpty(userTempAll)){
			return JsonResult.fail("请清除缓存");
		}


		if (valueList.size() > 1002)
			return JsonResult.fail("单次导入数据不能超过1000条");

		// *************************************** 检查头文件及内容

		String err = ExcelHeadChecker.chechHeader(valueList, ExcelTmplConstant.deptStaff.getTmplType(), true);
		if (EmptyUtil.isNotBlank(err))
			return JsonResult.fail(err);
		// 公司校验
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(companyId);
		companyDTO.setPlatformId(platformId);
		List<CompanyDTO> companyList = companyFacade.findCompanyAll(companyDTO);
		if (EmptyUtil.isEmpty(companyList) || companyList.size() != 1)
			return JsonResult.fail("公司不存在");

		CompanyDTO c = companyList.get(0);

		if (!c.getCompanyName().equals(valueList.get(0).get("CELL2").toString()))
			return JsonResult.fail("公司信息错误");

		// 模板类型
		String tmplName = ExcelTmplConstant.deptStaff.getTmplName();
		if (!StringUtils.equals(tmplName, valueList.get(0).get("CELL4").toString())) {
			return JsonResult.fail("导入的文件类型错误，请检查后重新选择文件导入");
		}

		// 创建时间
		String createTime = valueList.get(0).get("CELL6").toString();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = sdf.parse(createTime);
		} catch (ParseException e) {
			return JsonResult.fail("创建时间解析错误");
		}

		// 序列号校验
		String sn = valueList.get(0).get("CELL8").toString();
		// 创建导入正式记录的信息
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

		//headImportRecordsDTO.setGeneratedTime(date);
		headImportRecordsDTO.setTemplateType(tmplName);
		headImportRecordsDTO.setCompanyName(c.getCompanyName());
		headImportRecordsDTO.setFileSequenceNumber(sn);

		List<HeadImportRecordsDTO> importRecordList = userFacade.findHeadImportRecordsAll(headImportRecordsDTO);
		if (!EmptyUtil.isEmpty(importRecordList))
			return JsonResult.fail("文件序列已存在,请确认是否已经导入");

		// ************************************

		// 列表文件校验
		// 封装错误的集合
		List<PropblemReportRowVO<UserTempVO>> problemRep = new ArrayList<>();

		// 预览的集合
		List<UserTempVO> userViewList = new ArrayList<>();

		// 查询公司所有的部门编号
		// 设置一个集合用来装部门的编号
		List<Long> departmentIdList = new ArrayList<>();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setCompanyId(companyId);
		List<DepartmentDTO> findDepartmentAll = departmentFacade.findDepartmentAll(departmentDTO);

		for (DepartmentDTO departmentDTO2 : findDepartmentAll) {
			departmentIdList.add(departmentDTO2.getId());
		}

		if (departmentIdList.size() == 0) {
			return JsonResult.fail("公司的组织架构已经失效,请稍后再试");
		}
		CacheUser userCache = RuntimeContext.cacheUser();
		Long userId = userCache.getId();// 用户id
		String userName = userCache.getName();// 用户名称
		String ip=HostUtils.getClientIP(req);
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
		}

		//用户编号重复性判断set
		Set<String> memberCodeSet = new HashSet<String>();

		//用户邮箱重复性判断set
		Set<String> mailSet = new HashSet<String>();

		for (int i = 2; i < valueList.size(); i++) {

			// 内部不重复,空值校验

			UserTempVO vo = rowBeanDept(valueList.get(i), problemRep, i, departmentIdList,memberCodeSet,mailSet, companyId);
			vo.setCreateUserid(userId);
			vo.setCreateUsername(userName);
			vo.setCreateUserip(ip);
			vo.setCreateUsermac(mac);
			vo.setType(Integer.valueOf(3));//设置类型为3.员工部门导入
			userViewList.add(vo);
		}

		// 校验完成,封装数据
		if (problemRep.size() == 0) {

			// 说明没有任何错误
			// 1.经表头信息储存到临时导入记录表,并且封装导入记录表的信息
			// 写导入记录表的数据
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setCompanyName(valueList.get(0).get("CELL2").toString());
			importRecordsDTO.setTemplateType(valueList.get(0).get("CELL4").toString());
			importRecordsDTO.setGeneratedTime(date);
			importRecordsDTO.setFileSequenceNumber(sn);

			// 2.将读到的数据存储在临时表里面。封装要预览的数据
			for (UserTempVO userTempVO : userViewList) {
				userTempVO.setCompanyId(companyId);
				userTempVO.setPlatformId(platformId);
			}
			Set<UserTempVO> userViews = userViewList.stream().collect(Collectors.toSet());

			List<UserTempDTO> userTempDTOList = UserTempConverter.toDTO(userViews);

			Map<String, Object> data = userFacade.insertuserFacadesAndUserViewList(importRecordsDTO, userTempDTOList);

			// 将表头信心返回
			data.put("headInfo", importRecordsDTO);
			data.put("overView", userViewList);

			return JsonResult.success("success", data);
		} else {

			// 说明有错误
			/*
			 * 结果集包含内容包含内容: 错误报告url(导入失败) 导入预览批次(导入成功用于查询预览)
			 */
			// 出现错误
			// 生成问题报告.xlsx上传至dfs
			// 封装返回值得集合
			//Map<String, Object> data = new HashMap<>();
			String repUrl = genAndUploadRepDept(problemRep);

			return JsonResult.fail(repUrl, 169);

		}
	}

	@Override
	public Long assureImportUserDept(Long companyId, Long platformId, Long importOrder, String userOrderInfo,int type) {
		return userFacade.assureImportDeptUser(companyId,platformId,importOrder,userOrderInfo,type);
	}

	@Override
	public JsonResult<Map<String, Object>> findAboutUserByUserId(Long userId, Long platformId) {
		JsonResult<Map<String, Object>> jsonResult = new JsonResult<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		UserDTO user = userFacade.findUserByID(userId);

		UserExtendDTO userExtend = userExtendFacade.findById(userId);

		UserWelfareDTO userWelfareDTO = new UserWelfareDTO();
		userWelfareDTO.setUserId(userId);
		List<UserWelfareDTO> findUserWelfareAll = userWelfareFacade.findUserWelfareAll(userWelfareDTO);

		DepartmentDTO departmentDTO = null;
		if (EmptyUtil.isNotEmpty(findUserWelfareAll)) {
			userWelfareDTO = findUserWelfareAll.get(0);

			departmentDTO = new DepartmentDTO();
			departmentDTO.setId(userWelfareDTO.getDepartmentId());
			departmentDTO = departmentFacade.findDepartmentById(departmentDTO);
		}

		map.put("id", user.getId());
		map.put("mobile", user.getMobile());
		map.put("mail", user.getMail());
		map.put("companyId", user.getCompanyId());
		map.put("sex", userExtend.getSex());
		map.put("name", userExtend.getName());
		map.put("birthday", userExtend.getBirthday());
		map.put("memberCode", userExtend.getMemberCode());
		map.put("isAdministrator", userExtend.getIsAdministrator());
		if(departmentDTO != null){
			String path = departmentDTO.getPath().substring(1);
			String[] departmentIdArray = path.split(",");
			Long[] departmentIdArr = new Long[departmentIdArray.length];
			for (int i = 0; i < departmentIdArray.length; i++) {
				departmentIdArr[i] = Long.valueOf(departmentIdArray[i]);
			}
			map.put("departmentIdArr", departmentIdArr);
			map.put("departmentName", departmentDTO.getDepartmentName());
		}

		jsonResult.setCode(0);
		jsonResult.setData(map);
		return jsonResult;
	}

	@Override
	public int findUserByMemberCode(Long companyId, String memberCode, Long platformId) {
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		userExtendDTO.setCompanyId(companyId);
		userExtendDTO.setMemberCode(memberCode);
		userExtendDTO.setPlatformId(platformId);
		List<UserExtendDTO> userAll = userExtendFacade.findAlluser(userExtendDTO);
		if (userAll == null) {
			return 0;
		}
		return userAll.size();

	}

	@Override
	public List<UserDTO> findUser(UserDTO userDTO) {
		return userFacade.findUser(userDTO);
	}

	/**
	 * 行转对象,进行空值校验和重复性校验 如果发现问题,存入报告列表中
	 *
	 * @param row
	 * @param problemRep
	 * @param rowNo
	 * @param memberCodeSet
	 * @param mailSet
	 * @param departmentIdList
	 * @return
	 */
	private UserTempVO rowBean(Map<String, Object> row, List<PropblemReportRowVO<UserTempVO>> problemRep, int rowNo,
			Set<String> memberCodeSet, Set<String> mailSet, List<Long> departmentIdList,Long companyId,Long platformId) {
		// 创建对象
		UserTempVO userTempVO = new UserTempVO();

		// 1列 员工编号
		String memberCode = (String) row.get("CELL1");

		if (EmptyUtil.isEmpty(memberCode)) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号为空", rowNo + 1, userTempVO));
		}else{
			if (!memberCodeSet.add(memberCode.toLowerCase())) {
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据员工编号重复", rowNo + 1, userTempVO));
			}else{
				if(memberCode.length() > 15){
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据员工编号最多15个字符", rowNo + 1, userTempVO));
				}
				//查询用户正式表员工编号
				UserExtendDTO userExtendDTO = new UserExtendDTO();
				userExtendDTO.setMemberCode(memberCode.toLowerCase());
				userExtendDTO.setCompanyId(companyId);
				List<UserExtendDTO> userExtendAll = userExtendFacade.userAll(userExtendDTO);
				if(EmptyUtil.isNotEmpty(userExtendAll)){
//					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "正式数据员工编号重复", rowNo + 1, userTempVO));
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号已存在", rowNo + 1, userTempVO));
				}
				UserTempDTO userTempDTO = new UserTempDTO();
				userTempDTO.setMemberCode(memberCode.toLowerCase());
				userTempDTO.setType(Integer.valueOf(2));
				userTempDTO.setPlatformId(platformId);
				userTempDTO.setCompanyId(companyId);
				List<UserTempDTO> userTempList = userFacade.findUserTempAll(userTempDTO);
				if(EmptyUtil.isNotEmpty(userTempList)){
//					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "预导入数据员工编号重复", rowNo + 1, userTempVO));
					throw new BusinessException("请清除缓存");
				}
			}
			userTempVO.setMemberCode(memberCode.toLowerCase());
		}

		// 2 邮箱
		String mail = (String) row.get("CELL2");
		if(EmptyUtil.isNotEmpty(mail)){
			if (!MailCheckUtils.checkEmail(mail)) {
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱格式不合法", rowNo + 1, userTempVO));
			}else{
				if (!mailSet.add(mail.toLowerCase())) {
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱重复", rowNo + 1, userTempVO));
				}else{
					// 查询用户正式表员工邮箱
					// 邮箱查重: 导入和新增的邮箱必须在数据库中不存在，或者存在但是邮箱对应的账户均为失效状态
					UserExtendDTO userExtendDTO = new UserExtendDTO();
					userExtendDTO.setMail(mail.toLowerCase());
					userExtendDTO.setAccountStatus(0);
					userExtendDTO.setPlatformId(platformId);
					List<UserExtendDTO> userExtendAll = userExtendFacade.userAll(userExtendDTO);
					if(EmptyUtil.isNotEmpty(userExtendAll)){
//						problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "正式数据邮箱重复", rowNo + 1, userTempVO));
						problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "邮箱已被使用", rowNo + 1, userTempVO));
					}
					UserTempDTO userTempDTO = new UserTempDTO();
					userTempDTO.setMail(mail.toLowerCase());
					userTempDTO.setCompanyId(companyId);
					List<UserTempDTO> userTempList = userFacade.findUserTempAll(userTempDTO);
					if(EmptyUtil.isNotEmpty(userTempList)){
						problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "预导入数据邮箱重复", rowNo + 1, userTempVO));
					}
				}
				userTempVO.setMail(mail.toLowerCase());
			}
		}else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱不不能为空", rowNo + 1, userTempVO));
		}



		// 3部门编号

		String departmentCode = (String) row.get("CELL3");
		if (EmptyUtil.isNotEmpty(departmentCode)) {
			if(!StringUtils.isNotFigure(departmentCode)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "部门编号必须为数字 ", rowNo + 1, userTempVO));
			}else{
				if (!departmentIdList.contains(Long.valueOf(departmentCode))) {
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "不存在这个部门编号！ ", rowNo + 1, userTempVO));
				}
			}
			userTempVO.setDepartmentId(Long.valueOf(departmentCode));
		}/*else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "部门编号不能为空！ ", rowNo + 1, userTempVO));
		}*/

		// 4列员工姓名
		String name = (String) row.get("CELL4");

		if (EmptyUtil.isNotEmpty(name)) {
			if(StringUtils.length(name) > 10){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "姓名不合法", rowNo + 1, userTempVO));
			}
			userTempVO.setName(name.trim());
		}else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "姓名不能为空", rowNo + 1, userTempVO));
		}


		// 5性别（0女1男）
		String sex = (String) row.get("CELL5");
		if(EmptyUtil.isNotEmpty(sex)){
			if(!StringUtils.isNotFigure(sex)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "性别必须为数字", rowNo + 1, userTempVO));
			}else{
				if(Integer.valueOf(sex) != 0 && Integer.valueOf(sex) != 1){
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "性别必须为0或1", rowNo + 1, userTempVO));
				}
				userTempVO.setSex(Integer.valueOf(sex));
			}
		}/*else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "性别不能为空", rowNo + 1, userTempVO));
		}*/

		// 6 出生年月日
		String birthday = (String) row.get("CELL6");
		if(EmptyUtil.isNotEmpty(birthday)){
			if(!StringUtils.isNotFigure(birthday) || birthday.length() != 8){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "生日格式错误（如：20180517）", rowNo + 1, userTempVO));
			}else{
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Date birthdayToDate = sdf.parse(birthday);
					userTempVO.setBirthday(birthdayToDate);
				} catch (ParseException e) {
					throw new BusinessException("出生年月日时间转换异常!");
				}
			}
		}/*else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "生日不能为空", rowNo + 1, userTempVO));
		}*/

		// 7 手机号码
		String mobile = (String) row.get("CELL7");
		// 慢有优平台需判断该手机是否存在一个有效账户
		if(platformId.equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
			if(EmptyUtil.isNotEmpty(mobile)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "手机号码不能为空", rowNo + 1, userTempVO));
			}

			// 根据平台公司及手机判断是否存在有效用户
			Boolean isExistUser = userFacade.findIsExistUser(mobile,companyId,platformId);
			if(isExistUser){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "该手机已存在有效用户", rowNo + 1, userTempVO));
			}
		}
		if(EmptyUtil.isNotEmpty(mobile)){
			if(!StringUtils.isNotFigure(mobile)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "手机号码必须为数字", rowNo + 1, userTempVO));
			}else if(mobile.length() > 11){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "手机号码必须不能大于11位", rowNo + 1, userTempVO));
			}else if(mobile.equals("2147483647")){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "手机号码填写栏不能设置为数字,改为文本", rowNo + 1, userTempVO));

			}
			userTempVO.setMobile(mobile);
		}
		return userTempVO;
	}

	private UserTempVO rowBeanRe(Map<String, Object> row, List<PropblemReportRowVO<UserTempVO>> problemRep, int rowNo,
							   Set<String> memberCodeSet, Set<String> mailSet, List<Long> departmentIdList,Long companyId,Long platformId) {
		// 创建对象
		UserTempVO userTempVO = new UserTempVO();

		// 1列 员工编号
		String memberCode = (String) row.get("CELL1");
		// 2 邮箱
		String mail = (String) row.get("CELL2");

		if (EmptyUtil.isEmpty(memberCode)) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号为空", rowNo + 1, userTempVO));
		}else{
			if (!memberCodeSet.add(memberCode.toLowerCase())) {
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据员工编号重复", rowNo + 1, userTempVO));
			}else{
				if(memberCode.length() > 15){
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据员工编号最多15个字符", rowNo + 1, userTempVO));
				}
				// 判断文件中同一员工的员工编号和邮箱是否同时与数据库（不包含失效邮箱）重复
				UserExtendDTO userExtendDTO = new UserExtendDTO();
				userExtendDTO.setMail(memberCode.toLowerCase());
				userExtendDTO.setAccountStatus(0);
				userExtendDTO.setPlatformId(platformId);
				List<UserExtendDTO> userExtendAll = userExtendFacade.userAll(userExtendDTO);
				if(EmptyUtil.isNotEmpty(userExtendAll)){
					for (UserExtendDTO dto :userExtendAll) {
						if (!mail.toLowerCase().equals(dto.getMail())) {
							problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号已存在", rowNo + 1, userTempVO));
						}
					}
				}
				UserTempDTO userTempDTO = new UserTempDTO();
				userTempDTO.setMemberCode(memberCode.toLowerCase());
				userTempDTO.setType(Integer.valueOf(2));
				userTempDTO.setPlatformId(platformId);
				userTempDTO.setCompanyId(companyId);
				List<UserTempDTO> userTempList = userFacade.findUserTempAll(userTempDTO);
				if(EmptyUtil.isNotEmpty(userTempList)){
					throw new BusinessException("请清除缓存");
				}
			}
			userTempVO.setMemberCode(memberCode.toLowerCase());
		}


		if(EmptyUtil.isNotEmpty(mail)){
			if (!MailCheckUtils.checkEmail(mail)) {
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱格式不合法", rowNo + 1, userTempVO));
			}else{
				if (!mailSet.add(mail.toLowerCase())) {
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱重复", rowNo + 1, userTempVO));
				}else{
					// 查询用户正式表员工邮箱
					// 判断文件中同一员工的员工编号和邮箱是否同时与数据库（不包含失效邮箱）重复
					UserExtendDTO userExtendDTO = new UserExtendDTO();
					userExtendDTO.setMail(mail.toLowerCase());
					userExtendDTO.setAccountStatus(0);
					userExtendDTO.setPlatformId(platformId);
					List<UserExtendDTO> userExtendAll = userExtendFacade.userAll(userExtendDTO);
					if(EmptyUtil.isNotEmpty(userExtendAll)){
						for (UserExtendDTO dto :userExtendAll) {
							if (!memberCode.toLowerCase().equals(dto.getMemberCode())) {
								problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "邮箱已被使用", rowNo + 1, userTempVO));
							}
						}
					}
					UserTempDTO userTempDTO = new UserTempDTO();
					userTempDTO.setMail(mail.toLowerCase());
					userTempDTO.setCompanyId(companyId);
					List<UserTempDTO> userTempList = userFacade.findUserTempAll(userTempDTO);
					if(EmptyUtil.isNotEmpty(userTempList)){
						problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "预导入数据邮箱重复", rowNo + 1, userTempVO));
					}
				}
				userTempVO.setMail(mail.toLowerCase());
			}
		}else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "导入数据邮箱不能为空", rowNo + 1, userTempVO));
		}



		// 3部门编号

		String departmentCode = (String) row.get("CELL3");
		if (EmptyUtil.isNotEmpty(departmentCode)) {
			if(!StringUtils.isNotFigure(departmentCode)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "部门编号必须为数字 ", rowNo + 1, userTempVO));
			}else{
				if (!departmentIdList.contains(Long.valueOf(departmentCode))) {
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "不存在这个部门编号！ ", rowNo + 1, userTempVO));
				}
			}
			userTempVO.setDepartmentId(Long.valueOf(departmentCode));
		}else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "部门编号不能为空！ ", rowNo + 1, userTempVO));
		}

		// 4列员工姓名
		String name = (String) row.get("CELL4");

		if (EmptyUtil.isNotEmpty(name)) {
			if(StringUtils.length(name) > 10){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "姓名不合法", rowNo + 1, userTempVO));
			}
			userTempVO.setName(name.trim());
		}else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "姓名不能为空", rowNo + 1, userTempVO));
		}


		// 5性别（0女1男）
		String sex = (String) row.get("CELL5");
		if(EmptyUtil.isNotEmpty(sex)){
			if(!StringUtils.isNotFigure(sex)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "性别必须为数字", rowNo + 1, userTempVO));
			}else{
				if(Integer.valueOf(sex) != 0 && Integer.valueOf(sex) != 1){
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "性别必须为0或1", rowNo + 1, userTempVO));
				}
				userTempVO.setSex(Integer.valueOf(sex));
			}
		}else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "性别不能为空", rowNo + 1, userTempVO));
		}

		// 6 出生年月日
		String birthday = (String) row.get("CELL6");
		if(EmptyUtil.isNotEmpty(birthday)){
			if(!StringUtils.isNotFigure(birthday) || birthday.length() != 8){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "生日格式错误（如：20180517）", rowNo + 1, userTempVO));
			}else{
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Date birthdayToDate = sdf.parse(birthday);
					userTempVO.setBirthday(birthdayToDate);
				} catch (ParseException e) {
					throw new BusinessException("出生年月日时间转换异常!");
				}
			}
		}else{
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "生日不能为空", rowNo + 1, userTempVO));
		}

		// 7 手机号码
		String mobile = (String) row.get("CELL7");
		// 慢有优平台需判断该手机是否存在一个有效账户
		if(platformId.equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
			if(EmptyUtil.isNotEmpty(mobile)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "手机号码不能为空", rowNo + 1, userTempVO));
			}

			// 根据平台公司及手机判断是否存在有效用户
			Boolean isExistUser = userFacade.findIsExistUser(mobile,companyId,platformId);
			if(isExistUser){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "该手机已存在有效用户", rowNo + 1, userTempVO));
			}
		}
		if(EmptyUtil.isNotEmpty(mobile)){
			if(!StringUtils.isNotFigure(mobile)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "手机号码必须为数字", rowNo + 1, userTempVO));
			}else if(mobile.length() > 11){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "手机号码必须不能大于11位", rowNo + 1, userTempVO));
			}
			userTempVO.setMobile(mobile);
		}
		return userTempVO;
	}

	/**
	 * 生成和上传问题报告
	 *
	 * @param problemRep
	 * @return
	 */
	private String genAndUploadRep(List<PropblemReportRowVO<UserTempVO>> problemRep) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("新员工导入报告");

		Row headLine = sh.createRow(0);
		headLine.createCell(0).setCellValue("*员工编号");
		headLine.createCell(1).setCellValue("*邮箱");
		headLine.createCell(2).setCellValue("*部门编号");
		headLine.createCell(3).setCellValue("*员工姓名");
		headLine.createCell(4).setCellValue("*性别（0女1男）");
		headLine.createCell(5).setCellValue("*生日（如:20180517）");
		headLine.createCell(6).setCellValue("手机号码");
		headLine.createCell(7).setCellValue("问题");
		headLine.createCell(8).setCellValue("行号");

		for (int i = 1; i < problemRep.size() + 1; i++) {
			PropblemReportRowVO<UserTempVO> vo = problemRep.get(i - 1);
			Row r = sh.createRow(i);
			UserTempVO lineMeta = vo.getLineMeta();
			if(EmptyUtil.isNotEmpty(lineMeta.getMemberCode())){
				r.createCell(0).setCellValue(lineMeta.getMemberCode());
			}
			if(EmptyUtil.isNotEmpty(lineMeta.getMail())){
				r.createCell(1).setCellValue(lineMeta.getMail());
			}
			if(EmptyUtil.isNotEmpty(lineMeta.getDepartmentId())){
				r.createCell(2).setCellValue(String.valueOf(lineMeta.getDepartmentId()));
			}
			if(EmptyUtil.isNotEmpty(lineMeta.getName())){
				r.createCell(3).setCellValue(lineMeta.getName());
			}
			if(EmptyUtil.isNotEmpty(lineMeta.getSex())){
				r.createCell(4).setCellValue(lineMeta.getSex());
			}
			if(EmptyUtil.isNotEmpty(lineMeta.getBirthday())){

				r.createCell(5).setCellValue(DateUtils.stampToDate(lineMeta.getBirthday(), "yyyyMMdd"));
			}
			if(EmptyUtil.isNotEmpty(lineMeta.getMobile())){
				r.createCell(6).setCellValue(lineMeta.getMobile());
			}
			if(EmptyUtil.isNotEmpty(vo.getProblem())){
				r.createCell(7).setCellValue(vo.getProblem());
			}
			if(EmptyUtil.isNotEmpty(vo.getLineNo())){
				r.createCell(8).setCellValue(String.valueOf(vo.getLineNo()));
			}
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("问题报告生成失败");
		}
		// 文件上传至文件服务器
		String filePath = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return filePath;
	}

	/**
	 * 行转对象,进行空值校验和重复性校验 如果发现问题,存入报告列表中
	 *
	 * @param row
	 * @param problemRep
	 * @param rowNo
	 * @param memberCodeSet

	 * @return
	 */
	private StaffQuitVO rowBeanQuit(Map<String, Object> row, List<PropblemReportRowVO<StaffQuitVO>> problemRep,
			int rowNo, Set<String> memberCodeSet, Set<String> mailSet, Long companyId, Long platformId) {
		// 创建对象
		StaffQuitVO staffQuitVO = new StaffQuitVO();

		// 1列 员工姓名
		String name = (String) row.get("CELL1");

		if (EmptyUtil.isNotEmpty(name)) {
			if(StringUtils.length(name) >= 10){
				problemRep
				.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "姓名不合法", rowNo + 1, staffQuitVO));
			}

		}

		staffQuitVO.setName(name);

		// 2列 员工编号

		String memberCode = (String) row.get("CELL2");

		if (EmptyUtil.isEmpty(memberCode)) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号为空", rowNo + 1, staffQuitVO));
		}else{
			if (!memberCodeSet.add(memberCode)) {
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号重复", rowNo + 1, staffQuitVO));
			}else{
				UserQuitTempDTO userQuitTempDTO = new UserQuitTempDTO();
				userQuitTempDTO.setMemberCode(memberCode.toLowerCase());
				userQuitTempDTO.setCompanyId(companyId);
				List<UserQuitTempDTO> userQuitTempList = userFacade.findUserQuitTempAll(userQuitTempDTO);
				if(EmptyUtil.isNotEmpty(userQuitTempList)){
//					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "预导入数据员工编号重复", rowNo + 1, userTempVO));
					throw new BusinessException("请清楚缓存");
				}
			}

			staffQuitVO.setMemberCode(memberCode.trim());
		}

		// 3 邮箱

		String mail = (String) row.get("CELL3");
		if (EmptyUtil.isEmpty(mail.toLowerCase()) || StringUtils.isNotEmail(mail.toLowerCase())) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "邮箱不合法", rowNo + 1, staffQuitVO));
		}else{
			if (!mailSet.add(mail.toLowerCase())) {
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "邮箱重复", rowNo + 1, staffQuitVO));
			}
			staffQuitVO.setMail(mail.toLowerCase());
		}

		if(EmptyUtil.isNotEmpty(name)){
			// 根据名字，编号，邮箱判断存不存在此人。
//			UserDTO userDTO1 = userFacade.findByMail(mail);

			UserExtendDTO userExtendDTO = new UserExtendDTO();
			userExtendDTO.setCompanyId(companyId);
			userExtendDTO.setName(name);
			userExtendDTO.setMemberCode(memberCode);
			userExtendDTO.setMail(mail);
			userExtendDTO.setPlatformId(platformId);

			List<UserExtendDTO> userExtendAll = userExtendFacade.userAll(userExtendDTO);

			if (EmptyUtil.isNotEmpty(userExtendAll) && userExtendAll.size() == 1) {
				// 设置userId
				staffQuitVO.setUserId(userExtendAll.get(0).getId());
				// 导入员工在职和账户状态校验,需为在职和有效
				if(0 != userExtendAll.get(0).getAccountStatus() || 1 != userExtendAll.get(0).getIsAvailable()){
					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "用户的在职状态或失效状态为已离职或已失效，请手动重置状态后重新导入", rowNo + 1,
							staffQuitVO));
				}

			} else {
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工姓名-编号-邮箱  不对应,请核对", rowNo + 1,
						staffQuitVO));
			}
		}



		// 4离职日期

		String quitDateStr = (String) row.get("CELL4");
		if (quitDateStr.length() != 8) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "离职时间格式错误！", rowNo + 1, staffQuitVO));
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date quitDate = null;

		try {
			quitDate = sdf.parse(quitDateStr);
		} catch (ParseException e) {

			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "离职时间解析异常！", rowNo + 1, staffQuitVO));

		}

		staffQuitVO.setQuitTime(quitDateStr);

		// 5资产回收日期

		String invalidDateStr = (String) row.get("CELL5");
		if (invalidDateStr.length() != 8) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "资产回收日期格式错误！", rowNo + 1, staffQuitVO));
		}

		Date invalidDate = null;

		try {
			invalidDate = sdf.parse(invalidDateStr);
		} catch (ParseException e) {

			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "资产回收日期解析异常！", rowNo + 1, staffQuitVO));

		}

		staffQuitVO.setInvalidTime(invalidDateStr);

		if(quitDate != null && invalidDate != null){
			// 校验离职时间和失效时间大小,需满足 离职时间<=失效时间
			if(quitDate.after(invalidDate)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "离职时间不得晚于资产回收日期！", rowNo + 1, staffQuitVO));
			}
		}

		return staffQuitVO;
	}

	/**
	 * 生成和上传问题报告
	 *
	 * @param problemRep
	 * @return
	 */
	private String genAndUploadRepQuit(List<PropblemReportRowVO<StaffQuitVO>> problemRep) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("离职员工导入报告");

		Row headLine = sh.createRow(0);
		headLine.createCell(0).setCellValue("员工姓名");
		headLine.createCell(1).setCellValue("员工编号");
		headLine.createCell(2).setCellValue("邮箱");
		headLine.createCell(3).setCellValue("离职日期");
		headLine.createCell(4).setCellValue("失效日期");
		headLine.createCell(5).setCellValue("问题");
		headLine.createCell(6).setCellValue("行号");

		for (int i = 1; i < problemRep.size() + 1; i++) {
			PropblemReportRowVO<StaffQuitVO> vo = problemRep.get(i - 1);
			Row r = sh.createRow(i);
			StaffQuitVO lineMeta = vo.getLineMeta();
			r.createCell(0).setCellValue(lineMeta.getName());
			r.createCell(1).setCellValue(lineMeta.getMemberCode());
			r.createCell(2).setCellValue(lineMeta.getMail());
			r.createCell(3).setCellValue(lineMeta.getQuitTime());
			r.createCell(3).setCellValue(lineMeta.getInvalidTime());
			r.createCell(4).setCellValue(vo.getProblem());
			r.createCell(5).setCellValue(String.valueOf(vo.getLineNo()));
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("问题报告生成失败");
		}
		// 文件上传至文件服务器
		String filePath = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return filePath;
	}

	private UserTempVO rowBeanDept(Map<String, Object> row, List<PropblemReportRowVO<UserTempVO>> problemRep, int rowNo,
			List<Long> departmentIdList,Set<String> memberCodeSet,Set<String> mailSet, Long companyId) {

		// 创建对象
		UserTempVO userTempVO = new UserTempVO();

		// 1列员工姓名
		/*String name = (String) row.get("CELL1");

		if (EmptyUtil.isEmpty(name) || StringUtils.length(name) >= 10) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "姓名称为空或者不合法", rowNo + 1, userTempVO));
		}
		userTempVO.setName(name.trim());*/

		// 2列 员工编号

		String memberCode = (String) row.get("CELL1");

		if (EmptyUtil.isEmpty(memberCode)) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号为空", rowNo + 1, userTempVO));
		}else{
			if(!memberCodeSet.add(memberCode)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工编号重复", rowNo + 1, userTempVO));
			}else{
				UserTempDTO userTempDTO = new UserTempDTO();
				userTempDTO.setMemberCode(memberCode.toLowerCase());
				userTempDTO.setCompanyId(companyId);
				userTempDTO.setType(Integer.valueOf(3));
				List<UserTempDTO> userTempList = userFacade.findUserTempAll(userTempDTO);
				if(EmptyUtil.isNotEmpty(userTempList)){
//					problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "预导入数据员工编号重复", rowNo + 1, userTempVO));
					throw new BusinessException("请清楚缓存");
				}
			}
			userTempVO.setMemberCode(memberCode.trim());
		}



		// 3 邮箱
		String mail = (String) row.get("CELL2");
		if (EmptyUtil.isEmpty(mail) || StringUtils.isNotEmail(mail)) {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "邮箱不合法", rowNo + 1, userTempVO));
		}else{
			if(!mailSet.add(mail)){
				problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "员工邮箱重复", rowNo + 1, userTempVO));
			}
			userTempVO.setMail(mail.trim());
		}


		// 校验邮箱和员工编号
		UserDTO userDTO = new UserDTO();
		userDTO.setMail(mail.trim());
		userDTO.setCompanyId(companyId);
		List<UserDTO> userList = userFacade.findUser(userDTO);

		if (!EmptyUtil.isEmpty(userList)) {
			UserExtendDTO findById = userExtendFacade.findById(userList.get(0).getId());
			if (!findById.getMemberCode().equals(memberCode.trim())) {
				problemRep.add(
						new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "此员工不存在，请核对", rowNo + 1, userTempVO));
			}
		} else {
			problemRep.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "此员工不存在，请核对", rowNo + 1, userTempVO));
		}

		// 4 部门编号

		String departmentCode = (String) row.get("CELL3");

		if (EmptyUtil.isEmpty(departmentCode)) {
			problemRep.add(
					new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "部门编号必须是数字且不能为空！", rowNo + 1, userTempVO));
		}else{
			if (!departmentIdList.contains(Long.valueOf(departmentCode))) {
				problemRep
						.add(new PropblemReportRowVO<>("原表第" + (rowNo + 1) + "行：" + "不存在这个部门编号！ ", rowNo + 1, userTempVO));
			}

			userTempVO.setDepartmentId(Long.valueOf(departmentCode));
		}


		return userTempVO;

	}

	/**
	 * 生成和上传问题报告
	 *
	 * @param problemRep
	 * @return
	 */
	private String genAndUploadRepDept(List<PropblemReportRowVO<UserTempVO>> problemRep) {
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("员工部门导入报告");

		Row headLine = sh.createRow(0);
		headLine.createCell(0).setCellValue("员工姓名");
		headLine.createCell(1).setCellValue("员工编号");
		headLine.createCell(2).setCellValue("邮箱");
		headLine.createCell(3).setCellValue("部门编号");
		headLine.createCell(4).setCellValue("问题");
		headLine.createCell(5).setCellValue("行号");

		for (int i = 1; i < problemRep.size() + 1; i++) {
			PropblemReportRowVO<UserTempVO> vo = problemRep.get(i - 1);
			Row r = sh.createRow(i);
			UserTempVO lineMeta = vo.getLineMeta();
			r.createCell(0).setCellValue(lineMeta.getName());
			r.createCell(1).setCellValue(lineMeta.getMemberCode());
			r.createCell(2).setCellValue(lineMeta.getMail());
			if(EmptyUtil.isNotEmpty(lineMeta.getDepartmentId())){
				r.createCell(3).setCellValue(String.valueOf(lineMeta.getDepartmentId()));
			}
			r.createCell(4).setCellValue(vo.getProblem());
			r.createCell(5).setCellValue(String.valueOf(vo.getLineNo()));
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("问题报告生成失败");
		}
		// 文件上传至文件服务器
		String filePath = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return filePath;
	}

//	@Override
//	public boolean verificationPaymentPassword(Long userId, String paymentPassword) {
//		// 获取用户1分钟支付次数
//		Integer paymentPasswordInputNumber = (Integer) cache
//				.get(CacheKeyConstant.PAYMENTPASSWORD_INPUT_NUMBER_KEY + userId);
//		if (EmptyUtil.isEmpty(paymentPasswordInputNumber)) {
//			// 为空记录用户1分钟内输入1次
//			cache.set(CacheKeyConstant.PAYMENTPASSWORD_INPUT_NUMBER_KEY + userId, 60, 1);
//		} else {
//			if (paymentPasswordInputNumber > 3) {
//				// 删除用户支付次数缓存
//				cache.del(CacheKeyConstant.PAYMENTPASSWORD_INPUT_NUMBER_KEY + userId);
//				throw new BusinessException(BusinessExceptionConstant.PAYMENTPASSWORD_INPUT_NUMBER_BIG_THREE,
//						"支付密码输入超过3次,请重新输入");
//			}
//			// 把用户支付密码输入次数加一记录
//			cache.set(CacheKeyConstant.PAYMENTPASSWORD_INPUT_NUMBER_KEY + userId, 60, paymentPasswordInputNumber + 1);
//		}
//		return userFacade.verificationPaymentPassword(userId, paymentPassword);
//	}

	@Override
	public JsonResult<Map<String, Object>> userPayInfo(Long userId) {
		UserDTO user = userFacade.findUserByID(userId);
		if (user == null)
			return JsonResult.fail("用户不存在");
		Map<String,Object> resultMap = new HashMap<>();

		String ppwd = user.getPaymentCode();
		resultMap.put("havePpwd", EmptyUtil.isNotBlank(ppwd));
		setCompanyConfigDTO(user,resultMap);
		return JsonResult.success(resultMap);
	}

	private void setCompanyConfigDTO(UserDTO user,Map<String,Object> resultMap) {
		boolean ifNeedPayPwd = true;
		boolean isSinglePay = false;
		if(user !=null && user.getCompanyId()!=null) {
			List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigs(user.getCompanyId());
			for(CompanyConfigDTO config : configs) {
				if(config.getKey().equalsIgnoreCase("payment.password.valid")) {
					String isCheckPaymentPassword = config.getValue();
					ifNeedPayPwd = !Objects.equals(isCheckPaymentPassword,"0");

				}
				if(config.getKey().equalsIgnoreCase("is.single.pay")) {
					String isCheckSinglePay = config.getValue();
					isSinglePay = Objects.equals(isCheckSinglePay,"1");
				}
			}
		}
		resultMap.put("ifNeedPayPwd", ifNeedPayPwd);
		resultMap.put("isSinglePay", isSinglePay);
	}

//	/**
//	 * 用户确认支付并冻结福币
//	 *
//	 * @param userId
//	 * @param paymentPassword
//	 * @param orderCode
//	 * @return
//	 */
//	@Override
//	public Integer verificationPaymentPasswordAndFoscoinAccount(Long userId, String paymentPassword, String orderCode,
//			Long platformId) {
//		//支付密码校验
//		boolean b = verificationPaymentPassword(userId, paymentPassword);
//		Properties payConfig = new Properties();
//		try {
//			String path = System.getProperty("global.config.path")
//					+ "/back-user/back-user-business/HttpClient.properties";
//			FileInputStream inStream = new FileInputStream(new File(path));
//			payConfig = new Properties();
//			payConfig.load(inStream);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (b) {
//			String httpOrgCreateTest = payConfig.getProperty("HttpClient.url.ip")
//					+ "/back-account-web/account/account/foscoinAccountDeduct.do";
//			Map<String, String> createMap = new HashMap<String, String>();
//			createMap.put("orderCode", orderCode);
//			createMap.put("userId", String.valueOf(userId));
//			//createMap.put("userId", String.valueOf(userId));
//			createMap.put("platformId", String.valueOf(platformId));
//			String httpOrgCreateTestRtn = HttpClientUtil.doPost(httpOrgCreateTest, createMap, "utf-8");
//			JSONObject createJsonObject = JSON.parseObject(httpOrgCreateTestRtn);
//			if (createJsonObject.getString("code").equals("-1")) {
//				throw new BusinessException(createJsonObject.getString("error"));
//			}
//			return Integer.valueOf(createJsonObject.getString("data"));
//		}
//		return null;
//	}

	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	@Override
	public int updateUserPassword(Long userId, String password,String originalPassword) {
		return userFacade.updateUserPassword(userId, password,originalPassword);
	}

	@Override
	public JsonResult<Map<String, Object>> insurance3rdLogin(Long userId,Integer type) {
		if(type==null) {
			return JsonResult.fail("外链类型错误");
		}
		//检查用户在第三方保险平台是否已有注册信息
		InsuranceLoginDTO il=userFacade.queryInsuranceLoginByUserId(userId);
		//若无,注册,保存信息
		if(il==null) {
			//生成用户名
			String userName=InsuranceLoginUtil.genUserNameById(userId);
			//生成密码
			String password=InsuranceLoginUtil.genPassword();
			//注册
			String registerRes=InsuranceLoginUtil.register(userName, password);
			JSONObject regJSON=JSONObject.parseObject(registerRes);
			int code=regJSON.getIntValue("code");
			if(code!=InsuranceLoginUtil.SUCCESS) {
				return JsonResult.fail("请求异常，请稍后再试");
			}
			String cardNo=regJSON.getJSONObject("data").getString("membercode");
			//保存至数据库
			il=new InsuranceLoginDTO();
			il.setCardno(cardNo);//第三方登陆接口返回的卡号
			il.setPassword(password);//生成的密码
			il.setLoginName(userName);//生成的用户名
			il.setUserId(userId);//用户id
			il.setLoginTimes(0);//初始化登录次数
			Long ilId=userFacade.insertInsuranceInfo(il);
			il.setId(ilId);
		}
		//登陆,保存登陆次数,最后一次登陆时间
		String loginRes=InsuranceLoginUtil.login(il.getLoginName(), il.getPassword());
		JSONObject loginJSON=JSONObject.parseObject(loginRes);
		int code=loginJSON.getIntValue("code");
		if(code!=InsuranceLoginUtil.SUCCESS) {
			return JsonResult.fail("请求异常，请稍后再试");
		}
		JSONObject data=loginJSON.getJSONObject("data");
		InsuranceLoginDTO ilUpdateDTO=new InsuranceLoginDTO();
		ilUpdateDTO.setId(il.getId());
		ilUpdateDTO.setDccuid(data.getString("dccuid"));
		ilUpdateDTO.setDccucode(data.getString("dccucode"));
		ilUpdateDTO.setMemberid(data.getString("memberid"));
		ilUpdateDTO.setOpenid(data.getString("openid"));
		ilUpdateDTO.setUserno(data.getString("userno"));
		ilUpdateDTO.setMid(data.getLongValue("mid"));
		ilUpdateDTO.setVsId(data.getString("vs_id"));
		String memberCode=data.getString("membercode");
		ilUpdateDTO.setMembercode(memberCode);
		ilUpdateDTO.setNickname(data.getString("nickname"));
		ilUpdateDTO.setShopname(data.getString("shopname"));
		ilUpdateDTO.setHeaderurl(data.getString("headerurl"));
		userFacade.insuranceLogin(ilUpdateDTO);
		//按照第三方接口的返回登陆链接url至客户端
		String autoLoginUrl=data.getString("autologinurl");
		Map<String,Object> res=new HashMap<>();
		res.put("url", autoLoginUrl+(type==1?InsuranceLoginUtil.INDEX_REF:InsuranceLoginUtil.ORDER_REF));
		return JsonResult.success(res);
	}

	/**
	 * 拼接保险访问url
	 * @param autoLoginUrl

	 * @param memberCode
	 * @return
	 */
	private String concatUrl(String autoLoginUrl, String ref, String memberCode) {
		return autoLoginUrl+"?code="+memberCode+"&p=twin&ref="+ref;
	}
	/**
	 * 根据用户Id集合解绑用户

	 * @return
	 */
	@Override
	public Integer unbindByUserIds(List<Long> userIdList,Long platformId) {
		// TODO Auto-generated method stub
		return userFacade.unbindByUserIds(userIdList,platformId);
	}
	/**
	 * 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
	 * @param userId
	 * @param mobile
	 * @return
	 */
	@Override
	public UserVO findByUserIdMobile(Long userId, String mobile,Long platformId) {
		UserDTO userDTO = userFacade.findByUserIdMobile(userId, mobile,platformId);
		return UserConverter.toVO(userDTO);
	}

	@Override
	public void saveUserPermission(UserDTO user, Long roleId) {
		// 保存用户平台关系表
		UserPlatformDTO userPlatformDTO = new UserPlatformDTO();
		userPlatformDTO.setUserId(user.getId());
		userPlatformDTO.setPlatformId(user.getPlatformId());
		userPlatformFacade.save(userPlatformDTO);

		// 保存角色关系表
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setUserId(user.getId());
		userRoleDTO.setRoleId(roleId);
		userRoleFacade.save(userRoleDTO);
	}

	@Override
	public JsonResult<Map<String, Object>> registerForAdminWithTx(String mail, String code, String password,
			String repassword, HttpServletRequest req, HttpServletResponse resp) {

		if (EmptyUtil.isEmpty(mail))
			return JsonResult.fail("请输入邮箱");
		if (StringUtils.isNotEmail(mail))
			return JsonResult.fail("请输入正确的邮箱");

		// 根据邮箱查询有效用户
		UserExtendDTO userExtendDTO_ = new UserExtendDTO();
		userExtendDTO_.setMail(mail.toLowerCase());
		userExtendDTO_.setAccountStatus(0);
		List<UserExtendDTO> UserExtendDTOList = userExtendFacade.queryUserByCondition(userExtendDTO_);

		if (EmptyUtil.isEmpty(UserExtendDTOList))
			return JsonResult.fail("邮箱不存在");

		if (UserExtendDTOList.size() != 1)
			return JsonResult.fail("该邮箱存在重复的有效邮箱");

    	// 邮箱已注册
		if(UserExtendDTOList.get(0).getStatus() == 1)
			return JsonResult.fail("邮箱已注册，请使用邮箱登录");

		UserExtendDTO userExtendDTO = userExtendFacade.findById(UserExtendDTOList.get(0).getId());
		if (userExtendDTO.getIsAdministrator() == null
				|| (userExtendDTO.getIsAdministrator() != null && userExtendDTO.getIsAdministrator() != 1)) {
			return JsonResult.fail("该邮箱无注册权限");
		}

		if (EmptyUtil.isEmpty(code))
			return JsonResult.fail("请输入验证码");
		if (!MD5Support.MD5(code, mail.toLowerCase()).equals((String)cache.get(mail.toLowerCase())))
			return JsonResult.fail("验证码错误");		// 验证码错误或已失效

		// 每个验证码只能使用一次
		cache.del(mail.toLowerCase());

		if (EmptyUtil.isEmpty(password))
			return JsonResult.fail("请输入密码");
		if (EmptyUtil.isEmpty(repassword))
			return JsonResult.fail("请输入确认密码");
		if (!StringUtils.validContainSixCharAndNum(password))
			return JsonResult.fail("密码长度不少于6位，至少包含字母、数字两种类型");
		if (!password.equals(repassword))
			return JsonResult.fail("两次输入的密码不一致");

		// 注册管理员用户
		// 1)设置密码和salt
		UserDTO user = new UserDTO();
		String salt = SaltUtils.getRandomSalt();
		user.setId(userExtendDTO.getId());
		user.setPlatformId(7L);
		user.setSalt(salt);
		user.setPassword(MD5Support.MD5(MD5.encode(password), salt));
		userFacade.updateUserInfo(user);
		// 2)设置用户状态为已注册
		UserExtendDTO userExtend = new UserExtendDTO();
		userExtend.setId(userExtendDTO.getId());
		userExtend.setStatus(1);
		userExtend.setChannelId(-1L);	// 渠道默认为无渠道
		userExtendFacade.updateUserExtendInfoWithTx(userExtend);
		// 3)赋予用户会员权限
		this.saveUserPermission(user, BusinessConstant.PLATFORM7_USER_ROLEID);

		return JsonResult.success(null);

	}

	@Override
	public JsonResult<User> manageLogin(UserVO user, HttpServletRequest req, HttpServletResponse resp) {

        //checkUserName(user);
        JsonResult<CacheUser> rt = loginManage.manageLogin(user);
        int code = rt.getCode();// code 等于 0表示登陆成功

        if (code == 0) { // 登陆成功
        	UserCookieVO userCookie = null;
        	CacheUser cacheUser = (CacheUser) rt.getData();

        	// 写缓存 cookie
        	 userCookie = ssoClientUtil.login(cacheUser, req, resp);

        	// 写登陆日志
        	userLoginManage.insertLoginLogWithTx(rt, req,user.getLoginType(),user.getKeyMessage(),user.getStoreId());

        	User userfz = new User();
        	userfz.setCookieValue(userCookie.getCookieValue());
        	userfz.setName(rt.getData().getName());
        	userfz.setId(rt.getData().getId());
        	// 设置当前用户是否是超级管理员
        	userfz.setIsSuperAdministrator(rt.getData().getId().equals(1L) ? 1 : 0);

        	return JsonResult.success(userfz);
        }else{
        	userLoginManage.insertLoginExceptionLogWithTx(code,user, req);
        	return JsonResult.fail(rt.getError(),code);
        }

	}
	public JsonResult<User> login(UserVO user, HttpServletRequest req, HttpServletResponse resp) {
         checkUserName(user);
         JsonResult<CacheUser> rt = loginManage.login(user);
         int code = rt.getCode();// code 等于 0表示登陆成功

         if (code == 0) { // 登陆成功
         	UserCookieVO userCookie = null;
         	CacheUser cacheUser = (CacheUser) rt.getData();

         	// 写缓存 cookie
         	 userCookie = ssoClientUtil.login(cacheUser, req, resp);

         	// 写登陆日志
         	userLoginManage.insertLoginLogWithTx(rt, req,user.getLoginType(),user.getKeyMessage(),user.getStoreId());

         	User userfz = new User();
         	userfz.setCookieValue(userCookie.getCookieValue());
         	userfz.setName(rt.getData().getName());
         	userfz.setId(rt.getData().getId());
         	// 设置当前用户是否是超级管理员
         	userfz.setIsSuperAdministrator(rt.getData().getId().equals(1L) ? 1 : 0);

         	return JsonResult.success(userfz);
         }else{
         	userLoginManage.insertLoginExceptionLogWithTx(code,user, req);
         	return JsonResult.fail(rt.getError(),code);
         }
	}

	private void checkUserName(UserVO user) {

			String name = user.getName();
			if(StringUtils.isEmpty(name)){
				throw new BusinessException("登录用户名不能为空");
			}
			if (name.contains("@")) {// 邮箱登陆
				//所有字母转小写
				name = name.toLowerCase();
				user.setMail(name);

			} else if (StringUtils.validMobile(name)) {// 手机号登陆
				user.setMobile(name);
			} else {// 用户名登陆
				user.setLoginName(name);
			}

		}
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	@Override
	public UserDTO findByWeiXinOpenId(String openId, Long platformId) {
		// TODO Auto-generated method stub
		return userFacade.findByWeiXinOpenId(openId, platformId);
	}

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	@Override
	public UserDTO findLatestLoginByWeiXinOpenId(String openId, Long platformId) {
		return userFacade.findLatestLoginByWeiXinOpenId(openId, platformId);
	}
	/**
	 * 根据手机号平台id查询用户信息
	 */
	@Override
	public List<UserDTO> findListByManage(String mobile, Long platformId) {
		// TODO Auto-generated method stub
		return userFacade.findListByManage(mobile, platformId);
	}
	/**
	 * 用户根据手机号设置支付密码
	 */
	@Override
	public int setPaymentPasswordByMobile(String mobile, String verificationCode, String password,
			String confirmPassword,Long userId, Long platformId) {
		if(EmptyUtil.isEmpty(verificationCode))
			throw new BusinessException("请输入验证码");
		// 根据手机号获取放入缓存中的验证码
		String redisVerificationCode = (String) cache.get(CacheKeyConstant.UPDATE_PAYMENT_PASSWORD_MOBILE_KEY + mobile);
		if (EmptyUtil.isEmpty(redisVerificationCode)) {
			throw new BusinessException("验证码过期、请重新发送");
		}
		if (!redisVerificationCode.equals(verificationCode)) {
			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG, "验证码错误");
		}
		if (!confirmPassword.equals(password)) {
			throw new BusinessException(BusinessExceptionConstant.USER_NEW_PASSWORD_NO_MATCH, "密码不一致");
		}
		UserDTO userDTO = userFacade.findUserByID(userId);
		// 使用一次之后直接删除
    	cache.del(CacheKeyConstant.UPDATE_PAYMENT_PASSWORD_MOBILE_KEY + mobile);
		// 根据用户手机号设置支付密码
		return userFacade.setPaymentPassword(userDTO.getId(),userDTO.getMail(), password, platformId);
	}

	@Override
	public UserVO findAdminUserByManage(String mobile, Long platformId) {
		UserDTO userDTO = userFacade.findAdminUserByManage(mobile, platformId);
		return UserConverter.toVO(userDTO);
	}

	/**
	 * 自动领取注册优惠券
	 * 注册店铺为慢有优总店可以领取慢有优总店的优惠券，为门店可以领取门店加慢有优总店的优惠券
	 * @param userVO
	 * @return
	 */
	public void autoReceiveRegisterCoupon(UserVO userVO) {
		Long storeId = null;
		if (EmptyUtil.isNotEmpty(userVO.getRegisterStoreId())) {
			storeId = Long.valueOf(userVO.getRegisterStoreId());
			autoReceiveRegisterCouponByStore(userVO, storeId);
		}
		if (!PlatformKeyConstant.MYY_ROOT_STORE_ID.equals(storeId)&&PlatformKeyConstant.MYY_PLATFORM_ID.equals(userVO.getPlatformId())) {
			//默认发放慢有优总店关联优惠券
			autoReceiveRegisterCouponByStore(userVO, PlatformKeyConstant.MYY_ROOT_STORE_ID);
		}else if(!PlatformKeyConstant.FGJ_ROOT_STORE_ID.equals(storeId)&&PlatformKeyConstant.FGJ_PLATFORM_ID.equals(userVO.getPlatformId())){
			autoReceiveRegisterCouponByStore(userVO, PlatformKeyConstant.FGJ_ROOT_STORE_ID);
		}
	}

	/**
	 * 清除员工导入缓存
     * @param companyId
     * @param type
     * @param platformId
     */
	@Override
	public void clearImportMemoryBackStage(Long companyId, Integer type, Long platformId) {
		//清除草稿表
		switch (type){
			case 3:
			case 2:
				//新员工导入,员工部门导入
				//校验是否存在缓存
				UserTempDTO userTempDTO = new UserTempDTO();
				userTempDTO.setType(type);
				userTempDTO.setCompanyId(companyId);
				userTempDTO.setPlatformId(platformId);
				List<UserTempDTO> userTempAll = userFacade.findUserTempAll(userTempDTO);
				if(EmptyUtil.isEmpty(userTempAll)){
					//没有缓存直接返回
					break;
				}
				//有缓存则清除
				userFacade.deleteUserTempByParamsWithTx(userTempDTO);
				break;
			case 1:
				//离职员工导入
				UserQuitTempDTO quitTempDTO = new UserQuitTempDTO();
				quitTempDTO.setCompanyId(companyId);
				quitTempDTO.setPlatformId(platformId);
				List<UserQuitTempDTO> userQuitTempAll = userQuitTempFacade.findUserQuitTempAll(quitTempDTO);
				if(EmptyUtil.isEmpty(userQuitTempAll)){
					break;
				}
				userQuitTempFacade.deleteUserQuitTempByParamsWithTx(quitTempDTO);
				break;
		}
	}

	@Override
	public List<UserVO> findUserByMobile(String mobile, Long platformId) {
		List<UserDTO> list = userFacade.findUserByMobile(mobile,platformId);
		return UserConverter.toVO(list);

	}

	@Override
	public UserVO registDefaultCompanyUserByMobile(String mobile,String passwd,String wxOpenId, Long platformId,Long companyId) {
		return userFacade.registDefaultCompanyUserByMobile(mobile,passwd, wxOpenId, platformId,companyId);
	}

	@Override
	public JsonResult<User> userLogin(UserVO user,String wxOpenId, HttpServletRequest req, HttpServletResponse resp) {
		logger.info("登陆方法被调用,用户名称：" + user.getName());
		String deviceId=req.getHeader("deviceId");
		if(EmptyUtil.isEmpty(deviceId))
			return JsonResult.fail("设备编号参数缺失");
		String clientId_=req.getHeader("clientId");
		if(EmptyUtil.isEmpty(clientId_))
			return JsonResult.fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);
		//所有字母转小写
		//所有字母转小写
		if(EmptyUtil.isNotEmpty(user.getName())){
			user.setName(user.getName().toLowerCase());
		}
		checkUserName(user);
		JsonResult<CacheUser> rt = loginManage.mobileLogin(user,deviceId,clientId);
		int code = rt.getCode();// code 等于 0表示登陆成功

		if (code == 0) { // 登陆成功
			UserCookieVO userCookie = null;
			CacheUser cacheUser = (CacheUser) rt.getData();
			// 写缓存 cookie
			userCookie = ssoClientUtil.login(cacheUser, req, resp);

			//对于会员，使ut和用户信息在cache中进行关联
			loginManage.saveUttoCache(user,userCookie,deviceId,clientId);
			// 写登陆cookie表
			userCookie.setUserId(cacheUser.getId());
			userCookie.setCompanyId(cacheUser.getCompanyId());
			userCookie.setPlatformId(cacheUser.getPlatformId());
			userCookieManage.saveUserCookieWithTx(userCookie,req);

			saveWxOpenId(cacheUser,wxOpenId);

			// 写登陆日志
			userLoginManage.insertLoginLogWithTx(rt, req, UserLoginConstant.LOGIN_TYPE_NORMAL.getStatus(),user.getKeyMessage(),user.getStoreId());

			User userfz = new User();
			userfz.setCookieValue(userCookie.getCookieValue());
			userfz.setName(rt.getData().getName());
			userfz.setId(rt.getData().getId());

			return JsonResult.success(userfz);
		}else{
			userLoginManage.insertLoginExceptionLogWithTx(code,user, req);
			return JsonResult.fail(rt.getError(),code);
		}
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public JsonResult<User> registerAndLoginDefaultCompanyUserByMobile(String mobile, String passwd, String wxOpenId,
															 Long platformId, Long companyId,
															 HttpServletRequest req, HttpServletResponse resp) {
		UserVO registVo = registDefaultCompanyUserByMobile(mobile,passwd, wxOpenId, platformId,companyId);

		//登陆
		if(EmptyUtil.isNotEmpty(registVo)){
			UserVO user = new UserVO();
			user.setName(mobile);
			user.setPassword(passwd);
			user.setPlatformId(platformId);
			user.setWeiXinOpenId(wxOpenId);
			JsonResult<User> result = this.userLogin(user, wxOpenId,req, resp);
			// 使用一次之后直接删除
			cache.del(CacheKeyConstant.MOBILE_LOGIN_KEY + mobile);
			return result;
		}else{
			throw new BusinessException("用户注册失败");
		}
	}

	private void saveWxOpenId(CacheUser cacheUser,String wxOpenId){
		List<WxOpenidDTO> useropenids = wxOpenIdManage.findUserOpenIds(cacheUser.getId(), wxOpenId);
		boolean hasOpenId = false;
		Long openId = null;
		if (EmptyUtil.isEmpty(useropenids)) {
			List<WxOpenidDTO> openids = wxOpenIdManage.findByOpenId(wxOpenId);
			for (WxOpenidDTO openid : openids) {
				if (!hasOpenId && openid.getUserId() == null) {
					hasOpenId = true;
					openId = openid.getId();
				}
			}
			if (hasOpenId) {
				wxOpenIdManage.updateUserId(openId, cacheUser.getId(), cacheUser.getCompanyId());
			} else {
				if (openids != null && openids.size() > 0) {
					String appid = openids.get(0).getWxAppid();
					WxOpenidDTO dto = new WxOpenidDTO();

					dto.setCompanyId(cacheUser.getCompanyId());
					dto.setCreateTime(new Date());
					dto.setUserId(cacheUser.getId());
					dto.setWxOpenid(wxOpenId);
					dto.setWxAppid(appid);
					wxOpenIdManage.insert(dto);
				}
			}
		}
	}
	@Override
	public int activateUserByIds(List<Long> userIds) {
		return userFacade.activateUserByIds(userIds);
	}

	@Override
	public void openUserAccountWithTx(Long userId, Integer type,Long platformId) {
		UserDTO userDTO=userFacade.findUserByID(userId);
		if (Objects.isNull(userDTO)){
			throw new BusinessException("用户不存在");
		}
		userDTO.setPlatformId(platformId);
		UserAccountDTO userAccountDTO=userFacade.queryUserAccountByUserIdAndType(userId,type);
		if (Objects.nonNull(userAccountDTO)){
			return;
		}
		userFacade.createUserAccount(userDTO,type);
	}

	/**
	 * 领取门店关联的优惠券
	 * @param userVO
	 * @param storeId
	 * @return
	 */
	private void autoReceiveRegisterCouponByStore(UserVO userVO, Long storeId) {
		// 门店优惠券关联表
        List<CouponStoreDTO> couponStoreDTOList = userFacade.findCouponStore(storeId);
        List<Long> couponIdList = new ArrayList<>();
        for (CouponStoreDTO couponStoreDTO : couponStoreDTOList) {
            couponIdList.add(couponStoreDTO.getCouponId());
        }

        if (!couponIdList.isEmpty()) {
        	List<CouponBatchDTO> couponBatchList1 = userFacade.findRegisterCouponBatchByCouponId(couponIdList, userVO.getPlatformId());
        	if (EmptyUtil.isNotEmpty(couponBatchList1)) {
        		for(int i=0;i<couponBatchList1.size();i++){
					insertCouponUnitWithTx(userVO.getId(),couponBatchList1.get(i),i);
				}
        	}
        	List<CouponBatchDTO> couponBatchList2 = userFacade.findRegisterCouponBatchByCouponGroup(couponIdList, userVO.getPlatformId());
        	if (EmptyUtil.isNotEmpty(couponBatchList2)) {
				for(int i=0;i<couponBatchList1.size();i++){
					insertCouponUnitWithTx(userVO.getId(),couponBatchList1.get(i),i);
				}
        	}
        }
	}

	private boolean insertCouponUnitWithTx(Long userId, CouponBatchDTO couponBatchDTO,Integer index) {
        /*if (couponBatchDTO.getIsDisplay() == 0 || couponBatchDTO.getCouponRelType() == 1
                || couponBatchDTO.getGrantType() == 0) {
            return false;  //优惠卷批次不可领取
        }*/
		if (couponBatchDTO.getGrantType() == 0) {
            return false;  //系统发放的优惠卷不可领取
        }
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setId(couponBatchDTO.getCouponId());
        CouponDTO couponDTO_ = userFacade.findCouponById(couponDTO);
        if (EmptyUtil.isEmpty(couponDTO_)) {
            return false;  //优惠卷信息有误,优惠卷不存在
        }
        // 判断优惠券是否已领完
        CouponUnitDTO couponUnitDTO2 = new CouponUnitDTO();
        couponUnitDTO2.setCouponBatchId(couponBatchDTO.getId());
        List<CouponUnitDTO> couponUnitDTOList = userFacade.findCouponUnitAll(couponUnitDTO2);
        if (EmptyUtil.isEmpty(couponBatchDTO.getGrantCount())) {
            return false;
        }
        if (couponBatchDTO.getGrantCount().intValue() != -1 && couponBatchDTO.getGrantCount().intValue() <= couponUnitDTOList.size()) {
            return false;
        }
        // 验证通过,添加优惠卷unit
        CouponUnitDTO couponUnitDTO_ = new CouponUnitDTO();
        couponUnitDTO_.setCouponUnitCode(SequenceUtil.genCouponUnitNo(couponDTO_.getCouponType(),index));
        if (EmptyUtil.isNotEmpty(userFacade.findCouponUnitAll(couponUnitDTO_))) {
            return false;   //优惠卷unit编号重复
        }
		couponUnitDTO_.setBatchIndex(Long.valueOf(index));
        couponUnitDTO_.setTitle(couponDTO_.getTitle());
        couponUnitDTO_.setPlatformId(couponBatchDTO.getPlatformId());
        couponUnitDTO_.setCouponId(couponBatchDTO.getCouponId());
        couponUnitDTO_.setCouponBatchId(couponBatchDTO.getId());
        couponUnitDTO_.setUserId(userId);
        //领取时间
		couponUnitDTO_.setReceivedTime(new Date());
        // 设置优惠卷unit的有效时间
        if (couponBatchDTO.getEffectDays() != null
                && !couponBatchDTO.getEffectDays().equals(Integer.valueOf(-1))) {
            couponUnitDTO_.setEffectStartTime(new Date());
            couponUnitDTO_.setEffectEndTime(DateUtils.addDays(new Date(), couponBatchDTO.getEffectDays().intValue()));
        } else {
            couponUnitDTO_.setEffectStartTime(couponBatchDTO.getEffectStartTime());
            couponUnitDTO_.setEffectEndTime(couponBatchDTO.getEffectEndTime());
        }
        Long i = userFacade.insertCouponUnitWithTx(couponUnitDTO_);
        return  i > 0;
    }

	@Override
	public Map<String, Object> findUserNews(Long userId) {
		return userFacade.findUserNews(userId);
	}

	@Override
	public JsonResult<List<String>> getUserMailList(Long userId, Long platformId) {
		UserDTO userDTO = userFacade.findUserByID(userId);
		String mobile = userDTO.getMobile();
		List<UserDTO> userDTOList = userFacade.findUserByMobile(mobile, platformId);
		List<String> mailList = new ArrayList<>();
		for(UserDTO user:userDTOList ){
			mailList.add(user.getMail());
		}
		return JsonResult.success(mailList);
	}

	@Override
	public CompanyDTO findCompanyById(String companyId) {
		return companyFacade.findCompanyById(Long.valueOf(companyId));
	}

	@Override
	public void workWechatOAuth(HttpServletResponse response) throws IOException {
		//1.根据授权获取用户信息
		//1.1首先进行企业微信的网页授权，获取code

		String url = workWeChatUtil.oauth2URL + "?appid="+workWeChatUtil.SuiteID+"&redirect_uri="+URLEncoder.encode(workWeChatUtil.REDIRECT_URI,"UTF-8")+"&response_type=code&scope=snsapi_userinfo&state=appUrl#wechat_redirect";
		//HttpClientUtil.doGet(url);
		logger.info("重定向url："+url);
		response.sendRedirect(url);
		//WorkWeChatUtil.oauth2FromWorkWechat(corpid, Long.valueOf(companyId));


	}

	@Override
	public UserDTO createUser(String wxUserId, String name, String mobile, String email, Long companyId, String deviceId) {


		//2.根据用户信息查询该用户是否已存在
		//UserDTO userDTORes = userFacade.findByMail(email);
		List<UserDTO> userDTOList=userFacade.findUserByWxUserId(companyId,wxUserId);
		if(userDTOList.size()>1){
			throw new BusinessException("当前userid在该公司下已存在多个账户");
		}
		UserDTO userDTORes = new UserDTO();
		if(EmptyUtil.isEmpty(userDTOList)){
			//用户不存在创建新用户
			UserDTO userDTO = new UserDTO();
			userDTO.setCompanyId(companyId);
			userDTO.setDeviceId(deviceId);
			userDTO.setName(name);
			userDTO.setMail(email);
			userDTO.setPlatformId(7L);
			userDTO.setMobile(mobile);
			userDTO.setWechatUserId(wxUserId);
			UserExtendDTO extendDTO = new UserExtendDTO();
			extendDTO.setName(name);
			extendDTO.setDeviceId(deviceId);
			extendDTO.setStatus(Integer.valueOf(1));
			String userCode = System.currentTimeMillis() + CodeUtils.generateCode(5);
			extendDTO.setMemberCode(userCode);
			extendDTO.setAccountStatus(Integer.valueOf(0));
			Long userId= userFacade.createUser(userDTO, extendDTO);
			userDTORes=userFacade.findUserByID(userId);

		}else{
			userDTORes = userDTOList.get(0);
		}
		return userDTORes;
	}



	@Override
	public UserDTO createManageUser(String account,String passwd, String name, String mobile, String email, Integer type, Long enterpriseId, Long companyId,Set<Long> roles) {


		//2.根据用户信息查询该用户是否已存在
		//UserDTO userDTORes = userFacade.findByMail(email);
		List<UserDTO> userDTOList=userFacade.findByAccountAndMobile(account,mobile);
		if(userDTOList.size()>1){
			throw new BusinessException("账号或者手机号已存在，请更换");
		}
		if(roles==null || roles.size()==0) {
			throw new BusinessException("请设置角色");
		}
		UserDTO userDTORes = new UserDTO();
		if(EmptyUtil.isEmpty(userDTOList)){
			//用户不存在创建新用户
			UserDTO userDTO = new UserDTO();
			if(type!=null && type==2) {
				userDTO.setEnterpriseId(enterpriseId);
				userDTO.setType(type);
			}else if(type!=null && type==3) {
				userDTO.setEnterpriseId(enterpriseId);
				userDTO.setCompanyId(companyId);
				userDTO.setType(type);
			}
			userDTO.setName(name);
			userDTO.setLoginName(account);
			userDTO.setMail(email);
			userDTO.setPlatformId(7L);
			userDTO.setMobile(mobile);

			userDTO.setSalt(SaltUtils.getRandomSalt());
			userDTO.setPassword(MD5Support.MD5(passwd, userDTO.getSalt()));
			UserExtendDTO extendDTO = new UserExtendDTO();
			extendDTO.setName(name);
			extendDTO.setStatus(Integer.valueOf(1));
			String userCode = System.currentTimeMillis() + CodeUtils.generateCode(5);
			extendDTO.setMemberCode(userCode);
			extendDTO.setAccountStatus(Integer.valueOf(0));
			Long userId= userFacade.createUser(userDTO, extendDTO);
			userRoleWriteDAO.deleteByUser(userId);
			for(Long role: roles) {
				UserRoleDTO urDto = new UserRoleDTO();
				urDto.setRoleId(role);
				urDto.setUserId(userId);
				userRoleFacade.save(urDto);
			}

			userDTORes=userFacade.findUserByID(userId);

		}else{
			userDTORes = userDTOList.get(0);
		}
		return userDTORes;
	}

	@Override
	public String getWorkWeChatAccessToken(JedisUtil cache, String companyId) {
		return userFacade.getWorkWeChatAccessToken(cache,companyId);
	}

	@Override
	public List<CompanyDTO> findCompanyByParam(CompanyDTO companyDTO) {
		return companyFacade.findCompanyAll(companyDTO);
	}

	@Override
	public void saveCompany(CompanyDTO dto) {
		companyFacade.saveCompany(dto);
	}

	@Override
	public String getWorkWeChatSuiteAccessToken(JedisUtil cache) {
		return userFacade.getWorkWeChatSuiteAccessToken(cache);
	}

	@Override
	public void saveUserByMail(String id, String mail) {
		userFacade.saveUserByMail(id, mail);
	}

	@Override
	public PageResult<UserDTO> findManagePage(Pagination page, UserVO userVO) {

		// TODO Auto-generated method stub

		UserDTO dto = UserConverter.toDTO(userVO);
		return userFacade.findManagePage(page, dto);
	}

	@Override
	public int resetManageUserPasswordWithTx(Long userId, String password) {
		// TODO Auto-generated method stub
		return userFacade.resetManageUserPasswordWithTx(userId, password);
	}

	@Override
	public BaseResponse<String> updateManageUser(UniAuthUserInfoAddParam uniAuthUserInfoAddParam) {
		// TODO Auto-generated method stub
		BaseResponse<String> response = new BaseResponse<String>();
		logger.info("方法名:update \t参数：uniAuthUserInfo={},userUuid={}", uniAuthUserInfoAddParam.toString(), RuntimeContext.cacheUser()==null?"--":RuntimeContext.cacheUser().getId());
		// 验证参数是否为空
		if (UtilHelper.isEmpty(uniAuthUserInfoAddParam) || UtilHelper.isEmpty(uniAuthUserInfoAddParam.getId())
				|| UtilHelper.isEmpty(uniAuthUserInfoAddParam.getUserName())) {
			response.setCode(ResponseCodeEnum.PARAM_IS_NULL.getCode());
			response.setMsg(ResponseCodeEnum.PARAM_IS_NULL.getMsg());
			return response;
		}
		UserDTO dbUser = userFacade.findUserByID(uniAuthUserInfoAddParam.getId());
		if (dbUser == null) {
			throw new BusinessException(ResponseCodeEnum.USER_NOT_FOUND.getCode(),ResponseCodeEnum.USER_NOT_FOUND.getMsg());
		}
		UserDTO user = new UserDTO();
		user.setId(dbUser.getId());
		user.setName(uniAuthUserInfoAddParam.getUserName());
		userFacade.updateManageUser(user);
		return BaseResponse.success("更新成功");
	}


    @Override
    public UserVO switchLoginOpenId(String weiXinOpenId,Long platformId) {
        List<WxOpenidDTO> openIds=wxOpenIdManage.findByOpenIdHasUser(weiXinOpenId);
        if (EmptyUtil.isNotEmpty(openIds)){
            UserDTO userDTO=findUserByID(openIds.get(0).getUserId());
            if (EmptyUtil.isNotEmpty(userDTO) && EmptyUtil.isNotEmpty(userDTO.getMobile())){
                openIds=wxOpenIdManage.findUserAppids(userDTO.getId());
                for (WxOpenidDTO wxOpenidDTO:openIds){
                    if (!Objects.equals(weiXinOpenId, wxOpenidDTO.getWxOpenid())){
						UserVO userVO = loginManage.findByWeiXinOpenId(wxOpenidDTO.getWxOpenid(), platformId);
                        if (EmptyUtil.isNotEmpty(userVO) && Objects.equals(userDTO.getId(),userVO.getId())){
                            UserExtendDTO updateUserExtend=new UserExtendDTO();
                            updateUserExtend.setId(userDTO.getId());
							updateUserExtend.setWeixin(weiXinOpenId);
                            userExtendFacade.updateUserExtend(updateUserExtend);
							return userVO;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
	public int fillRealNameAndIdCard(Long userId,String realName,String idCardNo){

		//UserExtendDTO userExtendDTO = userExtendFacade.userByUserId(userId);

		return userExtendFacade.fillRealNameAndIdCard(userId,realName,idCardNo);
	}

	/**
	 * 根据渠道用户唯一标识查询用户信息
	 *
	 * @param channelUserUnique
	 * @return
	 */
	@Override
	public List<UserVO> findByChannelUserUniqueAndRegister(String channelUserUnique,Long companyId,Long platformId){
		List<UserDTO> list = userFacade.findByChannelUserUniqueAndRegister(channelUserUnique,companyId,platformId);
		return UserConverter.toVO(list);
	}

	@Override
	public UserVO registDefaultCompanyUserByChannelUserUnique(String channelUserUnique, String name, String passwd, String wxOpenId, Long platformId, Long companyId, ChannelUserUniqueRegLoginVO vo) {
		return userFacade.registDefaultCompanyUserByChannelUserUnique(channelUserUnique,name,passwd, wxOpenId, platformId,companyId,vo);
	}

	@Override
	public UserVO findLatestLoginByChannelUserUnique(String channelUserUnique,Long companyId,Long platformId) {
		UserDTO userDTO = userFacade.findLatestLoginByChannelUserUnique(channelUserUnique,companyId,platformId);
		return UserConverter.toVO(userDTO);
	}

	@Override
	public JsonResult<Integer> switchUserEnterprise(SwitchUserEnterpriseVO vo){

		return userFacade.switchUserEnterprise(vo);
	}
}
