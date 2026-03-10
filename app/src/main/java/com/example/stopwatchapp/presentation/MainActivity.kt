    /* While this template provides a good starting point for using Wear Compose, you can always
     * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
     * most up to date changes to the libraries and their usages.
     */

    package com.example.stopwatchapp.presentation


    import android.os.Bundle
    import android.widget.Space
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.viewModels
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Pause
    import androidx.compose.material.icons.filled.PlayArrow
    import androidx.compose.material.icons.filled.Stop
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.lifecycle.compose.collectAsStateWithLifecycle
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.wear.compose.material3.Button
    import androidx.wear.compose.material3.ButtonDefaults
    import androidx.wear.compose.material3.Icon
    import androidx.wear.compose.material3.MaterialTheme
    import androidx.wear.compose.material3.Text
    import androidx.wear.compose.material3.*

    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {

                val viewModel = viewModel<StopWatchViewModel>()
                val timerState by viewModel.timerState.collectAsStateWithLifecycle()
                val stopWatchText by viewModel.stopWatchText.collectAsStateWithLifecycle()



                            /**
                             *
                             * ✅ Correct Code (Material3 Wear OS)
                             *
                             * Just use TimeText() without styling:
                             *
                             * ScreenScaffold(
                             *     timeText = {
                             *         TimeText()
                             *     }
                             * ) {
                             *     // Your screen content
                             * }
                             *
                             * That is the official recommended usage.
                             *
                             * Why Google removed styling
                             *
                             * TimeText on watches must follow strict readability rules:
                             *
                             * curved screen edges
                             *
                             * small display
                             *
                             * glanceable UI
                             *
                             * consistent system appearance
                             *
                             * So Google locks the styling.
                             *
                             * If you want to customize something
                             *
                             * You can only customize what appears around the time, like adding icons:
                             *
                             * ScreenScaffold(
                             *     timeText = {
                             *         TimeText(
                             *             startLinearContent = {
                             *                 Icon(
                             *                     imageVector = Icons.Default.Timer,
                             *                     contentDescription = null
                             *                 )
                             *             }
                             *         )
                             *     }
                             * ) {
                             *     // content
                             * }*/


                ScreenScaffold(
                    timeText = {
                        TimeText()
                               },
                    modifier = Modifier.background(Color.Black)
                ) { padding ->

                    stopWatch(
                        state = timerState,
                        text = stopWatchText,
                        onToggleRunning = viewModel::toggleIsRunning,
                        onReset = viewModel::resetTimer,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    )
                }





            }
        }
    }

    @Composable
    private fun stopWatch (
        state : TimerState,
        text : String,
        onToggleRunning: () -> Unit,
        onReset: () -> Unit,
        modifier: Modifier = Modifier
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onToggleRunning
                ) {
                    Icon(
                        imageVector = if(state == TimerState.RUNNING){
                            Icons.Default.Pause
                        } else{
                            Icons.Default.PlayArrow
                        },
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onReset,
                    enabled = state != TimerState.RESET,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red

                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = null
                    )
                }
            }
        }

    }




