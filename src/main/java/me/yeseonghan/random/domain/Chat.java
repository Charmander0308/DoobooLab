package me.yeseonghan.random.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.yeseonghan.random.dto.AddChatRequest;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_no", updatable = false)
    private Long messageNo;

    @Enumerated(EnumType.STRING)
    private AddChatRequest.MessageType type;

    @Column(name = "room_id", nullable = false)
    private String roomId;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @CreatedDate
    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Chat(AddChatRequest.MessageType type, String roomId, String sender, String message, LocalDateTime time){
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.time = time;
    }



}
