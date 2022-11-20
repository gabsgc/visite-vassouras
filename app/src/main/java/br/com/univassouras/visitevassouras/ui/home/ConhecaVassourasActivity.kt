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
        "https://docs.google.com/uc?id=1lgslCx-IeZJo-ZO2gAb4pKiqW_fekL7f",
        "https://docs.google.com/uc?id=1cykPm9adt2Ggt29IkA-fOSljkZcC-GGM",
        "https://docs.google.com/uc?id=1cgk--gOlMGYlebDDYY7Oa8m2lupMKAu7",
        "https://docs.google.com/uc?id=1dAQME_1wYGzjea-EIH_qZlro4Npl1OKK",
        "https://docs.google.com/uc?id=1d7jLl7GlW1v4NQoLx-vIvYPl8C99J1y7"
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