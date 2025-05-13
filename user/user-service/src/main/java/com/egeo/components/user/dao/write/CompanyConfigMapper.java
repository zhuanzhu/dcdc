package com.egeo.components.user.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.egeo.components.user.dto.CompanyConfig;


/**
 * 管理员 Mapper
 * 
 * @author zhou_k 2017年5月25日
 */

public interface CompanyConfigMapper {

	@Select("select * from company_config where company_id=#{id}")
	public List<CompanyConfig> findCompanyConfigs(@Param("id") Long companyId);
	@Select("select value from company_config where company_id=#{id} and key=#{key}")
	public String findCompanyConfigValue(@Param("id") Long companyId,@Param("key") String key);
	
	
}