package com.xjgy.scanningupload.contract;

import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.base.BaseView;
import com.xjgy.scanningupload.entity.GoodsEntity;
import com.xjgy.scanningupload.entity.ResultEntity;
import com.xjgy.scanningupload.entity.SubmitEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/2
 */
public interface GoodsActivityContract {
    interface Model {
        Observable<BaseEntity<List<GoodsEntity.DataBean>>> getGoodsList(String box_number);

        Observable<BaseEntity<ResultEntity>> addGoods(SubmitEntity submitEntity);
    }

    interface View extends BaseView<List<GoodsEntity.DataBean>> {

        void showSubmitSuccess();
    }

    interface Presenter {

        void pGoodsList(String box_number);

        void pAddGoods(SubmitEntity submitEntity);
    }
}
