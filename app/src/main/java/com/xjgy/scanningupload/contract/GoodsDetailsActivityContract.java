package com.xjgy.scanningupload.contract;

import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.base.BaseView;
import com.xjgy.scanningupload.entity.KeyEntity;
import com.xjgy.scanningupload.entity.ResultEntity;
import com.xjgy.scanningupload.entity.SubmitEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/9
 */
public interface GoodsDetailsActivityContract {
    interface Model {
        Observable<BaseEntity<List<KeyEntity.DataBean>>> getKeyList(String box_number);

        Observable<BaseEntity<ResultEntity>> deleteGoods(SubmitEntity submitEntity);

        Observable<BaseEntity<ResultEntity>> updateGoods(SubmitEntity submitEntity);
    }

    interface View extends BaseView<List<KeyEntity.DataBean>> {

        void showKeyList(List<KeyEntity.DataBean> list);

        void deleteSuccess(ResultEntity resultEntity);

        void updateSuccess(ResultEntity resultEntity);
    }

    interface Presenter {
        void pKeyList(String box_number);

        void pdeleteGoods(SubmitEntity submitEntity);

        void pupdateGoods(SubmitEntity submitEntity);
    }
}
