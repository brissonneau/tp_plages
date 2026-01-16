package biz.ei6.eluvplages.data


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

object InternalImageStorage {

    fun saveProfileBitmap(context: Context, bitmap: Bitmap): String {
        val file = File(context.filesDir, "profile.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return file.absolutePath
    }

    fun loadBitmap(path: String): Bitmap? {
        val file = File(path)
        if (!file.exists()) return null
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    fun delete(path: String) {
        runCatching { File(path).delete() }
    }
}
