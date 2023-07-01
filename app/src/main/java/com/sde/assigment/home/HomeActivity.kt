package com.sde.assigment.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sde.assigment.databinding.ActivityHomeBinding
import com.sde.assigment.models.FacilitiesAndExclusionsModel

class HomeActivity : AppCompatActivity(), HomeContract.View {
    private lateinit var viewBinding: ActivityHomeBinding
    private lateinit var homePresenter: HomePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        homePresenter = HomePresenter(this)
        homePresenter.start()
    }

    override fun init() {
        viewBinding.txtErrorMessage.visibility= View.GONE
        viewBinding.btnRetry.visibility= View.GONE
        homePresenter.loadData()
    }


    override fun showError(message: String) {
        viewBinding.txtErrorMessage.visibility= View.VISIBLE
        viewBinding.txtErrorMessage.text=message
        viewBinding.btnRetry.visibility= View.VISIBLE
        viewBinding.btnRetry.setOnClickListener {
            viewBinding.txtErrorMessage.visibility= View.GONE
            viewBinding.btnRetry.visibility= View.GONE
            homePresenter.loadData()
        }
    }

    override fun loadRecyclerViewData(facilitiesAndExclusionsModel: FacilitiesAndExclusionsModel) {
       viewBinding.customSelectableView.setData(facilitiesAndExclusionsModel)
    }
}