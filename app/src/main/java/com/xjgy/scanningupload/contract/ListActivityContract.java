package com.xjgy.scanningupload.contract;

import com.xjgy.scanningupload.base.BaseEntity;
import com.xjgy.scanningupload.base.BaseView;
import com.xjgy.scanningupload.entity.ListEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/9
 */
public interface ListActivityContract {
    interface Model {
        Observable<BaseEntity<List<ListEntity.DataBean>>> getGoodsBindList(String box_number);
    }

    interface View extends BaseView<List<ListEntity.DataBean>> {
    }

    interface Presenter {
        void pGoodsBindList(String box_number);
    }
}
