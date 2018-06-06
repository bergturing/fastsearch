package com.berg.fastsearch.core.system.base.web.dto;

import java.io.Serializable;

/**
 * <p>REST响应数据体</p>
 *
 * @author bo.he02@hand-china.com
 * @version v1.0
 * @apiNote Created on 18-3-18
 */
public class ResponseData implements Serializable{
    private static final long serialVersionUID = 8723239645309087276L;
    /**
     * 状态码
     */
    private int code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 是否有更多数据
     */
    private boolean more;

    public ResponseData() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getMessage();
    }

    public ResponseData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public static ResponseData ofMessage(int code, String message){
        return new ResponseData(code, message, null);
    }

    public static ResponseData ofSuccess(Object data){
        return new ResponseData(Status.SUCCESS.getCode(),
                Status.SUCCESS.getMessage(),
                data);
    }

    public static ResponseData ofStatus(Status status){
        return new ResponseData(status.getCode(), status.getMessage(), null);
    }

    /**
     * 已定义的状态
     */
    public enum Status{
        SUCCESS(200, "OK"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found"),
        INTERNAL_SERVER_ERROR(500, "Unknown Internal Error"),
        NOT_VALID_PARAM(40005, "Not valid Params"),
        NOT_SUPPORTED_OPERATION(40006, "Operation not supported"),
        NOT_LOGIN(50000, "Not Login");

        /**
         * 状态码
         */
        private int code;

        /**
         * 信息
         */
        private String message;

        Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
