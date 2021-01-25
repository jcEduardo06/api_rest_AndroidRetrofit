package com.example.api_rest.data.models

import com.google.gson.annotations.SerializedName

data class Tarea_up(
    @SerializedName("descripcion")
    var descripcion: String,

    @SerializedName("completada")
    var completada: Boolean



)