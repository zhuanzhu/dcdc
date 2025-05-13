package com.egeo.components.promotion.business.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.promotion.business.CouponGroupManage;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.dto.CouponGroupStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.facade.CouponBatchFacade;
import com.egeo.components.promotion.facade.CouponFacade;
import com.egeo.components.promotion.facade.CouponGroupFacade;
import com.egeo.components.promotion.facade.CouponUnitFacade;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("couponGroup")
public class CouponGroupManageImpl implements CouponGroupManage {

    @Resource(name = "couponGroupFacade")
    private CouponGroupFacade couponGroupFacade;

    @Resource(name = "couponFacade")
    private CouponFacade couponFacade;

    @Resource(name = "couponUnitFacade")
    private CouponUnitFacade couponUnitFacade;

    @Resource(name = "couponBatchFacade")
    private CouponBatchFacade couponBatchFacade;

    @Override
    public PageResult<CouponGroupDTO> findCouponGroupOfPage(CouponGroupDTO dto, Pagination page) {
        return couponGroupFacade.findCouponGroupOfPage(dto, page);
    }

    @Override
    public List<CouponGroupDTO> findCouponGroupAll(CouponGroupDTO dto) {
        return couponGroupFacade.findCouponGroupAll(dto);
    }

    @Override
    public Long insertCouponGroupWithTx(CouponGroupDTO dto) {
        return couponGroupFacade.insertCouponGroupWithTx(dto);
    }

    @Override
    public int updateCouponGroupWithTx(CouponGroupDTO dto) {
        return couponGroupFacade.updateCouponGroupWithTx(dto);
    }

    @Override
    public int deleteCouponGroupWithTx(CouponGroupDTO dto) {
        return couponGroupFacade.deleteCouponGroupWithTx(dto);
    }

    @Override
    public JsonResult<Map<String, Object>> insertOrUpdateCouponGroupWithTx(String flag, CouponGroupDTO dto) {
        // 参数校验
        if (EmptyUtil.isEmpty(dto.getGroupName()))
            return JsonResult.fail("请填写分组名称");
        if (dto.getGroupName().length() > 10)
            return JsonResult.fail("分组名称最多不超过10个字");
        // 分组名称查重
        CouponGroupDTO couponGroupDTO = new CouponGroupDTO();
        couponGroupDTO.setGroupName(dto.getGroupName());
        List<CouponGroupDTO> couponGroupDTOList = couponGroupFacade.findCouponGroupAll(couponGroupDTO);

        // -1 关联所有门店
        if (StringUtils.equalsIgnoreCase("-1", flag)) {
            List<StoreDTO> storeDTOList = couponFacade.findRootStoreAll(new StoreDTO());
            List<Long> storeList = new ArrayList<>();
            for (StoreDTO storeDTO : storeDTOList) {
                storeList.add(storeDTO.getId());
            }
            dto.setStoreList(storeList);
        }

        if (dto.getId() == null) {
            // 新增
            // 更新人设置为null
            dto.setUpdateUser(null);

            if (EmptyUtil.isNotEmpty(couponGroupDTOList))
                return JsonResult.fail("分组名称已存在，请修改后重新保存");

            couponGroupFacade.insertCouponGroupWithTx(dto);
        } else {
            // 编辑
            for (CouponGroupDTO couponGroupDTO_ : couponGroupDTOList) {
                if (!dto.getId().equals(couponGroupDTO_.getId()))
                    return JsonResult.fail("分组名称已存在，请修改后重新保存");
            }

            if (getCouponBatchListSize(dto.getId()) > 0)
                return JsonResult.fail("该优惠券组已发放，不可编辑");

            couponGroupFacade.updateCouponGroupWithTx(dto);
        }

        return JsonResult.success(null);
    }

