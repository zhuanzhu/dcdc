package com.egeo.components.cms.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.user.client.CompanyCoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.cms.business.BannerManage;
import com.egeo.components.cms.business.CmsPageManage;
import com.egeo.components.cms.converter.CmsPageConverter;
import com.egeo.components.cms.dao.read.CmsMainCfgReadDAO;
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.components.cms.facade.CmsPageFacade;
import com.egeo.components.cms.po.CmsMainCfgPO;
import com.egeo.components.cms.vo.CmsPageVO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

/**
 *
 * 商品列表页管理
 *
 */
@Controller
@RequestMapping("/api/cms/cmsPage")
public class CmsPageAction extends BaseSpringController {

	public Logger logger = LoggerFactory.getLogger(CmsPageAction.class);

	@Resource(name="cmsPage")
	private CmsPageManage cmsPageManage;
	@Autowired
	private CmsPageFacade cmspageFacade;
	@Autowired
	private CmsMainCfgReadDAO cmsMainCfgReadDao;
	@Autowired
	private BannerManage bannerManage;
	@Autowired
	private CompanyCoreClient companyCoreClient;

	/**
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findCmsPageById")
	@ResponseBody
	public JsonResult<CmsPageVO> findCmsPageById(Long id ) {
		CmsPageDTO dto = new CmsPageDTO();
		dto.setId(id);
		CmsPageDTO rt = cmsPageManage.findCmsPageById(dto);
		return JsonResult.success(CmsPageConverter.toVO(rt));
	}



	// 业务方法：
	@RequestMapping(value = "/findCmsPageAll")
	@ResponseBody
	public JsonResult<List<CmsPageVO>> findCmsPageAll(CmsPageVO vo,HttpServletRequest req ) {
		CmsPageDTO dto = CmsPageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CmsPageDTO> rt = cmsPageManage.findCmsPageAll(dto);
		return JsonResult.success(CmsPageConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findCmsPageOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCmsPageOfPage(CmsPageVO vo,Pagination page,HttpServletRequest req ) {
		CmsPageDTO dto = CmsPageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = cmsPageManage.findCmsPageOfPage(dto, page);
		return JsonResult.success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCmsPageWithTx")
	@ResponseBody
	public JsonResult<Long> insertCmsPageWithTx(CmsPageVO vo,HttpServletRequest req ) {
		logger.info("新增页面保存，CmsPageVO：{}", JSONObject.toJSONString(vo));
		if (EmptyUtil.isEmpty(vo.getPageName())) {
			return JsonResult.fail("页面名称不能为空");
		}
		if (EmptyUtil.isEmpty(vo.getTemplateId())) {
			return JsonResult.fail("所属模板不能为空");
		}
		if (EmptyUtil.isEmpty(vo.getConfigJson())) {
			return JsonResult.fail("页面配置项不能为空");
		}
		CmsPageDTO pageDto = new CmsPageDTO();
		pageDto.setPageName(vo.getPageName());
		List<CmsPageDTO> list = cmsPageManage.findCmsPageAll(pageDto);
		if(list != null && list.size() > 0) {
			return JsonResult.fail("页面名称重复");
		}
		if (Objects.equals(vo.getIsDefault(),1) || vo.getCompanyId()<0){
			Long defaultCompanyId=companyCoreClient.findCompanyAllIdByCompanyType(
					Objects.nonNull(vo.getCompanyType())?vo.getCompanyType():0);
			vo.setCompanyId(defaultCompanyId);
		}
		CmsPageDTO dto = CmsPageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}

		CacheUser user = this.getCacheUser();
		dto.setUpdateUserId(user.getId());

		Long rt = cmsPageManage.insertCmsPageWithTx(dto, vo.getConfigJson());
		return JsonResult.success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCmsPageByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCmsPageByIdWithTx(CmsPageVO vo,HttpServletRequest req ) {
		logger.info("更新页面保存，CmsPageVO：{}", JSONObject.toJSONString(vo));
		if (EmptyUtil.isEmpty(vo.getPageName()) || EmptyUtil.isEmpty(vo.getTemplateId()) || EmptyUtil.isEmpty(vo.getConfigJson())) {
			return JsonResult.fail("参数错误");
		}
		CmsPageDTO pageDto = new CmsPageDTO();
		pageDto.setId(vo.getId());
		CmsPageDTO original = cmsPageManage.findCmsPageById(pageDto);
		if(!original.getPageName().equals(vo.getPageName())) {
			pageDto.setPageName(vo.getPageName());
			List<CmsPageDTO> list = cmsPageManage.findCmsPageAll(pageDto);
			if(list != null && list.size() > 0) {
				return JsonResult.fail("页面名称重复");
			}
		}
		if (Objects.equals(vo.getIsDefault(),1) || vo.getCompanyId()<0){
			Long defaultCompanyId=companyCoreClient.findCompanyAllIdByCompanyType(
					Objects.nonNull(vo.getCompanyType())?vo.getCompanyType():0);
			vo.setCompanyId(defaultCompanyId);
		}
		CmsPageDTO dto = CmsPageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser user = this.getCacheUser();
		dto.setUpdateUserId(user.getId());
		int rt = cmsPageManage.updateCmsPageWithTx(dto, vo.getConfigJson());
		return JsonResult.success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCmsPageWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCmsPageWithTx(CmsPageVO vo,HttpServletRequest req ) {
		CmsPageDTO dto = CmsPageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cmsPageManage.deleteCmsPageWithTx(dto);
		return JsonResult.success(rt);
	}

	/**
	 * scope 查询范围 0-默认（页面配置+页面组件实例） 1-仅页面配置  2-仅组件实例
	 * @param pageId
	 * @param scope
	 * @param req
	 * @return
	 */

