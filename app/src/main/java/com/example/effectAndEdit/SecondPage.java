package com.example.effectAndEdit;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

class SecondPage extends AppCompatActivity implements View.OnClickListener {
    static Bitmap image;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        image = FirstPage.Companion.getImageFile();

        imageView = findViewById(R.id.image_view_sec);
        Button resizeButton = findViewById(R.id.resize_button);
        Button cropButton = findViewById(R.id.crop_button);
        Button gridButton = findViewById(R.id.grid_button);
        Button blurButton = findViewById(R.id.blur_button);
        Button paintButton = findViewById(R.id.paint_button);
        Button effectButton = findViewById(R.id.effect_button);
        Button additionButton = findViewById(R.id.addition_button);
        imageView.setImageBitmap(image);

        resizeButton.setOnClickListener(this);
        cropButton.setOnClickListener(this);
        gridButton.setOnClickListener(this);
        blurButton.setOnClickListener(this);
        paintButton.setOnClickListener(this);
        effectButton.setOnClickListener(this);
        additionButton.setOnClickListener(this);
    }

    /**
     * To do resizing on image.
     */
    public void resize() {
        System.out.println("Resize Clicked.");
    }

    /**
     * To do cropping on image.
     */
    public void crop() {
        System.out.println("Crop Clicked.");
    }

    /**
     * To do griding on image.
     */
    public void grid() {
        System.out.println("Grid Clicked.");
    }

    /**
     * To do blurring on image.
     */
    public void blur() {
        System.out.println("Blur Clicked.");
    }

    /**
     * to do painting on image.
     */
    public void paint() {
        System.out.println("Paint Clicked.");
    }

    /**
     * To choose and apply effects.
     */
    public void chooseEffect() {
        System.out.println("Effect Clicked.");
        AlertDialog.Builder effectsDialog = new AlertDialog.Builder(this);
        effectsDialog.setTitle("Choose an Effect");
        String[] effectsDialogItems = {"سیاه و سفید", "Effect 2", "Effect 3"};
        effectsDialog.setItems(effectsDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Bitmap bitmap = Effects.doGreyScale(image);
                        imageView.setImageBitmap(bitmap);
                        break;
                    case 1:
                        Effects.effect2(image);
                        break;
                    case 2:
                        Effects.effect3(image);
                        break;
                }
            }
        });
        effectsDialog.show();
    }

    /**
     * To choose the addition and apply it.
     */
    public void chooseAddition() {
        System.out.println("Addition Clicked.");
        final AlertDialog.Builder additionsDialog = new AlertDialog.Builder(this);
        additionsDialog.setTitle("Choose an Addition");
        String[] additionsDialogItems = {"Sticker", "Text", "Frame"};
        additionsDialog.setItems(additionsDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //Here a sticker applies. From Addition class.
                        System.out.println("Sticker Clicked.");
                        break;
                    case 1:
                        //Here a text applies. From Text class.
                        System.out.println("Text Clicked.");
                        Intent intent = new Intent(additionsDialog.getContext(), Addition.class);
                        
                        break;
                    case 2:
                        //Here a frame applies. From Frame class.
                        System.out.println("Frame Clicked.");
                        break;
                }
            }
        });
        additionsDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resize_button:
                resize();
                break;
            case R.id.crop_button:
                crop();
                break;
            case R.id.grid_button:
                grid();
                break;
            case R.id.blur_button:
                blur();
                break;
            case R.id.paint_button:
                paint();
                break;
            case R.id.effect_button:
                chooseEffect();
                break;
            case R.id.addition_button:
                chooseAddition();
                break;
        }
    }
}
