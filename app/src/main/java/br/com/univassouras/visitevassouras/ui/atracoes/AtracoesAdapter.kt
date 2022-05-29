package br.com.univassouras.visitevassouras.ui.atracoes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.univassouras.visitevassouras.databinding.ItemAtracoesListBinding
import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import com.squareup.picasso.Picasso

class AtracoesAdapter(
    private val context: Context,
    atracoes: List<AtracaoResponse>,
    var onItemClicked: (atracao: AtracaoResponse) -> Unit = {},
) : RecyclerView.Adapter<AtracoesAdapter.AtracoesViewHolder>() {

    private val atracoes = atracoes.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtracoesViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemAtracoesListBinding.inflate(inflater, parent, false)
        return AtracoesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AtracoesViewHolder, position: Int) {
        val item = atracoes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = atracoes.size

    inner class AtracoesViewHolder(private val binding: ItemAtracoesListBinding) : RecyclerView.ViewHolder(binding.root) {
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
            Picasso.get().load(atracao.imagemPrincipal).into(binding.ivAtracao)

            itemView.setOnClickListener {
                val intent = Intent(context, DetalhesAtracaoActivity::class.java)
                intent.putExtra(CHAVE_ATRACAO, atracao)
                startActivity(context, intent, null)
            }
        }
    }

    fun updateList(atracoes: List<AtracaoResponse>) {
        this.atracoes.clear()
        this.atracoes.addAll(atracoes)
        notifyDataSetChanged()
    }
}