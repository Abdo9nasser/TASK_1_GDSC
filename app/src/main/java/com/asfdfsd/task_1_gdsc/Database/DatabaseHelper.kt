package com.asfdfsd.task_1_gdsc.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context):SQLiteOpenHelper(context,DB_name,null,dp_version) {
    companion object{
       private const val DB_name="my database"
       private const val  dp_version=1
        private const val  ID="id"
        private const val  EMAIL="email"
        private const val  NAME="name"
        private const val  TBL_NAME="STUDENTID"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createstudentid=("CREATE TABLE " + TBL_NAME + "(" + ID + " INTEGER PRIMARY KEY," +
                NAME + " TEXT," +  EMAIL + " TEXT"+ ")")
        db?.execSQL(createstudentid)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_NAME")
        onCreate(db)
    }
    fun Delete(n:Int):Int
    {
        val db=this.writableDatabase
        val content=ContentValues()
        val success=db.delete(TBL_NAME, "id=$n" , null)
        db.close()
        return success
    }
    fun insertstudent(std:Studentmodel):Long
    {
        val db=this.writableDatabase
        val content=ContentValues()
        content.put(ID,std.id)
        content.put(NAME,std.name)
        content.put(EMAIL,std.email)
        val success=db.insert(TBL_NAME,null,content)
        db.close()
        return  success

    }
    @SuppressLint("Range")
    fun getallstd():ArrayList<Studentmodel>{
        val listofsdd:ArrayList<Studentmodel> =ArrayList()
        val select="SELECT*FROM $TBL_NAME"
        val db=this.readableDatabase
        val cursor:Cursor?
        try {
     cursor=db.rawQuery(select,null)
        }
        catch (e:Exception){
       e.printStackTrace()
            db.execSQL(select)
            return ArrayList()
        }
        var id:Int
        var name:String
        var email:String
        if (cursor.moveToFirst()) {
            do {

                    id = cursor.getInt(cursor.getColumnIndex("id"))
                    name = cursor.getString(cursor.getColumnIndex("name"))
                    email = cursor.getString(cursor.getColumnIndex("email"))
                    val stddata = Studentmodel(id = id, name = name, email = email)
                    listofsdd.add(stddata)

            } while (cursor.moveToNext())
        }
return listofsdd
    }

}