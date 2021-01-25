package com.example.api_rest.data

import com.example.api_rest.data.models.Proyecto
import com.example.api_rest.data.models.Tarea_up
import com.example.api_rest.data.models.Tarea
import com.example.utils.Constants
import com.example.api_rest.data.requests.LoginRequest
import com.example.api_rest.data.responses.LoginResponse
import com.example.api_rest.data.responses.ProyectoResponse
import com.example.api_rest.data.responses.TareaResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

@POST(Constants.LOGIN_URL)
fun login(@Body request: LoginRequest) : Call<LoginResponse>

@POST(Constants.REGISTER_URL)
fun register(@Body request: LoginRequest) : Call<LoginResponse>

@GET(Constants.PROYECTO_URL)
fun index_proyecto() : Call<List<ProyectoResponse>>

@POST(Constants.PROYECTO_URL)
fun create_proyecto(@Body request: Proyecto) : Call<ProyectoResponse>

@DELETE(Constants.PROYECTO_DELETE_URL)
fun delete_proyecto(@Path("id") id: Int): Call<ProyectoResponse>

@PATCH(Constants.PROYECTO_UPDATE_URL)
fun update_proyecto(@Path("id") id: Int, @Body request: Proyecto) : Call<ProyectoResponse>

@GET(Constants.TAREA_URL)
fun index_tarea(@Path ("id") id: Int): Call<List<TareaResponse>>

@POST(Constants.TAREA_URL)
fun create_tarea(@Path("id")id: Int, @Body reques: Tarea): Call<TareaResponse>

@DELETE(Constants.TAREA_DELETE_URL)
fun delete_tarea(@Path("id") id: Int ): Call<TareaResponse>

@PATCH(Constants.TAREA_DELETE_URL)
fun update_tarea(@Path("id") id: Int, @Body request: Tarea_up): Call<TareaResponse>
}