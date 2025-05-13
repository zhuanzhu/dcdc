package com.egeo.components.order.strategy.service.impl.create.split;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.common.ProductChannelCodeEnum;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.manage.write.CakeAddressWriteManage;
import com.egeo.components.order.strategy.vo.SplitSoChildReqVO;
import com.egeo.components.order.strategy.vo.SplitSoChildRespVO;
import com.egeo.components.order.vo.world.WroldSplitOrderRuleVO;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 2:13
 * @Version V1.0
 **/
@Service("splitSoChildWorldBuyImpl")
public class SplitSoChildWorldBuyImpl extends SplitSoChildCommonBase{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CakeAddressWriteManage cakeAddressWriteManage;

    @Override
    public String getChannelCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public JsonResult<SplitSoChildRespVO> splitSoChild(SplitSoChildReqVO reqVO) {
        List<SoItemDTO> soItems = reqVO.getSoItems();
        String orderCode = reqVO.getOrderCode();
        Long mId = reqVO.getmId();
        JSONObject remarkObj = reqVO.getRemarkObj();
        int n = reqVO.getN();
        Map<String, String> worldChildCodeMap = new HashMap<>();
        List<SoChildDTO> soChildDTOList = new ArrayList<>();
        //全球购拆单
        List<String> ruleIdList = new ArrayList<>();
        for (SoItemDTO soItemDTO : soItems) {
            if (soItemDTO.getMerchantId() == 8L) {
                soItemDTO.setRuleId("0");
                if(EmptyUtil.isNotEmpty(soItemDTO.getSplitOrderRule())){
                    WroldSplitOrderRuleVO splitOrderRuleVO = JSONObject.parseObject(soItemDTO.getSplitOrderRule(),WroldSplitOrderRuleVO.class);
                    String ruleId =splitOrderRuleVO.getGoodsType()+""+splitOrderRuleVO.getStore_id()+""+splitOrderRuleVO.getStore_isCombineOrders();
                    //仓库不允许合并下单
                    if(!splitOrderRuleVO.getStore_isCombineOrders()){
                        ruleId = ruleId+soItemDTO.getExternalProductId();
                    }
                    soItemDTO.setRuleId(ruleId);
                    if(!ruleIdList.contains(ruleId)){
                        ruleIdList.add(ruleId);
                    }
                }
            }
        }
        //兼容没有拆单规则的情况
        if(CollectionUtils.isEmpty(ruleIdList)){
            ruleIdList.add("0");
        }
        logger.info("全球购拆单规则ids:{}", JSON.toJSONString(ruleIdList));
        for (int i = 0; i < ruleIdList.size(); i++) {
            String ruleId = ruleIdList.get(i);
            SoChildDTO sc = new SoChildDTO();
            String childCode = orderCode + "-" + n+ "-W-" +i;
            sc.setChildCode(childCode);
            sc.setPerformingParty(mId);
            sc.setRemark(EmptyUtil.isEmpty(remarkObj) ? null : remarkObj.getString(String.valueOf(mId)));
            //全球购都没运费
            sc.setOrdinaryDeliveryFee(BigDecimal.ZERO);
            sc.setDeliveryFee(BigDecimal.ZERO);
            sc.setNeedCountDeliveryFee(1);
            worldChildCodeMap.put(ruleId, childCode);
            soChildDTOList.add(sc);
        }
        for (SoItemDTO soItemDTO : soItems) {
           if(Objects.equals(soItemDTO.getMerchantId(), ThirdConst.Merchant.WORLD)){
                soItemDTO.setChildCode(worldChildCodeMap.get(soItemDTO.getRuleId()));
            }
        }
        SplitSoChildRespVO splitSoChildRespVO = new SplitSoChildRespVO();
        splitSoChildRespVO.setSoChildDTOList(soChildDTOList);
        splitSoChildRespVO.setmId(mId);
        return JsonResult.success(splitSoChildRespVO);
    }
}
