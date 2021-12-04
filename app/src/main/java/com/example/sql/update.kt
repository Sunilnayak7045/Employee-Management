package com.example.sql

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.displaydata.*
import kotlinx.android.synthetic.main.update.*
import kotlinx.android.synthetic.main.update.editTextTextRegisterAddress
import java.util.prefs.Preferences

class update: AppCompatActivity() {

    lateinit var  Preferences: SharedPreferences
    lateinit var msg2 : String
    lateinit  var msg234 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update)



            Preferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)


            val actionBar = supportActionBar
            actionBar!!.title = "Update"



        val name1: String? =  Preferences.getString("NAME", "")
        // t1.text= name

        val email1: String? = Preferences.getString("EMAIL", "")
            val bundle: Bundle? = intent.extras

             msg2 = bundle?.getString("user_message2").toString()

             msg234 = bundle?.getString("user_message212").toString()


            var helper = Myhelper(applicationContext)
            var db: SQLiteDatabase = helper.writableDatabase
            var rs: Cursor =
                db.rawQuery("SELECT NAME,DESIGNATION FROM credentialdb WHERE EMAIL='$msg2'", null)



            var rs1: Cursor = db.rawQuery(
                "SELECT  Employee_SALARY FROM EmployeeSalary WHERE Employee_NAME='$msg234' ",
                null
            )
            println(rs1)


            var rs2: Cursor = db.rawQuery(
                "SELECT  Employee_ADDRESS,STATE ,COUNTRY FROM AddressTable WHERE Employee_NAME='$msg234'",
                null
            )


            if (rs.moveToFirst()) {
                Updatename.setText(rs.getString(0))
                UpdateConfirmRegisterPassword.setText(rs.getString(1))
            }


            if (rs1.moveToNext()) {
                UpdateSalary.setText(rs1.getString(0))
            }

//
            if (rs2.moveToFirst()) {
                editTextTextRegisterAddress.setText(rs2.getString(0))
                textView.setText(rs2.getString(1))
                textView33.setText(rs2.getString(2))
            }

            var r: Cursor = db.rawQuery(
                "SELECT NAME, EMAIL,DESIGNATION FROM credentialdb WHERE EMAIL='$msg2'",
                null
            )

            val r1: Cursor = db.rawQuery(
                "SELECT  Employee_SALARY FROM EmployeeSalary WHERE Employee_NAME='$msg234' ",
                null
            )
            println(r1)


            var r2: Cursor = db.rawQuery(
                "SELECT  Employee_ADDRESS,STATE ,COUNTRY FROM AddressTable WHERE Employee_NAME='$msg234'",
                null
            )

        //====================sp===================================


        var rs11: Cursor =
            db.rawQuery("SELECT NAME,DESIGNATION FROM credentialdb WHERE EMAIL='$email1'", null)


//        var abc  =db.rawQuery("SELECT id FROM credentialdb WHERE EMAIL='$msg'",null)
//        var d = abc.getString(1)
//        println(d)

        var rs22: Cursor = db.rawQuery(
            "SELECT  Employee_SALARY FROM EmployeeSalary WHERE Employee_NAME='$name1' ",
            null
        )
        println(rs1)


        var rs33: Cursor = db.rawQuery(
            "SELECT  Employee_ADDRESS,STATE ,COUNTRY FROM AddressTable WHERE Employee_NAME='$name1'",
            null
        )


        if (rs11.moveToFirst()) {
            Updatename.setText(rs11.getString(0))
            UpdateConfirmRegisterPassword.setText(rs11.getString(1))
        }


        if (rs22.moveToNext()) {
            UpdateSalary.setText(rs22.getString(0))
        }

//
        if (rs33.moveToFirst()) {
            editTextTextRegisterAddress.setText(rs33.getString(0))
            textView.setText(rs33.getString(1))
            textView33.setText(rs33.getString(2))
        }

        var r11: Cursor = db.rawQuery(
            "SELECT NAME, EMAIL,DESIGNATION FROM credentialdb WHERE EMAIL='$email1'",
            null
        )

        val r22: Cursor = db.rawQuery(
            "SELECT  Employee_SALARY FROM EmployeeSalary WHERE Employee_NAME='$name1' ",
            null
        )
        println(r1)


        var r33: Cursor = db.rawQuery(
            "SELECT  Employee_ADDRESS,STATE ,COUNTRY FROM AddressTable WHERE Employee_NAME='$name1'",
            null
        )



        //==========sp======================================

            UpdatePageNextbutton?.setOnClickListener {


                println("aaaaaaa")
                var cv = ContentValues()
                var cv1 = ContentValues()
                var cv2 = ContentValues()
                cv.put("NAME", Updatename.text.toString())

                cv.put("DESIGNATION", UpdateConfirmRegisterPassword.text.toString())


                cv1.put("Employee_NAME", Updatename.text.toString())

                cv1.put("Employee_SALARY", UpdateSalary.text.toString())

                cv2.put("Employee_NAME", Updatename.text.toString())
                cv2.put("Employee_ADDRESS", editTextTextRegisterAddress.text.toString())
                cv2.put("STATE", textView.text.toString())
                cv2.put("COUNTRY", textView33.text.toString())

                db.update("credentialdb", cv, "EMAIL=?", arrayOf("$msg2"))

                db.update("EmployeeSalary", cv1, "Employee_NAME=?", arrayOf("$msg234"))
                db.update("AddressTable", cv2, "Employee_NAME=?", arrayOf("$msg234"))
                r.requery()
                r1.requery()
                r2.requery()

                //=====================sp1=================

                db.update("credentialdb", cv, "EMAIL=?", arrayOf("$email1"))

                db.update("EmployeeSalary", cv1, "Employee_NAME=?", arrayOf("$name1"))
                db.update("AddressTable", cv2, "Employee_NAME=?", arrayOf("$name1"))
                r11.requery()
                r22.requery()
                r33.requery()


                //===================sp2===========

                var ad1 = AlertDialog.Builder(this)
                ad1.setTitle("  Update Record")
                ad1.setMessage("Record Updated Successfully  :) ")
                ad1.setPositiveButton("OK", null)
                ad1.show()
            }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.updatenav,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings2 -> {

                val editor: SharedPreferences.Editor= Preferences.edit()
                editor.clear()
                editor.apply()


                val intent = Intent( this, loginpage::class.java)
                startActivity(intent)
                finish()

                // }

                true
            }
            R.id.profile11 -> {

                val intent = Intent( this, profile::class.java)

                intent.putExtra("user_message", msg2)

                intent.putExtra("user_message44", msg234)
                startActivity(intent)
                finish()

                return true
            }
            R.id.viewall11 -> {

                intent.putExtra("user_message2000", msg2)

                intent.putExtra("user_message2001", msg234)

                val intent = Intent( this, viewalldata::class.java)
                startActivity(intent)
                finish()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}