package com.xjgy.scanningupload.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Desccribe:商品选择实体类
 *
 * @author Created by wuyang on 2019/8/7
 */
public class ListEntity implements Serializable {

    /**
     * code : 200
     * msg : 操作成功
     * data : [{"id":1,"type_name":"蔬菜","goodsList":[{"id":75,"goods_number":"00001","goods_name":"土豆","goods_price":12.2,"discount_price":1.3,"key_id":1,"type_id":1},{"id":76,"goods_number":"00002","goods_name":"大白菜","goods_price":1.44,"discount_price":1.44,"key_id":2,"type_id":1}]},{"id":2,"type_name":"水果","goodsList":[]}]
     * version : null
     */

    private int code;
    private String msg;
    private String version;
    private List<DataBean> data;


    public ListEntity() {
    }


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


    public static class DataBean implements Serializable{
        /**
         * id : 1
         * type_name : 蔬菜
         * goodsList : [{"id":75,"goods_number":"00001","goods_name":"土豆","goods_price":12.2,"discount_price":1.3,"key_id":1,"type_id":1},{"id":76,"goods_number":"00002","goods_name":"大白菜","goods_price":1.44,"discount_price":1.44,"key_id":2,"type_id":1}]
         */

        private int id;
        private String type_name;
        boolean isSelected;
        private List<GoodsListBean> goodsList;
        private String boxnumber;

        public String getBoxnumber() {
            return boxnumber;
        }

        public void setBoxnumber(String boxnumber) {
            this.boxnumber = boxnumber;
        }
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean implements Serializable{
            /**
             * id : 75
             * goods_number : 00001
             * goods_name : 土豆
             * goods_price : 12.2
             * discount_price : 1.3
             * key_id : 1
             * type_id : 1
             */

            private int id;
            private String goods_number;
            private String goods_name;
            private double goods_price;
            private double discount_price;
            private int key_id;
            private int type_id;
            private int goods_id;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public double getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(double goods_price) {
                this.goods_price = goods_price;
            }

            public double getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(double discount_price) {
                this.discount_price = discount_price;
            }

            public int getKey_id() {
                return key_id;
            }

            public void setKey_id(int key_id) {
                this.key_id = key_id;
            }

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }
        }
    }
}
