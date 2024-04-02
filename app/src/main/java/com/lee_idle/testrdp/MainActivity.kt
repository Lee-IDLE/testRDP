package com.lee_idle.testrdp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lee_idle.testrdp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.Socket

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var ip: String
    lateinit var mPassword: String
    val port = "4907"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener{
            ip = "218.235.200.178"

            CoroutineScope(Dispatchers.IO).launch {
                val socket = Socket(ip, port.toInt())
                val dialog = activity_authentication(this@MainActivity, socket) // , socket
                dialog.setOnCheckClickedListener { password ->
                    Toast.makeText(this@MainActivity, "$password", Toast.LENGTH_LONG).show()
                }
                dialog.show()

            }
            /*
            return@setOnClickListener

            ip = binding.etIp.text.toString()
            try{
                val socket = Socket(ip, port.toInt())
                //인증
            }catch (e: Exception){
                e.printStackTrace()
            }

             */
        }
    }


}