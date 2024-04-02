package com.lee_idle.testrdp

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import com.lee_idle.testrdp.databinding.ActivityAuthenticationBinding
import kotlinx.coroutines.coroutineScope
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class activity_authentication(private val context: AppCompatActivity, val cSocket: Socket) {
    private lateinit var binding: ActivityAuthenticationBinding
    private val dlg = Dialog(context)
    private lateinit var listener: AuthenticationCheckClickedListener

    lateinit var passchk: DataOutputStream
    lateinit var verification: DataInputStream
    lateinit var verify: String
    lateinit var width: String
    lateinit var height: String
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

    private fun temp(){
        val value1 = binding.etPassword.text.toString()
        try{
            passchk = DataOutputStream(cSocket.getOutputStream())
            verification = DataInputStream(cSocket.getInputStream())
            passchk.writeUTF(value1)
            verify = verification.readUTF()
        }catch (e: IOException){
            e.printStackTrace()
        }

        if(verify == "valid"){
            try{
                width = verification.readUTF()
                height = verification.readUTF()
            }catch (e: Exception){
                e.printStackTrace()
            }
            //CreateFrame abc = new CreateFrame(cSock, width, height)
            dlg.dismiss()
        }else{
            val alertDialog = AlertDialog.Builder(context)
            with(alertDialog) {
                setTitle("알림")
                setMessage("비밀번호가 틀렸습니다. 다시 확인해주세요.")
                setPositiveButton("확인",
                    DialogInterface.OnClickListener{dialog, id ->

                    })
                    .show()
            }
        }
    }
}

