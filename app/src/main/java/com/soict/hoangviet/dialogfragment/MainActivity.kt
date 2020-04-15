package com.soict.hoangviet.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener, MyDialog.DialogListener {
    private val btnEmbedDialogFragment by lazy { findViewById<Button>(R.id.btnEmbedDialogFragment) }
    private val btnDialogFragment by lazy { findViewById<Button>(R.id.btnDialogFragment) }
    private val btnDialogFragmentFullScreen by lazy { findViewById<Button>(R.id.btnDialogFragmentFullScreen) }
    private val btnAlertDialogFragment by lazy { findViewById<Button>(R.id.btnAlertDialogFragment) }
    private val textView by lazy { findViewById<TextView>(R.id.textView) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEmbedDialogFragment.setOnClickListener(this)
        btnDialogFragment.setOnClickListener(this)
        btnDialogFragmentFullScreen.setOnClickListener(this)
        btnAlertDialogFragment.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnEmbedDialogFragment -> {
                val dialogFragment = MyDialog()
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.frameLayout, dialogFragment)
                ft.commit()
            }
            R.id.btnDialogFragment -> {
                val dialogFragment = MyDialog()
                dialogFragment.arguments = Bundle().apply {
                    putBoolean("notAlertDialog", true)
                }
                val ft = supportFragmentManager.beginTransaction()
                val prev = supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")
            }
            R.id.btnDialogFragmentFullScreen -> {
                val dialogFragment = MyDialog()
                dialogFragment.arguments = Bundle().apply {
                    putString("mobile", "0123456789")
                    putBoolean("fullScreen", true)
                    putBoolean("notAlertDialog", true)
                }
                val ft = supportFragmentManager.beginTransaction()
                val prev = supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")
            }
            R.id.btnAlertDialogFragment -> {
                val dialogFragment = MyDialog()
                val ft = supportFragmentManager.beginTransaction()
                val prev = supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")
            }
        }
    }

    override fun onFinishEditDialog(inputText: String) {
        if (TextUtils.isEmpty(inputText)) {
            textView.text = "Please enter Mobile Number"
        } else
            textView.text = "Number entered: " + inputText
    }
}
