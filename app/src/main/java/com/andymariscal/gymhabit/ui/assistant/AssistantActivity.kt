package com.andymariscal.gymhabit.ui.assistant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AssistantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, AssistantFragment())
                .commit()
        }
    }
    //endregion
}