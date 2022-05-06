package com.example.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Query("SELECT * FROM carBrands")
    fun getAllCarBrands(): Flow<List<Car>>

    @Query("SELECT * FROM carBrands ORDER BY car")
    fun getAlphabetizedCarBrands(): Flow<List<Car>>

//    @Query("SELECT * FROM carBrands WHERE id = :carBrand_id LIMIT 1")
//    fun findCarBrandByID(word_id: Int): Flow<Car>

    @Insert
    fun insertCar(car: Car)

    @Insert
    fun InsertALotOfCarBrands(cars: List<Car>)

    @Update
    fun updateCarBrands(car: Car)

    @Delete
    fun deleteCarBrands(car: Car)

}