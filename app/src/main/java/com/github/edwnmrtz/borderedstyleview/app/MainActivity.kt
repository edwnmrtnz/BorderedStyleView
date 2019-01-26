package com.github.edwnmrtz.borderedstyleview.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import android.view.View

import com.github.edwnmrtz.borderedstyleedittext.NormalBorderedStyleEditText
import com.github.edwnmrtz.borderedstyleedittext.PasswordBorderedStyleEditText

class MainActivity : AppCompatActivity() {

    private lateinit var btnReplace : AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initFragment()

        btnReplace = findViewById(R.id.btnReplace)

        btnReplace.setOnClickListener {
            val fragment = FragmentTwo()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flContainer, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initFragment() {
        val fragment = FragmentOne()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flContainer, fragment)
        fragmentTransaction.commit()
    }
}
