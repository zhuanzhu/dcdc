package com.egeo.components.promotion.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.common.NormalConstant;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.promotion.business.CouponManage;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.facade.CouponFacade;
import com.egeo.components.promotion.facade.CouponGroupFacade;
import com.egeo.components.promotion.facade.CouponUnitFacade;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("coupon")
public class CouponManageImpl implements CouponManage {

    private static final String DEFAULT_ICON_URL = "http://image.51gouxiang.com/20180712165228egeo111535";

    @Resource(name = "couponFacade")
    private CouponFacade couponFacade;

    @Resource(name = "couponGroupFacade")
    private CouponGroupFacade couponGroupFacade;

    @Resource(name = "couponUnitFacade")
    private CouponUnitFacade couponUnitFacade;

    @Override
    public PageResult<CouponDTO> findCouponOfPage(CouponDTO dto, Pagination page) {
        return couponFacade.findCouponOfPage(dto, page);
    }

    @Override
    public List<CouponDTO> findCouponAll(CouponDTO dto) {
        return couponFacade.findCouponAll(dto);
    }

    @Override
    public Long insertCouponWithTx(CouponDTO dto) {
        return couponFacade.insertCouponWithTx(dto);
    }

    @Override
    public int updateCouponWithTx(CouponDTO dto) {
        return couponFacade.updateCouponWithTx(dto);
    }

    @Override
    public int deleteCouponWithTx(CouponDTO dto) {
        return couponFacade.deleteCouponWithTx(dto);
    }

    @Override
    public JsonResult<Map<String, Object>> insertOrUpdateCouponWithTx(String flag, CouponDTO dto) {
        // 参数校验
        if (EmptyUtil.isEmpty(dto.getTitle()))
            return JsonResult.fail("请填写优惠卷标题");
        if (dto.getTitle().length() > 10)
            return JsonResult.fail("优惠卷标题字数不得超过10");

        if (EmptyUtil.isEmpty(dto.getCouponType()))
            return JsonResult.fail("请选择优惠卷类型");
        if (dto.getCouponType() == 0) {
            // 满减卷
            if (EmptyUtil.isEmpty(dto.getDiscountAmount()))
                return JsonResult.fail("请填写优惠金额");
            if (EmptyUtil.isEmpty(dto.getTriggerAmount()))
                return JsonResult.fail("请填写触发金额");
            if (dto.getDiscountAmount().compareTo(dto.getTriggerAmount()) > 0)
                return JsonResult.fail("优惠金额不能大于触发金额");
        } else if (dto.getCouponType() == 1) {
            // 兑换卷
            if (EmptyUtil.isEmpty(dto.getDiscountAmount()))
                return JsonResult.fail("请填写商品价值");
            // 自定义icon
            if (EmptyUtil.isEmpty(dto.getIconUrl()))
                dto.setIconUrl(DEFAULT_ICON_URL);
            dto.setUsageCount(1);
        }

        if (EmptyUtil.isEmpty(dto.getGoodsType()))
            return JsonResult.fail("请选择商品");
        if (EmptyUtil.isEmpty(dto.getGoodsId()))
            return JsonResult.fail("请选择商品su");
        if (EmptyUtil.isEmpty(dto.getUsageCount()))
            return JsonResult.fail("请填写可使用次数");
        if (EmptyUtil.isEmpty(dto.getJumpType()))
            return JsonResult.fail("请选择跳转配置");
        if (EmptyUtil.isEmpty(dto.getDetail()))
            return JsonResult.fail("请填写优惠详情");
        if (dto.getDetail().length() > 200)
            return JsonResult.fail("优惠详情字数不得超过200");
        // -1 关联所有门店
        if (StringUtils.equalsIgnoreCase("-1", flag)) {
            List<StoreDTO> storeDTOList = couponFacade.findRootStoreAll(new StoreDTO());
            List<Long> storeList = new ArrayList<>();
            for (StoreDTO storeDTO:storeDTOList) {
                storeList.add(storeDTO.getId());
            }
            dto.setStoreList(storeList);
        }
        CouponDTO findCouponDTO = new CouponDTO();
        findCouponDTO.setTitle(dto.getTitle());
        List<CouponDTO> dbCouponDTOList = couponFacade.findCouponAll(findCouponDTO);
        if (dto.getId() == null) {
        	if (EmptyUtil.isNotEmpty(dbCouponDTOList)) {
        		return JsonResult.fail("优惠券名称不能重复");
        	}
            // 新增优惠卷
            // 设置优惠卷编号
            dto.setCouponCode(SequenceUtil.genCouponNo(dto.getCouponType()));

            // 新增优惠卷不需要修改人
            dto.setUpdateUser(null);

            CouponDTO couponDTO = new CouponDTO();
            couponDTO.setCouponCode(dto.getCouponCode());
            List<CouponDTO> couponDTOList = couponFacade.findCouponAll(couponDTO);
            if (EmptyUtil.isNotEmpty(couponDTOList))
                return JsonResult.fail("优惠卷编号重复,请重试");
            couponFacade.insertCouponWithTx(dto);
        } else {
        	if (EmptyUtil.isNotEmpty(dbCouponDTOList)) {
        		for (CouponDTO c : dbCouponDTOList) {
        			if (!c.getId().equals(dto.getId())) {
        				return JsonResult.fail("优惠券名称不能重复");
        			}
        		}
        	}
            // 编辑优惠卷
            CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
            couponUnitDTO.setCouponId(dto.getId());
            List<CouponUnitDTO> couponUnitDTOList = couponUnitFacade.findCouponUnitAll(couponUnitDTO);
            if (EmptyUtil.isNotEmpty(couponUnitDTOList))
                return JsonResult.fail("该优惠券已发放，不可编辑");
            dto.setCouponCode(null); // 优惠卷编号不可以编辑
            couponFacade.updateCouponWithTx(dto);
        }

        return JsonResult.success(null);
    }

