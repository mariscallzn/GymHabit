package com.andymariscal.gymhabit.ui.assistant

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andymariscal.gymhabit.MainApplication
import com.andymariscal.gymhabit.R
import kotlinx.android.synthetic.main.fragment_assistat.*
import javax.inject.Inject

class AssistantFragment : Fragment() {

    //region @Inject
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //endregion

    //region Global properties
    private val viewModel by viewModels<AssistantViewModel> { viewModelFactory }
    //endregion

    //region Lifecycle
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MainApplication)
            .appComponent.assistantComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_assistat, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_assistant.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = viewModel.assistantAdapter
        }

        rv_actions.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = viewModel.actionsAdapter
        }
    }
    //endregion
}