package com.example.kamalolmolk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SecondPage extends AppCompatActivity implements View.OnClickListener {
    static Bitmap image;
    private ImageView imageView;
    @SuppressLint("StaticFieldLeak")
    public static Activity fa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        fa = this;
        image = FirstPage.Companion.getImageFile();

        imageView = findViewById(R.id.image_view_sec);
        Button resizeButton = findViewById(R.id.resize_button);
        Button rotateButton = findViewById(R.id.rotate_button);
        Button blurButton = findViewById(R.id.blur_button);
        Button paintButton = findViewById(R.id.paint_button);
        Button effectButton = findViewById(R.id.effect_button);
        Button additionButton = findViewById(R.id.addition_button);
        Button saveButton = findViewById(R.id.save_button);
        imageView.setImageBitmap(image);

        resizeButton.setOnClickListener(this);
        rotateButton.setOnClickListener(this);
        blurButton.setOnClickListener(this);
        paintButton.setOnClickListener(this);
        effectButton.setOnClickListener(this);
        additionButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }

    /**
     * To show resize options to user.
     */
    public void resize() {
        final int width = image.getWidth();
        final int height = image.getHeight();

        AlertDialog.Builder resizeDialog = new AlertDialog.Builder(this);
        resizeDialog.setTitle("اندازه را انتخاب کنید");
        String[] resizeDialogItems = new String[8];
        for (int i = 1; i <= 8; i++) resizeDialogItems[i - 1] = i * 0.25 * width + " : " + i * 0.25 * height;
        resizeDialog.setItems(resizeDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 7:
                        resizing(200);
                        break;
                    case 6:
                        resizing(175);
                        break;
                    case 5:
                        resizing(150);
                        break;
                    case 4:
                        resizing(125);
                        break;
                    case 3:
                        resizing(100);
                        break;
                    case 2:
                        resizing(75);
                        break;
                    case 1:
                        resizing(50);
                        break;
                    case 0:
                        resizing(25);
                        break;
                }
            }
        });
        resizeDialog.show();
    }

    /**
     * To do resizing on the image.
     *
     * @param percent percent we want to resize.
     */
    private void resizing(float percent) {
        float scaleWidth = percent / 100;
        float scaleHeight = percent / 100;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
        imageView.setImageBitmap(image);
    }

    /**
     * To do cropping on image.
     * //
     */