    @Override
    public PageResult<Map<String, Object>> findCouponOfPageByBlurry(CouponDTO dto, List<Long> couponIdList,
                                                                    Pagination page) {
        if (EmptyUtil.isNotEmpty(dto)) {
            if (EmptyUtil.isNotBlank(dto.getStoreName())) {
                List<Long> storeIds = new ArrayList<>();
                StoreDTO storeDTO = new StoreDTO();
                storeDTO.setName(dto.getStoreName());
                List<StoreDTO> storeList = couponFacade.findRootStoreAll(storeDTO);
                for (StoreDTO obj : storeList) {
                    storeIds.add(obj.getId());
                }
                dto.setStoreList(storeIds);
            }
        }
        PageResult<CouponDTO> rt = couponFacade.findCouponOfPageByBlurry(dto, couponIdList, page);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (CouponDTO couponDTO : rt.getList()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", couponDTO.getId());
            map.put("couponCode", couponDTO.getCouponCode());
            map.put("title", couponDTO.getTitle());
            map.put("couponType", couponDTO.getCouponType());
            map.put("discountAmount", couponDTO.getDiscountAmount());
            map.put("triggerAmount", couponDTO.getTriggerAmount());
            map.put("goodsType", couponDTO.getGoodsType());
            map.put("goodsId", couponDTO.getGoodsId());
            map.put("usageCount", couponDTO.getUsageCount());
            map.put("jumpType", couponDTO.getJumpType());
            map.put("createTime", DateUtils.format(DateUtils.DATE_TIME_FORMAT, couponDTO.getCreateTime()));
            map.put("updateTime", !couponDTO.getUpdateTime().equals(couponDTO.getCreateTime()) ?
                    DateUtils.format(DateUtils.DATE_TIME_FORMAT, couponDTO.getUpdateTime()) : null);
            map.put("detail", couponDTO.getDetail());
            // 查询修改人信息
            if (couponDTO.getUpdateUser() != null) {
                UserDTO userDTO = couponFacade.queryUserById(couponDTO.getUpdateUser());
                map.put("updateUser", userDTO != null ? userDTO.getLoginName() : null);
            } else {
                map.put("updateUser", null);
            }
            // 判断优惠卷是否可以编辑
            CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
            couponBatchDTO.setCouponRelId(couponDTO.getId());
            Long couponBatchAll = couponUnitFacade.findCouponBatchCount(couponBatchDTO);
            map.put("isEdit", EmptyUtil.isEmpty(couponBatchAll)||couponBatchAll <= 0 ? 1 : 0);
            map.put("storeList", null); //TODO 目前门店为空
            list.add(map);
        }

        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());

