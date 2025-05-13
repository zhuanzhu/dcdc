package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.InsuranceLoginReadService;
import com.egeo.components.user.dto.InsuranceLoginDTO;
import com.egeo.components.user.po.InsuranceLoginPO;
import com.egeo.components.user.dao.read.InsuranceLoginReadDAO;
import com.egeo.components.user.converter.InsuranceLoginConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("insuranceLoginReadService")
public class InsuranceLoginReadServiceImpl implements InsuranceLoginReadService {
	@Autowired
	private InsuranceLoginReadDAO insuranceLoginReadDAO ;

	@Override
	public InsuranceLoginDTO findInsuranceLoginById(InsuranceLoginDTO dto){
		InsuranceLoginPO po = InsuranceLoginConverter.toPO(dto);
		InsuranceLoginPO insuranceLoginpo = new InsuranceLoginPO();
		insuranceLoginpo.setId(po.getId());
		InsuranceLoginPO list = insuranceLoginReadDAO.findById(insuranceLoginpo);
		return InsuranceLoginConverter.toDTO(list);
	}
	@Override
	public PageResult<InsuranceLoginDTO> findInsuranceLoginOfPage(InsuranceLoginDTO dto, Pagination page) {
		InsuranceLoginPO po = InsuranceLoginConverter.toPO(dto);
		PageResult<InsuranceLoginPO> pageResult = new PageResult<InsuranceLoginPO>();
		List<InsuranceLoginPO> listT = null;
		int cnt = insuranceLoginReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = insuranceLoginReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<InsuranceLoginPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<InsuranceLoginDTO> list = InsuranceLoginConverter.toDTO(pageResult.getList());
        PageResult<InsuranceLoginDTO> result = new PageResult<InsuranceLoginDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InsuranceLoginDTO> findInsuranceLoginAll(InsuranceLoginDTO dto) {
		InsuranceLoginPO po = InsuranceLoginConverter.toPO(dto);
		List<InsuranceLoginPO> list = insuranceLoginReadDAO.findAll(po,null);
		return InsuranceLoginConverter.toDTO(list);
	}

	@Override
	public InsuranceLoginDTO queryInsuranceLoginByUserId(Long userId) {
		
		return InsuranceLoginConverter.toDTO(insuranceLoginReadDAO.queryInsuranceLoginByUserId(userId));
	}
}
	