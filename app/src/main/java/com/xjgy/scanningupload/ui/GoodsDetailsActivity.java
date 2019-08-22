package com.xjgy.scanningupload.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.zhouwei.library.CustomPopWindow;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.adapter.KeyAdapter;
import com.xjgy.scanningupload.base.BaseActivity;
import com.xjgy.scanningupload.contract.GoodsDetailsActivityContract;
import com.xjgy.scanningupload.entity.KeyEntity;
import com.xjgy.scanningupload.entity.ListEntity;
import com.xjgy.scanningupload.entity.ResultEntity;
import com.xjgy.scanningupload.entity.SubmitEntity;
import com.xjgy.scanningupload.presenter.GoodsDetailsActivityPresenter;
import com.xjgy.scanningupload.utils.Constant;
import com.xjgy.scanningupload.utils.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情，编辑页面
 *
 * @author wuyang create by 2019-08-08
 */
public class GoodsDetailsActivity extends BaseActivity<GoodsDetailsActivityPresenter, List<KeyEntity.DataBean>> implements GoodsDetailsActivityContract.View, View.OnClickListener {


    @BindView(R.id.backLayout)
    LinearLayout backLayout;
    @BindView(R.id.conmonTitleTextView)
    TextView conmonTitleTextView;
    @BindView(R.id.shortcut_keys)
    TextView shortcutKeys;
    @BindView(R.id.goods_type)
    TextView goodsType;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.good_code)
    TextView goodCode;
    @BindView(R.id.prices_tv)
    TextView pricesTv;
    @BindView(R.id.prices_iv)
    ImageView pricesIv;
    @BindView(R.id.discount_prices_tv)
    TextView discountPricesTv;
    @BindView(R.id.discount_prices_iv)
    ImageView discountPricesIv;
    @BindView(R.id.shortcut_keys_iv)
    ImageView shortcutKeysIv;
    @BindView(R.id.goods_delete)
    Button goodsDelete;
    @BindView(R.id.commonTitleLayout)
    LinearLayout commonTitleLayout;
    @BindView(R.id.details_layout)
    LinearLayout detailsLayout;
    private CustomDialog dialog;
    public final static int GOODS_PRICE = 0;
    public final static int DISCOUNT_PRICE = 1;
    public final static int SHORTCUT_KEYS = 2;
    private int dialogType;
    private View.OnClickListener listener;
    private ListEntity.DataBean.GoodsListBean goodsListBean;
    private String boxnumber;
    private List<KeyEntity.DataBean> keyList;
    private boolean hasUpdate;
    private KeyAdapter keyAdapter;
    private CustomPopWindow customPopWindow;


    @Override
    protected void reloadActivity() {

    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.details_layout);
    }

    @Override
    public GoodsDetailsActivityPresenter getPresenter() {
        return new GoodsDetailsActivityPresenter();
    }

    @Override
    protected void initData() {
        listener = this;
        keyList=new ArrayList<>();
        if (goodsListBean!=null){

            goodsName.setText(goodsListBean.getGoods_name());
            goodCode.setText(goodsListBean.getGoods_number());
            pricesTv.setText(goodsListBean.getGoods_price()+"");
            discountPricesTv.setText(goodsListBean.getDiscount_price()+"");
            shortcutKeys.setText(goodsListBean.getKey_id()+"");
        }

    }


    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            goodsListBean= (ListEntity.DataBean.GoodsListBean) bundle.getSerializable(Constant.GOODSBEAN);
            if (goodsListBean.getGoods_name()!=null) {
                conmonTitleTextView.setText(goodsListBean.getGoods_name());
                boxnumber=bundle.getString(Constant.BOXNUMBER);
            }
        }
    }

    @Override
    public int getContenView() {
        return R.layout.activity_goods_details;
    }

    @Override
    public void showDataSuccess(List<KeyEntity.DataBean> datas) {

    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dialog_cancel:
                dialog.dismiss();
                break;
            case R.id.dialog_save:
                dialog.dismiss();
                String value=((TextView)dialog.getView(R.id.edit_goodsName)).getText().toString();
                switch (dialogType) {

                    case GOODS_PRICE:
                        pricesTv.setText(value);
                        goodsListBean.setGoods_price(Double.parseDouble(value));
                        break;
                    case DISCOUNT_PRICE:
                        discountPricesTv.setText(value);
                        goodsListBean.setDiscount_price(Double.parseDouble(value));
                        break;
                    case SHORTCUT_KEYS:
                        shortcutKeys.setText(value);
                        goodsListBean.setKey_id(Integer.parseInt(value));
                        break;
                    default:
                }

                submitGoods(Constant.GOODSUPDATE);
                break;

            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.prices_iv, R.id.discount_prices_iv, R.id.shortcut_keys_iv, R.id.goods_delete,R.id.backLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.prices_iv:
                dialogType = GOODS_PRICE;
                editDialog();
                break;
            case R.id.discount_prices_iv:
                dialogType = DISCOUNT_PRICE;
                editDialog();
                break;
            case R.id.shortcut_keys_iv:
                dialogType = SHORTCUT_KEYS;
//                editDialog();
                keyList.clear();
                mPresenter.pKeyList(boxnumber);
                break;
            case R.id.goods_delete:
                submitGoods(Constant.GOODSDELETE);
                break;
            case R.id.backLayout:
                onBackPressed();
                break;
            default:
        }
    }

    private void editPopwindow() {

        View view=LayoutInflater.from(this).inflate(R.layout.key_popwindow_layout,null);
        RecyclerView recyclerView=view.findViewById(R.id.key_recycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        keyAdapter=new KeyAdapter(keyList);
        recyclerView.setAdapter(keyAdapter);

        customPopWindow=new CustomPopWindow.PopupWindowBuilder(this)
                .setView(view)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .create()
                .showAsDropDown(shortcutKeysIv,0,20);
        keyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                KeyEntity.DataBean  dataBean = (KeyEntity.DataBean) adapter.getData().get(position);
                shortcutKeys.setText(dataBean.getKey_name());
                goodsListBean.setKey_id(dataBean.getId());
                customPopWindow.dissmiss();
                submitGoods(Constant.GOODSUPDATE);
            }
        });
    }

    private void editDialog() {
        String keys = "";
        String value = "";
        switch (dialogType) {
            case GOODS_PRICE:
                keys = "价格";
                value = pricesTv.getText().toString();
                break;
            case DISCOUNT_PRICE:
                keys = "折扣价";
                value = discountPricesTv.getText().toString();
                break;
            case SHORTCUT_KEYS:
                keys = "快捷键";
                value = shortcutKeys.getText().toString();
                break;
            default:
        }
        CustomDialog.Builder builder = new CustomDialog.Builder(GoodsDetailsActivity.this);
        dialog = builder.view(R.layout.dialog_goods_update)
                .heightDimenRes(R.dimen.dialog_height)
                .widthDimenRes(R.dimen.dialog_witch)
                .style(R.style.Dialog)
                .setViewText(R.id.edit_goodsName, value)
                .setViewText(R.id.edit_key, keys)
                .addViewOnclick(R.id.dialog_cancel, listener)
                .addViewOnclick(R.id.dialog_save, listener)
                .build();
        dialog.show();

    }

    @Override
    public void showKeyList(List<KeyEntity.DataBean> list) {
        if (list!=null) {
            keyList.addAll(list);
            editPopwindow();

        }
    }

    @Override
    public void deleteSuccess(ResultEntity resultEntity) {
        Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putSerializable(Constant.GOODSBEAN,goodsListBean);
        bundle.putInt(Constant.ACTIONTYPE,Constant.GOODSDELETE);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void updateSuccess(ResultEntity resultEntity) {
        Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        hasUpdate=true;
    }


    /**
     * 提交商品信息
     * @param
     */
    private void submitGoods(int type) {

        SubmitEntity submitEntity=new SubmitEntity();

        List<SubmitEntity.GoodsListBean> goodsListBeans=new ArrayList<>();

        SubmitEntity.GoodsListBean listBean=new SubmitEntity.GoodsListBean();

        listBean.setId(goodsListBean.getId());
        listBean.setGoods_id(goodsListBean.getGoods_id());
        listBean.setDiscount_price(goodsListBean.getDiscount_price());
        listBean.setGoods_price(goodsListBean.getGoods_price());
        listBean.setKey_id(goodsListBean.getKey_id());
        listBean.setType_id(goodsListBean.getType_id());
        listBean.setGoods_number(goodsListBean.getGoods_number());
        goodsListBeans.add(listBean);

        submitEntity.setGoods_list(goodsListBeans);
        submitEntity.setBox_number(boxnumber);

        if (type==Constant.GOODSUPDATE) {
            mPresenter.pupdateGoods(submitEntity);
        }
        if (type==Constant.GOODSDELETE) {
            mPresenter.pdeleteGoods(submitEntity);
        }
    }


    @Override
    public void onBackPressed() {


        if(hasUpdate) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.GOODSBEAN, goodsListBean);
            bundle.putInt(Constant.ACTIONTYPE, Constant.GOODSUPDATE);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }else {
            super.onBackPressed();
        }
    }
}
