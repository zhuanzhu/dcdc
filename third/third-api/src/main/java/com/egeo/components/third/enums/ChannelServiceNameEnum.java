package com.egeo.components.third.enums;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public enum ChannelServiceNameEnum {

    GOOD_LIST("goodList","商品列表查询"),
    GOOD("good","商品单个查询"),
    ORDER_CREATE("orderCreate","订单创建"),
    ORDER_PAY("orderPay","订单支付"),
    ORDER_CANCEL("orderCancel","订单取消"),
    ORDER_DETAIL("orderDetail","订单详情查询"),
    ORDER_STATUS("orderStatus","订单状态查询"),
    ORDER_PUSH("orderPush","订单推送"),
    USER_CASH("userCash","用户余额查询"),
    USER_FANKA_PAY("fankaPay","用户饭卡支付"),
    USER_JIDIAN_PAY("jidianPay","用户积点支付"),
    ORDER_REFUND_PUSH("orderRefundPush","订单退款推送"),
    USER_INFO("userInfo","用户信息查询"),
    ;

    public String getChannelServiceName() {
        return channelServiceName;
    }

    public void setChannelServiceName(String channelServiceName) {
        this.channelServiceName = channelServiceName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String channelServiceName;

    private String msg;



    private ChannelServiceNameEnum(String channelServiceName, String msg) {
        this.channelServiceName = channelServiceName;
        this.msg = msg;
    }

    }
