package com.ast.app.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ast.app.R

@Composable
fun VerifyOtpScreen(
    onResendOtpTextClicked: (Int) -> Unit
) {
    var otp by rememberSaveable {
        mutableStateOf("")
    }
    val sec = 59
    val otpSize = 6
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    val resendOtpText = buildAnnotatedString {
        append("Resend OTP in 00:$sec")
    }

    Column(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_l)),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            label = {
                Text(
                    text = "Enter 6-digit OTP",
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            value = otp,
            onValueChange = {
                if (it.length <= otpSize) otp = it
            },
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Key, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth()
        )

        ClickableText(
            text = resendOtpText,
            style = MaterialTheme.typography.labelLarge,
            onClick = onResendOtpTextClicked,
            modifier = Modifier
                .align(Alignment.Start)
                .drawBehind {
                    val strokeWidthPx = 1.dp.toPx()
                    val verticalOffset = size.height
                    drawLine(
                        color = Color.Gray,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset),
                        pathEffect = pathEffect
                    )
                },
        )


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.button_height))
        ) {
            Text(text = "Verify", style = MaterialTheme.typography.titleMedium)
        }
    }
}