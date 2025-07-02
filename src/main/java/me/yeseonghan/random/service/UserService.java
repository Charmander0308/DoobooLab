package me.yeseonghan.random.service;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.dto.AddUserRequest;
import me.yeseonghan.random.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import me.yeseonghan.random.domain.User;

import java.time.LocalDate;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    //가입정보 저장
    public Long save(AddUserRequest dto){
        if (dto.getUserPassword() == null) {
            throw new IllegalArgumentException("비밀번호는 필수 입력 항목입니다.");
        }
        if (dto.getUserEmail() == null || dto.getUserEmail().isEmpty()) {
            throw new IllegalArgumentException("이메일은 필수 입력 항목입니다.");
        }
        if (dto.getUserNickname() == null || dto.getUserNickname().isEmpty()) {
            throw new IllegalArgumentException("닉네임은 필수 입력 항목입니다.");
        }
        if (dto.getUserName() == null || dto.getUserName().isEmpty()) {
            throw new IllegalArgumentException("이름은 필수 입력 항목입니다.");
        }
        if (dto.getUserBirth() == null) {
            throw new IllegalArgumentException("생년월일은 필수 입력 항목입니다.");
        }
        if (dto.getUserPhoneNumber() == null || dto.getUserPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("전화번호는 필수 입력 항목입니다.");
        }

        User user = User.builder()
                .userEmail(dto.getUserEmail())
                .userNickname(dto.getUserNickname())
                .userName(dto.getUserName())
                .userBirth(LocalDate.parse(dto.getUserBirth()))
                .userPhoneNumber(dto.getUserPhoneNumber())
                .userPassword(encoder.encode(dto.getUserPassword()))
                .build();
        try {
            return userRepository.save(user).getUserNo();
        } catch(Exception e){
            System.err.println("사용자 저장 중 오류 발생 : " + e.getMessage());
            throw e;
        }
    }

    //유저번호로 유저찾기
    public User findByUserNo(Long userNo){
        return userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("Unexcepted user"));
    }

    //유저이메일로 유저찾기
    public User findByUserEmail(String userEmail){
        return userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Unexcepted user") );
    }

    //닉네임 중복 확인하기
    public Map<String, Boolean> checkNicknameDuplicate(String userNickname) {
        boolean exists = userRepository.existsByUserNickname(userNickname);
        return Map.of("duplicate", exists);
    }

    //이메일 중복 확인하기
    public Map<String, Boolean> checkEmailDuplicate(String userEmail) {
        boolean exists = userRepository.existsByUserEmail(userEmail);
        return Map.of("duplicate", exists);
    }
}
