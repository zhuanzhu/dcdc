package com.egeo.components.user.controller.api;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.egeo.components.user.business.*;
import com.egeo.components.user.common.Constants;
import com.egeo.components.user.dto.*;
import com.egeo.components.user.service.read.CompanyReadService;
import com.egeo.components.user.vo.*;
import com.egeo.components.utils.IdCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.common.CacheKeyConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.business.util.SsoClientUtil;
import com.egeo.components.user.common.LockConfig;
import com.egeo.components.user.constant.UserLoginConstant;
import com.egeo.components.user.controller.thread.SendMailThread;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.MD5Support;
import com.egeo.util.security.MD5Util;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.utils.io.OnLineDownload;
import com.egeo.utils.lock.LogicLock;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

/**
 * 用户控制层
 * @author 闵浮龙
 *
 */
@Controller
@RequestMapping("/api/user/user")
public class UserAction extends BaseSpringController {

	@Resource(name="user")
	private UserManage userManage;

	@Resource
	private  JedisUtil cache;

	@Resource(name="login")
	private LoginManage loginManage;

	@Resource(name="userLogin")
	private UserLoginManage userLoginManage;

	@Resource(name="userCookie")
	private UserCookieManage userCookieManage;

	@Resource(name="ssoClientUtil")
	private  SsoClientUtil ssoClientUtil;

	@Resource(name="userExtend")
	private  UserExtendManage userExtendManage;

    @Autowired
    private LockConfig lockConfig;

	@Autowired
	private CompanyReadService companyReadService;
	/**
	 * 分页查看所有用户
	 * @param page
	 * @param userVO
	 * @param userExtendVO
	 * @param userCookieVO
	 * @return
	 */
	@RequestMapping(value = "userList")
	@ResponseBody
	public JsonResult<PageResult<UserDTO>> findPage(Pagination page,UserVO userVO,UserExtendVO userExtendVO,UserCookieVO userCookieVO) {
	    logger.info("分页查看所有用户");
		JsonResult<PageResult<UserDTO>> result = new JsonResult<PageResult<UserDTO>>();
	    try {
                PageResult<UserDTO> pageResult = userManage.findPage(page, userVO,userExtendVO,userCookieVO);
                result.setData(pageResult);
                return result;
            } catch (Exception e) {
                logger.error("查询所有用户异常！", e);
                result.setCode(1);
                result.setError("查询所有用户失败:" + e.getMessage());
                return result;
            }
	}

	/**
	 * 分页查看所有产品平台用户
	 * @param page
	 * @param userVO
	 * @param userExtendVO
	 * @param userCookieVO
	 * @return
	 */
	@RequestMapping(value = "findProductUser")
	@ResponseBody
	public JsonResult<PageResult<ProductUser>> findProductUser(Pagination page,UserVO userVO,UserExtendVO userExtendVO) {
	    JsonResult<PageResult<ProductUser>> result = new JsonResult<PageResult<ProductUser>>();
	    try {
	    		logger.info("开始分页查看所有产品平台用户！");
                PageResult<ProductUser> pageResult = userManage.findProductUser(page, userVO,userExtendVO);
                result.setData(pageResult);
                return result;
            } catch (Exception e) {
                logger.error("分页查看所有产品平台用户异常！", e);
                result.setCode(1);
                result.setError("分页查看所有产品平台用户失败:" + e.getMessage());
                return result;
            }
	}


	/**
	 * 更新用户
	 * @param vo
	 * @param userExtendVO
	 * @param userCookieVO
	 * @return
	 */
	@RequestMapping(value = "saveOrUpdate")
	@ResponseBody
	public JsonResult<String> saveOrUpdate(@Valid UserVO vo,UserExtendVO userExtendVO,UserCookieVO userCookieVO,BindingResult br,HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		JsonResult<String> result = new JsonResult<String>();
        if (logger.isInfoEnabled()) {
            logger.info("更新用户, UserDTO = {}", vo);
                }
        String id = userManage.saveOrUpdate(vo,userExtendVO,userCookieVO);
        if (logger.isInfoEnabled()) {
            logger.info("更新用户成功, UserDTOLoginName = {}", vo.getLoginName());
        }
        result.setData(id);
        return result;
    	}


	/**
	 * 校验员工编号或者邮箱
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "checkMemberCodeOrMail")
	@ResponseBody
	public JsonResult<Integer> checkMemberCodeOrMail(Long companyId,String memberCode,String mail,HttpServletRequest req) {

		String str = req.getHeader("platformId");
		Long platformId=null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}

		if(StringUtils.isNotEmpty(memberCode)){
			logger.info("校验员工编号!");

			int userCount = userManage.findUserByMemberCode(companyId,memberCode,platformId);
			if(userCount==0){
				return success("员工编号可以使用", 0);
			}
			return fail("员工编号已经被占用，请修改！");

		}else if(StringUtils.isNotEmpty(mail)){
			logger.info("校验邮箱!");
			UserDTO userDTO = new UserDTO();
			userDTO.setMail(mail);
			userDTO.setPlatformId(platformId);
			List<UserDTO> userDTOList = userManage.findUser(userDTO);
			if(EmptyUtil.isEmpty(userDTOList)){
				return success("邮箱可以使用", 0);
			}else{
				return fail("邮箱已经注册过账号！");
			}
		}

		return null;
	}





	/**
	 * 编辑用户时候的回显
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "findAboutUser")
	@ResponseBody
	public JsonResult<Map<String,Object>> findAboutUser(Long id,HttpServletRequest req) {
		logger.info("查询员工!");

		String str = req.getHeader("platformId");
		Long platformId=null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		JsonResult<Map<String,Object>>   jr = userManage.findAboutUserByUserId(id,platformId);

		return jr;
	}


	/**
	 * 新增或者更新用户
	 * @param vo
	 * @param userExtendVO
	 * @param userCookieVO
	 * @return
	 */
	@RequestMapping(value = "insertOrUpdate")
	@ResponseBody
	public JsonResult<String> insertOrUpdateUser(String id,String name,String memberCode,String mail,String sex,String birthday,
			String mobile,String companyId,String isAdministrator,String departmentIdArr,HttpServletRequest req) {
		UserTempConditionVO vo = new UserTempConditionVO();
		CacheUser cacheUser = getCacheUser();
		vo.setCurrUserId(cacheUser.getId());
		if(EmptyUtil.isEmpty(id)){
			logger.info("新增员工!");
			vo.setName(name);
			if(EmptyUtil.isNotEmpty(sex)){
				vo.setSex(Integer.valueOf(sex));
			}
			if(EmptyUtil.isNotEmpty(birthday)){
				vo.setBirthday(Long.valueOf(birthday));
			}

				vo.setMobile(mobile);

			if(EmptyUtil.isEmpty(memberCode)){
				return fail("员工编号不能为空！");
			}else{
				vo.setMemberCode(memberCode);
			}
			if(EmptyUtil.isEmpty(mail)){
				return fail("邮箱不能为空！");
			}else{
				vo.setMail(mail);
			}
			if(EmptyUtil.isEmpty(isAdministrator)){
//				return fail("是否设为管理员不能为空！");
			}else{
				vo.setIsAdministrator(Integer.valueOf(isAdministrator));
			}
			if(StringUtils.isEmpty(companyId)){
				return fail("公司不能为空");
			}else{
				vo.setCompanyId(Long.valueOf(companyId));
			}
		}else{
			logger.info("编辑员工!");
			if(EmptyUtil.isNotEmpty(id)){
				vo.setId(Long.valueOf(id));
			}
			if(EmptyUtil.isNotEmpty(name)){
				vo.setName(name);
			}
			if(EmptyUtil.isNotEmpty(birthday)){
				vo.setBirthday(Long.valueOf(birthday));
			}

				vo.setMobile(mobile);

			if(EmptyUtil.isNotEmpty(sex)){
				vo.setSex(Integer.valueOf(sex));
			}
			if(EmptyUtil.isEmpty(memberCode)){
				return fail("员工编号不能为空！");
			}else{
				vo.setMemberCode(memberCode);
			}
			if(EmptyUtil.isEmpty(mail)){
				return fail("邮箱不能为空！");
			}else{
				vo.setMail(mail);
			}
			if(EmptyUtil.isEmpty(isAdministrator)){
//				return fail("是否设为管理员不能为空！");
			}else{
				vo.setIsAdministrator(Integer.valueOf(isAdministrator));
			}
			if(StringUtils.isEmpty(companyId)){
				return fail("公司不能为空");
			}else{
				vo.setCompanyId(Long.valueOf(companyId));
			}


		}

		if(StringUtils.isNotEmpty(vo.getName()) && StringUtils.length(vo.getName()) >10){
			return fail("姓名不能超过10个汉字");
		}
		if(StringUtils.length(vo.getMemberCode())>20){
			return fail("员工编号不能超过20个汉字！");
		}
		if(StringUtils.isNotEmpty(vo.getName())){
			if(StringUtils.isNotEmail(vo.getMail()) ||  StringUtils.length(vo.getMail())>64){
				return fail("请检查邮箱是否合法！");
			}
		}
		if(EmptyUtil.isNotEmpty(vo.getBirthday())){
			if(System.currentTimeMillis() - vo.getBirthday().getTime() >36500L*24L*3600L*1000L){
				return fail("员工生日选择不合法！");
			}
		}
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		/*// 慢有优手机必填
		if(vo.getPlatformId().equals(2L)){*/
			if(EmptyUtil.isNotEmpty(vo.getMobile())){
				if(! StringUtils.validMobile(vo.getMobile())){
					return fail("请输入正确的手机号码");
				}
			}

		/*if(StringUtils.isNotEmpty(vo.getMobile())){
			if(! StringUtils.validMobile(vo.getMobile())){
				return fail("请输入正确的手机号码");
			}
		}*/



		userManage.insertOrUpdateUser(vo,departmentIdArr);

		return success("操作成功");
	}


