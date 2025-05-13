package com.egeo.components.promotion.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.service.read.CouponBatchReadService;
import com.egeo.components.promotion.service.read.CouponReadService;
import com.egeo.components.promotion.service.read.CouponUnitReadService;
import com.egeo.components.promotion.service.read.ExchangeBatchReadService;
import com.egeo.components.promotion.service.read.ExchangeOrderRecordReadService;
import com.egeo.components.promotion.service.write.ExchangeBatchWriteService;
import com.egeo.components.promotion.service.write.ExchangeOrderRecordWriteService;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;


@Component
public class ExchangeBatchFacade {

    @Autowired
    private ExchangeBatchReadService exchangeBatchReadService;

    @Autowired
    private ExchangeBatchWriteService exchangeBatchWriteService;

    @Autowired
    private CouponBatchReadService couponBatchReadService;

    @Autowired
    private CouponUnitReadService couponUnitReadService;

    @Autowired
    private CouponReadService couponReadService;
    @Autowired
    private ExchangeOrderRecordWriteService exchangeOrderRecordWriteService;
    @Autowired
    private ExchangeOrderRecordReadService exchangeOrderRecordReadService;
    @Autowired
    private UserClient userReadService;

    public ExchangeBatchDTO findExchangeBatchById(ExchangeBatchDTO dto) {

        return exchangeBatchReadService.findExchangeBatchById(dto);
    }

    public PageResult<Map<String, Object>> findExchangeBatchOfPage(ExchangeBatchDTO dto, Pagination page) {
        PageResult<ExchangeBatchDTO> exchangeBatchOfPage = exchangeBatchReadService.findExchangeBatchOfPage(dto, page);
        List<ExchangeBatchDTO> dtoList = exchangeBatchOfPage.getList();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (EmptyUtil.isNotEmpty(dtoList)) {
            for (ExchangeBatchDTO dto2 : dtoList) {
                //组装coupon
                CouponBatchDTO couponBatchDTO = new CouponBatchDTO();
                couponBatchDTO.setId(dto2.getBatchId());
                CouponBatchDTO couponBatchDTO2 = couponBatchReadService.findCouponBatchById(couponBatchDTO);

                Map<String, Object> map = new HashMap<String, Object>();
                CouponDTO couponDTO = new CouponDTO();
                Long couponId = couponBatchDTO2.getCouponRelId();
                couponDTO.setId(couponId);
                CouponDTO couponDTO2 = couponReadService.findCouponById(couponDTO);
                map.put("title", couponDTO2.getTitle());
                map.put("detail", couponDTO2.getDetail());
                map.put("couponType", couponDTO2.getCouponType());

                map.put("couponBatchCode", couponBatchDTO2.getCouponBatchCode());
                if (couponBatchDTO2.getEffectStartTime() != null && couponBatchDTO2.getEffectEndTime() != null) {
                    map.put("effectDays", null);
                    map.put("effectTime", DateUtils.getDefaultDate(couponBatchDTO2.getEffectStartTime()) + "至"
                            + DateUtils.getDefaultDate(couponBatchDTO2.getEffectEndTime()));
                } else {
                    map.put("effectDays", couponBatchDTO2.getEffectDays() == -1
                            ? "不限时间" : couponBatchDTO2.getEffectDays() + "天");
                    map.put("effectTime", null);
                }
                map.put("receiveTime", couponBatchDTO2.getReceiveStartTime() != null && couponBatchDTO2.getReceiveEndTime() != null ?
                        DateUtils.getDefaultDate(couponBatchDTO2.getReceiveStartTime()) + "至"
                                + DateUtils.getDefaultDate(couponBatchDTO2.getReceiveEndTime()) : null);
                map.put("isDisplay", couponBatchDTO2.getIsDisplay());
                map.put("isRepeat", couponBatchDTO2.getIsRepeat());
                map.put("isDisplay", couponBatchDTO2.getIsDisplay());
                map.put("id", couponBatchDTO2.getId());
                map.put("getType", couponBatchDTO2.getGetType());
                map.put("sort", dto2.getSort());
                map.put("addPrice", dto2.getAddPrice());
                if(couponBatchDTO2.getCouponBatchName() == null){
                    map.put("couponBatchName","");
                }else {
                    map.put("couponBatchName",couponBatchDTO2.getCouponBatchName());
                }

                list.add(map);
            }
        }
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(exchangeBatchOfPage.getPageNo());
        result.setPageSize(exchangeBatchOfPage.getPageSize());
        result.setTotalSize(exchangeBatchOfPage.getTotalSize());
        return result;
    }

    public List<ExchangeBatchDTO> findExchangeBatchAll(ExchangeBatchDTO dto) {

        return exchangeBatchReadService.findExchangeBatchAll(dto);

    }

    public Long insertExchangeBatchWithTx(ExchangeBatchDTO dto) {

        return exchangeBatchWriteService.insertExchangeBatchWithTx(dto);
    }

    public int updateExchangeBatchWithTx(ExchangeBatchDTO dto) {

        return exchangeBatchWriteService.updateExchangeBatchWithTx(dto);
    }

    public int deleteExchangeBatchWithTx(ExchangeBatchDTO dto) {

        return exchangeBatchWriteService.deleteExchangeBatchWithTx(dto);

    }

    public List<Long> findExchangeByBatchId(Long batchId) {
        ExchangeBatchDTO dto = new ExchangeBatchDTO();
        dto.setBatchId(batchId);
        dto.setType(1);
        return exchangeBatchReadService.findExchangeIdsByBatch(dto);
    }

    public Boolean exchangeRightNow(Long userId, String userName, Long exchangeCouponBatchId, Long exchangeCouponUnitId, Long exchangeId) {
        UserDTO userByID = userReadService.findUserByID(userId);
        return exchangeOrderRecordWriteService.insertExchangeOrderRecordAndCouponWithTx(userByID.getMail(), userId, exchangeCouponBatchId, exchangeCouponUnitId, exchangeId);
    }

    public List<ExchangeOrderRecordDTO> findExchangeOrderRecord(ExchangeOrderRecordDTO exchangeOrderRecordDTO) {
        return exchangeOrderRecordReadService.findExchangeOrderRecordLived(exchangeOrderRecordDTO);
    }

    //通过优惠券状态和旧批次id查询有效活动数
    public List<Long> findExchangeActivityByOldCouponUnitId(Long batchId, Integer couponUnitStatus) {
        return exchangeBatchReadService.findExchangeActivityByOldCouponUnitId(batchId, couponUnitStatus);
    }
}
