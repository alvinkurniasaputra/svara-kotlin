package com.zamrud.radio.mobile.app.svara.apiclient.model.document

import com.zamrud.radio.mobile.app.svara.apiclient.model.entity.Account

class ReceiversResponseModel {
    @JvmField
    var id: String? = null
    @JvmField
    var documentId: String? = null
    @JvmField
    var accountId: String? = null
    @JvmField
    var account: Account? = null

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getDocumentId(): String? {
        return documentId
    }

    fun setDocumentId(documentId: String?) {
        this.documentId = documentId
    }

    fun getAccountId(): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }

    fun getAccount(): Account? {
        return account
    }

    fun setAccount(account: Account?) {
        this.account = account
    }
}
