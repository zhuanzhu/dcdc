package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.StandardUnitCombinationManage;
import com.egeo.components.product.converter.StandardUnitCombinationConverter;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.vo.StandardUnitCombinationVO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/product/standardUnitCombination")
public class StandardUnitCombinationAction extends BaseSpringController {

    @Resource(name = "standardUnitCombination")
    private StandardUnitCombinationManage standardUnitCombinationManage;


    // 业务方法：
    @RequestMapping(value = "/findStandardUnitCombinationById")
    @ResponseBody
    public JsonResult<Map<String, Object>> findStandardUnitCombinationById(Long standardUnitCombinationId) {

        StandardUnitCombinationDTO dto = new StandardUnitCombinationDTO();
        dto.setId(standardUnitCombinationId);
        Map<String, Object> rt = standardUnitCombinationManage.findStandardUnitCombinationById(dto);
        return JsonResult.success(rt);

    }


    // 业务方法：
    @RequestMapping(value = "/findStandardUnitCombinationAll")
    @ResponseBody
    public JsonResult<List<StandardUnitCombinationVO>> findStandardUnitCombinationAll(StandardUnitCombinationVO vo, HttpServletRequest req) {
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<StandardUnitCombinationDTO> rt = standardUnitCombinationManage.findStandardUnitCombinationAll(dto);
        return JsonResult.success(StandardUnitCombinationConverter.toVO(rt));

    }

    // 业务方法：
    @RequestMapping(value = "/findStandardUnitCombinationOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findStandardUnitCombinationOfPage(StandardUnitCombinationVO vo, String SUCIds, Pagination page, HttpServletRequest req) {
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        // 判断su组合id集合是否为null，为null并且集合数量为0则直接返回
        if (SUCIds != null) {
            List<Long> standardUnitCombinationIdList = JSONArray.parseArray(SUCIds, Long.class);
            if (standardUnitCombinationIdList.size() <= 0) {
                PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
                List<Map<String, Object>> list = new ArrayList<>();
                result.setList(list);
                result.setPageNo(page.getPageNo());
                result.setPageSize(page.getPageSize());
                result.setTotalSize(0);
                return JsonResult.success(result);
            } else {
                PageResult<Map<String, Object>> rt = standardUnitCombinationManage.findStandardUnitCombinationOfPage(dto, page, standardUnitCombinationIdList);
            }
        }
        PageResult<Map<String, Object>> rt = standardUnitCombinationManage.findStandardUnitCombinationOfPage(dto, page, null);

        return JsonResult.success(rt);

    }


    /**
     * 添加商品分组
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/insertStandardUnitCombinationWithTx")
    @ResponseBody
    public JsonResult<Long> insertStandardUnitCombinationWithTx(StandardUnitCombinationVO vo, HttpServletRequest req) {
        logger.info("添加商品分组,分组名称：" + vo.getCombinationName());
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        CacheUser userCache = this.getCacheUser();
        if (EmptyUtil.isEmpty(vo.getCombinationName())) {
            throw new BusinessException("请输入分组名称");
        }
        if (vo.getCombinationName().length() > 10) {
            throw new BusinessException("分组名称不超过10个字");
        }
        if (EmptyUtil.isEmpty(vo.getType())) {
            throw new BusinessException("请选择组合类型");
        }
        //用户id
        Long userId = userCache.getId();
        String name = userCache.getName();
        dto.setCreateUserid(userId);
        dto.setCreateUsername(name);
        dto.setUpdateUserid(userId);
        dto.setUpdateUsername(name);
        dto.setType(vo.getType());
        Long rt = standardUnitCombinationManage.insertStandardUnitCombinationWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateStandardUnitCombinationByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateStandardUnitCombinationByIdWithTx(StandardUnitCombinationVO vo, HttpServletRequest req) {
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        CacheUser userCache = this.getCacheUser();
        if (vo.getCombinationName().length() > 10) {
            throw new BusinessException("分组名称不超过10个字");
        }

        //用户id
        Long userId = userCache.getId();
        String name = userCache.getName();
        dto.setUpdateUserid(userId);
        dto.setUpdateUsername(name);
        int rt = standardUnitCombinationManage.updateStandardUnitCombinationWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/deleteStandardUnitCombinationWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteStandardUnitCombinationWithTx(StandardUnitCombinationVO vo, HttpServletRequest req) {
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = standardUnitCombinationManage.deleteStandardUnitCombinationWithTx(dto);
        return JsonResult.success(rt);
    }

    /**
     * 查询所有su分组信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/queryStandardUnitCombinationAll")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> queryStandardUnitCombinationAll(StandardUnitCombinationVO vo, HttpServletRequest req) {
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<Map<String, Object>> rt = standardUnitCombinationManage.queryStandardUnitCombinationAll(dto);
        return JsonResult.success(rt);

    }

    /**
     * 模糊查询su分组信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/findStandardUnitCombinationAllByBlurry")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationVO vo, HttpServletRequest req) {
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<Map<String, Object>> rt = standardUnitCombinationManage.findStandardUnitCombinationAllByBlurry(dto);
        return JsonResult.success(rt);

    }

    List<Integer> types = Arrays.asList(1, 3, 6, 10);
    /**
     * 查询商品组合信息
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findSucTitleOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findSucTitleOfPage(StandardUnitCombinationVO vo,
                                                                          Pagination page, HttpServletRequest req) {
        StandardUnitCombinationDTO dto = StandardUnitCombinationConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        // 组合类型：1、su商品组合 2、关联前台类目节点 3、关联标签 4、关联后台类目树节点
        // 目前只查询商品组合类型
        //dto.setType(1);
        dto.setTypes(types);
        PageResult<Map<String, Object>> rt = standardUnitCombinationManage.findSucTitleOfPage(dto, page);
        return JsonResult.success(rt);

    }
}
