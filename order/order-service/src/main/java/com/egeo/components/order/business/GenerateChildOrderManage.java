package com.egeo.components.order.business;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 0:53
 * @Version V1.0
 **/
public interface GenerateChildOrderManage {

    public void generateChildByMerchant(List<SoChildDTO> soChildDTOList, List<SoItemDTO> soItems, String orderCode, JSONObject remarkObj, ReceiverAddressDTO addr, JSONObject deliveryPriceObj);

    public  List<SoThirdpartyDTO> generateSoThirdpartyDTO(List<SoChildDTO> soChildDTOList, List<SoItemDTO> soItems, Long platformId, Long invoiceId,
                                                          Long receiveAddressId, Integer source, Long commodityTemplateId, String phone,
                                                          CommodityProductUnitDTO puType0, Long puId, boolean isJdType0);
}
