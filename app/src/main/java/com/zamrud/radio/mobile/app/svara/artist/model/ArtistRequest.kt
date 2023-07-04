package com.zamrud.radio.mobile.app.svara.artist.model

class ArtistRequest {
    private var name: String? = null
    private  var coverArtS: String? = null
    private  var description: String? = null
    private  var genre: String? = null
    private  var country: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCoverArtS(): String? {
        return coverArtS
    }

    fun setCoverArtS(coverArtS: String) {
        this.coverArtS = coverArtS
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getGenre(): String? {
        return genre
    }

    fun setGenre(genre: String) {
        this.genre = genre
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String) {
        this.country = country
    }
}