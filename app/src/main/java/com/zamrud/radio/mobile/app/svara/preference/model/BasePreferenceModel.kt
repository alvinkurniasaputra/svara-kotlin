package com.zamrud.radio.mobile.app.svara.preference.model

import android.content.Context
import com.zamrud.radio.mobile.app.svara.preference.PreferenceCallback

/**
 * Created by Hinata on 1/19/2018.
 */

abstract class BasePreferenceModel {

    companion object {
        var parentShowItemInit = 6
    }

    abstract fun getTitle(): String?
    abstract fun getColor(): String?
    abstract fun getImage(): String?
    abstract fun getChildren(): List<BasePreferenceModel>
    abstract fun hasMore(): Boolean
    abstract fun showMore(): List<BasePreferenceModel>
    abstract fun onItemClick(context: Context, callback: PreferenceCallback)

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