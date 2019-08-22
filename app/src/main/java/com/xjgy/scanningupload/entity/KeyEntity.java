package com.xjgy.scanningupload.entity;

import java.util.List;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/9
 */
public class KeyEntity {

    /**
     * code : 200
     * msg : 操作成功
     * data : [{"id":1,"key_name":"1"}]
     * version : v1.0
     */

    private int code;
    private String msg;
    private String version;
    private List<DataBean> data;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * key_name : 1
         */

        private int id;
        private String key_name;


        public DataBean(String key_name) {
            this.key_name = key_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKey_name() {
            return key_name;
        }

        public void setKey_name(String key_name) {
            this.key_name = key_name;
        }
    }
}
