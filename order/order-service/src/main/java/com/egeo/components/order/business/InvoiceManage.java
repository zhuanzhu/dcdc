package com.egeo.components.order.business;

import java.util.List;

import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.components.order.vo.InvoiceSimpleVO;
import com.egeo.components.order.vo.InvoiceVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface InvoiceManage {

	public InvoiceDTO findInvoiceById(InvoiceDTO dto);	

	public PageResult<InvoiceSimpleVO> findInvoiceOfPage(InvoiceDTO dto, Pagination page);

	public List<InvoiceDTO> findInvoiceAll(InvoiceDTO dto);

	Long insertInvoiceWithTx(InvoiceDTO dto);

	int updateInvoiceWithTx(InvoiceDTO dto);

	int deleteInvoiceWithTx(InvoiceDTO dto);

	/**
	 * 新增或编辑公共发票信息
	 * @param dto
	 * @return
	 */
	public JsonResult<Long> insertOrUpdateInvoiceWithTx(InvoiceDTO dto, Long orderId);

	/**
	 * 选择发票信息
	 * @param dto
	 * @return
	 */
	public InvoiceSimpleVO chooseInvoice(InvoiceDTO dto, Long id);

	/**
	 * 编辑发票公共信息列表
	 * @param orderId
	 * @return
	 */
	public JsonResult<List<InvoiceVO>> findInvoiceAll(Long orderId);

    JsonResult<InvoiceVO> findDefaultInvoiceByUserId(Long userId, Long platformId);
}
	