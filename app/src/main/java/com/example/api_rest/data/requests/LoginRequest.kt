package com.example.api_rest.data.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String
)