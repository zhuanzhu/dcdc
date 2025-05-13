package com.egeo.components.order.vo.cake;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class CakeOrderStatusNoticeExpressVO implements Serializable {

    /**
     * 是	string	商品规格id
     **/
    private String spec_id;

    /**
     * 是	string	快递名称/配送员姓名
     **/
    private String express_cp;

    /**
     * 是	string	快递单号/配送员手机号
     **/
    private String express_no;

    /**
     * 否	string	快递编码（快递鸟接口）
     **/
    private String express_code;

    public String getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(String spec_id) {
        this.spec_id = spec_id;
    }

    public String getExpress_cp() {
        return express_cp;
    }

    public void setExpress_cp(String express_cp) {
        this.express_cp = express_cp;
    }

    public String getExpress_no() {
        return express_no;
    }

    public void setExpress_no(String express_no) {
        this.express_no = express_no;
    }

    public String getExpress_code() {
        return express_code;
    }

    public void setExpress_code(String express_code) {
        this.express_code = express_code;
    }
}
