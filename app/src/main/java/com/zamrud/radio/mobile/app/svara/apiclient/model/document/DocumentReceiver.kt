package com.zamrud.radio.mobile.app.svara.apiclient.model.document

class DocumentReceiver private constructor(@JvmField var accountId: String?) {

    companion object {
        fun init(accountId: String?): DocumentReceiver {
            return DocumentReceiver(accountId)
        }
    }

    fun getAccountId(): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }
}