package com.xjgy.scanningupload.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.base.BaseActivity;
import com.xjgy.scanningupload.base.BaseApplication;
import com.xjgy.scanningupload.contract.LoginActivityContract;
import com.xjgy.scanningupload.entity.LoginEntity;
import com.xjgy.scanningupload.presenter.LoginActivityPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 *
 * @author wuyang  create by 2019-08-05
 */
public class LoginActivity extends BaseActivity<LoginActivityPresenter, LoginEntity> implements LoginActivityContract.View {


    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.editText_user)
    EditText editTextUser;
    @BindView(R.id.textInput_user)
    TextInputLayout textInputUser;
    @BindView(R.id.editText_password)
    EditText editTextPassword;
    @BindView(R.id.textInput_password)
    TextInputLayout textInputPassword;


    @Override
    public LoginActivityPresenter getPresenter() {
        return new LoginActivityPresenter();
    }

    @Override
    protected void reloadActivity() {

    }

    @Override
    protected View injectTarget() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getContenView() {
        return R.layout.activity_login;
    }

    @Override
    public void showDataSuccess(LoginEntity datas) {
        BaseApplication.username=editTextUser.getText().toString();
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View v) {

        switch (v.getId()) {
            case R.id.btn_login:
                String username=editTextUser.getText().toString();
                String password=editTextPassword.getText().toString();
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }
                mPresenter.pLogin(username,password);
                break;
            default:
        }
    }

}
