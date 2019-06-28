package com.example.effectAndEdit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class Addition extends AppCompatActivity implements View.OnClickListener {
    static Bitmap image;
    String type;
    Context context;
    ImageView imageView, sticker;
    Bitmap  bitmapFrame;
    Bitmap bitmapSticker;
    TextView textView;
    ViewGroup viewGroup;
    Button btn;
    Button okButton;
    AlertDialog dialog;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        context = this;

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        image = FirstPage.Companion.getImageFile();
        imageView = findViewById(R.id.image_addition);

        //editText = findViewById(R.id.edit_text);


        btn = findViewById(R.id.button_addition_next);
        btn.setOnClickListener(this);
        switch (type) {
            case "text":
                imageView.setImageBitmap(image);
                setText();
                break;
            case "frame":
                setFrame();
                break;
            case "sticker":
                imageView.setImageBitmap(image);
                setSticker();
                break;
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setSticker() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_sticker, null);
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

        alertDialog.setView(view);
        dialog = alertDialog.create();
        dialog.show();

        viewGroup = findViewById(R.id.top_layout);
        sticker = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(240, 240);

        sticker.setLayoutParams(layoutParams);
        sticker.setOnTouchListener(new ChoiceTouchListener());
        viewGroup.addView(sticker);
    }

    private void setFrame() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.frame_layout, null);
        ImageView[] imageViews = new ImageView[7];
        imageViews[0] = view.findViewById(R.id.image1_frame);
        imageViews[1] = view.findViewById(R.id.image2_frame);
        imageViews[2] = view.findViewById(R.id.image3_frame);
        imageViews[3] = view.findViewById(R.id.image4_frame);
        imageViews[4] = view.findViewById(R.id.image5_frame);
//        imageViews[6] = view.findViewById(R.id.image7_frame);
        for (int i = 0; i < 5; i++) {
            imageViews[i].setOnClickListener(new frameTouch());
        }
        alertDialog.setView(view);
        dialog = alertDialog.create();
        dialog.show();
    }

    int[] color;

    private void setText() {

        Text t =new Text(context);
        color = t.getColor();

        viewGroup = findViewById(R.id.small_layout);
        textView = new TextView(this);
        editText = findViewById(R.id.edit_text);
        editText.setVisibility(View.VISIBLE);
        okButton = findViewById(R.id.ok_button_text);
        okButton.setOnClickListener(this);
        okButton.setVisibility(View.VISIBLE);
        textView.setDrawingCacheEnabled(true);
    }

    private class frameTouch implements View.OnClickListener {
        Drawable d;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image1_frame:

                    bitmapFrame = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.f1);
                    break;


                    //sample 1
                   /* bitmapFrame = BitmapFactory.decodeResource(context.getResources(), R.drawable.frame1);
                    //sticker.setImageBitmap(bitmapFrame);
                    dialog.dismiss();

                    Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                    Canvas canvas = new Canvas(merge);
                    canvas.drawBitmap(image, new Matrix(), null);
                    if (bitmapFrame != null) {
                        //canvas.drawBitmap(bitmapSticker, bitmapSticker.getHeight() + imageView.getX(), bitmapSticker.getWidth() + imageView.getY(), null);
                        canvas.drawBitmap(bitmapFrame, imageView.getX(), imageView.getY(), null);

                    }
                    //image = merge;
                    imageView.setImageBitmap(merge);*/


                    //sample 2
                    //This is sample picture.
                    //Please take picture form gallery or camera.
//                    bitmapFrame = BitmapFactory.decodeResource(getResources(), R.drawable.f1);

                    //This is sample frame.
                    // the number of left, top, right, bottom is the area to show picture.
                    // last argument is degree of rotation to fit picture and frame.
//                    Frame frame1 = new Frame(bitmapFrame, 0 ,0,imageView.getRight(),imageView.getBottom(),0);
//                    Bitmap mergedBitmap = frame1.mergeWith(context, image);

                    //showing result bitmap
//                    imageView.setImageBitmap(mergedBitmap);

                case R.id.image2_frame:
                    bitmapFrame = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.f8);
                    break;
                case R.id.image3_frame:
                    bitmapFrame = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.f3);
                    break;
                case R.id.image4_frame:
                    bitmapFrame = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.f4);
                    break;
                case R.id.image5_frame:
                    bitmapFrame = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.f5);
                    break;

