package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.rk_softwares.e_commerce.R
import com.rk_softwares.e_commerce.model.DataDimension
import com.rk_softwares.e_commerce.model.DataReviews
import com.rk_softwares.e_commerce.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetDialog(
    showDialog : Boolean = false,
    topText : String = "N/A",
    text : String = "N/A",
    brandText: String = "No Brand",
    warrantyText : String = "No warranty",
    weightG : Int = 0,
    dimension : DataDimension = DataDimension(),
    imageUrl: String = "",
    totalRatingCount: Double = 0.0,
    reviews : List<DataReviews> = emptyList(),
    onDismiss : () -> Unit = {}
) {

    if (!showDialog) return
    
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    ModalBottomSheet(
        onDismissRequest = {onDismiss()},
        sheetState = sheetState,
        containerColor = Color(0xFFFFFFFF)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 7.dp, end = 7.dp, bottom = 7.dp)

        ) {

            //top text
            Text(topText,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W500,
                color = Color(0xFF000000),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(10.dp))

            when(topText){

                "Delivery" -> Delivery(currentLocation = "Madhabdi", deliveryPrice = 20, deliveryTime = text)
                "Service" -> Service(returnText = text)
                "Specifications" -> Specification(bText = brandText, wText = warrantyText, weight = weightG, dimensions = dimension)
                "Review" -> Review(totalRatingCount = totalRatingCount, list = reviews, imageUrl = imageUrl)
                else -> null

            }

            Spacer(modifier = Modifier.height(10.dp))

        }//column

    }// dialog

}//fun end

//@Preview(showBackground = true)
@Composable
fun Delivery(
    currentLocation : String = "Location",
    deliveryPrice : Int = 0,
    deliveryTime : String = "Ship in"
){

    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        
        Box(

            modifier = Modifier.fillMaxWidth().padding(7.dp)

        ) {
            
            Text("Deliver To",
                fontSize = 15.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xFF000000),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterStart)
                )

            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
            ) {

                Icon( painter = painterResource(R.drawable.ic_location),
                    contentDescription = "",
                    tint = Color(0xFF3F51B5),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .size(20.dp)
                )

                Text(currentLocation,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF343131),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp)
                    )

            }//row

        }//box

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = Color(0x70FFE4E4))
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Delivery Fee",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W500,
            color = Color(0xFF000000),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .padding(start = 7.dp, top = 5.dp, bottom = 2.dp)
        )

        Box(

            modifier = Modifier.fillMaxWidth().padding(start = 7.dp, end = 7.dp)

        ) {

            Text("Standard Delivery",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF2F2C2C),
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 3.dp, bottom = 2.dp)
                    .align(Alignment.CenterStart)
                )

            Row(

                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 10.dp)
                    .align(Alignment.CenterEnd)

            ) {

                Icon( painter = painterResource(R.drawable.ic_dollar),
                    contentDescription = "",
                    tint = Color(0xFFF3A3434),
                    modifier = Modifier
                        .wrapContentWidth()
                        .size(13.dp)
                        .align(Alignment.CenterVertically)


                )

                Text(deliveryPrice.toString(),
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF3A3434),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                )

            }//row

        }//box

        Text(deliveryTime,
            fontSize = 12.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF695E5E),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .padding(start = 7.dp, top = 2.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(color = Color(0x70FFE4E4))
        )

        Text("Delivery Service",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W500,
            color = Color(0xFF000000),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .padding(start = 7.dp, top = 9.dp, bottom = 3.dp)
        )

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 7.dp, top = 2.dp)
                .align(Alignment.Start)
        ) {

            Image(painter = painterResource(R.drawable.ic_cash),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(12.dp)
            )

            Text("Cash on Delivery available",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF695E5E),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 5.dp)

            )

        }//row

    }//column

}//fun end

//@Preview(showBackground = true)
@Composable
fun Service(returnText : String = "Easy return"){

    Column (

        modifier = Modifier.fillMaxWidth().padding(3.dp)

    ) {

        Row(

            modifier = Modifier.fillMaxWidth()

        ) {

            Image( painter = painterResource(R.drawable.ic_box_return),
                contentDescription = "",
                modifier = Modifier
                    .wrapContentWidth()
                    .size(22.dp)
                    .align(Alignment.CenterVertically)

            )

            Column(

                modifier = Modifier.fillMaxWidth().padding(start = 10.dp)

            ) {

                Text(returnText,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .wrapContentWidth()
                        

                )

                Text("Daraz guarantees that all purchased products are genuine, brand new and not defective.",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF655858),
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 7.dp)
                )

            }//column

        }//row

    }// column

}//fun end

