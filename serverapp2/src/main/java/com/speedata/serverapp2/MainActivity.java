package com.speedata.serverapp2;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemProperties;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

/**
 * @author zzc
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "FloatBallManager";
    private ImageView imageView;
    private final String SCAN_BarCode = "com.geomobile.se4500barcode";

    private TextView tvBarCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFloatBtn();
        tvBarCode = findViewById(R.id.tv_code);
    }

    public void startFloatBtn() {
        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.scan);
        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                //设置悬浮控件宽高
                .setWidth(Screen.width, 0.15f)
                .setHeight(Screen.width, 0.15f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.slide, 50, -80)
                .setMoveStyle(500, new BounceInterpolator())
                .setDesktopShow(true)
                .setTag("FloatWindow")
                .build();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用扫头
                SystemProperties.set("persist.sys.scanstopimme", "false");
                MainActivity.this.sendBroadcast(new Intent(SCAN_BarCode));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FloatWindow.destroy("FloatWindow");
    }
}
