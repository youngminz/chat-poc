package kr.youngminz.chat.controller

import kr.youngminz.chat.dto.ChatMessageDto
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class StompChatController(
    private val template: SimpMessagingTemplate,
) {

    @MessageMapping(value = ["/chat/enter"])
    fun enter(message: ChatMessageDto) {
        val newMessage = message.copy(message = "${message.writer} 님이 채팅방에 참여하였습니다.")
        template.convertAndSend("/sub/chat/room/${message.roomId}", newMessage)
    }

    @MessageMapping(value = ["/chat/message"])
    fun message(message: ChatMessageDto) {
        template.convertAndSend("/sub/chat/room/${message.roomId}", message)
    }

}
