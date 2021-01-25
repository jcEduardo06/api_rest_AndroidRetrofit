package com.example.api_rest.data.models

import com.google.gson.annotations.SerializedName

data class Proyecto(

    @SerializedName("nombre")
    var nombre: String
)