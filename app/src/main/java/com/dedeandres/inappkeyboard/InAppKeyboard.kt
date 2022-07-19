package com.dedeandres.inappkeyboard

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class InAppKeyboard: ConstraintLayout, View.OnClickListener{
    private var keyValues = SparseArray<String>()
    private lateinit var inputConnection: InputConnection
    private lateinit var actionEnter: () -> Unit
    private lateinit var buttonEnter: TextView


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        Log.d("CustomKeyboard", "init CustomKeyboard")

        LayoutInflater.from(context).inflate(R.layout.layout_keyboard, this, true)

        keyValues.put(R.id.tv_0, "0")
        keyValues.put(R.id.tv_1, "1")
        keyValues.put(R.id.tv_2, "2")
        keyValues.put(R.id.tv_3, "3")
        keyValues.put(R.id.tv_4, "4")
        keyValues.put(R.id.tv_5, "5")
        keyValues.put(R.id.tv_6, "6")
        keyValues.put(R.id.tv_7, "7")
        keyValues.put(R.id.tv_8, "8")
        keyValues.put(R.id.tv_9, "9")

        val button1 = findViewById<TextView>(R.id.tv_1)
        val button2 = findViewById<TextView>(R.id.tv_2)
        val button3 = findViewById<TextView>(R.id.tv_3)
        val button4 = findViewById<TextView>(R.id.tv_4)
        val button5 = findViewById<TextView>(R.id.tv_5)
        val button6 = findViewById<TextView>(R.id.tv_6)
        val button7 = findViewById<TextView>(R.id.tv_7)
        val button8 = findViewById<TextView>(R.id.tv_8)
        val button9 = findViewById<TextView>(R.id.tv_9)
        val button0 = findViewById<TextView>(R.id.tv_0)
        val buttonDelete = findViewById<TextView>(R.id.tv_delete)
        buttonEnter = findViewById(R.id.tv_enter)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button0.setOnClickListener(this)
        buttonEnter.setOnClickListener(this)
        buttonDelete.setOnClickListener(this)
    }

    fun setInputConnection(ic: InputConnection) {
        inputConnection = ic
    }

    fun setActionEnter(actionConfirm: () -> Unit) {
        this.actionEnter = actionConfirm
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_delete -> {
                Log.d("CustomKeyboard", "delete clicked")

                val selectedText = inputConnection.getSelectedText(0)

                if(TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0)
                } else {
                    inputConnection.commitText("", 1)
                }
            }

            R.id.tv_enter -> {
                Log.d("CustomKeyboard", "enter clicked")
                actionEnter.invoke()
            }

            else -> {
                v?.let {
                    val value = keyValues.get(v.id)
                    Log.d("CustomKeyboard", "$value clicked")
                    inputConnection.commitText(value, 1)
                }
            }
        }
    }
}