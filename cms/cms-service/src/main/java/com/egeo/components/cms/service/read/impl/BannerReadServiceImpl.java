package com.egeo.components.cms.service.read.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.BannerReadService;
import com.egeo.components.cms.manage.read.BannerReadManage;
import com.egeo.components.cms.converter.BannerConverter;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.BannerPickDTO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.BannerPickPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("bannerReadService")
public class BannerReadServiceImpl  implements BannerReadService {
	@Autowired
	private BannerReadManage bannerReadManage;

	@Override
	public BannerDTO findBannerById(Long id) {
		BannerPO po = new BannerPO();
		po.setId(id);
		BannerPO list = bannerReadManage.findBannerById(po);		
		return BannerConverter.toDTO(list);
	}

	@Override
	public PageResult<BannerDTO> findBannerOfPage(BannerDTO dto, Pagination page) {
		BannerPO po = BannerConverter.toPO(dto);
        PageResult<BannerPO> pageResult = bannerReadManage.findBannerOfPage(po, page);
        
        List<BannerDTO> list = BannerConverter.toDTO(pageResult.getList());
        PageResult<BannerDTO> result = new PageResult<BannerDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<BannerDTO> findBannerAll(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		List<BannerPO> list = bannerReadManage.findBannerAll(po);		
		return BannerConverter.toDTO(list);
	}

	@Override
	public PageResult<Map<String, Object>> findPickBannerOfPage(BannerPickDTO dto, Pagination page) {
		BannerPickPO po = BannerConverter.toPO(dto);
		PageResult<Map<String, Object>> pageResult = bannerReadManage.findPickBannerOfPage(po,page);
		return pageResult;
	}

	@Override
	public List<BannerDTO> queryBannerListByInstIdAndCompanyId(Long instId, Long companyId,Long companyAllId) {
	return BannerConverter.toDTO(bannerReadManage.queryBannerListByInstIdAndCompanyId(instId,companyId,companyAllId));
	}

	@Override
	public PageResult<BannerDTO> queryBannerPage(String name, Integer linkType, List<Long> companyIdList,List<Long> bannerIdList,
			Integer belongPage,Pagination page, Integer enabled, Integer belongPageType,Long platformId,Integer companyType) {
		PageResult<BannerPO> poPage=bannerReadManage.queryBannerPage(
				name,linkType,companyIdList,bannerIdList,belongPage,page,enabled,belongPageType,platformId,companyType);
		PageResult<BannerDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(BannerConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public List<BannerDTO> queryBannerListByBelongPageAndCompanyId(Integer belongPage, Long companyId, Long platformId, Long companyAllId) {
		return BannerConverter.toDTO(bannerReadManage.queryBannerListByBelongPageAndCompanyId(belongPage,companyId,platformId,companyAllId));
	}

	@Override
	public BannerDTO findBannerByIdAndCompanyId(Long bannerId, Long companyId, Long companyAllId) {
		return BannerConverter.toDTO(bannerReadManage.findBannerByIdAndCompanyId(bannerId, companyId, companyAllId));
	}
}
	