package com.egeo.components.order.controller.client;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.bean.NoticeReqHelpBean;
import com.egeo.components.order.client.OrderNoticeClient;
import com.egeo.components.order.notice.OrderNoticeStrategy;
import com.egeo.components.order.notice.OrderProcessFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 订单通知服务
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/client/order/orderNotice")
public class OrderNoticeController implements OrderNoticeClient {

    @Autowired
    private OrderProcessFactory orderProcessFactory;

    /**
     * @Description {channelCode}换成cake代表是从蛋糕叔叔回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderStatusNotice", method = RequestMethod.POST)
    @ResponseBody
    //@Override
    public Object orderStatusNotice(@PathVariable("channelCode") String channelCode, @RequestBody JSONObject data, HttpServletRequest req){
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        return orderNoticeStrategy.orderStatusNotice(reqHelpBean,req);
    }

    /**
     * @Description {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderStatusNoticeForm", method = RequestMethod.POST)
    @ResponseBody
    public Object orderStatusNoticeForm(@PathVariable("channelCode") String channelCode,JSONObject data,HttpServletRequest req){
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        return orderNoticeStrategy.orderStatusNotice(reqHelpBean,req);
    }

    /**
     * @Description {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderExpressModify", method = RequestMethod.POST)
    @ResponseBody
    public Object orderExpressModify(@PathVariable("channelCode") String channelCode,@RequestBody JSONObject data,HttpServletRequest req){
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        return orderNoticeStrategy.orderExpressModify(reqHelpBean,req);
    }

    /**
     * @Description  订单售后审核通知回传接口
     * {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderAfterSaleVerifyNotify", method = RequestMethod.POST)
    @ResponseBody
    public Object orderAfterSaleVerifyNotify(@PathVariable("channelCode") String channelCode,@RequestBody JSONObject data,HttpServletRequest req){
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        return orderNoticeStrategy.orderExpressModify(reqHelpBean,req);
    }

    /**
     * @Description  订单物流发货通知接口
     * {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderExpress", method = RequestMethod.POST)
    @ResponseBody
    public Object orderExpress(@PathVariable("channelCode") String channelCode,@RequestBody JSONObject data,HttpServletRequest req){
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        return orderNoticeStrategy.orderExpressModify(reqHelpBean,req);
    }
}
