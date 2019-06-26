package com.example.effectAndEdit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Addition extends AppCompatActivity implements View.OnClickListener, View.OnDragListener{
    static Bitmap image;
    String type;
    ImageView imageView, sticker;
    Bitmap bitmapSticker;
    EditText editText;
    ViewGroup viewGroup;
    Button btn;
    float stickerX, stickerY;
    AlertDialog dialog;
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
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_sticker,null);
        ImageView[] imageViews = new ImageView[11];
        imageViews[0] = view.findViewById(R.id.image1);
        imageViews[1] = view.findViewById(R.id.image2);
        imageViews[2] = view.findViewById(R.id.image3);
        imageViews[3] = view.findViewById(R.id.image4);
        imageViews[4] = view.findViewById(R.id.image5);
        imageViews[5] = view.findViewById(R.id.image6);
        imageViews[6] = view.findViewById(R.id.image7);
        imageViews[7] = view.findViewById(R.id.image8);
        imageViews[8] = view.findViewById(R.id.image9);
        imageViews[9] = view.findViewById(R.id.image10);
        imageViews[10] = view.findViewById(R.id.image11);
        for (int i = 0; i < 11; i++) {
            imageViews[i].setOnClickListener(new StickerTouch());
        }

        alerDialog.setView(view);
        dialog = alerDialog.create();
        dialog.show();

        viewGroup = (ViewGroup)findViewById(R.id.top_layout);
        sticker = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(240,240);

        sticker.setLayoutParams(layoutParams);
        sticker.setOnTouchListener(new ChoiceTouchListener());
        viewGroup.addView(sticker);
    }

    private void setFrame() {
    }

    private void setText() {
        viewGroup = (ViewGroup)findViewById(R.id.small_layout);
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
    private class StickerTouch implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.image1:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.smiling_face_with_heart_shaped_eyes_1f60d);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image2:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.multiple_musical_notes_1f3b6);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image3:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.male_technologist_type_1_2_1f468_1f3fb_200d_1f4bb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image4:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.person_raising_both_hands_in_celebration_emoji_modifier_fitzpatrick_type_1_2_1f64c_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image5:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.person_with_folded_hands_emoji_modifier_fitzpatrick_type_1_2_1f64f_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image6:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.person_with_headscarf_emoji_modifier_fitzpatrick_type_1_2_1f9d5_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image7:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.raised_hand_with_fingers_splayed_emoji_modifier_fitzpatrick_type_1_2_1f590_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image8:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.victory_hand_emoji_modifier_fitzpatrick_type_1_2_270c_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image9:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.white_heavy_check_mark_2705);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image10:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.female_technologist_type_3_1f469_1f3fc_200d_1f4bb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image11:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.flag_for_iran_1f1ee_1f1f7);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addition_next:
                if (type.equals("sticker")) {
                    Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                    Canvas canvas = new Canvas(merge);
                    canvas.drawBitmap(image, new Matrix(), null);
                    if (bitmapSticker != null) {
                        //canvas.drawBitmap(bitmapSticker, bitmapSticker.getHeight() + imageView.getX(), bitmapSticker.getWidth() + imageView.getY(), null);
                        canvas.drawBitmap(bitmapSticker, sticker.getX() - imageView.getX(), sticker.getY() - imageView.getY(), null);

                    }
                    
                    image =  merge;
                    FirstPage.Companion.setImageFile(merge);
                    Intent intent = new Intent(this, SecondPage.class);
                    SecondPage.fa.finish();
                    startActivity(intent);
                    finish();
                }
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
