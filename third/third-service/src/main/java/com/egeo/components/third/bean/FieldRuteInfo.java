package com.egeo.components.third.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.utils.StringUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Data
public class FieldRuteInfo {
    private String ruteName;

    private String ruteValues;

    public List<FieldRuteInfo> getFieldRuteInfoList(String ruteStr){
        if(StringUtil.isEmpty(ruteStr) || StringUtils.isBlank(ruteStr)){
            return new ArrayList<>();
        }
        try {
            JSONArray jsonArr = JSONObject.parseArray(ruteStr);
            List<FieldRuteInfo> list = JSONObject.parseArray(jsonArr.toJSONString(), FieldRuteInfo.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("规则格式有误，转换成规则集出错!"+e);
        }
        return null;
    }
}
