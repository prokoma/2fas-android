package com.twofasapp.designsystem.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.twofasapp.designsystem.TwTheme

@Composable
fun TwButton(
    text: String,
    onClick: () -> Unit,
    height: Dp = TwTheme.dimen.buttonHeight,
    modifier: Modifier = Modifier,
    style: TextStyle = TwTheme.typo.body2,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = TwTheme.color.button,
            contentColor = TwTheme.color.onButton,
        ),
        enabled = enabled,
        modifier = modifier.height(height),
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}

@Composable
fun TwOutlinedButton(
    text: String,
    onClick: () -> Unit,
    height: Dp = TwTheme.dimen.buttonHeight,
    modifier: Modifier = Modifier,
    style: TextStyle = TwTheme.typo.body2,
    enabled: Boolean = true,
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            color = TwTheme.color.primary,
        ),
        enabled = enabled,
        modifier = modifier.height(height),
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}

@Composable
fun TwTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = TwTheme.typo.body2,
    enabled: Boolean = true,
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = textButtonColors(
            contentColor = TwTheme.color.primary,
            disabledContentColor = TwTheme.color.onSurfaceSecondary,
        )
    ) {
        Text(
            text = text,
            style = style,
        )
    }
}

@Composable
fun TwIconButton(
    painter: Painter? = null,
    contentDescription: String? = null,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    tint: Color? = null,
    iconModifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    content: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    ) {
        content?.invoke() ?: painter?.let {
            Icon(
                painter = it,
                contentDescription = contentDescription,
                modifier = iconModifier,
                tint = tint ?: TwTheme.color.iconTint
            )
        }
    }
}