package com.egeo.components.order.common;

import com.egeo.utils.excel2.ExcelTmplConstant;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/13 13:46
 * @Version V1.0
 **/
public enum ExcelTemplateEnum {
    SO_CHILD_DELIVERY(6, "发货信息导入", new String[]{"订单号", "子订单编号","快递单号", "物流公司"}),
    ;

    int tmplType;
    String tmplName;
    String[] columnArr;


    private ExcelTemplateEnum(int tmplType, String tmplName, String[] columnArr) {
        this.tmplType = tmplType;
        this.tmplName = tmplName;
        this.columnArr = columnArr;
    }

    public int getTmplType() {
        return this.tmplType;
    }

    public void setTmplType(int tmplType) {
        this.tmplType = tmplType;
    }

    public String getTmplName() {
        return this.tmplName;
    }

    public void setTmplName(String tmplName) {
        this.tmplName = tmplName;
    }

    public String[] getColumnArr() {
        return this.columnArr;
    }

    public void setColumnArr(String[] columnArr) {
        this.columnArr = columnArr;
    }

    public static ExcelTemplateEnum translate(int tmplType) {
        ExcelTemplateEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            ExcelTemplateEnum t = var4[var2];
            if (t.tmplType == tmplType) {
                return t;
            }
        }

        return null;
    }
}
