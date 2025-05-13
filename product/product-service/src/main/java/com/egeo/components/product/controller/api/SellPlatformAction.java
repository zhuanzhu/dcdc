package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SellPlatformManage;
import com.egeo.components.product.common.Cheker;
import com.egeo.components.product.converter.SellPlatformConverter;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.components.product.vo.SellPlatformVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/product/sellPlatform")
public class SellPlatformAction extends BaseSpringController {

    @Resource(name = "sellPlatform")
    private SellPlatformManage sellPlatformManage;


    // 业务方法：
    @RequestMapping(value = "/findSellPlatformById")
    @ResponseBody
    public JsonResult<SellPlatformVO> findSellPlatformById(Long id) {

        SellPlatformDTO dto = new SellPlatformDTO();
        dto.setId(id);
        SellPlatformDTO rt = sellPlatformManage.findSellPlatformById(dto);
        return JsonResult.success(SellPlatformConverter.toVO(rt));

    }


    // 业务方法：
    @RequestMapping(value = "/findSellPlatformAll")
    @ResponseBody
    public JsonResult<List<SellPlatformVO>> findSellPlatformAll(SellPlatformVO vo, HttpServletRequest req) {
        SellPlatformDTO dto = SellPlatformConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<SellPlatformDTO> rt = sellPlatformManage.findSellPlatformAll(dto);
        return JsonResult.success(SellPlatformConverter.toVO(rt));

    }

    // 业务方法：
    @RequestMapping(value = "/findSellPlatformOfPage")
    @ResponseBody
    public JsonResult<PageResult<SellPlatformVO>> findSellPlatformOfPage(SellPlatformVO vo, Pagination page, HttpServletRequest req) {
       /* if (EmptyUtil.isNotEmpty(vo)) {
            if (EmptyUtil.isEmpty(vo.getStatus())) {
                vo.setStatus(1); //1：启用，0：停用
            }
        }*/
        SellPlatformDTO dto = SellPlatformConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }

        PageResult<SellPlatformDTO> rt = sellPlatformManage.findSellPlatformOfPage(dto, page);
        List<SellPlatformVO> list = SellPlatformConverter.toVO(rt.getList());
        PageResult<SellPlatformVO> result = new PageResult<SellPlatformVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return JsonResult.success(result);

    }


    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertSellPlatformWithTx")
    @ResponseBody
    public JsonResult<Long> insertSellPlatformWithTx(SellPlatformVO vo, HttpServletRequest req) {
        avoidInsertParm(vo, req);
        SellPlatformDTO dto = SellPlatformConverter.toDTO(vo);
        Long rt = sellPlatformManage.insertSellPlatformWithTx(dto);
        return JsonResult.success(rt);
    }

    private void avoidInsertParm(SellPlatformVO vo, HttpServletRequest req) {
        if (EmptyUtil.isEmpty(vo.getName())) {
            throw new BusinessException("请填写平台名称");
        }
        if (EmptyUtil.isEmpty(vo.getSortValue())) {
            throw new BusinessException("请填写排序值");
        }
        if (!Cheker.checkParam(String.valueOf(vo.getSortValue()), "1")) {
            throw new BusinessException("请对平台进行排序");
        }
        if (vo.getName().length() > 15) {
            throw new BusinessException("平台名称不能超过15个字符");
        }
        if (EmptyUtil.isNotEmpty(vo.getRemark())) {
            if (vo.getRemark().length() > 50) {
                throw new BusinessException("平台备注不能超过50个字符");
            }
        }
        if (EmptyUtil.isEmpty(vo.getStatus())) {
            throw new BusinessException("平台备注不能超过50个字符");
        }
        String platformId = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(platformId)) {
            vo.setPlatformId(Long.valueOf(platformId));
        }
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateSellPlatformByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateSellPlatformByIdWithTx(SellPlatformVO vo, HttpServletRequest req) {
        avoidUpParm(vo, req);
        SellPlatformDTO dto = SellPlatformConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = sellPlatformManage.updateSellPlatformWithTx(dto);
        return JsonResult.success(rt);
    }

    private void avoidUpParm(SellPlatformVO vo, HttpServletRequest req) {
        if (EmptyUtil.isNotEmpty(vo.getName())) {
            if (vo.getName().length() > 15) {
                throw new BusinessException("平台名称不能超过15个字符");
            }
        }
        if (EmptyUtil.isNotEmpty(vo.getSortValue())) {
            if (!Cheker.checkParam(String.valueOf(vo.getSortValue()), "1")) {
                throw new BusinessException("排序值必須是整数");
            }
        }
        if (EmptyUtil.isNotEmpty(vo.getRemark())) {
            if (vo.getRemark().length() > 50) {
                throw new BusinessException("平台备注不能超过50个字符");
            }
        }
        String platformId = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(platformId)) {
            vo.setPlatformId(Long.valueOf(platformId));
        }
    }

    // 业务方法：
    @RequestMapping(value = "/deleteSellPlatformWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteSellPlatformWithTx(SellPlatformVO vo, HttpServletRequest req) {
        SellPlatformDTO dto = SellPlatformConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = sellPlatformManage.deleteSellPlatformWithTx(dto);
        return JsonResult.success(rt);
    }

}
	