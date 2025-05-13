package com.egeo.components.user.business;


import java.util.List;
import java.util.Map;

import com.egeo.components.user.vo.UserExtendVO;
import com.egeo.components.user.vo.UserWelfare;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface UserExtendManage {
	
	UserExtendDTO findById(Long id);
	/**
     * 完善用户扩展表信息
     * @param dto
     * @return
     */
	int saveUserExtend(UserExtendVO userExtendVO);
	/**
     * 完善用户信息
     * @param dto
     * @return
     */
	Map<String, Object> ImproveUserInformation(Long userId, String name, Long companyId, Integer sex,
			Long birthday,Long platformId);
	/**
	 * 添加扩展表
	 * @param userId
	 * @param entryTime
	 * @return
	 */
	Long saveUserWelfareWithTx(Long userId, Long departmentId, Long entryTime);
	/**
     * 修改用户扩展表信息(头像、真实姓名、性别、生日)
     * @param dto
     * @return
     */
	int updateUserExtend(Long userId, String name, Integer sex, Long birthday,Long departmentId);
	/**
     * 根据用户id查询修改用户头像、真实姓名、公司等信息
     * @param dto
     * @return
     */
	Map<String, Object> userExtendByUserId(Long userId);
	/**
     * 根据用户id查询个人信息接口
     * @param dto
     * @return
     */
	Map<String, Object> userByUserId(Long userId,String weiXinOpenId,Long platformId);
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
	PageResult<Map<String, Object>> userAllOfPage(Long companyId, String name, Long departmentId, Integer sex,
			Long birthdayStartTime, Long birthdayFinishTime, Long entryStartTime, Long entryFinishTime,Long channelId,String campaignCode,
			Long registerStoreId,Long platformId,Pagination page);
	/**
	 * 后台新增用户信息
	 * @param dto
	 * @return
	 */
	String saveUserBackground(String mail, String headPicUrl, String name,Long companyId, Long departmentId, Integer sex,
			Long birthday, Long entryTime);
	/**
	 * 后台修改用户信息
	 * @param dto
	 * @return
	 */
	String updateUserBackground(Long userId, String mail, String headPicUrl, String name,Long companyId, Long departmentId,
			Integer sex, Long birthday, Long entryTime);
	/**
	 * 根据公司id按条件查询所有用户信息导出
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
	List<UserWelfare> userAll(Long companyId, String name, Long departmentId, Integer sex,
			Long birthdayStartTime, Long birthdayFinishTime, Long entryStartTime, Long entryFinishTime, Long platformId);
	
	/**
	 * 根据客户端输入模糊查询用户,
	 * 查询的字段包括姓名和邮箱,
	 * 不包含当前用户自己,
	 * 目前供点赞模块使用
	 * @param keyWord
	 * @param userId
	 * @param companyId
	 * @return
	 */
	JsonResult<Map<String,Object>> searchUsers(String keyWord, Long userId, Long companyId);
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	int updateUserBackground(Long userId, String headPicUrl);
	/**
	 * 根据条件查询userExtend
	 * @param companyId
	 * @param isAvailable
	 * @param userExtendDTO
	 * @param platformId
	 * @param page
	 * @return
	 */
	PageResult<Map<String, Object>> userExtendAllOfPage(Long loginId,Long platformId,Long companyId, Integer isAvailable, UserExtendDTO userExtendDTO,
			Long couponId, Long couponGroupId, Pagination page,Long userCompanyId);
	
	/**
	 * 重置员工状态
	 * @param id
	 * @return
	 */
	JsonResult<Map<String, Object>> resetStatus(Long id);

	/**
	 * 设定员工管理员身份
	 * @param id
	 * @return
	 */
	JsonResult<Map<String, Object>> updateBackAccountMannager(Long id,Long clientId);
	/**
	 * 查询所有管理员用户
	 * @return
	 */
	List<Map<String, Object>> userAdminAll();
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	List<Map<String, Object>> userByMobile(String mobile,Long platformId);
	/**
	 * 查询符合条件的用户
	 * @param dto
	 * @return
	 */
	List<UserExtendDTO> queryUserByCondition(UserExtendDTO dto);
	
	/**
	 * 更新用户拓展信息
	 * @param userExtend
	 * @return
	 */
	int updateUserExtendInfo(UserExtendDTO userExtend);
	
	/**
	 * 根据用户手机号查询关联用户
	 * @param mobile
	 * @return
	 */
	List<UserExtendDTO> userExtendByMobile(String mobile,Long platformId);
	/**
	 * 切换用户OpenId
	 * @param id
	 * @param userId
	 * @return
	 */
	int switchUserOpenId(Long id, Long userId);
	/**
	 * 根据用户id注销微信登录
	 * @param id
	 * @param userId
	 * @return
	 */
	int weixinSignoutWithTx(String mobile);
	/**
	 * 根据百度云推送channel_id绑定当前用户
	 * @param userId
	 * @param baiDuChannelId
	 * @return
	 */
	int bindingChannelIdByUserId(Long userId, String baiDuChannelId,Integer deviceType);
	/**
	 * 根据userId退出
	 * @param userId
	 * @return
	 */
	int signoutByUserId(Long userId);
	/**
	 * 根据用户手机号查询管理员用户信息(只返回id、name)
	 * @param mobile
	 * @param platformId
	 * @return
	 */
	UserExtendDTO findAdminUserByManage(String mobile, Long platformId);

	List<StoreAdminDTO> getStoreAdminAll(StoreAdminDTO storeAdminDTO);
	
	List<UserExtendDTO> getUserAdminAll(UserExtendDTO userExtendDTO);


    void updateUserExtendWithTx(UserExtendVO userExtendVO);
}
	