    @Override
    public PageResult<Map<String, Object>> findCouponGroupOfPageByBlurry(CouponGroupDTO dto, Pagination page) {
        PageResult<CouponGroupDTO> rt = couponGroupFacade.findCouponGroupOfPageByBlurry(dto, page);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (CouponGroupDTO couponGroupDTO : rt.getList()) {
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询修改人信息
            map.put("id", couponGroupDTO.getId());
            map.put("groupName", couponGroupDTO.getGroupName());
            map.put("groupDescp", couponGroupDTO.getGroupDescp());

            // 查询优惠卷分组的优惠卷信息
            List<CouponGroupRelDTO> couponGroupRelDTOList = couponGroupFacade
                    .queryCouponGroupRelByCouponId(couponGroupDTO.getId());
            map.put("couponListSize", couponGroupRelDTOList.size());

            // 判断优惠卷分组是否可以编辑,是否有该优惠券组发放的批次
            map.put("isEdit", getCouponBatchListSize(couponGroupDTO.getId()) > 0 ? 0 : 1);

            list.add(map);
        }

        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return result;
    }

    public int getCouponBatchListSize(Long couponGroupId) {
        CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
        couponBatchDTO.setCouponRelType(Integer.valueOf(1));
        couponBatchDTO.setCouponRelId(couponGroupId);
        List<CouponBatchDTO> couponBatchDTOList = couponBatchFacade.findCouponBatchAll(couponBatchDTO);

        return couponBatchDTOList.size();
    }

    @Override
    public Map<String, Object> findCouponGroupById(CouponGroupDTO dto) {
        CouponGroupDTO dto_ = couponGroupFacade.findCouponGroupById(dto);
        if (EmptyUtil.isEmpty(dto_)) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", dto_.getId());
        map.put("groupName", dto_.getGroupName());
        map.put("groupDescp", dto_.getGroupDescp());
        // 查询更新人信息
        if (dto_.getUpdateUser() != null) {
            UserDTO userDTO = couponFacade.queryUserById(dto_.getUpdateUser());
            map.put("updateUser", userDTO != null ? userDTO.getLoginName() : null);
        } else {
            map.put("updateUser", null);
        }
        map.put("updateTime", !dto_.getUpdateTime().equals(dto_.getCreateTime()) ? dto_.getUpdateTime() : null);

        // 查询优惠卷分组下的优惠卷信息
        List<CouponGroupRelDTO> couponGroupRelDTOList = couponGroupFacade.queryCouponGroupRelByCouponId(dto_.getId());
        List<Map<String, Object>> couponList = new ArrayList<Map<String, Object>>();
        for (CouponGroupRelDTO couponGroupRelDTO : couponGroupRelDTOList) {
            CouponDTO couponDTO = new CouponDTO();
            couponDTO.setId(couponGroupRelDTO.getCouponId());
            CouponDTO couponDTO_ = couponFacade.findCouponById(couponDTO);
            if (EmptyUtil.isNotEmpty(couponDTO_)) {
                Map<String, Object> simpleCoupon = new HashMap<String, Object>();
                simpleCoupon.put("id", couponDTO_.getId());
                simpleCoupon.put("title", couponDTO_.getTitle());
                couponList.add(simpleCoupon);
            }
        }
        map.put("couponList", couponList);
        //查询优惠券组所关联的门店
        List<Long> stores = new ArrayList<>();
        CouponGroupStoreDTO couponGroupStoreDTO = new CouponGroupStoreDTO();
        couponGroupStoreDTO.setCouponGroupId(dto_.getId());
        List<CouponGroupStoreDTO> couponGroupStoreList = couponGroupFacade.findCouponGroupStoreAll(couponGroupStoreDTO);
        for (CouponGroupStoreDTO obj:couponGroupStoreList) {
            stores.add(obj.getStoreId());
        }
        map.put("stores", stores);
        return map;
    }

    /**
     * 查询优惠卷分组的优惠卷unit数量
     *
     * @param couponBatchId
     * @return
     */
    private int getCouponUnitListSize(Long couponBatchId, boolean isEdit) {
        int couponUnitListSize = 0;
        CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
        couponBatchDTO.setCouponRelType(1);
        couponBatchDTO.setCouponRelId(couponBatchId);
        List<CouponBatchDTO> couponBatchDTOList = couponBatchFacade.findCouponBatchAll(couponBatchDTO);
        for (CouponBatchDTO couponBatchDTO_ : couponBatchDTOList) {
            CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
            couponUnitDTO.setCouponBatchId(couponBatchDTO_.getId());
            List<CouponUnitDTO> couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO);
            couponUnitListSize += couponUnitDTOList.size();
            if (EmptyUtil.isNotEmpty(couponUnitDTOList) && isEdit)
                return couponUnitDTOList.size();
        }
        return couponUnitListSize;
    }

}