//@Preview(showBackground = true)
@Composable
fun Specification(
    bText : String = "No Brand",
    wText : String = "No warranty",
    weight : Int = 0,
    dimensions : DataDimension = DataDimension(),

    ){

    Column(

        modifier = Modifier.fillMaxWidth().padding(7.dp)

    ) {

        //brand
        Text("Brand",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W500,
            color = Color(0xFF000000),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .padding(5.dp)
            )

        Row(

            modifier = Modifier.wrapContentWidth().padding(start = 7.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)

        ) {

            Icon( painter = painterResource(R.drawable.ic_mark),
                contentDescription = "",
                tint = Color(0xFFAF9393),
                modifier = Modifier
                    .wrapContentWidth()
                    .size(12.dp)
                    .align(Alignment.CenterVertically)

            )

            Text(bText,
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF725D5D),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp)
            )

        }//row

        Spacer(modifier = Modifier.height(10.dp))

        //warranty
        Text("Warranty",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W500,
            color = Color(0xFF000000),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .padding(5.dp)
        )

        Row(

            modifier = Modifier.wrapContentWidth().padding(start = 7.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)

        ) {

            Icon( painter = painterResource(R.drawable.ic_mark),
                contentDescription = "",
                tint = Color(0xFFAF9393),
                modifier = Modifier
                    .wrapContentWidth()
                    .size(12.dp)
                    .align(Alignment.CenterVertically)

            )

            Text(wText,
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF725D5D),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp)
            )

        }//row

        Spacer(modifier = Modifier.height(10.dp))

        //weight
        Text("Weight",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W500,
            color = Color(0xFF000000),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .padding(5.dp)
        )

        Row(

            modifier = Modifier.wrapContentWidth().padding(start = 7.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)

        ) {

            Icon( painter = painterResource(R.drawable.ic_mark),
                contentDescription = "",
                tint = Color(0xFFAF9393),
                modifier = Modifier
                    .wrapContentWidth()
                    .size(12.dp)
                    .align(Alignment.CenterVertically)

            )

            Text("$weight Gram",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF725D5D),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp)
            )

        }//row

        Spacer(modifier = Modifier.height(10.dp))

        //weight
        Text("Dimensions",
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.W500,
            color = Color(0xFF000000),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .padding(5.dp)
        )

        //width
        Row(

            modifier = Modifier.wrapContentWidth().padding(start = 7.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)

        ) {

            Icon( painter = painterResource(R.drawable.ic_mark),
                contentDescription = "",
                tint = Color(0xFFAF9393),
                modifier = Modifier
                    .wrapContentWidth()
                    .size(12.dp)
                    .align(Alignment.CenterVertically)

            )

            Text("${dimensions.width} Width",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF725D5D),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp)
            )

        }//row

        //height
        Row(

            modifier = Modifier.wrapContentWidth().padding(start = 7.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)

        ) {

            Icon( painter = painterResource(R.drawable.ic_mark),
                contentDescription = "",
                tint = Color(0xFFAF9393),
                modifier = Modifier
                    .wrapContentWidth()
                    .size(12.dp)
                    .align(Alignment.CenterVertically)

            )

            Text("${dimensions.height} Height",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF725D5D),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp)
            )

        }//row

        //depth
        Row(

            modifier = Modifier.wrapContentWidth().padding(start = 7.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)

        ) {

            Icon( painter = painterResource(R.drawable.ic_mark),
                contentDescription = "",
                tint = Color(0xFFAF9393),
                modifier = Modifier
                    .wrapContentWidth()
                    .size(12.dp)
                    .align(Alignment.CenterVertically)

            )

            Text("${dimensions.depth} Depth",
                fontSize = 12.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF725D5D),
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp)
            )

        }//row

    }//column

}//fun end


