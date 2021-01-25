package com.example.api_rest.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserResponse(
@SerializedName("id")
var id:String,

@SerializedName("email")
var email: String,

@SerializedName("username")
var username: String,

@SerializedName("created_at")
var created_at: Date,

@SerializedName("updated_at")
var updated_at: Date
)