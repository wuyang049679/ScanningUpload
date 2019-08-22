package com.xjgy.scanningupload.api;


import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.entity.GoodsEntity;
import com.xjgy.scanningupload.entity.KeyEntity;
import com.xjgy.scanningupload.entity.ListEntity;
import com.xjgy.scanningupload.entity.LoginEntity;
import com.xjgy.scanningupload.entity.ResultEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * The interface Apistore.
 *
 * @author wuayng
 */
public interface Apistore {


    /**
     * 商品列表
     * @param body
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("app/goods/list")
    Observable<BaseEntity<List<GoodsEntity.DataBean>>> goodsList(@Body RequestBody body);

    /**
     * 商品信息提交
     * @param body
     * @return
     */
    @POST("app/goods/commit")
    Observable<BaseEntity<ResultEntity>> goodsSubmit(@Body RequestBody body);

    /**
     * 用户登录
     * @param body
     * @return
     */
    @POST("app/auth/login")
    Observable<BaseEntity<LoginEntity>> login(@Body RequestBody body);

    /**
     * 获取绑定商品集合
     * @param body
     * @return
     */
    @POST("app/goods/bind_list")
    Observable<BaseEntity<List<ListEntity.DataBean>>> goodsBindList(@Body RequestBody body);

    /**
     * 获取快捷键列表
     * @param body
     * @return
     */
    @POST("app/goods/key_list")
    Observable<BaseEntity<List<KeyEntity.DataBean>>> goodsKeyList(@Body RequestBody body);
    /**
     * 商品删除绑定接口
     * @param body
     * @return
     */
    @POST("app/goods/delete_bind")
    Observable<BaseEntity<ResultEntity>> goodsBindDelete(@Body RequestBody body);
    /**
     * 商品更新绑定接口
     * @param body
     * @return
     */
    @POST("app/goods/update_bind")
    Observable<BaseEntity<ResultEntity>> goodsBindUpdate(@Body RequestBody body);
    /**
     * 商品添加绑定接口
     * @param body
     * @return
     */
    @POST("app/goods/add_bind")
    Observable<BaseEntity<ResultEntity>> goodsBindAdd(@Body RequestBody body);
}