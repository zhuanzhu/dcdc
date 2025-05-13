package com.egeo.components.user.client;
import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.user.dto.UserExtendAllByCompanyOfPageDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserExtendPageDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@FeignClient(name = "service-user-fgj",contextId="UserExtendClient")
public interface UserExtendClient {

	@RequestMapping(value = { "/client/user/userExtend/updateInvalidTime" }, method = { RequestMethod.POST }) 
	public int updateInvalidTime(UserExtendDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/userExtend/updateUserExtendWithTx" }, method = { RequestMethod.POST }) 
	public int updateUserExtendWithTx(UserExtendDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/userExtend/findById" }, method = { RequestMethod.POST }) 
	public UserExtendDTO findById(Long id); 
 
 
	@RequestMapping(value = { "/client/user/userExtend/userByUserId" }, method = { RequestMethod.POST }) 
	public UserExtendDTO userByUserId(Long userId); 
 
 
	@RequestMapping(value = { "/client/user/userExtend/findUserList" }, method = { RequestMethod.POST }) 
	public List<String> findUserList(@RequestParam("name") String name,@RequestParam("mail")  String mail,@RequestParam("mobile")  String mobile,@RequestParam("sex")  Integer sex,@RequestParam("birthday")  Date birthday,@RequestParam("companyId")  List<String> companyId,@RequestParam("platformId")  Long platformId); 
 
 
	@RequestMapping(value = { "/client/user/userExtend/queryUserByCondition" }, method = { RequestMethod.POST }) 
	public List<UserExtendDTO> queryUserByCondition(UserExtendDTO dto); 
 
 
	@RequestMapping(value = { "/client/user/userExtend/userExtendAllByCompanyOfPage" }, method = { RequestMethod.POST }) 
	public PageResult<UserExtendDTO> userExtendAllByCompanyOfPage(UserExtendAllByCompanyOfPageDTO dto); 

	@RequestMapping(value = { "/client/user/userExtend/findProductUser" }, method = { RequestMethod.POST }) 
	public PageResult<UserExtendDTO> findProductUser(UserExtendPageDTO dto); 
 

	/**
	 * 根据邮箱查询userExtend
	 * @param email
	 * @return
	 */
	@RequestMapping(value = { "/client/user/userExtend/queryUserExtendsByEmail" }, method = { RequestMethod.POST }) 
	public UserExtendDTO queryUserExtendsByEmail(String email);
	

	/**
	 * 关联表查询用户信息
	 * @param page
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = { "/client/user/userExtend/queryFullUserExtendPage" }, method = { RequestMethod.POST }) 
	public PageResult<UserExtendDTO> queryFullUserExtendPage(UserExtendPageDTO dto);

	@RequestMapping(value = { "/client/user/userExtend/queryFullUserExtend" }, method = { RequestMethod.POST }) 
	public List<UserExtendDTO> queryFullUserExtend(List<String> userIdList);
	
}