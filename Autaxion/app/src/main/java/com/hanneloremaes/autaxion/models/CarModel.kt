package com.hanneloremaes.autaxion.models

class CarModel (
    var brand: String = "",
    var carimg: String = " "
){

    fun CarsModal(carBrand: String, carImage: String) {
        this.brand = carBrand;
        this.carimg = carImage;
    }

    fun getBrandCar(): String{
        return brand;
    }
    fun setBrandCar(brandCar: String) {
        this.brand = brandCar;
    }

    fun getCarImg(): String {
        return carimg
    }

    fun setCarImg(imgCar: String) {
        carimg = imgCar
    }
}