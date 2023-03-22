package com.example.tableapp.ui.main

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.tableapp.databinding.MainFragmentBinding
import com.google.android.material.textfield.TextInputEditText

class MainFragment : Fragment() {

    private val viewModel = MainViewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val range = viewModel.pointRange
    private var player1 = emptyList<TextView>()
    private var player2 = emptyList<TextView>()
    private var player3 = emptyList<TextView>()
    private var player4 = emptyList<TextView>()
    private var player5 = emptyList<TextView>()
    private var player6 = emptyList<TextView>()
    private var player7 = emptyList<TextView>()
    private var sumList = emptyList<TextView>()
    private var placesList = emptyList<TextView>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLists()
        bind()
    }

    private fun bind() {
        onEnterThePoint(binding.player1x2, binding.player2x1)
        onEnterThePoint(binding.player1x3, binding.player3x1)
        onEnterThePoint(binding.player1x4, binding.player4x1)
        onEnterThePoint(binding.player1x5, binding.player5x1)
        onEnterThePoint(binding.player1x6, binding.player6x1)
        onEnterThePoint(binding.player1x7, binding.player7x1)

        onEnterThePoint(binding.player2x3, binding.player3x2)
        onEnterThePoint(binding.player2x4, binding.player4x2)
        onEnterThePoint(binding.player2x5, binding.player5x2)
        onEnterThePoint(binding.player2x6, binding.player6x2)
        onEnterThePoint(binding.player2x7, binding.player7x2)

        onEnterThePoint(binding.player3x4, binding.player4x3)
        onEnterThePoint(binding.player3x5, binding.player5x3)
        onEnterThePoint(binding.player3x6, binding.player6x3)
        onEnterThePoint(binding.player3x7, binding.player7x3)

        onEnterThePoint(binding.player4x5, binding.player5x4)
        onEnterThePoint(binding.player4x6, binding.player6x4)
        onEnterThePoint(binding.player4x7, binding.player7x4)

        onEnterThePoint(binding.player5x6, binding.player6x5)
        onEnterThePoint(binding.player5x7, binding.player7x5)

        onEnterThePoint(binding.player6x7, binding.player7x6)
    }

    private fun onEnterThePoint(editText: TextInputEditText, textView: TextView) {
        editText.doOnTextChanged { _, _, _, _ ->
            val text = editText.text.toString()
            if (text.toIntOrNull() != null) {
                if (text.toIntOrNull() in range) {
                    text.let {
                        editText.setTextColor(Color.BLACK)
                        editText.setSelection(editText.text!!.length)
                        textView.text = calculateThePoint(it)
                        checkRow(editText)
                    }
                } else {
                    editText.setTextColor(Color.RED)
                    Toast.makeText(requireContext(), "wrong data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun calculateThePoint(point: String): String {
        if (point.toIntOrNull() != null) {
            return viewModel.calculateThePoint(point.toInt()).toString()
        } else return "X"
    }

    private fun checkRow(editText: TextInputEditText) {

        when (editText.tag) {
            "1" -> {
                if (isNotEmpty(player1)) {
                    val sum = viewModel.calculateTheSum(player1.map { it.text.toString().toInt() })
                    binding.player1sum.text = sum.toString()
                }
            }
            "2" -> {
                if (isNotEmpty(player2)) {
                    val sum = viewModel.calculateTheSum(player2.map { it.text.toString().toInt() })
                    binding.player2sum.text = sum.toString()
                }
            }
            "3" -> {
                if (isNotEmpty(player3)) {
                    val sum = viewModel.calculateTheSum(player3.map { it.text.toString().toInt() })
                    binding.player3sum.text = sum.toString()
                }
            }
            "4" -> {
                if (isNotEmpty(player4)) {
                    val sum = viewModel.calculateTheSum(player4.map { it.text.toString().toInt() })
                    binding.player4sum.text = sum.toString()
                }
            }
            "5" -> {
                if (isNotEmpty(player5)) {
                    val sum = viewModel.calculateTheSum(player5.map { it.text.toString().toInt() })
                    binding.player5sum.text = sum.toString()
                }
            }
            "6" -> {
                if (isNotEmpty(player6)) {
                    val sum = viewModel.calculateTheSum(player6.map { it.text.toString().toInt() })
                    binding.player6sum.text = sum.toString()
                }
            }
        }
        if (isNotEmpty(player7) && !player7.map { it.text.toString() == "X" }.contains(true)) {
            val sum = viewModel.calculateTheSum(player7.map { it.text.toString().toInt() })
            binding.player7sum.text = sum.toString()
        }
        if (isNotEmpty(sumList)) {
            calculateThePlace(sumList)
        }
    }

    private fun isNotEmpty(list: List<TextView>): Boolean {
        return !list.map { it.text.isNotEmpty() }.contains(false)
    }

    private fun calculateThePlace(list: List<TextView>) {
        val sorted = viewModel.calculateThePlace(list.map { it.text.toString().toInt() })
        placesList.mapIndexed { index, textView -> textView.text = sorted[index] }
    }

    private fun initLists() {
        player1 = listOf<TextView>(
            binding.player1x2,
            binding.player1x3,
            binding.player1x4,
            binding.player1x5,
            binding.player1x6,
            binding.player1x7,
        )
        player2 = listOf<TextView>(
            binding.player2x1,
            binding.player2x3,
            binding.player2x4,
            binding.player2x5,
            binding.player2x6,
            binding.player2x7,
        )
        player3 = listOf<TextView>(
            binding.player3x1,
            binding.player3x2,
            binding.player3x4,
            binding.player3x5,
            binding.player3x6,
            binding.player3x7,
        )
        player4 = listOf<TextView>(
            binding.player4x1,
            binding.player4x2,
            binding.player4x3,
            binding.player4x5,
            binding.player4x6,
            binding.player4x7,
        )
        player5 = listOf<TextView>(
            binding.player5x1,
            binding.player5x2,
            binding.player5x3,
            binding.player5x4,
            binding.player5x6,
            binding.player5x7,
        )
        player6 = listOf<TextView>(
            binding.player6x1,
            binding.player6x2,
            binding.player6x3,
            binding.player6x4,
            binding.player6x5,
            binding.player6x7,
        )
        player7 = listOf<TextView>(
            binding.player7x1,
            binding.player7x2,
            binding.player7x3,
            binding.player7x4,
            binding.player7x5,
            binding.player7x6,
        )
        sumList = listOf<TextView>(
            binding.player1sum,
            binding.player2sum,
            binding.player3sum,
            binding.player4sum,
            binding.player5sum,
            binding.player6sum,
            binding.player7sum,
        )
        placesList = listOf<TextView>(
            binding.player1place,
            binding.player2place,
            binding.player3place,
            binding.player4place,
            binding.player5place,
            binding.player6place,
            binding.player7place,
        )
    }


}