package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.InsuranceLoginDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InsuranceLoginReadService {

	public InsuranceLoginDTO findInsuranceLoginById(InsuranceLoginDTO dto);

	public PageResult<InsuranceLoginDTO> findInsuranceLoginOfPage(InsuranceLoginDTO dto,Pagination page);

	public List<InsuranceLoginDTO> findInsuranceLoginAll(InsuranceLoginDTO dto);

	/**
	 * 查询用户的第三方保险登陆信息
	 * @param userId
	 * @return
	 */
	public InsuranceLoginDTO queryInsuranceLoginByUserId(Long userId);
}
	