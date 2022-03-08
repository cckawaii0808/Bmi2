package com.example.bmi2

import android.app.AlertDialog
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.bmi2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding //lateinit 晚點給
    override fun onCreate(savedInstanceState: Bundle?) { //oncreate 一定會被執行
        super.onCreate(savedInstanceState)
        //宣告取得外觀中的變數
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun bmibottom(view: View) {  //方法後為按鈕onclick設定的名字
        Log.d("MainActivity", "點擊發生")
        var weight = binding.edWeight.text.toString().toFloat()
        //可變變數體重的值為binding裡的體重的資料轉文字再轉浮點數
        var height = binding.edHeight.text.toString().toFloat()
        val bmi = weight / (height * height)
        Log.d("BMI為", bmi.toString())  //Logcat除錯匡訊息顯示bmi

        Toast.makeText(this, bmi.toString(), Toast.LENGTH_LONG).show()//顯示bmi在小訊息匡
        /*val builder = AlertDialog.Builder(this)
        builder.setTitle("訊息提示")
            .setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
*/
        AlertDialog.Builder(this)
            .setTitle("我是訊息匡")
            .setMessage("你的ＢＭＩ＝ $bmi")
            .setPositiveButton("OK") { d, w ->
                binding.edHeight.setText("")//清空資料，設定該元件匡的資料為“”
                binding.edWeight.setText("")
            }
            .show()

        //下面開始轉換第二個畫面Activity
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("Bmi",bmi)//帶一個東西，先取一個標籤名，再取那個東西過去
        startActivity(intent)//注意不要選到有Ｓ的startActivity

    }
}