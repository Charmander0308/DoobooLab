package me.yeseonghan.random.controller;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.domain.Chat;
import me.yeseonghan.random.dto.AddChatRequest;
import me.yeseonghan.random.repository.ChatRepository;
import me.yeseonghan.random.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessagingTemplate template;
    private final ChatService service;
    private final ChatRepository chatRepository;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(AddChatRequest request){
        Chat saved = service.save(request);
        template.convertAndSend("/topic/ch at/"+saved.getRoomId(), saved);
    }

    @GetMapping("/history/{roomId}")
    @ResponseBody
    public List<Chat> getChatHistory(@PathVariable String roomId){
        return chatRepository.findByRoomId(roomId);
    }

}
