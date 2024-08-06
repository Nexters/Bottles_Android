package com.team.bottles.core.common.extension

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

fun Uri.toFile(context: Context): File {
    val tempFile =
        File.createTempFile("temp_image", null, context.cacheDir).also { file ->
            file.deleteOnExit()
        }

    val inputStream: InputStream? = context.contentResolver.openInputStream(this)

    FileOutputStream(tempFile).use { output ->
        inputStream?.copyTo(output)
    }

    return tempFile
}