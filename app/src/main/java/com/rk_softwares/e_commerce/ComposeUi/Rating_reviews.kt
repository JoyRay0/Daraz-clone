package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rk_softwares.e_commerce.R

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
                            .align(Alignment.CenterVertically)
                        ,
                    )
                }



            }//box

        } // row

        for (item in list){

            val name = item["name"] as? String ?: ""
            val comment = item["comment"] as? String ?: ""
            val rating = item["rating"] as? Int ?: 0

            Box(

                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 5.dp, bottom = 5.dp)

            ) {

                Comments(name = name, comment = comment ,tStar = rating,
                    rModifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0x83F1EDED), shape = RoundedCornerShape(12.dp))
                        .padding(7.dp)
                )

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


@Composable
fun Comments(name : String, comment : String, tStar : Int, rModifier : Modifier = Modifier){


    Row(

        modifier = rModifier

    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),

        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF927878),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(5.dp)
                )

                TotalStarCount(tStar.toDouble(),

                   rModifier = Modifier
                       .padding(7.dp)
                       .align(Alignment.CenterEnd)
                    ,
                   iModifier =  Modifier
                        .wrapContentSize()
                       .size(17.dp)

                    )

            }

            Text(comment,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF000000),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Start)
                    .padding(5.dp)

            )

        }//colum

    } //row

}