//    public void crop() {
//
//    }
    public void rotate() {
        AlertDialog.Builder rotateDialog = new AlertDialog.Builder(this);
        rotateDialog.setTitle("چرخش را انتخاب کنید");
        rotateDialog.setItems(new String[]{"راست", "معکوس", "چپ", "مقلوب"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rotating(which);
            }
        });
        rotateDialog.show();
    }

    public void rotating(int o) {
        int w = 0, h = 0;
        switch (o) {
            case 0:
            case 2:
                w = image.getHeight();
                h = image.getWidth();
                break;
            case 1:
            case 3:
                h = image.getHeight();
                w = image.getWidth();
                break;
        }
        Bitmap out = Bitmap.createBitmap(w, h, image.getConfig());
        for (int i = 0; i < image.getWidth(); ++i) {
            for (int j = 0; j < image.getHeight(); ++j) {
                // get one pixel color
                int pixel = image.getPixel(i, j);
                // retrieve color of all channels
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);
                int k = 0, l = 0;
                switch (o) {
                    case 0:
                        l = i;
                        k = h - 1 - j;
                        break;
                    case 1:
                        l = h - 1 - j;
                        k = w - 1 - i;
                        break;
                    case 2:
                        k = j;
                        l = w - 1 - i;
                        break;
                    case 3:
                        k = w - 1 - i;
                        l = j;
                        break;
                }
                // set new pixel color to output bitmap
                out.setPixel(k, l, Color.rgb(red, green, blue));
            }
        }
        image = out;
        imageView.setImageBitmap(image);
    }

    /**
     * To choose thickness of blurring.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void blur() {
        final int[] thickness = new int[1];
        AlertDialog.Builder thicknessDialog = new AlertDialog.Builder(this);
        thicknessDialog.setTitle("ضلع ناحیه واضح را انتخاب کنید");
        String[] thicknessDialogItems = {"اندازه 1", "اندازه 2", "اندازه 3", "اندازه 4", "اندازه 5"};
        thicknessDialog.setItems(thicknessDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        thickness[0] = 100;
                        break;
                    case 1:
                        thickness[0] = 200;
                        break;
                    case 2:
                        thickness[0] = 300;
                        break;
                    case 3:
                        thickness[0] = 400;
                        break;
                    case 4:
                        thickness[0] = 500;
                        break;
                }
            }
        });
        thicknessDialog.show();
        final int[] x = new int[1];
        final int[] y = new int[1];
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x[0] = (int) event.getX();
                y[0] = (int) event.getY();

                image = blurring(image, x[0], y[0], thickness[0]);
                imageView.setImageBitmap(image);
                return false;
            }
        });
    }

    /**
     * To do blurring on image.
     *
     * @param image  the image we want to blur.
     * @param x      x coordinate of where has been touched.
     * @param y      y coordinate of where has been touched.
     * @param radius radius of non-blur area.
     * @return blurred image.
     */
    public Bitmap blurring(Bitmap image, int x, int y, int radius) {
        Bitmap out = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
        for (int i = 0; i < image.getWidth(); ++i) {
            for (int j = 0; j < image.getHeight(); ++j) {
                // get one pixel color
                int pixel = image.getPixel(i, j);
                // retrieve color of all channels
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                double expression = Math.pow(Math.pow(i - x, 2) + Math.pow(j - y, 2), 0.5);
                if (expression > radius) {
                    int c = 0;
                    int ham;
                    red = 0;
                    blue = 0;
                    green = 0;
                    int p;
                    if (expression > 9 * radius)
                        p = 10;
                    else {
                        if (expression > 8 * radius)
                            p = 9;
                        else {
                            if (expression > 7 * radius)
                                p = 8;
                            else {
                                if (expression > 6 * radius)
                                    p = 7;
                                else {
                                    if (expression > 5 * radius)
                                        p = 6;
                                    else {
                                        if (expression > 4 * radius)
                                            p = 5;
                                        else {
                                            if (expression > 3 * radius)
                                                p = 4;
                                            else {
                                                if (expression > 2 * radius)
                                                    p = 3;
                                                else {
                                                    p = 2;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int k = -p; k < p + 1; k++) {
                        for (int l = -p; l < p + 1; l++) {
                            if ((i + k) >= 0 && (i + k) < out.getWidth() && (j + l) >= 0 && (j + l) < out.getHeight()) {
                                ham = image.getPixel(i + k, j + l);
                                red += Color.red(ham);
                                blue += Color.blue(ham);
                                green += Color.green(ham);
                                c++;
                            }
                        }
                    }

                    red /= c;
                    blue /= c;
                    green /= c;
                }

                // set new pixel color to output bitmap
                out.setPixel(i, j, Color.rgb(red, green, blue));
            }
        }

        return out;
    }

    /**
     * To choose color and thickness of the pen to do painting.
     */
    @SuppressLint("ClickableViewAccessibility")
    public void paint() {
        final int[] color = new int[1];
        final int[] thickness = new int[1];

        // To choose color
        AlertDialog.Builder colorDialog = new AlertDialog.Builder(this);
        final AlertDialog.Builder thicknessDialog = new AlertDialog.Builder(this);
        colorDialog.setTitle("یک رنگ انتخاب کنید");
        String[] colorDialogItems = {"آبی", "سبز", "قرمز", "زرد", "سفید", "مشکی"};
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
                thicknessDialog.show();
            }
        });

        // To choose thickness
        thicknessDialog.setTitle("ضخامت قلم را انتخاب کنید");
        String[] thicknessDialogItems = {"ضخامت 1", "ضخامت 2", "ضخامت 3"};
        thicknessDialog.setItems(thicknessDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        thickness[0] = 1;
                        break;
                    case 1:
                        thickness[0] = 5;
                        break;
                    case 2:
                        thickness[0] = 10;
                        break;
                }
            }
        });
        colorDialog.show();

        // To paint
        final int[] x = new int[1];
        final int[] y = new int[1];
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x[0] = (int) event.getX();
                y[0] = (int) event.getY();

                image = painting(x[0], y[0], thickness[0] * 5, color[0]);
                imageView.setImageBitmap(image);
                return false;
            }
        });
    }

    /**
     * To paint on the image.
     *
     * @param x         x coordinate of where user touch.
     * @param y         y coordinate of where user touch.
     * @param thickness thickness of the pen.
     * @param color     color of the pen.
     * @return painted image.
     */
    private Bitmap painting(int x, int y, int thickness, int color) {
        Bitmap out = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());

        for (int i = 0; i < image.getWidth(); ++i) {
            for (int j = 0; j < image.getHeight(); ++j) {
                // get one pixel color
                int pixel = image.getPixel(i, j);
                // retrieve color of all channels
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                if (i <= x + thickness && j <= y + thickness && i >= x - thickness && j >= y - thickness) {
                    switch (color) {
                        case Color.BLUE:
                            red = green = 0;
                            blue = 255;
                            break;
                        case Color.GREEN:
                            red = blue = 0;
                            green = 255;
                            break;
                        case Color.RED:
                            green = blue = 0;
                            red = 255;
                            break;
                        case Color.YELLOW:
                            red = green = 255;
                            blue = 0;
                            break;
                        case Color.BLACK:
                            red = blue = green = 0;
                            break;
                        case Color.WHITE:
                            red = blue = green = 255;
                            break;
                    }
                }

                // set new pixel color to output bitmap
                out.setPixel(i, j, Color.rgb(red, green, blue));
            }
        }
        return out;
    }

    /**
     * To choose and apply effects.
     */

    public void chooseEffect() {
        AlertDialog.Builder effectsDialog = new AlertDialog.Builder(this);
        effectsDialog.setTitle("یک افکت انتخاب کنید");
        String[] effectsDialogItems = {"سیاه و سفید", "طراحی", "گرم و قدیمی"};
        effectsDialog.setItems(effectsDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        image = Effects.doGreyScale(image);
                        imageView.setImageBitmap(image);
                        break;
                    case 1:
                        image = Effects.doSketchEffect(image);
                        imageView.setImageBitmap(image);
                        break;
                    case 2:
                        image = Effects.doSepiaEffect(image);
                        imageView.setImageBitmap(image);
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
        final AlertDialog.Builder additionsDialog = new AlertDialog.Builder(this);
        additionsDialog.setTitle("چه چیزی میخواهید به تصویر اضافه کنید؟");
        String[] additionsDialogItems = {"استیکر", "نوشته", "قاب"};
        additionsDialog.setItems(additionsDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(additionsDialog.getContext(), Addition.class);
                switch (which) {
                    case 0:
                        //Here a sticker applies. From Addition class.
                        intent.putExtra("type", "sticker");
                        break;
                    case 1:
                        //Here a text applies. From Text class.
                        intent.putExtra("type", "text");

                        break;
                    case 2:
                        //Here a frame applies.
                        intent.putExtra("type", "frame");
                        break;
                }
                startActivity(intent);
            }
        });
        additionsDialog.show();
    }

    /**
     * To go to the save page. Starts the activity of SavePage.
     */
    public void goToSavePage() {
        Intent saveIntent = new Intent(this, SavePage.class);
        startActivity(saveIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resize_button:
                resize();
                break;
            case R.id.rotate_button:
                rotate();
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
            case R.id.save_button:
                goToSavePage();
                break;
        }
    }
}
