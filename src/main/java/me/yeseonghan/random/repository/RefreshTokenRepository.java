package me.yeseonghan.random.repository;

import me.yeseonghan.random.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserNo(Long userNo);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
