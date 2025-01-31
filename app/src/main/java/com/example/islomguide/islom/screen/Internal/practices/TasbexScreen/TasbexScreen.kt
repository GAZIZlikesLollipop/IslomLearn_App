package com.example.islomguide.islom.screen.Internal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.islomguide.R
import com.example.islomguide.core.ui_kit.CommonFeatureScreen
import com.example.islomguide.islom.screen.Internal.practices.TasbexScreen.TasbexViewModel
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasbexScreen(
    navController: NavController,
    viewModel: TasbexViewModel,
    modifier: Modifier = Modifier
){
    var dialog1 by remember { mutableStateOf(false) }
    var dialog2 by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val altogether : String = context.getString(R.string.altogether)
    val state = viewModel.state.collectAsState().value
    val alert_array = context.resources.getStringArray(R.array.dialog)
    val text = when{
        state.is33 && !state.isInf -> "33"
        !state.is33 && !state.isInf -> "99"
        else -> "∞"
    }
    val tex2 = if(state.isInf && !state.is33){
        "${state.currentCount}"
    }else{
        "${state.currentCount}/${state.countMax}"
    }
    CommonFeatureScreen {
        Row(
            Modifier.padding(30.dp)
        ){
            Text(
                "$altogether ${state.totalCount}",
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(Modifier.padding(horizontal = 50.dp))
            Row{
                TextButton(
                    onClick = {
                        dialog2 = true
                    },
                    Modifier
                        .size(80.dp)
                        .offset(y = (-15).dp)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                IconButton(
                    onClick = {
                        dialog1 = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Replay,
                        contentDescription = "Remove",
                        modifier = Modifier
                            .size(80.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        }
            OutlinedButton(
                onClick = { viewModel.increaseCount() },
                Modifier
                    .padding(125.dp)
                    .padding(vertical = 150.dp)
                    .size(150.dp)
            ) {
                Text(
                    text = tex2,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        if(dialog1){
            AlertDialog(
                onDismissRequest = { dialog1 = false },
                title = {Text(alert_array[0])},
                text = {Text(alert_array[1])},
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.RemoveCount()
                            dialog1 = false
                        }
                    ){
                        Text(
                            alert_array[2]
                        )
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            dialog1 = false
                        }
                    ){
                        Text(
                            alert_array[3]
                        )
                    }
                }
            )
        }
        if(dialog2){
        AlertDialog(
            onDismissRequest = { dialog2 = false },
            title = {Text(alert_array[0])},
            text = {Text(alert_array[4])},
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.ChangeMax()
                        dialog2 = false
                    }
                ){
                    Text(
                        alert_array[2]
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        dialog2 = false
                    }
                ){
                    Text(
                        alert_array[3]
                    )
                }
            }
        )
    }
    }
}