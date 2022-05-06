package com.example.room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MyRepository (private val carDao: CarDao) {

    val allCarBrandsOrdered: Flow<List<Car>> = carDao.getAlphabetizedCarBrands()
    val allCarBrands: Flow<List<Car>> = carDao.getAllCarBrands()

    @WorkerThread
    fun insertCar(car: Car){
        carDao.insertCar(car)
    }

}