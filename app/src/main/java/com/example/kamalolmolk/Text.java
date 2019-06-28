package com.example.kamalolmolk;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;

class Text {
    private int[] color = new int[1];
    Text(Context context){
        AlertDialog.Builder colorDialog = new AlertDialog.Builder(context);
        colorDialog.setTitle("رنگ قلم را انتخاب کنید");
        String[] colorDialogItems = {"آبی", "سبز", "قرمز", "زرد", "سفید", "سیاه"};
        colorDialog.setItems(colorDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        color[0] = Color.BLUE;
                        break;
                    case 1:
                        color[0] = Color.GREEN;
                        break;
                    case 2:
                        color[0] = Color.RED;
                        break;
                    case 3:
                        color[0] = Color.YELLOW;
                        break;
                    case 4:
                        color[0] = Color.WHITE;
                        break;
                    case 5:
                        color[0] = Color.BLACK;
                        break;
                }
            }
        });
        colorDialog.show();

    }

    int[] getColor() {
        return color;
    }
}

