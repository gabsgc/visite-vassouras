package br.com.univassouras.visitevassouras.ui.eventos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.univassouras.visitevassouras.databinding.FragmentEventosBinding

class EventosFragment : Fragment() {

    private var _binding: FragmentEventosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val eventoViewModel = ViewModelProvider(this).get(EventosViewModel::class.java)

        _binding = FragmentEventosBinding.inflate(inflater, container,false)
        val root: View = binding.root

        val textView: TextView = binding.tvEvento
        eventoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}