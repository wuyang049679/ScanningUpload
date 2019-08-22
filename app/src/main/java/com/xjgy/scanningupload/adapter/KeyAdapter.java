package com.xjgy.scanningupload.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.entity.KeyEntity;

import java.util.List;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/15
 */
public class KeyAdapter extends BaseQuickAdapter<KeyEntity.DataBean, BaseViewHolder> {

    public KeyAdapter(@Nullable List<KeyEntity.DataBean> data) {
        super(R.layout.key_adapter_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyEntity.DataBean item) {
        helper.setText(R.id.key_tv,item.getKey_name());
        helper.addOnClickListener(R.id.key_tv);
    }
}
