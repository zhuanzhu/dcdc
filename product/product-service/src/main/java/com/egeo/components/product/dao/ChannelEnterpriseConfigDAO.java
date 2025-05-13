package com.egeo.components.product.dao;

import com.egeo.components.product.dto.ChannelEnterpriseConfigDTO;
import com.egeo.components.product.dto.JdEnterpriseConfigDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelEnterpriseConfigDAO {
	/**
	 * @return
	 */
	@Select("SELECT * FROM channel_enterprise_config c WHERE c.`merchant_id` = #{enterpriseId} OR c.`merchant_id` IS NULL")
	List<ChannelEnterpriseConfigDTO> findAllConfigOfEnterprise(@Param("enterpriseId") Long enterpriseId);
	@Select("SELECT * FROM channel_enterprise_config c")
	List<ChannelEnterpriseConfigDTO> findAllConfig1();

	@Select("SELECT * FROM channel_enterprise_config c where c.channel_code =#{channelCode}")
	List<ChannelEnterpriseConfigDTO> findAllConfig(@Param("channelCode") String channelCode);


	@Select("SELECT * FROM channel_enterprise_config c WHERE c.type =1 ORDER BY c.`id` DESC LIMIT 0,1")
	List<ChannelEnterpriseConfigDTO> findConfigOfPlatform();

	/**
	 * @return
	 */
	@Select("SELECT * FROM channel_enterprise_config c WHERE c.`merchant_id` = #{enterpriseId} and c.`channel_code`=#{channelCode}")
	List<ChannelEnterpriseConfigDTO> findAllConfigOfEnterprise1(@Param("enterpriseId") Long enterpriseId,@Param("channelCode") String channelCode);
}
