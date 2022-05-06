package com.example.room

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainModelView (private val repository: MyRepository): ViewModel() {
    val allCarBrands: LiveData<List<Car>> = repository.allCarBrandsOrdered.asLiveData()

    //soort van sync en await
    fun insert(car: Car) = viewModelScope.launch { repository.insertCar(car) }
}

class CarBrandsViewModelFactory(private val repository: MyRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainModelView::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainModelView(repository) as T
        }
        throw IllegalArgumentException("Unknown viewmodel")
    }
}