package com.egeo.components.order.service.read.impl;

import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.ReceiverAddressReadService;
import com.egeo.components.order.manage.read.ReceiverAddressReadManage;
import com.egeo.components.order.condition.ReceiverAddressCondition;
import com.egeo.components.order.converter.ReceiverAddressConverter;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDetailDTO;
import com.egeo.components.order.po.ReceiverAddressPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("receiverAddressReadService")
public class ReceiverAddressReadServiceImpl  implements ReceiverAddressReadService {
	@Autowired
	private ReceiverAddressReadManage receiverAddressReadManage;

	@Override
	public ReceiverAddressDTO findReceiverAddressById(Long id) {

		ReceiverAddressPO po =new ReceiverAddressPO();
		po.setId(id);
		ReceiverAddressPO list = receiverAddressReadManage.findReceiverAddressById(po);
		return ReceiverAddressConverter.toDTO(list);
	}

	@Override
	public PageResult<ReceiverAddressDTO> findReceiverAddressOfPage(ReceiverAddressDTO dto, Pagination page) {
		ReceiverAddressPO po = ReceiverAddressConverter.toPO(dto);
        PageResult<ReceiverAddressPO> pageResult = receiverAddressReadManage.findReceiverAddressOfPage(po, page);

        List<ReceiverAddressDTO> list = ReceiverAddressConverter.toDTO(pageResult.getList());
        PageResult<ReceiverAddressDTO> result = new PageResult<ReceiverAddressDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ReceiverAddressDTO> findReceiverAddressAll(ReceiverAddressDTO dto) {
		ReceiverAddressPO po = ReceiverAddressConverter.toPO(dto);
		List<ReceiverAddressPO> list = receiverAddressReadManage.findReceiverAddressAll(po);
		return ReceiverAddressConverter.toDTO(list);
	}

	@Override
	public ReceiverAddressDTO queryDefaultReceiverAddress(Long memberId, Long platformId) {
		return ReceiverAddressConverter.toDTO(receiverAddressReadManage.queryDefaultReceiverAddress(memberId, platformId));

	}

	@Override
	public List<ReceiverAddressDetailDTO> findReceiveDetailBySoId(Long soId, Long platformId) {
		List<ReceiverAddressCondition> list = receiverAddressReadManage.findReceiveDetailBySoId(soId, platformId);
		return ReceiverAddressConverter.toDetailDTO(list);
	}

	@Override
	public List<ReceiverAddressDTO> queryReceiverAddressListCreatedByUser(Long userId, Long platformId) {

		return ReceiverAddressConverter.toDTO(receiverAddressReadManage.queryReceiverAddressListCreatedByUser(userId,platformId));
	}

	@Override
	public int receiverAddressSumByUserId(Long userId, Long platformId) {
		// TODO Auto-generated method stub
		return receiverAddressReadManage.receiverAddressSumByUserId(userId, platformId);
	}

	@Override
	public ReceiverAddressDTO findReceiverAddressByChildCodeAndType(String childCode, int type) {
		return ReceiverAddressConverter.toDTO(receiverAddressReadManage.queryReceiverAddressByChildCodeAndType(childCode,type));
	}

	@Override
	public List<ReceiverAddressDTO> queryReceiverAddressListCreateByBackStage(String childCode) {
		return ReceiverAddressConverter.toDTO(receiverAddressReadManage.queryReceiverAddressListCreateByBackStage(childCode));

	}

	@Override
	public List<Long> getUserIdListByReceiverAddressMobile(String goodReceiverMobile){
		if(EmptyUtil.isEmpty(goodReceiverMobile) || EmptyUtil.isBlank(goodReceiverMobile)){
			return null;
		}
		return receiverAddressReadManage.getUserIdListByReceiverAddressMobile(goodReceiverMobile.trim());
	}

}
