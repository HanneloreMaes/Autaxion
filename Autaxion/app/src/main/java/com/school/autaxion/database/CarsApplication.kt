package com.example.room

import android.app.Application

class CarsApplication: Application() {

    val database by lazy { CarsDatabase.getDatabase(this)}
    val repository by lazy { MyRepository(database.carDao())}

}