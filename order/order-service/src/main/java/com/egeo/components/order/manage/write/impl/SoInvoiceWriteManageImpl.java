package com.egeo.components.order.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoInvoiceWriteManage;
import com.egeo.components.order.dao.read.InvoiceReadDAO;
import com.egeo.components.order.dao.read.SoChildReadDAO;
import com.egeo.components.order.dao.read.SoInvoiceReadDAO;
import com.egeo.components.order.dao.read.SoReadDAO;
import com.egeo.components.order.dao.write.SoChildFlowWriteDAO;
import com.egeo.components.order.dao.write.SoInvoiceWriteDAO;
import com.egeo.components.order.po.InvoicePO;
import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.po.SoInvoicePO;
import com.egeo.components.order.po.SoPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class SoInvoiceWriteManageImpl implements SoInvoiceWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoInvoiceWriteDAO soInvoiceWriteDAO;
	@Autowired
	private SoInvoiceReadDAO soInvoiceReadDAO;
	@Autowired
	private SoChildFlowWriteDAO soChildFlowWriteDAO;
	@Autowired
	private InvoiceReadDAO invoiceReadDAO;
	@Autowired
	private SoReadDAO soReadDAO;
	@Autowired
	private SoChildReadDAO soChildReadDAO;
	
	@Override
	public Long insertSoInvoiceWithTx(SoInvoicePO po,String mail) {
		
		SoInvoicePO po2 = new SoInvoicePO();
		po2.setSoChildId(po.getSoChildId());
		po2.setSoId(po.getSoId());
		List<SoInvoicePO> findSoInvoiceAll = soInvoiceReadDAO.findAll(po,null);
		
		int insertSoInvoiceId = 0;
		if(EmptyUtil.isNotEmpty(findSoInvoiceAll)){
		po.setId(findSoInvoiceAll.get(0).getId());
		po.setCreateTime(null);
		po.setUpdateTime(null);
		int update = soInvoiceWriteDAO.update(po);
		}else{
			insertSoInvoiceId = soInvoiceWriteDAO.insert(po);
		}
		
		//记录开票的流水
		SoChildFlowPO soChildFlowPO = new SoChildFlowPO();
		soChildFlowPO.setSoId(po.getSoId());
		soChildFlowPO.setSoChildId(po.getSoChildId());
		soChildFlowPO.setDoMail(mail);
		soChildFlowWriteDAO.insert(soChildFlowPO);
		
		return (long)insertSoInvoiceId;
	}

	@Override
	public int updateSoInvoiceWithTx(SoInvoicePO po) {
		int i;
		i = soInvoiceWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("修改发票失败!");
		return i;
	}
	
	@Override
	public int updateSoInvoiceWithTx(SoInvoicePO po, Long commonInvoiceId) {
		// 发票公共信息拷贝
		copyInvoiceCommonInfo(po, commonInvoiceId);
		
		int i;
		i = soInvoiceWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("修改发票失败!");
		return i;
	}

	@Override
	public int deleteSoInvoiceWithTx(SoInvoicePO po) {
		int i = soInvoiceWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateSoInvoiceBySoIdWithTx(Long soId, Integer titleType, String title, Integer invoiceType,
			String taxNo,Integer invoiceContentType) {
		return soInvoiceWriteDAO.updateSoInvoiceBySoId(soId,titleType,title,invoiceType,taxNo,invoiceContentType);
	}

	@Override
	public Long insertSoInvoiceWithTx(SoInvoicePO po) {
		
		int i ;
		try {
				i = soInvoiceWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}	
	
	@Override
	public Long insertSoInvoiceWithTx(SoInvoicePO po, Long commonInvoiceId) {
		// 发票公共信息拷贝
		copyInvoiceCommonInfo(po, commonInvoiceId);
		
		int i ;
		try {
			i = soInvoiceWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	private void copyInvoiceCommonInfo(SoInvoicePO po, Long commonInvoiceId) {
		if (commonInvoiceId != null) {
			po.setInvoiceId(commonInvoiceId);
			
			InvoicePO invoicePO = new InvoicePO();
			invoicePO.setId(commonInvoiceId);
			InvoicePO invoicePO_ = invoiceReadDAO.findById(invoicePO);
			if (invoicePO_ == null)
				throw new BusinessException("发票公共信息错误");
			
			if (po.getInvoiceForm() == 2 && 
					(EmptyUtil.isEmpty(invoicePO_.getRegisterAddr()) || EmptyUtil.isEmpty(invoicePO_.getRegisterTel()) 
							|| EmptyUtil.isEmpty(invoicePO_.getDepositBank()) || EmptyUtil.isEmpty(invoicePO_.getBankAccount())
							|| EmptyUtil.isEmpty(invoicePO_.getBusinessLicenceUrl())))
				throw new BusinessException("发票公共信息不完整");
			
			po.setInvoiceTitleContent(invoicePO_.getTitleContent());
			po.setTaxpayerIdentificationCode(invoicePO_.getTaxpayerIdentificationCode());
			po.setRegisterAddr(invoicePO_.getRegisterAddr());
			po.setRegisterTel(invoicePO_.getRegisterTel());
			po.setDepositBank(invoicePO_.getDepositBank());
			po.setBankAccount(invoicePO_.getBankAccount());
			po.setBusinessLicenceUrl(invoicePO_.getBusinessLicenceUrl());
		}
		
	}

	@Override
	public Integer changeCommonInvoiceInfoWithTx(Long orderId, Long commonInvoiceId) {
		SoPO soPO = new SoPO();
		soPO.setId(orderId);
		soPO = soReadDAO.findById(soPO);
		if (soPO == null)
			throw new BusinessException("订单不存在");
		
		InvoicePO invoicePO = new InvoicePO();
		invoicePO.setId(commonInvoiceId);
		invoicePO = invoiceReadDAO.findById(invoicePO);
		if (invoicePO == null)
			throw new BusinessException("公共发票信息不存在");
		
		SoInvoicePO po = new SoInvoicePO();
		po.setInvoiceTitleContent(invoicePO.getTitleContent());
		po.setTaxpayerIdentificationCode(invoicePO.getTaxpayerIdentificationCode());
		po.setRegisterAddr(invoicePO.getRegisterAddr());
		po.setRegisterTel(invoicePO.getRegisterTel());
		po.setDepositBank(invoicePO.getDepositBank());
		po.setBankAccount(invoicePO.getBankAccount());
		po.setBusinessLicenceUrl(invoicePO.getBusinessLicenceUrl());
		po.setSoId(orderId);
		
		SoChildPO soChildPO = new SoChildPO();
		soChildPO.setSoId(orderId);
		List<SoChildPO> soChildList = soChildReadDAO.findAll(soChildPO,null);
		for (SoChildPO soChildPO_ : soChildList) {
			po.setSoChildId(soChildPO_.getId());
			SoInvoicePO soInvoicePO = new SoInvoicePO();
			soInvoicePO.setSoId(orderId);
			soInvoicePO.setSoChildId(soChildPO_.getId());
			//插入时未写入userID，校验是否存在时去掉此条件[FGJAPP-2158]
//			soInvoicePO.setUserId(soPO.getUserId());
			List<SoInvoicePO> soInvoiceList = soInvoiceReadDAO.findAll(soInvoicePO,null);
			if (EmptyUtil.isEmpty(soInvoiceList)) {
				// 新增订单发票信息
				po.setInvoiceStatus(0);
				soInvoiceWriteDAO.insert(po);
			}
		}

		return soInvoiceWriteDAO.changeCommonInvoiceInfoWithTx(po);
	}

	@Override
	public Integer updateInvoiceTypeInfoWithTx(SoInvoicePO po) {
		SoPO soPO = new SoPO();
		soPO.setId(po.getSoId());
		soPO = soReadDAO.findById(soPO);
		if (soPO == null)
			throw new BusinessException("订单不存在");
		
		po.setSoId(po.getSoId());
		SoChildPO soChildPO = new SoChildPO();
		soChildPO.setSoId(po.getSoId());
		List<SoChildPO> soChildList = soChildReadDAO.findAll(soChildPO,null);
		for (SoChildPO soChildPO_ : soChildList) {
			po.setSoChildId(soChildPO_.getId());
			SoInvoicePO soInvoicePO = new SoInvoicePO();
			soInvoicePO.setSoId(po.getSoId());
			soInvoicePO.setSoChildId(soChildPO_.getId());
			//插入时未写入userID，校验是否存在时去掉此条件[FGJAPP-2158]
//			soInvoicePO.setUserId(soPO.getUserId());
			List<SoInvoicePO> soInvoiceList = soInvoiceReadDAO.findAll(soInvoicePO,null);
			if (EmptyUtil.isEmpty(soInvoiceList)) {
				// 新增订单发票信息
				po.setInvoiceStatus(0);
				soInvoiceWriteDAO.insert(po);
			} 
		}
		
		return soInvoiceWriteDAO.updateInvoiceTypeInfoWithTx(po);
	}	
}
	