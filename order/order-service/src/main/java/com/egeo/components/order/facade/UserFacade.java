package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.PlatformClient;
import com.egeo.components.user.client.SupplierClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;

@Component
public class UserFacade {

	@Autowired
	private UserExtendClient ueReadService;
	
	@Autowired
	private UserClient uReadService;
	
	@Autowired
	private PlatformClient pltReadService;
	
	@Autowired
	private CompanyClient compReadService;
	@Autowired
	private SupplierClient supplierReadService;
	
	
//	@Resource
//	private UserExtendWriteService ueWriteService;
	
	
	public UserExtendDTO queryUserExtendById(Long id) {
		return ueReadService.findById(id);
	}
	
	public UserDTO queryUserById(Long id) {
		return uReadService.findUserByID(id);
	}

	public PlatformDTO queryPlatformById(Long platformId) {
		return pltReadService.find(platformId);
	}

	/**
	 * 根据公司id查询公司名称
	 * @param companyId
	 * @return
	 */
	public CompanyDTO queryCompanyById(Long companyId) {
		return compReadService.findCompanyById(companyId);
	}

	/**
	 * 根据Email模糊查询用户
	 * @param email
	 * @return
	 */
	public List<UserDTO> queryUsersByEmail(String email) {
		return uReadService.queryUsersByEmail(email);
	}

	/**
	 * 根据名称查询公司
	 * @param companyName
	 * @return
	 */
	public CompanyDTO queryCompanyByName(String name) {
		return compReadService.queryCompanyByName(name);
	}

	/**
	 * 查询所有公司
	 * @param dto
	 * @return
	 */
	public List<CompanyDTO> findCompanyAll(CompanyDTO dto) {
		return compReadService.findCompanyAll(dto);
	}

    public UserDTO findUserById(Long userId) {
		return uReadService.findUserByID(userId);
	}

	public UserExtendDTO findUserExtendById(Long userId) {
		return ueReadService.findById(userId);
	}
	public Enterprise findSupplier(Integer supplierId) {
		return supplierReadService.findById(supplierId);
	}

	public List<UserDTO> queryUserByIds(List<String> userIdList){
		return uReadService.queryUserByIds(userIdList);
	}

}
