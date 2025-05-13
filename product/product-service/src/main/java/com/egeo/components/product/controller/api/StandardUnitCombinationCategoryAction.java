package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.StandardUnitCombinationCategoryManage;
import com.egeo.components.product.converter.StandardUnitCombinationCategoryConverter;
import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.vo.StandardUnitCombinationCategoryVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/product/standardUnitCombinationCategory")
public class StandardUnitCombinationCategoryAction extends BaseSpringController {

    @Resource(name = "standardUnitCombinationCategory")
    private StandardUnitCombinationCategoryManage standardUnitCombinationCategoryManage;


    // 业务方法：
    @RequestMapping(value = "/findStandardUnitCombinationCategoryById")
    @ResponseBody
    public JsonResult<StandardUnitCombinationCategoryVO> findStandardUnitCombinationCategoryById(Long id) {

        StandardUnitCombinationCategoryDTO dto = new StandardUnitCombinationCategoryDTO();
        dto.setId(id);
        StandardUnitCombinationCategoryDTO rt = standardUnitCombinationCategoryManage.findStandardUnitCombinationCategoryById(dto);
        return JsonResult.success(StandardUnitCombinationCategoryConverter.toVO(rt));

    }


    // 业务方法：
    @RequestMapping(value = "/findStandardUnitCombinationCategoryAll")
    @ResponseBody
    public JsonResult<List<StandardUnitCombinationCategoryVO>> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryVO vo, HttpServletRequest req) {
        StandardUnitCombinationCategoryDTO dto = StandardUnitCombinationCategoryConverter.toDTO(vo);
        List<StandardUnitCombinationCategoryDTO> rt = standardUnitCombinationCategoryManage.findStandardUnitCombinationCategoryAll(dto);
        return JsonResult.success(StandardUnitCombinationCategoryConverter.toVO(rt));

    }
    
    // 业务方法：
    @RequestMapping(value = "/findStandardUnitCombinationByCategoryId")
    @ResponseBody
    public JsonResult<List<Long>> findStandardUnitCombinationByCategoryId(StandardUnitCombinationCategoryVO vo, HttpServletRequest req) {
    	StandardUnitCombinationCategoryDTO dto = StandardUnitCombinationCategoryConverter.toDTO(vo);
    	List<StandardUnitCombinationCategoryDTO> rt = standardUnitCombinationCategoryManage.findStandardUnitCombinationCategoryAll(dto);
    	List<Long> result = new ArrayList<Long>();
    	for (StandardUnitCombinationCategoryDTO suc : rt) {
    		result.add(suc.getStandardUnitCombinationId());
    	}
    	return JsonResult.success(result);
    	
    }

    // 业务方法：
    @RequestMapping(value = "/findStandardUnitCombinationCategoryOfPage")
    @ResponseBody
    public JsonResult<PageResult<StandardUnitCombinationCategoryVO>> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryVO vo, Pagination page, HttpServletRequest req) {
        StandardUnitCombinationCategoryDTO dto = StandardUnitCombinationCategoryConverter.toDTO(vo);
        PageResult<StandardUnitCombinationCategoryDTO> rt = standardUnitCombinationCategoryManage.findStandardUnitCombinationCategoryOfPage(dto, page);
        List<StandardUnitCombinationCategoryVO> list = StandardUnitCombinationCategoryConverter.toVO(rt.getList());
        PageResult<StandardUnitCombinationCategoryVO> result = new PageResult<StandardUnitCombinationCategoryVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return JsonResult.success(result);

    }


    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertStandardUnitCombinationCategoryWithTx")
    @ResponseBody
    public JsonResult<Long> insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryVO vo, HttpServletRequest req) {
        StandardUnitCombinationCategoryDTO dto = StandardUnitCombinationCategoryConverter.toDTO(vo);
        Long rt = standardUnitCombinationCategoryManage.insertStandardUnitCombinationCategoryWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateStandardUnitCombinationCategoryByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateStandardUnitCombinationCategoryByIdWithTx(StandardUnitCombinationCategoryVO vo, HttpServletRequest req) {
        StandardUnitCombinationCategoryDTO dto = StandardUnitCombinationCategoryConverter.toDTO(vo);
        int rt = standardUnitCombinationCategoryManage.updateStandardUnitCombinationCategoryWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/deleteStandardUnitCombinationCategoryWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryVO vo, HttpServletRequest req) {
        StandardUnitCombinationCategoryDTO dto = StandardUnitCombinationCategoryConverter.toDTO(vo);
        int rt = standardUnitCombinationCategoryManage.deleteStandardUnitCombinationCategoryWithTx(dto);
        return JsonResult.success(rt);
    }

    /**
     * 批量保存su组合和前台类目节点关系
     *
     * @param standardUnitCombinationId
     * @param categoryTreeId
     * @param sortType
     * @param type
     * @param categoryTreeNodeIdList
     * @param req
     * @return
     */
    @RequestMapping(value = "/saveStandardUnitCombinationCategoryAll")
    @ResponseBody
    public JsonResult<Boolean> saveStandardUnitCombinationCategoryAllWithTx(Long standardUnitCombinationId,
                                                                            Long categoryTreeId, Integer sortType, Integer type, String categoryTreeNodeIdList, HttpServletRequest req) {
        if (EmptyUtil.isEmpty(standardUnitCombinationId)) {
            return JsonResult.fail("su商品组合id不能为空");
        }
        logger.info("批量保存su组合和前台类目节点关系,su商品组合id:" + standardUnitCombinationId);
        CacheUser userCache = this.getCacheUser();
        if (EmptyUtil.isEmpty(categoryTreeId)) {
            throw new BusinessException("请选择类目树");
        }
        if (EmptyUtil.isEmpty(sortType)) {
            throw new BusinessException("请选择排序方式");
        }

        //用户id
        Long userId = userCache.getId();
        String name = userCache.getName();
        StandardUnitCombinationDTO dto = new StandardUnitCombinationDTO();
        dto.setId(standardUnitCombinationId);
        dto.setCategoryTreeId(categoryTreeId);
        dto.setSortType(sortType);
        dto.setUpdateUserid(userId);
        dto.setUpdateUsername(name);
        if (EmptyUtil.isNotEmpty(type)) {
            if (4 == type) { //新增加字段，区别与之前业务（关联前台类目树关联关系），只有关联后台类目树才新增设置，前台类目树为null。
                dto.setType(type);
            }
            //throw new BusinessException("商品组合类型为空");
        }
        List<Long> categoryTreeNodeIds = new ArrayList<Long>(JSONArray.parseArray(categoryTreeNodeIdList, Long.class));
        boolean rt = standardUnitCombinationCategoryManage.saveStandardUnitCombinationCategoryAllWithTx(dto,
                categoryTreeNodeIds);
        return JsonResult.success(rt);
    }

}
	