	@RequestMapping(value = "/findPageCfgByIdMock")
	@ResponseBody
	public JsonResult<Map<String, Object>> findPageCfgByIdMock(Long pageId, Integer scope, Integer pageType, Pagination page, HttpServletRequest req) {
		logger.info("查询页面信息：pageId:{}, scope:{}", new Object[]{pageId, scope});
		String data ="";
		if(page.getPageNo()==1) {
			//data  = "{\"pageConfig\":{\"backgroundImageUrl\":\"https://image.dachuguanjia.cn/static/background\",\"showTitleCheckBox\":\"1\",\"titleHeight\":\"100\",\"titleOpacity\":\"0\",\"navRightUpIconCheckBox\":\"1\",\"navRightUpIcon1Type\":\"3\",\"navRightUpIcon2Type\":\"2\"},\"totalSize\":4,\"pageNo\":1,\"totalPage\":2,\"pageInsts\":[{\"elementId\":8,\"instId\":13440,\"config8\":{\"instName\":\"1\",\"totalHeight\":\"320\",\"style\":\"0\",\"bannerHeight\":\"320\",\"paddingLeftRight\":\"18\",\"paddingSide\":\"18\",\"banner\":[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}],\"bannerListSort\":\"1\",\"bannerListWidth\":\"750\",\"bannerList\":[{\"id\":132,\"imgUrl\":\"https://image.dachuguanjia.cn/20210312153616egeo153608\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值22\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null},{\"id\":138,\"imgUrl\":\"https://image.dachuguanjia.cn/20200930172231egeo170846\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null}],\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}},{\"elementId\":10,\"instId\":13441,\"config10\":{\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"columns\":\"4\",\"rows\":\"2\",\"rowHeight\":\"200\",\"iconHeight\":\"120\",\"xSpacing\":\"58\",\"showIconText\":\"1\",\"fontSize\":\"26\",\"showTop2Row\":\"0\",\"lastText\":\"全部\",\"iconList\":[{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143835egeo143719\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1610/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"美妆护肤\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143840egeo143756\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"家居家纺\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143844egeo143738\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1611/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"食品生鲜2\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144724egeo143946\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1595/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"生活家电\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143935egeo143924\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/6534/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"办公电子\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144730egeo143806\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1602/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"日用百货\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144734egeo143956\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1601/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"厨房配件\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144738egeo143646\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":25,\"extParam3\":\"\",\"available\":true,\"linkUrl\":null,\"extParam2\":\"\",\"suTmplId\":null,\"extParam1\":\"22\",\"linkType\":1,\"cmsPageId\":null},\"text\":\"更多\"}]}},{\"elementId\":12,\"instId\":13442,\"config12\":{\"totalHeight\":\"540\",\"xSpacing\":\"18\",\"ySpacing\":\"20\",\"style\":\"1\",\"sidePadding\":\"x`\",\"verticalBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"},\"sideUpBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"sideDownBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}}],\"pageSize\":3}";

			if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getCompanyId()!=null) {
				CmsMainCfgPO po = new CmsMainCfgPO();
				po.setCompanyId(RuntimeContext.cacheUser().getCompanyId());
				List<CmsMainCfgPO> cfgs = cmsMainCfgReadDao.findAll(po, null);
				if(cfgs!=null && cfgs.size()==1) {
					CmsMainCfgPO cfg = cfgs.get(0);
					List<Map<String, Object>> selfProductData = cmspageFacade.findSuList(cfg.getSelfProduct(), RuntimeContext.cacheUser().getCompanyId());
					List<Map<String, Object>> recommendationData = cmspageFacade.findSuList(cfg.getRecommendation(), RuntimeContext.cacheUser().getCompanyId());
					data ="{\"pageConfig\":{\"backgroundImageUrl\":\"https://image.dachuguanjia.cn/static/background\",\"showTitleCheckBox\":\"1\",\"titleHeight\":\"100\",\"titleOpacity\":\"0\",\"navRightUpIconCheckBox\":\"1\",\"navRightUpIcon1Type\":\"3\",\"navRightUpIcon2Type\":\"2\"},\"totalSize\":4,\"pageNo\":1,\"totalPage\":2,\"pageInsts\":[{\"elementId\":8,\"instId\":13440,\"config8\":{\"instName\":\"1\",\"totalHeight\":\"320\",\"style\":\"0\",\"bannerHeight\":\"320\",\"paddingLeftRight\":\"18\",\"paddingSide\":\"18\",\"banner\":[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}],\"bannerListSort\":\"1\",\"bannerListWidth\":\"750\",\"bannerList\":[{\"id\":132,\"imgUrl\":\"https://image.dachuguanjia.cn/20210312153616egeo153608\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值22\",\"linkParam\":null,\"suTmplId\":2,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null},{\"id\":138,\"imgUrl\":\"https://image.dachuguanjia.cn/20200930172231egeo170846\",\"linkType\":5,\"linkId\":38,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"linkParam\":null,\"suTmplId\":2,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null}],\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}},{\"elementId\":14,\"instId\":13442,\"config14\":{\"showType\":\"3\",\"totalHeight\":\"500\",\"xSpacing\":\"18\",\"paddingSide\":\"18\",\"ySpacing\":\"20\",\"style\":\"1\",\"sidePadding\":\"x`\",\"verticalBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"},\"sideUpBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"500\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"sideDownBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"columns\":\"2\",\"rows\":\"2\",\"rowHeight\":\"200\",\"iconHeight\":\"120\",\"xSpacingIcon\":\"58\",\"showIconText\":\"1\",\"fontSize\":\"26\",\"showTop2Row\":\"0\",\"lastText\":\"全部\",\"iconList\":[{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143835egeo143719\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1610/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"美妆护肤\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143840egeo143756\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"家居家纺\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143844egeo143738\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1611/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"食品生鲜2\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144724egeo143946\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1595/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"生活家电\"}]} },  {\"elementId\":14,\"instId\":13442,\"config14\":{\"totalHeight\":\"500\",\"xSpacing\":\"18\",\"paddingSide\":\"18\",\"ySpacing\":\"20\",\"style\":\"1\",\"sidePadding\":\"x`\",\"verticalBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"},\"sideUpBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"sideDownBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"marginTop\":\"9\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"columns\":\"2\",\"rows\":\"2\",\"rowHeight\":\"200\",\"iconHeight\":\"120\",\"xSpacingIcon\":\"58\",\"showIconText\":\"1\",\"fontSize\":\"26\",\"showTop2Row\":\"0\",\"lastText\":\"全部\",\"iconList\":[{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143835egeo143719\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1610/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"美妆护肤\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143840egeo143756\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"家居家纺\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143844egeo143738\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1611/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"食品生鲜2\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144724egeo143946\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1595/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"生活家电\"}]} },  {\"elementId\":8,\"instId\":13440,\"config8\":{\"instName\":\"1\",\"totalHeight\":\"280\",\"style\":\"0\",\"bannerHeight\":\"280\",\"paddingLeftRight\":\"18\",\"paddingSide\":\"18\",\"banner\":[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}],\"bannerListSort\":\"1\",\"bannerListWidth\":\"750\",\"bannerList\":[{\"id\":132,\"imgUrl\":\"https://image.dachuguanjia.cn/20210312153616egeo153608\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值22\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null},{\"id\":138,\"imgUrl\":\"https://image.dachuguanjia.cn/20200930172231egeo170846\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null}],\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}},{\"elementId\":11,\"instId\":13443,\"config11\":{\"showType\":\"4\",\"suRowHeightWithPC\":\"380\",\"suRowHeightNoPC\":\"310\",\"suImgHeight\":\"224\",\"suImgWidth\":\"224\",\"xSpacing\":\"20\",\"ySpacing\":\"0\",\"suComb\":[],\"showCount\":\"168\",\"showPrice\":\"2\",\"showCart\":\"1\",\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"showOwnMerchant\":\"2\"}}],\"pageSize\":3}";
					JSONObject rslt = new JSONObject();
					JSONObject pageConfig = new JSONObject();
					//pageConfig
					pageConfig.put("backgroundImageUrl", "https://image.dachuguanjia.cn/static/background");
					pageConfig.put("showTitleCheckBox", "1");
					pageConfig.put("titleHeight", "100");
					pageConfig.put("titleOpacity", "0");
					pageConfig.put("navRightUpIconCheckBox", "1");
					pageConfig.put("navRightUpIcon1Type", "3");
					pageConfig.put("navRightUpIcon2Type", "2");
					rslt.put("totalSize", 1);
					rslt.put("pageNo", 1);
					rslt.put("totalPage", 1);
					rslt.put("pageSize", 3);
					rslt.put("pageConfig", pageConfig);
					JsonResult<Map<String, Object>> banners = bannerManage.queryCompanyBannerList(null, null, null, null, null, null, null, RuntimeContext.cacheUser().getCompanyId(), null, null, 7l, null);
					JSONArray pageInsts = new JSONArray();
					JSONObject banner1 = new JSONObject();
					JSONObject config81 = new JSONObject();
					JSONObject selfProduct = new JSONObject();
					JSONObject banner2 = new JSONObject();
					JSONObject config82 = new JSONObject();
					JSONObject recommendation = new JSONObject();
					config81.put("bannerList", cfg.getBannerHeader());
					config82.put("bannerList", cfg.getBannerMiddle());

					banner1.put("elementId", 8);
					banner2.put("elementId", 8);
					banner1.put("instId", 99999);
					banner2.put("instId", 99999);
					config81.put("instName", "1");
					config82.put("instName", "1");
					config81.put("totalHeight", "320");
					config82.put("totalHeight", "320");
					config81.put("style", "0");
					config82.put("style", "0");
					config81.put("bannerHeight", "320");
					config82.put("bannerHeight", "320");

					config81.put("paddingLeftRight", "320");
					config81.put("paddingSide", "320");
					config81.put("banner", "[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}]");
					config81.put("bannerListSort", "1");
					config81.put("bannerListWidth", "750");
					config81.put("marginTop", "0");
					config81.put("backgroundType", "1");
					config81.put("backgroundRGB", "#FFFFFF");

					config82.put("paddingLeftRight", "320");
					config82.put("paddingSide", "320");
					config82.put("banner", "[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}]");
					config82.put("bannerListSort", "1");
					config82.put("bannerListWidth", "750");
					config82.put("marginTop", "0");
					config82.put("backgroundType", "1");
					config82.put("backgroundRGB", "#FFFFFF");

					banner1.put("config8", config81);
					banner2.put("config8", config82);







					JSONObject config141 = new JSONObject();
					JSONObject config142 = new JSONObject();
					config141.put("showType", "3");
					config141.put("totalHeight", "500");
					config141.put("xSpacing", "18");
					config141.put("paddingSide", "18");
					config141.put("ySpacing", "20");
					config141.put("style", "1");
					config141.put("sidePadding", "x`");
					config141.put("verticalBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"}");
					config141.put("sideUpBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"500\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config141.put("sideDownBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config141.put("marginTop", "0");
					config141.put("backgroundType", "1");
					config141.put("backgroundRGB", "#FFFFFF");
					config141.put("columns", "2");
					config141.put("rows", "2");
					config141.put("rowHeight", "200");
					config141.put("iconHeight", "120");
					config141.put("xSpacingIcon", "58");
					config141.put("showIconText", "1");
					config141.put("fontSize", "26");
					config141.put("showTop2Row", "0");
					config141.put("lastText", "全部");
					config141.put("suComb", selfProductData);


					config142.put("showType", "3");
					config142.put("totalHeight", "500");
					config142.put("xSpacing", "18");
					config142.put("paddingSide", "18");
					config142.put("ySpacing", "20");
					config142.put("style", "1");
					config142.put("sidePadding", "x`");
					config142.put("verticalBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"}");
					config142.put("sideUpBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"500\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config142.put("sideDownBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config142.put("marginTop", "0");
					config142.put("backgroundType", "1");
					config142.put("backgroundRGB", "#FFFFFF");
					config142.put("columns", "2");
					config142.put("rows", "2");
					config142.put("rowHeight", "200");
					config142.put("iconHeight", "120");
					config142.put("xSpacingIcon", "58");
					config142.put("showIconText", "1");
					config142.put("fontSize", "26");
					config142.put("showTop2Row", "0");
					config142.put("lastText", "全部");
					config142.put("suComb", recommendationData);



					selfProduct.put("elementId", 141);
					recommendation.put("elementId", 142);
					selfProduct.put("instId", 99999);
					recommendation.put("instId", 99999);
					selfProduct.put("config14", config141);
					recommendation.put("config14", config142);



					pageInsts.add(banner1);
					pageInsts.add(selfProduct);
					pageInsts.add(banner2);
					pageInsts.add(recommendation);
					rslt.put("pageInsts", pageInsts);
					data =  rslt.toJSONString();
				}
			}
		}else {
			data  =  "{\"pageConfig\":{\"backgroundImageUrl\":\"https://image.dachuguanjia.cn/static/background\",\"showTitleCheckBox\":\"1\",\"titleHeight\":\"100\",\"titleOpacity\":\"0\",\"navRightUpIconCheckBox\":\"1\",\"navRightUpIcon1Type\":\"3\",\"navRightUpIcon2Type\":\"2\"},\"totalSize\":4,\"pageNo\":"+page.getPageNo()+",\"totalPage\":1,\"pageInsts\":[],\"pageSize\":4}";
		}
		JSONObject result = JSONObject.parseObject(data);
		return JsonResult.success(result);
	}


	@RequestMapping(value = "/findPageCfgById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findPageCfgById(Long pageId, Integer scope, Integer pageType, Pagination page, HttpServletRequest req) {
		logger.info("查询页面信息：pageId:{}, scope:{}", new Object[]{pageId, scope});
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		} else {
			return JsonResult.fail("平台参数为空");
		}
		String clientId_=req.getHeader("clientId");
		Long clientId=1l;
		if(EmptyUtil.isNotEmpty(clientId_)) {
			clientId=Long.parseLong(clientId_);
		}
		String fStr=req.getHeader("f");
		Long f = null;
		if(EmptyUtil.isNotEmpty(fStr)) {
			f = Long.parseLong(fStr);
		}
		String versionCodeStr=req.getHeader("vCode");
		Integer versionCode = 0;
		if(EmptyUtil.isNotEmpty(versionCodeStr)) {
			versionCode = Integer.parseInt(versionCodeStr);
		}
		CacheUser userCache = getCacheUser(false);
		if (scope == null) {
			scope = 0;
		}
		Long userId = userCache.getId();
		logger.info("查询页面信息：pageId:{}, scope:{},pageType:{},companyId:{}", pageId, scope ,pageType,userCache.getCompanyId());
		Map<String, Object> result = cmsPageManage.findPageCfgById(pageId, clientId, versionCode, f, userCache.getCompanyId(), scope, platformId, pageType, page, userId);
		logger.info("返回页面配置信息：{}", JSONObject.toJSONString(result));
		return JsonResult.success(result);
	}

