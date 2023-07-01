package com.sde.assigment.components.selectableBox

import com.sde.assigment.components.selectableButton.CustomSelectableButton

interface CustomSelectableBoxSelectionCallback {
    fun onItemClicked(
        id: String,
        wantsToBeSelected: Boolean,
        customSelectableButton: CustomSelectableButton
    )
}