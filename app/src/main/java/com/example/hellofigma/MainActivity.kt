/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hellofigma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellofigma.addchallenge.AddChallenge
import com.example.hellofigma.congrats.Congrats
import com.example.hellofigma.creategame.CreateGame
import com.example.hellofigma.mainpage.MainPage
import com.example.hellofigma.ui.theme.HelloFigmaTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloFigmaTheme {
                // Set up the NavHost with a NavController
                val navController = rememberNavController()
                val challenge1 = "Example data for Hint1"
                val count1 = "0"
                val challenge2 = "Example data for Hint2"
                val count2 = "0"
                val challenge3 = "Example data for Hint3"
                val count3 = "0"
                val challenge4 = "Example data for Hint4"
                val count4 = "0"
                val (hintState, setHintState) = remember {
                    mutableStateOf(
                        HintState(
                            onOpen1 = false,
                            onOpen2 = false,
                            onOpen3 = false,
                            onOpen4 = false
                        )
                    )
                }

                NavHost(
                    navController = navController,
                    startDestination = "mainPage"
                ) {
                    composable("mainPage") {
                            MainPage(
                                modifier = Modifier.fillMaxSize(),
                                onPlayButton = { navController.navigate("hintPage")},
                                onNewButton = {navController.navigate("createGamePage")}
                            )
                    }
                    composable("hintPage") {
                        HintMainFlipPage(
                            modifier = Modifier.fillMaxSize(),
                            onHint1 = {(navController.navigate("hintChild1"))},
                            onHint3 = {(navController.navigate("hintChild3"))},
                            onHint4 = {(navController.navigate("hintChild4"))},
                            onHint2 = {(navController.navigate("hintChild2"))},
                            onClaim = {(navController.navigate("congratPage"))},
                            hintState = hintState,
                        )
                    }

                    composable("hintChild1") {
                        val (imageId, setImageId) = remember { mutableStateOf(R.drawable.hint_1_vector) }

                        HintFlipPage(hintState = hintState,
                            modifier = Modifier.fillMaxSize(),
                            challenge = challenge1,
                            count = count1,
                            onVector = {
                                setImageId(R.drawable.hint_1_6)
                            },
                            onUnlock = {
                                setHintState(hintState.copy(onOpen1 = true))
                                (navController.navigate("hintPage"))} ,
                            initialImageId = imageId

                        )
                    }
                    composable("hintChild2") {
                        val (imageId, setImageId) = remember { mutableStateOf(R.drawable.hint_1_vector) }

                        HintFlipPage(hintState = hintState,modifier = Modifier.fillMaxSize(),
                            challenge = challenge2,
                            count = count2,
                            onVector = {
                                setImageId(R.drawable.hint_1_7)
                            },
                            onUnlock = {
                                setHintState(hintState.copy(onOpen1 = true))
                                (navController.navigate("hintPage"))} ,
                            initialImageId = imageId

                        )
                    }
                    composable("hintChild3") {
                        val (imageId, setImageId) = remember { mutableStateOf(R.drawable.hint_1_vector) }
                        HintFlipPage(hintState = hintState,modifier = Modifier.fillMaxSize(),
                            challenge = challenge3,
                            count = count3,
                            onVector = {
                                setImageId(R.drawable.hint_1_bunny)
                            },
                            onUnlock = {
                                setHintState(hintState.copy(onOpen1 = true))
                                (navController.navigate("hintPage"))} ,
                            initialImageId = imageId

                        )
                    }
                    composable("hintChild4") {
                        val (imageId, setImageId) = remember { mutableStateOf(R.drawable.hint_1_vector) }
                        HintFlipPage(hintState = hintState,
                            modifier = Modifier.fillMaxSize(),
                            challenge = challenge4,
                            count = count4,
                            onVector = {
                                setImageId(R.drawable.hint_1_junction)
                            },
                            onUnlock = {
                                setHintState(hintState.copy(onOpen1 = true))
                                (navController.navigate("hintPage"))} ,
                            initialImageId = imageId

                        )
                    }
                    composable("congratPage") {
                        Congrats(modifier = Modifier.fillMaxSize(),
                            onHome = {(navController.navigate("mainPage"))},
                            onNewGame = {(navController.navigate("createGamePage"))}

                        )
                    }
                    composable("createGamePage") {
                        CreateGame(modifier = Modifier.fillMaxSize(),
                            onAdd = {navController.navigate("mainPage")},
                            onVector = {navController.navigate("addChallenge")},
                            onVector2 = {navController.navigate("addChallenge")},
                            onVector3 = {navController.navigate("addChallenge")},
                            onVector4 = {navController.navigate("addChallenge")}
                            )
                    }
                    composable("addChallenge") {
                        AddChallenge(modifier = Modifier.fillMaxSize(),
                            onAdd = {navController.navigate("createGamePage")})
                    }


                }
            }
        }
    }
    // Start of model
