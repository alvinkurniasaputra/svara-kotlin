package com.zamrud.radio.mobile.app.svara.apiclient.model.curation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zamrud.radio.mobile.app.svara.apiclient.model.BaseModel

open class CurationPagesMetadata : BaseModel() {
    companion object {
        const val TEXT_ALIGN_LEFT = "left"
        const val TEXT_ALIGN_CENTER = "center"
        const val TEXT_ALIGN_RIGHT = "right"
        const val INFO_OUTSIDE = "outside"
        const val INFO_INSIDE = "inside"
        const val INFO_INSIDE_TOP = "top"
        const val INFO_INSIDE_MIDDLE = "middle"
        const val INFO_INSIDE_BOTTOM = "bottom"
        const val LAYOUT_SIZE_EXTRA_SMALL = "extra-small"
        const val LAYOUT_SIZE_SMALL = "small"
        const val LAYOUT_SIZE_MEDIUM = "medium"
        const val LAYOUT_SIZE_LARGE = "large"
    }

    @SerializedName("name")
    @Expose
    var mName = ""

    @SerializedName("desc")
    @Expose
    var mDesc = ""

    @SerializedName("viewType")
    @Expose
    var mViewType = ""

    @SerializedName("viewTypeOnMore")
    @Expose
    @JvmField
    var viewTypeOnMore = ""

    @SerializedName("parent")
    @Expose
    var mParent = ""

    @SerializedName("dataFilter")
    @Expose
    lateinit var mDataFilter: DataFilter

    //    @SerializedName("order") @Expose private Integer mOrder;
    @SerializedName("id")
    @Expose
    var mId = ""

    @SerializedName("curationPageId")
    @Expose
    var mCurationPageId = ""

    @SerializedName("child")
    @Expose
    var mChilds: List<CurationPagesMetadata>? = null

    @SerializedName("layout")
    @Expose
    var mLayout = ""

    @SerializedName("layoutOnMore")
    @Expose
    @JvmField
    var layoutOnMore = ""

    @SerializedName("withHeader")
    @Expose
    var mWithHeader = true

    @SerializedName("withBody")
    @Expose
    var mWithBody = true

    @SerializedName("withTitle")
    @Expose
    @JvmField
    var withTitle = true

    @SerializedName("hasMore")
    @Expose
    open var hasMore = true

    @SerializedName("dynamicField1")
    @Expose
    var mDynamicField1 = ""

    @SerializedName("dynamicField2")
    @Expose
    var mDynamicField2 = ""

    @SerializedName("dynamicField3")
    @Expose
    var mDynamicField3 = ""

    @SerializedName("dynamicField1Row")
    @Expose
    var mDynamicField1Row = 1

    @SerializedName("dynamicField2Row")
    @Expose
    var mDynamicField2Row = 1

    @SerializedName("dynamicField3Row")
    @Expose
    var mDynamicField3Row = 1

    @SerializedName("actionButtonEnable")
    @Expose
    @JvmField
    var actionButtonEnable = false

    @SerializedName("actionButtonText")
    @Expose
    @JvmField
    var actionButtonText = ""

    @SerializedName("actionButtonLink")
    @Expose
    @JvmField
    var actionButtonLink = ""

    @SerializedName("isDirectDataList")
    @Expose
    @JvmField
    var isDirectDataList = false

    @SerializedName("titleTextAlign")
    @Expose
    @JvmField
    var titleTextAlign = TEXT_ALIGN_LEFT

    @SerializedName("imgRounded")
    @Expose
    @JvmField
    var imgRounded = true

    @SerializedName("imgOutline")
    @Expose
    @JvmField
    var imgOutline = false

    @SerializedName("textPosition")
    @Expose
    @JvmField
    var textPosition = INFO_OUTSIDE

    @SerializedName("textAlign")
    @Expose
    @JvmField
    var textAlign = TEXT_ALIGN_CENTER

    @SerializedName("textVertAlign")
    @Expose
    @JvmField
    var textVertAlign = INFO_INSIDE_MIDDLE

    @SerializedName("size")
    @Expose
    @JvmField
    var size = LAYOUT_SIZE_MEDIUM

    @SerializedName("imgOutlineOnMore")
    @Expose
    var imgOutlineOnMore = false

    @SerializedName("imgRoundedOnMore")
    @Expose
    var imgRoundedOnMore = true


