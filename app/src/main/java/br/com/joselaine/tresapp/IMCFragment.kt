package br.com.joselaine.tresapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.joselaine.tresapp.databinding.FragmentIMCBinding
import java.text.NumberFormat


class IMCFragment : Fragment() {

    private var binding: FragmentIMCBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIMCBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.button?.setOnClickListener {
            if (binding?.altura?.text.toString() == "0" || binding?.peso?.text.toString() == "0"
            ) {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val altura = binding?.altura?.text.toString().toDouble() / 100
                val peso = binding?.peso?.text.toString().toDouble()
                val altura2 = altura * altura
                val imc = peso / altura2
                binding?.imc?.text =   imc.format(2).toString()

                val textResult = when {
                    imc < 10 -> "Desnutrição Grau V"
                    imc in 10.0..12.99 -> "Desnutrição Grau IV"
                    imc in 13.0..15.99 -> "Desnutrição Grau III"
                    imc in 16.0..16.99 -> "Desnutrição Grau II"
                    imc in 17.0..18.49 -> "Desnutrição Grau I"
                    imc in 18.5..24.99 -> "Normal"
                    imc in 25.0..29.99 -> "Pré-obesidade"
                    imc in 30.0..34.59 -> "Obesidade Grau I"
                    imc in 34.6..39.99 -> "Obesidade Grau II"
                    imc >= 40 -> "Obesidade Grau III"
                    else -> ""
                }

                binding?.tipoObesidade?.text = textResult

                binding?.result?.isVisible = true
                binding?.imc?.isVisible = true
                binding?.tipoObesidade?.isVisible = true

            }
        }

        binding?.seekAltura?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding?.altura?.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        binding?.add?.setOnClickListener {
            val valorAtual = binding?.peso?.text.toString().toDouble()
            val novoValor = valorAtual + 1
            binding?.peso?.setText(novoValor.toString())
        }

        binding?.minus?.setOnClickListener {
            val valorAtual = binding?.peso?.text.toString().toDouble()
            val novoValor = valorAtual - 1
            binding?.peso?.setText(novoValor.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this)
}

