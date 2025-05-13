package com.egeo.components.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.egeo.components.product.dto.JdEnterpriseConfigDTO;
import com.egeo.components.product.dto.JdPriceDTO;
import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.orm.BaseWriteDAO;

@Mapper
public interface JdPriceDAO{
	@Select("SELECT * FROM jd_price c WHERE c.spu_id = #{spuId} and c.`enterprise_id` = #{enterpriseId} and c.delete=0")
	public List<JdPriceDTO> findAllPriceOfEnterpriseAndSpu(@Param("spuId") Long spuId,@Param("enterpriseId") Long enterpriseId);

	@Select("SELECT * FROM jd_price c WHERE c.pid = #{pid} and c.`enterprise_id` = #{enterpriseId} and c.delete=0")
	public List<JdPriceDTO> findAllPriceOfEnterpriseAndPid(@Param("pid") Long pid,@Param("enterpriseId") Long enterpriseId);


	@Select("SELECT * FROM jd_price c WHERE  c.`enterprise_id` = #{enterpriseId}  and c.delete=0")
	public List<JdPriceDTO> findAllPriceOfEnterprise(@Param("enterpriseId") Long enterpriseId);
	

	@Select("SELECT * FROM jd_price c WHERE c.spu_id = #{spuId} and c.`enterprise_id` = #{enterpriseId} and c.audit=#{audit}  and c.delete=0")
	public List<JdPriceDTO> findPriceOfEnterpriseAndSpuAndAudit(@Param("spuId") Long spuId,@Param("enterpriseId") Long enterpriseId,@Param("audit") Integer audit);


	@Select("SELECT * FROM jd_price c WHERE  c.`enterprise_id` = #{enterpriseId} and c.audit=#{audit}  and c.delete=0")
	public List<JdPriceDTO> findAllPriceOfEnterpriseAndAudit(@Param("enterpriseId") Long enterpriseId,@Param("audit") Integer audit);

	@Select("SELECT * FROM jd_price c WHERE   c.audit=#{audit}  and c.delete=0")
	public List<JdPriceDTO> findAllPriceAndAudit(@Param("audit") Integer audit);
	
	
	@Select({"<script>",
		"SELECT * FROM jd_price c WHERE c.audit=2 and  c.delete=0 and c.`enterprise_id` = #{enterpriseId} ",
			" and c.pid in ",
			"<foreach collection='pids' item='id' open='(' separator=',' close=')'>",
			"#{id}",
			"</foreach>",
			"</script>"})
	public List<JdPriceDTO> findAllPriceOfEnterpriseAndSpus(@Param("pids") List<Long> pids,@Param("enterpriseId") Long enterpriseId);
	

	@Insert("insert into jd_price(spu_id,price_type,price_value,enterprise_id,audit) " +
            "values (#{spuId},#{priceType},#{priceValue},#{enterpriseId},#{audit})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public int insertReturnId(JdPriceDTO jdPrice);
	

	@Insert("insert into jd_price(pid,spu_id,price_type,price_value,enterprise_id,audit) " +
            "values (#{pid},#{spuId},#{priceType},#{priceValue},#{enterpriseId},#{audit})")
	public int insert(JdPriceDTO jdPrice);

	@Update("update jd_price set price_type=#{priceType} ,price_value=#{priceValue},audit=#{audit} where id=#{id}")
	public int update(JdPriceDTO jdPrice);

	@Update("update jd_price set audit=#{audit} where id =#{id}")
	public int audit(JdPriceDTO jdPrice);
	

	@Delete("delete from jd_price  where enterprise_id =#{enterpriseId} and pid=#{pid}")
	public int delete(JdPriceDTO jdPrice);
}
	