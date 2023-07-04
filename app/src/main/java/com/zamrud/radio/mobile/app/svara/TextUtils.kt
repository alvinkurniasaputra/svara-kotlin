package com.zamrud.radio.mobile.app.svara

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by fahziar on 19/05/2016.
 */
class TextUtils {
    companion object {
        fun isVowel(c: Char): Boolean {
            return "AEIOUaeiou".indexOf(c) != -1
        }

        fun isEmail(data: String?): Boolean {
            val m: Matcher = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(data!!)
            return m.find()
        }

        fun hasText(text: String): Boolean {
            return text.matches(".*\\w.*".toRegex())
        }
    }
}