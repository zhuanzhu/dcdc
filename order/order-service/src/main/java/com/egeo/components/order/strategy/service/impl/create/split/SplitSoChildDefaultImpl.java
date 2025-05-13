package com.egeo.components.order.strategy.service.impl.create.split;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.strategy.vo.SplitSoChildReqVO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 2:17
 * @Version V1.0
 **/
@Service("splitSoChildDefaultImpl")
public class SplitSoChildDefaultImpl extends SplitSoChildCommonBase{
    @Override
    public String getChannelCode() {
       return ProductChannelCodeEnum.DEFAULT.getCode();
    }

    @Override
    public JsonResult<SplitSoChildRespVO> splitSoChild(SplitSoChildReqVO reqVO) {
        String orderCode = reqVO.getOrderCode();
        List<SoItemDTO> soItems = reqVO.getSoItems();
        Long mId = reqVO.getmId();
        JSONObject remarkObj = reqVO.getRemarkObj();
        int n = reqVO.getN();
        JSONObject deliveryPriceObj = reqVO.getDeliveryPriceObj();
        Map<Long, String> childCodeMap = new HashMap<>();
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        SoChildDTO sc = new SoChildDTO();
        String childCode = orderCode + "-" + n;
        sc.setChildCode(childCode);
        sc.setPerformingParty(mId);
        sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
        sc.setOrdinaryDeliveryFee(deliveryPriceObj.getBigDecimal(String.valueOf(mId)));
        sc.setNeedCountDeliveryFee(1);
        childCodeMap.put(mId, childCode);
        soChildDTOList.add(sc);

        for (SoItemDTO soItemDTO : soItems) {
            if(Objects.equals(soItemDTO.getMerchantId(), mId)){
                soItemDTO.setChildCode(childCodeMap.get(soItemDTO.getMerchantId()));
            }
        }

        SplitSoChildRespVO splitSoChildRespVO = new SplitSoChildRespVO();
        splitSoChildRespVO.setSoChildDTOList(soChildDTOList);
        splitSoChildRespVO.setmId(mId);

        return JsonResult.success(splitSoChildRespVO);
    }
}
