package com.example.evaluablespinnerclaudia

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluablespinnerclaudia.databinding.ActivityPaisesBinding

class PaisesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaisesBinding
    private var listaPaises: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    init {
        listaPaises = Paises.nombresPaises.toMutableList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaisesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniciarAdapter()
        iniciarEventos()
    }

    private fun iniciarAdapter() {
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listaPaises
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPaises.adapter = adapter
    }

    private fun iniciarEventos() {
        binding.spinnerPaises.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val paisElegido = parent?.getItemAtPosition(position).toString()
                binding.txtSeleccionado.text = paisElegido
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.txtSeleccionado.text = "No se seleccionó nada"
            }
        }

        binding.btnDuplicar.setOnClickListener {
            val textoActual = binding.txtSeleccionado.text.toString()
            if (textoActual.isNotEmpty()) {
                listaPaises.add(textoActual)
                adapter.notifyDataSetChanged()
            }
        }
    }
}