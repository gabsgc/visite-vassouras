package br.com.univassouras.visitevassouras.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import br.com.univassouras.visitevassouras.R
import br.com.univassouras.visitevassouras.databinding.ActivityConhecaVassourasBinding
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.ImageListener

class ConhecaVassourasActivity : AppCompatActivity() {
    private val binding by lazy { ActivityConhecaVassourasBinding.inflate(layoutInflater) }
    var sampleImages = arrayOf(
        "https://1.bp.blogspot.com/-_SJ9Cx9tAUA/Xac6uk16TOI/AAAAAAAAiZg/awz2GRN71WY_jTy_qGVCfHVDwgkKdJDbQCLcBGAsYHQ/s1600/praca-barao-do-campo-belo-vassouras-rj.jpg",
        "https://s2.glbimg.com/pAXsV8xzuoPro89DnYsZQxEo94I=/0x0:5184x3456/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2021/8/f/aa8tIaSja4ILN171BdVw/imagem.jpg",
        "https://www.viajali.com.br/wp-content/uploads/2020/10/vassouras-rj-5.jpg",
        "https://www.viajali.com.br/wp-content/uploads/2020/10/vassouras-rj-10.jpg",
        "https://blisshotelvassouras.com.br/imgs/fazenda-do-secretario.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Conheça Vassouras"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val carouselView = binding.carouselView
        carouselView.pageCount = sampleImages.size
        carouselView.setImageListener(imageListener)

        binding.tvSobreCidadeHistorica.text = getString(R.string.sobre_historica)
        binding.tvSobreCidadeTuristica.text = getString(R.string.sobre_turistica)
        binding.tvSobreCidadeUniversitaria.text = getString(R.string.sobre_universitaria)

        binding.mbQueroVisitarVassouras.setOnClickListener {
            val url = "https://goo.gl/maps/xfr7KVRxtoKE1cpKA"
            val uri = url.toUri()
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(uri.toString())
            startActivity(openURL)
        }

    }

    var imageListener: ImageListener = ImageListener { position, imageView ->
        Picasso.get().load(sampleImages[position]).into(imageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}