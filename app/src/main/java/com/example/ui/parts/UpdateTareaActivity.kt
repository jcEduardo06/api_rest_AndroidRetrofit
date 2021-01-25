package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.models.Tarea_up
import com.example.api_rest.data.responses.TareaResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_delete_tarea.*
import kotlinx.android.synthetic.main.activity_update_tarea.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateTareaActivity : AppCompatActivity() {

    private val con = this;
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_tarea)

        swTarea_update_complete.textOff = "No"
        swTarea_update_complete.textOn = "Sí"
        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        btnTarea_Update.setOnClickListener{
            val et_id = eTTarea_update_id.text.toString()
            val et_desc = eTTarea_update_desc.text.toString()
            val swComp = swTarea_update_complete.isChecked
            if(et_id.isNotEmpty() and et_desc.isNotEmpty())
                update_tarea(et_id,et_desc,swComp)
            else{
                val toast = Toast.makeText(this,"Llene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun update_tarea(etId: String, etDesc: String, swCom: Boolean) {
        apiClient.getApiService(this).update_tarea(etId.toInt(), Tarea_up(etDesc,swCom))
            .enqueue(object : Callback<TareaResponse> {
                override fun onFailure(call: Call<TareaResponse>, t: Throwable) {
                    val toast = Toast.makeText(con,"Error con el servidor", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<TareaResponse>,response: Response<TareaResponse>) {
                    if(response.message() == "OK"){
                        val tar = response.body()
                        val toast = Toast.makeText(con,"Se ha actualizado su tarea con id: " + tar?.id, Toast.LENGTH_LONG).show()
                        eTTarea_update_id.setText("")
                        eTTarea_update_desc.setText("")
                    }else{
                        val toast = Toast.makeText(con,"Se ha producido un error", Toast.LENGTH_SHORT).show()
                    }

                }

            })

    }


}