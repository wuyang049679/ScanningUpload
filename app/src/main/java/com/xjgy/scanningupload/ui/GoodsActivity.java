package com.xjgy.scanningupload.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.adapter.GoodsAdapter;
import com.xjgy.scanningupload.base.BaseActivity;
import com.xjgy.scanningupload.contract.GoodsActivityContract;
import com.xjgy.scanningupload.entity.GoodsEntity;
import com.xjgy.scanningupload.entity.ListEntity;
import com.xjgy.scanningupload.entity.SubmitEntity;
import com.xjgy.scanningupload.presenter.GoodsActivityPresenter;
import com.xjgy.scanningupload.utils.Constant;
import com.xjgy.scanningupload.utils.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuyang  19.02.01
 */
public class GoodsActivity extends BaseActivity<GoodsActivityPresenter,List<GoodsEntity.DataBean>> implements  GoodsActivityContract.View, View.OnClickListener {


    @BindView(R.id.backLayout)
    LinearLayout backLayout;
    @BindView(R.id.conmonTitleTextView)
    TextView conmonTitleTextView;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private GoodsAdapter adapter;
    private String box_number;
    private String qr_code;
    private List<GoodsEntity.DataBean.GoodsListBean> beanList;
    private CustomDialog dialog;
    private View.OnClickListener listener;
    private GoodsEntity.DataBean.GoodsListBean dataBean;
    private int positions;
    private ListEntity.DataBean bean;
    private GoodsEntity.DataBean.GoodsListBean listBean;
    @Override
    protected void reloadActivity() {

    }

    @Override
    public GoodsActivityPresenter getPresenter() {
        return new GoodsActivityPresenter();
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.reloadLayout);
    }

    @Override
    protected void initData() {

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        beanList=new ArrayList<>();
        adapter=new GoodsAdapter(beanList);
        recycler.setAdapter(adapter);
        listener=this;
        backLayout.setOnClickListener(listener);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsEntity.DataBean.GoodsListBean  listBean= (GoodsEntity.DataBean.GoodsListBean) adapter.getData().get(position);
                if (bean.getGoodsList()!=null) {
                    for (int i = 0; i < bean.getGoodsList().size(); i++) {
                        if (listBean.getId() == bean.getGoodsList().get(i).getGoods_id()) {
                            Toast.makeText(GoodsActivity.this, "该商品已添加!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                submitGoods(position);

            }
        });

        mPresenter.pGoodsList(box_number);
    }

    @Override
    protected void initView() {
        conmonTitleTextView.setText("商品列表");

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            bean= (ListEntity.DataBean) bundle.getSerializable(Constant.GOODSBEAN);
            conmonTitleTextView.setText(bean.getType_name());
            box_number=bean.getBoxnumber();
        }

    }

    @Override
    public int getContenView() {
        return R.layout.activity_goods;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



    @Override
    public void showDataSuccess(List<GoodsEntity.DataBean> datas) {

        if (datas!=null){
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).getId()==bean.getId()){
                    beanList.addAll(datas.get(i).getGoodsList());
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showDataError(String errorMessage) {
        super.showDataError(errorMessage);
        Toast.makeText(this, "加载失败:" + errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSubmitSuccess() {

        //因为有添加header刷新时position+1
//        adapter.notifyItemChanged(positions+1,dataBean);
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constant.GOODSBEAN,listBean);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.backLayout:
                finish();
                break;
            case R.id.dialog_cancel:
                if (dialog!=null){dialog.dismiss();}
                break;
            case R.id.dialog_save:

                if (dialog!=null){

                    EditText name = (EditText) dialog.getView(R.id.edit_goodsName);
                    EditText price = (EditText) dialog.getView(R.id.edit_goodsPrice);
                    String textname = name.getText().toString().trim();
                    String textprice = price.getText().toString().trim();
                    if (TextUtils.isEmpty(textname)){

                        Toast.makeText(this, "商品名称不能为空" , Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (TextUtils.isEmpty(textprice)){

                        Toast.makeText(this, "商品价格不能为空" , Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (dataBean!=null){
                        dataBean.setGoods_name(textname);
                        dataBean.setGoods_price(Float.parseFloat(textprice));
                    }

                    dialog.dismiss();
                }

                break;
            default:
                break;
        }
    }

    /**
     * 添加一个商品
     * @param position
     */
    private void submitGoods(int position) {

        SubmitEntity submitEntity=new SubmitEntity();

        List<SubmitEntity.GoodsListBean> goodsListBeans=new ArrayList<>();

            SubmitEntity.GoodsListBean goodsListBean=new SubmitEntity.GoodsListBean();
            listBean = beanList.get(position);

            goodsListBean.setDiscount_price(listBean.getDiscount_price());
            goodsListBean.setGoods_number(listBean.getGoods_number());
            goodsListBean.setGoods_price(listBean.getGoods_price());
            goodsListBean.setKey_id(listBean.getKey_id());
            goodsListBean.setType_id(listBean.getType_id());
            goodsListBean.setGoods_id(listBean.getId());
            goodsListBeans.add(goodsListBean);

        submitEntity.setGoods_list(goodsListBeans);
        submitEntity.setBox_number(box_number);

        mPresenter.pAddGoods(submitEntity);
    }


}
