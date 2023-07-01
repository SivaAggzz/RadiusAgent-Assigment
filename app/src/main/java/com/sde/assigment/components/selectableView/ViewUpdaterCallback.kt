package com.sde.assigment.components.selectableView

interface ViewUpdaterCallback{
    fun onSelected(id: String, unselectBtnIds: List<String>)
    fun onUnSelected(id:String)
}