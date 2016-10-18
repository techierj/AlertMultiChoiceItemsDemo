package com.example.jaimin.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jaimin.demo.Utils.Util;
import com.example.jaimin.demo.listener.AlertClickListeners;
import com.example.jaimin.demo.model.Category;
import com.example.jaimin.demo.model.Media;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AlertClickListeners {
    private static final String TAG = "MainActivity";
    ArrayList<Media> mediaArrayList = new ArrayList<>();
    ArrayList<Category> categoryArrayList = new ArrayList<>();
    private TextView tvMedia, tvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIds();
        setDummyData();
    }

    private void setIds() {
        tvMedia = (TextView) findViewById(R.id.tv_list_media);
        tvCategory = (TextView) findViewById(R.id.tv_list_category);

        tvMedia.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
    }

    private void setDummyData() {
        for (int i = 1; i <= 50; i++) {
            mediaArrayList.add(new Media(String.valueOf(i), "Media " + i));
            categoryArrayList.add(new Category(String.valueOf(i), "Category 1" + i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_list_media:
                Util.getInstance().showListPopup(tvMedia.getText().toString(),
                        mediaArrayList, this, v, MainActivity.this);
                break;

            case R.id.tv_list_category:
                Util.getInstance().showListPopup(tvCategory.getText().toString(),
                        categoryArrayList, this, v, MainActivity.this);
                break;
        }
    }

    @Override
    public void onAlertPositiveClick(View view, String string) {
        switch (view.getId()) {
            case R.id.tv_list_media:
                tvMedia.setText(string);
                break;
            case R.id.tv_list_category:
                tvCategory.setText(string);
                break;
        }
    }

    @Override
    public void onAlertNegativeClick(View view, String string) {
    }
}
