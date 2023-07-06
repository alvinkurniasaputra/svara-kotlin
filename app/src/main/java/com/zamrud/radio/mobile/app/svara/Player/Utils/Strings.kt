/*****************************************************************************
 * Strings.java
 *****************************************************************************
 * Copyright © 2011-2014 VLC authors and VideoLAN
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston MA 02110-1301, USA.
 *****************************************************************************/

package com.zamrud.radio.mobile.app.svara.Player.Utils

import com.zamrud.radio.mobile.app.svara.BuildConfig

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs
import kotlin.math.log10
import kotlin.math.pow

class Strings {

    companion object {
        const val TAG = "VLC/UiTools/Strings"
        fun stripTrailingSlash(s: String): String {
            return if (s.endsWith("/") && s.length > 1)
                s.substring(0, s.length - 1)
            else s
        }

        fun startsWith(array: Array<String>, text: String): Boolean {
            for (item in array)
                if (text.startsWith(item))
                    return true
            return false
        }

        fun containsName(array: ArrayList<String>, text: String): Int {
            for (i in array.size-1 downTo 0)
                if (array[i].endsWith(text))
                    return i
            return -1
        }

        /**
         * Convert time to a string
         * @param millis e.g.time/length from file
         * @return formated string (hh:)mm:ss
         */
        fun millisToString(millis: Long): String {
            return millisToString(millis, false)
        }

        /**
         * Convert time to a string
         * @param millis e.g.time/length from file
         * @return formated string "[hh]h[mm]min" / "[mm]min[s]s"
         */
        fun millisToText(millis: Long): String {
            return millisToString(millis, true)
        }

        fun millisToString(millis: Long, text: Boolean): String {
            val negative = millis < 0
            var millis1 = abs(millis)

            millis1 /= 1000
            val sec = (millis1 % 60).toInt()
            millis1 /= 60
            val min = (millis1 % 60).toInt()
            millis1 /= 60
            val hours = millis1.toInt()

            val time: String
            val format = NumberFormat.getInstance(Locale.US) as DecimalFormat
            format.applyPattern("00")
            time = if (text) {
                if (millis1 > 0) (if (negative) "-" else "") + hours + "h" + format.format(min.toLong()) + "min"
                else if (min > 0) (if (negative) "-" else "") + min + "min"
                else (if (negative) "-" else "") + sec + "s"
            } else {
                if (millis1 > 0) (if (negative) "-" else "") + hours + ":" + format.format(min.toLong()) + ":" + format.format(sec.toLong())
                else (if (negative) "-" else "") + min + ":" + format.format(sec.toLong())
            }
            return time
        }

        /**
         * equals() with two strings where either could be null
         */
        fun nullEquals(s1: String?, s2: String?): Boolean {
            return if (s1 == null) s2 == null else s1 == s2
        }

        /**
         * Get the formatted current playback speed in the form of 1.00x
         */
        fun formatRateString(rate: Float): String {
            return String.format(Locale.US, "%.2fx", rate)
        }

        fun readableFileSize(size: Long): String {
            if (size <= 0) return "0"
            val units = arrayOf("B", "KiB", "MiB", "GiB", "TiB")
            val digitGroups = (log10(size.toDouble()) / log10(1024.0)).toInt()
            return DecimalFormat("#,##0.#").format(size / 1024.0.pow(digitGroups.toDouble())) + " " + units[digitGroups]
        }

        fun removeFileProtocole(path: String?): String? {
            if (path == null)
                return null
            return if (path.startsWith("file://"))
                path.substring(7)
            else path
        }

        fun buildPkgString(string: String): String {
            return BuildConfig.APPLICATION_ID + "." + string
        }

        fun secondToTimeFormat(duration: Float): String {
            return secondToTimeFormat(duration, ":")
        }

        fun secondToTimeFormat(durationFloat: Float, spacer: String): String {
            val negative = durationFloat <= 0
            var duration = abs(durationFloat).toLong()
            val sec = (duration % 60).toInt()
            duration /= 60
            val min = (duration % 60).toInt()
            duration /= 60
            val hours = duration.toInt()

            val time: String
            val format = NumberFormat.getInstance(Locale.US) as DecimalFormat
            format.applyPattern("00")

            if (negative)
                return "-"
            time = if (duration > 0)
                hours.toString() + spacer + format.format(min.toLong()) + spacer + format.format(sec.toLong())
            else
                min.toString() + spacer + format.format(sec.toLong())
            return time
        }
    }
}