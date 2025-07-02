package me.yeseonghan.random.service;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.domain.Chat;
import me.yeseonghan.random.dto.AddChatRequest;
import me.yeseonghan.random.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository repository;
    private final ChatRepository chatRepository;

    public Chat save(AddChatRequest request){
        Chat chat = new Chat(
                request.getType(),
                request.getRoomId(),
                request.getSender(),
                request.getMessage(),
                LocalDateTime.now()
        );
        return chatRepository.save(chat);
    }
}