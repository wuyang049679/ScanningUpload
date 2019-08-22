package com.xjgy.scanningupload.entity;

import java.util.List;

/**
 * Desccribe:提交实体类
 *
 * @author Created by wuyang on 2019/8/5
 */
public class SubmitEntity {


    /**
     * goods_list : [{"type_id":-1.253045864020592E7,"id":-1.956675816391304E7,"goods_price":-1.1132116182041511E7,"discount_price":8705031.540067941,"key_id":"proident amet esse"},{"type_id":1.6667952894292831E7,"id":5.000713925503728E7,"goods_price":-2.7811556386563912E7,"discount_price":5.209719641559473E7,"key_id":"consequat ut"},{"type_id":-9135038.178573757,"id":5314088.826332912,"goods_price":4.3377391228438616E7,"discount_price":2.324613967197427E7,"key_id":"Excepteur veniam et"},{"type_id":-9.977165326519608E7,"id":-4.135532442022982E7,"goods_price":3.3013785925713226E7,"discount_price":-8.41344328675421E7,"key_id":"ut officia"}]
     * box_number : dolor et
     */

    private String box_number;
    private List<GoodsListBean> goods_list;

    public String getBox_number() {
        return box_number;
    }

    public void setBox_number(String box_number) {
        this.box_number = box_number;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * type_id : -1.253045864020592E7
         * id : -1.956675816391304E7
         * goods_price : -1.1132116182041511E7
         * discount_price : 8705031.540067941
         * key_id : proident amet esse
         */

        private int type_id;
        private int id;
        private double goods_price;
        private double discount_price;
        private int key_id;
        private int goods_id;
        private String goods_number;

        public String getGoods_number() {
            return goods_number;
        }

        public void setGoods_number(String goods_number) {
            this.goods_number = goods_number;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
