package com.sde.assigment.utils

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import java.util.concurrent.TimeUnit

fun String.showToast(context: Context){
    Toast.makeText(context,this,Toast.LENGTH_LONG).show()
}
fun Int.toDp(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        context.resources.displayMetrics
    ).toInt()
}

fun Long.isOutdatedCache():Boolean{
    if (this==-1L){
        return true
    }
    var isDayPassed = (System.currentTimeMillis() - this) >= TimeUnit.DAYS.toMillis(1)
    isDayPassed = (System.currentTimeMillis() - this) >= TimeUnit.SECONDS.toMillis(1)
    return isDayPassed
}

