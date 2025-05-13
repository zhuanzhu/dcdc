package com.egeo.components.product.business.impl;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.business.CakeBrandManage;
import com.egeo.components.product.dto.CakeBrandCityDTO;
import com.egeo.components.product.dto.CakeBrandDetailDTO;
import com.egeo.components.utils.CakeUtil;
import com.egeo.components.utils.JsonUtils;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/29 0:01
 * @Version V1.0
 **/
@Service("cakeBrandManage")
public class CakeBrandManageImpl implements CakeBrandManage {

    @Resource
    private CakeUtil cakeUtil;

    /**
     * 品牌列表与其开通城市
     * @return
     */
    @Override
    public JsonResult<List<CakeBrandCityDTO>> getBrandCityList(){
        JSONObject jSONObject =cakeUtil.brandCityList();
        JsonResult checkResult = cakeUtil.checkResult(jSONObject);
        if(Objects.nonNull(checkResult)){
            return checkResult;
        }
        String data = jSONObject.getString(cakeUtil.DATA_KEY);
        List<CakeBrandCityDTO> list = JsonUtils.jsonToList(data, CakeBrandCityDTO.class);
        return JsonResult.success(list);
    }

    /**
     * 品牌详情接口
     * @return
     */
    @Override
    public JsonResult<CakeBrandDetailDTO> getBrandInfo(String brandId){
        JSONObject jSONObject =cakeUtil.getBrandInfo(brandId);
        JsonResult checkResult = cakeUtil.checkResult(jSONObject);
        if(Objects.nonNull(checkResult)){
            return checkResult;
        }
        String data = jSONObject.getString(cakeUtil.DATA_KEY);
        CakeBrandDetailDTO rt = JsonUtils.jsonToPojo(data, CakeBrandDetailDTO.class);
        return JsonResult.success(rt);
    }


}
