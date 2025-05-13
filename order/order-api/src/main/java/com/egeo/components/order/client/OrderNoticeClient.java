package com.egeo.components.order.client;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/6/3 10:15
 * @Version V1.0
 **/
public interface OrderNoticeClient {
   /* @RequestMapping(value = { "/client/api/order/notice/{channelCode}/orderStatusNotice" }, method = { RequestMethod.POST })
    public Object orderStatusNotice(@PathVariable("channelCode") String channelCode, @RequestBody JSONObject data, HttpServletRequest req);
*/
}
