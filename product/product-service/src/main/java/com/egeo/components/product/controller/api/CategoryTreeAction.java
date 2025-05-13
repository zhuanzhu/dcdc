package com.egeo.components.product.controller.api;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.config.RuntimeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.business.CategoryTreeManage;
import com.egeo.components.product.vo.CategoryTreeVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/product/categoryTree")
public class CategoryTreeAction extends BaseSpringController {

	@Resource(name = "categoryTree")
	private CategoryTreeManage categoryTreeManage;
	@Autowired
	private CompanyClient companyClient;
	// 业务方法：新增类目树
	@RequestMapping(value = "/addCategoryTree")
	@ResponseBody
	public JsonResult<String> addCategoryTree(CategoryTreeVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		
		// 设类目树类型默认为前台类目树
		if (vo.getType() == null)
			vo.setType(2);
		
		// 设web客户端启用为停用
		if(EmptyUtil.isEmpty(vo.getWebStart()))
			vo.setWebStart(0);
		
		// 验证商家后台类目树信息
		if (vo.getType().intValue() == 1) {// 判断是否为后台类目树
			CategoryTreeVO categoryTreeVO = new CategoryTreeVO();
			categoryTreeVO.setType(vo.getType());
			List<CategoryTreeVO> lstTree = categoryTreeManage.queryAllCategoryTreeByParam(categoryTreeVO);
			if (lstTree.size() > 0) {
				return JsonResult.fail("商家后台类目树只能存在一个");
			}
		}else {
			if(Objects.nonNull(RuntimeContext.cacheUser()) && Objects.nonNull(RuntimeContext.cacheUser().getType())) {
				vo.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				if(Objects.equals(RuntimeContext.cacheUser().getType().intValue(),4)
						|| Objects.equals(RuntimeContext.cacheUser().getType().intValue(),3)) {
					if (Objects.nonNull(vo.getCompanyId()) && Objects.equals(vo.getCompanyId(),RuntimeContext.cacheUser().getCompanyId())){
						return JsonResult.fail("企业用户只能编辑本企业配置");
					}
					vo.setCompanyId(RuntimeContext.cacheUser().getCompanyId());
					vo.setIsDefault(0);
				}
			}else {
				return new JsonResult().fail("403:请重新登录");
			}
			if(EmptyUtil.isEmpty(vo.getCompanyId()) && !(Objects.equals(vo.getIsDefault(), 1))){
				return JsonResult.fail("类目树所属公司不能为空");
			}
			if (Objects.equals(vo.getIsDefault(),1)){
				vo.setCompanyId(null);
			}
		}

		try {
			logger.info("新增类目树：" + ",name=" + vo.getName() + ",type=" + vo.getType());
			if(EmptyUtil.isEmpty(vo.getName()))
				return JsonResult.fail("类目树名称不能为空");
			if(vo.getName().length() > 10)
				return JsonResult.fail("类目树名称不能超过10个字");
			if(EmptyUtil.isNotEmpty(vo.getContent())){
				if(vo.getContent().length() > 100){
					return JsonResult.fail("类目树备注不能超过100个字");
				}
			}
			if(EmptyUtil.isEmpty(vo.getSeriesType()))
				return JsonResult.fail("类目树类型不能为空");
			if(EmptyUtil.isEmpty(vo.getCompanyType()))
				return JsonResult.fail("类目树所属公司不能为空");
			if(EmptyUtil.isEmpty(vo.getCategoryTreeTemplateId()))
				return JsonResult.fail("类目树模板不能为空");
			//如果是否启用为空则默认赋值为停用
			if(EmptyUtil.isEmpty(vo.getStartUsing())){
				vo.setStartUsing(0);
			}
			String rt = categoryTreeManage.addCategoryTree(vo, req);
			return JsonResult.success(rt);

		} catch (Exception e) {
			logger.error("新增类目树异常！", e);
			return JsonResult.fail("新增类目树失败:" + e.getMessage());
		}
	}
	
	/**
	 * 根据类目树id修改类目树信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateCategoryTree")
	@ResponseBody
	public JsonResult<Boolean> updateCategoryTree(CategoryTreeVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}

		/*if (vo.getType() == null) {
			return JsonResult.fail("请选择类目树类型");
		}
		// 验证商家后台类目树信息
		if (vo.getType().intValue() == 1) {// 判断是否为后台类目树
			CategoryTreeVO categoryTreeVO = new CategoryTreeVO();
			categoryTreeVO.setType(vo.getType());
			List<CategoryTreeVO> lstTree = categoryTreeManage.queryAllCategoryTreeByParam(categoryTreeVO);
			if (lstTree.size() > 0) {
				return JsonResult.fail("商家后台类目树只能存在一个");
			}
		}*/

