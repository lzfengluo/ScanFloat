package com.speedata.scanfloat;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.speedata.scanfloat.utils.SharedXmlUtil;

/**
 * @author zzc
 */
public class MainActivity extends AppCompatActivity {

    private FloatBtnManager floatBtnManager;
    private Switch btnOpenFloat;
    private String isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatBtnManager = FloatBtnManager.getFloatBtnManager();

        initView();
        startFloatBtn();
    }

    private void initView() {
        btnOpenFloat = findViewById(R.id.switch_scan);
        btnOpenFloat.setChecked(true);
        SharedXmlUtil.getInstance(MainActivity.this).write("scanBtn", "true");
        btnOpenFloat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedXmlUtil.getInstance(MainActivity.this).write("scanBtn", "true");
                } else {
                    SharedXmlUtil.getInstance(MainActivity.this).write("scanBtn", "false");
                }
            }
        });
    }

    public void startFloatBtn() {
        isOpen = SharedXmlUtil.getInstance(this).read("scanBtn", "true");
        if ("true".equals(isOpen)) {
            if (floatBtnManager == null) {
                //获取实例对象
                floatBtnManager = FloatBtnManager.getInstance(getApplicationContext());
                //初始化悬浮按钮
                floatBtnManager.initButton();
            }

        }
    }

    public void releaseButton() {
        isOpen = SharedXmlUtil.getInstance(this).read("scanBtn", "true");
        if ("false".equals(isOpen) && floatBtnManager != null) {
            floatBtnManager.destroy();
            floatBtnManager = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseButton();
    }
}
