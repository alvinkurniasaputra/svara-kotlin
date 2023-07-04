package com.zamrud.radio.mobile.app.svara

import org.joda.time.DateTime

import java.sql.Timestamp
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs

/**
 * Created by fahziar on 12/05/2016.
 */
class DateUtils {

    companion object {
        const val YEAR_IN_MILIS = 31556952000L
        private const val SECOND_MILLIS = 1000
        private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
        private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
        private const val DAY_MILLIS = 24 * HOUR_MILLIS

        fun getCurrentIsoDate(): String {
            return dateToStringISO8601(Date())
        }

        fun dateToStringISO8601(date: Date): String {
            val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
            df.timeZone = TimeZone.getTimeZone("UTC")
            return df.format(date)
        }

        fun getTimeAgo(times: Long): String? {
            var time = times
            if (time < 1000000000000L) {
                // if timestamp given in seconds, convert to millis
                time *= 1000
            }

            val now: Long = System.currentTimeMillis()

//        if (time > now || time <= 0) {
//            return null;
//        }

            if (time > now) {
                return "just now" //terpaksa bukan null karena acuan waktu di backend dengan waktu di android beda
            }

            if (time <= 0) {
                return null
            }

            // TODO: localize
            val diff: Long = now - time
            return if (diff < MINUTE_MILLIS) {
                "just now"
            } else if (diff < 2 * MINUTE_MILLIS) {
                (diff / MINUTE_MILLIS).toString() + "m"
//                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                (diff / MINUTE_MILLIS).toString() + "m"
//                return diff / MINUTE_MILLIS + " minutes ago";
            } else if (diff < 90 * MINUTE_MILLIS) {
                "1h"
//                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                (diff / HOUR_MILLIS).toString() + "h"
//                return diff / HOUR_MILLIS + " hours ago";
            } else if (diff < 48 * HOUR_MILLIS) {
                (diff / HOUR_MILLIS).toString() + "h"
//                return "yesterday";
            } else {
                (diff / DAY_MILLIS).toString() + "d"
//                return diff / DAY_MILLIS + " days ago";
            }
        }

        fun toNormalHourGMT(timestamp: String): String {
            return toNormalHour(toGMT(timestamp))
        }

        fun toNormalHour(timestamp: String): String {
            val date = Date()
            val currDate: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").format(Date())
            val now: String = "" + Timestamp(date.time)
            val timeStampGMT: String = toGMT(timestamp)
            return toHours_HH_MM(timestamp, ":")
        }

        private fun toGMT(timestamp: String): String {
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z")
            val servertime = toYear(timestamp) + "-" + toMounth(timestamp) + "-" + toDay(timestamp) + " " + toHours_HH_MM(timestamp, ":") + ":" + toSec(timestamp) + "." + toMilSec(timestamp) + " GMT"
            var date: Date? = null
            var epoch: Long = 0
            var epochServer: Long = 0

            try {
                date = df.parse(df.format(Date()))
                epoch = date.time
                epochServer = df.parse(servertime).time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return calGMT(epochServer)
        }

        fun toHours_HH_MM(timestamp: String, dividing: String): String {
           if (timestamp.length < 19)
               return timestamp

           return toHours(timestamp) + dividing + toMinutes(timestamp)
        }

        fun calGMT(serverTime: Long): String {
            val offsetInMillis: Long = getCurrentTimezoneOffset().toLong()
            val server: Long = serverTime

            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z")
            val res: String = df.format(Date(server))
            return res
        }

        fun toMounth(timestamp: String): String {
            if (timestamp.length < 7) return "00"
            return timestamp.substring(5, 7)
        }

        fun toDay(timestamp: String): String {
            if (timestamp.length < 10) return "00"
            return timestamp.substring(8, 10)
        }

        fun toYear(timestamp: String): String {
            if (timestamp.length < 4) return "00"
            return timestamp.substring(0, 4)
        }

        fun toHours(timestamp: String): String {
            if (timestamp.length < 13) return "00"
            return timestamp.substring(11, 13)
        }

        fun toMinutes(timestamp: String): String {
            if (timestamp.length < 16) return "00"
            return timestamp.substring(14, 16)
        }

        fun toSec(timestamp: String): String {
            if (timestamp.length < 19) return "00"
            return timestamp.substring(17, 19)
        }

        fun toMilSec(timestamp: String): String {
            if (timestamp.length < 19) return "00"
            return timestamp.substring(20, 23)
        }

        fun getCurrentTimezoneOffset(): Int {
            val tz: TimeZone = TimeZone.getDefault()
            val cal: Calendar = GregorianCalendar.getInstance(tz)
            val offsetInMillis: Int = tz.getOffset(cal.timeInMillis)
            val offset: String = String.format("%02d:%02d", abs(offsetInMillis / 3600000), abs(offsetInMillis / 60000 % 60))
            return offsetInMillis
        }

        fun toDateYMD(timestamp: String): String {
            val tm: String = toGMT(timestamp)
            return toYear(tm) + "-" + toMounth(tm) + "-" + toDay(tm)
        }

        fun toDateDMY(timestamp: String): String {
            val tm: String = toGMT(timestamp)
            return toDay(tm) + "-" + toMounth(tm) + "-" + toYear(tm)
        }

        fun normalize(date: Date): String {
            val dateTime = DateTime(date)
            return dateTime.toString("dd MMM YYYY HH:ss")
        }

        fun isBeforeNow(date: Date): Boolean {
            val dateTime = DateTime(date)
            return dateTime.isBeforeNow
        }
    }
}