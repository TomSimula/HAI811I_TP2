package com.example.ex4

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myText = findViewById<TextView>(R.id.monText)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        if (accelerometerSensor == null){
            myText.text = "No Accelerometre Sensor"
        } else {
            myText.text = "Linear Accelerometre Sensor found and on listening"
            mSensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION){
            val myText = findViewById<TextView>(R.id.direction)
            val xAcceleration = event.values[0]
            val yAcceleration = event.values[1]
            when {
                xAcceleration > 2 && yAcceleration > 2 -> myText.text = "Haut + Droite"
                xAcceleration > 2 && yAcceleration < -2 -> myText.text = "Bas + Droite"
                xAcceleration < -2 && yAcceleration > 2 -> myText.text = "Haut + Gauche"
                xAcceleration < -2 && yAcceleration < -2 -> myText.text = "Bas + Gauche"
                xAcceleration > 2 -> myText.text = "Droite"
                xAcceleration < -2 -> myText.text = "Gauche"
                yAcceleration > 2 -> myText.text = "Haut"
                yAcceleration < -2 -> myText.text = "Bas"
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}