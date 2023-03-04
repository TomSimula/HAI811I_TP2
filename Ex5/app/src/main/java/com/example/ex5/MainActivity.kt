package com.example.ex5

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var mSensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor
    private var isLightOn: Boolean = false

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
            mSensorManager.registerListener(this, accelerometerSensor, 2000000)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION){
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val acceleration = x*x + y*y + z*z
            if (acceleration > 200){
                val mCamera = getSystemService(Context.CAMERA_SERVICE) as CameraManager
                val cameraId = mCamera.cameraIdList[0]
                mCamera.setTorchMode(cameraId, !isLightOn)
                isLightOn = !isLightOn
                Toast.makeText(applicationContext, "Shake event detected at $acceleration", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}