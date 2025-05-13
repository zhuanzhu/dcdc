package com.egeo.components.utils;

import com.egeo.components.order.dto.ReceiverAddressDTO;

import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/1 23:04
 * @Version V1.0
 **/
public class CakeAddressUtil {

    public static String getCityName(ReceiverAddressDTO addr){
        if(Objects.isNull(addr)){
            return null;
        }
        String cityName = addr.getGoodReceiverCity();
        if("市辖区".equals(cityName) || "直辖市".equals(cityName)){
            cityName = addr.getGoodReceiverProvince();
        }
        return cityName;
    }
}
