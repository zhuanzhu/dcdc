package com.egeo.components.order.notice;

import com.egeo.components.order.bean.NoticeReqHelpBean;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/9 17:33
 * @Version V1.0
 **/
public interface OrderNoticeStrategy {

    public String getChannelCode();

    public Object orderStatusNotice(NoticeReqHelpBean reqHelpBean, HttpServletRequest req);

    /**
     * @Description 订单物流发货更新接口（非必要可以不用）
     **/
    public Object orderExpressModify(NoticeReqHelpBean reqHelpBean, HttpServletRequest req);

    /**
     * @Description 订单物流发货通知接口
     **/
    public Object orderExpress(NoticeReqHelpBean reqHelpBean, HttpServletRequest req);

    /**
     * @Description 订单售后审核通知回传接口
     **/
    public Object orderAfterSaleVerifyNotify(NoticeReqHelpBean reqHelpBean, HttpServletRequest req);

}
