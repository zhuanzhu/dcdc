package com.egeo.components.order.business.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.order.business.InvoiceManage;
import com.egeo.components.order.converter.InvoiceConverter;
import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoInvoiceDTO;
import com.egeo.components.order.facade.InvoiceFacade;
import com.egeo.components.order.facade.SoFacade;
import com.egeo.components.order.facade.SoInvoiceFacade;
import com.egeo.components.order.vo.InvoiceSimpleVO;
import com.egeo.components.order.vo.InvoiceVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("invoice")
public class InvoiceManageImpl implements InvoiceManage {

	@Resource(name = "invoiceFacade")
	private InvoiceFacade invoiceFacade;

	@Resource(name = "soFacade")
	private SoFacade soFacade;
	
	@Resource(name = "soInvoiceFacade")
	private SoInvoiceFacade soInvoiceFacade;

	@Override
	public InvoiceDTO findInvoiceById(InvoiceDTO dto) {
		return invoiceFacade.findInvoiceById(dto);
	}

	@Override
	public PageResult<InvoiceSimpleVO> findInvoiceOfPage(InvoiceDTO dto, Pagination page) {
		dto.setTitleType(1);
		dto.setCreateType(0);	// 客户端只能看用户创建的公共发票信息(类型单位)
		PageResult<InvoiceDTO> rt = invoiceFacade.findInvoiceOfPage(dto, page);

		List<InvoiceSimpleVO> list = new ArrayList<>();
		for (InvoiceDTO invoiceDTO : rt.getList()) {
			InvoiceSimpleVO vo = new InvoiceSimpleVO();
			vo.setId(invoiceDTO.getId());
			vo.setTitleContent(invoiceDTO.getTitleContent());
			vo.setTaxpayerIdentificationCode(invoiceDTO.getTaxpayerIdentificationCode());
			vo.setIsDefault(invoiceDTO.getIsDefault());
			
			// 判断发票信息是否完整
			vo.setIsFullInfo(getIsFullInfo(invoiceDTO));
			
			list.add(vo);
		}

		PageResult<InvoiceSimpleVO> result = new PageResult<>();
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		result.setList(list);

		return result;
	}

