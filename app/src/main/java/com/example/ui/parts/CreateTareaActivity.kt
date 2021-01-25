package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.models.Tarea
import com.example.api_rest.data.responses.TareaResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_create_tarea.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateTareaActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val con = this;
    private var id_pro = ""
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tarea)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        sVTarea_Create.setOnQueryTextListener(this)

        btnTarea_Create.setOnClickListener {
            val descripcion = eTTarea_create.text.toString()

            if(descripcion.isNotEmpty())
                create(descripcion)
            else{
                val toast = Toast.makeText(this, "Llene todos los campos antes de continuar", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        id_pro = query
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        id_pro = newText
        return true
    }

    private fun create(descripcion: String) {
        apiClient.getApiService(this).create_tarea(id_pro.toInt(), Tarea(descripcion = descripcion))
            .enqueue(object : Callback<TareaResponse> {
                override fun onFailure(call: Call<TareaResponse>, t: Throwable) {
                    val toast = Toast.makeText(con, "Error" , Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<TareaResponse>,response: Response<TareaResponse>) {
                    val tar = response.body()

                    if(response.message() == "OK"){
                        eTTarea_create.setText("")
                        val toast = Toast.makeText(con, "Se añadio una nueva tarea" , Toast.LENGTH_LONG).show()
                    }

                }

            })
    }


}