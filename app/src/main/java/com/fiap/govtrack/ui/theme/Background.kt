import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fiap.govtrack.R

@Composable
fun GradientBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        colorResource(R.color.cinza),
                        colorResource(R.color.black)
                    ),
                )
            )
            .padding(
                top = 64.dp
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
