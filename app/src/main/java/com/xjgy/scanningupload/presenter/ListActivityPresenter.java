package com.xjgy.scanningupload.presenter;

import com.xjgy.scanningupload.base.BasePresenterIm;
import com.xjgy.scanningupload.base.RxSubscribe;
import com.xjgy.scanningupload.contract.ListActivityContract;
import com.xjgy.scanningupload.entity.ListEntity;
import com.xjgy.scanningupload.model.ListActivityModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/9
 */
public class ListActivityPresenter extends BasePresenterIm<ListActivityContract.View>implements ListActivityContract.Presenter {

    ListActivityModel listActivityModel;

    public ListActivityPresenter() {
        this.listActivityModel=new ListActivityModel();
    }

    @Override
    public void pGoodsBindList(String box_number) {
        listActivityModel.getGoodsBindList(box_number).subscribe(new RxSubscribe<List<ListEntity.DataBean>>() {
            @Override
            protected void onSuccess(List<ListEntity.DataBean> strings) {
                mView.showContentView();
                mView.showDataSuccess(strings);
            }

            @Override
            protected void onFailed(int code, String msg) {

                mView.showDataError(msg);
            }

            @Override
            public void onSubscribe(Disposable d) {
                mView.showLoadingView();
                addSubscription(d);
            }
        });
    }
}
