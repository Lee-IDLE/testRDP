package com.lee_idle.testrdp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.lee_idle.testrdp.databinding.ActivityAuthenticationBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class activity_authentication(private val context: AppCompatActivity/*, val cSocket: Socket*/) {
    private lateinit var binding: ActivityAuthenticationBinding
    private val dlg = Dialog(context)
    private lateinit var listener: AuthenticationCheckClickedListener

    lateinit var passchk: DataOutputStream
    lateinit var verification: DataInputStream
    lateinit var verify: String
    fun show(){
        binding = ActivityAuthenticationBinding.inflate(context.layoutInflater)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀바 없음
        dlg.setContentView(binding.root)
        dlg.setCancelable(false) // 바깥 클릭 방지

        binding.btnCheck.setOnClickListener{
            listener.onCheckClicked(binding.etPassword.text.toString())
            dlg.dismiss()
        }
        binding.btnCancel.setOnClickListener{
            dlg.dismiss()
        }

        dlg.show()
    }

    fun  setOnCheckClickedListener(password: (String) -> Unit){
        object : AuthenticationCheckClickedListener {
            override fun onCheckClicked(password: String) {
                password(binding.etPassword.text.toString())
            }
        }.also { this.listener = it }
    }

    interface AuthenticationCheckClickedListener{
        fun onCheckClicked(password: String)
    }
}

