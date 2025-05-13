package com.egeo.components.order.condition;

import com.egeo.components.order.po.SoItemPO;

/**
 * Created by 0.0 on 2018/9/15.
 */
public class NewSoItemCondition extends SoItemPO {
    private static final long serialVersionUID = 1L;

    private String soChildCode;

    public String getSoChildCode() {
        return soChildCode;
    }

    public void setSoChildCode(String soChildCode) {
        this.soChildCode = soChildCode;
    }
}
