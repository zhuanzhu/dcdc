package com.egeo.components.order.vo.cake;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 蛋糕叔叔订单状态通知接口参数模型
 * @Author lsl
 * @Version V1.0
 **/
public class CakeOrderStatusNoticeVO extends CakeNoticeBaseVO implements Serializable {

    /**
     *是	string	蛋叔订单号
     **/
    private String order_no;
    /**
     *	是	string	渠道订单号
     **/
    private String out_order_no;

    /**
     *是	string	订单状态 1-已分配到商家 2-已配送完成 4-订单退款且已取消(对应订单列表和详情中的状态 3)
     **/
    private String status;

    /**
     *否	array	快递信息/配送员信息
     **/
    private List<CakeOrderStatusNoticeExpressVO> express_data;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getOut_order_no() {
        return out_order_no;
    }

    public void setOut_order_no(String out_order_no) {
        this.out_order_no = out_order_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CakeOrderStatusNoticeExpressVO> getExpress_data() {
        return express_data;
    }

    public void setExpress_data(List<CakeOrderStatusNoticeExpressVO> express_data) {
        this.express_data = express_data;
    }
}
