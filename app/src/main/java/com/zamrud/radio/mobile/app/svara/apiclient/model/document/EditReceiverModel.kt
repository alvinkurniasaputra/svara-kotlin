package com.zamrud.radio.mobile.app.svara.apiclient.model.document

class EditReceiverModel(@JvmField var accountId: String?, @JvmField var documentId: String?){

    fun getAccountId(a: String): String? {
        return accountId
    }

    fun setAccountId(accountId: String?) {
        this.accountId = accountId
    }

    fun getDocumentId(): String? {
        return documentId
    }

    fun setDocumentId(documentId: String?) {
        this.documentId = documentId
    }

}