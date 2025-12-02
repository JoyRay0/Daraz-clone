package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.evaluateY
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.rk_softwares.e_commerce.R
import kotlinx.coroutines.delay

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun rating_reviews(
    totalReviewCount : Int = 0,
    totalStar : Double = 0.0,
    list : ArrayList<HashMap<String, Any>> = arrayListOf(),
    onClick : () -> Unit = {}){

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFFFFFFF))
        ,

        verticalArrangement = Arrangement.spacedBy(5.dp)

    ) {

        Row(

            modifier = Modifier.fillMaxWidth().padding(5.dp),
            horizontalArrangement = Arrangement.Start

        ) {

            Text("Rating & Reviews",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 5.dp, start = 2.dp, bottom = 5.dp, end = 3.dp)

            )

            Text("(",
                fontSize = 14.sp,
                color = Color(0xFF877676),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)

            )

            Text(totalReviewCount.toString(),
                fontSize = 14.sp,
                color = Color(0xFF877676),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
            )

            Text(")",
                fontSize = 14.sp,
                color = Color(0xFF877676),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)

            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.CenterEnd
            ) {

                Row(

                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.CenterEnd)
                        .clickable{onClick()},

                    ) {

                    Text(totalStar.toString(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)

                    )

                    TotalStarCount(totalStar,
                        rModifier = Modifier
                            .wrapContentSize()
                            .padding(start = 7.dp),
                        iModifier = Modifier.size(20.dp)

                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_right),
                        contentDescription = "",
                        tint = Color(0xFF625A5A),
                        modifier = Modifier
                            .wrapContentSize()
                            .size(20.dp)
                            .align(Alignment.CenterVertically),
                    )
                }

            }//box

        } // row

        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 7.dp, end = 7.dp, top = 5.dp, bottom = 5.dp)
                .background(color = Color(0x2AF1C5C5), shape = RoundedCornerShape(7.dp))
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_image),
                contentDescription = "",
                tint = Color(0xFFE27350),
                modifier = Modifier
                    .size(21.dp)
                    .padding(start = 5.dp, end = 2.dp, top = 5.dp, bottom = 5.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
            )

            Text("With images/videos",
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                color = Color(0xFFE27350),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp)
                    .align(Alignment.CenterVertically)
            
            )

            Text("(",
                fontSize = 14.sp,
                color = Color(0xFFE27350),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)

            )

            Text(totalReviewCount.toString(),
                fontSize = 14.sp,
                color = Color(0xFFE27350),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
            )

            Text(")",
                fontSize = 14.sp,
                color = Color(0xFFE27350),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically)
                    .padding(end = 5.dp)

            )


        }//row

        list.forEach{item ->

            val name = item["name"] as? String ?: ""
            val comment = item["comment"] as? String ?: ""
            val rating = item["rating"] as? Int ?: 0
            val image = item["image"] as? String ?: ""

            Box(

                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 5.dp, bottom = 5.dp)

            ) {

                Comments(name = name, comment = comment ,tStar = rating, image = image)

            }

        }




        Divider(
            color = Color(0x8DEED4D4),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()

        )

    }// main colum

}


@Composable
fun TotalStarCount(rating : Double = 0.0, rModifier: Modifier = Modifier, iModifier : Modifier = Modifier){

    Row (
        modifier = rModifier,

    ){

        for (i in 1..5){

            val icon = when{

                i<= rating -> R.drawable.ic_fill_star
                i - rating <= 0.5 -> R.drawable.ic_half_star
                else -> R.drawable.ic_outline_star

            }

            Icon(

                painter = painterResource(icon),
                contentDescription = "",
                tint = Color(0xFF8BC34A),
                modifier = iModifier

            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Comments(name : String = "Demo", comment : String = "Hello World", image : String = "", tStar : Int = 0){

    Box(

        modifier = Modifier.fillMaxWidth()
            .background(color = Color(0x83F1EDED), shape = RoundedCornerShape(14.dp))
            .padding(7.dp)

    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top

        ) {

            Text(name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF927878),
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 14.dp)
                    .wrapContentWidth()
                    .width(100.dp)
            )

            Text(comment,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF000000),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp)
                    .width(170.dp)


            )

            TotalStarCount(tStar.toDouble(),

                rModifier = Modifier
                    .padding(7.dp),

                iModifier =  Modifier
                    .wrapContentSize()
                    .size(17.dp)

            )

        }//colum

        Box (

            modifier = Modifier
                .wrapContentSize()
                .padding(7.dp)
                .height(70.dp)
                .width(70.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .align(Alignment.CenterEnd)
                .background(color = Color(0xFFFFFFFF)),

            ) {
            AsyncImage(
                model = image,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.img_loading_daraz),
                modifier = Modifier.fillMaxSize()

            )

        }//box

    } //box

}