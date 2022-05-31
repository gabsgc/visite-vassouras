package br.com.univassouras.visitevassouras.ui.hoteis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.univassouras.visitevassouras.databinding.FragmentHoteisBinding
import br.com.univassouras.visitevassouras.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HoteisFragment : Fragment() {
    private var binding: FragmentHoteisBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoteisBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        binding?.rvHoteis?.layoutManager = LinearLayoutManager(context)
        binding?.loader?.visibility = View.VISIBLE

        getHoteis()

        return root
    }

    private fun getHoteis() {
        GlobalScope.launch {
            val getHoteis = RetrofitInstance.service.getHoteis()
            withContext(Dispatchers.Main) {
                binding?.loader?.visibility = View.INVISIBLE
                val hoteis = getHoteis.body()!!
                withContext(Dispatchers.Main) {
                    binding?.rvHoteis?.adapter = context?.let { HoteisAdapter(it, hoteis) }
                    binding?.rvHoteis?.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}