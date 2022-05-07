package br.com.univassouras.visitevassouras.ui.restaurantes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.univassouras.visitevassouras.databinding.FragmentRestaurantesBinding

class RestaurantesFragment : Fragment() {

    private var _binding: FragmentRestaurantesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(RestaurantesViewModel::class.java)

        _binding = FragmentRestaurantesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRestaurante
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}