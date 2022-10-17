package kr.youngminz.chat.controller

import kr.youngminz.chat.repository.ChatRoomRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(value = ["/chat"])
class RoomController(
    private val repository: ChatRoomRepository,
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping(value = ["/rooms"])
    fun rooms(): ModelAndView {
        log.info("# All Chat Rooms")
        val mv = ModelAndView("chat/rooms")

        mv.addObject("list", repository.findAllRooms())

        return mv
    }

    @PostMapping(value = ["/room"])
    fun create(@RequestParam name: String, rttr: RedirectAttributes): String {
        log.info("# Create Chat Room, name: $name")
        rttr.addFlashAttribute("roomName", repository.createChatRoomDto(name))
        return "redirect:/chat/rooms"
    }

    @GetMapping("/room")
    fun getRoom(roomId: String, model: Model) {
        log.info("# Get Chat Room, roomId : $roomId")
        model.addAttribute("room", repository.findRoomById(roomId))
    }
}