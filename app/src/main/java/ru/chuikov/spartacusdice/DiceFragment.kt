package ru.chuikov.spartacusdice

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.chuikov.spartacusdice.databinding.FragmentDiceBinding

class DiceFragment : Fragment() {
    private lateinit var binding:FragmentDiceBinding
    private lateinit var mainViewModel:MainViewModel

    private val durationOfAnimation: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiceBinding.inflate(inflater,container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

//        Toast.makeText(requireContext(),"${mainViewModel.redCubes.value?:"Empty"}",Toast.LENGTH_LONG).show()
//        printCubes(CubeColor.Red,binding.redCubes,binding.redCount, mainViewModel.redCubes.value?: listOf())
//        printCubes(CubeColor.Black,binding.blackCubes,binding.blackCount, mainViewModel.blackCubes.value?: listOf())
//        printCubes(CubeColor.Blue,binding.blueCubes,binding.blueCount, mainViewModel.blueCubes.value?: listOf())

        //Black cubes
        binding.blackCubesInc.setImageDrawable(
            Drawable.createFromStream(
                context?.assets?.open("blackArrowUp.png"),
                "blackArrowUp.png"
            )
        )
        binding.blackCubesDec.setImageDrawable(
            Drawable.createFromStream(
                context?.assets?.open("blackArrowDown.png"),
                "blackArrowDown.png"
            )
        )
        //start black observer
        mainViewModel.blackCubes.observe(viewLifecycleOwner) {
            printCubes(CubeColor.Black,binding.blackCubes,binding.blackCount,it)
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
                context?.assets?.open("blueArrowUp.png"),
                "blueArrowUp.png"
            )
        )
        binding.blueCubesDec.setImageDrawable(
            Drawable.createFromStream(
                context?.assets?.open("blueArrowDown.png"),
                "blueArrowDown.png"
            )
        )
        //start blue observer
        mainViewModel.blueCubes.observe(viewLifecycleOwner) {
            printCubes(CubeColor.Blue,binding.blueCubes,binding.blueCount,it)
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
                context?.assets?.open("redArrowUp.png"),
                "redArrowUp.png"
            )
        )
        binding.redCubesDec.setImageDrawable(
            Drawable.createFromStream(
                context?.assets?.open("redArrowDown.png"),
                "redArrowDown.png"
            )
        )
        //start red observer
        mainViewModel.redCubes.observe(viewLifecycleOwner) {
            printCubes(CubeColor.Red,binding.redCubes,binding.redCount,it)
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

        return binding.root
    }

    private fun printCubes(cubeColor: CubeColor, parent:LinearLayout,text:TextView , list: List<Int>){
        ObjectAnimator.ofFloat(
            parent,
            View.ALPHA, 0f, 1f).apply {
            interpolator = AccelerateInterpolator()
            duration = durationOfAnimation
            start()
        }
        text.text = "${list.size}"
        parent.removeAllViews()
        list.forEachIndexed { index, i ->
            val view = generateCube(requireContext(),cubeColor,i, parent.measuredWidth
            ){
                mainViewModel.rollTheDiceByIndex(cubeColor,index)
            }
            parent.addView(view)
        }
    }

}