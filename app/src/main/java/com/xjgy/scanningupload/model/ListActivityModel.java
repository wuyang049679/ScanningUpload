package com.xjgy.scanningupload.model;

import com.xjgy.scanningupload.api.ApiManager;
import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.contract.ListActivityContract;
import com.xjgy.scanningupload.entity.ListEntity;
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
public class ListActivityModel implements ListActivityContract.Model {
    @Override
    public Observable<BaseEntity<List<ListEntity.DataBean>>> getGoodsBindList(String box_number) {
        RequestBodyBuilder.Builder builder = new RequestBodyBuilder.Builder();
        RequestBody requestBody = builder
                .params("box_number",box_number)
                .build();
        return ApiManager.getApistore().goodsBindList(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
