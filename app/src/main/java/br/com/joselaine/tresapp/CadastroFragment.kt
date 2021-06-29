package br.com.joselaine.tresapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.joselaine.tresapp.databinding.FragmentCadastroBinding
import java.util.*


class CadastroFragment : Fragment() {

    private var binding: FragmentCadastroBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val campo = binding?.campoData
        val cal = Calendar.getInstance()
        val savedYear = cal.get(Calendar.YEAR)
        val savedMonth = cal.get(Calendar.MONTH)
        val savedDay = cal.get(Calendar.DAY_OF_MONTH)
        campo?.keyListener = null

        binding?.campoData?.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireActivity(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    campo?.setText(
                        "$dayOfMonth/${month + 1}/$year"
                    )
                },
                savedYear,
                savedMonth,
                savedDay
            )
            datePickerDialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}