//    val sensorIntent = Intent(this, SensorActivity::class.java)

//    private val ortEnvironment = OrtEnvironment.getEnvironment()
//
//    private fun createORTSession(): OrtSession {
//        val modelBytes = resources.openRawResource(R.raw.svm_trained_model).readBytes()
//        return ortEnvironment.createSession(modelBytes)
//    }
//
//    private fun runPrediction(input : Float, ortSession: OrtSession, ortEnvironment: OrtEnvironment) : Float {
//        val inputName = ortSession.inputNames?.iterator()?.next()
//        val floatBufferInputs = FloatBuffer.wrap( floatArrayOf( input ) )
//        val inputTensor = OnnxTensor.createTensor( ortEnvironment , floatBufferInputs , longArrayOf( 1, 1 ) )
//        val results = ortSession.run( mapOf( inputName to inputTensor ) )
//        val output = results[0].value as Array<FloatArray>
//        return output[0][0]
//    }
}


//
//class SensorActivity : AppCompatActivity() {
//
//    private lateinit var sensorManager: SensorManager
//    private var accelerometerSensor: Sensor? = null
//    private var gyroscopeSensor: Sensor? = null
//    private var linearAccelerationSensor: Sensor? = null
//
//    private val accelerometerListener = object : SensorEventListener {
//        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//            // Not needed for this example
//        }
//
//        override fun onSensorChanged(event: SensorEvent) {
//            // Handle accelerometer data in event.values
//            val x = event.values[0]
//            val y = event.values[1]
//            val z = event.values[2]
//
//            // Process or display the accelerometer data as needed
//        }
//    }
//
//    private val gyroscopeListener = object : SensorEventListener {
//        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//            // Not needed for this example
//        }
//
//        override fun onSensorChanged(event: SensorEvent) {
//            // Handle gyroscope data in event.values
//            val x = event.values[0]
//            val y = event.values[1]
//            val z = event.values[2]
//
//            // Process or display the gyroscope data as needed
//        }
//    }
//
//    private val linearAccelerationListener = object : SensorEventListener {
//        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//            // Not needed for this example
//        }
//
//        override fun onSensorChanged(event: SensorEvent) {
//            // Handle linear acceleration data in event.values
//            val x = event.values[0]
//            val y = event.values[1]
//            val z = event.values[2]
//
//            // Process or display the linear acceleration data as needed
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//
//        // Get the sensors
//        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
//        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
//        linearAccelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        // Register sensor listeners
//        accelerometerSensor?.let {
//            sensorManager.registerListener(
//                accelerometerListener,
//                it,
//                SensorManager.SENSOR_DELAY_NORMAL
//            )
//        }
//
//        gyroscopeSensor?.let {
//            sensorManager.registerListener(
//                gyroscopeListener,
//                it,
//                SensorManager.SENSOR_DELAY_NORMAL
//            )
//        }
//
//        linearAccelerationSensor?.let {
//            sensorManager.registerListener(
//                linearAccelerationListener,
//                it,
//                SensorManager.SENSOR_DELAY_NORMAL
//            )
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//
//        // Unregister sensor listeners
//        sensorManager.unregisterListener(accelerometerListener)
//        sensorManager.unregisterListener(gyroscopeListener)
//        sensorManager.unregisterListener(linearAccelerationListener)
//    }
//}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloFigmaTheme {
        // Main composition
        Box(

        ) {
            MainPage(modifier =Modifier.fillMaxSize())//, rememberNavController())

        }
    }
}