package com.xjgy.scanningupload.presenter;

import android.widget.Toast;

import com.xjgy.scanningupload.base.BasePresenterIm;
import com.xjgy.scanningupload.base.RxSubscribe;
import com.xjgy.scanningupload.contract.LoginActivityContract;
import com.xjgy.scanningupload.entity.LoginEntity;
import com.xjgy.scanningupload.model.LoginActivityModel;

import io.reactivex.disposables.Disposable;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/6
 */
public class LoginActivityPresenter extends BasePresenterIm<LoginActivityContract.View> implements LoginActivityContract.Presenter {


    LoginActivityModel loginActivityModel;

    public LoginActivityPresenter() {
        this.loginActivityModel = new LoginActivityModel();
    }

    @Override
    public void pLogin(String username, String password) {
        loginActivityModel.login(username,password).subscribe(new RxSubscribe<LoginEntity>() {
            @Override
            protected void onSuccess(LoginEntity loginEntity) {
                mView.hideLoading();
                mView.showDataSuccess(loginEntity);
            }

            @Override
            protected void onFailed(int code, String msg) {
                mView.hideLoading();
                Toast.makeText(mContext, "登录失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSubscribe(Disposable d) {
                mView.showLoadingView("");
                addSubscription(d);
            }
        });
    }
}
