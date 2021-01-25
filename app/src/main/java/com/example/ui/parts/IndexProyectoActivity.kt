package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.responses.ProyectoResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_index_proyecto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndexProyectoActivity : AppCompatActivity() {

   private val con = this;
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index_proyecto)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        listProyecto()
    }

    private fun listProyecto(){
        apiClient.getApiService(this).index_proyecto()
            .enqueue(object: Callback<List<ProyectoResponse>>{
                override fun onFailure(call: Call<List<ProyectoResponse>>, t: Throwable) {
                    val toast = Toast.makeText(con, "Error, no se pudieron traer los datos" , Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<ProyectoResponse>>,response: Response<List<ProyectoResponse>>) {
                    val proyectoRes = response.body()
                    //val toast = Toast.makeText(con, proyectoRes?.get(0)?.id.toString() , Toast.LENGTH_SHORT).show()
                    var aux = ""
                    proyectoRes?.forEach {
                        aux += it.id
                        aux += " - "
                        aux += it.nombre
                        aux += "\n"
                    }
                    tVDatos.setText(aux)
                }

            })
    }
}