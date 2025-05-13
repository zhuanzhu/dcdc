package com.egeo.components.order.dto;


import java.io.Serializable;

public class QingMeiResult<T> implements Serializable {
    private static final long serialVersionUID = -1902455525299606460L;
    private String code = "200";
    private String msg;
    private boolean success=true;
    private T data;

    public QingMeiResult() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> QingMeiResult<T> fail(String msg, String code) {
        QingMeiResult<T> rt = new QingMeiResult();
        rt.setCode(code);
        rt.setMsg(msg);
        rt.setSuccess(false);
        return rt;
    }

}
