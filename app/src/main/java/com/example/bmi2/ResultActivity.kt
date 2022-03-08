package com.example.bmi2

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ResultActivity : AppCompatActivity() {
    private val TAG=ResultActivity::class.java.simpleName  //記得新增
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val bmi=intent.getFloatExtra("Bmi",0f)  //獲得標籤ＢＭＩ標籤的 以及如果沒有收到資料的預設值
        //且標籤名字要和上一頁的一樣 此處為      "Bmi"
        Log.d(TAG,"收到的BMI:$bmi")

    }
}