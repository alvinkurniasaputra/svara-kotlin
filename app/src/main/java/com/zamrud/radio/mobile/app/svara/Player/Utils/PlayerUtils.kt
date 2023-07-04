package com.zamrud.radio.mobile.app.svara.Player.Utils

import android.content.Context
import android.content.SharedPreferences
import com.google.android.exoplayer2.Player

/**
 * Created by solusi247 on 08/02/17.
 */

class PlayerUtils {

    companion object {
        private const val PLAYER_SETTINGS = "svara.player.setting"
        private const val KEY_SUFFLE = "avara.player.shuffle"
        private const val KEY_REPEAT = "avara.player.repeat"
        private const val KEY_LOCK_REPEAT = "avara.player.lockrepeat"
        private const val KEY_LOCK_SUFFLE = "avara.player.lockshuffle"
        private const val KEY_LOCK_NEXT = "avara.player.locknext"
        private const val KEY_LOCK_PREV = "avara.player.lockprev"

        /**
         * get repeat condition saved
         * @param context Context to access sharedPreference
         * @return int repeat mode
         */
        fun getRepeat(context: Context): Int {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            return setting.getInt(KEY_REPEAT, Player.REPEAT_MODE_OFF)
        }

        /**
         * save repeat condition
         * @param context Context to access sharedPreference
         * @param repeat repeat mode
         */
        fun setRepeat(context: Context, repeat: Int) {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            val editor = setting.edit()
            editor.putInt(KEY_REPEAT, repeat)
            editor.apply()
        }

        /**
         * get shuffle condition saved
         * @param context Context to access sharedPreference
         * @return boolean is shuffling
         */
        fun isShuffling(context: Context): Boolean {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            return setting.getBoolean(KEY_SUFFLE, false)
        }

        /**
         * save shuffling condition
         * @param context Context to access sharedPreference
         * @param isShuffling boolean is shuffling
         */
        fun setShuffling(context: Context, isShuffling: Boolean) {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            val editor = setting.edit()
            editor.putBoolean(KEY_SUFFLE, isShuffling)
            editor.apply()
        }

        fun saveStateLock(context: Context, shufle: Boolean, repeat: Boolean, next: Boolean, prev: Boolean) {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            val editor = setting.edit()
            editor.putBoolean(KEY_LOCK_SUFFLE, shufle)
            editor.putBoolean(KEY_LOCK_REPEAT, repeat)
            editor.putBoolean(KEY_LOCK_NEXT, next)
            editor.putBoolean(KEY_LOCK_PREV, prev)
        }

        fun getLockShuffle(context: Context): Boolean {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            return setting.getBoolean(KEY_LOCK_SUFFLE, false)
        }

        fun getLockRepeat(context: Context): Boolean {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            return setting.getBoolean(KEY_LOCK_REPEAT, false)
        }

        fun getLockNext(context: Context): Boolean {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            return setting.getBoolean(KEY_LOCK_NEXT, false)
        }

        fun getLockPrev(context: Context): Boolean {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            return setting.getBoolean(KEY_LOCK_PREV, true)
        }

        fun clearAll(context: Context) {
            val setting: SharedPreferences = context.getSharedPreferences(PLAYER_SETTINGS, Context.MODE_PRIVATE)
            val editor = setting.edit()
            editor.clear()
            editor.apply()
        }
    }

}
