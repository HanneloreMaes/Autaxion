package com.school.autaxion.models

class CarModel (
    var brand: String = "",
    var carimg: String = " "
) {

    // creating constructor for our variables.
    fun CourseModal(carBrand: String, carImage: String) {
        this.brand = carBrand;
        this.carimg = carImage;
    }

    // creating getter and setter methods.
    fun getCourseName(): String {
        return brand;
    }

    fun setCourseName(brandCar: String) {
        this.brand = brandCar;
    }

    fun getCourseimg(): String? {
        return carimg
    }

    fun setCourseimg(courseimg: String) {
        carimg = courseimg
    }

}