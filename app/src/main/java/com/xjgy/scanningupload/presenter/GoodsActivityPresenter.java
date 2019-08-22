package com.xjgy.scanningupload.presenter;

import android.widget.Toast;

import com.xjgy.scanningupload.base.BasePresenterIm;
import com.xjgy.scanningupload.base.RxSubscribe;
import com.xjgy.scanningupload.contract.GoodsActivityContract;
import com.xjgy.scanningupload.entity.GoodsEntity;
import com.xjgy.scanningupload.entity.ResultEntity;
import com.xjgy.scanningupload.entity.SubmitEntity;
import com.xjgy.scanningupload.model.GoodsActivityModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/2
 */
public class GoodsActivityPresenter extends BasePresenterIm<GoodsActivityContract.View>implements GoodsActivityContract.Presenter {


    GoodsActivityModel goodsActivityModel;


    public GoodsActivityPresenter() {
        this.goodsActivityModel = new GoodsActivityModel();
    }

    @Override
    public void pGoodsList(String box_number) {

        goodsActivityModel.getGoodsList(box_number).subscribe(new RxSubscribe<List<GoodsEntity.DataBean>>() {
            @Override
            protected void onSuccess(List<GoodsEntity.DataBean> strings) {
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

    @Override
    public void pAddGoods(SubmitEntity submitEntity) {
        goodsActivityModel.addGoods(submitEntity).subscribe(new RxSubscribe<ResultEntity>() {
            @Override
            protected void onSuccess(ResultEntity resultEntity) {
                mView.showSubmitSuccess();
            }

            @Override
            protected void onFailed(int code, String msg) {
                Toast.makeText(mContext,"操作失败："+msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSubscribe(Disposable d) {

                addSubscription(d);
            }
        });
    }
}
