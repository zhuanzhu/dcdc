package com.egeo.components.order.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.bean.NoticeReqHelpBean;
import com.egeo.components.order.dto.notice.world.WorldBuyBaseOrderNoticeDTO;
import com.egeo.components.order.dto.notice.world.WorldBuyBaseOrderNoticeRequestDTO;
import com.egeo.components.order.notice.OrderNoticeStrategy;
import com.egeo.components.order.notice.OrderProcessFactory;
import com.egeo.components.product.client.WorldGoodsNoticeClient;
import com.egeo.components.product.dto.world.WorldBuyBaseNoticeDTO;
import com.egeo.components.utils.OrderNoticeHelper;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description 订单通知相关服务
 * @Author lsl
 * @Version V1.0
 **/
@Controller
@RequestMapping("/api/order/notice")
public class OrderNoticeAction extends BaseSpringController {

    @Autowired
    private OrderProcessFactory orderProcessFactory;

    @Autowired
    private WorldGoodsNoticeClient worldGoodsNoticeClient;

    /**
     * @Description {channelCode}换成cake代表是从蛋糕叔叔回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/cakeOrderStatusNotice", method = RequestMethod.POST)
    @ResponseBody
    public Object cakeOrderStatusNotice(@PathVariable("channelCode") String channelCode, @RequestBody JSONObject data, HttpServletRequest req){
        logger.info("通知cakeOrderStatusNotice-start-channelCode={}接收通知参数:{}",channelCode,JSON.toJSONString(data));
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        Object rt =  orderNoticeStrategy.orderStatusNotice(reqHelpBean,req);
        logger.info("通知cakeOrderStatusNotice-end-channelCode={}接收通知处理结果:{}",channelCode,JSON.toJSONString(rt));
        return rt;
    }


    /**
     * @Description {channelCode}换成cake代表是从蛋糕叔叔回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderStatusNotice", method = RequestMethod.POST)
    @ResponseBody
    public Object orderStatusNotice(@PathVariable("channelCode") String channelCode, @RequestBody JSONObject data, HttpServletRequest req){
        logger.info("通知orderStatusNotice-start-channelCode={}接收通知参数:{}",channelCode,JSON.toJSONString(data));
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        Object rt = orderNoticeStrategy.orderStatusNotice(reqHelpBean,req);
        logger.info("通知orderStatusNotice-end-channelCode={}接收通知处理结果:{}",channelCode,JSON.toJSONString(rt));
        return rt;
    }

    /**
     * @Description {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderStatusNoticeForm", method = RequestMethod.POST)
    @ResponseBody
    public Object orderStatusNoticeForm(@PathVariable("channelCode") String channelCode,JSONObject data, HttpServletRequest req){
        logger.info("通知orderStatusNoticeForm-start-channelCode={}接收通知参数:{}",channelCode,JSON.toJSONString(data));
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        Object rt = orderNoticeStrategy.orderStatusNotice(reqHelpBean,req);
        logger.info("通知orderStatusNoticeForm-end-channelCode={}接收通知处理结果:{}",channelCode,JSON.toJSONString(rt));
        return rt;
    }

    /**
     * @Description {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderExpressModify", method = RequestMethod.POST)
    @ResponseBody
    public Object orderExpressModify(@PathVariable("channelCode") String channelCode,@RequestBody JSONObject data, HttpServletRequest req){
        logger.info("通知orderExpressModify-start-channelCode={}接收通知参数:{}",channelCode,JSON.toJSONString(data));
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        Object rt = orderNoticeStrategy.orderExpressModify(reqHelpBean,req);
        logger.info("通知orderExpressModify-end-channelCode={}接收通知处理结果:{}",channelCode,JSON.toJSONString(rt));
        return rt;
    }

    /**
     * @Description  订单售后审核通知回传接口
     * {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderAfterSaleVerifyNotify", method = RequestMethod.POST)
    @ResponseBody
    public Object orderAfterSaleVerifyNotify(@PathVariable("channelCode") String channelCode,@RequestBody JSONObject data, HttpServletRequest req){
        logger.info("通知orderAfterSaleVerifyNotify-start-channelCode={}接收通知参数:{}",channelCode,JSON.toJSONString(data));
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        Object rt = orderNoticeStrategy.orderAfterSaleVerifyNotify(reqHelpBean,req);
        logger.info("通知orderAfterSaleVerifyNotify-end-channelCode={}接收通知处理结果:{}",channelCode,JSON.toJSONString(rt));
        return rt;
    }

    /**
     * @Description  订单物流发货通知接口
     * {channelCode}换成cake代表是从某渠道回来的通知
     **/
    @RequestMapping(value = "/{channelCode}/orderExpress", method = RequestMethod.POST)
    @ResponseBody
    public Object orderExpress(@PathVariable("channelCode") String channelCode,@RequestBody JSONObject data, HttpServletRequest req){
        logger.info("通知orderExpress-start-channelCode={}接收通知参数:{}",channelCode,JSON.toJSONString(data));
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        Object rt = orderNoticeStrategy.orderExpress(reqHelpBean,req);
        logger.info("通知orderExpress-end-channelCode={}接收通知处理结果:{}",channelCode,JSON.toJSONString(rt));
        return rt;
    }

