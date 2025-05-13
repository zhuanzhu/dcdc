package com.egeo.components.utils.qc;

/**
 * Created by 0.0 on 2018/11/13.
 */
public class QCInfos2 {
    private String barcode;
    private String barpwd;
    private String duedt;
    private String shorturl;
    private Integer ticktype;

    public String getUsermernm() {
        return usermernm;
    }

    public void setUsermernm(String usermernm) {
        this.usermernm = usermernm;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String usermernm;
    private String uuid;


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarpwd() {
        return barpwd;
    }

    public void setBarpwd(String barpwd) {
        this.barpwd = barpwd;
    }

    public String getDuedt() {
        return duedt;
    }

    public void setDuedt(String duedt) {
        this.duedt = duedt;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl;
    }

    public Integer getTicktype() {
        return ticktype;
    }

    public void setTicktype(Integer ticktype) {
        this.ticktype = ticktype;
    }

}
