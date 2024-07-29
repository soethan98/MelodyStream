package com.soethan.melodystream.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalMaterial3Api
@Composable
fun SortMenu(modifier: Modifier = Modifier) {

    ModalBottomSheet(onDismissRequest = { /*TODO*/ },modifier= modifier) {
        Column(modifier = Modifier.padding(16.dp)){
            Text(
                text = "Sort By", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ), modifier = Modifier.padding(vertical = 8.dp)
            )
            SortBy.entries.forEach { sortItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = false, onClick = { /*TODO*/ })
                    Text(text = sortItem.title)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Order By", style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 16.sp
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OrderBy.entries.forEach { sortItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = false, onClick = { /*TODO*/ })
                    Text(text = sortItem.title)
                }
            }

            Row(modifier = Modifier.fillMaxWidth()){
                ElevatedButton(onClick = { /*TODO*/ },modifier = Modifier.weight(1f)) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(16.dp))
                ElevatedButton(onClick = { /*TODO*/ },
                    modifier = Modifier.weight(1f)) {
                    Text(text = "Apply")
                }
            }
        }
    }

}

enum class SortBy(val title: String) {
    TITLE("Title"),
    ARTIST("Artist"),
    ALBUM("Album"),
    DURATION("Duration"),
    DATE_ADDED("Date added"),
    SIZE("Size"),
    DISPLAY_NAME("Display name")
}

enum class OrderBy(val title: String) {
    ASC_SMALLER("Asc or smaller"),
    DESC_GREAT("Desc or greater")
}