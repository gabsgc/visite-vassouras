package br.com.univassouras.visitevassouras.ui.hoteis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.univassouras.visitevassouras.databinding.FragmentHoteisBinding

class HoteisFragment : Fragment() {

    private var _binding: FragmentHoteisBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val hotelViewModel =
            ViewModelProvider(this).get(HoteisViewModel::class.java)

        _binding = FragmentHoteisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvHotel
        hotelViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}