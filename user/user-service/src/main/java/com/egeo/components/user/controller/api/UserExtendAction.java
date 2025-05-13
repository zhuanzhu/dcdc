package com.egeo.components.user.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.user.business.StoreAdminManage;
import com.egeo.components.user.business.UserExtendManage;
import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserStoreAdminListDTO;
import com.egeo.components.user.vo.StoreUserAdminVO;
import com.egeo.components.user.vo.UserExtendVO;
import com.egeo.components.user.vo.UserWelfare;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.utils.excel.ExcelExportSXXSSF;
import com.egeo.utils.io.OnLineDownload;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/user/userExtend")
public class UserExtendAction extends BaseSpringController {

	@Resource(name = "userExtend")
	private UserExtendManage userExtendManage;

	@Resource(name="storeAdmin")
	private StoreAdminManage storeAdminManage;
	
	
	/**
	 * 完善用户信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "ImproveUserInformation")
	@ResponseBody
	public JsonResult<Map<String, Object>> ImproveUserInformation(String name, Long departmentId, Integer sex,
			Long birthday, HttpServletRequest req) {
		logger.info("大厨管家完善用户信息");
		CacheUser userCache = this.getCacheUser();
		Long platformId = null;
		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		Long userId = userCache.getId();
		if (StringUtils.isEmpty(name)) {
			throw new BusinessException(BusinessExceptionConstant.USER_NAME_NO_EMPTY, "用户姓名不能为空");
		}
		if (name.length() > 10) {
			throw new BusinessException(BusinessExceptionConstant.USER_NAME_NO_GREATER_TEN, "用户姓名不能超过10个");
		}
		if (StringUtils.isEmpty(departmentId)) {
			throw new BusinessException(BusinessExceptionConstant.USER_NAME_NO_GREATER_TEN, "部门不能为空");
		}
		if (StringUtils.isEmpty(sex)) {
			throw new BusinessException(BusinessExceptionConstant.USER_SEX_NO_EMPTY, "用户性别不能为空");
		}
		if (StringUtils.isEmpty(birthday)) {
			throw new BusinessException(BusinessExceptionConstant.USER_BIRTHDAY_NO_EMPTY, "用户生日不能为空");
		}
		JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();

		Map<String, Object> rows = userExtendManage.ImproveUserInformation(userId, name, departmentId, sex, birthday,platformId);

		result.setData(rows);
		return result;
	}

	/**
	 * 修改用户扩展表信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "updateUserExtend")
	@ResponseBody
	public JsonResult<String> updateUserExtend(String headPicUrl, String name, Integer sex, Long birthday,
			Long departmentId,Long entryTime,HttpServletRequest req) {
		logger.info("大厨管家修改用户扩展表信息(头像、真实姓名、性别、生日)");
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		if (StringUtils.isNotEmpty(name)) {
			if (name.length() > 10) {
				throw new BusinessException(BusinessExceptionConstant.USER_NAME_NO_GREATER_TEN, "用户姓名不能超过10个");
			}
		}
		JsonResult<String> result = new JsonResult<String>();

		int i = userExtendManage.updateUserExtend(userId, name, sex, birthday,departmentId);
		if(i != 0){
			result.setData("修改用户扩展信息成功");
		}else{
			result.setData("修改用户扩展信息失败");
		}
		return result;
	}

	/**
	 * 根据用户id查询大厨管家用户头像、真实姓名、公司等信息
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "userExtendByUserId")
	@ResponseBody
	public JsonResult<Map<String, Object>> userExtendByUserId(HttpServletRequest req) {
		logger.info("根据用户id查询大厨管家用户头像、真实姓名、公司等信息");
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();

		Map<String, Object> map = userExtendManage.userExtendByUserId(userId);

		result.setData(map);
		return result;
	}

	/**
	 * 根据用户id查询大厨管家个人信息接口
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "userByUserId")
	@ResponseBody
	public JsonResult<Map<String, Object>> userByUserId(String weiXinOpenId, HttpServletRequest req) {
		logger.info("根据用户id查询大厨管家个人信息接口");
		
		CacheUser userCache = RuntimeContext.cacheUser();
		if (StringUtils.isEmpty(userCache)) {
			throw new BusinessException(BusinessExceptionConstant.USER_NOT_LOGIN, "未登录或已过期，请登录！");
		}
		if (StringUtils.isEmpty(userCache.getId())) {
			throw new BusinessException(BusinessExceptionConstant.USER_NOT_LOGIN, "未登录或已过期，请登录！");
		}
		String clientId_ = req.getHeader("clientId");
		if (StringUtils.isEmpty(clientId_))
			return JsonResult.fail("客户端参数缺失");
		Long clientId = Long.valueOf(clientId_);
		
		Long platformId = null;
		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}else{
			return JsonResult.fail("平台id不能为空");
		}
		if(clientId.equals(2L) || clientId.equals(4L)){
			if(StringUtils.isEmpty(weiXinOpenId)) {
				weiXinOpenId = req.getHeader("weiXinOpenId");
			}
			if(StringUtils.isEmpty(weiXinOpenId)){
				return JsonResult.fail("微信OpenId参数缺失");
			}
		}
		Long userId = userCache.getId();
		JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();

		Map<String, Object> map = userExtendManage.userByUserId(userId,weiXinOpenId,platformId);

		result.setData(map);
		return result;
	}

	/**
	 * 根据公司id按条件查询所有用户信息
	 * 
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
	@RequestMapping(value = "userAllOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> userAllOfPage(Long companyId, String name, Long departmentId, Integer sex,
			Long birthdayStartTime, Long birthdayFinishTime, Long entryStartTime, Long entryFinishTime,Long channelId, String campaignCode,Long registerStoreId, Pagination page,
			HttpServletRequest req) {
		logger.info("根据公司id查询所有用户信息");
		Long platformId = null;
		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		JsonResult<PageResult<Map<String, Object>>> result = new JsonResult<PageResult<Map<String, Object>>>();
		PageResult<Map<String, Object>> pageResult = userExtendManage.userAllOfPage(
				companyId,name,departmentId,sex,birthdayStartTime,birthdayFinishTime,entryStartTime,entryFinishTime,
				channelId,campaignCode,registerStoreId,platformId,page);
		result.setData(pageResult);
		return result;
	}
	
	
	@RequestMapping(value = "userExtendAllOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> userExtendAllOfPage(Long companyId,  Integer isAvailable,String email, UserExtendVO vo,
			Long couponId, Long couponGroupId, Long birthdayStamp, Pagination page, HttpServletRequest req) {
		logger.info("根据公司id查询所有用户信息");
		Long platformId = null;
		long loginId = getCacheUser().getId();
		CacheUser cacheUser = getCacheUser();
		Long userCompanyId = cacheUser.getCompanyId();
		logger.info("当前登录人companyId:"+userCompanyId);

		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(vo);
		userExtendDTO.setPlatformId(platformId);
		if(StringUtils.isNotEmpty(email)){
			userExtendDTO.setMail(email.toLowerCase());
		}
		if (StringUtils.isNotEmpty(birthdayStamp)) {
			userExtendDTO.setBirthday(new Date(birthdayStamp));
		}
		
		
		
		
		JsonResult<PageResult<Map<String, Object>>> result = new JsonResult<PageResult<Map<String, Object>>>();
		PageResult<Map<String, Object>> pageResult = userExtendManage
				.userExtendAllOfPage(loginId,platformId,companyId, isAvailable, userExtendDTO, couponId, couponGroupId, page, userCompanyId);
		result.setData(pageResult);
		return result;
	}

	
	@RequestMapping(value = "staffOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> staffOfPage(Long companyId,  Integer isAvailable,String email, UserExtendVO vo,
			Long couponId, Long couponGroupId, Long birthdayStamp, Pagination page, HttpServletRequest req) {
		logger.info("根据公司id查询所有用户信息");
		Long platformId = null;
		long loginId = getCacheUser().getId();
		CacheUser cacheUser = getCacheUser();
		Long userCompanyId = cacheUser.getCompanyId();
		logger.info("当前登录人companyId:"+userCompanyId);

		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(vo);
		userExtendDTO.setPlatformId(platformId);
		if(StringUtils.isNotEmpty(email)){
			userExtendDTO.setMail(email.toLowerCase());
		}
		if (StringUtils.isNotEmpty(birthdayStamp)) {
			userExtendDTO.setBirthday(new Date(birthdayStamp));
		}
		
		
		
		
		JsonResult<PageResult<Map<String, Object>>> result = new JsonResult<PageResult<Map<String, Object>>>();
		PageResult<Map<String, Object>> pageResult = userExtendManage
				.userExtendAllOfPage(loginId,platformId,companyId, isAvailable, userExtendDTO, couponId, couponGroupId, page, userCompanyId);
		result.setData(pageResult);
		return result;
	}
	@RequestMapping(value = "userStoreAdmin")
	@ResponseBody
	public JsonResult<UserStoreAdminListDTO> userStoreAdmin(StoreUserAdminVO storeUserAdminVO, Pagination page, HttpServletRequest req) {
		logger.info("根据公司id按条件查询所有店铺管理员用户信息");
		Long platformId = null;
		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		UserExtendDTO userExtendDTO = new UserExtendDTO();
		userExtendDTO.setCompanyId(storeUserAdminVO.getCompanyId());
		userExtendDTO.setPlatformId(platformId);
		userExtendDTO.setStoreId(storeUserAdminVO.getStoreId());
		userExtendDTO.setMobile(storeUserAdminVO.getMobile());
		userExtendDTO.setAccountStatus(storeUserAdminVO.getAccount_status());
		List<UserExtendDTO> userStoreAdmin = userExtendManage.getUserAdminAll(userExtendDTO);
		userExtendDTO.setStoreId(-1L);
		List<UserExtendDTO> userAllAdmin = userExtendManage.getUserAdminAll(userExtendDTO);
		List<Long> storeAdminList = new ArrayList<Long>();
		List<UserExtendDTO> uList =  new ArrayList<UserExtendDTO>();
		List<UserExtendDTO> userList =  new ArrayList<UserExtendDTO>();
		
		for(UserExtendDTO u :userAllAdmin) {
			Boolean b  = true;
			 for(UserExtendDTO user: userStoreAdmin) {
				 if(u.getId().equals(user.getId())) {
					 b = false;
					 break;
				 }
			 }
			 if(b) {
				 uList.add(u);
			 }
		}
		 for(UserExtendDTO user: userStoreAdmin) {
			 storeAdminList.add(user.getId());
		 }
		userList.addAll(userStoreAdmin);
		userList.addAll(uList);

		List<Long> storeAdminIds = new ArrayList<Long>();
		StoreAdminDTO storeAdminDTO = new StoreAdminDTO();
		logger.info("storeId:"+storeUserAdminVO.getStoreId());
		storeAdminDTO.setStoreId(storeUserAdminVO.getStoreId());
		List<StoreAdminDTO> storeAdminAll = userExtendManage.getStoreAdminAll(storeAdminDTO);
		if (StringUtils.isNotEmpty(storeAdminAll)) {
			for (StoreAdminDTO storeAdmin : storeAdminAll) {
				storeAdminIds.add(storeAdmin.getUserId());
			}
		}

		UserStoreAdminListDTO urt = new UserStoreAdminListDTO();
		urt.setUids(storeAdminIds);
		urt.setUserList(userList);

		return JsonResult.success(urt);
	}	
	
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
	@RequestMapping(value = "userAll")
	@ResponseBody
	public JsonResult<String> userAll(Long companyId, String name, Long departmentId, Integer sex,
			Long birthdayStartTime, Long birthdayFinishTime, Long entryStartTime, Long entryFinishTime,String filePath,
			HttpServletRequest req,HttpServletResponse response) {
		logger.info("根据公司id按条件查询所有用户信息导出");
		Long platformId = null;
		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		JsonResult<String> result = new JsonResult<String>();
		//准备导出的数据，将数据存入list，且list中对象的字段名称必须是刚才传入ExcelExportSXXSSF的名称
		List<UserWelfare> list = userExtendManage.userAll(companyId,name,departmentId,sex,birthdayStartTime,birthdayFinishTime,entryStartTime,entryFinishTime,platformId);
		
		/**            导出文件存放物理路径
		 * @param fileWebPath
		 *            导出文件web下载路径
		 * @param filePrefix
		 *            导出文件名的前缀          
		 * @param flushRows
		 *            存放在内存的数据量
		 * @param fieldNames
		 *            导出文件列标题
		 * @param fieldCodes
		 * 			  导出数据对象的字段名称     
		 * @param flushRows*/
		//导出文件的前缀
		String filePrefix="egeo";
		//-1表示关闭自动刷新，手动控制写磁盘的时机，其它数据表示多少数据在内存保存，超过的则写入磁盘
		int flushRows=100;
		
