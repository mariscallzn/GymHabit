package com.andymariscal.gymhabit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.andymariscal.shared.data.db.AppDatabase
import java.util.concurrent.Executors
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Executors.newSingleThreadExecutor().execute {
            Log.e("Andres", "aqui si puto?")
            database.exerciseTypeDao().insertAll(listOf())
        }
    }
}
