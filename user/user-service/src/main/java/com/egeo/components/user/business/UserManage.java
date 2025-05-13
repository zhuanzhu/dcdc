package com.egeo.components.user.business;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egeo.components.user.controller.views.request.UniAuthUserInfoAddParam;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.User;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.vo.*;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.vo.BaseResponse;
import com.egeo.web.JsonResult;

public interface UserManage {

    UserVO findUniqueUser(UserVO user);

    /**
     * 根据id查用户信息
     *
     * @param user
     * @return
     */
    UserDTO findUserByID(Long userId);

    /**
     * 修改用户密码
     *
     * @param user
     */
    int updateEncryptInfoByUserWithTx(UserDTO updUser);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUserInfo(UserDTO user);
    BaseResponse<String> updateManageUser(UniAuthUserInfoAddParam user);

    int updateEncryptByNewEncryptWithTx(PassWordVO passWord, HttpServletRequest req);

    // 查询所有用户
    public PageResult<UserDTO> findPage(Pagination page, UserVO userVO, UserExtendVO userExtendVO,
            UserCookieVO userCookieVO,Integer... types);
    // 查询所有用户
    public PageResult<UserDTO> findPage(Pagination page, UserVO userVO,Integer... types);


    public PageResult<UserDTO> findPage(Pagination page,String sysCode, Long rid, UserVO userVO,Integer... types);
    // 查询所有用户
    public PageResult<UserDTO> findManagePage(Pagination page, UserVO userVO);
    /* String saveUser(UserDTO user); */

    // 更新用户
    public String saveOrUpdate(UserVO userVO, UserExtendVO userExtendVO, UserCookieVO userCookieVO);
    //保存或者更新用户
	Long insertOrUpdateUser(UserTempConditionVO vo,String dePartMentIdArr);
	public Long insertOrUpdateManageUser(String loginName,String mobile,String email,String newPwd, Long enterpriseId, Long companyId,String roleIds, Long id,Integer type);
    // 根据id删除用户
    void deleteWithTx(UserDTO dto);

    UserDTO userById(UserVO vo);

	PageResult<ProductUser> findProductUser(Pagination page, UserVO userVO, UserExtendVO userExtendVO);

	ProductUser productUserById(ProductUser vo);

	/**
	 * 查询公司部门列表
	 * @param companyId
	 * @return
	 */
	JsonResult<Map<String, Object>> companyDepartmentList(Long companyId);

	/**
	 * 查询部门员工列表
	 * @param departmentId
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	JsonResult<Map<String, Object>> departMentMemberList(Long departmentId,Long companyId, Integer pageNo, Integer pageSize);
	/**
	 * 根据邮箱查询用户
	 * @param mail
	 * @return
	 */
	UserVO userByMail(String mail);
	/**
     * 用户注册
     * @param dto
     * @return
     */
	Long userRegister(UserVO vo,String shortCode);
	/**
	 * 根据邮箱修改密码
	 * @param vo
	 * @param mobile
     * @return
	 */
	int updateUserPasswordByMail(String mobile, Long userId, String mail, String password, Long platformId);

	/**
	 * 绑定是手机的时候发送4位验证码
	 *
     *
	 * @param companyId
	 * @param type
     * @param mobile
     * @param i
     * @return
	 */
	Long sendIdentifyingCode(Long companyId, int type, String mobile, int i, String redisKey, Long platformId);

	/**
	 * 给用户绑定手机号
	 * @param mobile
	 * @param identityCode
	 * @param userId
	 * @return
	 */
	String bindingMobile(String mobile, String identityCode, Long userId,int type,String beforeMobile,String weiXinOpenId,Long platformId);
	/**
	 * 根据用户手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	List<UserVO> findByMobileAndRegister(String mobile,Long platformId);
	/**
     * 用户设置支付密码
     * @param dto
     * @return
     */
	int setPaymentPassword(String mail, String verificationCode, String password, String confirmPassword, Long platformId);

	/**
	 * 导入用户
	 * @param companyId
	 * @param tempType
	 * @param platformId
	 * @param valueList
	 * @return
	 */
	JsonResult<Map<String, Object>> importUserWithTx(Long companyId, Integer tempType, Long platformId,
			List<Map<String, Object>> valueList,HttpServletRequest req);