	@RequestMapping(value = "/findPageCfgByIdMock2")
	@ResponseBody
	public JsonResult<Map<String, Object>> findPageCfgByIdMock2(Long pageId, Integer scope, Integer pageType, Pagination page, HttpServletRequest req) {
		logger.info("查询页面信息：pageId:{}, scope:{}", new Object[]{pageId, scope});
		String data ="";
		if(page.getPageNo()==1) {
			//data  = "{\"pageConfig\":{\"backgroundImageUrl\":\"https://image.dachuguanjia.cn/static/background\",\"showTitleCheckBox\":\"1\",\"titleHeight\":\"100\",\"titleOpacity\":\"0\",\"navRightUpIconCheckBox\":\"1\",\"navRightUpIcon1Type\":\"3\",\"navRightUpIcon2Type\":\"2\"},\"totalSize\":4,\"pageNo\":1,\"totalPage\":2,\"pageInsts\":[{\"elementId\":8,\"instId\":13440,\"config8\":{\"instName\":\"1\",\"totalHeight\":\"320\",\"style\":\"0\",\"bannerHeight\":\"320\",\"paddingLeftRight\":\"18\",\"paddingSide\":\"18\",\"banner\":[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}],\"bannerListSort\":\"1\",\"bannerListWidth\":\"750\",\"bannerList\":[{\"id\":132,\"imgUrl\":\"https://image.dachuguanjia.cn/20210312153616egeo153608\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值22\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null},{\"id\":138,\"imgUrl\":\"https://image.dachuguanjia.cn/20200930172231egeo170846\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null}],\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}},{\"elementId\":10,\"instId\":13441,\"config10\":{\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"columns\":\"4\",\"rows\":\"2\",\"rowHeight\":\"200\",\"iconHeight\":\"120\",\"xSpacing\":\"58\",\"showIconText\":\"1\",\"fontSize\":\"26\",\"showTop2Row\":\"0\",\"lastText\":\"全部\",\"iconList\":[{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143835egeo143719\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1610/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"美妆护肤\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143840egeo143756\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"家居家纺\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143844egeo143738\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1611/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"食品生鲜2\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144724egeo143946\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1595/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"生活家电\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143935egeo143924\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/6534/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"办公电子\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144730egeo143806\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1602/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"日用百货\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144734egeo143956\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1601/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"厨房配件\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144738egeo143646\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":25,\"extParam3\":\"\",\"available\":true,\"linkUrl\":null,\"extParam2\":\"\",\"suTmplId\":null,\"extParam1\":\"22\",\"linkType\":1,\"cmsPageId\":null},\"text\":\"更多\"}]}},{\"elementId\":12,\"instId\":13442,\"config12\":{\"totalHeight\":\"540\",\"xSpacing\":\"18\",\"ySpacing\":\"20\",\"style\":\"1\",\"sidePadding\":\"x`\",\"verticalBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"},\"sideUpBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"sideDownBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}}],\"pageSize\":3}";

			if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getCompanyId()!=null) {
				CmsMainCfgPO po = new CmsMainCfgPO();
				po.setCompanyId(RuntimeContext.cacheUser().getCompanyId());
				logger.info("获取公司首页配置:"+RuntimeContext.cacheUser().getCompanyId());
				List<CmsMainCfgPO> cfgs = cmsMainCfgReadDao.findAll(po, null);
				if(cfgs!=null && cfgs.size()==1) {
					CmsMainCfgPO cfg = cfgs.get(0);
					List<Map<String, Object>> selfProductData = new ArrayList<Map<String, Object>>();/* = cmspageFacade.findSuList(cfg.getSelfProduct(), RuntimeContext.cacheUser().getCompanyId());*/

					List<Map<String, Object>> recommendationData =  new ArrayList<Map<String, Object>>();/*cmspageFacade.findSuList(cfg.getRecommendation(), RuntimeContext.cacheUser().getCompanyId());*/

					for(Map<String, Object> oneSelf : selfProductData) {
						oneSelf.put("source", 1);
						oneSelf.put("showPrice", 2);
						oneSelf.put("buyType", 1);
						oneSelf.put("showCart", 2);
					}
					for(Map<String, Object> oneSelf : recommendationData) {
						oneSelf.put("source", 1);
						oneSelf.put("showPrice", 2);
						oneSelf.put("buyType", 1);
						oneSelf.put("showCart", 2);
					}

					data ="{\"pageConfig\":{\"backgroundImageUrl\":\"https://image.dachuguanjia.cn/static/background\",\"showTitleCheckBox\":\"1\",\"titleHeight\":\"100\",\"titleOpacity\":\"0\",\"navRightUpIconCheckBox\":\"1\",\"navRightUpIcon1Type\":\"3\",\"navRightUpIcon2Type\":\"2\"},\"totalSize\":4,\"pageNo\":1,\"totalPage\":2,\"pageInsts\":[{\"elementId\":8,\"instId\":13440,\"config8\":{\"instName\":\"1\",\"totalHeight\":\"320\",\"style\":\"0\",\"bannerHeight\":\"320\",\"paddingLeftRight\":\"18\",\"paddingSide\":\"18\",\"banner\":[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}],\"bannerListSort\":\"1\",\"bannerListWidth\":\"750\",\"bannerList\":[{\"id\":132,\"imgUrl\":\"https://image.dachuguanjia.cn/20210312153616egeo153608\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值22\",\"linkParam\":null,\"suTmplId\":2,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null},{\"id\":138,\"imgUrl\":\"https://image.dachuguanjia.cn/20200930172231egeo170846\",\"linkType\":5,\"linkId\":38,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"linkParam\":null,\"suTmplId\":2,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null}],\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}},{\"elementId\":14,\"instId\":13442,\"config14\":{\"showType\":\"3\",\"totalHeight\":\"500\",\"xSpacing\":\"18\",\"paddingSide\":\"18\",\"ySpacing\":\"20\",\"style\":\"1\",\"sidePadding\":\"x`\",\"verticalBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"},\"sideUpBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"500\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"sideDownBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"columns\":\"2\",\"rows\":\"2\",\"rowHeight\":\"200\",\"iconHeight\":\"120\",\"xSpacingIcon\":\"58\",\"showIconText\":\"1\",\"fontSize\":\"26\",\"showTop2Row\":\"0\",\"lastText\":\"全部\",\"iconList\":[{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143835egeo143719\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1610/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"美妆护肤\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143840egeo143756\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"家居家纺\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143844egeo143738\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1611/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"食品生鲜2\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144724egeo143946\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1595/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"生活家电\"}]} },  {\"elementId\":14,\"instId\":13442,\"config14\":{\"totalHeight\":\"500\",\"xSpacing\":\"18\",\"paddingSide\":\"18\",\"ySpacing\":\"20\",\"style\":\"1\",\"sidePadding\":\"x`\",\"verticalBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"},\"sideUpBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"sideDownBanner\":{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"},\"marginTop\":\"9\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"columns\":\"2\",\"rows\":\"2\",\"rowHeight\":\"200\",\"iconHeight\":\"120\",\"xSpacingIcon\":\"58\",\"showIconText\":\"1\",\"fontSize\":\"26\",\"showTop2Row\":\"0\",\"lastText\":\"全部\",\"iconList\":[{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143835egeo143719\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1610/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"美妆护肤\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143840egeo143756\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"家居家纺\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205143844egeo143738\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1611/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"食品生鲜2\"},{\"imgUrl\":\"https://image.dachuguanjia.cn/20210205144724egeo143946\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/shop/goodsList/1595/1\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"text\":\"生活家电\"}]} },  {\"elementId\":8,\"instId\":13440,\"config8\":{\"instName\":\"1\",\"totalHeight\":\"280\",\"style\":\"0\",\"bannerHeight\":\"280\",\"paddingLeftRight\":\"18\",\"paddingSide\":\"18\",\"banner\":[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}],\"bannerListSort\":\"1\",\"bannerListWidth\":\"750\",\"bannerList\":[{\"id\":132,\"imgUrl\":\"https://image.dachuguanjia.cn/20210312153616egeo153608\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值22\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null},{\"id\":138,\"imgUrl\":\"https://image.dachuguanjia.cn/20200930172231egeo170846\",\"linkType\":2,\"linkId\":null,\"linkUrl\":\"/#/shop/goodsList/1600/1\",\"linkParam\":null,\"suTmplId\":null,\"companyIds\":null,\"suCompanyAvailable\":true,\"linkableButtonPageList\":null,\"cmsPageId\":null,\"extParam1\":null,\"extParam2\":null,\"extParam3\":null,\"available\":true,\"msg\":null}],\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\"}},{\"elementId\":11,\"instId\":13443,\"config11\":{\"showType\":\"4\",\"suRowHeightWithPC\":\"380\",\"suRowHeightNoPC\":\"310\",\"suImgHeight\":\"224\",\"suImgWidth\":\"224\",\"xSpacing\":\"20\",\"ySpacing\":\"0\",\"suComb\":[],\"showCount\":\"168\",\"showPrice\":\"2\",\"showCart\":\"1\",\"marginTop\":\"0\",\"backgroundType\":\"1\",\"backgroundRGB\":\"#FFFFFF\",\"showOwnMerchant\":\"2\"}}],\"pageSize\":3}";
					JSONObject rslt = new JSONObject();
					JSONObject pageConfig = new JSONObject();
					//pageConfigsuComb
					pageConfig.put("backgroundImageUrl", "https://image.dachuguanjia.cn/static/background");
					pageConfig.put("showTitleCheckBox", "1");
					pageConfig.put("titleHeight", "100");
					pageConfig.put("titleOpacity", "0");
					pageConfig.put("navRightUpIconCheckBox", "1");
					pageConfig.put("navRightUpIcon1Type", "3");
					pageConfig.put("navRightUpIcon2Type", "2");
					pageConfig.put("tempType", cfg.getTempType() !=null?cfg.getTempType().intValue():null);
					rslt.put("totalSize", 1);
					rslt.put("pageNo", 1);
					rslt.put("totalPage", 1);
					rslt.put("pageSize", 3);
					rslt.put("pageConfig", pageConfig);
					JsonResult<Map<String, Object>> banners = bannerManage.queryCompanyBannerList(null, null, null, null, null, null, null, RuntimeContext.cacheUser().getCompanyId(), null, null, 7l, null);
					JSONArray pageInsts = new JSONArray();
					JSONObject banner1 = new JSONObject();
					JSONObject config81 = new JSONObject();
					JSONObject selfProduct = new JSONObject();
					JSONObject banner2 = new JSONObject();
					JSONObject config82 = new JSONObject();
					JSONObject recommendation = new JSONObject();
					config81.put("bannerList", cfg.getBannerHeader());
					config82.put("bannerList", cfg.getBannerMiddle());

