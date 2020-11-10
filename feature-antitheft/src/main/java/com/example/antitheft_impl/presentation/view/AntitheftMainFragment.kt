package com.example.antitheft_impl.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.antitheft_api.R
import com.example.antitheft_impl.di.AntitheftFeatureComponent
import com.example.antitheft_impl.presentation.presenter.AntitheftPresenter
import dagger.Lazy
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

internal class AntitheftMainFragment : MvpAppCompatFragment(), AntitheftMainView {
    @Inject
    lateinit var daggerPresenter: Lazy<AntitheftPresenter>

    @InjectPresenter
    lateinit var antitheftPresenter: AntitheftPresenter

    @ProvidePresenter
    fun providePresenter(): AntitheftPresenter {
        return daggerPresenter.get()
    }

    init {
        AntitheftFeatureComponent.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_antitheft, null)
        view.findViewById<View>(R.id.button_purchase).setOnClickListener { v: View? -> antitheftPresenter.clickToBuyWork() }
        view.findViewById<View>(R.id.button_at).setOnClickListener { v: View? -> antitheftPresenter.clickToAtWork() }
        view.findViewById<View>(R.id.button_help).setOnClickListener { v: View? -> antitheftPresenter.clickToHelp() }
        return view
    }

    override fun showAtWork() {
        Toast.makeText(context, R.string.antitheft_screen_do_atjob_name, Toast.LENGTH_SHORT).show()
    }

    override fun showAtSuccess() {
        Toast.makeText(context, R.string.antitheft_screen_do_atjob_completed_name, Toast.LENGTH_SHORT).show()
    }

    override fun showBuyWork() {
        Toast.makeText(context, R.string.purchase_buy_job_doing, Toast.LENGTH_SHORT).show()
    }

    override fun showBuySuccess() {
        Toast.makeText(context, R.string.purchase_buy_job_completed, Toast.LENGTH_SHORT).show()
    }
}