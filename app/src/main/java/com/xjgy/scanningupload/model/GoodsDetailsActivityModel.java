package com.xjgy.scanningupload.model;

import com.xjgy.scanningupload.api.ApiManager;
import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.contract.GoodsDetailsActivityContract;
import com.xjgy.scanningupload.entity.KeyEntity;
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
 * @author Created by wuyang on 2019/8/9
 */
public class GoodsDetailsActivityModel implements GoodsDetailsActivityContract.Model {
    @Override
    public Observable<BaseEntity<List<KeyEntity.DataBean>>> getKeyList(String box_number) {
        RequestBodyBuilder.Builder builder = new RequestBodyBuilder.Builder();
        RequestBody requestBody = builder
                .params("box_number",box_number)
                .build();
        return ApiManager.getApistore().goodsKeyList(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<ResultEntity>> deleteGoods(SubmitEntity submitEntity) {
        RequestBodyBuilder.Builder builder = new RequestBodyBuilder.Builder();
        RequestBody requestBody = builder.build(submitEntity);
        return ApiManager.getApistore().goodsBindDelete(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<ResultEntity>> updateGoods(SubmitEntity submitEntity) {
        RequestBodyBuilder.Builder builder = new RequestBodyBuilder.Builder();
        RequestBody requestBody = builder.build(submitEntity);
        return ApiManager.getApistore().goodsBindUpdate(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
