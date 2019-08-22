package com.xjgy.scanningupload.presenter;

import android.widget.Toast;

import com.xjgy.scanningupload.base.BasePresenterIm;
import com.xjgy.scanningupload.base.RxSubscribe;
import com.xjgy.scanningupload.contract.GoodsDetailsActivityContract;
import com.xjgy.scanningupload.entity.KeyEntity;
import com.xjgy.scanningupload.entity.ResultEntity;
import com.xjgy.scanningupload.entity.SubmitEntity;
import com.xjgy.scanningupload.model.GoodsDetailsActivityModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/9
 */
public class GoodsDetailsActivityPresenter extends BasePresenterIm<GoodsDetailsActivityContract.View> implements GoodsDetailsActivityContract.Presenter {


    GoodsDetailsActivityModel detailsActivityModel;

    public GoodsDetailsActivityPresenter() {
        detailsActivityModel=new GoodsDetailsActivityModel();
    }

    @Override
    public void pKeyList(String box_number) {

        detailsActivityModel.getKeyList(box_number).subscribe(new RxSubscribe<List<KeyEntity.DataBean>>() {
            @Override
            protected void onSuccess(List<KeyEntity.DataBean> strings) {
                mView.showContentView();
                mView.showKeyList(strings);
            }

            @Override
            protected void onFailed(int code, String msg) {

                Toast.makeText(mContext,"获取列表失败："+msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSubscribe(Disposable d) {
                mView.showLoadingView();
                addSubscription(d);
            }
        });
    }

    @Override
    public void pdeleteGoods(SubmitEntity submitEntity) {
        detailsActivityModel.deleteGoods(submitEntity).subscribe(new RxSubscribe<ResultEntity>() {
            @Override
            protected void onSuccess(ResultEntity resultEntity) {
                mView.deleteSuccess(resultEntity);
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

    @Override
    public void pupdateGoods(SubmitEntity submitEntity) {
        detailsActivityModel.updateGoods(submitEntity).subscribe(new RxSubscribe<ResultEntity>() {
            @Override
            protected void onSuccess(ResultEntity resultEntity) {
                mView.updateSuccess(resultEntity);
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
