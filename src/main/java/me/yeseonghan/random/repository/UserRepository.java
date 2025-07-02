package me.yeseonghan.random.repository;

import me.yeseonghan.random.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //이메일 중복조회
    boolean existsByUserEmail(String userEmail);
    //닉네임 중복조회
    boolean existsByUserNickname(String userNickname); 
    //로그인 시 이메일 조회
    Optional<User> findByUserEmail(String userEmail);
}
