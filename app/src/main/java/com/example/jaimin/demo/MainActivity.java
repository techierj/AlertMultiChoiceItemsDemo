package com.example.jaimin.demo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jaimin.demo.listener.AlertClickListeners;
import com.example.jaimin.demo.model.Category;
import com.example.jaimin.demo.model.Media;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AlertClickListeners {
    private static final String TAG = "MainActivity";
    private TextView tvMedia, tvCategory;
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
            mediaArrayList.add(new Media(String.valueOf(i + 1), "Media " + i));
            categoryArrayList.add(new Category(String.valueOf(i + 1), "Category 1" + i));
        }
    }

    private void setIds() {
        tvMedia = (TextView) findViewById(R.id.tv_list_media);
        tvCategory = (TextView) findViewById(R.id.tv_list_category);

        tvMedia.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_list_media:
                showListPopup(tvMedia.getText().toString(), this, v, mediaArrayList);
                break;

            case R.id.tv_list_category:
                showListPopup(tvCategory.getText().toString(), this, v, categoryArrayList);
                break;
        }
    }

    private void showListPopup(final String tvString, final AlertClickListeners alertClickListeners, final View view,
                               ArrayList<?> list) {

        categoryArrayList.toArray();
        String[] checkedValue = tvString.split(",");
        final String[] itemString = new String[list.size()];
        checkedItems = new boolean[list.size()];

        if (list.size() > 0) {
            if (list.get(0) instanceof Media) {
                for (int i = 0; i < list.size(); i++) {
                    Media media = (Media) list.get(i);
                    itemString[i] = media.getName();
                }
            } else if (list.get(0) instanceof Category) {
                for (int i = 0; i < list.size(); i++) {
                    Category category = (Category) list.get(i);
                    itemString[i] = category.getCategoryName();
                }
            }
        }

        if (checkedValue != null) {
            for (int i = 0; i < itemString.length; i++) {
                for (int j = 0; j < checkedValue.length; j++) {
                    if (checkedValue[j].equals(itemString[i])) {
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
                        sb.append(itemString[i] + ", ");
                    }
                }
                if (sb.length() > 2) {
                    sb.setLength(sb.length() - 2);
                }
                alertClickListeners.onAlertPositiveClick(view, sb.toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertClickListeners.onAlertNegativeClick(view, "");
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
