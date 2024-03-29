package br.com.univassouras.visitevassouras.ui.qrcode

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.univassouras.visitevassouras.databinding.FragmentLerQrCodeBinding

class LerQrCodeFragment : Fragment() {
    private var _binding: FragmentLerQrCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLerQrCodeBinding.inflate(inflater, container,false)
        val root: View = binding.root

        val mbLerQrCode = binding.mbScanQrCode

        mbLerQrCode.setOnClickListener {
            val intent = Intent(context, QrCodeScannerActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}