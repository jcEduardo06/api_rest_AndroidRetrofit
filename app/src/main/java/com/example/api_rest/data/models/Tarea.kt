package com.example.api_rest.data.models

import com.google.gson.annotations.SerializedName

data class Tarea(

    @SerializedName("descripcion")
    var descripcion: String
)