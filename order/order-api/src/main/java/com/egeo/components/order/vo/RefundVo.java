package com.egeo.components.order.vo;

import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.utils.EmptyUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RefundVo implements Serializable {

    private Long orderId;
    private  Double refundCash;
    private  Double refundFubi;
    private  String reason;
    private boolean cancelOrder;
    private Long platformId;
    private  Long operatorId;
    private  String thirdRefundCode;
    private Long childId;
    private List<RefundItemVo>  refundItemVos;
    private boolean autoRefundCash;

    private Double refundJiDian;

    private List<SoItemDTO> soItemDTOS;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getRefundCash() {
        return refundCash;
    }

    public void setRefundCash(Double refundCash) {
        this.refundCash = refundCash;
    }

    public Double getRefundFubi() {
        return refundFubi;
    }

    public void setRefundFubi(Double refundFubi) {
        this.refundFubi = refundFubi;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isCancelOrder() {
        return cancelOrder;
    }

    public void setCancelOrder(boolean cancelOrder) {
        this.cancelOrder = cancelOrder;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getThirdRefundCode() {
        return thirdRefundCode;
    }

    public void setThirdRefundCode(String thirdRefundCode) {
        this.thirdRefundCode = thirdRefundCode;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public List<RefundItemVo> getRefundItemVos() {
        return refundItemVos;
    }

    public void setRefundItemVos(List<RefundItemVo> refundItemVos) {
        this.refundItemVos = refundItemVos;
    }

    public List<SoItemDTO> getSoItemDTOS() {
        return soItemDTOS;
    }

    public void setSoItemDTOS(List<SoItemDTO> soItemDTOS) {
        this.soItemDTOS = soItemDTOS;
    }

    public boolean isAutoRefundCash() {
        return autoRefundCash;
    }

    public void setAutoRefundCash(boolean autoRefundCash) {
        this.autoRefundCash = autoRefundCash;
    }

    public void transferRefundItem(List<SoItemDTO> soItemDTOS){
        if (EmptyUtil.isEmpty(soItemDTOS)){
            return;
        }
        List<RefundItemVo> itemVos=new ArrayList<>();
        soItemDTOS.forEach(item->{
            RefundItemVo itemVo=new RefundItemVo();
            itemVo.setSkuId(String.valueOf(item.getPuId()));
            itemVo.setSkuName(item.getPuName());
            itemVo.setRefundNum(item.getPuCount());
            itemVo.setPrice(item.getPrice());
            itemVo.setRefundAmount(item.getGoodsRefundPrice());
            itemVo.setRefundDeliveryFee(item.getRefundDeliveryFee());
            itemVo.setSoItemId(item.getId());
            itemVo.setSource(item.getSource());
            itemVo.setPlatformId(item.getPlatformId());
            itemVos.add(itemVo);
        });
        this.setRefundItemVos(itemVos);
    }

    public Double getRefundJiDian() {
        return refundJiDian;
    }

    public void setRefundJiDian(Double refundJiDian) {
        this.refundJiDian = refundJiDian;
    }
}