					banner1.put("elementId", 8);
					banner2.put("elementId", 108);
					banner1.put("instId", 99999);
					banner2.put("instId", 99999);
					config81.put("instName", "banner");
					config81.put("totalHeight", "320");
					config82.put("totalHeight", "320");
					config81.put("style", "0");
					config82.put("style", "0");
					config81.put("bannerHeight", "320");
					config82.put("bannerHeight", "280");

					config81.put("paddingLeftRight", "0");
					config81.put("paddingSide", "0");
					config81.put("banner", "[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}]");
					config81.put("bannerListSort", "1");
					config81.put("bannerListWidth", "750");
					config81.put("marginTop", "0");
					config81.put("backgroundType", "1");
					config81.put("backgroundRGB", "#FFFFFF");

					config82.put("paddingLeftRight", "320");
					config82.put("paddingSide", "320");
					config82.put("banner", "[{\"imgUrl\":null,\"borderRadius\":null,\"width\":\"750\",\"link\":null,\"borderRadiusCheckBox\":\"0\"}]");
					config82.put("bannerListSort", "1");
					config82.put("bannerListWidth", "750");
					config82.put("marginTop", "0");
					config82.put("backgroundType", "1");
					config82.put("backgroundRGB", "#FFFFFF");

					banner1.put("config8", config81);
					banner2.put("config8", config82);







