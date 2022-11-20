package br.com.univassouras.visitevassouras.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.univassouras.visitevassouras.databinding.FragmentHomeBinding
import br.com.univassouras.visitevassouras.retrofit.RetrofitInstance
import br.com.univassouras.visitevassouras.ui.atracoes.AtracoesAdapter
import br.com.univassouras.visitevassouras.ui.atracoes.CHAVE_ATRACAO
import br.com.univassouras.visitevassouras.ui.atracoes.DetalhesAtracaoActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val adapter by lazy { context?.let { HomeAtracoesAdapter(context = it, atracoes = emptyList()) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        val banner = "https://docs.google.com/uc?id=1WiPlKb5ehqfuwyONvOw_h1yYQGkjTouE"
        Picasso.get().load(banner).into(binding?.ivSobreVassouras)

        binding?.rvAtracoesHome?.layoutManager = LinearLayoutManager(context)
        binding?.rvEventosHome?.layoutManager = LinearLayoutManager(context)
        binding?.loader?.visibility = View.VISIBLE
        binding?.loaderEventos?.visibility = View.VISIBLE
        getAtracoes()
        getEventos()

        binding?.mbConhecaVassouras?.setOnClickListener {
            val intent = Intent(context, ConhecaVassourasActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    private fun getAtracoes() {
        GlobalScope.launch {
            val getAtracoes = RetrofitInstance.service.getAtracoes()
            withContext(context = Dispatchers.Main) {
                binding?.loader?.visibility = View.INVISIBLE
                val atracoes =  getAtracoes.body()!!
                withContext(Dispatchers.Main) {
                    binding!!.rvAtracoesHome.adapter = context?.let { AtracoesAdapter(it, atracoes = atracoes) }
                    binding!!.rvAtracoesHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter?.onItemClicked  = {
                        val intent = Intent(context, DetalhesAtracaoActivity::class.java)
                        intent.putExtra(CHAVE_ATRACAO, it)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun getEventos() {
        GlobalScope.launch {
            val getEventos = RetrofitInstance.service.getEventos()
            withContext(Dispatchers.Main) {
                binding?.loaderEventos?.visibility = View.INVISIBLE
                val eventos = getEventos.body()!!
                withContext(Dispatchers.Main){
                    binding?.rvEventosHome?.adapter = context?.let { HomeEventosAdapter (it, eventos) }
                    binding?.rvEventosHome?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}