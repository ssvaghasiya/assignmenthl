package com.assignmenthyperlink.ui.home.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MemeData {

    @SerializedName("status_code")
    @Expose
    var statusCode = 0

    constructor(statusCode: Int) {
        this.statusCode = statusCode
    }

    @SerializedName("success")
    @Expose
    var success = false

    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {
        @SerializedName("memes")
        @Expose
        var memes: List<Meme>? = null
    }

    class Meme {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("url")
        @Expose
        var url: String? = null

        @SerializedName("width")
        @Expose
        var width = 0

        @SerializedName("height")
        @Expose
        var height = 0

        @SerializedName("box_count")
        @Expose
        var boxCount = 0
    }
}