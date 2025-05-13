package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoInvoiceReadService {

	public SoInvoiceDTO findSoInvoiceById(Long id);

	public PageResult<SoInvoiceDTO> findSoInvoiceOfPage(SoInvoiceDTO dto,Pagination page);

	public List<SoInvoiceDTO> findSoInvoiceAll(SoInvoiceDTO dto);

	/**
	 * 根据订单编号查询发表信息
	 * @param orderCode
	 * @param platformId 
	 * @return
	 */
	public SoInvoiceDTO querySoInvoiceByOrderCode(String orderCode, Long platformId);

	/**
	 * 查询不重复的用户发票记录
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public List<SoInvoiceDTO> queryDistinctInvoiceByUserId(Long userId, Long platformId);

	/**
	 * 查询订单主发票
	 * @param orderId
	 * @return
	 */
	public SoInvoiceDTO queryMainSoInvoiceByOrderId(Long orderId);

	/**
	 * 根据子订单id查询发票信息
	 * @param id
	 * @return
	 */
	public SoInvoiceDTO querySoInvoiceBySoChildId(Long id);
}
	