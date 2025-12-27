package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.rk_softwares.e_commerce.R


@Preview(showBackground = true)
@Composable
fun vouchers(list : ArrayList<String>? = null, onClick : () -> Unit = {}){

    val pageCount = list?.size ?: 0
    val pagerState = rememberPagerState (pageCount = { pageCount })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,

    ) {

        Box (

            modifier = Modifier.fillMaxWidth(),

        ){

            Text("Vouchers",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterStart)
                    .padding(7.dp)
                    )

            IconButton(

                modifier = Modifier
                    .clip(CircleShape)
                    .size(35.dp)
                    .align(Alignment.CenterEnd)
                    .padding(7.dp),
                onClick = onClick

            ) {

                Icon(painter = painterResource(R.drawable.ic_right),
                    contentDescription = "",
                    tint = Color(0xFF625A5A),

                )

            }

        }

        HorizontalPager(state = pagerState) { page ->

            Box(

                modifier = Modifier
                    .padding(10.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(15.dp),
                        clip = false
                    )
                    .clip(RoundedCornerShape(15.dp))
                    .aspectRatio(16f / 7f)
                    .align(Alignment.CenterHorizontally)

            ) {

                AsyncImage(
                    model = list?.get(page),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(R.drawable.img_loading_daraz)

                )

            }

        }

    }

}