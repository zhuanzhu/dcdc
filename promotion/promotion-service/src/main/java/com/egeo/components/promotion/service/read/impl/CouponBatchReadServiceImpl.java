package com.egeo.components.promotion.service.read.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.CouponBatchReadService;
import com.egeo.components.promotion.manage.read.CouponBatchReadManage;
import com.egeo.components.promotion.condition.CouponBatchCondition;
import com.egeo.components.promotion.converter.CouponBatchConverter;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.po.CouponBatchPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("couponBatchReadService")
public class CouponBatchReadServiceImpl implements CouponBatchReadService {
    @Autowired
    private CouponBatchReadManage couponBatchReadManage;

    @Override
    public CouponBatchDTO findCouponBatchById(CouponBatchDTO dto) {
        CouponBatchPO po = CouponBatchConverter.toPO(dto);
        CouponBatchPO list = couponBatchReadManage.findCouponBatchById(po);
        return CouponBatchConverter.toDTO(list);
    }

    @Override
    public PageResult<CouponBatchDTO> findCouponBatchOfPage(CouponBatchDTO dto, Pagination page) {
        CouponBatchPO po = CouponBatchConverter.toPO(dto);
        PageResult<CouponBatchPO> pageResult = couponBatchReadManage.findCouponBatchOfPage(po, page);

        List<CouponBatchDTO> list = CouponBatchConverter.toDTO(pageResult.getList());
        PageResult<CouponBatchDTO> result = new PageResult<CouponBatchDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<CouponBatchDTO> findCouponBatchAll(CouponBatchDTO dto) {
        CouponBatchPO po = CouponBatchConverter.toPO(dto);
        List<CouponBatchPO> list = couponBatchReadManage.findCouponBatchAll(po);
        return CouponBatchConverter.toDTO(list);
    }

    @Override
    public PageResult<CouponBatchDTO> findCouponBatchOfPageByBlurry(CouponBatchDTO dto, Pagination page) {
        CouponBatchPO po = CouponBatchConverter.toPO(dto);
        PageResult<CouponBatchCondition> pageResult = couponBatchReadManage.findCouponBatchOfPageByBlurry(po, dto.getTitle(), page);

        List<CouponBatchDTO> list = CouponBatchConverter.conditionToDTO(pageResult.getList());
        PageResult<CouponBatchDTO> result = new PageResult<CouponBatchDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<CouponBatchDTO> findCouponBatchByCouponIds(CouponBatchDTO dto, List<Long> couponIdList, Map<String, Object> key) {
        CouponBatchPO po = CouponBatchConverter.toPO(dto);
        List<CouponBatchPO> list = couponBatchReadManage.findCouponBatchByCouponIds(po, couponIdList, key);
        return CouponBatchConverter.toDTO(list);
    }
    
    @Override
    public List<CouponBatchDTO> findRegisterCouponBatchByCouponId(List<Long> couponIdList, Long platformId) {
    	List<CouponBatchCondition> list = couponBatchReadManage.findRegisterCouponBatchByCouponId(couponIdList, platformId);
    	return CouponBatchConverter.conditionToDTO(list);
    }
    
    @Override
    public List<CouponBatchDTO> findRegisterCouponBatchByCouponGroup(List<Long> couponIdList, Long platformId) {
    	List<CouponBatchCondition> list = couponBatchReadManage.findRegisterCouponBatchByCouponGroup(couponIdList, platformId);
    	return CouponBatchConverter.conditionToDTO(list);
    }

    @Override
    public Long findCouponBatchCount(CouponBatchDTO couponBatchDTO) {
        return couponBatchReadManage.findCouponBatchCount(CouponBatchConverter.toPO(couponBatchDTO));
    }

    /**
     * 根据类型查询以旧换新功能中的新旧批次
     *
     * @param batchIdList
     * @param title
     * @param couponBatchCode
     * @param type
     * @param platformId
     * @param page    @return
     * */
    @Override
    public PageResult<CouponBatchDTO> findCouponBatchByParam(List<Long> batchIdList, String title, String couponBatchCode,String couponBatchName,Integer type, Long platformId, Pagination page) {
        PageResult<CouponBatchCondition> couponBatchByParam = couponBatchReadManage.findCouponBatchByParam(batchIdList,title,couponBatchCode,couponBatchName,type, platformId, page);
        List<CouponBatchDTO> list = CouponBatchConverter.conditionToDTO(couponBatchByParam.getList());
        PageResult<CouponBatchDTO> result = new PageResult<CouponBatchDTO>();
        result.setList(list);
        result.setPageNo(couponBatchByParam.getPageNo());
        result.setPageSize(couponBatchByParam.getPageSize());
        result.setTotalSize(couponBatchByParam.getTotalSize());
        return result;

    }

    //根据excahngeId查询所有的优惠券批次
    @Override
    public List<CouponBatchDTO> findCouponBatchByExchange(Long exchangeId) {
        return CouponBatchConverter.conditionToDTO(couponBatchReadManage.findCouponBatchByExchange(exchangeId));
    }

    @Override
    public List<CouponBatchDTO> findCouponBatchAllByBatchCode(String newBatchCode) {
        CouponBatchPO batchPO = new CouponBatchPO();
        batchPO.setCouponBatchCode(newBatchCode);
        return CouponBatchConverter.toDTO(couponBatchReadManage.findCouponBatchAll(batchPO));
    }
}
	