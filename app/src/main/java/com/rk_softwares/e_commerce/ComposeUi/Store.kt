package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.rk_softwares.e_commerce.R

@Preview(showBackground = true)
@Composable
fun Store(storeName : String = "Rada Krishna", image : String = "", visitStoreClick : () -> Unit ={}, followClick : () -> Unit = {}){

    Column(
        modifier = Modifier.fillMaxWidth().background(color = Color(0xFFFFFFFF))
    ) {

        Box(

            modifier = Modifier.fillMaxWidth().padding(7.dp)
        ) {

            Row(
                modifier = Modifier.wrapContentSize().align(Alignment.CenterStart)
            ) {
                
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(7.dp))
                        .background(color = Color(0xFFE3E2E2))
                        .size(width = 40.dp, height = 40.dp)

                ) {

                    AsyncImage(
                        model = image,
                        contentDescription = "",
                        placeholder = painterResource(R.drawable.img_loading_daraz),
                        contentScale = ContentScale.FillBounds
                    )

                }//image box

                Text(storeName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp)
                        .width(100.dp)
                    )

                var fText by remember { mutableStateOf("Follow") }
                

                Text(text = fText,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .border(1.dp, color = Color(0xFF908787), shape = RoundedCornerShape(7.dp))
                        .padding(7.dp)
                        .clickable(
                            interactionSource = null,
                            indication = null
                        ){
                            followClick()

                            if(fText == "Follow") fText = "Following" else fText = "Follow"

                        }
                )

            }// row

            Text("Visit Store",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .wrapContentSize()
                    .background(color = Color(0xFFF36032), shape = RoundedCornerShape(7.dp))
                    .align(Alignment.CenterEnd)
                    .padding(10.dp)
                    .clickable(
                        interactionSource = null,
                        indication = null
                    ){
                        visitStoreClick()
                    }

            )

        }// box

       Box (
           modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).padding(8.dp)
       ){

           Row (
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(top = 7.dp, bottom = 7.dp)
                   .background(color = Color(0x11ECB5B5), shape = RoundedCornerShape(7.dp))
                   .align(Alignment.Center)

           ){

               sellerReview(
                   percentage = 50,
                   quilty = "Good",
                   sellerReview = "Positive Seller",
                   cModifier = Modifier
                       .padding(5.dp)
                       .align(Alignment.CenterVertically)
               )

               VerticalDivider(
                   color = Color(0xFFA4EACBCB),
                   thickness = 1.dp,
                   modifier = Modifier
                       .height(30.dp)
                       .align(Alignment.CenterVertically)
                       .padding(end = 7.dp)
               )

               sellerReview(
                   percentage = 70,
                   quilty = "High",
                   sellerReview = "Shop in Time",
                   cModifier = Modifier
                       .padding(5.dp)
                       .align(Alignment.CenterVertically)
               )

               VerticalDivider(
                   color = Color(0xFFA4EACBCB),
                   thickness = 1.dp,
                   modifier = Modifier
                       .height(30.dp)
                       .align(Alignment.CenterVertically)
                       .padding(end = 7.dp)
               )


               sellerReview(
                   percentage = 70,
                   quilty = "Good",
                   sellerReview = "Chat Response",
                   cModifier = Modifier
                       .padding(5.dp)
                       .align(Alignment.CenterVertically)
               )

           }//row

       }//box

    }//colum

} //fun end


@Composable
fun sellerReview(percentage : Int = 10, quilty : String = "High", sellerReview : String = "Positive", cModifier: Modifier = Modifier){

    Column(

        modifier = cModifier

    ) {

        Row (
            modifier = Modifier.wrapContentSize()
        ){

            Text(percentage.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF6F5F5F),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                )

            Icon(
                painter = painterResource(R.drawable.ic_discount),
                contentDescription = "",
                tint = Color(0xFF6F5F5F),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Box (
                modifier = Modifier.padding(5.dp).align(Alignment.CenterVertically)
            ){

                qualityTag(
                    qualityText = quilty,
                    qModifier = Modifier
                        .wrapContentSize()
                        .background(color = Color(0xA3F1F3DF), shape = RoundedCornerShape(4.dp))
                        .padding(1.dp)

                )

            }//text box


        }//row

        Text(text = sellerReview,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF6F5F5F),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.Start)
                .width(100.dp)

        )

    }//colum

}//fun end

@Composable
fun qualityTag(qualityText : String = "", qModifier: Modifier = Modifier){

    Box(
        modifier = qModifier

    ) {

        when (qualityText) {
            "High" ->
                Text("High",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF72A33A),
                    modifier = Modifier.wrapContentSize().padding(2.dp).align(Alignment.Center)
                )
            "Good" ->
                Text("Good",
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFFFAC014),
                modifier = Modifier.wrapContentSize().padding(2.dp).align(Alignment.Center)
                )

            else ->
                Text("Bad",
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFFF44336),
                modifier = Modifier.wrapContentSize().padding(2.dp).align(Alignment.Center)
                )
        }


    }

}