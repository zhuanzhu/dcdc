package com.egeo.components.cms.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.components.cms.business.BannerManage;
import com.egeo.components.cms.dao.read.BannerImgReadDAO;
import com.egeo.components.cms.dao.read.BannerReadDAO;
import com.egeo.components.cms.dao.write.BannerCompanyWriteDAO;
import com.egeo.components.cms.dao.write.BannerImgWriteDAO;
import com.egeo.components.cms.dao.write.BannerWriteDAO;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.components.cms.po.BannerImgPO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/banner")
public class BannerAction extends BaseSpringController {
	
	@Resource(name="banner")
	private BannerManage bannerManage;
	@Autowired
	private BannerWriteDAO bannerWriteDAO;
	@Autowired
	private BannerReadDAO bannerReadDAO;
	@Autowired
	private BannerCompanyWriteDAO bannerCompanyWriteDAO;
	@Autowired
	private BannerImgWriteDAO bannerImgWriteDao;
	@Autowired
	private BannerImgReadDAO bannerImgReadDao;
	
	/**
	 * 查询轮播图列表
	 * @param vo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/queryBannerList")
	@ResponseBody
	public JsonResult<Map<String, Object>> queryBannerList(
			String name,String companyName,Integer linkType,String bannerIdList,
			Integer belongPage,Integer pageNo,Integer pageSize, Long companyId, 
			Integer enabled, Integer belongPageType,Integer companyType, HttpServletRequest req) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}

		List<Long> bannerIdList_ = null;
		try{
			bannerIdList_ = EmptyUtil.isNotBlank(bannerIdList) ? JSON.parseArray(bannerIdList, Long.class) : null;
		}catch(Exception e){
			return JsonResult.fail("轮播图id数组参数错误");
		}
		
		return bannerManage.queryBannerList(name, companyName, linkType, bannerIdList_,belongPage,pageNo, pageSize,
				companyId, enabled, belongPageType,platformId,companyType);
	}

	@RequestMapping(value = "/queryBannerTemplates")
	@ResponseBody
	public JsonResult<List<BannerImgPO>> queryBannerTemplate(String title,Integer type,Integer pageNo,Integer pageSize) {

		BannerImgPO po = new BannerImgPO();
		if(type!=null) {
			po.setType(type);
		}
		if(title!=null && title.length()>1) {
			po.setTitle(title);
		}
		Pagination page = new Pagination();
		if(pageNo==null) {
			page.setPageNo(1);
		}else {
			page.setPageNo(pageNo);
		}
		if(pageSize==null) {
			page.setPageSize(20);
		}else {
			page.setPageSize(pageSize);
		}
		if(pageSize==null && pageNo==null) {
			page.setPageNo(1);
			page.setPageSize(1000);
		}
		return JsonResult.success(bannerImgReadDao.findAll(po, page));
	}
	

	@RequestMapping(value = "/saveBannerTemplate")
	@ResponseBody
	public JsonResult saveBannerTemplate(BannerImgPO po) {
		po.setId(null);
		try {
			if(po.getType()==null) {
				po.setType(1);
			}
			bannerImgWriteDao.insert(po);
			return JsonResult.success();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsonResult.fail("保存Banner模板图片失败-请确保标题唯一性");
	}

	@RequestMapping(value = "/deleteBannerTemplate")
	@ResponseBody
	public JsonResult deleteBannerTemplate(BannerImgPO po) {
		try {
			bannerImgWriteDao.delete(po);
			return JsonResult.success();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsonResult.fail("保存Banner模板图片失败");
	}
	@RequestMapping(value = "/queryCBannerList")
	@ResponseBody
	public JsonResult<Map<String, Object>> queryCBannerList(
			String name,String companyName,Integer linkType,String bannerIdList,
			Integer belongPage,Integer pageNo,Integer pageSize, Long companyId, 
			Integer enabled, Integer belongPageType,Integer companyType, HttpServletRequest req) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		List<Long> bannerIdList_ = null;
		try{
			bannerIdList_ = EmptyUtil.isNotBlank(bannerIdList) ? JSON.parseArray(bannerIdList, Long.class) : null;
		}catch(Exception e){
			return JsonResult.fail("轮播图id数组参数错误");
		}
		
		return bannerManage.queryCompanyBannerList(name, companyName, linkType, bannerIdList_,belongPage,pageNo, pageSize,companyId,
				 enabled, belongPageType,platformId,companyType);
	}
	/**
	 * 查询轮播图详情
	 * @param bannerId
	 * @return
	 */
	@RequestMapping(value = "/bannerDetail")
	@ResponseBody
	public JsonResult<Map<String, Object>> bannerDetail(Long bannerId){
		return bannerManage.bannerDetail(bannerId);
	}
	
	/**
	 * 新建/编辑轮播图
	 * @param bannerId
	 * @param name
	 * @param companyIds
	 * @param imgUrl
	 * @param remark
	 * @param enabled
	 * @param linkType
	 * @param linkId
	 * @param linkUrl
	 * @param linkParam
	 * @param pcPageId PC端页面Id
	 * @param mobilePageId 移动端页面Id
	 * @return
	 */
	@RequestMapping(value = "/saveBanner")
	@ResponseBody
	public JsonResult<Map<String, Object>> saveBanner(
			Long bannerId,String name,String companyIds,Integer belongPage,
			String imgUrl,String remark,Integer enabled,Integer sort,Integer companyType,
			//链接信息
			Integer linkType,Long linkId,String linkUrl,String linkParam,
			String linkableButtonPageList,String extParam,Integer isDefault,
			HttpServletRequest req){
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}

