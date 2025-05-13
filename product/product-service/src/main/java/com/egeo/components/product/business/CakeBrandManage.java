package com.egeo.components.product.business;

import com.egeo.components.product.dto.CakeBrandCityDTO;
import com.egeo.components.product.dto.CakeBrandDetailDTO;
import com.egeo.web.JsonResult;

import java.util.List;

/**
 * @Description 蛋糕叔叔品牌管理
 * @Author lsl
 * @Date 2024/4/28 23:53
 * @Version V1.0
 **/
public interface CakeBrandManage {



    /**
     * 品牌列表与其开通城市
     * @return
     */
    public JsonResult<List<CakeBrandCityDTO>> getBrandCityList();

    /**
     * 品牌详情接口
     * @return
     */
    public JsonResult<CakeBrandDetailDTO> getBrandInfo(String brandId);
}
