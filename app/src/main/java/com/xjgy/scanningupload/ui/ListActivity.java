package com.xjgy.scanningupload.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.adapter.ListAdapter;
import com.xjgy.scanningupload.adapter.ListSecondAdapter;
import com.xjgy.scanningupload.base.BaseActivity;
import com.xjgy.scanningupload.base.BaseApplication;
import com.xjgy.scanningupload.contract.ListActivityContract;
import com.xjgy.scanningupload.entity.GoodsEntity;
import com.xjgy.scanningupload.entity.ListEntity;
import com.xjgy.scanningupload.presenter.ListActivityPresenter;
import com.xjgy.scanningupload.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品添加列表
 *
 * @author wuyang  create by 2019-08-07
 */
public class ListActivity extends BaseActivity<ListActivityPresenter, List<ListEntity.DataBean>> implements ListActivityContract.View, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.conmonTitleTextView)
    TextView conmonTitleTextView;
    @BindView(R.id.list_recycler)
    RecyclerView listRecycler;
    @BindView(R.id.list_recycler_detail)
    RecyclerView listRecyclerDetail;
    @BindView(R.id.boxnumber)
    TextView boxnumber;
    @BindView(R.id.username)
    TextView username;
    private ListAdapter listAdapter;
    private ListSecondAdapter listSecondAdapter;
    private List<ListEntity.DataBean> listEntities;
    private List<ListEntity.DataBean.GoodsListBean> listSecondEntities;
    private ListEntity.DataBean dataBean;
    private String boxNumber;
    public  static final int GOODSLIST=0;
    public  static final int GOODSDETAILS=1;
    private int secondPostion;
    @Override
    protected void reloadActivity() {

    }

    @Override
    public ListActivityPresenter getPresenter() {
        return new ListActivityPresenter();
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.reloadLayout);
    }

    @Override
    protected void initData() {

        listEntities = new ArrayList<>();
        listSecondEntities = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        listRecycler.setLayoutManager(layoutManager);
        listRecyclerDetail.setLayoutManager(layoutManager2);
        listAdapter = new ListAdapter(listEntities);
        listSecondAdapter = new ListSecondAdapter(listSecondEntities);
        View view = getLayoutInflater().inflate(R.layout.goods_header_item, null);
        listSecondAdapter.addFooterView(view);
        listRecycler.setAdapter(listAdapter);
        listRecyclerDetail.setAdapter(listSecondAdapter);
        listAdapter.setOnItemChildClickListener(this);
        listSecondAdapter.setOnItemChildClickListener(this);
        view.findViewById(R.id.add_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.GOODSBEAN, dataBean);
                startActivityForResult(GoodsActivity.class, bundle,GOODSLIST);
            }
        });

        mPresenter.pGoodsBindList(boxNumber);
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            boxNumber = bundle.getString(Constant.BOXNUMBER);
            boxnumber.setText(boxNumber);
            username.setText(BaseApplication.username);
        }
        conmonTitleTextView.setText("我的商品列表");
    }

    @Override
    public int getContenView() {
        return R.layout.activity_list;
    }

    @Override
    public void showDataSuccess(List<ListEntity.DataBean> datas) {

        listEntities.addAll(datas);
        listEntities.get(0).setSelected(true);
        if (listEntities.size() != 0) {
            List<ListEntity.DataBean.GoodsListBean> goodsList = listEntities.get(0).getGoodsList();
            dataBean = listEntities.get(0);
            dataBean.setBoxnumber(boxNumber);

            listAdapter.notifyDataSetChanged();
            if (goodsList!=null) {
                listSecondEntities.addAll(goodsList);
                listSecondAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.backLayout)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        switch (view.getId()) {
            case R.id.listText:
                listSecondEntities.clear();
                List<ListEntity.DataBean.GoodsListBean> goodsList = listEntities.get(position).getGoodsList();
                if (goodsList!=null) {
                    if (goodsList.size() != 0) {
                        for (int i1 = 0; i1 < goodsList.size(); i1++) {
                            listSecondEntities.add(goodsList.get(i1));
                        }
                    }
                }

                for (int i = 0; i < listEntities.size(); i++) {
                        if (i == position) {
                            listEntities.get(i).setSelected(true);
                            dataBean = listEntities.get(i);
                            dataBean.setBoxnumber(boxNumber);
                        } else {
                            listEntities.get(i).setSelected(false);
                        }

                    }
                    listAdapter.notifyDataSetChanged();
                    listSecondAdapter.notifyDataSetChanged();
                break;
            case R.id.see_details:
                secondPostion=position;
                ListEntity.DataBean.GoodsListBean goodsListBean = listSecondEntities.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.GOODSBEAN, goodsListBean);
                bundle.putString(Constant.BOXNUMBER,boxNumber);
                startActivityForResult(GoodsDetailsActivity.class, bundle,GOODSDETAILS);
                break;
            default:
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data!=null&&resultCode==RESULT_OK){
                Bundle bundle = data.getExtras();
            if (bundle!=null) {

            switch (requestCode){
                case GOODSLIST:
                    GoodsEntity.DataBean.GoodsListBean listBean = (GoodsEntity.DataBean.GoodsListBean) bundle.getSerializable(Constant.GOODSBEAN);
                    ListEntity.DataBean.GoodsListBean goodsListBean = new ListEntity.DataBean.GoodsListBean();
                    goodsListBean.setDiscount_price(listBean.getDiscount_price());
                    goodsListBean.setGoods_id(listBean.getId());
                    goodsListBean.setGoods_name(listBean.getGoods_name());
                    goodsListBean.setGoods_number(listBean.getGoods_number());
                    goodsListBean.setGoods_price(listBean.getGoods_price());
                    goodsListBean.setKey_id(listBean.getKey_id());
                    goodsListBean.setType_id(listBean.getType_id());
                        listSecondEntities.add(goodsListBean);
                        listSecondAdapter.notifyDataSetChanged();
                        //绑定分类总集合更新
                        for (int i = 0; i < listEntities.size(); i++) {
                            if (listBean.getType_id()==listEntities.get(i).getId()){
                                if (listEntities.get(i).getGoodsList()!=null) {
                                    listEntities.get(i).getGoodsList().add(goodsListBean);
                                }else {
                                    List<ListEntity.DataBean.GoodsListBean> goods=new ArrayList();
                                    goods.add(goodsListBean);
                                    listEntities.get(i).setGoodsList(goods);
                                }
                            }
                        }

                    break;

                case GOODSDETAILS:
                    ListEntity.DataBean.GoodsListBean goodsListBean1= (ListEntity.DataBean.GoodsListBean) bundle.getSerializable(Constant.GOODSBEAN);
                    int anInt = bundle.getInt(Constant.ACTIONTYPE);
                    if (anInt==Constant.GOODSUPDATE){

                        listSecondEntities.set(secondPostion,goodsListBean1);
                        listSecondAdapter.notifyItemChanged(secondPostion);
                        for (int i = 0; i < listEntities.size(); i++) {
                            //绑定分类总集合更新
                            if (listEntities.get(i).isSelected()){

                                listEntities.get(i).getGoodsList().set(secondPostion,goodsListBean1);
                            }
                        }
                    }
                    if (anInt==Constant.GOODSDELETE){
                        listSecondEntities.remove(secondPostion);
                        listSecondAdapter.notifyItemRemoved(secondPostion);
                        for (int i = 0; i < listEntities.size(); i++) {
                            //绑定分类总集合更新
                            if (listEntities.get(i).isSelected()){

                                listEntities.get(i).getGoodsList().remove(secondPostion);
                            }
                        }
                    }
                    break;
                    default:
            }
        }

    }
    }
}
