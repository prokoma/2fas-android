package com.twofasapp.design.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.twofasapp.design.theme.radioColors
import com.twofasapp.design.theme.switchColors
import com.twofasapp.designsystem.TwTheme

enum class SwitchEntryType { Switch, Radio }

@Composable
fun SwitchEntry(
    title: String = "",
    subtitle: String = "",
    icon: Painter? = null,
    iconTint: Color = Color.Unspecified,
    iconVisible: Boolean = true,
    isEnabled: Boolean = true,
    isChecked: Boolean = false,
    type: SwitchEntryType = SwitchEntryType.Switch,
    switch: ((isChecked: Boolean) -> Unit)? = null
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp, Dp.Infinity)
            .clickable(isEnabled) { switch?.invoke(isChecked.not()) }
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
    ) {
        val (iconRef, titleRef, subtitleRef, iconEndRef) = createRefs()
        val alpha = if (isEnabled) 1f else 0.3f

        Icon(
            painter = icon ?: painterResource(com.twofasapp.resources.R.drawable.ic_placeholder_old),
            contentDescription = null,
            tint = if (iconTint != Color.Unspecified) iconTint else TwTheme.color.primary,
            modifier = Modifier
                .size(if (iconVisible) 24.dp else 0.dp)
                .alpha(if (icon == null) 0f else alpha)
                .constrainAs(iconRef) {
                    top.linkTo(titleRef.top)
                    start.linkTo(parent.start, margin = 4.dp)
                    end.linkTo(titleRef.start)
                }
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 17.sp, color = TwTheme.color.onSurfacePrimary),
            modifier = Modifier
                .alpha(alpha)
                .constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(
                        if (subtitle.isNotEmpty()) subtitleRef.top else parent.bottom,
                        margin = 1.dp
                    )
                    start.linkTo(iconRef.end, margin = if (iconVisible) 28.dp else 16.dp)
                    end.linkTo(iconEndRef.start)
                    width = Dimension.fillToConstraints
                }
        )

        if (subtitle.isNotEmpty()) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp, color = TwTheme.color.onSurfaceSecondary),
                modifier = Modifier
                    .alpha(alpha)
                    .constrainAs(subtitleRef) {
                        top.linkTo(titleRef.bottom, margin = 2.dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(titleRef.start)
                        end.linkTo(iconEndRef.start)
                        width = Dimension.fillToConstraints
                    }
            )
        }

        when (type) {
            SwitchEntryType.Switch -> {
                Switch(
                    checked = isChecked,
                    colors = switchColors(),
                    enabled = isEnabled,
                    modifier = Modifier
                        .size(24.dp)
                        .alpha(alpha)
                        .constrainAs(iconEndRef) {
                            top.linkTo(titleRef.top)
                            end.linkTo(parent.end, margin = 4.dp)
                        },
                    onCheckedChange = { switch?.invoke(it) }
                )
            }

            SwitchEntryType.Radio -> {
                RadioButton(
                    selected = isChecked,
                    colors = radioColors(),
                    enabled = isEnabled,
                    modifier = Modifier
                        .size(24.dp)
                        .alpha(alpha)
                        .constrainAs(iconEndRef) {
                            top.linkTo(titleRef.top)
                            end.linkTo(parent.end, margin = 4.dp)
                        },
                    onClick = { switch?.invoke(isChecked.not()) }
                )
            }
        }
    }
}

@Preview
@Composable
internal fun PreviewSwitchEntryItem() {
    SwitchEntry(
        title = "Title",
        subtitle = "Subtitle",
        icon = painterResource(id = com.twofasapp.resources.R.drawable.ic_send_feedback),
    )
}