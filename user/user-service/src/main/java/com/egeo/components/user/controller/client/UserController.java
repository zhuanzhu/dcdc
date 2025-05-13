package com.egeo.components.user.controller.client;

import java.util.List;

import com.egeo.components.user.business.UserManage;
import com.egeo.components.user.vo.OpenAccountReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dao.write.WxOpenidMapper;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.WxOpenidDTO;
import com.egeo.components.user.service.read.UserReadService;
import com.egeo.components.user.service.write.UserWriteService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/client/user/user")
public class UserController implements UserClient{

	@Autowired
	private UserReadService userReadService;
	@Autowired
	private UserWriteService userWriteService;
	@Autowired
	private CompanyConfigClient companyConfig;

	@Autowired
	private WxOpenidMapper wxOpenIdManage;

	@Resource(name="user")
	private UserManage userManage;

	@Override
	@RequestMapping(value = "/updateUserInfoWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateUserInfoWithTx(@RequestBody UserDTO user) {
		return userWriteService.updateUserInfoWithTx(user);
	}

	@Override
	@RequestMapping(value = "/findUser", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserDTO> findUser(@RequestBody UserDTO userDTO) {
		return userReadService.findUser(userDTO);
	}

	@Override
	@RequestMapping(value = "/findUserIdsByCompanyId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findUserIdsByCompanyId(@RequestParam("companyId") Long companyId,@RequestParam("isAdministrator") Integer isAdministrator) {
		return com.egeo.utils.StringUtils.longsToStrings(userReadService.findUserIdsByCompanyId(companyId,isAdministrator));
	}

	@Override
	@RequestMapping(value = "/findUserByID", method = { RequestMethod.POST })
	@ResponseBody
	public UserDTO findUserByID(@RequestBody  Long userId) {
		return userReadService.findUserByID(userId);
	}

	@Override
	@RequestMapping(value = "/findUsersByCompanyId", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserDTO> findUsersByCompanyId(@RequestBody  Long id) {
		return userReadService.findUsersByCompanyId(id);
	}

	@Override
	@RequestMapping(value = "/queryUserByIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<UserDTO> queryUserByIds(@RequestBody  List<String> userIdList) {
		return userReadService.queryUserByIds(com.egeo.utils.StringUtils.stringsToLongs(userIdList));
	}

	@Override
	@RequestMapping(value = "/findByMail", method = { RequestMethod.POST })
	@ResponseBody
	public UserDTO findByMail(@RequestBody  String mail) {
		return userReadService.findByMail(mail);
	}

	@Override
	@RequestMapping(value = { "/queryUsersByEmail" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<UserDTO> queryUsersByEmail(@RequestBody  String email) {
		// TODO Auto-generated method stub
		return userReadService.queryUsersByEmail(email);
	}


	@Override
	@RequestMapping(value = { "/companyConfigs" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<CompanyConfigDTO> findUserCompanyConfigs(@RequestBody Long userId) {
		// TODO Auto-generated method stub
		UserDTO userDTO = userReadService.findUserByID(userId);
		if(userDTO!=null && userDTO.getCompanyId()!=null) {
			List<CompanyConfigDTO> configs = companyConfig.queryCompanyconfigs(userDTO.getCompanyId());
			return configs;
		}
		return null;
	}
 // "/client/user/user/queryWxOpenid"


	@Override
	@RequestMapping(value = { "/queryWxOpenid" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<WxOpenidDTO> queryWxOpenid(@RequestBody  WxOpenidDTO wxOpenid) {
		// TODO Auto-generated method stub
		return wxOpenIdManage.findUserOpenIds(wxOpenid.getUserId(), wxOpenid.getWxOpenid());
	}

	@Override
	@RequestMapping(value = { "/openUserAccountBack" }, method = { RequestMethod.POST })
	@ResponseBody
	public void openUserAccountBack(@RequestBody OpenAccountReqVO vo) {
		if(CollectionUtils.isEmpty(vo.getUserIds()) || CollectionUtils.isEmpty(vo.getAccountTypes())){
			return;
		}
		// TODO Auto-generated method stub
		for (Long userId : vo.getUserIds()) {
			for (Integer accountType : vo.getAccountTypes()) {
				userManage.openUserAccountWithTx(userId,accountType,vo.getPlatformId());
			}
		}

	}
}
