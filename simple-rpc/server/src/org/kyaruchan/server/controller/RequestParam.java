package org.kyaruchan.server.controller;

/* 存储Consumer发起的请求参数 */
public class RequestParam {
    private String id;
    private String methodName;
    private Object[] params;

    public RequestParam(){
    }

    public RequestParam(String id, String methodName, Object[] params){
        this.id = id;
        this.methodName = methodName;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParams() {
        return params;
    }
}
