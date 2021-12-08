package com.example.notesapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Long = System.currentTimeMillis(),

//    @ColumnInfo(name = "title")
    var title:String? = null,

//    @ColumnInfo(name = "sub_title")
    var subTitle:String? = null,

//    @ColumnInfo(name = "date_time")
    var dateTime:String? = null,

//    @ColumnInfo(name = "note_text")
    var noteText:String? = null,

//    @ColumnInfo(name = "img_path")
    var imgPath:String? = null,

//    @ColumnInfo(name = "web_link")
    var webLink:String? = null,

//    @ColumnInfo(name = "color")
    var color:String? = null
)

//    override fun toString(): String {
//
//        return "$title : $dateTime"
//    }

