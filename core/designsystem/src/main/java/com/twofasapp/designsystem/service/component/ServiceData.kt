package com.twofasapp.designsystem.service.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.twofasapp.designsystem.TwTheme

@Composable
fun ServiceData(
    name: String,
    info: String?,
    code: String? = null,
    nextCode: String? = null,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ServiceName(name)
        ServiceInfo(info)

        if (code != null && nextCode != null) {
            ServiceCode(code = code, nextCode = nextCode)
        }
    }
}





@Preview
@Composable
private fun Preview() {
    ServiceData(
        name = "Service Name",
        info = "test@mail.com",
        modifier = Modifier.fillMaxWidth()
    )
}