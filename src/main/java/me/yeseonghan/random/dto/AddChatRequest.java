package me.yeseonghan.random.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddChatRequest {
    public enum MessageType{
        ENTER, LEAVE, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
