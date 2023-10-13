package tv.mogiio.jettrivia.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tv.mogiio.jettrivia.util.AppColors

@Preview
@Composable
fun ProgressIndicator(score: Int = 100) {
  val gradient = Brush.linearGradient(colors = listOf(Color(0xFFF95075), Color(0xFFBE6BE5)))

  val progressFactor by remember(score) {
    mutableStateOf(score * 0.005f)
  }

  Row(
    modifier = Modifier
      .padding(3.dp)
      .fillMaxWidth()
      .height(45.dp)
      .border(
        width = 4.dp,
        brush = Brush.linearGradient(
          colors = listOf(
            AppColors.mLightPurple,
            AppColors.mLightPurple
          )
        ),
        shape = RoundedCornerShape(34.dp)
      )
      .clip(
        RoundedCornerShape(
          topStartPercent = 50,
          topEndPercent = 50,
          bottomStartPercent = 50,
          bottomEndPercent = 50
        )
      ),
    verticalAlignment = Alignment.CenterVertically

  ) {
    Button(
      onClick = { /*TODO*/ },
      contentPadding = PaddingValues(1.dp),
      modifier = Modifier
        .fillMaxWidth(progressFactor)
        .background(brush = gradient),
      enabled = false,
      elevation = null,
      colors = buttonColors(
        containerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent
      )
    ) {
      Text(
        text = (score*100).toString(),
        modifier = Modifier
          .clip(shape = RoundedCornerShape(23.dp))
          .fillMaxHeight(0.8f)
          .fillMaxWidth()
          .padding(6.dp),
        color = AppColors.mOffWhite,
        textAlign = TextAlign.Center

      )
    }
  }
}