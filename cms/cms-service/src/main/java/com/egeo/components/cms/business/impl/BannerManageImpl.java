package com.egeo.components.cms.business.impl;

import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.user.client.CompanyCoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.cms.business.BannerManage;
import com.egeo.components.cms.dto.BannerCompanyDTO;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.facade.BannerFacade;
import com.egeo.components.cms.facade.LinkableButtonFacade;
import com.egeo.components.cms.facade.ProductFacade;
import com.egeo.components.cms.facade.UserFacade;
import com.egeo.components.cms.manage.read.BannerCompanyReadManage;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.components.cms.vo.BannerCompanyListVO;
import com.egeo.components.cms.vo.BannerDetailVO;
import com.egeo.components.cms.vo.BannerListVO;
import com.egeo.components.cms.vo.BannerVO;
import com.egeo.components.cms.vo.LinkableButtonPageVO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.config.RuntimeContext;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("banner")
public class BannerManageImpl implements BannerManage{

	private static final Logger logger = LoggerFactory.getLogger(BannerManageImpl.class);
	
	@Resource(name="bannerFacade")
	private BannerFacade bannerFacade;
	
	@Resource(name="userFacade")
	private UserFacade userFacade;
	
	@Resource(name="linkableButtonFacade")
	private LinkableButtonFacade linkableButtonFacade;
	@Autowired
	private CompanyClient companyClient;
	@Autowired
	private CompanyCoreClient companyCoreClient;
	@Resource(name = "productFacade")
	private ProductFacade productFacade;
	@Autowired
	private BannerCompanyReadManage bannerCompanyReadManage;
	
	@Override
	public JsonResult<Map<String, Object>> queryBannerList(String name,String companyName,Integer linkType,
			List<Long> bannerIdList,Integer belongPage,Integer pageNo,Integer pageSize, Long companyId,
			Integer enabled, Integer belongPageType,Long platformId,Integer companyType){
		if(pageNo==null) {
			pageNo=1;
		}
		if(pageSize==null) {
			pageSize=20;
		}
		if (EmptyUtil.isNotEmpty(companyName) && EmptyUtil.isNotEmpty(companyId)) 
			return JsonResult.fail("公司名称和公司id不能同时查询");
		
		Pagination page=new Pagination(pageNo, pageSize);
		// 如果查询条件有公司名称
		List<Long> companyIdList=new ArrayList<>();
		// 选择公司下拉框查询
		if (companyId != null) {
			companyIdList.add(companyId);
			companyIdList.add(companyCoreClient.findEnterpriseCompanyAllId(companyId));
		}
		if(EmptyUtil.isNotBlank(companyName)){
			CompanyDTO companyDTO = new CompanyDTO();
			companyDTO.setCompanyName(companyName);
			List<CompanyDTO> companys = userFacade.queryCompanyListByFuzzyName(companyDTO);
			// 没有查到对应的公司名称的公司
			if(companys.isEmpty()){
				PageResult<BannerListVO> rt = new PageResult<>();
				rt.copy(page);
				rt.setTotalSize(0);
				rt.setList(new ArrayList<BannerListVO>());
				return JsonResult.success("bannerPage",rt);
			}else{
				companyIdList.add(companyCoreClient.findCompanyAllIdByCompanyType(0));	// 轮播图属于全部公司
				for (CompanyDTO company : companys) {
					companyIdList.add(company.getId());
					//  公司类型  0:正式公司 1:测试公司 2:竞品公司
					Long allCompanyId=companyCoreClient.findCompanyAllIdByCompanyType(company.getCompanyType());
					if (!companyIdList.contains(allCompanyId)) {
						companyIdList.add(allCompanyId);
					}
				}
			}
		}
		if(companyIdList.size()==0) {
			if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
				if(RuntimeContext.cacheUser().getType().intValue()==3 || RuntimeContext.cacheUser().getType().intValue()==4) {
					companyId = RuntimeContext.cacheUser().getCompanyId();
					companyIdList.add(companyId);
				}else if(RuntimeContext.cacheUser().getType().intValue()==2) {
					//获取所有的公司信息
					CompanyDTO dto = new CompanyDTO();
					dto.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
					List<CompanyDTO> companys = companyClient.findCompanyAll(dto);
					if(companys!=null) {
						for(CompanyDTO one : companys) {
							companyIdList.add(one.getId());
						}
					}
					companyIdList.add(companyCoreClient.findCompanyAllIdByCompanyType(0));	// 默认公司配置
				}else if(RuntimeContext.cacheUser().getType().intValue()==1) {
					companyIdList = null;
				}
			}else {
				return new JsonResult().fail("403");
			}
		
		}
		PageResult<BannerDTO> dtoPage = bannerFacade.queryBannerPage(name,linkType,companyIdList,bannerIdList,belongPage,page,enabled, belongPageType,platformId,companyType);
		List<BannerDTO> dtoList=dtoPage.getList();
		List<BannerListVO> voList=new ArrayList<>();
		for(BannerDTO dto:dtoList) {
			BannerListVO vo=new BannerListVO();
			vo.setEnabled(dto.getEnabled());
			vo.setId(dto.getId());
			vo.setImgUrl(dto.getImgUrl());
			vo.setName(dto.getName());
			vo.setSort(dto.getSort());
			vo.setBelongPage(dto.getBelongPage());
			vo.setLinkType(6);//默认无效果
			vo.setRemark(dto.getRemark());
			Long lbId=dto.getLinkableId();
			if(lbId!=null) {
				LinkableButtonDTO lb=linkableButtonFacade.queryLinkableButtonById(lbId);
				if(lb!=null) {
					vo.setLinkType(lb.getLinkType());
				}
			}
			voList.add(vo);
		}
		PageResult<BannerListVO> voPage=new PageResult<>();
		voPage.setList(voList);
		voPage.copy(dtoPage);
		
