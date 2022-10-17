package kr.youngminz.chat.dto

data class ChatMessageDto(
    val roomId: String,
    val writer: String,
    val message: String?,
)
