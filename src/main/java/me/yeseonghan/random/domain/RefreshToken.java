package me.yeseonghan.random.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "user_no", nullable = false, unique = true)
    private Long userNo;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

//    @Column(name = "expires_at", nullable = false)
//    private LocalDateTime expiresAt;

    public RefreshToken(Long userNo, String refreshToken){
        this.userNo = userNo;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String newRefreshToken){
        this.refreshToken = newRefreshToken;
        return this;
    }

}
