package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

public class Shop extends AppCompatActivity {

    ImageView skin;
    Button dress;
    int lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        skin = (ImageView) findViewById(R.id.skin);
        Glide.with(this).load(R.drawable.lv1_red).into(skin);
        dress = findViewById(R.id.dress);
        //获取保存的等级，是否符合皮肤切换条件
        lv = Integer.parseInt(CommonUtil.getSettingNote(Shop.this, "petinfo", "lv"));
        if (lv < 1) {
            skin.setVisibility(View.GONE);
            dress.setVisibility(View.GONE);
        }
    }

    //改变皮肤
    public void skin_dress(View v) {
        Map<String, String> map = new HashMap<String, String>();
        //第一次打开页面先初始化数据
        if (CommonUtil.getSettingNote(Shop.this, "petinfo", "dress") == null) {
            map.put("dress", String.valueOf(0));
            CommonUtil.saveSettingNote(Shop.this, "petinfo", map);
        }
        //皮肤已改变则还原，未改变则改变
        if (Integer.parseInt(CommonUtil.getSettingNote(Shop.this, "petinfo", "dress")) == 1) {
            map.put("dress", String.valueOf(0));
        } else {
            map.put("dress", String.valueOf(1));
        }
        CommonUtil.saveSettingNote(Shop.this, "petinfo", map);
    }

    public void shopToPet(View view) {
        //跳转页面
        Intent intent = new Intent(Shop.this, Pet.class);
        startActivity(intent);
    }
}
