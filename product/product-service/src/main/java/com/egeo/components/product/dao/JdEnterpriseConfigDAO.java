package com.egeo.components.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.egeo.components.product.dto.JdEnterpriseConfigDTO;
import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.orm.BaseWriteDAO;

@Mapper
public interface JdEnterpriseConfigDAO{
	/**
	 * 根据属性id更新属性范围信息
	 * @param attributeNameDecimalDTO
	 * @return
	 */
	@Select("SELECT * FROM jd_enterprise_config c WHERE c.`merchant_id` = #{enterpriseId} OR c.`merchant_id` IS NULL")
	List<JdEnterpriseConfigDTO> findAllConfigOfEnterprise(@Param("enterpriseId") Long enterpriseId);
	@Select("SELECT * FROM jd_enterprise_config c")
	List<JdEnterpriseConfigDTO> findAllConfig();
	

	@Select("SELECT * FROM jd_enterprise_config c WHERE c.type =1 ORDER BY c.`id` DESC LIMIT 0,1")
	List<JdEnterpriseConfigDTO> findConfigOfPlatform();
}
	