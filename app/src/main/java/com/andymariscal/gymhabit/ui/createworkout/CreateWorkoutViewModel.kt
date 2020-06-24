package com.andymariscal.gymhabit.ui.createworkout

import androidx.lifecycle.ViewModel
import com.andymariscal.gymhabit.ui.createworkout.delegate.TitleAndDateDelegate
import com.andymariscal.shared.inf.DefaultDiffCallback
import com.andymariscal.shared.inf.ItemDelegateAdapter
import com.andymariscal.shared.inf.StringConverter
import com.andymariscal.shared.inf.ViewTypeDelegate
import javax.inject.Inject

class CreateWorkoutViewModel @Inject constructor() : ViewModel() {

    //region Global properties
    val adapter by lazy {
        ItemDelegateAdapter(ItemDelegateAdapter.Builder(DefaultDiffCallback)
            .addAllDelegates(ViewTypeDelegate(1, TitleAndDateDelegate())))
    }
    //endregion

    init {
        adapter.submitList(StringConverter.convertList(listOf(
            ""
        )))
    }

}