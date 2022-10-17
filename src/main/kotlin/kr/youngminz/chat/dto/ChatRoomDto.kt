package kr.youngminz.chat.dto

import org.springframework.web.socket.WebSocketSession
import java.util.UUID

data class ChatRoomDto(
    val name: String,
    val roomId: String = UUID.randomUUID().toString(),
    val sessions: MutableSet<WebSocketSession> = mutableSetOf(),
)
