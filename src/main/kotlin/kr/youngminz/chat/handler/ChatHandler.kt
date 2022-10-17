package kr.youngminz.chat.handler

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class ChatHandler : TextWebSocketHandler() {

    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        val list = mutableListOf<WebSocketSession>()
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        log.info("payload : $payload")

        list.forEach { it.sendMessage(message) }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        list.add(session)
        log.info("$session 클라이언트 접속")
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        list.remove(session)
        log.info("$session 클라이언트 접속 해제")
    }

}