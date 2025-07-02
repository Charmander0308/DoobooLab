package me.yeseonghan.random.service;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.domain.RefreshToken;
import me.yeseonghan.random.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken){
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexcepted refresh token"));
    }
}
