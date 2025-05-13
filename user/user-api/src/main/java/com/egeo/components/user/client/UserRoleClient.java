package com.egeo.components.user.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.user.dto.UserRoleDTO;


@FeignClient(name = "service-user-fgj",contextId="UserRoleClient")
public interface UserRoleClient {

	@RequestMapping(value = { "/client/user/userRole/findUserRoleAll" }, method = { RequestMethod.POST }) 
	List<UserRoleDTO> findUserRoleAll(UserRoleDTO dto); 
 
} 
