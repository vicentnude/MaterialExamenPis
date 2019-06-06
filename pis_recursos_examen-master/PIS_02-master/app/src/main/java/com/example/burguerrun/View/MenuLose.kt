package com.example.burguerrun.View


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.burguerrun.R

class MenuLose : AppCompatActivity() {

    lateinit var changeActivityButton: Button;
    lateinit var btnMenuPrincipal: Button;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lose)

        this.changeActivityButton = findViewById<Button>(R.id.btnTornarLose)
        this.btnMenuPrincipal = findViewById<Button>(R.id.btnMenuLose)

        changeActivityButton.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnMenuPrincipal.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



    override fun onBackPressed() {
        finish()
    }
}

