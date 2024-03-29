package br.com.univassouras.visitevassouras.ui.contato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.univassouras.visitevassouras.databinding.FragmentContatoBinding

class ContatoFragment : Fragment() {
    private var _binding: FragmentContatoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContatoBinding.inflate(inflater, container,false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}