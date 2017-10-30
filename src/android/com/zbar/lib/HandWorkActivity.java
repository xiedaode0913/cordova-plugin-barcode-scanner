package com.zbar.lib;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import __PACKAGE_NAME__.R;

/**
 * Created by Francis.Huang on 2017/10/25.
 */

public class HandWorkActivity extends Activity implements View.OnClickListener{

    //返回键
    private LinearLayout llytLeftBtn;
    //输入框
    private EditText etInput;
    //底部确定按钮
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        initView();

        initListener();
    }

    private void initListener() {
        llytLeftBtn.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    private void initView() {
        llytLeftBtn = (LinearLayout) findViewById(R.id.llyt_left_btn);

        etInput = (EditText) findViewById(R.id.et_input);

        btnConfirm = (Button) findViewById(R.id.btn_confirm);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llyt_left_btn:
                finish();
                break;
            case R.id.btn_confirm:
                confirmNo();
                break;
        }
    }

    //确定输入框
    private void confirmNo() {
        if (TextUtils.isEmpty(etInput.getText().toString())){
            Toast.makeText(HandWorkActivity.this, getString(R.string.confirm_error), Toast.LENGTH_SHORT).show();
            return;
        }
        getIntent().putExtra("data", etInput.getText().toString());
        setResult(1, getIntent());
        finish();
    }
}
