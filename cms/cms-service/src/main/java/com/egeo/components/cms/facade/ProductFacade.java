package com.egeo.components.cms.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitCompanyClient;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.config.RuntimeContext;

@Component
public class ProductFacade {

	@Autowired
	private StandardProductUnitClient spuReadService;

	@Autowired
	private StandardUnitCompanyClient suCompanyReadService;
	@Autowired
	private StandardUnitClient standardUnitReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;

	/**
	 * 根据suId查询商品模板id
	 * @param linkId
	 * @return
	 */
	public Long querySuTmplIdBySuId(Long suId) {
		StandardProductUnitDTO spu=spuReadService.querySpuBySuId(suId);
		return spu==null?null:spu.getCommodityTemplateId();
	}

	/**
	 * 查询su是否对公司、客户端可见
	 * @param suId
	 * @param companyId
	 * @return
	 */
	public boolean querySuCompanyAvailable(Long suId, Long companyId,Long clientId) {
		// 根据公司id查询公司类型对应的所有公司id
        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		return suCompanyReadService.querySuCompanyAvailable(suId,companyId,clientId,companyType);
	}
	/**
	 * 根据suid查询su名称
	 * @param suId
	 * @return
	 */
	public String suNameBySuId(Long suId){
		StandardUnitDTO dto = new StandardUnitDTO();
		dto.setId(suId);
		StandardUnitDTO standardUnitDTO = standardUnitReadService.findStandardUnitById(dto);
		if(standardUnitDTO != null)
			return standardUnitDTO.getName();
		return null;
	}
	
	
}
