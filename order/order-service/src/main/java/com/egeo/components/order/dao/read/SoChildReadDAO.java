package com.egeo.components.order.dao.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.order.vo.OrderSortExportVO;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.condition.SoChildCondition;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface SoChildReadDAO extends BaseReadDAO<SoChildPO>{

	List<SoChildPO> findSoChildByCondition(@Param("soChildId")Long[] soChildId, @Param("platformId")Long platformId, @Param("page") Pagination page);

	/**
	 * 根据子订单编号查询子订单
	 * @param childCode
	 * @return
	 */
	SoChildPO querySoChildByChildCode(@Param("childCode")String childCode);
	Long findSoChildIdByThirdpartyCode(@Param("thirdpartyCode") String thirdpartyCode);

    List<SoChildPO> getSoChildAllListByPage(@Param("storeId")Integer storeId,@Param("merchantId")Integer merchantId, @Param("soChildCode") String soChildCode, @Param("userIds") List<Long> userIds, @Param("puIds") List<Long> puIds,
                                            @Param("soCreateTimeStart") Date soCreateTimeStart, @Param("soCreateTimeEnd") Date soCreateTimeEnd, @Param("soType") Integer soType, @Param("soChildDeliveryStatus") Integer soChildDeliveryStatus,
                                            @Param("soConfirmStatus") Integer soConfirmStatus, @Param("sendTimeStart") Date sendTimeStart, @Param("sendTimeEnd") Date sendTimeEnd, @Param("showTest") Boolean showTest, @Param("platformId") Long platformId,
                                            @Param("testCompanyIds") List<Long> testCompanyIds, @Param("page") Pagination page, @Param("supplierId") Long supplierId,@Param("orderPayStatus") Integer orderPayStatus,@Param("auditStatus") Integer auditStatus);

	Integer getSoChildAllListTotalCount(@Param("storeId")Integer storeId,@Param("merchantId")Integer merchantId, @Param("soChildCode") String soChildCode, @Param("userIds") List<Long> userIds, @Param("puIds") List<Long> puIds,
										@Param("soCreateTimeStart") Date soCreateTimeStart, @Param("soCreateTimeEnd") Date soCreateTimeEnd, @Param("soType") Integer soType, @Param("soChildDeliveryStatus") Integer soChildDeliveryStatus,
										@Param("soConfirmStatus") Integer soConfirmStatus, @Param("sendTimeStart") Date sendTimeStart, @Param("sendTimeEnd") Date sendTimeEnd, @Param("showTest") Boolean showTest, @Param("platformId") Long platformId,
										@Param("testCompanyIds") List<Long> testCompanyIds, @Param("supplierId") Long supplierId,@Param("orderPayStatus") Integer orderPayStatus,@Param("auditStatus") Integer auditStatus);

	/**
	 * 分页查询第三方运营订单
	 * @param po
	 * @param page
	 * @return
	 */
	List<SoChildCondition> findMerchantChildOrderOfPage(@Param("po")SoChildCondition po, @Param("page")Pagination page);
	
	/**
	 * 统计第三方运营订单数量
	 * @param po
	 * @param page
	 * @return
	 */
	Integer countMerchantChildOrderOfPage(@Param("po")SoChildCondition po, @Param("page")Pagination page);
	
	/**
	 * 查询需要预警的子订单列表
	 * @param warningDate
	 * @return
	 */
	List<SoChildCondition> findWarningChildOrder(@Param("warningDate")Date warningDate);

    Long findSoChildIdByThirdpartyId(@Param("jdOrderId") Long jdOrderId);

    List<SoChildCondition> findSoChildListByMerchantId(@Param("idList")List<Long> idList, @Param("merchantId") Long merchantId);
    List<SoChildCondition> findSoChildListBySupplierId(@Param("idList")List<Long> idList, @Param("supplierId") Long supplierId);

	List<OrderSortExportVO> soDetailOrderExport(@Param("idList")List<Long> soIdList);

}
	