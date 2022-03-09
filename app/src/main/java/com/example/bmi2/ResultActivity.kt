package com.example.bmi2

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bmi2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private val TAG=ResultActivity::class.java.simpleName  //記得新增
    private lateinit var  binding :ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  setContentView(R.layout.activity_result)
        showBmi()
        binding.bdone.setOnClickListener{
            val name =binding.edname.text.toString()
            val data =Intent()
            data.putExtra("NAME",name)
            setResult(RESULT_OK,data)
            finish()

        }
    }

    private fun showBmi() {
        val bmi = intent.getFloatExtra("Bmi", 0f)  //獲得標籤ＢＭＩ標籤的 以及如果沒有收到資料的預設值  //comand+M抽出
        //且標籤名字要和上一頁的一樣 此處為      "Bmi"
        Log.d(TAG, "收到的BMI:$bmi")
        binding.bmidisplay.text=bmi.toString()

    }
}