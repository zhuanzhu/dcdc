package com.egeo.components.product.dao.write;

import com.egeo.components.product.dto.ChannelMerchantConfigDTO;
import com.egeo.components.product.dto.JdMerchantConfigDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 管理员 Mapper
 *
 * @author zhou_k 2017年5月25日
 */

public interface ChannelMerchantConfigMapper {

	/**
	 * @return  企业修改信息
	 */
	/*@Update("update u_enterprise t set t.audit_status=0, t.name=#{name},t.address=#{address},t.contact=#{contact} ,t.update_millis=#{updateMillis} where t.id=#{id}")
	public int update(JdMerchantConfigDTO data);*/

	@Select("select * from channel_enterprise_config")
	public List<ChannelMerchantConfigDTO> findEnterpriseConfigAll();

	@Select("select * from channel_enterprise_config where channel_code =#{channelCode}")
	public List<ChannelMerchantConfigDTO> findEnterpriseConfigAllByChannelCode(@Param("channelCode") String channelCode);

	//2.查询所有审核通过且合作中的代理商
	@Select("select * from channel_enterprise_config where merchant_id=#{merchantId}")
	public List<ChannelMerchantConfigDTO> findEnterpriseConfig(@Param("merchantId") Integer merchantId);

	@Select("select * from channel_enterprise_config where merchant_id=#{merchantId} and channel_code =#{channelCode}")
	public List<ChannelMerchantConfigDTO> findEnterpriseConfigAndCode(@Param("merchantId") Integer merchantId,@Param("channelCode") String channelCode);

	@Select("select * from channel_enterprise_config where merchant_id=#{merchantId} and type=#{type} and channel_code =#{channelCode} limit 0,1")
	public ChannelMerchantConfigDTO findEnterpriseConfigAndType(@Param("merchantId") Integer merchantId,@Param("type") Integer type,@Param("channelCode") String channelCode);
	@Select("select * from channel_enterprise_config where type=1 and channel_code =#{channelCode} limit 0,1")
	public ChannelMerchantConfigDTO findPlatformConfig(@Param("channelCode") String channelCode);

	@Delete("delete  from channel_enterprise_config where merchant_id=#{merchantId} and type in(2,3)")
	public int deleteEnterpriseConfig(@Param("merchantId") Integer merchantId);
	@Delete("delete  from channel_enterprise_config where merchant_id=#{merchantId} and type in(3) and channel_code =#{channelCode}")
	public int deleteEnterpriseSelfConfig(@Param("merchantId") Integer merchantId,@Param("channelCode") String channelCode);
	/**
	 * @param data 平台设置代理商缺省配置
	 * @return
	 */
	@Insert({"<script>",
		  "INSERT INTO channel_enterprise_config(",
		  "<trim suffixOverrides=','>",
					  "<if test='merchantId != null'>merchant_id,</if>",
					  "<if test='type != null'>type,</if>",
					  "<if test='priceAddtion != null'>	price_addtion,</if>",
					  "<if test='priceAddtionMax != null'>	price_addtion_max,</if>",
					  "<if test='priceAddtionMin != null'>	price_addtion_min,</if>",
					  "<if test='grossMarginMax != null'>	gross_margin_max,</if>",
					  "<if test='grossMarginMin != null'>	gross_margin_min,</if>",
					  "<if test='channelCategorys != null'>	channel_categorys,</if>",
					  "<if test='channelPriceMax != null'>	channel_price_max,</if>",
					  "<if test='channelPriceMin != null'>	channel_price_min,</if>",
					  "<if test='createMillis != null'>	create_millis,</if>",
					  "<if test='updateMillis != null'>	update_millis,</if> ",
					  "<if test='channelCode != null'>	channel_code,</if> </trim>	",
		  ") VALUES (",


		  "<trim suffixOverrides=','>",
					  "<if test='merchantId != null'>#{merchantId},</if>",
					  "<if test='type != null'>#{type},</if>",
					  "<if test='priceAddtion != null'>	#{priceAddtion},</if>",
					  "<if test='priceAddtionMax != null'>	#{priceAddtionMax},</if>",
					  "<if test='priceAddtionMin != null'>	#{priceAddtionMin},</if>",
					  "<if test='grossMarginMax != null'>	#{grossMarginMax},</if>",
					  "<if test='grossMarginMin != null'>	#{grossMarginMin},</if>",
					  "<if test='channelCategorys != null'>	#{channelCategorys},</if>",
					  "<if test='channelPriceMax != null'>	#{channelPriceMax},</if>",
					  "<if test='channelPriceMin != null'>	#{channelPriceMin},</if>",
					  "<if test='createMillis != null'>	#{createMillis},</if>",
					  "<if test='updateMillis != null'>	#{updateMillis},</if> " ,
			         "<if test='channelCode != null'>	#{channelCode},</if> </trim>	)  ",
					  "</script>"})
	public int insertEnterprise(ChannelMerchantConfigDTO data);

	//设置代理商管理员
	@Update({"<script>",
		"UPDATE channel_enterprise_config 	",
		"<set>	",
		" <if test='priceAddtion != null'>price_addtion = #{priceAddtion},</if>				",
		" <if test='priceAddtionMax != null'>	",
		" 	price_addtion_max = #{priceAddtionMax},</if>				",
		" <if test='priceAddtionMin != null'>				price_addtion_min = #{priceAddtionMin},			</if>		",
		" <if test='grossMarginMax != null'>				gross_margin_max = #{grossMarginMax},			</if>		",
		" <if test='grossMarginMin != null'>				gross_margin_min = #{grossMarginMin},			</if>		",
		" <if test='channelCategorys != null'>				channel_categorys = #{channelCategorys},			</if>	",
		" <if test='channelPriceMax != null'>				channel_price_max = #{channelPriceMax},			</if>	",
		" <if test='channelPriceMin != null'>				channel_price_min = #{channelPriceMin},			</if>			",
		" <if test='updateMillis != null'>				update_millis = #{updateMillis},			</if>		",
	    "</set>	",
		"WHERE type = 1  and merchant_id is null",
		"</script>"})
	public int updatePlatform(ChannelMerchantConfigDTO data);

	//设置代理商管理员
	@Update({"<script>",
		"UPDATE channel_enterprise_config 	",
		"<set>	",
		" <if test='priceAddtion != null'>price_addtion = #{priceAddtion},</if>				",
		" <if test='plateformAddtion != null'>plateform_addtion = #{plateformAddtion},</if>				",
		" <if test='priceAddtionMax != null'>	",
		" 	price_addtion_max = #{priceAddtionMax},</if>				",
		" <if test='priceAddtionMin != null'>				price_addtion_min = #{priceAddtionMin},			</if>		",
		" <if test='grossMarginMax != null'>				gross_margin_max = #{grossMarginMax},			</if>		",
		" <if test='grossMarginMin != null'>				gross_margin_min = #{grossMarginMin},			</if>		",
		" <if test='channelCategorys != null'>				channel_categorys = #{channelCategorys},			</if>	",
		" <if test='channelPriceMax != null'>				channel_price_max = #{channelPriceMax},			</if>	",
		" <if test='channelPriceMin != null'>				channel_price_min = #{channelPriceMin},			</if>			",
		" <if test='updateMillis != null'>				update_millis = #{updateMillis},			</if>		",
			" <if test='channelCode != null'>				channel_code = #{channelCode},			</if>		",
	    "</set>	",
		"WHERE id = #{id}",
		"</script>"})
	public int updateEnterprise(ChannelMerchantConfigDTO data);
}
