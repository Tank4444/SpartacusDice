package ru.chuikov.spartacusdice

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.chuikov.spartacusdice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private val durationOfAnimation: Long = 2000
    private val diceParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //Black cubes
        binding.blackCubesInc.setImageDrawable(
            Drawable.createFromStream(
                assets.open("blackArrowUp.png"),
                "blackArrowUp.png"
            )
        )
        binding.blackCubesDec.setImageDrawable(
            Drawable.createFromStream(
                assets.open("blackArrowDown.png"),
                "blackArrowDown.png"
            )
        )
        //start black observer
        mainViewModel.blackCubes.observe(this) {
            ObjectAnimator.ofFloat(binding.blackCubes, View.ALPHA, 0f, 1f).apply {
                interpolator = AccelerateInterpolator()
                duration = durationOfAnimation
                start()
            }
            binding.blackCount.text = "${it.size}"
            binding.blackCubes.removeAllViews()
            it.forEachIndexed { index, i ->
                binding.blackCubes.addView(generateCube(this,CubeColor.Black,i){
                    mainViewModel.rollTheDiceByIndex(CubeColor.Black,index)
                })
            }
        }
        binding.blackRandomizer.setOnClickListener {
            mainViewModel.rollTheDice(CubeColor.Black)
        }
        binding.blackCubesInc.setOnClickListener {
            mainViewModel.addCube(CubeColor.Black)
        }
        binding.blackCubesDec.setOnClickListener {
            mainViewModel.removeCube(CubeColor.Black)
        }

        //Blue cubes
        binding.blueCubesInc.setImageDrawable(
            Drawable.createFromStream(
                assets.open("blueArrowUp.png"),
                "blueArrowUp.png"
            )
        )
        binding.blueCubesDec.setImageDrawable(
            Drawable.createFromStream(
                assets.open("blueArrowDown.png"),
                "blueArrowDown.png"
            )
        )
        //start blue observer
        mainViewModel.blueCubes.observe(this) {
            ObjectAnimator.ofFloat(binding.blueCubes, View.ALPHA, 0f, 1f).apply {
                interpolator = AccelerateInterpolator()
                duration = durationOfAnimation
                start()
            }
            binding.blueCount.text = "${it.size}"
            binding.blueCubes.removeAllViews()
            it.forEachIndexed { index, i ->
                binding.blueCubes.addView(generateCube(this,CubeColor.Blue,i){
                    mainViewModel.rollTheDiceByIndex(CubeColor.Blue,index)
                })
            }
        }
        binding.blueRandomizer.setOnClickListener {
            mainViewModel.rollTheDice(CubeColor.Blue)
        }
        binding.blueCubesInc.setOnClickListener {
            mainViewModel.addCube(CubeColor.Blue)
        }
        binding.blueCubesDec.setOnClickListener {
            mainViewModel.removeCube(CubeColor.Blue)
        }

        //red cubes
        binding.redCubesInc.setImageDrawable(
            Drawable.createFromStream(
                assets.open("redArrowUp.png"),
                "redArrowUp.png"
            )
        )
        binding.redCubesDec.setImageDrawable(
            Drawable.createFromStream(
                assets.open("redArrowDown.png"),
                "redArrowDown.png"
            )
        )
        //start red observer
        mainViewModel.redCubes.observe(this) {
            ObjectAnimator.ofFloat(binding.redCubes, View.ALPHA, 0f, 1f).apply {
                interpolator = AccelerateInterpolator()
                duration = durationOfAnimation
                start()
            }
            binding.redCount.text = "${it.size}"
            binding.redCubes.removeAllViews()
            it.forEachIndexed { index, i ->
                binding.redCubes.addView(generateCube(this,CubeColor.Red,i){
                    mainViewModel.rollTheDiceByIndex(CubeColor.Red,index)
                })
            }
        }
        binding.redRandomizer.setOnClickListener {
            mainViewModel.rollTheDice(CubeColor.Red)
        }
        binding.redCubesInc.setOnClickListener {
            mainViewModel.addCube(CubeColor.Red)
        }
        binding.redCubesDec.setOnClickListener {
            mainViewModel.removeCube(CubeColor.Red)
        }


    }


    private fun generateCube(context:Context, color: CubeColor, i: Int, onClick:(View)->Unit):ImageButton = ImageButton(context).apply{
        diceParams.width = (binding.blackCubes.measuredWidth / 5)
        layoutParams = diceParams
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

}

