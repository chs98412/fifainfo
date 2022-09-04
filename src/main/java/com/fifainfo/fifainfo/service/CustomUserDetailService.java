package com.fifainfo.fifainfo.service;

import com.fifainfo.fifainfo.dto.LoginDTO;
import com.fifainfo.fifainfo.dto.UserDTO;
import com.fifainfo.fifainfo.entity.PlayerInfo;
import com.fifainfo.fifainfo.entity.PlayerList;
import com.fifainfo.fifainfo.entity.User;
import com.fifainfo.fifainfo.exception.AlreadyExsistException;
import com.fifainfo.fifainfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService {

    private final UserRepository userRepository;

    public Long joinUser(UserDTO userDTO) throws RuntimeException {
        // 비밀번호 암호화

//        if (userRepository.countByUserEmail(userDTO.getUserEmail()) > 0) {
//            throw new AlreadyExsistException("이미 존재하는 사용자 입니다.");
//        }
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User newuser=UserDTO.toEntity(userDTO);
        return userRepository.save(newuser).getId();
    }

    public User loginUser(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByUserEmail(loginDTO.getUserEmail());
        if (user.isPresent()) {
            User loginUser = user.get();
            if (loginUser.getPassword().equals(loginDTO.getPassword())) {
                return loginUser;
            }else {
                return new User();
            }
        } else {
            return new User();
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        return userRepository.findByUserEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//
//
//    }
}