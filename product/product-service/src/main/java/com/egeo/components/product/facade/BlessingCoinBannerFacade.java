package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.components.product.service.read.BlessingCoinBannerCompanyReadService;
import com.egeo.components.product.service.read.BlessingCoinBannerReadService;
import com.egeo.components.product.service.write.BlessingCoinBannerWriteService;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class BlessingCoinBannerFacade {
	
	@Resource
	private BlessingCoinBannerReadService blessingCoinBannerReadService;
	
	@Resource
	private BlessingCoinBannerWriteService blessingCoinBannerWriteService;
	
	@Resource
	private BlessingCoinBannerCompanyReadService blessingCoinBannerCompanyReadService;
	
	@Autowired
	private CompanyClient companyReadService;
	
	
	public Map<String, Object> findBlessingCoinBannerById(BlessingCoinBannerDTO dto){
		BlessingCoinBannerCompanyDTO blessingCoinBannerCompanyDTO = new BlessingCoinBannerCompanyDTO();
		blessingCoinBannerCompanyDTO.setBlessingCoinBannerId(dto.getId());
		List<BlessingCoinBannerCompanyDTO> blessingCoinBannerCompanyList = blessingCoinBannerCompanyReadService.findBlessingCoinBannerCompanyAll(blessingCoinBannerCompanyDTO);
		//拼接公司集合
		List<Long> companyIds = new ArrayList<>();
		for (BlessingCoinBannerCompanyDTO blessingCoinBannerCompanyDTO2 : blessingCoinBannerCompanyList) {
			companyIds.add(blessingCoinBannerCompanyDTO2.getCompanyId());
		}
		BlessingCoinBannerDTO blessingCoinBannerDTO = blessingCoinBannerReadService.findBlessingCoinBannerById(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("id", blessingCoinBannerDTO.getId());
		map.put("name", blessingCoinBannerDTO.getName());
		map.put("type", blessingCoinBannerDTO.getType());
		map.put("sortValue", blessingCoinBannerDTO.getSortValue());
		map.put("location", blessingCoinBannerDTO.getLocation());
		map.put("isShow", blessingCoinBannerDTO.getIsShow());
		map.put("pictureUrl", blessingCoinBannerDTO.getPictureUrl());
		map.put("path", blessingCoinBannerDTO.getPath());
		map.put("standardUnitId", blessingCoinBannerDTO.getStandardUnitId());
		map.put("companyList", companyIds);
		return map;
	}

	public PageResult<BlessingCoinBannerDTO> findBlessingCoinBannerOfPage(BlessingCoinBannerDTO dto,Pagination page){
		
		return blessingCoinBannerReadService.findBlessingCoinBannerOfPage(dto, page);
		
	}

	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAll(BlessingCoinBannerDTO dto){
		
		return blessingCoinBannerReadService.findBlessingCoinBannerAll(dto);
		
	}

	public Long insertBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds){
		
		return blessingCoinBannerWriteService.insertBlessingCoinBannerWithTx(dto,companyIds);
	}

	public int updateBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto,List<Long> companyIds){
		
		return blessingCoinBannerWriteService.updateBlessingCoinBannerWithTx(dto,companyIds);
	}

	public int deleteBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto){
		
		return blessingCoinBannerWriteService.deleteBlessingCoinBannerWithTx(dto);
		
	}
	/**
	 * 根据轮播图id查询公司信息
	 * @param id
	 * @return
	 */
	public String findCompanysByBlessingCoinBannerId(Long blessingCoinBannerId) {
		BlessingCoinBannerCompanyDTO blessingCoinBannerCompanyDTO = new BlessingCoinBannerCompanyDTO();
		blessingCoinBannerCompanyDTO.setBlessingCoinBannerId(blessingCoinBannerId);
		List<BlessingCoinBannerCompanyDTO> blessingCoinBannerCompanyList = blessingCoinBannerCompanyReadService.findBlessingCoinBannerCompanyAll(blessingCoinBannerCompanyDTO);
		StringBuilder stringBuffer = new StringBuilder();
		//拼接公司集合
		List<Long> companyIds = new ArrayList<>();
		for (BlessingCoinBannerCompanyDTO blessingCoinBannerCompanyDTO2 : blessingCoinBannerCompanyList) {
			companyIds.add(blessingCoinBannerCompanyDTO2.getCompanyId());
		}
		if(EmptyUtil.isNotEmpty(companyIds)){
			//根据公司id集合查询公司名称信息
			List<String> companys = companyReadService.findByCompanys(com.egeo.utils.StringUtils.longsToStrings(companyIds));
			
			for (int i = 0; i < companys.size(); i++) {
				if(i + 1 >= companys.size()){
					stringBuffer.append(companys.get(i));
				}else{
					stringBuffer.append(companys.get(i));
					stringBuffer.append(";");
				}
			}
		}
		return stringBuffer.toString();
	}
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<BlessingCoinBannerDTO> findBlessingCoinBannerAllApp(BlessingCoinBannerDTO dto) {
		return blessingCoinBannerReadService.findBlessingCoinBannerAllApp(dto);
	}

}
	