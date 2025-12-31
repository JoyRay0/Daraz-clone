package com.rk_softwares.e_commerce.ComposeUi

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
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
import com.rk_softwares.e_commerce.model.DataDimension
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
fun Review(totalRatingCount : Double = 2.0, totalReviewCount : Int = 0){

    var isClick by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(
            modifier = Modifier.fillMaxWidth().padding(7.dp)
        ) {

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color(0x57E5D9D9))
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

                    Text(totalReviewCount.toString(),
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
                    .clickable{
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
                    .clickable{
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
                    .clickable{
                        isClick = "Low rating"
                    }
                    .padding(7.dp)
            )

        }//row

    }//column

}//fun end