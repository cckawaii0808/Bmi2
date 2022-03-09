package com.example.bmi2

import android.app.AlertDialog
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import com.example.bmi2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val TAG = ResultActivity::class.java.simpleName
    val REQUEST_DISPLAY_BMI = 16
    lateinit var binding: ActivityMainBinding //lateinit 晚點給
    var launcher = registerForActivityResult(NameContract()) { name ->
        Log.d(TAG, "$name")
    }

    override fun onCreate(savedInstanceState: Bundle?) { //oncreate 一定會被執行
        super.onCreate(savedInstanceState)
        //宣告取得外觀中的變數
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreate")//複寫要在類別最外一層crti+o
        setContentView(binding.root)
        registerForActivityResult(NameContract()) {        //註冊下面合約以及建構子ＮＡＭＥ 呼叫建構子得到物件
            Log.d(TAG, "G")

        }


    }


    override fun onStart() {//畫面產生，還未出先
        super.onStart()
        Log.d(TAG, "onStart")

    }

    override fun onResume() {//畫面出現
        super.onResume()
        Log.d(TAG, "onResume")

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

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
        intent.putExtra("Bmi", bmi)//帶一個東西，先取一個標籤名，再取那個東西過去
        //startActivity(intent)//注意不要選到有Ｓ的startActivity
        // startActivityForResult(intent,REQUEST_DISPLAY_BMI)
        launcher.launch(bmi)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)//在方法外面按ctrl+O 找複寫
        Log.d(TAG, "onActivityResult")
        if (requestCode == REQUEST_DISPLAY_BMI && resultCode == RESULT_OK) {
            Log.d(TAG, "back from ResultActivity")
        }
    }

    /*  class NameContract : ActivityResultContract<Unit, String>() {  //創立合約//unit 前為傳入 後為傳出
          //希望一開始過去沒東西 但是可以拿一個字串的名字回來
          //alt + enter 創造未知函式


          override fun createIntent(context: Context, input: Unit?): Intent {
              return Intent(context, ResultActivity::class.java)
          }

          override fun parseResult(resultCode: Int, intent: Intent?): String {
              if (resultCode == RESULT_OK) {
                  val name = intent?.getStringExtra("NAME")
                  return name!!
              } else {
                  return "No name"
              }*/
    class NameContract : ActivityResultContract<Float, String>() {


        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == RESULT_OK) {
                val name = intent?.getStringExtra(Extras.NAME)
                return name!!
            } else {
                return "No name"
            }
        }

        override fun createIntent(context: Context, input: Float?): Intent {
            val data = Intent(context, ResultActivity::class.java).putExtra(Extras.BMI, input)
            return data
        }
    }
}


