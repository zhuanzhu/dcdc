package com.egeo.components.promotion.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ErCardRecordReadService;
import com.egeo.components.promotion.manage.read.ErCardRecordReadManage;
import com.egeo.components.promotion.manage.write.ECardWriteManage;
import com.egeo.components.promotion.converter.ErCardRecordConverter;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.po.ECardPO;
import com.egeo.components.promotion.po.ErCardRecordPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("erCardRecordReadService")
public class ErCardRecordReadServiceImpl implements ErCardRecordReadService {
	@Autowired
	private ErCardRecordReadManage erCardRecordReadManage;
	
	@Autowired
	private ECardWriteManage eCardWriteManage;

	@Override
	public ErCardRecordDTO findErCardRecordById(ErCardRecordDTO dto) {
		ErCardRecordPO po = ErCardRecordConverter.toPO(dto);
		ErCardRecordPO list = erCardRecordReadManage.findErCardRecordById(po);		
		return ErCardRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<ErCardRecordDTO> findErCardRecordOfPage(ErCardRecordDTO dto, Pagination page) {
		ErCardRecordPO po = ErCardRecordConverter.toPO(dto);
        PageResult<ErCardRecordPO> pageResult = erCardRecordReadManage.findErCardRecordOfPage(po, page);
        
        List<ErCardRecordDTO> list = ErCardRecordConverter.toDTO(pageResult.getList());
        PageResult<ErCardRecordDTO> result = new PageResult<ErCardRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ErCardRecordDTO> findErCardRecordAll(ErCardRecordDTO dto) {
		ErCardRecordPO po = ErCardRecordConverter.toPO(dto);
		List<ErCardRecordPO> list = erCardRecordReadManage.findErCardRecordAll(po);		
		return ErCardRecordConverter.toDTO(list);
	}

	@Override
	public List<ErCardRecordDTO> confirmTheImport(ErCardRecordDTO dto) {
		ErCardRecordPO po = ErCardRecordConverter.toPO(dto);
		List<ErCardRecordPO> erCardRecordList = erCardRecordReadManage.findErCardRecordAll(po);
		List<ECardPO> eCardPOs = new ArrayList<ECardPO>();
		for (ErCardRecordPO erCardRecordPO : erCardRecordList) {
			ECardPO tar = new ECardPO();
			tar.setBatch(erCardRecordPO.getBatch());
			tar.setSkuId(erCardRecordPO.getSkuId());
			tar.setSkuName(erCardRecordPO.getSkuName());
			tar.setSkuSerialNumber(erCardRecordPO.getSkuSerialNumber());
			tar.setType(erCardRecordPO.getType());
			tar.setCardNumber(erCardRecordPO.getCardNumber());
			tar.setCardThick(erCardRecordPO.getCardThick());
			tar.setUuid(erCardRecordPO.getUuid());
			tar.setStartTime(erCardRecordPO.getStartTime());
			tar.setEndTime(erCardRecordPO.getEndTime());
			tar.setSource(erCardRecordPO.getSource());
			tar.setFaceValue(erCardRecordPO.getFaceValue());
			tar.setRemark(erCardRecordPO.getRemark());
			tar.setCreateUserid(erCardRecordPO.getCreateUserid());
			tar.setCreateUsername(erCardRecordPO.getCreateUsername());
			tar.setCreateUserip(erCardRecordPO.getCreateUserip());
			tar.setCreateUsermac(erCardRecordPO.getCreateUsermac());
			tar.setCreateTime(erCardRecordPO.getCreateTime());
			tar.setUpdateUserid(erCardRecordPO.getUpdateUserid());
			tar.setUpdateUsername(erCardRecordPO.getUpdateUsername());
			tar.setUpdateUserip(erCardRecordPO.getUpdateUserip());
			tar.setUpdateUsermac(erCardRecordPO.getUpdateUsermac());
			tar.setClientVersionno(erCardRecordPO.getClientVersionno());
			tar.setIsValid(erCardRecordPO.getIsValid());
			tar.setOrderCode(erCardRecordPO.getOrderCode());
			tar.setUserId(erCardRecordPO.getUserId());
			tar.setUserLoginName(erCardRecordPO.getUserLoginName());
			tar.setIsAllocation(erCardRecordPO.getIsAllocation());
			tar.setAllocationTime(erCardRecordPO.getAllocationTime());
			tar.setUpdateTime(erCardRecordPO.getUpdateTime());
			tar.setPlatformId(erCardRecordPO.getPlatformId());
			tar.setStandardProductUnitId(erCardRecordPO.getStandardProductUnitId());
			eCardPOs.add(tar);
		}
		// 批量保存卡密信息
		eCardWriteManage.insertECardAllWithTx(eCardPOs);
		return ErCardRecordConverter.toDTO(erCardRecordList);
	}
}
	