@Preview(showBackground = true)
@Composable
fun Review(
    totalRatingCount : Double = 2.0,
    list : List<DataReviews> = emptyList(),
    imageUrl: String = ""
    ){

    var isClick by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        //full rating
        Box(
            modifier = Modifier.fillMaxWidth().padding(7.dp)
        ) {

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0x37E5D9D9))
                    .padding(7.dp)

            ) {

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterStart)
                        .padding(3.dp)
                ) {

                    Text(totalRatingCount.toString(),
                        fontSize = 29.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                    )

                    TotalStarCount(totalRatingCount,
                        rModifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp),

                        iModifier = Modifier
                            .size(20.dp)
                    )

                }//row

                //total review count

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterEnd)
                        .padding(3.dp)
                ) {

                    Text(list.size.toString(),
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF504747),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                            .padding(start = 3.dp)
                    )

                    Text("Reviews",
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF504747),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                            .padding(start = 3.dp)
                    )

                }//row

            }//box

        }//box
        //full rating


        //tab item
        Row(
            modifier = Modifier.wrapContentWidth().padding(7.dp)
        ) {

            Text("All",
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = if (isClick == "All") Color(0xFFFFFFFF) else Color(0xFF000000),
                modifier = Modifier
                    .wrapContentWidth()
                    .background(
                        color = if (isClick == "All") Color(0xFFFF5722) else Color.Transparent,
                        shape = if (isClick == "All") RoundedCornerShape(10.dp) else RoundedCornerShape(0.dp)
                    )
                    .clickable(
                        indication = null,
                        interactionSource = null
                    ){
                        isClick = "All"
                    }
                    .padding(7.dp)
                )

            Text("With images/video",
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = if (isClick == "With images/video") Color(0xFFFFFFFF) else Color(0xFF000000),
                modifier = Modifier
                    .wrapContentWidth()
                    .background(
                        color = if (isClick == "With images/video") Color(0xFFFF5722) else Color.Transparent,
                        shape = if (isClick == "With images/video") RoundedCornerShape(10.dp) else RoundedCornerShape(0.dp)
                    )
                    .clickable(
                        indication = null,
                        interactionSource = null
                    ){
                        isClick = "With images/video"
                    }
                    .padding(7.dp)
            )

            Text("Low rating",
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = if (isClick == "Low rating") Color(0xFFFFFFFF) else Color(0xFF000000),
                modifier = Modifier
                    .wrapContentWidth()
                    .background(
                        color = if (isClick == "Low rating") Color(0xFFFF5722) else Color.Transparent,
                        shape = if (isClick == "Low rating") RoundedCornerShape(10.dp) else RoundedCornerShape(0.dp)
                    )
                    .clickable(
                        indication = null,
                        interactionSource = null
                    ){
                        isClick = "Low rating"
                    }
                    .padding(7.dp)
            )

        }//row
        //tab item

        //genuine review

        Box(

            modifier = Modifier.fillMaxWidth().padding(5.dp)

        ) {

            Row(
                modifier = Modifier.wrapContentWidth().align(Alignment.CenterStart)
            ) {

                Spacer(modifier = Modifier.width(2.dp))

                Icon( painter = painterResource(R.drawable.ic_shield_ok),
                    contentDescription = "",
                    tint = Color(0xFF695656),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .size(14.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text("Genuine Reviews",
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF695656),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)

                    )

                Spacer(modifier = Modifier.width(4.dp))

                Icon( painter = painterResource(R.drawable.ic_question_outline),
                    contentDescription = "",
                    tint = Color(0xFF695656),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                        .size(14.dp)
                )

                
            }//row

            Row(
                modifier = Modifier.wrapContentWidth().align(Alignment.CenterEnd)
            ) {

                Text("Relevance",
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterVertically)
                    )

                Spacer(modifier = Modifier.width(4.dp))

                Icon( painter = painterResource(R.drawable.ic_right),
                    contentDescription = "",
                    tint = Color(0xFF000000),
                    modifier = Modifier
                        .rotate(90f)
                        .size(15.dp)
                        .align(Alignment.CenterVertically)

                )
                Spacer(modifier = Modifier.width(4.dp))

            }//row

        }//box

        //genuine review

        Spacer(modifier = Modifier.height(4.dp))

        HorizontalDivider(
            color = Color(0x6FB29C9C),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(4.dp))

        //review messages
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .verticalScroll(rememberScrollState(), true)
        ) {

            list.forEach { item ->

                ReviewHelper(

                    imageUrl = imageUrl,
                    userName = item.reviewerName,
                    date = item.date,
                    reviewText = item.comment,
                    rating = item.rating
                )

            }

        }//column
        //review messages


    }//column

}//fun end

