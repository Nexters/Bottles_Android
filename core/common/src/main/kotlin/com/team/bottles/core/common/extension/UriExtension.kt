package com.team.bottles.core.common.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

fun Uri.toFile(context: Context): File {
    val bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, this))
    val tempFile =
        File.createTempFile("temp_image", ".png", context.cacheDir).also { file ->
            file.deleteOnExit()
        }

    FileOutputStream(tempFile).use { output ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
    }

    return tempFile
}