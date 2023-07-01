package com.sde.assigment.utils

import com.sde.assigment.R

fun String.getResId():Int{
    return when(this){
        "apartment"-> R.drawable.apartment
        "boat"-> R.drawable.boat
        "condo"-> R.drawable.condo
        "garage"-> R.drawable.garage
        "garden"-> R.drawable.garden
        "land"-> R.drawable.land
        "no_room"-> R.drawable.no_room
        "rooms"-> R.drawable.rooms
        "swimming"-> R.drawable.swimming
        else->R.drawable.apartment
    }
}