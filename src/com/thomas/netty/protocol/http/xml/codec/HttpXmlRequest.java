package com.thomas.netty.protocol.http.xml.codec;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @创建人 thomas_liu
 * @创建时间 2019/1/8 15:49
 * @描述 TODO
 */
public class HttpXmlRequest {
    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private FullHttpRequest request;
    private Object body;

    // ===========================================================
    // Constructors
    // ===========================================================

    public HttpXmlRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }


    // ===========================================================
    // Getter &amp; Setter
    // ===========================================================

    public FullHttpRequest getRequest() {
        return request;
    }

    public void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================


    // ===========================================================
    // Methods
    // ===========================================================

    @Override
    public String toString() {
        return super.toString();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
