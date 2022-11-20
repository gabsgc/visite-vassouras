package br.com.univassouras.visitevassouras

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.univassouras.visitevassouras.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if(!isNetworkAvailable(this)) {
            Handler(Looper.getMainLooper()).postDelayed({
                AlertDialog.Builder(this)
                    .setTitle("Sem conexão com a internet")
                    .setMessage("Por favor, verifique sua conexão e tente novamente.")
                    .setPositiveButton("OK") { _, _ ->
                        finish()
                    }
                    .setIcon(R.drawable.ic_dialog_alert)
                    .show()
            }, 3000)
        } else {
            loadSplashScreen()
        }

    }

    private fun loadSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        val conMan = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return conMan.activeNetworkInfo != null && conMan.activeNetworkInfo!!.isConnected
    }

}