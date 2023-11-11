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

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hellofigma.addchallenge.AddChallenge
import com.example.hellofigma.congrats.Congrats
import com.example.hellofigma.creategame.CreateGame
import com.example.hellofigma.hint1.Hint1
import com.example.hellofigma.hintpage.HintPage
import com.example.hellofigma.mainpage.MainPage
import com.example.hellofigma.ui.theme.HelloFigmaTheme
import java.nio.FloatBuffer
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloFigmaTheme {
                // Set up the NavHost with a NavController
                val navController = rememberNavController()

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
                            HintPage(modifier = Modifier.fillMaxSize(),
                                onHint1 = {(navController.navigate("hintChild"))},
                                onHint2 = {(navController.navigate("hintChild"))},
                                onHint3 = {(navController.navigate("hintChild"))},
                                onHint4 = {(navController.navigate("hintChild"))},
                                onClaim = {(navController.navigate("congratPage"))}
                                )
                    }
                    composable("hintChild") {
                        Hint1(modifier = Modifier.fillMaxSize(),
                            onVector = {},
                            onUnlock = {(navController.navigate("hintPage"))}

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
    private val ortEnvironment = OrtEnvironment.getEnvironment()

    private fun createORTSession(): OrtSession {
        val modelBytes = resources.openRawResource(R.raw.svm_trained_model).readBytes()
        return ortEnvironment.createSession(modelBytes)
    }

    private fun runPrediction(input : Float, ortSession: OrtSession, ortEnvironment: OrtEnvironment) : Float {
        val inputName = ortSession.inputNames?.iterator()?.next()
        val floatBufferInputs = FloatBuffer.wrap( floatArrayOf( input ) )
        val inputTensor = OnnxTensor.createTensor( ortEnvironment , floatBufferInputs , longArrayOf( 1, 1 ) )
        val results = ortSession.run( mapOf( inputName to inputTensor ) )
        val output = results[0].value as Array<FloatArray>
        return output[0][0]
    }
}
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