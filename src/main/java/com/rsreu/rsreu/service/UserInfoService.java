package com.rsreu.rsreu.service;

import com.rsreu.rsreu.configuration.ApplicationConfig;
import com.rsreu.rsreu.data.entity.RoleInfo;
import com.rsreu.rsreu.data.entity.UserInfo;
import com.rsreu.rsreu.data.repository.RoleInfoRepository;
import com.rsreu.rsreu.data.repository.UserRepository;
import com.rsreu.rsreu.enums.RoleEnum;
import com.rsreu.rsreu.security.jwt.JwtUtils;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final RoleInfoRepository roleInfoRepository;

    private final JwtUtils jwtUtils;

    private final ApplicationConfig config;

    public UserInfo save(UserInfo user) {
        return userRepository.save(user);
    }

    public Cookie getAuthorizeCookie(UserInfo userInfo) {
        String jwtToken = jwtUtils.generateTokenFromEmail(userInfo.getUsername());
        Cookie cookie = new Cookie(config.jwt().headerName(), jwtToken);
        cookie.setPath("/");
        cookie.setMaxAge(Math.toIntExact(config.jwt().expiration() / 100));
        return cookie;
    }

    @Transactional
    public UserInfo signIn(String username, String password) {
        Optional<UserInfo> userInfoOptional = userRepository.findByUsername(username);
        if (userInfoOptional.isPresent()) {
            UserInfo userInfo = userInfoOptional.get();
            if (passwordEncoder.matches(password, userInfo.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userInfo.getUsername(), password)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return userInfo;
            }
        }
        return null;
    }

    @Transactional
    public UserInfo signUp(String username, String password) {
        UserInfo user = new UserInfo()
                .setUsername(username)
                .setPassword(passwordEncoder.encode(password));

        RoleInfo userRole = roleInfoRepository.findByName(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.getRoles().add(userRole);
        user = userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }
}
