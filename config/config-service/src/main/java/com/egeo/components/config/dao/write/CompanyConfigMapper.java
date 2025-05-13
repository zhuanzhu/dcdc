package com.egeo.components.config.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.egeo.components.config.dto.CompanyConfigDTO;


/**
 * 管理员 Mapper
 * 
 * @author zhou_k 2017年5月25日
 */

public interface CompanyConfigMapper {

	@Select("select * from company_config t where t.companyId=#{id}")
	public List<CompanyConfigDTO> findCompanyConfigs(@Param("id") Long companyId);
	@Select("select value from company_config t where t.companyId=#{id} and t.key=#{key}")
	public String findCompanyConfigValue(@Param("id") Long companyId,@Param("key") String key);
	@Select("select * from company_config t where  t.key='company.code' and t.value=#{code}")
	public CompanyConfigDTO findCompanyCodeValue(@Param("code") String code);
	
	
}