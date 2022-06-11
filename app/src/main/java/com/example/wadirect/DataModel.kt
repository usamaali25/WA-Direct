package com.example.wadirect

class DataModel {

    var number:String? = null
    var date:String? = null

    fun setNumbers(number:String?)
    {
        this.number = number
    }
    fun setDates(date:String?)
    {
        this.date = date
    }
    fun getNumbers():String?{
        return number
    }
    fun getDates():String?{
        return date
    }

}