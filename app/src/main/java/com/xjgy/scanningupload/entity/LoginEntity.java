package com.xjgy.scanningupload.entity;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/6
 */
public class LoginEntity {

    /**
     * code : 200
     * msg : 成功
     * data : {"uid":4,"username":"appuser","password":"\u201c\u201d","roleid":1,"roledesc":"\u201c\u201d","salt":null,"jwt":null,"userenable":null,"authCacheKey":"appuser"}
     * version : v1.0
     */

    private int code;
    private String msg;
    private DataBean data;
    private String version;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        /**
         * uid : 4
         * username : appuser
         * password : “”
         * roleid : 1
         * roledesc : “”
         * salt : null
         * jwt : null
         * userenable : null
         * authCacheKey : appuser
         */

        private int uid;
        private String username;
        private String password;
        private int roleid;
        private String roledesc;
        private Object salt;
        private Object jwt;
        private Object userenable;
        private String authCacheKey;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getRoleid() {
            return roleid;
        }

        public void setRoleid(int roleid) {
            this.roleid = roleid;
        }

        public String getRoledesc() {
            return roledesc;
        }

        public void setRoledesc(String roledesc) {
            this.roledesc = roledesc;
        }

        public Object getSalt() {
            return salt;
        }

        public void setSalt(Object salt) {
            this.salt = salt;
        }

        public Object getJwt() {
            return jwt;
        }

        public void setJwt(Object jwt) {
            this.jwt = jwt;
        }

        public Object getUserenable() {
            return userenable;
        }

        public void setUserenable(Object userenable) {
            this.userenable = userenable;
        }

        public String getAuthCacheKey() {
            return authCacheKey;
        }

        public void setAuthCacheKey(String authCacheKey) {
            this.authCacheKey = authCacheKey;
        }
    }
}
