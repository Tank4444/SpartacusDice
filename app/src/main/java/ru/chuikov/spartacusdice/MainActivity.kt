package ru.chuikov.spartacusdice

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setMargins
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import ru.chuikov.spartacusdice.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel:MainViewModel

    private var blackCubesLinear = mutableListOf<Int>()
    private var blueCubesLinear = mutableListOf<Int>()
    private var redCubesLinear = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val diceParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT,)
        diceParams.setMargins(10)

        //Black cubes
        binding.blackCubesInc.setImageDrawable(Drawable.createFromStream(assets.open("blackArrowUp.png"),"blackArrowUp.png"))
        binding.blackCubesDec.setImageDrawable(Drawable.createFromStream(assets.open("blackArrowDown.png"),"blackArrowDown.png"))
        mainViewModel.blackCubes.observe(this){
            binding.blackCount.text = "$it"
            binding.blackCubes.removeAllViews()

            val value = mainViewModel.blackCubes.value?:3
            for (i in 1..value) binding.blackCubes.addView(ImageButton(this).apply {
                layoutParams = diceParams
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_CROP
                setBackgroundResource(when((1..6).random()){
                    1 -> R.mipmap.dice_1_black
                    2 -> R.mipmap.dice_2_black
                    3 -> R.mipmap.dice_4_black
                    4 -> R.mipmap.dice_4_black
                    5 -> R.mipmap.dice_5_black
                    else -> R.mipmap.dice_6_black
                })
            })
        }
        binding.blackRandomizer.setOnClickListener {
            blackCubesLinear.clear()
            val value = mainViewModel.blackCubes.value?:3
            for (i in 0..value -1) blackCubesLinear.add((1..6).random())
            blackCubesLinear.sort()
            blackCubesLinear.reverse()


            binding.blackCubes.removeAllViews()
            for (i in blackCubesLinear){
                binding.blackCubes.addView(ImageButton(this).apply {
                    layoutParams = diceParams
                    setBackgroundResource(when(i){
                        1 -> R.mipmap.dice_1_black
                        2 -> R.mipmap.dice_2_black
                        3 -> R.mipmap.dice_4_black
                        4 -> R.mipmap.dice_4_black
                        5 -> R.mipmap.dice_5_black
                        else -> R.mipmap.dice_6_black
                    })
                    adjustViewBounds = true
                    scaleType = ImageView.ScaleType.CENTER_CROP

                })
            }
        }
        binding.blackCubesInc.setOnClickListener {
            val value = mainViewModel.blackCubes.value?:3
            mainViewModel.blackCubes.value = if ((value+1)>5) 5 else value+1
        }
        binding.blackCubesDec.setOnClickListener {
            val value = mainViewModel.blackCubes.value?:3
            mainViewModel.blackCubes.value = if ((value-1)<1) 1 else value-1
        }

        //Blue cubes
        binding.blueCubesInc.setImageDrawable(Drawable.createFromStream(assets.open("blueArrowUp.png"),"blueArrowUp.png"))
        binding.blueCubesDec.setImageDrawable(Drawable.createFromStream(assets.open("blueArrowDown.png"),"blueArrowDown.png"))
        mainViewModel.blueCubes.observe(this){
            binding.blueCount.text = "$it"
            binding.blueCubes.removeAllViews()

            val value = mainViewModel.blueCubes.value?:3
            for (i in 1..value) binding.blueCubes.addView(ImageButton(this).apply {
                layoutParams = diceParams
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_CROP
                setBackgroundResource(when((1..6).random()){
                    1 -> R.mipmap.dice_1_blue
                    2 -> R.mipmap.dice_2_blue
                    3 -> R.mipmap.dice_4_blue
                    4 -> R.mipmap.dice_4_blue
                    5 -> R.mipmap.dice_5_blue
                    else -> R.mipmap.dice_6_blue
                })
            })
        }
        binding.blueRandomizer.setOnClickListener {
            blueCubesLinear.clear()
            val value = mainViewModel.blueCubes.value?:3
            for (i in 0..value -1) blueCubesLinear.add((1..6).random())
            blueCubesLinear.sort()
            blueCubesLinear.reverse()


            binding.blueCubes.removeAllViews()
            for (i in blueCubesLinear){
                binding.blueCubes.addView(ImageButton(this).apply {
                    layoutParams = diceParams
                    setBackgroundResource(when(i){
                        1 -> R.mipmap.dice_1_blue
                        2 -> R.mipmap.dice_2_blue
                        3 -> R.mipmap.dice_4_blue
                        4 -> R.mipmap.dice_4_blue
                        5 -> R.mipmap.dice_5_blue
                        else -> R.mipmap.dice_6_blue
                    })
                    adjustViewBounds = true
                    scaleType = ImageView.ScaleType.CENTER_CROP

                })
            }
        }
        binding.blueCubesInc.setOnClickListener {
            val value = mainViewModel.blueCubes.value?:3
            mainViewModel.blueCubes.value = if ((value+1)>5) 5 else value+1
        }
        binding.blueCubesDec.setOnClickListener {
            val value = mainViewModel.blueCubes.value?:3
            mainViewModel.blueCubes.value = if ((value-1)<1) 1 else value-1
        }

        //red cubes
        binding.redCubesInc.setImageDrawable(Drawable.createFromStream(assets.open("redArrowUp.png"),"redArrowUp.png"))
        binding.redCubesDec.setImageDrawable(Drawable.createFromStream(assets.open("redArrowDown.png"),"redArrowDown.png"))
        mainViewModel.redCubes.observe(this){
            binding.redCount.text = "$it"
            binding.redCubes.removeAllViews()

            val value = mainViewModel.redCubes.value?:3
            for (i in 1..value) binding.redCubes.addView(ImageButton(this).apply {
                layoutParams = diceParams
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_CROP
                setBackgroundResource(when((1..6).random()){
                    1 -> R.mipmap.dice_1_red
                    2 -> R.mipmap.dice_2_red
                    3 -> R.mipmap.dice_4_red
                    4 -> R.mipmap.dice_4_red
                    5 -> R.mipmap.dice_5_red
                    else -> R.mipmap.dice_6_red
                })
            })
        }
        binding.redRandomizer.setOnClickListener {
            redCubesLinear.clear()
            val value = mainViewModel.redCubes.value?:3
            for (i in 0..value -1) redCubesLinear.add((1..6).random())
            redCubesLinear.sort()
            redCubesLinear.reverse()


            binding.redCubes.removeAllViews()
            for (i in redCubesLinear){
                binding.redCubes.addView(ImageButton(this).apply {
                    layoutParams = diceParams
                    setBackgroundResource(when(i){
                        1 -> R.mipmap.dice_1_red
                        2 -> R.mipmap.dice_2_red
                        3 -> R.mipmap.dice_4_red
                        4 -> R.mipmap.dice_4_red
                        5 -> R.mipmap.dice_5_red
                        else -> R.mipmap.dice_6_red
                    })
                    adjustViewBounds = true
                    scaleType = ImageView.ScaleType.CENTER_CROP

                })
            }
        }
        binding.redCubesInc.setOnClickListener {
            val value = mainViewModel.redCubes.value?:3
            mainViewModel.redCubes.value = if ((value+1)>5) 5 else value+1
        }
        binding.redCubesDec.setOnClickListener {
            val value = mainViewModel.redCubes.value?:3
            mainViewModel.redCubes.value = if ((value-1)<1) 1 else value-1
        }


    }

}