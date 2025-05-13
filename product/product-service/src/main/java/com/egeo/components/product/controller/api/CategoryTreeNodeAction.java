package com.egeo.components.product.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.CategoryTreeManage;
import com.egeo.components.product.business.CategoryTreeNodeManage;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.vo.CategoryTreeNodeVO;
import com.egeo.components.product.vo.CategoryTreeVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/product/categoryTreeNode")
public class CategoryTreeNodeAction extends BaseSpringController {

	@Resource(name = "categoryTreeNode")
	private CategoryTreeNodeManage categoryTreeNodeManage;

	@Resource(name = "categoryTree")
	private CategoryTreeManage categoryTreeManage;

	// 业务方法：类目树查询
	@RequestMapping(value = "/queryAllCategoryTreeNode")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> queryCategoryTree(Long categoryTreeId, HttpServletRequest req) {
		logger.info("查询类目树数id为：{} 树下的所有节点数据",categoryTreeId);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str)) {
			return JsonResult.fail("平台id不能为空");
		}
		Long platformId = Long.valueOf(str);
		List<Map<String, Object>> lstTree = categoryTreeNodeManage.getCategoryTree(categoryTreeId, platformId);

		return JsonResult.success(lstTree);
	}

	// 业务方法：类目树查询
	@RequestMapping(value = "/queryAllCategoryTreeNodeByType")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> queryAllCategoryTreeNodeByType(Integer type,
			HttpServletRequest req) {
		logger.info("查询类目树数type为：{} 树下的所有节点数据",type);
		long time1 = System.currentTimeMillis();
		List<CategoryTreeVO> rt = null;
		Long platformId = null;
		try {
			logger.info("查询类目树类型 {} 类目树,platformId= {}",type,platformId);

			rt = categoryTreeManage.queryCategoryTreeByType(platformId, type);

		} catch (Exception e) {
			logger.error("查询类目树类型异常！", e);
			return JsonResult.fail("查询类目树类型失败:" + e.getMessage());
		}
		if (EmptyUtil.isEmpty(rt)) {
			return success("没有建立类目树", null);
		}

		List<Map<String, Object>> lstTree = new ArrayList<Map<String, Object>>();

		logger.info("准备开始查询类目数:size="+rt.size());
		for (int i = 0; i < rt.size(); i++) {
			Long categoryTreeId = rt.get(i).getId();
			List<Map<String, Object>> list = categoryTreeNodeManage.getCategoryTree(categoryTreeId, platformId);
			logger.info("类目树结构数据"+list.size());
			Map<String, Object> ctnVo = new HashMap<>();
			ctnVo.put("categoryTreeId",categoryTreeId);
			ctnVo.put("list",list);
			lstTree.add(ctnVo);
		}
		long time = System.currentTimeMillis();
		logger.info("耗时:"+(time-time1));
		logger.info("类目数查询完成");
		return JsonResult.success(lstTree);
	}

	// 业务方法：新增类目节点
	@RequestMapping(value = "/addCategoryTreeNode")
	@ResponseBody
	public JsonResult<String> addCategoryTreeNode(CategoryTreeNodeVO categoryTreeNodeVO,Long parentId, String listSort,String tagIds,
			HttpServletRequest req) {
		if (EmptyUtil.isEmpty(categoryTreeNodeVO)) {
			return JsonResult.fail("请输入要新增的节点信息");
		}
		if (EmptyUtil.isEmpty(categoryTreeNodeVO.getName())) {
			return JsonResult.fail("类目名称不能为空");
		}
		if (!StringUtils.isNotFigure(listSort)) {
			return JsonResult.fail("类目排序必须为数字");
		}

		/*if (EmptyUtil.isEmpty(categoryTreeNodeVO.getSerialNumber())) {
			return JsonResult.fail("类目编号不能为空");
		} else {
			if (!StringUtils.isNotFigure(categoryTreeNodeVO.getSerialNumber())) {
				return JsonResult.fail("类目编号必须为数字");
			}
		}*/
		if (EmptyUtil.isEmpty(categoryTreeNodeVO.getSerialNumber()) || !StringUtils.isNotFigure(categoryTreeNodeVO.getSerialNumber())) {
			return JsonResult.fail("新增类目无效");
		}

		if(categoryTreeNodeVO.getName().length() > 30){
			throw new BusinessException("类目名称长度不得超过30位");
		}
		String pids = null;
		if (EmptyUtil.isNotEmpty(categoryTreeNodeVO.getPids())) {
			pids = categoryTreeNodeVO.getPids().substring(1, categoryTreeNodeVO.getPids().length() - 1);
		}

		if (EmptyUtil.isEmpty(pids)) {
			pids = "0";
		}
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str)) {
			return JsonResult.fail("平台id不能为空");
		}
		Long platformId = Long.valueOf(str);
		categoryTreeNodeVO.setPlatformId(platformId);
		List<String> list = new ArrayList<>();
		if(pids != null){
			list = Arrays.asList(pids.split(","));
		}
		
		long parentNodeId = Long.parseLong(list.get(list.size() - 1));
		categoryTreeNodeVO.setParentId(parentNodeId);
		categoryTreeNodeVO.setPids(pids);
		List<Long> tagIdList = new ArrayList<>();
		if(EmptyUtil.isNotEmpty(tagIds)){
			tagIdList = new ArrayList<>(JSONArray.parseArray(tagIds, Long.class));
		}
		
		String rt = categoryTreeNodeManage.addCategoryTreeNode(categoryTreeNodeVO, listSort,tagIdList, req);
		return JsonResult.success(rt);

	}

	// 业务方法：根据类目id查看类目节点信息以及和该节点关联的类目信息
	@RequestMapping(value = "/findCategoryTreeNodeInofByNodeId")
	@ResponseBody
	public JsonResult<CategoryTreeNodeDTO> findCategoryTreeNodeInofByNodeId(String pids) {

		try {
			String nodeId = StringUtils.getArrayLastValue(pids);
			long ctnId = Long.parseLong(nodeId);
			CategoryTreeNodeDTO rt = categoryTreeNodeManage.findCategoryTreeNodeInofByNodeId(ctnId);
			return JsonResult.success(rt);
		} catch (Exception e) {
			logger.error("查询节点信息失败！", e);
			return JsonResult.fail("查询节点信息失败:" + e.getMessage());
		}
	}

	// 业务方法：根据id查询类目节点信息
	@RequestMapping(value = "findById")
	@ResponseBody
	public JsonResult<CategoryTreeNodeDTO> findById(CategoryTreeNodeVO categoryTreeNodeVO, HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryTreeNodeVO.setPlatformId(platformId);
		}
		JsonResult<CategoryTreeNodeDTO> result = new JsonResult<CategoryTreeNodeDTO>();
		logger.info("根据id查询类目节点信息，id为：{}",categoryTreeNodeVO.getId());
		CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeManage.findById(categoryTreeNodeVO);
		result.setData(categoryTreeNodeDTO);
		return result;
	}

	// 业务方法：类目树条件查询
	@RequestMapping(value = "/findAll")
	@ResponseBody
	public JsonResult<List<CategoryTreeNodeDTO>> findAll(CategoryTreeNodeVO categoryTreeNodeVO,
			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryTreeNodeVO.setPlatformId(platformId);
		}
		logger.info("查询类目树数id为：{} 树下的所有节点数据",categoryTreeNodeVO.getCategoryTreeId());
		JsonResult<List<CategoryTreeNodeDTO>> result = new JsonResult<List<CategoryTreeNodeDTO>>();
		List<CategoryTreeNodeDTO> lstTree = categoryTreeNodeManage.findAll(categoryTreeNodeVO);
		result.setData(lstTree);
		return result;
	}

	/**
	 * 根据类目节点id查询子类目节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
	@RequestMapping(value = "/findSeedCategoryTreeNodeAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findSeedCategoryTreeNodeAll(Long categoryTreeNodeId) {
		if (EmptyUtil.isEmpty(categoryTreeNodeId)) {
			return JsonResult.fail("类目节点id不能为空");
		}
		logger.info("根据类目节点id：{} 查询其所有子类目节点数据",categoryTreeNodeId);
		List<Map<String, Object>> lstTree = categoryTreeNodeManage.findSeedCategoryTreeNodeAll(categoryTreeNodeId);
		return JsonResult.success(lstTree);
	}

	/**
	 * 添加前台类目节点信息
	 * 
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/saveCategoryTreeNode")
	@ResponseBody
	public JsonResult<Long> saveCategoryTreeNode(CategoryTreeNodeVO categoryTreeNodeVO, String listSort,
			String linkableButtonPageList,String extParam,
			HttpServletRequest req) {
		logger.info("添加前台类目节点信息: {} ",categoryTreeNodeVO);
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryTreeNodeVO.setPlatformId(platformId);
		}

		String pids = null;
		if (EmptyUtil.isNotEmpty(categoryTreeNodeVO.getPids())) {
			pids = categoryTreeNodeVO.getPids().substring(1, categoryTreeNodeVO.getPids().length() - 1);
		}

		if (EmptyUtil.isEmpty(pids)) {
			pids = "0";
		}
		List<String> list = new ArrayList<>();
		if(pids != null){
			list = Arrays.asList(pids.split(","));
		}
		long parentNodeId = Long.parseLong(list.get(list.size() - 1));
		categoryTreeNodeVO.setParentId(parentNodeId);
		categoryTreeNodeVO.setPids(pids);
		
		// 验证数据信息
		verificationCategoryTreeNode(categoryTreeNodeVO);
		Long categoryTreeNodeId = categoryTreeNodeManage.saveCategoryTreeNodeWithTx(categoryTreeNodeVO, listSort,linkableButtonPageList,extParam);
		return JsonResult.success(categoryTreeNodeId);
	}

	/**
	 * 验证数据信息
	 * 
	 * @param categoryTreeNodeVO
	 */
	private void verificationCategoryTreeNode(CategoryTreeNodeVO categoryTreeNodeVO) {
		//根据类目树id查询类目树信息
		CategoryTreeDTO categoryTreeDTO =  categoryTreeNodeManage.findCategoryTreeByCategoryTreeNodeId(categoryTreeNodeVO.getCategoryTreeId());
		if (EmptyUtil.isEmpty(categoryTreeNodeVO.getName())) {
			throw new BusinessException("类目名称不能为空");
		}
		if (categoryTreeNodeVO.getName().length() > 30) {
			throw new BusinessException("类目名称不超过30个字");
		}
		if (EmptyUtil.isNotEmpty(categoryTreeNodeVO.getDescription())) {
			if (categoryTreeNodeVO.getDescription().length() > 100) {
				throw new BusinessException("类目备注不超过100个字");
			}
		}

		if (EmptyUtil.isEmpty(categoryTreeNodeVO.getListSort())) {
			throw new BusinessException("类目排序不能为空");
		}
		if (EmptyUtil.isEmpty(categoryTreeNodeVO.getBannerImg())) {
			throw new BusinessException("Banner图片不能为空");
		}
		//类目树为一级类目才进行下面判断
		if(categoryTreeDTO.getSeriesType() == 1 && EmptyUtil.isEmpty(categoryTreeNodeVO.getParentId())){
			if (EmptyUtil.isEmpty(categoryTreeNodeVO.getLinkType())) {
				throw new BusinessException("跳转类型不能为空");
			}
			
			// 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
			switch (categoryTreeNodeVO.getLinkType()) {
			case 1:
				if (EmptyUtil.isEmpty(categoryTreeNodeVO.getLocalParamId())) {
					throw new BusinessException("配置参数不能为空");
				}
				break;
			case 2:
				if (EmptyUtil.isEmpty(categoryTreeNodeVO.getLinkUrl())) {
					throw new BusinessException("配置链接不能为空");
				}
				break;
			case 3:
				if (EmptyUtil.isEmpty(categoryTreeNodeVO.getLinkUrl())) {
					throw new BusinessException("配置链接不能为空");
				}
				break;
			case 4:
				if (EmptyUtil.isEmpty(categoryTreeNodeVO.getStandardUnitCombinationId())) {
					throw new BusinessException("商品分类不能为空");
				}
				break;
			case 5:
				if (EmptyUtil.isEmpty(categoryTreeNodeVO.getStandardUnitCombinationId())) {
					throw new BusinessException("配置商品不能为空");
				}
				break;
			case 6:
				break;

			default:
				throw new BusinessException("未定义跳转类型");
			}
		}
		
	}

	/**
	 * 根据类目节点id修改前台类目节点信息
	 * 
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateCategoryTreeNode")
	@ResponseBody
	public JsonResult<Boolean> updateCategoryTreeNode(CategoryTreeNodeVO categoryTreeNodeVO, String listSort,
			String linkableButtonPageList,String extParam,
			HttpServletRequest req) {
		logger.info("根据类目节点id修改前台类目节点信息,类目节点id: {}",categoryTreeNodeVO.getId());
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			categoryTreeNodeVO.setPlatformId(platformId);
		}

		String pids = null;
		if (EmptyUtil.isNotEmpty(categoryTreeNodeVO.getPids())) {
			pids = categoryTreeNodeVO.getPids().substring(1, categoryTreeNodeVO.getPids().length() - 1);
		}

		if (EmptyUtil.isEmpty(pids)) {
			pids = "0";
		}
		List<String> list = new ArrayList<>();
		if(pids != null){
			list = Arrays.asList(pids.split(","));
		}
		
		long parentNodeId = Long.parseLong(list.get(list.size() - 1));
		categoryTreeNodeVO.setParentId(parentNodeId);
		categoryTreeNodeVO.setPids(pids);
		
		// 验证数据信息
		verificationCategoryTreeNode(categoryTreeNodeVO);
		boolean rt = categoryTreeNodeManage.updateCategoryTreeNodeCategoryWithTx(categoryTreeNodeVO, listSort,
				linkableButtonPageList,extParam);
		return JsonResult.success(rt);
	}

	/**
	 * 根据前台类目节点id查询前台类目节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
	@RequestMapping(value = "/findByCategoryTreeNodeId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findByCategoryTreeNodeId(Long categoryTreeNodeId) {
		logger.info("根据前台类目节点id查询前台类目节点信息,类目节点id: {} ", categoryTreeNodeId);
		Map<String, Object> rt = categoryTreeNodeManage.findByCategoryTreeNodeId(categoryTreeNodeId);
		return JsonResult.success(rt);
	}

	/**
	 * 根据前台类目节点id删除前台类目节点及子节点信息
	 * @param categoryTreeNodeId
	 * @return
	 */
	@RequestMapping(value = "/delByCategoryTreeNodeId")
	@ResponseBody
	public JsonResult<Boolean> delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId) {
		logger.info("根据前台类目节点id删除前台类目节点及子节点信息,类目节点id: {}", categoryTreeNodeId);
		boolean rt = categoryTreeNodeManage.delByCategoryTreeNodeIdWithTx(categoryTreeNodeId);
		return JsonResult.success(rt);
	}

	/**
	 * 根据类目树id查询前台类目节点信息
	 * @param categoryTreeId
	 * @return
	 */
	@RequestMapping(value = "/findByCategoryTreeId")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findByCategoryTreeId(Long categoryTreeId) {
		logger.info("根据类目树id查询前台类目节点信息,类目树id: {}", categoryTreeId);
		List<Map<String, Object>> rt = categoryTreeNodeManage.findByCategoryTreeId(categoryTreeId);
		return JsonResult.success(rt);
	}
	
	/**
	 * 根据用户id查询web前台类目
	 */
	@RequestMapping(value = "/findWebCategoryByUserId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findWebCategoryByUserId(Pagination page,HttpServletRequest req) {
		try {
			CacheUser cacheUser = this.getCacheUser();
			logger.info("根据用户id查询web前台类目树,用户id={}",cacheUser.getId());
			String str = req.getHeader("clientId");
			Long clientId = null;
			if (EmptyUtil.isNotEmpty(str)) {
				clientId = Long.valueOf(str);
			}
			String platformIdStr = req.getHeader("platformId");
			Long platformId = null;
			if (EmptyUtil.isNotEmpty(platformIdStr)) {
				platformId = Long.valueOf(platformIdStr);
			} else {
				return JsonResult.fail("平台信息为空");
			}
			Map<String, Object> map = categoryTreeNodeManage.findWebCategoryByUserId(cacheUser.getCompanyId(),page,clientId,platformId);
			return JsonResult.success(map);
		} catch (Exception e) {
			logger.error("查询前台类目树异常！", e);
			return JsonResult.fail("查询前台类目树失败:" + e.getMessage());
		}
	}

	/**
	 * 根据type来查询目录树vo
	 * @param type
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryAllCategoryTreeNodeListVoByType")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> queryAllCategoryTreeNodeListVoByType(Integer type,
																				HttpServletRequest req) {
		logger.info("查询类目树数type为：{} 树下的所有节点数据",type);
		List<CategoryTreeVO> rt = null;
		Long platformId = null;
		try {
			logger.info("查询类目树类型 {} 类目树,platformId= {}",type,platformId);
			rt = categoryTreeManage.queryCategoryTreeByType(platformId, type);
		} catch (Exception e) {
			logger.error("查询类目树类型异常！", e);
			return JsonResult.fail("查询类目树类型失败:" + e.getMessage());
		}
		if (EmptyUtil.isEmpty(rt)) {
			return success("没有建立类目树", null);
		}
		List<Map<String, Object>> lstTree = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < rt.size(); i++) {
			Long categoryTreeId = rt.get(i).getId();
			List<Map<String, Object>> list = categoryTreeNodeManage.queryAllCategoryTreeNodeListVoByType(
					categoryTreeId, platformId);
			Map<String, Object> ctnVo = new HashMap<>();
			ctnVo.put("categoryTreeId",categoryTreeId);
			ctnVo.put("list",list);
			lstTree.add(ctnVo);
		}
		return JsonResult.success(lstTree);
	}

	/**
	 * 获取新的子类目编号
	 * @param categoryTreeId
	 * @param parentId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findMaxSubSerialNumber")
	@ResponseBody
	public JsonResult<String> findMaxSubSerialNumber(Long categoryTreeId, Long parentId, HttpServletRequest req) {
		logger.info("获取新的子类目编号,categoryTreeId:{},parentId:{}", new Object[]{categoryTreeId, parentId});

		if (EmptyUtil.isEmpty(categoryTreeId)) {
			return JsonResult.fail("类目树ID不能为空");
		}
		if (EmptyUtil.isEmpty(parentId)) {
			parentId = 0L;
		}
		String newSerialNum = categoryTreeNodeManage.findMaxSubSerialNumber(categoryTreeId, parentId);
		logger.info("获取新的子类目编号,newSerialNum:{}", newSerialNum);
		return JsonResult.success(newSerialNum);
	}
	
}
