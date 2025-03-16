import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GradientBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF302F2F),
                        Color(0xFF030303)
                    ),
                )
            )
            .padding(
                top = 64.dp,
                start = 32.dp
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        content()
    }
}

@Preview(showSystemUi = true)
@Composable
fun GradientBackgroundPreview() {
    GradientBackground {
        Box(modifier = Modifier.fillMaxSize())
    }
}
