package me.yeseonghan.random.service;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.domain.User;
import me.yeseonghan.random.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    //이메일로 유저정보 가져오기
    @Override
    public User loadUserByUsername(String userEmail){
        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