    @SerializedName("textAlignOnMore")
    @Expose
    var textAlignOnMore = TEXT_ALIGN_CENTER

    @SerializedName("textPositionOnMore")
    @Expose
    var textPositionOnMore = INFO_OUTSIDE

    @SerializedName("sizeOnMore")
    @Expose
    var sizeOnMore = LAYOUT_SIZE_MEDIUM

    @SerializedName("textVertAlignOnMore")
    @Expose
    var textVertAlignOnMore = INFO_INSIDE_MIDDLE

    @SerializedName("showShadow")
    @Expose
    @JvmField
    var showShadow = false

    @SerializedName("shadowLevel")
    @Expose
    var shadowLevel = "3"

    @SerializedName("imgTopRounded")
    @Expose
    @JvmField
    var imgTopRounded = true

    @SerializedName("imgBottomRounded")
    @Expose
    @JvmField
    var imgBottomRounded = true

    //++++++++++++++
    @SerializedName("showShadowOnMore")
    @Expose
    var showShadowOnMore = false

    @SerializedName("shadowLevelOnMore")
    @Expose
    var shadowLevelOnMore = "3"

    @SerializedName("imgTopRoundedOnMore")
    @Expose
    var imgTopRoundedOnMore = true

    @SerializedName("imgBottomRoundedOnMore")
    @Expose
    var imgBottomRoundedOnMore = true


    fun getTitleTextAlign(): String {
        return titleTextAlign
    }

    fun setTitleTextAlign(titleTextAlign: String) {
        this.titleTextAlign = titleTextAlign
    }

    fun isImgRounded(isOnMorePage: Boolean): Boolean {
        return if (isOnMorePage) imgRoundedOnMore else imgRounded
    }

    fun setImgRounded(imgRounded: Boolean) {
        this.imgRounded = imgRounded
    }

    fun isImgOutline(isOnMorePage: Boolean): Boolean {
        return if (isOnMorePage) imgOutlineOnMore else imgOutline
    }

    fun setImgOutline(imgOutline: Boolean) {
        this.imgOutline = imgOutline
    }

    fun getTextPosition(isOnMorePage: Boolean): String {
        return if (isOnMorePage) textPositionOnMore else textPosition
    }

    fun setTextPosition(textPosition: String) {
        this.textPosition = textPosition
    }

    fun getTextAlign(isOnMorePage: Boolean): String {
        return if (isOnMorePage) textAlignOnMore else textAlign
    }

    fun setTextAlign(textAlign: String) {
        this.textAlign = textAlign
    }

    fun getTextVertAlign(isOnMorePage: Boolean): String {
        return if (isOnMorePage) textVertAlignOnMore else textVertAlign
    }

    fun setTextVertAlign(textVertAlign: String) {
        this.textVertAlign = textVertAlign
    }

    fun getSize(isOnMorePage: Boolean): String {
        return if (isOnMorePage) sizeOnMore else size
    }

    fun setSize(size: String) {
        this.size = size
    }

    fun getLayout(isOnMorePage: Boolean): String {
        return if (isOnMorePage) layoutOnMore else mLayout
    }

    fun setLayout(mLayout: String) {
        this.mLayout = mLayout
    }

    fun getViewType(): String {
        return mViewType
    }

    fun setViewType(viewType: String) {
        mViewType = viewType
    }

    fun getViewTypeOnMore(): String {
        return viewTypeOnMore
    }

    fun setViewTypeOnMore(viewTypeOnMore: String) {
        this.viewTypeOnMore = viewTypeOnMore
    }

    fun setLayoutOnMore(layoutOnMore: String) {
        this.layoutOnMore = layoutOnMore
    }

    fun isWithHeader(): Boolean {
        return mWithHeader
    }

    fun setWithHeader(mWithHeader: Boolean) {
        this.mWithHeader = mWithHeader
    }

    fun isWithBody(): Boolean {
        return mWithBody
    }

    fun setWithBody(mWithBody: Boolean) {
        this.mWithBody = mWithBody
    }

    open fun isHasMore(): Boolean {
        return hasMore
    }

    open fun setHasMore(hasMore: Boolean?) {
        this.hasMore = hasMore!!
    }

    var mData: List<out BaseModel> = ArrayList()

    open fun getData(): List<out BaseModel> {
        return mData
    }

    fun setData(mData: List<out BaseModel>) {
        this.mData = mData
    }

    fun getName(): String {
        return mName
    }

