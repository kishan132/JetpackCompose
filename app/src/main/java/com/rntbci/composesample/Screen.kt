package com.rntbci.composesample

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TAG = "TestCompose"

//@Preview(showBackground = true, heightDp = 300)
@Composable
fun PreviewItem(onClick: (categery: Category) -> Unit){

    // this will start render all items
    /*Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        getCategoryList().map {item ->
            BlogCategory(img = item.img, title = item.title, subtitle = item.subtitle)
        }
    }*/

    // so, we use lazy column (will render only item which is visible)
    LazyColumn(content = {
        items(getCategoryList()){item ->
            BlogCategory(img = item.img, title = item.title, subtitle = item.subtitle, onClick = onClick)
        }
    })

}


@Composable
fun BlogCategory(img:Int, title:String, subtitle:String, onClick : (categery: Category) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(Category(img,title,subtitle)) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
                    .weight(.2f)
            )
            ItemDescription(title,subtitle,Modifier.weight(.8f))
        }

    }
}

@Composable
private fun ItemDescription(title: String, subtitle: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = subtitle, fontWeight = FontWeight.Thin, fontSize = 12.sp)
    }
}

data class Category(val img: Int, val title: String, val subtitle: String)

fun getCategoryList() : MutableList<Category>{
    val list = mutableListOf<Category>()

    list.add(Category(R.drawable.emp1,"title1","sub1"))
    list.add(Category(R.drawable.emp2,"title2","sub2"))
    list.add(Category(R.drawable.emp3,"title3","sub3"))
    list.add(Category(R.drawable.emp1,"title4","sub4"))
    list.add(Category(R.drawable.emp2,"title5","sub5"))
    list.add(Category(R.drawable.emp3,"title6","sub6"))
    list.add(Category(R.drawable.emp1,"title1","sub1"))
    list.add(Category(R.drawable.emp2,"title2","sub2"))
    list.add(Category(R.drawable.emp3,"title3","sub3"))
    list.add(Category(R.drawable.emp1,"title4","sub4"))
    list.add(Category(R.drawable.emp2,"title5","sub5"))
    list.add(Category(R.drawable.emp3,"title6","sub6"))

    return list
}