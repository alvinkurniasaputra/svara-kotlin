package com.zamrud.radio.mobile.app.svara.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.zamrud.radio.mobile.app.svara.R

/**
 * Created by solusi247 on 06/10/16.
 */
class DialogShowFreePopup : BaseDialog() {
    lateinit var txtMessage: TextView
    lateinit var btnClose: TextView
    lateinit var btnPremium: Button
    lateinit var btnSetting: ImageView
    lateinit var activity: Context
    lateinit var mType: type

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_free_popup, container, false)
        txtMessage = view.findViewById(R.id.txt_msg) as TextView
        btnPremium = view.findViewById(R.id.btn_go_premium) as Button
        //        btnSetting = (ImageView) view.findViewById(R.id.btn_p_setting) ;
        btnClose = view.findViewById(R.id.btn_close) as TextView

        when (mType) {
            type.force_logout -> {
                //                btnSetting.setVisibility(View.GONE);
                btnClose.visibility = View.GONE
                txtMessage.setText(R.string.force_logout)
                btnPremium.text = getString(R.string.close)
            }
            else -> {}
        }

        btnPremium.setOnClickListener { dismiss() }

        return view
    }

    enum class type {
        playlist,
        artist,
        album,
        content,
        force_logout
    }

    companion object {
        fun newInstance(activity: Context, t: type): DialogShowFreePopup {
            val args = Bundle()

            val fragment = DialogShowFreePopup()
            fragment.arguments = args
            fragment.activity = activity
            fragment.mType = t
            return fragment
        }
    }
}