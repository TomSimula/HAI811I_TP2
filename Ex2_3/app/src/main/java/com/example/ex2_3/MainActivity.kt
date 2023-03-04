package com.example.ex2_3

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
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
        accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometerSensor == null){
            myText.text = "No Accelerometre Sensor"
        } else {
            myText.text = "Accelerometre Sensor found and on listening"
            mSensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            val myText = findViewById<TextView>(R.id.accelerate)
            val myLayout = findViewById<ConstraintLayout>(R.id.myLayout)
            val acceleration = event.values[0] + event.values[1] + event.values[2]
            myText.text = "Average = 9,807m/s2 \n" + acceleration.toString() + "m/s2"
            if (acceleration > 15){
                myLayout.setBackgroundColor(Color.BLACK)
            } else if (acceleration < 5){
                myLayout.setBackgroundColor(Color.GREEN)
            } else {
                myLayout.setBackgroundColor(Color.RED)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


}
