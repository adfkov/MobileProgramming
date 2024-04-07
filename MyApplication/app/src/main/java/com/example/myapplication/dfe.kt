package com.example.myapplication

fun main() {
//    println("EEE")
    val test : (a:Int, b:Double) -> Double
    test ={ a,b ->
        a.toDouble() +b
    }
   val result =  test(1,20.5)
    println(result)
}
