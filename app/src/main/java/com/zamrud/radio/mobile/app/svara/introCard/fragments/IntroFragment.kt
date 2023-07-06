package com.zamrud.radio.mobile.app.svara.introCard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder
import com.github.paolorotolo.appintro.ISlideSelectionListener
import com.github.paolorotolo.appintro.util.CustomFontCache
import com.zamrud.radio.mobile.app.svara.PlaceholderUtil
import com.zamrud.radio.mobile.app.svara.R

/**
 * Created by solusi247 on 17/07/17.
 */
class IntroFragment : Fragment(), ISlideSelectionListener, ISlideBackgroundColorHolder {
    private var drawable = 0
    private var bgColor = 0
    private var titleColor = 0
    private var descColor = 0
    private val layoutId = 0

    private var title: String? = null
    private var titleTypeface: String? = null
    private var description: String? = null
    private var descTypeface: String? = null
    private var imgUrl: String? = null
    private var imgPH: String? = null

    private var mainLayout: LinearLayout? = null

    private val fontUrl = "fonts/FontHeader.ttf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        if (arguments != null && requireArguments().size() != 0) {
            drawable = requireArguments().getInt(ARG_DRAWABLE)
            title = requireArguments().getString(ARG_TITLE)
            titleTypeface = if (requireArguments().containsKey(ARG_TITLE_TYPEFACE))
                requireArguments().getString(ARG_TITLE_TYPEFACE) else ""
            description = requireArguments().getString(ARG_DESC)
            descTypeface = if (requireArguments().containsKey(ARG_DESC_TYPEFACE))
                requireArguments().getString(ARG_DESC_TYPEFACE) else ""
            bgColor = requireArguments().getInt(ARG_BG_COLOR)
            titleColor = if (requireArguments().containsKey(ARG_TITLE_COLOR))
                requireArguments().getInt(ARG_TITLE_COLOR) else 0
            descColor = if (requireArguments().containsKey(ARG_DESC_COLOR))
                requireArguments().getInt(ARG_DESC_COLOR) else 0
            imgUrl = if (requireArguments().containsKey(ARG_IMAGE_URL))
                requireArguments().getString(ARG_IMAGE_URL) else ""
            imgPH = if (requireArguments().containsKey(ARG_IMAGE_PH))
                requireArguments().getString(ARG_IMAGE_PH) else ""
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.intro_card, container, false)
        val t = v.findViewById(com.github.paolorotolo.appintro.R.id.title) as TextView
        val d = v.findViewById(com.github.paolorotolo.appintro.R.id.description) as TextView
        val i = v.findViewById(com.github.paolorotolo.appintro.R.id.image) as ImageView
        mainLayout = v.findViewById(com.github.paolorotolo.appintro.R.id.main) as LinearLayout

        if (CustomFontCache.get(fontUrl, this.activity) != null) {
            t.typeface = CustomFontCache.get(fontUrl, this.activity)
            d.typeface = CustomFontCache.get(fontUrl, this.activity)
        }

        t.text = title
        if (title == null || title!!.isEmpty())
            t.visibility = View.GONE
        if (titleColor != 0) {
            t.setTextColor(titleColor)
        }
        if (titleTypeface != null) {
            if (CustomFontCache.get(titleTypeface, context) != null) {
                t.typeface = CustomFontCache.get(titleTypeface, context)
            }
        }
        d.text = description
        if (description == null || description!!.isEmpty())
            d.visibility = View.GONE
        if (descColor != 0) {
            d.setTextColor(descColor)
        }
        if (descTypeface != null) {
            if (CustomFontCache.get(descTypeface, context) != null) {
                d.typeface = CustomFontCache.get(descTypeface, context)
            }
        }
        if (drawable == 0) {
            PlaceholderUtil.into(requireContext(), imgPH, imgUrl, i)
        } else {
            i.setImageResource(drawable)
        }

        mainLayout!!.setBackgroundColor(bgColor)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            drawable = savedInstanceState.getInt(ARG_DRAWABLE)
            title = savedInstanceState.getString(ARG_TITLE)
            titleTypeface = savedInstanceState.getString(ARG_TITLE_TYPEFACE)
            description = savedInstanceState.getString(ARG_DESC)
            descTypeface = savedInstanceState.getString(ARG_DESC_TYPEFACE)
            bgColor = savedInstanceState.getInt(ARG_BG_COLOR)
            titleColor = savedInstanceState.getInt(ARG_TITLE_COLOR)
            descColor = savedInstanceState.getInt(ARG_DESC_COLOR)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(ARG_DRAWABLE, drawable)
        outState.putString(ARG_TITLE, title)
        outState.putString(ARG_DESC, description)
        outState.putInt(ARG_BG_COLOR, bgColor)
        outState.putInt(ARG_TITLE_COLOR, titleColor)
        outState.putInt(ARG_DESC_COLOR, descColor)
        super.onSaveInstanceState(outState)
    }

    override fun getDefaultBackgroundColor(): Int {
        return bgColor
    }

    override fun setBackgroundColor(@ColorInt backgroundColor: Int) {
        mainLayout?.setBackgroundColor(backgroundColor)
    }

    override fun onSlideSelected() {}

    override fun onSlideDeselected() {}

    companion object {
        protected const val ARG_TITLE = "title"
        protected const val ARG_TITLE_TYPEFACE = "title_typeface"
        protected const val ARG_DESC = "desc"
        protected const val ARG_DESC_TYPEFACE = "desc_typeface"
        protected const val ARG_DRAWABLE = "drawable"
        protected const val ARG_BG_COLOR = "bg_color"
        protected const val ARG_TITLE_COLOR = "title_color"
        protected const val ARG_DESC_COLOR = "desc_color"
        protected const val ARG_IMAGE_URL = "image_url"
        protected const val ARG_IMAGE_PH = "image_ph"

        fun newInstance(title: CharSequence?, description: CharSequence?,
                        @DrawableRes imageDrawable: Int,
                        @ColorInt bgColor: Int): IntroFragment {
            return newInstance(title, description, imageDrawable, null, null, bgColor, 0, 0)
        }

        fun newInstance(title: CharSequence?, description: CharSequence?,
                        imageUrl: String?, imagePh: String?,
                        @ColorInt bgColor: Int): IntroFragment {
            return newInstance(title, description, 0, imageUrl, imagePh, bgColor, 0, 0)
        }

        fun newInstance(title: CharSequence?, description: CharSequence?,
                        @DrawableRes imageDrawable: Int, imageUrl: String?,
                        imagePh: String?,
                        @ColorInt bgColor: Int,
                        @ColorInt titleColor: Int, @ColorInt descColor: Int): IntroFragment {
            val slide = IntroFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title.toString())
            args.putString(ARG_TITLE_TYPEFACE, null)
            args.putString(ARG_DESC, description.toString())
            args.putString(ARG_DESC_TYPEFACE, null)
            args.putInt(ARG_DRAWABLE, imageDrawable)
            args.putInt(ARG_BG_COLOR, bgColor)
            args.putInt(ARG_TITLE_COLOR, titleColor)
            args.putInt(ARG_DESC_COLOR, descColor)
            args.putString(ARG_IMAGE_URL, imageUrl)
            slide.arguments = args

            return slide
        }

        fun newInstance(title: CharSequence?, titleTypeface: String?,
                        description: CharSequence?, descTypeface: String?,
                        @DrawableRes imageDrawable: Int,
                        @ColorInt bgColor: Int): IntroFragment {
            return newInstance(title, titleTypeface, description, descTypeface, imageDrawable, bgColor,
                0, 0)
        }

        fun newInstance(title: CharSequence?, titleTypeface: String?, description: CharSequence?, descTypeface: String?,
                        @DrawableRes imageDrawable: Int, @ColorInt bgColor: Int,
                        @ColorInt titleColor: Int, @ColorInt descColor: Int): IntroFragment {
            val slide = IntroFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title.toString())
            args.putString(ARG_TITLE_TYPEFACE, titleTypeface)
            args.putString(ARG_DESC, description.toString())
            args.putString(ARG_DESC_TYPEFACE, descTypeface)
            args.putInt(ARG_DRAWABLE, imageDrawable)
            args.putInt(ARG_BG_COLOR, bgColor)
            args.putInt(ARG_TITLE_COLOR, titleColor)
            args.putInt(ARG_DESC_COLOR, descColor)
            slide.arguments = args

            return slide
        }
    }
}