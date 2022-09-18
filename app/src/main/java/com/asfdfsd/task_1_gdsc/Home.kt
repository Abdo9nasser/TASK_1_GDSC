package com.asfdfsd.task_1_gdsc

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.asfdfsd.task_1_gdsc.Database.DatabaseHelper
import com.asfdfsd.task_1_gdsc.Database.Studentmodel
import com.google.android.material.textfield.TextInputLayout

class Home : AppCompatActivity() {
    lateinit var text_n:TextInputLayout
    lateinit var text_e:TextInputLayout
    lateinit var b_add:Button
    lateinit var b_view:Button
    lateinit var myre: RecyclerView
    lateinit var myadapter:Adapter
    lateinit var sqlitehelper:DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       inview()
        b_add.setOnClickListener{
            addstudent()
            getallsd()
            refrech()
        }
        myadapter=Adapter(getallsd())
        myre.adapter=myadapter
        b_view.setOnClickListener{
            myadapter=Adapter(getallsd())
            myre.adapter=myadapter
        }
        myadapter.object_0n=object :Adapter.onclick_delete{
            override fun onclilck(position: Int,id:Int) {
         delete(id)
            }

        }


    }

    private fun getallsd():ArrayList<Studentmodel> {
        val list_st= sqlitehelper.getallstd()
        Log.e("lll","gggg${list_st.size}")
        return list_st
    }
    fun refrech()
    {
        myadapter.LIST=sqlitehelper.getallstd()
        myadapter.notifyDataSetChanged()

    }

    private fun addstudent() {
        if(text_e.editText?.text.toString().isEmpty() ||text_n.editText?.text.toString().isEmpty())
        {
            text_n.error="Enter name"
        }
        else
        {
            text_n.error=null
            val name =text_n.editText?.text.toString()
            val email =text_e.editText?.text.toString()
            val data=Studentmodel(name = name, email = email)
            val stat=sqlitehelper.insertstudent(data)
            text_e.editText?.setText("")
            text_n.editText?.setText("")
            text_n.editText?.requestFocus()
            Toast.makeText(this, "student added", Toast.LENGTH_SHORT).show()

        }
    }

    private fun inview() {
        text_n=findViewById(R.id.edit_name)
        text_e=findViewById(R.id.edit_mail)
        b_add=findViewById(R.id.bt_Add)
        b_view=findViewById(R.id.bt_view)
        myre=findViewById(R.id.rec)
        sqlitehelper=DatabaseHelper(this)
    }
    fun delete(id:Int)
    {
        val alertDialog=android.app.AlertDialog.Builder(this)
        alertDialog.setCancelable(true)
        alertDialog.setMessage("Are you suer you want to delete item?")
        alertDialog.setPositiveButton("yes"){ dialog,_->
            sqlitehelper.Delete(id)
            refrech()

        }
        alertDialog.setNegativeButton("no"){dialog,_->
            dialog.dismiss()
        }
        val builder=alertDialog.create()
        builder.show()
    }
}