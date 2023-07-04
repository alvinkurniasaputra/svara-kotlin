package com.zamrud.radio.mobile.app.svara.apiclient.model.entity

import com.zamrud.radio.mobile.app.svara.apiclient.ModelContract
import com.zamrud.radio.mobile.app.svara.apiclient.model.ModelWithAction

/**
 * Created by Hinata on 1/19/2018.
 */

class TtxModel : ModelWithAction() {
    @JvmField
    var description: String? = ""
    var tXId: String? = ""
    @JvmField
    var logo: String? = ""
    @JvmField
    var appId: String? = ""
    var tId: String? = ""
    @JvmField
    var background: String? = ""
    @JvmField
    var color: String? = ""
    @JvmField
    var showChildOnPreference = false
    @JvmField
    var parent: String? = ""
    @JvmField
    var child: ArrayList<String>? = ArrayList()
    @JvmField
    var order = 0.01
    @JvmField
    var contentType: String? = ""
    @JvmField
    var id: String? = ""
    @JvmField
    var t: Term? = Term()
    @JvmField
    var tx: Taxonomy? = Taxonomy()
    @JvmField
    var images: Images? = Images()
    @JvmField
    var children: List<TtxModel>? = ArrayList()

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun gettXId(): String? {
        return tXId
    }

    fun settXId(tXId: String?) {
        this.tXId = tXId
    }

    fun getLogo(): String? {
        return logo
    }

    fun setLogo(logo: String?) {
        this.logo = logo
    }

    fun getAppId(): String? {
        return appId
    }

    fun setAppId(appId: String?) {
        this.appId = appId
    }

    fun gettId(): String? {
        return tId
    }

    fun settId(tId: String?) {
        this.tId = tId
    }

    fun getBackground(): String? {
        return background
    }

    fun setBackground(background: String?) {
        this.background = background
    }

    fun getColor(): String? {
        return color
    }

    fun setColor(color: String?) {
        this.color = color
    }

    fun isShowChildOnPreference(): Boolean {
        return showChildOnPreference
    }

    fun setShowChildOnPreference(showChildOnPreference: Boolean) {
        this.showChildOnPreference = showChildOnPreference
    }

    fun getParent(): String? {
        return parent
    }

    fun setParent(parent: String?) {
        this.parent = parent
    }

    fun getChild(): ArrayList<String>? {
        return child
    }

    fun setChild(child: ArrayList<String>?) {
        this.child = child
    }

    fun getOrder(): Double {
        return order
    }

    fun setOrder(order: Double) {
        this.order = order
    }

    fun getContentType(): String? {
        return contentType
    }

    override fun getContentTypeString(): String {
        return ModelContract.getSearchString(ModelContract.CATEGORY_TTX)
    }

    fun setContentType(contentType: String?) {
        this.contentType = contentType
    }

    override fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getT(): TtxModel.Term? {
        return t
    }

    fun setT(t: TtxModel.Term?) {
        this.t = t
    }

    fun getTx(): Taxonomy? {
        return tx
    }

    fun setTx(tx: Taxonomy?) {
        this.tx = tx
    }

    fun setImages(images: Images?) {
        this.images = images
    }

    fun getChildren(): List<TtxModel>? {
        return children
    }

    fun setChildren(children: List<TtxModel>?) {
        this.children = children
    }

    override fun getPlaceholder(): String? {
        return images!!.getPlaceholder()
    }

    override fun getImages(): Images? {
        return images
    }

    override fun getCoverImageMedium(): String? {
        return images!!.getImage300()
    }

    class Term {
        @JvmField
        var name = ""
        @JvmField
        var slug = ""
        @JvmField
        var id = ""
        @JvmField
        var showChildOnPreference = false

        fun getName(): String {
            return name
        }

        fun setName(name: String) {
            this.name = name
        }

        fun getSlug(): String {
            return slug
        }

        fun setSlug(slug: String) {
            this.slug = slug
        }

        fun getId(): String {
            return id
        }

        fun setId(id: String) {
            this.id = id
        }

        fun isShowChildOnPreference(): Boolean {
            return showChildOnPreference
        }

        fun setShowChildOnPreference(showChildOnPreference: Boolean) {
            this.showChildOnPreference = showChildOnPreference
        }
    }

    override fun getLines(): ArrayList<CharSequence?> {
        val out: ArrayList<CharSequence?> = ArrayList(3)
        out.add(t!!.name)
        out.add("")
        out.add("")
        return out
    }

    class Taxonomy {
        @JvmField
        var name = ""
        @JvmField
        var slug = ""
        @JvmField
        var id = ""

        fun getName(): String {
            return name
        }

        fun setName(name: String) {
            this.name = name
        }

        fun getSlug(): String {
            return slug
        }

        fun setSlug(slug: String) {
            this.slug = slug
        }

        fun getId(): String {
            return id
        }

        fun setId(id: String) {
            this.id = id
        }
    }

}