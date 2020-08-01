package com.example.covidtracker

/*
*   1. Data Class
*   In Kotlin, you can create a data class to hold the data.
*   The reason why would you want to mark a class as data is to let compiler know that you are creating
*   this class for holding the data, compiler then creates several functions automatically for your data
*   class which would be helpful in managing data.
*
*   2. ?= in Kotlin
*   A variable of type String can not hold null. If we try to assign null to the variable, it gives compiler error.
*   To allow a variable to hold null, we can declare a variable as nullable string, written String?
*   eg- var s2: String? = "Robert"
* */

data class Response(
    val statewise : List<StatewiseItem>
)


data class StatewiseItem(
    val recovered : String?= null,
    val deltadeaths : String?= null,
    val deltarecovered : String?= null,
    val active : String?= null,
    val deltaconfirmed : String?= null,
    val state : String?= null,
    val statecode : String?= null,
    val confirmed  : String?= null,
    val deaths  : String?= null,
    val lastupdatedtime : String?= null

)

