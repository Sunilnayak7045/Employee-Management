package com.example.sql;

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.forgotpassu.*
import kotlinx.android.synthetic.main.forgotpassv.*
import kotlinx.android.synthetic.main.login.*

class forgotPassValidate : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpassv)

        var helper= Myhelper(applicationContext)
        var db = helper.readableDatabase

        val actionBar = supportActionBar
        actionBar!!.title = "Forgot Password"

        forgetloginbutton.setOnClickListener{
            var a = listOf<String>(forgeteditTextTextEmailAddress.text.toString(),forgetloginnamebtn.text.toString() ).toTypedArray()

            var verify=db.rawQuery("SELECT EMAIL,NAME FROM credentialdb WHERE EMAIL=? AND NAME=?",a)

            if(verify.moveToNext() )
            {
                val updatemessage1:String=forgeteditTextTextEmailAddress.text.toString()

                val intent1 = Intent(this, forgotPassUpdate::class.java)

               intent1.putExtra("update_user_message",updatemessage1)
                startActivity(intent1)
                verify.close()
                finish()



            }
            else{

                var ad= AlertDialog.Builder(this)
                ad.setTitle("Verify Email and Name")
                ad.setMessage(" Invalid Data  :( ")
                ad.setPositiveButton("OK", null)
                ad.show()
                verify.close()

            }
        }


    }

}
