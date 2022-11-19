package br.com.univassouras.visitevassouras.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.univassouras.visitevassouras.R
import br.com.univassouras.visitevassouras.databinding.ItemAtracoesListHomeBinding
import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import br.com.univassouras.visitevassouras.ui.atracoes.CHAVE_ATRACAO
import br.com.univassouras.visitevassouras.ui.atracoes.DetalhesAtracaoActivity
import com.squareup.picasso.Picasso

class HomeAtracoesAdapter(
    private val context: Context,
    atracoes: List<AtracaoResponse>,
    var onItemClicked: (atracao: AtracaoResponse) -> Unit = {}
) : RecyclerView.Adapter<HomeAtracoesAdapter.AtracoesHomeViewHolder>()  {
    private val atracoes = atracoes.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAtracoesAdapter.AtracoesHomeViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemAtracoesListHomeBinding.inflate(inflater, parent, false)
        return AtracoesHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AtracoesHomeViewHolder, position: Int) {
        val item = atracoes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = atracoes.size

    inner class AtracoesHomeViewHolder(private val binding: ItemAtracoesListHomeBinding) : RecyclerView.ViewHolder(binding.root)  {
        private lateinit var atracao: AtracaoResponse

        init {
            itemView.setOnClickListener {
                if (::atracao.isInitialized) {
                    onItemClicked(atracao)
                }
            }
        }

        fun bind(atracao: AtracaoResponse) {
            this.atracao = atracao
            binding.tvTituloAtracaoItem.text = atracao.nome
            if (atracao.imgPrincipal.isNullOrEmpty()) {
                Picasso.get().load(R.drawable.placeholder_atrativos).into(binding.ivAtracao)
            } else {
                Picasso.get().load(atracao.imgPrincipal).into(binding.ivAtracao)
            }
            binding.ivAtracao.contentDescription = atracao.nome

            itemView.setOnClickListener {
                val intent = Intent(context, DetalhesAtracaoActivity::class.java)
                intent.putExtra(CHAVE_ATRACAO, atracao)
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }
}