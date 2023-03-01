package ru.chuikov.spartacusdice

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(app:Application): AndroidViewModel(app) {

    var blueCubes:MutableLiveData<List<Int>> = MutableLiveData(listOf(
        (1..6).random(),
        (1..6).random(),
        (1..6).random(),
    ))
    var blackCubes:MutableLiveData<List<Int>> = MutableLiveData(listOf(
        (1..6).random(),
        (1..6).random(),
        (1..6).random(),
    ))
    var redCubes:MutableLiveData<List<Int>> = MutableLiveData(listOf(
        (1..6).random(),
        (1..6).random(),
        (1..6).random(),
    ))


    private var listOfDiceThrows:MutableList<DiceThrow> = mutableListOf()

    fun getListOfThrows() = listOfDiceThrows

    fun addToListThrows(dThrow:DiceThrow){
        listOfDiceThrows.add(dThrow)
    }

    fun addCube(color: CubeColor){
        when(color){
            CubeColor.Blue -> if(blueCubes.value!!.size<5) blueCubes.value = blueCubes.value?.toMutableList()?.apply { add(getRandomDice()) }
            CubeColor.Black -> if(blackCubes.value!!.size<5) blackCubes.value = blackCubes.value?.toMutableList()?.apply { add(getRandomDice()) }
            CubeColor.Red -> if(redCubes.value!!.size<5) redCubes.value = redCubes.value?.toMutableList()?.apply { add(getRandomDice()) }
        }
    }

    fun removeCube(color: CubeColor){
        when(color){
            CubeColor.Blue -> if(blueCubes.value!!.size>1) blueCubes.value = blueCubes.value?.toMutableList()?.apply { removeLast()}
            CubeColor.Red -> if(redCubes.value!!.size>1) redCubes.value = redCubes.value?.toMutableList()?.apply { removeLast()}
            CubeColor.Black -> if(blackCubes.value!!.size>1)blackCubes.value = blackCubes.value?.toMutableList()?.apply { removeLast()}
        }
    }

    fun rollTheDice(color: CubeColor){
        when(color){
            CubeColor.Blue -> blueCubes.value = blueCubes.value?.toMutableList()?.apply {
                forEachIndexed { index, i ->
                    this.set(index,getRandomDice())
                }
                sort()
                reverse()
                addToListThrows(DiceThrow(color,this))
            }
            CubeColor.Red -> redCubes.value = redCubes.value?.toMutableList()?.apply {
                forEachIndexed { index, i ->
                    this.set(index,getRandomDice())
                }
                sort()
                reverse()
                addToListThrows(DiceThrow(color,this))
            }
            CubeColor.Black -> blackCubes.value = blackCubes.value?.toMutableList()?.apply {
                forEachIndexed { index, i ->
                    this.set(index,getRandomDice())
                }
                sort()
                reverse()
                addToListThrows(DiceThrow(color,this))
            }
        }
    }

    fun rollTheDiceByIndex(color: CubeColor, index: Int){
        when(color){
            CubeColor.Blue -> blueCubes.value = blueCubes.value?.toMutableList()?.apply {
                set(index,getRandomDice())
                addToListThrows(DiceThrow(color,this))
            }
            CubeColor.Black -> blackCubes.value = blackCubes.value?.toMutableList()?.apply {
                set(index,getRandomDice())
                addToListThrows(DiceThrow(color,this))
            }
            CubeColor.Red -> redCubes.value = redCubes.value?.toMutableList()?.apply {
                set(index,getRandomDice())
                addToListThrows(DiceThrow(color,this))
            }

        }
    }


    private fun getRandomDice():Int = (1..6).random()

}