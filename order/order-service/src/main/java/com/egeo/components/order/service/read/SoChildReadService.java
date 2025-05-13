package com.egeo.components.order.service.read;


import java.util.Date;
import java.util.List;

import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildDTOCondition;
import com.egeo.components.order.vo.OrderSortExportVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoChildReadService {

	public SoChildDTO findSoChildById(Long id);

	public PageResult<SoChildDTO> findSoChildOfPage(SoChildDTO dto,Pagination page);

	public List<SoChildDTO> findSoChildAll(SoChildDTO dto);

	/** 根据条件查询子订单的信息和发票信息
	 * @param soChildId
	 * @param platformId
	 * @return
	 */
	public List<SoChildDTOCondition> findSoChildByCondition(Long[] soChildId, Long platformId);

	/**
	 * 查询当前订单下最大的子订单编号+1
	 * @param soId
	 * @return
	 */
	public String queryMaxChildCodePlus1BySoId(Long soId);

	/**
	 * 根据子订单编号查询子订单
	 * @param childCode
	 * @return
	 */
	public SoChildDTO querySoChildByChildCode(String childCode);

	/**
	 * 根据母订单id查询子订单列表
	 * @param id
	 * @return
	 */
	public List<SoChildDTO> querySoChildListBySoId(Long id);

    PageResult<SoChildDTO> getSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Integer orderPayStatus,Integer auditStatus);
    PageResult<SoChildDTO> getSupplierSoChildAllList(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Long supplierId,Integer orderPayStatus,Integer auditStatus);

    PageResult<SoChildDTO> findMerchantChildOrderOfPage(SoChildDTO dto, Pagination page);

    /**
     * 查询需要预警的子订单列表
     * @param warningDate
     * @return
     */
    List<SoChildDTO> findWarningChildOrder(Date warningDate);

    Long findSoChildIdByThirdpartyId(Long jdOrderId);

	Long findSoChildIdByThirdpartyCode(String thirdpartyCode);

	List<SoChildDTO> findSoChildListByMerchantId(List<Long> idList, long merchantId);

	List<SoChildDTO> findSoChildListBySupplierId(List<Long> idList, long supplierId);

	List<SoChildDTO> findSoChildByIds(Long[] soChildId, Long platformId);

	List<SoChildDTO> findSoChildByIdList(List<Long> idList);

	List<OrderSortExportVO> soDetailOrderExport(List<Long> soIdList);

	List<SoChildDTO> getSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Integer orderPayStatus,Integer auditStatus);
	List<SoChildDTO> getSupplierSoChildAllListToExport(Integer merchantId, String soChildCode, List<Long> userIds, List<Long> puIds, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Long platformId, List<Long> testCompanyIds, Pagination page,Long supplierId,Integer orderPayStatus,Integer auditStatus);


}