	/**
	 * 根据id删除用户
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public JsonResult<String> delete(UserDTO dto) {
	    JsonResult<String> result = new JsonResult<String>();
		try {
            userManage.deleteWithTx(dto);
            result.setData("删除用户成功");
            return result;
        } catch (Exception e) {
            logger.error("删除用户异常！", e);
            result.setCode(1);
            result.setError("删除用户失败:" + e.getMessage());
            return result;
        }
	}

		/**
         * 根据id查询用户
         * @param dto
         * @return
         */
        @RequestMapping(value = "userById")
        @ResponseBody
        public JsonResult<UserDTO> userById(UserVO vo) {
                JsonResult<UserDTO> result = new JsonResult<UserDTO>();
                try {
                    UserDTO userDTO = userManage.userById(vo);
                    result.setData(userDTO);
                    return result;
                } catch (Exception e) {
                    logger.error("根据id查询用户异常！", e);
                    result.setCode(1);
                    result.setError("根据id查询用户失败:" + e.getMessage());
                    return result;
                }
        }

        /**
         * 根据id查询产品用户
         * @param dto
         * @return
         */
        @RequestMapping(value = "productUserById")
        @ResponseBody
        public JsonResult<ProductUser> productUserById(ProductUser vo) {
                JsonResult<ProductUser> result = new JsonResult<ProductUser>();
                try {
                	logger.info("根据id查询产品用户!");
                	ProductUser productUser = userManage.productUserById(vo);
                    result.setData(productUser);
                    return result;
                } catch (Exception e) {
                    logger.error("根据id查询产品用户异常！", e);
                    result.setCode(1);
                    result.setError("根据id查询产品用户失败:" + e.getMessage());
                    return result;
                }
        }

        /**
         * 获取验证码
         * @param dto
         * @return
         */
        @RequestMapping(value = "gainVerificationode")
        @ResponseBody
        public JsonResult<String> gainVerificationode(String mail,Integer type,HttpServletRequest req) {
        	logger.info("获取邮箱验证码,邮箱={}",mail);
        	Long platformId = null;
        	String str = req.getHeader("platformId");
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}
        	SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd");
        	//所有字母转小写
        	if(EmptyUtil.isBlank(mail)){
        		return fail(BusinessExceptionConstant.EMAIL_EMPTY,"请填写邮箱");
        	}
        	final String mail_=mail.toLowerCase();;
        	JsonResult<String> result = new JsonResult<String>();
        	//判断是否为邮箱
            if(!mail_.contains("@")){
                throw new BusinessException("邮箱格式错误");
            }
            if(type==null){
            	throw new BusinessException("类型不能为空");
            }

        	// 根据邮箱查询有效用户
				UserExtendDTO userExtendDTO_ = new UserExtendDTO();
				userExtendDTO_.setMail(mail.toLowerCase());
				userExtendDTO_.setAccountStatus(0);
				List<UserExtendDTO> UserExtendDTOList = userExtendManage.queryUserByCondition(userExtendDTO_);
			UserExtendDTO userVO = new UserExtendDTO();
				if(type!=4){
					if (EmptyUtil.isEmpty(UserExtendDTOList))
						return fail("邮箱不存在");

					if (UserExtendDTOList.size() != 1)
						return fail("该邮箱存在重复的有效邮箱,请联系后台管理员修正数据");
					userVO = UserExtendDTOList.get(0);
				}



    		// redisKey值规则：固定常量+平台id+客户端id+用户邮箱
    		String redisKey = mail_;
        	// 0：修改支付密码、1：注册接口邮箱验证码、2：忘记密码接口验证码 3: 管理员有效邮箱自主注册 4:解绑手机邮箱验证
    		switch (type) {
			case 0:
        		redisKey = CacheKeyConstant.UPDATE_PAYMENT_PASSWORD_MAIL_KEY + mail_;
				break;
			//注册接口来的邮箱验证码进入判断
			case 1:
				if(userVO.getStatus() == 1){
        			throw new BusinessException(BusinessExceptionConstant.EMAIL_ALREADY_REGISTER, "邮箱已注册，请使用邮箱登录");
        		}
        		redisKey = CacheKeyConstant.USER_REGISTER_MAIL_KEY + mail_;
				break;
			//忘记密码接口来的邮箱验证码进入判断
			case 2:
				if(userVO.getStatus() == 0){
        			throw new BusinessException(BusinessExceptionConstant.ACCOUNT_NO_REGISTER, "邮箱未注册");
        		}
        		redisKey = CacheKeyConstant.UPDATE_PASSWORD_MAIL_KEY + mail_;
				break;
			// 管理员有效邮箱自主注册
			case 3:
				// 邮箱已注册
        		if(userVO.getStatus() == 1)
        			return fail("邮箱已注册，请使用邮箱登录");

        		UserExtendDTO userExtendDTO = userExtendManage.findById(userVO.getId());
        		if (userExtendDTO.getIsAdministrator() == null
        				|| (userExtendDTO.getIsAdministrator() != null && userExtendDTO.getIsAdministrator() != 1)) {
        			return fail("该邮箱无注册权限");
        		}
        		// 此处后期修改
        		redisKey = mail_;
				break;
			case 4:
					// 此处后期修改
					redisKey =CacheKeyConstant.CHANGE_MOBILE_KEY+ mail_;
					break;
			default:
				throw new BusinessException("未定义获取邮箱验证码类型：" + type);
			}
    		String platformNmae = null;

				if(platformId.equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
					platformNmae = PlatformKeyConstant.MYY_PLATFORM_NAME;
				}else if(platformId.equals(PlatformKeyConstant.FGJ_PLATFORM_ID)){
					if(EmptyUtil.isNotEmpty(userVO.getCompanyId())&&userVO.getCompanyId().equals(PlatformKeyConstant.BAI_ER_COMPANY_ID)){
						//拜尔公司接入新需求(推送消息福管加字样改成yougo)
						platformNmae = PlatformKeyConstant.BAI_ER_MESSAGE_HEAD_NAME;
					}else {
						platformNmae = PlatformKeyConstant.FGJ_PLATFORM_NAME;
					}
				}

