package com.egeo.components.cms.facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.NavigationBarCompanyDTO;
import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.components.cms.service.read.LinkableButtonReadService;
import com.egeo.components.cms.service.read.NavigationBarCompanyReadService;
import com.egeo.components.cms.service.read.NavigationBarReadService;
import com.egeo.components.cms.service.read.NavigationLabelReadService;
import com.egeo.components.cms.service.write.NavigationBarWriteService;
import com.egeo.components.cms.service.write.NavigationLabelWriteService;
import com.egeo.components.cms.vo.NavigationBarWebVO;
import com.egeo.components.cms.vo.NavigationLabelWebVO;
import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitCompanyClient;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.fo.FindCompanyOfPageFO;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Component
public class NavigationBarFacade {

	@Resource
	private NavigationBarReadService navigationBarReadService;

	@Resource
	private NavigationBarWriteService navigationBarWriteService;

	@Resource
	private NavigationLabelReadService navigationLabelReadService;

	@Resource
	private NavigationLabelWriteService navigationLabelWriteService;

	@Resource
	private NavigationBarCompanyReadService navigationBarCompanyReadService;

	@Autowired
	private CompanyClient companyReadService;

	@Autowired
	private UserClient userReadService;
	
	@Autowired
	private StandardProductUnitClient spuReadService;

	@Resource
	private LinkableButtonReadService linkableButtonReadService;
	
	@Autowired
	private StandardUnitCompanyClient suCompanyReadService;
	
	@Autowired
	private CompanyCoreClient companyCoreReadService;

	public NavigationBarDTO findNavigationBarById(NavigationBarDTO dto) {

		return navigationBarReadService.findNavigationBarById(dto);
	}

	public PageResult<NavigationBarDTO> findNavigationBarOfPage(NavigationBarDTO dto, Pagination page) {

		return navigationBarReadService.findNavigationBarOfPage(dto, page);

	}

	public List<NavigationBarDTO> findNavigationBarAll(NavigationBarDTO dto) {

		return navigationBarReadService.findNavigationBarAll(dto);

	}

	public Long insertNavigationBarWithTx(NavigationBarDTO dto) {

		return navigationBarWriteService.insertNavigationBarWithTx(dto);
	}

	public int updateNavigationBarWithTx(NavigationBarDTO dto) {

		return navigationBarWriteService.updateNavigationBarWithTx(dto);
	}

	public int deleteNavigationBarWithTx(NavigationBarDTO dto) {

		return navigationBarWriteService.deleteNavigationBarWithTx(dto);

	}

	/**
	 * 新增或编辑导航栏标签
	 * 
	 * @param dto
	 * @param linkableButtonDTO
	 * @return
	 */
	public Long insertOrUpdateNavigationLableWithTx(NavigationLabelDTO dto, LinkableButtonDTO linkableButtonDTO) {

		return navigationLabelWriteService.insertOrUpdateNavigationLableWithTx(dto, linkableButtonDTO);
	}

	/**
	 * 查询导航栏标签详情通过id
	 * 
	 * @param dto
	 * @return
	 */
	public NavigationLabelDTO findNavigationLabelById(NavigationLabelDTO dto) {

		return navigationLabelReadService.findNavigationLabelById(dto);
	}

	/**
	 * 删除导航栏标签详情通过id
	 * 
	 * @param dto
	 * @return
	 */
	public int deleteNavigationLableWithTx(NavigationLabelDTO dto) {

		return navigationLabelWriteService.deleteNavigationLabelWithTx(dto);
	}

	/**
	 * 查询所有导航栏标签
	 * 
	 * @param navigationLabelDTO
	 * @return
	 */
	public List<NavigationLabelDTO> findNavigationLabelAll(NavigationLabelDTO dto) {

		return navigationLabelReadService.findNavigationLabelAll(dto);
	}

	/**
	 * 查询所有导航栏公司关系列表
	 * 
	 * @param navigationBarCompanyDTO
	 * @return
	 */
	public List<NavigationBarCompanyDTO> findNavigationBarCompanyAll(NavigationBarCompanyDTO dto) {

		return navigationBarCompanyReadService.findNavigationBarCompanyAll(dto);
	}

	/**
	 * 通过id查询公司
	 * 
	 * @param id
	 * @return
	 */
	public CompanyDTO findCompanyById(Long id) {

		return companyReadService.findCompanyById(id);
	}