//                case R.id.image7_frame:
//                    bitmapFrame = BitmapFactory.decodeResource(context.getResources(),
//                            R.drawable.f7);
//                    break;
            }
            d = new BitmapDrawable(getResources(), image);
            imageView.setBackground(d);
            imageView.setImageBitmap(bitmapFrame);
            viewGroup = findViewById(R.id.top_layout);
            viewGroup.invalidate();
            dialog.dismiss();

        }
    }


    class StickerTouch implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.image1:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.smiling_face_with_heart_shaped_eyes_1f60d);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image2:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.multiple_musical_notes_1f3b6);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image3:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.male_technologist_type_1_2_1f468_1f3fb_200d_1f4bb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image4:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.person_raising_both_hands_in_celebration_emoji_modifier_fitzpatrick_type_1_2_1f64c_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image5:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.person_with_folded_hands_emoji_modifier_fitzpatrick_type_1_2_1f64f_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image6:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.person_with_headscarf_emoji_modifier_fitzpatrick_type_1_2_1f9d5_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image7:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.raised_hand_with_fingers_splayed_emoji_modifier_fitzpatrick_type_1_2_1f590_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image8:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.victory_hand_emoji_modifier_fitzpatrick_type_1_2_270c_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image9:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.white_heavy_check_mark_2705);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image10:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.female_technologist_type_3_1f469_1f3fc_200d_1f4bb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image11:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(),
                            R.drawable.flag_for_iran_1f1ee_1f1f7);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;

            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_addition_next:
                switch (type) {
                    case "sticker": {
                        Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                        Canvas canvas = new Canvas(merge);
                        canvas.drawBitmap(image, new Matrix(), null);
                        if (bitmapSticker != null && sticker.getX() - imageView.getX() > 0 && sticker.getY() - imageView.getY() > 0) {
                            canvas.drawBitmap(bitmapSticker, sticker.getX() - imageView.getX(), sticker.getY() - imageView.getY(), null);
                        }
                        image = merge;
                        FirstPage.Companion.setImageFile(merge);

                        break;
                    }
                    case "frame": {
                        BitmapDrawable a = (BitmapDrawable) imageView.getDrawable();
                        Bitmap a1 = a.getBitmap();
                        BitmapDrawable b = (BitmapDrawable) imageView.getBackground();
                        Bitmap b1 = b.getBitmap();
//                        Bitmap merge = drawable.getBitmap();
                        Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                        Canvas canvas = new Canvas(merge);
                        canvas.drawBitmap(b1, new Matrix(), null);
                        if (a1 != null ) {
                            canvas.drawBitmap(a1, 0,0, null);
                        }
                        image = merge;
                        FirstPage.Companion.setImageFile(merge);
                        break;
                    }
                    case "text": {
                        Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                        Canvas canvas = new Canvas(merge);
                        canvas.drawBitmap(image, new Matrix(), null);

                        Bitmap text = Bitmap.createBitmap(textView.getDrawingCache());

                        if (text != null && textView.getX() - imageView.getX() > 0 && textView.getY() - imageView.getY() > 0) {

                            canvas.drawBitmap(text, textView.getX() - imageView.getX(), textView.getY() - imageView.getY() , null);
                        }
                        image = merge;
                        FirstPage.Companion.setImageFile(merge);
                        break;
                    }
                }
                Intent intent = new Intent(this, SecondPage.class);
                SecondPage.fa.finish();
                startActivity(intent);
                finish();
                break;
            case R.id.ok_button_text:
                textView.setText(editText.getText().toString());
                okButton.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(400, 400);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(20, 20, 20, 20);
                textView.setTextSize(22f);
                textView.setTextColor(color[0]);
                viewGroup.addView(textView);
                textView.setOnTouchListener(new ChoiceTouchListener());
        }
    }


    private class ChoiceTouchListener implements View.OnTouchListener {
        private int xDelta;
        private int yDelta;
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    xDelta = X - lParams.leftMargin;
                    yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
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
