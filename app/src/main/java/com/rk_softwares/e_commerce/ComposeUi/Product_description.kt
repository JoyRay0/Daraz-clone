package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.model.Product

@Preview(showBackground = true)
@Composable
fun ProductDetails(list : MutableList<Product> = arrayListOf(), btnClick : () -> Unit = {}){

    var btnText by remember { mutableStateOf("See More") }
    var btnIcon by remember { mutableStateOf(R.drawable.ic_down) }
    var isVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text("Product details",
            fontSize = 17.sp,
            color = Color(0xFF8e8999),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .padding(7.dp)
                .align(Alignment.Start)

        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Specification",
                fontSize = 12.sp,
                color = Color(0xFF000000),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(7.dp)
                    .align(Alignment.CenterStart)
                )

            Text("Brand",
                fontSize = 12.sp,
                color = Color(0xFF5e5c66),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(7.dp)
                    .align(Alignment.CenterEnd)
            )

        }//box

        Text("Highlights",
            fontSize = 13.sp,
            color = Color(0xFF000000),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .padding(7.dp)
                .align(Alignment.Start)
        )


        Text("- Demo",
            fontSize = 12.sp,
            color = Color(0xFF5B5151),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(start = 12.dp, end = 7.dp, top = 7.dp, bottom = 7.dp)
                .align(Alignment.Start)
                .wrapContentWidth()
        )

       if (isVisible){

           Text("Description",
               fontSize = 13.sp,
               color = Color(0xFF000000),
               fontFamily = FontFamily.SansSerif,
               fontWeight = FontWeight.W600,
               modifier = Modifier
                   .padding(7.dp)
                   .align(Alignment.Start)
           )

           Text("- Demo description",
               fontSize = 12.sp,
               color = Color(0xFF5B5151),
               fontFamily = FontFamily.SansSerif,
               fontWeight = FontWeight.Normal,
               modifier = Modifier
                   .padding(start = 12.dp, end = 7.dp, top = 7.dp, bottom = 7.dp)
                   .align(Alignment.Start)
                   .wrapContentWidth()
           )

       }

        Row(
            modifier = Modifier
                .height(40.dp)
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color(0xFFC9E5EF))
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
                .clickable(
                    interactionSource = null,
                    indication = null
                ){
                    btnClick()

                    if ((btnText == "See More") && (btnIcon == R.drawable.ic_down)) isVisible = true else isVisible = false

                    if (btnText == "See More") btnText = "See Less" else btnText = "See More"

                    if (btnIcon == R.drawable.ic_down) btnIcon = R.drawable.ic_up else btnIcon = R.drawable.ic_down


                }

        ) {

            Text(text = btnText,
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W600,
                color = Color(0xFF6B5E5E),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(2.dp)
                )

            Icon(painter = painterResource(btnIcon),
                contentDescription = "",
                tint = Color(0xFF6B5E5E),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                )

        }//box

        Spacer(
            modifier = Modifier.height(10.dp)
        )

    }//colum

}//fun end