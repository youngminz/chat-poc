package kr.youngminz.chat.dto

import org.springframework.web.socket.WebSocketSession
import java.util.UUID

var id = 1

data class ChatRoomDto(
    val name: String,
    val roomId: String = "${id++}",
    val sessions: MutableSet<WebSocketSession> = mutableSetOf(),
)
