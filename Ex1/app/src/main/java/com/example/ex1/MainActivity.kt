package com.example.ex1

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var mSensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL)
        var sensorDesc = StringBuffer()
        for(sensor in sensorList){
            sensorDesc.append("New sensor detected : \r\n")
            sensorDesc.append("\tName: " + sensor.name + "\r\n")
            sensorDesc.append("\tType: " + sensor.type + "\r\n")
            sensorDesc.append("\tVersion: " + sensor.version + "\r\n")
            sensorDesc.append("\tResolution (in the sensor unit): " + sensor.resolution + "\r\n")
            sensorDesc.append("\tPower in mA used by this sensor while in use" + sensor.power +"\r\n")
            sensorDesc.append("\tVendor: " + sensor.vendor + "\r\n")
            sensorDesc.append("\tMaximum range of the sensor in the sensor's unit." + sensor.maximumRange + "\r\n")
            sensorDesc.append("\tMinimum delay allowed between two events is " + sensor.minDelay + " microsecond\r\n")
        }
        findViewById<TextView>(R.id.sensor).text = sensorDesc.toString()

    }
}