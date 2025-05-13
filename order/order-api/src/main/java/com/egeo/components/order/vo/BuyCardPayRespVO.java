package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 14:42
 * @Version V1.0
 **/
public class BuyCardPayRespVO implements Serializable {

    private List<BuyCardPayDetailRespVO> details;

    private Boolean result;

    private String msg;

    public List<BuyCardPayDetailRespVO> getDetails() {
        return details;
    }

    public void setDetails(List<BuyCardPayDetailRespVO> details) {
        this.details = details;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BuyCardPayRespVO() {
    }

    public BuyCardPayRespVO(Boolean result) {
        this.result = result;
    }

    public BuyCardPayRespVO(Boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }
}
