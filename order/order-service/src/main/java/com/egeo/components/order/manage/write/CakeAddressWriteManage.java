package com.egeo.components.order.manage.write;

import com.egeo.components.order.dto.CakeAddResultDTO;
import com.egeo.components.order.dto.ReceiverAddressDTO;

/**
 * @Description 蛋糕叔叔地址服务
 * @Author lsl
 * @Date 2024/4/29 11:11
 * @Version V1.0
 **/
public interface CakeAddressWriteManage {

    /**
     * @Description 根据城市名称查询城市ID
     **/
    public String getCityId(String cityName);

    /**
     * @Description 新增或编辑收货地址
     **/
    public CakeAddResultDTO addOrEditCakeAddress(ReceiverAddressDTO dto,String channelUserId);

    /**
     * @Description 删除收货地址
     **/
    public void deleteCakeAddress(String userId,String id);
}
