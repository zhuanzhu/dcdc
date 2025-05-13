package com.egeo.components.utils.qc;

import java.util.List;

/**
 * Created by 0.0 on 2018/11/13.
 */
public class QCInfos {
    private Integer amount;
    private List<QCInfos2> infos2;
    private String phoneno;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<QCInfos2> getInfos2() {
        return infos2;
    }

    public void setInfos2(List<QCInfos2> infos2) {
        this.infos2 = infos2;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
