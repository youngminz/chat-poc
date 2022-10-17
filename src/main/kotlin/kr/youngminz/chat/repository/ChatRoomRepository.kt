package kr.youngminz.chat.repository

import kr.youngminz.chat.dto.ChatRoomDto
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class ChatRoomRepository {

    private val chatRoomDtoMap = ConcurrentHashMap<String, ChatRoomDto>()

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