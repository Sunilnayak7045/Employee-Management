package com.example.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.provider.ContactsContract.DisplayNameSources.EMAIL
import android.provider.Telephony
import android.provider.Telephony.Carriers.PASSWORD

class Myhelper(context: Context):SQLiteOpenHelper(context,"Employee",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE credentialdb( _id integer primary key autoincrement, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, DESIGNATION TEXT)")

        db?.execSQL("CREATE TABLE EmployeeSalary( _id integer primary key autoincrement, Employee_NAME TEXT, Employee_SALARY FLOAT,   FOREIGN KEY( _id) REFERENCES credentialdb( _id)  )")

        db?.execSQL("CREATE TABLE AddressTable( _id integer primary key autoincrement, Employee_NAME TEXT, Employee_ADDRESS VARCHAR,STATE VARCHAR,COUNTRY TEXT, FOREIGN KEY( _id) REFERENCES credentialdb( _id))")



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }



}