		return JsonResult.success("bannerPage",voPage);
		
	}

	@Override
	public JsonResult<Map<String, Object>> queryCompanyBannerList(String name,String companyName,Integer linkType,
			List<Long> bannerIdList,Integer belongPage,Integer pageNo,Integer pageSize, Long companyId,
			Integer enabled, Integer belongPageType,Long platformId,Integer companyType){
		if(RuntimeContext.cacheUser().getType().intValue()==3 || RuntimeContext.cacheUser().getType().intValue()==4) {
			
		}else {
			if(RuntimeContext.cacheUser().getType().intValue()==2 && companyId!=null) {
				
			}else {
				return JsonResult.fail("接口调用错误");
			}
		}
		if(pageNo==null) {
			pageNo=1;
		}
		if(pageSize==null) {
			pageSize=20;
		}
		List<Long> companyIdList=new ArrayList<>();
		if(companyId==null) {
			companyId = RuntimeContext.cacheUser().getCompanyId();
		}
		companyIdList.add(companyId);
		
		Pagination page=new Pagination(pageNo, pageSize);
		
		PageResult<BannerDTO> dtoPage = bannerFacade.queryBannerPage(name,linkType,companyIdList,bannerIdList,belongPage,page,enabled, belongPageType,platformId,companyType);
		List<BannerDTO> dtoList=dtoPage.getList();
		List<BannerCompanyListVO> voList=new ArrayList<>();
		for(BannerDTO dto:dtoList) {
			BannerCompanyListVO vo=new BannerCompanyListVO();
			vo.setEnabled(dto.getEnabled());
			vo.setId(dto.getId());
			vo.setImgUrl(dto.getImgUrl());
			vo.setName(dto.getName());
			vo.setSort(dto.getSort());
			vo.setBelongPage(dto.getBelongPage());
			vo.setLinkType(6);//默认无效果
			vo.setRemark(dto.getRemark());
			Long lbId=dto.getLinkableId();
			BannerCompanyPO po = new BannerCompanyPO();
			po.setBannerId(dto.getId());
			List<BannerCompanyPO> bannerCompanyPOs = bannerCompanyReadManage.findBannerCompanysAll(po, companyIdList);
			if(bannerCompanyPOs!=null && bannerCompanyPOs.size()==1) {
				vo.setUp(bannerCompanyPOs.get(0).getUp()==null?0:bannerCompanyPOs.get(0).getUp());
				if(lbId!=null) {
					LinkableButtonDTO lb=linkableButtonFacade.queryLinkableButtonById(lbId);
					if(lb!=null) {
						vo.setLinkType(lb.getLinkType());
					}
				}
				
				voList.add(vo);
			}
		}
		PageResult<BannerCompanyListVO> voPage=new PageResult<>();
		voPage.setList(voList);
		voPage.copy(dtoPage);
		
		return JsonResult.success("bannerPage",voPage);
		
	}
	@Override
	public JsonResult<Map<String, Object>> bannerDetail(Long bannerId) {
		if(bannerId==null) {
			return JsonResult.fail("请选择轮播图");
		}
		BannerDTO b=bannerFacade.queryBannerById(bannerId);
		if(b==null) {
			return JsonResult.fail("轮播图不存在");
		}
		List<BannerCompanyDTO> bcList=bannerFacade.queryBannerCompanyListByBannerId(bannerId);
		List<Long> companyIds=new ArrayList<>();
		for(BannerCompanyDTO bc:bcList) {
			companyIds.add(bc.getCompanyId());
		}
		BannerDetailVO vo=new BannerDetailVO();
		vo.setCompanyIds(companyIds);
		vo.setEnabled(b.getEnabled());
		vo.setId(b.getId());
		vo.setImgUrl(b.getImgUrl());
		vo.setSort(b.getSort());
		vo.setLinkType(6);
		Long linkableId=b.getLinkableId();
		if(linkableId!=null) {
			LinkableButtonDTO lb = linkableButtonFacade.queryLinkableButtonById(linkableId);
			if(lb != null) {
				vo.setLinkId(lb.getLinkId());
				vo.setLinkParam(lb.getLinkParam());
				vo.setLinkType(lb.getLinkType());
				vo.setLinkUrl(lb.getLinkUrl());
				
				//设置 su组跳转页面属性
				if(lb.getLinkType() == CmsConstant.CMS_LINK_TYPE_SU_LIST) {
					List<LinkableButtonPageVO> linkableButtonPageList = linkableButtonFacade.queryLinkableButtonPagByLinkableId(linkableId);
					vo.setLinkableButtonPageList(linkableButtonPageList);
				}
				//增加 linkParam
				List<Map<String, Object>> extParam = bannerFacade.findLinkParamByLinkableId(linkableId);
				vo.setExtParam(extParam);
			}
		}
		vo.setName(b.getName());
		vo.setRemark(b.getRemark());
		vo.setBelongPage(b.getBelongPage());
		return JsonResult.success("banner", vo);
	}

	@Override
	public JsonResult<Map<String, Object>> saveBanner(
			Long bannerId, String name, String companyIds, 
			String imgUrl,
			String remark, Integer enabled, Integer sort,Integer companyType,
			Integer linkType, Long linkId, String linkUrl, String linkParam,Integer belongPage,Long platformId,
			String linkableButtonPageList,String extParam,Integer isDefault) {
		//参数校验
		if(EmptyUtil.isBlank(name)) {
			return JsonResult.fail("请填写轮播图名称");
		}
		if (name.length() > 15) {
			return JsonResult.fail("最多输入15个字");
		}
		if(EmptyUtil.isBlank(companyIds) && !Objects.equals(isDefault,1)) {
			return JsonResult.fail("请选择所属公司!");
		}
		List<Long> companyIdList=new ArrayList<>();
		if(EmptyUtil.isNotBlank(companyIds)){
			companyIdList=StringUtils.stringWithBorder2IdList(companyIds,",");
		}
		if(companyIdList.size()==0 && !Objects.equals(isDefault,1)) {
			return JsonResult.fail("请选择所属公司!");
		}
		if (Objects.equals(isDefault,1)){
			companyIdList=new ArrayList<>();
			companyIdList.add(companyCoreClient.findCompanyAllIdByCompanyType(companyType));
		}
		if(EmptyUtil.isBlank(imgUrl)) {
			return JsonResult.fail("请上传图片");
		}
		if(EmptyUtil.isBlank(remark)) {
			remark=null;
		}
		if(enabled==null) {
			enabled=0;
		}
		if (linkType == null) {
			return JsonResult.fail("跳转类型不能为空");
		}
		if((linkType!=6 && linkType!=2) && linkId==null) {
			//链接类型为单个商品时,linkId不可为空
			return JsonResult.fail("链接配置不能为空");
		}
		if (linkType==2 && EmptyUtil.isEmpty(linkUrl)) {
			// H5链接（内部）
			return JsonResult.fail("链接配置不能为空");
		}
		
		BannerDTO banner=new BannerDTO();
		banner.setEnabled(enabled);
		banner.setImgUrl(imgUrl);
		banner.setName(name);
		banner.setRemark(remark);
		banner.setSort(sort);
		banner.setBelongPage(belongPage);
		banner.setCompanyType(companyType);
		banner.setPlatformId(platformId);
		LinkableButtonDTO lb=new LinkableButtonDTO();
		lb.setLinkId(linkId);
		lb.setLinkParam(linkParam);
		lb.setLinkType(linkType);
		lb.setLinkUrl(linkUrl);
		// if(linkType = 4) insert linkableButtonPage
		List<LinkableButtonPageDTO> listLbp = null;
		if(linkType == 4 && org.apache.commons.lang3.StringUtils.isNotBlank(linkableButtonPageList)) {
			listLbp = JSONArray.parseArray(linkableButtonPageList, LinkableButtonPageDTO.class);
		}
		
		bannerFacade.saveBanner(bannerId,banner,lb,companyIdList,listLbp,extParam);
		
		return JsonResult.success();
	}

	@Override
	public JsonResult<Map<String, Object>> queryBannerListByType(Integer belongPage, Long companyId, Long platformId, Long clientId) {
		List<BannerDTO> dtoList = bannerFacade.queryBannerListByBelongPageAndCompanyId(belongPage,companyId, platformId);
		List<BannerVO> bannerList = new ArrayList<>();
		for (BannerDTO dto : dtoList) {
			BannerVO vo = new BannerVO();
			vo.setId(dto.getId());
			vo.setImgUrl(dto.getImgUrl());
			vo.setLinkType(6);// 默认给出无效果
			Long linkableId = dto.getLinkableId();
			if (linkableId != null) {
				LinkableButtonDTO lb = linkableButtonFacade.queryLinkableButtonById(linkableId);
				if (lb != null) {
					Integer linkType = lb.getLinkType();
					vo.setLinkType(linkType);
					Long LinkId = lb.getLinkId();
					vo.setLinkId(LinkId);
					if (linkType != null && linkType == 5) {
						// 单个商品查询spu模板
						Long suTmplId = productFacade.querySuTmplIdBySuId(LinkId);
						vo.setSuTmplId(suTmplId);
						
						// 查询单个商品与当前公司、当前客户端是否有关系
						boolean companyAvailable = productFacade.querySuCompanyAvailable(LinkId, companyId, clientId);
						vo.setSuCompanyAvailable(companyAvailable);
					}
					vo.setLinkParam(lb.getLinkParam());
					vo.setLinkUrl(lb.getLinkUrl());
				}
			}
			bannerList.add(vo);
		}
		return JsonResult.success("bannerList", bannerList);
	}

	@Override
	public JsonResult<Integer> resetBannerStatusById(BannerDTO dto) {
		BannerDTO dto_ = bannerFacade.queryBannerById(dto.getId());
		if (EmptyUtil.isEmpty(dto_))
			return JsonResult.fail("该轮播图对象不存在");
		
		// 设置停用启用状态
		dto.setEnabled(dto_.getEnabled() != null && dto_.getEnabled() == 1 ? Integer.valueOf(0) : Integer.valueOf(1));
		
		return JsonResult.success(bannerFacade.updateBannerById(dto));
	}

	@Override
	public JsonResult<List<Map<String, Object>>> queryCompanyListByBannerId(BannerDTO dto) {
		List<CompanyDTO> rt = bannerFacade.queryCompanyListByBannerId(dto);
		
		List<Map<String, Object>> list = new ArrayList<>();
		for (CompanyDTO companyDTO : rt) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", companyDTO.getId());
			map.put("isTest", companyDTO.getIsTest());
			map.put("companyContent", companyDTO.getCompanyContent());
			map.put("companyName", companyDTO.getCompanyName());
			
			list.add(map);
		}
		
		return JsonResult.success(list);
	}

}
	