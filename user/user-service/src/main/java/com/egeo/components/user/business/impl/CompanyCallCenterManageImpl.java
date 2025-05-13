package com.egeo.components.user.business.impl;
	
import static com.egeo.web.JsonResult.success;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.CompanyCallCenterManage;
import com.egeo.components.user.dto.CompanyCallCenterDTO;
import com.egeo.components.user.service.read.CompanyCallCenterReadService;
import com.egeo.components.user.service.write.CompanyCallCenterWriteService;
import com.egeo.components.user.vo.CompanyCallCenterVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

@Service("companyCallCenter")
public class CompanyCallCenterManageImpl implements CompanyCallCenterManage{


	@Resource
	private CompanyCallCenterReadService companyCallCenterReadService;
	
	@Resource
	private CompanyCallCenterWriteService companyCallCenterWriteService;

	@Override
	public PageResult<CompanyCallCenterDTO> findCompanyCallCenterOfPage(CompanyCallCenterDTO dto, Pagination page) {
		return companyCallCenterReadService.findCompanyCallCenterOfPage(dto, page);
	}

	@Override
	public List<CompanyCallCenterDTO> findCompanyCallCenterAll(CompanyCallCenterDTO dto) {
		return companyCallCenterReadService.findCompanyCallCenterAll(dto);
	}

	@Override
	public Long insertCompanyCallCenterWithTx(CompanyCallCenterDTO dto) {
		return companyCallCenterWriteService.insertCompanyCallCenterWithTx(dto);
	}

	@Override
	public int updateCompanyCallCenterWithTx(CompanyCallCenterDTO dto) {
		return companyCallCenterWriteService.updateCompanyCallCenterWithTx(dto);
	}

	@Override
	public int deleteCompanyCallCenterWithTx(CompanyCallCenterDTO dto) {
		return companyCallCenterWriteService.deleteCompanyCallCenterWithTx(dto);
	}

	@Override
	public JsonResult<Map<String, Object>> defaultInfo(Long companyId) {
		
		CompanyCallCenterDTO dto= companyCallCenterReadService.findCompanyCallCenterById(1l);
		CompanyCallCenterVO vo=new CompanyCallCenterVO();
		vo.setCallCenterPhone(dto.getCallCenterPhone());
		vo.setCompanyQq(dto.getCompanyQq());
		vo.setCompanyWeixin(dto.getCompanyWeixin());
		return JsonResult.success("callCenter", vo);
	}


}
	