package com.zamrud.radio.mobile.app.svara.login

import android.Manifest
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.TransitionDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.zamrud.radio.mobile.app.svara.CustomProject
import com.zamrud.radio.mobile.app.svara.DateUtils
import com.zamrud.radio.mobile.app.svara.R
import com.zamrud.radio.mobile.app.svara.TextUtils
import com.zamrud.radio.mobile.app.svara.apiclient.AuthenticationUtils
import com.zamrud.radio.mobile.app.svara.apiclient.ServiceGenerator
import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account
import com.zamrud.radio.mobile.app.svara.apiclient.services.AccountsService
import com.zamrud.radio.mobile.app.svara.dialog.DialogUploadProgress
import com.zamrud.radio.mobile.app.svara.main.LoginActivity
import pl.tajchert.nammu.Nammu
import pl.tajchert.nammu.PermissionCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.util.*

open class RegisterFragment : Fragment() {
    var account: Account? = null
    lateinit var btnSkip: View
    lateinit var btnGo: View
    lateinit var btnMale: TextView
    lateinit var btnFemale: TextView
    lateinit var lblUsername: TextView
    lateinit var imgProfile: ImageView
    lateinit var etFirstName: EditText
    lateinit var etLastName: EditText
    lateinit var etAbout: EditText
    lateinit var etBirthDay: EditText
    lateinit var etUsername: EditText
    private var gender: String? = ""
    private var imageFile: File? = null
    private var showUsername: Boolean = false
    var birthDay: String? = ""
    var progressDialog: DialogUploadProgress? = null
//    var imageChooser: ImageChooser? = null
    protected var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>? = null
    protected var takePicture: ActivityResultLauncher<Uri>? = null
    private var mCoverArtUri: Uri? = null

