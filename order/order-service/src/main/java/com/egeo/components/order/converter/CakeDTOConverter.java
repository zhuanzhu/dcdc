package com.egeo.components.order.converter;

import com.egeo.components.order.dto.CakeAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.utils.CakeAddressUtil;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.StringUtils;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/29 10:37
 * @Version V1.0
 **/
public class CakeDTOConverter {

    public static CakeAddressDTO toCakeAddressDTO(ReceiverAddressDTO src){
        CakeAddressDTO target = new CakeAddressDTO();
        String phone = (StringUtils.isEmpty(src.getGoodReceiverPhone()) || StringUtils.isBlank(src.getGoodReceiverPhone()))?src.getGoodReceiverMobile():src.getGoodReceiverPhone();
        String addr = src.getGoodReceiverAddress();
        target.setCity_name(CakeAddressUtil.getCityName(src));
        target.setArea(src.getGoodReceiverCounty());
        target.setAddr(addr);
        target.setName(src.getGoodReceiverName());
        target.setPhone(phone);
        target.setLandmark("");
        target.setIs_default(src.getIsDefault());
        target.setZip("");
        return target;
    }


}
