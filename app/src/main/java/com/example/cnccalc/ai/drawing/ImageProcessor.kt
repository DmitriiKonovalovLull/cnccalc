package com.example.cnccalc.ai.drawing

import android.graphics.Bitmap

class ImageProcessor {

    fun preprocessImage(bitmap: Bitmap): Bitmap {
        // TODO: Implement image preprocessing for ML model
        return bitmap
    }

    fun resizeImage(bitmap: Bitmap, maxSize: Int): Bitmap {
        val ratio = bitmap.width.toFloat() / bitmap.height.toFloat()
        val width = if (bitmap.width > bitmap.height) maxSize else (maxSize * ratio).toInt()
        val height = if (bitmap.height > bitmap.width) maxSize else (maxSize / ratio).toInt()

        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }
}