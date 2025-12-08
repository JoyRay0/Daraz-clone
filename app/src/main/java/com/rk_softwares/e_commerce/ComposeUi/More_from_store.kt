package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
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
fun MoreItem(goToStoreClick : () -> Unit = {}, list : ArrayList<HashMap<String, Any>> = arrayListOf()){

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 7.dp, top = 5.dp, bottom = 5.dp)
        ){

            Text(text = "More from Store",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF000000),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterStart)
            )

            IconButton(
                onClick = goToStoreClick,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(shape = CircleShape)
                    .size(35.dp)
            ) {

                Icon(
                    painter = painterResource(R.drawable.ic_right),
                    contentDescription = "",
                    tint = Color(0xFF8D7676),
                )

            }

        }//box

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(list){ item ->

                val pName = item["title"] as? String ?: ""
                val pPrice = item["price"] as? Double ?: 0.0
                val rating = item["rating"] as? Double ?: 0.0
                val reviewCount = item["reviews"] as? Int ?: 0
                val status = item["in_stock"] as? String ?: ""
                val tItem = item["stock"] as? Int ?: 0
                val image = item["short_image"] as? String ?: ""

                Product(
                    productName = pName ,
                    productImage = image,
                    price = pPrice,
                    totalRating = rating,
                    ratingCount = reviewCount,
                    availableStock = status,
                    stockCount = tItem
                    )

            }

        }

    }//colum

}// fun end

@Preview(showBackground = true)
@Composable
fun Product(
    productName : String = "Rada krishna",
    productImage : String = "",
    price : Double = 0.0,
    totalRating : Double = 0.0,
    ratingCount : Int = 0,
    availableStock : String = "In Stock",
    stockCount : Int = 0
){

    Box(

        modifier = Modifier
            .wrapContentSize()
            .padding(5.dp)
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp), clip = false)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color.Transparent)
    ) {

        Column(

            modifier = Modifier
                .wrapContentHeight()
                .background(color = Color.Transparent)
        ) {

            Box(
                modifier = Modifier
                    .height(120.dp)
                    .background(color = Color(0xFFEAE6E6))
                    .width(100.dp)

            ) {

                AsyncImage(
                    model = productImage,
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(R.drawable.img_loading_daraz),
                    modifier = Modifier
                        .fillMaxSize()
                )

            }// box image

            Column(
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentHeight()
                    .align(Alignment.Start)
                    .background(color = Color(0xFFFFFFFF))
                    .padding(5.dp)

            ) {

                Text(text = productName,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)

                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .padding(0.dp)

                ){

                    Icon(
                        painter = painterResource(R.drawable.ic_taka),
                        contentDescription = "",
                        tint = Color(0xFF7F7171),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(9.dp)
                    )

                    Text(price.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF7F7171),
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterVertically)
                    )

                }//row

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .padding(0.dp)
                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_fill_star),
                        contentDescription = "",
                        tint = Color(0xFFCDDC39),
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Text(text = totalRating.toString(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8D7E7E),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 2.dp)

                    )

                    Text("(",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8D7E7E),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 2.dp)

                    )

                    Text(text = ratingCount.toString(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8D7E7E),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)

                    )

                    Text(")",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8D7E7E),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)

                    )

                }//row

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                ) {

                    Text(text = availableStock,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8D7E7E),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)

                    )

                    Text(":",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8D7E7E),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(2.dp)

                    )

                    Text(text = stockCount.toString(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8D7E7E),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)

                    )

                }//row

            }//colum


        }//colum
    }


}// fun end