		try {
			logger.info("修改类目树：" + ",name=" + vo.getName() + ",type=" + vo.getType());
			if(EmptyUtil.isEmpty(vo.getName()))
				return JsonResult.fail("类目树名称不能为空");
			if(vo.getName().length() > 10)
				return JsonResult.fail("类目树名称不能超过10个字");
			if(EmptyUtil.isNotEmpty(vo.getContent())){
				if(vo.getContent().length() > 100){
					return JsonResult.fail("类目树备注不能超过100个字");
				}
			}
			if(EmptyUtil.isEmpty(vo.getSeriesType()))
				return JsonResult.fail("类目树类型不能为空");
			if(EmptyUtil.isEmpty(vo.getCompanyType()))
				return JsonResult.fail("类目树所属公司不能为空");
			if(Objects.nonNull(RuntimeContext.cacheUser()) && Objects.nonNull(RuntimeContext.cacheUser().getType())) {
				vo.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				if(Objects.equals(RuntimeContext.cacheUser().getType().intValue(),4)
						|| Objects.equals(RuntimeContext.cacheUser().getType().intValue(),3)) {
					if (Objects.nonNull(vo.getCompanyId()) && Objects.equals(vo.getCompanyId(),RuntimeContext.cacheUser().getCompanyId())){
						return JsonResult.fail("企业用户只能编辑本企业配置");
					}
					vo.setCompanyId(RuntimeContext.cacheUser().getCompanyId());
					vo.setIsDefault(0);
				}
			}else {
				return new JsonResult().fail("403:请重新登录");
			}
			if(EmptyUtil.isEmpty(vo.getCompanyId()) && !(Objects.equals(vo.getIsDefault(), 1))){
				return JsonResult.fail("类目树所属公司不能为空");
			}
			if (Objects.equals(vo.getIsDefault(),1)){
				vo.setCompanyId(null);
			}
			if(EmptyUtil.isEmpty(vo.getCategoryTreeTemplateId()))
				return JsonResult.fail("类目树模板不能为空");
			vo.setIsDefault(Objects.isNull(vo.getIsDefault())?0: vo.getIsDefault());
			boolean rt = categoryTreeManage.updateCategoryTreeWithTx(vo);
			return JsonResult.success(rt);

		} catch (Exception e) {
			logger.error("新增类目树异常！", e);
			return JsonResult.fail("新增类目树失败:" + e.getMessage());
		}
	}

	// 业务方法：查询所有类目树
	@RequestMapping(value = "/queryCategoryTree")
	@ResponseBody
	public JsonResult<List<CategoryTreeVO>> queryCategoryTree(HttpServletRequest req) {

		String str = req.getHeader("platformId");
		Long platformId = null;
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}

		try {
			logger.info("查询所有类目树：" + ",platformId=" + platformId);

			List<CategoryTreeVO> rt = categoryTreeManage.queryCategoryTree(platformId);
			return JsonResult.success(rt);

		} catch (Exception e) {
			logger.error("查询类目树异常！", e);
			return JsonResult.fail("查询类目树失败:" + e.getMessage());
		}
	}

	// 业务方法：查询类目树类型
	@RequestMapping(value = "/queryCategoryTreeByType")
	@ResponseBody
	public JsonResult<List<CategoryTreeVO>> queryCategoryTreeByType(HttpServletRequest req, Integer type) {

		CacheUser userCache = this.getCacheUser();
		Long platformId = userCache.getPlatformId();

		try {
			logger.info("查询类目树类型" + type + "类目树：" + ",platformId=" + platformId);

			List<CategoryTreeVO> rt = categoryTreeManage.queryCategoryTreeByType(platformId, type);
			return JsonResult.success(rt);

		} catch (Exception e) {
			logger.error("查询类目树类型异常！", e);
			return JsonResult.fail("查询类目树类型失败:" + e.getMessage());
		}
	}

	/**
	 * 查询前台类目树（目前启用只可能有一棵）
	 */
	@RequestMapping(value = "/findCategoryByType")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCategoryByType(HttpServletRequest req) {
		try {
			logger.info("查询前台类目树");
			String platformIdStr = req.getHeader("platformId");
			Long platformId = null;
			if (EmptyUtil.isNotEmpty(platformIdStr)) {
				platformId = Long.valueOf(platformIdStr);
			} else {
				return JsonResult.fail("平台信息为空");
			}
			
			String str = req.getHeader("clientId");
			Long clientId = null;
			if (EmptyUtil.isNotEmpty(str)) {
				clientId = Long.valueOf(str);
			}
			CacheUser cacheUser = this.getCacheUser(false);
			Map<String, Object> map = categoryTreeManage.findCategoryByType(cacheUser.getCompanyId(),cacheUser.getEnterpriseId(),clientId, platformId,1);
			logger.info("查询前台类目树 返回信息：" + JSON.toJSONString(map));
			return JsonResult.success(map);
		} catch (Exception e) {
			logger.error("查询前台类目树异常！", e);
			return JsonResult.fail("查询前台类目树失败:" + e.getMessage());
		}
	}
	
	/**
	 * 分页查询前台类目树
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCategoryTreeOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCategoryTreeOfPage(CategoryTreeVO categoryTreeVO,Pagination page,HttpServletRequest req) {

		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryTreeVO.setPlatformId(platformId);
		}

		try {
			logger.info("查询所有类目树：" + ",platformId=" + categoryTreeVO.getPlatformId());
			//1是后台类目树、2是前台类目树
			categoryTreeVO.setType(2);
			if(EmptyUtil.isNotBlank(categoryTreeVO.getCompanyName())) {
				CompanyDTO companyDTO = new CompanyDTO();
				companyDTO.setCompanyName(categoryTreeVO.getCompanyName());
				List<CompanyDTO> companyDTOs = companyClient.findCompanyAllByFuzzyName(companyDTO);
				if (EmptyUtil.isEmpty(companyDTOs)){
					return new JsonResult().fail("公司【"+categoryTreeVO.getCompanyName()+"】不存在");
				}
				categoryTreeVO.setCompanyId(companyDTOs.get(0).getId());
			}
			if(Objects.nonNull(RuntimeContext.cacheUser()) && Objects.nonNull(RuntimeContext.cacheUser().getType())) {
				if(Objects.equals(RuntimeContext.cacheUser().getType().intValue(),3)
						|| Objects.equals(RuntimeContext.cacheUser().getType().intValue(),4)) {
					if (EmptyUtil.isNotBlank(categoryTreeVO.getCompanyName())
							&&Objects.nonNull(categoryTreeVO.getCompanyId())
							&& !Objects.equals(categoryTreeVO.getCompanyId(),RuntimeContext.cacheUser().getCompanyId())){
						return new JsonResult().fail("企业用户只能查询本企业公司");
					}
					categoryTreeVO.setCompanyId(RuntimeContext.cacheUser().getCompanyId());
				}else if(Objects.equals(RuntimeContext.cacheUser().getType().intValue(),2)) {
					//获取所有的公司信息
					categoryTreeVO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				}
			}else {
				return new JsonResult().fail("403");
			}
			PageResult<Map<String, Object>> rt = categoryTreeManage.findCategoryTreeOfPage(categoryTreeVO,page);
			return JsonResult.success(rt);

		} catch (Exception e) {
			logger.error("查询类目树异常！", e);
			return JsonResult.fail("查询类目树失败:" + e.getMessage());
		}
	}
	/**
	 * 根据类目树id将类目树设为启用
	 * @param categoryTreeId 类目树id
	 * @param companyType 类目树所属公司类型
	 */
	@RequestMapping(value = "categoryTreeStartUsing")
	@ResponseBody
	public JsonResult<Boolean> categoryTreeStartUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, HttpServletRequest request){
		logger.info("根据类目树id将类目树设为启用,类目树id:{},类目树所属公司类型:{},类目树客户端：{}",categoryTreeId,companyType,clientType);
		String str = request.getHeader("platformId");
		Long platformId = null;
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		} else {
			return JsonResult.fail("platformId为空");
		}
		boolean rt = categoryTreeManage.categoryTreeStartUsingWithTx(categoryTreeId,companyType,clientType, platformId);
		return JsonResult.success(rt);
	}

	/**
	 * 根据类目树id将类目树设为启用
	 * @param categoryTreeId 类目树id
	 * @param companyType 类目树所属公司类型
	 */
	@RequestMapping(value = "categoryTreeStopUsing")
	@ResponseBody
	public JsonResult<Boolean> categoryTreeStopUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, HttpServletRequest request){
		logger.info("根据类目树id将类目树设为启用,类目树id:{},类目树所属公司类型:{},类目树客户端：{}",categoryTreeId,companyType,clientType);
		boolean rt = categoryTreeManage.categoryTreeStopUsingWithTx(categoryTreeId);
		return JsonResult.success(rt);
	}
	
	/**
	 * 根据类目树id查询类目树信息
	 */
	@RequestMapping(value = "findByCategoryTreeId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findByCategoryTreeId(Long categoryTreeId){
		logger.info("根据类目树id查询类目树信息,类目树id:"+categoryTreeId);
		Map<String, Object> rt = categoryTreeManage.findByCategoryTreeId(categoryTreeId);
		return JsonResult.success(rt);
	}
	/**
	 * 查询所有类目树信息
	 */
	@RequestMapping(value = "findCategoryTreeAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findCategoryTreeAll(HttpServletRequest req){
		String str = req.getHeader("platformId");
		Long platformId = null;
		if (EmptyUtil.isNotEmpty(str)) {
			platformId = Long.valueOf(str);
		}
		logger.info("查询所有类目树信息,平台id:"+platformId);
		List<Map<String, Object>> rt = categoryTreeManage.findCategoryTreeAll(platformId);
		return JsonResult.success(rt);
	}

	/**
	 * 根据type 查询目录树,1:后天目录树,2:前台目录树
	 * @param req
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/queryCategoryTreeVoByType")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> queryCategoryTreeVoByType(HttpServletRequest req, Integer type) {
		try {
			Long platformId = null;
			String str = req.getHeader("platformId");
			if (EmptyUtil.isNotEmpty(str)) {
				platformId = Long.valueOf(str);
			}
			List<Map<String, Object>> rt = categoryTreeManage.queryCategoryTreeVoByType(platformId, type);
			return JsonResult.success(rt);
		} catch (Exception e) {
			logger.error("查询类目树类型异常！", e);
			return JsonResult.fail("查询类目树类型失败:" + e.getMessage());
		}
	}


}