        	String redisVerificationCode = StringUtils.getRandom(6);
        	//以邮箱为key将验证码放入缓存、设置300秒过期
        	cache.set(redisKey, 300, MD5Support.MD5(redisVerificationCode, mail_));
        	final String content = "<html><head></head><body><h3>亲爱的用户：</h3><br/>您好！感谢您使用" + platformNmae + "，您正在进行邮箱验证，本次请求的验证码为：<font color='#FF0000'>"+ redisVerificationCode +"</font>（验证码有效期5分钟。）<br/><br/><br/><br/><br/>" + platformNmae + "团队<br/>"+format.format(new Date())+"</body></html>";
        	//发送邮箱
        	new Thread(new SendMailThread(mail_,platformNmae,content)).start();
        	logger.info("验证码已发送至邮箱:"+redisVerificationCode);

        	//MailUtils.send(exChange2,redisVerificationCode);
        	result.setData("验证码已发送至邮箱");
			return result;
        }


        /**
         * 用户注册
         * @param dto
         * @return
         */
        @RequestMapping(value = "userRegister")
        @ResponseBody
        public JsonResult<User> userRegister(String mail,String verificationCode, String password,String confirmPassword,String channelName, HttpServletRequest req, HttpServletResponse resp) {
        	logger.info("用户注册,邮箱={}",mail);
        	String mail_ = null;
        	//所有字母转小写
        	if(EmptyUtil.isNotEmpty(mail)){
        		mail_ = mail.toLowerCase();
        	}
        	String str = req.getHeader("platformId");
        	Long platformId = null;
        	UserVO vo = new UserVO();
        	String device_type = req.getHeader("f");
        	if(StringUtils.isNotFigure(device_type)){
        		vo.setDeviceType(Integer.valueOf(device_type));
        	}else{
        		return fail("设备类型参数缺失");
        	}
        	String version = req.getHeader("v");
        	if(EmptyUtil.isNotEmpty(version)){
        		vo.setVersion(version);
        	}else{
        		return fail("版本号参数缺失");
        	}
        	String deviceId = req.getHeader("deviceId");
        	if(EmptyUtil.isNotEmpty(deviceId)){
        		vo.setDeviceId(deviceId);
        	}

        	String weiXinOpenId = req.getHeader("weiXinOpenId");
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}
        	if(mail_ == null){
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_EMPTY,"邮箱为空");
        	}
        	//判断是否为邮箱
            if(!mail_.contains("@")){
                throw new BusinessException("请输入正确的邮箱");
            }
            if(EmptyUtil.isEmpty(verificationCode))
            	return fail("请输入验证码");
            if(EmptyUtil.isEmpty(password)){
        		throw new BusinessException(BusinessExceptionConstant.USER_PASSWORD_EMPTY, "用户密码为空");
        	}
        	if(!password.equals(confirmPassword)){
        		throw new BusinessException(BusinessExceptionConstant.USER_NEW_PASSWORD_NO_MATCH,"两次输入的密码不一致，请重新输入");
        	}
            // 字符串是否与正则表达式相匹配
			/*if (!StringUtils.isNotPasswordMay(password)) {
				throw new BusinessException(BusinessExceptionConstant.LETTER_FIGURE_SYMBOL,
						"密码必须包含大写字母、小写字母、数字和符号其中三项！");
			}*/

        	//根据邮箱查询用户
        	UserVO userVO = userManage.userByMail(mail_);
        	if(userVO.getStatus() == 1){
    			throw new BusinessException(BusinessExceptionConstant.EMAIL_ALREADY_REGISTER, "邮箱已注册，请使用邮箱登录");
    		}
        	if(EmptyUtil.isNotEmpty(userVO)){
        		vo.setCompanyId(userVO.getCompanyId());
        	}else{
        		//根据邮箱后缀查询公司id
                /*String a[] = vo.getMail().split("@");
                String companyMailSuffix = a[a.length-1];
        		CompanyDTO companyDTO = new CompanyDTO();
        		companyDTO.setCompanyMailSuffix("@"+companyMailSuffix);
        		List<CompanyDTO> list = companyFacade.findCompanyAll(companyDTO);
        		if(EmptyUtil.isEmpty(list)){
        			throw new BusinessException("请输入正确的邮箱后缀");
        		}else{
        			vo.setCompanyId(list.get(0).getId());
        		}*/
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_NO_EXIST,"邮箱未认证");
        	}

        	//根据邮箱获取放入缓存中的验证码
        	String redisVerificationCode = (String) cache.get(CacheKeyConstant.USER_REGISTER_MAIL_KEY + mail_);
        	if(EmptyUtil.isEmpty(redisVerificationCode)){
        		throw new BusinessException("验证码过期、请重新发送");
        	}
        	if(!redisVerificationCode.equals(MD5Support.MD5(verificationCode, mail_))){
        		throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"输入的验证码不对");
        	}
        	vo.setMail(mail_);
        	vo.setPassword(password);
        	vo.setPlatformId(platformId);
        	Long userId = userManage.userRegister(vo,channelName);

        	//登陆
        	if(EmptyUtil.isNotEmpty(userId)){
        		UserVO user = new UserVO();
        		user.setName(mail_);
        		user.setPassword(password);
        		user.setPlatformId(platformId);
        		user.setWeiXinOpenId(weiXinOpenId);
        		JsonResult<User> result = this.userLogin(user, req, resp);
        		// 使用一次之后直接删除
            	cache.del(CacheKeyConstant.USER_REGISTER_MAIL_KEY + mail_);
        		return result;
            }else{
            	throw new BusinessException("用户注册失败");
            }



        }


        /**
         * 用户注册
         * @param dto
         * @return
         */
        @RequestMapping(value = "userMobileRegister")
        @ResponseBody
        public JsonResult<User> userMobileRegister(String mobile,String wxOpenId,String verificationCode,
												   String password,String confirmPassword,
												   String channelName, Long companyId,Boolean checkPwd,
												   HttpServletRequest req, HttpServletResponse resp) {
        	logger.info("用户注册,手机={}",mobile);
        	String mobile_ = null;
        	//所有字母转小写
        	if(EmptyUtil.isNotEmpty(mobile)){
        		mobile_ = mobile.toLowerCase();
        	}
        	String str = req.getHeader("platformId");
        	Long platformId = null;
        	UserVO vo = new UserVO();
        	String device_type = req.getHeader("f");
        	if(StringUtils.isNotFigure(device_type)){
        		vo.setDeviceType(Integer.valueOf(device_type));
        	}else{
        		return fail("设备类型参数缺失");
        	}
        	String version = req.getHeader("v");
        	if(EmptyUtil.isNotEmpty(version)){
        		vo.setVersion(version);
        	}else{
        		return fail("版本号参数缺失");
        	}
        	String deviceId = req.getHeader("deviceId");
        	if(EmptyUtil.isNotEmpty(deviceId)){
        		vo.setDeviceId(deviceId);
        	}

        	String weiXinOpenId = req.getHeader("weiXinOpenId");
			if (EmptyUtil.isEmpty(wxOpenId)){
				wxOpenId=weiXinOpenId;
			}
			if (EmptyUtil.isEmpty(wxOpenId)){
				return fail("wxOpenId参数不可为空");
			}
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}
        	if(mobile_ == null){
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_EMPTY,"手机为空");
        	}
        	//判断是否为邮箱
            /*if(!mail_.contains("@")){
                throw new BusinessException("请输入正确的邮箱");
            } */
            if(EmptyUtil.isEmpty(verificationCode))
            	return fail("请输入验证码");
            if (Objects.isNull(checkPwd)){
                checkPwd=true;
            }
            if (!checkPwd && EmptyUtil.isEmpty(password)){
                password=Constants.USER_DEFAULT_PWD;
                confirmPassword=Constants.USER_DEFAULT_PWD;
            }
            if(EmptyUtil.isEmpty(password)){
        		throw new BusinessException(BusinessExceptionConstant.USER_PASSWORD_EMPTY, "用户密码为空");
        	}
        	if(!password.equals(confirmPassword)){
        		throw new BusinessException(BusinessExceptionConstant.USER_NEW_PASSWORD_NO_MATCH,"两次输入的密码不一致，请重新输入");
        	}
            // 字符串是否与正则表达式相匹配
			/*if (!StringUtils.isNotPasswordMay(password)) {
				throw new BusinessException(BusinessExceptionConstant.LETTER_FIGURE_SYMBOL,
						"密码必须包含大写字母、小写字母、数字和符号其中三项！");
			}*/

        	//根据邮箱查询用户
        	List<UserVO> userIdList=userManage.findUserByMobile(mobile,platformId);
        	if(userIdList!=null && userIdList.size()>0) {
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_ALREADY_REGISTER, "手机号码已注册，请使用手机号码登录");
        	}
        	/*UserVO userVO = userManage.userByMail(mail_);
        	if(userVO.getStatus() == 1){
    			throw new BusinessException(BusinessExceptionConstant.EMAIL_ALREADY_REGISTER, "邮箱已注册，请使用邮箱登录");
    		}*/
        	/*if(EmptyUtil.isNotEmpty(userVO)){
        		vo.setCompanyId(userVO.getCompanyId());
        	}else{
        		//根据邮箱后缀查询公司id
                String a[] = vo.getMail().split("@");
                String companyMailSuffix = a[a.length-1];
        		CompanyDTO companyDTO = new CompanyDTO();
        		companyDTO.setCompanyMailSuffix("@"+companyMailSuffix);
        		List<CompanyDTO> list = companyFacade.findCompanyAll(companyDTO);
        		if(EmptyUtil.isEmpty(list)){
        			throw new BusinessException("请输入正确的邮箱后缀");
        		}else{
        			vo.setCompanyId(list.get(0).getId());
        		}
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_NO_EXIST,"邮箱未认证");
        	}*/
        	//根据邮箱获取放入缓存中的验证码
        	String redisVerificationCode = (String) cache.get(CacheKeyConstant.MOBILE_LOGIN_KEY + mobile_);
        	if(EmptyUtil.isEmpty(redisVerificationCode)){
        		throw new BusinessException("验证码过期、请重新发送");
        	}
        	if(!redisVerificationCode.equalsIgnoreCase(verificationCode)){
        		throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"输入的验证码不对");
        	}
			if (Objects.nonNull(companyId)){
				CompanyDTO companyDTO=companyReadService.findCompanyById(companyId);
				if (Objects.isNull(companyDTO)){
					throw new BusinessException("指定公司信息不存在");
				}
			}
        	return userManage.registerAndLoginDefaultCompanyUserByMobile(mobile_,password, wxOpenId, platformId,companyId,req,resp);
        }
        /**
         * 用户找回密码
         * @param dto
         * @return
         */
        @RequestMapping(value = "findUserPassword")
        @ResponseBody
        public JsonResult<User> findUserPassword(String mail,String verificationCode, String password,String confirmPassword,HttpServletRequest req, HttpServletResponse resp) {
        	logger.info("用户找回密码,邮箱={}",mail);
        	String mail_ = null;
        	//所有字母转小写
        	if(EmptyUtil.isNotEmpty(mail)){
        		mail_ = mail.toLowerCase();
        	}
        	String str = req.getHeader("platformId");
        	Long platformId = null;
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}
        	if(EmptyUtil.isEmpty(mail_)){
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_EMPTY,"邮箱为空");
        	}
        	//判断是否为邮箱
            if(StringUtils.isNotEmail(mail_)){
                throw new BusinessException("邮箱格式错误");
            }
            if(EmptyUtil.isEmpty(verificationCode))
            	throw new BusinessException("请输入验证码");
            if(EmptyUtil.isEmpty(password)){
    			throw new BusinessException(BusinessExceptionConstant.USER_PASSWORD_EMPTY,"用户密码为空");
    		}
    		if(EmptyUtil.isEmpty(confirmPassword)){
    			throw new BusinessException("确认密码不能为空");
    		}
    		if(!password.equals(confirmPassword)){
    			throw new BusinessException(BusinessExceptionConstant.USER_NEW_PASSWORD_NO_MATCH,"二次密码输入不一致");
    		}
        	//根据邮箱查询用户
        	UserVO userVO = userManage.userByMail(mail_);
        	if(EmptyUtil.isEmpty(userVO)){
        		throw new BusinessException(BusinessExceptionConstant.EMAIL_NO_EXIST,"邮箱未认证");
        	}
        	//根据邮箱获取放入缓存中的验证码
        	String redisVerificationCode = (String) cache.get(CacheKeyConstant.UPDATE_PASSWORD_MAIL_KEY + mail_);
        	if(EmptyUtil.isEmpty(redisVerificationCode)){
        		throw new BusinessException("验证码错误");
        	}
        	if(!redisVerificationCode.equals(MD5Support.MD5(verificationCode, mail_))){
        		throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"验证码错误");
        	}
        	int rows = userManage.updateUserPasswordByMail(null, userVO.getId(),mail_,password, platformId);

        	if(rows != 0){
        		UserVO user = new UserVO();
        		user.setName(mail_);
        		user.setPassword(password);
        		user.setPlatformId(platformId);
        		JsonResult<User> result = this.userLogin(user, req, resp);
        		// 使用一次之后直接删除
            	cache.del(CacheKeyConstant.UPDATE_PASSWORD_MAIL_KEY + mail_);
        		return result;
        	}else{
        		throw new BusinessException("找回密码失败");
        	}
        }

        /**
         * 用户根据手机号设置密码
         * @param dto
         * @return
         */
        @RequestMapping(value = "setPasswordByMobile")
        @ResponseBody
        public JsonResult<User> setPasswordByMobile(String mobile,String verificationCode, String password,String confirmPassword,HttpServletRequest req, HttpServletResponse resp) {
        	logger.info("用户手机号为" + mobile + "设置密码");
        	String str = req.getHeader("platformId");
        	Long platformId = null;
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}
        	CacheUser cacheUser = this.getCacheUser();
        	boolean b = StringUtils.validMobile(mobile);
        	if(StringUtils.isEmpty(mobile) || !b){
        		//如果手机号码为空
            	logger.error("手机号格式错误:{}",mobile);
                return fail("手机号格式错误");
        	}
        	if(EmptyUtil.isEmpty(verificationCode))
        		return fail("请输入验证码");
        	if(EmptyUtil.isEmpty(password)){
    			throw new BusinessException(BusinessExceptionConstant.USER_PASSWORD_EMPTY,"用户密码为空");
    		}
    		if(EmptyUtil.isEmpty(confirmPassword)){
    			throw new BusinessException("确认密码不能为空");
    		}
    		if(!password.equals(confirmPassword)){
    			throw new BusinessException(BusinessExceptionConstant.USER_NEW_PASSWORD_NO_MATCH,"二次密码输入不一致");
    		}
    		//根据邮箱获取放入缓存中的验证码
        	String redisVerificationCode = (String) cache.get(CacheKeyConstant.UPDATE_PASSWORD_MOBILE_KEY + mobile);
        	if(EmptyUtil.isEmpty(redisVerificationCode)){
        		throw new BusinessException("验证码输入错误");
        	}
        	if(!redisVerificationCode.equals(verificationCode)){
        		throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"验证码输入错误");
        	}
        	UserDTO userDTO = userManage.findUserByID(cacheUser.getId());
        	int rows = userManage.updateUserPasswordByMail(mobile,cacheUser.getId(),userDTO.getMail(),password, platformId);

        	if(rows != 0){
        		UserVO user = new UserVO();
        		user.setName(userDTO.getMail());
        		user.setPassword(password);
        		user.setPlatformId(platformId);
        		JsonResult<User> result = this.userLogin(user, req, resp);
        		// 使用一次之后直接删除
            	cache.del(CacheKeyConstant.UPDATE_PASSWORD_MOBILE_KEY + mobile);
        		return result;
        	}else{
        		throw new BusinessException("找回密码失败");
        	}
        }

        /**
         * 根据用户id修改用户密码
         * @param dto
         * @return
         */
        @RequestMapping(value = "updateUserPassword")
        @ResponseBody
        public JsonResult<Integer> updateUserPassword(String password,String confirmPassword,String originalPassword,HttpServletRequest req) {
        	logger.info("用户找回密码");
        	CacheUser userCache = this.getCacheUser();
    		Long userId = userCache.getId();// 用户id
    		if(EmptyUtil.isEmpty(password)){
    			throw new BusinessException(BusinessExceptionConstant.USER_PASSWORD_EMPTY,"用户密码为空");
    		}
    		if(EmptyUtil.isEmpty(confirmPassword)){
    			throw new BusinessException("确认密码不能为空");
    		}
    		if(EmptyUtil.isEmpty(originalPassword)){
    			throw new BusinessException("原密码不能为空");
    		}
    		if(!password.equals(confirmPassword)){
    			throw new BusinessException(BusinessExceptionConstant.USER_NEW_PASSWORD_NO_MATCH,"二次密码输入不一致");
    		}
    		int rt = userManage.updateUserPassword(userId, password,originalPassword);
			return success(rt);
        }

        /**
         * 用户设置支付密码
         * @param dto
         * @return
         */
        @RequestMapping(value = "setPaymentPassword")
        @ResponseBody
        public JsonResult<String> setPaymentPassword(String mail,String verificationCode, String password,String confirmPassword,HttpServletRequest req) {
        	logger.info("用户"+ mail +"设置支付密码");
        	String str = req.getHeader("platformId");
        	Long platformId = null;
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}

        	String caseMail = null;
        	//所有字母转小写
        	if(EmptyUtil.isNotEmpty(mail)){
        		caseMail = mail.toLowerCase();
        	}

        	int i = userManage.setPaymentPassword(caseMail,verificationCode, password,confirmPassword, platformId);
        	if(i != 0){
        		return success("设置支付密码成功");
        	}else{
        		return success("设置支付密码失败");
        	}
        }

        /**
         * 用户根据手机号设置支付密码
         * @param dto
         * @return
         */
        @RequestMapping(value = "setPaymentPasswordByMobile")
        @ResponseBody
        public JsonResult<String> setPaymentPasswordByMobile(String mobile,String verificationCode, String password,String confirmPassword,HttpServletRequest req) {
        	logger.info("用户手机号为" + mobile + "设置支付密码");
        	String str = req.getHeader("platformId");
        	Long platformId = null;
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}

        	CacheUser cacheUser = this.getCacheUser();
        	boolean b = StringUtils.validMobile(mobile);
        	if(StringUtils.isEmpty(mobile) || !b){
        		//如果手机号码为空
            	logger.error("手机号格式错误:{}",mobile);
                return fail("手机号格式错误");
        	}
        	int i = userManage.setPaymentPasswordByMobile(mobile,verificationCode, password,confirmPassword,cacheUser.getId(), platformId);
        	if(i != 0){
        		return success("设置支付密码成功");
        	}else{
        		return success("设置支付密码失败");
        	}
        }

        /**
         * 根据公司查询部门列表
         * @param companyId
         * @param req
         * @return
         */
        @RequestMapping(value = "companyDepartmentList")
    	@ResponseBody
        public JsonResult<Map<String,Object>> companyDepartmentList(Long companyId){
        	logger.info("根据公司查询部门列表");
//        	CacheUser userCache = (CacheUser) req.getAttribute("ut");
//    		if(EmptyUtil.isEmpty(userCache)){
//    			throw new BusinessException(BusinessExceptionConstant.USER_NOT_LOGIN, "未登录或已过期，请登录！");
//    		}
//    		if(EmptyUtil.isEmpty(userCache.getId())){
//    			throw new BusinessException(BusinessExceptionConstant.USER_NOT_LOGIN, "未登录或已过期，请登录！");
//    		}
//    		Long userId=userCache.getId();//用户id
//
//    		String platformId_=req.getHeader("platformId");
//    		if(EmptyUtil.isEmpty(platformId_))
//    			return fail("platformId为空");
//    		Long platformId=Long.parseLong(platformId_);

    		return userManage.companyDepartmentList(companyId);
        }

        /**
         * 查询部门员工列表
         * @param departmentId
         * @return
         */
        @RequestMapping(value = "departMentMemberList")
    	@ResponseBody
        public JsonResult<Map<String,Object>> departMentMemberList(Long departmentId,Integer pageNo,Integer pageSize,HttpServletRequest req){
        	logger.info("查询部门员工列表");
        	CacheUser userCache = this.getCacheUser();
    		Long companyId=userCache.getCompanyId();
        	return userManage.departMentMemberList(departmentId,companyId,pageNo,pageSize);
        }

        /**
         * 发送手机验证码信息
         * @return
         */
        @RequestMapping(value = "sendIdentifyingCodeUn")
    	@ResponseBody
        public JsonResult<String> sendIdentifyingCodeUn(String mobile,int type,HttpServletRequest req){
        	logger.info("发送手机验证码信息");
        	String str = req.getHeader("platformId");
        	Long platformId = null;
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}
			Long companyId =null;
    		if(type!=1&&type!=6){
				CacheUser userCache = this.getCacheUser();
				companyId = userCache.getCompanyId();
			}

			boolean b = StringUtils.validMobile(mobile);
        	if(StringUtils.isEmpty(mobile) || !b){
        		//如果手机号码为空
            	logger.error("手机号格式错误:{}",mobile);
                return fail("手机号格式错误");
        	}
        	// redisKey值规则：固定常量+用户手机号
    		String redisKey = null;
        	//1为登录时发送验证码 2、为绑定手机发送验证码 3、修改登录密码验证码 4、修改支付密码 5、慢有优管理员用户登录手机验证码的key,6福管加couponunit扫码发送验证码
    		switch (type) {
			case 1:
				/*//根据用户手机号查询用户信息
        		List<UserVO> list = userManage.findByMobileAndRegister(mobile,platformId);
        		// 福管家判断逻辑
        		if(EmptyUtil.isEmpty(list) && platformId.equals(7L)){
    				throw new BusinessException(BusinessExceptionConstant.USER_MOBILE_NO_EMPTY,"手机号未绑定邮箱，请使用邮箱登录");
    			}*/
        		redisKey = CacheKeyConstant.MOBILE_LOGIN_KEY + mobile;
				break;
			case 2:
				redisKey = CacheKeyConstant.MOBILE_BINDING_IDENTITY_KEY + mobile;
				break;
			case 3:
				redisKey = CacheKeyConstant.UPDATE_PASSWORD_MOBILE_KEY + mobile;
				break;
			case 4:
				redisKey = CacheKeyConstant.UPDATE_PAYMENT_PASSWORD_MOBILE_KEY + mobile;
				break;
			case 5:
				redisKey = CacheKeyConstant.MYY_ADMIN_USER_LOGIN_MOBILE_KEY + mobile;
				break;
			case 6:
				redisKey = CacheKeyConstant.MOBILE_LOGIN_KEY + mobile;
				break;

			default:
				throw new BusinessException("未定义手机验证码类型：" + type);
			}

        	//发送到手机4位验证码
        	Long count=userManage.sendIdentifyingCode(companyId,type,mobile, 4,redisKey,platformId);
        	if(EmptyUtil.isNotEmpty(count) && count==1L){
        		return  success("手机发送验证码成功");
        	}
        	logger.error("发送短信失败！");
            return fail("发送短信失败");
        }

        @RequestMapping(value = "sendIdentifyingCode")
    	@ResponseBody
        public JsonResult<String> sendIdentifyingCode(String mobile,int type,HttpServletRequest req){
        	logger.info("发送手机验证码信息");
        	String str = req.getHeader("platformId");
        	Long platformId = null;
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}
			Long companyId =null;
    		if(type!=1&&type!=6){
				CacheUser userCache = this.getCacheUser();
				companyId = userCache.getCompanyId();
			}

			boolean b = StringUtils.validMobile(mobile);
        	if(StringUtils.isEmpty(mobile) || !b){
        		//如果手机号码为空
            	logger.error("手机号格式错误:{}",mobile);
                return fail("手机号格式错误");
        	}
        	// redisKey值规则：固定常量+用户手机号
    		String redisKey = null;
        	//1为登录时发送验证码 2、为绑定手机发送验证码 3、修改登录密码验证码 4、修改支付密码 5、慢有优管理员用户登录手机验证码的key,6福管加couponunit扫码发送验证码
    		switch (type) {
			case 1:
				/*//根据用户手机号查询用户信息
        		List<UserVO> list = userManage.findByMobileAndRegister(mobile,platformId);
        		// 福管家判断逻辑
        		if(EmptyUtil.isEmpty(list) && platformId.equals(7L)){
    				throw new BusinessException(BusinessExceptionConstant.USER_MOBILE_NO_EMPTY,"手机号未绑定邮箱，请使用邮箱登录");
    			}*/
        		redisKey = CacheKeyConstant.MOBILE_LOGIN_KEY + mobile;
				break;
			case 2:
				redisKey = CacheKeyConstant.MOBILE_BINDING_IDENTITY_KEY + mobile;
				break;
			case 3:
				redisKey = CacheKeyConstant.UPDATE_PASSWORD_MOBILE_KEY + mobile;
				break;
			case 4:
				redisKey = CacheKeyConstant.UPDATE_PAYMENT_PASSWORD_MOBILE_KEY + mobile;
				break;
			case 5:
				redisKey = CacheKeyConstant.MYY_ADMIN_USER_LOGIN_MOBILE_KEY + mobile;
				break;
			case 6:
				redisKey = CacheKeyConstant.MOBILE_LOGIN_KEY + mobile;
				break;

			default:
				throw new BusinessException("未定义手机验证码类型：" + type);
			}

        	//发送到手机4位验证码
        	Long count=userManage.sendIdentifyingCode(companyId,type,mobile, 4,redisKey,platformId);
        	if(EmptyUtil.isNotEmpty(count) && count==1L){
        		return  success("手机发送验证码成功");
        	}
        	logger.error("发送短信失败！");
            return fail("发送短信失败");
        }
        /**
         * 绑定手机
         * @param mobile
         * @param identityCode
         * @param req
         * @return
         */
        @RequestMapping(value = "bindingMobile")
    	@ResponseBody
        public JsonResult<String> bindingMobile(String mobile,String weiXinOpenId,String identityCode,int type,HttpServletRequest req){
        	logger.info("绑定手机,手机号={}",mobile);
        	Long platformId = null;
    		String str = req.getHeader("platformId");
    		if (EmptyUtil.isNotEmpty(str)) {
    			platformId = Long.valueOf(str);
    		}else{
    			return fail("平台id不能为空");
    		}
    		String deviceId = req.getHeader("deviceId");
    		if (EmptyUtil.isEmpty(deviceId))
    			return fail("设备编号参数缺失");
    		String clientId_ = req.getHeader("clientId");
    		if (EmptyUtil.isEmpty(clientId_))
    			return fail("客户端参数缺失");

    		Long clientId = Long.valueOf(clientId_);
        	CacheUser userCache = this.getCacheUser();
    		//用户id
    		Long userId=userCache.getId();

//    		String platformId_=req.getHeader("platformId");
//    		if(EmptyUtil.isEmpty(platformId_))
//    			return fail("platformId为空");
//    		Long platformId=Long.parseLong(platformId_);

    		//判断手机号和验证码
    		if(StringUtils.isEmpty(mobile)){
    			throw new BusinessException(BusinessExceptionConstant.USER_MOBILE_NO_EMPTY,"手机号为空");
    		}
    		if(StringUtils.isEmpty(identityCode)){
    			throw new BusinessException("验证码为空");
    		}
    		if(!StringUtils.validMobile(mobile)){
    			throw new BusinessException("手机号格式错误");
    		}

			String rt = userManage.bindingMobile(mobile,identityCode,userId,type,userCache.getMobile(),weiXinOpenId,platformId);

			// 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
			UserVO userVO = userManage.findByUserIdMobile(userId, mobile,platformId);
			// 更新user缓存
			loginManage.updateUserCache(userVO, platformId, deviceId, clientId);
			return success(rt);

        }

        /**
         * 解绑手机邮箱验证
         * @param mobile
         * @param identityCode
         * @param req
         * @return
         */
        @RequestMapping(value = "bindingMobileMailVerification")
    	@ResponseBody
        public JsonResult<String> bindingMobileMailVerification(String mail,String verificationCode,HttpServletRequest req){
        	logger.info("解绑手机邮箱验证");
        	String caseMail = null;
        	//所有字母转小写
        	if(EmptyUtil.isNotEmpty(mail)){
        		caseMail = mail.toLowerCase();
        	}

//    		String platformId_=req.getHeader("platformId");
//    		if(EmptyUtil.isEmpty(platformId_))
//    			return fail("platformId为空");
//    		Long platformId=Long.parseLong(platformId_);
        	if(EmptyUtil.isEmpty(caseMail)){
        		return fail("邮箱不能为空");
        	}
        	//根据邮箱获取放入缓存中的验证码
        	String redisVerificationCode = (String) cache.get(caseMail);
        	if(EmptyUtil.isEmpty(redisVerificationCode)){
        		return fail("验证码过期、请重新发送");
        	}
        	if(!redisVerificationCode.equals(MD5Support.MD5(verificationCode, caseMail))){
        		throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"验证码错误");
        	}
        	// 使用成功之后删除缓存
        	cache.del(caseMail);
			return success("邮箱验证成功");

        }

        /**
         * 解绑手机手机验证
         * @param mobile
         * @param identityCode
         * @param req
         * @return
         */
        @RequestMapping(value = "bindingMobileMobileVerification")
    	@ResponseBody
        public JsonResult<String> bindingMobileMobileVerification(String mobile,String mobileCode,HttpServletRequest req){
        	logger.info("解绑手机手机验证");
        	if(mobile == null)
        		return fail("请填写手机号");
        	if(mobileCode == null)
        		return fail("请填写验证码");
        	String code = (String) cache.get(CacheKeyConstant.MOBILE_BINDING_IDENTITY_KEY + mobile);

    		// 判断验证码
    		if (EmptyUtil.isEmpty(code)) {
    			// 如果验证码为空
    			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG, "验证码错误");
    		}

    		// 验证码不为空，比较验证码是否正确
    		if (StringUtils.equals(code, mobileCode)) {
    			// 使用成功之后直接删除缓存
    			cache.del(CacheKeyConstant.MOBILE_BINDING_IDENTITY_KEY + mobile);
    			return success("手机验证成功");
    		}else{
    			return fail("手机验证失败");
    		}


        }
	/**
	 * 清除员工导入缓存(尚未完成)
	 */
	@RequestMapping("/clearImportMemoryBackStage")
	@ResponseBody
	public JsonResult<String> clearImportMemoryBackStage(Long companyId,Integer type ,HttpServletRequest req){
		logger.info("清楚员工导入缓存接口入口");
		logger.info("接收参数companyId:"+companyId);
		logger.info("接收参数type:"+type);
		if(EmptyUtil.isEmpty(companyId)){
			return fail("请选择公司");
		}
		if(EmptyUtil.isEmpty(type)){
			return fail("请选择类型");

		}
		String platformId_=req.getHeader("platformId");
		if(EmptyUtil.isEmpty(platformId_))
			return fail("platformId为空");
		Long platformId=Long.parseLong(platformId_);
		userManage.clearImportMemoryBackStage(companyId,type,platformId);
		return success("清除缓存成功");


	}

	/**
         * 关于员工的导入
         * @param companyId
         * @param identityCode
         * @param req
         * @return
         */
        @RequestMapping(value = "userImport")
        @ResponseBody
        public JsonResult<Map<String,Object>> userImport(Long companyId,Integer tempType,HttpServletRequest req){
        	logger.info("关于员工的导入");
        	if(companyId==null){
        		return fail("请选择公司！");
        	}
        	if (EmptyUtil.isEmpty(tempType)) {
        		return fail("请选择导入类型");
        	}
        	String platformId_=req.getHeader("platformId");
        	if(EmptyUtil.isEmpty(platformId_))
        		return fail("platformId为空");
        	Long platformId=Long.parseLong(platformId_);

        	// 从请求体中获取文件
        	MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
        	Iterator<String> iter = multiRequest.getFileNames();
        	if(!iter.hasNext()) {
        		return fail("请上传文件");
        	}
        	MultipartFile file = multiRequest.getFile(iter.next());
        	if (file == null)
        		return fail("未发现Excel文件");

        	String originalFilename = file.getOriginalFilename();
        	// 获取文件后缀
        	String suffix = "";

        	suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        	if(! StringUtils.equals(suffix, ".xls") && ! StringUtils.equals(suffix, ".xlsx")){
        		return fail("导入的文件类型错误，请选择后重新选择文件导入");
        	};

        	List<Map<String, Object>> valueList = null;
        	try {
        		valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
        	} catch (IOException e) {
        		e.printStackTrace();
        		return fail("Excel文件读取发生异常");
        	}

        	if(tempType==1){
        		logger.info("离职员工导入");
        		return userManage.importQuitUserWithTx(companyId,tempType,platformId,valueList,req);
        	}
        	if(tempType==2){
        		logger.info("新员工导入");
            	return userManage.importUserWithTx(companyId,tempType,platformId,valueList,req);
        	}
        	if(tempType==3){
        		logger.info("员工部门导入");
        		return userManage.importUserDeptWithTx(companyId,tempType,platformId,valueList,req);
        	}
        	if(tempType==10){
				logger.info("员工名单重导入");
				return userManage.reImportUserWithTx(companyId,tempType,platformId,valueList,req);
			}
			return fail("无法识别的导入类型,导入失败");
        }

    	//确认导入员工相关
  	@RequestMapping(value = "/assureImportAboutUser")
    	@ResponseBody
    	public JsonResult<String> assureImport(Long companyId ,String templateType, int type,HttpServletRequest req ,String importUserInfo,String userInfo) {
    		logger.info("确认导入员工");
    		Long platformId;
    		String str = req.getHeader("platformId");
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}else{
    			return fail("平台id不能为空");
    		}

    		Long parseLong =null;
    		if(companyId==null){
    			return fail("公司不能为空");
    		}
    		if(templateType==null){
    			return fail("确定导入类型不能为空");
    		}
    		if(importUserInfo==null){
    			return fail("导入记录信息为空");
    		}else{
    			try {
    				parseLong = Long.parseLong(importUserInfo);
    			} catch (NumberFormatException e) {
    				e.printStackTrace();
    			}
    		}


    		//************************************第二部分
    		Long insertCount =null;
    		if(templateType.equals("新员工导入")){
    			logger.info("确认导入员工");
    			insertCount = userManage.assureImportUser(companyId,platformId,parseLong,userInfo);
    		}else if(templateType.equals("离职员工导入")){
    			logger.info("确认导入离职员工");
    			insertCount = userManage.assureImportQuitUser(companyId,platformId,parseLong,userInfo);

    		}else if(templateType.equals("员工部门导入")){
    			logger.info("确认导入员工部门");
    			insertCount = userManage.assureImportUserDept(companyId,platformId,parseLong,userInfo,type);

    		}else if(templateType.equals("员工名单重导入")){
				logger.info("确认员工名单重导入");
				insertCount = userManage.assureReImportUser(companyId,platformId,parseLong,userInfo);

			}else {
				return fail("无法识别的导入类型,导入失败");
			}


			String userIdStr = userInfo.substring(1);

    		String[] userIdArr = userIdStr.split(",");

    		if(userIdArr.length == insertCount){
    			return success("导入成功！");
    		}

			return fail("导入失败");
    	}



    	/**
    	 * 获取导入用户信息失败记录
    	 */
    	@RequestMapping(value = "userErrorRecord")
    	@ResponseBody
    	public void userErrorRecord(String userErrorRecordUrl,HttpServletResponse response) {
    		logger.info("获取导入用户信息失败记录");
    		try {
    			//获取目标文件的绝对路径
    	        //String fullFileName = req.getSession().getServletContext().getRealPath("/userTemplate.xlsx");
    	        //String fullFileName = req.getSession().getServletContext().getRealPath("./egeo_20171208105212.xlsx");
    			OnLineDownload.downLoad(userErrorRecordUrl, response, true);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    	}



        private void checkUserName(UserVO user) {

    		String name = user.getName();
    		if(StringUtils.isEmpty(name)){
    			throw new BusinessException("登录用户名不能为空");
    		}
    		if (name.contains("@")) {// 邮箱登陆
    			user.setMail(name);

    		} else if (StringUtils.validMobile(name)) {// 手机号登陆
    			user.setMobile(name);
    		} else {// 用户名登陆
    			user.setLoginName(name);
    		}

    	}
        /**
         * 登录
         * @param user
         * @param req
         * @param resp
         * @return
         */
        private JsonResult<User> userLogin(UserVO user,HttpServletRequest req, HttpServletResponse resp){
        	logger.info("登陆方法被调用,用户名称：" + user.getName());
        	String deviceId=req.getHeader("deviceId");
    		if(EmptyUtil.isEmpty(deviceId))
    			return fail("设备编号参数缺失");
    		String clientId_=req.getHeader("clientId");
    		if(EmptyUtil.isEmpty(clientId_))
    			return fail("客户端参数缺失");
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


            	// 写登陆日志
            	userLoginManage.insertLoginLogWithTx(rt, req,UserLoginConstant.LOGIN_TYPE_NORMAL.getStatus(),user.getKeyMessage(),user.getStoreId());

            	User userfz = new User();
            	userfz.setCookieValue(userCookie.getCookieValue());
            	userfz.setName(rt.getData().getName());
            	userfz.setId(rt.getData().getId());

            	return success(userfz);
            }else{
            	userLoginManage.insertLoginExceptionLogWithTx(code,user, req);
            	return fail(code, rt.getError());
            }
        }

        /**
         * 查询用户支付相关信息
         * @param req
         * @return
         */
    	@RequestMapping(value = "userPayInfo")
    	@ResponseBody
    	public JsonResult<Map<String,Object>> userPayInfo(HttpServletRequest req){
    		logger.info("查询用户支付相关信息");
    		CacheUser userCache = this.getCacheUser();
    		Long userId = userCache.getId();// 用户id
    		return userManage.userPayInfo(userId);
    	}

    	/**
    	 * 第三方保险登陆
    	 * @param req
    	 * @return
    	 */
    	@RequestMapping(value = "insurance3rdLogin")
    	@ResponseBody
    	public JsonResult<Map<String,Object>> insurance3rdLogin(Integer type,HttpServletRequest req){
    		logger.info("第三方保险登陆");
    		CacheUser userCache = this.getCacheUser();
    		Long userId = userCache.getId();// 用户id
    		return userManage.insurance3rdLogin(userId,type);
    	}

    	/**
    	 * 管理员有效邮箱自主注册
    	 * @param mail
    	 * @param code
    	 * @param password
    	 * @param repassword
    	 * @return
    	 */
    	@RequestMapping(value = "registerForAdmin")
    	@ResponseBody
    	public JsonResult<Map<String,Object>> registerForAdmin(String mail, String code, String password,
    			String repassword, HttpServletRequest req, HttpServletResponse resp){
    		logger.info("管理员有效邮箱自主注册");
    		// 管理员注册
    		JsonResult<Map<String,Object>> rt = userManage.registerForAdminWithTx(mail, code, password, repassword, req, resp);

    		// 注册成功,调用登录接口
    		if (rt.getCode() == 0) {
        		UserVO user_ = new UserVO();
        		user_.setName(mail);
        		user_.setPassword(MD5Util.MD5(password));
        		user_.setPlatformId(7L);
        		JsonResult<User> result = userManage.login(user_, req, resp);

        		if (result.getCode() == 0) {
        			// 登录成功
        			User user = result.getData();
        			Map<String,Object> map = new HashMap<String,Object>();
        			map.put("id", user.getId());
        			map.put("name", user.getName());
        			map.put("cookieValue", user.getCookieValue());
        			map.put("isSuperAdministrator", user.getIsSuperAdministrator());

        			rt.setData(map);
        		} else {
        			// 登录失败
        			rt.setCode(result.getCode());
        			rt.setError(result.getError());
        		}
    		}

    		return rt;
    	}

    	/**
    	 * 根据用户Id集合解绑用户
    	 * @param req
    	 * @return
    	 */
    	@RequestMapping(value = "unbindByUserIds")
    	@ResponseBody
    	public JsonResult<Integer> unbindByUserIds(String userIds,HttpServletRequest req){
    		logger.info("根据用户Id集合解绑用户");
    		Long platformId = null;
    		String str = req.getHeader("platformId");
    		if(EmptyUtil.isNotEmpty(str)){
    			platformId = Long.valueOf(str);
    		}else{
    			return fail("平台id不能为空");
    		}
    		List<Long> userIdList = new ArrayList<>();
    		String[] split = userIds.split(",");
    		for (String string : split) {
    			userIdList.add(Long.valueOf(string));
			}
    		if(EmptyUtil.isEmpty(userIdList)){
    			throw new BusinessException("请选择账号");
    		}
    		return success(userManage.unbindByUserIds(userIdList,platformId));
    	}

    	/**
    	 * 释放员工导入zk锁
    	 * @param req
    	 * @return
    	 */
    	@RequestMapping(value = "releaseLockUserImport")
    	@ResponseBody
    	public void releaseLockUserImport(){
    		LogicLock lock = lockConfig.getLockManager().getLogicLock("assureImportUser");
    		if (lock.tryLock()) {
    			lock.unlock();
    		}
    	}

    	/**
    	 * 设置客户端缓存
    	 * @param req
    	 * @return
    	 * @throws Exception
    	 */
    	@RequestMapping(value = "saveClientCache")
    	@ResponseBody
    	public JsonResult<String> saveClientCache(String key,String value) throws Exception{
    		cache.set(key, value);
    		return success("success");
    	}

    	/**
    	 * 获取客户端缓存
    	 * @param req
    	 * @return
    	 */
    	@RequestMapping(value = "getClientCache")
    	@ResponseBody
    	public JsonResult<Object> getClientCache(String key){
    		Object value = cache.get(key);
    		cache.del(key);
			return success(value);
    	}

	@RequestMapping("/buildUserStatusAndPassword")
	@ResponseBody
	public JsonResult indexPassword(String nameCode,String mail,String password){
    		if(EmptyUtil.isEmpty(nameCode)||EmptyUtil.isEmpty(mail)){
				return fail("参数不能为空");
			}

		password = MD5Util.MD5(password);
		String salt = SaltUtils.getRandomSalt();
		String passwordSalt = MD5Support.MD5(password, salt);



		String sql1 = "update u_user_extend set status=1 where member_code ="+nameCode+";";
		String sql2 = "update u_user set password='"+passwordSalt+"',salt='"+salt+"' where mail='"+mail+"';";
		String sql3 = "update u_user u,u_user_extend ue set u.update_salt=CURRENT_TIMESTAMP,ue.account_status=0 where u.id=ue.id and u.mail='"+mail+"';";

		List<String> res = new ArrayList<>();
		res.add(sql1+sql2+sql3);

		return success(res);

	}



	@RequestMapping("findUserNews")
	@ResponseBody
	public JsonResult<Map<String, Object>> findUserNews(){
		CacheUser user = getCacheUser();
		return success(userManage.findUserNews(user.getId()));
	}




	/**
	 * 解绑手机邮箱验证
	 * @param mobile
	 * @param identityCode
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "changeMobileByMailVerification")
	@ResponseBody
	public JsonResult<String> changeMobileByMailVerification(String mail,String verificationCode,HttpServletRequest req){
		logger.info("解绑手机通过邮箱验证");
		String caseMail = null;
		//所有字母转小写
		if(EmptyUtil.isNotEmpty(mail)){
			caseMail = mail.toLowerCase();
		}
		if(EmptyUtil.isEmpty(caseMail)){
			return fail("邮箱不能为空");
		}
		//根据邮箱获取放入缓存中的验证码
		String redisVerificationCode = (String) cache.get(CacheKeyConstant.CHANGE_MOBILE_KEY+caseMail);
		if(EmptyUtil.isEmpty(redisVerificationCode)){
			return fail("验证码过期、请重新发送");
		}
		if(!redisVerificationCode.equals(MD5Support.MD5(verificationCode, caseMail))){
			throw new BusinessException(BusinessExceptionConstant.VERIFICATIONcODE_WRONG,"验证码错误");
		}
		// 使用成功之后删除缓存
		cache.del(CacheKeyConstant.CHANGE_MOBILE_KEY+caseMail);
		return success("邮箱验证成功");

	}

	@RequestMapping("/getUserMailList")
	@ResponseBody
	public JsonResult<List<String>> getUserMailList(HttpServletRequest req){
		logger.info("获取当前用户手机号绑定的所有账户的邮箱");
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();// 用户id
		String str = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(str)){
			return fail("平台id不能为空");
		}
		Long platformId = Long.valueOf(str);
		return userManage.getUserMailList(userId,platformId);


	}

	@RequestMapping(value = "openUserAccount")
	@ResponseBody
	public JsonResult<String> openUserAccount(Long userId,int accountType,Long platformId){
		logger.info("openUserAccount:userId-{},accountType-{},platformId-{}",userId,accountType,platformId);
		try {
			userManage.openUserAccountWithTx(userId,accountType,platformId);
		}catch (BusinessException businessException){
			logger.error(businessException.getMessage());
			return fail(businessException.getMessage());
		}catch (Exception ex){
			logger.error(ex.getMessage());
			return fail("添加失败");
		}
		return success("添加成功");
	}

	/**
	 * 根据用户id修改填充用户真实姓名和身份证号
	 * @param realName 真实姓名
	 * @param idCardNo 身份证号
	 * @return
	 */
	@RequestMapping(value = "fillRealNameAndIdCard")
	@ResponseBody
	public JsonResult<Integer> fillRealNameAndIdCard(String realName,String idCardNo,HttpServletRequest req) {
		logger.info("根据用户id修改填充用户真实姓名和身份证号");
		if(EmptyUtil.isEmpty(realName) || EmptyUtil.isBlank(realName)){
			return JsonResult.fail("请填写姓名");
		}
		if(EmptyUtil.isEmpty(idCardNo) || EmptyUtil.isBlank(idCardNo)){
			return JsonResult.fail("请求填写身份证号");
		}
		boolean isIdCard = IdCardUtil.isIDCard(idCardNo);
		if(!isIdCard){
			return JsonResult.fail("请填写正确的身份证号");
		}
		CacheUser cacheUser = getCacheUser();

		int rt = userManage.fillRealNameAndIdCard(cacheUser.getId(),realName,idCardNo);
		return success(rt);
	}

	@RequestMapping(value = "switchUserEnterprise")
	@ResponseBody
	public JsonResult<Integer> switchUserEnterprise(SwitchUserEnterpriseVO vo){
		return userManage.switchUserEnterprise(vo);
	}
}
