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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ProductDetails(brand : String = "Brand", hText : String = "N/A", description : String = "N/A"){

    var btnText by remember { mutableStateOf("See More") }
    var btnIcon by remember { mutableIntStateOf(R.drawable.ic_down) }
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

            Text(brand,
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


        Text(hText,
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

           Text(description,
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
                .height(35.dp)
                .wrapContentWidth()
                .clip(shape = RoundedCornerShape(22.dp))
                .background(color = Color(0xAEDBE4E7))
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .clickable(
                    interactionSource = null,
                    indication = null
                ){

                    if ((btnText == "See More") && (btnIcon == R.drawable.ic_down)) isVisible = true else isVisible = false

                    if (btnText == "See More") btnText = "See Less" else btnText = "See More"

                    if (btnIcon == R.drawable.ic_down) btnIcon = R.drawable.ic_up else btnIcon = R.drawable.ic_down


                }

        ) {

            Text(text = btnText,
                fontSize = 10.sp,
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