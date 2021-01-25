package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.api_rest.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        button_proyectos.setOnClickListener {
            val intent:Intent = Intent(this, ProyectoActivity::class.java)
            startActivity(intent)
        }

        button_tareas.setOnClickListener {
            val intent:Intent = Intent(this, TareaActivity::class.java )
            startActivity(intent)
        }
    }


}