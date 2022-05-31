package br.com.univassouras.visitevassouras.ui.sobre

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import br.com.univassouras.visitevassouras.R
import br.com.univassouras.visitevassouras.databinding.FragmentSobreVassourasBinding

class SobreVassourasFragment : Fragment() {
    private var binding: FragmentSobreVassourasBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSobreVassourasBinding.inflate(inflater, container,false)
        val root: View = binding!!.root

        binding!!.tvSobreCidadeHistorica.text = getString(R.string.sobre_historica)
        binding!!.tvSobreCidadeTuristica.text = getString(R.string.sobre_turistica)
        binding!!.tvSobreCidadeUniversitaria.text = getString(R.string.sobre_universitaria)

        binding!!.mbQueroVisitarVassouras.setOnClickListener {
            val url = "https://goo.gl/maps/xfr7KVRxtoKE1cpKA"
            val uri = url.toUri()
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(uri.toString())
            startActivity(openURL)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}