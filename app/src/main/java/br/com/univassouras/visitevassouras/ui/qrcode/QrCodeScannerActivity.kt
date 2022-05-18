package br.com.univassouras.visitevassouras.ui.qrcode

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.univassouras.visitevassouras.databinding.ActivityQrCodeScannerBinding
import com.budiyev.android.codescanner.*

private const val CAMERA_REQUEST_CODE = 101

class QrCodeScannerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityQrCodeScannerBinding.inflate(layoutInflater) }
    private val scannerView by lazy { binding.scannerView }
    private val codeScanner by lazy { CodeScanner(this, scannerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Leitor de Qr Code"

        setupPermissions()
        openCodeScanner()
    }

    private fun openCodeScanner() {
        codeScanner.apply {
            codeScanner.camera = CodeScanner.CAMERA_BACK
            codeScanner.formats = CodeScanner.ALL_FORMATS
            codeScanner.autoFocusMode = AutoFocusMode.SAFE
            codeScanner.scanMode = ScanMode.CONTINUOUS
            codeScanner.isAutoFocusEnabled = true
            codeScanner.isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    binding.tvQrCodeScannerResult.text = it.text
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Toast.makeText(
                        this@QrCodeScannerActivity,
                        "Camera initialization error: ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            CAMERA_REQUEST_CODE -> {
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@QrCodeScannerActivity, "Você precisa permitir o acesso à câmera para usar ler o QrCode.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}