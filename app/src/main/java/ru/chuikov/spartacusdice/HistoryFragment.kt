package ru.chuikov.spartacusdice

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import ru.chuikov.spartacusdice.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        mainViewModel.listOfDiceThrows.observe(viewLifecycleOwner){
            binding.throwsList.adapter = null
            val l = it
            l.reverse()
            val adapter = DiceThrowAdapter(l,requireContext())
            binding.throwsList.adapter = adapter
        }
        binding.clearButton.setOnClickListener {
            mainViewModel.listOfDiceThrows.value = mutableListOf()
        }


        return binding.root
    }



}
    class DiceThrowAdapter(private val list: MutableList<DiceThrow>, private val context: Context): RecyclerView.Adapter<DiceThrowAdapter.DiceThrowViewHolder>() {
        private var inflater: LayoutInflater = LayoutInflater.from(context)

        class DiceThrowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var list:LinearLayout = itemView.findViewById<LinearLayout>(R.id.dice_throw_list)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceThrowViewHolder {
            return DiceThrowViewHolder(inflater.inflate(R.layout.dice_throw_item,parent,false))
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: DiceThrowViewHolder, position: Int) {
            //holder.list.setBackgroundColor(Color.rgb((0..255).random(),(0..255).random(),(0..255).random(),))
            list[position].list.forEachIndexed { index, i ->
                val button = generateCube(
                        context,
                        list[position].cubeColor,
                        i,
                        holder.list.width,
                    ){
                    }
                holder.list.addView(button)
            }

        }
    }