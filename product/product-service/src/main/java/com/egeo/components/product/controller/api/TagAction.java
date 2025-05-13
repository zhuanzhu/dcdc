package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.TagManage;
import com.egeo.components.product.converter.TagConverter;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.TagDTO;
import com.egeo.components.product.vo.QuerySuDetailVO;
import com.egeo.components.product.vo.TagVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/product/tag")
public class TagAction extends BaseSpringController {

    @Resource(name = "tag")
    private TagManage tagManage;


    /**
     * 根据标签id查询标签信息
     *
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/findTagById")
    @ResponseBody
    public JsonResult<Map<String, Object>> findTagById(Long tagId) {
        logger.info("根据标签id查询标签信息，标签id：" + tagId);
        return JsonResult.success(tagManage.findTagById(tagId));

    }


    // 业务方法：
    @RequestMapping(value = "/findTagAll")
    @ResponseBody
    public JsonResult<List<TagVO>> findTagAll(TagVO vo, HttpServletRequest req) {
        TagDTO dto = TagConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<TagDTO> rt = tagManage.findTagAll(dto);
        return JsonResult.success(TagConverter.toVO(rt));

    }

    /**
     * 分页查询标签信息
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findTagOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findTagOfPage(TagVO vo, String tagList, Pagination page, HttpServletRequest req) {
        logger.info("分页查询标签信息");
        TagDTO dto = TagConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<Long> tags = new ArrayList<>();

        if (EmptyUtil.isNotEmpty(tagList)) {
            tags = new ArrayList<Long>(JSONArray.parseArray(tagList, Long.class));
        }
        if (tagList != null) {
            if (tags.size() == 0) {
                PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
                List<Map<String, Object>> list = new ArrayList<>();
                result.setList(list);
                result.setPageNo(page.getPageNo());
                result.setPageSize(page.getPageSize());
                result.setTotalSize(0);
                return JsonResult.success(result);
            }

        }

        return JsonResult.success(tagManage.findTagOfPage(dto, page, tags));

    }


    /**
     * 新增标签信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/insertTagWithTx")
    @ResponseBody
    public JsonResult<Long> insertTagWithTx(TagVO vo, HttpServletRequest req) {
        logger.info("新增标签");
        TagDTO dto = TagConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        Long rt = tagManage.insertTagWithTx(dto);
        return JsonResult.success(rt);
    }

    /**
     * 根据标签id修改标签信息
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/updateTagByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateTagByIdWithTx(TagVO vo, HttpServletRequest req) {
        logger.info("根据标签id修改标签信息,标签id:" + vo.getId());
        TagDTO dto = TagConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = tagManage.updateTagWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/deleteTagWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteTagWithTx(TagVO vo, HttpServletRequest req) {
        TagDTO dto = TagConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = tagManage.deleteTagWithTx(dto);
        return JsonResult.success(rt);
    }

    /**
     * 根据标签id启用停用标签
     *
     * @param tagId
     * @param isValid
     * @return
     */
    @RequestMapping(value = "/updateTagTypeByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateTagTypeByIdWithTx(Long tagId, Integer isValid) {
        logger.info("根据标签id启用停用标签,标签id:" + tagId);
        int rt = tagManage.updateTagTypeByIdWithTx(tagId, isValid);
        return JsonResult.success(rt);
    }

