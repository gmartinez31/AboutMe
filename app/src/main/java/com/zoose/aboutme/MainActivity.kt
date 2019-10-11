package com.zoose.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zoose.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // created data
    private val myName: MyName = MyName("Gustavo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(R.layout.activity_main)

        // data binding
        binding.myName = myName

        val doneButton = binding.doneButton
        doneButton.setOnClickListener { addNickname() }

        val nicknameText = binding.nicknameText
        nicknameText.setOnClickListener { updateNickName(it) }
    }

    private fun addNickname() {
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)
    }

    private fun updateNickName(view: View) {
        val editText = binding.nicknameEdit
        val doneButton = binding.doneButton

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        editText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}
