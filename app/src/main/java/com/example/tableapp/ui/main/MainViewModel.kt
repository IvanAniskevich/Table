package com.example.tableapp.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val pointRange = 0..5
    private val maxPoint = pointRange.last

    fun calculateThePoint(point: Int): Int {
        val r = maxPoint - point
        return r
    }


  fun calculateTheSum(list :List<Int>):Int{
    return list.sum()
  }
    fun calculateThePlace(list: List<Int>):List<String>{
        val l = list.sorted().reversed().mapIndexed { index, _ -> index+1 }
        return l.map { it.toString() }
    }
}