package com.sde.assigment.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sde.assigment.models.Option
import com.sde.assigment.components.selectableButton.CustomSelectableButton
import com.sde.assigment.components.selectableBox.CustomSelectableBoxSelectionCallback

class BindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter(value = ["bindText"])
        fun bindText(textView: TextView,text:String){
            textView.text=text
        }

        @JvmStatic
        @BindingAdapter(value= ["setupOption","setupOnClick"])
        fun setupOption(customSelectableButton: CustomSelectableButton, option: Option, callback: CustomSelectableBoxSelectionCallback?) {
            customSelectableButton.tag = option.id!!
            customSelectableButton.setViewId(option.id!!)
            customSelectableButton.setOption(option)

            callback?.apply {
                customSelectableButton.setOnClickListener {
                    if (customSelectableButton.isButtonDisabled()) {
                        return@setOnClickListener
                    }
                    val isButtonSelected = customSelectableButton.isButtonSelected()
                    this.onItemClicked(
                        option.id!!,
                        !isButtonSelected,
                        customSelectableButton
                    )

                }
            }
        }

        @JvmStatic
        @BindingAdapter(value= ["iconName"])
        fun setupIcon(imageView: ImageView,iconName:String?) {
            iconName?.apply {
                imageView.setImageResource(this.getResId())
            }
        }
    }
}