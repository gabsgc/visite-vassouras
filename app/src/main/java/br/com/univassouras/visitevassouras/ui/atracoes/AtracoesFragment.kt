package br.com.univassouras.visitevassouras.ui.atracoes

import android.content.Intent
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
    private val adapter by lazy { context?.let { AtracoesAdapter(context = it, atracoes = emptyList()) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAtracoesBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        binding?.rvAtracoes?.layoutManager = GridLayoutManager(context, 2)
        binding?.loader?.visibility = View.VISIBLE

        getAtracoes()

        return root
    }

    private fun getAtracoes() {
        GlobalScope.launch {
            val getAtracoes = RetrofitInstance.service.getAtracoes()
            withContext(context = Dispatchers.Main) {
                binding?.loader?.visibility = View.INVISIBLE
                val atracoes =  getAtracoes.body()!!
                withContext(Dispatchers.Main) {
                    binding!!.rvAtracoes.adapter = context?.let { AtracoesAdapter(it, atracoes = atracoes) }
                    binding!!.rvAtracoes.layoutManager = GridLayoutManager(context, 2)
                    adapter?.onItemClicked  = {
                        val intent = Intent(context, DetalhesAtracaoActivity::class.java)
                        intent.putExtra(CHAVE_ATRACAO, it)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}