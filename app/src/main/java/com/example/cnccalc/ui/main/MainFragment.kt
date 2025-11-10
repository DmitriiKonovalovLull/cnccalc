package com.example.cnccalc.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cnccalc.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.btnTools.setOnClickListener {
            // TODO: Переход к инструментам
        }

        binding.btnAssistant.setOnClickListener {
            // TODO: Переход к ассистенту
        }

        binding.btnDrawing.setOnClickListener {
            // TODO: Переход к анализу чертежей
        }

        binding.btnMachines.setOnClickListener {
            // TODO: Переход к станкам
        }

        binding.btnMaterials.setOnClickListener {
            // TODO: Переход к материалам
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}