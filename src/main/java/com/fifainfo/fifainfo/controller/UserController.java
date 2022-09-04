package com.fifainfo.fifainfo.controller;

import com.fifainfo.fifainfo.dto.UserDTO;
import com.fifainfo.fifainfo.entity.User;
import com.fifainfo.fifainfo.repository.UserRepository;
import com.fifainfo.fifainfo.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
//    @Autowired
//    private final JwtTokenProvider jwtTokenProvider;
//    @Autowired
//    private final CustomUserDetailService userDetailService;
//    @Autowired
//    private AuthenticationManager authenticationManager;

    final String BIRTH = "001200";
    final String EMAIL = "aabbcc@gmail.com";
    final String NICKNAME = "침착맨";
    final Long SEQUENCEID = Long.valueOf(1);


@GetMapping("test")
public String test() {
    return "잘 동작 하는중!";
}

//
//    @PostMapping("/join")
//    public String join(@RequestBody UserDTO dto){
//        System.out.println(dto);
//
//        userDetailService.joinUser(dto);
//
//
//        return dto.toString();
//
//    }

    // 로그인
//    @PostMapping("/login")
//    public String login(UserDTO userDTO) {
//        log.info("user email = {}", userDTO.getUserEmail());
//        User user = userDetailService.findByUserEmail(user.get("email"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
//
//        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//    }

//    @PostMapping(value = "/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO dto) throws Exception {
//        System.out.println(dto.getUserEmail());
//        System.out.println(dto.getPassword());
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        Authentication authentication = authenticate(dto.getUserEmail(),dto.getPassword());
//        User user = (User) authentication.getPrincipal();
//        final String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
//        return ResponseEntity.ok(token);
//    }
//
//    private Authentication authenticate(String username, String password) throws Exception {
//        try {
//            System.out.println(password);
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username, password);
//            System.out.println(usernamePasswordAuthenticationToken.getPrincipal());
//            System.out.println(usernamePasswordAuthenticationToken.getCredentials());
//            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        }
//    }
}