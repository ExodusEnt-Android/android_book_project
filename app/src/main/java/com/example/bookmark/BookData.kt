package com.example.bookmark

//책 이미지랑 타이틀, 내용 get
data class BookData (
    var lastBuildDate: String = "",
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<Items>
)

data class Items(
    var title: String = "",
    var link: String = "",
    var image: String = "",
    var author: String = "",
    var price: String = "",
    var discount : String = "",
    var publisher : String = "",
    var pubdate : String = "",
    var isbn : String = "",
    var description : String = "",
)
