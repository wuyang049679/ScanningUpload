package com.xjgy.scanningupload.model;

import com.xjgy.scanningupload.api.ApiManager;
import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.contract.LoginActivityContract;
import com.xjgy.scanningupload.entity.LoginEntity;
import com.xjgy.scanningupload.utils.RequestBodyBuilder;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/6
 */
public class LoginActivityModel implements LoginActivityContract.Model {
    @Override
    public Observable<BaseEntity<LoginEntity>> login(String username, String password) {
        RequestBodyBuilder.Builder builder=new RequestBodyBuilder.Builder()
                .params("version","v1.0")
                //表示用户来源
                .params("createwhere","2")
                .params("username",username)
                .params("password",password);
        return ApiManager.getApistore().login(builder.build()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
