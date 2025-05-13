package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CommodityProductUnitCompareManage;
import com.egeo.components.product.converter.CommodityProductUnitCompareConverter;
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.components.product.vo.CommodityProductUnitCompareVO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/product/commodityProductUnitCompare")
public class CommodityProductUnitCompareAction extends BaseSpringController {

    @Resource(name = "commodityProductUnitCompare")
    private CommodityProductUnitCompareManage commodityProductUnitCompareManage;


    // 业务方法：
    @RequestMapping(value = "/findCommodityProductUnitCompareById")
    @ResponseBody
    public JsonResult<CommodityProductUnitCompareVO> findCommodityProductUnitCompareById(Long id) {
        if (EmptyUtil.isEmpty(id)) {
            throw new BusinessException("request param is null");
        }
        CommodityProductUnitCompareDTO dto = new CommodityProductUnitCompareDTO();
        dto.setId(id);
        CommodityProductUnitCompareDTO rt = commodityProductUnitCompareManage.findCommodityProductUnitCompareById(dto);
        return JsonResult.success(CommodityProductUnitCompareConverter.toVO(rt));

    }


    // 业务方法：
    @RequestMapping(value = "/findCommodityProductUnitCompareAll")
    @ResponseBody
    public JsonResult<List<CommodityProductUnitCompareVO>> findCommodityProductUnitCompareAll(CommodityProductUnitCompareVO vo, HttpServletRequest req) {
        CommodityProductUnitCompareDTO dto = CommodityProductUnitCompareConverter.toDTO(vo);
        List<CommodityProductUnitCompareDTO> rt = commodityProductUnitCompareManage.findCommodityProductUnitCompareAll(dto);
        return JsonResult.success(CommodityProductUnitCompareConverter.toVO(rt));

    }

    // 业务方法：
    @RequestMapping(value = "/findCommodityProductUnitCompareOfPage")
    @ResponseBody
    public JsonResult<PageResult<CommodityProductUnitCompareVO>> findCommodityProductUnitCompareOfPage(CommodityProductUnitCompareVO vo, Pagination page, HttpServletRequest req) {
        CommodityProductUnitCompareDTO dto = CommodityProductUnitCompareConverter.toDTO(vo);
        PageResult<CommodityProductUnitCompareDTO> rt = commodityProductUnitCompareManage.findCommodityProductUnitCompareOfPage(dto, page);
        List<CommodityProductUnitCompareVO> list = CommodityProductUnitCompareConverter.toVO(rt.getList());
        PageResult<CommodityProductUnitCompareVO> result = new PageResult<CommodityProductUnitCompareVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return JsonResult.success(result);

    }


    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertCommodityProductUnitCompareWithTx")
    @ResponseBody
    public JsonResult<Long> insertCommodityProductUnitCompareWithTx(CommodityProductUnitCompareVO vo, HttpServletRequest req) {
    	String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		} else {
			return JsonResult.fail("platformId不能为空");
		}
    	avoidInsertParam(vo);
        CommodityProductUnitCompareDTO dto = CommodityProductUnitCompareConverter.toDTO(vo);
        CacheUser userCache = RuntimeContext.cacheUser();
        if (EmptyUtil.isNotEmpty(userCache)) {
            if (EmptyUtil.isNotEmpty(userCache.getId().intValue())) {
                dto.setOperator(userCache.getId().intValue());
            }
        }
        Long rt = commodityProductUnitCompareManage.insertCommodityProductUnitCompareWithTx(dto);
        return JsonResult.success(rt);
    }

    private void avoidInsertParam(CommodityProductUnitCompareVO vo) {
        if (EmptyUtil.isEmpty(vo)) {
            throw new BusinessException("request param is null");
        }
        if (EmptyUtil.isEmpty(vo.getPlatformName())) {
            throw new BusinessException("比价说明不能为空");
        }
        if (vo.getPlatformName().length() < 30) {
            throw new BusinessException("比价说明不能少于30字");
        }
        if (vo.getPlatformName().length() > 500) {
            throw new BusinessException("比价说明文字不能超过500字");
        }
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateCommodityProductUnitCompareByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateCommodityProductUnitCompareByIdWithTx(CommodityProductUnitCompareVO vo, HttpServletRequest req) {
        avoidUpdateParam(vo);
        CommodityProductUnitCompareDTO dto = CommodityProductUnitCompareConverter.toDTO(vo);
        int rt = commodityProductUnitCompareManage.updateCommodityProductUnitCompareWithTx(dto);
        return JsonResult.success(rt);
    }

    private void avoidUpdateParam(CommodityProductUnitCompareVO vo) {
        if (EmptyUtil.isNotEmpty(vo) && EmptyUtil.isNotEmpty(vo.getPlatformName())) {
            if (vo.getPlatformName().length() > 500) {
                throw new BusinessException("比价说明文字超过500个字符");
            }
        }
    }

    // 业务方法：
    @RequestMapping(value = "/deleteCommodityProductUnitCompareWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteCommodityProductUnitCompareWithTx(CommodityProductUnitCompareVO vo, HttpServletRequest req) {
        CommodityProductUnitCompareDTO dto = CommodityProductUnitCompareConverter.toDTO(vo);
        int rt = commodityProductUnitCompareManage.deleteCommodityProductUnitCompareWithTx(dto);
        return JsonResult.success(rt);
    }

    // 业务方法：
    @RequestMapping(value = "/findCommodityProductUnitCompare")
    @ResponseBody
    public JsonResult<CommodityProductUnitCompareVO> findCommodityProductUnitCompare(CommodityProductUnitCompareVO vo, HttpServletRequest req) {
        CommodityProductUnitCompareVO result = new CommodityProductUnitCompareVO();
        CommodityProductUnitCompareDTO dto = CommodityProductUnitCompareConverter.toDTO(vo);
        List<CommodityProductUnitCompareDTO> rt = commodityProductUnitCompareManage.findCommodityProductUnitCompareAll(dto);
        if (EmptyUtil.isNotEmpty(rt)) {
            result = CommodityProductUnitCompareConverter.toVO(rt.get(0));
        }
        return JsonResult.success(result);
    }
}
	