package com.xjgy.scanningupload.contract;

import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.base.BaseView;
import com.xjgy.scanningupload.entity.LoginEntity;

import io.reactivex.Observable;

/**
 * Desccribe:登录控制器
 *
 * @author Created by wuyang on 2019/8/6
 */
public interface LoginActivityContract {
    interface Model {
        Observable<BaseEntity<LoginEntity>> login(String username, String password);
    }

    interface View extends BaseView<LoginEntity> {
    }

    interface Presenter {
        void pLogin(String username,String password);
    }
}
