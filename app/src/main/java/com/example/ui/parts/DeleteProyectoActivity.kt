package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.responses.ProyectoResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_create_proyecto.*
import kotlinx.android.synthetic.main.activity_delete_proyecto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteProyectoActivity : AppCompatActivity() {

    private val con = this;
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_proyecto)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        btnProyecto_delete.setOnClickListener {
            val id_proyecto = eTProyect_delete.text.toString()

            if(id_proyecto.isNotBlank())
                delete(id_proyecto.toInt())
        }
    }

    private fun delete(idProyecto: Int) {
        apiClient.getApiService(this).delete_proyecto(idProyecto)
            .enqueue(object : Callback<ProyectoResponse> {
                override fun onFailure(call: Call<ProyectoResponse>, t: Throwable) {
                    val toast = Toast.makeText(con, "Error con el servidor", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ProyectoResponse>,response: Response<ProyectoResponse>) {
                    val pro = response.body()
                    if(pro?.nombre != null){
                        val toast = Toast.makeText(con, "Se ha eliminado el proyecto: " + pro.nombre, Toast.LENGTH_LONG).show()
                        eTProyect_delete.setText("")
                    }else{
                        val toast = Toast.makeText(con, "Ha ocurrido un problema", Toast.LENGTH_LONG).show()
                    }
                }

            })
    }
}