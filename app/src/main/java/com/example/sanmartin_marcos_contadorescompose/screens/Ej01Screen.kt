package com.example.sanmartin_marcos_contadorescompose.screens.ejercicios

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sanmartin_marcos_contadorescompose.R


const val startCountDefault = 0

@Composable
fun Ej01Screen() {

    var contador1 by rememberSaveable { mutableStateOf(startCountDefault) }
    var contador2 by rememberSaveable { mutableStateOf(startCountDefault) }
    var contadorGlobal by rememberSaveable { mutableStateOf(startCountDefault) }

    var incremento1 by rememberSaveable { mutableStateOf("1") }
    var incremento2 by rememberSaveable { mutableStateOf("2") }

    val maxLength = 2

    Column(
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState()),
            .padding(top = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyFunction(
            counter = contador1,
            increment = incremento1,
            onCounterChange = { contador1 += incremento1.toInt() },
            onIncrementChange = { incremento1 = it },
            onGlobalCounterChange = { contadorGlobal += incremento1.toInt() },
            onResetCounter = { contador1 = it },
            maxLength = maxLength
        )

        MyFunction(
            counter = contador2,
            increment = incremento2,
            onCounterChange = { contador2 += incremento2.toInt() },
            onIncrementChange = { incremento2 = it },
            onGlobalCounterChange = { contadorGlobal += incremento2.toInt() },
            onResetCounter = { contador2 = it },
            maxLength = maxLength
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.global_counter))
            MySpacer(horizontal = 5.dp)
            Text(text = contadorGlobal.toString())
            MySpacer(horizontal = 5.dp)
            Image(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                modifier = Modifier.clickable {
                    contadorGlobal = 0
                }
            )
        }
    }


}

@Composable
fun MyFunction(
    counter: Int,
    increment: String,
    onIncrementChange: (String) -> Unit,
    onCounterChange: (Int) -> Unit,
    onGlobalCounterChange: (Int) -> Unit,
    onResetCounter: (Int) -> Unit,
    maxLength: Int
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {
                    if (increment.isNotEmpty()) {
                        onCounterChange(increment.toInt())
                        onGlobalCounterChange(increment.toInt())
                    }
                },
                enabled = increment.isNotEmpty()
            ) {
                Text(text = stringResource(R.string.counter1) + "  ($counter)")
            }
            MySpacer(horizontal = 5.dp)
            Text(text = counter.toString())
            MySpacer(horizontal = 5.dp)
            Image(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                modifier = Modifier.clickable {
                    onResetCounter(0)
                }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.increment))
            MySpacer(horizontal = 5.dp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = increment,
                    onValueChange = {
                        if (TextUtils.isDigitsOnly(it) && it.length <= maxLength) { // Prevenir que se pegue texto y que sea mayor de 2 dígitos
                            onIncrementChange(it)
                        }
                    },
                    isError = increment.isEmpty(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp),
                )
            }
        }

        if (increment.isEmpty()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.error_message_textfield),
                    color = MaterialTheme.colors.error,
                    style = MaterialTheme.typography.caption
                )
                MySpacer(horizontal = 2.dp)
                Icon(
                    painter = painterResource(id = R.drawable.baseline_error_24),
                    contentDescription = "",
                    tint = MaterialTheme.colors.error,
                )
            }
        }

//        OutlinedTextFieldValidation(
//            value = increment,
//            onValueChange = {
//                if (TextUtils.isDigitsOnly(it) && it.length <= maxLength) { // Prevenir que se pegue texto y que sea mayor de 2 dígitos
//                    onIncrementChange(it)
//                }
//            },
//            label = { Text(text = stringResource(id = R.string.increment)) },
////            label = null,
////            placeholder = {
////                Text(
////                    text = stringResource(id = R.string.error_message_textfield),
////                    color = MaterialTheme.colors.error
////                )
////            },
//            placeholder = null,
//            error = stringResource(id = R.string.error_message_textfield),
//            isError = increment.isEmpty(),
//            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//            errorMessageBelow = false
//        )


    }

}

@Composable
fun MySpacer(vertical: Dp = 0.dp, horizontal: Dp = 0.dp) {
    Spacer(modifier = Modifier.padding(vertical = vertical, horizontal = horizontal))
}


@Composable
fun OutlinedTextFieldValidation(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(0.8f),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    error: String = "",
    isError: Boolean = value.isEmpty(),
    trailingIcon: @Composable (() -> Unit)? = {
        if (value.isEmpty())
            Icon(Icons.Filled.Warning, "error", tint = MaterialTheme.colors.error)
    },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        disabledTextColor = Color.Black
    ),
    errorMessageBelow: Boolean = value.isEmpty(),

    ) {

    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        OutlinedTextField(
            enabled = enabled,
            readOnly = readOnly,
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = singleLine,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors
        )
        if (errorMessageBelow && isError) {
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(top = 0.dp)
            )
        }
    }
}