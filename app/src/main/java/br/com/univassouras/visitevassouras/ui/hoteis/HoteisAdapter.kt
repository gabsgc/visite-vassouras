package br.com.univassouras.visitevassouras.ui.hoteis

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.univassouras.visitevassouras.R
import br.com.univassouras.visitevassouras.databinding.ItemHoteisListBinding
import br.com.univassouras.visitevassouras.model.hotel.HotelResponse
import com.squareup.picasso.Picasso

class HoteisAdapter(
    private val context: Context,
    hoteis: List<HotelResponse>
) : RecyclerView.Adapter<HoteisAdapter.HoteisViewHolder>() {

    private val hoteis = hoteis.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoteisViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemHoteisListBinding.inflate(inflater, parent, false)
        return HoteisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HoteisViewHolder, position: Int) {
        val hoteisOrdenados = hoteis.sortedBy { it.nome }
        val item = hoteisOrdenados[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = hoteis.size

    inner class HoteisViewHolder(private val binding: ItemHoteisListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel: HotelResponse) {
            binding.tvTituloHotelItem.text = hotel.nome
            if (hotel.imagem.isNullOrEmpty() || hotel.imagem?.isBlank() == true) {
                Picasso.get().load(R.drawable.placeholder_hoteis).into(binding.ivImagemHotel)
            } else {
                Picasso.get().load(hotel.imagem).into(binding.ivImagemHotel)
            }
            binding.ivImagemHotel.contentDescription = hotel.nome

            if (hotel.url.isNullOrEmpty() || hotel.url?.isBlank() == true) {
                binding.mbAcessarSiteHotel.visibility = View.INVISIBLE
            } else {
                val url = hotel.url
                binding.mbAcessarSiteHotel.setOnClickListener {
                    val uri = url?.toUri()
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(uri.toString())
                    ContextCompat.startActivity(context, openURL, null)
                }
            }
        }
    }
}