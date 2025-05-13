package com.egeo.components.order.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoRefundItemDTO;
import com.egeo.components.order.service.read.SoRefundItemReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoRefundReadService;
import com.egeo.components.order.manage.read.SoRefundReadManage;
import com.egeo.components.order.converter.SoRefundConverter;
import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.po.SoRefundPO;

import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Service("soRefundReadService")
public class SoRefundReadServiceImpl  implements SoRefundReadService {
	@Autowired
	private SoRefundReadManage soRefundReadManage;

	@Resource
	private SoRefundItemReadService soRefundItemReadService;

	@Override
	public SoRefundDTO findSoRefundById(SoRefundDTO dto) {
		SoRefundPO po = SoRefundConverter.toPO(dto);
		SoRefundPO list = soRefundReadManage.findSoRefundById(po);
		return SoRefundConverter.toDTO(list);
	}

	@Override
	public PageResult<SoRefundDTO> findSoRefundOfPage(SoRefundDTO dto, List<Long> userIdList, Pagination page) {
		SoRefundPO po = SoRefundConverter.toPO(dto);
        PageResult<SoRefundPO> pageResult = soRefundReadManage.findSoRefundOfPage(po, userIdList, page);

        List<SoRefundDTO> list = SoRefundConverter.toDTO(pageResult.getList());
        PageResult<SoRefundDTO> result = new PageResult<SoRefundDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoRefundDTO> findSoRefundAll(SoRefundDTO dto) {
		SoRefundPO po = SoRefundConverter.toPO(dto);
		List<SoRefundPO> list = soRefundReadManage.findSoRefundAll(po);
		return SoRefundConverter.toDTO(list);
	}

	@Override
	public List<String> genSoRefundNO() {
		String soRefundCodeByCash = SequenceUtil.genSoRefundNo();
		String soRefundCodeByFubi = SequenceUtil.genSoRefundNo();
		String soRefundCodeByJiDian = SequenceUtil.genSoRefundNo();
		String soRefundCodeByBuyCard = SequenceUtil.genSoRefundNo();
		SoRefundDTO soRefundDTO = new SoRefundDTO();
		soRefundDTO.setSoRefundCode(soRefundCodeByCash);
		List<SoRefundDTO> soRefundDTOListByCash = findSoRefundAll(soRefundDTO);
		soRefundDTO.setSoRefundCode(soRefundCodeByCash);
		List<SoRefundDTO> soRefundDTOListByFubi = findSoRefundAll(soRefundDTO);
		soRefundDTO.setSoRefundCode(soRefundCodeByJiDian);
		List<SoRefundDTO> soRefundDTOListByJiDian = findSoRefundAll(soRefundDTO);
		soRefundDTO.setSoRefundCode(soRefundCodeByBuyCard);
		List<SoRefundDTO> soRefundDTOListByBuyCard = findSoRefundAll(soRefundDTO);


		List<String> list = new ArrayList<String>();
		list.add(soRefundCodeByCash);
		list.add(soRefundCodeByFubi);
		list.add(soRefundCodeByJiDian);
		list.add(soRefundCodeByBuyCard);

		if ((EmptyUtil.isNotEmpty(soRefundDTOListByFubi)
				|| EmptyUtil.isNotEmpty(soRefundDTOListByCash)
				|| EmptyUtil.isNotEmpty(soRefundDTOListByJiDian)
				|| EmptyUtil.isNotEmpty(soRefundDTOListByBuyCard))
				&& !(soRefundCodeByCash.equals(soRefundDTOListByFubi) || soRefundCodeByCash.equals(soRefundCodeByJiDian))
					|| soRefundCodeByCash.equals(soRefundCodeByBuyCard)) {
			return genSoRefundNO();
		} else {
			return list;
		}
	}

	@Override
	public List<SoRefundDTO> getByBatchId(Long batchId,Long orderId,Integer type){
		if(EmptyUtil.isEmpty(batchId)){
			return null;
		}
		if(type !=null && (type ==5 || type==6 || type==16)){
			return getByBatchIdAndType(batchId,orderId,type);
		}
		return getByBatchIdUnType(batchId,orderId);
	}

	@Override
	public List<SoRefundItemDTO> getRefundItemByBatchId(Long batchId,Long orderId,Integer type){
		List<SoRefundDTO> soRefundDTOList = getByBatchId(batchId,orderId,type);
		if(CollectionUtils.isEmpty(soRefundDTOList)){
			return null;
		}
		Long id = soRefundDTOList.get(0).getId();
		SoRefundItemDTO soRefundItemDTO = new SoRefundItemDTO();
		soRefundItemDTO.setRefundId(id);
		return soRefundItemReadService.findSoRefundItemAll(soRefundItemDTO);
	}

	private List<SoRefundDTO>  getByBatchIdAndType(Long batchId,Long orderId,Integer type){
		List<SoRefundPO> poList =null;
		if(type==5){
			//现金
			poList =soRefundReadManage.getByBatchCashId(batchId,orderId);
		}else if(type==6){
			//餐卡/积分
			poList = soRefundReadManage.getByBatchFuBiId(batchId,orderId);
		}else if(type ==16){
			//积点
			poList =soRefundReadManage.getByBatchJIDianId(batchId,orderId);
		}

		return SoRefundConverter.toDTO(poList);
	}


	private List<SoRefundDTO>  getByBatchIdUnType(Long batchId,Long orderId){
		List<SoRefundPO> poList =null;

		//积点
		poList =soRefundReadManage.getByBatchJIDianId(batchId,orderId);
		if(!CollectionUtils.isEmpty(poList)){
			return SoRefundConverter.toDTO(poList);
		}
		//餐卡/积分
		poList = soRefundReadManage.getByBatchFuBiId(batchId,orderId);
		if(!CollectionUtils.isEmpty(poList)){
			return SoRefundConverter.toDTO(poList);
		}
		//现金
		poList =soRefundReadManage.getByBatchCashId(batchId,orderId);
		if(!CollectionUtils.isEmpty(poList)){
			return SoRefundConverter.toDTO(poList);
		}
		return null;
	}
}
