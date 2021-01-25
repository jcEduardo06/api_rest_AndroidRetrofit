package com.example.api_rest.data.responses

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ProyectoResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("user_id")
    var user_id: Int,

    @SerializedName("nombre")
    var nombre: String


)