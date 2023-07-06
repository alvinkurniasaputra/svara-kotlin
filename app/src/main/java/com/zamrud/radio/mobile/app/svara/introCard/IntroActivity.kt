package com.zamrud.radio.mobile.app.svara.introCard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro
import com.google.gson.Gson
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.model.IntroCard.IntroCard
import com.zamrud.radio.mobile.app.svara.apiclient.model.curation.AppParam
import com.zamrud.radio.mobile.app.svara.introCard.fragments.IntroFragment
import com.zamrud.radio.mobile.app.svara.main.LoginActivity
import com.zamrud.radio.mobile.app.svara.main.PrepareActivity

/**
 * Created by solusi247 on 17/07/17.
 */
class IntroActivity : AppIntro() {
    private var fontUrl = "fonts/FontBody.ttf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val caller = intent
        var appParam: AppParam? = null
        if (caller != null && caller.extras?.getString("AppParam") != null) {
            appParam = Gson().fromJson(caller.extras?.getString("AppParam"), AppParam::class.java)
        }
        if (CustomProject.useIntroStart)
            addSlide(IntroFragment.newInstance("", "",
                R.drawable.intro_logo, downColor(resources.getColor(R.color.background_intro_start))))
        if (appParam?.getIntroCards() != null && appParam.getIntroCards()!!.isNotEmpty()) {
            for (introCard: IntroCard in appParam.getIntroCards()!!) {
                addSlide(IntroFragment.newInstance(introCard.getTitle(),
                        introCard.getDescription(),
                        introCard.getImage().getUrl(),
                        introCard.getImage().getPlaceholder(),
                        Color.parseColor(introCard.getColorHex())))
            }
        }
        addSlide(IntroFragment.newInstance("", "",
            R.drawable.intro_done, resources.getColor(R.color.background_intro_end)))

        showSkipButton(false)
        showStatusBar(false)
        isProgressButtonEnabled = true
        setColorTransitionsEnabled(true)
        setDoneText(getString(R.string.start))

        setDoneTextTypeface(fontUrl)
        setSkipTextTypeface(fontUrl)

        askForPermissions(arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.FOREGROUND_SERVICE
            ), 1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
                setSwipeLock(false)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        setSwipeLock(false)
    }

    override fun onSkipPressed(currentFragment: Fragment) {
//        super.onSkipPressed(currentFragment);
//        finish();
        pager.setCurrentItem(fragments.size - 1, true)
    }

    override fun onDonePressed(currentFragment: Fragment) {
//        super.onDonePressed(currentFragment);
//        finish();
        AuthenticationUtils.setIntro(false)
        val i: Intent
        i = if (CustomProject.autoSkip) {
            Intent(this, PrepareActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(i)
        finish()
    }

    override fun onSlideChanged(oldFragment: Fragment?, newFragment: Fragment?) {
//        super.onSlideChanged(oldFragment, newFragment);
//        finish();
        val pos = pager.currentItem
        if (pos > 0 && pos < fragments.size - 1) showSkipButton(true)
        else {
            showSkipButton(false)
        }
    }

    private fun downColor(color: Int): Int {
        var r = color shr 16 and 0xFF
        var g = color shr 8 and 0xFF
        var b = color shr 0 and 0xFF

        r = if (r > 30) r - 30 else 0
        g = if (g > 30) g - 30 else 0
        b = if (b > 30) b - 30 else 0
        return Color.rgb(r, g, b)
    }
}