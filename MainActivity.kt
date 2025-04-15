package com.example.bolillerobingargentino

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

private val bolillas = mutableListOf < Int > ()
private val historial = mutableListOf < Int > ()

override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)
setContentView(R.layout.activity_main)

val minInput = findViewById < EditText > (R.id.minInput)
val maxInput = findViewById < EditText > (R.id.maxInput)
val startButton = findViewById < Button > (R.id.startButton)
val resetButton = findViewById < Button > (R.id.resetButton)
val ballNumber = findViewById < TextView > (R.id.ballNumber)
val historyText = findViewById < TextView > (R.id.historyText)

startButton.setOnClickListener {
val min = minInput.text.toString().toIntOrNull()
val max = maxInput.text.toString().toIntOrNull()

if (min == null || max == null || min > max) {
Toast.makeText(this, "Revisa el rango ingresado", Toast.LENGTH_SHORT).show()
return@setOnClickListener
}

if (bolillas.isEmpty()) {
bolillas.addAll((min..max))
historial.clear()
}

if (bolillas.isNotEmpty()) {
val index = bolillas.indices.random()
val numero = bolillas.removeAt(index)
historial.add(numero)

val anim = AnimationUtils.loadAnimation(this, R.anim.rotate_ball)
ballNumber.startAnimation(anim)
ballNumber.text = numero.toString()

historyText.text = "Historial: ${historial.joinToString(", ")}"
} else {
ballNumber.text = "-"
Toast.makeText(this, "¡No quedan más bolillas!", Toast.LENGTH_SHORT).show()
}
}

resetButton.setOnClickListener {
bolillas.clear()
historial.clear()
ballNumber.text = "?"
historyText.text = "Historial:"
minInput.text.clear()
maxInput.text.clear()
}
}
}