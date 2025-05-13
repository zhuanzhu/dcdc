package com.egeo.components.order.strategy.vo;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoItemDTO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 1:25
 * @Version V1.0
 **/
public class SplitSoChildReqVO implements Serializable {

    private List<Long> supplierIdList;

    private String orderCode;

    private Long mId;

    private JSONObject remarkObj;
    private ReceiverAddressDTO addr;
    private JSONObject deliveryPriceObj;
    private List<SoItemDTO> soItems;
    private int m;

    private int n;


    public List<Long> getSupplierIdList() {
        return supplierIdList;
    }

    public void setSupplierIdList(List<Long> supplierIdList) {
        this.supplierIdList = supplierIdList;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public JSONObject getRemarkObj() {
        return remarkObj;
    }

    public void setRemarkObj(JSONObject remarkObj) {
        this.remarkObj = remarkObj;
    }

    public ReceiverAddressDTO getAddr() {
        return addr;
    }

    public void setAddr(ReceiverAddressDTO addr) {
        this.addr = addr;
    }

    public JSONObject getDeliveryPriceObj() {
        return deliveryPriceObj;
    }

    public void setDeliveryPriceObj(JSONObject deliveryPriceObj) {
        this.deliveryPriceObj = deliveryPriceObj;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<SoItemDTO> getSoItems() {
        return soItems;
    }

    public void setSoItems(List<SoItemDTO> soItems) {
        this.soItems = soItems;
    }
}
