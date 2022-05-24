package br.com.univassouras.visitevassouras.ui.atracoes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.univassouras.visitevassouras.databinding.ItemAtracoesListBinding
import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import com.squareup.picasso.Picasso

class AtracoesAdapter(
    private val atracoes: ArrayList<AtracaoResponse>,
    //private val onItemClicked: OnItemClickListener,
    //private val getContent: ActivityResultLauncher<Atracao>
) : RecyclerView.Adapter<AtracoesAdapter.AtracoesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtracoesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AtracoesViewHolder(ItemAtracoesListBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AtracoesViewHolder, position: Int) {
        val item = atracoes[position]
        holder.bind(item)

//        holder.itemView.setOnClickListener {
//            onItemClicked.onItemClick()
//           // getContent.launch(item)
//        }
    }

    override fun getItemCount(): Int = atracoes.size

    inner class AtracoesViewHolder(private val binding: ItemAtracoesListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(atracao: AtracaoResponse?) {
            if (atracao != null) {
                binding.tvTituloAtracaoItem.text = atracao.nome
                Picasso.get().load(atracao.imagemPrincipal).into(binding.ivAtracao)
            }
        }
    }

}