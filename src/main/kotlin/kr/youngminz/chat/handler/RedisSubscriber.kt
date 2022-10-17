package kr.youngminz.chat.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.youngminz.chat.dto.ChatMessageDto
import org.slf4j.LoggerFactory
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class RedisSubscriber(
    private val objectMapper: ObjectMapper,
    private val redisTemplate: RedisTemplate<String, Any>,
    private val messagingTemplate: SimpMessageSendingOperations,
) : MessageListener {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val publishMessage = redisTemplate.stringSerializer.deserialize(message.body)!!
            val msg: ChatMessageDto = objectMapper.readValue(publishMessage)
            messagingTemplate.convertAndSend("/sub/chat/room/${msg.roomId}", msg)
        } catch (e: Throwable) {
            log.error("에러발생", e)
        }
    }

}