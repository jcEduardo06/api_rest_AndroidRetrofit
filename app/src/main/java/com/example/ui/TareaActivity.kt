package com.example.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.api_rest.R
import com.example.ui.parts.*
import kotlinx.android.synthetic.main.activity_tarea.*

class TareaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarea)

        button_index.setOnClickListener {
            val intent: Intent = Intent(this, IndexTareaActivity::class.java)
            startActivity(intent)
        }
        button_create.setOnClickListener {
            val intent: Intent = Intent(this, CreateTareaActivity::class.java)
            startActivity(intent)
        }
        button_update.setOnClickListener {
            val intent: Intent = Intent(this, UpdateTareaActivity::class.java)
            startActivity(intent)
        }
        button_delete.setOnClickListener {
            val intent: Intent = Intent(this, DeleteTareaActivity::class.java)
            startActivity(intent)
        }
    }
}