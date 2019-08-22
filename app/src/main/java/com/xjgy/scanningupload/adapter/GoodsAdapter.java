package com.xjgy.scanningupload.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.entity.GoodsEntity;

import java.util.List;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/2
 */
public class GoodsAdapter extends BaseQuickAdapter<GoodsEntity.DataBean.GoodsListBean, BaseViewHolder> {
    public GoodsAdapter(@Nullable List<GoodsEntity.DataBean.GoodsListBean> data) {
        super(R.layout.goods_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsEntity.DataBean.GoodsListBean item) {

            helper.setText(R.id.goodsName,item.getGoods_name());
            helper.addOnClickListener(R.id.goodsName);
    }
}
