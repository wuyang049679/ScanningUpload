package com.xjgy.scanningupload.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xjgy.scanningupload.R;
import com.xjgy.scanningupload.entity.BoxEntity;
import com.xjgy.scanningupload.utils.Constant;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wuyang
 */
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.button)
    ImageView button;
    private int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.button:
                AndPermission.with(this)
                        .runtime()
                        .permission(Permission.Group.CAMERA)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {
                                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                                startActivityForResult(intent, REQUEST_CODE);
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> data) {

                            }
                        })
                        .start();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }


                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    result = new String(Base64.decode(result.getBytes(), Base64.DEFAULT));
                    Intent intent=new Intent(MainActivity.this,ListActivity.class);
                    BoxEntity boxEntity = new Gson().fromJson(result, BoxEntity.class);
                    bundle.putString(Constant.BOXNUMBER,boxEntity.getSerial());
                    intent.putExtras(bundle);
                    startActivity(intent);

                    Log.i("tag",result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}
