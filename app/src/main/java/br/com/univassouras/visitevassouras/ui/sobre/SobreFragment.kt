package br.com.univassouras.visitevassouras.ui.sobre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.univassouras.visitevassouras.databinding.FragmentSobreBinding

class SobreFragment : Fragment() {
    private var binding: FragmentSobreBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSobreBinding.inflate(inflater, container,false)
        val root: View = binding!!.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}