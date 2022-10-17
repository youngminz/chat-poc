package kr.youngminz.chat.repository

import kr.youngminz.chat.dto.ChatRoomDto
import org.springframework.stereotype.Repository

@Repository
class ChatRoomRepository {

    private val chatRoomDtoMap = mutableMapOf<String, ChatRoomDto>()

    fun findAllRooms(): List<ChatRoomDto> {
        return chatRoomDtoMap.values.reversed()
    }

    fun findRoomById(id: String): ChatRoomDto {
        return chatRoomDtoMap[id]!!
    }

    fun createChatRoomDto(name: String): ChatRoomDto {
        val room = ChatRoomDto(name)
        chatRoomDtoMap[room.roomId] = room
        return room
    }
}