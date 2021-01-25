package com.example.api_rest.data.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
@SerializedName("token")
var token: String,

@SerializedName("field")
var field: String,

@SerializedName("message")
var message: String
)