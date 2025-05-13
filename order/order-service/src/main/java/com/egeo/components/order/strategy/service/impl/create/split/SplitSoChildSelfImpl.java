package com.egeo.components.order.strategy.service.impl.create.split;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.strategy.vo.SplitSoChildReqVO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 1:28
 * @Version V1.0
 **/
@Service("splitSoChildSelfImpl")
public class SplitSoChildSelfImpl extends SplitSoChildCommonBase{
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.SELF.getCode();
    }

    @Override
    public JsonResult<SplitSoChildRespVO> splitSoChild(SplitSoChildReqVO reqVO) {
        List<Long> supplierIdList = reqVO.getSupplierIdList();
        String orderCode = reqVO.getOrderCode();
        List<SoItemDTO> soItems = reqVO.getSoItems();
        int m = reqVO.getM();
        int n = reqVO.getN();
        Long mId = reqVO.getmId();
        JSONObject remarkObj = reqVO.getRemarkObj();
        JSONObject deliveryPriceObj = reqVO.getDeliveryPriceObj();
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        Map<Long,String> selfChildCodeMap = new HashMap<>();
        //自营按照供应商拆单
        for (Long sId : supplierIdList) {
            m++;
            SoChildDTO sc = new SoChildDTO();
            String childCode = orderCode + "-" + n+ "-" + m;
            sc.setChildCode(childCode);
            sc.setPerformingParty(mId);
            sc.setSupplierId(sId);
            sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
            sc.setOrdinaryDeliveryFee(deliveryPriceObj.getBigDecimal(String.valueOf(mId)));
            sc.setNeedCountDeliveryFee(1);
            selfChildCodeMap.put(sId, childCode);
            soChildDTOList.add(sc);
        }
        for (SoItemDTO soItemDTO : soItems) {
            if(soItemDTO.getMerchantId()==1L) {
                soItemDTO.setChildCode(selfChildCodeMap.get(soItemDTO.getSupplierId()));
            }
        }
        SplitSoChildRespVO splitSoChildRespVO = new SplitSoChildRespVO();
        splitSoChildRespVO.setSoChildDTOList(soChildDTOList);
        splitSoChildRespVO.setmId(mId);

        return JsonResult.success(splitSoChildRespVO);
    }
}
