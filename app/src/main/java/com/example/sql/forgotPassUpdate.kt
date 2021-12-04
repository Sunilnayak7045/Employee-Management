package com.example.sql

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.forgotpassu.*
import kotlinx.android.synthetic.main.update.*
import kotlinx.android.synthetic.main.update.UpdatePageNextbutton

class forgotPassUpdate: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpassu)


        Updateloginbutton?.setOnClickListener{


            val intent = Intent(this, loginpage::class.java)
            startActivity(intent)
        }


        UpdatePageNextbutton?.setOnClickListener{


            var helper = Myhelper(applicationContext)
            var db: SQLiteDatabase = helper.writableDatabase

            val actionBar = supportActionBar
            actionBar!!.title = "Forgot Password"

            val bundle: Bundle? = intent.extras
            val msg200 = bundle?.getString("update_user_message")


            var sr: Cursor =
                db.rawQuery("SELECT PASSWORD FROM credentialdb WHERE EMAIL='$msg200'", null)


            var cv= ContentValues()
            cv.put("PASSWORD",newUpdatename.text.toString())

            db.update("credentialdb",cv, "EMAIL=?", arrayOf("$msg200"))
            sr.requery()

            var ad1= AlertDialog.Builder(this)
            ad1.setTitle("  Update Record")
            ad1.setMessage("New Password Updated Successfully  :) ")
            ad1.setPositiveButton("OK", null)
            ad1.show()
        }

    }
}