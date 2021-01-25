package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.models.Proyecto
import com.example.api_rest.data.responses.ProyectoResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_update_proyecto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProyectoActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val con = this;
    private var id_pro = ""
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_proyecto)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        sVProyect_update.setOnQueryTextListener(this)

        btnProyecto_update.setOnClickListener {
            val nombre_pro = eTProyect_update.text.toString()

            if(nombre_pro.isNotEmpty() and id_pro.isNotBlank()){
                update(nombre_pro)
            }
        }
    }

    private fun update(nombrePro: String) {
        apiClient.getApiService(this).update_proyecto(id_pro.toInt(), Proyecto(nombre = nombrePro))
            .enqueue(object : Callback<ProyectoResponse> {
                override fun onFailure(call: Call<ProyectoResponse>, t: Throwable) {
                    val toast = Toast.makeText(con, "Error con el servidor", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ProyectoResponse>,response: Response<ProyectoResponse>) {
                    val pro = response.body()


                    if(pro?.nombre != null){
                        val toast = Toast.makeText(con, "Se ha actualizado el proyecto con id: " + pro.id , Toast.LENGTH_LONG).show()
                    }else {
                        val toast = Toast.makeText(con, response.message(), Toast.LENGTH_LONG).show()
                    }
                }

            })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        id_pro = newText
        return true
    }
}