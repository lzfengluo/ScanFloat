package com.speedata.scanfloat;

import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

/**
 * @author zzc
 */
public class FloatBtnManager {
    private static FloatBtnManager floatBtnManager;
    private Context context;
    private ImageView imageView;
    /**
     * 启用扫描服务的广播
     */
    private final String SCAN_BarCode = "com.geomobile.se4500barcode";

    private FloatBtnManager(Context context) {
        this.context = context;
    }

    public static FloatBtnManager getFloatBtnManager() {
        return floatBtnManager;
    }

    public static FloatBtnManager getInstance(Context context) {
        if (floatBtnManager == null) {
            floatBtnManager = new FloatBtnManager(context);
        }
        return floatBtnManager;
    }

    public void initButton() {
        if (floatBtnManager != null) {
            startFloatBtn();
        }
    }

    private void startFloatBtn() {
        imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.scan3);
        FloatWindow
                .with(context)
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
                context.sendBroadcast(new Intent(SCAN_BarCode));
            }
        });
    }

    public void destroy() {
        if (floatBtnManager != null) {
            FloatWindow.destroy("FloatWindow");
            floatBtnManager = null;
        }
    }
}
