package com.egeo.components.cms.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.components.cms.dto.TemplateDTO;
import com.egeo.components.cms.service.read.BannerReadService;
import com.egeo.components.cms.service.read.CmsDictReadService;
import com.egeo.components.cms.service.read.IconGroupReadService;
import com.egeo.components.cms.service.read.IconReadService;
import com.egeo.components.cms.service.read.ImagetextGroupReadService;
import com.egeo.components.cms.service.read.ImagetextReadService;
import com.egeo.components.cms.service.read.LinkableButtonPageReadService;
import com.egeo.components.cms.service.read.LinkableButtonReadService;
import com.egeo.components.cms.service.read.ShoppingLabelGroupReadService;
import com.egeo.components.cms.service.read.ShoppingLabelReadService;
import com.egeo.components.cms.service.read.SuListReadService;
import com.egeo.components.cms.service.read.TemplateReadService;
import com.egeo.components.cms.service.write.TemplateWriteService;
import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitCompanyClient;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitByTypeDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.user.client.CompanyCoreClient;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class TemplateFacade {
	
	@Resource
	private TemplateReadService templateReadService;
	@Resource
	private CmsDictReadService cmsDictReadService;
	@Resource
	private TemplateWriteService templateWriteService;
	@Autowired
	private CompanyCoreClient companyCoreReadService;
	@Resource
	private BannerReadService bannerReadService;
	@Resource
	private LinkableButtonReadService linkableButtonReadService;
	@Autowired
	private StandardProductUnitClient spuReadService;
	@Autowired
	private StandardUnitCompanyClient suCompanyReadService;
	@Resource
	private SuListReadService suListReadService;
	@Autowired
	private StandardUnitClient suReadService;
	@Resource
	private IconGroupReadService iconGroupReadService;
	@Resource
	private IconReadService iconReadService;
	@Resource
	private ImagetextGroupReadService imagetextGroupReadService;
	@Resource
	private ImagetextReadService imagetextReadService;
	@Resource
	private ShoppingLabelGroupReadService shoppingLabelGroupReadService;
	@Resource
	private ShoppingLabelReadService shoppingLabelReadService;
	
	@Resource
	private LinkableButtonPageReadService linkableButtonPageReadService;

	/**
	 * 查询模板分页列表
	 * @param name
	 * @param page
	 * @param clientType 
	 * @param status 
	 * @return
	 */
	public PageResult<TemplateDTO> queryTemplatePage(
			String name,Integer type ,Pagination page, 
			Integer status, Integer clientType,
			Integer companyType,Long platformId) {
		return templateReadService.queryTemplatePage(name,type,page,status,clientType,companyType,platformId);
	}

	/**
	 * 事务启用模板
	 * 不同客户端类型,不同模板类型进行隔离
	 *
	 * @param platformId
	 * @param templateId
	 * @param clientType
	 * @param type
	 * @return
	 */
	public boolean useTemplate(Long platformId, Long templateId, Integer clientType, Integer type, Integer companyType) {
		return templateWriteService.useTemplate(platformId,templateId,clientType,type, companyType);
	}

	/**
	 * 新增模板,关联组件
	 * @param tmpl
	 * @param eleIds
	 */
	public Long createTemplate(TemplateDTO tmpl, List<Long> eleIds) {
		return templateWriteService.createTemplate(tmpl,eleIds);
	}

	/**
	 * 修改模板,关联组件
	 * @param tmpl
	 * @param eleIds
	 * @return
	 */
	public boolean editTemplate(TemplateDTO tmpl, List<Long> eleIds) {
		
		return templateWriteService.editTemplate(tmpl,eleIds);
	}

	/**
	 * 根据id查询模板
	 * @param templateId
	 * @return
	 */
	public TemplateDTO queryTemplateById(Long templateId) {
		return templateReadService.findTemplateById(templateId);
	}

	/**
	 * 根据客户端类型查询被启用的模板
	 * @param clientType
	 * @return
	 */
	public TemplateDTO queryInUseTemplateByClientType(Integer clientType,Integer type, Integer companyType,Long platformId) {
		return templateReadService.queryInUseTemplateByClientType(clientType, type, companyType,platformId);
	}

	/**
	 * 新建空白模板
	 * @param type
	 * @return
	 */
	public Long createEmptyTmpl(Integer type, Integer companyType,Long platformId) {
		TemplateDTO dto=new TemplateDTO();
		dto.setShowFgj(1);
		dto.setStatus(1);
		dto.setType(type);
		dto.setCompanyType(companyType);
		dto.setPlatformId(platformId);
		return templateWriteService.insertTemplateWithTx(dto);
	}

	/**
	 * 查询该客户端同一类型,公司类型下的模板列表
	 * @param clientType
	 * @return
	 */
	public List<TemplateDTO> queryTemplatesByClientType(Integer clientType,Integer type,Integer companyType,Long platformId) {
		TemplateDTO dto=new TemplateDTO();
		dto.setClientType(clientType);
		dto.setType(type);
		dto.setCompanyType(companyType);
		dto.setPlatformId(platformId);
		return templateReadService.findTemplateAll(dto);
	}

	public List<BannerDTO> queryBannerListByInstIdAndCompanyId(Long instId, Long companyId) {
		// 根据公司id查询公司类型对应的所有公司id
		Long companyAllId = companyCoreReadService.findCompanyAllIdByCompanyId(companyId);
		return bannerReadService.queryBannerListByInstIdAndCompanyId(instId,companyId,companyAllId);

	}

	/**
	 * 根据id查询可跳转链接
	 * @param linkableId
	 * @return
	 */
	public LinkableButtonDTO queryLinkableByttonById(Long linkableId) {
		return linkableButtonReadService.findLinkableButtonById(linkableId);

	}
	
	/**
	 * 根据linkableId 查询跳转页面Id
	 * @param linkableId
	 * @return
	 */
	public List<LinkableButtonPageDTO> queryLinkableButtonPageById(Long linkableId) {
		return linkableButtonPageReadService.findLinkableButtonPageAll(new LinkableButtonPageDTO(linkableId));

	}

	/**
	 * 根据suId查询商品模板id
	 * @param linkId
	 * @return
	 */
	public Long querySuTmplIdBySuId(Long linkId) {
		StandardProductUnitDTO spu=spuReadService.querySpuBySuId(linkId);
		return spu==null?null:spu.getCommodityTemplateId();
	}

	/**
	 * 查询su是否对公司、客户端可见
	 */
	public boolean querySuCompanyAvailable(Long linkId, Long companyId, Long clientId) {
		// 根据公司id查询公司类型对应的所有公司id
        Integer companyType = null;
    	if(RuntimeContext.cacheUser()==null || RuntimeContext.cacheUser().getCompanyType()==null) {
    		companyType = companyCoreReadService.findCompanyTypeById(companyId);
    	}else {
    		companyType = RuntimeContext.cacheUser().getCompanyType();
    	}
		return suCompanyReadService.querySuCompanyAvailable(linkId,companyId,clientId,companyType);

	}

/**
 * 根据实例id查询商品列表
 */
	public SuListDTO querySuListByInstId(Long instId) {
		return suListReadService.querySuListByInstId(instId);

	}

	public CmsDictDTO findCmsDictById(CmsDictDTO cmsDictDTO) {
		return cmsDictReadService.findCmsDictById(cmsDictDTO);
	}

	/**
	 * 根据sucid查询suList所关联的商品列表
	 */
	public PageResult<StandardUnitDTO> querySusBySucId(Long sucId, Long platformId, Long companyId, Long clientId, Integer maxShow, Long storeId) {
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
	/**
	 * 根据实例id查询icon组
	 * @param instId
	 * @return
	 */
	public IconGroupDTO queryIconGroupByInstId(Long instId) {
		return iconGroupReadService.queryIconGroupByInstId(instId);
	}

	public List<IconDTO> queryIconsByGroupId(Long id) {
		return iconReadService.queryIconsByGroupId(id);
	}
	/**
	 * 根据实例id查询图文组件组
	 */
	public ImagetextGroupDTO queryImagetextGroupByInstId(Long instId, Integer groupType) {
		return imagetextGroupReadService.queryImagetextGroupByInstId(instId, groupType);

	}
/**
 * 根据组id查询图文组件
 */
	public List<ImagetextDTO> queryImagetextByGroupId(Long id) {
		return imagetextReadService.queryImagetextByGroupId(id);

	}
	/**
	 * 根据实例id查询商城标签组
	 * @param instId
	 * @return
	 */
	public ShoppingLabelGroupDTO queryShoppingLabelGroupByInstId(Long instId) {
		return shoppingLabelGroupReadService.queryShoppingLabelGroupByInstId(instId);

	}
	/**
	 * 根据组id查询商城标签列表
	 * @return
	 */
	public List<ShoppingLabelDTO> queryShoppingLabelListByGroupId(Long id) {
		return shoppingLabelReadService.queryShoppingLabelListByGroupId(id);
	}
	
}
	