					JSONObject config141 = new JSONObject();
					JSONObject config142 = new JSONObject();
					config141.put("showType", "3");
					config141.put("totalHeight", "500");
					config141.put("xSpacing", "18");
					config141.put("paddingSide", "18");
					config141.put("ySpacing", "20");
					config141.put("style", "1");
					config141.put("sidePadding", "x`");
					config141.put("verticalBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"}");
					config141.put("sideUpBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"500\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config141.put("sideDownBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config141.put("marginTop", "0");
					config141.put("backgroundType", "1");
					config141.put("backgroundRGB", "#FFFFFF");
					config141.put("columns", "2");
					config141.put("rows", "2");
					config141.put("rowHeight", "200");
					config141.put("iconHeight", "120");
					config141.put("xSpacingIcon", "58");
					config141.put("showIconText", "1");
					config141.put("fontSize", "26");
					config141.put("showTop2Row", "0");
					config141.put("lastText", "全部");
					config141.put("showPrice", "2");
					config141.put("buyType", "1");
					config141.put("showCart", "1");
					config141.put("suComb", selfProductData);


					config142.put("showType", "3");
					config142.put("totalHeight", "500");
					config142.put("xSpacing", "18");
					config142.put("paddingSide", "18");
					config142.put("ySpacing", "20");
					config142.put("style", "1");
					config142.put("sidePadding", "x`");
					config142.put("verticalBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115102822egeo102424\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=超值\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"500\"}");
					config142.put("sideUpBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104613egeo104551\",\"borderRadius\":null,\"width\":\"500\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=生日\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config142.put("sideDownBanner", "{\"imgUrl\":\"https://image.dachuguanjia.cn/20210115104617egeo104559\",\"borderRadius\":null,\"width\":\"366\",\"link\":{\"msg\":null,\"linkParam\":null,\"linkId\":null,\"available\":true,\"linkUrl\":\"/#/activeGoodsList/-2/2/7/0?key=夏季\",\"suTmplId\":null,\"linkType\":2,\"cmsPageId\":null},\"borderRadiusCheckBox\":\"0\",\"height\":\"240\"}");
					config142.put("marginTop", "0");
					config142.put("backgroundType", "1");
					config142.put("backgroundRGB", "#FFFFFF");
					config142.put("columns", "2");
					config142.put("rows", "2");
					config142.put("rowHeight", "200");
					config142.put("iconHeight", "120");
					config142.put("xSpacingIcon", "58");
					config142.put("showIconText", "1");
					config142.put("fontSize", "26");
					config142.put("showTop2Row", "0");
					config142.put("lastText", "全部");
					config142.put("showPrice", "2");
					config142.put("buyType", "1");
					config142.put("showCart", "1");
					config142.put("suComb", recommendationData);



					selfProduct.put("elementId", 141);
					recommendation.put("elementId", 142);
					selfProduct.put("instId", 99999);
					recommendation.put("instId", 99999);
					selfProduct.put("config14", config141);
					recommendation.put("config14", config142);



					pageInsts.add(banner1);
					pageInsts.add(selfProduct);
					pageInsts.add(banner2);
					pageInsts.add(recommendation);
					rslt.put("pageInsts", pageInsts);
					data =  rslt.toJSONString();
				}
			}
		}else {
			data  =  "{\"pageConfig\":{\"backgroundImageUrl\":\"https://image.dachuguanjia.cn/static/background\",\"showTitleCheckBox\":\"1\",\"titleHeight\":\"100\",\"titleOpacity\":\"0\",\"navRightUpIconCheckBox\":\"1\",\"navRightUpIcon1Type\":\"3\",\"navRightUpIcon2Type\":\"2\"},\"totalSize\":4,\"pageNo\":"+page.getPageNo()+",\"totalPage\":1,\"pageInsts\":[],\"pageSize\":4}";
		}
		JSONObject result = JSONObject.parseObject(data);
		return JsonResult.success(result);
	}

	// 业务方法：
	@RequestMapping(value = "/findCmsPageAllByClientType")
	@ResponseBody
	public JsonResult<List<CmsPageVO>> findCmsPageAllByClientType(CmsPageVO vo,HttpServletRequest req ) {
		CmsPageDTO dto = CmsPageConverter.toDTO(vo);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CmsPageDTO> rt = cmsPageManage.findCmsPageAllByClientType(dto);
		return JsonResult.success(CmsPageConverter.toVO(rt));

	}

	/**
	 * 修改页面状态（启用 停用）
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public JsonResult<Integer> updateStatus(CmsPageVO vo,HttpServletRequest req ) {
		logger.info("更新页面保存，CmsPageVO：{}", JSONObject.toJSONString(vo));
		CmsPageDTO dto = CmsPageConverter.toDTO(vo);

		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cmsPageManage.updateStatus(dto);

		return JsonResult.success(rt);
	}

}
