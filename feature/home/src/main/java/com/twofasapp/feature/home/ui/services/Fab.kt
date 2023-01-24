package com.twofasapp.feature.home.ui.services

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.twofasapp.designsystem.TwTheme
import com.twofasapp.locale.TwLocale

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun ServicesFab(
    isVisible: Boolean,
    isExtendedVisible: Boolean,
    isNormalVisible: Boolean,
    onClick: () -> Unit,
) {

    if (isVisible) {
        if (isExtendedVisible) {
            ExtendedFloatingActionButton(
                onClick = onClick,
                icon = { Icon(Icons.Filled.Add, null) },
                text = { Text(text = TwLocale.strings.servicesEmptyPairServiceCta) },
                containerColor = TwTheme.color.button,
                contentColor = TwTheme.color.onButton,
            )
        } else {
            AnimatedVisibility(
                visible = isNormalVisible,
                enter = scaleIn(tween(150)),
                exit = scaleOut(tween(150)),
            ) {
                FloatingActionButton(
                    onClick = onClick,
                    content = { Icon(Icons.Filled.Add, null) },
                    containerColor = TwTheme.color.button,
                    contentColor = TwTheme.color.onButton,
                )
            }
        }
    }
}