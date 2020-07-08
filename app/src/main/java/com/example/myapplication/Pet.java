package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pet extends AppCompatActivity {

    ImageView pet;
    TextView talk_box;
    TextView lv_num;
    TextView exp_num;
    Button next_talk;
    int lv;
    int exp;

    //测试用初始化宠物数据
    public void init(View view) {
        init2();
    }

    public void init2() {
        lv = 0;
        exp = 200;
        Map<String, String> map = new HashMap<String, String>();
        map.put("dress", String.valueOf(0));
        CommonUtil.saveSettingNote(Pet.this, "petinfo", map);
        petSet();
        lvSet();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet);

        pet = (ImageView) findViewById(R.id.pet);
        talk_box = findViewById(R.id.talk_box);
        lv_num = findViewById(R.id.lv_num);
        exp_num = findViewById(R.id.exp_num);
        next_talk = findViewById(R.id.next_talk);

        //获取保存的等级和经验并显示
        if (CommonUtil.getSettingNote(Pet.this, "petinfo", "lv") == null) {
            init2();
        } else {
            lv = Integer.parseInt(CommonUtil.getSettingNote(Pet.this, "petinfo", "lv"));
            exp = Integer.parseInt(CommonUtil.getSettingNote(Pet.this, "petinfo", "exp"));
            petSet();
        }
    }

    //根据等级改变显示宠物形象，经验值，等级
    public void petSet() {
        lv_num.setText(lv + "");
        exp_num.setText(exp + "");
        switch (lv) {
            case 0:
                Glide.with(this).load(R.drawable.lv0).into(pet);
                break;
            case 1:
                if (Integer.parseInt(CommonUtil.getSettingNote(Pet.this, "petinfo", "dress")) == 1)
                    Glide.with(this).load(R.drawable.lv1_red).into(pet);
                else
                    Glide.with(this).load(R.drawable.lv1).into(pet);
                break;
            case 2:
                Glide.with(this).load(R.drawable.lv2).into(pet);
                break;
        }
    }

    //保存等级和经验到本地存储
    public void lvSet() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("lv", String.valueOf(lv));
        map.put("exp", String.valueOf(exp));
        CommonUtil.saveSettingNote(Pet.this, "petinfo", map);
    }

    //对话内容
    public void talk(View view) {
        //获取系统时间
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String str = sd.format(curDate);

        Random random = new Random();
        int t1 = random.nextInt(3);//范围[0,n)
        String[] tt1 = new String[]{"啾啾", "嗯……现在是" + str, "事情都做好了吗？"};
        String[] tt2 = new String[]{"啾啾啾！", "啾啾！现在是" + str, "要记得完成任务啾"};

        talk_box.setVisibility(View.VISIBLE);
        switch (lv) {
            case 0:
                talk_box.setText("……（好像还不会说话）");
                break;
            case 1:
                talk_box.setText(tt1[t1]);
                break;
            case 2:
                talk_box.setText(tt2[t1]);
                break;
        }
        next_talk.setVisibility(View.VISIBLE);
    }

    //对话框出现后点击任意地方
    public void nextTalk(View view) {
        talk_box.setVisibility(View.INVISIBLE);
        next_talk.setVisibility(View.GONE);
    }

    //点击升级
    public void upgrade(View view) {
        if (exp >= 100) {
            exp -= 100;
            lv += 1;
            petSet();
            lvSet();
        }
    }

    public void toMain(View view) {
        //跳转页面
        Intent intent = new Intent(Pet.this, MainActivity.class);
        startActivity(intent);
    }

    public void petToShop(View view) {
        //跳转页面
        Intent intent = new Intent(Pet.this, Shop.class);
        startActivity(intent);
    }

}
