package ru.chuikov.spartacusdice

data class DiceThrow(
    var cubeColor: CubeColor,
    var list: MutableList<Int>
)
