package com.egeo.components.cms.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.components.cms.service.read.SuListReadService;
import com.egeo.components.cms.service.write.SuListWriteService;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.dto.StandardUnitByTypeDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class SuListFacade {
	
	@Autowired
	private SuListReadService suListReadService;
	
	@Autowired
	private SuListWriteService suListWriteService;

	@Autowired
	private StandardUnitClient suReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;

	/**
	 * 根据实例id查询商品列表
	 * @param instId
	 * @return
	 */
	public SuListDTO querySuListByInstId(Long instId) {
		return suListReadService.querySuListByInstId(instId);
	}

	/**
	 * 根据sucid查询suList所关联的商品列表
	 * @param sucId
	 * @return
	 */
	public PageResult<StandardUnitDTO> querySusBySucId(Long sucId,Long platformId,Long companyId,Long clientId,Integer maxShow, Long storeId) {
		// 公司类型 0:正式公司 1:测试公司 2:竞品公司
        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		PageResult<StandardUnitDTO> pageResult = suReadService.standardUnitByType(new StandardUnitByTypeDTO(null, null, sucId, 2, platformId, null, clientId, companyId,companyType, storeId, new Pagination(1, maxShow), null));
		List<StandardUnitDTO> list = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(pageResult.getList())){
			list=pageResult.getList();
		}
		for (StandardUnitDTO standardUnitDTO : list) {
			standardUnitDTO.setSalePrice(settingSalePrice(standardUnitDTO, companyType));
		}
		pageResult.setList(list);
		return pageResult;
	}
	
	/**
	 * 设置su商品销售价格
	 * @param standardUnitDTO2
	 * @param companyType 公司类型 0:正式公司 1:测试公司 2:竞品公司
	 */
	private BigDecimal settingSalePrice(StandardUnitDTO standardUnitDTO2, Integer companyType) {
		switch (companyType) {
		case  0:
			return standardUnitDTO2.getSalePrice();
		case  1:
			return standardUnitDTO2.getDemoSalePrice();
		case  2:
			return standardUnitDTO2.getCompetingSalePrice();

		default:
			throw new BusinessException("未定义公司类型");
		}
	}

}
	