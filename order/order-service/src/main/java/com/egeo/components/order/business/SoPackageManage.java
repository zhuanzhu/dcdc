package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.vo.SoPackageVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface SoPackageManage {

	SoPackageDTO findSoPackageById(SoPackageDTO dto);

	PageResult<SoPackageDTO> findSoPackageOfPage(SoPackageDTO dto,Pagination page);

	List<SoPackageDTO> findSoPackageAll(SoPackageDTO dto);

	Long insertSoPackageWithTx(SoPackageDTO dto,Long soId);

	int updateSoPackageWithTx(SoPackageDTO dto);

	int deleteSoPackageWithTx(SoPackageDTO dto);

	List<SoPackageVO> SoPackageAllByOrderCode(SoPackageDTO dto);

	String updateSoPackageByIdWithTxAll(List<SoPackageVO> lists,Long soId,Long platformId);

	JsonResult<Map<String,Object>> packageBySoId(String orderCode);

	/**
	 * 发货信息导入
	 * @param operatorId
	 * @param platformId
	 * @param valueList
	 * @return
	 */
	JsonResult<Map<String, Object>> deliveryImport(Long operatorId, Long platformId,
			List<Map<String, Object>> valueList, HttpServletRequest req);

	@Deprecated
	JsonResult<Map<String, Object>> signImport(Integer tempType, Long platformId,
			List<Map<String, Object>> valueList);

	@Deprecated
	Long assureImportDelivery(Long platformId, Long parseLong, String soPackageInfo);

	@Deprecated
	Long assureImportSign(Long platformId, Long parseLong, String soPackageInfo);

	PageResult<SoPackageDTO> findSoPackageAndBoxOfPage(SoPackageDTO dto, Pagination page);

	JsonResult<Map<String, Object>> sortImport(Integer tempType, Long platformId,
			List<Map<String, Object>> valueList);

	/**
	 * 订单物流信息列表
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> soDeliveryInfoList(Long orderId);

	/**
	 * 新增箱号
	 * @param orderId
	 * @param boxCode
	 * @return
	 */
	JsonResult<Map<String, Object>> addBox(Long soChildId, Long boxCode);

	/**
	 * 通过箱号code查询箱号信息
	 * @return
	 */
	SoPackageBoxDTO querySoPackageBoxByBoxCodeAndChildId(Long boxCode,Long soChildId);

    JsonResult<Map<String, Object>> addDeliveryInfo(Long boxCode, String orderCode, String childCode, String deliveryCompany, String deliveryCode, Long operatorId, Long platformId,Boolean force);
	JsonResult<Map<String, Object>> deliveryImportChild(Long operatorId, Long platformId,
			List<Map<String, Object>> valueList, HttpServletRequest req);

	JsonResult<Map<String, Object>> packageBySoChildCode(String soChildCode);

	JsonResult<Map<String, Object>> deliveryImportSoChild(Long operatorId, Long platformId,
														List<Map<String, Object>> valueList, HttpServletRequest req);

	/**
	 * 发货信息导入
	 * @param operatorId
	 * @param platformId
	 * @param valueList
	 * @return
	 */
	JsonResult<Map<String, Object>> deliveryPlatformImport(Long operatorId, Long platformId,
												   List<Map<String, Object>> valueList, HttpServletRequest req);
}
