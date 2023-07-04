package com.zamrud.radio.mobile.app.svara.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.FragmentManager
import android.content.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.zamrud.radio.mobile.app.svara.R
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment

/**
 * Created by solusi247 on 06/10/16.
 */
class DialogUploadProgress : BlurDialogFragment() {
    var imageView: ImageView? = null
    var seekBar: SeekBar? = null
    var txtLoading: TextView? = null
    var txtPercent: TextView? = null
    var img: String? = null
    var waiting: String? = null
    var action = "Uploading"
    @JvmField
    var context: Context? = null

    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun isActionBarBlurred(): Boolean {
        return true
    }

    override fun isDimmingEnable(): Boolean {
        return true
    }

    override fun getBlurRadius(): Int {
        return 7
    }

    override fun isRenderScriptEnable(): Boolean {
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        LocalBroadcastManager.getInstance(context!!).registerReceiver(progressReceiver, IntentFilter(UploadHelper.EVENT_NAME))
    }

    override fun onDismiss(dialog: DialogInterface) {
        LocalBroadcastManager.getInstance(context!!).unregisterReceiver(progressReceiver)
        super.onDismiss(dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_upload_progress, container, false)
        imageView = view.findViewById<ImageView>(R.id.cover)
        seekBar = view.findViewById<SeekBar>(R.id.seek_bar)
        txtLoading = view.findViewById<TextView>(R.id.text_loading)
        txtPercent = view.findViewById<TextView>(R.id.text_percent)
        if (img == null) imageView!!.setVisibility(View.GONE) else Glide.with(context!!).load(img)
            .into(imageView!!)
        txtLoading!!.setText(String.format("%s %s", action, waiting))
        txtPercent!!.setText("0%")
        seekBar!!.setEnabled(false)
        seekBar!!.setMax(100)
        seekBar!!.setPadding(0, 0, 0, 0)
        //        seekBar.setProgress(0);
        return view
    }

    fun tag(s: String?) {
        waiting = s
        action = "Uploading"
        if (txtLoading != null) txtLoading!!.text = String.format("Uploading %s", waiting)
    }

    fun tag(s: String?, action: String) {
        waiting = s
        this.action = action
        if (txtLoading != null) txtLoading!!.text = String.format("%s %s", action, waiting)
    }

    fun changeImage(imageUrl: String?) {
        img = imageUrl
        if (imageView != null) Glide.with(context!!).load(img).into(imageView!!)
    }

    @SuppressLint("DefaultLocale")
    fun setPercent(percent: Float) {
        if (seekBar == null) return
        seekBar!!.progress = percent.toInt()
        txtPercent!!.text = String.format("%.2f", percent)
    }

    override fun show(manager: FragmentManager, tag: String) {
        super.show(manager, tag)
    }

    private val progressReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("DefaultLocale")
        override fun onReceive(context: Context, intent: Intent) {
            if (seekBar == null || txtPercent == null) return
            val progress = intent.getFloatExtra("progress", 0.0f)
            val message = intent.getStringExtra("message")
            if (message != null) txtLoading!!.text = String.format("%s", message)
            seekBar!!.progress = progress.toInt()
            txtPercent!!.text = String.format("%.2f%s", progress, "%")
        }
    }

    companion object {
        fun newInstance(context: Context?, img: String?, waiting: String?): DialogUploadProgress {
            val args = Bundle()
            val fragment = DialogUploadProgress()
            fragment.arguments = args
            fragment.img = img
            fragment.context = context
            fragment.waiting = waiting
            return fragment
        }
    }
}