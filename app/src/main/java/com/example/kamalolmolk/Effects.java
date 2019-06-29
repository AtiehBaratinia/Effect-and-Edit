package com.example.kamalolmolk;

import android.graphics.Bitmap;
import android.graphics.Color;
import org.jetbrains.annotations.NotNull;

class Effects {

    /**
     * To make the image gray scale.
     *
     * @param src the image we want to make changes.
     * @return the processed image.
     */
    static Bitmap doGreyScale(@NotNull Bitmap src) {
        // constant factors
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                // take conversion up to one single value
                R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        return bmOut;
    }
    static Bitmap doSketchEffect(@NotNull Bitmap src) {

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // height and width of Image
        int imageHeight = bmOut.getHeight();
        int imageWidth = bmOut.getWidth();

//        Log.e("Image Size", "Height=" + imageHeight + " Width=" + imageWidth);


        // traversing each pixel in Image as an 2D Array
        for (int i = 0; i < imageWidth; i++) {

            for (int j = 0; j < imageHeight; j++) {

                // getting each pixel
                pixel = src.getPixel(i, j);


                // each pixel is made from RED_BLUE_GREEN_ALPHA
                // so, getting current values of pixel
                R = Color.red(pixel);
                B = Color.blue(pixel);
                G = Color.green(pixel);
                A = Color.alpha(pixel);


                // Algorithm for SKETCH FILTER

                int intensity = (R + B + G) / 3;

                // applying new pixel value to newBitmap
                // condition for Sketch
                int newPixel = 0;
                int INTENSITY_FACTOR = 120;

                if (intensity > INTENSITY_FACTOR) {
                    // apply white color
                    newPixel = Color.argb(A, 255, 255, 255);

                } else if (intensity > 100) {
                    // apply grey color
                    newPixel = Color.argb(A, 150, 150, 150);
                } else {
                    // apply black color
                    newPixel = Color.argb(A, 0, 0, 0);


                    // applying new pixel values from above to newBitmap
                    bmOut.setPixel(i, j, newPixel);
                }
            }



//        System.out.println("Effect 2 Applied.");
        }

        return bmOut;

    }
    static Bitmap doSepiaEffect(@NotNull Bitmap src){

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel;

        // height and width of Image
        int imageHeight = bmOut.getHeight();
        int imageWidth = bmOut.getWidth();

//        Log.e("Image Size", "Height=" + imageHeight + " Width=" + imageWidth);


        // traversing each pixel in Image as an 2D Array
        for (int i = 0; i < imageWidth; i++) {

            for (int j = 0; j < imageHeight; j++) {

                // getting each pixel
                pixel = src.getPixel(i, j);


                // each pixel is made from RED_BLUE_GREEN_ALPHA
                // so, getting current values of pixel
                R = Color.red(pixel);
                B = Color.blue(pixel);
                G = Color.green(pixel);
                A = Color.alpha(pixel);


                // Algorithm for new values
                // after calculation of filter
//                int newR, newG, newB;
                int newR = (int) (0.393 * R + 0.769 * G + 0.189 * B);
                int newG = (int) (0.349 * R + 0.686 * G + 0.168 * B);
                int newB = (int) (0.272 * R + 0.534 * G + 0.131 * B);


                // applying new pixel values from above to newBitmap
                bmOut.setPixel(i, j, Color.argb(A, newR, newG, newB));
            }
        }

        return bmOut;

        //System.out.println("Effect 3 Applied.");
    }
}
