package com.example.effectAndEdit;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Frame {

    //filename of frame
    private String mFrameName;
    private Bitmap frameBitmap;

    //Rect of picture area in frame
    private final Rect mPictureRect;

    //degree of rotation to fit picture and frame.
    private final float mRorate;

    public Frame(Bitmap frameBitmap,int left, int top, int right, int bottom, float rorate) {
        mPictureRect = new Rect(left, top, right, bottom);
        mRorate = rorate;
        this.frameBitmap = frameBitmap;
    }

    public Bitmap mergeWith(Context context, Bitmap pictureBitmap) {
        //Bitmap frameBitmap = AssetsUtil.getBitmapFromAsset(context, mFrameName);
        //Bitmap frameBitmap = AssetsUtil.getBitmapFromAsset(context, mFrameName);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(frameBitmap.getWidth(), frameBitmap.getHeight(), conf);
        Canvas canvas = new Canvas(bitmap);

        Matrix matrix = getMatrix(pictureBitmap);
        canvas.drawBitmap(pictureBitmap, matrix, null);

        canvas.drawBitmap(frameBitmap, 0, 0, null);

        return bitmap;

    }

    Matrix getMatrix(Bitmap pictureBitmap) {
        float widthRatio = mPictureRect.width() /  (float) pictureBitmap.getWidth();
        float heightRatio = mPictureRect.height() / (float) pictureBitmap.getHeight();

        float ratio;

        if (widthRatio > heightRatio) {
            ratio = widthRatio;

        } else {
            ratio = heightRatio;
        }

        float width = pictureBitmap.getWidth() * ratio;
        float height = pictureBitmap.getHeight() * ratio;
        float left = mPictureRect.left - (width - mPictureRect.width()) / 2f;
        float top = mPictureRect.top - (height - mPictureRect.height()) / 2f;

        Matrix matrix = new Matrix();
        matrix.postRotate(mRorate);
        matrix.postScale(ratio, ratio);
        matrix.postTranslate(left, top);

        return matrix;
    }
}
