package kr.youngminz.chat.controller

import kr.youngminz.chat.dto.ChatMessageDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class StompChatController(
    private val redisTemplate: RedisTemplate<String, Any>,
) {

    @MessageMapping(value = ["/chat/enter"])
    fun enter(message: ChatMessageDto) {
        val newMessage = message.copy(message = "${message.writer} 님이 채팅방에 참여하였습니다.")
        redisTemplate.convertAndSend("/sub/chat/room/${message.roomId}", newMessage)
    }

    @MessageMapping(value = ["/chat/message"])
    fun message(message: ChatMessageDto) {
        redisTemplate.convertAndSend("/sub/chat/room/${message.roomId}", message)
    }
}
