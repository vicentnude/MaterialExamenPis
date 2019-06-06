package com.example.burguerrun.View


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.burguerrun.R


class MenuGuanyat : AppCompatActivity() {
    lateinit var changeActivityButton: Button;
    lateinit var btnMenuPrincipal: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_guanyat)

        this.changeActivityButton = findViewById<Button>(R.id.btnTornar)
        this.btnMenuPrincipal = findViewById<Button>(R.id.btnMenu)
        val intent = Intent(this, LvlActivity::class.java)

        changeActivityButton.setOnClickListener {
            //intent.putExtra(LEVEL,"Nivell 1")
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnMenuPrincipal.setOnClickListener {
            //intent.putExtra(LEVEL,"Nivell 1")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onBackPressed() {
        finish()
    }
}
