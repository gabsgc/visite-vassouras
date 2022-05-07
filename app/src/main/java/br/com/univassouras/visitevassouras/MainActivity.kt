package br.com.univassouras.visitevassouras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.univassouras.visitevassouras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val btnComecar = binding.btnComecar

        btnComecar.setOnClickListener {
           val intent = Intent(this, HomeActivity::class.java)
           startActivity(intent)
        }

    }
}