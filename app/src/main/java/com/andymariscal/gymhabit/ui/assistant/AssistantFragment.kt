package com.andymariscal.gymhabit.ui.assistant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.andymariscal.gymhabit.R

class AssistantFragment : Fragment() {

    //region Global properties
    private var viewModel: AssistantViewModel? = null
    //endregion

    //region Lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.activity_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = AssistanceViewModelProvider().create(AssistantViewModel::class.java)
        viewModel?.apply {
            loading.observe(viewLifecycleOwner, loadingObserver)
            showText.observe(viewLifecycleOwner, showTextObserver)
        }

        viewModel?.tryCourutine()
    }
    //endregion

    //region Observers
    private val loadingObserver = Observer<Boolean> {

    }

    private val showTextObserver = Observer<String> {

    }
    //endregion
}