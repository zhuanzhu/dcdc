package com.egeo.components.user.controller.client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.UserExtendAllByCompanyOfPageDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserExtendPageDTO;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.components.user.service.write.UserExtendWriteService;
import com.egeo.orm.PageResult;

@Controller
@RequestMapping("/client/user/userExtend") 
public class UserExtendController implements UserExtendClient{ 

	@Autowired
	private UserExtendReadService userExtendReadService;
	@Autowired
	private UserExtendWriteService userExtendWriteService;


	@Override
	@RequestMapping(value = "/updateInvalidTime", method = { RequestMethod.POST })
	@ResponseBody
	public int updateInvalidTime(@RequestBody UserExtendDTO dto) {
		return userExtendWriteService.updateInvalidTime(dto);
	} 
 
	@Override
	@RequestMapping(value = "/updateUserExtendWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateUserExtendWithTx(@RequestBody UserExtendDTO dto) {
		return userExtendWriteService.updateUserExtendWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findById", method = { RequestMethod.POST })
	@ResponseBody
	public UserExtendDTO findById(@RequestBody Long id) {
		return userExtendReadService.findById(id);
	} 
 
	@Override
	@RequestMapping(value = "/userByUserId", method = { RequestMethod.POST })
	@ResponseBody
	public UserExtendDTO userByUserId(@RequestBody Long userId) {
		return userExtendReadService.userByUserId(userId);
	} 
 
	@Override
	@RequestMapping(value = "/findUserList", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findUserList(@RequestParam("name") String name,@RequestParam("mail")  String mail,@RequestParam("mobile")  String mobile,@RequestParam("sex")  Integer sex,@RequestParam("birthday")  Date birthday,@RequestParam("companyId")  List<String> companyId,@RequestParam("platformId")  Long platformId) {
		return com.egeo.utils.StringUtils.longsToStrings(userExtendReadService.findUserList(name, mail, mobile, sex, birthday, com.egeo.utils.StringUtils.stringsToLongs(companyId), platformId));
	} 
 
	@Override
	@RequestMapping(value = "/queryUserByCondition", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserExtendDTO> queryUserByCondition(@RequestBody UserExtendDTO dto) {
		return userExtendReadService.queryUserByCondition(dto);
	} 
 
	@Override
	@RequestMapping(value = "/userExtendAllByCompanyOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<UserExtendDTO> userExtendAllByCompanyOfPage(@RequestBody UserExtendAllByCompanyOfPageDTO dto) {
		return userExtendReadService.userExtendAllByCompanyOfPage(dto.getCompanyList(), dto.getPage());
	}

	@Override
	@RequestMapping(value = "/findProductUser", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<UserExtendDTO> findProductUser(@RequestBody UserExtendPageDTO dto) {
		// TODO Auto-generated method stub
		return userExtendReadService.findProductUser(dto.getPage(), dto.getDto());
	}

	@Override
	@RequestMapping(value = "/queryUserExtendsByEmail", method = { RequestMethod.POST })
	@ResponseBody
	public UserExtendDTO queryUserExtendsByEmail(@RequestBody String email) {
		// TODO Auto-generated method stub
		return userExtendReadService.queryUserExtendsByEmail(email);
	}

	@Override
	@RequestMapping(value = "/queryFullUserExtendPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<UserExtendDTO> queryFullUserExtendPage(@RequestBody UserExtendPageDTO dto) {
		// TODO Auto-generated method stub
		return userExtendReadService.queryFullUserExtendPage(dto.getPage(), dto.getDto());
	}

	@Override
	@RequestMapping(value = "/queryFullUserExtend", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserExtendDTO> queryFullUserExtend(@RequestBody List<String> userIdList) {
		// TODO Auto-generated method stub
		return userExtendReadService.queryFullUserExtend(com.egeo.utils.StringUtils.stringsToLongs(userIdList));
	} 

}