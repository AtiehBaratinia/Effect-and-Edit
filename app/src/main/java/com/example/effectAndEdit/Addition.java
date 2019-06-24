package com.example.effectAndEdit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Addition extends AppCompatActivity {
    static Bitmap image;
    String type;
    ImageView imageView;
    EditText editText;
    ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        image = FirstPage.Companion.getImageFile();
        imageView = findViewById(R.id.image_addition);
        imageView.setImageBitmap(image);
        editText = findViewById(R.id.edittext);
        viewGroup = (ViewGroup)findViewById(R.id.root_layout);
        if (type.equals("text")){
            setText();
        }else if(type.equals("frame")){
            setFrame();
        }else if(type.equals("sticker")){
            setSticker();
        }

    }

    private void setSticker() {
    }

    private void setFrame() {
    }

    private void setText() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150,150);
        editText.setLayoutParams(layoutParams);
        editText.setOnTouchListener(new ChoiceTouchListener());
        //imageView.setOnTouchListener(new ChoiceTouchListener());
    }

    private class ChoiceTouchListener implements View.OnTouchListener {
        private int xDelta;
        private int yDelta;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lparams =(RelativeLayout.LayoutParams)v.getLayoutParams();
                    xDelta = X - lparams.leftMargin;
                    yDelta = Y - lparams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                    layoutParams.leftMargin = X - xDelta;
                    layoutParams.topMargin = Y - yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    v.setLayoutParams(layoutParams);
                    break;
            }
            viewGroup.invalidate();
            return true;
        }
    }
}
