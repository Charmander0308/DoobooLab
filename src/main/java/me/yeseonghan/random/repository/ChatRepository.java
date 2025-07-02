package me.yeseonghan.random.repository;

import me.yeseonghan.random.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByRoomId(String roomId);
}
