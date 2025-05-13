package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.CategoryTreeNodeCategoryManage;
import com.egeo.components.product.converter.CategoryTreeNodeCategoryConverter;
import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.components.product.vo.CategoryTreeNodeCategoryVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/product/categoryTreeNodeCategory")
public class CategoryTreeNodeCategoryAction extends BaseSpringController {

    @Resource(name = "categoryTreeNodeCategory")
    private CategoryTreeNodeCategoryManage categoryTreeNodeCategoryManage;


    // 业务方法：
    @RequestMapping(value = "/findCategoryTreeNodeCategoryById")
    @ResponseBody
    public JsonResult<CategoryTreeNodeCategoryVO> findCategoryTreeNodeCategoryById(Long id) {

        CategoryTreeNodeCategoryDTO dto = new CategoryTreeNodeCategoryDTO();
        dto.setId(id);
        CategoryTreeNodeCategoryDTO rt = categoryTreeNodeCategoryManage.findCategoryTreeNodeCategoryById(dto);
        return JsonResult.success(CategoryTreeNodeCategoryConverter.toVO(rt));

    }


    // 业务方法：
    @RequestMapping(value = "/findCategoryTreeNodeCategoryAll")
    @ResponseBody
    public JsonResult<List<CategoryTreeNodeCategoryVO>> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryVO vo, HttpServletRequest req) {
        CategoryTreeNodeCategoryDTO dto = CategoryTreeNodeCategoryConverter.toDTO(vo);
        List<CategoryTreeNodeCategoryDTO> rt = categoryTreeNodeCategoryManage.findCategoryTreeNodeCategoryAll(dto);
        return JsonResult.success(CategoryTreeNodeCategoryConverter.toVO(rt));

    }

    // 业务方法：
    @RequestMapping(value = "/findCategoryTreeNodeCategoryOfPage")
    @ResponseBody
    public JsonResult<PageResult<CategoryTreeNodeCategoryVO>> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryVO vo, Pagination page, HttpServletRequest req) {
        CategoryTreeNodeCategoryDTO dto = CategoryTreeNodeCategoryConverter.toDTO(vo);
        PageResult<CategoryTreeNodeCategoryDTO> rt = categoryTreeNodeCategoryManage.findCategoryTreeNodeCategoryOfPage(dto, page);
        List<CategoryTreeNodeCategoryVO> list = CategoryTreeNodeCategoryConverter.toVO(rt.getList());
        PageResult<CategoryTreeNodeCategoryVO> result = new PageResult<CategoryTreeNodeCategoryVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return JsonResult.success(result);

    }


    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertCategoryTreeNodeCategoryWithTx")
    @ResponseBody
    public JsonResult<Long> insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryVO vo, HttpServletRequest req) {
        CategoryTreeNodeCategoryDTO dto = CategoryTreeNodeCategoryConverter.toDTO(vo);
        Long rt = categoryTreeNodeCategoryManage.insertCategoryTreeNodeCategoryWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateCategoryTreeNodeCategoryByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateCategoryTreeNodeCategoryByIdWithTx(CategoryTreeNodeCategoryVO vo, HttpServletRequest req) {
        CategoryTreeNodeCategoryDTO dto = CategoryTreeNodeCategoryConverter.toDTO(vo);
        int rt = categoryTreeNodeCategoryManage.updateCategoryTreeNodeCategoryWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/deleteCategoryTreeNodeCategoryWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryVO vo, HttpServletRequest req) {
        CategoryTreeNodeCategoryDTO dto = CategoryTreeNodeCategoryConverter.toDTO(vo);
        int rt = categoryTreeNodeCategoryManage.deleteCategoryTreeNodeCategoryWithTx(dto);
        return JsonResult.success(rt);
    }

    /**
     * 批量添加前台类目节点与后台类目节点关系
     *
     * @param frontCategoryTreeNodeId
     * @param queenCategoryTreeNodeIdList
     * @return
     */
    @RequestMapping(value = "/insertCategoryTreeNodeCategoryAllWithTx")
    @ResponseBody
    public JsonResult<Boolean> insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId, String queenCategoryTreeNodeIdList) {
        logger.info("批量添加前台类目节点与后台类目节点关系，前台类目id：" + frontCategoryTreeNodeId + "，后台类目节点：" + queenCategoryTreeNodeIdList);
        List<Long> queenCategoryTreeNodeIds = new ArrayList<>();
        if (EmptyUtil.isNotEmpty(queenCategoryTreeNodeIdList)) {
            queenCategoryTreeNodeIds = new ArrayList<>(JSONArray.parseArray(queenCategoryTreeNodeIdList, Long.class));
        }
        boolean rt = categoryTreeNodeCategoryManage.insertCategoryTreeNodeCategoryAllWithTx(frontCategoryTreeNodeId, queenCategoryTreeNodeIds);
        return JsonResult.success(rt);
    }

    /**
     * 根据前台类目节点查询后台类目节点
     *
     * @param categoryTreeNodeId
     * @return
     */
    @RequestMapping(value = "/findCategoryTreeNodeId")
    @ResponseBody
    public JsonResult<List<Long>> findCategoryTreeNodeId(Long categoryTreeNodeId) {
        logger.info("根据前台类目节点查询后台类目节点，前台类目id：" + categoryTreeNodeId);
        List<Long> rt = categoryTreeNodeCategoryManage.findCategoryTreeNodeId(categoryTreeNodeId);
        return JsonResult.success(rt);
    }


    /**
     * 前台类目节点关联后台类目节点与商品组合
     *
     * @param frontCategoryTreeNodeId
     * @param queenCategoryTreeNodeIdList
     * @return
     */
    @RequestMapping(value = "/insertCtnAndSuc")
    @ResponseBody
    public JsonResult<Boolean> insertCtnAndSucWithTx(Long frontCategoryTreeNodeId,
                      String queenCategoryTreeNodeIdList, String standardUnitCombinationIdList) throws Exception {
        try {
            List<Long> ctnIds = new ArrayList<>();
            List<Long> sucIds = new ArrayList<>();
            if (EmptyUtil.isEmpty(queenCategoryTreeNodeIdList) && EmptyUtil.isEmpty(standardUnitCombinationIdList)) {
                throw new BusinessException("请选择要关联的商品组合或后台目录树");
            }
            if (EmptyUtil.isNotEmpty(queenCategoryTreeNodeIdList)) {
                ctnIds = new ArrayList<>(JSONArray.parseArray(queenCategoryTreeNodeIdList, Long.class));
            }
            if (EmptyUtil.isNotEmpty(standardUnitCombinationIdList)) {
                sucIds = new ArrayList<>(JSONArray.parseArray(standardUnitCombinationIdList, Long.class));
            }
            return JsonResult.success(categoryTreeNodeCategoryManage.insertCtnAndSucWithTx(frontCategoryTreeNodeId,
                    ctnIds, sucIds));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("关联异常,请稍后在试!");
        }
    }
}
	