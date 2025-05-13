package com.egeo.components.product.business.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StoreManage;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.facade.StoreFacade;
import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("store")
public class StoreManageImpl implements StoreManage {


    @Resource(name = "storeFacade")
    private StoreFacade storeFacade;

    @Override
    public StoreDTO findStoreById(StoreDTO dto) {
        return storeFacade.findStoreById(dto);
    }

    @Override
    public PageResult<StoreDTO> findStoreOfPage(StoreDTO dto, Pagination page) {
        return storeFacade.findStoreOfPage(dto, page);
    }

    @Override
    public List<StoreDTO> findStoreAll(StoreDTO dto) {
        return storeFacade.findStoreAll(dto);
    }

    @Override
    public Long insertStoreWithTx(StoreDTO dto) {
        return storeFacade.insertStoreWithTx(dto);
    }

    @Override
    public int updateStoreWithTx(StoreDTO dto) {
        return storeFacade.updateStoreWithTx(dto);
    }

    @Override
    public int deleteStoreWithTx(StoreDTO dto) {
        return storeFacade.deleteStoreWithTx(dto);
    }

    @Override
    public List<StoreDTO> findRootStoreAll(StoreDTO dto) {
        return storeFacade.findRootStoreAll(dto);
    }

    @Override
    public PageResult<StoreDTO> findRootStoreOfPage(StoreDTO dto, Pagination page) {
        return storeFacade.findRootStoreOfPage(dto, page);
    }

    @Override
    public List<StoreDTO> findStoreAllByTreeId(Long storeTreeId) {
        // TODO Auto-generated method stub
        return storeFacade.findStoreAllByTreeId(storeTreeId);
    }

    @Override
    public PageResult<StoreDTO> findStoreOfPage(String flag, StoreDTO dto, Pagination page) {
        if (EmptyUtil.isEmpty(flag)) {
            if (EmptyUtil.isNotEmpty(dto.getCouponId())) {
                //根据优惠券标识查询门店信息
                Set<Long> stordIds = new HashSet<>();
                CouponStoreDTO couponStoreDTO = new CouponStoreDTO();
                couponStoreDTO.setCouponId(dto.getCouponId());
                List<CouponStoreDTO> ls = storeFacade.findCouponStoreAll(couponStoreDTO);
                for (CouponStoreDTO obj : ls) {
                    stordIds.add(obj.getStoreId());
                }
                List re = new ArrayList(stordIds);
                dto.setIds(re);
            }
            return storeFacade.findStoreOfPage(dto, page);
        } else if (EmptyUtil.isNotEmpty(flag) && StringUtils.equalsIgnoreCase("-1", flag)){
            // -1 查询所有
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setPlatformId(dto.getPlatformId());
            return storeFacade.findStoreOfPage(storeDTO, page);
        }
        return null;
    }
    
    
    @Override
    public List<Map<String, Object>> findCouponStore(StoreDTO dto) {
    	List<Map<String, Object>> resultList = new ArrayList<>();
    	if (EmptyUtil.isNotEmpty(dto.getCouponId())) {
            //根据优惠券标识查询门店信息
            List<Long> stordIds = new ArrayList<>();
            CouponStoreDTO couponStoreDTO = new CouponStoreDTO();
            couponStoreDTO.setCouponId(dto.getCouponId());
            List<CouponStoreDTO> couponStoreList = storeFacade.findCouponStoreAll(couponStoreDTO);
            if (EmptyUtil.isNotEmpty(couponStoreList)) {
            	for (CouponStoreDTO cs : couponStoreList) {
                	stordIds.add(cs.getStoreId());
                }
                dto.setIds(stordIds);
                List<StoreDTO> storeList= storeFacade.findCouponStore(dto);
                for (StoreDTO store : storeList) {
                	Map<String, Object> storeMap = new HashMap<>();
                	storeMap.put("id", store.getId());
                	storeMap.put("name", store.getName());
                	resultList.add(storeMap);
                }
            }
        }
    	return resultList;
    }

    @Override
    public JsonResult<Map<String, String>> getStoreInfo(Long storeId, Long platformId) {

        //根据storeid查询所属总店
        StoreDTO store=storeFacade.getHeadStoreId(storeId);
        String headStoreName = store.getName();
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(storeId);
        StoreDTO storeById = storeFacade.findStoreById(storeDTO);
        if(EmptyUtil.isEmpty(storeById)){
            return JsonResult.fail(storeId + "不存在");
        }
        String storeName = storeById.getName();

        String platformName=null;
        if(platformId==2){
            platformName = "富宏云采";
        }else if(platformId==7){
            platformName = "大厨管家";
        }
        Map<String, String> data = new HashMap<>();
        data.put("platformName", platformName);
        data.put("headStoreName", headStoreName);
        data.put("storeName", storeName);
        return JsonResult.success(data);
    }


    @Override
	public StoreDTO findStoreByNodeId(Long nodeId) {

		return storeFacade.findStoreByNodeId(nodeId);
	}

	@Override
	public List<StoreDTO> findStoreByPlatformIdAndStoreMenu(StoreDTO dto) {
		return storeFacade.findStoreByPlatformIdAndStoreMenu(dto);
	}
}
	