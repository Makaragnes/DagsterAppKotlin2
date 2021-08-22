package com.example.dagsterappkotlin.utilits

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dagsterappkotlin.MainActivity
import com.example.dagsterappkotlin.R

fun showToast(message:String){
    /* Функция показывает сообщение */
    Toast.makeText(APP_ACTIVITY,message, Toast.LENGTH_SHORT).show()
}

fun restartActivity(){
    /* Функция расширения для AppCompatActivity, позволяет запускать активити */
    val intent = Intent(APP_ACTIVITY, MainActivity::class.java)
    APP_ACTIVITY.startActivity(intent)
    APP_ACTIVITY.finish()
}

fun replaceFragment(fragment: Fragment, addStack:Boolean = true){
    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
    if (addStack){
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.data_container,
                fragment
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(R.id.data_container,
                fragment
            ).commit()
    }

}

fun saveSharedPrefString(
    key: String,
    value: String,
    sharedPreferences: SharedPreferences
) {
    val editor: SharedPreferences.Editor = sharedPreferences.edit();
    editor.putString(key, value)
    editor.apply()
    editor.commit()
}



fun hideKeyboard() {
    /* Функция скрывает клавиатуру */
    val imm: InputMethodManager = APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
}