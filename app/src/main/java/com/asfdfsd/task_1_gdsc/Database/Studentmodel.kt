package com.asfdfsd.task_1_gdsc.Database

import android.text.Editable
import java.net.IDN
import kotlin.random.Random

data class Studentmodel(
    var id: Int=getautuid(),
    val name: String ="",
    val email: String? =""

)
{
    companion object{
        fun getautuid():Int{
            val randon= java.util.Random()
            return randon.nextInt(100)
        }
    }
}
