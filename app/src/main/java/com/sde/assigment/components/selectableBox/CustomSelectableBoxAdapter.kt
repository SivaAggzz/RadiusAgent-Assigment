package com.sde.assigment.components.selectableBox

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sde.assigment.components.selectableButton.CustomSelectableButton
import com.sde.assigment.databinding.ItemSelectableButtonBinding
import com.sde.assigment.models.Option

class CustomSelectableBoxAdapter(
    private val options: List<Option>,
    private val callback: CustomSelectableBoxSelectionCallback
) : RecyclerView.Adapter<CustomSelectableBoxAdapter.CustomSelectableBoxVH>() {
    private var recyclerView: RecyclerView? = null
    private val customSelectableButtons = ArrayList<CustomSelectableButton>()

    class CustomSelectableBoxVH(val viewBinding: ItemSelectableButtonBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomSelectableBoxVH {
        recyclerView = parent as RecyclerView
        val itemBinding = ItemSelectableButtonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CustomSelectableBoxVH(itemBinding)
    }

    override fun onBindViewHolder(holder: CustomSelectableBoxVH, position: Int) {
        holder.viewBinding.option = options[position]
        holder.viewBinding.callback = callback
    }

    override fun getItemCount(): Int {
        return options.size
    }

    fun selectOnly(id: String) {
        for (index in 0 until itemCount) {
            val customSelectableBoxVH =
                recyclerView?.findViewHolderForAdapterPosition(index) as CustomSelectableBoxVH?
            customSelectableBoxVH?.apply {
                val isSelected = (this.viewBinding.customSelectableButton.tag == id)
                viewBinding.customSelectableButton.setButtonSelected(isSelected)
            }
        }
    }

    fun unselectItem(id: String) {
        Log.e("unselectItem","id: $id")
        for (index in 0 until itemCount) {
            val customSelectableBoxVH =
                recyclerView?.findViewHolderForAdapterPosition(index) as CustomSelectableBoxVH?
            customSelectableBoxVH?.apply {
                if (this.viewBinding.customSelectableButton.tag == id) {
                    viewBinding.customSelectableButton.setButtonSelected(false)
                }
            }
        }
    }

    fun getAllCustomSelectableBox(): ArrayList<CustomSelectableButton> {
        customSelectableButtons.clear()
        for (index in 0 until itemCount) {
            val customSelectableBoxVH =
                recyclerView?.findViewHolderForAdapterPosition(index) as CustomSelectableBoxVH?
            customSelectableBoxVH?.apply {
                customSelectableButtons.add(this.viewBinding.customSelectableButton)
            }
        }
        return customSelectableButtons
    }


}