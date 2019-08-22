package com.xjgy.scanningupload.entity;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/5
 */
public class ResultEntity {

    /**
     * code : 200
     * msg : 操作成功
     * data : null
     * version : null
     */

    private int code;
    private String msg;
    private Object data;
    private Object version;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }
}