@Preview(showBackground = true)
@Composable
fun ReviewHelper(
    imageUrl : String = "",
    userImage : String = "",
    userName : String = "Rada krishna",
    date : String = "1/1/2000",
    reviewText : String = "Good Product",
    rating : Int = 3
    ){

    var isLiked by remember { mutableStateOf(false) }
    var likeCount by remember { mutableIntStateOf(1) }
    var commentCount by remember { mutableIntStateOf(1) }
    val bgColor = remember(userName) { getRandomColor(userName) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(7.dp)
    ) {
        
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.wrapContentWidth()
            ) {


                //image box
                Box(
                    modifier = Modifier
                        .width(35.dp)
                        .height(35.dp)
                        .clip(shape = CircleShape)
                        .background(
                            if(userImage.isEmpty()) bgColor else Color.Transparent
                        )
                        .align(Alignment.CenterVertically)
                ) {

                    if(userImage.isNotEmpty()){

                        AsyncImage( model = "",
                            contentDescription = "",
                            placeholder = painterResource(R.drawable.img_loading_daraz),
                            error = painterResource(R.drawable.img_loading_daraz),
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                    }else{

                        val firstChar = userName.trim().firstOrNull()?.uppercase() ?: "?"

                        Text( firstChar.toString(),
                            fontSize = 17.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)

                        )


                    }
                    


                }// box

                //image box

                Spacer(modifier = Modifier.width(9.dp))

                //name and date
                
                Column(
                    modifier = Modifier.wrapContentWidth().align(Alignment.CenterVertically)
                ) {

                    Text(userName,
                        fontSize = 13.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF000000),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width(120.dp)
                            .align(Alignment.Start)
                        )

                    //Spacer(modifier = Modifier.height(1.dp))

                    Text(date,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF947777),
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.Start)
                    )

                }//column

                //name and date

            }//row

            TotalStarCount(rating = rating.toDouble(),
                rModifier = Modifier
                    .align(Alignment.CenterEnd),

                iModifier = Modifier
                    .size(18.dp)
            )

        }//box

        Spacer(modifier = Modifier.height(7.dp))

        Text(reviewText,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF000000)
            )

        Spacer(modifier = Modifier.height(7.dp))

        //main image

        Box(

            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .align(Alignment.Start)

        ) {

            AsyncImage( model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.img_loading_daraz),
                error = painterResource(R.drawable.img_loading_daraz),
                modifier = Modifier
                    .fillMaxSize()

            )

        }//box

        //main image

        //seller replied

        Spacer(modifier = Modifier.height(7.dp))

        Box(
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ) {
            
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0x7EF5F0F0))
                    .padding(5.dp)
            ) {

                Text("Seller Replied:",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xE4000000),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                        .padding(3.dp)
                    )

                Text("আপনার মতামত প্রোডাক্ট এর মান উন্নয়েনে সাহায্য করবে। ধন্যবাদ",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF756A6A),
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start)
                        .padding(3.dp)
                )

            }//column

        }//box

        //seller replied

        Spacer(modifier = Modifier.height(4.dp))

        HorizontalDivider(
            color = Color(0x9FDEBABA),
            thickness = 1.dp
        )

        Spacer(modifier = Modifier.height(5.dp))

        //like and comment

        Box(

            modifier = Modifier.fillMaxWidth().padding(5.dp).align(Alignment.Start)

        ) {

            Row(
                modifier = Modifier.wrapContentWidth()
            ) {

                Icon( painter = painterResource(R.drawable.ic_like),
                    contentDescription = "",
                    tint = Color(0xFF232121),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(18.dp)
                        .clickable(
                            indication = null,
                            interactionSource = null
                        ){
                            if (isLiked){

                                likeCount -= 1
                                
                            }else{

                                likeCount += 1

                            }

                            isLiked = !isLiked

                        }
                )

                Text(likeCount.toString(),
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 7.dp)
                        .alpha(if (likeCount > 0) 1f else 0f)
                    )

                Text("Likes",
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 2.dp)
                        .alpha(if (likeCount > 0) 1f else 0f)
                )

                Spacer(modifier = Modifier.width(30.dp))

                Icon( painter = painterResource(R.drawable.ic_comment),
                    contentDescription = "",
                    tint = Color(0xFF232121),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(18.dp)
                        .clickable(
                            indication = null,
                            interactionSource = null
                        ){

                            commentCount = 1

                        }
                )

                Text(commentCount.toString(),
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 7.dp)
                        .alpha(if (commentCount > 0) 1f else 0f)
                )

                Text("Comment",
                    fontSize = 13.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 2.dp)
                        .alpha(if (commentCount > 0) 1f else 0f)
                )

            }//row

            Icon(painter = painterResource(R.drawable.ic_three_dots_ver),
                contentDescription = "",
                tint = Color(0xFF232121),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(18.dp)

            )


        }//box

        //like and comment

        Spacer(modifier = Modifier.height(5.dp))

        HorizontalDivider(
            color = Color(0x9FDEBABA),
            thickness = 1.dp
        )
        
    }//column

}//fun end

//random color
fun getRandomColor(name : String) : Color{

    val colors = listOf(

        Color(0xFF754EB9),
        Color(0xFF2196F3),
        Color(0xFF03A9F4),
        Color(0xFF00BCD4),
        Color(0xFF009688),
        Color(0xFF4CAF50),
        Color(0xFF80C72E),
        Color(0xFFCDDC39),
        Color(0xFFFFC107),
        Color(0xFFFF9800),
        Color(0xFFFF5722)
    )

    val cleanName = name.trim().lowercase()

    val index = kotlin.math.abs(cleanName.hashCode()) % colors.size

    return colors[index]

}//fun end