package com.example.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.api_rest.R
import com.example.api_rest.data.ApiClient
import com.example.api_rest.data.requests.LoginRequest
import com.example.api_rest.data.responses.LoginResponse
import com.example.utils.SessionManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient : ApiClient
    val con = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        button_login.setOnClickListener{
            val email = editTextTextPersonName.text.toString()
            val password = editTextTextPassword.text.toString()
            if (email != null && password != null)
                logueo(email,password)
            else {
                val toast = Toast.makeText(this, "Agregue datos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
        button_register.setOnClickListener {
            val email = editTextTextPersonName.text.toString()
            val password = editTextTextPassword.text.toString()
            if (email != null && password != null)
                register(email,password)
            else {
                val toast = Toast.makeText(this, "Agregue datos para continuar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun logueo(email_s: String, password_s: String){
        apiClient.getApiService(this).login(LoginRequest(email = email_s, password = password_s))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable){
                    val toast = Toast.makeText(con,"Error al iniciar sesión",Toast.LENGTH_SHORT).show()
                    Log.i("LoginActivity",t.toString())
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()

                    if (loginResponse?.message == null && loginResponse?.token != null){
                        sessionManager.saveToken(loginResponse.token)
                        open_menu()
                    } else {
                        val toast = Toast.makeText(con, loginResponse?.message, Toast.LENGTH_LONG).show()

                    }
                }
            })
    }
    fun register(email_s: String, password_s: String) {
        apiClient.getApiService(this).register(LoginRequest(email = email_s, password = password_s))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    val toast = Toast.makeText(con, "Error patrón", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse = response.body()

                    if (loginResponse?.message == null && loginResponse?.token != null) {
                        sessionManager.saveToken(loginResponse.token)
                        open_menu()
                    } else {
                        val toast = Toast.makeText(con, "Al parecer este usuario ya existe", Toast.LENGTH_LONG).show()
                    }
                }
            })
    }

    fun open_menu(){
        Log.i("LoginActivity",this.toString())
        val intent: Intent = Intent(con, MenuActivity::class.java)
        startActivity(intent)
    }

}