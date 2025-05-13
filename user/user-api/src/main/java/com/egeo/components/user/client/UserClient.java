package com.egeo.components.user.client;
import java.util.List;

import com.egeo.components.user.vo.OpenAccountReqVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.WxOpenidDTO;


@FeignClient(name = "service-user-fgj",contextId="UserClient")
public interface UserClient {

	@RequestMapping(value = { "/client/user/user/updateUserInfoWithTx" }, method = { RequestMethod.POST })
	public int updateUserInfoWithTx(UserDTO user);

	@RequestMapping(value = { "/client/user/user/companyConfigs" }, method = { RequestMethod.POST })
	public List<CompanyConfigDTO> findUserCompanyConfigs(Long userId);

	@RequestMapping(value = { "/client/user/user/findUser" }, method = { RequestMethod.POST })
	public List<UserDTO> findUser(UserDTO userDTO);


	@RequestMapping(value = { "/client/user/user/findUserIdsByCompanyId" }, method = { RequestMethod.POST })
	public List<String> findUserIdsByCompanyId(@RequestParam("companyId") Long companyId,@RequestParam("isAdministrator") Integer isAdministrator);


	@RequestMapping(value = { "/client/user/user/findUserByID" }, method = { RequestMethod.POST })
	public UserDTO findUserByID(Long userId);


	@RequestMapping(value = { "/client/user/user/findUsersByCompanyId" }, method = { RequestMethod.POST })
	public List<UserDTO> findUsersByCompanyId(Long id);


	@RequestMapping(value = { "/client/user/user/queryUserByIds" }, method = { RequestMethod.POST })
	public List<UserDTO> queryUserByIds(List<String> userIdList);


	@RequestMapping(value = { "/client/user/user/findByMail" }, method = { RequestMethod.POST })
	public UserDTO findByMail(String mail);

	/**
	 * 根据Email模糊查询用户
	 * @param email
	 * @return
	 */
	@RequestMapping(value = { "/client/user/user/queryUsersByEmail" }, method = { RequestMethod.POST })
	public List<UserDTO> queryUsersByEmail(String email);

	@RequestMapping(value = { "/client/user/user/queryWxOpenid" }, method = { RequestMethod.POST })
	public List<WxOpenidDTO> queryWxOpenid(WxOpenidDTO wxOpenid);

	@RequestMapping(value = { "/client/user/user/openUserAccountBack" }, method = { RequestMethod.POST })
	public void openUserAccountBack(OpenAccountReqVO vo);
}
