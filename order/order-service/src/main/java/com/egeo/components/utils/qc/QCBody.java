package com.egeo.components.utils.qc;

import java.util.List;

/**
 * Created by 0.0 on 2018/11/13.
 */
public class QCBody {
    private String actid;
    private List<QCInfos> infos;
    private String mercid;
    private String ordno;
    private String tranid;
    private String ticktype;

    public String getOrdsts() {
        return ordsts;
    }

    public void setOrdsts(String ordsts) {
        this.ordsts = ordsts;
    }

    private String ordsts;

    public String getTicktype() {
        return ticktype;
    }

    public void setTicktype(String ticktype) {
        this.ticktype = ticktype;
    }



    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }



    public String getActid() {
        return actid;
    }

    public void setActid(String actid) {
        this.actid = actid;
    }

    public String getMercid() {
        return mercid;
    }

    public void setMercid(String mercid) {
        this.mercid = mercid;
    }

    public String getOrdno() {
        return ordno;
    }

    public void setOrdno(String ordno) {
        this.ordno = ordno;
    }

    public List<QCInfos> getInfos() {
        return infos;
    }

    public void setInfos(List<QCInfos> infos) {
        this.infos = infos;
    }
}
