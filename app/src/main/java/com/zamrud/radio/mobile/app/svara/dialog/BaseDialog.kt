package com.zamrud.radio.mobile.app.svara.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogEngine

/**
 * Created by fahziar on 02/11/2016.
 */
abstract class BaseDialog : DialogFragment() {
    private var mBlurEngine: BlurDialogEngine? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBlurEngine = BlurDialogEngine(activity)
        mBlurEngine!!.setBlurRadius(7)
        mBlurEngine!!.debug(false)
        mBlurEngine!!.setBlurActionBar(true)
        mBlurEngine!!.setUseRenderScript(false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onResume() {
        super.onResume()
        mBlurEngine!!.onResume(retainInstance)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        mBlurEngine!!.onDismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        //        mBlurEngine.onDestroy();
    }

    override fun onDestroyView() {
        if (dialog != null) {
            dialog!!.setDismissMessage(null)
        }
        super.onDestroyView()
    }
}