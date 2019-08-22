package com.xjgy.scanningupload.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.entity.ListEntity;

import java.util.List;

/**
 * Desccribe:
 *
 * @author Created by wuyang on 2019/8/7
 */
public class ListAdapter extends BaseQuickAdapter<ListEntity.DataBean, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ListAdapter(List<ListEntity.DataBean> data) {
        super(R.layout.item_list_title,data);

    }

    @Override
    protected void convert( BaseViewHolder helper,ListEntity.DataBean item) {

            helper.setText(R.id.listText,item.getType_name());
            if (item.isSelected()){
                helper.getView(R.id.listText).setBackgroundColor(mContext.getResources().getColor(R.color.white));
            }else {
                helper.getView(R.id.listText).setBackgroundDrawable(null);
            }
            helper.addOnClickListener(R.id.listText);
    }
}
