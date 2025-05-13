package com.egeo.components.product.dao.write;

import com.egeo.components.product.dto.ChannelPriceDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChannelPriceDAO {
	@Select("SELECT * FROM channel_price c WHERE c.channel_code = #{channelCode} and c.spu_id = #{spuId} and c.`enterprise_id` = #{enterpriseId} and c.delete=0")
	public List<ChannelPriceDTO> findAllPriceOfEnterpriseAndSpu(@Param("channelCode") String channelCode,@Param("spuId") String spuId, @Param("enterpriseId") Long enterpriseId);


	@Select("SELECT * FROM channel_price c WHERE c.channel_code = #{channelCode} and c.`enterprise_id` = #{enterpriseId}  and c.delete=0")
	public List<ChannelPriceDTO> findAllPriceOfEnterprise(@Param("channelCode") String channelCode,@Param("enterpriseId") Long enterpriseId);


	@Select("SELECT * FROM channel_price c WHERE c.channel_code = #{channelCode} and c.spu_id = #{spuId} and c.`enterprise_id` = #{enterpriseId} and c.audit=#{audit}  and c.delete=0")
	public List<ChannelPriceDTO> findPriceOfEnterpriseAndSpuAndAudit(@Param("channelCode") String channelCode,@Param("spuId") String spuId,@Param("enterpriseId") Long enterpriseId,@Param("audit") Integer audit);


	@Select("SELECT * FROM channel_price c WHERE c.channel_code = #{channelCode} and c.`enterprise_id` = #{enterpriseId} and c.audit=#{audit}  and c.delete=0")
	public List<ChannelPriceDTO> findAllPriceOfEnterpriseAndAudit(@Param("channelCode") String channelCode,@Param("enterpriseId") Long enterpriseId,@Param("audit") Integer audit);

	@Select("SELECT * FROM channel_price c WHERE  c.channel_code = #{channelCode} and c.audit=#{audit}  and c.delete=0")
	public List<ChannelPriceDTO> findAllPriceAndAudit(@Param("channelCode") String channelCode,@Param("audit") Integer audit);


	@Select({"<script>",
		"SELECT * FROM channel_price c WHERE c.channel_code = #{channelCode} and c.audit=2 and  c.delete=0 and c.`enterprise_id` = #{enterpriseId} ",
			" and c.pid in ",
			"<foreach collection='pids' item='id' open='(' separator=',' close=')'>",
			"#{id}",
			"</foreach>",
			"</script>"})
	public List<ChannelPriceDTO> findAllPriceOfEnterpriseAndSpus(@Param("channelCode") String channelCode,@Param("pids") List<String> pids,@Param("enterpriseId") Long enterpriseId);


	@Insert("insert into channel_price(channel_code,spu_id,price_type,price_value,enterprise_id,audit) " +
            "values (#{channelCode},#{spuId},#{priceType},#{priceValue},#{enterpriseId},#{audit})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public int insertReturnId(ChannelPriceDTO channelPrice);


	@Insert("insert into channel_price(channel_code,pid,spu_id,price_type,price_value,enterprise_id,audit) " +
            "values (#{channelCode},#{pid},#{spuId},#{priceType},#{priceValue},#{enterpriseId},#{audit})")
	public int insert(ChannelPriceDTO channelPrice);

	@Update("update channel_price set price_type=#{priceType} ,price_value=#{priceValue},audit=#{audit} where id=#{id}")
	public int update(ChannelPriceDTO channelPrice);

	@Update("update channel_price set audit=#{audit} where id =#{id}")
	public int audit(ChannelPriceDTO channelPrice);


	@Update("update channel_price t set t.delete=1 where t.channel_code = #{channelCode} and t.delete=0 and t.enterprise_id =#{enterpriseId} and t.pid=#{pid}")
	public int delete(ChannelPriceDTO channelPrice);

	@Select({"<script>",
			"SELECT * FROM channel_price c WHERE c.channel_code = #{channelCode} and c.audit=2 and  c.delete=0 and c.`enterprise_id` = #{enterpriseId} ",
			" and (c.pid in ",
			"<foreach collection='pids' item='id' open='(' separator=',' close=')'>",
			"#{id}",
			"</foreach>",
			"<if test='supIds != null'>",
				" or c.spu_id in ",
				"<foreach collection='supIds' item='item' open='(' separator=',' close=')'>",
				"#{item}",
				"</foreach>",
			"</if>)				",
			"</script>"})
	public List<ChannelPriceDTO> findAllPriceOfEnterpriseAndSpusPIds(@Param("channelCode") String channelCode,@Param("pids") List<String> pids,@Param("supIds") List<String> supIds,@Param("enterpriseId") Long enterpriseId);

	//@Update("delete channel_price t  where t.channel_code = #{channelCode} and t.delete=1 and t.enterprise_id =#{enterpriseId} and t.pid=#{pid}")
	@Delete({"<script>",
			"delete from channel_price   where channel_code = #{channelCode} and `delete`=1 and enterprise_id =#{enterpriseId} and pid=#{pid}",
			"<if test='spuId != null'>",
			" and spu_id =#{spuId} ",
			"</if>				",
			"</script>"})
	public int deleteExists(ChannelPriceDTO channelPrice);
}
