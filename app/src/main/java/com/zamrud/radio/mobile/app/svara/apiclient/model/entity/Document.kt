package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

//import com.zamrud.radio.mobile.app.svara.dialog.ActionDialog
//import com.zamrud.radio.mobile.app.svara.document.FragmentDocument
import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.BaseActivity
import com.zamrud.radio.mobile.app.svara.PlaceholderUtil
import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction
import com.zamrud.radio.mobile.app.svara.document.DocumentHelpers
import com.zamrud.radio.mobile.app.svara.helper.DocumentChooser
import org.parceler.Parcel
import org.parceler.Parcels
import java.text.SimpleDateFormat
import java.util.*

@Parcel
class Document : ModelWithAction() {
    @SerializedName("id")
    @Expose
    @JvmField
    var id: String? = ""

    @SerializedName("name")
    @Expose
    @JvmField
    var name: String? = ""

    @SerializedName("content")
    @Expose
    @JvmField
    var content: String? = ""

    @SerializedName("isPrivate")
    @Expose
    @JvmField
    var isPrivate = false

    @SerializedName("caption")
    @Expose
    @JvmField
    var caption: String? = ""

    @SerializedName("fileSize")
    @Expose
    @JvmField
    var fileSize: Long = 0

    @SerializedName("provider")
    @Expose
    @JvmField
    var provider: String? = ""

    @SerializedName("createdAt")
    @Expose
    @JvmField
    var createAt: Date? = Date()

    @SerializedName("accountId")
    @Expose
    @JvmField
    var accountId: String? = ""

    @SerializedName("keyId")
    @Expose
    @JvmField
    var keyId: String? = ""

    @SerializedName("container")
    @Expose
    @JvmField
    var container: String? = ""

    @SerializedName("owner")
    @Expose
    @JvmField
    var owner: Account? = null

    @SerializedName("image")
    @Expose
    @JvmField
    var image: String? = ""

    @SerializedName("images")
    @Expose
    @JvmField
    var images: Images? = null


    init {
        owner = Account()
    }

    override fun getContentTypeString(): String {
        return ModelContract.getSearchString(ModelContract.CATEGORY_DOCUMENT)
    }

    override fun getLines(): ArrayList<CharSequence?>? {
        val out: ArrayList<CharSequence?>? = super.getLines()
        out?.set(0, getName())
        out?.set(1, getOwner()?.getFullName())
        @SuppressLint("SimpleDateFormat") val dateFormat = SimpleDateFormat("MMM dd yyyy")
        out?.set(2, dateFormat.format(getCreateAt()!!))
        return out
    }

    override fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getPrivate(): Boolean {
        return isPrivate
    }

    fun setPrivate(aPrivate: Boolean) {
        isPrivate = aPrivate
    }

    fun getCaption(): String? {
        return caption
    }

    fun setCaption(caption: String?) {
        this.caption = caption
    }

    fun getFileSize(): Long {
        return fileSize
    }

    fun setFileSize(fileSize: Long) {
        this.fileSize = fileSize
    }

    fun getProvider(): String? {
        return provider
    }

    fun setProvider(provider: String?) {
        this.provider = provider
    }

    fun getCreateAt(): Date? {
        return createAt
    }

    fun setCreateAt(createAt: Date?) {
        this.createAt = createAt
    }

    fun getAccountId(): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }

    fun getKeyId(): String? {
        return keyId
    }

    fun setKeyId(keyId: String?) {
        this.keyId = keyId
    }

    fun getContainer(): String? {
        return container
    }

    fun setContainer(container: String?) {
        this.container = container
    }

    fun getOwner(): Account? {
        return owner
    }

    fun setOwner(owner: Account?) {
        this.owner = owner
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun setImages(images: Images?) {
        this.images = images
    }

    override fun getImages(): Images? {
        if (images == null)
            images = Images()

        return if (images!!.imageOri!!.isEmpty())
            createImageFromExt()
        else images
    }

    private fun createImageFromExt(): Images {
        val icon: String? = getDocIcon(container)
        val images = Images()
        val coverImages = CoverImages()
        coverImages.setCoverImage300(icon!!)
        coverImages.setCoverImage640(icon)
        coverImages.setCoverImage1280(icon)
        coverImages.setPlaceholder("")
        images.setCoverImages(coverImages)
        images.setImage64(icon)
        images.setImage150(icon)
        images.setImage300(icon)
        images.setImage640(icon)
        images.setImageOri(icon)
        images.setImagew640(icon)
        images.setPlaceholder("")

        return images
    }

    private fun getDocIcon(ext: String?): String? {
        return when (ext) {
            DocumentChooser.JPG,
            DocumentChooser.JPEG,
            DocumentChooser.PNG -> {
                if (DocumentHelpers.isImage(this) && DocumentHelpers.isFileExist(this))
                    return DocumentHelpers.getFile(this).path
                if (getContent()!!.startsWith("http")) getContent() else PlaceholderUtil.ICON_DOC
            }
            DocumentChooser.DOC,
            DocumentChooser.DOCX -> PlaceholderUtil.ICON_WORD
            DocumentChooser.XLS,
            DocumentChooser.XLSX -> PlaceholderUtil.ICON_EXCEL
            DocumentChooser.PPT,
            DocumentChooser.PPTX -> PlaceholderUtil.ICON_PPT
            DocumentChooser.CSV -> PlaceholderUtil.ICON_CSV
            DocumentChooser.PDF -> PlaceholderUtil.ICON_PDF
            DocumentChooser.TXT -> PlaceholderUtil.ICON_TXT
            DocumentChooser.JSON -> PlaceholderUtil.ICON_DOC
            else -> PlaceholderUtil.ICON_DOC
        }
    }

    fun getMimeType(): String {
        return when (container) {
            DocumentChooser.JPG -> "image/jpg"
            DocumentChooser.JPEG -> "image/jpeg"
            DocumentChooser.PNG -> "image/png"
            DocumentChooser.DOC -> "application/msword"
            DocumentChooser.DOCX -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            DocumentChooser.XLS -> "application/vnd.ms-excel"
            DocumentChooser.XLSX -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            DocumentChooser.CSV -> "text/csv"
            DocumentChooser.PDF -> "application/pdf"
            DocumentChooser.JSON -> "application/json"
            DocumentChooser.TXT -> "text/plain"
            else -> "text/plain"
        }
    }

    override fun getOwnerId(): String? {
        return if (owner != null)
            owner!!.getId()
        else " "
    }

    override fun onClick(activity: BaseActivity) {
//        activity.startFragment(FragmentDocument.newInstance(id))
    }

    override fun onLongClick(activity: BaseActivity) {
        val data: Parcelable = Parcels.wrap(Document::class.java, this)
//        val dialog: ActionDialog = ActionDialog.newInstance(ModelContract.CATEGORY_DOCUMENT, data)
//        dialog.show(activity.getSupportFragmentManager(), "dialog")
    }
}