package com.sde.assigment.home

import com.sde.assigment.models.FacilitiesAndExclusionsModel

interface HomeContract {

    interface View{
        fun init()
        fun showError(message:String)
        fun loadRecyclerViewData(facilitiesAndExclusionsModel: FacilitiesAndExclusionsModel)
    }

    interface Presenter{
        fun start()
        fun loadData()
    }
}