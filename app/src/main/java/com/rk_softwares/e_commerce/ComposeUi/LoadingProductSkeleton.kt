package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun LoadingPage(){

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // image
        Loading(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.padding(7.dp)
        ) {

            //price
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .width(120.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            //title
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            //rating
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            //delivery
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            //return
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            //voucher title
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            //vouchers
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(130.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            //rating and reviews
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            //review image count
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .width(150.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            //review
            Loading(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(70.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

        }//column

    }// column

} //fun end

@Preview(showBackground = true)
@Composable
fun Loading( modifier: Modifier = Modifier
){
    Box(modifier = modifier
        .background(color = Color(0xFFE3E3E3))
    )
    
}