package com.speedata.scanfloat;

import android.content.Intent;
import android.os.SystemProperties;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author zzc
 */
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnBarCode;
    private final String SCAN_BarCode = "com.geomobile.se4500barcode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBarCode = findViewById(R.id.btn_barcode);
        btnBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用扫头
                SystemProperties.set("persist.sys.scanstopimme", "false");
                MainActivity.this.sendBroadcast(new Intent(SCAN_BarCode));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
