package me.yeseonghan.random.controller;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.config.jwt.JwtProperties;
import me.yeseonghan.random.config.jwt.TokenProvider;
import me.yeseonghan.random.dto.CreateAccessTokenRequest;
import me.yeseonghan.random.dto.CreateAccessTokenResponse;
import me.yeseonghan.random.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;
    private final TokenProvider tokenProvider;

    //기본 엑세스 토큰 발급 메서드
    @PostMapping("/api/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(
            @RequestBody CreateAccessTokenRequest request){
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateAccessTokenResponse(newAccessToken));
    }

}
