package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoInvoiceReadService;
import com.egeo.components.order.service.write.SoChildFlowWriteService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoInvoiceWriteService;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoInvoiceFacade {
	
	@Resource
	private SoInvoiceReadService soInvoiceReadService;
	
	@Resource
	private SoInvoiceWriteService soInvoiceWriteService;
	
	@Resource
	private SoChildFlowWriteService opFlowWriteService;
	
	@Resource
	private SoChildWriteService scWriteService;
	
	
	public SoInvoiceDTO findSoInvoiceById(Long id){
		
		return soInvoiceReadService.findSoInvoiceById(id);
	}

	public PageResult<SoInvoiceDTO> findSoInvoiceOfPage(SoInvoiceDTO dto,Pagination page){
		
		return soInvoiceReadService.findSoInvoiceOfPage(dto, page);
		
	}

	public List<SoInvoiceDTO> findSoInvoiceAll(SoInvoiceDTO dto){
		
		return soInvoiceReadService.findSoInvoiceAll(dto);
		
	}

	public Long insertSoInvoiceWithTx(SoInvoiceDTO dto, String mail){
		
		return soInvoiceWriteService.insertSoInvoiceWithTx(dto,mail);
	}

	public int updateSoInvoiceWithTx(SoInvoiceDTO dto){
		
		return soInvoiceWriteService.updateSoInvoiceWithTx(dto);
	}
	public int updateSoInvoiceWithTx(SoInvoiceDTO dto, Long commonInvoiceId){
		
		return soInvoiceWriteService.updateSoInvoiceWithTx(dto, commonInvoiceId);
	}

	public int deleteSoInvoiceWithTx(SoInvoiceDTO dto){
		
		return soInvoiceWriteService.deleteSoInvoiceWithTx(dto);
		
	}

	/**
	 * 以订单id为条件修改发票信息
	 * @param orderCode
	 * @param titleType
	 * @param title
	 * @param invoiceType
	 * @param taxNo
	 * @return
	 */
	public int updateSoInvoiceBySoId(Long soId, Integer titleType, String title, Integer invoiceType,
			String taxNo,Integer invoiceContentType) {
		return soInvoiceWriteService.updateSoInvoiceBySoId(soId,titleType,title,invoiceType,taxNo,invoiceContentType);
	}

	/**
	 * 根据订单编号查询发票信息
	 * @param orderCode
	 * @param platformId 
	 * @return
	 */
	public SoInvoiceDTO querySoInvoiceByOrderCode(String orderCode, Long platformId) {
		return soInvoiceReadService.querySoInvoiceByOrderCode(orderCode,platformId);
	}

	/**
	 * 查询不重复的用户发票记录
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public List<SoInvoiceDTO> queryDistinctInvoiceByUserId(Long userId, Long platformId) {
		
		return soInvoiceReadService.queryDistinctInvoiceByUserId(userId,platformId);
	}

	public Long insertSoInvoiceWithTx(SoInvoiceDTO inv, Long commonInvoiceId) {
		return soInvoiceWriteService.insertSoInvoiceWithTx(inv, commonInvoiceId);
	}
	
	public Long insertSoInvoiceWithTx(SoInvoiceDTO inv) {
		return soInvoiceWriteService.insertSoInvoiceWithTx(inv);
	}

	/**
	 * 查询订单主发票
	 * @param orderId
	 * @return
	 */
	public SoInvoiceDTO queryMainSoInvoiceByOrderId(Long orderId) {
		return soInvoiceReadService.queryMainSoInvoiceByOrderId(orderId);
	}

	/**
	 * 根据子订单id查询发票信息
	 * @param id
	 * @return
	 */
	public SoInvoiceDTO querySoInvoiceBySoChildId(Long id) {
		return soInvoiceReadService.querySoInvoiceBySoChildId(id);
	}

	/**
	 * 开局发票并新增订单操作流水
	 * @param insertInvoice
	 */
	public boolean insertSoInvoiceAndOperateFlow(SoInvoiceDTO insertInvoice,Long operatorId) {
		//新增发票
		soInvoiceWriteService.insertSoInvoiceWithTx(insertInvoice);
//		SoChildDTO cond=new SoChildDTO();
//		cond.setId(insertInvoice.getSoChildId());
//		cond.setInvoiceId(invoiceId);
//		scWriteService.updateSoChildWithTx(cond);
		//新增操作流水
		SoChildFlowDTO flow=new SoChildFlowDTO();
		flow.setOperate(2);
		flow.setOperatorId(operatorId);
		flow.setSoChildId(insertInvoice.getSoChildId());
		flow.setSoId(insertInvoice.getSoId());
		opFlowWriteService.insertSoChildFlowWithTx(flow);
		return true;
	}

	/**
	 * 更换公共发票信息
	 * @param orderId
	 * @param commonInvoiceId
	 * @return
	 */
	public Integer changeCommonInvoiceInfoWithTx(Long orderId, Long commonInvoiceId) {
		
		return soInvoiceWriteService.changeCommonInvoiceInfoWithTx(orderId, commonInvoiceId);
	}

	public Integer updateInvoiceTypeInfoWithTx(SoInvoiceDTO dto) {
		
		return soInvoiceWriteService.updateInvoiceTypeInfoWithTx(dto);
	}

}
	