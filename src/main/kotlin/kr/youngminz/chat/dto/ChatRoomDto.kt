package kr.youngminz.chat.dto

var id = 1

data class ChatRoomDto(
    val name: String,
    val roomId: String = "${id++}",
)