        return result;
    }

    @Override
    public List<Map<String, Object>> findCouponCompanyOfPage(Long platformId, Long id, Integer type, Pagination page) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();

        if (type.equals(Integer.valueOf(0))) {
            // 优惠卷
            companyDTOList = getCouponCompanyByCouponId(platformId, id);

        } else if (type.equals(Integer.valueOf(1))) {
            // 优惠分组
            List<CouponGroupRelDTO> couponGroupRelDTOList = couponGroupFacade.queryCouponGroupRelByCouponId(id);
            boolean isNotFirstCoupon = false;
            for (CouponGroupRelDTO couponGroupRelDTO : couponGroupRelDTOList) {
                List<CompanyDTO> temp = getCouponCompanyByCouponId(platformId, couponGroupRelDTO.getCouponId());
                List<CompanyDTO> tempList = new ArrayList<>();
                if (isNotFirstCoupon) {
//					companyDTOList.retainAll(temp);
                    for (CompanyDTO companyDTO : companyDTOList) {
                        for (CompanyDTO companyDTO_ : temp) {
                            if (companyDTO.getId().equals(companyDTO_.getId())) {
                                tempList.add(companyDTO);
                            }
                        }
                    }
                    companyDTOList = tempList;

                } else {
                    companyDTOList.addAll(temp);
                }
                isNotFirstCoupon = true;
            }
        }

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (CompanyDTO companyDTO : companyDTOList) {
            addCouponCompany(companyDTO, list);
        }

        return list;
    }

    private void addCouponCompany(CompanyDTO companyDTO, List<Map<String, Object>> list) {
        if (companyDTO.getId().intValue() > 0) {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", companyDTO.getId());
            map.put("companyName", companyDTO.getCompanyName());
            map.put("createTime", DateUtils.format("yyyy-MM-dd HH:mm", companyDTO.getCreateTime()));
            map.put("companyContent",
                    EmptyUtil.isNotBlank(companyDTO.getCompanyContent()) ? companyDTO.getCompanyContent() : "无");

            map.put("empCount", couponFacade.findUserCountByCompanyId(companyDTO.getId()));

            list.add(map);
            int count = 0;
            for (Map<String, Object> map_ : list) {
                if (map_.get("id").equals(map.get("id"))) {
                    // 公司去重添加
                    count++;
                    if (count == 2) {
                        list.remove(map);
                        break;
                    }
                }
            }

        }
    }

    /**
     * 通过优惠卷id获取其下所有的关联公司
     *
     * @param couponId
     * @return
     */
    private List<CompanyDTO> getCouponCompanyByCouponId(Long platformId, Long couponId) {
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        List<CouponCompanyDTO> list = couponFacade.findCouponCompanyAll(couponId);
        for (CouponCompanyDTO couponCompanyDTO : list) {

            Integer companyType = NormalConstant.getCompanyTypeByCompanyId(couponCompanyDTO.getCompanyId());
            if (companyType != null) {
                List<CompanyDTO> teamCompanyList = couponFacade.findCouponCompanyAll(platformId, companyType);
                companyDTOList.addAll(teamCompanyList);
            } else {
                CompanyDTO tempCompany = couponFacade.findCouponCompanyById(couponCompanyDTO.getCompanyId());
                companyDTOList.add(tempCompany);
            }
        }

        return companyDTOList;
    }

    @Override
    public List<Map<String, Object>> findCouponGoodsAll(CouponDTO dto) {
        CouponDTO dto_ = couponFacade.findCouponById(dto);
        if (EmptyUtil.isEmpty(dto_))
            return null;
        return couponFacade.findCouponGoodsAll(dto_);
    }

    @Override
    public Map<String, Object> findCouponById(CouponDTO dto) {
        CouponDTO dto_ = couponFacade.findCouponById(dto);
        if (EmptyUtil.isEmpty(dto_))
            return null;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", dto_.getId());
        map.put("couponCode", dto_.getCouponCode());
        map.put("title", dto_.getTitle());
        map.put("couponType", dto_.getCouponType());
        map.put("discountAmount", dto_.getDiscountAmount() != null ? dto_.getDiscountAmount().intValue() : null);
        map.put("triggerAmount", dto_.getTriggerAmount() != null ? dto_.getTriggerAmount().intValue() : null);
        map.put("usageCount", dto_.getUsageCount());
        map.put("jumpType", dto_.getJumpType());
        map.put("iconUrl", dto_.getIconUrl());
        map.put("detail", dto_.getDetail());
        map.put("goodsType", dto_.getGoodsType());
        map.put("goodsId", dto_.getGoodsId());

        // 选择的商品或商品组的名称
        if (dto_.getGoodsType() == 0) {
            // 单su
            StandardUnitDTO su = couponFacade.findStandardUnitById(dto_.getGoodsId());
            map.put("suName", su.getName());
        } else if (dto_.getGoodsType() == 1) {
            // 商品组
            StandardUnitCombinationDTO sus = couponFacade.findStandardUnitCombinationById(dto_.getGoodsId());
            map.put("suName", sus.getCombinationName());
        }

        // 所属公司
        List<CouponCompanyDTO> couponCompanyDTOList = couponFacade.findCouponCompanyAll(dto_.getId());
        List<Long> companyList = new ArrayList<>();
        for (CouponCompanyDTO couponCompanyDTO : couponCompanyDTOList) {
            /*
             * CompanyDTO companyDTO =
             * couponFacade.findCouponCompanyById(couponCompanyDTO.getCompanyId(
             * )); if (EmptyUtil.isNotEmpty(companyDTO)){ }
             */
            companyList.add(couponCompanyDTO.getCompanyId());

        }
        map.put("companyList", companyList);

        // 优惠卷下的优惠卷unit数量
        CouponUnitDTO couponUnitDTO = new CouponUnitDTO();
        couponUnitDTO.setCouponId(dto_.getId());
        Long couponUnitSize = couponUnitFacade.findCouponUnitAllCount(couponUnitDTO);
        map.put("couponUnitCount", couponUnitSize);

        //查询该优惠券已关联的门店ids
        List<Long> stores = new ArrayList<>();
        CouponStoreDTO couponStoreDTO = new CouponStoreDTO();
        couponStoreDTO.setCouponId(dto_.getId());
        List<CouponStoreDTO> couponStoreList= couponUnitFacade.findCouponStoreAll(couponStoreDTO);
        for (CouponStoreDTO obj:couponStoreList) {
            stores.add(obj.getStoreId());
        }
        map.put("stores", stores);
        return map;
    }

}
