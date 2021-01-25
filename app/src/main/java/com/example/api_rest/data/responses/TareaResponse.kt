package com.example.api_rest.data.responses

import com.google.gson.annotations.SerializedName

data class TareaResponse (
    @SerializedName("id")
    var id: Int,

    @SerializedName("proyecto_id")
    var proyecto_id: Int,

    @SerializedName("descripcion")
    var descripcion: String,

    @SerializedName("completada")
    var completada: Boolean


)