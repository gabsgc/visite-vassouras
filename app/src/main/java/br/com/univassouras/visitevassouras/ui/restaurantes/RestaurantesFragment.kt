package br.com.univassouras.visitevassouras.ui.restaurantes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.univassouras.visitevassouras.databinding.FragmentRestaurantesBinding
import br.com.univassouras.visitevassouras.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RestaurantesFragment : Fragment() {
    private var binding: FragmentRestaurantesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantesBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        binding?.rvRestaurantes?.layoutManager = LinearLayoutManager(context)
        binding?.loader?.visibility = View.VISIBLE

        getRestaurantes()

        return root
    }

    private fun getRestaurantes() {
        GlobalScope.launch {
            val getRestaurantes = RetrofitInstance.service.getRestaurantes()
            withContext(Dispatchers.Main) {
                binding?.loader?.visibility = View.INVISIBLE
                val restaurantes = getRestaurantes.body()!!
                withContext(Dispatchers.Main) {
                    binding?.rvRestaurantes?.adapter = context?.let { RestaurantesAdapter(it, restaurantes) }
                    binding?.rvRestaurantes?.layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}