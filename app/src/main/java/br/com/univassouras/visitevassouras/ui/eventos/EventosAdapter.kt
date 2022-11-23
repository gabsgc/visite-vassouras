package br.com.univassouras.visitevassouras.ui.eventos

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import br.com.univassouras.visitevassouras.databinding.ItemEventosListBinding
import br.com.univassouras.visitevassouras.model.evento.EventoResponse

class EventosAdapter(
    private val context: Context,
    eventos: List<EventoResponse>
) : RecyclerView.Adapter<EventosAdapter.EventosViewHolder>() {

    private val eventos = eventos.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventosViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemEventosListBinding.inflate(inflater, parent, false)
        return EventosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventosViewHolder, position: Int) {
        val item = eventos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = eventos.size

    inner class EventosViewHolder(private val binding: ItemEventosListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(evento: EventoResponse) {
            binding.tvTituloEvento.text = evento.titulo
            binding.tvDataInicioEvento.text = evento.dataInicio
            binding.tvDataTerminoEvento.text = evento.dataTermino
            binding.tvHorarioEvento.text = evento.horario
            binding.tvIngressoEvento.text = evento.valor
            binding.tvLocalEvento.text = evento.local

            if (evento.linkIngresso.isNullOrEmpty() || evento.linkIngresso?.isBlank() == true) {
                binding.mbComprarIngressoEvento.visibility = View.INVISIBLE
            } else if(evento.linkIngresso != null) {
                val url = evento.linkIngresso

                binding.mbComprarIngressoEvento.setOnClickListener {
                    val uri = url?.toUri()
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(uri.toString())
                    ContextCompat.startActivity(context, openURL, null)
                }
            }
        }
    }
}