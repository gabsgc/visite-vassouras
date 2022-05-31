package br.com.univassouras.visitevassouras.ui.restaurantes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.univassouras.visitevassouras.databinding.ItemRestaurantesListBinding
import br.com.univassouras.visitevassouras.model.restaurante.RestauranteResponse
import com.squareup.picasso.Picasso

class RestaurantesAdapter(
    private val context: Context,
    restaurantes: List<RestauranteResponse>
) : RecyclerView.Adapter<RestaurantesAdapter.RestaurantesViewHolder>() {

    private val restaurantes = restaurantes.toMutableList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantesAdapter.RestaurantesViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemRestaurantesListBinding.inflate(inflater, parent, false)
        return RestaurantesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantesViewHolder, position: Int) {
        val item = restaurantes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = restaurantes.size

   inner class RestaurantesViewHolder(private val binding: ItemRestaurantesListBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(restaurante: RestauranteResponse) {
           binding.tvTituloRestaurantetem.text = restaurante.nome
           Picasso.get().load(restaurante.logotipo).into(binding.ivLogoRestaurante)
           binding.ivLogoRestaurante.contentDescription = restaurante.nome
           val url = restaurante.urlSite

           binding.mbAcessarSiteRestaurante.setOnClickListener {
               val uri = url?.toUri()
               val openURL = Intent(Intent.ACTION_VIEW)
               openURL.data = Uri.parse(uri.toString())
               startActivity(context, openURL, null)
           }
       }
    }
}
