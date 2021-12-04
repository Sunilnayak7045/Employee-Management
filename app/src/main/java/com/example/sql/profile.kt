package com.example.sql

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.displaydata.*
import kotlinx.android.synthetic.main.displaydata.editTextTextRegisterAddress
import kotlinx.android.synthetic.main.displaydata.textView3
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register.*
import java.util.prefs.Preferences

class profile : AppCompatActivity() {


    lateinit var  Preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.displaydata)
            Preferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
            val actionBar = supportActionBar
            actionBar!!.title = "Profile"
            var bundle: Bundle? = intent.extras

            var msg = bundle?.getString("user_message")
            var msg22 = bundle?.getString("user_message44")



        val name: String? =  Preferences.getString("NAME", "")


        val email: String? = Preferences.getString("EMAIL", "")

            var helper = Myhelper(applicationContext)
            var db: SQLiteDatabase = helper.writableDatabase
            var rs: Cursor = db.rawQuery(
                "SELECT NAME, EMAIL,DESIGNATION FROM credentialdb WHERE EMAIL='$msg'",
                null
            )


            var rs1: Cursor = db.rawQuery(
                "SELECT  Employee_SALARY FROM EmployeeSalary WHERE Employee_NAME='$msg22' ",
                null
            )


            println(rs1)


            var rs2: Cursor = db.rawQuery(
                "SELECT  Employee_ADDRESS,STATE ,COUNTRY FROM AddressTable WHERE Employee_NAME='$msg22'",
                null
            )


            if (rs.moveToFirst()) {
                profilename.setText(rs.getString(0))
                profileEmailAddress.setText(rs.getString(1))
                profilePassword.setText(rs.getString(2))
            }


            if (rs1.moveToNext()) {
                profileSalary.setText(rs1.getString(0))
            }

//
            if (rs2.moveToFirst()) {
                editTextTextRegisterAddress.setText(rs2.getString(0))
                profileView.setText(rs2.getString(1))
                textView3.setText(rs2.getString(2))
            }



        var r1s: Cursor = db.rawQuery(
            "SELECT NAME, EMAIL,DESIGNATION FROM credentialdb WHERE EMAIL='$email'",
            null
        )


        var r2s: Cursor = db.rawQuery(
            "SELECT  Employee_SALARY FROM EmployeeSalary WHERE Employee_NAME='$name' ",
            null
        )



        var r3s: Cursor = db.rawQuery(
            "SELECT  Employee_ADDRESS,STATE ,COUNTRY FROM AddressTable WHERE Employee_NAME='$name'",
            null
        )


        if (r1s.moveToFirst()) {
            profilename.setText(r1s.getString(0))
            profileEmailAddress.setText(r1s.getString(1))
            profilePassword.setText(r1s.getString(2))
        }


        if (r2s.moveToNext()) {
            profileSalary.setText(r2s.getString(0))
        }


        if (r3s.moveToFirst()) {
            editTextTextRegisterAddress.setText(r3s.getString(0))
            profileView.setText(r3s.getString(1))
            textView3.setText(r3s.getString(2))
        }

        viewbtn?.setOnClickListener {
                var message100 = msg

                var message101 = msg22

                var message103 = msg
                var message102 = msg22

                var intent2 = Intent(this, viewalldata::class.java)

                intent2.putExtra("user_message2000", message100)

                intent2.putExtra("user_message2001", message101)

                intent2.putExtra("user_message2", message103)
                intent2.putExtra("user_message212", message102)

                startActivity(intent2)
            }

            updatebtn.setOnClickListener() {
                var message = msg

                var message33 = msg22


                var forward1=name
                var forward2=email
                var intent2 = Intent(this, update::class.java)

                intent2.putExtra("user_message2", message)

                intent2.putExtra("user_message212", message33)


                intent2.putExtra("user_message2", name)

                intent2.putExtra("user_message212", email)
                startActivity(intent2)


            }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings -> {


                var editor: SharedPreferences.Editor= Preferences.edit()
                editor.clear()
                editor.apply()


                var intent = Intent( this, loginpage::class.java)
                startActivity(intent)
                finish()

                // }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}