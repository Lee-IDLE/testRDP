package com.lee_idle.testrdp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.lee_idle.testrdp.databinding.ActivityFrameBinding
import java.net.Socket

class FrameActivity(sock: Socket, width: String, height: String) : AppCompatActivity() {
    private lateinit var binding:  ActivityFrameBinding

    private val desktop = JDesktopPane()
    private val width = width
    private val height = height
    private val cSock = sock
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutParams = ViewGroup.LayoutParams(width.toInt(), height.toInt())
        binding.sfvFrame.layoutParams = layoutParams


    }
}