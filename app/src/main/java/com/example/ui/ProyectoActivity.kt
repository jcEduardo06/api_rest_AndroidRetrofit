package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.api_rest.R
import com.example.ui.parts.CreateProyectoActivity
import com.example.ui.parts.DeleteProyectoActivity
import com.example.ui.parts.IndexProyectoActivity
import com.example.ui.parts.UpdateProyectoActivity
import kotlinx.android.synthetic.main.activity_proyecto.*

class ProyectoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proyecto)

        button_index.setOnClickListener {
            val intent: Intent = Intent(this, IndexProyectoActivity::class.java)
            startActivity(intent)
        }
        button_create.setOnClickListener {
            val intent: Intent = Intent(this, CreateProyectoActivity::class.java)
            startActivity(intent)
        }
        button_update.setOnClickListener {
            val intent: Intent = Intent(this, UpdateProyectoActivity::class.java)
            startActivity(intent)
        }
        button_delete.setOnClickListener {
            val intent: Intent = Intent(this, DeleteProyectoActivity::class.java)
            startActivity(intent)
        }
    }
}