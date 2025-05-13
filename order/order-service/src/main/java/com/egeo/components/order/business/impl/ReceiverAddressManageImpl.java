package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.order.business.ReceiverAddressManage;
import com.egeo.components.order.facade.ReceiverAddressFacade;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDetailDTO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;
import com.egeo.utils.str.StringUtils;

@Service("receiverAddress")
public class ReceiverAddressManageImpl implements ReceiverAddressManage{

	
	@Resource(name="receiverAddressFacade")
	private ReceiverAddressFacade receiverAddressFacade;

	@Override
	public ReceiverAddressDTO findReceiverAddressById(Long id) {
		return receiverAddressFacade.findReceiverAddressById(id);
	}

	@Override
	public PageResult<ReceiverAddressDTO> findReceiverAddressOfPage(ReceiverAddressDTO dto, Pagination page) {
		return receiverAddressFacade.findReceiverAddressOfPage(dto, page);
	}

	@Override
	public List<ReceiverAddressDTO> findReceiverAddressAll(ReceiverAddressDTO dto) {
		dto.setIsDefault(0);
		List<ReceiverAddressDTO> list = receiverAddressFacade.findReceiverAddressAll(dto);
		for (ReceiverAddressDTO receiverAddressDTO : list) {
			receiverAddressDTO.setGoodReceiverAddress(receiverAddressDTO.getGoodReceiverProvince()+receiverAddressDTO.getGoodReceiverCity()+receiverAddressDTO.getGoodReceiverCounty()+receiverAddressDTO.getGoodReceiverAddress());
		}
		
		dto.setIsDefault(1);
		List<ReceiverAddressDTO> lists = receiverAddressFacade.findReceiverAddressAll(dto);
		if(lists.size() > 0){
			ReceiverAddressDTO receiverAddressDTO = lists.get(0);
			receiverAddressDTO.setGoodReceiverAddress(receiverAddressDTO.getGoodReceiverProvince()+receiverAddressDTO.getGoodReceiverCity()+receiverAddressDTO.getGoodReceiverCounty()+receiverAddressDTO.getGoodReceiverAddress());
			list.add(0, receiverAddressDTO);
		}
		return list;
	}

	@Override
	public Long insertReceiverAddressWithTx(ReceiverAddressDTO dto) {
		if(!StringUtils.validMobile(dto.getGoodReceiverMobile())){
			throw new BusinessException("请输入正确的手机号");
		}
		return receiverAddressFacade.insertReceiverAddressWithTx(dto);
	}

	@Override
	public int updateReceiverAddressWithTx(ReceiverAddressDTO dto) {
		if(!StringUtils.validMobile(dto.getGoodReceiverMobile())){
			throw new BusinessException("请输入正确的手机号");
		}
		return receiverAddressFacade.updateReceiverAddressWithTx(dto);
	}

	@Override
	public int deleteReceiverAddressWithTx(ReceiverAddressDTO dto) {
		return receiverAddressFacade.deleteReceiverAddressWithTx(dto);
	}

	@Override
	public String defaultAddressById(Long id,Long userId) {
		//查询默认地址
		ReceiverAddressDTO receiverAddressDTO = new ReceiverAddressDTO();
		receiverAddressDTO.setIsDefault(1);
		receiverAddressDTO.setUserId(userId);
		List<ReceiverAddressDTO> receiverAddressList = receiverAddressFacade.findReceiverAddressAll(receiverAddressDTO);
		if(receiverAddressList.size() > 0){
			ReceiverAddressDTO dto = receiverAddressList.get(0);
			dto.setIsDefault(0);
			receiverAddressFacade.updateReceiverAddressWithTx(dto);
		}
		//修改默认地址
		receiverAddressDTO.setId(id);
		receiverAddressFacade.updateReceiverAddressWithTx(receiverAddressDTO);
		return "修改默认地址成功";
	}

	@Override
	public List<ReceiverAddressDetailDTO> findReceiveDetailBySoId(Long soId, Long platformId) {
		return receiverAddressFacade.findReceiveDetailBySoId(soId,platformId);
	}

	@Override
	public JsonResult<String> modifyReceiverAddress(Long soChildId,ReceiverAddressDTO receiverAddressDTO) {
		 try {
			receiverAddressFacade.modifyReceiverAddress(soChildId,receiverAddressDTO);
		} catch (Exception e) {
			return JsonResult.fail("修改失败");
		}
		 return JsonResult.success("修改成功");
	}

	@Override
	public List<ReceiverAddressDTO> queryReceiverAddressListCreatedByUser(Long userId, Long platformId) {
		
		return receiverAddressFacade.queryReceiverAddressListCreatedByUser(userId,platformId);
	}

	@Override
	public int receiverAddressSumByUserId(Long userId, Long platformId) {
		// TODO Auto-generated method stub
		return receiverAddressFacade.receiverAddressSumByUserId(userId, platformId);
	}


}
	