	/**
	 * 确认导入用户
	 * @param companyId
	 * @param importOrderInfo
	 * @param userOrderInfo
	 * @return
	 */
	Long assureImportUser(Long companyId,Long platformId ,Long parseLong, String userOrderInfo);

	/**
	 * 员工名单重导入
	 * @param companyId
	 * @param tempType
	 * @param platformId
	 * @param valueList
	 * @param req
	 * @return
	 */
	JsonResult<Map<String, Object>> reImportUserWithTx(Long companyId, Integer tempType, Long platformId,
													 List<Map<String, Object>> valueList,HttpServletRequest req);
	/**
	 * 确认员工名单重导入
	 * @param companyId
	 * @param platformId
	 * @param parseLong
	 * @param userOrderInfo
	 * @return
	 */
	Long assureReImportUser(Long companyId,Long platformId ,Long parseLong, String userOrderInfo);

	/**
	 * 导入离职员工
	 * @param companyId
	 * @param tempType
	 * @param platformId
	 * @param valueList
	 * @return
	 */
	JsonResult<Map<String, Object>> importQuitUserWithTx(Long companyId, Integer tempType, Long platformId,
			List<Map<String, Object>> valueList, HttpServletRequest req);

	/**
	 * 确定导入离职员工
	 * @param companyId
	 * @param platformId
	 * @param importOrder
	 * @param userQuitOrderInfo
	 * @return
	 */
	Long assureImportQuitUser(Long companyId, Long platformId, Long importOrder, String userQuitOrderInfo);

	/**
	 * 查询用户的相关信息
	 * @param userId
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> findAboutUserByUserId(Long userId, Long platformId);

	/**
	 * 检验用户
	 * @param memberCode
	 * @param platformId
	 * @param object
	 * @return
	 */
	int findUserByMemberCode(Long companyId,String memberCode, Long platformId);

	/**查询用户
	 * @param userDTO
	 * @return
	 */
	List<UserDTO> findUser(UserDTO userDTO);

	/**
	 * 导入员工部门
	 * @param companyId
	 * @param tempType
	 * @param platformId
	 * @param valueList
	 * @return
	 */
	JsonResult<Map<String, Object>> importUserDeptWithTx(Long companyId, Integer tempType, Long platformId,
			List<Map<String, Object>> valueList, HttpServletRequest req);

	/**
	 * 确认导入员工部门
	 * @param companyId
	 * @param platformId
	 * @param parseLong
	 * @param userOrderInfo
	 * @return
	 */
	Long assureImportUserDept(Long companyId, Long platformId, Long parseLong, String userOrderInfo,int type);
//	/**
//     * 用户验证支付密码
//     * @param dto
//     * @return
//     */
//	boolean verificationPaymentPassword(Long userId, String paymentPassword);

	/**
	 * 查询用户支付信息
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String, Object>> userPayInfo(Long userId);
	/**
	 * 用户确认支付并冻结福币
	 * @param userId
	 * @param paymentPassword
	 * @param orderCode
	 * @return
	 */
	//Integer verificationPaymentPasswordAndFoscoinAccount(Long userId, String paymentPassword, String orderCode,Long platformId);
	/**
	 * 根据用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	int updateUserPassword(Long userId, String password,String originalPassword);

	/**
	 * 根据管理用户id修改用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public int resetManageUserPasswordWithTx(Long userId, String password);



	/**
	 * 第三方保险登陆
	 * @param userId
	 * @param type
	 * @return
	 */
	JsonResult<Map<String, Object>> insurance3rdLogin(Long userId, Integer type);
	/**
	 * 根据用户Id集合解绑用户
	 * @param req
	 * @return
	 */
	Integer unbindByUserIds(List<Long> userIdList,Long platformId);
	/**
	 * 根据用户手机号及用户id判断该手机号是否绑定该用户并返回数据
	 * @param userId
	 * @param mobile
	 * @return
	 */
	UserVO findByUserIdMobile(Long userId, String mobile,Long platformId);

	/**
	 * 赋予用户特定权限
	 * @param user
	 * @param roleId
	 */
	void saveUserPermission(UserDTO user, Long roleId);

