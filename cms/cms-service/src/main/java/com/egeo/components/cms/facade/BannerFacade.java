package com.egeo.components.cms.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.dto.BannerCompanyDTO;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.BannerInstDTO;
import com.egeo.components.cms.dto.BannerPickDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.cms.service.read.BannerCompanyReadService;
import com.egeo.components.cms.service.read.BannerInstReadService;
import com.egeo.components.cms.service.read.BannerReadService;
import com.egeo.components.cms.service.read.LinkableParamReadService;
import com.egeo.components.cms.service.write.BannerWriteService;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.fo.FindCompanyOfPageFO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class BannerFacade {
	
	@Resource
	private BannerReadService bannerReadService;
	
	@Resource
	private BannerWriteService bannerWriteService;
	
	@Resource
	private BannerInstReadService bannerInstReadService;
	
	@Resource
	private BannerCompanyReadService bannerCompanyReadService;
	
	@Autowired
	private CompanyClient companyReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;
	
	@Resource
	private LinkableParamReadService linkableParamReadService;
	

	/**
	 * 根据实例id查询轮播图列表
	 * @param instId
	 * @return
	 */
	public List<BannerInstDTO> queryBannerListByInstId(Long instId) {
		BannerInstDTO dto=new BannerInstDTO();
		dto.setInstId(instId);
		return bannerInstReadService.findBannerInstAll(dto);
	}

	/**
	 * 根据查询轮播图
	 * @param bannerId
	 * @return
	 */
	public BannerDTO queryBannerById(Long bannerId) {
		return bannerReadService.findBannerById(bannerId);
	}

	/**
	 * 选择轮播图时查询轮播图信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> queryBannerPickList(BannerPickDTO dto, Pagination page) {
		return bannerReadService.findPickBannerOfPage(dto,page);
	}

	/**
	 * 根据实例id和公司id查询轮播图列表
	 * @param instId
	 * @param companyId
	 * @return
	 */
	public List<BannerDTO> queryBannerListByInstIdAndCompanyId(Long instId, Long companyId) {
		// 根据公司id查询公司类型对应的所有公司id
		Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
		return bannerReadService.queryBannerListByInstIdAndCompanyId(instId,companyId,companyAllId);
	}

	/**
	 * 根据bannnerid查询轮播图与公司关系列表
	 * @param bannerId
	 * @return
	 */
	public List<BannerCompanyDTO> queryBannerCompanyListByBannerId(Long bannerId) {
		BannerCompanyDTO cond=new BannerCompanyDTO();
		cond.setBannerId(bannerId);
		return bannerCompanyReadService.findBannerCompanyAll(cond);
	}

	/**
	 * 新增编辑轮播图
	 * @param bannerId
	 * @param banner
	 * @param lb
	 * @param companyIdList 
	 * @return
	 */
	public boolean saveBanner(Long bannerId, BannerDTO banner, LinkableButtonDTO lb, List<Long> companyIdList,List<LinkableButtonPageDTO> listLbp,String extParam) {
		return bannerWriteService.saveBanner(bannerId,banner,lb,companyIdList,listLbp,extParam);
	}

	/**
	 * 查询轮播图分页列表
	 * @param name
	 * @param linkType
	 * @param companyIdList
	 * @param page
	 * @return
	 */
	public PageResult<BannerDTO> queryBannerPage(String name, Integer linkType, List<Long> companyIdList,List<Long> bannerIdList,
			Integer belongPage,Pagination page, Integer enabled, Integer belongPageType,Long platformId,Integer companyType) {
		return bannerReadService.queryBannerPage(name,linkType,companyIdList,bannerIdList,belongPage,page,enabled, belongPageType,platformId,companyType);
	}
	/**
	 * 查询我的体检轮播图列表(暂不做平台区分)
	 * @param belongPage 所属页面
	 * @param companyId 公司id
	 * @param platformId 平台id
	 * @return
	 */
	public List<BannerDTO> queryBannerListByBelongPageAndCompanyId(Integer belongPage, Long companyId, Long platformId) {
		// 根据公司id查询公司类型对应的所有公司id
		Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
		return bannerReadService.queryBannerListByBelongPageAndCompanyId(belongPage,companyId,platformId,companyAllId);
	}

	public Integer updateBannerById(BannerDTO dto) {
		
		return bannerWriteService.updateBannerWithTx(dto);
	}

	public List<CompanyDTO> queryCompanyListByBannerId(BannerDTO dto) {
		BannerCompanyDTO bannerCompanyDTO = new BannerCompanyDTO();
		bannerCompanyDTO.setBannerId(dto.getId());
		List<BannerCompanyDTO> bannerCompanyDTOList = bannerCompanyReadService.findBannerCompanyAll(bannerCompanyDTO);	
		
		List<Long> companyIdList = new ArrayList<>();
		for (BannerCompanyDTO navigationBarCompanyDTO_ : bannerCompanyDTOList) {
			
			companyIdList.add(navigationBarCompanyDTO_.getCompanyId());
		}
		FindCompanyOfPageFO fo = new FindCompanyOfPageFO();
		fo.setCompanyIdList(companyIdList);
		PageResult<CompanyDTO> rt = companyReadService.findCompanyOfPage(fo);
		
		return rt.getList();
		
	}

	public List<Map<String, Object>> findLinkParamByLinkableId(Long linkableId) {
		List<Map<String,Object>> mapList = new ArrayList<>();
		LinkableParamDTO dto = new LinkableParamDTO();
		dto.setLinkButtonId(linkableId);
		List<LinkableParamDTO> list = linkableParamReadService.findLinkableParamAll(dto);
		if(list != null && list.size() > 0) {
			for (LinkableParamDTO linkableParamDTO : list) {
				Map<String, Object> map = new HashMap<>();
				map.put("name", linkableParamDTO.getName());
				map.put("value", linkableParamDTO.getValue());
				mapList.add(map);
			}
		}
		
		return mapList;
	}

}
	