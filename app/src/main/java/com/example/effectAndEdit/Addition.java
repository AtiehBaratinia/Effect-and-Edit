package com.example.effectAndEdit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Addition extends AppCompatActivity implements View.OnClickListener, View.OnDragListener {
    static Bitmap image;
    String type;
    ImageView imageView, sticker;
    EditText editText;
    ViewGroup viewGroup;
    Button btn;
    private int xDelta;
    private int yDelta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        image = FirstPage.Companion.getImageFile();
        imageView = findViewById(R.id.image_addition);
        imageView.setImageBitmap(image);
        //editText = findViewById(R.id.edittext);
        viewGroup = (ViewGroup)findViewById(R.id.root_layout);
        btn = findViewById(R.id.button_addition_next);
        btn.setOnClickListener(this);
        if (type.equals("text")){
            setText();
        }else if(type.equals("frame")){
            setFrame();
        }else if(type.equals("sticker")){
            setSticker();
        }

    }

    private void setSticker() {
//        new AlertDialog.Builder(this).setItems({})
        sticker = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150,150);
        sticker.setImageResource(R.drawable.smiling_face_with_heart_shaped_eyes_1f60d);
        sticker.setLayoutParams(layoutParams);
        sticker.setOnTouchListener(new ChoiceTouchListener());
        viewGroup.addView(sticker);
    }

    private void setFrame() {
    }

    private void setText() {
        editText = new EditText(this);
        editText.setHint("type here");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150,150);
        editText.setLayoutParams(layoutParams);
        editText.setPadding(20,20,20,20);
        editText.setFocusable(true);
        viewGroup.addView(editText);
        editText.setEnabled(true);
        viewGroup.setOnTouchListener(new ChoiceTouchListener());
        viewGroup.setOnDragListener(this);

        //editText.setOnTouchListener(new ChoiceTouchListener());
        //editText.setOnDragListener(this);
        //imageView.setOnTouchListener(new ChoiceTouchListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addition_next:

        }
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        final int X = (int) event.getX();
        final int Y = (int) event.getY();
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

    private class ChoiceTouchListener implements View.OnTouchListener {



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
