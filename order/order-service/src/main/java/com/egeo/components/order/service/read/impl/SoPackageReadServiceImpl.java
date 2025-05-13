package com.egeo.components.order.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoPackageReadService;
import com.egeo.components.order.manage.read.SoPackageReadManage;
import com.egeo.components.order.condition.SoPackageCondition;
import com.egeo.components.order.converter.SoPackageConverter;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.po.SoPackagePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soPackageReadService")
public class SoPackageReadServiceImpl  implements SoPackageReadService {
	@Autowired
	private SoPackageReadManage soPackageReadManage;

	@Override
	public SoPackageDTO findSoPackageById(SoPackageDTO dto) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
		SoPackagePO list = soPackageReadManage.findSoPackageById(po);		
		return SoPackageConverter.toDTO(list);
	}

	@Override
	public PageResult<SoPackageDTO> findSoPackageOfPage(SoPackageDTO dto, Pagination page) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
        PageResult<SoPackagePO> pageResult = soPackageReadManage.findSoPackageOfPage(po, page);
        
        List<SoPackageDTO> list = SoPackageConverter.toDTO(pageResult.getList());
        PageResult<SoPackageDTO> result = new PageResult<SoPackageDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoPackageDTO> findSoPackageAll(SoPackageDTO dto) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
		List<SoPackagePO> list = soPackageReadManage.findSoPackageAll(po);
		return SoPackageConverter.toDTO(list);
	}

	@Override
	public List<SoPackageDTO> findUnReceive(SoPackageDTO dto) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
		List<SoPackagePO> list = soPackageReadManage.findUnReceive(po);
		return SoPackageConverter.toDTO(list);
	}
	@Override
	public List<SoPackageDTO> packageByOrderCode(String orderCode) {
		return SoPackageConverter.toDTO(soPackageReadManage.packageByOrderCode(orderCode));
	}

	@Override
	public PageResult<SoPackageDTO> findSoPackageAndBoxOfPage(SoPackageDTO dto, Pagination page) {
		SoPackagePO po = SoPackageConverter.toPO(dto);
        PageResult<SoPackageCondition> pageResult = soPackageReadManage.findSoPackageAndBoxOfPage(po, page);
        
        List<SoPackageDTO> list = SoPackageConverter.conditionToDTO(pageResult.getList());
        PageResult<SoPackageDTO> result = new PageResult<SoPackageDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoPackageDTO> queryPackageBySoChildId(Long id) {
		return SoPackageConverter.toDTOs2(soPackageReadManage.queryPackageBySoChildId(id));
	}

	@Override
	public String findDeliveryCompanyNameBySoChildId(Long soChildId) {
		return soPackageReadManage.findDeliveryCompanyNameBySoChildId(soChildId);
	}
}
	