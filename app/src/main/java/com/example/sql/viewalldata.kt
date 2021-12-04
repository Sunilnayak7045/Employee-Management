package com.example.sql

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.viewalldata.*
import java.util.prefs.Preferences

import kotlinx.android.synthetic.main.displaydata.*


class viewalldata:AppCompatActivity() {

    lateinit var  Preferences: SharedPreferences

    lateinit var msg : String
   lateinit  var msg22 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewalldata)
        Preferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)

        val bundle: Bundle? = intent.extras

         msg = bundle?.getString("user_message2000").toString()
         msg22 = bundle?.getString("user_message2001").toString()

        println(msg)
        println(msg22)



        val actionBar = supportActionBar
        actionBar!!.title = "View Data"


        var helper = Myhelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var rss: Cursor = db.rawQuery("select  * from credentialdb , EmployeeSalary , AddressTable  where   credentialdb._id = EmployeeSalary._id and credentialdb._id = AddressTable._id; ", null)


        var adapter = SimpleCursorAdapter(applicationContext,android.R.layout.simple_expandable_list_item_2,rss,
            arrayOf("Employee_NAME","DESIGNATION"), intArrayOf(android.R.id.text1,android.R.id.text2),0  )

        listview.adapter=adapter

        adapter.notifyDataSetChanged()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profilenav,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_settings1 -> {
                val editor: SharedPreferences.Editor= Preferences.edit()
                editor.clear()
                editor.apply()


                val intent = Intent( this, loginpage::class.java)
                startActivity(intent)
                finish()


                true
            }
            R.id.update-> {
                val intent = Intent( this, update::class.java)
                intent.putExtra("user_message2", msg)

                intent.putExtra("user_message212", msg22)
                startActivity(intent)
                finish()


                true
            }

            R.id.profile -> {



                println("=====================")
                println(msg)
                println(msg22)

                val intent = Intent( this, profile::class.java)
                intent.putExtra("user_message", msg)

                intent.putExtra("user_message44", msg22)

                startActivity(intent)
                finish()



                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}