    @RequestMapping(value = "/worldBuyNotice", method = RequestMethod.POST)
    @ResponseBody
    public Object worldBuyNotice(@RequestBody JSONObject data, HttpServletRequest req){
        logger.info("全球购接收通知参数:{}", JSON.toJSONString(data));
        Object rt =  worldNoticeProcess(data, req);
        logger.info("全球购通知处理结果:{}", JSON.toJSONString(rt));
        return rt;
    }

    private Object worldNoticeProcess(JSONObject data, HttpServletRequest req) {
        String messageType = data.getString("MessageType");
        if(EmptyUtil.isEmpty(messageType) || EmptyUtil.isBlank(messageType)){
            return OrderNoticeHelper.returnResult("code",0,"msg","接收失败,MessageType参数不能为空");
        }
        WorldBuyBaseNoticeDTO dto = JSONObject.parseObject(JSON.toJSONString(data), WorldBuyBaseNoticeDTO.class);
        //商品相关通知
        if(Objects.equals(messageType,"goodsChange")){
            //商品变更
            return worldGoodsNoticeClient.goodsChange(dto);
        }else if(Objects.equals(messageType,"goodsSkuChange")){
            //sku下架
            return worldGoodsNoticeClient.goodsSkuChange(dto);
        }else if(Objects.equals(messageType,"goodsBatchDown")){
            //全部下架
            return worldGoodsNoticeClient.goodsBatchDown();
        }

        //订单相关通知
        String channelCode = "worldBuy";
        NoticeReqHelpBean reqHelpBean = new NoticeReqHelpBean();
        reqHelpBean.setParamObject(data);
        reqHelpBean.setChannelCode(channelCode);
        OrderNoticeStrategy orderNoticeStrategy = orderProcessFactory.getOrderNoticeStrategy(channelCode);
        //订单物流发货更新接口（非必要可以不用）
        if(Objects.equals(messageType,"OrderExpressModify")){
            return orderNoticeStrategy.orderExpressModify(reqHelpBean, req);
        }
        //订单物流发货通知接口
        if(Objects.equals(messageType,"OrderExpress")){
            return orderNoticeStrategy.orderExpress(reqHelpBean, req);
        }

        //订单售后审核通知回传接口（老版本接口使用，新版本接口废弃）
        if(Objects.equals(messageType,"OrderAfterSaleVerifyNotify")){
           return orderNoticeStrategy.orderAfterSaleVerifyNotify(reqHelpBean, req);
        }

        //售后退款审核接口OrderAfterSaleVerifyRefundAmount（新版本，老版本不支持）
        if(Objects.equals(messageType,"OrderAfterSaleVerifyRefundAmount")){

        }
        return OrderNoticeHelper.returnResult("code",200,"msg","接收成功");
    }
}
