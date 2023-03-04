package com.example.ex6

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myText = findViewById<TextView>(R.id.monText)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (accelerometerSensor == null){
            myText.text = "No Accelerometre Sensor"
        } else {
            myText.text = "Proximity Sensor found and on listening"
            mSensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_PROXIMITY){
            val monText = findViewById<TextView>(R.id.proximity)
            val monImage = findViewById<ImageView>(R.id.monImage)
            val distance = event.values[0]
            if (distance < 5){
                monImage.setImageDrawable(getDrawable(R.drawable.ic_baseline_signal_cellular_alt_24))
                monText.text = "Near : $distance cm"
            } else {
                monImage.setImageDrawable(getDrawable(R.drawable.ic_baseline_signal_cellular_alt_1_bar_24))
                monText.text = "Far : $distance cm"
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}