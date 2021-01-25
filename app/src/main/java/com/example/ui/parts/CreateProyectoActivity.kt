package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.models.Proyecto
import com.example.api_rest.data.responses.ProyectoResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_create_proyecto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProyectoActivity : AppCompatActivity() {

    private val con = this;
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_proyecto)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        btnProyecto_Create.setOnClickListener {
            val nombre_new = eTProyect_create.text.toString()
            if(nombre_new.isNotEmpty())
                create(nombre_new)
        }
    }

    private fun create(nombre_new: String){
        apiClient.getApiService(this).create_proyecto(Proyecto( nombre = nombre_new))
            .enqueue(object : Callback<ProyectoResponse> {
                override fun onFailure(call: Call<ProyectoResponse>, t: Throwable) {
                    val toast = Toast.makeText(con, "Error con el servidor", Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(call: Call<ProyectoResponse>,response: Response<ProyectoResponse>) {
                    val pro = response.body()
                    if(pro?.nombre != null){
                        val toast = Toast.makeText(con, "Se ha agregado el proyecto: " + pro.nombre + " satisfactoriamente!", Toast.LENGTH_LONG).show()
                        eTProyect_create.setText("")
                    }else{
                        val toast = Toast.makeText(con, "Ha ocurrido un problema", Toast.LENGTH_LONG).show()
                    }
                }
            })
    }
}