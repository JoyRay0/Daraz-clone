package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rk_softwares.e_commerce.R

@Preview(showBackground = true)
@Composable
fun Question(questionCount : Int = 0, viewAllClick : () -> Unit = {}, askQues : () -> Unit = {}){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF))

    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.wrapContentSize().padding(5.dp)
            ) {

                Text("Question about this product",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(2.dp)
                        .align(Alignment.CenterVertically)
                    )

                Text(
                    "(",
                    fontSize = 14.sp,
                    color = Color(0xFF877676),
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)

                )

                Text(
                    questionCount.toString(),
                    fontSize = 14.sp,
                    color = Color(0xFF877676),
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                )

                Text(
                    ")",
                    fontSize = 14.sp,
                    color = Color(0xFF877676),
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)

                )

            }//row

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterEnd)
                    .clickable{
                        viewAllClick()
                    }
            ) {

                Text("View All",
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color(0xFF8D7676),
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterVertically)
                    )

                Icon(
                    painter = painterResource(R.drawable.ic_right),
                    contentDescription = "",
                    tint = Color(0xFF8D7676),
                    modifier = Modifier
                        .size(20.dp)
                        .padding(2.dp)
                        .align(Alignment.CenterVertically)
                )

            }//row

        }//box

        Row(
            modifier = Modifier.wrapContentSize().padding(start = 11.dp, top = 5.dp, bottom = 5.dp)
        ){

            Image(
                painter = painterResource(R.drawable.ic_question_fill),
                contentDescription = "",
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.CenterVertically)

                )

            Text("Is this original ? what are the proves ?",
                fontSize = 13.sp,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF2F2D2D),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .align(Alignment.CenterVertically)
                )


        }//row

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable{askQues()}
                .padding(start = 7.dp, end = 7.dp, top = 12.dp, bottom = 7.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_chat_line),
                contentDescription = "",
                tint = Color(0xFF2196F3),
                modifier = Modifier
                    .size(22.dp)
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)

            )

            Text("Ask Question",
                color = Color(0xFF403A3A),
                fontSize = 13.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Normal,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(3.dp)
                )

            Icon(
                painter = painterResource(R.drawable.ic_right),
                contentDescription = "",
                tint = Color(0xFF8D7676),
                modifier = Modifier
                    .size(20.dp)
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)

            )

        }//row


    }//colum

}//fun