package com.egeo.components.order.strategy.service.impl.create.item;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.strategy.vo.CreateOrderReqVO;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 22:47
 * @Version V1.0
 **/
@Service("addItemCreateOrderQMImpl")
public class AddItemCreateOrderQMImpl extends AddItemsCommonBase{

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public String getChannelCode() {
        return "qm";
    }

    @Override
    public JsonResult<CreateOrderRespVO> getProduct(CreateOrderReqVO createOrderReqVO) {
        return null;
    }

    @Override
    public JsonResult<CreateOrderRespVO> addItemsAndLimitRules(CreateOrderReqVO createOrderReqVO) {
        Integer type = createOrderReqVO.getType();
        Long platformId = createOrderReqVO.getPlatformId();
        Long userId = createOrderReqVO.getUserId();
        String userName =createOrderReqVO.getUserName();
        UserDTO user = createOrderReqVO.getUser();
        Long companyId = createOrderReqVO.getCompanyId();
        Long storeId = createOrderReqVO.getStoreId();
        String thirdOrderJsonStr = createOrderReqVO.getThirdOrderJsonStr();
        QingMeiOrderDTO qingMeiOrderDTO= JSON.parseObject(thirdOrderJsonStr, QingMeiOrderDTO.class);
        qingMeiOrderDTO.setTotalShippingFee(BigDecimal.ZERO);
        List<SoItemDTO> soItems = new ArrayList<>();
        // 组织限购规则记录集合
        List<LimitRuleRecordDTO> limitRuleRecordList = new ArrayList<>();
        Double orderAmount = 0D;
        for(QingMeiChildOrderDTO qmChildDTO:qingMeiOrderDTO.getTradeList()){
            qingMeiOrderDTO.setTotalShippingFee(qingMeiOrderDTO.getTotalShippingFee().add(qmChildDTO.getShippingFee()));
            for (QingMeiChildItemDTO qmItemDTO:qmChildDTO.getGoodsList()){
                //校验结束
                SoItemDTO item = new SoItemDTO();
                item.setCartType(type);
                item.setPlatformId(platformId);
                item.setPuId(qmItemDTO.getPuId());
                item.setPrice(qmItemDTO.getPrice());
                item.setPuCount(qmItemDTO.getQuantity());
                // 一期暂时认为购物车商品不存在unit属性
                item.setUnitExist(0);
                item.setUserId(userId);
                item.setPuPicUrl(qmItemDTO.getGoodsImage());
                item.setPuName(qmItemDTO.getSkuName());
                item.setThirdChildCode(qmChildDTO.getOrderNo());
                item.setMerchantId(ThirdConst.Merchant.QM);
                item.setSource(ThirdConst.Source.QM);
                item.setExternalSkuId(qmItemDTO.getSkuCode());
                item.setSnapshot(JSON.toJSONString(qmItemDTO));
                item.setFinalPromotionAver(item.getPrice().multiply(new BigDecimal(item.getPuCount())).subtract(qmItemDTO.getGoodsPayPrice()));
                item.setTaxRate(qmItemDTO.getTaxRate());
                item.setTaxCode(qmItemDTO.getTaxCode());
                item.setTaxUnit(qmItemDTO.getTaxUnit());
                item.setSubBizId(qmItemDTO.getSubBizId());
                soItems.add(item);
                orderAmount += item.getPrice().multiply(new BigDecimal(item.getPuCount())).doubleValue();

                // 限购规则记录
                LimitRuleRecordDTO lrro = new LimitRuleRecordDTO();
                lrro.setBuySum(Long.valueOf(qmItemDTO.getQuantity()));
                lrro.setBuyMoneySum(qmItemDTO.getGoodsPayPrice());
                lrro.setProductUnitId(qmItemDTO.getPuId());
                lrro.setProductUnitSerialNumber("qm-"+qmItemDTO.getSkuCode());
                lrro.setStandardUnitId(qmItemDTO.getPuId());
                lrro.setCreateUserid(userId);
                lrro.setCreateUsername(userName);
                lrro.setCreateUserMobile(user.getMobile());
                lrro.setCompanyId(companyId);
                lrro.setStoreId(storeId);
                limitRuleRecordList.add(lrro);
            }
        }
        CreateOrderRespVO createOrderRespVO = new CreateOrderRespVO();
        createOrderRespVO.setOrderAmount(orderAmount);
        createOrderRespVO.setSoItems(soItems);
        createOrderRespVO.setLimitRuleRecordList(limitRuleRecordList);
        createOrderRespVO.setSourceProduct(ThirdConst.Source.QM);
        createOrderRespVO.setSource(ThirdConst.Source.QM);
        createOrderRespVO.setOrderPayByCash(0D);
        createOrderRespVO.setLimitFuBiPayAmount(BigDecimal.ZERO);
        return JsonResult.success(createOrderRespVO);
    }
}
