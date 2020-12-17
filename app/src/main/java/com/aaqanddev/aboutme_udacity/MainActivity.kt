package com.aaqanddev.aboutme_udacity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.aaqanddev.aboutme_udacity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val myName = MyName("Aaron A Quaday")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        val img = ContextCompat.getDrawable(this, R.drawable.ic_smily_plus)
        img?.setBounds(0, 0, 124, 124)
        binding.bioText.setCompoundDrawables(img, null, img, null)

        binding.doneButton.setOnClickListener {
            addNickname()
        }
        binding.nicknameTv.setOnClickListener {
            displayViews()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.nicknameEt, 0)

        }
    }

    private fun addNickname() {
        val nick = binding.nicknameEt.text.toString()
        if (nick != "") {
            myName.nickname = nick
            displayViews()
        } else {
            Toast.makeText(this, "Please enter a nickname", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayViews(){
        binding.invalidateAll()
        val viewsList = listOf(binding.nicknameTv, binding.nicknameEt, binding.doneButton)
        for (view in viewsList){
            toggleVisibility(view)
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.nicknameEt.windowToken, 0)
    }

    private fun toggleVisibility(view: View){
        view.visibility = when (view.visibility){
            View.VISIBLE -> View.GONE
            else -> View.VISIBLE
        }
    }
}