package com.example.sql

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.register.*

//testtttt  sq
//testtttt  sq 2
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        //master test

        //xyz
        //commit 2

        var helper= Myhelper(applicationContext)
        var db:SQLiteDatabase = helper.writableDatabase
        var rs=db.rawQuery("SELECT * FROM credentialdb",null)

        var rs1=db.rawQuery("SELECT * FROM EmployeeSalary",null)


        var rs2=db.rawQuery("SELECT * FROM AddressTable",null)

        registerPageNextbutton?.setOnClickListener{
            var cv=ContentValues()
            var cv1=ContentValues()
            var cv2=ContentValues()
            cv.put("NAME",Registername.text.toString())
            cv.put("EMAIL",editTextTextRegisterEmailAddress.text.toString())

            cv.put("PASSWORD",editTextTextRegisterPassword.text.toString())

            cv.put("DESIGNATION",editTextTextConfirmRegisterPassword.text.toString())


            cv1.put("Employee_NAME",Registername.text.toString())

            cv1.put("Employee_SALARY",editTextSalary.text.toString())

            cv2.put("Employee_NAME",Registername.text.toString())
            cv2.put("Employee_ADDRESS",editTextTextRegisterAddress.text.toString())
            cv2.put("STATE",textView.text.toString())
            cv2.put("COUNTRY",textView3.text.toString())



            db.insert("credentialdb",null,cv)

            db.insert("EmployeeSalary",null,cv1)
            db.insert("AddressTable",null,cv2)
            rs.requery()
            rs1.requery()
            rs2.requery()

            var ad= AlertDialog.Builder(this)
            ad.setTitle("Add Record")
            ad.setMessage("Record Added Successfully  :) ")
            ad.setPositiveButton("OK", null)
            ad.show()

        }

        registerPageLoginbutton?.setOnClickListener {

            val intent = Intent(this, loginpage::class.java)
            startActivity(intent)
        }

        val bundle: Bundle? = intent.extras
        val msg= bundle?.getString("user_message")

        val actionBar = supportActionBar
        actionBar!!.title = "Register"



    }
}