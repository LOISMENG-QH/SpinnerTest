package com.example.spinnertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<HeroBean> mData = null;//存放英雄的名字和图片的实体类数组
    private String[] talkArray = {"haha","keke","huh","em"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSpinnerDrop();
        initSpinnerHero();
    }

    private void initSpinnerHero() {
        Spinner spinner_hero = findViewById(R.id.spinner_hero);
        final TextView hero_show=findViewById(R.id.hero_show);
        mData=new ArrayList<HeroBean>();
        mData.add(new HeroBean(R.drawable.zf1,"张子枫"));
        mData.add(new HeroBean(R.drawable.zf1,"子枫妹妹"));
        mData.add(new HeroBean(R.drawable.zf1,"子枫妹"));
        mData.add(new HeroBean(R.drawable.zf1,"小张张"));
        mData.add(new HeroBean(R.drawable.zf1,"妹妹"));
        mData.add(new HeroBean(R.drawable.zf1,"疯子张"));

        MyAdapter myAdapter = new MyAdapter(mData, this);
        spinner_hero.setAdapter(myAdapter);

        //给选择英雄的spinner添加监听
        spinner_hero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override //选中的时候执行的方法
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, mData.get(i).getName(), Toast.LENGTH_SHORT).show();
                hero_show.setText(mData.get(i).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });}

    private void initSpinnerDrop() {
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> talkAdapter = new ArrayAdapter<String>(this, R.layout.item_selector, talkArray);
        //设置数组适配器的布局样式
        talkAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //从布局文件中获取名叫sp_dialog的下拉框
        Spinner sp = findViewById(R.id.spinner_drop);
        //设置下拉框的标题，不设置就没有难看的标题了
        sp.setPrompt("请选择聊天话语");
        //设置下拉框的数组适配器
        sp.setAdapter(talkAdapter);
        //设置下拉框默认的显示第一项，这个方法要放在setAdapter后面
        sp.setSelection(0);
        //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的OnItemSelected方法
        sp.setOnItemSelectedListener(new MySelectedListener());
    }

    class MySelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "你选择的是：" + talkArray[position],Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}