    /**
     * 根据su商品id分页查询标签数据
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findTagOfPageBystandardUnitCId")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findTagOfPageBystandardUnitCId(TagVO vo, Pagination page, Long standardUnitCombinationId, HttpServletRequest req) {
        TagDTO dto = TagConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        return JsonResult.success(tagManage.findTagOfPageBystandardUnitCId(dto, page, standardUnitCombinationId));

    }

    /**
     * 根据标签id清除记录
     *
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/tagClearRecordByTagId")
    @ResponseBody
    public JsonResult<Boolean> tagClearRecordByTagId(Long tagId) {
        return JsonResult.success(tagManage.tagClearRecordByTagId(tagId));

    }

    /**
     * 商品标签页面->关联标签->根据tagIds查询商品su详情列表
     *
     * @param tagIds
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/queryMpByTagId", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> queryMerchantProductByTagId(String tagIds,Integer sortType, QuerySuDetailVO vo,
                                                                                   Pagination page, HttpServletRequest req) {
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        List<Map<String, Object>> standardUnitList = new ArrayList<>();
        Map<String, Object> reqParam = new HashMap<>();
        /*try {*/
            List<Long> tagList = new ArrayList<>();
            if(EmptyUtil.isNotEmpty(tagIds)){
                tagList = new ArrayList<Long>(JSONArray.parseArray(tagIds, Long.class));
            }
            String str = req.getHeader("platformId");
            if (EmptyUtil.isNotEmpty(str)) {
                reqParam.put("platformId", Long.valueOf(str));
            }
            if (!EmptyUtil.isEmpty(vo)) {
                if (EmptyUtil.isNotEmpty(vo.getName())) {
                    reqParam.put("name", vo.getName());
                }
                if (EmptyUtil.isNotEmpty(vo.getMerchantProductSerialNumber())) {
                    reqParam.put("merchantProductSerialNumber", vo.getMerchantProductSerialNumber());
                }
                if (EmptyUtil.isNotEmpty(vo.getBeginSalePrice())) {
                    reqParam.put("beginSalePrice", vo.getBeginSalePrice());
                }
                if (EmptyUtil.isNotEmpty(vo.getEndSalePrice())) {
                    reqParam.put("endSalePrice", vo.getEndSalePrice());
                }
                if (EmptyUtil.isNotEmpty(vo.getBeginPromotionPrice())) {
                    reqParam.put("beginPromotionPrice", vo.getBeginPromotionPrice());
                }
                if (EmptyUtil.isNotEmpty(vo.getEndPromotionPrice())) {
                    reqParam.put("endPromotionPrice", vo.getEndPromotionPrice());
                }
                if (EmptyUtil.isNotEmpty(vo.getStatus())) {
                    reqParam.put("status", vo.getStatus());
                }
                if (EmptyUtil.isNotEmpty(vo.getIsVisible())) {
                    reqParam.put("isVisible", vo.getIsVisible());
                }
                if (EmptyUtil.isNotEmpty(vo.getPlatformId())) {
                    reqParam.put("platformId", vo.getPlatformId());
                }
                if (EmptyUtil.isNotEmpty(vo.getMerchantId())) {
                	reqParam.put("merchantId", vo.getMerchantId());
                }
            }
            if (sortType != null) {
            	if(sortType == 1) {
            		page.setOrderBy(" su.sold_base desc");
            	} else if(sortType == 2) {
            		page.setOrderBy(" su.update_time desc");
            	} else if(sortType == 3) {
            		page.setOrderBy(" su.front_serial_number,su.id desc");
            	}
                reqParam.put("platformId", Long.valueOf(str));
            }
            PageResult<StandardUnitDTO> result = tagManage.querySuInfoByTagId(tagList, page, reqParam);
            if (EmptyUtil.isNotEmpty(result)) {
                List<StandardUnitDTO> standardUnitDTOList = result.getList();
                for (StandardUnitDTO standardUnitDTO : standardUnitDTOList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("standardUnitId", standardUnitDTO.getId());
                    map.put("merchantProductSerialNumber", standardUnitDTO.getMerchantProductSerialNumber());
                    map.put("name", standardUnitDTO.getName());
                    map.put("salePrice", standardUnitDTO.getSalePrice());
                    map.put("promotionPrice", standardUnitDTO.getPromotionPrice());
                    map.put("marketPrice", standardUnitDTO.getMarketPrice());
                    map.put("status", standardUnitDTO.getStatus());
                    map.put("isVisible", standardUnitDTO.getIsVisible());
                    map.put("merchantId", standardUnitDTO.getMerchantId());
                    map.put("frontSerialNumber", standardUnitDTO.getFrontSerialNumber());
                    standardUnitList.add(map);
                }
                pageResult.setList(standardUnitList);
                pageResult.setPageNo(result.getPageNo());
                pageResult.setPageSize(result.getPageSize());
                pageResult.setTotalSize(result.getTotalSize());
            }
       /* } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("商品详情页面展示异常,请稍后在试!");
        }*/
        return JsonResult.success(pageResult);
    }

    /**
     * 根据su商品标识standardUnitId,商品标签标识tagId,删除商品标签关联的商品su关联关系
     *
     * @param standardUnitId
     * @return
     */
    @RequestMapping(value = "/delMpTagInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Boolean> delMpTagInfo(@RequestParam Long standardUnitId) {
        if (EmptyUtil.isEmpty(standardUnitId)) {
            throw new BusinessException("request params is null");
        }
        return JsonResult.success(tagManage.delMpTagInfo(standardUnitId));
    }
}
	