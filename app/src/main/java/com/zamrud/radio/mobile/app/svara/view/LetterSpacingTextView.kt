package com.zamrud.radio.mobile.app.svara.view

import android.content.Context
import android.content.res.TypedArray
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ScaleXSpan
import android.util.AttributeSet
import android.widget.TextView
import com.zamrud.radio.mobile.app.svara.R

/**
 * Created by fahziar on 08/03/2016.
 */
class LetterSpacingTextView : TextView {
    private var spacing: Float = Spacing.NORMAL
    private var originalText: CharSequence? = ""

    constructor(context: Context?) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LetterSpacingTextView,
            0, 0)
        try {
            setText(a.getString(R.styleable.LetterSpacingTextView_text))
            setSpacing(a.getFloat(R.styleable.LetterSpacingTextView_spacing, 0.0f))
            setCompoundDrawablesWithIntrinsicBounds(0, 0, a.getResourceId(R.styleable.LetterSpacingTextView_drawableRight, 0), 0)
            compoundDrawablePadding = a.getDimensionPixelSize(R.styleable.LetterSpacingTextView_drawablePadding, 0)
        } finally {
            a.recycle()
        }
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    fun getSpacing(): Float {
        return this.spacing
    }

    fun setSpacing(spacing: Float) {
        this.spacing = spacing
        applySpacing()
    }

    override fun setText(text: CharSequence, type: BufferType) {
        originalText = text
        applySpacing()
    }

    override fun getText(): CharSequence? {
        return originalText
    }

    private fun applySpacing() {
        if (originalText == null) return
        val builder = StringBuilder()
        for (i in 0 until originalText!!.length) {
            builder.append(originalText!![i])
            if (i + 1 < originalText!!.length) {
                if (originalText!![i] != ' ') {
                    builder.append("\u00A0")
                } else {
                    builder.append(" ")
                }
            }
        }
        val finalText = SpannableString(builder.toString())
        if (builder.toString().length > 1) {
            for (i in 1 until builder.toString().length step 2) {
                finalText.setSpan(ScaleXSpan((spacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        super.setText(finalText, BufferType.SPANNABLE)
    }

    class Spacing {
        companion object {
            const val NORMAL: Float = 0f
        }
    }
}