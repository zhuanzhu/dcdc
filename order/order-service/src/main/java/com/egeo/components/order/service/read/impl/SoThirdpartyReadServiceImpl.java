package com.egeo.components.order.service.read.impl;

import java.util.List;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoThirdpartyReadService;
import com.egeo.components.order.manage.read.SoThirdpartyReadManage;
import com.egeo.components.order.converter.SoThirdpartyConverter;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.po.SoThirdpartyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.util.CollectionUtils;

@Service("soThirdpartyReadService")
public class SoThirdpartyReadServiceImpl  implements SoThirdpartyReadService {
	@Autowired
	private SoThirdpartyReadManage soThirdpartyReadManage;

	@Override
	public SoThirdpartyDTO findSoThirdpartyById(SoThirdpartyDTO dto) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(dto);
		SoThirdpartyPO list = soThirdpartyReadManage.findSoThirdpartyById(po);
		return SoThirdpartyConverter.toDTO(list);
	}

	@Override
	public PageResult<SoThirdpartyDTO> findSoThirdpartyOfPage(SoThirdpartyDTO dto, Pagination page) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(dto);
        PageResult<SoThirdpartyPO> pageResult = soThirdpartyReadManage.findSoThirdpartyOfPage(po, page);

        List<SoThirdpartyDTO> list = SoThirdpartyConverter.toDTO(pageResult.getList());
        PageResult<SoThirdpartyDTO> result = new PageResult<SoThirdpartyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoThirdpartyDTO> findSoThirdpartyAll(SoThirdpartyDTO dto) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(dto);
		List<SoThirdpartyPO> list = soThirdpartyReadManage.findSoThirdpartyAll(po);
		return SoThirdpartyConverter.toDTO(list);
	}

	@Override
	public List<Long> getThirdpartyIdListByStatus() {
		return soThirdpartyReadManage.getThirdpartyIdListByStatus();
	}

	@Override
	public Long findSoChildIdByThirdpartyId(Long jdOrderId) {
		return soThirdpartyReadManage.findSoChildIdByThirdpartyId(jdOrderId);
	}

	@Override
	public SoThirdpartyDTO findSoThirdpartyByChildId(Long childId){
		if(childId == null){
			return null;
		}
		SoThirdpartyPO queryPO = new SoThirdpartyPO();
		queryPO.setSoChildId(childId);
		List<SoThirdpartyPO> list = soThirdpartyReadManage.findSoThirdpartyAll(queryPO);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return SoThirdpartyConverter.toDTO(list.get(0));
	}

	@Override
	public SoThirdpartyDTO findSoThirdpartyByChildCode(String childCode){
		if(EmptyUtil.isEmpty(childCode)){
			return null;
		}
		SoThirdpartyPO queryPO = new SoThirdpartyPO();
		queryPO.setSoChildCode(childCode);
		List<SoThirdpartyPO> list = soThirdpartyReadManage.findSoThirdpartyAll(queryPO);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return SoThirdpartyConverter.toDTO(list.get(0));
	}

	@Override
	public SoThirdpartyDTO findSoThirdpartyByChild(Long childId,String childCode){
		if(EmptyUtil.isEmpty(childId) && EmptyUtil.isEmpty(childCode)){
			return null;
		}
		SoThirdpartyDTO rt = findSoThirdpartyByChildId(childId);
		if(rt !=null){
			return rt;
		}
		return findSoThirdpartyByChildCode(childCode);
	}
}
