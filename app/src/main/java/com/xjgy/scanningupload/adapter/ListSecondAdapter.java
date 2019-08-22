package com.xjgy.scanningupload.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.entity.ListEntity;

import java.util.List;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/8
 */
public class ListSecondAdapter extends BaseQuickAdapter<ListEntity.DataBean.GoodsListBean, BaseViewHolder> {
    public ListSecondAdapter(@Nullable List<ListEntity.DataBean.GoodsListBean> data) {
        super(R.layout.item_listsecond_details,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListEntity.DataBean.GoodsListBean item) {

        helper.setText(R.id.foodname,item.getGoods_name());
        helper.setText(R.id.prices,item.getGoods_price()+"");
        helper.setText(R.id.discount_prices,item.getDiscount_price()+"");
        helper.addOnClickListener(R.id.see_details);
    }
}
