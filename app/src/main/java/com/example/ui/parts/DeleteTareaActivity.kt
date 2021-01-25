package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.responses.TareaResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_delete_tarea.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteTareaActivity : AppCompatActivity() {

    private val con = this;
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_tarea)


        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        btnTarea_Delete.setOnClickListener {
            val id_tar = eTTarea_delete_tar.text.toString()

            if( id_tar.isNotEmpty())
                delete(id_tar)
        }


    }

    private fun delete(idTar: String) {
        apiClient.getApiService(this).delete_tarea(id = idTar.toInt())
            .enqueue(object : Callback<TareaResponse> {
                override fun onFailure(call: Call<TareaResponse>, t: Throwable) {
                    val toast = Toast.makeText(con, "Error" , Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<TareaResponse>,response: Response<TareaResponse>) {
                    val tar = response.body()

                    if(response.message() == "OK"){
                        val toast = Toast.makeText(con, "Se ha eliminado la tarea con id: " + tar?.id , Toast.LENGTH_LONG).show()
                    }else{
                        val toast = Toast.makeText(con, "Error" , Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }
}