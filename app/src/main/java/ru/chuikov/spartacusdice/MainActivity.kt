package ru.chuikov.spartacusdice

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import ru.chuikov.spartacusdice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        binding.blackCubesInc.setImageDrawable(Drawable.createFromStream(assets.open("blackArrowUp.png"),"blackArrowUp.png"))
        binding.blackCubesDec.setImageDrawable(Drawable.createFromStream(assets.open("blackArrowDown.png"),"blackArrowDown.png"))
        mainViewModel.blackCubes.observe(this){
            binding.blackCount.text = "$it"
        }
        binding.blackCubesInc.setOnClickListener {
            val value = mainViewModel.blackCubes.value?:3
            mainViewModel.blackCubes.value = if ((value+1)>5) 5 else value+1
        }
        binding.blackCubesDec.setOnClickListener {
            val value = mainViewModel.blackCubes.value?:3
            mainViewModel.blackCubes.value = if ((value-1)<1) 1 else value-1
        }

        binding.blueCubesInc.setImageDrawable(Drawable.createFromStream(assets.open("blueArrowUp.png"),"blueArrowUp.png"))
        binding.blueCubesDec.setImageDrawable(Drawable.createFromStream(assets.open("blueArrowDown.png"),"blueArrowDown.png"))
        mainViewModel.blueCubes.observe(this){
            binding.blueCount.text = "$it"
        }
        binding.blueCubesInc.setOnClickListener {
            val value = mainViewModel.blueCubes.value?:3
            mainViewModel.blueCubes.value = if ((value+1)>5) 5 else value+1
        }
        binding.blueCubesDec.setOnClickListener {
            val value = mainViewModel.blueCubes.value?:3
            mainViewModel.blueCubes.value = if ((value-1)<1) 1 else value-1
        }

        binding.redCubesInc.setImageDrawable(Drawable.createFromStream(assets.open("redArrowUp.png"),"redArrowUp.png"))
        binding.redCubesDec.setImageDrawable(Drawable.createFromStream(assets.open("redArrowDown.png"),"redArrowDown.png"))
        mainViewModel.redCubes.observe(this){
            binding.redCount.text = "$it"
        }
        binding.redCubesInc.setOnClickListener {
            val value = mainViewModel.redCubes.value?:3
            mainViewModel.redCubes.value = if ((value+1)>5) 5 else value+1
        }
        binding.redCubesDec.setOnClickListener {
            val value = mainViewModel.redCubes.value?:3
            mainViewModel.redCubes.value = if ((value-1)<1) 1 else value-1
        }


//        val layoutParams1 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT,1f)
//        layoutParams1.setMargins(10)
//        for (i in 1..7){
//            binding.blueCubes.addView(TextView(this).apply {
//                text = "$i text"
//                layoutParams = layoutParams1
//                setBackgroundColor(Color.rgb(Random.nextInt(255),Random.nextInt(255),Random.nextInt(255),))
//                gravity = Gravity.CENTER
//
//                //setPadding(10,10,10,10)
//            //textAlignment = View.TEXT_ALIGNMENT_CENTER
//            })
//        }




    }

}