	/**
	 * 判断发票信息是否完整
	 * @param invoiceDTO
	 * @return	0:不完整   1:完整
	 */
	private Integer getIsFullInfo(InvoiceDTO invoiceDTO) {
		if (EmptyUtil.isNotEmpty(invoiceDTO.getRegisterAddr())
				&& EmptyUtil.isNotEmpty(invoiceDTO.getRegisterTel())
				&& EmptyUtil.isNotEmpty(invoiceDTO.getDepositBank())
				&& EmptyUtil.isNotEmpty(invoiceDTO.getBankAccount())
				&& EmptyUtil.isNotEmpty(invoiceDTO.getBusinessLicenceUrl())) {

			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public List<InvoiceDTO> findInvoiceAll(InvoiceDTO dto) {
		return invoiceFacade.findInvoiceAll(dto);
	}

	@Override
	public Long insertInvoiceWithTx(InvoiceDTO dto) {
		return invoiceFacade.insertInvoiceWithTx(dto);
	}

	@Override
	public int updateInvoiceWithTx(InvoiceDTO dto) {
		return invoiceFacade.updateInvoiceWithTx(dto);
	}

	@Override
	public int deleteInvoiceWithTx(InvoiceDTO dto) {
		// 删除校验
		InvoiceDTO dto_ = invoiceFacade.findInvoiceById(dto);
		if (dto_ == null || !dto_.getPlatformId().equals(dto.getPlatformId()) 
				|| !dto_.getUserId().equals(dto.getUserId()) || dto_.getIsDelete() == 1) {
			throw new BusinessException("该公共发票不存在");
		}
		
		dto.setIsDelete(1);
		return invoiceFacade.updateInvoiceWithTx(dto);
	}

	@Override
	public JsonResult<Long> insertOrUpdateInvoiceWithTx(InvoiceDTO dto, Long orderId) {
		// 参数校验
		if (EmptyUtil.isEmpty(dto.getTitleContent()))
			return JsonResult.fail("请输入抬头名称");
		if (dto.getTitleContent().length() > 30)
			return JsonResult.fail("抬头名称长度不能大于30");
		if (EmptyUtil.isEmpty(dto.getTitleType()))
			return JsonResult.fail("发票抬头类型不能为空");
		
		// 税号: 单位发票税号必填，限制输入数字和字母，只能输入 15位、18 或者 20 位
		if (dto.getTitleType() == 1) {
			
			if (EmptyUtil.isEmpty(dto.getTaxpayerIdentificationCode()))
				return JsonResult.fail("请输入税号");
			if (!StringUtils.validOnlyContainCharAndNum(dto.getTaxpayerIdentificationCode())
					|| (dto.getTaxpayerIdentificationCode().length() != 15
					&& dto.getTaxpayerIdentificationCode().length() != 18
					&& dto.getTaxpayerIdentificationCode().length() != 20))
				return JsonResult.fail("请输入正确的税号");
		}
		
		if (EmptyUtil.isNotEmpty(dto.getRegisterAddr()) && dto.getRegisterAddr().length() > 50)
			return JsonResult.fail("注册地址长度不能大于50");
		if (EmptyUtil.isNotEmpty(dto.getDepositBank()) && dto.getDepositBank().length() > 30)
			return JsonResult.fail("开户银行长度不能大于50");
		
		if (orderId == null) {
			// 用户创建
			dto.setCreateType(0);
		} else {
			// 运营创建
			SoDTO soDTO = new SoDTO();
			soDTO.setId(orderId);
			SoDTO soDTO_ = soFacade.findSoById(soDTO);
			if (soDTO_ == null)
				return JsonResult.fail("订单不存在");
			dto.setPlatformId(soDTO_.getPlatformId());
			dto.setUserId(soDTO_.getUserId());
			dto.setCreateType(1);
			dto.setIsDefault(0);
		}
		if (EmptyUtil.isEmpty(dto.getIsDefault()))
			return JsonResult.fail("设为默认参数缺失");

		Long invoiceId = null;
		if (dto.getId() == null || dto.getId().longValue() == 0) {
			// 新增
			invoiceId = invoiceFacade.insertInvoiceWithTx(dto);
		} else {
			// 编辑
			InvoiceDTO invoiceDTO = invoiceFacade.findInvoiceById(dto);
			if (invoiceDTO == null)
				return JsonResult.fail("该公共发票信息不存在");
			if (!invoiceDTO.getCreateType().equals(dto.getCreateType())) 
				return JsonResult.fail("运营不可修改用户创建的公共发票信息");
			invoiceFacade.updateInvoiceWithTx(dto);
			invoiceId = dto.getId();
		}

		return JsonResult.success(invoiceId);
	}

	@Override
	public InvoiceSimpleVO chooseInvoice(InvoiceDTO dto, Long id) {
		// 判断是否已选择公共发票
		InvoiceDTO dto_ = new InvoiceDTO();
		InvoiceDTO rt = null;
		InvoiceSimpleVO vo = new InvoiceSimpleVO();
		if (id != null && !id.equals(0L)) {
			// 是,返回已选择发票信息
			SoInvoiceDTO soInvoiceDTO = soInvoiceFacade.findSoInvoiceById(id);
			if (soInvoiceDTO != null) {
				if (soInvoiceDTO.getInvoiceId() != null && 
						soInvoiceDTO.getInvoiceTitleType() != null && soInvoiceDTO.getInvoiceTitleType() == 1) {
					dto_.setId(soInvoiceDTO.getInvoiceId());
					rt = invoiceFacade.findInvoiceById(dto_);
				}
				
				vo.setTitleContent(soInvoiceDTO.getInvoiceTitleContent());
				vo.setInvoiceContentType(soInvoiceDTO.getInvoiceContentType());
				vo.setInvoiceTitleType(soInvoiceDTO.getInvoiceTitleType());
				vo.setInvoiceForm(soInvoiceDTO.getInvoiceForm());
				vo.setId(soInvoiceDTO.getId());
			}
			
		} else {
			// 否,返回默认发票信息
			dto_.setUserId(dto.getUserId());
			dto_.setPlatformId(dto.getPlatformId());
			dto_.setIsDefault(1);
			dto_.setIsDelete(0);
			List<InvoiceDTO> invoiceList = invoiceFacade.findInvoiceAll(dto_);
			rt = EmptyUtil.isNotEmpty(invoiceList) ? invoiceList.get(0) : null;
			
			vo.setTitleContent(rt != null ? rt.getTitleContent() :org.apache.commons.lang3.StringUtils.EMPTY);
		}

		if (rt != null) {
			vo.setInvoiceId(rt.getId());
			vo.setTaxpayerIdentificationCode(rt.getTaxpayerIdentificationCode());
			vo.setIsDefault(rt.getIsDefault());
			// 发票信息是否完整
			vo.setIsFullInfo(getIsFullInfo(rt));
		}

		// 查询公共发票的数量(用户创建,单位类型)
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setUserId(dto.getUserId());
		invoiceDTO.setPlatformId(dto.getPlatformId());
		invoiceDTO.setCreateType(0);
		invoiceDTO.setTitleType(1);
		invoiceDTO.setIsDelete(0);
		List<InvoiceDTO> invoiceList = invoiceFacade.findInvoiceAll(invoiceDTO);
		vo.setCommonInvoiceListSize(invoiceList.size());
		
		return vo;
	}

	@Override
	public JsonResult<List<InvoiceVO>> findInvoiceAll(Long orderId) {
		SoDTO soDTO = new SoDTO();
		soDTO.setId(orderId);
		soDTO = soFacade.findSoById(soDTO);
		if (soDTO == null)
			return JsonResult.fail("订单不存在");
		
		// 查询公共发票信息列表
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setIsDelete(0);
		invoiceDTO.setUserId(soDTO.getUserId());
		List<InvoiceDTO> list = invoiceFacade.findInvoiceAll(invoiceDTO);
		
		// 查询发票快照
		SoInvoiceDTO soInvoice = soInvoiceFacade.queryMainSoInvoiceByOrderId(orderId);
		InvoiceVO invoiceVO = new InvoiceVO();
		if (soInvoice != null) {
			
			invoiceVO.setBankAccount(soInvoice.getBankAccount());
			invoiceVO.setBusinessLicenceUrl(soInvoice.getBusinessLicenceUrl());
			invoiceVO.setDepositBank(soInvoice.getDepositBank());
			invoiceVO.setRegisterAddr(soInvoice.getRegisterAddr());
			invoiceVO.setRegisterTel(soInvoice.getRegisterTel());
			invoiceVO.setTitleContent(soInvoice.getInvoiceTitleContent());
			invoiceVO.setTaxpayerIdentificationCode(soInvoice.getTaxpayerIdentificationCode());
		}
		
		List<InvoiceVO> rt = new ArrayList<>();
		rt.add(invoiceVO);
		rt.addAll(InvoiceConverter.toVO(list));
		
		return JsonResult.success(rt);
	}

	@Override
	public JsonResult<InvoiceVO> findDefaultInvoiceByUserId(Long userId, Long platformId) {
		InvoiceDTO invoiceDTO = new InvoiceDTO();
		invoiceDTO.setUserId(userId);
		invoiceDTO.setPlatformId(platformId);
		invoiceDTO.setIsDefault(Integer.valueOf(1));//设置查询条件:默认发票
		List<InvoiceDTO> invoiceAll=invoiceFacade.findDefaultInvoiceByUserId(invoiceDTO);
		if(EmptyUtil.isEmpty(invoiceAll)){
			return JsonResult.success();
		}else if(invoiceAll.size()>1){
			throw new BusinessException("您的默认发票出现多条,请联系管理员");
		}
		return JsonResult.success(InvoiceConverter.toVO(invoiceAll.get(0)));

	}

}
