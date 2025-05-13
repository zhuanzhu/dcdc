package com.egeo.components.product.strategy;

import com.egeo.components.product.bean.KeyWordSearchBean;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.orm.PageResult;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/26 15:44
 * @Version V1.0
 **/
public interface KeyWordSearchStrategy {

    /**获取产品code 参照枚举ProductChannelCodeEnum**/
    public String getProductCode();

    PageResult<StandardUnitDTO> getProductByKeyWord(KeyWordSearchBean bean);
}
