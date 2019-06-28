package com.example.kamalolmolk


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class SavePage : AppCompatActivity() {

    private var image: Bitmap? = null
    private var saveButton: Button? = null
    private var finalImageView: ImageView? = null
    private var currentPhotoPath: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        image = SecondPage.image
        finalImageView = findViewById(R.id.final_image_view)
        finalImageView!!.setImageBitmap(image)
        saveButton = findViewById(R.id.save_button_save_page)
        saveButton!!.setOnClickListener { save(image!!) }
    }

    /**
     * To save final image. This method asks user how he/she want to save the image.
     */
    private fun save(image: Bitmap) {
        val saveDialog = AlertDialog.Builder(this)
        saveDialog.setTitle("نوع ذخیره را انتخاب کنید")
        val saveDialogItems = arrayOf("ذخیره عادی", "ذخیره به صورت 9 تکه")
        saveDialog.setItems(saveDialogItems) { _, which ->
            when (which) {
                0 -> normalSave(image)
                1 -> gridSave(image)
            }
        }
        saveDialog.show()
    }

    /**
     * To save the final image in the specific directory normally and show it to the user.
     */
    @SuppressLint("SimpleDateFormat")
    private fun normalSave(bitmap: Bitmap) {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir("../Final Pictures")!!
        val file = File.createTempFile(
            "FINAL_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply { currentPhotoPath = absolutePath }

        try {
            // Compress the bitmap and save in jpg format
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()

            FirstPage.imageFile = null
            SecondPage.image = null

            Toast.makeText(this, "تصویر تهایی ذخیره شد! مکان دخیره: $currentPhotoPath.", Toast.LENGTH_LONG).show()
            val firstIntent = Intent(this, FirstPage::class.java)
            startActivity(firstIntent)
        } catch (e: IOException) {
            Toast.makeText(this, "مشکلی رخ داد.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * To save the image in a 3*3 format.
     */
    private fun gridSave(bitmap: Bitmap) {
        val width = bitmap.width
        val height = bitmap.height
        val side = if (width > height) height / 3 else width / 3

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val buffBitmap = Bitmap.createBitmap(bitmap, j * side, i * side, side, side)
                normalSave(buffBitmap)
            }
        }
    }
}