package com.sde.assigment.components.selectableButton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.sde.assigment.databinding.CustomSelectableButtonBinding
import com.sde.assigment.models.Option

class CustomSelectableButton(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    private var viewBinding: CustomSelectableButtonBinding
    private val layoutInflater = LayoutInflater.from(context)

    private var btnSelected = false
    private var viewIdInString = ""


    init {
        viewBinding = CustomSelectableButtonBinding.inflate(layoutInflater)
        addView(viewBinding.root)
    }

    fun setViewId(viewId: String) {
        this.viewIdInString = viewId
    }
    fun getViewId(): String {
        return viewIdInString
    }

    fun setOption(option: Option) {
        viewBinding.option = option
    }

    fun setButtonSelected(selected: Boolean) {
        this.btnSelected = selected
        viewBinding.rootView.isSelected = btnSelected
        viewBinding.text.isSelected = btnSelected
        viewBinding.icon.isSelected = btnSelected
    }

    fun isButtonSelected(): Boolean {
        return viewBinding.rootView.isSelected
    }
    fun isButtonDisabled():Boolean{
        return !viewBinding.rootView.isEnabled
    }

    fun setButtonDisabled(shouldBeDisabled: Boolean) {
        if (shouldBeDisabled) {
            this.btnSelected = false
            viewBinding.rootView.isSelected = false
            viewBinding.text.isSelected = false
            viewBinding.icon.isSelected = false

            viewBinding.rootView.isEnabled = false
            viewBinding.text.isEnabled = false
            viewBinding.icon.isEnabled = false
        } else {
            viewBinding.rootView.isEnabled = true
            viewBinding.text.isEnabled = true
            viewBinding.icon.isEnabled = true
        }

    }


}