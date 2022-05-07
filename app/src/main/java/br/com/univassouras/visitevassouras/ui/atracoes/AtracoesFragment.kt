package br.com.univassouras.visitevassouras.ui.atracoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.univassouras.visitevassouras.databinding.FragmentAtracoesBinding

class AtracoesFragment : Fragment() {

    private var _binding: FragmentAtracoesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(AtracoesViewModel::class.java)

        _binding = FragmentAtracoesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAtracoes
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}