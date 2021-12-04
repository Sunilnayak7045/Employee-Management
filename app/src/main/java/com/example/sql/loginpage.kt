package com.example.sql
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class loginpage : AppCompatActivity() {


    lateinit var  sharedPreferences: SharedPreferences
    var isRemembered= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

        isRemembered = sharedPreferences.getBoolean("checkbox", false)

        var helper= Myhelper(applicationContext)
        var db = helper.readableDatabase

        val actionBar = supportActionBar
        actionBar!!.title = "Login"
        if (isRemembered) {

            val intent = Intent( this, profile::class.java)
            startActivity(intent)
            finish()

        }

        loginbutton.setOnClickListener{
            var a = listOf<String>(editTextTextEmailAddress.text.toString(),editTextTextPassword.text.toString(),loginnamebtn.text.toString() ).toTypedArray()

            var verify=db.rawQuery("SELECT EMAIL,PASSWORD,NAME FROM credentialdb WHERE EMAIL=? AND PASSWORD=? AND NAME=?",a)

            if(verify.moveToNext() )
            {
                    //========testing================
                var message =editTextTextEmailAddress.text.toString()
                var m =    loginnamebtn.text.toString() //testing


                    val name: String = loginnamebtn.text.toString()
                    println(name)

                    val email: String = editTextTextEmailAddress.text.toString()

                    println(email)
                    val pass: String = editTextTextPassword.text.toString()

                    println(pass)
                    val checked : Boolean = checkBox.isChecked
                    val editor : SharedPreferences.Editor = sharedPreferences.edit()

                    editor.putString("NAME", name)
                    editor.putString("EMAIL", email)
                    editor.putString("PASS", pass)

                    editor.putBoolean("checkbox",checked)
                    editor.apply()

                val intent1 = Intent(this, profile::class.java)
                intent1.putExtra("user_message",message)
                intent1.putExtra("user_message44",m) //testing

                startActivity(intent1)

            }
            else{

                Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show()
                verify.close()

            }
        }


        registerbutton?.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        textView5?.setOnClickListener {

            val intent = Intent(this, forgotPassValidate::class.java)
            startActivity(intent)
        }

    }
}

