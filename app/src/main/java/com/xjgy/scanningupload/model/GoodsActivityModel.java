package com.xjgy.scanningupload.model;

import com.xjgy.scanningupload.api.ApiManager;
import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.contract.GoodsActivityContract;
import com.xjgy.scanningupload.entity.GoodsEntity;
import com.xjgy.scanningupload.entity.ResultEntity;
import com.xjgy.scanningupload.entity.SubmitEntity;
import com.xjgy.scanningupload.utils.RequestBodyBuilder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/2
 */
public class GoodsActivityModel implements GoodsActivityContract.Model {
    @Override
    public Observable<BaseEntity<List<GoodsEntity.DataBean>>> getGoodsList(String box_number) {
        RequestBodyBuilder.Builder builder = new RequestBodyBuilder.Builder();
        RequestBody requestBody = builder
                .params("box_number",box_number)
                .build();
        return ApiManager.getApistore().goodsList(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<ResultEntity>> addGoods(SubmitEntity submitEntity) {

        RequestBodyBuilder.Builder builder = new RequestBodyBuilder.Builder();
        RequestBody requestBody = builder.build(submitEntity);
        return ApiManager.getApistore().goodsBindAdd(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
