package com.egeo.components.order.manage.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.order.condition.SoChildCondition;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.vo.OrderSortExportVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoChildReadManage {

	public SoChildPO findSoChildById(SoChildPO po);

	public PageResult<SoChildPO> findSoChildOfPage(SoChildPO po,Pagination page);

	public List<SoChildPO> findSoChildAll(SoChildPO po);
	Long findSoChildIdByThirdpartyCode(String thirdpartyCode);

	/**
	 * soChildPOList
	 * @param soChildId
	 * @param platformId
	 * @return
	 */
	public List<SoChildPO> findSoChildByCondition(Long[] soChildId, Long platformId);

	/**
	 * 根据子订单编号查询子订单
	 * @param childCode
	 * @return
	 */
	public SoChildPO querySoChildByChildCode(String childCode);


    PageResult<SoChildPO> getSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Integer orderPayStatus,Integer auditStatus);

    PageResult<SoChildPO> getSupplierSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Long supplierId,Integer orderPayStatus,Integer auditStatus);

    /**
	 * 分页查询第三方运营订单
	 * @param po
	 * @param page
	 * @return
	 */
    PageResult<SoChildCondition> findMerchantChildOrderOfPage(SoChildCondition po, Pagination page);

    /**
     * 查询需要预警的子订单列表
     * @param warningDate
     * @return
     */
    List<SoChildCondition> findWarningChildOrder(Date warningDate);

    Long findSoChildIdByThirdpartyId(Long jdOrderId);

    List<SoChildCondition> findSoChildListByMerchantId(List<Long> idList, Long merchantId);

	List<SoChildCondition> findSoChildListBySupplierId(List<Long> idList, Long supplierId);

	List<OrderSortExportVO> soDetailOrderExport(List<Long> soIdList);

	List<SoChildPO> getSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
													 Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
													 Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
													 Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,
													 Integer orderPayStatus,Integer auditStatus);

	List<SoChildPO> getSupplierSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds,
													  Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType,
													  Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart,
													  Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds,
													  Pagination page, Long supplierId, Integer orderPayStatus,Integer auditStatus);
}
