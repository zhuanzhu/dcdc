package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.StandardUnitCombinationTagManage;
import com.egeo.components.product.converter.StandardUnitCombinationTagConverter;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.components.product.vo.StandardUnitCombinationTagVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitCombinationTag")
public class StandardUnitCombinationTagAction extends BaseSpringController {
	
	@Resource(name="standardUnitCombinationTag")
	private StandardUnitCombinationTagManage standardUnitCombinationTagManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCombinationTagById")
	@ResponseBody
	public JsonResult<StandardUnitCombinationTagVO> findStandardUnitCombinationTagById(Long id ) {
		
		StandardUnitCombinationTagDTO dto = new StandardUnitCombinationTagDTO();
		dto.setId(id);
		StandardUnitCombinationTagDTO rt = standardUnitCombinationTagManage.findStandardUnitCombinationTagById(dto);		
		return JsonResult.success(StandardUnitCombinationTagConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCombinationTagAll")
	@ResponseBody
	public JsonResult<List<StandardUnitCombinationTagVO>> findStandardUnitCombinationTagAll(StandardUnitCombinationTagVO vo,HttpServletRequest req ) {
		StandardUnitCombinationTagDTO dto = StandardUnitCombinationTagConverter.toDTO(vo);
		List<StandardUnitCombinationTagDTO> rt = standardUnitCombinationTagManage.findStandardUnitCombinationTagAll(dto);	
		return JsonResult.success(StandardUnitCombinationTagConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitCombinationTagOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitCombinationTagVO>> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitCombinationTagDTO dto = StandardUnitCombinationTagConverter.toDTO(vo);
		PageResult<StandardUnitCombinationTagDTO> rt = standardUnitCombinationTagManage.findStandardUnitCombinationTagOfPage(dto, page);
        List<StandardUnitCombinationTagVO> list = StandardUnitCombinationTagConverter.toVO(rt.getList());
        PageResult<StandardUnitCombinationTagVO> result = new PageResult<StandardUnitCombinationTagVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitCombinationTagWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagVO vo,HttpServletRequest req ) {
		StandardUnitCombinationTagDTO dto = StandardUnitCombinationTagConverter.toDTO(vo);
		Long rt = standardUnitCombinationTagManage.insertStandardUnitCombinationTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitCombinationTagByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitCombinationTagByIdWithTx(StandardUnitCombinationTagVO vo,HttpServletRequest req ) {
		StandardUnitCombinationTagDTO dto = StandardUnitCombinationTagConverter.toDTO(vo);
		int rt = standardUnitCombinationTagManage.updateStandardUnitCombinationTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitCombinationTagWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagVO vo,HttpServletRequest req ) {
		StandardUnitCombinationTagDTO dto = StandardUnitCombinationTagConverter.toDTO(vo);
		int rt = standardUnitCombinationTagManage.deleteStandardUnitCombinationTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@RequestMapping(value = "/findTagByStandardUnitCombinationId")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findTagByStandardUnitCombinationId(Long standardUnitCombinationId ) {
		logger.info("根据su组合id查询su组合标签信息,su组合id:" + standardUnitCombinationId);
		List<Map<String, Object>> rt = standardUnitCombinationTagManage.findTagByStandardUnitCombinationId(standardUnitCombinationId);	
		return JsonResult.success(rt);
	}	
	
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@RequestMapping(value = "/saveStandardUnitCombinationTag")
	@ResponseBody
	public JsonResult<Integer> saveStandardUnitCombinationTagWithTx (Long standardUnitCombinationId,String tagIds,Integer sortType,HttpServletRequest req ) {
		logger.info("批量保存su组合id与标签关系信息,su组合id:" + standardUnitCombinationId);
		CacheUser userCache = this.getCacheUser();
		if(EmptyUtil.isEmpty(sortType)){
			throw new BusinessException("请选择排序方式");
		}
		//用户id
		Long userId=userCache.getId();
		String name = userCache.getName();
		StandardUnitCombinationDTO dto = new StandardUnitCombinationDTO();
		dto.setId(standardUnitCombinationId);
		dto.setSortType(sortType);
		dto.setUpdateUserid(userId);
		dto.setUpdateUsername(name);
		List<Long> tagIdList = new ArrayList<>(JSONArray.parseArray(tagIds, Long.class));
		Integer rt = standardUnitCombinationTagManage.saveStandardUnitCombinationTagWithTx(dto,tagIdList);	
		return JsonResult.success(rt);
	}	
		
}
	