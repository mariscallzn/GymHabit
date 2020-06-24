package com.andymariscal.gymhabit.ui.createworkout

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
import kotlinx.android.synthetic.main.fragment_create_workout.*
import javax.inject.Inject

class CreateWorkoutFragment : Fragment() {

    //region @Inject
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    //endregion

    //region ViewModel
    val viewModel by viewModels<CreateWorkoutViewModel> { viewModelFactory }
    //endregion

    //region Life cycle
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MainApplication)
            .appComponent.createWorkoutComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_create_workout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_create_workout.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = viewModel.adapter
        }
    }
    //endregion
}