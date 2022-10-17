package kr.youngminz.chat.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ChatController {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/chat")
    fun chat(): String {
        log.info("GET /chat")
        return "chat"
    }
}