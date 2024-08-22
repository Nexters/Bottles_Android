package com.team.bottles.core.common.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun Uri.toFile(context: Context): File {
    val bitmap = try {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, this))
    } catch (e: Exception) {
        throw IllegalArgumentException("Filed Image Decoding", e) // TODO : 커스텀 예외 처리시 변경
    }
    val tempFile =
        File.createTempFile("temp_image", ".jpg", context.cacheDir).also { file ->
            file.deleteOnExit()
        }

    var compressQuality = 100
    val maxSize = 1024 * 1024
    var streamLength: Int
    val bmpStream = ByteArrayOutputStream()

    bmpStream.use { stream ->
        do {
            stream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, stream)
            streamLength = stream.size()
            compressQuality -= 5
        } while (streamLength > maxSize && compressQuality > 0)

        FileOutputStream(tempFile).use { output ->
            output.write(stream.toByteArray())
        }
    }

    return tempFile
}