package br.com.univassouras.visitevassouras.ui.atracoes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import br.com.univassouras.visitevassouras.R
import br.com.univassouras.visitevassouras.databinding.ActivityDetalhesAtracaoBinding
import br.com.univassouras.visitevassouras.model.atracao.AtracaoResponse
import com.squareup.picasso.Picasso

class DetalhesAtracaoActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetalhesAtracaoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tryLoadAtracao()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun tryLoadAtracao() {
        intent.getParcelableExtra<AtracaoResponse>(CHAVE_ATRACAO)?.let { atracao ->
            fillInFields(atracao)
        } ?: finish()
    }

    private fun fillInFields(atracao: AtracaoResponse) {
        if (atracao.imgSecundaria.isNullOrEmpty() || atracao.imgSecundaria?.isBlank() == true) {
            if (atracao.imgPrincipal?.isNullOrEmpty() == false &&
                atracao.imgPrincipal?.isBlank() == false) {
                Picasso.get().load(atracao.imgPrincipal).into(binding.ivImagemAtracao)
            } else {
                Picasso.get().load(R.drawable.placeholder_atrativos).into(binding.ivImagemAtracao)
            }
        } else {
            Picasso.get().load(atracao.imgSecundaria).into(binding.ivImagemAtracao)
        }
        binding.tvTituloAtracao.text = atracao.nome
        binding.tvHorarioFuncionamentoAtracao.text = atracao.horarioFuncionamento
        binding.tvEnderecoAtracao.text = atracao.endereco
        binding.tvSobreAtracao.text = atracao.descricao
        binding.tvDiasFuncionamentoAtracao.text = atracao.diasFuncionamento

        title = atracao.nome

        val url = atracao.rota

        binding.mbQueroVisitarAtracao.setOnClickListener {
            val uri = url?.toUri()
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(uri.toString())
            startActivity(openURL)
        }
    }
}
