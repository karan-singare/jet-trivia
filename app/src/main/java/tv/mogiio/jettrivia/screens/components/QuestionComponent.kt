package tv.mogiio.jettrivia.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tv.mogiio.jettrivia.model.Question
import tv.mogiio.jettrivia.screens.QuestionsViewModel
import tv.mogiio.jettrivia.util.AppColors


@Composable
fun QuestionComponent(
  question: Question,
  questionIndex: MutableState<Int>,
  viewModel: QuestionsViewModel,
  onNextClick: (Int) -> Unit
) {
  val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
  val choices = remember(question) {
    question.choices.toMutableList()
  }

  val answer = remember(question) {
    mutableStateOf<Int?>(null)
  }

  val correctAnswer = remember {
    mutableStateOf<Boolean?>(null)
  }

  val updateAnswer: (Int) -> Unit = remember(question) {
    {
      answer.value = it
      correctAnswer.value = choices[it] == question.answer
    }
  }

  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(),
    color = AppColors.mDarkPurple
  ) {
    Column(
      modifier = Modifier,
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.Start
    ) {
      val outOf = viewModel.getTotalQuestionsCount()

      if (questionIndex.value >= 3) {
        ProgressIndicator(questionIndex.value)
      }

      if (outOf != null) {
        QuestionTracker(counter = questionIndex.value + 1, outOf = outOf)
      }
      DottedLine(pathEffect)

      Column() {
        Text(
          modifier = Modifier
            .padding(6.dp)
            .align(alignment = Alignment.Start)
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
          text = question.question,
          fontSize = 17.sp,
          fontWeight = FontWeight.Bold,
          lineHeight = 22.sp,
          color = AppColors.mOffWhite
        )

        choices.forEachIndexed { index, choice ->
          Row(
            modifier = Modifier
              .padding(3.dp)
              .fillMaxWidth()
              .height(45.dp)
              .border(
                width = 4.dp, brush = Brush.linearGradient(
                  colors = listOf(AppColors.mOffDarkPurple, AppColors.mOffDarkPurple)
                ), shape = RoundedCornerShape(15.dp)
              )
              .clip(
                RoundedCornerShape(
                  topStartPercent = 50,
                  topEndPercent = 50,
                  bottomStartPercent = 50,
                  bottomEndPercent = 50
                )
              )
              .background(Color.Transparent), verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = answer.value == index,
              onClick = { updateAnswer(index) },
              modifier = Modifier.padding(16.dp),
              colors = RadioButtonDefaults.colors(
                selectedColor = if (correctAnswer.value == true && index == answer.value) {
                  Color.Green.copy(alpha = 0.2f)
                } else {
                  Color.Red.copy(alpha = 0.2f)
                }
              )
            )

            val annotatedString = buildAnnotatedString {
              val color =
                if (correctAnswer.value == true && index == answer.value) {
                  Color.Green
                } else if (correctAnswer.value == false && index == answer.value) {
                  Color.Red.copy(alpha = 0.2f)
                } else {
                  AppColors.mOffWhite
                }

              withStyle(
                style = SpanStyle(
                  fontWeight = FontWeight.Light,
                  color = color,
                  fontSize = 17.sp
                )
              ) {
                append(text = choice)
              }
            }

            Text(text = annotatedString, modifier = Modifier.padding(6.dp))
          }

        }
        
        Button(
          onClick = { onNextClick(questionIndex.value) },
          modifier = Modifier
            .padding(3.dp)
            .align(alignment = Alignment.CenterHorizontally),
          shape = RoundedCornerShape(34.dp),
          colors = ButtonDefaults.buttonColors(containerColor = AppColors.mLightBlue)
        ) {
          Text(
            text = "Next",
            modifier = Modifier.padding(4.dp),
            color = AppColors.mOffWhite,
            fontSize = 17.sp
          )
        }
        
      }

    }
  }
}




