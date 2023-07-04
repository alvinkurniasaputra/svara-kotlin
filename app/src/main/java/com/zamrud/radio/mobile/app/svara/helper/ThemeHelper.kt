package com.zamrud.radio.mobile.app.svara.helper

import android.content.Context
import android.content.res.Resources

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import android.util.TypedValue

class ThemeHelper {
    companion object {
        fun getColorAttr(context: Context, attrId: Int): Int {
            val typedValue = TypedValue()
            val themes: Resources.Theme = context.theme
            themes.resolveAttribute(attrId, typedValue, true)
            @ColorInt val color: Int = typedValue.data
            return color
        }

        fun getDrawableAttr(context: Context, attrId: Int): Int {
            val typedValue = TypedValue()
            val themes: Resources.Theme = context.theme
            themes.resolveAttribute(attrId, typedValue, true)
            //        Drawable drawable = ContextCompat.getDrawable(context, typedValue.resourceId);
            @DrawableRes val drawable: Int = typedValue.resourceId
            return drawable
        }
    }
}