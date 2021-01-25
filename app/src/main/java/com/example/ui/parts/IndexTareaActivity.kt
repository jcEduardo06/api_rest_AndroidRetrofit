package com.example.ui.parts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.responses.TareaResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_index_tarea.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IndexTareaActivity : AppCompatActivity(), View.OnFocusChangeListener,
    SearchView.OnQueryTextListener {

    private val con = this;
    var id_pro = ""
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index_tarea)
        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        sVTarea_index.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        id_pro = newText
        if (id_pro.isNotEmpty())
            listTarea()
        return true
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {}

    private fun listTarea() {
        apiClient.getApiService(this).index_tarea(id_pro.toInt())
            .enqueue(object : Callback<List<TareaResponse>> {
                override fun onFailure(call: Call<List<TareaResponse>>, t: Throwable) {
                    val toast = Toast.makeText(
                        con,
                        "Error, no se pudieron traer los datos",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<List<TareaResponse>>,
                    response: Response<List<TareaResponse>>
                ) {
                    var aux = ""
                    val tar = response.body()

                    if(response.message() == "OK"){
                        tar?.forEach {
                            val aux2 =
                                "id: " + it.id + "\n" + "descripción: " + it.descripcion + "\n" + "completada: " + changeComplete(it.completada) + "\n"
                            aux += aux2
                        }
                        if(aux == "")
                            aux = "Agregue alguna tarea para comenzar"

                    }
                    if(aux == "")
                        aux = "No hay ningun proyecto con ese número"

                    tVTarea_datos.setText(aux)

                }

            })
    }

    private fun changeComplete(a: Boolean): String {
        return if (a) "sí" else "no"
    }
}