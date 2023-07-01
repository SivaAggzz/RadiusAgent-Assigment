package com.sde.assigment.components.selectableView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.sde.assigment.adapters.HomeFacilitiesAdapter
import com.sde.assigment.databinding.CustomSelectableViewBinding
import com.sde.assigment.models.FacilitiesAndExclusionsModel

class CustomSelectableView(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs), ViewUpdaterCallback {
    private lateinit var allExclusionMap: java.util.LinkedHashMap<String, java.util.ArrayList<String>>
    private var viewBinding: CustomSelectableViewBinding
    private val layoutInflater = LayoutInflater.from(context)

    private lateinit var homeFacilitiesAdapter: HomeFacilitiesAdapter
    private val currentExclusions=LinkedHashSet<String>()
    private val selectedItemViewIdList= LinkedHashSet<String>()

    init {
        viewBinding = CustomSelectableViewBinding.inflate(layoutInflater)
        addView(viewBinding.root)
    }

    fun setData(facilitiesAndExclusionsModel: FacilitiesAndExclusionsModel){
        loadRecyclerViewData(facilitiesAndExclusionsModel)
    }

    private fun loadRecyclerViewData(facilitiesAndExclusionsModel: FacilitiesAndExclusionsModel) {
        allExclusionMap =LinkedHashMap()
        generateExclusionsMap(facilitiesAndExclusionsModel)
        homeFacilitiesAdapter = HomeFacilitiesAdapter(context,facilitiesAndExclusionsModel,this)
        viewBinding.recyclerView.adapter=homeFacilitiesAdapter
    }


    private fun generateExclusionsMap(facilitiesAndExclusionsModel: FacilitiesAndExclusionsModel) {
        val exclusions = facilitiesAndExclusionsModel.exclusions
        for (elementList in exclusions){
            val key=elementList[0].optionsId!!
            val value = if (allExclusionMap.containsKey(key)){
                allExclusionMap[key]
            }else{
                ArrayList()
            }
            value?.add(elementList[1].optionsId!!)
            allExclusionMap[key]=value!!
        }

    }


    override fun onSelected(id: String, unselectBtnIds: List<String>){
        refreshExclusionList()
        applyConstraintsAndDisableButtons()
    }

    override fun onUnSelected(id:String){
        refreshExclusionList()
        applyConstraintsAndDisableButtons()
    }


    private fun getSelectedItemViewIdList(): LinkedHashSet<String> {
        selectedItemViewIdList.clear()
        for (customSelectableBox in homeFacilitiesAdapter.getAllCustomSelectableBox()) {
            val customSelectableButtons = customSelectableBox.getCustomSelectableButtons()
            for (customSelectableButton in customSelectableButtons) {
                if (customSelectableButton.isButtonSelected()){
                    selectedItemViewIdList.add(customSelectableButton.getViewId())
                }
            }
        }
        return selectedItemViewIdList
    }



    private fun refreshExclusionList() {
        currentExclusions.clear()
        for (excludedId in getSelectedItemViewIdList()){
            val excludedOptions = if (allExclusionMap.containsKey(excludedId)) {
                allExclusionMap[excludedId]
            } else {
                ArrayList()
            }
            excludedOptions?.apply {
                currentExclusions.addAll(this)
            }
        }

    }

    private fun applyConstraintsAndDisableButtons() {
        for (customSelectableBox in homeFacilitiesAdapter.getAllCustomSelectableBox()){
            val customSelectableButtons=customSelectableBox.getCustomSelectableButtons()
            for (customSelectableButton in customSelectableButtons){
                val shouldBeDisabled = currentExclusions.contains(customSelectableButton.tag)
                customSelectableButton.setButtonDisabled(shouldBeDisabled)
            }
        }
    }
}