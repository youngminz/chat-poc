package kr.youngminz.chat.controller

import kr.youngminz.chat.dto.ChatMessageDto
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class StompChatController(
    private val template: RedisTemplate<String, Any>,
) {

    @MessageMapping(value = ["/chat/enter"])
    fun enter(message: ChatMessageDto) {
        val newMessage = message.copy(message = "${message.writer} 님이 채팅방에 참여하였습니다.")
        template.convertAndSend("/sub/chat/room", newMessage)
    }

    @MessageMapping(value = ["/chat/message"])
    fun message(message: ChatMessageDto) {
        template.convertAndSend("/sub/chat/room", message)
    }
}