    companion object {
        fun newInstance(account: Account?): RegisterFragment {
            return newInstance(account, false)
        }

        fun newInstance(account: Account?, showUsername: Boolean): RegisterFragment {
            val registerFragment = RegisterFragment()
            registerFragment.account = account
            registerFragment.showUsername = showUsername
            return registerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is LoginActivity)
            throw ClassCastException(RegisterFragment::class.java.simpleName
                    + " must be place in LoginActivity! ")

        if (account == null)
            throw ClassCastException(RegisterFragment::class.java.simpleName
                    + " must be call with newInstance! ")

        getActivityLogin().setRegister(true)
//        imageChooser = ImageChooser(this@RegisterFragment)
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                mCoverArtUri = uri
                Glide.with(requireActivity()).load(mCoverArtUri).into(imgProfile)
                try {
//                    imageFile = ImageChooser.getFile(requireActivity(), mCoverArtUri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                Glide.with(requireActivity()).load(mCoverArtUri).into(imgProfile)
                try {
//                    imageFile = ImageChooser.getFile(requireActivity(), mCoverArtUri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        btnSkip = view.findViewById(R.id.btnSkip)
        imgProfile = view.findViewById(R.id.imgProfile)
        etFirstName = view.findViewById(R.id.etFirstName)
        etLastName = view.findViewById(R.id.etLastName)
        etAbout = view.findViewById(R.id.etAbout)
        etBirthDay = view.findViewById(R.id.etBirthDay)
        btnMale = view.findViewById(R.id.btnMale)
        btnFemale = view.findViewById(R.id.btnFemale)
        btnGo = view.findViewById(R.id.btnGo)
        lblUsername = view.findViewById(R.id.lblUsername)
        etUsername = view.findViewById(R.id.etUsername)

        etBirthDay.onFocusChangeListener = OnFocusChangeListener { v: View?, hasFocus: Boolean -> showDatePicker(hasFocus) }
        btnMale.setOnClickListener { v: View? -> onGenderChange(true) }
        btnFemale.setOnClickListener { v: View? -> onGenderChange(false) }

        if (!CustomProject.withSkip) {
            btnSkip.visibility = View.GONE
        } else {
            btnSkip.visibility = View.VISIBLE
        }

        btnSkip.setOnClickListener { v: View? ->
            getActivityLogin().showProgressDialog()
            getActivityLogin().onAuthenticationFinish()
        }
        btnGo.setOnClickListener { v: View? -> onGoClick() }
        imgProfile.setOnClickListener { v: View? -> onChangeImageClick() }
        fillField()

        if (showUsername) {
            lblUsername.visibility = View.VISIBLE
            etUsername.visibility = View.VISIBLE
        }
        getActivityLogin().setupUI(view)
        return view
    }

    private fun fillField() {
        etUsername.setText(account!!.getUsername())
        etFirstName.setText(account!!.getFirstName())
        etLastName.setText(account!!.getLastName())
        etAbout.setText(account!!.getAbout())
        etBirthDay.setText(DateUtils.toDateDMY(account!!.getBirthday()!!))
        if (account!!.getImages().getImage150()!!.isNotEmpty())
            Glide.with(imgProfile).load(account!!.getImages().getImage150()).into(imgProfile)
        if (account!!.getGender() != "undefined") {
            onGenderChange(account!!.getGender() == "male")
        }
    }

    private fun showDatePicker(hasFocus: Boolean) {
        if (!hasFocus)
            return
        etBirthDay.clearFocus()
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog.newInstance(
            { view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                var monthOfYears = monthOfYear
                val stringBuilder: StringBuilder = StringBuilder()
                stringBuilder.append(year)
                stringBuilder.append("-")
                stringBuilder.append(++monthOfYears)
                stringBuilder.append("-")
                stringBuilder.append(dayOfMonth)
                etBirthDay.setText(stringBuilder)
            },
            now[Calendar.YEAR],
            now[Calendar.MONTH],
            now[Calendar.DAY_OF_MONTH]
        )
        dpd.isThemeDark = false
        dpd.vibrate(true)
        dpd.dismissOnPause(true)
        dpd.showYearPickerFirst(true)
        dpd.show(getActivityLogin().supportFragmentManager, "date")
    }

    private fun onGenderChange(isMale: Boolean) {
        if (context == null)
            return
        if (gender != null) {
            if ((gender == "male") && isMale)
                return
            else if ((gender == "female") && !isMale)
                return
        }

        val maleTransition = btnMale.background as TransitionDrawable
        val femaleTransition = btnFemale.background as TransitionDrawable

        if (isMale) {
            maleTransition.startTransition(300)
            btnMale.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            if (gender != null && gender == "female") {
                femaleTransition.reverseTransition(300)
                btnFemale.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_75))
            }
        } else {
            femaleTransition.startTransition(300)
            btnFemale.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            if (gender != null && (gender == "male")) {
                maleTransition.reverseTransition(300)
                btnMale.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_75))
            }
        }

        if (isMale)
            gender = "male"
        else gender = "female"
    }

    private fun onGoClick() {
        getActivityLogin().hideKeyboard()
        val isFirstName: Boolean = TextUtils.hasText(etFirstName.text.toString())
        if (!isFirstName) {
            notifyToUser(getString(R.string.inter_first_name_blank))
            return
        }

        if (imageFile != null) {
            uploadImage()
            return
        }
        updateProfile(Account().nullify())
    }

    private fun uploadImage() {
        progressDialog = DialogUploadProgress.newInstance(context, imageFile!!.path, "cover art")
//        UploadHelper.toProfile(context)
//            .file(imageFile)
//            .setUploadResult(object : UploadResult() {
//                fun onSuccess(fileUrl: String?) {
//                    val accountUpdate = Account().nullify()
//                    accountUpdate.setProfilePicture((fileUrl)!!)
//                    progressDialog!!.dismiss()
//                    updateProfile(accountUpdate)
//                }
//
//                fun onFailed() {
//                    progressDialog!!.dismiss()
//                    notifyToUser(getString(R.string.failed_to_upload_image))
//                    updateProfile(Account().nullify())
//                }
//            }).start()
    }

    private fun updateProfile(accountUpdate: Account) {
        getActivityLogin().showProgressDialog()
        accountUpdate.setFirstName(etFirstName.text.toString())
        if (etLastName.length() > 0)
            accountUpdate.setLastName(etLastName.text.toString())
        if (etAbout.length() > 0)
            accountUpdate.setAbout(etAbout.text.toString())
        if (etBirthDay.length() > 0)
            accountUpdate.setBirthday(etBirthDay.text.toString())
        if (gender!!.isNotEmpty())
            accountUpdate.setGender(gender!!)
        val accountsService = ServiceGenerator.createServiceWithAuth(AccountsService::class.java, requireContext())
        accountsService.updateUserSetting(account!!.getId(), accountUpdate)
            .enqueue(object : Callback<Account?> {
                override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                    getUserDetail()
                }

                override fun onFailure(call: Call<Account?>, t: Throwable) {
                    getActivityLogin().onAuthenticationFinish()
                }
            })
    }

    private fun getUserDetail() {
        if (context == null) {
            getActivityLogin().onAuthenticationFinish()
            return
        }
        ServiceGenerator.createServiceWithAuth(AccountsService::class.java, requireContext())
            .getProfile(AuthenticationUtils.getLoggeInUserId(requireContext()))
            .enqueue(object : Callback<Account?> {
                override fun onResponse(call: Call<Account?>, response: Response<Account?>) {
                    if (response.isSuccessful && response.body() != null)
                        AuthenticationUtils.setAccount(call, response)
                    getActivityLogin().onAuthenticationFinish()
                }

                override fun onFailure(call: Call<Account?>, t: Throwable) {
                    getActivityLogin().onAuthenticationFinish()
                }
            })
    }

    private fun onChangeImageClick() {
        if (context == null)
            return
        Nammu.askForPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE, object : PermissionCallback {
            override fun permissionGranted() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
                    StrictMode.setVmPolicy(builder.build())
                }
                onCreateDialog()
            }

            override fun permissionRefused() {

            }
        })
    }

    fun onCreateDialog(): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.pick_resource)
            .setItems(R.array.colors_array, DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    0 -> {
                        try {
//                            mCoverArtUri = FileProvider.getUriForFile(requireActivity(), "com.zamrud.radio.mobile.app.svara.provider", imageChooser!!.createImageFile())
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        takePicture!!.launch(mCoverArtUri)
                    }
                    1 -> pickMedia!!.launch(PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        .build())
                }
            })
        builder.create()
        return builder.show()
    }

    private fun notifyToUser(message: String) {
        if (context == null)
            return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun getActivityLogin(): LoginActivity {
        return activity as LoginActivity
    }

}