    fun setName(name: String) {
        mName = name
    }

    fun getDesc(): String {
        return mDesc
    }

    fun setDesc(desc: String) {
        mDesc = desc
    }

    fun getParent(): String {
        return mParent
    }

    fun setParent(parent: String) {
        mParent = parent
    }

    fun getDataFilter(): DataFilter {
        return mDataFilter
    }

    fun setDataFilter(dataFilter: DataFilter) {
        mDataFilter = dataFilter
    }

    //    public Integer getOrder() {
    //        return mOrder;
    //    }
    //
    //    public void setOrder(Integer order) {
    //        mOrder = order;
    //    }

    fun isActionButtonEnable(): Boolean {
        return actionButtonEnable
    }

    fun setActionButtonEnable(actionButtonEnable: Boolean) {
        this.actionButtonEnable = actionButtonEnable
    }

    fun getActionButtonText(): String {
        return actionButtonText
    }

    fun setActionButtonText(actionButtonText: String) {
        this.actionButtonText = actionButtonText
    }

    fun getActionButtonLink(): String {
        return actionButtonLink
    }

    fun setActionButtonLink(actionButtonLink: String) {
        this.actionButtonLink = actionButtonLink
    }

    override fun getId(): String {
        return mId
    }

    fun setId(id: String) {
        mId = id
    }

    fun getCurationPageId(): String {
        return mCurationPageId
    }

    fun setCurationPageId(curationPageId: String) {
        mCurationPageId = curationPageId
    }

    fun getChilds(): List<CurationPagesMetadata>? {
        return mChilds
    }

    fun setChilds(childs: List<CurationPagesMetadata>?) {
        mChilds = childs
    }

    fun isDirectDataList(): Boolean {
        return isDirectDataList
    }

    fun setDirectDataList(directDataList: Boolean) {
        isDirectDataList = directDataList
    }

    fun isWithTitle(): Boolean {
        return withTitle
    }

    fun setWithTitle(withTitle: Boolean) {
        this.withTitle = withTitle
    }


    fun getmDynamicField1(): String {
        return mDynamicField1
    }

    fun setmDynamicField1(mDynamicField1: String) {
        this.mDynamicField1 = mDynamicField1
    }

    fun getmDynamicField2(): String {
        return mDynamicField2
    }

    fun setmDynamicField2(mDynamicField2: String) {
        this.mDynamicField2 = mDynamicField2
    }

    fun getmDynamicField3(): String {
        return mDynamicField3
    }

    fun setmDynamicField3(mDynamicField3: String) {
        this.mDynamicField3 = mDynamicField3
    }

    fun getmDynamicField1Row(): Int {
        return mDynamicField1Row
    }

    fun setmDynamicField1Row(mDynamicField1Row: Int) {
        this.mDynamicField1Row = mDynamicField1Row
    }

    fun getmDynamicField2Row(): Int {
        return mDynamicField2Row
    }

    fun setmDynamicField2Row(mDynamicField2Row: Int) {
        this.mDynamicField2Row = mDynamicField2Row
    }

    fun getmDynamicField3Row(): Int {
        return mDynamicField3Row
    }

    fun setmDynamicField3Row(mDynamicField3Row: Int) {
        this.mDynamicField3Row = mDynamicField3Row
    }

    fun isShowShadow(isOnMorePage: Boolean): Boolean {
        return if (isOnMorePage) showShadowOnMore else showShadow
    }

    fun setShowShadow(showShadow: Boolean) {
        this.showShadow = showShadow
    }

    fun getShadowLevel(isOnMorePage: Boolean): Int {
        return if (isOnMorePage) shadowLevelOnMore.toInt() else shadowLevel.toInt()
    }

    fun setShadowLevel(shadowLevel: Int) {
        this.shadowLevel = shadowLevel.toString()
    }

    fun isImgTopRounded(isOnMorePage: Boolean): Boolean {
        return if (isOnMorePage) imgTopRoundedOnMore else imgTopRounded
    }

    fun setImgTopRounded(imgTopRounded: Boolean) {
        this.imgTopRounded = imgTopRounded
    }

    fun isImgBottomRounded(isOnMorePage: Boolean): Boolean {
        return if (isOnMorePage) imgBottomRoundedOnMore else imgBottomRounded
    }

    fun setImgBottomRounded(imgBottomRounded: Boolean) {
        this.imgBottomRounded = imgBottomRounded
    }
}