package com.egeo.components.promotion.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.promotion.business.CouponManage;
import com.egeo.components.promotion.converter.CouponConverter;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.vo.CouponVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/promotion/coupon")
public class CouponAction extends BaseSpringController {

    @Resource(name = "coupon")
    private CouponManage couponManage;

    /**
     * 创建或编辑优惠卷
     *
     * @param vo
     * @param companys
     * @param stores
     * @param req
     * @return
     */
    @RequestMapping(value = "/insertOrUpdateCouponWithTx")
    @ResponseBody
    public JsonResult<Map<String, Object>> insertOrUpdateCouponWithTx(CouponVO vo, String companys, String stores,
                                                                      HttpServletRequest req) {
        logger.info("创建或编辑优惠卷");
        String str = req.getHeader("platformId");
        String flag = null;
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        try {
            vo.setCompanyList(JSONArray.parseArray(companys, Long.class));
        } catch (Exception e) {
            return fail("所属企业参数有误");
        }
        if (EmptyUtil.isEmpty(vo.getCompanyList()))
            return fail("请选择所属企业");
        if (EmptyUtil.isNotEmpty(stores)) {
            List storeIds = JSONArray.parseArray(stores, Long.class);
            if (EmptyUtil.isNotEmpty(storeIds)) {
                if (StringUtils.equalsIgnoreCase("-1", storeIds.get(0).toString())) {
                    flag = "-1";
                } else {
                    vo.setStoreList(storeIds);
                }
            }
        }
        if(vo.getCouponType()==0){
            //满减券时优惠金额大于0
            if(vo.getDiscountAmount()<=0){
                return fail("优惠金额必须大于0");
            }

        }
        CacheUser cacheUser = getCacheUser();
        vo.setUpdateUser(cacheUser.getId());
        logger.info(""+vo.getDetail().length());

        return couponManage.insertOrUpdateCouponWithTx(flag, CouponConverter.toDTO(vo));
    }

    /**
     * 优惠卷列表
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findCouponOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findCouponOfPage(CouponVO vo, String couponIds,
                                                                        Pagination page, HttpServletRequest req) {
        logger.info("优惠卷列表");
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        // 目前只有商品组搜素,写死
        if (vo.getGoodsId() != null) {
            vo.setGoodsType(1);
        }

        List<Long> couponIdList = null;
        try {
            couponIdList = JSONArray.parseArray(couponIds, Long.class);
        } catch (Exception e) {
            return fail("优惠卷id数组参数错误");
        }

        //定义不同类型优惠券的排序规则



        return success(couponManage.findCouponOfPageByBlurry(CouponConverter.toDTO(vo), couponIdList, page));
    }

    /**
     * 删除优惠卷(逻辑删除)
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/deleteCouponWithTx")
    @ResponseBody
    public JsonResult<Integer> deleteCouponWithTx(CouponVO vo, HttpServletRequest req) {
        logger.info("删除优惠卷(逻辑删除)");
        if (EmptyUtil.isEmpty(vo.getId()))
            return fail("优惠卷id不能为空");
        CouponDTO dto = new CouponDTO();
        dto.setId(vo.getId());
        dto.setIsDelete(Integer.valueOf(1));
        return success(couponManage.updateCouponWithTx(dto));
    }

    /**
     * 查询优惠卷的所属公司
     *
     * @param id
     * @param page
     * @return
     */
    @RequestMapping(value = "/findCouponCompanyOfPage")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> findCouponCompanyOfPage(Long id, Integer type, Pagination page,
                                                                         HttpServletRequest req) {
    	String str = req.getHeader("platformId");
        if (EmptyUtil.isEmpty(str)) {
        	return fail("platformId不能为空");
        }
    	if (type == null)
            return fail("type不能为空");

        if (type.equals(Long.valueOf(0L)) && id == null)
            return fail("优惠卷id不能为空");

        if (type.equals(Long.valueOf(1L)) && id == null)
            return fail("优惠卷分组id不能为空");
        Long platformId = Long.valueOf(str);
        
        return success(couponManage.findCouponCompanyOfPage(platformId, id, type, page));
    }

    /**
     * 查询优惠卷的相关商品
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findCouponGoodsAll")
    @ResponseBody
    public JsonResult<List<Map<String, Object>>> findCouponGoodsAll(CouponVO vo, HttpServletRequest req) {
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            vo.setPlatformId(platformId);
        }
        if (vo.getId() == null)
            return fail("优惠卷id不能为空");

        CouponDTO dto = new CouponDTO();
        dto.setId(vo.getId());
        List<Map<String, Object>> rt = couponManage.findCouponGoodsAll(dto);
        if (EmptyUtil.isEmpty(rt))
            return fail("优惠卷不存在");
        return success(rt);
    }

    /**
     * 通过id查询优惠卷详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findCouponById")
    @ResponseBody
    public JsonResult<Map<String, Object>> findCouponById(Long id) {
        CouponDTO dto = new CouponDTO();
        dto.setId(id);
        Map<String, Object> map = couponManage.findCouponById(dto);
        if (EmptyUtil.isEmpty(map))
            return fail("优惠卷不存在");
        return success(map);

    }

    // 业务方法：
    @RequestMapping(value = "/findCouponAll")
    @ResponseBody
    public JsonResult<List<CouponVO>> findCouponAll(CouponVO vo, HttpServletRequest req) {
        CouponDTO dto = CouponConverter.toDTO(vo);
        List<CouponDTO> rt = couponManage.findCouponAll(dto);
        return success(CouponConverter.toVO(rt));

    }

    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertCouponWithTx")
    @ResponseBody
    public JsonResult<Long> insertCouponWithTx(CouponVO vo, HttpServletRequest req) {
        CouponDTO dto = CouponConverter.toDTO(vo);
        Long rt = couponManage.insertCouponWithTx(dto);
        return success(rt);
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateCouponByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateCouponByIdWithTx(CouponVO vo, HttpServletRequest req) {
        CouponDTO dto = CouponConverter.toDTO(vo);
        int rt = couponManage.updateCouponWithTx(dto);
        return success(rt);
    }

}
