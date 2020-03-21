package com.andymariscal.gymhabit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andymariscal.shared.data.db.AppDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runOnUiThread {
            AppDatabase.getInstance(this).openHelper.readableDatabase
        }
    }
}
