package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.egeo.components.product.dto.JdMerchantConfigDTO;


/**
 * 管理员 Mapper
 * 
 * @author zhou_k 2017年5月25日
 */

public interface JdMerchantConfigMapper {

	/**
	 * @return  企业修改信息
	 */
	/*@Update("update u_enterprise t set t.audit_status=0, t.name=#{name},t.address=#{address},t.contact=#{contact} ,t.update_millis=#{updateMillis} where t.id=#{id}")
	public int update(JdMerchantConfigDTO data);*/

	//2.查询所有审核通过且合作中的代理商
	@Select("select * from jd_enterprise_config where merchant_id=#{merchantId}")
	public List<JdMerchantConfigDTO> findEnterpriseConfig(@Param("merchantId") Integer merchantId);
	@Select("select * from jd_enterprise_config where merchant_id=#{merchantId} and type=#{type} limit 0,1")
	public JdMerchantConfigDTO findEnterpriseConfigAndType(@Param("merchantId") Integer merchantId,@Param("type") Integer type);
	@Select("select * from jd_enterprise_config where type=1 limit 0,1")
	public JdMerchantConfigDTO findPlatformConfig();

	@Delete("delete  from jd_enterprise_config where merchant_id=#{merchantId} and type in(2,3)")
	public int deleteEnterpriseConfig(@Param("merchantId") Integer merchantId);
	@Delete("delete  from jd_enterprise_config where merchant_id=#{merchantId} and type in(3)")
	public int deleteEnterpriseSelfConfig(@Param("merchantId") Integer merchantId);
	/**
	 * @param 平台设置代理商缺省配置
	 * @return
	 */
	@Insert({"<script>",
		  "INSERT INTO jd_enterprise_config(",
		  "<trim suffixOverrides=','>",
					  "<if test='merchantId != null'>merchant_id,</if>",
					  "<if test='type != null'>type,</if>",
					  "<if test='priceAddtion != null'>	price_addtion,</if>",
					  "<if test='priceAddtionMax != null'>	price_addtion_max,</if>",
					  "<if test='priceAddtionMin != null'>	price_addtion_min,</if>",
					  "<if test='grossMarginMax != null'>	gross_margin_max,</if>",
					  "<if test='grossMarginMin != null'>	gross_margin_min,</if>",
					  "<if test='jdCategorys != null'>	jd_categorys,</if>",
					  "<if test='jdPriceMax != null'>	jd_price_max,</if>",
					  "<if test='jdPriceMin != null'>	jd_price_min,</if>",
					  "<if test='createMillis != null'>	create_millis,</if>",
					  "<if test='updateMillis != null'>	update_millis,</if> </trim>	",  
		  ") VALUES (",
		  
		  
		  "<trim suffixOverrides=','>",
					  "<if test='merchantId != null'>#{merchantId},</if>",
					  "<if test='type != null'>#{type},</if>",
					  "<if test='priceAddtion != null'>	#{priceAddtion},</if>",
					  "<if test='priceAddtionMax != null'>	#{priceAddtionMax},</if>",
					  "<if test='priceAddtionMin != null'>	#{priceAddtionMin},</if>",
					  "<if test='grossMarginMax != null'>	#{grossMarginMax},</if>",
					  "<if test='grossMarginMin != null'>	#{grossMarginMin},</if>",
					  "<if test='jdCategorys != null'>	#{jdCategorys},</if>",
					  "<if test='jdPriceMax != null'>	#{jdPriceMax},</if>",
					  "<if test='jdPriceMin != null'>	#{jdPriceMin},</if>",
					  "<if test='createMillis != null'>	#{createMillis},</if>",
					  "<if test='updateMillis != null'>	#{updateMillis},</if> </trim>	)  ",  
					  "</script>"})
	public int insertEnterprise(JdMerchantConfigDTO data);
	
	//设置代理商管理员
	@Update({"<script>",  
		"UPDATE jd_enterprise_config 	",
		"<set>	",
		" <if test='priceAddtion != null'>price_addtion = #{priceAddtion},</if>				",
		" <if test='priceAddtionMax != null'>	",
		" 	price_addtion_max = #{priceAddtionMax},</if>				",
		" <if test='priceAddtionMin != null'>				price_addtion_min = #{priceAddtionMin},			</if>		",		
		" <if test='grossMarginMax != null'>				gross_margin_max = #{grossMarginMax},			</if>		",		
		" <if test='grossMarginMin != null'>				gross_margin_min = #{grossMarginMin},			</if>		",
		" <if test='jdCategorys != null'>				jd_categorys = #{jdCategorys},			</if>	",
		" <if test='jdPriceMax != null'>				jd_price_max = #{jdPriceMax},			</if>	",
		" <if test='jdPriceMin != null'>				jd_price_min = #{jdPriceMin},			</if>			",
		" <if test='updateMillis != null'>				update_millis = #{updateMillis},			</if>		",
	    "</set>	",
		"WHERE type = 1  and merchant_id is null",
		"</script>"})
	public int updatePlatform(JdMerchantConfigDTO data);

	//设置代理商管理员
	@Update({"<script>",  
		"UPDATE jd_enterprise_config 	",
		"<set>	",
		" <if test='priceAddtion != null'>price_addtion = #{priceAddtion},</if>				",
		" <if test='plateformAddtion != null'>plateform_addtion = #{plateformAddtion},</if>				",
		" <if test='priceAddtionMax != null'>	",
		" 	price_addtion_max = #{priceAddtionMax},</if>				",
		" <if test='priceAddtionMin != null'>				price_addtion_min = #{priceAddtionMin},			</if>		",		
		" <if test='grossMarginMax != null'>				gross_margin_max = #{grossMarginMax},			</if>		",		
		" <if test='grossMarginMin != null'>				gross_margin_min = #{grossMarginMin},			</if>		",
		" <if test='jdCategorys != null'>				jd_categorys = #{jdCategorys},			</if>	",
		" <if test='jdPriceMax != null'>				jd_price_max = #{jdPriceMax},			</if>	",
		" <if test='jdPriceMin != null'>				jd_price_min = #{jdPriceMin},			</if>			",
		" <if test='updateMillis != null'>				update_millis = #{updateMillis},			</if>		",
	    "</set>	",
		"WHERE id = #{id}",
		"</script>"})
	public int updateEnterprise(JdMerchantConfigDTO data);
}