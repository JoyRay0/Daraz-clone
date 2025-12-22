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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
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

@Preview(showBackground = true)
@Composable
fun ProductPrice(
    price : Double = 0.0,
    discount : Double = 0.0,
    title : String = "Rada krishna",
    totalRating : Double = 0.0,
    starCount : Int = 0,
    stock : String = "In Stock",
    stockCount : Int = 0,
    addToWishListBtn : () -> Unit = {},
    shareProductBtn : () -> Unit = {},
    deliveryDialogBtn :() -> Unit = {},
    returnDialogBtn : () -> Unit = {}
    ){

    Column(

        modifier = Modifier.fillMaxWidth()

    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Row(

            modifier = Modifier.fillMaxWidth().padding(start = 5.dp)

        ) {

            Icon(painter = painterResource(R.drawable.ic_dollar),
                contentDescription = "",
                tint = Color(0xFFFF5722),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .size(16.dp)

            )

            Text( price.toString(),
                fontSize = 23.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF5722),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 3.dp, end = 6.dp)

            )

            //discount percentage
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = Color(0x54EBD0D0))
                    .align(Alignment.CenterVertically)
            ) {

                Text("-",
                    fontSize = 14.sp,
                    color = Color(0xFFFF5722),
                    modifier = Modifier
                        .padding(start = 3.dp)
                        .align(Alignment.CenterVertically)
                )

                Text( discount.toString(),
                    fontSize = 12.sp,
                    color = Color(0xFFFF5722),
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterVertically)

                )

                Icon(painter = painterResource(R.drawable.ic_discount),
                    contentDescription = "",
                    tint = Color(0xFFFF5722),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 2.dp)
                    )

            }//box

            //discount percentage

        }//row

        Text( title,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 5.dp, top = 7.dp, bottom = 7.dp, end = 5.dp)

            )

        //rating and stock

        Spacer(modifier = Modifier.height(10.dp))
        
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(

                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 5.dp)

            ) {

                Icon( painter = painterResource(R.drawable.ic_rating),
                    contentDescription = "",
                    tint = Color(0xFF92E733),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .size(18.dp)

                )

                Text(totalRating.toString(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                        .padding(start = 4.dp, end = 4.dp)
                )

                Text("(",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                )

                Text(starCount.toString(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                        .padding(start = 1.dp, end = 0.5.dp)
                )

                Text(")",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                )

                VerticalDivider(
                    color = Color(0xFF736262),
                    modifier = Modifier
                        .size(13.dp)
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                )

                Text(stock,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(end = 3.dp)
                    )

                Text("(",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                )

                Text(stockCount.toString(),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                        .padding(start = 0.5.dp, end = 0.5.dp)
                )

                Text(")",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                )

            }//row

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)
            ) {

                IconButton(
                    onClick = addToWishListBtn,
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(shape = CircleShape)
                        .padding(end = 12.dp)
                        .size(21.dp)
                        .align(Alignment.CenterVertically)
                ) {

                    Icon( painter = painterResource(R.drawable.ic_love_outline),
                        contentDescription = "",
                        tint = Color(0xFF625A5A),
                        )

                }


                IconButton(
                    onClick = shareProductBtn,
                    modifier = Modifier
                        .wrapContentWidth()
                        .clip(shape = CircleShape)
                        .padding(end = 5.dp)
                        .size(25.dp)
                        .align(Alignment.CenterVertically)

                ) {

                    Icon( painter = painterResource(R.drawable.ic_share2),
                        contentDescription = "",
                        tint = Color(0xFF625A5A),
                    )

                }

            }// row

        }//box

        //rating and stock

        Spacer(modifier = Modifier.height(10.dp))

        //delivery

        Box(

            modifier = Modifier.fillMaxWidth()

        ) {

            Row(

                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 5.dp)

            ) {

                Icon( painter = painterResource(R.drawable.ic_delivery_box),
                    contentDescription = "",
                    tint = Color(0xFF625A5A),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .size(16.dp)

                )

                Column(

                    modifier = Modifier.wrapContentWidth()

                ) {

                    Text("Delivery time",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.Start)
                            .padding(start = 7.dp)
                    )

                    Text("Standard Delivery",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF625A5A),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.Start)
                            .padding(start = 7.dp, top = 5.dp)
                    )

                }//colum

            }//row

            IconButton(
                onClick = deliveryDialogBtn,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    .align(Alignment.CenterEnd)
                    .padding(end = 5.dp)
                    .size(30.dp)

            ) {

                Icon( painter = painterResource(R.drawable.ic_right),
                    contentDescription = "",
                    tint = Color(0xFF625A5A),
                    )

            }



        }//box

        //delivery

        Spacer(modifier = Modifier.height(10.dp))

        //return policy

        Box(

            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)

        ) {

            Row(

                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterStart)
                    .padding(start = 5.dp)

            ) {

                Icon( painter = painterResource(R.drawable.ic_ok),
                    contentDescription = "",
                    tint = Color(0xFF625A5A),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .size(16.dp)

                )

                Text("Best Price",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 7.dp)
                )

                VerticalDivider(
                    color = Color(0xFF736262),
                    modifier = Modifier
                        .size(13.dp)
                        .wrapContentWidth()
                        .align(Alignment.Bottom)
                )

                Text("Return Policy",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                )


            }//row

            IconButton(
                onClick = returnDialogBtn,
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = CircleShape)
                    .align(Alignment.CenterEnd)
                    .padding(end = 5.dp)
                    .size(30.dp)

            ) {

                Icon( painter = painterResource(R.drawable.ic_right),
                    contentDescription = "",
                    tint = Color(0xFF625A5A),

                    )
            }


        }//box

        //return policy

        Spacer(modifier = Modifier.height(10.dp))
        
    }//colum

}//fun end