package com.egeo.components.utils.qc;

/**
 * Created by 0.0 on 2018/11/13.
 */
public class QCResult {
    private QCBody body;
    private QCHeader header;
    private QCSecurity security;

    public QCBody getBody() {
        return body;
    }

    public void setBody(QCBody body) {
        this.body = body;
    }

    public QCHeader getHeader() {
        return header;
    }

    public void setHeader(QCHeader header) {
        this.header = header;
    }

    public QCSecurity getSecurity() {
        return security;
    }

    public void setSecurity(QCSecurity security) {
        this.security = security;
    }
}