		//指导导出数据的title
		List<String> fieldNames=new ArrayList<String>();
		fieldNames.add("用户编号");
		fieldNames.add("姓名");
		fieldNames.add("性别");
		fieldNames.add("出生日期");
		fieldNames.add("入职日期");
		fieldNames.add("邮箱");
		fieldNames.add("手机");
		fieldNames.add("部门名称");
		
		//告诉导出类数据list中对象的属性，让ExcelExportSXXSSF通过反射获取对象的值
		List<String> fieldCodes=new ArrayList<String>();
		fieldCodes.add("userId");//用户编号
		fieldCodes.add("name");//姓名
		fieldCodes.add("sex");//性别
		fieldCodes.add("birthday");//出生日期
		fieldCodes.add("entryTime");//入职日期
		fieldCodes.add("mail");//邮箱
		fieldCodes.add("mobile");//手机
		fieldCodes.add("departmentName");//部门名称
		
		
		//注意：fieldCodes和fieldNames个数必须相同且属性和title顺序一一对应，这样title和内容才一一对应
		
		
		try {
			//开始导出，执行一些workbook及sheet等对象的初始创建
			ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath, filePath, filePrefix, fieldNames, fieldCodes, flushRows);
			
			//执行导出
			excelExportSXXSSF.writeDatasByObject(list);
			//输出文件
			excelExportSXXSSF.exportFile(response);
			
		} catch (Exception e) {
			throw new BusinessException("数据导出异常");
		}
		result.setData("数据导出成功");
		return result;
	}
	
	
	/**
	 * 获取用户信息导入模板
	 */
	@RequestMapping(value = "userAllTemplate")
	@ResponseBody
	public void userAllTemplate(HttpServletRequest req,HttpServletResponse response) {
		try {
			//获取目标文件的绝对路径  
	        String fullFileName = req.getSession().getServletContext().getRealPath("/userTemplate.xls");  
			OnLineDownload.downLoad(fullFileName, response, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 后台新增用户信息
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "saveUserBackground")
	@ResponseBody
	public JsonResult<String> saveUserBackground(String mail,String headPicUrl, String name,Long companyId, Long departmentId, Integer sex,
			Long birthday, Long entryTime, HttpServletRequest req) {
		logger.info("大厨管家后台新增用户信息");
		String caseMail = null;
		JsonResult<String> result = new JsonResult<String>();
		//所有字母转小写
    	if(StringUtils.isNotEmpty(mail)){
    		caseMail = mail.toLowerCase();
    	}
		String rows = userExtendManage.saveUserBackground(caseMail,headPicUrl, name,companyId, departmentId, sex, birthday,
				entryTime);

		result.setData(rows);
		return result;
	}
	
	/**
		 * 后台修改用户信息
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "updateUserBackground")
	@ResponseBody
	public JsonResult<String> updateUserBackground(Long userId, String mail,String headPicUrl, String name,Long companyId, Long departmentId, Integer sex,
			Long birthday, Long entryTime, HttpServletRequest req) {
		logger.info("大厨管家后台修改用户信息");
		String caseMail = null;
		//所有字母转小写
    	if(StringUtils.isNotEmpty(mail)){
    		caseMail = mail.toLowerCase();
    	}
		JsonResult<String> result = new JsonResult<String>();
		String rows = userExtendManage.updateUserBackground(userId,caseMail,headPicUrl, name,companyId, departmentId, sex, birthday,
				entryTime);

		result.setData(rows);
		return result;
	}
	
	/**
	 * 后台修改用户头像信息
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "updateUserHeadPicUrl")
	@ResponseBody
	public JsonResult<String> updateUserBackground(String headPicUrl,HttpServletRequest req) {
		logger.info("大厨管家后台修改用户头像信息");
		CacheUser userCache = this.getCacheUser();
		Long userId = userCache.getId();
		JsonResult<String> result = new JsonResult<String>();
		userExtendManage.updateUserBackground(userId,headPicUrl);

		result.setData(headPicUrl);
		return result;
	}
	
	/**
	 * 根据客户端输入模糊查询用户,
	 * 查询的字段包括姓名和邮箱
	 * 不包含当前用户自己
	 * 只查询本公司
	 * 只查询姓名存在的
	 * 目前供点赞模块使用
	 * @param keyWord
	 * @return
	 */
	@RequestMapping(value = "searchUsers")
	@ResponseBody
	public JsonResult<Map<String,Object>> searchUsers(String keyWord,HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long userId=userCache.getId();
		Long companyId=userCache.getCompanyId();
		if(companyId==null)
			return JsonResult.fail("公司信息有误");
		return userExtendManage.searchUsers(keyWord,userId,companyId);
	}
	
	/**
	 * 重置员工状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "resetStatus")
	@ResponseBody
	public JsonResult<Map<String,Object>> resetStatus(Long id){
		if(StringUtils.isEmpty(id)){
			return JsonResult.fail("员工id不能为空");
		}
		
		return userExtendManage.resetStatus(id);
	}

	/**
	 *设定员工管理员身份
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "upadteBackAccountMannager")
	@ResponseBody
	public JsonResult<Map<String,Object>> updateBackAccountMannager(Long id,HttpServletRequest req) {
		logger.info("设定员工管理员身份");
		if(StringUtils.isEmpty(id)){
			return JsonResult.fail("员工id不能为空");
		}
		String clientId_ = req.getHeader("clientId");
		Long clientId = Long.valueOf(clientId_);
		//判断此员工是否为门店管理员
		StoreAdminDTO storeAdminDTO = new StoreAdminDTO();
		storeAdminDTO.setUserId(id);
		List<StoreAdminDTO> storeAdminAll = userExtendManage.getStoreAdminAll(storeAdminDTO);
		if (StringUtils.isNotEmpty(storeAdminAll)) {
			//删除此员工门店管理员身份
			storeAdminManage.deleteStoreAdminWithTx(storeAdminDTO);
		}
		return userExtendManage.updateBackAccountMannager(id,clientId);
	}


	/**
	 * 查询所有管理员用户
	 * @return
	 */
	@RequestMapping(value = "userAdminAll")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> userAdminAll(){
		logger.info("查询所有管理员用户");
		return JsonResult.success(userExtendManage.userAdminAll());
	}
	/**
	 * 根据当前用户手机号查询关联用户
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "userByMobile")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> userByMobile(HttpServletRequest req){
		CacheUser userCache = this.getCacheUser();
		Long platformId = null;
		String str = req.getHeader("platformId");
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		List<Map<String,Object>> userList = new ArrayList<>();
		if(StringUtils.isNotEmpty(userCache.getMobile())){
			// 根据用户手机号查询关联用户
			userList = userExtendManage.userByMobile(userCache.getMobile(),platformId);
		}
		return JsonResult.success(userList);
	}
	/**
	 * 根据百度云推送channel_id绑定当前用户
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "bindingChannelIdByUserId")
	@ResponseBody
	public JsonResult<Map<String, Object>> bindingChannelIdByUserId(String baiDuChannelId,HttpServletRequest req){
		CacheUser cacheUser = this.getCacheUser();
		String device_type = req.getHeader("f");
		Integer deviceType = null;
		if(StringUtils.isNotEmpty(device_type))
			deviceType = Integer.valueOf(device_type);
		int i = userExtendManage.bindingChannelIdByUserId(cacheUser.getId(),baiDuChannelId,deviceType);
		Map<String, Object> map = new HashMap<>();
		map.put("isTrue",i);
		return JsonResult.success(map);
	}
	
}
