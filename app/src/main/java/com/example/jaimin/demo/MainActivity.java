package com.example.jaimin.demo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.TouchUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jaimin.demo.model.Category;
import com.example.jaimin.demo.model.Media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private TextView tvList;
    ArrayList<Media> mediaArrayList = new ArrayList<>();
    ArrayList<Category> categoryArrayList = new ArrayList<>();
    private boolean[] checkedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setIds();
        setDummyData();
    }

    private void setDummyData() {
        for (int i = 0; i < 50; i++) {
            mediaArrayList.add(new Media(String.valueOf(i + 1), "Media "+i));
            categoryArrayList.add(new Category(String.valueOf(i + 1), "Category 1"+i));
        }
    }

    private void setIds() {
        tvList = (TextView) findViewById(R.id.tv_list);
        tvList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_list:
                showListPopup(tvList.getText().toString());
                break;
        }
    }

    private void showListPopup(final String tvString) {
        categoryArrayList.toArray();
        final Media[] items = (Media[]) mediaArrayList.toArray(new Media[mediaArrayList.size()]);
        String[] checkedValue = tvString.split(",");
        final String[] itemString = new String[items.length];
        checkedItems = new boolean[items.length];


        if (checkedValue != null) {
            for (int i = 0; i < items.length; i++) {
                String value = items[i].getName();
                itemString[i] = value;
                for (int j = 0; j < checkedValue.length; j++) {
                    if (checkedValue[j].equals(value)) {
                        checkedItems[i] = true;
                        break;
                    }
                }
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        Log.d(TAG, "onClick: " + i + " " + itemString[i]);
                        sb.append(mediaArrayList.get(i).getName() + ",");
                    }
                }
                tvList.setText(sb.toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        builder.setMultiChoiceItems(itemString, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkedItems[which] = isChecked;
            }
        });
        builder.create();
        builder.show();
    }
}
