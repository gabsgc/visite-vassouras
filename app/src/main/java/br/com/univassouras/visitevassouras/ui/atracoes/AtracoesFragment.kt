package br.com.univassouras.visitevassouras.ui.atracoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.univassouras.visitevassouras.databinding.FragmentAtracoesBinding
import br.com.univassouras.visitevassouras.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AtracoesFragment : Fragment() {
    private var binding: FragmentAtracoesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAtracoesBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        binding?.rvAtracoes?.layoutManager = GridLayoutManager(context, 2)

        GlobalScope.launch {
            val getAtracoes = RetrofitInstance.service.getAtracoes()
            withContext(context = Dispatchers.Main) {
                val atracoesAdapter : AtracoesAdapter =
                    getAtracoes.body()?.let { AtracoesAdapter(it) }!!
                withContext(Dispatchers.Main) {
                    binding!!.rvAtracoes.adapter = atracoesAdapter
                    binding!!.rvAtracoes.layoutManager = GridLayoutManager(context, 2)
                }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun onItemClick(){}
}