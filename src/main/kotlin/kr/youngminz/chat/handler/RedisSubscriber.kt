package kr.youngminz.chat.handler

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class RedisSubscriber(
    private val messagingTemplate: SimpMessagingTemplate,
) : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        messagingTemplate.convertAndSend(message.channel.decodeToString(), message.body.decodeToString())
    }
}
