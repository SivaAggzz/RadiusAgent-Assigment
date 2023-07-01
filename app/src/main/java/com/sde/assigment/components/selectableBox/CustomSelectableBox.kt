package com.sde.assigment.components.selectableBox

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.sde.assigment.components.selectableView.ViewUpdaterCallback
import com.sde.assigment.components.selectableButton.CustomSelectableButton
import com.sde.assigment.databinding.CustomSelectableBoxBinding
import com.sde.assigment.models.Option
import com.sde.assigment.utils.toDp

class CustomSelectableBox(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs), CustomSelectableBoxSelectionCallback {
    private var viewBinding: CustomSelectableBoxBinding
    private val layoutInflater = LayoutInflater.from(context)

    private lateinit var options: List<Option>
    private lateinit var viewUpdaterCallback: ViewUpdaterCallback
    private lateinit var adapter: CustomSelectableBoxAdapter
    private val unselectBtnIds = ArrayList<String>()

    init {
        viewBinding = CustomSelectableBoxBinding.inflate(layoutInflater)
        addView(viewBinding.root)
    }

    fun setOptions(options: List<Option>, viewUpdaterCallback: ViewUpdaterCallback) {
        this.options = options
        this.viewUpdaterCallback=viewUpdaterCallback
        adapter = CustomSelectableBoxAdapter(options, this)
        viewBinding.optionsView.adapter = adapter
        viewBinding.optionsView.addItemDecoration(GridDividerItemDecoration(8.toDp(context)))
    }

    fun getCustomSelectableButtons(): ArrayList<CustomSelectableButton> {
        return adapter.getAllCustomSelectableBox()
    }

    private fun getUnselectBtnIds(id: String): List<String> {
        unselectBtnIds.clear()
        for (option in options) {
            if (option.id != id) {
                unselectBtnIds.add(option.id!!)
            }
        }
        return unselectBtnIds
    }

    override fun onItemClicked(
        id: String,
        wantsToBeSelected: Boolean,
        customSelectableButton: CustomSelectableButton
    ) {
        if (wantsToBeSelected) {
            adapter.selectOnly(id)
            viewUpdaterCallback.onSelected(id, getUnselectBtnIds(id))
        } else {
            adapter.unselectItem(id)
            viewUpdaterCallback.onUnSelected(id)
        }
    }






}