		return bannerManage.saveBanner(
				bannerId, name, companyIds, imgUrl, remark, enabled,sort,companyType,
				linkType, linkId, linkUrl, linkParam,belongPage,platformId,
				linkableButtonPageList,extParam,isDefault);
	}
	@RequestMapping(value = "/saveCompanyBanner")
	@ResponseBody
	public JsonResult<Map<String, Object>> saveCompanyBanner(
			Long bannerId,String name,Integer belongPage,
			String imgUrl,String remark,Integer enabled,Integer sort,Integer companyType,
			//链接信息
			Integer linkType,Long linkId,String linkUrl,String linkParam,
			String linkableButtonPageList,String extParam,
			HttpServletRequest req){
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		if(RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getType().intValue()==3 && RuntimeContext.cacheUser().getCompanyId()!=null && RuntimeContext.cacheUser().getCompanyId()>0) {
		return bannerManage.saveBanner(
				bannerId, name, RuntimeContext.cacheUser().getCompanyId()+"", imgUrl, remark, enabled,sort,companyType,
				linkType, linkId, linkUrl, linkParam,belongPage,platformId,
				linkableButtonPageList,extParam,null);
		}
		return JsonResult.fail("没有操作权限");
	}

	@RequestMapping(value = "/saveBannerActive")
	@ResponseBody
	public JsonResult<Map<String, Object>> saveBannerActive(
			Long bannerId,String name,Integer belongPage,
			String imgUrl,String remark,Integer enabled,Integer sort,Integer companyType,
			//链接信息
			Integer linkType,Long linkId,String linkUrl,String linkParam,
			String linkableButtonPageList,String extParam,
			HttpServletRequest req){
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		if(RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getType().intValue()==3 && RuntimeContext.cacheUser().getCompanyId()!=null && RuntimeContext.cacheUser().getCompanyId()>0) {
		return bannerManage.saveBanner(
				bannerId, name, RuntimeContext.cacheUser().getCompanyId()+"", imgUrl, remark, enabled,sort,companyType,
				linkType, linkId, linkUrl, linkParam,belongPage,platformId,
				linkableButtonPageList,extParam,null);
		}
		return JsonResult.fail("没有操作权限");
	}
	/**
	 * 查询我的体检轮播图列表
	 * @param vo
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/queryBannerListByType")
	@ResponseBody
	public JsonResult<Map<String, Object>> queryBannerListByType(Integer belongPage,HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		String clientIdStr = req.getHeader("clientId");		
		Long platformId = null;
		Long clientId = null;		
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		if(EmptyUtil.isNotEmpty(clientIdStr)){
			clientId = Long.valueOf(clientIdStr);
		}
		CacheUser cacheUser = this.getCacheUser();
		return bannerManage.queryBannerListByType(belongPage,cacheUser.getCompanyId(),platformId, clientId);
	}
	
	/**
	 * 重置banner状态(显示/不显示)
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/resetBannerStatusById")
	@ResponseBody
	public JsonResult<Integer> resetBannerStatusById(Long id,HttpServletRequest req) {
		if (EmptyUtil.isEmpty(id)) 
			return JsonResult.fail("id不能为空");
		CacheUser cacheUser = this.getCacheUser();
		
		BannerDTO dto = new BannerDTO();
		dto.setId(id);
		return bannerManage.resetBannerStatusById(dto);
	}
	
	/**
	 * 通过轮播图id查询公司信息
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping("queryCompanyListByBannerId")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> queryCompanyListByBannerId(Long id, HttpServletRequest req) {
		if (id == null)
			return JsonResult.fail("id不能为空");
		
		BannerDTO dto = new BannerDTO();
		dto.setId(id);
		return bannerManage.queryCompanyListByBannerId(dto);
	}
	
	/**
	 * web商城查询顶部轮播图
	 * @param req
	 * @return
	 */
	@RequestMapping("queryTopBannerList")
	@ResponseBody
	public JsonResult<List<Map<String,Object>>> queryCompanyListByBannerId(HttpServletRequest req) {
		CacheUser cacheUser = getCacheUser();
		BannerDTO dto = new BannerDTO();
		dto.setBelongPage(3);
		dto.setEnabled(1);
		
		return null;
	}
	@RequestMapping("up")
	@ResponseBody
	public JsonResult<Integer> up(Long bannerId,Integer enabled) {
		BannerPO po = new BannerPO();
		po.setId(bannerId);
		BannerPO porslt = bannerReadDAO.findById(po);
		porslt.setEnabled(enabled==null?0:(enabled>0?1:0));
		return JsonResult.success(bannerWriteDAO.update(porslt));
	}
	@RequestMapping("upC")
	@ResponseBody
	public JsonResult<Integer> upC(Long bannerId,Integer enabled) {
		if(RuntimeContext.cacheUser().getType().intValue()==3 || RuntimeContext.cacheUser().getType().intValue()==4) {
			
		}else {
			return JsonResult.fail("接口调用错误");
		}
		List<Long> companyIdList=new ArrayList<>();
		Long companyId = RuntimeContext.cacheUser().getCompanyId();
		companyIdList.add(companyId);
		BannerCompanyPO po = new BannerCompanyPO();
		po.setBannerId(bannerId);
		po.setCompanyId(companyId);
		po.setUp((enabled!=null && enabled>0)?1:0);
		return JsonResult.success(bannerCompanyWriteDAO.updown(po));
	}

		
}
	