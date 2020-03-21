package com.andymariscal.shared.utils

import timber.log.Timber
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

object IOUtils {

    // https://www.baeldung.com/convert-input-stream-to-string
    @Throws(IOException::class) //No need in this project tho, it's a JAVA interpolation annotation
    fun convertToString(inputStream: InputStream, charSet: Charset = StandardCharsets.UTF_8): String? =
        try {
            val stringBuilder = StringBuilder()
            val reader = BufferedReader(
                InputStreamReader(
                    inputStream,
                    Charset.forName(charSet.name())
                )
            )
            var c = 0
            while (reader.read().also { c = it } != -1) {
                stringBuilder.append(c.toChar())
            }
            stringBuilder.toString()
        } catch (e: IOException) {
            Timber.e(e.cause)
            null
        }
}