	/**
	 * 管理员用户自主注册
	 * @param mail
	 * @param code
	 * @param password
	 * @param repassword
	 * @return
	 */
	JsonResult<Map<String, Object>> registerForAdminWithTx(String mail, String code, String password, String repassword,
			HttpServletRequest req, HttpServletResponse resp);

	/**
	 * 用户登录
	 * @param user_
	 * @param req
	 * @param resp
	 * @return
	 */
	JsonResult<User> login(UserVO user, HttpServletRequest req, HttpServletResponse resp);


	JsonResult<User> manageLogin(UserVO user, HttpServletRequest req, HttpServletResponse resp);
	/**
	 * 根据微信OpenId查询用户信息、最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	UserDTO findByWeiXinOpenId(String openId, Long platformId);
	/**
	 * 根据手机号平台id查询用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	List<UserDTO> findListByManage(String mobile, Long platformId);
	/**
	 * 用户根据手机号设置支付密码
	 * @param mobile
	 * @param verificationCode
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	int setPaymentPasswordByMobile(String mobile, String verificationCode, String password, String confirmPassword,Long userId, Long platformId);
	/**
	 * 根据用户手机号查询管理员用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserVO findAdminUserByManage(String mobile, Long platformId);

	/**
	 * 根据微信OpenId查询用户信息、最近登录的或者最新创建的已激活账号登录
	 * @param openId
	 * @return
	 */
	UserDTO findLatestLoginByWeiXinOpenId(String openId, Long platformId);
	/**
	 * 根据用户手机号查询最近登录的用户信息
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserVO findLatestLoginByMobile(String mobile,Long platformId);

	/**
	 * 自动领取注册优惠券
	 * @param userVO
	 * @return
	 */
	void autoReceiveRegisterCoupon(UserVO userVO);

    void clearImportMemoryBackStage(Long companyId, Integer type, Long platformId);

	List<UserVO> findUserByMobile(String mobile, Long platformId);

	int activateUserByIds(List<Long> userIds);

	Map<String, Object> findUserNews(Long userId);

    JsonResult<List<String>> getUserMailList(Long userId, Long platformId);

    CompanyDTO findCompanyById(String companyId);

	void workWechatOAuth( HttpServletResponse response) throws IOException;

	UserDTO createUser(String wxUserId, String name, String mobile, String email, Long companyId, String deviceId);


	UserDTO createManageUser(String account,String passwd, String name, String mobile, String email, Integer type, Long enterpriseId, Long companyId,Set<Long> roles);

    String getWorkWeChatAccessToken(JedisUtil cache, String companyId);

    List<CompanyDTO> findCompanyByParam(CompanyDTO companyDTO);

	void saveCompany(CompanyDTO dto);

    String getWorkWeChatSuiteAccessToken(JedisUtil cache);

    void saveUserByMail(String id, String mail);

    UserVO registDefaultCompanyUserByMobile(String mobile,String passwd,String wxOpenId, Long platformId,Long companyId);

	JsonResult<User> userLogin(UserVO user,String wxOpenId,HttpServletRequest req, HttpServletResponse resp);

	JsonResult<User> registerAndLoginDefaultCompanyUserByMobile(String mobile,String passwd,String wxOpenId, Long platformId,Long companyId,HttpServletRequest req, HttpServletResponse resp);

	void openUserAccountWithTx(Long userId,Integer type,Long platformId);

	UserVO switchLoginOpenId(String wxOpenId,Long platformId);

	int fillRealNameAndIdCard(Long userId,String realName,String idCardNo);

	/**
	 * 根据渠道用户唯一标识查询用户信息
	 *
	 * @return
	 */
	List<UserVO> findByChannelUserUniqueAndRegister(String channelUserUnique,Long companyId,Long platformId);

	UserVO registDefaultCompanyUserByChannelUserUnique(String channelUserUnique, String name, String passwd, String wxOpenId, Long platformId, Long companyId, ChannelUserUniqueRegLoginVO vo);

	/**
	 * 根据用户手机号查询最近登录的用户信息
	 * @param channelUserUnique
	 * @param platformId
	 * @return
	 */
	UserVO findLatestLoginByChannelUserUnique(String channelUserUnique,Long companyId,Long platformId);

	JsonResult<Integer> switchUserEnterprise(SwitchUserEnterpriseVO vo);
}
