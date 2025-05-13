package com.egeo.components.order.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface SoInvoiceManage {

	public SoInvoiceDTO findSoInvoiceById(Long id);	

	public PageResult<SoInvoiceDTO> findSoInvoiceOfPage(SoInvoiceDTO dto,Pagination page);

	public List<SoInvoiceDTO> findSoInvoiceAll(SoInvoiceDTO dto);

	/**
	 * 开具发票保存信息
	 * @param soChildId
	 * @param invoiceType
	 * @param invoiceCode
	 * @param remark
	 * @param userId
	 * @return
	 */
	JsonResult<Map<String,Object>> drawInvoice(Long soChildId,Integer invoiceType,String invoiceCode,String remark,
			Long userId, String invoiceAttcUrl);

	int updateSoInvoiceWithTx(SoInvoiceDTO dto);

	int deleteSoInvoiceWithTx(SoInvoiceDTO dto);

	/**
	 * 保存发票信息
	 * @param invoiceForm 发票形式 0:纸质发票  1:电子发票
	 * @param invoiceTitleType 抬头类型 0：个人；1：公司
	 * @param title
	 * @param tpid 纳税人识别号
	 * @param contentType 发票明细类型 0:商品明细  1:商品类别
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public JsonResult<Map<String, Object>> saveInvoice(Long id,Integer invoiceForm, Integer invoiceTitleType, Integer contentType,
			Long userId, String title, Long commonInvoiceId, Long platformId);

	/**
	 * 发票提示
	 * @param platformId
	 * @param userId
	 * @return
	 */
	public JsonResult<Map<String, Object>> invoiceHint(Long platformId, Long userId);

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public JsonResult<Map<String, Object>> invoiceDetail(Long id);

	/**
	 * 根据母单id查询发票公共信息和子单关联发票信息
	 * @param soId
	 * @return
	 */
	public JsonResult<Map<String, Object>> findInvoiceBySoId(Long soId);

	/**
	 * 修改/新增母订单发票信息
	 * @param orderId
	 * @param titleType
	 * @param title
	 * @param invoiceType
	 * @param taxNo
	 * @param invoiceContentType 
	 * @return
	 */
	public JsonResult<Map<String, Object>> updateInvoiceInfo(Long orderId, Integer titleType, String title,
			Integer invoiceType, String taxNo,Integer invoiceContentType, Long platformId);

	/**
	 * 根据子订单id查询发票
	 * @param soChildId
	 * @return
	 */
	public JsonResult<Map<String, Object>> soChildInvoiceInfo(Long soChildId);

	/**
	 * 根据id查询发票信息
	 * @param id
	 * @return
	 */
	public JsonResult<Map<String, Object>> invoiceById(Long id);

	/**
	 * 通过订单id查询发票抬头信息
	 * @param orderId
	 * @return
	 */
	public SoInvoiceDTO queryMainSoInvoiceByOrderId(Long orderId);

	/**
	 * 通过子订单id查询发票信息
	 * @param soChildId
	 * @return
	 */
	public SoInvoiceDTO querySoInvoiceBySoChildId(Long soChildId);

	/**
	 * 查询已开的电子发票列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryElecInvoice(SoInvoiceDTO dto);

	/**
	 * 下载发票
	 * @param invoiceIdList
	 * @return
	 */
	public JsonResult<Long> downloadInvoice(List<Long> invoiceIdList, String email, String loginName);

	/**
	 * 更换公共发票信息
	 * @param orderId
	 * @param commonInvoiceId
	 * @return
	 */
	public JsonResult<Integer> changeCommonInvoiceInfoWithTx(Long orderId, Long commonInvoiceId);

	public JsonResult<Integer> updateInvoiceTypeInfoWithTx(SoInvoiceDTO dto);
}
	