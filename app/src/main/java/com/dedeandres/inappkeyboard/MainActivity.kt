package com.dedeandres.inappkeyboard

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import com.dedeandres.inappkeyboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initKeyboard()
    }

    private fun initKeyboard() {
        if(Build.VERSION.SDK_INT >=21) {
            binding.etInput.showSoftInputOnFocus = false
        } else if(Build.VERSION.SDK_INT >= 11) {
            binding.etInput.setRawInputType(InputType.TYPE_CLASS_TEXT)
            binding.etInput.setTextIsSelectable(true)
        }else{
            binding.etInput.setRawInputType(InputType.TYPE_NULL)
            binding.etInput.isFocusable = true
        }

        val inputConnection = binding.etInput.onCreateInputConnection(EditorInfo())
        binding.keyboard.setInputConnection(inputConnection!!)
        binding.keyboard.setActionEnter {
            //doing something after click enter button
        }

    }
}