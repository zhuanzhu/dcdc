package com.egeo.components.order.vo.jd;

/**
 * Created by 0.0 on 2019/3/25.
 */
public class JdDeliveryPrice {
    private String freight;
    private String baseFreight;

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getBaseFreight() {
        return baseFreight;
    }

    public void setBaseFreight(String baseFreight) {
        this.baseFreight = baseFreight;
    }

    public String getRemoteRegionFreight() {
        return remoteRegionFreight;
    }

    public void setRemoteRegionFreight(String remoteRegionFreight) {
        this.remoteRegionFreight = remoteRegionFreight;
    }

    public String getRemoteSku() {
        return remoteSku;
    }

    public void setRemoteSku(String remoteSku) {
        this.remoteSku = remoteSku;
    }

    private String remoteRegionFreight;
    private String remoteSku;

}
