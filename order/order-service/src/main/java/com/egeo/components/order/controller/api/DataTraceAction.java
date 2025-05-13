package com.egeo.components.order.controller.api;

import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/order/dataTrace")
public class DataTraceAction extends BaseSpringController {



    @RequestMapping(value = "/record")
    @ResponseBody
    public JsonResult record(Integer type, String title, String content) {
        logger.info("traceRecord:{},title:{},record:{}",type,title,content);
        return JsonResult.success();
    }
}
