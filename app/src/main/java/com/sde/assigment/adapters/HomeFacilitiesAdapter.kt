package com.sde.assigment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sde.assigment.components.selectableView.ViewUpdaterCallback
import com.sde.assigment.databinding.ItemFacilityBinding
import com.sde.assigment.models.FacilitiesAndExclusionsModel
import com.sde.assigment.models.Option
import com.sde.assigment.components.selectableBox.CustomSelectableBox


class HomeFacilitiesAdapter(
    context: Context,
    facilitiesAndExclusionsModel: FacilitiesAndExclusionsModel,
    private val viewUpdaterCallback: ViewUpdaterCallback
) : RecyclerView.Adapter<HomeFacilitiesAdapter.HomeFacilitiesVh>() {

    private lateinit var recyclerView: RecyclerView
    private val layoutInflater = LayoutInflater.from(context)
    private var facilityText: ArrayList<String>
    private var optionsList: ArrayList<List<Option>>
    private var facilitiesMap: LinkedHashMap<String, List<Option>> = LinkedHashMap()
    private val customSelectableBoxes=ArrayList<CustomSelectableBox>()


    init {
        for (facility in facilitiesAndExclusionsModel.facilities) {
            val name = facility.name ?: ""
            facilitiesMap[name] = facility.options
        }
        facilityText = ArrayList(facilitiesMap.keys)
        optionsList = ArrayList(facilitiesMap.values)
    }

    class HomeFacilitiesVh(val viewBinding: ItemFacilityBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFacilitiesVh {
        recyclerView = parent as RecyclerView
        return HomeFacilitiesVh(ItemFacilityBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return facilitiesMap.size
    }

    override fun onBindViewHolder(holder: HomeFacilitiesVh, position: Int) {
        holder.viewBinding.propertyType = facilityText[position]
        holder.viewBinding.customSelectableBox.setOptions(optionsList[position],viewUpdaterCallback)
    }

    fun getAllCustomSelectableBox(): ArrayList<CustomSelectableBox> {
        customSelectableBoxes.clear()
        for (index in 0 until itemCount){
            val homeFacilitiesVh = recyclerView.findViewHolderForAdapterPosition(index) as HomeFacilitiesVh?
            homeFacilitiesVh?.apply {
                customSelectableBoxes.add(this.viewBinding.customSelectableBox)
            }
        }
        return customSelectableBoxes
    }


}