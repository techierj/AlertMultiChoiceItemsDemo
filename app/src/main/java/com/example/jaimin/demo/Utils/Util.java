package com.example.jaimin.demo.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.jaimin.demo.listener.AlertClickListeners;
import com.example.jaimin.demo.model.Category;
import com.example.jaimin.demo.model.Media;

import java.util.ArrayList;

/**
 * Created by jaimin on 10/18/2016.
 */

public class Util {

    static Util mUtil;

    public static Util getInstance() {
        if (mUtil == null) {
            mUtil = new Util();
        }
        return mUtil;
    }

    /**
     * @param tvString            = textview already set values
     * @param list                = original arraylist containing all objects
     * @param alertClickListeners = listeners to manage click events
     * @param view
     * @param mContext
     */
    public void showListPopup(final String tvString, ArrayList<?> list,
                              final AlertClickListeners alertClickListeners,
                              final View view,
                              Context mContext) {


        String[] checkedValue = tvString.split(",");
        final String[] itemString = new String[list.size()];
        final boolean[] checkedItems = new boolean[list.size()];

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

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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

}
