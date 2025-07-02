package me.yeseonghan.random.service;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.config.jwt.TokenProvider;
import me.yeseonghan.random.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken){
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalArgumentException("Unexpect refreshToken");
        }

        Long userNo = refreshTokenService.findByRefreshToken(refreshToken).getUserNo();
        User user = userService.findByUserNo(userNo);

        return tokenProvider.generateToken(user, Duration.ofHours(1));
    }
}
