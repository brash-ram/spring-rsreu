package com.rsreu.rsreu.controller;

import com.rsreu.rsreu.configuration.ApplicationConfig;
import com.rsreu.rsreu.data.entity.RoleInfo;
import com.rsreu.rsreu.data.entity.UserInfo;
import com.rsreu.rsreu.enums.RoleEnum;
import com.rsreu.rsreu.service.UserInfoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserInfoService userInfoService;
    private final ApplicationConfig config;

    @GetMapping("/")
    public ModelAndView loginPage() {
        ModelAndView view = new ModelAndView("login");
        view.addObject("userInfo", new UserInfo());
        return view;
    }

    @PostMapping("/auth/signIn")
    public Object signIn(
            @ModelAttribute @Valid UserInfo user,
            BindingResult result,
            HttpServletResponse response,
            HttpSession session
    ) throws IOException {
        if (result.hasErrors()) {
            return "login";
        }
        UserInfo userInfo = userInfoService.signIn(user.getUsername(), user.getPassword());
        if (userInfo == null) {
            result.rejectValue("username", "", "Неверное значение");
            return "login";
        }

        session.setAttribute("user", userInfo);
        response.addCookie(userInfoService.getAuthorizeCookie(userInfo));

        List<RoleEnum> roles = userInfo.getEnumRoles();

        if (roles.contains(RoleEnum.ADMIN)) {
            response.sendRedirect("/school/addSchool");
        } else {
            response.sendRedirect("/listSchools");
        }
        return null;
    }

    @PostMapping("/auth/signUp")
    public Object signUp(
            @ModelAttribute @Valid UserInfo user,
            BindingResult result,
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session
    ) throws IOException {
        if (result.hasErrors()) {
            return "login";
        }
        UserInfo userInfo;
        try {
            userInfo = userInfoService.signUp(user.getUsername(), user.getPassword());
            session.setAttribute("user", userInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.rejectValue("username", "", "Неверное значение");
            return "login";
        }

        response.addCookie(userInfoService.getAuthorizeCookie(userInfo));
        response.sendRedirect("/listSchools");
        return null;
    }

    @GetMapping("/auth/logout")
    public void logout(
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session
    ) throws IOException {
        Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies())
                .filter(c -> config.jwt().headerName().equals(c.getName()))
                .findAny();
        if (cookieOptional.isPresent()) {
            Cookie cook = userInfoService.getCookie(config.jwt().headerName(), null, 0);
            response.addCookie(cook);
            session.invalidate();
            response.sendRedirect("/");
        }
    }
}