	/**
	 * 模糊查询导航栏列表
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<NavigationBarDTO> findNavigationBarOfPageByBlurry(NavigationBarDTO dto, Pagination page) {

		return navigationBarReadService.findNavigationBarOfPageByBlurry(dto, page);
	}

	/**
	 * 通过id查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	public UserDTO findUserById(Long id) {

		return userReadService.findUserByID(id);
	}

	/**
	 * 通过导航栏id查询公司信息
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	public List<CompanyDTO> findNavigationBarCompanyOfPage(Long id, Pagination page) {
		NavigationBarCompanyDTO navigationBarCompanyDTO = new NavigationBarCompanyDTO();
		navigationBarCompanyDTO.setNavigationBarId(id);
		List<NavigationBarCompanyDTO> navigationBarCompanyList = navigationBarCompanyReadService
				.findNavigationBarCompanyAll(navigationBarCompanyDTO);

		List<Long> companyIdList = new ArrayList<>();
		CompanyDTO dto = new CompanyDTO();
		for (NavigationBarCompanyDTO navigationBarCompanyDTO_ : navigationBarCompanyList) {

			companyIdList.add(navigationBarCompanyDTO_.getCompanyId());
		}
		FindCompanyOfPageFO fo = new FindCompanyOfPageFO();
		fo.setDto(dto);
		fo.setCompanyIdList(companyIdList);
		return companyReadService.findCompanyOfPage(fo).getList();
	}

	/**
	 * List去重
	 * 
	 * @param list
	 * @return
	 */
	public List removeDuplicate(List list) {
		if (list == null)
			return null;
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		return list;
	}

	/**
	 * 通过公司id查询首页导航栏和底部信息栏的信息
	 * 
	 * @param companyId
	 * @return
	 */
	public List<NavigationBarWebVO> findPageTabAllByCompanyId(Integer navigationBarType, Long companyId, Long platformId, Long clientId) {
		List<Long> companyIdList = new ArrayList<>();
		companyIdList.add(companyId);
		
		// 根据公司id查询公司类型对应的所有公司id
		Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
		companyIdList.add(companyAllId);
		
		// 查询所在公司的所有导航栏
		List<NavigationBarCompanyDTO> navigationBarCompanyDTOList = navigationBarCompanyReadService
				.findPageTabAllByCompanyId(companyIdList);

		List<NavigationBarWebVO> nblist = new ArrayList<>();
		for (NavigationBarCompanyDTO navigationBarCompanyDTO : navigationBarCompanyDTOList) {
			// 查询所在公司的导航栏信息
			NavigationBarDTO navigationBarDTO = new NavigationBarDTO();
			navigationBarDTO.setId(navigationBarCompanyDTO.getNavigationBarId());
			NavigationBarDTO navigationBarDTO_ = navigationBarReadService.findNavigationBarById(navigationBarDTO);
			
			if (navigationBarDTO_ == null || navigationBarDTO_.getNavigationBarType() != navigationBarType) {
				continue;
			}
			
			if ((navigationBarType == 0 && nblist.size() >= 4)
					|| (navigationBarType == 1 && nblist.size() >= 5)) {
				// 顶部导航栏最多展示4个和底部信息栏最多展示5个
				break;
			}
			
			// 查询导航栏下的导航栏标签信息
			NavigationLabelDTO navigationLabelDTO = new NavigationLabelDTO();
			navigationLabelDTO.setNavigationBarId(navigationBarDTO_.getId());
			List<NavigationLabelDTO> NavigationLabelList = navigationLabelReadService
					.findNavigationLabelAll(navigationLabelDTO);
			
			List<NavigationLabelWebVO> nlList = new ArrayList<>();
			for (NavigationLabelDTO navigationLabelDTO_ : NavigationLabelList) {
				// 查询导航栏标签下的跳转链接信息
				LinkableButtonDTO linkableButton = linkableButtonReadService
						.findLinkableButtonById(navigationLabelDTO_.getLinkableButtonId());
				NavigationLabelWebVO nlVO = new NavigationLabelWebVO();
				nlVO.setId(navigationLabelDTO_.getId());
				nlVO.setNavigationLabelName(navigationLabelDTO_.getNavigationLabelName());
				nlVO.setLinkType(linkableButton.getLinkType());
				nlVO.setLinkUrl(linkableButton.getLinkUrl());
				nlVO.setLinkParam(linkableButton.getLinkParam());
				nlVO.setLinkId(linkableButton.getLinkId());
				
				if (linkableButton.getLinkType() != null && linkableButton.getLinkType() == 5) {
					// 单个商品查询spu模板
					Long suTmplId = querySuTmplIdBySuId(linkableButton.getLinkId());
					nlVO.setSuTmplId(suTmplId);
					
					// 查询单个商品与当前公司、当前客户端是否有关系
					boolean companyAvailable = querySuCompanyAvailable(linkableButton.getLinkId(), companyId, clientId);
					nlVO.setSuCompanyAvailable(companyAvailable);
				}
				
				nlList.add(nlVO);
			}
			
			NavigationBarWebVO nbVO = new NavigationBarWebVO();
			nbVO.setId(navigationBarDTO_.getId());
			nbVO.setNavigationBarType(navigationBarDTO_.getNavigationBarType());
			nbVO.setNavigationBarName(navigationBarDTO_.getNavigationBarName());
			nbVO.setNavigationLabelList(nlList);
			
			nblist.add(nbVO);
		}

		return nblist;
	}
	
	private Long querySuTmplIdBySuId(Long suId) {
		StandardProductUnitDTO spu=spuReadService.querySpuBySuId(suId);
		return spu==null?null:spu.getCommodityTemplateId();
	}
	
	private boolean querySuCompanyAvailable(Long suId, Long companyId,Long clientId) {
		// 根据公司id查询公司类型对应的所有公司id
        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		return suCompanyReadService.querySuCompanyAvailable(suId,companyId,clientId,companyType);
	}
}
