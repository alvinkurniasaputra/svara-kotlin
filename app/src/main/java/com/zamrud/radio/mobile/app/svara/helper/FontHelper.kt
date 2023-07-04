package com.zamrud.radio.mobile.app.svara.helper

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView

class FontHelper {
    companion object {
        fun Bold(context: Context?, target: TextView?) {
            if (context != null && target != null) {
                val typeface: Typeface = Typeface.createFromAsset(context.assets, "fonts/FontBold.ttf")
                target.setTypeface(typeface, Typeface.BOLD)
            }
        }

        fun Bold(target: TextView?) {
            if (target != null)
                Bold(target.context, target)
        }

        fun Normal(context: Context?, target: TextView?) {
            if (context != null && target != null) {
                val typeface: Typeface = Typeface.createFromAsset(context.assets, "fonts/FontBody.ttf")
                target.setTypeface(typeface, Typeface.NORMAL)
            }
        }

        fun Normal(target: TextView?) {
            if (target != null)
                Normal(target.context, target)
        }
    }
}