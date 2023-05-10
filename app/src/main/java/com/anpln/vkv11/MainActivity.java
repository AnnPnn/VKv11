package com.anpln.vkv11;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_STORAGE = 101;

    private TextView tvPermission;
    private Button btnPermission;
    private CheckBox cbSize;
    private CheckBox cbDate;
    private CheckBox cbName;

    public static String sort;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPermission = findViewById(R.id.tvPermission);
        btnPermission = findViewById(R.id.btnPermission);
        cbSize = findViewById(R.id.checkBoxSize);
        cbDate = findViewById(R.id.checkBoxDate);
        cbName = findViewById(R.id.checkBoxName);
        Button btnOpen = findViewById(R.id.buttonOpen);

        if (PermissionUtils.hasPermissions(this)) {
            tvPermission.setText("Разрешение получено");
            btnPermission.setVisibility(View.INVISIBLE);
        } else {
            tvPermission.setText("Разрешение не предоставлено");
        }
        btnPermission.setOnClickListener(v -> {
            if (PermissionUtils.hasPermissions(MainActivity.this)) return;
            PermissionUtils.requestPermissions(MainActivity.this, PERMISSION_STORAGE);
        });

        btnOpen.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FilesActivity.class);
            startActivity(intent);

        });
        cbName.setOnClickListener(checkboxListener);
        cbSize.setOnClickListener(checkboxListener);
        cbDate.setOnClickListener(checkboxListener);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PERMISSION_STORAGE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (PermissionUtils.hasPermissions(this)) {
                    tvPermission.setText("Разрешение получено");
                    btnPermission.setVisibility(View.INVISIBLE);
                } else {
                    tvPermission.setText("Разрешение не предоставлено");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Версии > 11
    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tvPermission.setText("Разрешение получено");
                btnPermission.setVisibility(View.INVISIBLE);
            } else {
                tvPermission.setText("Разрешение не предоставлено");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    View.OnClickListener checkboxListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == cbDate){
                    cbDate.setChecked(true);
                    cbName.setChecked(false);
                    cbSize.setChecked(false);
                    sort = "Date";
                } else if (v == cbName){
                    cbName.setChecked(true);
                    cbDate.setChecked(false);
                    cbSize.setChecked(false);
                    sort = "Name";
                }
                else if (v == cbSize){
                    cbSize.setChecked(true);
                    cbDate.setChecked(false);
                    cbName.setChecked(false);
                    sort = "Size";
                }
            }
        };



}