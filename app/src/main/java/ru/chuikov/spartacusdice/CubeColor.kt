package ru.chuikov.spartacusdice

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout

enum class CubeColor {
    Red,
    Black,
    Blue
}

fun generateCube(context: Context, color: CubeColor, i: Int, measuredWidth:Int, onClick:(View)->Unit): ImageButton = ImageButton(context).apply{
    val param = LinearLayout.LayoutParams(
        0,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        1f
    )
    //param.width = measuredWidth / 5
    layoutParams = param
    adjustViewBounds = true
    scaleType = ImageView.ScaleType.CENTER_CROP
    setBackgroundResource(
        when(color){
            CubeColor.Black -> when (i) {
                1 -> R.mipmap.dice_1_black
                2 -> R.mipmap.dice_2_black
                3 -> R.mipmap.dice_4_black
                4 -> R.mipmap.dice_4_black
                5 -> R.mipmap.dice_5_black
                else -> R.mipmap.dice_6_black
            }
            CubeColor.Blue -> when (i){
                1 -> R.mipmap.dice_1_blue
                2 -> R.mipmap.dice_2_blue
                3 -> R.mipmap.dice_4_blue
                4 -> R.mipmap.dice_4_blue
                5 -> R.mipmap.dice_5_blue
                else -> R.mipmap.dice_6_blue
            }
            CubeColor.Red -> when (i){
                1 -> R.mipmap.dice_1_red
                2 -> R.mipmap.dice_2_red
                3 -> R.mipmap.dice_4_red
                4 -> R.mipmap.dice_4_red
                5 -> R.mipmap.dice_5_red
                else -> R.mipmap.dice_6_red
            }
        }

    )
    setOnClickListener {
        onClick(it)
    }
}