package com.egeo.components.product.controller.api;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CategoryTreeNodeRelationManage;
import com.egeo.components.product.vo.CategoryTreeNodeRelationVO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/categoryTreeNodeRelation")
public class CategoryTreeNodeRelationAction extends BaseSpringController {
	
	@Resource(name="categoryTreeNodeRelation")
	private CategoryTreeNodeRelationManage categoryTreeNodeRelationManage;
	

	
	// 业务方法：新增类目聚合
	@RequestMapping(value = "/addCategoryTreeNodeRelation")
	@ResponseBody
	public JsonResult<String> addCategoryTreeNodeRelation(CategoryTreeNodeRelationVO categoryTreeNodeRelationVO,HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			categoryTreeNodeRelationVO.setPlatformId(platformId);
		}
		
		if(EmptyUtil.isEmpty(categoryTreeNodeRelationVO.getBackTreeNodeId()) ||EmptyUtil.isEmpty(categoryTreeNodeRelationVO.getFrontTreeNodeId())){
			return JsonResult.fail("nodeid不能为空");
		}
		logger.info("新增类目聚合(聚合节点)：后台类目树节点id = "+categoryTreeNodeRelationVO.getBackTreeNodeId()+"前台台类目树节点id = " + categoryTreeNodeRelationVO.getFrontTreeNodeId());
		String rt = categoryTreeNodeRelationManage.addCategoryTreeNodeRelation(categoryTreeNodeRelationVO,req);

		return JsonResult.success(rt);
	}
	
	// 业务方法：删除类目聚合
	@RequestMapping(value = "/deleteCategoryTreeNodeRelation")
	@ResponseBody
	public JsonResult<String> deleteCategoryTreeNodeRelation(CategoryTreeNodeRelationVO categoryTreeNodeRelationVO) {
		
		if(EmptyUtil.isEmpty(categoryTreeNodeRelationVO.getBackTreeNodeId()) ||EmptyUtil.isEmpty(categoryTreeNodeRelationVO.getFrontTreeNodeId())){
			return JsonResult.fail("nodeid不能为空");
		}
		logger.info("删除类目聚合(聚合节点)：后台类目树节点id = "+categoryTreeNodeRelationVO.getBackTreeNodeId()+"前台台类目树节点id = " + categoryTreeNodeRelationVO.getFrontTreeNodeId());
		String rt = categoryTreeNodeRelationManage.deleteCategoryTreeNodeRelation(categoryTreeNodeRelationVO);

		return JsonResult.success(rt);
	}	
	
}
	