package com.egeo.components.product.facade;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.common.BusinessException;
import com.egeo.components.product.dto.ChannelCategoryDTO;
import com.egeo.components.utils.CakeUtil;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class ChannelCategoryThirdFacade {

    @Resource
    private CakeUtil cakeUtil;

    @Autowired
    private ChannelCategoryFacade channelCategoryFacade;

    public List<ChannelCategoryDTO> findChannelCategoryAll(ChannelCategoryDTO dto){
        if(dto.getChannelCode().equals("cake")){
            return getCakeCategoryList(dto);
        }
        return channelCategoryFacade.findChannelCategoryAll(dto);
    }

    private List<ChannelCategoryDTO> getCakeCategoryList(ChannelCategoryDTO dto) {
        JSONObject reqResult =  cakeUtil.getCatsInfo();
        JsonResult checkResult = cakeUtil.checkResult(reqResult);
        if(Objects.nonNull(checkResult)){
            throw new BusinessException(checkResult.getError() !=null?checkResult.getError():"获取第三方商品分类失败");
        }
        JSONArray jsonArray = reqResult.getJSONArray(cakeUtil.DATA_KEY);
        if(null == jsonArray || jsonArray.size()==0){
            return null;
        }
        List<ChannelCategoryDTO> list = new ArrayList<>();
        ChannelCategoryDTO resultDTO =null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer level = jsonObject.getInteger("level");
            String name = jsonObject.getString("name");
            String id = jsonObject.getString("id");
            String parentId = jsonObject.getString("parent_id");
            resultDTO = new ChannelCategoryDTO();
            resultDTO.setChannelCode("cake");
            if(dto.getChannelCategoryLevel().intValue()== 1 && level.intValue() ==1){
                resultDTO.setChannelCategoryId(id);
                resultDTO.setChannelCategoryName(name);
            }else {
                if(dto.getChannelCategoryPId().equals(parentId)){
                    resultDTO.setChannelCategoryId(id);
                    resultDTO.setChannelCategoryName(name);
                    resultDTO.setChannelCategoryLevel(level);
                }
            }
            if(EmptyUtil.isNotEmpty(resultDTO.getChannelCategoryId())){
                list.add(resultDTO);
            }

        }
        return list;
    }
}
