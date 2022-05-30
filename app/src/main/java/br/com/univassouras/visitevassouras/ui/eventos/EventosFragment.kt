package br.com.univassouras.visitevassouras.ui.eventos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.univassouras.visitevassouras.databinding.FragmentEventosBinding
import br.com.univassouras.visitevassouras.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventosFragment : Fragment() {

    private var binding: FragmentEventosBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventosBinding.inflate(inflater, container,false)
        val root: View = binding!!.root

        binding?.rvEventos?.layoutManager = LinearLayoutManager(context)
        binding?.loader?.visibility = View.VISIBLE

        getEventos()

        return root
    }

    private fun getEventos() {
        GlobalScope.launch {
            val getEventos = RetrofitInstance.service.getEventos()
            withContext(Dispatchers.Main) {
                binding?.loader?.visibility = View.INVISIBLE
                val eventos = getEventos.body()!!
                withContext(Dispatchers.Main){
                    binding?.rvEventos?.adapter = context?.let { EventosAdapter (it, eventos) }
                    binding?.rvEventos?.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}