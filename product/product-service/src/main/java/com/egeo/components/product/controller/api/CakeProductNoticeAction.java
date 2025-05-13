package com.egeo.components.product.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.egeo.web.BaseSpringController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/product/cake/notice")
public class CakeProductNoticeAction  extends BaseSpringController {

    @RequestMapping(value = "productNotice")
    @ResponseBody
    public JSONObject productNotice(@RequestBody JSONObject json){

        return returnSuccess();
    }

    @RequestMapping(value = "productAtrrNotice")
    @ResponseBody
    public JSONObject productAtrrNotice(@RequestBody JSONObject json){

        return returnSuccess();
    }

    @RequestMapping(value = "brandProductUpOrDown")
    @ResponseBody
    public JSONObject brandUpOrDown(@RequestBody JSONObject json){

        return returnSuccess();
    }

    @RequestMapping(value = "specsChange")
    @ResponseBody
    public JSONObject specsChange(@RequestBody JSONObject json){

        return returnSuccess();
    }

    private JSONObject returnSuccess(){
        JSONObject resultJsonObject = new JSONObject();
        resultJsonObject.put("code",200);
        resultJsonObject.put("message","success");
        return resultJsonObject;
    }
}
